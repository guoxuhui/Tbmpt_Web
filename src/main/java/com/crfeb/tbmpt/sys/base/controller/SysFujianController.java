package com.crfeb.tbmpt.sys.base.controller;

import java.util.ArrayList;
import java.util.Arrays;
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

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.crfeb.tbmpt.commons.base.BaseController;
import com.crfeb.tbmpt.commons.utils.DateUtils;
import com.crfeb.tbmpt.commons.utils.ExportDto;
import com.crfeb.tbmpt.commons.utils.ExportExcelUtil;
import com.crfeb.tbmpt.commons.utils.ExportPdfUtil;
import com.crfeb.tbmpt.commons.utils.ExportSetUtil;
import com.crfeb.tbmpt.commons.utils.PageInfo;
import com.crfeb.tbmpt.commons.utils.StringUtils;
import com.crfeb.tbmpt.commons.utils.UploadUtil;
import com.crfeb.tbmpt.commons.utils.UtilMath;
import com.crfeb.tbmpt.gczl.base.controller.GczlYdxjSGZLXJInfoController;
import com.crfeb.tbmpt.gczl.base.model.GczlYdxjSGZLXJInfo;
import com.crfeb.tbmpt.gczl.base.model.dto.GczlYdxjSGZLXJInfoDto;
import com.crfeb.tbmpt.sys.base.model.SysFujian;
import com.crfeb.tbmpt.sys.base.model.dto.SysFujianDto;
import com.crfeb.tbmpt.sys.base.service.SysFujianService;
import com.crfeb.tbmpt.sys.model.Res;
import com.crfeb.tbmpt.sys.model.User;

/**
 * <p>公共统一附件controlle</p>
 * <p>系统：系统管理</p>
 * <p>模块：公共模块</p>
 * <p>日期：2017-02-17</p>
 * @version 1.0
 * @author wangbinbin
 */
@Controller
@RequestMapping(value="/sys/base/sysFujian")
public class SysFujianController extends BaseController{
	
	private static final Logger LOGGER = LogManager.getLogger(SysFujianController.class);
    @Autowired
    private SysFujianService sysFujianService;
     @RequestMapping(value = "", method = RequestMethod.GET)
    public String manager() {
        return "sys/base/sysFujian";
    }

    /**
     * 获取公共统一附件easyUi列表
     * @param pageNo 页码
     * @param pageSize 每页条数
     * @param dto 查询条件dto
     * @return 返回查询结果信息
     */
    @RequestMapping(value = "/dataGrid", method = RequestMethod.POST)
    @ResponseBody
    public Object dataGrid(SysFujianDto dto,Integer page, Integer rows, String sort, String order)
    {
        PageInfo pageInfo = new PageInfo(page, rows, sort, order);
        Map<String, Object> condition = new HashMap<String, Object>();
        condition.put("fileName", dto.getFileName());
        condition.put("fileType", dto.getFileType());
        condition.put("resId", dto.getResId());
        condition.put("createUsername", dto.getCreateUsername());
        condition.put("createOrgzname", dto.getCreateOrgzname());
        pageInfo.setCondition(condition);
        try {
           sysFujianService.selectDataGrid(pageInfo);
        } catch (Exception e) {
           e.printStackTrace();
        }
        return pageInfo;
    }

    /**
     * 添加公共统一附件
     * @param gczlYdxjGPZLDDInfo 要新增的实体信息
     * @return 返回保存成功/失败的提示信息
     */
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public Object add(SysFujianDto sysFujianDto) {
    	String errmessage = "";
        try {
          sysFujianService.save(sysFujianDto,getCurrentUser());
          log(null, true, "新增成功!");
        } catch (Exception e) {
          log(null, false, "新增失败!");
          e.printStackTrace();
        }
        return renderSuccess("添加成功！");
    }

    /**
     * 删除公共统一附件
     * @param ids 要删除的数据的ID集合
     * @return 返回删除成功/失败的提示信息
     */
    @RequestMapping("/delete")
    @ResponseBody
    public Object delete(String ids) {
    	String[] idss = ids.split(",");
    	List<String> idlist = new ArrayList<String>();
    	Collections.addAll(idlist, idss);
    	sysFujianService.deleteBatchIds(idlist);
    	log(null, true, "删除："+ids);
    	return renderSuccess("删除成功！");
    }

