package com.best.peng.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.best.peng.repository.MenuRepository;
import com.best.peng.service.MenuService;
import com.best.peng.sys.entity.Menu;
import com.best.peng.util.DateUtils;
import com.best.peng.util.ValidateHelper;

/**
 * 菜单
 * @author zhoupeng
 *
 */
@Service
public class MenuServiceImpl implements MenuService{

	@Autowired
	private MenuRepository menuRepository;
	
	
	@Override
	public Menu findMenuById(Integer id) {
		
		return menuRepository.findOne(id);
	}

	@Override
	public Page<Menu> findMenuPageList(Pageable page) {
		
		return menuRepository.findAll(page);
	}

	@Override
	public void remove(Integer id) {
		Menu menu=menuRepository.findOne(id);
		if(menu!=null){
			menuRepository.delete(id);
		}
		
	}

	@Override
	public Menu saveOrUpdate(Menu menu) {
		
		if(ValidateHelper.isEmptyString(menu.getIconName())){
			menu.setIconName("");
		}
		if(menu.getId()==null){
			menu.setValid(true);
			menu.setVisible(true);
			menu.setCreateDate(DateUtils.getCurrentDate());
		}else{
			
			menu.setModifiedDate(DateUtils.getCurrentDate());
		}
		return menuRepository.save(menu);
	}

	
	/**
	 * 菜单列表
	 */
	@Override
	public List<Menu> list() {
		List<?> menuList=menuRepository.findMenuList();
		
		List<Menu> list=new ArrayList<Menu>();
		if(menuList!=null){
			for (int i = 0; i < menuList.size(); i++) {
				Menu menu=new Menu();
				Object[] obj=(Object[])menuList.get(i);
				
				menu.setId((Integer)obj[0]);
				menu.setMenuName((String)obj[1]);
				menu.setMenuLink((String)obj[2]);
				
				//一级菜单时，上级菜单为空，
				if(obj[3]==null){
					menu.setParentName("根目录");
				}else{
					menu.setParentName((String)obj[3]);
				}
				
				menu.setIconName((String)obj[4]);
				menu.setCreateDate((Date)obj[5]);
				menu.setModifiedDate((Date)obj[6]);
				menu.setSortNo((Integer)obj[7]);
				menu.setValid((boolean)obj[8]);
				menu.setVisible((boolean)obj[9]);
				menu.setParentId((Integer)obj[10]);
				menu.setPermissionType((Integer)obj[11]);
				if(obj[12]==null){
					menu.setPermissionCode("");
				}else{
					menu.setPermissionCode((String)obj[12]);
				}
				
				list.add(menu);
				
			}
		}
		return list;
	}

	/**
	 * 获取下级菜单
	 */
	@Override
	public List<Menu> listMenuChild(Integer id) {
		
		return menuRepository.findByParentId(id);
	}

	/**
	 * 更改菜单的启用状态或者可见性
	 */
	@Transactional
	@Override
	public void updateState(Integer id, int state) {

		menuRepository.updateMenuVisible(id, state);
		
	}

	/**
	 * 可见的菜单列表
	 */
	@Override
	public List<Menu> listMenuVisible() {
		List<?> menuList=menuRepository.findMenuListVisible();
		List<Menu> list=new ArrayList<Menu>();
		if(menuList!=null){
			for (int i = 0; i < menuList.size(); i++) {
				Menu menu=new Menu();
				Object[] obj=(Object[])menuList.get(i);
				
				menu.setId((Integer)obj[0]);
				menu.setMenuName((String)obj[1]);
				menu.setMenuLink((String)obj[2]);
				
				//一级菜单时，上级菜单为空，
				if(obj[3]==null){
					menu.setParentName("根目录");
				}else{
					menu.setParentName((String)obj[3]);
				}
				
				menu.setIconName((String)obj[4]);
				menu.setCreateDate((Date)obj[5]);
				menu.setModifiedDate((Date)obj[6]);
				menu.setSortNo((Integer)obj[7]);
				menu.setValid((boolean)obj[8]);
				menu.setVisible((boolean)obj[9]);
				menu.setParentId((Integer)obj[10]);
				list.add(menu);
				
			}
		}
		return list;
	}

	@Override
	public Menu findByPermissionCode(String permissionCode) {
		return menuRepository.findByPermissionCode(permissionCode);
	}
	
}
