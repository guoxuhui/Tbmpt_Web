package com.crfeb.tbmpt.gczl.report.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.crfeb.tbmpt.commons.base.BaseController;

/**
 * @description：报表跳转管理
 * @author：smxg
 * @date：2016-11-08 11:12
 */
@Controller
@RequestMapping("/gczl/report")
public class GczlReportAction extends BaseController{

	@Autowired  
	private  HttpServletRequest request;  
	/**
	 * http://127.0.0.1:8080/tbmpt/report/reportJsp/preview.jsp
		?rpx=/report/reportFiles/dmcjjcrb.rpx&rpxHome=&dfxHome=&arg=/report/reportFiles/dmcjjcrb_arg.rpx&orgID=
	 * @return
	 */
    @RequestMapping(value = "/gczlDay", method = RequestMethod.GET)
    public String gczlDay() {
    	StringBuffer url = new StringBuffer();
    	url.append("report/reportJsp/preview.jsp?");
    	//获取查询参数url
    	url.append("rpx=/report/reportFiles/gczlDay.rpx&rpxHome=&dfxHome=&arg=/report/reportFiles/gczlDay_arg.rpx&orgID=");
    	//获取用户部门信息
    	url.append(findOrgzId());
        return "redirect:/"+url.toString();
    }
   
}