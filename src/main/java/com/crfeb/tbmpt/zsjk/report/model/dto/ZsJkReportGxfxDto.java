package com.crfeb.tbmpt.zsjk.report.model.dto;

import java.io.Serializable;

public class ZsJkReportGxfxDto implements Serializable{
	
	
	private String id;
	
	/**
	 * 掘进状态
	 */
	
	private String jjzt;
	
	/**
	 * 开始时间
	 */
	
	private String kssj;
	
	/**
	 * 结束时间
	 */
	
	private String jssj;
	
	/**
	 * 掘进环号
	 */
	
	private String jjhh;
	
	/**
	 * 项目ID
	 * @return
	 */
	
	private String proId;
	
	/**
	 * 线路ID
	 */
	
	private String lineId;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getJjzt() {
		return jjzt;
	}

	public void setJjzt(String jjzt) {
		this.jjzt = jjzt;
	}

	public String getKssj() {
		return kssj;
	}

	public void setKssj(String kssj) {
		this.kssj = kssj;
	}

	public String getJssj() {
		return jssj;
	}

	public void setJssj(String jssj) {
		this.jssj = jssj;
	}

	

	public String getJjhh() {
		return jjhh;
	}

	public void setJjhh(String jjhh) {
		this.jjhh = jjhh;
	}

	public String getProId() {
		return proId;
	}

	public void setProId(String proId) {
		this.proId = proId;
	}

	public String getLineId() {
		return lineId;
	}

	public void setLineId(String lineId) {
		this.lineId = lineId;
	}

	
	
	

}
