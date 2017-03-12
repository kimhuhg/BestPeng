package com.best.peng.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.best.peng.base.controller.BaseController;
import com.best.peng.service.BaseModuleService;
import com.best.peng.sys.entity.BaseModule;
import com.best.peng.sys.entity.DataTable;
import com.github.pagehelper.PageHelper;

@RestController
@RequestMapping("api/module")
public class BaseModuleController extends BaseController{
	
	@Autowired
	private BaseModuleService baseModuleService;
	
	@RequestMapping(value="{id}",method=RequestMethod.GET)
	public String getBaseModule(@PathVariable("id") Integer id){
		return json(baseModuleService.selectByPrimaryKey(id));
	}
	
	/**
	 * 获取菜单列表---DataTables格式
	 * @param draw
	 * @param start 
	 * @param length
	 * @return
	 */
	@RequestMapping(method=RequestMethod.GET)
	public String list(Integer draw,@RequestParam(defaultValue="1",required=false)Integer start,@RequestParam(defaultValue="1",required=false)Integer length){
		//分页插件
		PageHelper.startPage(start,length);//(当前页码，每页条数)
		List<BaseModule> data=baseModuleService.list();
		DataTable dt=new DataTable();
		
		dt.setData(data);
		dt.setDraw(draw);
		dt.setRecordsFiltered(data.size());
		dt.setRecordsTotal(data.size());
		
		return json(data);
	}
}
