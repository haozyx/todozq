package com.bootdo.adminm.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.bootdo.adminm.dao.DicdatasetDao;
import com.bootdo.adminm.domain.DicdatasetDO;
import com.bootdo.adminm.service.DicdatasetService;



@Service
public class DicdatasetServiceImpl implements DicdatasetService {
	@Autowired
	private DicdatasetDao dicdatasetDao;
	
	@Override
	public DicdatasetDO get(Integer id){
		return dicdatasetDao.get(id);
	}
	
	@Override
	public List<DicdatasetDO> list(Map<String, Object> map){
		return dicdatasetDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return dicdatasetDao.count(map);
	}
	
	@Override
	@CacheEvict(value = "dicdata", allEntries = true, beforeInvocation = true)
	public int save(DicdatasetDO dicdataset){
		return dicdatasetDao.save(dicdataset);
	}
	
	@Override
	public int update(DicdatasetDO dicdataset){
		return dicdatasetDao.update(dicdataset);
	}
	
	@Override
	public int remove(Integer id){
		return dicdatasetDao.remove(id);
	}
	
	@Override
	public int batchRemove(Integer[] ids){
		return dicdatasetDao.batchRemove(ids);
	}
	
}
