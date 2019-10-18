package com.bootdo.adminm.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bootdo.adminm.dao.AppCustomDao;
import com.bootdo.adminm.service.AppCustomService;


@Transactional(readOnly = true,rollbackFor = Exception.class)
@Service
public class AppCustomServiceImpl implements AppCustomService {

	@Autowired
	AppCustomDao dao;
	
 
//	------------------------------------------
//	执行自定义的SQL start
	@Transactional(readOnly = false,rollbackFor = Exception.class)
	@Override
	public int delete(String sql) {
		return dao.delete(sql);
	}
	@Transactional(readOnly = false,rollbackFor = Exception.class)
	@Override
	public int update(String sql) {
		return dao.update(sql);
	}
	@Override
	public List<Map<String, Object>> selectList(String sql) {
		return dao.selectList(sql);
	}
	@Override
	public String selectOne(String sql) {
		return dao.selectOne(sql);
	}
//	执行自定义的SQL end
//	------------------------------------------
	 
}
