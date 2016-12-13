package com.best.peng.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.best.peng.domian.BestNews;
import com.best.peng.repository.BestNewsRepository;
import com.best.peng.service.BestNewsService;

/**
 * 信息
 * @author zhoupeng
 *
 */
@RestController
@RequestMapping("news")
public class BestNewsController {
	
	@Autowired
	private BestNewsService bestNewsService;
	
	/**
	 * 添加或修改
	 * @param news
	 * @return
	 */
	@RequestMapping(method=RequestMethod.POST)
	public BestNews add(@RequestBody BestNews news){
		return bestNewsService.saveOrUpdate(news);
	}
	
	
	/**
	 * 分页查询
	 * @param title
	 * @PageableDefault(value = 10, sort = { "createDate" }, direction = Sort.Direction.DESC) Pageable pageable
	 * 
	 * @return
	 */
	@RequestMapping(value="get/page/{title}",method=RequestMethod.GET)
	public Page<BestNews> get(@PathVariable("title") String title,
			@PageableDefault(value = 10, sort = { "createDate" }, direction = Sort.Direction.DESC,page=0,size=10) Pageable pageable){
		return bestNewsService.getBestNewsWithPage(title,pageable);
	}
	
	/**
	 * 分页查询
	 * page，第几页，从0开始，默认为第0页
	 * size，每一页的大小，默认为20
	 * sort，排序相关的信息，以property,property(,ASC|DESC)的方式组织，例如sort=firstname&sort=lastname,desc表示在按firstname正序排列基础上按lastname倒序排列
	 * @param pageable   默认每页10条，和默认排序的方式，默认页码
	 * @return
	 */
	@RequestMapping(method=RequestMethod.GET)
	public Page<BestNews> getList(@PageableDefault(value = 10, sort = { "createDate" }, direction = Sort.Direction.DESC,page=0,size=10) Pageable pageable){
		return bestNewsService.getBestNews(pageable);
	}
	
	/**
	 * 删除
	 * @param id
	 */
	@RequestMapping(value="{id}",method=RequestMethod.DELETE)
	public void delete(@PathVariable("id") Long id){
		bestNewsService.delete(id);
	}
}
