package com.bootdo.adminm.domain;

import java.io.Serializable;
import java.util.Date;



/**
 * 
 * 
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2019-05-08 08:52:38
 */
public class DicdatasetDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//
	private Integer id;
	//
	private Integer dicId;
	//
	private String disName;
	//
	private String disCode;

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
	public void setDicId(Integer dicId) {
		this.dicId = dicId;
	}
	/**
	 * 获取：
	 */
	public Integer getDicId() {
		return dicId;
	}
	/**
	 * 设置：
	 */
	public void setDisName(String disName) {
		this.disName = disName;
	}
	/**
	 * 获取：
	 */
	public String getDisName() {
		return disName;
	}
	/**
	 * 设置：
	 */
	public void setDisCode(String disCode) {
		this.disCode = disCode;
	}
	/**
	 * 获取：
	 */
	public String getDisCode() {
		return disCode;
	}
}
