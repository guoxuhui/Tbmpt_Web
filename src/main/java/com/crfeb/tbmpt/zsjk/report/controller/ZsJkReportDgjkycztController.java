package com.crfeb.tbmpt.zsjk.report.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.crfeb.tbmpt.commons.base.BaseController;
import com.crfeb.tbmpt.commons.utils.PageInfo;
import com.crfeb.tbmpt.commons.utils.StringUtils;
import com.crfeb.tbmpt.zsjk.report.model.dto.ZsJkReportDgjkycztDto;
import com.crfeb.tbmpt.zsjk.report.service.ZsJkReportDgjkycztService;

/**
 * <p>盾构监控异常状态  controlle</p>
 * <p>系统：展示界面接口</p>
 * <p>日期：2017-03-07</p>
 * @version 1.0
 * @author wl_wpg
 */
@Controller
@RequestMapping(value="/zsjk/report/zsJkReportDgjkyczt")
public class ZsJkReportDgjkycztController extends BaseController{

	@Autowired
	private ZsJkReportDgjkycztService zsJkReportDgjkycztService;
	
	@RequestMapping(value = "", method = RequestMethod.GET)
    public String manager() {
        return "zsjk/report/zsJkReportDgjkyczt";
    }
	
	/**
     * 获取 沉降最大监测点分析集合
     * @param dto 工具类 
     * @return 返回查询结果集合
     */
    @RequestMapping(value = "/cjzdjcdfxList", method = RequestMethod.POST)
    @ResponseBody
    public Object dataGrid(ZsJkReportDgjkycztDto dto,List<String> typeList,Integer page, Integer rows){
    	
    	 PageInfo pageInfo = new PageInfo(page, rows);
         Map<String, Object> condition = new HashMap<String, Object>();
         pageInfo.setCondition(condition);
         //设置查询条件
       		if(StringUtils.isNotBlank(dto.getXmId())){
       			condition.put("xmId", dto.getXmId());
       		}
       		if(StringUtils.isNotBlank(dto.getXlId())){
       			condition.put("xlId", dto.getXlId());
       		}
       		if(StringUtils.isNotBlank(dto.getDgjId())){
       			condition.put("dgjId", dto.getDgjId());
       		}
       		if(StringUtils.isNotBlank(dto.getKshs())){
       			condition.put("kshs", dto.getKshs());
       		}
       		if(StringUtils.isNotBlank(dto.getJshs())){
       			condition.put("jshs",dto.getJshs());
       		}
       		List<String> List  = new ArrayList<String>();
       		if(null != typeList && typeList.size() !=0 ){
       			List =typeList;
       		}
       		
         try {
             zsJkReportDgjkycztService.inquireDgjkycztDataPage(pageInfo,List);
        	 
         } catch (Exception e) {
            e.printStackTrace();
         }
         return pageInfo;
    }
}
