package com.crfeb.tbmpt.sys.model.dto;

import java.io.Serializable;

/**
 * @description：RoleDto
 * @author：smxg
 * @date：2016-10-10 11:12
 */
public class RoleDto implements Serializable {
	
	private static final long serialVersionUID = 1L;
    private String name;
    private Integer status;
    private String startTime;
    private String endTime;
    private String orgzId;
    
	public String getOrgzId() {
		return orgzId;
	}
	public void setOrgzId(String orgzId) {
		this.orgzId = orgzId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
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