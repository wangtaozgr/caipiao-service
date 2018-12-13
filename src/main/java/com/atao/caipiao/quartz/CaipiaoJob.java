package com.atao.caipiao.quartz;

import javax.annotation.Resource;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.atao.caipiao.service.CaipiaoWyService;
import com.atao.caipiao.service.CpRecordWyService;

@Component
public class CaipiaoJob {
	@Resource
	private CaipiaoWyService caipiaoWyService;
	@Resource
	private CpRecordWyService cpRecordWyService;

	@Scheduled(cron = "50 0/1 * * * ?")
	public void retriveTodayCaiPiaoKaijianInfo() {
		caipiaoWyService.retriveTodayCaiPiaoKaijianInfo();
		cpRecordWyService.kaijian();
	}

	@Scheduled(cron = "0 3,13,23,43,53 10,11,12,13,14,15,16,17,18,19,20,21 * * ?")
	public void zhuihao10() {
		caipiaoWyService.zhuihao();
	}

	@Scheduled(cron = "0 2,7,12,17,22,27,32,37,42,47,52,57 0,1,22,23 * * ?")
	public void zhuihao5() {
		caipiaoWyService.zhuihao();
	}

	@Scheduled(cron = "0 8,18,28,38,48,58 10,11,12,13,14,15,16,17,18,19,20,21 * * ?")
	public void buy10() {
		cpRecordWyService.buy();
	}

	@Scheduled(cron = "30 3,8,13,18,23,28,33,38,43,48,53,58 0,1,22,23 * * ?")
	public void buy5() {
		cpRecordWyService.buy();
	}
}