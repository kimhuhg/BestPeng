package com.best.peng.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.best.peng.sys.entity.Menu;

public interface MenuRepository extends JpaRepository<Menu, Integer> {

	//菜单列表
	@Query(value="select id,menu_name,menu_link,(select menu_name from menu mp where mp.id=m.parent_id) parentName"
			+ ",icon_name,create_date,modified_date,sort_no,valid,visible,parent_id,permission_type,permission_code FROM Menu m where valid=1 order by sort_no",nativeQuery=true)
	List<?> findMenuList();
	
	//所有可见的菜单--左侧菜单--菜单树
	@Query(value="select id,menu_name,menu_link,(select menu_name from menu mp where mp.id=m.parent_id) parentName"
			+ ",icon_name,create_date,modified_date,sort_no,valid,visible,parent_id FROM Menu m where valid=1 and visible=1 and permission_type=1 order by sort_no",nativeQuery=true)
	List<?> findMenuListVisible();
	
	//获取子菜单
	List<Menu> findByParentId(Integer parentId);
	
	//设置可见性
	@Modifying
	@Query(value="update menu set visible=:visible where id=:id or parent_id=:id",nativeQuery=true)
	void updateMenuVisible(@Param("id")Integer id,@Param("visible")int visible);
	
	//根据授权标识查询菜单
	Menu findByPermissionCode(String permissionCode);
	
	
	/**
	 *（1）可以通过自定义的 JPQL 完成 UPDATE 和 DELETE 操作。 注意： JPQL 不支持使用 INSERT； 
	 *（2）在 @Query 注解中编写 JPQL 语句， 但必须使用 @Modifying 进行修饰. 以通知 SpringData， 这是一个 UPDATE 或 DELETE 操作 
	 *（3）UPDATE 或 DELETE 操作需要使用事务，此时需要定义 Service 层，在 Service 层的方法上添加事务操作； 
	 *（4）默认情况下， SpringData 的每个方法上有事务， 但都是一个只读事务。 他们不能完成修改操作。
	 */
	
}
