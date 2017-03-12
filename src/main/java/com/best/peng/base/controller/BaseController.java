package com.best.peng.base.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.best.peng.sys.entity.BestUser;
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
		return JSON.toJSONString(object,SerializerFeature.DisableCircularReferenceDetect);//DisableCircularReferenceDetect禁止循环引用检测
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
	public ResponseMessage getReMsg(Integer code,String msg,Object data){
		ResponseMessage rm=new ResponseMessage();
		rm.setCode(code);
		rm.setMsg(msg);
		rm.setData(data);
		return rm;
	}

	/**
	 * 响应消息
	 * @param code
	 * @param msg
	 * @return
	 */
	public String msg(Integer code,String msg){
		return json(getReMsg(code,msg,null));
	}
	public String msg(Integer code,String msg,Object data){
		return json(getReMsg(code,msg,data));
	}
	
	public String success(){
		return msg(0,"操作成功");
	}
	public String failed(){
		return msg(1,"操作失败");
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
