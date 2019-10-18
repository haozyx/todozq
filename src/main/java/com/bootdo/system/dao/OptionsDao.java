package com.bootdo.system.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.bootdo.system.domain.OptionsDO;

/**
 * 
 * @author wanghby
 * @email wanghby@yonyou.com
 * @date 2019-07-26 10:10:53
 */
@Mapper
public interface OptionsDao {

	List<OptionsDO> list();
	 
}
