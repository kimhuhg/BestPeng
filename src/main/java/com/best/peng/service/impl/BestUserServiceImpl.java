package com.best.peng.service.impl;

import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.best.peng.domian.BestFolder;
import com.best.peng.domian.BestRootFolder;
import com.best.peng.domian.BestUser;
import com.best.peng.global.BestConstant;
import com.best.peng.repository.BestUserRepository;
import com.best.peng.service.BestFolderService;
import com.best.peng.service.BestRootFolderService;
import com.best.peng.service.BestUserService;

@Service
public class BestUserServiceImpl implements BestUserService {
	
	@Autowired
	private BestUserRepository bestUserRepository;
	
	@Autowired
	private BestRootFolderService bestRootFolderService;
	
	@Autowired
	private BestFolderService bestFolderService;

	@Override
	@Transactional//开启事务
	public BestUser addOrUpdate(BestUser user) {
		
		boolean flag=user.getUserId()==null?true:false;
		
		Date nowTime=Calendar.getInstance().getTime();
		user.setCreateDate(nowTime);
		user.setUpdateDate(nowTime);
		user.setStatus(false);
		user.setValid(true);
		BestUser bestUser=bestUserRepository.save(user);
		
		//为新用户时
		if(flag){
			//创建用户根文件夹
			BestRootFolder rootFolder=new BestRootFolder();
			rootFolder.setUserId(bestUser.getUserId());
			BestRootFolder rootFolderDb = bestRootFolderService.addOrUpdate(rootFolder);
			
			//创建默认文件夹
			for (String type : BestConstant.FOLDER_TYPE) {
				BestFolder bestFolder=new BestFolder();
				bestFolder.setName(type);
				bestFolder.setParentId(rootFolderDb.getRootFolderId());
				bestFolder.setRootFoldeId(rootFolderDb.getRootFolderId());
				bestFolder.setUpdateDate(nowTime);
				bestFolder.setValid(true);
				bestFolderService.addOrUpdate(bestFolder);
			}
			
		}
		
		return bestUser;
	}

	@Override
	public BestUser getUserById(Long userId) {
		return bestUserRepository.findOne(userId);
	}

	@Override
	public void delete(Long userId) {
		bestUserRepository.delete(userId);
	}

	@Override
	public BestUser findBestUserByEmail(String email) {
		return bestUserRepository.findByEmail(email);
	}
	
}
