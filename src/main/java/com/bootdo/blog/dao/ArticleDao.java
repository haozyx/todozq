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
public interface ArticleDao {

	ArticleDO get(Integer id);
	
	List<ArticleDO> list(Map<String,Object> map);
	
	int count(Map<String,Object> map);
	
	int save(ArticleDO article);
	
	int update(ArticleDO article);
	
	int remove(Integer id);
	
	int batchRemove(Integer[] ids);

}
