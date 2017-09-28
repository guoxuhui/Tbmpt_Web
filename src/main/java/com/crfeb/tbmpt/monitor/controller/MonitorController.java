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
import com.crfeb.tbmpt.commons.result.Result;
import com.crfeb.tbmpt.commons.utils.PageInfo;
import com.crfeb.tbmpt.commons.utils.StringUtils;
import com.crfeb.tbmpt.monitor.model.GeoLineModel;
import com.crfeb.tbmpt.monitor.service.IProGeoLinesService;
import com.crfeb.tbmpt.project.model.ProRSectionLine;
import com.crfeb.tbmpt.project.model.vo.ProjectinfoVo;
import com.crfeb.tbmpt.project.service.IProProjectinfoService;
import com.crfeb.tbmpt.project.service.IProRProjectSectionService;
import com.crfeb.tbmpt.project.service.IProRSectionLineService;
import com.crfeb.tbmpt.sys.service.ILogService;
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
@RequestMapping("/monitor")
public class MonitorController extends BaseController {

    @Autowired
    private ILogService sysLogService;
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
    private IProGeoLinesService proGeoLinesService;

    /**
     * 获取项目列表数据
     * @return
     */
	@RequestMapping(value="/getProjectList")
	@ResponseBody
	public Object getProjectList(){
		PageInfo pageInfo = new PageInfo(1, 1000, "id", "desc");
		Map<String, Object> condition = new HashMap<String, Object>();
		String sqlPurview = this.sqlPurview("p","id");
        condition.put("sqlPurview", sqlPurview);
		pageInfo.setCondition(condition);
		proProjectinfoService.selectDataGrid(pageInfo,getCurrentUser());
		List list = pageInfo.getRows();
        Result result = new Result();
        result.setSuccess(true);
        result.setObj(list);
        return JSON.toJSONString(result,SerializerFeature.WriteMapNullValue,SerializerFeature.WriteNullStringAsEmpty);
	}
	
	
    /**
     * 获取项目线路数据
     * @return
     */
	@RequestMapping(value="/getGeoLineList")
	@ResponseBody
	public Object getGeoLineList(){
		
		PageInfo pageInfo = new PageInfo(1, 1000, "id", "desc");
		Map<String, Object> condition = new HashMap<String, Object>();
		pageInfo.setCondition(condition);
		proProjectinfoService.selectDataGrid(pageInfo,getCurrentUser());
		List<ProjectinfoVo> list = pageInfo.getRows();
		JSONArray results = new JSONArray();
		for(ProjectinfoVo pv : list){
			Map<String,Object> semap = new HashMap<String,Object>();
			semap.put("PRO_ID", pv.getId());
			List<ProRSectionLine> llist = proRSectionLineService.selectByMap(semap);
			for(ProRSectionLine sl :llist){
				List<GeoLineModel> glms = proGeoLinesService.selectGeoLineListByLId(sl.getId());
				if(glms != null && glms.size()>1){
					JSONObject res = new JSONObject();
		        	res.put("lineId", sl.getId());
		        	res.put("lineName", glms.get(0).getL_name());
		        	res.put("state", sl.getLineStatus());
		        	res.put("zhh", sl.getRingCount());
		        	res.put("zlc", sl.getEndMileage());
		        	res.put("proId", pv.getId());
		        	res.put("proName", pv.getProName());
		        	res.put("tbmId", sl.getTbmId());
		        	JSONArray arrs = new JSONArray();
		        	for(GeoLineModel glm:glms){
		        		JSONArray arr = new JSONArray();
		            	arr.add(Double.parseDouble(glm.getLng()));
		            	arr.add(Double.parseDouble(glm.getLat()));
		            	arrs.add(arr);
		        	}
		        	res.put("lnglat", arrs);
		        	results.add(res);
				}
			}
		}
		
        Result result = new Result();
        result.setSuccess(true);
        result.setObj(results);
        return JSON.toJSONString(result,SerializerFeature.WriteMapNullValue,SerializerFeature.WriteNullStringAsEmpty);
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
	 * 获取盾构机状态信息  
	 * tbms 盾构机基本信息
	 * sum 盾构机总数
	 * @return
	 */
	@RequestMapping(value="/getAllTbmStatus")
	@ResponseBody
	public Object getAllTbmStatus(){
		List<ProTbminfo> tbms = proTbminfoService.selectAllList();
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("tbms", tbms);
		map.put("sum", tbms.size());
		map.put("sum_zx", 16);
		map.put("sum_zx_jj", 8);
		map.put("sum_zx_ph", 3);
		map.put("sum_zx_tj", 5);
		map.put("sum_lx", 8);
		map.put("sum_wh", tbms.size()-18);
		Result result = new Result();
		result.setSuccess(true);
		result.setObj(map);
		return JSON.toJSONString(result,SerializerFeature.WriteMapNullValue,SerializerFeature.WriteNullStringAsEmpty);
	}
}
