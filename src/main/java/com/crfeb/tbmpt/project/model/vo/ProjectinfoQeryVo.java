package com.crfeb.tbmpt.project.model.vo;

import java.io.Serializable;

/**
 * @description：ProjectinfoQeryVo
 * @author：smxg
 * @date：2016-10-17 11:12
 */
public class ProjectinfoQeryVo implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String name;
    
	/** 项目简称 */
	private String abbreviation;

    private String startTime;
    
    private String endTime;
    
    /** 施工状态*/
    private String proState;
    
    

	public String getProState() {
		return proState;
	}

	public void setProState(String proState) {
		this.proState = proState;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAbbreviation() {
		return abbreviation;
	}

	public void setAbbreviation(String abbreviation) {
		this.abbreviation = abbreviation;
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