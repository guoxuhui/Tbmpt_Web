package com.crfeb.tbmpt.gczl.base.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.crfeb.tbmpt.commons.base.BaseController;
import com.crfeb.tbmpt.commons.utils.BeanUtils;
import com.crfeb.tbmpt.commons.utils.DateUtils;
import com.crfeb.tbmpt.commons.utils.ExportDto;
import com.crfeb.tbmpt.commons.utils.ExportExcelUtil;
import com.crfeb.tbmpt.commons.utils.ExportPdfUtil;
import com.crfeb.tbmpt.commons.utils.ExportSetUtil;
import com.crfeb.tbmpt.commons.utils.PageInfo;
import com.crfeb.tbmpt.commons.utils.StringUtils;
import com.crfeb.tbmpt.commons.utils.UploadUtil;
import com.crfeb.tbmpt.gczl.base.model.GczlYdxjGPZLXJInfo;
import com.crfeb.tbmpt.gczl.base.model.GczlYdxjSGZLXJInfo;
import com.crfeb.tbmpt.gczl.base.model.dto.GczlYdxjGPZLXJInfoDto;
import com.crfeb.tbmpt.gczl.base.service.GczlYdxjGPZLXJInfoService;
import com.crfeb.tbmpt.open.service.IOpenPushService;
import com.crfeb.tbmpt.project.model.ProProjectinfo;
import com.crfeb.tbmpt.project.service.IProProjectinfoService;
import com.crfeb.tbmpt.sys.base.service.SysFujianService;
import com.crfeb.tbmpt.sys.controller.LoginController;

/**
 * <p>盾构施工质量巡检信息controlle</p>
 * <p>系统：</p>
 * <p>模块：基础模块</p>
 * <p>日期：2016-11-23</p>
 * @version 1.0
 * @author wangbinbin
 */
@Controller
@RequestMapping(value="/gczl/base/gczlYdxjGPZLXJInfo")
public class GczlYdxjGPZLXJInfoController extends BaseController{
	private static final Logger LOGGER = LogManager.getLogger(BaseController.class);

    @Autowired
    private GczlYdxjGPZLXJInfoService gczlYdxjGPZLXJInfoService;
    @Autowired
    private IProProjectinfoService iProProjectinfoService;
    @Autowired
    private IOpenPushService iOpenPushService;
    @Autowired
    private SysFujianService sysFujianService;
    
    private String imagePath = "/upload/gczl/pic/"+DateUtils.format(new Date(), "yyyyMMdd");

  @RequestMapping(value = "", method = RequestMethod.GET)
    public ModelAndView manager() {
	  ModelAndView modelAndView = new ModelAndView("gczl/base/gczlYdxjGPZLXJInfo");
	  ProProjectinfo projectinfo = iProProjectinfoService.getProjectInfoByUserId(getUserId());
	  if(projectinfo!=null){
		  modelAndView.addObject("projectId",projectinfo.getId());
		  modelAndView.addObject("projectName",projectinfo.getProName());
	  }
      return modelAndView;
    }

    /**
     * 获取盾构施工质量巡检信息easyUi列表
     * @param pageNo 页码
     * @param pageSize 每页条数
     * @param dto 查询条件dto
     * @return 返回查询结果信息
     */
    @RequestMapping(value = "/dataGrid", method = RequestMethod.POST)
    @ResponseBody
    public Object dataGrid(GczlYdxjGPZLXJInfoDto dto,Integer page, Integer rows, String sort, String order)
    {
        PageInfo pageInfo = new PageInfo(page, rows, sort, order);
        Map<String, Object> condition = new HashMap<String, Object>();
        condition.put("gcBh", dto.getGcBh());
        condition.put("typeName", dto.getTypeName());
        condition.put("sbzt", dto.getSbzt());
        condition.put("xjtime", dto.getXjtime());
        condition.put("zgzt", dto.getZgzt());
        condition.put("dw", dto.getDw());
        condition.put("shzt", dto.getShzt());
        condition.put("xjbm", dto.getXjbm());
        condition.put("xjry", dto.getXjry());
        condition.put("hh", dto.getHh());
        condition.put("xjtimeStr", dto.getXjtimeStr());
        condition.put("xjtimeEnd", dto.getXjtimeEnd());
        condition.put("qkms", dto.getQkms());
        condition.put("sqlPurview", this.sqlPurview(null,null));
        pageInfo.setCondition(condition);
        gczlYdxjGPZLXJInfoService.selectDataGrid(pageInfo);
        return pageInfo;
    }

