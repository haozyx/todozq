package com.bootdo.adminm.dao;

import com.bootdo.adminm.domain.DicdatasetDO;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

/**
 * 
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2019-05-08 08:52:38
 */
@Mapper
public interface DicdatasetDao {

	DicdatasetDO get(Integer id);
	
	List<DicdatasetDO> list(Map<String,Object> map);
	
	int count(Map<String,Object> map);
	
	int save(DicdatasetDO dicdataset);
	
	int update(DicdatasetDO dicdataset);
	
	int remove(Integer id);
	
	int batchRemove(Integer[] ids);
}
