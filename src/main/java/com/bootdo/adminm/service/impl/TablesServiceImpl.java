package com.bootdo.adminm.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.bootdo.adminm.dao.TablesDao;
import com.bootdo.adminm.domain.TablesDO;
import com.bootdo.adminm.service.TablesService;



@Service
public class TablesServiceImpl implements TablesService {
	@Autowired
	private TablesDao tablesDao;
	
	@Override
	public TablesDO get(Integer id){
		return tablesDao.get(id);
	}
	
	@Override
	public List<TablesDO> list(Map<String, Object> map){
		return tablesDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return tablesDao.count(map);
	}
	
	@Override
	public int save(TablesDO tables){
		return tablesDao.save(tables);
	}
	
	@Override
	public int update(TablesDO tables){
		return tablesDao.update(tables);
	}
	
	@Override
	public int remove(Integer id){
		return tablesDao.remove(id);
	}
	
	@Override
	public int batchRemove(Integer[] ids){
		return tablesDao.batchRemove(ids);
	}
	
}
