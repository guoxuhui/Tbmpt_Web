package com.crfeb.tbmpt.project.model;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotations.IdType;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;

/**
 *
 * 项目区间信息表
 *
 */
@TableName("PRO_R_PROJECT_SECTION")
public class ProRProjectSection implements Serializable {

	@TableField(exist = false)
	private static final long serialVersionUID = 1L;

	/** UUID */
	@TableId(type = IdType.UUID)
	private String id;
	
	
	/** 区间名称 */
	@TableField(value = "SECTION_NAME")
	private String sectionName;

	/** 区间简称 */
	private String abbreviation;
	
	/** 地层情况 */
	private String dcqk;
	

	/** 备注 */
	private String remark;

	/** 项目信息ID */
	@TableField(value = "PRO_ID")
	private String proId;

	/** 录入时间 */
	private String entertime;

	/** 录入人 */
	private String enterperson;

	/** 删除标识 */
	private int deleteflag;


	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getSectionName() {
		return this.sectionName;
	}

	public void setSectionName(String sectionName) {
		this.sectionName = sectionName;
	}

	
	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getProId() {
		return this.proId;
	}

	public void setProId(String proId) {
		this.proId = proId;
	}

	public String getEntertime() {
		return this.entertime;
	}

	public void setEntertime(String entertime) {
		this.entertime = entertime;
	}

	public String getEnterperson() {
		return this.enterperson;
	}

	public void setEnterperson(String enterperson) {
		this.enterperson = enterperson;
	}

	public int getDeleteflag() {
		return this.deleteflag;
	}

	public void setDeleteflag(int deleteflag) {
		this.deleteflag = deleteflag;
	}


	public String getAbbreviation() {
		return abbreviation;
	}

	public void setAbbreviation(String abbreviation) {
		this.abbreviation = abbreviation;
	}

	public String getDcqk() {
		return dcqk;
	}

	public void setDcqk(String dcqk) {
		this.dcqk = dcqk;
	}

	
}
