package com.bootdo.adminm.domain;

import java.io.Serializable;
import java.util.Date;



/**
 * 
 * 
 * @author wanghby
 * @email wanghby@yonyou.com
 * @date 2019-07-04 11:21:48
 */
public class IndexmenuDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//编号
	private Integer id;
	//菜单名称
	private String menuName;
	//菜单图标
	private String menuIcon;
	//显示顺序
	private Integer menuSort;
	//打开方式
	private String menuTarget;
	//菜单链接
	private String menuUrl;

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
	 * 设置：菜单名称
	 */
	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}
	/**
	 * 获取：菜单名称
	 */
	public String getMenuName() {
		return menuName;
	}
	/**
	 * 设置：菜单图标
	 */
	public void setMenuIcon(String menuIcon) {
		this.menuIcon = menuIcon;
	}
	/**
	 * 获取：菜单图标
	 */
	public String getMenuIcon() {
		return menuIcon;
	}
	/**
	 * 设置：显示顺序
	 */
	public void setMenuSort(Integer menuSort) {
		this.menuSort = menuSort;
	}
	/**
	 * 获取：显示顺序
	 */
	public Integer getMenuSort() {
		return menuSort;
	}
	/**
	 * 设置：打开方式
	 */
	public void setMenuTarget(String menuTarget) {
		this.menuTarget = menuTarget;
	}
	/**
	 * 获取：打开方式
	 */
	public String getMenuTarget() {
		return menuTarget;
	}
	/**
	 * 设置：菜单链接
	 */
	public void setMenuUrl(String menuUrl) {
		this.menuUrl = menuUrl;
	}
	/**
	 * 获取：菜单链接
	 */
	public String getMenuUrl() {
		return menuUrl;
	}
}
