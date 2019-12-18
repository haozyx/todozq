package com.bootdo.adminm.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.bootdo.adminm.dao.TablefieldDao;
import com.bootdo.adminm.domain.TablefieldDO;
import com.bootdo.adminm.service.TablefieldService;



@Service
public class TablefieldServiceImpl implements TablefieldService {
	@Autowired
	private TablefieldDao tablefieldDao;
	
	@Override
	public TablefieldDO get(Integer id){
		return tablefieldDao.get(id);
	}
	
	@Override
	public List<TablefieldDO> list(Map<String, Object> map){
		return tablefieldDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return tablefieldDao.count(map);
	}
	
	@Override
	public int save(TablefieldDO tablefield){
		return tablefieldDao.save(tablefield);
	}
	
	@Override
	public int update(TablefieldDO tablefield){
		return tablefieldDao.update(tablefield);
	}
	
	@Override
	public int remove(Integer id){
		return tablefieldDao.remove(id);
	}
	
	@Override
	public int batchRemove(Integer[] ids){
		return tablefieldDao.batchRemove(ids);
	}
	
}
