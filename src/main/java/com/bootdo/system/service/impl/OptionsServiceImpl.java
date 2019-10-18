package com.bootdo.system.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bootdo.system.dao.OptionsDao;
import com.bootdo.system.domain.OptionsDO;
import com.bootdo.system.service.OptionsService;



@Service
public class OptionsServiceImpl implements OptionsService {
	@Autowired
	private OptionsDao optionsDao;
 
	@Override
	public List<OptionsDO> list(){
		return optionsDao.list();
	}
	
}
