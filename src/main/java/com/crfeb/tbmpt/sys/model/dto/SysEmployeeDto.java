package com.crfeb.tbmpt.sys.model.dto;

import java.io.Serializable;

/**
 * @description：SysEmployeeDto
 * @author：smxg
 * @date：2016-10-10 11:12
 */
public class SysEmployeeDto implements Serializable {

	private static final long serialVersionUID = 1L;

	private String name;
	private String orgzId;
	private String sex;
	private String birthday;
	private String cid;
	private String phone;
    private String startTime;
    private String endTime;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getBirthday() {
		return birthday;
	}
	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}
	public String getCid() {
		return cid;
	}
	public void setCid(String cid) {
		this.cid = cid;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
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
	public String getOrgzId() {
		return orgzId;
	}
	public void setOrgzId(String orgzId) {
		this.orgzId = orgzId;
	}
}
