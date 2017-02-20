package com.best.peng.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.best.peng.base.controller.BaseController;
import com.best.peng.service.MenuService;
import com.best.peng.sys.entity.DataTable;
import com.best.peng.sys.entity.Menu;
import com.best.peng.sys.entity.MenuTree;
import com.best.peng.util.ValidateHelper;

/**
 * 菜单接口类
 * @author zhoupeng
 *
 */
@RestController
@RequestMapping("api/menu")
public class MenuController extends BaseController{
	
	@Autowired
	private MenuService menuService;
	
	/**
	 * page，第几页，从0开始，默认为第0页
	 * size，每一页的大小，默认为20
	 * sort，排序相关的信息，以property,property(,ASC|DESC)的方式组织，例如sort=firstname&sort=lastname,desc表示在按firstname正序排列基础上按lastname倒序排列
	 * @param pageable   默认每页10条，和默认排序的方式，默认页码
	 * @return
	 */
	@RequestMapping(value="page",method=RequestMethod.GET)
	public String listPage(@PageableDefault(page=0,size=10,sort={"sortNo"},direction=Direction.ASC) Pageable page){
		return json(menuService.findMenuPageList(page));
	}
	
	/**
	 * 菜单HTML,左侧菜单树
	 * @return
	 */
	@RequestMapping(value="list/tree",method=RequestMethod.GET)
	public Object listMenuPage(){
		List<Menu> data=menuService.listMenuVisible();	
		
		MenuTree tree=new MenuTree(data);	
		
		return tree.buildTree();
	}
	
	/**
	 * 所有菜单列表--tree使用
	 * @return
	 */
	@RequestMapping(value="list",method=RequestMethod.GET)
	public String listMenu(){
		
		List<Menu> data=menuService.listMenuVisible();
		
		return json(data);
	}
	
	/**
	 * 获取菜单列表(联表)---DataTables格式
	 * @param draw
	 * @param start 
	 * @param length
	 * @return
	 */
	@RequestMapping(method=RequestMethod.GET)
	public String list(Integer draw,Integer start,Integer length){
		
		List<Menu> data=menuService.list();
		DataTable dt=new DataTable();
		
		dt.setData(data);
		dt.setDraw(draw);
		dt.setRecordsFiltered(data.size());
		dt.setRecordsTotal(data.size());
		
		return json(dt);
	}
	
	/**
	 * 获取单个菜单信息
	 * @param id
	 * @return
	 */
	@RequestMapping(value="{id}",method=RequestMethod.GET)
	public String getMenu(@PathVariable("id") Integer id){
		return json(menuService.findMenuById(id));
	}
	
	/**
	 * 添加或修改菜单
	 * @param menu
	 * @return
	 */
	@RequestMapping(method=RequestMethod.POST)
	public String saveOrUpdate(Menu menu){
		if(!ValidateHelper.isEmptyString(menu.getPermissionCode())){
			Menu menuDbPerm=menuService.findByPermissionCode(menu.getPermissionCode());
			if(menuDbPerm!=null){
				return msg(1,"授权标识已存在");
			}
		}
		Menu menuDb=menuService.saveOrUpdate(menu);
		if(menuDb==null){
			return msg(1,"添加失败");
		}
		return success();
	}
	
	/**
	 * 删除菜单
	 * @param id
	 */
	@RequestMapping(value="{id}",method=RequestMethod.DELETE)
	public String remove(@PathVariable("id") Integer id){
		//有子菜单则不能删除
		List<Menu> child=menuService.listMenuChild(id);
		if(!child.isEmpty()){
			return msg(1,"存在子菜单无法删除");
		}
		
		menuService.remove(id);
		return success();
	}
	
	/**
	 * 设置启用状态或者可见状态
	 * type:1为设置启用状态,2为设置可见性
	 * @return
	 */
	@RequestMapping(value="{id}/{state}",method=RequestMethod.PUT)
	public String update(@PathVariable("id") Integer id,@PathVariable("state") int state){
		
		menuService.updateState(id, state);
		
		return success();
	}
}
