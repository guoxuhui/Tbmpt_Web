package com.crfeb.tbmpt.zsjk.jt.controller;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.util.IOUtils;
import com.crfeb.tbmpt.commons.base.BaseController;
import com.crfeb.tbmpt.commons.utils.DateUtils;
import com.crfeb.tbmpt.commons.utils.ExcelUtils;
import com.crfeb.tbmpt.commons.utils.ExportDto;
import com.crfeb.tbmpt.commons.utils.ExportExcelUtil;
import com.crfeb.tbmpt.commons.utils.ExportPdfUtil;
import com.crfeb.tbmpt.commons.utils.ExportSetUtil;
import com.crfeb.tbmpt.commons.utils.PageInfo;
import com.crfeb.tbmpt.commons.utils.ParseExcelFile;
import com.crfeb.tbmpt.commons.utils.StringUtils;
import com.crfeb.tbmpt.commons.utils.SysPropertieUtil;
import com.crfeb.tbmpt.dmcjjc.info.controller.DmcjJcInfoAction;
import com.crfeb.tbmpt.zsjk.jt.model.ZsJkJtDjXhZTrXx;
import com.crfeb.tbmpt.zsjk.jt.model.dto.ZsJkJtDjXhZTrXxDto;
import com.crfeb.tbmpt.zsjk.jt.service.ZsJkJtDjXhZTrXxService;

/**
 * <p>集团角度刀具消耗总投入信息controlle</p>
 * <p>系统：展示界面接口</p>
 * <p>模块：集团角度模块</p>
 * <p>日期：2017-01-09</p>
 * @version 1.0
 * @author YangYJ
 */
@Controller
@RequestMapping(value="/zsjk/jt/zsJkJtDjXhZTrXx")
public class ZsJkJtDjXhZTrXxController extends BaseController{

    @Autowired
    private ZsJkJtDjXhZTrXxService zsJkJtDjXhZTrXxService;
    
    private String fileRelativePath = "/upload/DjXhZTrXx/file/"+DateUtils.format(new Date(), "yyyyMMdd");//文件上传相对路径
    
    private static final Logger LOGGER = LogManager
			.getLogger(DmcjJcInfoAction.class);

  @RequestMapping(value = "", method = RequestMethod.GET)
    public String manager() {
        return "zsjk/jt/zsJkJtDjXhZTrXx";
    }

    /**
     * 获取集团角度刀具消耗总投入信息easyUi列表
     * @param pageNo 页码
     * @param pageSize 每页条数
     * @param dto 查询条件dto
     * @return 返回查询结果信息
     */
    @RequestMapping(value = "/dataGrid", method = RequestMethod.POST)
    @ResponseBody
    public Object dataGrid(ZsJkJtDjXhZTrXxDto dto,Integer page, Integer rows, String sort, String order)
    {
        PageInfo pageInfo = new PageInfo(page, rows, sort, order);
        Map<String, Object> condition = new HashMap<String, Object>();
        pageInfo.setCondition(condition);
        try {
           zsJkJtDjXhZTrXxService.selectDataGrid(pageInfo);
        } catch (Exception e) {
           e.printStackTrace();
        }
        return pageInfo;
    }

    /**
     * 添加集团角度刀具消耗总投入信息
     * @param gczlYdxjGPZLDDInfo 要新增的实体信息
     * @return 返回保存成功/失败的提示信息
     */
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public Object add(ZsJkJtDjXhZTrXxDto zsJkJtDjXhZTrXxDto) {
    	String errmessage = "";
        try {
          zsJkJtDjXhZTrXxService.save(zsJkJtDjXhZTrXxDto,getCurrentUser());
          log(null, true, "新增成功!");
        } catch (Exception e) {
          log(null, false, "新增失败!");
          e.printStackTrace();
        }
        return renderSuccess("添加成功！");
    }

