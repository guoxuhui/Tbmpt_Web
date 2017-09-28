package com.crfeb.tbmpt.project.model.vo;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotations.IdType;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;

/**
 *
 * 项目信息表
 *
 */
public class ProjectinfoVo implements Serializable {

	@TableField(exist = false)
	private static final long serialVersionUID = 1L;

	/** UUID */
	@TableId(type = IdType.UUID)
	private String id;

	/** 自定义编码规则 */
	@TableField(value = "PRO_CODE")
	private String proCode;

	/** 项目名称 */
	@TableField(value = "PRO_NAME")
	private String proName;

	/** 项目简称 */
	private String abbreviation;
	
	/** 项目负责人 */
	@TableField(value = "EMP_ID")
	private String empId;
	
	/** 项目负责人名称 */
	private String empName;
	
	/** 项目负责人 联系方式*/
	private String phone;


	/** 关联机构 */
	@TableField(value = "ORGZ_ID")
	private String orgzId;
	
	/** 上级机构 */
	@TableField(value = "PARENT_ID")
	private String parentId;
	
	private String parentName;

	/** 地理位置 */
	private String position;
	
	/** 国家 **/
	private String country;
	
	/** 地区 **/
	private String area;
	
	/** 所在省份 */
	private String province;
	
	/** 城市 **/
	private String city;
	
	/** 地铁线路 **/
	private String line;
	
	/** 线路标段 **/
	private String tender;

	/** 施工地址 */
	private String projectaddress;

	/** 掘进总长度(米) */
	private Double tunnellength;

	/** 项目环宽(米) */
	private Double ringwidth;

	/** 进场日期 */
	private String startdate;

	/** 预计完成日期 */
	private String estimateenddate;

	/** 实际完成日期 */
	private String actualenddate;

	/** 0，未开工；1，在建；2，完工. */
	@TableField(value = "PRO_STATE")
	private Double proState;

	/** 项目介绍 */
	private String description;

	/** 备注 */
	private String remark;

	/** 录入时间 */
	private String entertime;

	/** 录入人 */
	private String enterperson;

	/** 删除标识 */
	private Double deleteflag;
	
	/** 承建单位   */
	private String cjdw;

	/** 监理单位 */
	private String jldw;
	
	/** 建设单位 */
	private String jsdw;

	/** 合同金额*/
	private String htje;
	/** 开累完成*/
	private String klwc;
	/** 管片外径*/
	private String gpwj;
	
	
	public String getHtje() {
		return htje;
	}

	public void setHtje(String htje) {
		this.htje = htje;
	}

	public String getKlwc() {
		return klwc;
	}

	public void setKlwc(String klwc) {
		this.klwc = klwc;
	}

	public String getGpwj() {
		return gpwj;
	}

	public void setGpwj(String gpwj) {
		this.gpwj = gpwj;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getProName() {
		return this.proName;
	}

	public void setProName(String proName) {
		this.proName = proName;
	}

	
	public String getAbbreviation() {
		return abbreviation;
	}

	public void setAbbreviation(String abbreviation) {
		this.abbreviation = abbreviation;
	}

	public String getProvince() {
		return this.province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getEmpId() {
		return this.empId;
	}

	public void setEmpId(String empId) {
		this.empId = empId;
	}

	public String getEmpName() {
		return empName;
	}

	public void setEmpName(String empName) {
		this.empName = empName;
	}

	public String getParentName() {
		return parentName;
	}

	public void setParentName(String parentName) {
		this.parentName = parentName;
	}

	public String getOrgzId() {
		return orgzId;
	}

	public void setOrgzId(String orgzId) {
		this.orgzId = orgzId;
	}

	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	public String getProjectaddress() {
		return this.projectaddress;
	}

	public void setProjectaddress(String projectaddress) {
		this.projectaddress = projectaddress;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getLine() {
		return line;
	}

	public void setLine(String line) {
		this.line = line;
	}

	public String getTender() {
		return tender;
	}

	public void setTender(String tender) {
		this.tender = tender;
	}

	public String getPosition() {
		return this.position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public Double getTunnellength() {
		return this.tunnellength;
	}

	public void setTunnellength(Double tunnellength) {
		this.tunnellength = tunnellength;
	}

	public Double getRingwidth() {
		return this.ringwidth;
	}

	public void setRingwidth(Double ringwidth) {
		this.ringwidth = ringwidth;
	}

	public String getStartdate() {
		return this.startdate;
	}

	public void setStartdate(String startdate) {
		this.startdate = startdate;
	}

	public String getEstimateenddate() {
		return this.estimateenddate;
	}

	public void setEstimateenddate(String estimateenddate) {
		this.estimateenddate = estimateenddate;
	}

	public String getActualenddate() {
		return this.actualenddate;
	}

	public void setActualenddate(String actualenddate) {
		this.actualenddate = actualenddate;
	}

	public Double getProState() {
		return this.proState;
	}

	public void setProState(Double proState) {
		this.proState = proState;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
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

	public Double getDeleteflag() {
		return this.deleteflag;
	}

	public void setDeleteflag(Double deleteflag) {
		this.deleteflag = deleteflag;
	}

	public String getProCode() {
		return proCode;
	}

	public void setProCode(String proCode) {
		this.proCode = proCode;
	}

	public String getCjdw() {
		return cjdw;
	}

	public void setCjdw(String cjdw) {
		this.cjdw = cjdw;
	}

	public String getJldw() {
		return jldw;
	}

	public void setJldw(String jldw) {
		this.jldw = jldw;
	}

	public String getJsdw() {
		return jsdw;
	}

	public void setJsdw(String jsdw) {
		this.jsdw = jsdw;
	}
}
