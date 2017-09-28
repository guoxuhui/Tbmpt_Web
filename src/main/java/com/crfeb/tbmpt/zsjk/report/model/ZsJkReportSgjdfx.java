package com.crfeb.tbmpt.zsjk.report.model;

import com.baomidou.mybatisplus.annotations.IdType;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;

/**
 * <p>施工进度分析  实体信息类</p>
 * <p>描述：实体描述信息.</p>
 * <p>系统：展示界面接口</p>
 * <p>日期：2017-03-08</p>
 * @version 1.0
 * @author wl_wpg
 */
@TableName("ZSJK_REPORT_SGJDFX")
public class ZsJkReportSgjdfx {

	/** UUID */
	@TableId(type = IdType.UUID)
    private String id;
	
	/** 项目ID */
	private String xmId;
	
	/** 线路ID */
	private String xlId;
	
	/** 盾构机ID */
	private String dgjId;
	
	/** 日期 */
	private String rq;
	
	/** 完成环 */
	private String wch;
	
	/** 目标环 */
	private String mbh;

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

	public String getRq() {
		return rq;
	}

	public void setRq(String rq) {
		this.rq = rq;
	}

	public String getWch() {
		return wch;
	}

	public void setWch(String wch) {
		this.wch = wch;
	}

	public String getMbh() {
		return mbh;
	}

	public void setMbh(String mbh) {
		this.mbh = mbh;
	}
	
	
}
