package com.crfeb.tbmpt.sgkshgl.jcdzbgl.model.dto;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotations.TableField;

public class SgkshglJcdzbglDto implements Serializable{
	
	/** null */
	private Double objectid;

	/** 导出时用到“页面js,公用导出方法，是获取字段名为‘id’的值” */
	private Double id;
	
	/** null */
	private String proId;

	/** null */
	private String secId;

	/** null */
	private String jcId;

	/** null */
	private String jcType;

	/** null */
	private String lc;

	/** null */
	private String hh;

	/** null */
	private String xx;

	/** null */
	private String sx;

	/** null */
	private String x;

	/** null */
	private String y;

	/** null */
	private String z;

	/** null */
	private String bz;

	/** null */
//	private String shape;

	/** null */
	private String proName;

	/** null */
	private String secName;


	private String details;
	
	
	public String getDetails() {
		return details;
	}

	public void setDetails(String details) {
		this.details = details;
	}

	public Double getObjectid() {
		return this.objectid;
	}

	public void setObjectid(Double objectid) {
		this.objectid = objectid;
		this.id = objectid;
	}

	
	public Double getId() {
		return id;
	}

	public void setId(Double id) {
		this.id = id;
	}

	public String getProId() {
		return this.proId;
	}

	public void setProId(String proId) {
		this.proId = proId;
	}

	public String getSecId() {
		return this.secId;
	}

	public void setSecId(String secId) {
		this.secId = secId;
	}

	public String getJcId() {
		return this.jcId;
	}

	public void setJcId(String jcId) {
		this.jcId = jcId;
	}

	public String getJcType() {
		return this.jcType;
	}

	public void setJcType(String jcType) {
		this.jcType = jcType;
	}

	public String getLc() {
		return this.lc;
	}

	public void setLc(String lc) {
		this.lc = lc;
	}

	public String getHh() {
		return this.hh;
	}

	public void setHh(String hh) {
		this.hh = hh;
	}

	public String getXx() {
		return this.xx;
	}

	public void setXx(String xx) {
		this.xx = xx;
	}

	public String getSx() {
		return this.sx;
	}

	public void setSx(String sx) {
		this.sx = sx;
	}

	public String getX() {
		return this.x;
	}

	public void setX(String x) {
		this.x = x;
	}

	public String getY() {
		return this.y;
	}

	public void setY(String y) {
		this.y = y;
	}

	public String getZ() {
		return this.z;
	}

	public void setZ(String z) {
		this.z = z;
	}

	public String getBz() {
		return this.bz;
	}

	public void setBz(String bz) {
		this.bz = bz;
	}

//	public String getShape() {
//		return this.shape;
//	}
//
//	public void setShape(String shape) {
//		this.shape = shape;
//	}

	public String getProName() {
		return this.proName;
	}

	public void setProName(String proName) {
		this.proName = proName;
	}

	public String getSecName() {
		return this.secName;
	}

	public void setSecName(String secName) {
		this.secName = secName;
	}
}
