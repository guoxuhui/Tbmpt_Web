package com.crfeb.tbmpt.sgkshgl.jcdzbgl.model;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotations.IdType;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;

/**
 *
 * null
 *
 */
@TableName("TBMPT_SP.JCPOINT_NEW")
public class SgkshglJcdzbgl implements Serializable {

	/** null */
	@TableId(type=IdType.INPUT)
	private Double objectid;

	/** null */
	@TableField(value = "PRO_ID")
	private String proId;

	/** null */
	@TableField(value = "SEC_ID")
	private String secId;

	/** null */
	@TableField(value = "JC_ID")
	private String jcId;

	/** null */
	@TableField(value = "JC_TYPE")
	private String jcType;

	/** null */
	@TableField(value = "LC")
	private String lc;

	/** null */
	@TableField(value = "HH")
	private String hh;

	/** null */
	@TableField(value = "XX")
	private String xx;

	/** null */
	@TableField(value = "SX")
	private String sx;

	/** null */
	@TableField(value = "X")
	private String x;

	/** null */
	@TableField(value = "Y")
	private String y;

	/** null */
	@TableField(value = "Z")
	private String z;

	/** null */
	@TableField(value = "BZ")
	private String bz;

	/** null */
//	@TableField(value = "SHAPE")
//	private String shape;

	/** null */
	@TableField(value = "PRO_NAME")
	private String proName;

	/** null */
	@TableField(value = "SEC_NAME")
	private String secName;


	public Double getObjectid() {
		return this.objectid;
	}

	public void setObjectid(Double objectid) {
		this.objectid = objectid;
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
