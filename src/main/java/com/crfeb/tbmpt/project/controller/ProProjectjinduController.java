package com.crfeb.tbmpt.project.controller;

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
import com.crfeb.tbmpt.commons.base.BaseController;
import com.crfeb.tbmpt.commons.calc.CalcBhl;
import com.crfeb.tbmpt.commons.utils.CommUtils;
import com.crfeb.tbmpt.commons.utils.ExportDto;
import com.crfeb.tbmpt.commons.utils.ExportExcelUtil;
import com.crfeb.tbmpt.commons.utils.ExportPdfUtil;
import com.crfeb.tbmpt.commons.utils.ExportSetUtil;
import com.crfeb.tbmpt.commons.utils.PageInfo;
import com.crfeb.tbmpt.commons.utils.StringUtils;
import com.crfeb.tbmpt.dmcjjc.bg.model.JcbgDetail;
import com.crfeb.tbmpt.dmcjjc.info.model.JcInfo;
import com.crfeb.tbmpt.dmcjjc.info.model.vo.DmcjJcInfoDetailsDto;
import com.crfeb.tbmpt.project.model.ProProjectinfo;
import com.crfeb.tbmpt.project.model.ProProjectjindu;
import com.crfeb.tbmpt.project.model.ProProjectjinduDetails;
import com.crfeb.tbmpt.project.model.ProRProjectSection;
import com.crfeb.tbmpt.project.model.ProRSectionLine;
import com.crfeb.tbmpt.project.model.vo.ProProjectjinduQueryVo;
import com.crfeb.tbmpt.project.model.vo.ProProjectjinduVo;
import com.crfeb.tbmpt.project.model.vo.SectionLineQueryVo;
import com.crfeb.tbmpt.project.service.IProProjectinfoService;
import com.crfeb.tbmpt.project.service.IProProjectjinduService;
import com.crfeb.tbmpt.project.service.IProRProjectSectionService;
import com.crfeb.tbmpt.project.service.IProRSectionLineService;
import com.crfeb.tbmpt.sys.model.SysEmployee;
import com.crfeb.tbmpt.sys.model.User;
import com.crfeb.tbmpt.tbmmg.service.IProTbminfoService;


@Controller
@RequestMapping("/project/jindu")
public class ProProjectjinduController extends BaseController{
	
	    @Autowired
	    private IProProjectjinduService proProjectjinduService;
	    @Autowired
	    private IProRProjectSectionService proRProjectSectionService;
	    @Autowired
	    private IProProjectinfoService proProjectinfoService; 
	    @Autowired
	    private IProRSectionLineService proRSectionLineService;
	    /**首页
	     * @return
	     */
	    @RequestMapping(value = "", method = RequestMethod.GET)
	    public String manager() {
	    	return "project/jindu/index";
	    }
	    /**
	     * 导入明细页面
	     * @return
	     */
	    @RequestMapping(value = "/import", method = RequestMethod.GET)
	    public String imp() {
	        return "project/jindu/import";
	    }
	    
	    /**
	     * 明细页面
	     * @param rq
	     * @param proId
	     * @param mode
	     * @return
	     */
	    @RequestMapping(value = "/addDetails", method = RequestMethod.GET)
	    
	    public Object addDetails(String rq,String proId,Model mode) {
	    	
			//查子表数据
	    	List<ProProjectjinduQueryVo> details = proProjectjinduService.selectaddDetails(rq,proId);
			
			
			mode.addAttribute("list", JSON.toJSONString(details)); 
			
			return "project/jindu/addDetails";
	    }
	    //查看明细页面
	    @RequestMapping(value = "/show", method = RequestMethod.GET)
	    
