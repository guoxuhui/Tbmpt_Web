package com.crfeb.tbmpt.dmcjjc.info.model.vo;

import com.crfeb.tbmpt.dmcjjc.info.model.JcPoint;

public class DmcjJcPointDto extends  JcPoint{
	private String xianluName;
	private String qujianName;
	private Float scgc; //上次高程
	public String getXianluName() {
		return xianluName;
	}
	public void setXianluName(String xianluName) {
		this.xianluName = xianluName;
	}
	public String getQujianName() {
		return qujianName;
	}
	public void setQujianName(String qujianName) {
		this.qujianName = qujianName;
	}
	public Float getScgc() {
		return scgc;
	}
	public void setScgc(Float scgc) {
		this.scgc = scgc;
	}

	
}
