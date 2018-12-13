package com.atao.caipiao.service;

import com.atao.caipiao.model.Caipiao;
import com.atao.caipiao.model.CpRecord;
import com.atao.caipiao.model.CpZhuihao;
import com.atao.caipiao.model.CpRecord;

import tk.mybatis.mapper.weekend.Weekend;
import tk.mybatis.mapper.weekend.WeekendCriteria;

import com.atao.caipiao.mapper.CpRecordMapper;
import com.atao.base.mapper.BaseMapper;
import com.atao.base.service.BaseService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

/**
 *
 * @author twang
 */
@Service
public class CpRecordWyService extends BaseService<CpRecord> {

	@Resource
	private CpRecordMapper cpRecordMapper;
	@Resource
	private CaipiaoWyService caipiaoWyService;
	@Resource
	private CpZhuihaoWyService cpZhuihaoWyService;

	private static int bs = 3;
	private static int zhCs = 5;

	@Override
	public BaseMapper<CpRecord> getMapper() {
		return cpRecordMapper;
	}

	@Transactional
	public void buy() {
		List<CpZhuihao> zhs = cpZhuihaoWyService.queryNeedBuy();
		for (CpZhuihao zh : zhs) {
			int buynum = zh.getBuynum();
			int code = zh.getCode() + buynum;
			logger.info("要购习的期数code={}", code);
			int kjCode = caipiaoWyService.getMaxNewCode(new Date());
			if (code == kjCode) {
				CpRecord r = new CpRecord();
				r.setZhId(zh.getZhId());
				r.setBuyTime(new Date());
				double buyMoney = Math.pow(bs, buynum);
				r.setBuyMoney(buyMoney);
				int buyNumber = getBuyNumber(zh.getType());
				r.setBuyNumber(buyNumber);
				r.setCode(code);
				r.setStatus(0);
				logger.info("开始购买,code={}|buyMoney={}|buyNumber={}", code, buyMoney, buyNumber);
				super.insert(r);
				logger.info("购买成功,code={}|buyMoney={}|buyNumber={}", code, buyMoney, buyNumber);
				zh.setStatus(1);
				cpZhuihaoWyService.updateBySelect(zh);
			} else {
				logger.info("要购买的期数与待开奖的期数不一致,code={}|kjCode={}", code, kjCode);
				zh.setStatus(3);
				cpZhuihaoWyService.updateBySelect(zh);
			}
		}
	}

	@Transactional
	public void kaijian() {
		CpRecord r = new CpRecord();
		r.setStatus(0);
		List<CpRecord> crs = super.queryList(r, null);
		for (CpRecord cr : crs) {
			Caipiao cp = caipiaoWyService.queryById(cr.getCode());
			if (cp != null) {
				CpZhuihao zh = cpZhuihaoWyService.queryById(cr.getZhId());
				if ((zh.getW().intValue() == 5 && cr.getBuyNumber().toString().contains(cp.getWan().toString()))
						|| (zh.getW().intValue() == 4 && cr.getBuyNumber().toString().contains(cp.getQian().toString()))
						|| (zh.getW().intValue() == 3 && cr.getBuyNumber().toString().contains(cp.getBai().toString()))
						|| (zh.getW().intValue() == 2 && cr.getBuyNumber().toString().contains(cp.getShi().toString()))
						|| (zh.getW().intValue() == 1
								&& cr.getBuyNumber().toString().contains(cp.getGe().toString()))) {
					logger.info("中奖了,code={}", cr.getCode());
					cr.setStatus(1);
					cr.setWinMoney(cr.getBuyMoney() * 0.955);
					super.updateBySelect(cr);
					zh.setBuynum(zh.getBuynum() + 1);
					zh.setStatus(2);
					cpZhuihaoWyService.updateBySelect(zh);
				} else {
					logger.info("未中奖,code={}", cr.getCode());
					cr.setStatus(2);
					cr.setWinMoney(-cr.getBuyMoney());
					super.updateBySelect(cr);
					zh.setBuynum(zh.getBuynum() + 1);
					if (zh.getBuynum() < zhCs) {
						zh.setStatus(0);
					} else {
						zh.setStatus(3);
					}
					cpZhuihaoWyService.updateBySelect(zh);
				}
			} else {
				logger.info("没有找到对应的开奖信息.");
			}
		}

	}

	private int getBuyNumber(int type) {
		int buyNumber = 0;
		switch (type) {
		case 1:
			buyNumber = 43210;
			break;
		case 2:
			buyNumber = 98765;
			break;
		case 3:
			buyNumber = 97531;
			break;
		case 4:
			buyNumber = 86420;
			break;
		default:
			break;
		}
		return buyNumber;

	}

	@Override
	public Weekend<CpRecord> genSqlExample(CpRecord t) {
		Weekend<CpRecord> w = super.genSqlExample(t);
		WeekendCriteria<CpRecord, Object> c = w.weekendCriteria();
		if (t.getCode() != null) {
			c.andEqualTo(CpRecord::getCode, t.getCode());
		}
		if (t.getStatus() != null) {
			c.andEqualTo(CpRecord::getStatus, t.getStatus());
		}
		if (t.getZhId() != null) {
			c.andEqualTo(CpRecord::getZhId, t.getZhId());
		}
		w.and(c);
		return w;
	}

}
