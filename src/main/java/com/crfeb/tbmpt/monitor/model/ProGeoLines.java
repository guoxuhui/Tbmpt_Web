package com.crfeb.tbmpt.monitor.model;

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
public class ProGeoLines implements Serializable {

	@TableField(exist = false)
	private static final long serialVersionUID = 1L;

	/** null */
	@TableId
	private String id;

	/** null */
	@TableField(value = "L_ID")
	private String lId;

	/** null */
	private String ring;

	/** null */
	private String lng;

	/** null */
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

	public String getRing() {
		return this.ring;
	}

	public void setRing(String ring) {
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
