package com.crfeb.tbmpt.open.model;

import java.util.List;

public class ProSec {

	private String id;
	private String name;
	private List<SecLine> secLines;
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
	public List<SecLine> getSecLines() {
		return secLines;
	}
	public void setSecLines(List<SecLine> secLines) {
		this.secLines = secLines;
	}
}
