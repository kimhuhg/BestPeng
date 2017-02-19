package com.best.peng.app.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.best.peng.base.controller.BaseController;
import com.best.peng.service.BestUserService;
import com.best.peng.sys.entity.BestUser;
import com.best.peng.util.ShiroUtils;

@Controller
@RequestMapping("account")
public class AccountController extends BaseController{
	
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
	@ResponseBody
	public String login(BestUser user,@RequestParam(value="backUrl",required=false) String backUrl,
			HttpServletRequest request){
		//用户账号密码验证
//		BestUser userDb=bestUserService.findByBestUserByEmailAndPwd(user.getEmail(),user.getPassword());
//		
//		if(userDb==null){
//			return msg(1,"用户名或密码错误");
//		}
//		
//		//检测邮箱是否激活?
//		
//		HttpSession session=request.getSession();
//		session.setAttribute("user", userDb);
//		
//		//更新登录时间
//		bestUserService.updateBestUserLoginDate(userDb.getEmail());
		
		//获取登录之前访问URL，如果存在则跳回
//		if(!ValidateHelper.isEmptyString(backUrl)){
//			return "redirect:"+backUrl;
//		}
		
		try{
			Subject subject = ShiroUtils.getSubject();
			
			String password = user.getPassword();
			UsernamePasswordToken token = new UsernamePasswordToken(user.getEmail(), password);
			subject.login(token);
		}catch (UnknownAccountException e) {
			return msg(1,e.getMessage());
		}catch (IncorrectCredentialsException e) {
			return msg(2,e.getMessage());
		}catch (LockedAccountException e) {
			return msg(3,e.getMessage());
		}catch (AuthenticationException e) {
			return msg(4,"账户验证失败");
		}
		
		// 此方法不处理登录成功,由shiro进行处理.
		return success();
	}
	
	/**
	 * 用户注册
	 * @param user
	 * @return
	 */
	@RequestMapping(value="register",method=RequestMethod.POST)
	@ResponseBody
	public String register(BestUser user,HttpServletRequest request){
		//检测邮箱是否存在
		BestUser userDb_e=bestUserService.findBestUserByEmail(user.getEmail());
		if(userDb_e!=null){
			return msg(1,"邮箱已被使用");
		}
		if(user.getPassword().length()>16||user.getPassword().length()<6){
			return msg(1,"密码长度必须大于6小于16");
		}
		
		//邮箱验证
		
		
		BestUser userDb=bestUserService.saveOrUpdate(user);
		HttpSession session=request.getSession();
		session.setAttribute("user", userDb);
		
		return success();
	}
	
	/**
	 * logout
	 */
//	@RequestMapping(value="logout",method=RequestMethod.GET)
//	public String logout(HttpSession session){
//		if(session.getAttribute("user")!=null){
//			session.setAttribute("user", null);
//		}
//		return "redirect:/login";
//	}
	
}
