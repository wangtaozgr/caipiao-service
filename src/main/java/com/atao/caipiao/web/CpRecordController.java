package com.atao.caipiao.web;

import com.atao.base.controller.BaseController;
import com.atao.base.service.BaseService;

import com.atao.caipiao.model.CpRecord;
import com.atao.caipiao.service.CpRecordWyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author twang
 */
@RestController
@RequestMapping("/cp/record")
public class CpRecordController extends BaseController<CpRecord> {
    public static final String BASE_URL = "/CpRecord/";

    @Autowired
    private CpRecordWyService cpRecordWyService;

    @Override
    protected BaseService<CpRecord> getService() {
        return cpRecordWyService;
    }
    
}
