package com.bootdo.adminm.domain;

import java.io.Serializable;
import java.util.Date;



/**
 * 
 * 
 * @author wanghby
 * @email wanghby@yonyou.com
 * @date 2019-10-19 16:49:41
 */
public class AppuserDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//
	private Integer id;
	//
	private String username;
	//
	private String password;
	//手机号
	private String phone;
	//微信
	private String weixin;
	//注册时间
	private Date registime;
	//激活码
	private String activeCode;
	//过期时间
	private Date outdate;
	//
	private String qq;
	//
	private String status;
	//激活时间
	private Date activetime;

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
	public void setUsername(String username) {
		this.username = username;
	}
	/**
	 * 获取：
	 */
	public String getUsername() {
		return username;
	}
	/**
	 * 设置：
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	/**
	 * 获取：
	 */
	public String getPassword() {
		return password;
	}
	/**
	 * 设置：手机号
	 */
	public void setPhone(String phone) {
		this.phone = phone;
	}
	/**
	 * 获取：手机号
	 */
	public String getPhone() {
		return phone;
	}
	/**
	 * 设置：微信
	 */
	public void setWeixin(String weixin) {
		this.weixin = weixin;
	}
	/**
	 * 获取：微信
	 */
	public String getWeixin() {
		return weixin;
	}
	/**
	 * 设置：注册时间
	 */
	public void setRegistime(Date registime) {
		this.registime = registime;
	}
	/**
	 * 获取：注册时间
	 */
	public Date getRegistime() {
		return registime;
	}
	/**
	 * 设置：激活码
	 */
	public void setActiveCode(String activeCode) {
		this.activeCode = activeCode;
	}
	/**
	 * 获取：激活码
	 */
	public String getActiveCode() {
		return activeCode;
	}
	/**
	 * 设置：过期时间
	 */
	public void setOutdate(Date outdate) {
		this.outdate = outdate;
	}
	/**
	 * 获取：过期时间
	 */
	public Date getOutdate() {
		return outdate;
	}
	/**
	 * 设置：
	 */
	public void setQq(String qq) {
		this.qq = qq;
	}
	/**
	 * 获取：
	 */
	public String getQq() {
		return qq;
	}
	/**
	 * 设置：
	 */
	public void setStatus(String status) {
		this.status = status;
	}
	/**
	 * 获取：
	 */
	public String getStatus() {
		return status;
	}
	/**
	 * 设置：激活时间
	 */
	public void setActivetime(Date activetime) {
		this.activetime = activetime;
	}
	/**
	 * 获取：激活时间
	 */
	public Date getActivetime() {
		return activetime;
	}
}
