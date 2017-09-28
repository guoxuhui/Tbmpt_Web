package com.crfeb.tbmpt.zl.controller;

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
import com.crfeb.tbmpt.commons.base.BaseController;
import com.crfeb.tbmpt.commons.result.Result;
import com.crfeb.tbmpt.commons.scan.SpringUtils;
import com.crfeb.tbmpt.commons.utils.DateUtils;
import com.crfeb.tbmpt.commons.utils.PageInfo;
import com.crfeb.tbmpt.commons.utils.StringUtils;
import com.crfeb.tbmpt.project.model.ProProjectinfo;
import com.crfeb.tbmpt.project.model.ProRProjectSection;
import com.crfeb.tbmpt.project.model.ProRSectionLine;
import com.crfeb.tbmpt.project.service.IProProjectinfoService;
import com.crfeb.tbmpt.project.service.IProProjectjinduService;
import com.crfeb.tbmpt.project.service.IProRProjectSectionService;
import com.crfeb.tbmpt.project.service.IProRSectionLineService;
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
@RequestMapping("/zl/fx")
public class QualityFxManageController extends BaseController {
	
	@Autowired
	private IProProjectinfoService proProjectinfoService;
	@Autowired
	IQualityInfoService  iQualityInfoService;
	@Autowired
	private IProProjectjinduService proProjectjinduService;
	@Autowired
    private IProRProjectSectionService proRProjectSectionService;
	@Autowired
    private IProRSectionLineService proRSectionLineService;
	 @Autowired
	private ISysEmployeeService sysEmployeeService;
	 
	/**
	 * 管片质量分析  初始化
	 * String
     * @return
     */
    @RequestMapping(value = "", method = RequestMethod.GET)
    public String manager() {



        return "zl/fx/index";
    }

    final static String qualityUpProblemType = "[{\"key\":\"开裂\",\"value\":\"开裂\"},{\"key\":\"错台\",\"value\":\"错台\"},{\"key\":\"渗漏水\",\"value\":\"渗漏水\"},{\"key\":\"姿态\",\"value\":\"姿态\"},{\"key\":\"复紧\",\"value\":\"复紧\"}]";

    /**
     * 获取图表列表
     */
    @RequestMapping(value = "/getChartData", method = RequestMethod.POST)
    @ResponseBody
    public Object getChartData(String proName,String section,String line,String day,String zhou,String month,String picType) {
        JSONArray problemType = JSONArray.parseArray(qualityUpProblemType);
        JSONArray returnArray = new JSONArray();
        String[] t = getTimeT(day,zhou,month);
        if(t!=null && t.length>0){
            String type = t[0];
            Integer tNum = Integer.parseInt(t[1]);
//            int forInt = getForInt(day,zhou,month);
//            String unitDay = "";
            for(int j=0;j<problemType.size();j++) {
                JSONArray proArray = new JSONArray();
                JSONObject typeJson = new JSONObject();
                JSONObject singleJson = problemType.getJSONObject(j);
                for(int i=0;i<getForInt(day,zhou,month);i++){
                    String startTime = "";
                    String endTime = "";
                    if(type=="day"){//日
//                        unitDay = "天";
                        startTime = DateUtils.addDayOfCurrentDay((-(i)*tNum));
                        endTime = DateUtils.addDayOfCurrentDay(-((i+1)*tNum));
                    }else if(type=="zhou"){//周
//                        unitDay = "周";
                        startTime = DateUtils.addDayOfCurrentDay(-((i)*tNum));
                        endTime = DateUtils.addDayOfCurrentDay(-((i+1)*tNum));
                    }else if(type=="month"){//月
//                        unitDay = "月";
                        startTime = DateUtils.addDayOfCurrentDay(-((i)*tNum));
                        endTime = DateUtils.addDayOfCurrentDay(-((i+1)*tNum));
                    }
                    List<QualityInfo> list = iQualityInfoService.selectVoQualityInfoByChartData(endTime, startTime, proName, section, line,singleJson.getString("value"));
                    JSONObject listJSON = new JSONObject();
                    listJSON.put("infos",list);
                    proArray.add(listJSON);
                }
                typeJson.put("proArray",proArray);
                returnArray.add(typeJson);
            }
        }
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("charts",returnArray);
        jsonObject.put("problemType",qualityUpProblemType);
        return jsonObject;
    }

