package com.crfeb.tbmpt.zsjk.report.model;

import com.baomidou.mybatisplus.annotations.IdType;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;

/**
 * <p>盾构监控异常状态  实体信息类</p>
 * <p>描述：实体描述信息.</p>
 * <p>系统：展示界面接口</p>
 * <p>日期：2017-03-07</p>
 * @version 1.0
 * @author wl_wpg
 */
@TableName("ZSJK_REPORT_DGJKYCZT")
public class ZsJkReportDgjkyczt {

	/** UUID */
	@TableId(type = IdType.UUID)
    private String id;
	
	/** 项目ID */
	private String xmId;
	
	/** 线路ID */
	private String xlId;
	
	/** 盾构机ID */
	private String dgjId;
	
	/** 环号 */
	private String hh;
	
	/** 施工时间 */
	private String sgsj;
	
	/** 停机时间 */
	private String tjsj;
	
	/** 实际出土量（理论出土量）（方） */
	private String sjctl;
	
	/** 注浆量（方） */
	private String zjl;
	
	/** 平均掘进速度 */
	private String pjjjsd;
	
	/** 土压时间统计（土压时间／掘进时间） */
	private String tysjtj;
	
	/** 盾构姿态（mm） */
	private String dgzt;
	
	/** 备注 */
	private String bz;

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

	public String getHh() {
		return hh;
	}

	public void setHh(String hh) {
		this.hh = hh;
	}

	public String getSgsj() {
		return sgsj;
	}

	public void setSgsj(String sgsj) {
		this.sgsj = sgsj;
	}

	public String getTjsj() {
		return tjsj;
	}

	public void setTjsj(String tjsj) {
		this.tjsj = tjsj;
	}

	public String getSjctl() {
		return sjctl;
	}

	public void setSjctl(String sjctl) {
		this.sjctl = sjctl;
	}

	public String getZjl() {
		return zjl;
	}

	public void setZjl(String zjl) {
		this.zjl = zjl;
	}

	public String getPjjjsd() {
		return pjjjsd;
	}

	public void setPjjjsd(String pjjjsd) {
		this.pjjjsd = pjjjsd;
	}

	public String getTysjtj() {
		return tysjtj;
	}

	public void setTysjtj(String tysjtj) {
		this.tysjtj = tysjtj;
	}

	public String getDgzt() {
		return dgzt;
	}

	public void setDgzt(String dgzt) {
		this.dgzt = dgzt;
	}

	public String getBz() {
		return bz;
	}

	public void setBz(String bz) {
		this.bz = bz;
	}
	
	
	
}
