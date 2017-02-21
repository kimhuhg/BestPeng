package com.best.peng.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.best.peng.mapper.BaseModuleMapper;
import com.best.peng.service.BaseModuleService;
import com.best.peng.sys.entity.BaseModule;

@Service
public class BaseModuleServiceImpl implements BaseModuleService {
	@Autowired
	private BaseModuleMapper baseModuleMapper;

	@Override
	public int deleteByPrimaryKey(Integer id) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int insert(BaseModule record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int insertSelective(BaseModule record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public BaseModule selectByPrimaryKey(Integer id) {
		// TODO Auto-generated method stub
		return baseModuleMapper.selectByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKeySelective(BaseModule record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int updateByPrimaryKey(BaseModule record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<BaseModule> list() {
		
		return baseModuleMapper.list(null);
	}

}
