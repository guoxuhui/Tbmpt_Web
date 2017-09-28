package com.crfeb.tbmpt.gpztcl.base.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.crfeb.tbmpt.commons.base.BaseController;
/**
 * 管片姿态测量报表控制类
 * @author yangyongjiang
 *
 */
@Controller
@RequestMapping("/gpztcl/base/gpztclreport")
public class Gpztclreport  extends BaseController{
	@Autowired  
	private  HttpServletRequest request; 
	
	/**
	 * 方法说明：管片姿态测量上报报表
	 * @return
	 * @author:YangYj
	 * @Time: 2016年12月28日 下午3:07:08
	 */
	@RequestMapping(value = "", method = RequestMethod.GET)
    public String gpztclsb() {
    	StringBuffer url = new StringBuffer();
    	url.append("report/reportJsp/preview.jsp?");
    	//获取查询参数url
    	url.append("rpx=/report/reportFiles/gpztclsb.rpx&rpxHome=&dfxHome=&arg=/report/reportFiles/gpztclsb_arg.rpx&orgID=");
    	//获取用户部门信息
    	url.append(findOrgzId());
        return "redirect:/"+url.toString();
    }

}
