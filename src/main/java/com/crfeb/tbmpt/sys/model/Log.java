package com.crfeb.tbmpt.sys.model;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotations.IdType;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;

/**
 *
 * 系统日志
 *
 */
@TableName("sys_log")
public class Log implements Serializable {

	@TableField(exist = false)
	private static final long serialVersionUID = 1L;

	/** 主键id */
	@TableId(type = IdType.UUID)
	private String id;
	
	/** 登陆名 */
	@TableField(value = "login_id")
	private String loginId;

	/** 登陆名 */
	@TableField(value = "login_name")
	private String loginName;

	/** 角色名 */
	@TableField(value = "role_name")
	private String roleName;
	
	/** 操作系统 */
	@TableField(value = "sys_name")
	private String sysName;
	
	/** 操作模块 */
	@TableField(value = "module_name")
	private String moduleName;
	
	/** 操作类型 */
	@TableField(value = "opt_name")
	private String optName;
	
	/** 操作url*/
	@TableField(value = "opt_url")
	private String optUrl;

	/** 内容 */
	@TableField(value = "opt_content")
	private String optContent;
	
	@TableField(value = "opt_status")
	private String optStatus;
	
	public String getOptStatus() {
		return optStatus;
	}

	public void setOptStatus(String optStatus) {
		this.optStatus = optStatus;
	}

	/** 客户端ip */
	@TableField(value = "client_ip")
	private String clientIp;
	
	/** 执行时间 */
	@TableField(value = "opt_time")
	private String optTime;

	/** 创建时间 */
	@TableField(value = "create_time")
	private String createTime;


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

	public String getRoleName() {
		return this.roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public String getOptContent() {
		return this.optContent;
	}

	public void setOptContent(String optContent) {
		this.optContent = optContent;
	}

	public String getClientIp() {
		return this.clientIp;
	}

	public void setClientIp(String clientIp) {
		this.clientIp = clientIp;
	}

	public String getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public String getSysName() {
		return sysName;
	}

	public void setSysName(String sysName) {
		this.sysName = sysName;
	}

	public String getModuleName() {
		return moduleName;
	}

	public void setModuleName(String moduleName) {
		this.moduleName = moduleName;
	}

	public String getOptName() {
		return optName;
	}

	public void setOptName(String optName) {
		this.optName = optName;
	}

	public String getOptUrl() {
		return optUrl;
	}

	public void setOptUrl(String optUrl) {
		this.optUrl = optUrl;
	}

	public String getOptTime() {
		return optTime;
	}

	public void setOptTime(String optTime) {
		this.optTime = optTime;
	}

	public String getLoginId() {
		return loginId;
	}

	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}

}
