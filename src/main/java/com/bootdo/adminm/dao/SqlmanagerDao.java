package com.bootdo.adminm.dao;

import com.bootdo.adminm.domain.SqlmanagerDO;

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
public interface SqlmanagerDao {

	SqlmanagerDO get(Integer id);
	
	List<SqlmanagerDO> list(Map<String,Object> map);
	
	int count(Map<String,Object> map);
	
	int save(SqlmanagerDO sqlmanager);
	
	int update(SqlmanagerDO sqlmanager);
	
	int remove(Integer id);
	
	int batchRemove(Integer[] ids);
}
