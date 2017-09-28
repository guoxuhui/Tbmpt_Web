package com.crfeb.tbmpt.dgjj.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.crfeb.tbmpt.commons.base.BaseController;

/**
 * 掘进报表控制类
 * @author wl_zjh
 *
 */
@Controller
@RequestMapping("/dgjj/jjreport")
public class DgjjJjreport  extends BaseController{
	@Autowired  
	private  HttpServletRequest request; 
	
	/**
	 * 方法说明：盾构掘进管理报表
	 * @return
	 * @author:wl_zjh
	 * @Time: 2016年12月28日 下午3:07:08
	 */
	@RequestMapping(value = "", method = RequestMethod.GET)
    public String gpztclsb() {
    	StringBuffer url = new StringBuffer();
    	url.append("report/reportJsp/preview.jsp?");
    	//获取查询参数url
    	url.append("rpx=/report/reportFiles/dgjjreport.rpx&rpxHome=&dfxHome=&arg=/report/reportFiles/dgjjreport_arg.rpx&orgID=");
    	//获取用户部门信息
    	url.append(getCurrentUser().getOrgzId());
        return "redirect:/"+url.toString();
    }
}