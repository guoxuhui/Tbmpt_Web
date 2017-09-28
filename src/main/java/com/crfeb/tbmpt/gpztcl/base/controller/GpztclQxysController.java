package com.crfeb.tbmpt.gpztcl.base.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.crfeb.tbmpt.commons.base.BaseController;
import com.crfeb.tbmpt.commons.utils.BeanUtils;
import com.crfeb.tbmpt.commons.utils.ExportDto;
import com.crfeb.tbmpt.commons.utils.ExportExcelUtil;
import com.crfeb.tbmpt.commons.utils.ExportSetUtil;
import com.crfeb.tbmpt.commons.utils.StringUtils;
import org.springframework.ui.Model;

import com.crfeb.tbmpt.gpztcl.base.model.GpztclQxys;
import com.crfeb.tbmpt.gpztcl.base.model.dto.GpztclQxysDto;
import com.crfeb.tbmpt.gpztcl.base.service.GpztclQxysService;
import com.crfeb.tbmpt.project.model.vo.SectionLineVo;


/**
 * <p>设置曲线要素  Controller</p>
 * <p>系统：管片姿态测量系统</p>
 * <p>模块：线路中心线信息管理模块</p>
 * <p>日期：2016-12-27</p>
 * @version 1.0
 * @author wpg
 */
@Controller
@RequestMapping(value="/gpztcl/base/qxys")
public class GpztclQxysController extends BaseController{

    @Autowired
    private GpztclQxysService qxysService;

    /***
     * 打开“设置曲线要素”页面
     * @author wpg
     * @param xlBh 线路表ID
     * @param model 
     * @return
     */
    @RequestMapping(value = "", method = RequestMethod.GET)
    public String manager(String xlBh,Model model) { 
    
    	
    	if(!StringUtils.isBlank(xlBh)){
    		//曲线要素对象集合
    	    List<GpztclQxys> qxyslist= new ArrayList<GpztclQxys>();
	    	
	    	try {
	    		//根据线路主表ID查询曲线要素对象集合
		    	qxyslist = qxysService.selectByFIdQxysList(xlBh);
		        //根据Id 查询线路信息
		    	SectionLineVo sectionLineVo = qxysService.selectSectionLineVoByXlId(xlBh);
		    	
		    	model.addAttribute("xlBh",JSON.toJSONString(sectionLineVo.getId()));
		    	model.addAttribute("proName", sectionLineVo.getProName());
		    	model.addAttribute("sectionName", sectionLineVo.getSectionName());
		    	model.addAttribute("lineName",sectionLineVo.getLineName());
		    	model.addAttribute("qxyslist",JSON.toJSONString(qxyslist));
	    	 } catch (Exception e) {
	             e.printStackTrace();
	         }
    	}
        return "gpztcl/base/gpztclQxys";
    }
    
    /**
     * “设置曲线要素”增加保存
     * @param dto
     * @return
     */
     @RequestMapping(value = "/save", method = RequestMethod.POST)
 	@ResponseBody
 	public Object save(GpztclQxysDto dto) {
    	 try {
    		 String json=dto.getDetails();
    	     	String FID=null;
    	     	//子表信息
    	 		if(!"[]".equals(json) && null != json){
    	 			json = json.replace("&quot;", "\"");
    	 			
    	 			 List<GpztclQxysDto> list = new ArrayList<GpztclQxysDto>();  
    	                       //转换类型
    	 		     list = JSONObject.parseArray(json, GpztclQxysDto.class);
    	 		     List<GpztclQxys> qxyslist = new ArrayList<>();
    	 		     
    	 		     for(GpztclQxysDto j:list){
    	 		    	FID=j.getFId();
    	 		    	GpztclQxys qxys = new GpztclQxys();
    	 		    	//反射将一个对象"j"的值 赋值另外一个对象"qxys"
    	 	    		BeanUtils.copyProperties(j,qxys);
    	 	    		qxyslist.add(qxys);
    	 		     }
    	 		     Map<String, Object> columnMap = new HashMap<>();
    	         	 columnMap.put("FID", FID);
    	         	 qxysService.deleteByMap(columnMap);
    	         	 qxysService.insertBatch(qxyslist);
    	 		     
    	 		}
    	     	return renderSuccess("保存成功！");
		} catch (Exception e) {
			e.printStackTrace();
			log(null, false, "新增 “曲线要素”信息失败!");
		}
    	 return renderError("保存失败！");
 	}

     /**
      * 数据导出 “曲线要素”信息
      * @param exportDto
      * @param Dto
      * @param response
      */
 	@SuppressWarnings({"unchecked","rawtypes"})
 	@RequestMapping(value="/expXls")
 	public void expXls(ExportDto exportDto,GpztclQxysDto dto, HttpServletResponse response){
 		try {
 			String Fid = String.valueOf(dto.getFId());
 	    	//根据  FID 查询 所有  “曲线要素”信息
 	    	List qxysList = qxysService.selectByFIdQxysList(Fid);
 			
 			ExportExcelUtil.writeExcelToClient(response, exportDto, ExportSetUtil.objectToListMap(qxysList));
 			log(null, false, "导出 “曲线要素”信息成功!");
 		} catch (Exception e) {
 			e.printStackTrace();
 			log(null, false, "导出 “曲线要素”信息失败!");
 		}
 	}
}