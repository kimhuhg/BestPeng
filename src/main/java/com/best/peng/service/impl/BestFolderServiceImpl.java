package com.best.peng.service.impl;

import java.util.Calendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.best.peng.domian.BestFolder;
import com.best.peng.repository.BestFolderRepository;
import com.best.peng.service.BestFolderService;

@Service
public class BestFolderServiceImpl implements BestFolderService{
	@Autowired
	private BestFolderRepository bestFolderRepository;

	@Override
	public BestFolder addOrUpdate(BestFolder folder) {
		folder.setCreateDate(Calendar.getInstance().getTime());
		return bestFolderRepository.save(folder);
	}
	
}
