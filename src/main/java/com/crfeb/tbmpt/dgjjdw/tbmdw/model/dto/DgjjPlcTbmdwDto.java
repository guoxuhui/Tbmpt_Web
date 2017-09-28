package com.crfeb.tbmpt.dgjjdw.tbmdw.model.dto;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotations.IdType;
import com.baomidou.mybatisplus.annotations.TableId;

public class DgjjPlcTbmdwDto implements Serializable{
	
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
	
	/**
	 * 标准盾构机名称
	 */
	private String bztbmname;
	
	/**
	 * 标准盾构机编号
	 */
	private String bztbmCode;
	
	/**
	 * 开始时间
	 */
	private String startTime;
	
	/**
	 * 结束时间
	 */
	private String endTime;
	
	private String details;
	

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public String getBztbmCode() {
		return bztbmCode;
	}

	public void setBztbmCode(String bztbmCode) {
		this.bztbmCode = bztbmCode;
	}

	public String getDetails() {
		return details;
	}

	public void setDetails(String details) {
		this.details = details;
	}

	public String getBztbmname() {
		return bztbmname;
	}

	public void setBztbmname(String bztbmname) {
		this.bztbmname = bztbmname;
	}

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
