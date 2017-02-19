package com.best.peng.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.best.peng.sys.entity.BestUser;

public interface BestUserService {
	
	Page<BestUser> list(Pageable page);

	BestUser saveOrUpdate(BestUser user);
	
	BestUser getUserById(Integer userId);
	
	void remove(Integer userId);
	
	//根据Email查询用户
	BestUser findBestUserByEmail(String email);
	
	//根据邮箱、密码查询用户
	boolean findByBestUserByEmailAndPwd(String email,String password);
	
	//修改登录时间
	int updateBestUserLoginDate(String email);
	
	//修改密码
	int updateBestUserPassword(Integer userId,String oldPassword,String newPassword);
}
