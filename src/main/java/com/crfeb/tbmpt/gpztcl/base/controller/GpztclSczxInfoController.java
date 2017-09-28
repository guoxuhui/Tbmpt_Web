package com.crfeb.tbmpt.gpztcl.base.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

import com.crfeb.tbmpt.commons.base.BaseController;
import com.crfeb.tbmpt.commons.utils.ExportDto;
import com.crfeb.tbmpt.commons.utils.ExportExcelUtil;
import com.crfeb.tbmpt.commons.utils.ExportPdfUtil;
import com.crfeb.tbmpt.commons.utils.ExportSetUtil;
import com.crfeb.tbmpt.commons.utils.PageInfo;
import com.crfeb.tbmpt.commons.utils.StringUtils;
import com.crfeb.tbmpt.gpztcl.base.model.GpztclSczxInfo;
import com.crfeb.tbmpt.gpztcl.base.model.dto.GpztclSczxInfoDto;
import com.crfeb.tbmpt.gpztcl.base.model.dto.GpztclSczxpcDto;
import com.crfeb.tbmpt.gpztcl.base.model.dto.TextFileDto;
import com.crfeb.tbmpt.gpztcl.base.service.GpztclSczxInfoService;

/**
 * <p>实测中线信息明细管理controlle</p>
 * <p>系统：管片姿态测量系统</p>
 * <p>模块：基础模块</p>
 * <p>日期：2016-12-20</p>
 * @version 1.0
 * @author YangYj
 */
@Controller
@RequestMapping(value="/gpztcl/base/gpztclSczxInfo")
public class GpztclSczxInfoController extends BaseController{
	private static final Logger LOGGER = LogManager.getLogger(GpztclSczxInfoController.class);
    @Autowired
    private GpztclSczxInfoService gpztclSczxInfoService;
    

  @RequestMapping(value = "", method = RequestMethod.GET)
    public String manager() {
        return "gpztcl/base/gpztclSczxInfo";
    }

    /**
     * 获取实测中线信息明细管理easyUi列表
     * @param pageNo 页码
     * @param pageSize 每页条数
     * @param dto 查询条件dto
     * @return 返回查询结果信息
     */
    @RequestMapping(value = "/dataGrid", method = RequestMethod.POST)
    @ResponseBody
    public Object dataGrid(GpztclSczxInfoDto dto,Integer page, Integer rows, String sort, String order)
    {
    	PageInfo pageInfo = new PageInfo(0, 65530, "hh", "desc");
        Map<String, Object> condition = new HashMap<String, Object>();
        condition.put("fid", dto.getFid());
        pageInfo.setCondition(condition);
        try {
           gpztclSczxInfoService.selectDataGrid(pageInfo);
        } catch (Exception e) {
           e.printStackTrace();
        }
        return pageInfo;
    }

    /**
     * 添加实测中线信息明细管理
     * @param gczlYdxjGPZLDDInfo 要新增的实体信息
     * @return 返回保存成功/失败的提示信息
     */
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public Object add(GpztclSczxInfoDto gpztclSczxInfoDto) {
    	String errmessage = "";
        try {
          gpztclSczxInfoService.save(gpztclSczxInfoDto,getCurrentUser());
          log(null, true, "新增成功!");
        } catch (Exception e) {
          log(null, false, "新增失败!");
          e.printStackTrace();
        }
        return renderSuccess("添加成功！");
    }

    /**
     * 删除实测中线信息明细管理
     * @param ids 要删除的数据的ID集合
     * @return 返回删除成功/失败的提示信息
     */
    @RequestMapping("/delete")
    @ResponseBody
    public Object delete(String ids) {
    	String[] idss = ids.split(",");
    	List<String> idlist = new ArrayList<String>();
    	Collections.addAll(idlist, idss);
    	gpztclSczxInfoService.deleteBatchIds(idlist);
    	log(null, true, "删除："+ids);
    	return renderSuccess("删除成功！");
    }