    /**
     * 添加盾构施工质量巡检信息
     * @param gczlYdxjGPZLDDInfo 要新增的实体信息
     * @return 返回保存成功/失败的提示信息
     */
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public Object add(GczlYdxjGPZLXJInfoDto gczlYdxjGPZLXJInfoDto) {
    	String errmessage = gczlYdxjGPZLXJInfoService.checkClumIfexits(gczlYdxjGPZLXJInfoDto,new String[]{"typeName","xlBh","hh","dw","xjtime"});
    	if(errmessage!=null){
    		log(null, false, errmessage);
    		return renderError(errmessage);
    	}
    	gczlYdxjGPZLXJInfoService.insertDto(gczlYdxjGPZLXJInfoDto,getCurrentUser());
    	log(null, true, "盾构施工质量巡检信息添加成功！");
        return renderSuccess("添加成功！");
    }
    
    /**
     * 修改盾构施工质量巡检信息
     * @param gczlYdxjGPZLDDInfo 要修改的实体信息
     * @return 返回编辑成功/失败的提示信息
     */
    @RequestMapping("/edit")
    @ResponseBody
    public Object edit(HttpServletRequest request,GczlYdxjGPZLXJInfoDto gczlYdxjGPZLXJInfoDto) {
    	String errorMessage = "";
    	String optionName = gczlYdxjGPZLXJInfoDto.getOptionName();
    	GczlYdxjGPZLXJInfo gczlYdxjGPZLXJInfo = gczlYdxjGPZLXJInfoService.selectById(gczlYdxjGPZLXJInfoDto.getId());
    	GczlYdxjGPZLXJInfoDto dbDto = gczlYdxjGPZLXJInfoService.findDtoById(gczlYdxjGPZLXJInfoDto.getId());
    	String qjAndXl = dbDto.getQlName()+dbDto.getXlName();
    	if(gczlYdxjGPZLXJInfo==null)return renderError("盾构施工质量巡检信息已不存在，不能"+optionName+"!");
    	try {
	    	if(optionName.equals("修改")){
	    		errorMessage = gczlYdxjGPZLXJInfoService.checkClumIfexits(gczlYdxjGPZLXJInfoDto,new String[]{"typeName","xlBh","hh","dw","xjtime"});
	    		if(StringUtils.isNotBlank(errorMessage)){
	    			log(null, false, errorMessage);
	    			return renderError(errorMessage);
	    		}
	    		errorMessage = gczlYdxjGPZLXJInfoService.updateDto(gczlYdxjGPZLXJInfoDto,getCurrentUser());
	    		if(StringUtils.isNotBlank(errorMessage)){
	    			log("", false, errorMessage);
	    			return renderError(errorMessage);
	    		}
	    		log(null, true, "编辑操作：成功!");
	    		return renderSuccess("编辑操作：成功!");
	    		
	    	}else if(optionName.equals("审核")){
	    		errorMessage = gczlYdxjGPZLXJInfoService.shengHe(gczlYdxjGPZLXJInfoDto, getCurrentUser());
	    		if(StringUtils.isNotBlank(errorMessage)){
	    			log(gczlYdxjGPZLXJInfoDto.getShzt(), false, errorMessage);
	    			return renderError(errorMessage);
	    		}
	    		//iOpenPushService.gczlPushMsg(getCurrentUser(),"/gczl/base/gczlYdxjGPZLXJInfo", qjAndXl+"有新的盾构管片质量巡检信息已审核");
	    		iOpenPushService.gczlPushMsgV2("管片", "已审核", gczlYdxjGPZLXJInfo, getCurrentUser(),"/gczl/base/gczlYdxjGPZLXJInfo", qjAndXl+"有新的盾构管片质量巡检信息已审核");
	    		log(gczlYdxjGPZLXJInfoDto.getShzt(), true, "审核操作：成功!");
	        	return renderSuccess("审核操作：成功!");
	        	
	    	}else if(optionName.equals("整改")){
	    		errorMessage = gczlYdxjGPZLXJInfoService.zhengGai(gczlYdxjGPZLXJInfoDto, getCurrentUser());
	    		if(StringUtils.isNotBlank(errorMessage)){
	    			log("整改", false, errorMessage);
	    			return renderError(errorMessage);
	    		}
	    		//iOpenPushService.gczlPushMsg(getCurrentUser(),"/gczl/base/gczlYdxjGPZLXJInfo", qjAndXl+"有新的盾构管片质量巡检信息已整改");
	    		iOpenPushService.gczlPushMsgV2("管片", "已整改", gczlYdxjGPZLXJInfo, getCurrentUser(),"/gczl/base/gczlYdxjGPZLXJInfo", qjAndXl+"有新的盾构管片质量巡检信息已整改");
	    		log("整改", true, "整改操作：成功!");
	            return renderSuccess("整改操作：成功!");
	    	}
    	} catch (Exception e) {
			// TODO: handle exception
		}
    	return null;
    }

