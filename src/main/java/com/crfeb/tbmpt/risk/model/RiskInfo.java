package com.crfeb.tbmpt.risk.model;

import com.baomidou.mybatisplus.annotations.IdType;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;

import java.io.Serializable;

/**
 *
 * 风控信息表
 *
 */
@TableName("RISK_INFO")
public class RiskInfo implements Serializable {

	@TableField(exist = false)
	private static final long serialVersionUID = 1L;

	/** UUID */
	@TableId(type = IdType.UUID)
	private String id;

	/** 片区分类 */
	private String section;

	/** 项目部名称 */
	@TableField(value = "PRO_NAME")
	private String proName;

	/** 安全风险级别 */
	@TableField(value = "RIKE_LEVEL")
	private String rikeLevel;

	/** 安全质量风险 */
	@TableField(value = "RIKE_DESC")
	private String rikeDesc;

	/** 上报人 */
	@TableField(value = "UP_USER")
	private String upUser;

	/** 上报人联系方式 */
	@TableField(value = "UP_USER_PHONE")
	private String upUserPhone;

	/** 监控(发生)时段开始 */
	@TableField(value = "RIKE_TIME_START")
	private String rikeTimeStart;

	/** 监控(发生)时段结束 */
	@TableField(value = "RIKE_TIME_END")
	private String rikeTimeEnd;

	/** 填报时间 */
	@TableField(value = "UP_TIME")
	private String upTime;

	/** 状态 是否排除 0:未排除 1:已排除 */
	@TableField(value = "IS_OUT")
	private String isOut;

	/** 项目负责人 */
	@TableField(value = "EMP_ID")
	private String empId;

	/** 关联机构编号 */
	@TableField(value = "ORGZ_ID")
	private String orgzId;

	/** 上级机构编号 */
	@TableField(value = "PARENT_ID")
	private String parentId;

	/** 负责部门 */
	private String dpts;

	/** 负责人 */
	private String persoon;

	/** 主要管控措施 */
	@TableField(value = "MAIN_CONTROL_METHOD")
	private String mainControlMethod;

	/** 备注 */
	private String remark;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getSection() {
		return this.section;
	}

	public void setSection(String section) {
		this.section = section;
	}

	public String getProName() {
		return this.proName;
	}

	public void setProName(String proName) {
		this.proName = proName;
	}

	public String getRikeLevel() {
		return this.rikeLevel;
	}

	public void setRikeLevel(String rikeLevel) {
		this.rikeLevel = rikeLevel;
	}

	public String getRikeDesc() {
		return this.rikeDesc;
	}

	public void setRikeDesc(String rikeDesc) {
		this.rikeDesc = rikeDesc;
	}

	public String getUpUser() {
		return this.upUser;
	}

	public void setUpUser(String upUser) {
		this.upUser = upUser;
	}

	public String getUpUserPhone() {
		return this.upUserPhone;
	}

	public void setUpUserPhone(String upUserPhone) {
		this.upUserPhone = upUserPhone;
	}

	public String getRikeTimeStart() {
		return rikeTimeStart;
	}

	public void setRikeTimeStart(String rikeTimeStart) {
		this.rikeTimeStart = rikeTimeStart;
	}

	public String getRikeTimeEnd() {
		return rikeTimeEnd;
	}

	public void setRikeTimeEnd(String rikeTimeEnd) {
		this.rikeTimeEnd = rikeTimeEnd;
	}

	public String getUpTime() {
		return this.upTime;
	}

	public void setUpTime(String upTime) {
		this.upTime = upTime;
	}

	public String getIsOut() {
		return this.isOut;
	}

	public void setIsOut(String isOut) {
		this.isOut = isOut;
	}

	public String getEmpId() {
		return this.empId;
	}

	public void setEmpId(String empId) {
		this.empId = empId;
	}

	public String getOrgzId() {
		return this.orgzId;
	}

	public void setOrgzId(String orgzId) {
		this.orgzId = orgzId;
	}

	public String getParentId() {
		return this.parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	public String getDpts() {
		return this.dpts;
	}

	public void setDpts(String dpts) {
		this.dpts = dpts;
	}

	public String getPersoon() {
		return this.persoon;
	}

	public void setPersoon(String persoon) {
		this.persoon = persoon;
	}

	public String getMainControlMethod() {
		return this.mainControlMethod;
	}

	public void setMainControlMethod(String mainControlMethod) {
		this.mainControlMethod = mainControlMethod;
	}

	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

}
