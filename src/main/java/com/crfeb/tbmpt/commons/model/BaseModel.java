package com.crfeb.tbmpt.commons.model;

import com.baomidou.mybatisplus.annotations.TableField;

/**
 * 基础实体类
 * @author smxg
 *
 */
public class BaseModel {

	/**
	 * 人员ID
	 */
	@TableField(value = "create_user")
	private String createUser;
	/**
	 * 人员姓名
	 */
	@TableField(value = "create_username")
	private String createUsername;
	/**
	 * 组织机构ID
	 */
	@TableField(value = "create_orgz")
	private String createOrgz;
	/**
	 * 组织机构名称
	 */
	@TableField(value = "create_orgzname")
	private String createOrgzname;
	/**
	 * 创建时间
	 */
	@TableField(value = "create_time")
	private String createTime;
	
	@TableField(value = "update_user")
	private String updateUser;
	@TableField(value = "update_username")
	private String updateUsername;
	@TableField(value = "update_orgz")
	private String updateOrgz;
	@TableField(value = "update_orgzname")
	private String updateOrgzname;
	@TableField(value = "update_time")
	private String updateTime;
	
	
	public String getCreateUser() {
		return createUser;
	}
	public void setCreateUser(String createUser) {
		this.createUser = createUser;
	}
	public String getCreateUsername() {
		return createUsername;
	}
	public void setCreateUsername(String createUsername) {
		this.createUsername = createUsername;
	}
	public String getCreateOrgz() {
		return createOrgz;
	}
	public void setCreateOrgz(String createOrgz) {
		this.createOrgz = createOrgz;
	}
	public String getCreateOrgzname() {
		return createOrgzname;
	}
	public void setCreateOrgzname(String createOrgzname) {
		this.createOrgzname = createOrgzname;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	public String getUpdateUser() {
		return updateUser;
	}
	public void setUpdateUser(String updateUser) {
		this.updateUser = updateUser;
	}
	public String getUpdateUsername() {
		return updateUsername;
	}
	public void setUpdateUsername(String updateUsername) {
		this.updateUsername = updateUsername;
	}
	public String getUpdateOrgz() {
		return updateOrgz;
	}
	public void setUpdateOrgz(String updateOrgz) {
		this.updateOrgz = updateOrgz;
	}
	public String getUpdateOrgzname() {
		return updateOrgzname;
	}
	public void setUpdateOrgzname(String updateOrgzname) {
		this.updateOrgzname = updateOrgzname;
	}
	public String getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}
	
	
	
}
