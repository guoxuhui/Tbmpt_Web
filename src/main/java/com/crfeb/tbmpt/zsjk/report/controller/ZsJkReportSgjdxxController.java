package com.crfeb.tbmpt.zsjk.report.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.crfeb.tbmpt.commons.base.BaseController;
import com.crfeb.tbmpt.zsjk.report.model.ZsJkReportSgjdxx;
import com.crfeb.tbmpt.zsjk.report.model.dto.ZsJkReportSgjdxxDto;
import com.crfeb.tbmpt.zsjk.report.service.ZsJkReportSgjdxxService;

/**
 * <p>施工进度信息 Controlle</p>
 * <p>系统：展示界面接口</p>
 * <p>日期：2017-03-08</p>
 * @version 1.0
 * @author wl_wpg
 */
@Controller
@RequestMapping(value="/zsjk/report/zsJkReportSgjdxx")
public class ZsJkReportSgjdxxController extends BaseController {

	@Autowired
	private ZsJkReportSgjdxxService zsJkReportSgjdxxService ;
	
	@RequestMapping(value = "", method = RequestMethod.GET)
    public String manager() {
        return "zsjk/report/zsJkReportSgjdxx";
    }
	
	@RequestMapping(value = "/sgjdxx", method = RequestMethod.POST)
    @ResponseBody
    public Object dataGrid(ZsJkReportSgjdxxDto dto)
    {
		ZsJkReportSgjdxx zsJkReportSgjdxx = new ZsJkReportSgjdxx(); 
    	try {
        	if(null!=dto && dto.equals(null)){
        		zsJkReportSgjdxx = zsJkReportSgjdxxService.inquireSgjdxxList(dto.getDgjId(), dto.getXlId(), dto.getDgjId());            
    	    }
        } catch (Exception e) {
           e.printStackTrace();
        }
        return zsJkReportSgjdxx;
    }
	
}
