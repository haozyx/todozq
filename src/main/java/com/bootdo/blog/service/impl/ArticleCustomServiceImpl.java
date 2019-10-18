package com.bootdo.blog.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bootdo.blog.dao.ArticleCustomDao;
import com.bootdo.blog.domain.ArticleDO;
import com.bootdo.blog.domain.Tags;
import com.bootdo.blog.service.ArticleCustomService;



@Service
public class ArticleCustomServiceImpl implements ArticleCustomService {
	@Autowired
	private ArticleCustomDao articleDao;
	
	 

	@Override
	public int findRepeatByUrl(String articleUrl) {
		return articleDao.findRepeatByUrl(articleUrl);
	}

	@Override
	public ArticleDO findByArticleUrl(String articleUrl) {
		return articleDao.findByArticleUrl(articleUrl);
	}

	@Override
	public void updateArticleViews(ArticleDO article) {
		articleDao.updateArticleViews(article);
	}

	@Override
	public int saveTags(Tags tag) {
		return articleDao.saveTags(tag);
	}

	@Override
	public List<Tags> findAllTags() {
		return articleDao.findAllTags();
	}

	@Override
	public Tags findTagsByName(String name) {
		return articleDao.findTagsByName(name);
	}

	@Override
	public int count(Map<String, Object> map){
		return articleDao.count(map);
	}
	@Override
	public List<ArticleDO> findByContent(Map<String, Object> map) {
		return articleDao.findByContent(map);
	}

	@Override
	public void pushBaidu(Integer[] ids) {
		articleDao.pushBaidu(ids);
	}

	@Override
	public List<ArticleDO> getByIds(Integer[] ids) {
		return articleDao.getByIds(ids);
	}

	@Override
	public List<ArticleDO> getArticleByViews() {
		return articleDao.getArticleByViews();
	}
	@Override
	public List<ArticleDO> getCommendArticle() {
		return articleDao.getCommendArticle();
	}
	@Override
	public List<ArticleDO> getTopArticles() {
		return articleDao.getTopArticles();
	}

	@Override
	public ArticleDO getPreArticle(Integer id,String category) {
		return articleDao.getPreArticle(id, category);
	}

	@Override
	public ArticleDO getBackArticle(Integer id,String category) {
		return articleDao.getBackArticle(id,category);
	}
	
}
