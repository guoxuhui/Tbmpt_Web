package com.crfeb.tbmpt.sys.model.dto;

import java.io.Serializable;

/**
 * @description：UserDto
 * @author：smxg
 * @date：2016-10-10 11:12
 */
public class UserDto implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private String loginName;
    private String name;
    private Integer status;
    private String orgzId;
    private String empId;
    private String startTime;
    private String endTime;
	public String getLoginName() {
		return loginName;
	}
	public void setLoginName(String loginName) {
		this.loginName = loginName;
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
	public String getOrgzId() {
		return orgzId;
	}
	public void setOrgzId(String orgzId) {
		this.orgzId = orgzId;
	}
	public String getEmpId() {
		return empId;
	}
	public void setEmpId(String empId) {
		this.empId = empId;
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