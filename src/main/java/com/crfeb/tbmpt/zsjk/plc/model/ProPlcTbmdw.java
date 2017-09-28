package com.crfeb.tbmpt.zsjk.plc.model;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotations.IdType;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;

/**
 *
 * 盾构机点位与标准关联表
 *
 */
@TableName("PRO_PLC_TBMDW")
public class ProPlcTbmdw implements Serializable {

	@TableField(exist = false)
	private static final long serialVersionUID = 1L;

	/** ID */
	@TableId(type = IdType.UUID)
	private String id;

	/** 盾构机ID */
	private String tbmid;

	/** 盾构机CODE */
	private String tbmcode;

	/** PLC点位名称 */
	private String tagname;

	/** 标准ID */
	private String dwid;

	/** 标准名称 */
	private String dwname;

	/** 标准类型 */
	private String dwlx;


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

	public String getTagname() {
		return this.tagname;
	}

	public void setTagname(String tagname) {
		this.tagname = tagname;
	}

	public String getDwid() {
		return this.dwid;
	}

	public void setDwid(String dwid) {
		this.dwid = dwid;
	}

	public String getDwname() {
		return this.dwname;
	}

	public void setDwname(String dwname) {
		this.dwname = dwname;
	}

	public String getDwlx() {
		return this.dwlx;
	}

	public void setDwlx(String dwlx) {
		this.dwlx = dwlx;
	}

}
