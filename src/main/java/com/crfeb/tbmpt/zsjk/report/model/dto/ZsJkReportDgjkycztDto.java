package com.crfeb.tbmpt.zsjk.report.model.dto;


/**
 * <p>盾构监控异常状态  工具实类</p>
 * <p>日期：2017-03-07</p>
 * @version 1.0
 * @author wl_wpg
 */
public class ZsJkReportDgjkycztDto {

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
