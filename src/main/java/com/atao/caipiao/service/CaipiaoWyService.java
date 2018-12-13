package com.atao.caipiao.service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.atao.base.mapper.BaseMapper;
import com.atao.base.service.BaseService;
import com.atao.base.util.DateUtils;
import com.atao.caipiao.http.ChunqiuHttp;
import com.atao.caipiao.mapper.CaipiaoMapper;
import com.atao.caipiao.model.Caipiao;
import com.atao.caipiao.util.HttpUrlConnectUtils;
import com.atao.util.StringUtils;
import com.github.pagehelper.PageHelper;

import tk.mybatis.mapper.weekend.Weekend;
import tk.mybatis.mapper.weekend.WeekendCriteria;

/**
 *
 * @author twang
 */
@Service
public class CaipiaoWyService extends BaseService<Caipiao> {
	private Logger logger = LoggerFactory.getLogger(getClass());

	@Resource
	private CaipiaoMapper caipiaoMapper;

	@Resource
	private CpZhuihaoWyService cpZhuihaoWyService;

	@Override
	public BaseMapper<Caipiao> getMapper() {
		return caipiaoMapper;
	}

	@Transactional
	public void retriveTodayCaiPiaoKaijianInfo() {
		logger.info("开始获取彩票开奖信息.");
		JSONArray data = ChunqiuHttp.retriveTodayCaiPiaoKaijianInfo();
		for (int i = 0; i < data.size(); i++) {
			JSONArray array = data.getJSONArray(i);
			Caipiao cp = super.queryById(array.getString(0));
			if (cp == null) {
				cp = new Caipiao();
				cp.setCode(array.getIntValue(0));
				String kaijianNum = array.getString(1);
				cp.setWan(getNum(kaijianNum, 5));
				cp.setQian(getNum(kaijianNum, 4));
				cp.setBai(getNum(kaijianNum, 3));
				cp.setShi(getNum(kaijianNum, 2));
				cp.setGe(getNum(kaijianNum, 1));
				cp.setQishu(array.getIntValue(0) / 1000);
				super.insert(cp);
			}
		}
		logger.info("结束获取彩票开奖信息.");
	}

	public List<Caipiao> queryAll() {
		Caipiao p = new Caipiao();
		Map<String, Object> orderP = new HashMap<String, Object>();
		orderP.put("sorts", "code=0");
		return super.queryList(p, orderP);
	}

	public List<Caipiao> queryByDate(int day) {
		Caipiao p = new Caipiao();
		p.setQishu(day);
		Map<String, Object> orderP = new HashMap<String, Object>();
		orderP.put("sorts", "code=0");
		return super.queryList(p, orderP);
	}

	public List<Caipiao> queryByDate(int day, int pageSize) {
		Caipiao p = new Caipiao();
		p.setQishu(day);
		p.setRows(pageSize);
		Map<String, Object> orderP = new HashMap<String, Object>();
		orderP.put("sorts", "code=1");
		return super.queryPage(p, orderP).getList();
	}

	public List<Caipiao> queryLastByCode(int code, int pageSize) {
		PageHelper.startPage(1, pageSize);
		return caipiaoMapper.queryLastByCode(code);
	}

	@Transactional
	public void zhuihao() {
		int day = Integer.valueOf(DateUtils.formatDate(new Date(), "yyMMdd"));
		/*
		 * String hour = DateUtils.formatDate(new Date(), "HH"); String min =
		 * DateUtils.formatDate(new Date(), "mm"); int h = Integer.valueOf(hour); int m
		 * = Integer.valueOf(min); int startQs = 24 + (h - 10) * 6 + m / 10; int code =
		 * day * 1000 + startQs;
		 */
		List<Caipiao> cps = queryByDate(day, 10);
		if (cps.size() < 10) {
			logger.info("还未开奖到5期，不再查询");
			return;
		}
		int code = cps.get(0).getCode();
		int kjCode = getMaxNewCode(new Date());
		logger.info("当前最后一期开奖 code={}|kjCode={}", code, kjCode);
		if(code==kjCode-1) {
			for (int w = 1; w < 6; w++) {
				for (int type = 1; type < 5; type++) {
					boolean success = isPay(cps, w, type);
					if (success) {
						logger.info("插入要购买的期数，追号的  code={}", code + 1);
						cpZhuihaoWyService.zhuihao(code + 1, w, type);
					} else {
						logger.info("code={}|w={}|type={}没有可以购买的", code + 1, w, type);
					}
				}
			}
		}
	}

