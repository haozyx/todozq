package com.bootdo.blog.service;

import com.bootdo.blog.domain.ArticleDO;
import com.bootdo.blog.domain.Tags;

import java.util.List;
import java.util.Map;

/**
 * 
 * 
 * @author wanghby
 * @email wanghby@yonyou.com
 * @date 2019-06-25 15:25:48
 */
public interface ArticleCustomService {
 

	int saveTags(Tags tag);
	
	List<Tags> findAllTags();

	int findRepeatByUrl(String articleUrl);

	/**
	 * 通过自定义连接来访问文章
	 * @param articleUrl
	 * @return
	 */
	ArticleDO findByArticleUrl(String articleUrl);

	/**
	 * 更新文章的点击次数
	 * @param article
	 */
	void updateArticleViews(ArticleDO article);
	
	Tags findTagsByName(String name);
	
	List<ArticleDO> findByContent(Map<String, Object> map);

	int count(Map<String, Object> params);

	void pushBaidu(Integer[] ids);

	List<ArticleDO> getByIds(Integer[] ids);

	List<ArticleDO> getArticleByViews();

	List<ArticleDO> getCommendArticle();

	List<ArticleDO> getTopArticles();

	ArticleDO getPreArticle(Integer id,String category);

	ArticleDO getBackArticle(Integer id,String category);
}
