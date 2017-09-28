package com.crfeb.tbmpt.project.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

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
import com.crfeb.tbmpt.dgjj.model.dto.DgjjBzglEmpDto;
import com.crfeb.tbmpt.project.model.ProDwgcInfo;
import com.crfeb.tbmpt.project.model.ProFbgcInfo;
import com.crfeb.tbmpt.project.model.ProProjectinfo;
import com.crfeb.tbmpt.project.model.vo.ProFbgcQueryVo;
import com.crfeb.tbmpt.project.model.vo.ProFbgcVo;
import com.crfeb.tbmpt.project.service.IProDwgcInfoService;
import com.crfeb.tbmpt.project.service.IProFbgcInfoService;
import com.crfeb.tbmpt.project.service.IProProjectinfoService;
import com.crfeb.tbmpt.project.service.IProRProjectSectionService;
import com.crfeb.tbmpt.sys.model.User;


/**
 * <p>分部分项工程管理 控制器 Controller</p>
 * <p>模块：项目管理</p>
 * <p>日期：2017-03-28</p>
 * @version 1.0
 * @author wl_wpg
 */
@Controller
@RequestMapping("/project/fbgc")
public class ProFbgcInfoController extends BaseController {

	//@Autowired 注释，它可以对类成员变量、方法及构造函数进行标注，完成自动装配的工作。
	
	@Autowired
	private IProFbgcInfoService proFbgcInfoService;

    @Autowired
    private IProProjectinfoService proProjectinfoService;
    
    @Autowired
	private IProDwgcInfoService proDwgcInfoService;
    /**
	 * @return
	 */
	@RequestMapping(value = "", method = RequestMethod.GET)
	public String list() {
		return "project/fbgc/index";
	}
	
	
	/**
     * @param page
     * @param rows
     * @param sort
     * @param order
     * @return
     */
    @RequestMapping(value = "/dataGrid", method = RequestMethod.POST)
    @ResponseBody
    public Object dataGrid(ProFbgcVo vo,Integer page, Integer rows, String sort, String order) {
    	PageInfo pageInfo = new PageInfo(page, rows, sort, order);
        try {
		    Map<String, Object> condition = new HashMap<String, Object>();
	        pageInfo.setCondition(condition);
	        if (StringUtils.isNotBlank(vo.getProId())) {
	            condition.put("proId", vo.getProId());
	        }
	        if (StringUtils.isNotBlank(vo.getDwgcId())) {
	            condition.put("dwgcId", vo.getDwgcId());
	        }
	        if (StringUtils.isNotBlank(vo.getFbgcname())) {
	            condition.put("fbgcname", vo.getFbgcname());
	        }
	        condition.put("sqlPurview", this.sqlPurview("p","id"));
	        proFbgcInfoService.selectDataGrid(pageInfo,getCurrentUser());
        	
        } catch (Exception e) {
           e.printStackTrace();
        }
        return pageInfo;

    }
    /**
     * 根据 “单位工程Id” 查询 列表；
     * @param page
     * @param rows
     * @param sort
     * @param order
     * @return
     */
    @RequestMapping(value = "/dataGridByDwgcId", method = RequestMethod.POST)
    @ResponseBody
    public Object dataGridByDwgcId(ProDwgcInfo vo,Integer page, Integer rows, String sort, String order) {
    	PageInfo pageInfo = new PageInfo(page, rows, sort, order);
        try {
        	if(StringUtils.isBlank(vo.getId())){
        		return pageInfo;
        	}else{
        	List<ProFbgcVo> list=proFbgcInfoService.selectDataGridByDwgcId(vo.getId());
        	pageInfo.setRows(list);
        	}
        } catch (Exception e) {
           e.printStackTrace();
        }
        return pageInfo;
    	
//    	PageInfo pageInfo = new PageInfo(page, rows, sort, order);
//        Map<String, Object> condition = new HashMap<String, Object>();
//        pageInfo.setCondition(condition);
//        if (StringUtils.isNotBlank(vo.getProId())) {
//            condition.put("proId", vo.getProId());
//        }
//        if (StringUtils.isNotBlank(vo.getQjId())) {
//            condition.put("qjId", vo.getQjId());
//        }
//        if (StringUtils.isNotBlank(vo.getFbgcname())) {
//            condition.put("fbgcname", vo.getFbgcname());
//        }
//        proFbgcInfoService.selectDataGrid(pageInfo);
//        return pageInfo;
    }
    
