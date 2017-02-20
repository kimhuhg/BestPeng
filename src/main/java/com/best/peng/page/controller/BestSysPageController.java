package com.best.peng.page.controller;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.best.peng.base.controller.BaseController;

/**
 * 系统页面
 * @author zhoupeng
 *
 */
@Controller
@RequestMapping("page")
public class BestSysPageController{
	
	@RequestMapping(value="user",method=RequestMethod.GET)
	@RequiresPermissions("user:list")//权限管理;
	public String user(){
		return "system/user_list";
	}
	@RequestMapping(value="menu",method=RequestMethod.GET)
	@RequiresPermissions("menu:list")//权限管理;
	public String menu(){
		return "system/menu_list";
	}
	@RequestMapping(value="role",method=RequestMethod.GET)
	@RequiresPermissions("role:list")//权限管理;
	public String role(){
		return "system/role_list";
	}
}
