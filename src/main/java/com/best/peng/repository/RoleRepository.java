package com.best.peng.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.best.peng.sys.entity.Role;

public interface RoleRepository extends JpaRepository<Role, Integer>{
	
	@Query(value="select id,name,description from role",nativeQuery=true)
	List<?> list();

}
