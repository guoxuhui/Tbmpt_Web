package com.crfeb.tbmpt.zsjk.report.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.crfeb.tbmpt.commons.base.BaseController;
import com.crfeb.tbmpt.zsjk.report.model.ZsJkReportPcqxsj;
import com.crfeb.tbmpt.zsjk.report.model.dto.ZsJkReportPcqxsjDto;
import com.crfeb.tbmpt.zsjk.report.service.ZsJkReportPcqxsjService;

/**
 * <p>掘进姿态:偏差曲线数据 Controlle</p>
 * <p>系统：展示界面接口</p>
 * <p>日期：2017-03-09</p>
 * @version 1.0
 * @author wl_wpg
 */
@Controller
@RequestMapping(value="/zsjk/report/zsJkReportPcqxsj")
public class ZsJkReportPcqxsjController extends BaseController {

	@Autowired
	private ZsJkReportPcqxsjService zsJkReportPcqxsjService ;
	
	@RequestMapping(value = "", method = RequestMethod.GET)
    public String manager() {
        return "zsjk/report/zsJkReportPcqxsj";
    }
	
	/**
	 * 偏差曲线数据
	 * @param xmId 项目id
	 * @param xlId 线路Id
	 * @param dgjId 盾构机Id
	 * @param kshs 开始环数
	 * @param jshs 结束环数
	 * @return 结果集合
	 */
    @RequestMapping(value = "/dataGrid", method = RequestMethod.POST)
    @ResponseBody
    public Object dataGrid(ZsJkReportPcqxsjDto dto)
    {
    	List<ZsJkReportPcqxsj> resultData = new ArrayList<ZsJkReportPcqxsj>();
    	
        try {
        	if(null!=dto && dto.equals(null)){
    		    resultData = zsJkReportPcqxsjService.inquirePcqxsjList(dto.getDgjId(), dto.getXlId(), dto.getDgjId(),dto.getKshs(),dto.getJshs());            
    	    }
        } catch (Exception e) {
           e.printStackTrace();
        }
        return resultData;
    }
}
