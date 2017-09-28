package com.crfeb.tbmpt.project.model.vo;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotations.IdType;
import com.baomidou.mybatisplus.annotations.TableId;



public class ProProjectjinduQueryVo implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**主键*/
	@TableId(type = IdType.INPUT)
	private String id;
	
	private String proId;
	
	private String qjId;
	
	private String xlId;
	
	
	private String startTime;
	
	
	private String endTime;
	
	private String rq;
	
	private Integer rjjnum;
	
	private String lineName;
	
	private String proName;
	
	private String sectionName;
	
	private String remarks;
	
	
	
	

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public String getProName() {
		return proName;
	}

	public void setProName(String proName) {
		this.proName = proName;
	}

	public String getSectionName() {
		return sectionName;
	}

	public void setSectionName(String sectionName) {
		this.sectionName = sectionName;
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

	public String getXlId() {
		return xlId;
	}

	public void setXlId(String xlId) {
		this.xlId = xlId;
	}

	public String getLineName() {
		return lineName;
	}

	public void setLineName(String lineName) {
		this.lineName = lineName;
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

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	


}
