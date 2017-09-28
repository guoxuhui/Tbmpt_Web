package com.crfeb.tbmpt.risk.controller;

import java.util.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.crfeb.tbmpt.zl.model.QualityInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.crfeb.tbmpt.commons.base.BaseController;
import com.crfeb.tbmpt.commons.result.Result;
import com.crfeb.tbmpt.commons.scan.SpringUtils;
import com.crfeb.tbmpt.commons.utils.DateUtils;
import com.crfeb.tbmpt.commons.utils.PageInfo;
import com.crfeb.tbmpt.commons.utils.StringUtils;
import com.crfeb.tbmpt.open.service.IOpenCommService;
import com.crfeb.tbmpt.project.model.ProProjectinfo;
import com.crfeb.tbmpt.project.model.ProRProjectSection;
import com.crfeb.tbmpt.project.model.ProRSectionLine;
import com.crfeb.tbmpt.project.service.IProProjectinfoService;
import com.crfeb.tbmpt.project.service.IProProjectjinduService;
import com.crfeb.tbmpt.project.service.IProRProjectSectionService;
import com.crfeb.tbmpt.project.service.IProRSectionLineService;
import com.crfeb.tbmpt.risk.model.RiskInfo;
import com.crfeb.tbmpt.risk.model.RiskLevel;
import com.crfeb.tbmpt.risk.model.vo.RiskInfoVo;
import com.crfeb.tbmpt.risk.service.IRiskInfoService;
import com.crfeb.tbmpt.risk.service.IRiskLevelService;
import com.crfeb.tbmpt.sys.model.Orgz;
import com.crfeb.tbmpt.sys.model.SysEmployee;
import com.crfeb.tbmpt.sys.model.User;
import com.crfeb.tbmpt.sys.service.ISysEmployeeService;
import com.crfeb.tbmpt.tbmmg.model.ProTbminfo;
import com.crfeb.tbmpt.tbmmg.service.IProTbminfoService;
import com.crfeb.tbmpt.zl.model.vo.QualityInfoQueryVo;
import com.crfeb.tbmpt.zl.service.IQualityInfoService;
import com.crfeb.tbmpt.zsjk.plc.model.dto.ProPlcBzRealDto;
import com.crfeb.tbmpt.zsjk.plc.service.IProPlcRealService;

/**
 * 管片质量分析
 */
@Controller
@RequestMapping("/risk/fx")
public class RiskFxController extends BaseController {
	@Autowired
	IOpenCommService openCommServiceImpl;
	@Autowired
	private IProProjectinfoService proProjectinfoService;
	@Autowired
	private IRiskLevelService iRiskLevelService;
	@Autowired
	private IProProjectjinduService proProjectjinduService;
	@Autowired
	private IProRProjectSectionService proRProjectSectionService;
	@Autowired
	private IProRSectionLineService proRSectionLineService;
	@Autowired
	private ISysEmployeeService sysEmployeeService;
	@Autowired
	private IRiskInfoService iRiskInfoService;

	/**
	 * 管片质量分析 初始化 String
	 * 
	 * @return
	 */
	@RequestMapping(value = "", method = RequestMethod.GET)
	public String manager() {
		return "risk/fx/index";
	}

