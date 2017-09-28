package com.crfeb.tbmpt.zsjk.plc.model.dto;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotations.IdType;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;

/**
 *
 * 盾构机机器数据实时表
 *
 */
public class ProPlcBzRealDto implements Serializable {

	private static final long serialVersionUID = 1L;

	/** ID */
	private String id;

	/** 盾构机ID */
	private String tbmid;

	/** 盾构机CODE */
	private String tbmcode;

	/** 工业务点位数据类型 */
	private String tagtype;

	/** 工业库定位名称 */
	private String tagname;
	
	/** 工业库点位值 */
	private String tagvalue;
	
	private String tagtime;
	
	public String getTagtime() {
		return tagtime;
	}

	public void setTagtime(String tagtime) {
		this.tagtime = tagtime;
	}

	/** 标准点位ID */
	private String dwid;
	
	/** 标准点位NAME */
	private String dwname;
	
	/** 描述 */
	private String ms;

	/** 备注 */
	private String bz;


	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTbmid() {
		return this.tbmid;
	}

	public void setTbmid(String tbmid) {
		this.tbmid = tbmid;
	}

	public String getTbmcode() {
		return this.tbmcode;
	}

	public void setTbmcode(String tbmcode) {
		this.tbmcode = tbmcode;
	}

	public String getTagtype() {
		return this.tagtype;
	}

	public void setTagtype(String tagtype) {
		this.tagtype = tagtype;
	}

	public String getTagvalue() {
		return this.tagvalue;
	}

	public void setTagvalue(String tagvalue) {
		this.tagvalue = tagvalue;
	}

	public String getMs() {
		return this.ms;
	}

	public void setMs(String ms) {
		this.ms = ms;
	}

	public String getTagname() {
		return this.tagname;
	}

	public void setTagname(String tagname) {
		this.tagname = tagname;
	}

	public String getBz() {
		return this.bz;
	}

	public void setBz(String bz) {
		this.bz = bz;
	}

	public String getDwid() {
		return dwid;
	}

	public void setDwid(String dwid) {
		this.dwid = dwid;
	}

	public String getDwname() {
		return dwname;
	}

	public void setDwname(String dwname) {
		this.dwname = dwname;
	}

}
