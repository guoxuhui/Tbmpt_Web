package com.crfeb.tbmpt.sgcj.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.crfeb.tbmpt.commons.base.BaseController;
import com.crfeb.tbmpt.commons.result.Result;
import com.crfeb.tbmpt.commons.utils.StringUtils;
import com.crfeb.tbmpt.commons.utils.XmlUtils;
import com.crfeb.tbmpt.dgjjdw.real.service.IDgjjdwPlcRealService;
import com.crfeb.tbmpt.project.service.IProProjectinfoService;
import com.crfeb.tbmpt.project.service.IProRProjectSectionService;
import com.crfeb.tbmpt.project.service.IProRSectionLineService;
import com.crfeb.tbmpt.sgcj.service.ISgcjService;
import com.crfeb.tbmpt.simple.model.Test;
import com.crfeb.tbmpt.simple.service.ITestService;
import com.crfeb.tbmpt.tbmmg.model.ProTbminfo;
import com.crfeb.tbmpt.tbmmg.service.IProTbminfoService;
import com.crfeb.tbmpt.zsjk.plc.model.dto.ProPlcBzRealDto;
import com.crfeb.tbmpt.zsjk.plc.service.IProPlcRealService;

/**
 * @author：smxg
 * @date：2016-10-10 11:12
 */
@Controller
@RequestMapping("/sgcj")
public class SgcjController extends BaseController {

    @Autowired
    private IProRSectionLineService proRSectionLineService;
    @Autowired
    private IProRProjectSectionService proRProjectSectionService;
    @Autowired
    private IProProjectinfoService proProjectinfoService;
    @Autowired
    private IProTbminfoService proTbminfoService;
    @Autowired
    private IDgjjdwPlcRealService dgjjdwPlcRealService;
    @Autowired
    private IProPlcRealService proPlcRealService;
    @Autowired
    private ISgcjService sgcjService;
    @RequestMapping("/getConfigByXmId")
    @ResponseBody
    public Object getConfigByXmId(String xmId,HttpServletResponse response) {
		String doc = sgcjService.getConfig(xmId);
		response.setContentType("text/xml; charset=UTF-8");
		return doc;
    }
    
    /**
     * 获取盾构机信息以及盾构机掘进位置信息
     * @return
     */
	@RequestMapping(value="/getTbmInfoList")
	@ResponseBody
	public Object getTbmInfoList(String ids){
		Result result = new Result();
		if(StringUtils.isEmpty(ids)){
	        result.setSuccess(false);
	        result.setMsg("盾构机ID不能为空");
	        return result;
		}
		JSONArray arrs = new JSONArray();
		String[] strs = ids.split(",");
		for(String id:strs){
			Map<String,Object> tbmMap = new HashMap<String,Object>();
			ProTbminfo tbm = proTbminfoService.selectById(id);
			List<ProPlcBzRealDto> pbzs = new ArrayList<ProPlcBzRealDto>();
			String dwIds = "TY_DXXT_0001,"
					+ "TY_ZGZT_0001,TY_ZGZT_0002,TY_ZGZT_0003,"
					+ "TY_TJXT_0001,TY_TJXT_0002,TY_TJXT_0003,TY_TJXT_0004,TY_TJXT_0005,TY_TJXT_0006";
			try {
				pbzs = proPlcRealService.getSsJqsjByDw(id, dwIds);
			} catch (Exception e) {
				e.printStackTrace();
			}
			Map<String,String> map = new HashMap<String,String>();
			for(ProPlcBzRealDto pto:pbzs){
				if("TY_DXXT_0001".equals(pto.getDwid())){
					map.put("jjhh", pto.getTagvalue());
				}
				
				if("TY_ZGZT_0001".equals(pto.getDwid())){
					map.put("jjms", pto.getTagvalue());
				}
				if("TY_ZGZT_0002".equals(pto.getDwid())){
					map.put("pzms", pto.getTagvalue());
				}
				if("TY_ZGZT_0003".equals(pto.getDwid())){
					map.put("tjms", pto.getTagvalue());
				}
				
				if("TY_TJXT_0001".equals(pto.getDwid())){
					map.put("tjyl", pto.getTagvalue());
				}
				if("TY_TJXT_0002".equals(pto.getDwid())){
					map.put("tjsd", pto.getTagvalue());
				}
				if("TY_TJXT_0003".equals(pto.getDwid())){
					map.put("ztl", pto.getTagvalue());
				}
				if("TY_TJXT_0004".equals(pto.getDwid())){
					map.put("dpzs", pto.getTagvalue());
				}
				if("TY_TJXT_0005".equals(pto.getDwid())){
					map.put("dpnj", pto.getTagvalue());
				}
				if("TY_TJXT_0006".equals(pto.getDwid())){
					map.put("dpyl", pto.getTagvalue());
				}
				tbmMap.put("plcTime", pto.getTagtime());
			}
			
			tbmMap.put("tbmInfo", tbm);
			tbmMap.put("plcInfo", map);
			
			arrs.add(tbmMap);
		}
		result.setSuccess(true);
		result.setObj(arrs);
		return JSON.toJSONString(result,SerializerFeature.WriteMapNullValue,SerializerFeature.WriteNullStringAsEmpty);
	}
    
	/**
	 * 根据盾构机编号获取当前施工环号以及时间信息
	 * @param parameter
	 * @return
	 */
    @RequestMapping("/getCurrHHByDgjId")
    @ResponseBody
	public Object getCurrHHByDgjId(String dgjId){
    	Map<String,Object> map = new HashMap<String,Object>();
    	map.put("hh", 20);
    	map.put("time", "2017-03-27 11:23:12");
    	return renderSuccess(map);
	}
	
	/**
	 * 根据盾构机编号获取当前机器简要（推进系统）数据
	 * @param parameter
	 * @return
	 */
    @RequestMapping("/getSimpleDataByDgjId")
    @ResponseBody
	public Object getSimpleDataByDgjId(String dgjId){
    	List<ProPlcBzRealDto> list = null;
    	List<ProPlcBzRealDto> nlist = new ArrayList<ProPlcBzRealDto>();
    	try {
    		list = proPlcRealService.getSsJqsj(dgjId);
    		if(list != null){
    			for(ProPlcBzRealDto dto:list){
    				if(dto.getDwid().contains("TJXT")
    						||dto.getDwid().contains("DXXT")){
    					nlist.add(dto);
    				}
    			}
    		}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return renderSuccess(list);
	}
	
	/**
	 * 根据盾构机编号、拼装环号获取所在环号拼装参数
	 * @param parameter
	 * @return
	 */
    @RequestMapping("/getSysDataByHH")
    @ResponseBody
	public Object getSysDataByHH(String dgjId,String hh){
    	List<ProPlcBzRealDto> list = null;
    	List<ProPlcBzRealDto> nlist = new ArrayList<ProPlcBzRealDto>();
    	try {
    		list = proPlcRealService.getSsJqsj(dgjId);
    		if(list != null){
    			for(ProPlcBzRealDto dto:list){
    				if(dto.getDwid().contains("TJXT")){
    					nlist.add(dto);
    				}
    			}
    		}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return renderSuccess(list);
	}

}
