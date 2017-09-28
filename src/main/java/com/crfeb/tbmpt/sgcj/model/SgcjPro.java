package com.crfeb.tbmpt.sgcj.model;

import java.util.List;

import com.crfeb.tbmpt.project.model.ProProjectinfo;
import com.crfeb.tbmpt.sys.base.model.dto.SysFujianDto;

public class SgcjPro {
	
	private ProProjectinfo pro;
	private List<SgcjSec> secs;
	private List<SysFujianDto> fjs;
	
	public ProProjectinfo getPro() {
		return pro;
	}
	public void setPro(ProProjectinfo pro) {
		this.pro = pro;
	}
	public List<SgcjSec> getSecs() {
		return secs;
	}
	public void setSecs(List<SgcjSec> secs) {
		this.secs = secs;
	}
	public List<SysFujianDto> getFjs() {
		return fjs;
	}
	public void setFjs(List<SysFujianDto> fjs) {
		this.fjs = fjs;
	}

}
