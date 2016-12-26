package com.best.peng.service.impl;

import java.util.Calendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.best.peng.domian.BestRootFolder;
import com.best.peng.repository.BestRootFolderRepository;
import com.best.peng.service.BestRootFolderService;

@Service
public class BestRootFolderServiceImpl implements BestRootFolderService {

	@Autowired
	private BestRootFolderRepository bestRootFolderRepository;
	
	@Override
	public BestRootFolder addOrUpdate(BestRootFolder rootFolder) {
		rootFolder.setCreateDate(Calendar.getInstance().getTime());
		return bestRootFolderRepository.save(rootFolder);
	}

}
