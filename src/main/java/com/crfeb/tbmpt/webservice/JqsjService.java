package com.crfeb.tbmpt.webservice;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.crfeb.tbmpt.commons.result.Request;
import com.crfeb.tbmpt.commons.result.Result;
import com.crfeb.tbmpt.commons.scan.SpringUtils;
import com.crfeb.tbmpt.commons.utils.DateUtils;
import com.crfeb.tbmpt.commons.utils.StringUtils;
import com.crfeb.tbmpt.project.model.ProRSectionLine;
import com.crfeb.tbmpt.project.service.IProRSectionLineService;
import com.crfeb.tbmpt.tbmmg.model.ProTbminfo;
import com.crfeb.tbmpt.tbmmg.service.IProTbminfoService;
import com.crfeb.tbmpt.webservice.model.Tunnel;
import com.crfeb.tbmpt.zsjk.plc.model.dto.ProPlcBzRealDto;
import com.crfeb.tbmpt.zsjk.plc.service.IProPlcBzdwService;
import com.crfeb.tbmpt.zsjk.plc.service.IProPlcRealService;
import com.crfeb.tbmpt.zsjk.plc.service.IProPlcTbmdwService;

public class JqsjService extends BaseService{
	
	private IProPlcBzdwService proPlcBzdwService;
	private IProPlcRealService proPlcRealService;
	private IProPlcTbmdwService proPlcTbmdwService;
    private IProTbminfoService proTbminfoService;
    private IProRSectionLineService proRSectionLineService;
	
	/**
	 * 获取机器全部实时数据
	 * @param parameter tbmId盾构机ID
	 * @return
	 */
	public String getSsJqsj(String parameter){
		boolean audit = this.audit(parameter);
		if(!audit)return erroMessage();
		
		Result rs = new Result();
		
		JSONObject obj = JSONObject.parseObject(parameter);
		Request rq =  obj.toJavaObject(obj, Request.class);
		Map<String,String> parameters = (Map<String, String>) rq.getArg();
		String tbmId = parameters.get("dgjId");
		proPlcRealService = SpringUtils.getBean(IProPlcRealService.class);
		try {
			List<ProPlcBzRealDto> list = proPlcRealService.getSsJqsj(tbmId);
			rs.setObj(list);
			rs.setSuccess(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return toJsonString(rs);
	}
	
	/**
	 * 获取机器全部实时数据
	 * @param parameter xlId ID
	 * @return
	 */
	public String getSsJqsjByXlId(String parameter){
		boolean audit = this.audit(parameter);
		if(!audit)return erroMessage();
		
		Result rs = new Result();
		
		JSONObject obj = JSONObject.parseObject(parameter);
		Request rq =  obj.toJavaObject(obj, Request.class);
		Map<String,String> parameters = (Map<String, String>) rq.getArg();
		String xlId = parameters.get("xlId");
		proTbminfoService = SpringUtils.getBean(IProTbminfoService.class);
		proRSectionLineService = SpringUtils.getBean(IProRSectionLineService.class);
		proPlcRealService = SpringUtils.getBean(IProPlcRealService.class);
		
		ProRSectionLine line = proRSectionLineService.selectById(xlId);
		ProTbminfo tbm = null;
		List<ProPlcBzRealDto> list = null;
		
		try {
			if(line != null){
				tbm = proTbminfoService.selectById(line.getTbmId());
				list = proPlcRealService.getSsJqsj(tbm.getId());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		rs.setObj(list);
		rs.setSuccess(true);
		return toJsonString(rs);
	}
	
	/**
	 * 获取实时机器数据 根据盾构机编号以及点位编号
	 * @param parameter 
	 * @param tbmId盾构机ID 
	 * @param dwIds点位ID组，通过逗号分隔
	 * @return
	 */
	public String getSsJqsjByDw(String parameter){
		boolean audit = this.audit(parameter);
		if(!audit)return erroMessage();
		
		Result rs = new Result();
		
		JSONObject obj = JSONObject.parseObject(parameter);
		Request rq =  obj.toJavaObject(obj, Request.class);
		Map<String,String> parameters = (Map<String, String>) rq.getArg();
		String tbmId = parameters.get("dgjId");
		String dwIds = parameters.get("dwIds");
		proPlcRealService = SpringUtils.getBean(IProPlcRealService.class);
		try {
			List<ProPlcBzRealDto> list = proPlcRealService.getSsJqsjByDw(tbmId,dwIds);
			rs.setObj(list);
			rs.setSuccess(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return toJsonString(rs);
	}
	
	/**
	 * 根据盾构机编号获取当前施工环号以及时间信息
	 * @param xmId 项目ID qjId 区间ID xlId 线路ID
	 * @return jjhh 掘进换号  plcTime 当前时间  tbmInfo 盾构机信息
	 */
    @RequestMapping("/getCurrHHByXlId")
    @ResponseBody
	public Object getCurrHHByXlId(String xmId,String qjId,String xlId){
		Result result = new Result();
		if(StringUtils.isEmpty(xlId)){
	        result.setSuccess(false);
	        result.setMsg("线路ID不能为空");
	        return result;
		}
		Map<String,Object> tbmMap = new HashMap<String,Object>();
		proTbminfoService = SpringUtils.getBean(IProTbminfoService.class);
		proRSectionLineService = SpringUtils.getBean(IProRSectionLineService.class);
		ProRSectionLine line = proRSectionLineService.selectById(xlId);
		ProTbminfo tbm = null;
		if(line != null){
			tbm = proTbminfoService.selectById(line.getTbmId());
		}
		if(tbm == null){
	        result.setSuccess(false);
	        result.setMsg("盾构机不存在");
	        return result;
		}
		List<ProPlcBzRealDto> pbzs = new ArrayList<ProPlcBzRealDto>();
		String dwIds = "TY_DXXT_0001";
		try {
			pbzs = proPlcRealService.getSsJqsjByDw(tbm.getId(), dwIds);
		} catch (Exception e) {
			e.printStackTrace();
		}
		tbmMap.put("tbmInfo", tbm);
		if(pbzs != null && pbzs.size()==1){
			if("TY_DXXT_0001".equals(pbzs.get(0).getDwid())){
				tbmMap.put("jjhh", pbzs.get(0).getTagvalue());
			}
			tbmMap.put("plcTime", pbzs.get(0).getTagtime());
		}
		
		if(tbmMap.get("jjhh") == null){
			tbmMap.put("jjhh", "0");
			tbmMap.put("plcTime", DateUtils.getToday());
		}
			
		result.setSuccess(true);
		result.setObj(tbmMap);
		return JSON.toJSONString(result,SerializerFeature.WriteMapNullValue,SerializerFeature.WriteNullStringAsEmpty);

	}
    
    
    /**
	 * 根据盾构机编号获取当前施工环号以及时间信息
	 * @param xmId 项目ID qjId 区间ID xlId 线路ID，其中项目编号必填项 其余选填
	 * @return List<Tunnel>
	 */
    @RequestMapping("/getTunnelInfo")
    @ResponseBody
	public Object getTunnelInfo(String xmId,String qjId,String xlId){
		Result result = new Result();
		if(StringUtils.isEmpty(xmId)){
	        result.setSuccess(false);
	        result.setMsg("项目ID不能为空");
	        return result;
		}
		List<Tunnel> list = new ArrayList<Tunnel>(); 
		result.setSuccess(true);
		result.setObj(list);
		return JSON.toJSONString(result,SerializerFeature.WriteMapNullValue,SerializerFeature.WriteNullStringAsEmpty);
	}
}
