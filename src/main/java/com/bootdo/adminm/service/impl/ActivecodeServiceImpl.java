package com.bootdo.adminm.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bootdo.adminm.dao.ActivecodeDao;
import com.bootdo.adminm.domain.ActivecodeDO;
import com.bootdo.adminm.service.ActivecodeService;



@Service
public class ActivecodeServiceImpl implements ActivecodeService {
	@Autowired
	private ActivecodeDao activecodeDao;
	
	@Override
	public ActivecodeDO get(Integer id){
		return activecodeDao.get(id);
	}
	
	@Override
	public List<ActivecodeDO> list(Map<String, Object> map){
		return activecodeDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return activecodeDao.count(map);
	}
	
	@Override
	public int save(ActivecodeDO activecode){
		return activecodeDao.save(activecode);
	}
	
	@Override
	public int update(ActivecodeDO activecode){
		return activecodeDao.update(activecode);
	}
	
	@Override
	public int remove(Integer id){
		return activecodeDao.remove(id);
	}
	
	@Override
	public int batchRemove(Integer[] ids){
		return activecodeDao.batchRemove(ids);
	}
	
}
