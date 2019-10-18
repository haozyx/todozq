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
public interface ArticleService {
	
	ArticleDO get(Integer id);
	
	List<ArticleDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(ArticleDO article);
	
	int update(ArticleDO article);
	
	int remove(Integer id);
	
	int batchRemove(Integer[] ids);

}
