package com.bootdo.adminm.dao;

import com.bootdo.adminm.domain.LinkDO;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

/**
 * 
 * @author wanghby
 * @email wanghby@yonyou.com
 * @date 2019-07-15 19:19:55
 */
@Mapper
public interface LinkDao {

	LinkDO get(Integer id);
	
	List<LinkDO> list(Map<String,Object> map);
	
	int count(Map<String,Object> map);
	
	int save(LinkDO link);
	
	int update(LinkDO link);
	
	int remove(Integer id);
	
	int batchRemove(Integer[] ids);
}
