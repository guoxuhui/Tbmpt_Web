package com.crfeb.tbmpt.sys.model.dto;

import java.io.Serializable;

/***
 * 系统日志 工具类
 * @author wl_wpg
 *
 */
public class LogDto implements Serializable  {
	
	 
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	/** 登陆名 */
	private String loginName;
	/** 操作系统 */
    private String sysName;
    /** 开始时间*/
    private String startTime;
    /** 结束时间 */
    private String endTime;
    
	public String getLoginName() {
		return loginName;
	}
	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}
	public String getSysName() {
		return sysName;
	}
	public void setSysName(String sysName) {
		this.sysName = sysName;
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
