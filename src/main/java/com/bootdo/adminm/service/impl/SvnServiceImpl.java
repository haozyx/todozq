package com.bootdo.adminm.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.bootdo.adminm.dao.SvnDao;
import com.bootdo.adminm.domain.SvnDO;
import com.bootdo.adminm.service.SvnService;



@Service
public class SvnServiceImpl implements SvnService {
	@Autowired
	private SvnDao svnDao;
	
	@Override
	public SvnDO get(Integer svnid){
		return svnDao.get(svnid);
	}
	
	@Override
	public List<SvnDO> list(Map<String, Object> map){
		return svnDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return svnDao.count(map);
	}
	
	@Override
	public int save(SvnDO svn){
		return svnDao.save(svn);
	}
	
	@Override
	public int update(SvnDO svn){
		return svnDao.update(svn);
	}
	
	@Override
	public int remove(Integer svnid){
		return svnDao.remove(svnid);
	}
	
	@Override
	public int batchRemove(Integer[] svnids){
		return svnDao.batchRemove(svnids);
	}
	
}
