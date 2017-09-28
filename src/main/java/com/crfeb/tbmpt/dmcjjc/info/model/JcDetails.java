package com.crfeb.tbmpt.dmcjjc.info.model;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;

@TableName("dmcj_jc_details")
public class JcDetails implements Serializable{
	@TableField(exist = false)
	private static final long serialVersionUID = 1L;

	/** 主键id */
	@TableId(value = "id")
	private String id;
	
	/** 外键fid */
	@TableField(value = "fid")
	private String fid;

	/** 工程编号 */
	@TableField(value = "gcbh")
	private String gcbh;

	/** 点位编号 */
	@TableField(value = "jcdno")
	private String jcdNo;
	
	/** 区间 */
	@TableField(value = "qujian")
	private String qujian;

	/** 线路编号 */
	@TableField(value = "xianlu")
	private String xianlu;
	
	/** 环号/位置 */
	@TableField(value = "weizhi")
	private String weizhi;
	
	/** 里程 */
	@TableField(value = "licheng")
	private String licheng;
	
	/** 本次高程 */
	@TableField(value = "bcgc")
	private Float bcgc;
	
	/** 备注 */
	@TableField(value = "remarks")
	private String remarks;
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getFid() {
		return fid;
	}

	public void setFid(String fid) {
		this.fid = fid;
	}

	public String getGcbh() {
		return gcbh;
	}

	public void setGcbh(String gcbh) {
		this.gcbh = gcbh;
	}

	public String getJcdNo() {
		return jcdNo;
	}

	public void setJcdNo(String jcdNo) {
		this.jcdNo = jcdNo;
	}

	public String getQujian() {
		return qujian;
	}

	public void setQujian(String qujian) {
		this.qujian = qujian;
	}

	public String getXianlu() {
		return xianlu;
	}

	public void setXianlu(String xianlu) {
		this.xianlu = xianlu;
	}

	public String getWeizhi() {
		return weizhi;
	}

	public void setWeizhi(String weizhi) {
		this.weizhi = weizhi;
	}

	public String getLicheng() {
		return licheng;
	}

	public void setLicheng(String licheng) {
		this.licheng = licheng;
	}

	public Float getBcgc() {
		return bcgc;
	}

	public void setBcgc(Float bcgc) {
		this.bcgc = bcgc;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

}
