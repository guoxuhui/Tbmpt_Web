package com.crfeb.tbmpt.sys.model;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotations.IdType;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;

/**
 *
 * 用户
 *
 */
@TableName("sys_user")
public class User implements Serializable {

	@TableField(exist = false)
	private static final long serialVersionUID = 1L;

	/** 主键id */
	@TableId(type = IdType.UUID)
	private String id;

	/** 登陆名 */
	@TableField(value = "login_name")
	private String loginName;

	/** 用户名 */
	private String name;

	/** 密码 */
	private String password;

	/** 用户类别 */
	@TableField(value = "user_type")
	private Integer userType;

	/** 用户状态 */
	private Integer status;

	/** 所属机构 */
	@TableField(value = "orgz_id")
	private String orgzId;
	
	@TableField(exist = false)
	private String orgzName;

	public String getOrgzName() {
		return orgzName;
	}

	public void setOrgzName(String orgzName) {
		this.orgzName = orgzName;
	}

	@TableField(value = "emp_id")
	private String empId;
	
	/** 创建时间 */
	@TableField(value = "create_time")
	private String createTime;
	
	@TableField(value = "cid")
	private String cid;


	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getLoginName() {
		return this.loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Integer getUserType() {
		return this.userType;
	}

	public void setUserType(Integer userType) {
		this.userType = userType;
	}

	public Integer getStatus() {
		return this.status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getOrgzId() {
		return this.orgzId;
	}

	public void setOrgzId(String orgzId) {
		this.orgzId = orgzId;
	}

	public String getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public String getEmpId() {
		return empId;
	}

	public void setEmpId(String empId) {
		this.empId = empId;
	}

	public String getCid() {
		return cid;
	}

	public void setCid(String cid) {
		this.cid = cid;
	}

}
