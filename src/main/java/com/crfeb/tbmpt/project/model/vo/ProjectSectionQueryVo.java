package com.crfeb.tbmpt.project.model.vo;

import java.io.Serializable;

/**
 * @description：ProjectSectionQueryVo
 * @author：smxg
 * @date：2016-10-17 11:12
 */
public class ProjectSectionQueryVo implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String proId;
	
	private String sectionName;

    private String startTime;
    
    private String endTime;

	public String getProId() {
		return proId;
	}

	public void setProId(String proId) {
		this.proId = proId;
	}

	public String getSectionName() {
		return sectionName;
	}

	public void setSectionName(String sectionName) {
		this.sectionName = sectionName;
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