	    public Object show(String rq,String proId,Model mode) {	    	
			//查子表数据
	    	List<ProProjectjinduQueryVo> details = proProjectjinduService.selectaddDetails(rq,proId);	
			mode.addAttribute("list", JSON.toJSONString(details)); 	
			return "project/jindu/show";
	    }
	    
	    
	    @RequestMapping("/addForm")  
		@ResponseBody
		public Object addForm() {
	       
			return renderSuccess("成功");
		}
	    
//	    @RequestMapping(value = "/dataGrid", method = RequestMethod.POST)
//	    @ResponseBody
//	    public Object dataGrid(ProProjectjinduQueryVo vo,Integer page, Integer rows, String sort, String order) {
//	        PageInfo pageInfo = new PageInfo(page, rows, sort, order);
//	        Map<String, Object> condition = new HashMap<String, Object>();
//	        pageInfo.setCondition(condition);
//	        if (StringUtils.isNotBlank(vo.getProId())) {
//	            condition.put("proId", vo.getProId());
//	        }
//	        if (StringUtils.isNotBlank(vo.getQjId())) {
//	            condition.put("qjId", vo.getQjId());
//	        }
//	        if (StringUtils.isNotBlank(vo.getXlId())) {
//	            condition.put("xlId", vo.getLineName());
//	        }
//	       
//	        if (vo.getStartTime() != null) {
//	            condition.put("startTime", vo.getStartTime());
//	        }
//	        if (vo.getEndTime() != null) {
//	            condition.put("endTime", vo.getEndTime());
//	        }
//	        proProjectjinduService.selectDataGrid(pageInfo);
//	        return pageInfo;
//	    }
	    /**
	     * 显示一天的数据
	     * @param vo
	     * @param page
	     * @param rows
	     * @param sort
	     * @param order
	     * @return
	     */
	    @RequestMapping(value = "/dataGrid", method = RequestMethod.POST)
	    @ResponseBody
	    public Object dataGrid(ProProjectjinduQueryVo vo,Integer page, Integer rows, String sort, String order) {
	        PageInfo pageInfo = new PageInfo(page, rows, sort, order);
	        Map<String, Object> condition = new HashMap<String, Object>();
	        pageInfo.setCondition(condition);
	        if (StringUtils.isNotBlank(vo.getProId())) {
	            condition.put("proId", vo.getProId());
	        }
	        if (StringUtils.isNotBlank(vo.getQjId())) {
	            condition.put("qjId", vo.getQjId());
	        }
	        if (StringUtils.isNotBlank(vo.getXlId())) {
	            condition.put("xlId", vo.getLineName());
	        }
	       
	        if (vo.getStartTime() != null) {
	            condition.put("startTime", vo.getStartTime());
	        }
	        if (vo.getEndTime() != null) {
	            condition.put("endTime", vo.getEndTime());
	        }
	        condition.put("sqlPurview", this.sqlPurview("p","id"));
	        proProjectjinduService.selectDataGrid(pageInfo,getCurrentUser());
	        return pageInfo;
	    }
	    
	    /**
	     * 跳转到添加页面
	     * @return
	     */
	    @RequestMapping(value = "/addPage", method = RequestMethod.GET)
	    public String addPage() {
	        return "project/jindu/add";
	    }
	    
	    @RequestMapping(value = "/add", method = RequestMethod.POST)
	    @ResponseBody
	    public Object add(ProProjectjindu p) {
	    	p.setId(CommUtils.getUUID());
	    	
	    	proProjectjinduService.insert(p);
	        return renderSuccess("添加成功！");
	    }
	    
	    /**
	     * 编辑页面
	     * @param model
	     * @param id
	     * @return
	     */
	    @RequestMapping(value = "/editPage")
	    public String editPage(Model model,String id) {
	    	ProProjectjindu p=proProjectjinduService.selectById(id);
	    	model.addAttribute("model",p);
	        return "project/jindu/edit";
	    }
	    
	    
	    @RequestMapping(value = "/edit", method = RequestMethod.POST)
	    @ResponseBody
	    public Object edit(ProProjectjinduDetails p){
	    	
	    	String json=p.getDetails();
	    	//子表信息
			if(!"[]".equals(json) && null != json){
				json = json.replace("&quot;", "\"");
				 List<ProProjectjindu> list = new ArrayList<ProProjectjindu>();  
			     list = JSONObject.parseArray(json, ProProjectjindu.class);  
			     for(ProProjectjindu j:list){
			    	 
			    	 proProjectjinduService.updateSelectiveById(j);
			    	 
			     }
			     
			}
	    	return renderSuccess("保存成功！");
	    }
	    
	    /**
	     * 删除
	     * @return
	     */
	    @RequestMapping(value = "/delete", method = RequestMethod.GET)
	    public String delete() {
	        return "project/jindu/delete";
	    }
	    
