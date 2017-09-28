package com.crfeb.tbmpt.sys.model;

import com.baomidou.mybatisplus.annotations.IdType;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;

/**
 *
 * 角色
 *
 */
@TableName("sys_role")
public class Role {

	/** 主键id */
	@TableId(type = IdType.UUID)
	private String id;

	/** 角色名 */
	private String name;

	/** 排序号 */
	private Integer seq = 0;

	/** 简介 */
	private String description;
	
	/** 部门*/
	@TableField(value = "ORGZ_ID")
	private String orgzId;

	/** 状态 */
	private Integer status;
	
	@TableField(value = "CREATE_TIME")
	private String createTime;
	@TableField(value = "CREATE_USER")
	private String createUser;
	@TableField(value = "UPDATE_TIME")
	private String updateTime;
	@TableField(value = "UPDATE_USER")
	private String updateUser;
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

	public Integer getSeq() {
		return this.seq;
	}

	public void setSeq(Integer seq) {
		this.seq = seq;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getStatus() {
		return this.status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}
	
	public String getOrgzId() {
		return orgzId;
	}
	public void setOrgzId(String orgzId) {
		this.orgzId = orgzId;
	}
	
}
