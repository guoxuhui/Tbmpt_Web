/**
 *
 */
package com.crfeb.tbmpt.commons.shiro;

import java.io.Serializable;
import java.util.List;

import com.crfeb.tbmpt.sys.model.Orgz;
import com.crfeb.tbmpt.sys.model.User;

/**
 * @description：自定义Authentication对象，使得Subject除了携带用户的登录名外还可以携带更多信息
 * @author：smxg
 * @date：2016-10-10 11:12
 */
public class ShiroUser implements Serializable {

    private static final long serialVersionUID = -1373760761780840081L;
    public String id;
    public String loginName;
	public String name;
    public List<String> roleList;
    
    public String orgzId;
	public String orgzName;
    public int orgzType;
    
    public User user;
    public Orgz orgz;
    
    public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

    public ShiroUser(String id, String loginName, String name, List<String> roleList) {
        this.id = id;
        this.loginName = loginName;
        this.name = name;
        this.roleList = roleList;
    }

    public String getName() {
        return name;
    }

    public String getId() {
        return id;
    }
    
    public User getUser() {
		return user;
	}

	public Orgz getOrgz() {
		return orgz;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public void setOrgz(Orgz orgz) {
		this.orgz = orgz;
	}
	public String getOrgzId() {
		return orgzId;
	}

	public void setOrgzId(String orgzId) {
		this.orgzId = orgzId;
	}

	public String getOrgzName() {
		return orgzName;
	}

	public void setOrgzName(String orgzName) {
		this.orgzName = orgzName;
	}

	public int getOrgzType() {
		return orgzType;
	}

	public void setOrgzType(int orgzType) {
		this.orgzType = orgzType;
	}

	/**
     * 本函数输出将作为默认的<shiro:principal/>输出.
     */
    
    public String toString() {
        return loginName;
    }
}