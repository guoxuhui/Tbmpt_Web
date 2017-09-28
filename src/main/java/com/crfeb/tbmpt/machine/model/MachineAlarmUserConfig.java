package com.crfeb.tbmpt.machine.model;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotations.IdType;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;

/**
 *
 * 机器参数管理模块-报警退推送用户配置
 *
 */
@TableName("MACHINE_ALARM_USER_CONFIG")
public class MachineAlarmUserConfig implements Serializable {

	@TableField(exist = false)
	private static final long serialVersionUID = 1L;

	/** 关联ID */
	@TableId(type = IdType.UUID)
	private String id;

	/** 报警分类ID */
	private String alarmtypeid;

	/** 用户ID */
	private String userid;

	/** 用户名称 */
	private String username;

	/** 员工ID */
	private String emlpoyeeid;

	/** 员工名称 */
	private String employeename;

	/** 单位ID */
	private String orgzid;

	/** 所属单位 */
	private String orgzname;

	/** 备注 */
	private String remark;


	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getAlarmtypeid() {
		return this.alarmtypeid;
	}

	public void setAlarmtypeid(String alarmtypeid) {
		this.alarmtypeid = alarmtypeid;
	}

	public String getUserid() {
		return this.userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmlpoyeeid() {
		return this.emlpoyeeid;
	}

	public void setEmlpoyeeid(String emlpoyeeid) {
		this.emlpoyeeid = emlpoyeeid;
	}

	public String getEmployeename() {
		return this.employeename;
	}

	public void setEmployeename(String employeename) {
		this.employeename = employeename;
	}

	public String getOrgzid() {
		return this.orgzid;
	}

	public void setOrgzid(String orgzid) {
		this.orgzid = orgzid;
	}

	public String getOrgzname() {
		return this.orgzname;
	}

	public void setOrgzname(String orgzname) {
		this.orgzname = orgzname;
	}

	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

}
