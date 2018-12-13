package com.atao.caipiao.web;

import com.atao.base.controller.BaseController;
import com.atao.base.service.BaseService;

import com.atao.caipiao.model.Caipiao;
import com.atao.caipiao.service.CaipiaoWyService;
import com.atao.caipiao.service.CpZhuihaoWyService;

import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author twang
 */
@RestController
@RequestMapping("/cp")
public class CaipiaoController extends BaseController<Caipiao> {
	private Logger logger = LoggerFactory.getLogger(getClass());
	@Autowired
	private CaipiaoWyService caipiaoWyService;
	@Resource
	private CpZhuihaoWyService cpZhuihaoWyService;

	@Override
	protected BaseService<Caipiao> getService() {
		return caipiaoWyService;
	}

	public double totaoPrice = 200d;
	public int day = 181111;
	public int bs = 3;

	
	
	@RequestMapping("/check")
	public Object check(int day) {
		List<Caipiao> list = caipiaoWyService.queryByDate(day);
		list = list.subList(8,list.size()-1);
		for(Caipiao cp : list) {
			List<Caipiao> cps = caipiaoWyService.queryLastByCode(cp.getCode(), 7);
			for (int w = 1; w < 6; w++) {
				for (int type = 1; type < 5; type++) {
					boolean success = caipiaoWyService.isPay(cps, w, type);
					if (success) {
						logger.info("插入要购买的期数，追号的  code={}", cp.getCode() + 1);
						cpZhuihaoWyService.zhuihao(cp.getCode() + 1, w, type);
					}else {
						//logger.info("code={}|w={}|type={}没有可以购买的", cp.getCode() + 1, w, type);

					}
				}
			}
		}
		return null;
	}
	
	
	@RequestMapping("/start")
	public Object start(int day) {
		totaoPrice = 200d;
		List<Caipiao> cps = caipiaoWyService.queryByDate(day);
		logger.info("我的余额={}元", totaoPrice);

		int num = 0;
		boolean start = false;
		int c = 0;
		for (Caipiao cp : cps) {
			if (!start) {
				if (cp.getWan() > 4) {
					num++;
					if (num > 4) {
						logger.info("5次大了，开始。");
						start = true;
						num = 0;
					}
				} else {
					num = 0;
				}
			} else {
				boolean zj = kaijian(cp, Math.pow(bs, c));
				if (!zj) {
					if (c > 3) {
						start = false;
						num = 0;
						c = 0;
					}
					c++;
				} else {
					start = false;
					num = 0;
					c = 0;
				}
			}
		}

		/*
		 * int i = 1; int num=0; for(Caipiao cp : cps) { //if(totaoPrice<0) {
		 * logger.info("第{}次",i); logger.info("我的余额={}元",totaoPrice); //} i++; boolean
		 * zj = kaijian(cp, Math.pow(3,num)); if(!zj) { num ++; if(num>2) num = 0; }else
		 * { num = 0; } }
		 */
		return null;
	}
	
	@RequestMapping("/start02")
	public Object start02(int day) {
		totaoPrice = 200d;
		List<Caipiao> cps = caipiaoWyService.queryByDate(day);
		logger.info("我的余额={}元", totaoPrice);

		int num = 0;
		boolean start = false;
		int c = 0;
		for (Caipiao cp : cps) {
			if (!start) {
				if (cp.getWan() < 5) {
					num++;
					if (num > 4) {
						logger.info("5次小了，开始。");
						start = true;
						num = 0;
					}
				} else {
					num = 0;
				}
			} else {
				boolean zj = kaijian02(cp, Math.pow(bs, c));
				if (!zj) {
					if (c > 3) {
						start = false;
						num = 0;
						c = 0;
					}
					c++;
				} else {
					start = false;
					num = 0;
					c = 0;
				}
			}
		}
		start02(day);
		start03(day);
		start04(day);

		/*
		 * int i = 1; int num=0; for(Caipiao cp : cps) { //if(totaoPrice<0) {
		 * logger.info("第{}次",i); logger.info("我的余额={}元",totaoPrice); //} i++; boolean
		 * zj = kaijian(cp, Math.pow(3,num)); if(!zj) { num ++; if(num>2) num = 0; }else
		 * { num = 0; } }
		 */
		return null;
	}
	
