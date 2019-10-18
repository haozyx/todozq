package com.bootdo.adminm.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.bootdo.adminm.dao.LinkDao;
import com.bootdo.adminm.domain.LinkDO;
import com.bootdo.adminm.service.LinkService;



@Service
public class LinkServiceImpl implements LinkService {
	@Autowired
	private LinkDao linkDao;
	
	@Override
	public LinkDO get(Integer id){
		return linkDao.get(id);
	}
	
	@Override
	public List<LinkDO> list(Map<String, Object> map){
		return linkDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return linkDao.count(map);
	}
	
	@Override
	public int save(LinkDO link){
		return linkDao.save(link);
	}
	
	@Override
	public int update(LinkDO link){
		return linkDao.update(link);
	}
	
	@Override
	public int remove(Integer id){
		return linkDao.remove(id);
	}
	
	@Override
	public int batchRemove(Integer[] ids){
		return linkDao.batchRemove(ids);
	}
	
}
