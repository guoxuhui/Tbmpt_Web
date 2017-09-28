package com.crfeb.tbmpt.aqxj.xjdgl.controller;

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
import org.springframework.web.servlet.ModelAndView;

import com.crfeb.tbmpt.aqxj.xjdgl.model.AqxjXjd;
import com.crfeb.tbmpt.aqxj.xjdgl.model.dto.AqxjXjdDto;
import com.crfeb.tbmpt.aqxj.xjdgl.service.AqxjXjdService;
import com.crfeb.tbmpt.aqxj.xjdgl.service.AqxjXjnrService;
import com.crfeb.tbmpt.commons.base.BaseController;
import com.crfeb.tbmpt.commons.utils.ExportDto;
import com.crfeb.tbmpt.commons.utils.ExportExcelUtil;
import com.crfeb.tbmpt.commons.utils.ExportPdfUtil;
import com.crfeb.tbmpt.commons.utils.ExportSetUtil;
import com.crfeb.tbmpt.commons.utils.PageInfo;
import com.crfeb.tbmpt.commons.utils.StringUtils;
import com.crfeb.tbmpt.project.model.ProProjectinfo;
import com.crfeb.tbmpt.project.service.IProProjectinfoService;
import com.crfeb.tbmpt.sys.model.Orgz;
import com.crfeb.tbmpt.sys.model.User;
import com.crfeb.tbmpt.sys.service.IOrgzService;

/**
 * <p>安全巡检点controlle</p>
 * <p>系统：工程项目安全巡检系统</p>
 * <p>模块：巡检点管理</p>
 * <p>日期：2017-05-26</p>
 * @version 1.0
 * @author zhuyabing
 */
@Controller
@RequestMapping(value="/aqxj/xjdgl/aqxjXjd")
public class AqxjXjdController extends BaseController{
	@Autowired
    private IProProjectinfoService iProProjectinfoService;
    @Autowired
    private AqxjXjdService aqxjXjdService;
    @Autowired
    private IOrgzService orgzService;
    @Autowired
	private IProProjectinfoService proProjectinfoService;
    @Autowired
    private AqxjXjnrService aqxjXjnrService;

    
    
   //返回巡检点页面 
  @RequestMapping(value = "", method = RequestMethod.GET)
    public ModelAndView manager() {
	  ModelAndView modelAndView = new ModelAndView("aqxj/xjdgl/aqxjXjd");
	  ProProjectinfo projectinfo = iProProjectinfoService.getProjectInfoByUserId(getUserId());
	  if(projectinfo!=null){
		  modelAndView.addObject("projectId",projectinfo.getId());
		  modelAndView.addObject("projectName",projectinfo.getProName());
	  }
      return modelAndView;
    }
  
  
  //返回巡检信息引用页面 
  @RequestMapping(value = "xxyy", method = RequestMethod.GET)
    public ModelAndView xxyyManager() {
	  ModelAndView modelAndView = new ModelAndView("aqxj/xjxxyy/aqxjXjxxyy");
	  ProProjectinfo projectinfo = iProProjectinfoService.getProjectInfoByUserId(getUserId());
	  Orgz orgz=orgzService.getOrgzInfoByUserId(getUserId());
	 if(projectinfo!=null){
		  modelAndView.addObject("projectId",projectinfo.getId());
		  modelAndView.addObject("projectName",projectinfo.getProName());
	  }
	 if(orgz!=null){
		  modelAndView.addObject("orgzId",orgz.getId());
		  modelAndView.addObject("OrgzName",orgz.getName());
	  }
      return modelAndView;
    }

    /**
     * 获取安全巡检点easyUi列表
     * @param pageNo 页码
     * @param pageSize 每页条数
     * @param dto 查询条件dto
     * @return 返回查询结果信息
     */
    @RequestMapping(value = "/dataGrid", method = RequestMethod.POST)
    @ResponseBody
    public Object dataGrid(AqxjXjdDto dto,Integer page, Integer rows, String sort, String order)
    {
        PageInfo pageInfo = new PageInfo(page, rows, sort, order);
        Map<String, Object> condition = new HashMap<String, Object>();
        condition.put("projectName", dto.getProjectName());
        condition.put("typeName", dto.getTypeName());
        condition.put("mingCheng", dto.getMingCheng());
        condition.put("zeRenrmc", dto.getZeRenrmc());
        condition.put("jianDurmc", dto.getJianDurmc());
        pageInfo.setCondition(condition);
        try {
           aqxjXjdService.selectDataGrid(pageInfo);
        } catch (Exception e) {
           e.printStackTrace();
        }
        return pageInfo;
    }

    @RequestMapping(value = "/getSxuHao", method = RequestMethod.POST)
    @ResponseBody
    public List<AqxjXjd> getSxuHao() {
        return aqxjXjdService.getAqxjXjd();
    }
    
    /**
     * 添加安全巡检点
     * @param gczlYdxjGPZLDDInfo 要新增的实体信息
     * @return 返回保存成功/失败的提示信息
     */
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public Object add(AqxjXjdDto aqxjXjdDto) {
    	String errmessage = "";
        try {
    	errmessage = aqxjXjdService.checkClumIfexits(aqxjXjdDto,new String[]{"mingCheng","projectName"});
	    log(null, false, errmessage);
    	if(errmessage!=null) return renderError(errmessage);
          aqxjXjdService.save(aqxjXjdDto,getCurrentUser());
          log(null, true, "新增成功!");
        } catch (Exception e) {
          log(null, false, "新增失败!");
          e.printStackTrace();
        }
        return renderSuccess("添加成功！");
    }

