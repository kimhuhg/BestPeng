package com.best.peng.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.best.peng.domian.BestUser;

public interface BestUserRepository extends JpaRepository<BestUser, Long> {
	BestUser findByEmail(String email);
}
