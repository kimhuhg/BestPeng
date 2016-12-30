package com.best.peng.repository;

import java.util.Date;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.best.peng.domian.BestUser;

public interface BestUserRepository extends JpaRepository<BestUser, Long>,JpaSpecificationExecutor<BestUser> {
	BestUser findByEmail(String email);
	
	BestUser findByEmailAndPassword(String email,String password);
	
	
	
	@Modifying
	@Query("UPDATE BestUser B SET B.loginDate=:loginDate WHERE B.email=:email")
	int updateBestUserLoginDate(@Param("loginDate") Date loginDate,@Param("email") String email);
	
	@Modifying
	@Query("UPDATE BestUser B SET B.password=:password WHERE B.userId=:userId")
	int updateBestUserPassword(@Param("userId")Long userId,@Param("password")String newPassword);
}
