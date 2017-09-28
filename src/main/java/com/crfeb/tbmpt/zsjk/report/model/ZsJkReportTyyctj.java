package com.crfeb.tbmpt.zsjk.report.model;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotations.IdType;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;

@TableName("zsjk_report_tyyctj")
public class ZsJkReportTyyctj implements Serializable{
	
	@TableId(type=IdType.UUID)
	private String id;
	
	/**
	 * 项目ID
	 */
	@TableField(value="xmId")
	private String xmId;
	
	/**
	 * 线路ID
	 */
	@TableField(value="xlId")
	private String xlId;
	
	/**
	 * 线路名称
	 */
	@TableField(value="xlMc")
	private String xlMc;
	
	/**
	 * 土压力异常环数
	 */
	@TableField(value="tylychs")
	private String tylychs;
	
	/**
	 * 掘进环数
	 */
	@TableField(value="jjhs")
	private String jjhs;
	
	/**
	 * 日期
	 */
	@TableField(value="rq")
	private String rq;

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

	public String getXlId() {
		return xlId;
	}

	public void setXlId(String xlId) {
		this.xlId = xlId;
	}

	

	public String getTylychs() {
		return tylychs;
	}

	public void setTylychs(String tylychs) {
		this.tylychs = tylychs;
	}

	public String getJjhs() {
		return jjhs;
	}

	public void setJjhs(String jjhs) {
		this.jjhs = jjhs;
	}

	public String getRq() {
		return rq;
	}

	public void setRq(String rq) {
		this.rq = rq;
	}

	public String getXlMc() {
		return xlMc;
	}

	public void setXlMc(String xlMc) {
		this.xlMc = xlMc;
	}
	
}
