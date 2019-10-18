package com.bootdo.adminm.domain;

import java.io.Serializable;
import java.util.Date;



/**
 * 文章分类表
 * 
 * @author wanghby
 * @email wanghby@yonyou.com
 * @date 2019-07-22 15:50:41
 */
public class CategoryDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//编号
	private Integer id;
	//名称
	private String name;
	//图标
	private String cIcon;
	//访问地址
	private String cUrl;
	//打开方式
	private String cTarget;

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
	 * 设置：名称
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * 获取：名称
	 */
	public String getName() {
		return name;
	}
	/**
	 * 设置：图标
	 */
	public void setCIcon(String cIcon) {
		this.cIcon = cIcon;
	}
	/**
	 * 获取：图标
	 */
	public String getCIcon() {
		return cIcon;
	}
	/**
	 * 设置：访问地址
	 */
	public void setCUrl(String cUrl) {
		this.cUrl = cUrl;
	}
	/**
	 * 获取：访问地址
	 */
	public String getCUrl() {
		return cUrl;
	}
	/**
	 * 设置：打开方式
	 */
	public void setCTarget(String cTarget) {
		this.cTarget = cTarget;
	}
	/**
	 * 获取：打开方式
	 */
	public String getCTarget() {
		return cTarget;
	}
}
