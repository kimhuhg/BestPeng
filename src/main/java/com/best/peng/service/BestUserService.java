package com.best.peng.service;

import com.best.peng.domian.BestUser;

public interface BestUserService {

	BestUser addOrUpdate(BestUser user);
	
	BestUser getUserById(Long userId);
	
	void delete(Long userId);
	
	//根据Email查询用户
	BestUser findBestUserByEmail(String email);
	
	
}
