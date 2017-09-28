package com.crfeb.tbmpt.sgkshgl.jcdtgl.model.dto;


/**
 * <p>基础底图管理 工具类</p>
 * <p>模块：施工可视化管理</p>
 * <p>日期：2017-04-12</p>
 * @version 1.0
 * @author wl_wpg
 */
public class SgkshglJcdtglDto {

	/** 图层编号 UUID */
	private String id;
	
	/** 关联ID */
	private String refId;

	/** 关联名称 */
	private String refName;
	
	/** 关联类型,（PM--区间平面图） 线路（DM--纵断面图） */
	private String mapType;
	
	/** 关联路径 */
	private String mapPath;
	
	/** 项目ID */
	private String proId;
	
	/** 项目名称 */
	private String proName;
	
	/** 区间ID */
	private String sectionId;
	
	/** 区间名称 */
	private String sectionName;
	
	/** 线路ID */
	private String lineId;
	
	/** 线路名称 */
	private String lineName;
	
	
	/** 项目名称 */
	public String getProName() {
		return proName;
	}
	/** 项目名称 */
	public void setProName(String proName) {
		this.proName = proName;
	}
	/** 区间名称 */
	public String getSectionName() {
		return sectionName;
	}
	/** 区间名称 */
	public void setSectionName(String sectionName) {
		this.sectionName = sectionName;
	}
	/** 线路名称 */
	public String getLineName() {
		return lineName;
	}
	/** 线路名称 */
	public void setLineName(String lineName) {
		this.lineName = lineName;
	}
	/** 项目ID */
	public String getProId() {
		return proId;
	}
	/** 项目ID */
	public void setProId(String proId) {
		this.proId = proId;
	}
	/** 区间ID */
	public String getSectionId() {
		return sectionId;
	}
	/** 区间ID */
	public void setSectionId(String sectionId) {
		this.sectionId = sectionId;
	}
	/** 线路ID */
	public String getLineId() {
		return lineId;
	}
	/** 线路ID */
	public void setLineId(String lineId) {
		this.lineId = lineId;
	}
	

	/** 图层编号 UUID */
	public String getId() {
		return id;
	}
	/** 图层编号 UUID */    
	public void setId(String id) {
		this.id = id;
	}
	/** 关联ID */
	public String getRefId() {
		return refId;
	}
	/** 关联ID */
	public void setRefId(String refId) {
		this.refId = refId;
	}
	/** 关联名称 */
	public String getRefName() {
		return refName;
	}
	/** 关联名称 */
	public void setRefName(String refName) {
		this.refName = refName;
	}
	/** 关联类型,（PM--区间平面图） 线路（DM--纵断面图） */
	public String getMapType() {
		return mapType;
	}
	/** 关联类型,（PM--区间平面图） 线路（DM--纵断面图） */
	public void setMapType(String mapType) {
		this.mapType = mapType;
	}
	/** 关联路径 */
	public String getMapPath() {
		return mapPath;
	}
	/** 关联路径 */
	public void setMapPath(String mapPath) {
		this.mapPath = mapPath;
	}
}
