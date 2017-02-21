package com.best.peng.service;

import java.util.List;

import com.best.peng.sys.entity.BaseModule;

public interface BaseModuleService {
	int deleteByPrimaryKey(Integer id);

    int insert(BaseModule record);

    int insertSelective(BaseModule record);

    BaseModule selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(BaseModule record);

    int updateByPrimaryKey(BaseModule record);
    
    List<BaseModule> list();
    
    
}
