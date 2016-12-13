package com.best.peng.service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.best.peng.domian.BestNews;
import com.best.peng.repository.BestNewsRepository;
import com.best.peng.service.BestNewsService;

@Service("bestNewsService")
public class BestNewsServiceImpl implements BestNewsService {
	
	private final String DATE_FORMAT="yyy-mm-dd HH:mm:ss";
	
	@Autowired
	private BestNewsRepository bestNewsRepository;

	@Override
	public BestNews saveOrUpdate(BestNews news) {
		news.setCreateDate(Calendar.getInstance().getTime());
		return bestNewsRepository.save(news);
	}

	@Override
	public void delete(Long id) {
		bestNewsRepository.delete(id);
	}

	@Override
	public Page<BestNews> getBestNewsWithPage(String title,Pageable page) {
		return bestNewsRepository.findByBestNews(title,page);
	}

	@Override
	public Page<BestNews> getBestNews(Pageable page) {
		return bestNewsRepository.findAll(page);
	}
	
	
}
