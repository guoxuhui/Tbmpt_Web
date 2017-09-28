package com.crfeb.tbmpt.project.model.vo;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotations.IdType;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;

/**
 *分部工程信息表
 */
public class ProFbgcVo implements Serializable{
	@TableField(exist=false)
	private static final long serialVersionUID = 1L;
	
	/** 编码生成规则:长年号+2016 月份数+四位自增数 0001 */
	@TableId(type = IdType.INPUT)
	private String id;
	
	@TableField(value = "PRO_ID")
	private String proId;
	private String proName;
	
	@TableField(value = "DWGC_ID")
	private String dwgcId;
	
	private String dwgcName;
	
	private String fbgcname;
		
	private String remarks;
	
	private String details;
	
	
	
	
	public String getDwgcId() {
		return dwgcId;
	}

	public void setDwgcId(String dwgcId) {
		this.dwgcId = dwgcId;
	}

	public String getDetails() {
		return details;
	}

	public void setDetails(String details) {
		this.details = details;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getProId() {
		return proId;
	}

	public void setProId(String proId) {
		this.proId = proId;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getProName() {
		return proName;
	}

	public void setProName(String proName) {
		this.proName = proName;
	}


	public String getFbgcname() {
		return fbgcname;
	}

	public void setFbgcname(String fbgcname) {
		this.fbgcname = fbgcname;
	}

	public String getDwgcName() {
		return dwgcName;
	}

	public void setDwgcName(String dwgcName) {
		this.dwgcName = dwgcName;
	}
	
	
}
