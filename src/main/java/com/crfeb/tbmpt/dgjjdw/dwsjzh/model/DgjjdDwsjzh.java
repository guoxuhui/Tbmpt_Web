package com.crfeb.tbmpt.dgjjdw.dwsjzh.model;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotations.IdType;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;

/**
 *
 * 盾构机点位与标准关联表
 *
 */
@TableName("DWDATAZHH")
public class DgjjdDwsjzh implements Serializable {


	@TableField(exist = false)
	private static final long serialVersionUID = 1L;

	/** 主键 */
	@TableId(type = IdType.UUID)
	private String id;

	/** 标准点位名称（实际盾构机采集配置的各点位名称） */
	@TableField(value = "DWNAME")
	private String dwname;

	/** 跨度开始 */
	@TableField(value = "KD_START")
	private Float kdStart;

	/** 跨度结束 */
	@TableField(value = "KD_END")
	private Float kdEnd;

	/** 数据值开始 */
	@TableField(value = "DATA_START")
	private Float dataStart;

	/** 数据值结束 */
	@TableField(value = "DATA_END")
	private Float dataEnd;

	/** 单位 */
	@TableField(value = "DW")
	private String dw;

	/** 正负数 */
	@TableField(value = "DATA_ZF")
	private int dataZf;

	/** 计算方式 */
	@TableField(value = "CAL_TYPE")
	private String calType;

	/** 盾构机名称 */
	@TableField(value = "DGJNAME")
	private String dgjname;

	/** 备注 */
	@TableField(value = "REMARKS")
	private String remarks;


	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getDwname() {
		return this.dwname;
	}

	public void setDwname(String dwname) {
		this.dwname = dwname;
	}

	public Float getKdStart() {
		return this.kdStart;
	}

	public void setKdStart(Float kdStart) {
		this.kdStart = kdStart;
	}

	public Float getKdEnd() {
		return this.kdEnd;
	}

	public void setKdEnd(Float kdEnd) {
		this.kdEnd = kdEnd;
	}

	public Float getDataStart() {
		return this.dataStart;
	}

	public void setDataStart(Float dataStart) {
		this.dataStart = dataStart;
	}

	public Float getDataEnd() {
		return this.dataEnd;
	}

	public void setDataEnd(Float dataEnd) {
		this.dataEnd = dataEnd;
	}

	public String getDw() {
		return this.dw;
	}

	public void setDw(String dw) {
		this.dw = dw;
	}

	public int getDataZf() {
		return this.dataZf;
	}

	public void setDataZf(int dataZf) {
		this.dataZf = dataZf;
	}

	public String getCalType() {
		return this.calType;
	}

	public void setCalType(String calType) {
		this.calType = calType;
	}

	public String getDgjname() {
		return this.dgjname;
	}

	public void setDgjname(String dgjname) {
		this.dgjname = dgjname;
	}

	public String getRemarks() {
		return this.remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	 

}
