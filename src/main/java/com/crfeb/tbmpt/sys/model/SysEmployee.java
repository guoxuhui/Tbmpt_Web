package com.crfeb.tbmpt.sys.model;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotations.IdType;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;

/**
 *
 * null
 *
 */
@TableName("SYS_EMPLOYEE")
public class SysEmployee implements Serializable {

	@TableField(exist = false)
	private static final long serialVersionUID = 1L;

	/** UUID */
	@TableId(type = IdType.UUID)
	private String id;

	/** 姓名 */
	private String name;

	/** 性别 */
	private String sex;

	/** 民族 */
	private String nation;

	/** 出生日期 */
	private String birthday;

	/** 省份证 */
	private String cid;

	/** 手机号 */
	private String phone;

	/** 电子邮箱 */
	private String email;
	
	/** 职务*/
	private String job;

	/** 地址 */
	private String address;

	/** 创建时间 */
	@TableField(value = "CREATE_TIME")
	private String createTime;

	/** 创建人 */
	@TableField(value = "CREATE_USER")
	private String createUser;

	/** 更新时间 */
	@TableField(value = "UPDATE_TIME")
	private String updateTime;

	/** 更新人 */
	@TableField(value = "UPDATE_USER")
	private String updateUser;

	/** 删除标示 */
	private Double deleteflag;

	/** 所在部门编号 */
	@TableField(value = "ORGZ_ID")
	private String orgzId;

	/** 备注 */
	private String remark;

	/** 头像Url */
	@TableField(value = "HEAD_URL")
	private String headUrl;
	
	
	
	public String getHeadUrl() {
		return headUrl;
	}

	public void setHeadUrl(String headUrl) {
		this.headUrl = headUrl;
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSex() {
		return this.sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getNation() {
		return this.nation;
	}

	public void setNation(String nation) {
		this.nation = nation;
	}

	public String getBirthday() {
		return this.birthday;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

	public String getCid() {
		return this.cid;
	}

	public void setCid(String cid) {
		this.cid = cid;
	}

	public String getPhone() {
		return this.phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public String getCreateUser() {
		return this.createUser;
	}

	public void setCreateUser(String createUser) {
		this.createUser = createUser;
	}

	public String getUpdateTime() {
		return this.updateTime;
	}

	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}

	public String getUpdateUser() {
		return this.updateUser;
	}

	public void setUpdateUser(String updateUser) {
		this.updateUser = updateUser;
	}

	public Double getDeleteflag() {
		return this.deleteflag;
	}

	public void setDeleteflag(Double deleteflag) {
		this.deleteflag = deleteflag;
	}

	public String getOrgzId() {
		return this.orgzId;
	}

	public void setOrgzId(String orgzId) {
		this.orgzId = orgzId;
	}

	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getJob() {
		return job;
	}

	public void setJob(String job) {
		this.job = job;
	}
	
}
