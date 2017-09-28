package com.crfeb.tbmpt.zsjk.plc.model.dto;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * 盾构机机器数据实时表
 * 所属项目、所属区间、所属线路、所属盾构机、以及PLC点位
 */
public class ProSecLinePlcDto implements Serializable {

	private static final long serialVersionUID = 1L;

	private String orgz_id;
	private String pro_id;
	private String pro_name;
	private String section_id;
	private String section_name;
	private String line_id;
	private String line_name;
	private String tbm_id;
	private String tbm_name;
	private String dwid;
	private String dwname;
	private String tagvalue;
	private String tagtime;
	private Map<String,ProSecLinePlcDto> plcMap = new HashMap<String,ProSecLinePlcDto>();
	
	public String getOrgz_id() {
		return orgz_id;
	}
	public void setOrgz_id(String orgz_id) {
		this.orgz_id = orgz_id;
	}
	public String getPro_id() {
		return pro_id;
	}
	public void setPro_id(String pro_id) {
		this.pro_id = pro_id;
	}
	public String getPro_name() {
		return pro_name;
	}
	public void setPro_name(String pro_name) {
		this.pro_name = pro_name;
	}
	public String getSection_id() {
		return section_id;
	}
	public void setSection_id(String section_id) {
		this.section_id = section_id;
	}
	public String getSection_name() {
		return section_name;
	}
	public void setSection_name(String section_name) {
		this.section_name = section_name;
	}
	public String getLine_id() {
		return line_id;
	}
	public void setLine_id(String line_id) {
		this.line_id = line_id;
	}
	public String getLine_name() {
		return line_name;
	}
	public void setLine_name(String line_name) {
		this.line_name = line_name;
	}
	public String getTbm_id() {
		return tbm_id;
	}
	public void setTbm_id(String tbm_id) {
		this.tbm_id = tbm_id;
	}
	public String getTbm_name() {
		return tbm_name;
	}
	public void setTbm_name(String tbm_name) {
		this.tbm_name = tbm_name;
	}
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
	public Map<String, ProSecLinePlcDto> getPlcMap() {
		return plcMap;
	}
	public void setPlcMap(Map<String, ProSecLinePlcDto> plcMap) {
		this.plcMap = plcMap;
	}


}