	/**
	 * 风险上报监控
	 * 
	 * @param cid
	 * @param queryType 0、今日 1、本周 2、本年3、本年
	 * @param sdata edata queryType为空则使用 sdata
	 * @return JSONObject
	 */
	@RequestMapping(value = "/getChartData")
	@ResponseBody
	public Object riskmonitor(String cid, String queryType,String sdata,String edata,String type) {
		User user = getCurrentUser();
		if (user == null) {
			return renderError("您尚未登录或登录时间过长,请重新登录!");
		}
		if(!StringUtils.isBlank(sdata) && !StringUtils.isBlank(edata)) {
			String startTime = sdata;
		    	long start = Long.valueOf(startTime.replaceAll("[-\\s:]",""));
		    	String endTime = edata;
		    	long end = Long.valueOf(endTime.replaceAll("[-\\s:]",""));
		    	if(end < start){
		    		return renderError("结束时间必须大于开始时间");
		    	}
		}
	    	
		if(type == null || (!type.equals("bar") && !type.equals("line")) ) {
			type = "bar";
		}
		JSONObject jsonObject = new JSONObject();
		// 查询当前人的所有项目
		List<ProProjectinfo> proProjectinfos = proProjectinfoService.getProjectInfosByUserId(user.getId());
		EntityWrapper<RiskLevel> riskLevel = new EntityWrapper<RiskLevel>();
		riskLevel.orderBy("sort", true);
		// 当前所有等级
		List<RiskLevel> levels = openCommServiceImpl.queryRiskLevelList(riskLevel);		
		for(RiskLevel l:levels){
			if("a76f1c4bd37b4faeb33116c7f21a994f".equals(l.getId())){
				levels.remove(l);
				break;
			}
		}
		String[] time = new String[] { "", "" };
		if (org.apache.commons.lang.StringUtils.isNotBlank(queryType)) {// 区间-日期
			time = timeType(queryType);
		}else {
			time[0] = sdata;
			time[1] = edata;
		}
		
		JSONArray proArr = new JSONArray();
		for (int i = 0; i < proProjectinfos.size(); i++) {
			proArr.add(proProjectinfos.get(i).getAbbreviation());
		}
		JSONArray serieArr = new JSONArray();
		JSONArray legendArr = new JSONArray();
		
		JSONObject labelJson = new JSONObject();
		JSONObject normalJson = new JSONObject();
		normalJson.put("show",true);
		normalJson.put("position","inside");
		labelJson.put("normal", normalJson);
		for (int j = 0; j < levels.size(); j++) {
			legendArr.add(levels.get(j).getLevelName());
			
			JSONObject itemStyle = new JSONObject();
			JSONObject normalColor = new JSONObject();
			normalColor.put("color",levels.get(j).getColorFlag());
			itemStyle.put("normal", normalColor);
			
			JSONObject serieJson = new JSONObject();
			serieJson.put("name", levels.get(j).getLevelName());
			serieJson.put("type", type);
			serieJson.put("stack", "0");
			serieJson.put("itemStyle", itemStyle);
			serieJson.put("label", labelJson);
			serieJson.put("barWidth", "60");
			JSONArray dataArr = new JSONArray();
			for (int i = 0; i < proProjectinfos.size(); i++) {	
				long levelSum = openCommServiceImpl.selectRiskInfoProIdUpUserRikeLevelUpTimeConut(
						proProjectinfos.get(i).getId(), null, levels.get(j).getId(), time);
				dataArr.add(levelSum);
			}
			serieJson.put("data", dataArr);
			serieArr.add(serieJson);
		}
		
		//去除为空的值
		for(int i = 0;i<proArr.size();i++) {
			int sum = 0;
			for(int j=0;j<serieArr.size();j++) {
				JSONObject ob = (JSONObject)serieArr.get(j);	
				sum = sum + ob.getJSONArray("data").getIntValue(i);
			}
			if(sum == 0) {
				proArr.remove(i);
				for(int j=0;j<serieArr.size();j++) {
					JSONObject ob = (JSONObject)serieArr.get(j);	
					ob.getJSONArray("data").remove(i);
				}
				i = i - 1;
			}
		}
		
		jsonObject.put("legend", legendArr);
		jsonObject.put("xaxis", proArr);
		jsonObject.put("series", serieArr);
		jsonObject.put("type",type);
		return renderSuccess(jsonObject);
	}

	/**
	 * 通过type 获取查询日期数组
	 * 
	 * @param timeType
	 *            0、今日 1、本周 2、本月3、本年
	 * @return
	 */
	private String[] timeType(String timeType) {
		String startTime = "";
		String endTime = "";
		switch (timeType) {
		case "0":// 今日
			startTime = DateUtils.format(new Date(), "yyyy-MM-dd");
			endTime = DateUtils.getDayAfter(DateUtils.format(new Date(), "yyyy-MM-dd"), 1);
			break;
		case "1":// 本周
			startTime = DateUtils.getCurrentMonday();
			endTime = DateUtils.getDayAfter(DateUtils.getSunday(), 1);
			break;
		case "2":// 本月
			startTime = DateUtils.getMonthFirstDay();
			endTime = DateUtils.getDayAfter(DateUtils.getMonthLastDay(), 1);
			break;
		case "3":// 本年
			startTime = DateUtils.getCurrentYear() + "-01-01";
			endTime = DateUtils.getDayAfter(DateUtils.getCurrentYear() + "-12-31", 1);
			break;
		default:
			break;
		}
		return new String[] { startTime, endTime };
	}

	/**
	 * 获取项目信息
	 * 
	 * @return
	 */
	@RequestMapping(value = "/getProDic")
	@ResponseBody
	public Object getDic() {
		User user = getCurrentUser();
		List<ProProjectinfo> list = proProjectinfoService.getProjectInfosByUserId(String.valueOf(user.getId()));
		return list;
	}
}