	@RequestMapping("/start03")
	public Object start03(int day) {
		totaoPrice = 200d;
		List<Caipiao> cps = caipiaoWyService.queryByDate(day);
		logger.info("我的余额={}元", totaoPrice);

		int num = 0;
		boolean start = false;
		int c = 0;
		for (Caipiao cp : cps) {
			if (!start) {
				if (cp.getWan() % 2 == 0) {
					num++;
					if (num > 4) {
						logger.info("5次偶数，开始。");
						start = true;
						num = 0;
					}
				} else {
					num = 0;
				}
			} else {
				boolean zj = kaijian03(cp, Math.pow(bs, c));
				if (!zj) {
					if (c > 3) {
						start = false;
						num = 0;
						c = 0;
					}
					c++;
				} else {
					start = false;
					num = 0;
					c = 0;
				}
			}
		}
		return null;
	}
	
	@RequestMapping("/start04")
	public Object start04(int day) {
		totaoPrice = 200d;
		List<Caipiao> cps = caipiaoWyService.queryByDate(day);
		logger.info("我的余额={}元", totaoPrice);

		int num = 0;
		boolean start = false;
		int c = 0;
		for (Caipiao cp : cps) {
			if (!start) {
				if (cp.getWan() % 2 != 0) {
					num++;
					if (num > 4) {
						logger.info("5次奇数，开始。");
						start = true;
						num = 0;
					}
				} else {
					num = 0;
				}
			} else {
				boolean zj = kaijian04(cp, Math.pow(bs, c));
				if (!zj) {
					if (c > 3) {
						start = false;
						num = 0;
						c = 0;
					}
					c++;
				} else {
					start = false;
					num = 0;
					c = 0;
				}
			}
		}
		return null;
	}

	private boolean kaijian(Caipiao cp, double price) {
		boolean zj = true;
		if (cp.getWan() < 5) {
			totaoPrice = totaoPrice + price * 0.95;
			logger.info("中奖，获得金额={}元", price * 0.95);
			zj = true;
		} else {
			totaoPrice = totaoPrice - price;
			logger.info("未中奖，失去金额={}元", price);
			zj = false;
		}
		logger.info("我的余额={}元", totaoPrice);
		return zj;
	}
	
	private boolean kaijian02(Caipiao cp, double price) {
		boolean zj = true;
		if (cp.getWan() > 4 ) {
			totaoPrice = totaoPrice + price * 0.95;
			logger.info("中奖，获得金额={}元", price * 0.95);
			zj = true;
		} else {
			totaoPrice = totaoPrice - price;
			logger.info("未中奖，失去金额={}元", price);
			zj = false;
		}
		logger.info("我的余额={}元", totaoPrice);
		return zj;
	}
	
	private boolean kaijian03(Caipiao cp, double price) {
		boolean zj = true;
		if (cp.getWan() % 2 != 0 ) {
			totaoPrice = totaoPrice + price * 0.95;
			logger.info("中奖，获得金额={}元", price * 0.95);
			zj = true;
		} else {
			totaoPrice = totaoPrice - price;
			logger.info("未中奖，失去金额={}元", price);
			zj = false;
		}
		logger.info("我的余额={}元", totaoPrice);
		return zj;
	}
	
	private boolean kaijian04(Caipiao cp, double price) {
		boolean zj = true;
		if (cp.getWan() % 2 == 0 ) {
			totaoPrice = totaoPrice + price * 0.95;
			logger.info("中奖，获得金额={}元", price * 0.95);
			zj = true;
		} else {
			totaoPrice = totaoPrice - price;
			logger.info("未中奖，失去金额={}元", price);
			zj = false;
		}
		logger.info("我的余额={}元", totaoPrice);
		return zj;
	}


	public int getDay() {
		return day;
	}


	public void setDay(int day) {
		this.day = day;
	}

}