    /**
     * 删除盾构施工质量巡检信息
     * @param ids 要删除的数据的ID集合
     * @return 返回删除成功/失败的提示信息
     */
    @RequestMapping("/delete")
    @ResponseBody
    public Object delete(HttpServletRequest request,String ids) {
    	String[] idss = ids.split(",");
    	List<String> idlist = new ArrayList<String>();
    	Collections.addAll(idlist, idss);
    	List<GczlYdxjGPZLXJInfo> list = gczlYdxjGPZLXJInfoService.selectBatchIds(idlist);
    	gczlYdxjGPZLXJInfoService.deleteBatchIds(idlist);
    	//=======================删除记录所关联的图片====================================//
	   	 String rootPath =request.getSession().getServletContext().getRealPath("/");
	   	 try {
	    	 for(GczlYdxjGPZLXJInfo entity :list){
			    this.sysFujianService.deleteFujianByForeignId(rootPath, entity.getId());
	    	 }
	   	 } catch (Exception e) {
	   		 e.printStackTrace();
	   		 return renderError("删除附件失败！");
	   	 }
        return renderSuccess("删除成功！");
    }

//    /**
//     * 整改盾构施工质量巡检信息
//     * @param gczlYdxjGPZLDDInfo 要修改的实体信息
//     * @return 返回编辑成功/失败的提示信息
//     */
//    @RequestMapping("/zhg")
//    @ResponseBody
//    public Object zhg(GczlYdxjGPZLXJInfoDto gczlYdxjGPZLXJInfoDto) {
//    	gczlYdxjGPZLXJInfoService.zhengGai(gczlYdxjGPZLXJInfoDto, getCurrentUser());
//        return renderSuccess("整改操作：成功!");
//    }
//    /**
//     * 审核盾构施工质量巡检信息
//     * @param gczlYdxjGPZLDDInfo 要修改的实体信息
//     * @return 返回编辑成功/失败的提示信息
//     */
//    @RequestMapping("/shh")
//    @ResponseBody
//    public Object shh(GczlYdxjGPZLXJInfoDto gczlYdxjGPZLXJInfoDto) {
//    	gczlYdxjGPZLXJInfoService.shengHe(gczlYdxjGPZLXJInfoDto, getCurrentUser());
//    	return renderSuccess("审核操作：成功!");
//    }
    /**
     * 上报盾构施工质量巡检信息
     * @param gczlYdxjGPZLDDInfo 要修改的实体信息
     * @return 返回编辑成功/失败的提示信息
     */
    @RequestMapping("/shb")
    @ResponseBody
    public Object shb(String ids) {
    	String[] idss = ids.split(",");
    	String errorMessage = "";
    	if(idss.length>0)
    	for (String idstr : idss) { 
    		String[] id= idstr.split("#");
    		errorMessage =  gczlYdxjGPZLXJInfoService.checkIfguoQi(id[0],id[1]);
    		if(StringUtils.isNotBlank(errorMessage)){
    			return renderError(errorMessage);
    		}
		}
    	for (String idstr : idss) { 
    		String[] id= idstr.split("#");
    		if(!id[0].trim().equals(""))gczlYdxjGPZLXJInfoService.sendUp(id[0], getCurrentUser());
    		GczlYdxjGPZLXJInfo info = gczlYdxjGPZLXJInfoService.findById(id[0]);
    		//iOpenPushService.gczlPushMsg(getCurrentUser(),"/gczl/base/gczlYdxjGPZLXJInfo", "有新的盾构施工质量巡检信息上报");
        	iOpenPushService.gczlPushMsgV2("管片", "已上报", info, getCurrentUser(),"/gczl/base/gczlYdxjGPZLXJInfo", "有新的盾构管片质量巡检信息已上报");
    	}
    	
    	log("上报", true, "上报数据ID："+ids);
    	return renderSuccess("上报操作：成功!");
    }
   