    /**
     * 修改公共统一附件
     * @param gczlYdxjGPZLDDInfo 要修改的实体信息
     * @return 返回编辑成功/失败的提示信息
     */
    @RequestMapping("/edit")
    @ResponseBody
    public Object edit(SysFujianDto sysFujianDto) {
    	String errmessage = "";
        try {
           errmessage = sysFujianService.update(sysFujianDto,getCurrentUser());
           if(StringUtils.isNotBlank(errmessage)){
             log(null, false, errmessage);
             return renderError(errmessage);
           }
           log(null, true, "编辑成功:"+sysFujianDto.getId());
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
    	public void expXls(ExportDto exportDto,SysFujian dto, HttpServletResponse response){
    		try {
    			if(StringUtils.isNotBlank(exportDto.getIds())){
	    			List<String> idsList =new ArrayList<String>();
    				Collections.addAll(idsList, exportDto.getIds().split(ExportDto.SPLIT_STR));
    				List list = sysFujianService.selectBatchIds(idsList);
    				ExportExcelUtil.writeExcelToClient(response, exportDto, ExportSetUtil.objectToListMap(list));
	    		}else{
	    	        PageInfo pageInfo = new PageInfo(0, 65530, "id", "desc");
    		        Map<String, Object> condition = new HashMap<String, Object>();
	    	        pageInfo.setCondition(condition);
	    	        sysFujianService.selectDataGrid(pageInfo);
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
     public void expPdf(ExportDto exportDto,SysFujian dto, HttpServletResponse response){
	     	try {
	     		if(StringUtils.isNotBlank(exportDto.getIds())){
	     			List<String> idsList =new ArrayList<String>();
	     			 Collections.addAll(idsList, exportDto.getIds().split(ExportDto.SPLIT_STR));
	     			 List list = sysFujianService.selectBatchIds(idsList);
	     			 ExportPdfUtil.writePdfToClient(response, exportDto, ExportSetUtil.objectToListMap(list));
	     		}else{
	     	        PageInfo pageInfo = new PageInfo(0, 65530, "id", "desc");
	     	        Map<String, Object> condition = new HashMap<String, Object>();
	     	        pageInfo.setCondition(condition);
	     	        sysFujianService.selectDataGrid(pageInfo);
	     			ExportPdfUtil.writePdfToClient(response, exportDto, ExportSetUtil.objectToListMap(pageInfo.getRows()));
	     		}
	     	} catch (Exception e) {
	     		e.printStackTrace();
	     	}
     }
     
     /**
      * 上传图片
      */
  	@RequestMapping("/uploadPic")  
  	@ResponseBody
  	public Object uploadPic(@RequestParam(value = "xjZp", required = false) MultipartFile[] files , HttpServletRequest request) {  
  		String keyId = (String) request.getParameter("id");//业务主键
  		String syspath = (String) request.getParameter("syspath");//模块路径
  		String beizhu = (String) request.getParameter("beizhu");//备注
  		String menueName =(String) request.getParameter("menueName");//菜单名称
  		List<SysFujianDto> sysfjList = new ArrayList<SysFujianDto>();
  		User user = this.getCurrentUser();
  		String errorMessage = "";
  		String imagePath = "/upload/"+syspath+"/"+DateUtils.format(new Date(), "yyyyMMdd");
  		 for (MultipartFile mf : files) {  
  			 String tempName = DateUtils.format(new Date(), "yyyyMMddHHmmssSSS")+UUID.randomUUID().toString().substring(0, 4);
              if(!mf.isEmpty() && mf.getSize()>0){  
                  String[] imgType = {"BMP", "bmp", "jpg", "JPG", "wbmp", "jpeg", "png", "PNG", "JPEG", "WBMP", "GIF", "gif" };
                  String fileName = mf.getOriginalFilename();
                  String type = fileName.substring(fileName.lastIndexOf(".")+1);
                  String originalFileName = "";//原始图名称
                  String compressFileNewName = "";//缩略图名称
                  originalFileName=fileName.substring(0, fileName.lastIndexOf("."));//"Y"+tempUid;//原始图片保存文件名
                  UploadUtil uploadutil = new UploadUtil();  
                  if (!Arrays.asList(imgType).contains(type)) {
                	  //非图片
                	  LOGGER.error("文件格式错误，不可上传，请确认。");
                      this.log("上传附件", false, "文件格式错误，不可上传，请确认。");
                	  return renderError("文件格式错误，不可上传，请确认。");
                  }else{
                	  //图片 图片上传的同时需要生成缩略图
                	  compressFileNewName = originalFileName+"S";//缩略图片保存文件名
                	  errorMessage = uploadutil.uploadImage(request, mf, tempName, tempName+"S", imagePath);//上传原始图片并生成对应的缩略图
                	  if(StringUtils.isNotBlank(errorMessage)){
                  		LOGGER.error("结构施工质量巡检上传现场图片失败。");
                        this.log("上传附件", false, "用户"+user.getName()+"上传图片失败。");
                  		return renderError(errorMessage);
                  	 }
                  }
                  SysFujianDto sysFujianDto = new SysFujianDto();
                  //=======================更新原始图片和缩略图片路径到数据库==================//
                  sysFujianDto.setFilePath(imagePath+"/"+tempName+"."+type);
                  if(StringUtils.isNotBlank(compressFileNewName)){
                	  sysFujianDto.setMinImgPath(imagePath+"/"+tempName+"S"+"."+type);
                  }
                  sysFujianDto.setFileName(originalFileName+"."+type);
                  sysFujianDto.setFileType(type);
                  sysFujianDto.setFileSize(UtilMath.divide(mf.getSize(), 1024, 2) );//文件大小换算成kb
                  sysFujianDto.setResId(menueName);
                  sysFujianDto.setForeignId(keyId);
                  sysFujianDto.setBackupOne(beizhu);
                  sysfjList.add(sysFujianDto);
              }  
          }
  		 //==================更新上传附件信息到附件表中==========================//
  		 if(null != sysfjList && sysfjList.size()>0){
  			 try {
  				 this.sysFujianService.save(sysfjList, user);
  			 } catch (Exception e) {
  				 e.printStackTrace();
  				 LOGGER.error("附件上传失败。");
  				 this.log("上传附件", false, "用户"+user.getName()+"上传附件失败。");
			     return renderError("上传附件失败");
  			 }
  		 }
         LOGGER.info("上传附件成功。");
   		 this.log("上传附件", true, "用户"+user.getName()+"上传图片成功。");
         return renderSuccess("上传图片成功!");
      }
  	
  	/**
     * 上传附件
     */
 	@RequestMapping("/uploadFile")  
 	@ResponseBody
 	public Object uploadFile(@RequestParam(value = "xjZp", required = false) MultipartFile[] files , HttpServletRequest request) {  
 		String keyId = (String) request.getParameter("id");//业务主键
 		String syspath = (String) request.getParameter("syspath");//模块路径
 		String beizhu = (String) request.getParameter("beizhu");//备注
 		String menueName =(String) request.getParameter("menueName");//菜单名称
 		List<SysFujianDto> sysfjList = new ArrayList<SysFujianDto>();
 		User user = this.getCurrentUser();
 		String errorMessage = "";
 		String imagePath = "/upload/"+syspath+"/"+DateUtils.format(new Date(), "yyyyMMdd");
 		 for (MultipartFile mf : files) {  
 			 String tempName = DateUtils.format(new Date(), "yyyyMMddHHmmssSSS")+UUID.randomUUID().toString().substring(0, 4);
             if(!mf.isEmpty() && mf.getSize()>0){  
                 String fileName = mf.getOriginalFilename();
                 String type = fileName.substring(fileName.lastIndexOf(".")+1);
                 String originalFileName = "";//原始图名称
                 originalFileName=fileName.substring(0, fileName.lastIndexOf("."));//"Y"+tempUid;//原始图片保存文件名
                 UploadUtil uploadutil = new UploadUtil();  
                 errorMessage = uploadutil.uploadFile(request, mf, originalFileName, imagePath);
                 if(StringUtils.isNotBlank(errorMessage)){
                	 LOGGER.error("附件上传失败。");
     				 this.log("上传附件", false, "用户"+user.getName()+"上传附件失败。");
    			     return renderError("上传附件失败");
                 }
                 SysFujianDto sysFujianDto = new SysFujianDto();
                 //=======================更新原始图片和缩略图片路径到数据库==================//
                 sysFujianDto.setFilePath(imagePath+"/"+tempName+"."+type);
                 sysFujianDto.setFileName(originalFileName+"."+type);
                 sysFujianDto.setFileType(type);
                 sysFujianDto.setFileSize(UtilMath.divide(mf.getSize(), 1024, 2) );//文件大小换算成kb
                 sysFujianDto.setResId(menueName);
                 sysFujianDto.setForeignId(keyId);
                 sysFujianDto.setBackupOne(beizhu);
                 sysfjList.add(sysFujianDto);
             }  
         }
 		 //==================更新上传附件信息到附件表中==========================//
 		 if(null != sysfjList && sysfjList.size()>0){
 			 try {
 				 this.sysFujianService.save(sysfjList, user);
 			 } catch (Exception e) {
 				 e.printStackTrace();
 				 LOGGER.error("附件上传失败。");
 				 this.log("上传附件", false, "用户"+user.getName()+"上传附件失败。");
			     return renderError("上传附件失败");
 			 }
 		 }
        LOGGER.info("上传附件成功。");
  		 this.log("上传附件", true, "用户"+user.getName()+"上传图片成功。");
        return renderSuccess("上传图片成功!");
     }
  	
  	/**
  	 * 方法说明：获取模块id
  	 * @return
  	 * @author:YangYj
  	 * @Time: 2017年2月18日 下午2:08:35
  	 */
  	public  String getModeId(HttpServletRequest request){
  		Res modelres = null;
    	String currUrl = request.getRequestURI();
    	String modelUrl = currUrl.substring(0, currUrl.lastIndexOf("/"));
    	//获取当前模块菜单
    	EntityWrapper<Res> modelew = new EntityWrapper<Res>();
    	modelew.where("url={0}",modelUrl);
    	List<Res> modelress = resService.selectList(modelew);
    	if(modelress.size()>0){
    		modelres = modelress.get(0);
    	}
    	return modelres.getId();
  	}
  	
  	/**
  	 * 方法说明：根据业务主键及备用字段查询附件信息
  	 * @param dto 参数dto
  	 * @return
  	 * @author:YangYj
  	 * @Time: 2017年2月18日 下午2:40:45
  	 */
  	@RequestMapping(value = "/findDataByKeyidAndBeiZhu")
    @ResponseBody
    public Object findDataByKeyidAndBeiZhu(SysFujianDto dto)
    {
  		List<SysFujianDto> list = new ArrayList<SysFujianDto>();
        try {
			list = this.sysFujianService.findFuJianListByForeignId(dto.getForeignId(), dto.getBackupOne(), dto.getBackupTwo());
		} catch (Exception e) {
			e.printStackTrace();
			 LOGGER.error("查询附件失败。");
			this.log("查询附件", false, "查询附件失败。");
		}
        return list;
    }
  	
  	/**
  	 * 方法说明：根据业务主键及备用字段查询附件信息
  	 * @param dto 参数dto
  	 * @return
  	 * @author:YangYj
  	 * @Time: 2017年2月18日 下午2:40:45
  	 */
  	@RequestMapping(value = "/deleteFile")
    @ResponseBody
    public Object deleteFile(String id)
    {
  		try {
	  		SysFujian fujian = this.sysFujianService.findById(id);
	  		String filepath = "";//原始图路径
	  		String sltPath = "";//缩略图路径
	  		if(null != fujian && StringUtils.isNotBlank(fujian.getId())){
	  			filepath = fujian.getFilePath();
	  			sltPath = fujian.getMinImgPath();
  				this.sysFujianService.deleteById(id);//删除数据库记录
  				UploadUtil uploadutil = new UploadUtil(); 
  				String rootPath =request.getSession().getServletContext().getRealPath("/");
  				uploadutil.deleteFile(rootPath + filepath);//删除原始文件
  				if(StringUtils.isNotBlank(sltPath)){
  					uploadutil.deleteFile(rootPath + sltPath);//删除缩略图文件
  				}
	  		}
  		} catch (Exception e) {
  			e.printStackTrace();
  			LOGGER.error("删除附件失败。");
  			this.log("删除附件", false, "删除附件失败。");
  			return renderError("删除附件失败！");
  		}
        return renderSuccess("删除附件成功!");
    }


}