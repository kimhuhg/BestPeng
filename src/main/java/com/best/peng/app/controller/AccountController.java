package com.best.peng.app.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.best.peng.domian.BestUser;
import com.best.peng.service.BestUserService;
import com.best.peng.util.ValidateHelper;

@Controller
@RequestMapping("account")
public class AccountController {
	
	@Autowired
	private BestUserService bestUserService;
	
	/**
	 * 登录
	 * @param user
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value="login",method=RequestMethod.POST)
	public String login(BestUser user,@RequestParam(value="backUrl",required=false) String backUrl,
			HttpServletRequest request,ModelMap model){
		
		BestUser userDb=bestUserService.findByBestUserByEmailAndPwd(user.getEmail(),user.getPassword());
		
		if(userDb==null){
			model.addAttribute("msg", "用户名或密码错误。");
			model.addAttribute("email", user.getEmail());
			return "account/login";
		}
		
		//检测邮箱是否激活?
		
		HttpSession session=request.getSession();
		session.setAttribute("user", userDb);
		
		//更新登录时间
		bestUserService.updateBestUserLoginDate(userDb.getEmail());
		
		//获取登录之前访问URL，如果存在则跳回
		if(!ValidateHelper.isEmptyString(backUrl)){
			return "redirect:"+backUrl;
		}
		
		
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
		
		//邮箱验证
		
		BestUser userDb=bestUserService.saveOrUpdate(user);
		HttpSession session=request.getSession();
		session.setAttribute("user", userDb);
		
		return "redirect:/";
	}
	
	/**
	 * logout
	 */
	@RequestMapping(value="logout",method=RequestMethod.GET)
	public String logout(HttpSession session){
		if(session.getAttribute("user")!=null){
			session.setAttribute("user", null);
		}
		return "redirect:/login";
	}
	
	/**
	 * 修改密码
	 * @param oldpassword
	 * @param newPassword
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="repassword",method=RequestMethod.POST)
	public Map<String,Object> updatePwd(@RequestParam("password") String oldPassword,
			@RequestParam("newPassword") String newPassword,HttpSession session,
			Model model){
		Map<String,Object> message=new HashMap<String,Object>();
		
		BestUser user=(BestUser)session.getAttribute("user");
		if(user!=null){
			int count=bestUserService.updateBestUserPassword(user.getId(), oldPassword, newPassword);
			if(count>0){
				message.put("code", 200);
				message.put("msg", "密码修改成功!");
			}else{
				message.put("code", 500);
				message.put("msg", "旧密码不正确!");
			}
		}
		
		return message;
	}
	
}
