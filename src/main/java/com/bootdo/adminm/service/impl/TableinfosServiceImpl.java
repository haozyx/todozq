package com.bootdo.adminm.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.bootdo.adminm.dao.TableinfosDao;
import com.bootdo.adminm.domain.TableinfosDO;
import com.bootdo.adminm.service.TableinfosService;



@Service
public class TableinfosServiceImpl implements TableinfosService {
	@Autowired
	private TableinfosDao tableinfosDao;
	
	@Override
	public TableinfosDO get(Integer id){
		return tableinfosDao.get(id);
	}
	
	@Override
	public List<TableinfosDO> list(Map<String, Object> map){
		return tableinfosDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return tableinfosDao.count(map);
	}
	
	@Override
	public int save(TableinfosDO tableinfos){
		return tableinfosDao.save(tableinfos);
	}
	
	@Override
	public int update(TableinfosDO tableinfos){
		return tableinfosDao.update(tableinfos);
	}
	
	@Override
	public int remove(Integer id){
		return tableinfosDao.remove(id);
	}
	
	@Override
	public int batchRemove(Integer[] ids){
		return tableinfosDao.batchRemove(ids);
	}
	
}
