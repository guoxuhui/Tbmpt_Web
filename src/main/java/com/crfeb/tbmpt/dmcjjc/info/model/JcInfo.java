package com.crfeb.tbmpt.dmcjjc.info.model;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;

@TableName("dmcj_jc_info")
public class JcInfo implements Serializable{
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
	
	/** 导入文件路径 */
	@TableField(value = "impfilepath")
	private String impFilePath;
	
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

	public String getImpFilePath() {
		return impFilePath;
	}

	public void setImpFilePath(String impFilePath) {
		this.impFilePath = impFilePath;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
}
