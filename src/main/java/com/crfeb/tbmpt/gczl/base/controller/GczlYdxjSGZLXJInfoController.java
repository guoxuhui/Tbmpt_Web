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
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.crfeb.tbmpt.commons.base.BaseController;
import com.crfeb.tbmpt.commons.utils.DateUtils;
import com.crfeb.tbmpt.commons.utils.ExportDto;
import com.crfeb.tbmpt.commons.utils.ExportExcelUtil;
import com.crfeb.tbmpt.commons.utils.ExportPdfUtil;
import com.crfeb.tbmpt.commons.utils.ExportSetUtil;
import com.crfeb.tbmpt.commons.utils.PageInfo;
import com.crfeb.tbmpt.commons.utils.StringUtils;
import com.crfeb.tbmpt.commons.utils.UploadUtil;
import com.crfeb.tbmpt.gczl.base.model.GczlYdxjSGZLXJInfo;
import com.crfeb.tbmpt.gczl.base.model.dto.GczlYdxjSGZLXJInfoDto;
import com.crfeb.tbmpt.gczl.base.service.GczlYdxjSGZLXJInfoService;
import com.crfeb.tbmpt.project.model.ProFbgcInfo;
import com.crfeb.tbmpt.project.model.ProProjectinfo;
import com.crfeb.tbmpt.project.service.IProFbgcInfoService;
import com.crfeb.tbmpt.project.service.IProProjectinfoService;
import com.crfeb.tbmpt.sys.base.service.SysFujianService;
import com.crfeb.tbmpt.sys.model.User;

/**
 * <p>结构施工质量巡检管理controlle</p>
 * <p>系统：</p>
 * <p>模块：基础模块</p>
 * <p>日期：2016-11-24</p>
 * @version 1.0
 * @author wangbinbin
 */
@Controller
@RequestMapping(value="/gczl/base/gczlYdxjSGZLXJInfo")
public class GczlYdxjSGZLXJInfoController extends BaseController{
	private static final Logger LOGGER = LogManager.getLogger(GczlYdxjSGZLXJInfoController.class);

    @Autowired
    private GczlYdxjSGZLXJInfoService gczlYdxjSGZLXJInfoService;
    @Autowired
    private IProProjectinfoService iProProjectinfoService;
    @Autowired
    private IProFbgcInfoService  iProFbgcInfoService;
    @Autowired
    private SysFujianService sysFujianService;
    
    
   private String imagePath = "/upload/gczl/pic/"+DateUtils.format(new Date(), "yyyyMMdd");

  @RequestMapping(value = "", method = RequestMethod.GET)
    public String manager() {
        return "gczl/base/gczlYdxjSGZLXJInfo";
    }

    /**
     * 获取结构施工质量巡检管理easyUi列表
     * @param pageNo 页码
     * @param pageSize 每页条数
     * @param dto 查询条件dto
     * @return 返回查询结果信息
     */
    @RequestMapping(value = "/dataGrid", method = RequestMethod.POST)
    @ResponseBody
    public Object dataGrid(GczlYdxjSGZLXJInfoDto dto,Integer page, Integer rows, String sort, String order)
    {
    	
        PageInfo pageInfo = new PageInfo(page, rows, sort, order);
        Map<String, Object> condition = new HashMap<String, Object>();
        condition.put("sgNr", dto.getSgNr());
        condition.put("gcBh", dto.getGcBh());
        condition.put("dwgcBh", dto.getDwgcBh());
        condition.put("fbGcbh", dto.getFbGcbh());
        condition.put("jtWz", dto.getJtWz());
        condition.put("zlQx", dto.getZlQx());
        condition.put("xjTime", dto.getXjTime());
        condition.put("sbZt", dto.getSbZt());
        condition.put("shZt", dto.getShZt());
        condition.put("zgZt", dto.getZgZt());
        condition.put("startXjTime", dto.getStartXjTime());
        condition.put("endXjTime", dto.getEndXjTime());
        String autoritySql = this.sqlPurview(null,null);
        condition.put("autoritySql", autoritySql);
        pageInfo.setCondition(condition);
        gczlYdxjSGZLXJInfoService.selectDataGrid(pageInfo);
        return pageInfo;
    }