    /**
     * 
     * @return
     */
    @RequestMapping(value = "/addPage", method = RequestMethod.GET)
    public String addPage(){
    	return "project/fbgc/add";
    }
    
    /**
     * 增加
     */
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public Object add(ProFbgcInfo fbgcInfo) {
    	proFbgcInfoService.insert(fbgcInfo);
    	return renderSuccess("添加成功！");
    }
    
    /**
     * @param id
     * @return
     */
    @RequestMapping("/delete")
    @ResponseBody
    public Object delete(String ids) {
    	
    	String[] idss = ids.split(",");
    	List<String> idlist = new ArrayList<String>();
    	Collections.addAll(idlist, idss);
    	Boolean falt = proFbgcInfoService.deleteBatchIds(idlist);
    	if(falt){
    		return renderSuccess("删除成功！");
    	}else{
    		return renderSuccess("删除异常！");
    	}
    }
    
    /**
     */
    @RequestMapping("/edit")
    @ResponseBody
    public Object edit(ProFbgcInfo fbgcInfo) {
    	proFbgcInfoService.updateSelectiveById(fbgcInfo);
        return renderSuccess("编辑成功！");
    }
    
    /**
     * 保存
     */
    @RequestMapping(value="/save",method = RequestMethod.POST)
    @ResponseBody
    public Object save(ProFbgcVo vo){
    	String json=vo.getDetails();
    	String dwgcId=vo.getDwgcId();
    	
    	//子表信息
		if(!"[]".equals(json) && null != json){
			json = json.replace("&quot;", "\"");
			 List<ProFbgcVo> list = new ArrayList<ProFbgcVo>();  
		     list = JSONObject.parseArray(json, ProFbgcVo.class);
		     List<ProFbgcInfo> addlist = new ArrayList<ProFbgcInfo>();
		     
		     for(ProFbgcVo j:list){
		    	
		    	ProFbgcInfo proFbgcInfo = new ProFbgcInfo();
	    		BeanUtils.copyProperties(j, proFbgcInfo);
	    		proFbgcInfo.setDwgcId(dwgcId);
	    		addlist.add(proFbgcInfo);
		     }
		     Map<String, Object> columnMap = new HashMap<>();
        	 columnMap.put("DWGC_ID", dwgcId);
        	 proFbgcInfoService.deleteByMap(columnMap);
        	 proFbgcInfoService.insertBatch(addlist);
		     
		}else{
			Map<String, Object> columnMap = new HashMap<>();
			columnMap.put("DWGC_ID", vo.getDwgcId());
			proFbgcInfoService.deleteByMap(columnMap);
		}
    	return renderSuccess("保存成功！");

    	
    }
    
    /**
     * 获取项目信息
     * @return
     */
    @RequestMapping(value = "/getProDic")
    @ResponseBody
    public Object getProDic() {
    	User user = getCurrentUser();
    	List<ProProjectinfo> list = proProjectinfoService.getProjectInfosByUserId(String.valueOf(user.getId()));
    	return list;
    }

    /**
     * 获取单位工程信息
     * @return
     */
    @RequestMapping(value = "/getDwgcDic", method = RequestMethod.POST)
    @ResponseBody
    public Object getDwgcDic(String proId) {
    	if(StringUtils.isEmpty(proId)){
    		return renderError("查询失败");
    	}else{
        	List<ProDwgcInfo> list = proDwgcInfoService.getDwgcInfoByProId(proId);
        	return list;
    	}
    }
    
