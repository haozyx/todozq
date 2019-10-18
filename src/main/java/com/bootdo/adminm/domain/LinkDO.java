package com.bootdo.adminm.domain;

import java.io.Serializable;
import java.util.Date;



/**
 * 
 * 
 * @author wanghby
 * @email wanghby@yonyou.com
 * @date 2019-07-15 19:19:55
 */
public class LinkDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//
	private Integer id;
	//名称
	private String linkName;
	//路径
	private String linkUrl;
	//链接logo
	private String linkLogo;
	//描述
	private String linkDescribe;

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
	 * 设置：名称
	 */
	public void setLinkName(String linkName) {
		this.linkName = linkName;
	}
	/**
	 * 获取：名称
	 */
	public String getLinkName() {
		return linkName;
	}
	/**
	 * 设置：路径
	 */
	public void setLinkUrl(String linkUrl) {
		this.linkUrl = linkUrl;
	}
	/**
	 * 获取：路径
	 */
	public String getLinkUrl() {
		return linkUrl;
	}
	/**
	 * 设置：链接logo
	 */
	public void setLinkLogo(String linkLogo) {
		this.linkLogo = linkLogo;
	}
	/**
	 * 获取：链接logo
	 */
	public String getLinkLogo() {
		return linkLogo;
	}
	/**
	 * 设置：描述
	 */
	public void setLinkDescribe(String linkDescribe) {
		this.linkDescribe = linkDescribe;
	}
	/**
	 * 获取：描述
	 */
	public String getLinkDescribe() {
		return linkDescribe;
	}
}
