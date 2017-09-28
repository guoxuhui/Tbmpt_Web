package com.crfeb.tbmpt.risk.model.vo;

import java.io.Serializable;

public class RiskLevelQueryVo  implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String levelName;
	private String sort;
	private String riskDesc;
	public String getLevelName() {
		return levelName;
	}
	public void setLevelName(String levelName) {
		this.levelName = levelName;
	}
	public String getSort() {
		return sort;
	}
	public String getRiskDesc() {
		return riskDesc;
	}
	public void setRiskDesc(String riskDesc) {
		this.riskDesc = riskDesc;
	}
	public void setSort(String sort) {
		this.sort = sort;
	}

}
