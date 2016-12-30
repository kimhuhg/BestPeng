package com.best.peng.base.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;

import com.best.peng.domian.BestUser;

@Controller
public class BestBaseController {
	
	public BestUser user;

	
	
	public BestUser getUser() {
		return user;
	}

	public void setUser(HttpSession session) {
		this.user = (BestUser)session.getAttribute("user");
	}
	
}
