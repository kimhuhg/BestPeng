package com.best.peng.base.controller;

import org.springframework.boot.autoconfigure.web.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 404错误处理
 * @author zhoupeng
 *
 */
@Controller
public class Error404Controller implements ErrorController{

	private static final String ERROR_PATH = "/error";

	@RequestMapping(value = ERROR_PATH)
	public String handleError() {
		return "404";
	}
	
	@Override
	public String getErrorPath() {
		
		return ERROR_PATH;
	}

}
