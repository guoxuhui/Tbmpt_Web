package com.crfeb.tbmpt.dmcjjc.bg.model.vo;

import com.crfeb.tbmpt.dmcjjc.bg.model.JcbgsbInfo;


public class JcbgsbInfoDto extends JcbgsbInfo {
	private String beginTime;
	private String endTime;
	private String projName;
	private String ifsb;
	public String getBeginTime() {
		return beginTime;
	}
	public void setBeginTime(String beginTime) {
		this.beginTime = beginTime;
	}
	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	public String getProjName() {
		return projName;
	}
	public void setProjName(String projName) {
		this.projName = projName;
	}
	public String getIfsb() {
		return ifsb;
	}
	public void setIfsb(String ifsb) {
		this.ifsb = ifsb;
	}
	
}
