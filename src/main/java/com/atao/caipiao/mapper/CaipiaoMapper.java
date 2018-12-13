package com.atao.caipiao.mapper;

import com.atao.caipiao.model.Caipiao;

import java.util.List;

import com.atao.base.mapper.BaseMapper;
import com.atao.base.support.MyBatisDao;

/**
 * @Description:
 * @author twang
 */
@MyBatisDao
public interface CaipiaoMapper extends BaseMapper<Caipiao> {
	public List<Caipiao> queryLastByCode(int code);
}
