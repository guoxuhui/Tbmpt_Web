package com.crfeb.tbmpt.zsjk.report.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.crfeb.tbmpt.commons.base.BaseController;
import com.crfeb.tbmpt.zsjk.report.model.ZsJkReportDgjdxxtsssj;
import com.crfeb.tbmpt.zsjk.report.model.dto.ZsJkReportSgjdxxDto;
import com.crfeb.tbmpt.zsjk.report.service.ZsJkReportDgjdxxtsssjService;

/**
 * <p>掘进姿态:盾构机导向系统实时数据 Controlle</p>
 * <p>系统：展示界面接口</p>
 * <p>日期：2017-03-09</p>
 * @version 1.0
 * @author wl_wpg
 */
@Controller
@RequestMapping(value="/zsjk/report/zsJkReportDgjdxxtsssj")
public class ZsJkReportDgjdxxtsssjController extends BaseController {

	@Autowired
	private ZsJkReportDgjdxxtsssjService zsJkReportDgjdxxtsssjService ;
	
	@RequestMapping(value = "", method = RequestMethod.GET)
    public String manager() {
        return "zsjk/report/zsJkReportDgjdxxtsssj";
    }
	
	@RequestMapping(value = "/sgjdxx", method = RequestMethod.POST)
    @ResponseBody
    public Object dataGrid(ZsJkReportSgjdxxDto dto)
    {
		ZsJkReportDgjdxxtsssj zsJkReportDgjdxxtsssj = new ZsJkReportDgjdxxtsssj(); 
    	try {
        	if(null!=dto && dto.equals(null)){
        		zsJkReportDgjdxxtsssj = zsJkReportDgjdxxtsssjService.inquireDgjdxxtsssjList(dto.getDgjId(), dto.getXlId(), dto.getDgjId());            
    	    }
        } catch (Exception e) {
           e.printStackTrace();
        }
        return zsJkReportDgjdxxtsssj;
    }
	
}
