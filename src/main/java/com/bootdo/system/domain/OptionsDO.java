package com.bootdo.system.domain;

import java.io.Serializable;
import java.util.Date;



/**
 * 
 * 
 * @author wanghby
 * @email wanghby@yonyou.com
 * @date 2019-07-26 10:10:53
 */
public class OptionsDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//选项名
	private String optionName;
	//选项值
	private String optionValue;

	/**
	 * 设置：选项名
	 */
	public void setOptionName(String optionName) {
		this.optionName = optionName;
	}
	/**
	 * 获取：选项名
	 */
	public String getOptionName() {
		return optionName;
	}
	/**
	 * 设置：选项值
	 */
	public void setOptionValue(String optionValue) {
		this.optionValue = optionValue;
	}
	/**
	 * 获取：选项值
	 */
	public String getOptionValue() {
		return optionValue;
	}
}
