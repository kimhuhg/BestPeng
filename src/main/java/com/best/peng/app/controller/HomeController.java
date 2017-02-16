package com.best.peng.app.controller;


import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.best.peng.domian.BestUser;
import com.best.peng.service.BestNewsService;

@Controller
public class HomeController {
	
	@Autowired
	private BestNewsService bestNewsService;
	
	/**
	 * 首页
	 * @return
	 */
	@RequestMapping("/")
	public String index(ModelMap model,@RequestParam(defaultValue="",required=false,value="title") String title,
			@PageableDefault(page=0,size=10,direction=Sort.Direction.DESC,sort={"createDate"}) Pageable page,
			HttpSession session){
		
		model.addAttribute("news", bestNewsService.getBestNews(title, page));
		
		BestUser user=(BestUser)session.getAttribute("user");
		model.addAttribute("user", user);
		
		return "system/user_list";
	}
	
	/**
	 * 登录
	 * @return
	 */
	@RequestMapping("login")
	public String table(){
		return "account/table";
	}
	
	/**
	 * 登录
	 * @return
	 */
//	@RequestMapping("login")
//	public String login(){
//		return "account/login";
//	}
	
	/**
	 * 注册
	 * @return
	 */
	@RequestMapping("register")
	public String register(){
		return "account/register";
	}
	/**
	 * 个人设置
	 * @param session
	 * @return
	 */
	@RequestMapping(value="set",method=RequestMethod.GET)
	public String set(HttpSession session,ModelMap model){
		BestUser user=(BestUser)session.getAttribute("user");
		model.addAttribute("user", user);
		return "account/set";
	}
}
