package com.crfeb.tbmpt.zsjk.report.model;

import com.baomidou.mybatisplus.annotations.IdType;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;

/**
 * <p>施工进度信息  实体信息类</p>
 * <p>描述：实体描述信息.</p>
 * <p>系统：展示界面接口</p>
 * <p>日期：2017-03-08</p>
 * @version 1.0
 * @author wl_wpg
 */
@TableName("ZSJK_REPORT_SGJDXX")
public class  ZsJkReportSgjdxx {

	/** UUID */
	@TableId(type = IdType.UUID)
    private String id;
	
	/** 项目ID */
	private String xmId;
	
	/** 线路ID */
	private String xlId;
	
	/** 盾构机ID */
	private String dgjId;
	
	/** 盾构机ID */
	private String xmmc;
	
	/** 项目总长（环） */
	private String xmzc;
	
	/** 已推进（环） */
	private String yth;
	
	/** 7日内平均推进速度*/
	private String pjjjsd7;
	
	/** 计划工期 */
	private String jhgq;
	
	/** 统计截止日期 */
	private String tjjzrq;
	
	/** 预计完成时间 */
	private String yjwgrq;
	
	/** 计划日掘进速度 */
	private String jhrjjsd;
	
	/** 日掘进速度合格范围 */
	private String rjjsdhgfw;
	
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getXmId() {
		return xmId;
	}

	public void setXmId(String xmId) {
		this.xmId = xmId;
	}

	public String getXlId() {
		return xlId;
	}

	public void setXlId(String xlId) {
		this.xlId = xlId;
	}

	public String getDgjId() {
		return dgjId;
	}

	public void setDgjId(String dgjId) {
		this.dgjId = dgjId;
	}

	public String getXmmc() {
		return xmmc;
	}

	public void setXmmc(String xmmc) {
		this.xmmc = xmmc;
	}

	public String getXmzc() {
		return xmzc;
	}

	public void setXmzc(String xmzc) {
		this.xmzc = xmzc;
	}

	public String getYth() {
		return yth;
	}

	public void setYth(String yth) {
		this.yth = yth;
	}

	public String getPjjjsd7() {
		return pjjjsd7;
	}

	public void setPjjjsd7(String pjjjsd7) {
		this.pjjjsd7 = pjjjsd7;
	}

	public String getJhgq() {
		return jhgq;
	}

	public void setJhgq(String jhgq) {
		this.jhgq = jhgq;
	}

	public String getTjjzrq() {
		return tjjzrq;
	}

	public void setTjjzrq(String tjjzrq) {
		this.tjjzrq = tjjzrq;
	}

	public String getYjwgrq() {
		return yjwgrq;
	}

	public void setYjwgrq(String yjwgrq) {
		this.yjwgrq = yjwgrq;
	}

	public String getJhrjjsd() {
		return jhrjjsd;
	}

	public void setJhrjjsd(String jhrjjsd) {
		this.jhrjjsd = jhrjjsd;
	}

	public String getRjjsdhgfw() {
		return rjjsdhgfw;
	}

	public void setRjjsdhgfw(String rjjsdhgfw) {
		this.rjjsdhgfw = rjjsdhgfw;
	}

	
}
