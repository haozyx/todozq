package com.bootdo.adminm.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.bootdo.adminm.dao.ThinglogsDao;
import com.bootdo.adminm.domain.ThinglogsDO;
import com.bootdo.adminm.service.ThinglogsService;



@Service
public class ThinglogsServiceImpl implements ThinglogsService {
	@Autowired
	private ThinglogsDao thinglogsDao;
	
	@Override
	public ThinglogsDO get(Integer id){
		return thinglogsDao.get(id);
	}
	
	@Override
	public List<ThinglogsDO> list(Map<String, Object> map){
		return thinglogsDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return thinglogsDao.count(map);
	}
	
	@Override
	public int save(ThinglogsDO thinglogs){
		return thinglogsDao.save(thinglogs);
	}
	
	@Override
	public int update(ThinglogsDO thinglogs){
		return thinglogsDao.update(thinglogs);
	}
	
	@Override
	public int remove(Integer id){
		return thinglogsDao.remove(id);
	}
	
	@Override
	public int batchRemove(Integer[] ids){
		return thinglogsDao.batchRemove(ids);
	}
	
}
