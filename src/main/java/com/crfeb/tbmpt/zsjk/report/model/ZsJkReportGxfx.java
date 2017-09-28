package com.crfeb.tbmpt.zsjk.report.model;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotations.IdType;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;

@TableName("zsjk_report_gxfx")
public class ZsJkReportGxfx implements Serializable{
	
	@TableId(type=IdType.UUID)
	private String id;
	
	/**
	 * 掘进状态
	 */
	@TableField(value="jjzt")
	private String jjzt;
	
	/**
	 * 开始时间
	 */
	@TableField(value="kssj")
	private String kssj;
	
	/**
	 * 结束时间
	 */
	@TableField(value="jssj")
	private String jssj;
	
	/**
	 * 掘进环号
	 */
	@TableField(value="jjhh")
	private String jjhh;
	
	/**
	 * 项目ID
	 * @return
	 */
	@TableField(value="PRO_ID")
	private String proId;
	
	/**
	 * 线路ID
	 */
	@TableField(value="LINE_ID")
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