    /**
     * 删除集团角度刀具消耗总投入信息
     * @param ids 要删除的数据的ID集合
     * @return 返回删除成功/失败的提示信息
     */
    @RequestMapping("/delete")
    @ResponseBody
    public Object delete(String ids) {
    	String[] idss = ids.split(",");
    	List<String> idlist = new ArrayList<String>();
    	Collections.addAll(idlist, idss);
    	zsJkJtDjXhZTrXxService.deleteBatchIds(idlist);
    	log(null, true, "删除："+ids);
    	return renderSuccess("删除成功！");
    }

    /**
     * 修改集团角度刀具消耗总投入信息
     * @param gczlYdxjGPZLDDInfo 要修改的实体信息
     * @return 返回编辑成功/失败的提示信息
     */
    @RequestMapping("/edit")
    @ResponseBody
    public Object edit(ZsJkJtDjXhZTrXxDto zsJkJtDjXhZTrXxDto) {
    	String errmessage = "";
        try {
           errmessage = zsJkJtDjXhZTrXxService.update(zsJkJtDjXhZTrXxDto,getCurrentUser());
           if(StringUtils.isNotBlank(errmessage)){
             log(null, false, errmessage);
             return renderError(errmessage);
           }
           log(null, true, "编辑成功:"+zsJkJtDjXhZTrXxDto.getId());
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
    	public void expXls(ExportDto exportDto,ZsJkJtDjXhZTrXx dto, HttpServletResponse response){
    		try {
    			if(StringUtils.isNotBlank(exportDto.getIds())){
	    			List<String> idsList =new ArrayList<String>();
    				Collections.addAll(idsList, exportDto.getIds().split(ExportDto.SPLIT_STR));
    				List list = zsJkJtDjXhZTrXxService.selectBatchIds(idsList);
    				ExportExcelUtil.writeExcelToClient(response, exportDto, ExportSetUtil.objectToListMap(list));
	    		}else{
	    	        PageInfo pageInfo = new PageInfo(0, 65530, "id", "desc");
    		        Map<String, Object> condition = new HashMap<String, Object>();
	    	        pageInfo.setCondition(condition);
	    	        zsJkJtDjXhZTrXxService.selectDataGrid(pageInfo);
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
     public void expPdf(ExportDto exportDto,ZsJkJtDjXhZTrXx dto, HttpServletResponse response){
	     	try {
	     		if(StringUtils.isNotBlank(exportDto.getIds())){
	     			List<String> idsList =new ArrayList<String>();
	     			 Collections.addAll(idsList, exportDto.getIds().split(ExportDto.SPLIT_STR));
	     			 List list = zsJkJtDjXhZTrXxService.selectBatchIds(idsList);
	     			 ExportPdfUtil.writePdfToClient(response, exportDto, ExportSetUtil.objectToListMap(list));
	     		}else{
	     	        PageInfo pageInfo = new PageInfo(0, 65530, "id", "desc");
	     	        Map<String, Object> condition = new HashMap<String, Object>();
	     	        pageInfo.setCondition(condition);
	     	        zsJkJtDjXhZTrXxService.selectDataGrid(pageInfo);
	     			ExportPdfUtil.writePdfToClient(response, exportDto, ExportSetUtil.objectToListMap(pageInfo.getRows()));
	     		}
	     	} catch (Exception e) {
	     		e.printStackTrace();
	     	}
     }
     
     /**
      * 获取安全质量信息easyUi列表
      * @author wl_zjh
      * @param pageNo 页码
      * @param pageSize 每页条数
      * @param dto 查询条件dto
      * @return 返回查询结果信息
      */
     @RequestMapping(value = "/djXhZTrXxdataGrid", method = RequestMethod.POST)
     @ResponseBody
     public Object djXhZTrXxdataGrid(ZsJkJtDjXhZTrXxDto dto,Integer page, Integer rows, String sort, String order)
     {
         PageInfo pageInfo = new PageInfo(page, rows, sort, order);
         Map<String, Object> condition = new HashMap<String, Object>();
         pageInfo.setCondition(condition);
       //设置查询条件
         if(StringUtils.isNotBlank(dto.getJhTrz())){
    			condition.put("jhTrz", dto.getJhTrz());
    		}
		 if(StringUtils.isNotBlank(dto.getSjTrz())){
			condition.put("sjTrz", dto.getSjTrz());
		}
		 if(StringUtils.isNotBlank(dto.getNd())){
			condition.put("nd", dto.getNd());
		}
         try {
            zsJkJtDjXhZTrXxService.selectdjXhZTrXxDataGrid(pageInfo);;
         } catch (Exception e) {
            e.printStackTrace();
         }
         return pageInfo;
     }
     
     /**
      * 生成excel模板
      * @author wl_zjh
      * @param response
      */
      @RequestMapping(value="/excelTemplate",method = RequestMethod.GET)
      @ResponseBody
      public void ExcelTemplate(HttpServletResponse response) {
   		// 生成excel并下载
   		HSSFWorkbook wb = zsJkJtDjXhZTrXxService.generateExcelTemplate();
   		String filename = "刀具消耗总投入信息.xls";
   		String iso_filename = ExcelUtils.parseGBK(filename);
   		response.setContentType("text/plain");
   		response.setHeader("Content-Disposition","attachment;filename=" + iso_filename);
   		
   		OutputStream ouputStream = null; 
   		try {
   			ouputStream = response.getOutputStream();
   			wb.write(ouputStream);
   			ouputStream.flush();
   		} catch (IOException e) {
   			
   		} finally{
   			IOUtils.close(ouputStream);
   		}
   	}
      
      /**
       * @author wl_zjh
       * 上传文件，然后跳转到新增页面 
       */
    @RequestMapping("/upload")  
   	@ResponseBody
   	public Object upload(String gcbh,@RequestParam(value = "file", required = false) MultipartFile file, HttpServletRequest request, Model model) {  
   		

           //获取   后续路径
           SysPropertieUtil configEntity = SysPropertieUtil.getInstince();
           String upLoadPath = configEntity.getUploadPath();
           //文件路径=前续路径 (来自配置文件) +  后续
           String path = upLoadPath + fileRelativePath;
           //filePath = upLoadPath + filePath;
           
           
           //获取路径
           //应该用新地址  找文件夹   “jcPointUpload”
           //String path = request.getSession().getServletContext().getRealPath("jcPointUpload");  
           File uploadDir = new File(path);
           //即判断你指定的路径或着指定的目录文件是否已经存在。
           if(!uploadDir.exists()){  
           	uploadDir.mkdirs();  
           }  
           //用 “系统时间” 和 “文件名称” 组成导入 文件的名称，避免名称重复；
           String fileName = System.currentTimeMillis()+file.getOriginalFilename();  
           //用日志 输出 “上传文件路径”
           LOGGER.info("upload file path="+path);
           File targetFile = new File(path, fileName);  
           //保存文件到服务器  
           String ring =null;
           try {  
               file.transferTo(targetFile);  
               //获取 文件 的 信息 并 保存 ：文件名，项目ID ，文件路径
               ring = this.ObtainFileData(fileName, gcbh, path);
               if(ring ==null || ring==""){
       		    return renderError("新增操作：失败。");
       	   }
           } catch (Exception e) {  
           	LOGGER.error("upload error:",e);
           	if(ring ==null || ring==""){
       		    return renderError("新增操作：失败。");
       	   }
           	
           }
           return renderSuccess( ring);
   		
         
       }  
   	
   	
    /**
  	 * @author wl_zjh
 	 * 作用：新增页面再请求上传的文件，解析文件内容 并 把信息存表
  	 * @return 
 	 */
      public String ObtainFileData(String fileName,String gcbh,String path) {
   	   String ring =null;
   	   try {
       	   //
       	   List<List<Map<String,String>>> list = ParseExcelFile.readExcelWithTitle(path+"/"+fileName);
       	   //把 Excel表格数据集合 转成  对象  并  保存
       	   ring = zsJkJtDjXhZTrXxService.transformationBingPreservation(list);
   		} catch (Exception e) {
   			LOGGER.error(" 失败", e);
   		}
   	  
   		return ring;
   	  
      }
}