	/**
	 * 是否可以购买
	 * 
	 * @param cps
	 * @param w
	 *            1:个位2:十位;3:百位;4:千位;5:万位
	 * @param type
	 *            类型1:大;2:小;3:偶数;4:奇数
	 * @return
	 */
	public boolean isPay(List<Caipiao> cps, int w, int type) {
		boolean success = true;
		if (w == 1) {
			if (type == 1) {
				for (Caipiao cp : cps) {
					if (cp.getGe() < 5)
						return false;
				}
			} else if (type == 2) {
				for (Caipiao cp : cps) {
					if (cp.getGe() > 4)
						return false;
				}
			} else if (type == 3) {
				for (Caipiao cp : cps) {
					if (cp.getGe() % 2 != 0)
						return false;
				}
			} else if (type == 4) {
				for (Caipiao cp : cps) {
					if (cp.getGe() % 2 == 0)
						return false;
				}
			}
		} else if (w == 2) {
			if (type == 1) {
				for (Caipiao cp : cps) {
					if (cp.getShi() < 5)
						return false;
				}
			} else if (type == 2) {
				for (Caipiao cp : cps) {
					if (cp.getShi() > 4)
						return false;
				}
			} else if (type == 3) {
				for (Caipiao cp : cps) {
					if (cp.getShi() % 2 != 0)
						return false;
				}
			} else if (type == 4) {
				for (Caipiao cp : cps) {
					if (cp.getShi() % 2 == 0)
						return false;
				}
			}
		} else if (w == 3) {
			if (type == 1) {
				for (Caipiao cp : cps) {
					if (cp.getBai() < 5)
						return false;
				}
			} else if (type == 2) {
				for (Caipiao cp : cps) {
					if (cp.getBai() > 4)
						return false;
				}
			} else if (type == 3) {
				for (Caipiao cp : cps) {
					if (cp.getBai() % 2 != 0)
						return false;
				}
			} else if (type == 4) {
				for (Caipiao cp : cps) {
					if (cp.getBai() % 2 == 0)
						return false;
				}
			}
		} else if (w == 4) {
			if (type == 1) {
				for (Caipiao cp : cps) {
					if (cp.getQian() < 5)
						return false;
				}
			} else if (type == 2) {
				for (Caipiao cp : cps) {
					if (cp.getQian() > 4)
						return false;
				}
			} else if (type == 3) {
				for (Caipiao cp : cps) {
					if (cp.getQian() % 2 != 0)
						return false;
				}
			} else if (type == 4) {
				for (Caipiao cp : cps) {
					if (cp.getQian() % 2 == 0)
						return false;
				}
			}
		} else if (w == 5) {
			if (type == 1) {
				for (Caipiao cp : cps) {
					if (cp.getWan() < 5)
						return false;
				}
			} else if (type == 2) {
				for (Caipiao cp : cps) {
					if (cp.getWan() > 4)
						return false;
				}
			} else if (type == 3) {
				for (Caipiao cp : cps) {
					if (cp.getWan() % 2 != 0)
						return false;
				}
			} else if (type == 4) {
				for (Caipiao cp : cps) {
					if (cp.getWan() % 2 == 0)
						return false;
				}
			}
		}
		return success;
	}

	private int getNum(String number, int type) {
		int num = 0;
		switch (type) {
		case 1:
			num = Integer.parseInt(number.substring(4));
			break;
		case 2:
			num = Integer.parseInt(number.substring(3, 4));
			break;
		case 3:
			num = Integer.parseInt(number.substring(2, 3));
			break;
		case 4:
			num = Integer.parseInt(number.substring(1, 2));
			break;
		case 5:
			num = Integer.parseInt(number.substring(0, 1));
			break;
		default:
			break;
		}
		return num;
	}

	public int getMaxNewCode(Date date) {// 待开奖号码
		int day = Integer.valueOf(DateUtils.formatDate(date, "yyMMdd"));
		String hour = DateUtils.formatDate(date, "HH");
		String min = DateUtils.formatDate(date, "mm");
		int h = Integer.valueOf(hour);
		int m = Integer.valueOf(min);
		int startQs = 0;
		if (h >= 0 && h <= 1) {
			startQs = 12 * (h) + m / 5 + 1;
		} else if (h >= 10 && h <= 21) {
			startQs = 25 + (h - 10) * 6 + m / 10;
		} else if (h >= 22 && h <= 23) {
			startQs = 97 + 12 * (h - 22) + m / 5;
		}
		int code = day * 1000 + startQs;
		return code;

	}

	@Override
	public Weekend<Caipiao> genSqlExample(Caipiao t) {
		Weekend<Caipiao> w = super.genSqlExample(t);
		WeekendCriteria<Caipiao, Object> c = w.weekendCriteria();
		if (t.getCode() != null) {
			c.andEqualTo(Caipiao::getCode, t.getCode());
		}
		if (t.getQishu() != null) {
			c.andEqualTo(Caipiao::getQishu, t.getQishu());
		}
		w.and(c);
		return w;
	}

	public static void main(String[] args) {

		CaipiaoWyService a = new CaipiaoWyService();
		// a.retriveTodayCaiPiaoKaijianInfo();
		Date date = DateUtils.parseDate("2018-11-20 22:13:30", "yyyy-MM-dd HH:mm:ss");
		System.out.println(a.getMaxNewCode(date));

	}

}
