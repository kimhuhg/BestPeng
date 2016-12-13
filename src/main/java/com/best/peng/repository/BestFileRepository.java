package com.best.peng.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.best.peng.domian.BestFile;

public interface BestFileRepository extends JpaRepository<BestFile, Long>{
	
}