    /**
     * 添加结构施工质量巡检管理
     * @param gczlYdxjGPZLDDInfo 要新增的实体信息
     * @return 返回保存成功/失败的提示信息
     */
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public Object add(GczlYdxjSGZLXJInfoDto gczlYdxjSGZLXJInfo) {
    	gczlYdxjSGZLXJInfo.setWhTime(DateUtils.getToday());
    	User user = this.getCurrentUser();
    	gczlYdxjSGZLXJInfo.setXjRy(user.getId());
    	gczlYdxjSGZLXJInfo.setXjBm(user.getOrgzId());
    	gczlYdxjSGZLXJInfo.setSbZt("未上报");
    	gczlYdxjSGZLXJInfo.setShZt("未审核");
    	gczlYdxjSGZLXJInfo.setZgZt("未整改");
    	String errorMesage = this.gczlYdxjSGZLXJInfoService.checkClumIfexits(gczlYdxjSGZLXJInfo, new String[]{"gcBh","dwgcBh","sgNr","fbGcbh","sgd","jtWz","zlQx","xjTime"});
    	if(StringUtils.isNotBlank(errorMesage)){
    		return renderError("相同结构施工质量巡检信息已存在，新增失败。");
    	}else{
    		errorMesage = gczlYdxjSGZLXJInfoService.insert(gczlYdxjSGZLXJInfo,user);
    		if(StringUtils.isNotBlank(errorMesage)){
    			LOGGER.error("添加结构施工质量巡检数据失败。");
    			this.log(null, false, "用户"+user.getName()+"添加数据失败。");
    			return renderError(errorMesage);
    		}
    		LOGGER.info("添加结构施工质量巡检数据成功。");
    		this.log(null, true, "用户"+user.getName()+"添加数据成功。");
    		return renderSuccess("添加成功！");
    	}
    }

    /**
     * 删除结构施工质量巡检管理
     * @param ids 要删除的数据的ID集合
     * @return 返回删除成功/失败的提示信息
     */
    @RequestMapping("/delete")
    @ResponseBody
    public Object delete(HttpServletRequest request,String ids) {
    	User user = this.getCurrentUser();
    	String[] idss = ids.split(",");
    	List<String> idlist = new ArrayList<String>();
    	Collections.addAll(idlist, idss);
    	List<GczlYdxjSGZLXJInfo> list = gczlYdxjSGZLXJInfoService.selectBatchIds(idlist);
    	gczlYdxjSGZLXJInfoService.deleteBatchIds(idlist);
    	//=======================删除记录所关联的图片====================================//
    	 String rootPath =request.getSession().getServletContext().getRealPath("/");
    	 try {
	    	 for(GczlYdxjSGZLXJInfo entity :list){
			    this.sysFujianService.deleteFujianByForeignId(rootPath, entity.getId());
	    	 }
    	 } catch (Exception e) {
    		 e.printStackTrace();
    		 return renderError("删除附件失败！");
    	 }
    	LOGGER.info("删除结构施工质量巡检数据成功。");
 		this.log(null, true, "用户"+user.getName()+"删除数据成功:"+ids);
        return renderSuccess("删除成功！");
    }

