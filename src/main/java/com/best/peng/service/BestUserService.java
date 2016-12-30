package com.best.peng.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.best.peng.domian.BestUser;

public interface BestUserService {
	
	Page<BestUser> list(Pageable page);

	BestUser addOrUpdate(BestUser user);
	
	BestUser getUserById(Long userId);
	
	void delete(Long userId);
	
	//根据Email查询用户
	BestUser findBestUserByEmail(String email);
	
	//根据邮箱、密码查询用户
	BestUser findByBestUserByEmailAndPwd(String email,String password);
	
	//修改登录时间
	int updateBestUserLoginDate(String email);
	
	int updateBestUserPassword(Long userId,String oldPassword,String newPassword);
}
