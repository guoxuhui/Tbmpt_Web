package com.crfeb.tbmpt.zsjk.plc.model.dto;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotations.IdType;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;

/**
 *
 * 标准点位字典表
 *
 */

public class ProPlcBzdwDto implements Serializable {

	private static final long serialVersionUID = 1L;

	/** ID */
	private String id;

	/** 点位名称 */
	private String name;

	/** 点位类型 */
	private String dwlx;

	/** 所属系统 */
	private String ssxt;

	/** 盾构机类型 */
	private String dgjlx;
	
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

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDwlx() {
		return this.dwlx;
	}

	public void setDwlx(String dwlx) {
		this.dwlx = dwlx;
	}

	public String getSsxt() {
		return this.ssxt;
	}

	public void setSsxt(String ssxt) {
		this.ssxt = ssxt;
	}

	public String getDgjlx() {
		return this.dgjlx;
	}

	public void setDgjlx(String dgjlx) {
		this.dgjlx = dgjlx;
	}

	public String getMs() {
		return this.ms;
	}

	public void setMs(String ms) {
		this.ms = ms;
	}

	public String getBz() {
		return this.bz;
	}

	public void setBz(String bz) {
		this.bz = bz;
	}

}
