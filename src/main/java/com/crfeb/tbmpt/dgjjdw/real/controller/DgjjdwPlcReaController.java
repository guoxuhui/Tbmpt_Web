package com.crfeb.tbmpt.dgjjdw.real.controller;

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
import com.crfeb.tbmpt.dgjjdw.real.model.DgjjdwPlcReal;
import com.crfeb.tbmpt.dgjjdw.real.model.dto.DgjjdwPlcRealDto;
import com.crfeb.tbmpt.dgjjdw.real.service.IDgjjdwPlcRealService;
import com.crfeb.tbmpt.dmcjjc.info.controller.DmcjJcInfoAction;

/**
 * <p>盾构掘进点位管理：盾构机机器数据实时信息管理 controlle</p>
 * <p>模块：盾构掘进点位管理</p>
 * <p>日期：2017-03-17</p>
 * @version 1.0
 * @author wl_wpg
 */
@Controller
@RequestMapping(value="/dgjjdw/real/dgjjdwPlcReal")
public class DgjjdwPlcReaController extends BaseController{

	/**
	 * 上传文件路径
	 */
	private String fileRelativePath = "/upload/dgjjdw/rea/file/"+DateUtils.format(new Date(), "yyyyMMdd");//文件上传相对路径
    
	private static final Logger LOGGER = LogManager
			.getLogger(DmcjJcInfoAction.class);

	@Autowired
    private IDgjjdwPlcRealService dgjjdwPlcRealService;

	
   

  @RequestMapping(value = "", method = RequestMethod.GET)
    public String manager() {
        return "dgjjdw/real/dgjjdwPlcReal";
    }

    /**
     * 标准点位字典信息easyUi列表
     * @param pageNo 页码
     * @param pageSize 每页条数
     * @param dto 查询条件dto
     * @return 返回查询结果信息
     */
    @RequestMapping(value = "/dataGrid", method = RequestMethod.POST)
    @ResponseBody
    public Object dataGrid(DgjjdwPlcRealDto dto,Integer page, Integer rows, String sort, String order)
    {
        PageInfo pageInfo = new PageInfo(page, rows, sort, order);
        Map<String, Object> condition = new HashMap<String, Object>();
        pageInfo.setCondition(condition);
        if(StringUtils.isNotBlank(dto.getId())){
    		condition.put("id", dto.getId());
		}
        if(StringUtils.isNotBlank(dto.getTbmcode())){
    		condition.put("tbmcode", dto.getTbmcode());
		}
        if(StringUtils.isNotBlank(dto.getTagname())){
    		condition.put("tagname", dto.getTagname());
		}
        try {
           dgjjdwPlcRealService.selectDataGrid(pageInfo);
        } catch (Exception e) {
           e.printStackTrace();
        }
        return pageInfo;
    }

   
    /**     
    * 数据导出     
    * @param exportDto     
    * @param Dto     
    * @param response     
    */
    @SuppressWarnings({"unchecked", "rawtypes"})
    	@RequestMapping(value="/expXls")
    	public void expXls(ExportDto exportDto,DgjjdwPlcReal dto, HttpServletResponse response){
    		try {
    			if(StringUtils.isNotBlank(exportDto.getIds())){
	    			List<String> idsList =new ArrayList<String>();
    				Collections.addAll(idsList, exportDto.getIds().split(ExportDto.SPLIT_STR));
    				
    				if(idsList.size()>0){
	    				List<Object> list = new  ArrayList<Object>();
	    				DgjjdwPlcReal entity = new DgjjdwPlcReal();
					    for(String id :idsList){
						     entity.setId(id);
						     DgjjdwPlcReal bzdw = new DgjjdwPlcReal();
						     bzdw = dgjjdwPlcRealService.selectOne(entity);
						     list.add(bzdw);
					    }
					    ExportExcelUtil.writeExcelToClient(response, exportDto, ExportSetUtil.objectToListMap(list));
    				}
    				
    			}else{
	    	        PageInfo pageInfo = new PageInfo(0, 65530, "id", "desc");
    		        Map<String, Object> condition = new HashMap<String, Object>();
	    	        pageInfo.setCondition(condition);
	    	        dgjjdwPlcRealService.selectDataGrid(pageInfo);
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
     public void expPdf(ExportDto exportDto,DgjjdwPlcReal dto, HttpServletResponse response){
	     	try {
	     		if(StringUtils.isNotBlank(exportDto.getIds())){
	     			List<String> idsList =new ArrayList<String>();
	     			 Collections.addAll(idsList, exportDto.getIds().split(ExportDto.SPLIT_STR));
	     			if(idsList.size()>0){
	    				List<Object> list = new  ArrayList<Object>();
	    				DgjjdwPlcReal entity = new DgjjdwPlcReal();
					    for(String id :idsList){
						     entity.setId(id);
						     DgjjdwPlcReal bzdw = new DgjjdwPlcReal();
						     bzdw = dgjjdwPlcRealService.selectOne(entity);
						     list.add(bzdw);
					    }
					    ExportPdfUtil.writePdfToClient(response, exportDto, ExportSetUtil.objectToListMap(list));
    				}
	     		
	     		}else{
	     	        PageInfo pageInfo = new PageInfo(0, 65530, "id", "desc");
	     	        Map<String, Object> condition = new HashMap<String, Object>();
	     	        pageInfo.setCondition(condition);
	     	        dgjjdwPlcRealService.selectDataGrid(pageInfo);
	     			ExportPdfUtil.writePdfToClient(response, exportDto, ExportSetUtil.objectToListMap(pageInfo.getRows()));
	     		}
	     	} catch (Exception e) {
	     		e.printStackTrace();
	     	}
     }
     
     
    /**
  	 * 生成excel模板
  	 */
      @RequestMapping(value="/excelTemplate",method = RequestMethod.GET)
      @ResponseBody
      public void ExcelTemplate(HttpServletResponse response) {
   		// 生成excel并下载
   		HSSFWorkbook wb = dgjjdwPlcRealService.generateExcelTemplate();
   		String filename = "盾构机机器数据实时信息模版.xls";
   		String iso_filename = ExcelUtils.parseGBK(filename);
   		response.setContentType("text/plain");
   		response.setHeader("Content-Disposition","attachment;filename=" + iso_filename);
   		
   		OutputStream ouputStream = null; 
   		try {
   			ouputStream = response.getOutputStream();
   			wb.write(ouputStream);
   			ouputStream.flush();
   		} catch (IOException e) {
   			e.printStackTrace();
   		} finally{
   			IOUtils.close(ouputStream);
   		}
   	}
     
      
      /**
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
   	 * 作用：新增页面再请求上传的文件，解析文件内容 并 把信息存表
     * @return 
   	 */
    public String ObtainFileData(String fileName,String gcbh,String path) {
	     String ring =null;
	     try {
    	     //
    	     List<List<Map<String,String>>> list = ParseExcelFile.readExcelWithTitle(path+"/"+fileName);
    	     //把 Excel表格数据集合 转成  对象  并  保存
    	     ring = dgjjdwPlcRealService.transformationBingPreservation(list);
		  } catch (Exception e) {
			LOGGER.error(" 失败", e);
		  }
		  return ring;
     }
       
     
     
     
     
     

}