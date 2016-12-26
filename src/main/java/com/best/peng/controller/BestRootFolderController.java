package com.best.peng.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("root")
public class BestRootFolderController {
	
	/**
	 * 获取用户的根目录
	 * @return
	 */
	@RequestMapping(method=RequestMethod.GET)
	public String list(){
		return "";
	}
}
