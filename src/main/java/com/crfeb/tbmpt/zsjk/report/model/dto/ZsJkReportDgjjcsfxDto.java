package com.crfeb.tbmpt.zsjk.report.model.dto;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotations.IdType;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;

public class ZsJkReportDgjjcsfxDto implements Serializable{
	
	@TableId(type=IdType.UUID)
	private String id;
	
	/**
	 * 项目ID
	 */
	private String xmId;
	
	/**
	 * 盾构机ID
	 */
	private String dgjId;
	
	/**
	 * 环号
	 */
	private String hh;
	
	
	/**
	 * 掘进时间
	 */
	private String jjsj;
	
	/**
	 * 类型ID
	 */
	private String typeId;
	
	/**
	 * 数值
	 * @return
	 */
	private String typeNumber;
	
	/**
	 * 类型名称
	 * @return
	 */
	private String typeName;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getXmId() {
		return xmId;
	}

	public void setXmId(String xmId) {
		this.xmId = xmId;
	}

	public String getDgjId() {
		return dgjId;
	}

	public void setDgjId(String dgjId) {
		this.dgjId = dgjId;
	}

	public String getHh() {
		return hh;
	}

	public void setHh(String hh) {
		this.hh = hh;
	}

	public String getJjsj() {
		return jjsj;
	}

	public void setJjsj(String jjsj) {
		this.jjsj = jjsj;
	}


	public String getTypeId() {
		return typeId;
	}

	public void setTypeId(String typeId) {
		this.typeId = typeId;
	}

	public String getTypeNumber() {
		return typeNumber;
	}

	public void setTypeNumber(String typeNumber) {
		this.typeNumber = typeNumber;
	}

	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

}