    /**
     * 修改结构施工质量巡检管理
     * @param gczlYdxjGPZLDDInfo 要修改的实体信息
     * @return 返回编辑成功/失败的提示信息
     */
    @RequestMapping(value="/edit", method = RequestMethod.POST)
    @ResponseBody
    public Object edit(GczlYdxjSGZLXJInfoDto gczlYdxjSGZLXJInfo,HttpServletRequest request) {
    	User user = this.getCurrentUser();
    	GczlYdxjSGZLXJInfo oldEntity = this.gczlYdxjSGZLXJInfoService.selectById(gczlYdxjSGZLXJInfo.getId());
    	if(null == oldEntity || StringUtils.isBlank(oldEntity.getId())){
    		return renderError("结构施工质量巡检信息已不存在，不能修改。");
    	}
    	String errorMesage = this.gczlYdxjSGZLXJInfoService.checkClumIfexits(gczlYdxjSGZLXJInfo, new String[]{"gcBh","dwgcBh","sgNr","fbGcbh","sgd","jtWz","zlQx","xjTime"});
    	if(StringUtils.isNotBlank(errorMesage)){
    		return renderError("相同结构施工质量巡检信息已存在，编辑失败。");
    	}
    	gczlYdxjSGZLXJInfo.setSbZt(oldEntity.getSbZt());
    	gczlYdxjSGZLXJInfo.setShZt(oldEntity.getShZt());
    	gczlYdxjSGZLXJInfo.setZgZt(oldEntity.getZgZt());
    	gczlYdxjSGZLXJInfo.setXjBm(oldEntity.getXjBm());
    	gczlYdxjSGZLXJInfo.setWhTime(oldEntity.getWhTime());
    	gczlYdxjSGZLXJInfo.setXjRy(oldEntity.getXjRy());
    	gczlYdxjSGZLXJInfo.setSbTime(oldEntity.getSbTime());
    	gczlYdxjSGZLXJInfo.setSbRy(oldEntity.getSbRy());
    	gczlYdxjSGZLXJInfo.setSbBm(oldEntity.getSbBm());
    	gczlYdxjSGZLXJInfo.setSbRy(oldEntity.getSbRy());
    	gczlYdxjSGZLXJInfo.setShBm(oldEntity.getShBm());
    	gczlYdxjSGZLXJInfo.setShRy(oldEntity.getShRy());
    	gczlYdxjSGZLXJInfo.setShSm(oldEntity.getShSm());
    	gczlYdxjSGZLXJInfo.setShTime(oldEntity.getShTime());
    	gczlYdxjSGZLXJInfo.setZgJg(oldEntity.getZgJg());
    	gczlYdxjSGZLXJInfo.setXjZp(oldEntity.getXjZp());
    	errorMesage = gczlYdxjSGZLXJInfoService.update(gczlYdxjSGZLXJInfo,user);
    	if(StringUtils.isNotBlank(errorMesage)){
    		LOGGER.error("更新结构施工质量巡检数据失败。");
     		this.log(null, false, "用户"+user.getName()+"更新数据失败。"+gczlYdxjSGZLXJInfo.getId());
    		return renderError(errorMesage);
    	}
    	LOGGER.info("更新结构施工质量巡检数据成功。");
 		this.log(null, true, "用户"+user.getName()+"更新数据成功。"+gczlYdxjSGZLXJInfo.getId());
        return renderSuccess("编辑成功!");
    }
 
    /**
     * 上传文件
     */
 	@RequestMapping("/upload")  
 	@ResponseBody
 	public Object upload(@RequestParam(value = "xjZp", required = false) MultipartFile[] files , HttpServletRequest request) {  
 		 for (MultipartFile mf : files) {  
             if(!mf.isEmpty()){  
                 //生成uuid作为文件名称  
                 String uuid = UUID.randomUUID().toString().replaceAll("-","");  
                 //获得文件类型（可以判断如果不是图片，禁止上传）  
                 String contentType=mf.getContentType();  
                 //获得文件后缀名称  
                 String imageName=contentType.substring(contentType.indexOf("/")+1);  
//                 path="/static/images/"+uuid+"."+imageName;  
//                 mf.transferTo(new File(pathRoot+path));  
//                 listImagePath.add(path);  
             }  
         }  
 		MultipartFile  file = files[0];
 		
 		
 		
 		User user = this.getCurrentUser();
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
         GczlYdxjSGZLXJInfo oldEntity = this.gczlYdxjSGZLXJInfoService.selectById(id);
         if(null !=oldEntity){
        	 if(StringUtils.isNotBlank(oldEntity.getXjZp())){
        		 //删除原始图片
        		 uploadutil.deleteFile(rootPath + oldEntity.getXjZp());
        	 }
        	 if(StringUtils.isNotBlank(oldEntity.getXjZpslt())){
        		 //删除缩略图片
        		 uploadutil.deleteFile(rootPath + oldEntity.getXjZpslt());
        	 }
        	 //=======================更新原始图片和缩略图片路径到数据库==================//
        	 oldEntity.setXjZp(imagePath+"/"+originalFileName+"."+type);
        	 oldEntity.setXjZpslt(imagePath+"/"+compressFileNewName+"."+type);
        	 GczlYdxjSGZLXJInfoDto dto = new GczlYdxjSGZLXJInfoDto();
        	 BeanUtils.copyProperties(oldEntity, dto);
        	 errorMessage = this.gczlYdxjSGZLXJInfoService.update(dto,user);
        	 if(StringUtils.isNotBlank(errorMessage)){
        		LOGGER.error("结构施工质量巡检上传现场图片失败。");
          		this.log("上传图片", false, "用户"+user.getName()+"上传图片失败。"+id);
        		return renderError(errorMessage);
        	 }
         }
         LOGGER.info("结构施工质量巡检上传现场图片成功。");
  		 this.log("上传图片", true, "用户"+user.getName()+"上传图片成功。"+id);
         return renderSuccess("上传图片成功!");
     }

