package com.best.peng.base.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.alibaba.fastjson.JSON;
import com.best.peng.domian.BestUser;
import com.best.peng.sys.entity.ResponseMessage;
import com.best.peng.util.DataTablesUtils;

@Controller
public class BaseController {
	
	
	/**
	 * 获取登录用户
	 * @return
	 */
	public BestUser getUser() {
		BestUser user=(BestUser)getRequest().getSession().getAttribute("user");
		return user;
	}
	
	/**
	 * 得到request对象
	 */
	public HttpServletRequest getRequest() {
		HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
		return request;
	}
	
	/**
	 * json转换
	 * @param object
	 * @return
	 */
	public String json(Object object){
		return JSON.toJSONString(object);
	}
	
	/**
	 * json转换
	 * @param page
	 * @param draw
	 * @return
	 */
	public String json(Page<?> page,Integer draw){
		return DataTablesUtils.getJson(page, draw);
	}
	
	/**
	 * 响应消息对象
	 * @return
	 */
	public ResponseMessage getReMsg(Integer code,String msg){
		ResponseMessage rm=new ResponseMessage();
		rm.setCode(code);
		rm.setMsg(msg);
		return rm;
	}
	public ResponseMessage getReMsg(){
		ResponseMessage rm=new ResponseMessage();
		rm.setCode(0);
		rm.setMsg("操作成功");
		return rm;
	}
	
	/**
	 * 获取登录用户的IP
	 * @throws Exception 
	 */
	public String getRemortIP(){  
		HttpServletRequest request = this.getRequest();
		String ip = "";
		if (request.getHeader("x-forwarded-for") == null) {  
			ip = request.getRemoteAddr();  
	    }else{
	    	ip = request.getHeader("x-forwarded-for");  
	    }
		return ip;
	}  
}
