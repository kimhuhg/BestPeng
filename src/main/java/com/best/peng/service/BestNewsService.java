package com.best.peng.service;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.best.peng.domian.BestNews;

public interface BestNewsService{
	
	BestNews saveOrUpdate(BestNews news);
	
	void delete(Long id);
	
	Page<BestNews> getBestNews(Pageable page);
	
	Page<BestNews> getBestNewsWithPage(String title,Pageable page);
}
