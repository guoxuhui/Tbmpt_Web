package com.crfeb.tbmpt.aqsc.base.controller;

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

import com.crfeb.tbmpt.commons.base.BaseController;
import com.crfeb.tbmpt.commons.utils.DateUtils;
import com.crfeb.tbmpt.commons.utils.ExcelUtils;
import com.crfeb.tbmpt.commons.utils.ExportDto;
import com.crfeb.tbmpt.commons.utils.ExportExcelUtil;
import com.crfeb.tbmpt.commons.utils.ExportPdfUtil;
import com.crfeb.tbmpt.commons.utils.ExportSetUtil;
import com.crfeb.tbmpt.commons.utils.IdCard;
import com.crfeb.tbmpt.commons.utils.PageInfo;
import com.crfeb.tbmpt.commons.utils.ParseExcelFile;
import com.crfeb.tbmpt.commons.utils.StringUtils;
import com.crfeb.tbmpt.commons.utils.SysPropertieUtil;
import com.crfeb.tbmpt.commons.utils.UploadUtil;
import com.crfeb.tbmpt.dmcjjc.info.controller.DmcjJcInfoAction;
import com.alibaba.fastjson.util.IOUtils;
import com.crfeb.tbmpt.aqsc.base.model.PeiXunRenYuan;
import com.crfeb.tbmpt.aqsc.base.model.dto.PeiXunRenYuanDto;
import com.crfeb.tbmpt.aqsc.base.service.PeiXunRenYuanService;

/**
 * <p>培训人员管理controlle</p>
 * <p>系统：安全生产管理</p>
 * <p>模块：基础信息</p>
 * <p>日期：2017-02-20</p>
 * @version 1.0
 * @author wangbinbin
 */
@Controller
@RequestMapping(value="/aqsc/base/peiXunRenYuan")
public class PeiXunRenYuanController extends BaseController{

	/**
	 * 上传文件路径
	 */
	private String fileRelativePath = "/upload/aqsc/base/peiXunRenYuan/file/"+DateUtils.format(new Date(), "yyyyMMdd");//文件上传相对路径
    
	private static final Logger LOGGER = LogManager
			.getLogger(DmcjJcInfoAction.class);
	
	
    @Autowired
    private PeiXunRenYuanService peiXunRenYuanService;

  @RequestMapping(value = "", method = RequestMethod.GET)
    public String manager() {
        return "aqsc/base/peiXunRenYuan";
    }

    /**
     * 获取培训人员管理easyUi列表
     * @param pageNo 页码
     * @param pageSize 每页条数
     * @param dto 查询条件dto
     * @return 返回查询结果信息
     */
    @RequestMapping(value = "/dataGrid", method = RequestMethod.POST)
    @ResponseBody
    public Object dataGrid(PeiXunRenYuanDto dto,Integer page, Integer rows, String sort, String order)
    {
        PageInfo pageInfo = new PageInfo(page, rows, sort, order);
        Map<String, Object> condition = new HashMap<String, Object>();
        condition.put("name", dto.getName());
        condition.put("sex", dto.getSex());
        condition.put("cardNo", dto.getCardNo());
        condition.put("adress", dto.getAdress());
        condition.put("phone", dto.getPhone());
        condition.put("state", dto.getState());
        condition.put("remark", dto.getRemark());
        condition.put("demptName", dto.getDemptName());
        condition.put("queryStarDate", dto.getQueryStarDate());
        condition.put("queryEndDate", dto.getQueryEndDate());
        
        if(!this.getUserId().equals("1"))condition.put("sqlPurview", this.sqlPurview(null,"xmBh"));
        pageInfo.setCondition(condition);
        try {
           peiXunRenYuanService.selectDataGrid(pageInfo);
        } catch (Exception e) {
           e.printStackTrace();
        }
        return pageInfo;
    }

    /**
     * 添加培训人员管理
     * @param gczlYdxjGPZLDDInfo 要新增的实体信息
     * @return 返回保存成功/失败的提示信息
     */
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public Object add(PeiXunRenYuanDto peiXunRenYuanDto) {
    	String errmessage = "";
        try {
    	errmessage = peiXunRenYuanService.checkClumIfexits(peiXunRenYuanDto,new String[]{"cardNo"});
    	
    	String sex = IdCard.getGenderByIdCard(peiXunRenYuanDto.getCardNo());//性别
    	peiXunRenYuanDto.setSex(sex);
	    log(null, false, errmessage);
    	if(errmessage!=null) return renderError(errmessage);
          peiXunRenYuanService.save(peiXunRenYuanDto,getCurrentUser());
          log(null, true, "新增成功!");
        } catch (Exception e) {
          log(null, false, "新增失败!");
          e.printStackTrace();
        }
        return renderSuccess("添加成功！");
    }

