package com.best.peng.global;


import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

/**
 * 全局异常处理
 * @author zhoupeng
 *
 */
@ControllerAdvice
public class GlobalExcetionHandler {
	
	// 异常处理的方法
	@ExceptionHandler
	public ModelAndView error(Exception ex,HttpServletRequest req) {
		ModelAndView mv = new ModelAndView("error");
		
		mv.addObject("excetion", ex);
		mv.addObject("message", ex.getMessage());
		mv.addObject("url", req.getRequestURL());
		
		return mv;
	}
	
	// 异常处理,返回json
//	@ExceptionHandler
//	@ResponseBody
//	public Map<String,Object> error2(Exception ex, HttpServletRequest req) {
//		Map<String,Object> exMap=new HashMap<String, Object>();
//		exMap.put("message", ex.getMessage());
//		
//		exMap.put("url", req.getRequestURL());
//		
//		exMap.put("exception", ex);
//		return exMap;
//	}
}
