package com.crfeb.tbmpt.dmcjjc.info.model.vo;

import com.crfeb.tbmpt.dmcjjc.info.model.JcInfo;

public class DmcjJcInfoDto extends JcInfo {
	private String beginTime;
	private String endTime;
	private String details;
	private String projName;
	private String ifsb;
	/**
	 * 显示导入文件名
	 */
	private String impFileName;
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
	public String getDetails() {
		return details;
	}
	public void setDetails(String details) {
		this.details = details;
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
	public String getImpFileName() {
		return impFileName;
	}
	public void setImpFileName(String impFileName) {
		this.impFileName = impFileName;
	}
	
}
