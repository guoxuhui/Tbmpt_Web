package com.crfeb.tbmpt.project.model.vo;

import java.io.Serializable;

public class ProFbgcQueryVo implements Serializable {

	private static final long serialVersionUID = 1L;

	private String proId;

	private String dwgcId;
	
	private String fbgcname;

	public String getProId() {
		return proId;
	}

	public void setProId(String proId) {
		this.proId = proId;
	}

	

	public String getDwgcId() {
		return dwgcId;
	}

	public void setDwgcId(String dwgcId) {
		this.dwgcId = dwgcId;
	}

	public String getFbgcname() {
		return fbgcname;
	}

	public void setFbgcname(String fbgcname) {
		this.fbgcname = fbgcname;
	}

	
}
