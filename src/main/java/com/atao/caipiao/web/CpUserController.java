package com.atao.caipiao.web;

import com.atao.base.controller.BaseController;
import com.atao.base.service.BaseService;

import com.atao.caipiao.model.CpUser;
import com.atao.caipiao.service.CpUserWyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author twang
 */
@RestController
@RequestMapping("${adminPath}" + CpUserController.BASE_URL)
public class CpUserController extends BaseController<CpUser> {
    public static final String BASE_URL = "/CpUser/";

    @Autowired
    private CpUserWyService cpUserWyService;

    @Override
    protected BaseService<CpUser> getService() {
        return cpUserWyService;
    }
}
