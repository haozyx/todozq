package com.bootdo.adminm.service;

import com.bootdo.adminm.domain.TableinfosDO;

import java.util.List;
import java.util.Map;

/**
 * 
 * 
 * @author wanghby
 * @email wanghby@yonyou.com
 * @date 2019-05-27 14:42:36
 */
public interface TableinfosService {
	
	TableinfosDO get(Integer id);
	
	List<TableinfosDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(TableinfosDO tableinfos);
	
	int update(TableinfosDO tableinfos);
	
	int remove(Integer id);
	
	int batchRemove(Integer[] ids);
}
