package com.bootdo.adminm.domain;

import java.io.Serializable;
import java.util.Date;



/**
 * 
 * 
 * @author wanghby
 * @email wanghby@yonyou.com
 * @date 2019-12-05 14:28:24
 */
public class TablesDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//
	private Integer id;
	//表分类
	private Integer tablecategory;
	//表名
	private String entablename;
	//中文表名
	private String zntablename;
	//表之间关联关系
	private String tablerelations;
	//视图关联
	private String vwrelations;
	//序列关联
	private String seqrelation;
	//备注说明
	private String note;

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
	 * 设置：表分类
	 */
	public void setTablecategory(Integer tablecategory) {
		this.tablecategory = tablecategory;
	}
	/**
	 * 获取：表分类
	 */
	public Integer getTablecategory() {
		return tablecategory;
	}
	/**
	 * 设置：表名
	 */
	public void setEntablename(String entablename) {
		this.entablename = entablename;
	}
	/**
	 * 获取：表名
	 */
	public String getEntablename() {
		return entablename;
	}
	/**
	 * 设置：中文表名
	 */
	public void setZntablename(String zntablename) {
		this.zntablename = zntablename;
	}
	/**
	 * 获取：中文表名
	 */
	public String getZntablename() {
		return zntablename;
	}
	/**
	 * 设置：表之间关联关系
	 */
	public void setTablerelations(String tablerelations) {
		this.tablerelations = tablerelations;
	}
	/**
	 * 获取：表之间关联关系
	 */
	public String getTablerelations() {
		return tablerelations;
	}
	/**
	 * 设置：视图关联
	 */
	public void setVwrelations(String vwrelations) {
		this.vwrelations = vwrelations;
	}
	/**
	 * 获取：视图关联
	 */
	public String getVwrelations() {
		return vwrelations;
	}
	/**
	 * 设置：序列关联
	 */
	public void setSeqrelation(String seqrelation) {
		this.seqrelation = seqrelation;
	}
	/**
	 * 获取：序列关联
	 */
	public String getSeqrelation() {
		return seqrelation;
	}
	/**
	 * 设置：备注说明
	 */
	public void setNote(String note) {
		this.note = note;
	}
	/**
	 * 获取：备注说明
	 */
	public String getNote() {
		return note;
	}
}
