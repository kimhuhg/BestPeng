package com.best.peng.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.best.peng.sys.entity.Menu;

public interface MenuService {

	Menu findMenuById(Integer id);
	
	List<Menu> listMenuVisible();
	
	Page<Menu> findMenuPageList(Pageable page);
	
	void remove(Integer id);
	
	Menu saveOrUpdate(Menu menu);
	
	/**联表查询**/
	List<Menu> list();
	
	
	List<Menu> listMenuChild(Integer id);
	
	void updateState(Integer id,int state);
	
	//根据授权标识获取权限
	Menu findByPermissionCode(String permissionCode);
}
