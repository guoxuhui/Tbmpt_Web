package com.crfeb.tbmpt.zsjk.report.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.crfeb.tbmpt.commons.base.BaseController;
import com.crfeb.tbmpt.zsjk.report.model.ZsJkReportCjzdjcdfx;
import com.crfeb.tbmpt.zsjk.report.model.dto.ZsJkReportCjzdjcdfxDto;
import com.crfeb.tbmpt.zsjk.report.service.ZsJkReportCjzdjcdfxService;

/**
 * <p>沉降最大监测点分析 Controlle</p>
 * <p>系统：展示界面接口</p>
 * <p>日期：2017-03-07</p>
 * @version 1.0
 * @author wl_wpg
 */
@Controller
@RequestMapping(value="/zsjk/report/zsJkReportCjzdjcdfx")
public class ZsJkReportCjzdjcdfxController extends BaseController {

	@Autowired
	private ZsJkReportCjzdjcdfxService zsJkReportCjzdjcdfxService;
	
	@RequestMapping(value = "", method = RequestMethod.GET)
    public String manager() {
        return "zsjk/report/zsJkReportCjzdjcdfx";
    }
	
	/**
     * 获取 沉降最大监测点分析集合
     * @param dto 工具类 
     * @return 返回查询结果集合
     */
    @RequestMapping(value = "/cjzdjcdfxList", method = RequestMethod.POST)
    @ResponseBody
    public Object cjzdjcdfxList(ZsJkReportCjzdjcdfxDto dto){
    	
    	List<ZsJkReportCjzdjcdfx> cjzdjcdfxList = new ArrayList<ZsJkReportCjzdjcdfx>();
        try {
        	if(null!= dto){
          	    cjzdjcdfxList = zsJkReportCjzdjcdfxService.inquireCjzdjcdfxList(dto);
            }
        } catch (Exception e) {
           e.printStackTrace();
        }
        return cjzdjcdfxList;
    }
}