    /**
     * 删除安全巡检点
     * @param ids 要删除的数据的ID集合
     * @return 返回删除成功/失败的提示信息
     */
    @RequestMapping("/delete")
    @ResponseBody
    public Object delete(String ids) {
    	String[] idss = ids.split(",");//把一个字符串分割成字符串数组
    	List<String> idlist = new ArrayList<String>();
    	Collections.addAll(idlist, idss);
    	aqxjXjdService.deleteBatchIds(idlist);
    	//删除巡检内容
    	List<String> idlistNr= new ArrayList<String>();
    	String idsNr=aqxjXjnrService.selectXjnrByItems(idss);
    	String[] idsss = idsNr.split(",");
    	Collections.addAll(idlistNr, idsss);
    	aqxjXjnrService.deleteBatchIds(idlistNr);
    	log(null, true, "删除："+ids);
    	return renderSuccess("删除成功！");
    }

    
    
    /**
     * 【巡检点复制】复制巡检点
     */
    @RequestMapping("/copyXjdXjd")
    @ResponseBody
    public Object copyXjdXjd(String ids,String currId,String currProName,String currFengL) {
    	String[] idss = ids.split(",");//把一个字符串分割成字符串数组
    	List<String> idlist = new ArrayList<String>();
    	Collections.addAll(idlist, idss);
    	String str=aqxjXjdService.copyAqxjxjdXjd(idlist,currId,currProName,currFengL);
    		return renderSuccess(str);
    }
    
    
    /**
     * 【巡检点复制】复制巡检内容
     */
    @RequestMapping("/copyXjdXjnr")
    @ResponseBody
    public Object copyXjdXjnr(String ids,String currId) {
    	String[] idss = ids.split(",");//把一个字符串分割成字符串数组
    	List<String> idlist = new ArrayList<String>();
    	Collections.addAll(idlist, idss);
    	String str=aqxjXjnrService.copyAqxjxjdXjnr(idlist,currId);
    		return renderSuccess(str);
    }
    
    
    /**
     * 修改安全巡检点
     * @param gczlYdxjGPZLDDInfo 要修改的实体信息
     * @return 返回编辑成功/失败的提示信息
     */
    @RequestMapping("/edit")
    @ResponseBody
    public Object edit(AqxjXjdDto aqxjXjdDto) {
    	String errmessage = "";
        try {
    	errmessage = aqxjXjdService.checkClumIfexits(aqxjXjdDto,new String[]{"mingCheng","projectName"});
    	if(errmessage!=null) return renderError(errmessage);
           errmessage = aqxjXjdService.update(aqxjXjdDto,getCurrentUser());
           if(StringUtils.isNotBlank(errmessage)){
             log(null, false, errmessage);
             return renderError(errmessage);
           }
           log(null, true, "编辑成功:"+aqxjXjdDto.getId());
        } catch (Exception e) {
           log(null, false, "编辑失败!");
           e.printStackTrace();
        }
	      log(null, true, errmessage);
        return renderSuccess("编辑成功!");
    }

    /**     
    * 数据导出     
    * @param exportDto     
    * @param Dto     
    * @param response     
    */
    @SuppressWarnings({"unchecked", "rawtypes"})
    	@RequestMapping(value="/expXls")
    	public void expXls(ExportDto exportDto,AqxjXjd dto, HttpServletResponse response){
    		try {
    			if(StringUtils.isNotBlank(exportDto.getIds())){
	    			List<String> idsList =new ArrayList<String>();
    				Collections.addAll(idsList, exportDto.getIds().split(ExportDto.SPLIT_STR));
    				List list = aqxjXjdService.selectBatchIds(idsList);
    				ExportExcelUtil.writeExcelToClient(response, exportDto, ExportSetUtil.objectToListMap(list));
	    		}else{
	    	        PageInfo pageInfo = new PageInfo(0, 65530, "id", "desc");
    		        Map<String, Object> condition = new HashMap<String, Object>();
	    	        pageInfo.setCondition(condition);
	    	        aqxjXjdService.selectDataGrid(pageInfo);
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
     public void expPdf(ExportDto exportDto,AqxjXjd dto, HttpServletResponse response){
	     	try {
	     		if(StringUtils.isNotBlank(exportDto.getIds())){
	     			List<String> idsList =new ArrayList<String>();
	     			 Collections.addAll(idsList, exportDto.getIds().split(ExportDto.SPLIT_STR));
	     			 List list = aqxjXjdService.selectBatchIds(idsList);
	     			 ExportPdfUtil.writePdfToClient(response, exportDto, ExportSetUtil.objectToListMap(list));
	     		}else{
	     	        PageInfo pageInfo = new PageInfo(0, 65530, "id", "desc");
	     	        Map<String, Object> condition = new HashMap<String, Object>();
	     	        pageInfo.setCondition(condition);
	     	        aqxjXjdService.selectDataGrid(pageInfo);
	     			ExportPdfUtil.writePdfToClient(response, exportDto, ExportSetUtil.objectToListMap(pageInfo.getRows()));
	     		}
	     	} catch (Exception e) {
	     		e.printStackTrace();
	     	}
     }

}