package com.bootdo.adminm.dao;

import com.bootdo.adminm.domain.ThinglogsDO;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

/**
 * 
 * @author wanghby
 * @email wanghby@yonyou.com
 * @date 2019-05-15 10:49:48
 */
@Mapper
public interface ThinglogsDao {

	ThinglogsDO get(Integer id);
	
	List<ThinglogsDO> list(Map<String,Object> map);
	
	int count(Map<String,Object> map);
	
	int save(ThinglogsDO thinglogs);
	
	int update(ThinglogsDO thinglogs);
	
	int remove(Integer id);
	
	int batchRemove(Integer[] ids);
}
