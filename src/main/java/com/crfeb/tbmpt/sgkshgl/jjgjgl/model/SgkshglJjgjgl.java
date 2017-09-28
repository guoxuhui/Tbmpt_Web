package com.crfeb.tbmpt.sgkshgl.jjgjgl.model;

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
@TableName("PRO_GEO_LINES")
public class SgkshglJjgjgl implements Serializable {

	@TableId(type = IdType.UUID)
	private String id;

	/** null */
	@TableField(value = "L_ID")
	private String lId;

	/** 环号 */
	@TableField(value = "RING")
	private Double ring;

	/** 经纬度 */
	@TableField(value = "LNG")
	private String lng;

	/** 经纬度 */
	@TableField(value = "LAT")
	private String lat;


	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getLId() {
		return this.lId;
	}

	public void setLId(String lId) {
		this.lId = lId;
	}

	public Double getRing() {
		return this.ring;
	}

	public void setRing(Double ring) {
		this.ring = ring;
	}

	public String getLng() {
		return this.lng;
	}

	public void setLng(String lng) {
		this.lng = lng;
	}

	public String getLat() {
		return this.lat;
	}

	public void setLat(String lat) {
		this.lat = lat;
	}

}
