package com.bootdo.adminm.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.bootdo.adminm.dao.DicDao;
import com.bootdo.adminm.domain.DicDO;
import com.bootdo.adminm.domain.DicdatasetDO;
import com.bootdo.adminm.service.DicService;



@Service
public class DicServiceImpl implements DicService {
	@Autowired
	private DicDao dicDao;
	
	@Override
	public DicDO get(Integer id){
		return dicDao.get(id);
	}
	
	@Override
	public List<DicDO> list(Map<String, Object> map){
		return dicDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return dicDao.count(map);
	}
	
	@Override
	public int save(DicDO dic){
		return dicDao.save(dic);
	}
	
	@Override
	public int update(DicDO dic){
		return dicDao.update(dic);
	}
	
	@Override
	public int remove(Integer id){
		return dicDao.remove(id);
	}
	
	@Override
	public int batchRemove(Integer[] ids){
		return dicDao.batchRemove(ids);
	}

	@Override
	public List<DicDO> list() {
		return dicDao.listAll();
	}

	@Override
	@Cacheable(value = "dicdata", key = "'getDicDataByTypecode'+#typecode")
	public List<DicdatasetDO> getDicDataByTypecode(String typecode) {
		return dicDao.getDicDataByTypecode(typecode);
	}
	
}
