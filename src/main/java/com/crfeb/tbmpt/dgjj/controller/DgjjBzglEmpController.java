package com.crfeb.tbmpt.dgjj.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.plugins.Page;
import com.crfeb.tbmpt.commons.base.BaseController;
import com.crfeb.tbmpt.commons.utils.BeanUtils;
import com.crfeb.tbmpt.commons.utils.ExportDto;
import com.crfeb.tbmpt.commons.utils.ExportExcelUtil;
import com.crfeb.tbmpt.commons.utils.ExportPdfUtil;
import com.crfeb.tbmpt.commons.utils.ExportSetUtil;
import com.crfeb.tbmpt.commons.utils.PageInfo;
import com.crfeb.tbmpt.commons.utils.StringUtils;
import com.crfeb.tbmpt.dgjj.model.DgjjBzglEmp;
import com.crfeb.tbmpt.dgjj.model.dto.DgjjBzglDto;
import com.crfeb.tbmpt.dgjj.model.dto.DgjjBzglEmpDto;
import com.crfeb.tbmpt.dgjj.service.DgjjBzglEmpService;
import com.crfeb.tbmpt.dgjj.service.DgjjBzglService;
import com.crfeb.tbmpt.gpztcl.base.model.GpztclSjzxInfo;
import com.crfeb.tbmpt.gpztcl.base.model.GpztclXyfys;
import com.crfeb.tbmpt.gpztcl.base.model.dto.GpztclXyfysDto;

@Controller
@RequestMapping(value="/dgjj/bzglEmp")
public class DgjjBzglEmpController extends BaseController{
	@Autowired
	private DgjjBzglEmpService bzglEmpService;
	
	/**
	 * 员工数据表格
	 * @param dto
	 * @return
	 */
	@RequestMapping(value = "/dataGrid", method = RequestMethod.POST)
    @ResponseBody
    public Object dataGrid(DgjjBzglDto dto) {
		
		PageInfo pageInfo = new PageInfo();
        try {
        	if(StringUtils.isBlank(dto.getId())){
        		return pageInfo;
        	}else{
        	List<DgjjBzglEmpDto> list=bzglEmpService.selectDataGrid(dto.getId());
        	pageInfo.setRows(list);
        	}
        } catch (Exception e) {
           e.printStackTrace();
        }
        return pageInfo;
       
}
	
	/**
	 * 员工主页保存
	 * @param dto
	 * @return
	 */
	@RequestMapping(value = "/save", method = RequestMethod.POST)
    @ResponseBody
    public Object save(DgjjBzglDto dto) {
		String json=dto.getDetails();
    	String FID=null;
    	
    	//子表信息
		if(!"[]".equals(json) && null != json){
			json = json.replace("&quot;", "\"");
			 List<DgjjBzglEmpDto> list = new ArrayList<DgjjBzglEmpDto>();  
		     list = JSONObject.parseArray(json, DgjjBzglEmpDto.class);
		     List<DgjjBzglEmp> addlist = new ArrayList<DgjjBzglEmp>();
		     
		     for(DgjjBzglEmpDto j:list){
		    	FID=j.getFid();
		    	DgjjBzglEmp dgjjBzglEmp = new DgjjBzglEmp();
	    		BeanUtils.copyProperties(j, dgjjBzglEmp);
	    		dgjjBzglEmp.setFid(FID);
	    		addlist.add(dgjjBzglEmp);
		     }
		     Map<String, Object> columnMap = new HashMap<>();
        	 columnMap.put("FID", FID);
        	 bzglEmpService.deleteByMap(columnMap);
        	 bzglEmpService.insertBatch(addlist);
		     
		}else{
			Map<String, Object> columnMap = new HashMap<>();
			columnMap.put("FID", dto.getId());
			bzglEmpService.deleteByMap(columnMap);
		}
    	return renderSuccess("保存成功！");
	}
	
	/**
	 * 导出excel
	 * @param exportDto
	 * @param bzId
	 * @param response
	 */
    @SuppressWarnings({"unchecked", "rawtypes"})
 	@RequestMapping(value="/expXls")
    public void expXls(ExportDto exportDto,String bzId, HttpServletResponse response){
		try {
 			if(StringUtils.isNotBlank(exportDto.getIds())){
	    			List<String> idsList =new ArrayList<String>();
 				Collections.addAll(idsList, exportDto.getIds().split(ExportDto.SPLIT_STR));
 				List list = bzglEmpService.selectBatchIds(idsList);
 				ExportExcelUtil.writeExcelToClient(response, exportDto, ExportSetUtil.objectToListMap(list));
	    		}else{
	    	        PageInfo pageInfo = new PageInfo(0, 65530, "id", "desc");
 		        Map<String, Object> condition = new HashMap<String, Object>();
	    	        pageInfo.setCondition(condition);
	    	        List<DgjjBzglEmpDto> list=bzglEmpService.selectDataGrid(bzId);
	    	        Page<DgjjBzglEmpDto> page = new Page<DgjjBzglEmpDto>(pageInfo.getNowpage(), pageInfo.getSize());
	    	        pageInfo.setRows(list);
	    	        pageInfo.setTotal(page.getTotal());
	    			ExportExcelUtil.writeExcelToClient(response, exportDto, ExportSetUtil.objectToListMap(pageInfo.getRows()));
	    		}
	    	} catch (Exception e) {
	    		e.printStackTrace();
	    	}
	    }
    
    /**
     * 导出pdf
     * @param exportDto
     * @param bzId
     * @param response
     */
    @SuppressWarnings({"unchecked", "rawtypes"})
 	@RequestMapping(value="/expPdf")
    public void expPdf(ExportDto exportDto,String bzId, HttpServletResponse response){
		try {
 			if(StringUtils.isNotBlank(exportDto.getIds())){
	    			List<String> idsList =new ArrayList<String>();
 				Collections.addAll(idsList, exportDto.getIds().split(ExportDto.SPLIT_STR));
 				List list = bzglEmpService.selectBatchIds(idsList);
 				ExportExcelUtil.writeExcelToClient(response, exportDto, ExportSetUtil.objectToListMap(list));
	    		}else{
	    	        PageInfo pageInfo = new PageInfo(0, 65530, "id", "desc");
 		        Map<String, Object> condition = new HashMap<String, Object>();
	    	        pageInfo.setCondition(condition);
	    	        List<DgjjBzglEmpDto> list=bzglEmpService.selectDataGrid(bzId);
	    	        Page<DgjjBzglEmpDto> page = new Page<DgjjBzglEmpDto>(pageInfo.getNowpage(), pageInfo.getSize());
	    	        pageInfo.setRows(list);
	    	        pageInfo.setTotal(page.getTotal());
	    	        ExportPdfUtil.writePdfToClient(response, exportDto, ExportSetUtil.objectToListMap(pageInfo.getRows()));
	    		}
	    	} catch (Exception e) {
	    		e.printStackTrace();
	    	}
	    }
    
}