    /**
     * 修改实测中线信息明细管理
     * @param gczlYdxjGPZLDDInfo 要修改的实体信息
     * @return 返回编辑成功/失败的提示信息
     */
    @RequestMapping("/edit")
    @ResponseBody
    public Object edit(GpztclSczxInfoDto gpztclSczxInfoDto) {
    	String errmessage = "";
        try {
           errmessage = gpztclSczxInfoService.update(gpztclSczxInfoDto,getCurrentUser());
           if(StringUtils.isNotBlank(errmessage)){
             log(null, false, errmessage);
             return renderError(errmessage);
           }
           log(null, true, "编辑成功:"+gpztclSczxInfoDto.getId());
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
	public void expXls(ExportDto exportDto,GpztclSczxInfo dto, HttpServletResponse response){
		try {
			Map<String, Object> condition = new HashMap<String, Object>();
			if(StringUtils.isNotBlank(exportDto.getIds())){
    			List<String> idsList =new ArrayList<String>();
    			String[] array = exportDto.getIds().split(ExportDto.SPLIT_STR);
    			String ids = "";
    			for(String id:array){
    				ids +=",'"+id+"'";
    			}
    			ids = ids.substring(1);
    			condition.put("ids", ids);
    		}
			PageInfo pageInfo = new PageInfo(0, 65530, "id", "desc");
			condition.put("fid", dto.getFid());
			pageInfo.setCondition(condition);
			gpztclSczxInfoService.selectDataGrid(pageInfo);
			gpztclSczxInfoService.myWriteExcelToClient(response, exportDto, ExportSetUtil.objectToListMap(pageInfo.getRows()));
    	} catch (Exception e) {
    		e.printStackTrace();
    	}
    }
    
    
//    @SuppressWarnings({"unchecked", "rawtypes"})
//	@RequestMapping(value="/expXls")
//	public void expXls1(ExportDto exportDto,GpztclSczxInfo dto, HttpServletResponse response){
//		try {
//			if(StringUtils.isNotBlank(exportDto.getIds())){
//    			List<String> idsList =new ArrayList<String>();
//				Collections.addAll(idsList, exportDto.getIds().split(ExportDto.SPLIT_STR));
//				List list = gpztclSczxInfoService.selectBatchIds(idsList);
//				ExportExcelUtil.writeExcelToClient(response, exportDto, ExportSetUtil.objectToListMap(list));
//    		}else{
//    	        PageInfo pageInfo = new PageInfo(0, 65530, "id", "desc");
//		        Map<String, Object> condition = new HashMap<String, Object>();
//    	        pageInfo.setCondition(condition);
//    	        gpztclSczxInfoService.selectDataGrid(pageInfo);
//    			ExportExcelUtil.writeExcelToClient(response, exportDto, ExportSetUtil.objectToListMap(pageInfo.getRows()));
//    		}
//    	} catch (Exception e) {
//    		e.printStackTrace();
//    	}
//    }
    
    
    /**
     * 数据导出
     * @param exportDto
     * @param Dto
     * @param response
     */
     @SuppressWarnings({"unchecked", "rawtypes"})
     @RequestMapping(value="/expPdf")
     public void expPdf(ExportDto exportDto,GpztclSczxInfo dto, HttpServletResponse response){
	     	try {
	     		if(StringUtils.isNotBlank(exportDto.getIds())){
	     			List<String> idsList =new ArrayList<String>();
	     			 Collections.addAll(idsList, exportDto.getIds().split(ExportDto.SPLIT_STR));
	     			 List list = gpztclSczxInfoService.selectBatchIds(idsList);
	     			 ExportPdfUtil.writePdfToClient(response, exportDto, ExportSetUtil.objectToListMap(list));
	     		}else{
	     	        PageInfo pageInfo = new PageInfo(0, 65530, "id", "desc");
	     	        Map<String, Object> condition = new HashMap<String, Object>();
	     	        pageInfo.setCondition(condition);
	     	        gpztclSczxInfoService.selectDataGrid(pageInfo);
	     			ExportPdfUtil.writePdfToClient(response, exportDto, ExportSetUtil.objectToListMap(pageInfo.getRows()));
	     		}
	     	} catch (Exception e) {
	     		e.printStackTrace();
	     	}
     }
     
     /**
     * 方法说明：解析全站仪数据
     * @param file 全站仪源文件
     * @param request
     * @return
     * @author:YangYj
     * @Time: 2016年12月22日 上午10:41:13
     */
     @RequestMapping(value="/parseQzyData", method = RequestMethod.POST)
     @ResponseBody
    public Object parseQzyData(@RequestParam(value = "qzyFile", required = false) MultipartFile file , HttpServletRequest request) {  
    	 List<TextFileDto> resultList = new ArrayList<TextFileDto>();
  		try {
  			if(null != file){
  				String fileName = file.getOriginalFilename();
  				String type = fileName.substring(fileName.lastIndexOf(".")+1);
  				if("txt".equals(type) || "dat".equals(type)){
  					//txt文本文件
  					resultList = this.gpztclSczxInfoService.parseTextFile(file);
  				}else{
  					return renderError("导入文件格式必须为txt或者dat文件,请确认。");
  				}
  				LOGGER.info("解析全站仪数据成功。");
  			}
		} catch (Exception e) {
			e.printStackTrace();
		}
  		return renderSuccess(resultList);
      }
     
     /**
     * 方法说明：将解析的文件列按照自定义进行调整，拼装成对应的实测中线明细数据
     * @param dto 配置信息
     * @return
     * @author:YangYj
     * @Time: 2016年12月22日 下午4:11:39
     */
    @RequestMapping(value="/configParseQzyData", method = RequestMethod.POST)
     @ResponseBody
    public Object configParseQzyData(GpztclSczxInfoDto dto) {  
    	 List<GpztclSczxInfoDto> resultList = new ArrayList<GpztclSczxInfoDto>();
   		try {
   			resultList = this.gpztclSczxInfoService.configParseQzyData(dto);
 		} catch (Exception e) {
 			e.printStackTrace();
 		}
   		return renderSuccess(resultList);
      }
    
    /**
     * 方法说明：解析导向系统数据，并返回完整的数据集合
     * @param file 需要解析的导向系统数据文件
     * @param request
     * @return
     * @author:YangYj
     * @Time: 2016年12月26日 下午12:21:51
     */
    @RequestMapping(value="/parseDaoXiangSystemFile", method = RequestMethod.POST)
    @ResponseBody
   public Object parseDaoXiangSystemFile(@RequestParam(value = "dxxtFile", required = false) MultipartFile file , HttpServletRequest request,GpztclSczxpcDto dto) {  
   	 List<GpztclSczxInfoDto> resultList = new ArrayList<GpztclSczxInfoDto>();
 		try {
 			if(null != file){
 				String fileName = file.getOriginalFilename();
 				String type = fileName.substring(fileName.lastIndexOf(".")+1);
 				if("db".equals(type) ||"DB".equals(type)){
 					//txt文本文件
 					resultList = this.gpztclSczxInfoService.parseDaoXiangDbFile(file,dto);
 				}else{
 					return renderError("导入文件格式必须为db或者DB文件,请确认。");
 				}
 				LOGGER.info("解析导向系统数据成功。");
 			}
		} catch (Exception e) {
			e.printStackTrace();
		}
 		return renderSuccess(resultList);
     }
    
    /**
     * 方法说明：：计算线路中心线设计坐标
     * @param request
     * @param dto
     * @return
     * @author:YangYj
     * @Time: 2016年12月27日 下午6:44:56
     */
    @RequestMapping(value="/countZb", method = RequestMethod.POST)
    @ResponseBody
   public Object countZb(HttpServletRequest request,GpztclSczxInfoDto dto) {  
    	String lineId = (String)request.getParameter("lineId");
   	    List<GpztclSczxInfoDto> resultList = new ArrayList<GpztclSczxInfoDto>();
 		try {
 			    resultList = this.gpztclSczxInfoService.countZb(dto,lineId);
 				LOGGER.info("计算设计中线坐标");
		} catch (Exception e) {
			e.printStackTrace();
		}
 		return renderSuccess(resultList);
     }


}