package com.crfeb.tbmpt.gpztcl.base.controller;

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
import com.crfeb.tbmpt.commons.base.BaseService;
import com.crfeb.tbmpt.commons.utils.BeanUtils;
import com.crfeb.tbmpt.commons.utils.CommUtils;
import com.crfeb.tbmpt.commons.utils.ExportDto;
import com.crfeb.tbmpt.commons.utils.ExportExcelUtil;
import com.crfeb.tbmpt.commons.utils.ExportSetUtil;
import com.crfeb.tbmpt.commons.utils.PageInfo;
import com.crfeb.tbmpt.commons.utils.StringUtils;
import com.crfeb.tbmpt.gpztcl.base.model.GpztclSjzxInfo;
import com.crfeb.tbmpt.gpztcl.base.model.GpztclXyfys;
import com.crfeb.tbmpt.gpztcl.base.model.dto.GpztclXyfysDto;
import com.crfeb.tbmpt.gpztcl.base.service.GpztclSjzxInfoService;
import com.crfeb.tbmpt.gpztcl.base.service.GpztclXyfysService;
import com.crfeb.tbmpt.project.model.ProProjectjindu;
import com.crfeb.tbmpt.project.model.vo.SectionLineVo;


@Controller
@RequestMapping(value="/gpztcl/base/gpztclXyfys")
public class GpztclXyfysController extends BaseController{
	
	@Autowired
    private GpztclXyfysService gpztclXyfysService;
	
	/**
	 * 平曲线要素增加页面
	 * @param proId
	 * @param sectionId
	 * @param id
	 * @param pn
	 * @param model
	 * @return
	 */
    @RequestMapping(value="/xyfys",method = RequestMethod.GET)
    public Object xyfys(String proId,String sectionId,String id,String pn,Model model){
   	
   	 GpztclXyfysDto xy=new GpztclXyfysDto();
   	 List<SectionLineVo> line=gpztclXyfysService.selectByXlId(id);
   	 for(SectionLineVo vo :line){
   		 if(vo.getId().equals(id)){
   			 xy.setProName(vo.getProName());
   			 xy.setSectionName(vo.getSectionName());
   			 xy.setLineName(vo.getLineName());
   		 }
   	 }
   	 List<GpztclXyfysDto> list=gpztclXyfysService.selectXyfys(id);
   	 model.addAttribute("proId",JSON.toJSONString(proId));
   	 model.addAttribute("sectionId",JSON.toJSONString(sectionId));
   	 model.addAttribute("id",JSON.toJSONString(id));
   	 model.addAttribute("xy",JSON.toJSONString(xy));
   	 model.addAttribute("list",JSON.toJSONString(list));
   	 return "gpztcl/base/gpztclXyfys";
   	 
    }
    
   /**
    * 平曲线要素增加保存
    * @param dto
    * @return
    */
    @RequestMapping(value = "/save", method = RequestMethod.POST)
	@ResponseBody
	public Object save(GpztclXyfysDto dto) {
    	String json=dto.getDetails();
    	String FID=null;
    	
    	//子表信息
		if(!"[]".equals(json) && null != json){
			json = json.replace("&quot;", "\"");
			 List<GpztclXyfysDto> list = new ArrayList<GpztclXyfysDto>();  
		     list = JSONObject.parseArray(json, GpztclXyfysDto.class);
		     List<GpztclXyfys> addlist = new ArrayList<>();
		     
		     for(GpztclXyfysDto j:list){
		    	FID=j.getXlId();
		    	GpztclXyfys gpztclXyfys = new GpztclXyfys();
	    		BeanUtils.copyProperties(j, gpztclXyfys);
	    		gpztclXyfys.setFid(FID);
	    		addlist.add(gpztclXyfys);
		     }
		     Map<String, Object> columnMap = new HashMap<>();
        	 columnMap.put("FID", FID);
        	 gpztclXyfysService.deleteByMap(columnMap);
        	 gpztclXyfysService.insertBatch(addlist);
		     
		}
    	return renderSuccess("保存成功！");
	}
    /**     
     * 数据导出     
     * @param exportDto     
     * @param Dto     
     * @param response     
     */
     @SuppressWarnings({"unchecked", "rawtypes"})
     	@RequestMapping(value="/expXls")
     	public void expXls(ExportDto exportDto,GpztclXyfysDto dto, HttpServletResponse response,String id){
     		try {
     			if(StringUtils.isNotBlank(exportDto.getIds())){
 	    			List<String> idsList =new ArrayList<String>();
     				Collections.addAll(idsList, exportDto.getIds().split(ExportDto.SPLIT_STR));
     				List list = gpztclXyfysService.selectBatchIds(idsList);
     				ExportExcelUtil.writeExcelToClient(response, exportDto, ExportSetUtil.objectToListMap(list));
 	    		}else{
 	    	        PageInfo pageInfo = new PageInfo(0, 65530, "id", "desc");
     		        Map<String, Object> condition = new HashMap<String, Object>();
 	    	        pageInfo.setCondition(condition);
 	    	        List<GpztclXyfysDto> list=gpztclXyfysService.selectXyfys(id);
 	    	        Page<GpztclSjzxInfo> page = new Page<GpztclSjzxInfo>(pageInfo.getNowpage(), pageInfo.getSize());
 	    	        pageInfo.setRows(list);
 	    	        pageInfo.setTotal(page.getTotal());
 	    			ExportExcelUtil.writeExcelToClient(response, exportDto, ExportSetUtil.objectToListMap(pageInfo.getRows()));
 	    		}
 	    	} catch (Exception e) {
 	    		e.printStackTrace();
 	    	}
 	    }

 
}
