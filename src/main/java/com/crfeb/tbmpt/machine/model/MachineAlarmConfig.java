package com.crfeb.tbmpt.machine.model;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotations.IdType;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;


/**
 * <p>机器数据报警类别配置 实体类</p>
 * <p>模块：机器参数管理模块</p>
 * <p>日期：2017-06-06</p>
 * @version 1.0
 * @author wl_wpg
 */
@TableName("MACHINE_ALARM_CONFIG")
public class MachineAlarmConfig implements Serializable {

	@TableField(exist = false)
	private static final long serialVersionUID = 1L;

	/** 报警分类ID */
	@TableId(type = IdType.UUID)
	private String id;

	/** 报警分类名称 */
	private String name;

	/** 所属电器系统 */
	private String electricalsystem;

	/** 报警条件 */
	private String alarmcondition;

	/** 报警周期 */
	private String alarmcycle;

	/** 上次报警时间 */
	private String lastalarmtime;

	/** 下次报警时间 */
	private String nextalarmtime;

	/** 是否开启 */
	private String isopen;

	/** 备注 */
	private String remark;


	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getElectricalsystem() {
		return this.electricalsystem;
	}

	public void setElectricalsystem(String electricalsystem) {
		this.electricalsystem = electricalsystem;
	}

	public String getAlarmcondition() {
		return this.alarmcondition;
	}

	public void setAlarmcondition(String alarmcondition) {
		this.alarmcondition = alarmcondition;
	}

	public String getAlarmcycle() {
		return this.alarmcycle;
	}

	public void setAlarmcycle(String alarmcycle) {
		this.alarmcycle = alarmcycle;
	}

	public String getLastalarmtime() {
		return this.lastalarmtime;
	}

	public void setLastalarmtime(String lastalarmtime) {
		this.lastalarmtime = lastalarmtime;
	}

	public String getNextalarmtime() {
		return this.nextalarmtime;
	}

	public void setNextalarmtime(String nextalarmtime) {
		this.nextalarmtime = nextalarmtime;
	}

	public String getIsopen() {
		return this.isopen;
	}

	public void setIsopen(String isopen) {
		this.isopen = isopen;
	}

	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

}
