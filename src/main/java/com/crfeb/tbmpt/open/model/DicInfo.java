package com.crfeb.tbmpt.open.model;

import java.util.List;

public class DicInfo {

	private String id;
	private String name;

	private List<DicInfo> childs;
	
	private List<DicInfo> sections;
	private List<DicInfo> lines;
	private List<DicInfo> dwgcs;
	private List<DicInfo> fbgcs;
	
	private List<DicInfo> gpzlDDinfos;
	
	private List<DicInfo> sgzlJtwzinfos;
	private List<DicInfo> sgzlZlqxinfos;
	
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
	public List<DicInfo> getChilds() {
		return childs;
	}
	public void setChilds(List<DicInfo> childs) {
		this.childs = childs;
	}
	public List<DicInfo> getGpzlDDinfos() {
		return gpzlDDinfos;
	}
	public void setGpzlDDinfos(List<DicInfo> gpzlDDinfos) {
		this.gpzlDDinfos = gpzlDDinfos;
	}
	public List<DicInfo> getSgzlJtwzinfos() {
		return sgzlJtwzinfos;
	}
	public void setSgzlJtwzinfos(List<DicInfo> sgzlJtwzinfos) {
		this.sgzlJtwzinfos = sgzlJtwzinfos;
	}
	public List<DicInfo> getSgzlZlqxinfos() {
		return sgzlZlqxinfos;
	}
	public void setSgzlZlqxinfos(List<DicInfo> sgzlZlqxinfos) {
		this.sgzlZlqxinfos = sgzlZlqxinfos;
	}
	public List<DicInfo> getSections() {
		return sections;
	}
	public void setSections(List<DicInfo> sections) {
		this.sections = sections;
	}
	public List<DicInfo> getLines() {
		return lines;
	}
	public void setLines(List<DicInfo> lines) {
		this.lines = lines;
	}
	public List<DicInfo> getFbgcs() {
		return fbgcs;
	}
	public void setFbgcs(List<DicInfo> fbgcs) {
		this.fbgcs = fbgcs;
	}
	public List<DicInfo> getDwgcs() {
		return dwgcs;
	}
	public void setDwgcs(List<DicInfo> dwgc) {
		this.dwgcs = dwgc;
	}
}