	    @RequestMapping(value = "/delete", method = RequestMethod.POST)
	    @ResponseBody
	    public Object delete(String times){
	    	List<String> result = new ArrayList<String>();
	    	String[] timess = times.split(",");
	    	for(String time : timess){
	    	proProjectjinduService.deleteByTime(time);
	    	
	    }
	    	return renderSuccess("删除成功！");
	    }
	    
	    /**
	     * 获取项目信息
	     * @return
	     */
	    @RequestMapping(value = "/getProDic", method = RequestMethod.POST)
	    @ResponseBody
	    public Object getProDic() {
	    	User user = getCurrentUser();
	    	List<ProProjectinfo> list = proProjectinfoService.getProjectInfosByUserId(String.valueOf(user.getId()));
	    	return list;
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
	     * 数据导出
	     * @param exportDto
	     * @param Dto
	     * @param response
	     */
		@SuppressWarnings({"unchecked", "rawtypes"})
		@RequestMapping(value="/expXls")
		public void expXls(ExportDto exportDto,ProProjectjinduQueryVo vo, HttpServletResponse response){
			try {
				if(StringUtils.isNotBlank(exportDto.getIds())){
					List<String> idsList =new ArrayList<String>();
					Collections.addAll(idsList, exportDto.getIds().split(ExportDto.SPLIT_STR));
					List list = proProjectjinduService.selectBatchIds(idsList);
					ExportExcelUtil.writeExcelToClient(response, exportDto, ExportSetUtil.objectToListMap(list));
				}else{
					PageInfo pageInfo = new PageInfo(0, 65530, "id", "desc");
			        Map<String, Object> condition = new HashMap<String, Object>();
			        pageInfo.setCondition(condition);
			        if (StringUtils.isNotBlank(vo.getProId())) {
			            condition.put("proId", vo.getProId());
			        }
			        if (StringUtils.isNotBlank(vo.getXlId())) {
			            condition.put("xlId", vo.getXlId());
			        }
			        if (StringUtils.isNotBlank(vo.getQjId())) {
			            condition.put("qjId", vo.getQjId());
			        }
			        if (vo.getStartTime() != null) {
			            condition.put("startTime", vo.getStartTime());
			        }
			        if (vo.getEndTime() != null) {
			            condition.put("endTime", vo.getEndTime());
			        }
			        condition.put("sqlPurview", this.sqlPurview("p","id"));
			        proProjectjinduService.selectDataGrid(pageInfo,getCurrentUser());
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
		public void expPdf(ExportDto exportDto,ProProjectjinduQueryVo vo, HttpServletResponse response){
			try {
				if(StringUtils.isNotBlank(exportDto.getIds())){
					List<String> idsList =new ArrayList<String>();
					 Collections.addAll(idsList, exportDto.getIds().split(ExportDto.SPLIT_STR));
					 List list = proProjectjinduService.selectBatchIds(idsList);
					 ExportPdfUtil.writePdfToClient(response, exportDto, ExportSetUtil.objectToListMap(list));
				}else{
					PageInfo pageInfo = new PageInfo(0, 65530, "id", "desc");
			        Map<String, Object> condition = new HashMap<String, Object>();
			        pageInfo.setCondition(condition);
			        if (StringUtils.isNotBlank(vo.getProId())) {
			            condition.put("proId", vo.getProId());
			        }
			        if (StringUtils.isNotBlank(vo.getXlId())) {
			            condition.put("qjId", vo.getQjId());
			        }
			        if (StringUtils.isNotBlank(vo.getLineName())) {
			            condition.put("xlId", vo.getXlId());
			        }
			        if (vo.getStartTime() != null) {
			            condition.put("startTime", vo.getStartTime());
			        }
			        if (vo.getEndTime() != null) {
			            condition.put("endTime", vo.getEndTime());
			        }
			        condition.put("sqlPurview", this.sqlPurview("p","id"));
			        proProjectjinduService.selectDataGrid(pageInfo,getCurrentUser());
					ExportPdfUtil.writePdfToClient(response, exportDto, ExportSetUtil.objectToListMap(pageInfo.getRows()));
				}
			} catch (Exception e) {
				e.printStackTrace();	
			}
		}
		
		
	    
	    
	   
}
