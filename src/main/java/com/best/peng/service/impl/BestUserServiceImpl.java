package com.best.peng.service.impl;

import java.util.Calendar;
import java.util.Date;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.best.peng.repository.BestUserRepository;
import com.best.peng.service.BestUserService;
import com.best.peng.sys.entity.BestUser;
import com.best.peng.util.DateUtils;
import com.best.peng.util.UserUtils;

@Service
public class BestUserServiceImpl implements BestUserService {
	
	@Autowired
	private BestUserRepository bestUserRepository;

	/**
	 * 添加或修改用户
	 */
	@Override
	@Transactional//开启事务
	public BestUser saveOrUpdate(BestUser user) {
		boolean flag=user.getId()==null?true:false;
		
		//密码加密
		String password=UserUtils.passwordEncrypt(user.getPassword());
		Date nowTime=DateUtils.getCurrentDate();
		user.setModifiedDate(nowTime);
		
		//新用户
		if(flag){
			user.setCreateDate(nowTime);
			user.setLoginDate(nowTime);
			user.setPassword(password);
			user.setStatus(0);
			user.setValid(true);
			user.setUserName(user.getEmail());
			user.setGroupId(0);
		}
		BestUser bestUser=bestUserRepository.save(user);
		
		return bestUser;
	}

	@Override
	public Page<BestUser> list(Pageable page) {
		
		return bestUserRepository.findAll(page);
	}
	
	/**
	 * 根据userId获取用户
	 */
	@Override
	public BestUser getUserById(Integer userId) {
		
		
		return bestUserRepository.findOne(userId);
	}

	/**
	 * 删除用户
	 */
	@Override
	public void remove(Integer userId) {
		bestUserRepository.delete(userId);
	}

	/**
	 * 根据Email获取用户
	 */
	@Override
	public BestUser findBestUserByEmail(String email) {
		return bestUserRepository.findByEmail(email);
	}

	/**
	 * 根据Email和密码获取用户---密码账户检测
	 */
	@Override
	public boolean findByBestUserByEmailAndPwd(String email, String password) {
		password=UserUtils.passwordEncrypt(password);
		BestUser user=bestUserRepository.findByEmailAndPassword(email, password);
		if(user!=null){
			return true;
		}
		return false;
	}

	/**
	 * 更新用户登录时间
	 */
	@Override
	@Transactional //更新，必须开启事务
	public int updateBestUserLoginDate(String email) {
		
		return bestUserRepository.updateBestUserLoginDate(Calendar.getInstance().getTime(), email);
	}

	/**
	 * 修改密码
	 */
	@Transactional
	@Override
	public int updateBestUserPassword(Integer userId, String oldPassword,String newPassword) {
		BestUser user=getUserById(userId);
		oldPassword=UserUtils.passwordEncrypt(oldPassword);
		int count=0;
		if(user.getPassword().equals(oldPassword)){
			newPassword=UserUtils.passwordEncrypt(newPassword);
			count=bestUserRepository.updateBestUserPassword(userId, newPassword);
		}
		
		return count;
	}

}
