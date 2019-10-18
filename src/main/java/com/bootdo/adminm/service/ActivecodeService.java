package com.bootdo.adminm.service;

import java.util.List;
import java.util.Map;

import com.bootdo.adminm.domain.ActivecodeDO;

/**
 * 
 * 
 * @author wanghby
 * @email wanghby@yonyou.com
 * @date 2019-08-14 14:55:31
 */
public interface ActivecodeService {
	
	ActivecodeDO get(Integer id);
	
	List<ActivecodeDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(ActivecodeDO activecode);
	
	int update(ActivecodeDO activecode);
	
	int remove(Integer id);
	
	int batchRemove(Integer[] ids);
}
