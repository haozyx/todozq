package com.bootdo.adminm.dao;

import com.bootdo.adminm.domain.TablefieldDO;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

/**
 * 
 * @author wanghby
 * @email wanghby@yonyou.com
 * @date 2019-12-18 19:59:40
 */
@Mapper
public interface TablefieldDao {

	TablefieldDO get(Integer id);
	
	List<TablefieldDO> list(Map<String,Object> map);
	
	int count(Map<String,Object> map);
	
	int save(TablefieldDO tablefield);
	
	int update(TablefieldDO tablefield);
	
	int remove(Integer id);
	
	int batchRemove(Integer[] ids);
}
