package com.crfeb.tbmpt.zsjk.report.model.dto;


/**
 * <p>沉降最大监测点分析 工具实类</p>
 * <p>日期：2017-03-07</p>
 * @version 1.0
 * @author wl_wpg
 */
public class ZsJkReportCjzdjcdfxDto {

	/** UUID */
    private String id;
	/** 项目ID */
	private String xmId;
	/** 区间ID */
	private String qjId;
	/** 开始时间 */
	private String kssj;
	/** 结束时间" */
	private String jssj;
	/** 环数间隔 */
	private String hsjg;
	/** 最大沉降量 */
	private String zdcjl;
	
	
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
	public String getQjId() {
		return qjId;
	}
	public void setQjId(String qjId) {
		this.qjId = qjId;
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
	public String getHsjg() {
		return hsjg;
	}
	public void setHsjg(String hsjg) {
		this.hsjg = hsjg;
	}
	public String getZdcjl() {
		return zdcjl;
	}
	public void setZdcjl(String zdcjl) {
		this.zdcjl = zdcjl;
	}
	
	
}
