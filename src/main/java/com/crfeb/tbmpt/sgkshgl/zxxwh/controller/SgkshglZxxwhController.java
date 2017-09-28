package com.crfeb.tbmpt.sgkshgl.zxxwh.controller;

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
import com.crfeb.tbmpt.commons.utils.UploadUtil;
import com.crfeb.tbmpt.dmcjjc.info.controller.DmcjJcInfoAction;
import com.crfeb.tbmpt.project.service.IProProjectinfoService;
import com.crfeb.tbmpt.sgkshgl.zxxwh.model.SgkshglZxxwh;
import com.crfeb.tbmpt.sgkshgl.zxxwh.model.dto.SgkshglZxxwhDto;
import com.crfeb.tbmpt.sgkshgl.zxxwh.service.ISgkshglZxxwhService;
import com.crfeb.tbmpt.sys.model.User;

/**
 * <p>CAD中心线维护 controlle</p>
 * <p>模块：施工可视化管理</p>
 * <p>日期：2017-04-12</p>
 * @version 1.0
 * @author wl_wpg
 */
@Controller
@RequestMapping(value="/sgkshgl/zxxwh")
public class SgkshglZxxwhController extends BaseController{
	
	/**
	 * 上传文件路径
	 */
	private String fileRelativePath = "/upload/sgkshgl/zxxwh/file/"+DateUtils.format(new Date(), "yyyyMMdd");//文件上传相对路径
    
	private static final Logger LOGGER = LogManager
			.getLogger(DmcjJcInfoAction.class);

	@Autowired
	IProProjectinfoService proProjectinfoService;

    @Autowired
    private ISgkshglZxxwhService sgkshglZxxwhService;
  
    
    /**
     * 页面
     *
     * @return
     */
    @RequestMapping(value = "", method = RequestMethod.GET)
    public String manager() {
        return "sgkshgl/zxxwh/index";
    }

    
    
    
    /**
     * 项目资源树
     *
     * @return
     */
    @RequestMapping(value = "/tree", method = RequestMethod.POST)
    @ResponseBody
    public Object treeByUid(String id,String flag) {
    	User user = getCurrentUser();
		if("admin".equals(user.getLoginName())){
			return proProjectinfoService.selectTree();
		}else{
			return proProjectinfoService.selectTreeByUser(user);
		}
    }

    
    /**
     * 员工列表
     *
     * @param page
     * @param rows
     * @param sort
     * @param order
     * @return
     */
    @RequestMapping(value = "/dataGrid", method = RequestMethod.POST)
    @ResponseBody
    public Object dataGrid(SgkshglZxxwhDto dto,Integer page, Integer rows, String sort, String order) {
        PageInfo pageInfo = new PageInfo(page, rows, sort, order);
        Map<String, Object> condition = new HashMap<String, Object>();
        pageInfo.setCondition(condition);
        if (StringUtils.isNotBlank(dto.getProInquireId())) {
        	/***
        	 * 点击左侧项目栏查询
        	 */
            condition.put("inquireId", dto.getProInquireId());
        }else{
        	/***
        	 * 查询按钮查询
        	 */
        	if (StringUtils.isNotBlank(dto.getProId())) {
                condition.put("proId", dto.getProId());
            }
            if (StringUtils.isNotBlank(dto.getSectionId())) {
                condition.put("sectionId", dto.getSectionId());
            }
            if (StringUtils.isNotBlank(dto.getLineId())) {
                condition.put("lineId", dto.getLineId());
            }
            if (StringUtils.isNotBlank(dto.getType())) {
                condition.put("type", dto.getType());
            }
            if (dto.getHh()!=null && !dto.getHh().equals(null)) {
                condition.put("hh", dto.getHh());
            }
        }
        
        sgkshglZxxwhService.selectDataGrid(pageInfo,getCurrentUser());
        return pageInfo;
    }
    
    

    /**
     * 打开添加页面
     *
     * @return
     */
    @RequestMapping(value = "/addPage", method = RequestMethod.GET)
    public String addPage() {
    	return "sgkshgl/Zxxwh/add";
    }

    /**
     * 添加保存
     *
     * @param role
     * @return
     */
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public Object add(SgkshglZxxwhDto dto) {
    	
    	try {
    		String consequence= sgkshglZxxwhService.addSave(dto);
    		if( consequence ==""){
    			return renderSuccess("添加成功！");
        	}
    		else{
    			return renderError("添加失败！");
    		}
    	} catch (Exception e) {
			e.printStackTrace();
			return renderError("添加失败！");
		}
    	
        
    }

    /**
     * 删除
     *
     * @param id
     * @return
     */
    @RequestMapping("/delete")
    @ResponseBody
    public Object delete(String ids) {
    	try {
    		String[] idss = ids.split(",");
        	List<String> idlist = new ArrayList<String>();
        	Collections.addAll(idlist, idss);
        	sgkshglZxxwhService.deleteBatchIds(idlist);
            return renderSuccess("删除成功！");
		} catch (Exception e) {
			e.printStackTrace();
			return renderError("删除失败！");
		}
        
    }

    /**
     * 打开编辑
     *
     * @param model
     * @param id
     * @return
     */
    @RequestMapping("/editPage")
    public String editPage() {
    	
        return "sgkshgl/Zxxwh/edit";
    }

