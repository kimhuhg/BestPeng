package com.best.peng.page.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {
	
	/**
	 * 首页
	 * @return
	 */
	@RequestMapping("/")
	public String index(){
		return "/system/menu_list";
	}
	
	/**
	 * 登录
	 * @return
	 */
	@RequestMapping("login")
	public String login(){
		return "account/login";
	}

}