    private int getForInt(String day,String zhou,String month){
        int forInt = 0;
        if(StringUtils.isBlank(day) && StringUtils.isBlank(zhou) && StringUtils.isBlank(month)){
            forInt = 7;
        }
        if(StringUtils.isNotBlank(day)){
            forInt = Integer.parseInt(day);
        }
        if(StringUtils.isNotBlank(zhou)){
            forInt = Integer.parseInt(zhou);
        }
        if(StringUtils.isNotBlank(month)){
            forInt = Integer.parseInt(month);;
        }
        return forInt;
    }

    private String[] getTimeT(String day,String zhou,String month){
        String[] t = new String[]{};
        if(StringUtils.isBlank(day) && StringUtils.isBlank(zhou) && StringUtils.isBlank(month)){
            t = new String[]{"day","1"};
        }
        if(StringUtils.isNotBlank(day)){
            t = new String[]{"day","1"};
        }
        if(StringUtils.isNotBlank(zhou)){
            t = new String[]{"zhou","7"};
        }
        if(StringUtils.isNotBlank(month)){
            t = new String[]{"month","30"};
        }
        return t;
    }
    
    /**
     * 获取区间线路信息
     * @return
     */
    @RequestMapping(value = "/getProLineDic", method = RequestMethod.POST)
    @ResponseBody
    public Object getProLineDic(String sectionId) {
    	if(StringUtils.isEmpty(sectionId)){
    		return renderError("查询失败");
    	}else{
        	List<ProRSectionLine> list = proRSectionLineService.getLineBySectionId(sectionId);
        	return list;
    	}
    }
    /**
     * 获取项目区间信息
     * @return
     */
    @RequestMapping(value = "/getProSectionDic", method = RequestMethod.POST)
    @ResponseBody
    public Object getProSectionDic(String proId) {
    	if(StringUtils.isEmpty(proId)){
    		return renderError("查询失败");
    	}else{
        	List<ProRProjectSection> list = proRProjectSectionService.getSectionByProId(proId);
        	return list;
    	}
    }

    /**
     * 获取项目信息
     * @return
     */
    @RequestMapping(value = "/getProDic")
    @ResponseBody
    public Object getDic() {
    	User user = getCurrentUser();
    	List<ProProjectinfo> list = proProjectinfoService.getProjectInfosByUserId(String.valueOf(user.getId()));
    	return list;
    }

    @Autowired
    private IProPlcRealService proPlcRealService;
    @Autowired
    private IProTbminfoService proTbminfoService;

    /**
     * 獲取環號
     * @param xlId 线路ID
     * @return jjhh 掘进换号  plcTime 当前时间  tbmInfo 盾构机信息
     */
    @RequestMapping("/getHHByXlId")
    @ResponseBody
    public Object getCurrHHByXlId(String xlId){
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
        return JSON.toJSONString(result, SerializerFeature.WriteMapNullValue,SerializerFeature.WriteNullStringAsEmpty);

    }

    /**
     * 数据同步
     * @return
     */
    @RequestMapping("/dataSynchronous")
    @ResponseBody
    public Object dataSynchronous(HttpServletRequest request, HttpServletResponse response) {
        //int result = QualityPZTask.qualitypzOpen(request,response,iQualityInfoService,proProjectinfoService);
    	QualityPZTask q = new QualityPZTask();
    	int result = q.qualitypzOpen();
    	if(result>0){
            return renderSuccess("同步成功！" + "共更新：" + result + "条数据");
        }else{
            return renderSuccess("同步成功！" + "共更新：0 条数据");
        }
    }

}
