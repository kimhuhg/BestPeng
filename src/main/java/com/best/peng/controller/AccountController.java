package com.best.peng.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.best.peng.domian.BestUser;
import com.best.peng.service.BestUserService;

@Controller
@RequestMapping("account")
public class AccountController {
	
	@Autowired
	private BestUserService bestUserService;
	
	@RequestMapping(value="login",method=RequestMethod.POST)
	public String login(BestUser user,HttpServletRequest request,ModelMap model){
		BestUser userDb=bestUserService.findByBestUserByEmailAndPwd(user.getEmail(),user.getPassword());
		
		if(userDb==null){
			model.addAttribute("msg", "用户名或密码错误。");
			model.addAttribute("email", user.getEmail());
			return "account/login";
		}
		
		//检测邮箱是否激活
		
		HttpSession session=request.getSession();
		session.setAttribute("user", userDb);
		
		//更新登录时间
		bestUserService.updateBestUserLoginDate(userDb.getEmail());
		
		return "redirect:/";
	}
	
	/**
	 * 用户注册
	 * @param user
	 * @return
	 */
	@RequestMapping(value="register",method=RequestMethod.POST)
	public String register(BestUser user,Model model,HttpServletRequest request){
		if(user.getPassword().length()>16||user.getPassword().length()<6){
			model.addAttribute("msg_p", "密码长度不能大于16.");
			model.addAttribute("email", user.getEmail());
			return "account/register";
		}
		
		//检测邮箱是否存在
		BestUser userDb_e=bestUserService.findBestUserByEmail(user.getEmail());
		if(userDb_e!=null){
			model.addAttribute("msg", "邮箱已被使用.");
			return "account/register";
		}
		
		
		BestUser userDb=bestUserService.addOrUpdate(user);
		HttpSession session=request.getSession();
		session.setAttribute("user", userDb);
		
		return "redirect:/";
	}
	
	
	
}
