package com.best.peng.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.best.peng.repository.RoleRepository;
import com.best.peng.service.RoleService;
import com.best.peng.sys.entity.Menu;
import com.best.peng.sys.entity.Role;

@Service
public class RoleServiceImpl implements RoleService {

	@Autowired
	private RoleRepository roleRepository;
	
	@Override
	public Role addOrUpdate(Role role) {
		return roleRepository.save(role);
	}

	@Override
	public List<Role> list() {
		List<?> list=roleRepository.list();
		List<Role> roleList=new ArrayList<Role>();
		if(list!=null){
			for (int i = 0; i < list.size(); i++) {
				Object[] array=(Object[])list.get(i);
				Role role=new Role();
				role.setId((Integer)array[0]);
				role.setId((Integer)array[1]);
				role.setId((Integer)array[2]);
				roleList.add(role);
			}
		}
		return null;
	}

	@Override
	public List<String> findUserRolePermission(Integer roleId) {
		List<String> permList=null;
		Role role=roleRepository.findOne(roleId);
		if(role!=null&&role.getMenus()!=null){
			permList=new ArrayList<String>();
			List<Menu> rolePermList=role.getMenus();
			for (Menu menu : rolePermList) {
				permList.add(menu.getPermissionCode());
			}
			return permList;
		}
		return null;
	}

}
