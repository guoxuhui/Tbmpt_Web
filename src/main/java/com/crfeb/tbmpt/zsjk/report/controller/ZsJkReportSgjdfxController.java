package com.crfeb.tbmpt.zsjk.report.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.crfeb.tbmpt.commons.base.BaseController;
import com.crfeb.tbmpt.zsjk.report.model.ZsJkReportSgjdfx;
import com.crfeb.tbmpt.zsjk.report.model.dto.ZsJkReportSgjdfxDto;
import com.crfeb.tbmpt.zsjk.report.service.ZsJkReportSgjdfxService;

/**
 * <p>施工进度分析 Controlle</p>
 * <p>系统：展示界面接口</p>
 * <p>日期：2017-03-08</p>
 * @version 1.0
 * @author wl_wpg
 */
@Controller
@RequestMapping(value="/zsjk/report/zsJkReportSgjdfx")
public class ZsJkReportSgjdfxController extends BaseController {

	@Autowired
	private ZsJkReportSgjdfxService zsJkReportSgjdfxService ;
	
	@RequestMapping(value = "", method = RequestMethod.GET)
    public String manager() {
        return "zsjk/report/zsJkReportSgjdfx";
    }
	
	/**
	 * 施工进度分析
	 * @param xmId 项目id
	 * @param xlId 线路Id
	 * @param dgjId 盾构机Id
	 * @param ksrq 开始日期
	 * @param jsrq 结束日期
	 * @return 结果集合
	 */
    @RequestMapping(value = "/dataGrid", method = RequestMethod.POST)
    @ResponseBody
    public Object dataGrid(ZsJkReportSgjdfxDto dto)
    {
    	List<ZsJkReportSgjdfx> resultData = new ArrayList<ZsJkReportSgjdfx>();
    	
        try {
        	if(null!=dto && dto.equals(null)){
    		    resultData = zsJkReportSgjdfxService.inquireSgjdfxList(dto.getDgjId(), dto.getXlId(), dto.getDgjId(),dto.getKsrq(),dto.getJsrq());            
    	    }
        } catch (Exception e) {
           e.printStackTrace();
        }
        return resultData;
    }
}
