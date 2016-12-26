package com.best.peng.controller;

import javax.servlet.ServletException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.best.peng.domian.BestHttpResponseMessage;
import com.best.peng.domian.BestUser;
import com.best.peng.service.BestUserService;

@RestController
@RequestMapping("account")
public class AccountController {
	
	@Autowired
	private BestUserService bestUserService;
	
	@RequestMapping(value="login",method=RequestMethod.POST)
	public BestHttpResponseMessage login(@RequestBody BestUser user){
		BestUser userDb=bestUserService.findBestUserByEmail(user.getEmail());
		BestHttpResponseMessage responseMsg=new BestHttpResponseMessage();
		if(userDb==null){
			responseMsg.setCode(400);
			responseMsg.setMsg("用户不存在!");
			responseMsg.setUrl("");
			return responseMsg;
		}
		return responseMsg;
	}
	
	@RequestMapping(value="register",method=RequestMethod.POST)
	public String register(BestUser user){
		bestUserService.addOrUpdate(user);
		return "";
	}
	
}
