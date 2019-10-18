package com.bootdo.common.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix="bootdo")
public class BootdoConfig {
 
	//上传路径
	private String uploadPath;
	//用户名
	private String username;
	//密码
	private String password;
    //又拍云仓库名
	private String upyuname;
	//又拍云账号
	private String up_username;
	//又拍云密码
	private String up_password;
	//百度推动Token
	private String baiduToken;
	//域名
	private String domain;
	public String getUploadPath() {
		return uploadPath;
	}
	public void setUploadPath(String uploadPath) {
		this.uploadPath = uploadPath;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getUpyuname() {
		return upyuname;
	}
	public void setUpyuname(String upyuname) {
		this.upyuname = upyuname;
	}
	public String getUp_username() {
		return up_username;
	}
	public void setUp_username(String up_username) {
		this.up_username = up_username;
	}
	public String getUp_password() {
		return up_password;
	}
	public void setUp_password(String up_password) {
		this.up_password = up_password;
	}
	public String getBaiduToken() {
		return baiduToken;
	}
	public void setBaiduToken(String baiduToken) {
		this.baiduToken = baiduToken;
	}
	public String getDomain() {
		return domain;
	}
	public void setDomain(String domain) {
		this.domain = domain;
	}
	
	
	
}
