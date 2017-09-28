package com.crfeb.tbmpt.zsjk.report.model.dto;


/**
 * <p>施工进度分析  工具实类</p>
 * <p>日期：2017-03-08</p>
 * @version 1.0
 * @author wl_wpg
 */
public class ZsJkReportSgjdfxDto {

	/** Id */
    private String id;
	
	/** 项目ID */
	private String xmId;
	
	/** 线路ID */
	private String xlId;
	
	/** 盾构机ID */
	private String dgjId;
	
	/** 开始日期*/
	private String ksrq;
	
	/** 结束日期 */
	private String jsrq;
	
	

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

	public String getKsrq() {
		return ksrq;
	}

	public void setKsrq(String ksrq) {
		this.ksrq = ksrq;
	}

	public String getJsrq() {
		return jsrq;
	}

	public void setJsrq(String jsrq) {
		this.jsrq = jsrq;
	}

	
	
}
