package com.crfeb.tbmpt.machine.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.crfeb.tbmpt.commons.base.BaseController;
import com.crfeb.tbmpt.commons.utils.ExportDto;
import com.crfeb.tbmpt.commons.utils.ExportExcelUtil;
import com.crfeb.tbmpt.commons.utils.ExportPdfUtil;
import com.crfeb.tbmpt.commons.utils.ExportSetUtil;
import com.crfeb.tbmpt.commons.utils.PageInfo;
import com.crfeb.tbmpt.commons.utils.StringUtils;
import com.crfeb.tbmpt.machine.model.MachineAlarm;
import com.crfeb.tbmpt.machine.model.MachineAlarmParam;
import com.crfeb.tbmpt.machine.service.IMachineAlarmService;

/**
 * <p>设备报警数据controlle</p>
 * <p>系统：</p>
 * <p>模块：设备报警数据</p>
 * <p>日期：2016-11-19</p>
 * @version 1.0
 * @author smxg
 */
@Controller
@RequestMapping(value="/machine/alarmhis")
public class MachineAlarmController extends BaseController{

    @Autowired
    private IMachineAlarmService machineAlarmService;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public String manager() {
        return "machine/alarmhis/list";
    }

    /**
     * 获取设备报警数据easyUi列表
     * @param pageNo 页码
     * @param pageSize 每页条数
     * @param dto 查询条件dto
     * @return 返回查询结果信息
     */
    @RequestMapping(value = "/dataGrid", method = RequestMethod.POST)
    @ResponseBody
    public Object dataGrid(MachineAlarm dto,String startTime,String endTime,Integer page, Integer rows, String sort, String order){
    	
    	PageInfo pageInfo = new PageInfo(page, rows, sort, order);
        Map<String, Object> condition = new HashMap<String, Object>();
        pageInfo.setCondition(condition);
        if (StringUtils.isNotBlank(dto.getProid())) {
            condition.put("proId", dto.getProid());
        }
        if (StringUtils.isNotBlank(dto.getSectionid())) {
            condition.put("sectionId", dto.getSectionid());
        }
        if (StringUtils.isNotBlank(dto.getLineid())) {
            condition.put("lineId", dto.getLineid());
        }
        if (startTime != null) {
            condition.put("startTime", startTime);
        }
        if (endTime != null) {
            condition.put("endTime", endTime);
        }
        condition.put("sqlPurview", this.sqlPurview("l","proid"));
        machineAlarmService.selectDataGrid(pageInfo);
        return pageInfo;
    }
    
    @RequestMapping(value = "/info", method = RequestMethod.GET)
    public String infoPage(Model model,String id) {
    	List<MachineAlarmParam> list = new ArrayList<MachineAlarmParam>();
    	MachineAlarm  alarm = machineAlarmService.selectById(id);
    	if(alarm != null){
    		String param = alarm.getParam();
    		JSONObject jsonContent = JSONObject.parseObject(param);
    		
    		Set<String> sIterator = jsonContent.keySet();
    		for(String key:sIterator){
    		    JSONObject json = jsonContent.getJSONObject(key);
    		    MachineAlarmParam para = new MachineAlarmParam();
    		    para.setDwid(json.getString("dwid"));
    		    para.setDwname(json.getString("dwname"));
    		    para.setTagvalue(json.getString("tagvalue"));
    		    para.setTagtime(json.getString("tagtime"));
    		    list.add(para);
    		}  
    		
    		model.addAttribute("param", list);
    		model.addAttribute("alarm",alarm);
    	}
    	
    	return "machine/alarmhis/info";
    }
    
    
    @RequestMapping(value = "/machine", method = RequestMethod.GET)
    public String machine(Model model,String id) {
    	model.addAttribute("id", id);
    	
    	Map<String,MachineAlarmParam> map = new HashMap<String,MachineAlarmParam>();
    	MachineAlarm  alarm = machineAlarmService.selectById(id);
    	if(alarm != null){
    		String param = alarm.getParam();
    		JSONObject jsonContent = JSONObject.parseObject(param);

    		Set<String> sIterator = jsonContent.keySet();
    		for(String key:sIterator){
    		    JSONObject json = jsonContent.getJSONObject(key);
    		    MachineAlarmParam para = new MachineAlarmParam();
    		    para.setDwid(json.getString("dwid"));
    		    para.setDwname(json.getString("dwname"));
    		    para.setTagvalue(json.getString("tagvalue"));
    		    para.setTagtime(json.getString("tagtime"));
    		    map.put(json.getString("dwid"),para);
    		}
    	}
    	model.addAttribute("alarm",alarm);
    	model.addAttribute("map", map);
    	return "machine/alarmhis/machine";
    }
    
