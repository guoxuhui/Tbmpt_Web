package com.crfeb.tbmpt.monitor.controller;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.crfeb.tbmpt.commons.base.BaseController;
import com.crfeb.tbmpt.commons.result.Request;
import com.crfeb.tbmpt.commons.result.Result;
import com.crfeb.tbmpt.commons.scan.SpringUtils;
import com.crfeb.tbmpt.commons.utils.PageInfo;
import com.crfeb.tbmpt.commons.utils.StringUtils;
import com.crfeb.tbmpt.monitor.model.GeoLineModel;
import com.crfeb.tbmpt.monitor.model.dto.XmbXmXlXxDto;
import com.crfeb.tbmpt.monitor.model.dto.XmbXmXxDto;
import com.crfeb.tbmpt.monitor.service.IProGeoLinesService;
import com.crfeb.tbmpt.project.model.ProProjectinfo;
import com.crfeb.tbmpt.project.model.ProRProjectSection;
import com.crfeb.tbmpt.project.model.ProRSectionLine;
import com.crfeb.tbmpt.project.model.vo.ProjectinfoVo;
import com.crfeb.tbmpt.project.service.IProProjectinfoService;
import com.crfeb.tbmpt.project.service.IProRProjectSectionService;
import com.crfeb.tbmpt.project.service.IProRSectionLineService;
import com.crfeb.tbmpt.sys.model.SysEmployee;
import com.crfeb.tbmpt.sys.service.ILogService;
import com.crfeb.tbmpt.sys.service.ISysEmployeeService;
import com.crfeb.tbmpt.tbmmg.model.ProTbminfo;
import com.crfeb.tbmpt.tbmmg.service.IProTbminfoService;
import com.crfeb.tbmpt.zsjk.plc.model.dto.ProPlcBzRealDto;
import com.crfeb.tbmpt.zsjk.plc.service.IProPlcRealService;

/**
 * @description：远程监控界面接口
 * @author：smxg
 * @date：2017-03-10 11:12
 */
@Controller
@RequestMapping("/webmui")
public class WebmuiController extends BaseController {

    @Autowired
    private IProProjectinfoService proProjectinfoService;
    @Autowired
    private IProRProjectSectionService proRProjectSectionService;
    @Autowired
    private IProRSectionLineService proRSectionLineService;
    @Autowired
    private IProTbminfoService proTbminfoService;
    @Autowired
    private IProPlcRealService proPlcRealService;
    @Autowired
    private ISysEmployeeService sysEmployeeService;

    /**
     * 获取项目线路信息
     * @return
     */
	@RequestMapping(value="/getProjectLineData")
	@ResponseBody
	public Object getProjectLineData(String xlId){
		Result result = new Result();
		if(StringUtils.isBlank(xlId)){
			result.setSuccess(false);
			result.setMsg("线路不能为空");
			return result;
		}
		
		ProRSectionLine line = proRSectionLineService.selectById(xlId);
		if(line == null){
			result.setSuccess(false);
			result.setMsg("线路不存在");
			return result;
		}
		ProRProjectSection section = proRProjectSectionService.selectById(line.getSectionId());
		if(section == null){
			result.setSuccess(false);
			result.setMsg("区间不存在");
			return result;
		}
		ProProjectinfo  pro = proProjectinfoService.selectById(section.getProId());
		if(pro == null){
			result.setSuccess(false);
			result.setMsg("项目不存在");
			return result;
		}
		ProTbminfo tbm = proTbminfoService.selectById(line.getTbmId());
		if(tbm == null){
			result.setSuccess(false);
			result.setMsg("盾构机不存在");
			return result;
		}
		SysEmployee emp = sysEmployeeService.selectById(pro.getEmpId());
		if(emp == null){
			result.setSuccess(false);
			result.setMsg("负责人不存在");
			return result;
		}
		XmbXmXxDto dto = new XmbXmXxDto();
		dto.setBjsl("");
		dto.setCsdq(pro.getProjectaddress());
		dto.setDgjId(line.getTbmId());
		dto.setDgjMc(tbm.getTbmName());
		dto.setDgjczs(line.getDgChauffeur());
		
		if(line.getLineStatus() == 2){
			dto.setDgjzt("未开工");	
		}else if(line.getLineStatus() == 1){
			dto.setDgjzt("已开工");
		}else{
			dto.setDgjzt("已完工");
		}
		
		dto.setDqdz(section.getDcqk());
		dto.setDqhs("");
		dto.setDtxl(pro.getProName());
		dto.setFzr(emp.getName());
		dto.setId(pro.getId());
		dto.setKgrq(line.getSjsfrq());
		dto.setQjzc(String.valueOf(line.getTunnellength()));
		dto.setSgbd(pro.getTender());
		dto.setXmCz("");
		dto.setXmMc(pro.getProName());
		dto.setXmWccz("");
		dto.setYjwcrq(pro.getEstimateenddate());
		dto.setZbjg("");
		dto.setZgqds(pro.getEstimateenddate());
		dto.setZhs(String.valueOf(line.getRingCount()));
		
		ArrayList<XmbXmXlXxDto> xllist = new ArrayList<XmbXmXlXxDto>();
		XmbXmXlXxDto dtoxl = new XmbXmXlXxDto();
		dtoxl.setId(line.getId());
		dtoxl.setXlId(line.getId());
		dtoxl.setXlMc(section.getSectionName()+line.getLineName());
		dtoxl.setXmId(pro.getId());
		
		xllist.add(dtoxl);
		dto.setXlList(xllist);
        result.setSuccess(true);
        result.setObj(dto);
        return JSON.toJSONString(result,SerializerFeature.WriteMapNullValue,SerializerFeature.WriteNullStringAsEmpty);
	}
	
	/**
	 * 获取机器全部实时数据
	 * @param parameter tbmId盾构机ID
	 * @return
	 */
	@RequestMapping(value="/getJqjmData")
	@ResponseBody
	public String getJqjmData(String tbmId){
		Result rs = new Result();
		try {
			List<ProPlcBzRealDto> list = proPlcRealService.getSsJqsj(tbmId);
			rs.setObj(list);
			rs.setSuccess(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return JSON.toJSONString(rs,SerializerFeature.WriteMapNullValue,SerializerFeature.WriteNullStringAsEmpty);
	}
}
