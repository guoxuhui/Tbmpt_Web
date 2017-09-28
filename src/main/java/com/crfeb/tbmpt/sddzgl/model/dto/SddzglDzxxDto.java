package com.crfeb.tbmpt.sddzgl.model.dto;

import java.io.Serializable;

/**
 * <p>隧道地质管理：地质信息实体工具类</p>
 * <p>模块：隧道地质管理</p>
 * <p>日期：2017-03-28</p>
 * @version 1.0
 * @author wl_wpg
 */
public class SddzglDzxxDto implements Serializable{

	/** ID */
	private String id;

	/** 岩土名称 */
	private String ytmc;
	
	/** 岩土编号 */
	private String ytbh;
	
	/** 所属钻孔Id */
	private String zkId;
	
	/** 所属钻孔编号 */
	private String zkBh;
	
	/** 承载力特征值 */
	private String czltzz;
	
	/** 压缩模量 */
	private String ysml;
	
	/** 岩土施工分级 */
	private String ytsgfc;
	
	/** 地层埋深 */
	private String tcms;
	
	/** 地层描述 */
	private String dcms;
	
	/** 备注 */
	private String bz;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getYtmc() {
		return ytmc;
	}

	public void setYtmc(String ytmc) {
		this.ytmc = ytmc;
	}

	public String getYtbh() {
		return ytbh;
	}

	public void setYtbh(String ytbh) {
		this.ytbh = ytbh;
	}

	public String getZkId() {
		return zkId;
	}

	public void setZkId(String zkId) {
		this.zkId = zkId;
	}

	public String getZkBh() {
		return zkBh;
	}

	public void setZkBh(String zkBh) {
		this.zkBh = zkBh;
	}

	public String getCzltzz() {
		return czltzz;
	}

	public void setCzltzz(String czltzz) {
		this.czltzz = czltzz;
	}

	public String getYsml() {
		return ysml;
	}

	public void setYsml(String ysml) {
		this.ysml = ysml;
	}

	public String getYtsgfc() {
		return ytsgfc;
	}

	public void setYtsgfc(String ytsgfc) {
		this.ytsgfc = ytsgfc;
	}

	public String getTcms() {
		return tcms;
	}

	public void setTcms(String tcms) {
		this.tcms = tcms;
	}

	public String getDcms() {
		return dcms;
	}

	public void setDcms(String dcms) {
		this.dcms = dcms;
	}

	public String getBz() {
		return bz;
	}

	public void setBz(String bz) {
		this.bz = bz;
	}

	
}