    /**
     * 删除培训人员管理
     * @param ids 要删除的数据的ID集合
     * @return 返回删除成功/失败的提示信息
     */
    @RequestMapping("/delete")
    @ResponseBody
    public Object delete(String ids) {
    	String[] idss = ids.split(",");
    	List<String> idlist = new ArrayList<String>();
    	Collections.addAll(idlist, idss);
    	peiXunRenYuanService.deleteBatchIds(idlist);
    	log(null, true, "删除："+ids);
    	return renderSuccess("删除成功！");
    }

    /**
     * 修改培训人员管理
     * @param gczlYdxjGPZLDDInfo 要修改的实体信息
     * @return 返回编辑成功/失败的提示信息
     */
    @RequestMapping("/edit")
    @ResponseBody
    public Object edit(PeiXunRenYuanDto peiXunRenYuanDto) {
    	String errmessage = "";
        try {
	    	errmessage = peiXunRenYuanService.checkClumIfexits(peiXunRenYuanDto,new String[]{"cardNo"});
	    	if(errmessage!=null) return renderError(errmessage);
	    	
	    	String sex = IdCard.getGenderByIdCard(peiXunRenYuanDto.getCardNo());//性别
	    	peiXunRenYuanDto.setSex(sex);
            errmessage = peiXunRenYuanService.update(peiXunRenYuanDto,getCurrentUser());
            if(StringUtils.isNotBlank(errmessage)){
             log(null, false, errmessage);
             return renderError(errmessage);
            }
           log(null, true, "编辑成功:"+peiXunRenYuanDto.getId());
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
    	public void expXls(ExportDto exportDto,PeiXunRenYuanDto dto, HttpServletResponse response){
    		try {
    			if(StringUtils.isNotBlank(exportDto.getIds())){
	    			List<String> idsList =new ArrayList<String>();
    				Collections.addAll(idsList, exportDto.getIds().split(ExportDto.SPLIT_STR));
    				List list = peiXunRenYuanService.selectBatchIds(idsList);
    				ExportExcelUtil.writeExcelToClient(response, exportDto, ExportSetUtil.objectToListMap(list));
	    		}else{
	    	        PageInfo pageInfo = new PageInfo(0, 65530, "id", "desc");
    		        Map<String, Object> condition = new HashMap<String, Object>();
    		        condition.put("name", dto.getName());
	     	        condition.put("sex", dto.getSex());
	     	        condition.put("cardNo", dto.getCardNo());
	     	        condition.put("adress", dto.getAdress());
	     	        condition.put("phone", dto.getPhone());
	     	        condition.put("state", dto.getState());
	     	        condition.put("remark", dto.getRemark());
	     	        condition.put("demptName", dto.getDemptName());
	     	        condition.put("queryStarDate", dto.getQueryStarDate());
	     	        condition.put("queryEndDate", dto.getQueryEndDate());
	     	        if(!this.getUserId().equals("1"))condition.put("sqlPurview", this.sqlPurview(null,"xmBh"));
	    	        pageInfo.setCondition(condition);
	    	        peiXunRenYuanService.selectDataGrid(pageInfo);
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
     public void expPdf(ExportDto exportDto,PeiXunRenYuanDto dto, HttpServletResponse response){
	     	try {
	     		if(StringUtils.isNotBlank(exportDto.getIds())){
	     			List<String> idsList =new ArrayList<String>();
	     			 Collections.addAll(idsList, exportDto.getIds().split(ExportDto.SPLIT_STR));
	     			 List list = peiXunRenYuanService.selectBatchIds(idsList);
	     			 ExportPdfUtil.writePdfToClient(response, exportDto, ExportSetUtil.objectToListMap(list));
	     		}else{
	     	        PageInfo pageInfo = new PageInfo(0, 65530, "id", "desc");
	     	        Map<String, Object> condition = new HashMap<String, Object>();
	     	        condition.put("name", dto.getName());
	     	        condition.put("sex", dto.getSex());
	     	        condition.put("cardNo", dto.getCardNo());
	     	        condition.put("adress", dto.getAdress());
	     	        condition.put("phone", dto.getPhone());
	     	        condition.put("state", dto.getState());
	     	        condition.put("remark", dto.getRemark());
	     	        condition.put("demptName", dto.getDemptName());
	     	        condition.put("queryStarDate", dto.getQueryStarDate());
	     	        condition.put("queryEndDate", dto.getQueryEndDate());
	     	        if(!this.getUserId().equals("1"))condition.put("sqlPurview", this.sqlPurview(null,"xmBh"));
	     	        pageInfo.setCondition(condition);
	     	        peiXunRenYuanService.selectDataGrid(pageInfo);
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
     	String filepath = "/downloadMode/aqscMode/baseMode/peiXunRenYuanMode/peiXunRenYuanMode.xls";
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
		HSSFWorkbook wb = peiXunRenYuanService.generateExcelTemplate();
		String filename = "培训人员信息模版.xls";
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
		      ring = peiXunRenYuanService.transformationBingPreservation(list,getCurrentUser());
		  } catch (Exception e) {
			  LOGGER.error(" 失败", e);
		  }
			  return ring;
	 }
              
     
     
     
     
     
     
     
     
}