package com.atao.caipiao.web;

import com.atao.base.controller.BaseController;
import com.atao.base.service.BaseService;

import com.atao.caipiao.model.CpZhuihao;
import com.atao.caipiao.service.CpZhuihaoWyService;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author twang
 */
@RestController
@RequestMapping("/cp/zhrecord")
public class CpZhuihaoController extends BaseController<CpZhuihao> {
	public static final String BASE_URL = "/CpZhuihao/";

	@Autowired
	private CpZhuihaoWyService cpZhuihaoWyService;

	@Override
	protected BaseService<CpZhuihao> getService() {
		return cpZhuihaoWyService;
	}

	@RequestMapping("/myRecords")
	public Object myRecords() {
		return cpZhuihaoWyService.queryZhPage();
	}
}
