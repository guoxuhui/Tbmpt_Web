package com.crfeb.tbmpt.project.model.vo;

import java.io.Serializable;


/**
 * @description：ProjectSectionQueryVo
 * @author：smxg
 * @date：2016-10-17 11:12
 */
public class SectionLineQueryVo implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String proId;
	
	private String sectionId;
	
	private String lineName;

    private String startTime;
    
    private String endTime;

	public String getProId() {
		return proId;
	}

	public void setProId(String proId) {
		this.proId = proId;
	}


	public String getSectionId() {
		return sectionId;
	}

	public void setSectionId(String sectionId) {
		this.sectionId = sectionId;
	}

	public String getLineName() {
		return lineName;
	}

	public void setLineName(String lineName) {
		this.lineName = lineName;
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