    /**     
    * 数据导出     
    * @param exportDto     
    * @param Dto     
    * @param response     
    */
    @SuppressWarnings({"unchecked", "rawtypes"})
	@RequestMapping(value="/expXls")
	public void expXls(ExportDto exportDto,GczlYdxjGPZLXJInfoDto dto, HttpServletResponse response){
		try {
			if(StringUtils.isNotBlank(exportDto.getIds())){
    			List<String> idsList =new ArrayList<String>();
				Collections.addAll(idsList, exportDto.getIds().split(ExportDto.SPLIT_STR));
				List list = gczlYdxjGPZLXJInfoService.selectBatchIds(idsList);
				ExportExcelUtil.writeExcelToClient(response, exportDto, ExportSetUtil.objectToListMap(list));
    		}else{
    	        PageInfo pageInfo = new PageInfo(0, 65530, "id", "desc");
		        Map<String, Object> condition = new HashMap<String, Object>();
		        condition.put("sqlPurview", this.sqlPurview(null,null));
    	        pageInfo.setCondition(condition);
    	        gczlYdxjGPZLXJInfoService.selectDataGrid(pageInfo);
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
     public void expPdf(ExportDto exportDto,GczlYdxjGPZLXJInfo dto, HttpServletResponse response){
	     	try {
	     		if(StringUtils.isNotBlank(exportDto.getIds())){
	     			List<String> idsList =new ArrayList<String>();
	     			 Collections.addAll(idsList, exportDto.getIds().split(ExportDto.SPLIT_STR));
	     			 List list = gczlYdxjGPZLXJInfoService.selectBatchIds(idsList);
	     			 ExportPdfUtil.writePdfToClient(response, exportDto, ExportSetUtil.objectToListMap(list));
	     		}else{
	     	        PageInfo pageInfo = new PageInfo(0, 65530, "id", "desc");
	     	        Map<String, Object> condition = new HashMap<String, Object>();
	     	        condition.put("sqlPurview", this.sqlPurview(null,null));
	     	        pageInfo.setCondition(condition);
	     	        gczlYdxjGPZLXJInfoService.selectDataGrid(pageInfo);
	     			ExportPdfUtil.writePdfToClient(response, exportDto, ExportSetUtil.objectToListMap(pageInfo.getRows()));
	     		}
	     	} catch (Exception e) {
	     		e.printStackTrace();
	     	}
     }
     
     /**
      * 上传文件
      */
  	@RequestMapping("/upload")  
  	@ResponseBody
  	public Object upload(@RequestParam(value = "xjZp", required = false) MultipartFile file , HttpServletRequest request) {  
  		
  		String fileName = file.getOriginalFilename();
  		String type = fileName.substring(fileName.lastIndexOf(".")+1);
  		String tempUid =UUID.randomUUID().toString(); 
  		String originalFileName="Y"+tempUid;//原始图片保存文件名
  		String compressFileNewName = "S"+tempUid;//缩略图片保存文件名
  		
  		UploadUtil uploadutil = new UploadUtil();  
  		String errorMessage = uploadutil.uploadImage(request, file, originalFileName, compressFileNewName, imagePath);//上传原始图片并生成对应的缩略图
  		if(StringUtils.isNotBlank(errorMessage)){
  			return renderError(errorMessage);
  		}
  		
        //=======================若存在原有图片则删除记录所关联的图片====================================//
  		 String rootPath =request.getSession().getServletContext().getRealPath("/");
  		 String id = (String) request.getParameter("id");//业务主键
          GczlYdxjGPZLXJInfo oldEntity = this.gczlYdxjGPZLXJInfoService.selectById(id);
          if(null !=oldEntity){
         	 if(StringUtils.isNotBlank(oldEntity.getXjzp())){
         		 //删除原始图片
         		 uploadutil.deleteFile(rootPath + oldEntity.getXjzp());
         	 }
         	 if(StringUtils.isNotBlank(oldEntity.getXjzpslt())){
         		 //删除缩略图片
         		 uploadutil.deleteFile(rootPath + oldEntity.getXjzpslt());
         	 }
         	 //=======================更新原始图片和缩略图片路径到数据库==================//
         	 oldEntity.setXjzp(imagePath+"/"+originalFileName+"."+type);
         	 oldEntity.setXjzpslt(imagePath+"/"+compressFileNewName+"."+type);
         	 this.gczlYdxjGPZLXJInfoService.update(oldEntity);
          }
          return renderSuccess("上传图片成功!");
      }

}