package com.best.peng.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.best.peng.domian.BestUser;
import com.best.peng.service.BestUserService;

/**
 * 用户
 * @author zhoupeng
 *
 */
@RestController
@RequestMapping("user")
public class BestUserController {
	
	@Autowired
	private BestUserService bestUserService;
	
	/**
	 * 添加或修改用户
	 * @param user
	 * @return
	 */
	@RequestMapping(method=RequestMethod.POST)
	public BestUser addOrUpdate(@RequestBody BestUser user){
		return bestUserService.addOrUpdate(user);
	}
	
	/**
	 * 根据userId 获取用户信息
	 * @param userId
	 * @return
	 */
	@RequestMapping(value="{userId}",method=RequestMethod.GET)
	public BestUser get(@PathVariable("userId") Long userId){
		return bestUserService.getUserById(userId);
	}
	
	/**
	 * 获取用户列表
	 * @return
	 */
	@RequestMapping(method=RequestMethod.GET)
	public String list(){
		
		
		
		return "";
	}
	
	/**
	 * 删除用户
	 * @param userId
	 * @return
	 */
	@RequestMapping(value="{userId}",method=RequestMethod.DELETE)
	public void delete(@PathVariable("userId") Long userId){
		bestUserService.delete(userId);
	}
}
