package com.bootdo.adminm.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.bootdo.adminm.dao.SqlmanagerDao;
import com.bootdo.adminm.domain.SqlmanagerDO;
import com.bootdo.adminm.service.SqlmanagerService;



@Service
public class SqlmanagerServiceImpl implements SqlmanagerService {
	@Autowired
	private SqlmanagerDao sqlmanagerDao;
	
	@Override
	public SqlmanagerDO get(Integer id){
		return sqlmanagerDao.get(id);
	}
	
	@Override
	public List<SqlmanagerDO> list(Map<String, Object> map){
		return sqlmanagerDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return sqlmanagerDao.count(map);
	}
	
	@Override
	public int save(SqlmanagerDO sqlmanager){
		return sqlmanagerDao.save(sqlmanager);
	}
	
	@Override
	public int update(SqlmanagerDO sqlmanager){
		return sqlmanagerDao.update(sqlmanager);
	}
	
	@Override
	public int remove(Integer id){
		return sqlmanagerDao.remove(id);
	}
	
	@Override
	public int batchRemove(Integer[] ids){
		return sqlmanagerDao.batchRemove(ids);
	}
	
}
