package com.bootdo.adminm.domain;

import java.io.Serializable;
import java.util.Date;



/**
 * 
 * 
 * @author wanghby
 * @email wanghby@yonyou.com
 * @date 2019-05-15 10:49:48
 */
public class SqlmanagerDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//
	private Integer id;
	//
	private String name;
	//
	private String sqlcontent;
	//
	private String sqldesp;

	/**
	 * 设置：
	 */
	public void setId(Integer id) {
		this.id = id;
	}
	/**
	 * 获取：
	 */
	public Integer getId() {
		return id;
	}
	/**
	 * 设置：
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * 获取：
	 */
	public String getName() {
		return name;
	}
	/**
	 * 设置：
	 */
	public void setSqlcontent(String sqlcontent) {
		this.sqlcontent = sqlcontent;
	}
	/**
	 * 获取：
	 */
	public String getSqlcontent() {
		return sqlcontent;
	}
	/**
	 * 设置：
	 */
	public void setSqldesp(String sqldesp) {
		this.sqldesp = sqldesp;
	}
	/**
	 * 获取：
	 */
	public String getSqldesp() {
		return sqldesp;
	}
}
