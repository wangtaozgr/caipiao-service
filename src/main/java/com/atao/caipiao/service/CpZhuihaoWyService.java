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

import com.atao.base.mapper.BaseMapper;
import com.atao.base.service.BaseService;
import com.atao.base.support.MyPage;
import com.atao.caipiao.mapper.CpZhuihaoMapper;
import com.atao.caipiao.model.CpRecord;
import com.atao.caipiao.model.CpZhuihao;

import tk.mybatis.mapper.weekend.Weekend;
import tk.mybatis.mapper.weekend.WeekendCriteria;

/**
 *
 * @author twang
 */
@Service
public class CpZhuihaoWyService extends BaseService<CpZhuihao> {
	private Logger logger = LoggerFactory.getLogger(getClass());

	@Resource
	private CpZhuihaoMapper cpZhuihaoMapper;

	@Resource
	private CpRecordWyService cpRecordWyService;

	@Override
	public BaseMapper<CpZhuihao> getMapper() {
		return cpZhuihaoMapper;
	}

	@Transactional
	public boolean zhuihao(int code, int w, int type) {
		if (existZh(w, type)) {
			logger.info("已经存在追号的组别,w={}|type={}", w, type);
			return false;
		}
		CpZhuihao zh = new CpZhuihao();
		zh.setW(w);
		zh.setType(type);
		zh.setCode(code);
		CpZhuihao exists = super.queryOne(zh, null);
		if (exists == null) {
			zh.setCreateTime(new Date());
			zh.setStatus(0);
			zh.setBuynum(0);
			super.insert(zh);
			return true;
		} else {
			logger.info("已经存在追号的组别,code={}|w={}|type={}", code, w, type);
			return true;
		}
	}

	public boolean existZh(int w, int type) {
		CpZhuihao zh = new CpZhuihao();
		zh.setW(w);
		zh.setType(type);
		zh.setStatus(0);
		CpZhuihao exists = super.queryOne(zh, null);
		if (exists == null)
			return false;
		return true;
	}

	public List<CpZhuihao> queryNeedBuy() {
		CpZhuihao p = new CpZhuihao();
		p.setStatus(0);
		return super.queryList(p, null);
	}

	public MyPage<CpZhuihao> queryZhPage() {
		CpZhuihao zh = new CpZhuihao();
		zh.setRows(90);
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("sorts", "code=1");
		MyPage<CpZhuihao> page = super.queryPage(zh, params);
		for (CpZhuihao z : page.getList()) {
			CpRecord cr = new CpRecord();
			cr.setZhId(z.getZhId());
			Map<String, Object> p = new HashMap<String, Object>();
			p.put("sorts", "code=0");
			List<CpRecord> records = cpRecordWyService.queryList(cr, p);
			z.setRecords(records);
		}
		return page;
	}

	@Override
	public Weekend<CpZhuihao> genSqlExample(CpZhuihao t) {
		Weekend<CpZhuihao> w = super.genSqlExample(t);
		WeekendCriteria<CpZhuihao, Object> c = w.weekendCriteria();
		if (t.getCode() != null) {
			c.andEqualTo(CpZhuihao::getCode, t.getCode());
		}
		if (t.getStatus() != null) {
			c.andEqualTo(CpZhuihao::getStatus, t.getStatus());
		}
		if (t.getW() != null) {
			c.andEqualTo(CpZhuihao::getW, t.getW());
		}
		if (t.getType() != null) {
			c.andEqualTo(CpZhuihao::getType, t.getType());
		}
		w.and(c);
		return w;
	}

}
