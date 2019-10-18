package com.bootdo.adminm.dao;

import com.bootdo.adminm.domain.CategoryDO;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

/**
 * 文章分类表
 * @author wanghby
 * @email wanghby@yonyou.com
 * @date 2019-07-22 15:50:41
 */
@Mapper
public interface CategoryDao {

	CategoryDO get(Integer id);
	
	List<CategoryDO> list(Map<String,Object> map);
	
	int count(Map<String,Object> map);
	
	int save(CategoryDO category);
	
	int update(CategoryDO category);
	
	int remove(Integer id);
	
	int batchRemove(Integer[] ids);
}
