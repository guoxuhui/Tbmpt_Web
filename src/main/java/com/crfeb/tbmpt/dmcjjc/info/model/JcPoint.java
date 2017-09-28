package com.crfeb.tbmpt.dmcjjc.info.model;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;

/**
 *
 * 监测点实体类
 *
 */
@TableName("dmcj_jcpoint")
public class JcPoint implements Serializable {

	@TableField(exist = false)
	private static final long serialVersionUID = 1L;

	/** 主键id */
	@TableId(value = "id")
	private String id;

	/** 工程编号 */
	@TableField(value = "gcbh")
	private String gcbh;

	/** 点位编号 */
	@TableField(value = "jcdbh")
	private String jcdbh;
	
	/** 区间编号 */
	@TableField(value = "qjbh")
	private String qjbh;

	/** 线路编号 */
	@TableField(value = "xlbh")
	private String xlbh;
	
	/** 点位设计类型 */
	@TableField(value = "sjtype")
	private String sjType;
	
	/** 环号/位置 */
	@TableField(value = "weizhi")
	private String weizhi;
	
	/** 里程 */
	@TableField(value = "licheng")
	private String licheng;
	
	/** 初始高程 */
	@TableField(value = "csgc")
	private Float csgc;
	
	/** 点位设计时间 */
	@TableField(value = "sjtimetype")
	private String sjTimeType;
	
	/** 备注 */
	@TableField(value = "remarks")
	private String remarks;
	
	/** 使用状态 */
	@TableField(value = "ifqy")
	private String ifqy;
	
	/** 控制值上限 */
	@TableField(value = "maxControl")
	private String maxControl;
	
	/** 控制值下限 */
	@TableField(value = "minControl")
	private String minControl;
	
	/** 报警值上限 */
	@TableField(value = "maxAlarm")
	private String maxAlarm;
	
	/** 报警值下限 */
	@TableField(value = "minAlarm")
	private String minAlarm;

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

	public String getJcdbh() {
		return jcdbh;
	}

	public void setJcdbh(String jcdbh) {
		this.jcdbh = jcdbh;
	}

	public String getQjbh() {
		return qjbh;
	}

	public void setQjbh(String qjbh) {
		this.qjbh = qjbh;
	}

	public String getXlbh() {
		return xlbh;
	}

	public void setXlbh(String xlbh) {
		this.xlbh = xlbh;
	}

	public String getSjType() {
		return sjType;
	}

	public void setSjType(String sjType) {
		this.sjType = sjType;
	}

	public String getWeizhi() {
		return weizhi;
	}

	public void setWeizhi(String weizhi) {
		this.weizhi = weizhi;
	}

	public String getLicheng() {
		return licheng;
	}

	public void setLicheng(String licheng) {
		this.licheng = licheng;
	}

	public Float getCsgc() {
		return csgc;
	}

	public void setCsgc(Float csgc) {
		this.csgc = csgc;
	}

	public String getSjTimeType() {
		return sjTimeType;
	}

	public void setSjTimeType(String sjTimeType) {
		this.sjTimeType = sjTimeType;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public String getIfqy() {
		return ifqy;
	}

	public void setIfqy(String ifqy) {
		this.ifqy = ifqy;
	}

	public String getMaxControl() {
		return maxControl;
	}

	public void setMaxControl(String maxControl) {
		this.maxControl = maxControl;
	}

	public String getMinControl() {
		return minControl;
	}

	public void setMinControl(String minControl) {
		this.minControl = minControl;
	}

	public String getMaxAlarm() {
		return maxAlarm;
	}

	public void setMaxAlarm(String maxAlarm) {
		this.maxAlarm = maxAlarm;
	}

	public String getMinAlarm() {
		return minAlarm;
	}

	public void setMinAlarm(String minAlarm) {
		this.minAlarm = minAlarm;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
}