    /**
     * 根据单位工程Id数据导出
     * @param exportDto
     * @param Dto
     * @param response
     */
	@SuppressWarnings({"unchecked", "rawtypes"})
	@RequestMapping(value="/expXlsByDwgcId")
	public void expXlsByDwgcId(ExportDto exportDto,ProFbgcQueryVo vo,String dwgcId,HttpServletResponse response){
		try {
			if(StringUtils.isNotBlank(exportDto.getIds())){
				List<String> idsList =new ArrayList<String>();
				Collections.addAll(idsList, exportDto.getIds().split(ExportDto.SPLIT_STR));
				List list = proFbgcInfoService.selectBatchIds(idsList);
				List<ProFbgcVo> dtolist = new ArrayList<ProFbgcVo>();
				 List list2 = new ArrayList();
	   			 if(list !=null && list.size()>0){
	   				 for(int i =0;i<list.size();i++){
	   					ProFbgcVo fbgcVo = new ProFbgcVo();
	   					BeanUtils.copyProperties(list.get(i),fbgcVo);
	                      
	   					ProProjectinfo projectinfo= proProjectinfoService.selectById(fbgcVo.getProId());
	   					ProDwgcInfo proDwgcInfo = proDwgcInfoService.selectById(fbgcVo.getDwgcId());
	   					fbgcVo.setProName(projectinfo.getProName());
	   					fbgcVo.setDwgcName(proDwgcInfo.getDwgcname());
	   					dtolist.add(fbgcVo);
	   				 }
	   			 }
	   			 list2 = dtolist;
				 ExportExcelUtil.writeExcelToClient(response, exportDto, ExportSetUtil.objectToListMap(list2));
			}else{
				PageInfo pageInfo = new PageInfo(0, 65530, "id", "desc");
                List<ProFbgcVo> list=proFbgcInfoService.selectDataGridByDwgcId(dwgcId);
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
     * 根据单位工程Id数据导出
     * @param exportDto
     * @param Dto
     * @param response
     */
	@SuppressWarnings({"unchecked", "rawtypes"})
	@RequestMapping(value="/expPdfByDwgcId")
	public void expPdfByDwgcId(ExportDto exportDto,ProFbgcQueryVo vo, String dwgcId,HttpServletResponse response){
		try {
			if(StringUtils.isNotBlank(exportDto.getIds())){
				List<String> idsList =new ArrayList<String>();
				 Collections.addAll(idsList, exportDto.getIds().split(ExportDto.SPLIT_STR));
				 List list = proFbgcInfoService.selectBatchIds(idsList);
				 List<ProFbgcVo> dtolist = new ArrayList<ProFbgcVo>();
				 List list2 = new ArrayList();
	   			 if(list !=null && list.size()>0){
	   				 for(int i =0;i<list.size();i++){
	   					ProFbgcVo fbgcVo = new ProFbgcVo();
	   					BeanUtils.copyProperties(list.get(i),fbgcVo);
	                      
	   					ProProjectinfo projectinfo= proProjectinfoService.selectById(fbgcVo.getProId());
	   					ProDwgcInfo proDwgcInfo = proDwgcInfoService.selectById(fbgcVo.getDwgcId());
	   					fbgcVo.setProName(projectinfo.getProName());
	   					fbgcVo.setDwgcName(proDwgcInfo.getDwgcname());
	   					dtolist.add(fbgcVo);
	   				 }
	   			 }
	   			 list2 = dtolist;
				 ExportPdfUtil.writePdfToClient(response, exportDto, ExportSetUtil.objectToListMap(list2));
			}else{
				PageInfo pageInfo = new PageInfo(0, 65530, "id", "desc");
                List<ProFbgcVo> list=proFbgcInfoService.selectDataGridByDwgcId(dwgcId);
		        Page<DgjjBzglEmpDto> page = new Page<DgjjBzglEmpDto>(pageInfo.getNowpage(), pageInfo.getSize());
		        pageInfo.setRows(list);
    	        pageInfo.setTotal(page.getTotal());
               
				ExportPdfUtil.writePdfToClient(response, exportDto, ExportSetUtil.objectToListMap(pageInfo.getRows()));
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
	@RequestMapping(value="/expXls")
	public void expXls(ExportDto exportDto,ProFbgcQueryVo vo,HttpServletResponse response){
		try {
			if(StringUtils.isNotBlank(exportDto.getIds())){
				List<String> idsList =new ArrayList<String>();
				Collections.addAll(idsList, exportDto.getIds().split(ExportDto.SPLIT_STR));
				List list = proFbgcInfoService.selectBatchIds(idsList);
				List<ProFbgcVo> dtolist = new ArrayList<ProFbgcVo>();
				
				List list2 = new ArrayList();
	   			if(list !=null && list.size()>0){
	   				 for(int i =0;i<list.size();i++){
	   					ProFbgcVo fbgcVo = new ProFbgcVo();
	   					BeanUtils.copyProperties(list.get(i),fbgcVo);
	                      
	   					ProProjectinfo projectinfo= proProjectinfoService.selectById(fbgcVo.getProId());
	   					ProDwgcInfo proDwgcInfo = proDwgcInfoService.selectById(fbgcVo.getDwgcId());
	   					fbgcVo.setProName(projectinfo.getProName());
	   					fbgcVo.setDwgcName(proDwgcInfo.getDwgcname());
	   					dtolist.add(fbgcVo);
	   				 }
	   			}
	   			list2 = dtolist;
   			 
			    ExportExcelUtil.writeExcelToClient(response, exportDto, ExportSetUtil.objectToListMap(list2));
			}else{
				PageInfo pageInfo = new PageInfo(0, 65530, "id", "desc");
                Map<String, Object> condition = new HashMap<String, Object>();
    	        pageInfo.setCondition(condition);
    	        if (StringUtils.isNotBlank(vo.getProId())) {
    	            condition.put("proId", vo.getProId());
    	        }
    	        if (StringUtils.isNotBlank(vo.getDwgcId())) {
    	            condition.put("dwgcId", vo.getDwgcId());
    	        }
    	        if (StringUtils.isNotBlank(vo.getFbgcname())) {
    	            condition.put("fbgcname", vo.getFbgcname());
    	        }
    	        condition.put("sqlPurview", this.sqlPurview("p","id"));
    	        proFbgcInfoService.selectDataGrid(pageInfo,getCurrentUser());
                
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
	public void expPdf(ExportDto exportDto,ProFbgcQueryVo vo, String dwgcId,HttpServletResponse response){
		try {
			if(StringUtils.isNotBlank(exportDto.getIds())){
				 List<String> idsList =new ArrayList<String>();
				 Collections.addAll(idsList, exportDto.getIds().split(ExportDto.SPLIT_STR));
				 List list = proFbgcInfoService.selectBatchIds(idsList);
				 List<ProFbgcVo> dtolist = new ArrayList<ProFbgcVo>();
				 List list2 = new ArrayList();
	   			 if(list !=null && list.size()>0){
	   				 for(int i =0;i<list.size();i++){
	   					ProFbgcVo fbgcVo = new ProFbgcVo();
	   					BeanUtils.copyProperties(list.get(i),fbgcVo);
	                      
	   					ProProjectinfo projectinfo= proProjectinfoService.selectById(fbgcVo.getProId());
	   					ProDwgcInfo proDwgcInfo = proDwgcInfoService.selectById(fbgcVo.getDwgcId());
	   					fbgcVo.setProName(projectinfo.getProName());
	   					fbgcVo.setDwgcName(proDwgcInfo.getDwgcname());
	   					dtolist.add(fbgcVo);
	   				 }
	   			 }
	   			 list2 = dtolist;
				 ExportPdfUtil.writePdfToClient(response, exportDto, ExportSetUtil.objectToListMap(list2));
			}else{
				PageInfo pageInfo = new PageInfo(0, 65530, "id", "desc");
                Map<String, Object> condition = new HashMap<String, Object>();
    	        pageInfo.setCondition(condition);
    	        if (StringUtils.isNotBlank(vo.getProId())) {
    	            condition.put("proId", vo.getProId());
    	        }
    	        if (StringUtils.isNotBlank(vo.getDwgcId())) {
    	            condition.put("dwgcId", vo.getDwgcId());
    	        }
    	        if (StringUtils.isNotBlank(vo.getFbgcname())) {
    	            condition.put("fbgcname", vo.getFbgcname());
    	        }
    	        condition.put("sqlPurview", this.sqlPurview("p","id"));
    	        proFbgcInfoService.selectDataGrid(pageInfo,getCurrentUser());
    	        
				ExportPdfUtil.writePdfToClient(response, exportDto, ExportSetUtil.objectToListMap(pageInfo.getRows()));
			}
		} catch (Exception e) {
			e.printStackTrace();	
		}
	}
	/**
	 * 方法说明：根据单位工程查询其下的分部工程信息
	 * @param dwgcId 单位工程id
	 * @return 返回分部工程列表信息
	 * @author:YangYj
	 * @Time: 2017年2月16日 下午3:39:30
	 */
	@RequestMapping(value="/fbgcListBydwGcId")
	@ResponseBody
	public List<ProFbgcInfo> getDwgcListByProjectId(String dwgcId){
		List<ProFbgcInfo> list = new ArrayList<ProFbgcInfo>();
		if(StringUtils.isBlank(dwgcId)) dwgcId = "-1";
		Map<String,Object> columnMap = new HashMap<String,Object>();
		columnMap.put("DWGC_ID", dwgcId);
		list = this.proFbgcInfoService.selectByMap(columnMap);
		return list;
	}
}
