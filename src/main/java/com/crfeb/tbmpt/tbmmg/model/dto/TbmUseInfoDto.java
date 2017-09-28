package com.crfeb.tbmpt.tbmmg.model.dto;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotations.IdType;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;

/**
 * @description：ProjectinfoQeryVo
 * @author：lzh
 * @date：2017年1月7日16:34:01
 */
public class TbmUseInfoDto implements Serializable {

	private static final long serialVersionUID = 1L;
	
	/** 编号 */
	private String id;

	/** 工程ID */
	private String gcid;

	/** 区间ID */
	private String qjid;

	/** 线路ID */
	private String xlid;

	/** 盾构机编号 */
	private String tbmbh;

	/** 盾构机使用时间 */
	private String tbmUseTime;

	/** 盾构机名称 */
	private String tbmName;

	/** 到达时间 */
	private String daTime;

	/** 离开时间 */
	private String lkTime;

	/** 始发时间 */
	private String sfTime;

	/** 出洞时间 */
	private String cdTime;

	/** 运输时间 */
	private String ysTime;

	/** 维修时间 */
	private String wxTime;

	/** 所在单位 */
	private String szdw;

	/** 负责人 */
	private String person;

	/** 联系电话 */
	private String phone;

	/** 盾构机状态 */
	private String tbmState;

	/** 日期 */
	private String rq;


	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	
	public String getGcid() {
		return gcid;
	}

	public void setGcid(String gcid) {
		this.gcid = gcid;
	}

	public String getQjid() {
		return qjid;
	}

	public void setQjid(String qjid) {
		this.qjid = qjid;
	}

	public String getXlid() {
		return xlid;
	}

	public void setXlid(String xlid) {
		this.xlid = xlid;
	}

	public String getTbmbh() {
		return this.tbmbh;
	}

	public void setTbmbh(String tbmbh) {
		this.tbmbh = tbmbh;
	}

	public String getTbmUseTime() {
		return this.tbmUseTime;
	}

	public void setTbmUseTime(String tbmUseTime) {
		this.tbmUseTime = tbmUseTime;
	}


	public String getTbmName() {
		return this.tbmName;
	}

	public void setTbmName(String tbmName) {
		this.tbmName = tbmName;
	}

	public String getDaTime() {
		return this.daTime;
	}

	public void setDaTime(String daTime) {
		this.daTime = daTime;
	}

	public String getLkTime() {
		return this.lkTime;
	}

	public void setLkTime(String lkTime) {
		this.lkTime = lkTime;
	}

	public String getSfTime() {
		return this.sfTime;
	}

	public void setSfTime(String sfTime) {
		this.sfTime = sfTime;
	}

	public String getCdTime() {
		return this.cdTime;
	}

	public void setCdTime(String cdTime) {
		this.cdTime = cdTime;
	}

	public String getYsTime() {
		return this.ysTime;
	}

	public void setYsTime(String ysTime) {
		this.ysTime = ysTime;
	}

	public String getWxTime() {
		return this.wxTime;
	}

	public void setWxTime(String wxTime) {
		this.wxTime = wxTime;
	}

	public String getSzdw() {
		return this.szdw;
	}

	public void setSzdw(String szdw) {
		this.szdw = szdw;
	}

	public String getPerson() {
		return this.person;
	}

	public void setPerson(String person) {
		this.person = person;
	}

	public String getPhone() {
		return this.phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getTbmState() {
		return this.tbmState;
	}

	public void setTbmState(String tbmState) {
		this.tbmState = tbmState;
	}

	public String getRq() {
		return this.rq;
	}

	public void setRq(String rq) {
		this.rq = rq;
	}
	
}