    /**
     * 编辑保存
     *
     * @param role
     * @return
     */
    @RequestMapping("/edit")
    @ResponseBody
    public Object edit(SgkshglZxxwh dto) {
    	try {
    		String consequence = sgkshglZxxwhService.edit(dto);
    		if( consequence ==""){
    			return renderSuccess("编辑成功！");
        	}
    		else{
    			return renderError("编辑失败！");
    		}
    	} catch (Exception e) {
			e.printStackTrace();
			return renderError("编辑失败！");
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
	public void expXls(ExportDto exportDto ,SgkshglZxxwhDto dto, HttpServletResponse response){
		try {
			if(StringUtils.isNotBlank(exportDto.getIds())){
				List<String> idsList =new ArrayList<String>();
				Collections.addAll(idsList, exportDto.getIds().split(ExportDto.SPLIT_STR));
				List list = sgkshglZxxwhService.selectBatchIds(idsList);
				ExportExcelUtil.writeExcelToClient(response, exportDto, ExportSetUtil.objectToListMap(list));
			}else{
				
				
				
				PageInfo pageInfo = new PageInfo(0, 65530, "objectid", "desc");
		        Map<String, Object> condition = new HashMap<String, Object>();
		        pageInfo.setCondition(condition);
		        if (StringUtils.isNotBlank(dto.getProInquireId())) {
		        	/***
		        	 * 点击左侧项目栏查询
		        	 */
		            condition.put("inquireId", dto.getProInquireId());
		        }else{
		        	/***
		        	 * 查询按钮查询
		        	 */
		        	if (StringUtils.isNotBlank(dto.getProId())) {
		                condition.put("proId", dto.getProId());
		            }
		            if (StringUtils.isNotBlank(dto.getSectionId())) {
		                condition.put("sectionId", dto.getSectionId());
		            }
		            if (StringUtils.isNotBlank(dto.getLineId())) {
		                condition.put("lineId", dto.getLineId());
		            }
		            if (StringUtils.isNotBlank(dto.getType())) {
		                condition.put("type", dto.getType());
		            }
		            if (dto.getHh()!=null && !dto.getHh().equals(null)) {
		                condition.put("hh", dto.getHh());
		            }
		        }
		        
		        sgkshglZxxwhService.selectDataGrid(pageInfo,getCurrentUser());
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
	public void expPdf(ExportDto exportDto,SgkshglZxxwhDto dto, HttpServletResponse response){
		try {
			if(StringUtils.isNotBlank(exportDto.getIds())){
				List<String> idsList =new ArrayList<String>();
				 Collections.addAll(idsList, exportDto.getIds().split(ExportDto.SPLIT_STR));
				 List list = sgkshglZxxwhService.selectBatchIds(idsList);
				 ExportPdfUtil.writePdfToClient(response, exportDto, ExportSetUtil.objectToListMap(list));
			}else{
				
				PageInfo pageInfo = new PageInfo(0, 65530, "objectid", "desc");
		        Map<String, Object> condition = new HashMap<String, Object>();
		        pageInfo.setCondition(condition);
		        if (StringUtils.isNotBlank(dto.getProInquireId())) {
		        	/***
		        	 * 点击左侧项目栏查询
		        	 */
		            condition.put("inquireId", dto.getProInquireId());
		        }else{
		        	/***
		        	 * 查询按钮查询
		        	 */
		        	if (StringUtils.isNotBlank(dto.getProId())) {
		                condition.put("proId", dto.getProId());
		            }
		            if (StringUtils.isNotBlank(dto.getSectionId())) {
		                condition.put("sectionId", dto.getSectionId());
		            }
		            if (StringUtils.isNotBlank(dto.getLineId())) {
		                condition.put("lineId", dto.getLineId());
		            }
		            if (StringUtils.isNotBlank(dto.getType())) {
		                condition.put("type", dto.getType());
		            }
		            if (dto.getHh()!=null && !dto.getHh().equals(null)) {
		                condition.put("hh", dto.getHh());
		            }
		        }
		        sgkshglZxxwhService.selectDataGrid(pageInfo,getCurrentUser());
				ExportPdfUtil.writePdfToClient(response, exportDto, ExportSetUtil.objectToListMap(pageInfo.getRows()));
			}
		} catch (Exception e) {
			e.printStackTrace();	
		}
	}

	
	
	
	
	 /***
     * 作用：下载已有的“模版.xls”
     * @param filepath 下载文件全路径
     * @param dto
     * @param response
     * @author wpg
     */
    @RequestMapping(value = "/downLoadModel")
    @ResponseBody
    public Object downLoadModel(HttpServletResponse response)
    {
    	//模版已建好
    	//模版存放路径，模版存放在基础路径的 downloadMode文件夹下；
    	String filepath = "/downloadMode/sgkshglMode/zxxwhMode/zxxwhMode.xlsx";
    	//通用类  ，调用 文件下载 方法
    	UploadUtil.downloadFile2(filepath, request, response);
    	return null;
    }
     
    /**
  	 * 生成excel模板
  	 */
      @RequestMapping(value="/excelTemplate",method = RequestMethod.GET)
      @ResponseBody
      public void ExcelTemplate(HttpServletResponse response) {
   		// 生成excel并下载
   		HSSFWorkbook wb = sgkshglZxxwhService.generateExcelTemplate();
   		String filename = "CAD中心线维护模版.xls";
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
   	public Object upload(@RequestParam(value = "file", required = false) MultipartFile file,SgkshglZxxwhDto dto, HttpServletRequest request, Model model) {  

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
               ring = this.ObtainFileData(fileName,path,dto);
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
    public String ObtainFileData(String fileName,String path,SgkshglZxxwhDto dto) {
	     String ring =null;
	     try {
    	     //
    	     List<List<Map<String,String>>> list = ParseExcelFile.readExcelWithTitle(path+"/"+fileName);
    	     //把 Excel表格数据集合 转成  对象  并  保存
    	     ring = sgkshglZxxwhService.transformationBingPreservation(list, dto);
		  } catch (Exception e) {
			LOGGER.error(" 失败", e);
		  }
		  return ring;
     }
            
	
	
	
}