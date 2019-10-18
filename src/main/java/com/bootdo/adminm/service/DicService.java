package com.bootdo.adminm.service;

import com.bootdo.adminm.domain.DicDO;
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
public interface DicService {
	
	DicDO get(Integer id);
	
	List<DicDO> list(Map<String, Object> map);
	
	List<DicDO> list();
	
	int count(Map<String, Object> map);
	
	int save(DicDO dic);
	
	int update(DicDO dic);
	
	int remove(Integer id);
	
	int batchRemove(Integer[] ids);
	
	List<DicdatasetDO> getDicDataByTypecode(String typecode);
}
