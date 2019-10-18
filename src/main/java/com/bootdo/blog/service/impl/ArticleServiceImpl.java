package com.bootdo.blog.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.bootdo.blog.dao.ArticleDao;
import com.bootdo.blog.domain.ArticleDO;
import com.bootdo.blog.domain.Tags;
import com.bootdo.blog.service.ArticleService;



@Service
public class ArticleServiceImpl implements ArticleService {
	@Autowired
	private ArticleDao articleDao;
	
	@Override
	public ArticleDO get(Integer id){
		return articleDao.get(id);
	}
	
	@Override
	public List<ArticleDO> list(Map<String, Object> map){
		return articleDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return articleDao.count(map);
	}
	
	@Override
	public int save(ArticleDO article){
		return articleDao.save(article);
	}
	
	@Override
	public int update(ArticleDO article){
		return articleDao.update(article);
	}
	
	@Override
	public int remove(Integer id){
		return articleDao.remove(id);
	}
	
	@Override
	public int batchRemove(Integer[] ids){
		return articleDao.batchRemove(ids);
	}
	
}
