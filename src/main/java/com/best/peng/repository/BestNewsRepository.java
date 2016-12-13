package com.best.peng.repository;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.best.peng.domian.BestNews;

public interface BestNewsRepository extends JpaRepository<BestNews, Long>{
	
	/**
	 * //包含title的结果
	 * @param title
	 * @param page 分页信息
	 * @return
	 */
	@Query(value="select B from BestNews B where B.title like %?1%")
	Page<BestNews> findByBestNews(String title,Pageable page);
	
	/**
	 * 分页查询
	 * @param title
	 * @param page
	 * @return
	 */
	Page<BestNews> findByTitleContaining(String title,Pageable page);
	
}
