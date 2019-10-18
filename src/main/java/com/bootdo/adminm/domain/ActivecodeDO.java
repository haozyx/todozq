package com.bootdo.adminm.domain;

import java.io.Serializable;
import java.util.Date;



/**
 * 
 * 
 * @author wanghby
 * @email wanghby@yonyou.com
 * @date 2019-08-14 14:55:31
 */
public class ActivecodeDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//编号
	private Integer id;
	//
	private String code;
	//
	private String type;
	//是否可用
	private String status;

	
	public ActivecodeDO() {
		super();
	}
	public ActivecodeDO(Integer id, String code, String type, String status) {
		super();
		this.id = id;
		this.code = code;
		this.type = type;
		this.status = status;
	}
	public ActivecodeDO(String code, String type, String status) {
		super();
		this.code = code;
		this.type = type;
		this.status = status;
	}
	/**
	 * 设置：编号
	 */
	public void setId(Integer id) {
		this.id = id;
	}
	/**
	 * 获取：编号
	 */
	public Integer getId() {
		return id;
	}
	/**
	 * 设置：
	 */
	public void setCode(String code) {
		this.code = code;
	}
	/**
	 * 获取：
	 */
	public String getCode() {
		return code;
	}
	/**
	 * 设置：
	 */
	public void setType(String type) {
		this.type = type;
	}
	/**
	 * 获取：
	 */
	public String getType() {
		return type;
	}
	/**
	 * 设置：是否可用
	 */
	public void setStatus(String status) {
		this.status = status;
	}
	/**
	 * 获取：是否可用
	 */
	public String getStatus() {
		return status;
	}
}
