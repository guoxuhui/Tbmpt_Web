package com.crfeb.tbmpt.tbmmg.model;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotations.IdType;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;

/**
 *
 * 盾构机信息表
 *
 */
@TableName("PRO_TBMINFO")
public class ProTbminfo implements Serializable {

	@TableField(exist = false)
	private static final long serialVersionUID = 1L;

	/** 主键生成规则:长年号:2016+四位自增号 0001 */
	@TableId(type = IdType.UUID)
	private String id;

	/** 盾构机编号 */
	@TableField(value = "TBM_CODE")
	private String tbmCode;

	/** 盾构机名称 */
	@TableField(value = "TBM_NAME")
	private String tbmName;

	/** 盾构机管理号 */
	private String manageno;

	/** 生产厂商 */
	private String manufacturer;

	/** 出厂编号 */
	private String factorynumber;

	/** 规格 */
	private String specification;

	/** 型号 */
	private String model;

	/** 盾构机类型（1：土压、2：泥水）*/
	private int type;
	
	/** 适应地层*/
	private String sydc;
	
	/** 初始里程*/
	private String cslc;		

	/** 出厂日期 */
	private String factorydate;

	/** 资产归属 */
	@TableField(value = "TBM_VEST")
	private String tbmVest;

	/** 购置日期 */
	private String buydate;

	/** 0，未分配；1，已分配 */
	@TableField(value = "TBM_STATE")
	private int tbmState;
	
	/** 负责人名称 */
	private String functionaryName;
	
	/** 负责人联系方式 */
	@TableField(value = "contact_number")
	private String contactNumber;
	
	/** 备注 */
	private String remark;

	@TableField(value = "CREATE_TIME")
	private String createTime;

	@TableField(value = "CREATE_USER")
	private String createUser;
	
	@TableField(value = "UPDATE_TIME")
	private String updateTime;

	@TableField(value = "UPDATE_USER")
	private String updateUser;

	/** 删除标识 */
	private int deleteflag;

	
	
	public String getSydc() {
		return sydc;
	}

	public void setSydc(String sydc) {
		this.sydc = sydc;
	}

	public String getCslc() {
		return cslc;
	}

	public void setCslc(String cslc) {
		this.cslc = cslc;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTbmCode() {
		return this.tbmCode;
	}

	public void setTbmCode(String tbmCode) {
		this.tbmCode = tbmCode;
	}

	public String getTbmName() {
		return this.tbmName;
	}

	public void setTbmName(String tbmName) {
		this.tbmName = tbmName;
	}

	public String getManageno() {
		return this.manageno;
	}

	public void setManageno(String manageno) {
		this.manageno = manageno;
	}

	public String getManufacturer() {
		return this.manufacturer;
	}

	public void setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer;
	}

	public String getFactorynumber() {
		return this.factorynumber;
	}

	public void setFactorynumber(String factorynumber) {
		this.factorynumber = factorynumber;
	}

	public String getSpecification() {
		return this.specification;
	}

	public void setSpecification(String specification) {
		this.specification = specification;
	}

	public String getModel() {
		return this.model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public String getFactorydate() {
		return this.factorydate;
	}
	
	public void setFactorydate(String factorydate) {
		this.factorydate = factorydate;
	}

	public String getTbmVest() {
		return this.tbmVest;
	}

	public void setTbmVest(String tbmVest) {
		this.tbmVest = tbmVest;
	}

	public String getBuydate() {
		return this.buydate;
	}
	
	public void setBuydate(String buydate) {
		this.buydate = buydate;
	}

	public int getTbmState() {
		return this.tbmState;
	}

	public void setTbmState(int tbmState) {
		this.tbmState = tbmState;
	}

	public String getRemark() {
		return this.remark;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public String getCreateUser() {
		return createUser;
	}

	public void setCreateUser(String createUser) {
		this.createUser = createUser;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}

	public String getUpdateUser() {
		return updateUser;
	}

	public void setUpdateUser(String updateUser) {
		this.updateUser = updateUser;
	}

	public int getDeleteflag() {
		return this.deleteflag;
	}

	public void setDeleteflag(int deleteflag) {
		this.deleteflag = deleteflag;
	}

	
	public String getFunctionaryName() {
		return functionaryName;
	}

	public void setFunctionaryName(String functionaryName) {
		this.functionaryName = functionaryName;
	}

	public String getContactNumber() {
		return contactNumber;
	}

	public void setContactNumber(String contactNumber) {
		this.contactNumber = contactNumber;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}
}
