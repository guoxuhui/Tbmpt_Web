package com.crfeb.tbmpt.machine.model;

import java.io.Serializable;

public class MachineAlarmParam implements Serializable {

	private static final long serialVersionUID = 1L;
	private String dwid;
	private String dwname;
	private String tagvalue;
	private String tagtime;
	
	public String getDwid() {
		return dwid;
	}
	public void setDwid(String dwid) {
		this.dwid = dwid;
	}
	public String getDwname() {
		return dwname;
	}
	public void setDwname(String dwname) {
		this.dwname = dwname;
	}
	public String getTagvalue() {
		return tagvalue;
	}
	public void setTagvalue(String tagvalue) {
		this.tagvalue = tagvalue;
	}
	public String getTagtime() {
		return tagtime;
	}
	public void setTagtime(String tagtime) {
		this.tagtime = tagtime;
	}

}
