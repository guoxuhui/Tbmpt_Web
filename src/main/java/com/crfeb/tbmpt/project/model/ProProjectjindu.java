package com.crfeb.tbmpt.project.model;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotations.IdType;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.crfeb.tbmpt.commons.utils.CommUtils;

@TableName("PRO_JINDU_DAY")
public class ProProjectjindu implements Serializable{

	/**
	 * 
	 */
	@TableField(exist = false)
	private static final long serialVersionUID = 1L;
	/**主键*/
	@TableId(type = IdType.UUID)
	private String id;
	
	/**工程编号*/
	@TableField(value = "PRO_ID")
	private String proId;
	
	/**区间编号*/
	@TableField(value = "QJ_ID")
	private String qjId;
	
	/**线路编号*/
	@TableField(value = "XL_ID")
	private String xlId;
	
	
	/**日期*/
	private String rq;
	
	/**日掘进环数*/
	private Integer rjjnum;
	
	/**备注*/
	private String remarks;
	
	

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getProId() {
		return proId;
	}

	public void setProId(String proId) {
		this.proId = proId;
	}

	public String getQjId() {
		return qjId;
	}

	public void setQjId(String qjId) {
		this.qjId = qjId;
	}

	public String getXlId() {
		return xlId;
	}

	public void setXlId(String xlId) {
		this.xlId = xlId;
	}

	public String getRq() {
		return rq;
	}

	public void setRq(String rq) {
		this.rq = rq;
	}

	public Integer getRjjnum() {
		return rjjnum;
	}

	public void setRjjnum(Integer rjjnum) {
		this.rjjnum = rjjnum;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}




}
