package com.crfeb.tbmpt.zsjk.report.model.dto;


/**
 * <p>掘进姿态:偏差曲线数据  工具类</p>
 * <p>日期：2017-03-08</p>
 * @version 1.0
 * @author wl_wpg
 */
public class ZsJkReportPcqxsjDto {

	/** ID */
    private String id;
	
	/** 项目ID */
	private String xmId;
	
	/** 线路ID */
	private String xlId;
	
	/** 盾构机ID */
	private String dgjId;
	
	/** 开始环数 */
	private String kshs;
	
	/** 结束环数 */
	private String jshs;
	
	

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

	public String getDgjId() {
		return dgjId;
	}

	public void setDgjId(String dgjId) {
		this.dgjId = dgjId;
	}

	public String getKshs() {
		return kshs;
	}

	public void setKshs(String kshs) {
		this.kshs = kshs;
	}

	public String getJshs() {
		return jshs;
	}

	public void setJshs(String jshs) {
		this.jshs = jshs;
	}

	
	
	
}
