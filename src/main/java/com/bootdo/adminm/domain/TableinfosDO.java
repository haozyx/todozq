package com.bootdo.adminm.domain;

import java.io.Serializable;
import java.util.Date;



/**
 * 
 * 
 * @author wanghby
 * @email wanghby@yonyou.com
 * @date 2019-05-27 14:42:36
 */
public class TableinfosDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//主键
	private Integer id;
	//所属模块
	private String tablemodule;
	//表明
	private String tablename;
	//中文名
	private String zname;
	//对应视图
	private String tableview;
	//表关联关系
	private String tablerelation;
	//备注
	private String tablenote;
	//列备注
	private String columnnote;

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
	public void setTablemodule(String tablemodule) {
		this.tablemodule = tablemodule;
	}
	/**
	 * 获取：
	 */
	public String getTablemodule() {
		return tablemodule;
	}
	/**
	 * 设置：
	 */
	public void setTablename(String tablename) {
		this.tablename = tablename;
	}
	/**
	 * 获取：
	 */
	public String getTablename() {
		return tablename;
	}
	/**
	 * 设置：
	 */
	public void setZname(String zname) {
		this.zname = zname;
	}
	/**
	 * 获取：
	 */
	public String getZname() {
		return zname;
	}
	/**
	 * 设置：
	 */
	public void setTableview(String tableview) {
		this.tableview = tableview;
	}
	/**
	 * 获取：
	 */
	public String getTableview() {
		return tableview;
	}
	/**
	 * 设置：
	 */
	public void setTablerelation(String tablerelation) {
		this.tablerelation = tablerelation;
	}
	/**
	 * 获取：
	 */
	public String getTablerelation() {
		return tablerelation;
	}
	/**
	 * 设置：
	 */
	public void setTablenote(String tablenote) {
		this.tablenote = tablenote;
	}
	/**
	 * 获取：
	 */
	public String getTablenote() {
		return tablenote;
	}
	/**
	 * 设置：
	 */
	public void setColumnnote(String columnnote) {
		this.columnnote = columnnote;
	}
	/**
	 * 获取：
	 */
	public String getColumnnote() {
		return columnnote;
	}
}
