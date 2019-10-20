package com.bootdo.adminm.dao;

import com.bootdo.adminm.domain.AppuserDO;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

/**
 * 
 * @author wanghby
 * @email wanghby@yonyou.com
 * @date 2019-10-19 16:49:41
 */
@Mapper
public interface AppuserDao {

	AppuserDO get(Integer id);
	
	List<AppuserDO> list(Map<String,Object> map);
	
	int count(Map<String,Object> map);
	
	int save(AppuserDO appuser);
	
	int update(AppuserDO appuser);
	
	int remove(Integer id);
	
	int batchRemove(Integer[] ids);
}
