package com.crfeb.tbmpt.commons.filters;

import java.io.IOException;
import java.util.Set;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.CollectionUtils;
import org.apache.shiro.web.filter.authz.AuthorizationFilter;
import org.apache.shiro.web.util.WebUtils;

import com.crfeb.tbmpt.commons.result.Result;
import com.crfeb.tbmpt.commons.utils.StringUtils;
import com.crfeb.tbmpt.commons.utils.WebUtil;

/**
 * 
 * 1.自定义角色鉴权过滤器(满足其中一个角色则认证通过) 2.扩展异步请求认证提示功能;
 * 
 * @author
 * 
 */
public class RoleAuthorizationFilter extends AuthorizationFilter {

	protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws IOException {

		HttpServletRequest httpRequest = (HttpServletRequest) request;
		HttpServletResponse httpResponse = (HttpServletResponse) response;

		Subject subject = getSubject(request, response);

		if (subject.getPrincipal() == null) {
			if (WebUtil.isAjax(httpRequest)) {
				Result re = new Result();
				re.setSuccess(false);
				re.setMsg("您尚未登录或登录时间过长,请重新登录!");
				try {
					WebUtil.response(httpResponse,re);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			} else {
				saveRequestAndRedirectToLogin(request, response);
			}
		} else {
			if (WebUtil.isAjax(httpRequest)) {
				Result re = new Result();
				re.setSuccess(false);
				re.setMsg("您没有足够的权限执行该操作!");
				try {
					WebUtil.response(httpResponse,re);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} else {
				String unauthorizedUrl = getUnauthorizedUrl();
				if (StringUtils.hasText(unauthorizedUrl)) {
					WebUtils.issueRedirect(request, response, unauthorizedUrl);
				} else {
					WebUtils.toHttp(response).sendError(401);
					
				}
			}
		}
		return false;
	}

	public boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue)
			throws IOException {

		Subject subject = getSubject(request, response);
		String[] rolesArray = (String[]) mappedValue;

		if (rolesArray == null || rolesArray.length == 0) {
			// no roles specified, so nothing to check - allow access.
			return true;
		}

		Set<String> roles = CollectionUtils.asSet(rolesArray);
		for (String role : roles) {
			if (subject.hasRole(role)) {
				return true;
			}
		}
		return false;
	}

}