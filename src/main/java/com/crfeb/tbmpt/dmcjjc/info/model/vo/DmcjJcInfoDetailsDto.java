package com.crfeb.tbmpt.dmcjjc.info.model.vo;

import com.crfeb.tbmpt.dmcjjc.info.model.JcDetails;

public class DmcjJcInfoDetailsDto extends  JcDetails{
	private Float csgc;//初始高程
	private Float scgc;//上次高程
	private Float bcbhl;//本次变化量
	private Float ljbhl;//累计变化量
	private Float bhsl;//变化速率
	private String ifqr;
	private String ifsb;
	private String xianluName;
	private String qujianName;
	public String getIfqr() {
		return ifqr;
	}
	public void setIfqr(String ifqr) {
		this.ifqr = ifqr;
	}
	public String getIfsb() {
		return ifsb;
	}
	public void setIfsb(String ifsb) {
		this.ifsb = ifsb;
	}
	public Float getCsgc() {
		return csgc;
	}
	public void setCsgc(Float csgc) {
		this.csgc = csgc;
	}
	public Float getScgc() {
		return scgc;
	}
	public void setScgc(Float scgc) {
		this.scgc = scgc;
	}
	public Float getBcbhl() {
		return bcbhl;
	}
	public void setBcbhl(Float bcbhl) {
		this.bcbhl = bcbhl;
	}
	public Float getLjbhl() {
		return ljbhl;
	}
	public void setLjbhl(Float ljbhl) {
		this.ljbhl = ljbhl;
	}
	public Float getBhsl() {
		return bhsl;
	}
	public void setBhsl(Float bhsl) {
		this.bhsl = bhsl;
	}
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
}
