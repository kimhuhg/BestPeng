package com.best.peng.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.best.peng.domian.BestFolder;
import com.best.peng.service.BestFolderService;

/**
 * 文件夹操作
 * @author zhoupeng
 *
 */
@RestController
@RequestMapping("folder")
public class BestFolderController {

	@Autowired
	private BestFolderService bestFolderService;
	
	/**
	 * 添加文件夹
	 * @param folder
	 * @return
	 */
	@RequestMapping(method=RequestMethod.POST)
	public BestFolder addOrUpdate(@RequestBody BestFolder folder){
		
		return bestFolderService.addOrUpdate(folder);
	}
	
	/**
	 * 获取文件夹列表
	 * @return
	 */
	@RequestMapping(method=RequestMethod.GET)
	public String list(){
		return "";
	}
	
	@RequestMapping(value="{id}",method=RequestMethod.GET)
	public String get(@PathVariable("id") Long id){
		
		return "";
	}
	
	/**
	 * 删除文件夹
	 * @return
	 */
	@RequestMapping(value="{id}",method=RequestMethod.DELETE)
	public String delete(@PathVariable("id") Long id){
		return "";
	}
}
