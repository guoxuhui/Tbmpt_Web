package com.crfeb.tbmpt.commons.shiro;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import com.crfeb.tbmpt.commons.utils.StringUtils;
import com.crfeb.tbmpt.sys.model.Orgz;
import com.crfeb.tbmpt.sys.model.Res;
import com.crfeb.tbmpt.sys.model.User;
import com.crfeb.tbmpt.sys.service.IOrgzService;
import com.crfeb.tbmpt.sys.service.IResService;
import com.crfeb.tbmpt.sys.service.IRoleService;
import com.crfeb.tbmpt.sys.service.IUserService;

/**
 * @description：shiro权限认证
 * @author：smxg
 * @date：2016-10-10 11:12
 */
public class ShiroDbRealm extends AuthorizingRealm {
    private static final Logger LOGGER = LogManager.getLogger(ShiroDbRealm.class);

    @Autowired
    private IUserService userService;
    @Autowired
    private IRoleService roleService;
    @Autowired
    private IOrgzService orgzService;
    @Autowired
    private IResService resService;

    /**
     * Shiro登录认证(原理：用户提交 用户名和密码  --- shiro 封装令牌 ---- realm 通过用户名将密码查询返回 ---- shiro 自动去比较查询出密码和用户输入密码是否一致---- 进行登陆控制 )
     */
    
    protected AuthenticationInfo doGetAuthenticationInfo(
            AuthenticationToken authcToken) throws AuthenticationException {
        LOGGER.info("Shiro开始登录认证");
        UsernamePasswordToken token = (UsernamePasswordToken) authcToken;
        User user = userService.selectByLoginName(token.getUsername());
        // 账号不存在
        if (user == null) {
            return null;
        }
        // 账号未启用
        if (user.getStatus() == 1) {
            return null;
        }
        List<String> roleList = roleService.selectRoleIdListByUserId(user.getId());
        ShiroUser shiroUser = new ShiroUser(user.getId(), user.getLoginName(), user.getName(), roleList);
        shiroUser.setUser(user);
        Orgz orgz = orgzService.selectById(user.getOrgzId());
        if(orgz != null){
        	shiroUser.setOrgz(orgz);
        	user.setOrgzName(orgz.getName());
        	
        	shiroUser.setOrgzId(orgz.getId());
        	shiroUser.setOrgzName(orgz.getName());
        	shiroUser.setOrgzType(orgz.getType());
        }
        
        // 认证缓存信息
        return new SimpleAuthenticationInfo(shiroUser, user.getPassword().toCharArray(), getName());

    }

    /**
     * Shiro权限认证
     */
    
    protected AuthorizationInfo doGetAuthorizationInfo(
            PrincipalCollection principals) {

        ShiroUser shiroUser = (ShiroUser) principals.getPrimaryPrincipal();
        List<String> roleList = shiroUser.roleList;

        Set<String> urlSet = new HashSet<String>();
        if("admin".equals(shiroUser.getLoginName())){
        	List<Res> roleResourceList = resService.selectAll();
            if (roleResourceList != null) {
                for (Res res : roleResourceList) {
                    if (StringUtils.isNotBlank(res.getUrl())) {
                        urlSet.add(res.getUrl());
                    }
                }
            }
        }else{
            for (String roleId : roleList) {
                List<Map<String, String>> roleResourceList = roleService.selectRoleResourceListByRoleId(roleId);
                if (roleResourceList != null) {
                    for (Map<String, String> map : roleResourceList) {
                        if (StringUtils.isNotBlank(map.get("URL"))) {
                            urlSet.add(map.get("URL"));
                        }
                    }
                }
            }
        }
        
        
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        info.addStringPermissions(urlSet);
        return info;
    }
}
