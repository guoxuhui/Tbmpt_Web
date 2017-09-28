package com.crfeb.tbmpt.sgkshgl.jcdtgl.model;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotations.IdType;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;

/**
 * <p>基础底图管理 实体类</p>
 * <p>模块：施工可视化管理</p>
 * <p>日期：2017-04-12</p>
 * @version 1.0
 * @author wl_wpg
 */
@TableName("PRO_CAD_MAP")
public class SgkshglJcdtgl implements Serializable  {

	/** 图层编号 UUID */
	@TableId(type = IdType.UUID)
	private String id;
	
	/** 关联ID */
	@TableField(value = "REF_ID")
	private String refId;

	/** 关联名称 */
	@TableField(value = "REF_NAME")
	private String refName;
	
	/** 关联类型,（PM--区间平面图） 线路（DM--纵断面图） */
	@TableField(value = "MAP_TYPE")
	private String mapType;
	
	/** 关联路径 */
	@TableField(value = "MAP_PATH")
	private String mapPath;

	/** 图层编号 UUID */
	public String getId() {
		return id;
	}
	/** 图层编号 UUID */    
	public void setId(String id) {
		this.id = id;
	}
	/** 关联Id */
	public String getRefId() {
		return refId;
	}
	/** 关联Id */
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
