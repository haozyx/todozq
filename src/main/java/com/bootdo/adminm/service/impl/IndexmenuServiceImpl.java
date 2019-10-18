package com.bootdo.adminm.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.bootdo.adminm.dao.IndexmenuDao;
import com.bootdo.adminm.domain.IndexmenuDO;
import com.bootdo.adminm.service.IndexmenuService;



@Service
public class IndexmenuServiceImpl implements IndexmenuService {
	@Autowired
	private IndexmenuDao indexmenuDao;
	
	@Override
	public IndexmenuDO get(Integer id){
		return indexmenuDao.get(id);
	}
	
	@Override
	public List<IndexmenuDO> list(Map<String, Object> map){
		return indexmenuDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return indexmenuDao.count(map);
	}
	
	@Override
	public int save(IndexmenuDO indexmenu){
		return indexmenuDao.save(indexmenu);
	}
	
	@Override
	public int update(IndexmenuDO indexmenu){
		return indexmenuDao.update(indexmenu);
	}
	
	@Override
	public int remove(Integer id){
		return indexmenuDao.remove(id);
	}
	
	@Override
	public int batchRemove(Integer[] ids){
		return indexmenuDao.batchRemove(ids);
	}
	
}
