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
public class ThinglogsDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//
	private Integer id;
	//
	private String name;
	//
	private String position;
	//
	private String record;
	//
	private Date recordtime;

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
	public void setPosition(String position) {
		this.position = position;
	}
	/**
	 * 获取：
	 */
	public String getPosition() {
		return position;
	}
	/**
	 * 设置：
	 */
	public void setRecord(String record) {
		this.record = record;
	}
	/**
	 * 获取：
	 */
	public String getRecord() {
		return record;
	}
	/**
	 * 设置：
	 */
	public void setRecordtime(Date recordtime) {
		this.recordtime = recordtime;
	}
	/**
	 * 获取：
	 */
	public Date getRecordtime() {
		return recordtime;
	}
}
