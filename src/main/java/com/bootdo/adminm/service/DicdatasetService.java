package com.bootdo.adminm.service;

import com.bootdo.adminm.domain.DicdatasetDO;

import java.util.List;
import java.util.Map;

/**
 * 
 * 
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2019-05-08 08:52:38
 */
public interface DicdatasetService {
	
	DicdatasetDO get(Integer id);
	
	List<DicdatasetDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(DicdatasetDO dicdataset);
	
	int update(DicdatasetDO dicdataset);
	
	int remove(Integer id);
	
	int batchRemove(Integer[] ids);
}