    @RequestMapping(value = "/getjqsj", method = RequestMethod.GET)
    @ResponseBody
    public Object getjqsj(Model model,String id) {
    	List<MachineAlarmParam> list = new ArrayList<MachineAlarmParam>();
    	MachineAlarm  alarm = machineAlarmService.selectById(id);
    	if(alarm != null){
    		String param = alarm.getParam();
    		JSONObject jsonContent = JSONObject.parseObject(param);
    		
    		Set<String> sIterator = jsonContent.keySet();
    		for(String key:sIterator){
    		    JSONObject json = jsonContent.getJSONObject(key);
    		    MachineAlarmParam para = new MachineAlarmParam();
    		    para.setDwid(json.getString("dwid"));
    		    para.setDwname(json.getString("dwname"));
    		    para.setTagvalue(json.getString("tagvalue"));
    		    para.setTagtime(json.getString("tagtime"));
    		    list.add(para);
    		}
    	}
    	return renderSuccess(list);
    }

    /**     
    * 数据导出     
    * @param exportDto     
    * @param Dto     
    * @param response     
    */
    @SuppressWarnings({"unchecked", "rawtypes"})
    	@RequestMapping(value="/expXls")
    	public void expXls(ExportDto exportDto,MachineAlarm dto,String startTime,String endTime, HttpServletResponse response){
    		try {
    			if(StringUtils.isNotBlank(exportDto.getIds())){
	    			List<String> idsList =new ArrayList<String>();
    				Collections.addAll(idsList, exportDto.getIds().split(ExportDto.SPLIT_STR));
    				List list = machineAlarmService.selectBatchIds(idsList);
    				ExportExcelUtil.writeExcelToClient(response, exportDto, ExportSetUtil.objectToListMap(list));
	    		}else{
	    	        PageInfo pageInfo = new PageInfo(0, 65530, "id", "desc");
    		        Map<String, Object> condition = new HashMap<String, Object>();
	    	        pageInfo.setCondition(condition);
	    	        if (StringUtils.isNotBlank(dto.getProid())) {
	    	            condition.put("proId", dto.getProid());
	    	        }
	    	        if (StringUtils.isNotBlank(dto.getSectionid())) {
	    	            condition.put("sectionId", dto.getSectionid());
	    	        }
	    	        if (StringUtils.isNotBlank(dto.getLineid())) {
	    	            condition.put("lineId", dto.getLineid());
	    	        }
	    	        if (startTime != null) {
	    	            condition.put("startTime", startTime);
	    	        }
	    	        if (endTime != null) {
	    	            condition.put("endTime", endTime);
	    	        }
	    	        condition.put("sqlPurview", this.sqlPurview("l","proid"));
	    	        machineAlarmService.selectDataGrid(pageInfo);
	    			ExportExcelUtil.writeExcelToClient(response, exportDto, ExportSetUtil.objectToListMap(pageInfo.getRows()));
	    		}
	    	} catch (Exception e) {
	    		e.printStackTrace();
	    	}
	    }

    /**
     * 数据导出
     * @param exportDto
     * @param Dto
     * @param response
     */
     @SuppressWarnings({"unchecked", "rawtypes"})
     @RequestMapping(value="/expPdf")
     public void expPdf(ExportDto exportDto,MachineAlarm dto,String startTime,String endTime, HttpServletResponse response){
	     	try {
	     		if(StringUtils.isNotBlank(exportDto.getIds())){
	     			 List<String> idsList =new ArrayList<String>();
	     			 Collections.addAll(idsList, exportDto.getIds().split(ExportDto.SPLIT_STR));
	     			 List list = machineAlarmService.selectBatchIds(idsList);
	     			 ExportPdfUtil.writePdfToClient(response, exportDto, ExportSetUtil.objectToListMap(list));
	     		}else{
	     	        PageInfo pageInfo = new PageInfo(0, 65530, "id", "desc");
	     	        Map<String, Object> condition = new HashMap<String, Object>();
	     	        pageInfo.setCondition(condition);
	     	       if (StringUtils.isNotBlank(dto.getProid())) {
	     	            condition.put("proId", dto.getProid());
	     	        }
	     	        if (StringUtils.isNotBlank(dto.getSectionid())) {
	     	            condition.put("sectionId", dto.getSectionid());
	     	        }
	     	        if (StringUtils.isNotBlank(dto.getLineid())) {
	     	            condition.put("lineId", dto.getLineid());
	     	        }
	     	        if (startTime != null) {
	     	            condition.put("startTime", startTime);
	     	        }
	     	        if (endTime != null) {
	     	            condition.put("endTime", endTime);
	     	        }
	     	        condition.put("sqlPurview", this.sqlPurview("l","proid"));
	     	        machineAlarmService.selectDataGrid(pageInfo);
	     			ExportPdfUtil.writePdfToClient(response, exportDto, ExportSetUtil.objectToListMap(pageInfo.getRows()));
	     		}
	     	} catch (Exception e) {
	     		e.printStackTrace();
	     	}
     }
     
}