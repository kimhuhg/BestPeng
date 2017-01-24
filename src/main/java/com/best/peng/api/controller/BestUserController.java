package com.best.peng.api.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.best.peng.base.controller.BaseController;
import com.best.peng.domian.BestUser;
import com.best.peng.service.BestNewsService;
import com.best.peng.service.BestUserService;

/**
 * 用户管理
 * @author zhoupeng
 *
 */
@RestController
@RequestMapping("api/user")
public class BestUserController extends BaseController {
	
	@Autowired
	private BestUserService bestUserService;
	
	/**
	 * 添加或修改用户
	 * @param user
	 * @return
	 */
	@RequestMapping(method=RequestMethod.POST)
	public BestUser addOrUpdate(@RequestBody BestUser user){
		return bestUserService.saveOrUpdate(user);
	}
	
	/**
	 * 根据userId 获取用户信息
	 * @param userId
	 * @return
	 */
	@RequestMapping(value="{userId}",method=RequestMethod.GET)
	public BestUser get(@PathVariable("userId") Integer userId){
		return bestUserService.getUserById(userId);
	}
	
	/**
	 * 获取用户列表
	 * @return
	 */
	@RequestMapping(method=RequestMethod.GET)
	public String list(Integer draw,Integer start,Integer length){

		PageRequest page=new PageRequest(start/length,length,new Sort("createDate"));
		
		Page<?> pageList=bestUserService.list(page);
		
		return json(pageList, draw);
	}
	
	/**
	 * 删除用户
	 * @param userId
	 * @return
	 */
	@RequestMapping(value="{userId}",method=RequestMethod.DELETE)
	public void delete(@PathVariable("userId") Integer userId){
		bestUserService.remove(userId);
	}
}
