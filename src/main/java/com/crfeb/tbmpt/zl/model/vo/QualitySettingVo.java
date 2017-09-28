package com.crfeb.tbmpt.zl.model.vo;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotations.IdType;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;

/**
 *
 * 管片质量配置表
 *
 */
public class QualitySettingVo implements Serializable {

	@TableField(exist = false)
	private static final long serialVersionUID = 1L;

	/** UUID */
	@TableId(type = IdType.UUID)
	private String id;

	/** 项目部名称 */
	@TableField(value = "PRO_NAME")
	private String proName;
	private String proName1;
	private String lineName;
	public String getLineName() {
		return lineName;
	}

	public void setLineName(String lineName) {
		this.lineName = lineName;
	}

	
	public String getProName1() {
		return proName1;
	}

	public void setProName1(String proName1) {
		this.proName1 = proName1;
	}

	/** 区间 */
	private String section;
	

	/** 线路 */
	private String line;

	/** 当前进度 */
	@TableField(value = "CUR_JD")
	private String curJd;

	/** 当前上报环数 */
	@TableField(value = "CUR_UP_CYCLE_NO")
	private String curUpCycleNo;

	public String getProId() {
		return proId;
	}

	public void setProId(String proId) {
		this.proId = proId;
	}

	/** 上报人 */
	@TableField(value = "UP_USER")
	private String upUser;

	/** 联系方式 */
	@TableField(value = "LINK_WAY")
	private String linkWay;

	/** 设置上报时间(天) */
	@TableField(value = "SET_UP_DAY")
	private String setUpDay;

    private String proId;   
    private String sectionName;
    
	public String getSectionName() {
		return sectionName;
	}

	public void setSectionName(String sectionName) {
		this.sectionName = sectionName;
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getProName() {
		return this.proName;
	}

	public void setProName(String proName) {
		this.proName = proName;
	}

	public String getSection() {
		return this.section;
	}

	public void setSection(String section) {
		this.section = section;
	}

	public String getLine() {
		return this.line;
	}

	public void setLine(String line) {
		this.line = line;
	}

	public String getCurJd() {
		return this.curJd;
	}

	public void setCurJd(String curJd) {
		this.curJd = curJd;
	}

	public String getCurUpCycleNo() {
		return this.curUpCycleNo;
	}

	public void setCurUpCycleNo(String curUpCycleNo) {
		this.curUpCycleNo = curUpCycleNo;
	}

	public String getUpUser() {
		return this.upUser;
	}

	public void setUpUser(String upUser) {
		this.upUser = upUser;
	}

	public String getLinkWay() {
		return this.linkWay;
	}

	public void setLinkWay(String linkWay) {
		this.linkWay = linkWay;
	}

	public String getSetUpDay() {
		return this.setUpDay;
	}

	public void setSetUpDay(String setUpDay) {
		this.setUpDay = setUpDay;
	}

}
