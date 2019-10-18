package com.bootdo.adminm.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;


@Mapper
public interface AppCustomDao {
	
//	------------------------------------------
//	执行自定义的SQL start
	 int delete(String sql);

	 int update(String sql);

	 List<Map<String, Object>> selectList(String sql);

	 String selectOne(String statement);
//	执行自定义的SQL end
//	------------------------------------------
	 
}
