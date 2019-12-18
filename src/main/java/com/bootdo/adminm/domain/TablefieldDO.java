package com.bootdo.adminm.domain;

import java.io.Serializable;
import java.util.Date;



/**
 * 
 * 
 * @author wanghby
 * @email wanghby@yonyou.com
 * @date 2019-12-18 20:36:01
 */
public class TablefieldDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//
	private Integer id;
	//关联表ID
	private Integer tableid;
	//字段名
	private String fieldname;
	//字段类型
	private String fieldtype;
	//字段注释
	private String fieldnote;
	//字段是否为空
	private String fieldisnull;

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
	 * 设置：关联表ID
	 */
	public void setTableid(Integer tableid) {
		this.tableid = tableid;
	}
	/**
	 * 获取：关联表ID
	 */
	public Integer getTableid() {
		return tableid;
	}
	/**
	 * 设置：字段名
	 */
	public void setFieldname(String fieldname) {
		this.fieldname = fieldname;
	}
	/**
	 * 获取：字段名
	 */
	public String getFieldname() {
		return fieldname;
	}
	/**
	 * 设置：字段类型
	 */
	public void setFieldtype(String fieldtype) {
		this.fieldtype = fieldtype;
	}
	/**
	 * 获取：字段类型
	 */
	public String getFieldtype() {
		return fieldtype;
	}
	/**
	 * 设置：字段注释
	 */
	public void setFieldnote(String fieldnote) {
		this.fieldnote = fieldnote;
	}
	/**
	 * 获取：字段注释
	 */
	public String getFieldnote() {
		return fieldnote;
	}
	/**
	 * 设置：字段是否为空
	 */
	public void setFieldisnull(String fieldisnull) {
		this.fieldisnull = fieldisnull;
	}
	/**
	 * 获取：字段是否为空
	 */
	public String getFieldisnull() {
		return fieldisnull;
	}
}
