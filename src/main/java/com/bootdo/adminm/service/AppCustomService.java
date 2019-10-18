package com.bootdo.adminm.service;

import java.util.List;
import java.util.Map;


public interface AppCustomService {
//	------------------------------------------
//	执行自定义的SQL start
	 int delete(String sql);

	 int update(String sql);

	 List<Map<String, Object>> selectList(String sql);

	 String selectOne(String sql);
//	执行自定义的SQL end
//	------------------------------------------
	
	
	 
	
}
