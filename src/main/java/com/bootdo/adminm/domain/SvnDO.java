package com.bootdo.adminm.domain;

import java.io.Serializable;
import java.util.Date;



/**
 * 
 * 
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2019-05-07 16:02:21
 */
public class SvnDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//
	private Integer svnid;
	//
	private String svnModule;
	//
	private String svnName;
	//
	private String svnUrl;
	//
	private String svnVersion;

	/**
	 * 设置：
	 */
	public void setSvnid(Integer svnid) {
		this.svnid = svnid;
	}
	/**
	 * 获取：
	 */
	public Integer getSvnid() {
		return svnid;
	}
	/**
	 * 设置：
	 */
	public void setSvnModule(String svnModule) {
		this.svnModule = svnModule;
	}
	/**
	 * 获取：
	 */
	public String getSvnModule() {
		return svnModule;
	}
	/**
	 * 设置：
	 */
	public void setSvnName(String svnName) {
		this.svnName = svnName;
	}
	/**
	 * 获取：
	 */
	public String getSvnName() {
		return svnName;
	}
	/**
	 * 设置：
	 */
	public void setSvnUrl(String svnUrl) {
		this.svnUrl = svnUrl;
	}
	/**
	 * 获取：
	 */
	public String getSvnUrl() {
		return svnUrl;
	}
	/**
	 * 设置：
	 */
	public void setSvnVersion(String svnVersion) {
		this.svnVersion = svnVersion;
	}
	/**
	 * 获取：
	 */
	public String getSvnVersion() {
		return svnVersion;
	}
}
