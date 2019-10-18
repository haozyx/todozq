package com.bootdo.blog.dao;

import com.bootdo.blog.domain.ArticleDO;
import com.bootdo.blog.domain.Tags;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

/**
 * 
 * @author wanghby
 * @email wanghby@yonyou.com
 * @date 2019-06-25 15:25:48
 */
@Mapper
public interface ArticleCustomDao {

	int findRepeatByUrl(String articleUrl);

	ArticleDO findByArticleUrl(String articleUrl);

	void updateArticleViews(ArticleDO article);

	int saveTags(Tags tag);

	List<Tags> findAllTags();

	Tags findTagsByName(String name);

	List<ArticleDO> findByContent(Map<String, Object> map);

	int count(Map<String, Object> map);

	void pushBaidu(Integer[] ids);

	List<ArticleDO> getByIds(Integer[] ids);

	List<ArticleDO>  getArticleByViews();

	List<ArticleDO>  getCommendArticle();

	List<ArticleDO> getTopArticles();

	ArticleDO getPreArticle(Integer id,String category);

	ArticleDO getBackArticle(Integer id,String category);
}
