package com.crfeb.tbmpt.zsjk.report.model;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotations.IdType;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;

@TableName("zsjk_report_dgjjcsfx")
public class ZsJkReportDgjjcsfx implements Serializable{
	
	@TableId(type=IdType.UUID)
	private String id;
	
	/**
	 * 项目ID
	 */
	@TableField(value="xmId")
	private String xmId;
	
	/**
	 * 盾构机ID
	 */
	@TableField(value="dgjId")
	private String dgjId;
	
	/**
	 * 环号
	 */
	@TableField(value="hh")
	private String hh;
	
	/**
	 * 掘进时间
	 */
	@TableField(value="jjsj")
	private String jjsj;
	
	
	/**
	 * 类型名称
	 */
	@TableField(value="type_name")
	private String typeName;
	
	/**
	 * 类型ID
	 */
	@TableField(value="TYPE_ID")
	private String typeId;
	
	/**
	 * 数值
	 * @return
	 */
	@TableField(value="TYPE_NUMBER")
	private String typeNumber;

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

	
	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
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
	
	

}
