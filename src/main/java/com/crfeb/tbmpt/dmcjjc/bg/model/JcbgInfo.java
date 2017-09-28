package com.crfeb.tbmpt.dmcjjc.bg.model;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;

@TableName("dmcj_jcbg_info")
public class JcbgInfo implements Serializable{
	@TableField(exist = false)
	private static final long serialVersionUID = 1L;

	/** 主键id */
	@TableId(value = "id")
	private String id;

	/** 工程编号 */
	@TableField(value = "gcbh")
	private String gcbh;

	/** 本次检测时间 */
	@TableField(value = "jctime")
	private String jcTime;
	
	/** 天气 */
	@TableField(value = "tianqi")
	private String tianqi;

	/** 确认状态 */
	@TableField(value = "ifqr")
	private String ifqr;
	
	/** 是否上报 */
	@TableField(value = "ifsb")
	private String ifsb;
	
	/** 备注 */
	@TableField(value = "remarks")
	private String remarks;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getGcbh() {
		return gcbh;
	}

	public void setGcbh(String gcbh) {
		this.gcbh = gcbh;
	}

	public String getJcTime() {
		return jcTime;
	}

	public void setJcTime(String jcTime) {
		this.jcTime = jcTime;
	}

	public String getTianqi() {
		return tianqi;
	}

	public void setTianqi(String tianqi) {
		this.tianqi = tianqi;
	}

	public String getIfqr() {
		return ifqr;
	}

	public void setIfqr(String ifqr) {
		this.ifqr = ifqr;
	}

	public String getIfsb() {
		return ifsb;
	}

	public void setIfsb(String ifsb) {
		this.ifsb = ifsb;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
}
