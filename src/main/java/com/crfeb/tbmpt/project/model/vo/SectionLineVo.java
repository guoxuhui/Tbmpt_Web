package com.crfeb.tbmpt.project.model.vo;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotations.IdType;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;

/**
 *
 * 业务管理——掘进线路信息表
 *
 */
public class SectionLineVo implements Serializable {

	@TableField(exist = false)
	private static final long serialVersionUID = 1L;

	/** UUID */
	@TableId(type = IdType.UUID)
	private String id;
	
	/** 掘进线路名称 */
	@TableField(value = "LINE_NAME")
	private String lineName;

	/** 起始里程 */
	@TableField(value = "START_MILEAGE")
	private String startMileage;

	/** 终止里程 */
	@TableField(value = "END_MILEAGE")
	private String endMileage;

	/** 管环数量 */
	@TableField(value = "RING_COUNT")
	private Double ringCount;

	/** 里程前辍标识 */
	@TableField(value = "MILEAGE_PREFIX")
	private String mileagePrefix;
	
	private String usingTbmName;
	
	/** 在用盾构机信息ID */
	@TableField(value = "USING_TBM_ID")
	private String usingTbmId;
	
	private String tbmName;
	
	/** 盾构机信息ID */
	@TableField(value = "TBM_ID")
	private String tbmId;

	/** 盾构机司机 */
	private String dgChauffeur;
	
	

	/** 区间信息ID */
	@TableField(value = "SECTION_ID")
	private String sectionId;
	
	private String sectionName;

	/** 项目信息ID */
	@TableField(value = "PRO_ID")
	private String proId;
	
	private String proName;

	/** 隧道长度(米) */
	private Double tunnellength;

	/** 预计始发日期 */
	private String yjsfrq;
	
	/** 预计出洞日期  */
	private String yjcdrq;
	
	/** 实际始发日期 */
	private String sjsfrq;
	
	/** 实际出洞日期  */
	private String sjcdrq;
	

	/** 推进总工期(天) */
	private Double tunneltime;

	/** 起始环号 */
	@TableField(value = "START_RINGNUM")
	private Double startRingnum;

	/** 起始位置*/
	@TableField(value = "START_POSITION")
	private String startPosition;
	
	/** 施工状态：0：未开工，1=已开工，2=已完工 */
	@TableField(value = "LINE_STATUS")
	private int lineStatus;

	/** 录入时间 */
	private String entertime;

	/** 录入人 */
	private String enterperson;

	/** 删除标识 */
	private int deleteflag;


	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getLineName() {
		return this.lineName;
	}

	public void setLineName(String lineName) {
		this.lineName = lineName;
	}

	public String getSectionName() {
		return sectionName;
	}

	public void setSectionName(String sectionName) {
		this.sectionName = sectionName;
	}

	public String getProName() {
		return proName;
	}

	public void setProName(String proName) {
		this.proName = proName;
	}

	public String getStartMileage() {
		return this.startMileage;
	}

	public void setStartMileage(String startMileage) {
		this.startMileage = startMileage;
	}

	public String getEndMileage() {
		return this.endMileage;
	}

	public void setEndMileage(String endMileage) {
		this.endMileage = endMileage;
	}

	public Double getRingCount() {
		return this.ringCount;
	}

	public void setRingCount(Double ringCount) {
		this.ringCount = ringCount;
	}

	public String getMileagePrefix() {
		return this.mileagePrefix;
	}

	public void setMileagePrefix(String mileagePrefix) {
		this.mileagePrefix = mileagePrefix;
	}

	public String getSectionId() {
		return this.sectionId;
	}

	public void setSectionId(String sectionId) {
		this.sectionId = sectionId;
	}

	public String getProId() {
		return this.proId;
	}

	public void setProId(String proId) {
		this.proId = proId;
	}

	public Double getTunnellength() {
		return this.tunnellength;
	}

	public void setTunnellength(Double tunnellength) {
		this.tunnellength = tunnellength;
	}

	public Double getTunneltime() {
		return this.tunneltime;
	}

	public void setTunneltime(Double tunneltime) {
		this.tunneltime = tunneltime;
	}

	public Double getStartRingnum() {
		return this.startRingnum;
	}

	public void setStartRingnum(Double startRingnum) {
		this.startRingnum = startRingnum;
	}

	public String getStartPosition() {
		return this.startPosition;
	}

	public void setStartPosition(String startPosition) {
		this.startPosition = startPosition;
	}

	public String getUsingTbmId() {
		return usingTbmId;
	}

	public void setUsingTbmId(String usingTbmId) {
		this.usingTbmId = usingTbmId;
	}

	public String getTbmId() {
		return tbmId;
	}

	public void setTbmId(String tbmId) {
		this.tbmId = tbmId;
	}

	public int getLineStatus() {
		return lineStatus;
	}

	public void setLineStatus(int lineStatus) {
		this.lineStatus = lineStatus;
	}

	public String getUsingTbmName() {
		return usingTbmName;
	}

	public void setUsingTbmName(String usingTbmName) {
		this.usingTbmName = usingTbmName;
	}

	public String getTbmName() {
		return tbmName;
	}

	public void setTbmName(String tbmName) {
		this.tbmName = tbmName;
	}

	public String getEntertime() {
		return this.entertime;
	}

	public void setEntertime(String entertime) {
		this.entertime = entertime;
	}

	public String getEnterperson() {
		return this.enterperson;
	}

	public void setEnterperson(String enterperson) {
		this.enterperson = enterperson;
	}

	public int getDeleteflag() {
		return this.deleteflag;
	}

	public void setDeleteflag(int deleteflag) {
		this.deleteflag = deleteflag;
	}

	

	public String getYjsfrq() {
		return yjsfrq;
	}

	public void setYjsfrq(String yjsfrq) {
		this.yjsfrq = yjsfrq;
	}

	public String getYjcdrq() {
		return yjcdrq;
	}

	public void setYjcdrq(String yjcdrq) {
		this.yjcdrq = yjcdrq;
	}

	public String getSjsfrq() {
		return sjsfrq;
	}

	public void setSjsfrq(String sjsfrq) {
		this.sjsfrq = sjsfrq;
	}

	public String getSjcdrq() {
		return sjcdrq;
	}

	public void setSjcdrq(String sjcdrq) {
		this.sjcdrq = sjcdrq;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getDgChauffeur() {
		return dgChauffeur;
	}

	public void setDgChauffeur(String dgChauffeur) {
		this.dgChauffeur = dgChauffeur;
	}
}
