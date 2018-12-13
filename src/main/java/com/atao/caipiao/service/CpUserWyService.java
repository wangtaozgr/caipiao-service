package com.atao.caipiao.service;

import com.atao.caipiao.model.CpUser;
import com.atao.caipiao.mapper.CpUserMapper;
import com.atao.base.mapper.BaseMapper;
import com.atao.base.service.BaseService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 *
 * @author twang
 */
@Service
public class CpUserWyService extends BaseService<CpUser> {

    @Resource
    private CpUserMapper cpUserMapper;

    @Override
    public BaseMapper<CpUser> getMapper() {
        return cpUserMapper;
    }
    
}
