package com.crfeb.tbmpt.open.model;

import java.util.List;

public class ProInfo {
	private String id;
	private String name;
	//负责人名称
	private String empName;
	
	
	public String getEmpName() {
		return empName;
	}
	public void setEmpName(String empName) {
		this.empName = empName;
	}
	private List<ProSec> proSecs;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<ProSec> getProSecs() {
		return proSecs;
	}
	public void setProSecs(List<ProSec> proSecs) {
		this.proSecs = proSecs;
	}
}
