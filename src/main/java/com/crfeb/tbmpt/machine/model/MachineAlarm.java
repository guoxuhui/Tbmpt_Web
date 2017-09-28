package com.crfeb.tbmpt.machine.model;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotations.IdType;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;

/**
 *
 * 机器参数管理模块-机器报警历史记录表
 *
 */
@TableName("MACHINE_ALARM")
public class MachineAlarm implements Serializable {

	@TableField(exist = false)
	private static final long serialVersionUID = 1L;

	/** 报警ID */
	@TableId(type = IdType.UUID)
	private String id;

	/** 报警标题 */
	private String alarmname;

	/** 报警内容 */
	private String alarmcontent;

	/** 报警分类 */
	private String alarmtype;

	/** 报警时间 */
	private String alarmtime;

	/** 所属电器系统 */
	private String electricalsystem;

	/** 当前机器参数JSON */
	private String param;

	/** 盾构机ID */
	private String tbmid;

	/** 盾构机名称 */
	private String tbmname;

	/** 项目ID */
	private String proid;

	/** 项目名称 */
	private String proname;

	/** 区间ID */
	private String sectionid;

	/** 区间名称 */
	private String sectionname;

	/** 线路ID */
	private String lineid;

	/** 线路名称 */
	private String linename;

	/** 备注 */
	private String remark;


	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getAlarmname() {
		return this.alarmname;
	}

	public void setAlarmname(String alarmname) {
		this.alarmname = alarmname;
	}

	public String getAlarmcontent() {
		return this.alarmcontent;
	}

	public void setAlarmcontent(String alarmcontent) {
		this.alarmcontent = alarmcontent;
	}

	public String getAlarmtype() {
		return this.alarmtype;
	}

	public void setAlarmtype(String alarmtype) {
		this.alarmtype = alarmtype;
	}

	public String getAlarmtime() {
		return this.alarmtime;
	}

	public void setAlarmtime(String alarmtime) {
		this.alarmtime = alarmtime;
	}

	public String getElectricalsystem() {
		return this.electricalsystem;
	}

	public void setElectricalsystem(String electricalsystem) {
		this.electricalsystem = electricalsystem;
	}

	public String getParam() {
		return this.param;
	}

	public void setParam(String param) {
		this.param = param;
	}

	public String getTbmid() {
		return this.tbmid;
	}

	public void setTbmid(String tbmid) {
		this.tbmid = tbmid;
	}

	public String getTbmname() {
		return this.tbmname;
	}

	public void setTbmname(String tbmname) {
		this.tbmname = tbmname;
	}

	public String getProid() {
		return this.proid;
	}

	public void setProid(String proid) {
		this.proid = proid;
	}

	public String getProname() {
		return this.proname;
	}

	public void setProname(String proname) {
		this.proname = proname;
	}

	public String getSectionid() {
		return this.sectionid;
	}

	public void setSectionid(String sectionid) {
		this.sectionid = sectionid;
	}

	public String getSectionname() {
		return this.sectionname;
	}

	public void setSectionname(String sectionname) {
		this.sectionname = sectionname;
	}

	public String getLineid() {
		return this.lineid;
	}

	public void setLineid(String lineid) {
		this.lineid = lineid;
	}

	public String getLinename() {
		return this.linename;
	}

	public void setLinename(String linename) {
		this.linename = linename;
	}

	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

}
