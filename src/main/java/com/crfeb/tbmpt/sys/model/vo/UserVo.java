package com.crfeb.tbmpt.sys.model.vo;

import java.io.Serializable;
import java.util.List;

import com.crfeb.tbmpt.sys.model.Role;

/**
 * @description：UserVo
 * @author：smxg
 * @date：2016-10-10 11:12
 */
public class UserVo implements Serializable {
    private String id;

    private String loginName;

    private String name;

    private String password;

    private Integer userType;

    private Integer status;

    private String orgzId;
    
    private String empId;

    public String getEmpId() {
		return empId;
	}

	public void setEmpId(String empId) {
		this.empId = empId;
	}

    private String createTime;

    private List<Role> rolesList;

    private String orgzName;

    private String roleIds;

    private static final long serialVersionUID = 1L;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName == null ? null : loginName.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public Integer getUserType() {
        return userType;
    }

    public void setUserType(Integer userType) {
        this.userType = userType;
    }

    public Integer getStatus() {
        return status;
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

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public List<Role> getRolesList() {
        return rolesList;
    }

    public void setRolesList(List<Role> rolesList) {
        this.rolesList = rolesList;
    }

    public String getOrgzName() {
        return orgzName;
    }

    public void setOrgzName(String orgzName) {
        this.orgzName = orgzName;
    }

    public String getRoleIds() {
        return roleIds;
    }

    public void setRoleIds(String roleIds) {
        this.roleIds = roleIds;
    }

}