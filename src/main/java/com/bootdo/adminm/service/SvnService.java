package com.bootdo.adminm.service;

import com.bootdo.adminm.domain.SvnDO;

import java.util.List;
import java.util.Map;

/**
 * 
 * 
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2019-05-07 16:02:21
 */
public interface SvnService {
	
	SvnDO get(Integer svnid);
	
	List<SvnDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(SvnDO svn);
	
	int update(SvnDO svn);
	
	int remove(Integer svnid);
	
	int batchRemove(Integer[] svnids);
}
