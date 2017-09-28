package com.crfeb.tbmpt.zsjk.report.model;

import com.baomidou.mybatisplus.annotations.IdType;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;

/**
 * <p>掘进姿态:偏差曲线数据  实体信息类</p>
 * <p>描述：实体描述信息.</p>
 * <p>系统：展示界面接口</p>
 * <p>日期：2017-03-08</p>
 * @version 1.0
 * @author wl_wpg
 */
@TableName("ZSJK_REPORT_PCQXSJ")
public class ZsJkReportPcqxsj {

	/** UUID */
	@TableId(type = IdType.UUID)
    private String id;
	
	/** 项目ID */
	private String xmId;
	
	/** 线路ID */
	private String xlId;
	
	/** 盾构机ID */
	private String dgjId;
	
	/** 环号 */
	private String hh;
	
	/** 切口水平偏差 */
	private String qksppc;
	
	/** 铰接水平偏差 */
	private String jjsppc;
	
	/** 盾尾水平偏差 */
	private String dwsppc;
	
	/** 切口垂直偏差 */
	private String qkczpc;
	
	/** 铰接垂直偏差 */
	private String jjczpc;
	
	/** 盾尾垂直偏差 */
	private String dwczpc;

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

	public String getHh() {
		return hh;
	}

	public void setHh(String hh) {
		this.hh = hh;
	}

	public String getQksppc() {
		return qksppc;
	}

	public void setQksppc(String qksppc) {
		this.qksppc = qksppc;
	}

	public String getJjsppc() {
		return jjsppc;
	}

	public void setJjsppc(String jjsppc) {
		this.jjsppc = jjsppc;
	}

	public String getDwsppc() {
		return dwsppc;
	}

	public void setDwsppc(String dwsppc) {
		this.dwsppc = dwsppc;
	}

	public String getQkczpc() {
		return qkczpc;
	}

	public void setQkczpc(String qkczpc) {
		this.qkczpc = qkczpc;
	}

	public String getJjczpc() {
		return jjczpc;
	}

	public void setJjczpc(String jjczpc) {
		this.jjczpc = jjczpc;
	}

	public String getDwczpc() {
		return dwczpc;
	}

	public void setDwczpc(String dwczpc) {
		this.dwczpc = dwczpc;
	}


	
	
}
