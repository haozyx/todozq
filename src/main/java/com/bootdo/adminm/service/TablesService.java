package com.bootdo.adminm.service;

import com.bootdo.adminm.domain.TablesDO;

import java.util.List;
import java.util.Map;

/**
 * 
 * 
 * @author wanghby
 * @email wanghby@yonyou.com
 * @date 2019-12-05 14:28:24
 */
public interface TablesService {
	
	TablesDO get(Integer id);
	
	List<TablesDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(TablesDO tables);
	
	int update(TablesDO tables);
	
	int remove(Integer id);
	
	int batchRemove(Integer[] ids);
}
