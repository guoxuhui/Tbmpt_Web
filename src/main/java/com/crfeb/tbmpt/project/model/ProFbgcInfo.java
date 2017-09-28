package com.crfeb.tbmpt.project.model;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotations.IdType;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;

/**
 * 
 * 分部工程信息表
 *
 */
@TableName("PRO_FBGC_INFO")
public class ProFbgcInfo implements Serializable{
	@TableField(exist = false)
	private static final long serialVersionUID = 1L;
	
	/** 编码生成规则:长年号+2016 月份数+四位自增数 0001 */
	@TableId(type = IdType.UUID)
	private String id;
	
	@TableField(value = "PRO_ID")
	private String proId;
	
	@TableField(value = "DWGC_ID")
	private String dwgcId;
	
	private String fbgcname;
		
	private String remarks;

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

	

	public String getDwgcId() {
		return dwgcId;
	}

	public void setDwgcId(String dwgcId) {
		this.dwgcId = dwgcId;
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

	public String getFbgcname() {
		return fbgcname;
	}

	public void setFbgcname(String fbgcname) {
		this.fbgcname = fbgcname;
	}

	
}
