package com.best.peng.service;

import java.util.List;

import com.best.peng.sys.entity.Role;

public interface RoleService{
	
	Role addOrUpdate(Role role);
	
	/**
	 * 角色列表
	 * @return
	 */
	List<Role> list();
	
	/**
	 * 根据ID获取角色下的权限的授权标识列表
	 * @param roleId
	 * @return
	 */
	List<String> findUserRolePermission(Integer roleId);
}
