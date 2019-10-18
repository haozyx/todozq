package com.bootdo.adminm.service;

import com.bootdo.adminm.domain.IndexmenuDO;

import java.util.List;
import java.util.Map;

/**
 * 
 * 
 * @author wanghby
 * @email wanghby@yonyou.com
 * @date 2019-07-04 11:21:48
 */
public interface IndexmenuService {
	
	IndexmenuDO get(Integer id);
	
	List<IndexmenuDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(IndexmenuDO indexmenu);
	
	int update(IndexmenuDO indexmenu);
	
	int remove(Integer id);
	
	int batchRemove(Integer[] ids);
}
