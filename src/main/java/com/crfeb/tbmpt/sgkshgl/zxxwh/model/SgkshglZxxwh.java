package com.crfeb.tbmpt.sgkshgl.zxxwh.model;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotations.IdType;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;

/**
 * <p>CAD中心线维护 实体类</p>
 * <p>模块：施工可视化管理</p>
 * <p>日期：2017-04-12</p>
 * @version 1.0
 * @author wl_wpg
 */
@TableName("TBMPT_SP.ZXLINE")
public class SgkshglZxxwh implements Serializable  {

	/** objectId/IdType.INPUT */
	@TableId(type = IdType.INPUT)
	private Double objectid;
	
	/** 项目ID */
	@TableField(value = "PRO_ID")
	private String proId;
	
	/** 项目名称 */
	@TableField(value = "PRO_NAME")
	private String proName;
	
	/** 区间ID */
	@TableField(value = "SEC_ID")
	private String sectionId;
	
	/** 区间名称 */
	@TableField(value = "SEC_NAME")
	private String sectionName;
	
	/** 线路ID */
	@TableField(value = "L_ID")
	private String lineId;
	
	/** 线路名称 */
	@TableField(value = "L_NAME")
	private String lineName;
	
	/** 环号 */
	private Integer hh;
	
	
	/** 里程 */
	private Integer lc;
	
	/** x */
	private String x;
	
	/** y */
	private String y;
	
	/** z */
	private String z;
	
	/** 类型,（PM--区间平面图） 线路（DM--纵断面图） */
	private String type;

	/** objectid/IdType.INPUT */
	public Double getObjectid() {
		return objectid;
	}

	/** objectid/IdType.INPUT */
	public void setObjectid(Double objectid) {
		this.objectid = objectid;
	}

	/** 项目ID */
	public String getProId() {
		return proId;
	}

	/** 项目ID */
	public void setProId(String proId) {
		this.proId = proId;
	}

	/** 项目名称 */
	public String getProName() {
		return proName;
	}

	/** 项目名称 */
	public void setProName(String proName) {
		this.proName = proName;
	}

	/** 区间ID */
	public String getSectionId() {
		return sectionId;
	}

	/** 区间ID */
	public void setSectionId(String sectionId) {
		this.sectionId = sectionId;
	}

	/** 区间名称 */
	public String getSectionName() {
		return sectionName;
	}

	/** 区间名称 */
	public void setSectionName(String sectionName) {
		this.sectionName = sectionName;
	}

	/** 线路ID */
	public String getLineId() {
		return lineId;
	}

	/** 线路ID */
	public void setLineId(String lineId) {
		this.lineId = lineId;
	}
	
	/** 线路名称 */
	public String getLineName() {
		return lineName;
	}

	/** 线路名称 */
	public void setLineName(String lineName) {
		this.lineName = lineName;
	}

	/** 环号 */
	public Integer getHh() {
		return hh;
	}

	/** 环号 */
	public void setHh(Integer hh) {
		this.hh = hh;
	}

	/** 里程 */
	public Integer getLc() {
		return lc;
	}

	/** 里程 */
	public void setLc(Integer lc) {
		this.lc = lc;
	}

	/** x */
	public String getX() {
		return x;
	}

	/** x */
	public void setX(String x) {
		this.x = x;
	}

	/** y */
	public String getY() {
		return y;
	}

	/** y */
	public void setY(String y) {
		this.y = y;
	}

	/** z */
	public String getZ() {
		return z;
	}

	/** z */
	public void setZ(String z) {
		this.z = z;
	}

	/** 关联类型,（PM--区间平面图） 线路（DM--纵断面图） */
	public String getType() {
		return type;
	}

	/** 关联类型,（PM--区间平面图） 线路（DM--纵断面图） */
	public void setType(String type) {
		this.type = type;
	}
	
	
}
