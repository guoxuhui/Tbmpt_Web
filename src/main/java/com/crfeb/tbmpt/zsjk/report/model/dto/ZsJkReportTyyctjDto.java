package com.crfeb.tbmpt.zsjk.report.model.dto;

import java.io.Serializable;

public class ZsJkReportTyyctjDto implements Serializable{
	
	private String id;
	
	/**
	 * 项目ID
	 */
	private String xmId;
	
	/**
	 * 线路ID
	 */
	private String xlId;
	
	/**
	 * 线路名称
	 */
	private String xlMc;
	
	/**
	 * 土压力异常总环数
	 */
	private String tylyczhs;
	
	/**
	 * 掘进总环数
	 */
	private String jjzhs;
	
	/**
	 * 土压力异常环数
	 */
	private String tylychs;
	
	/**
	 * 掘进环数
	 */
	private String jjhs;
	
	/**
	 * 日期
	 */
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

	public String getTylyczhs() {
		return tylyczhs;
	}

	public void setTylyczhs(String tylyczhs) {
		this.tylyczhs = tylyczhs;
	}

	public String getJjzhs() {
		return jjzhs;
	}

	public void setJjzhs(String jjzhs) {
		this.jjzhs = jjzhs;
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
	
	

}
