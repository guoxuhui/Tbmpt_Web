package com.crfeb.tbmpt.tbmmg.model;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotations.IdType;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;

/**
 *
 * 盾构状态信息表
 *
 */
@TableName("PRO_TBM_DGSTATE")
public class ProTbmDgstateInfo implements Serializable {

	@TableField(exist = false)
	private static final long serialVersionUID = 1L;
	
	@TableId(type = IdType.UUID)
	private String id;

	/** 盾构区间以及状态 **/
	@TableField(value = "TBMZT")
	private String tbmzt;
	
	/** 设备管理号码 **/
	@TableField(value = "SBGLH")
	private String sbglh;
	
	/** 所在位置，项目部或者维修中心等 **/
	@TableField(value = "SZWZ")
	private String szwz;
	
	/** 盾构机名称及型号 **/
	@TableField(value = "DGJ")
	private String dgj;
	
	/** 本月停机总天数 **/
	@TableField(value = "Y_STOP_DAYS")
	private String yStopDays;
	
	/** 本月故障停机天数  **/
	@TableField(value = "Y_GZSTOP_DAYS")
	private String yGzstopDays;
	
	/** 负责人及电话 **/
	@TableField(value = "MAN_TEL")
	private String manTel;
	
	/** 备注 **/
	@TableField(value = "REMARKS")
	private String remarks;
	
	/** 坐标 **/
	@TableField(value = "ZB")
	private String zb;
	
	/** 月份 **/
	@TableField(value = "RIQI")
	private String riqi;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTbmzt() {
		return tbmzt;
	}

	public void setTbmzt(String tbmzt) {
		this.tbmzt = tbmzt;
	}

	public String getSbglh() {
		return sbglh;
	}

	public void setSbglh(String sbglh) {
		this.sbglh = sbglh;
	}

	public String getSzwz() {
		return szwz;
	}

	public void setSzwz(String szwz) {
		this.szwz = szwz;
	}

	public String getDgj() {
		return dgj;
	}

	public void setDgj(String dgj) {
		this.dgj = dgj;
	}

	public String getyStopDays() {
		return yStopDays;
	}

	public void setyStopDays(String yStopDays) {
		this.yStopDays = yStopDays;
	}

	public String getyGzstopDays() {
		return yGzstopDays;
	}

	public void setyGzstopDays(String yGzstopDays) {
		this.yGzstopDays = yGzstopDays;
	}

	public String getManTel() {
		return manTel;
	}

	public void setManTel(String manTel) {
		this.manTel = manTel;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public String getZb() {
		return zb;
	}

	public void setZb(String zb) {
		this.zb = zb;
	}

	public String getRiqi() {
		return riqi;
	}

	public void setRiqi(String riqi) {
		this.riqi = riqi;
	}

}