package com.best.peng.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.best.peng.repository.BestFileRepository;
import com.best.peng.service.BestFileService;

@Service("bestFileService")
public class BestFileServiceImpl implements BestFileService{
	
	@Resource
	private BestFileRepository bestFileRepository;
	
}
