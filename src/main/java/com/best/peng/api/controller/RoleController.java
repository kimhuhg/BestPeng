package com.best.peng.api.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.best.peng.base.controller.BaseController;

/**
 * 角色控制器
 * @author zhoupeng
 *
 */
@RestController
@RequestMapping("role")
public class RoleController extends BaseController{
	@RequestMapping(method=RequestMethod.GET)
	public String list(){
		
		return "";
	}
	@RequestMapping(method=RequestMethod.POST)
	public String addOrUpdate(){
		return "";
	}
	@RequestMapping(value="{id}",method=RequestMethod.DELETE)
	public String delete(){
		return "";
	}
	@RequestMapping(value="{id}",method=RequestMethod.GET)
	public String getRole(){
		return "";
	}
}
