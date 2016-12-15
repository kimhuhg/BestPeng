package com.best.peng.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.best.peng.service.BestNewsService;

@Controller
public class HomeController {
	
	@Autowired
	private BestNewsService bestNewsService;
	
	/**
	 * 首页
	 * @return
	 */
	@RequestMapping("/")
	public String index(ModelMap model,@RequestParam(defaultValue="",required=false,value="title") String title,
			@PageableDefault(page=0,size=10,direction=Sort.Direction.DESC,sort={"createDate"}) Pageable page){
		
		model.addAttribute("news", bestNewsService.getBestNews(title, page));
		return "index";
	}
}