    /**     
    * 数据导出     
    * @param exportDto     
    * @param Dto     
    * @param response     
    */
    @SuppressWarnings({"unchecked", "rawtypes"})
    	@RequestMapping(value="/expXls")
    	public void expXls(ExportDto exportDto,GczlYdxjSGZLXJInfo dto, HttpServletResponse response){
    		try {
    			if(StringUtils.isNotBlank(exportDto.getIds())){
	    			List<String> idsList =new ArrayList<String>();
    				Collections.addAll(idsList, exportDto.getIds().split(ExportDto.SPLIT_STR));
    				List list = gczlYdxjSGZLXJInfoService.selectDtoBatchIds(idsList);
    				ExportExcelUtil.writeExcelToClient(response, exportDto, ExportSetUtil.objectToListMap(list));
	    		}else{
	    	        PageInfo pageInfo = new PageInfo(0, 65530, "id", "desc");
    		        Map<String, Object> condition = new HashMap<String, Object>();
    		        String autoritySql = this.sqlPurview(null,null);
    		        condition.put("autoritySql", autoritySql);
	    	        pageInfo.setCondition(condition);
	    	        gczlYdxjSGZLXJInfoService.selectDataGrid(pageInfo);
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
     public void expPdf(ExportDto exportDto,GczlYdxjSGZLXJInfo dto, HttpServletResponse response){
	     	try {
	     		if(StringUtils.isNotBlank(exportDto.getIds())){
	     			List<String> idsList =new ArrayList<String>();
	     			 Collections.addAll(idsList, exportDto.getIds().split(ExportDto.SPLIT_STR));
	     			 List list = gczlYdxjSGZLXJInfoService.selectDtoBatchIds(idsList);
	     			 ExportPdfUtil.writePdfToClient(response, exportDto, ExportSetUtil.objectToListMap(list));
	     		}else{
	     	        PageInfo pageInfo = new PageInfo(0, 65530, "id", "desc");
	     	        Map<String, Object> condition = new HashMap<String, Object>();
	     	        String autoritySql = this.sqlPurview(null,null);
	     	        condition.put("autoritySql", autoritySql);
	     	        pageInfo.setCondition(condition);
	     	        gczlYdxjSGZLXJInfoService.selectDataGrid(pageInfo);
	     			ExportPdfUtil.writePdfToClient(response, exportDto, ExportSetUtil.objectToListMap(pageInfo.getRows()));
	     		}
	     	} catch (Exception e) {
	     		e.printStackTrace();
	     	}
     }
     
     
   
     
     /**
      *获取当前登录用户所在项目
     * @return
     */
    @RequestMapping("/findProjectByUserId")
     @ResponseBody
     public ProProjectinfo findProjectByUserId() {
    	 ProProjectinfo project = new ProProjectinfo();
    	 String userId = "-1";
    	  User user = this.getCurrentUser();
    	  if(null != user){
    		  userId = user.getId();
    	  }
    	 project = iProProjectinfoService.getProjectInfoByUserId(userId);
         return project;
     }
     
    
    /**
     *根据区间id 获取分部工程列表信息
    * @return
    */
    @RequestMapping("/findFbGcListByQjId")
    @ResponseBody
    public List<ProFbgcInfo> findFbGcListByQjId(HttpServletRequest request) {
    	String sectionId = (String)request.getParameter("sectionId");//区间id
        return this.iProFbgcInfoService.getFbgcBySid(sectionId);
    }
    
    /**
     * 上报数据
     * @param ids 要上报的数据ID
     * @param state 1：启用      0：禁用
     * @return
     */
    @RequestMapping("/sbInfo")
    @ResponseBody
    public Object sbInfo(String ids,String state) {
    	String[] idss = ids.split(",");
    	User user = this.getCurrentUser();
    	String info =  gczlYdxjSGZLXJInfoService.sbInfo(idss,user);
    	if(StringUtils.isNotBlank(info)){
    	    this.log("上报", true, "用户"+user.getName()+"上报失败。"+ids);
    		return renderError(info);
    	}
    	 LOGGER.info("结构施工质量巡检数据上报成功。");
  		 this.log("上报", true, "用户"+user.getName()+"上报成功。"+ids);
        return renderSuccess("操作成功!");
    }
    
    /**
     * 审核结构施工质量巡检管理
     * @param gczlYdxjGPZLDDInfo 要审核的实体信息
     * @return 返回审核成功/失败的提示信息
     */
    @RequestMapping("/shInfo")
    @ResponseBody
    public Object shInfo(GczlYdxjSGZLXJInfoDto gczlYdxjSGZLXJInfo) {
    	GczlYdxjSGZLXJInfo oldEntity = this.gczlYdxjSGZLXJInfoService.selectById(gczlYdxjSGZLXJInfo.getId());
    	if(null == oldEntity || StringUtils.isBlank(oldEntity.getId())){
    		return renderError("结构施工质量巡检信息已不存在，不能审核。");
    	}
    	User user = this.getCurrentUser();
    	String errorMessage = this.gczlYdxjSGZLXJInfoService.shInfo(gczlYdxjSGZLXJInfo, user);
    	if(StringUtils.isNotBlank(errorMessage)){
    		LOGGER.info("结构施工质量巡检数据审核失败。");
    		this.log("审核", false, "审核失败。"+oldEntity.getId());
    		return renderError(errorMessage);
    	}
    	 LOGGER.info("结构施工质量巡检数据审核成功。");
  		 this.log("审核", true, "用户"+user.getName()+"审核成功。"+oldEntity.getId());
        return renderSuccess("审核成功!");
    }
    
    /**
     * 质量巡检管理 整改反馈操作
     * @param gczlYdxjGPZLDDInfo 要 整改反馈的实体信息
     * @return 返回整改反馈成功/失败的提示信息
     */
    @RequestMapping("/zgInfo")
    @ResponseBody
    public Object zgInfo(GczlYdxjSGZLXJInfoDto gczlYdxjSGZLXJInfo) {
    	GczlYdxjSGZLXJInfo oldEntity = this.gczlYdxjSGZLXJInfoService.selectById(gczlYdxjSGZLXJInfo.getId());
    	if(null == oldEntity || StringUtils.isBlank(oldEntity.getId())){
    		return renderError("结构施工质量巡检信息已不存在，不能整改反馈。");
    	}
    	User user = this.getCurrentUser();
    	String errorMessage = this.gczlYdxjSGZLXJInfoService.zgInfo(gczlYdxjSGZLXJInfo, user);
    	if(StringUtils.isNotBlank(errorMessage)){
    		LOGGER.info("结构施工质量巡检数据整改反馈失败。");
    		this.log("整改反馈", false, "用户"+user.getName()+"整改反馈失败。"+oldEntity.getId());
    		return renderError("结构施工质量巡检数据整改反馈失败。");
    	}
    	 LOGGER.info("结构施工质量巡检数据整改反馈成功。");
  		 this.log("整改反馈", true, "用户"+user.getName()+"整改反馈成功。"+oldEntity.getId());
        return renderSuccess("整改反馈成功!");
    }
    
     

}