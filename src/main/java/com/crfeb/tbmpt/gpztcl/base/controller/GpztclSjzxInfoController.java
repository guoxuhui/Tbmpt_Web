package com.crfeb.tbmpt.gpztcl.base.controller;

import java.io.IOException;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.crfeb.tbmpt.commons.base.BaseController;
import com.crfeb.tbmpt.commons.result.Result;
import com.crfeb.tbmpt.commons.utils.ExportDto;
import com.crfeb.tbmpt.commons.utils.ExportExcelUtil;
import com.crfeb.tbmpt.commons.utils.ExportPdfUtil;
import com.crfeb.tbmpt.commons.utils.ExportSetUtil;
import com.crfeb.tbmpt.commons.utils.PageInfo;
import com.crfeb.tbmpt.commons.utils.StringUtils;
import com.crfeb.tbmpt.commons.utils.UploadUtil;
import com.crfeb.tbmpt.gpztcl.base.model.GpztclSjzxInfo;
import com.crfeb.tbmpt.gpztcl.base.model.dto.GpztclSjzxInfoDto;
import com.crfeb.tbmpt.gpztcl.base.model.dto.GpztclSjzxInfoParentDto;
import com.crfeb.tbmpt.gpztcl.base.service.GpztclCalService;
import com.crfeb.tbmpt.gpztcl.base.service.GpztclSjzxInfoService;

/**
 * <p>线路中心线信息管理controlle</p>
 * <p>系统：管片姿态测量系统</p>
 * <p>模块：基础模块</p>
 * <p>日期：2016-12-20</p>
 * @version 1.0
 * @author wangbinbin
 */
@Controller
@RequestMapping(value="/gpztcl/base/gpztclSjzxInfo")
public class GpztclSjzxInfoController extends BaseController{

    @Autowired
    private GpztclSjzxInfoService gpztclSjzxInfoService;
    @Autowired
    private GpztclCalService gpztclCalService;

	  @RequestMapping(value = "", method = RequestMethod.GET)
	  public String manager() {
	      return "gpztcl/base/gpztclSjzxInfo";
	  }
  
	  /**
	   * 获取线路中心线信息管理easyUi列表
	   * @param pageNo 页码
	   * @param pageSize 每页条数
	   * @param dto 查询条件dto
	   * @return 返回查询结果信息
	   */
	  @RequestMapping(value = "/dataGridParent", method = RequestMethod.POST)
	  @ResponseBody
	  public Object dataGridParent(GpztclSjzxInfoParentDto dto,Integer page, Integer rows, String sort, String order)
	  {
	      PageInfo pageInfo = new PageInfo(page, rows, sort, order);
	      Map<String, Object> condition = new HashMap<String, Object>();
	      
	      condition.put("proId", dto.getProId());
	      condition.put("sqlPurview", this.sqlPurview("l","pro_id"));
	      pageInfo.setCondition(condition);
	      try {
	         gpztclSjzxInfoService.selectDataGridParent(pageInfo);
	      } catch (Exception e) {
	         e.printStackTrace();
	      }
	      /*
	      JSONArray arr = new JSONArray();
	      //{"环号": 340,  "坐标X": 46364.2988,  "坐标Y": 190583.5955}
	      JSONObject json = new JSONObject();
	      json.put("环号", 340);
	      json.put("坐标X", 46364.2988);
	      json.put("坐标Y", 190583.5955);
	      arr.add(json);
	      long star = System.currentTimeMillis();
	      System.out.println(gpztclCalService.calSjzxInfo(arr, "8e8c910507aa45c69287661afa4a8de0"));
	      long end = System.currentTimeMillis();
	      System.out.println("执行="+(end-star));
	      */
	      return pageInfo;
	  }

    /**
     * 获取线路中心线信息管理easyUi列表
     * @param pageNo 页码
     * @param pageSize 每页条数
     * @param dto 查询条件dto
     * @return 返回查询结果信息
     */
    @RequestMapping(value = "/dataGrid", method = RequestMethod.POST)
    @ResponseBody
    public Object dataGrid(GpztclSjzxInfoDto dto)
    {
    	PageInfo pageInfo = new PageInfo();
        try {
        	if(StringUtils.isBlank(dto.getXlBh()))return pageInfo;
        	List<GpztclSjzxInfo> list = gpztclSjzxInfoService.selectGpztclSjzxInfoList(dto.getXlBh());
        	pageInfo.setRows(list);
        } catch (Exception e) {
           e.printStackTrace();
        }
        return pageInfo;
    }
    
    @RequestMapping(value = "/downLoadModel")
    @ResponseBody
    public Object downLoadModel(GpztclSjzxInfoDto dto, HttpServletResponse response)
    {
    	String filepath = "/downloadMode/shejiZhongXianMode.xlsx";
    	UploadUtil.downloadFile2(filepath, request, response);
    	return null;
    }

    /**
     * 添加线路中心线信息管理
     * @param gczlYdxjGPZLDDInfo 要新增的实体信息
     * @return 返回保存成功/失败的提示信息
     */
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public Object add(GpztclSjzxInfoDto gpztclSjzxInfoDto) {
        try {
        	
        	
        	 Map<String, Object> columnMap = new HashMap<>();
        	 columnMap.put("XL_BH", gpztclSjzxInfoDto.getXlBh());
        	 gpztclSjzxInfoService.deleteByMap(columnMap);
          gpztclSjzxInfoService.save(gpztclSjzxInfoDto,getCurrentUser());
          log(null, true, "新增成功!");
        } catch (Exception e) {
          log(null, false, "新增失败!");
          e.printStackTrace();
        }
        return renderSuccess("添加成功！");
    }

    /**
     * 删除线路中心线信息管理
     * @param ids 要删除的数据的ID集合
     * @return 返回删除成功/失败的提示信息
     */
    @RequestMapping("/delete")
    @ResponseBody
    public Object delete(String ids) {
    	String[] idss = ids.split(",");
    	List<String> idlist = new ArrayList<String>();
    	Collections.addAll(idlist, idss);
    	gpztclSjzxInfoService.deleteBatchIds(idlist);
    	log(null, true, "删除："+ids);
    	return renderSuccess("删除成功！");
    }

    /**
     * 修改线路中心线信息管理
     * @param gczlYdxjGPZLDDInfo 要修改的实体信息
     * @return 返回编辑成功/失败的提示信息
     */
    @RequestMapping("/edit")
    @ResponseBody
    public Object edit(GpztclSjzxInfoDto gpztclSjzxInfoDto) {
    	String errmessage = "";
        try {
           errmessage = gpztclSjzxInfoService.update(gpztclSjzxInfoDto,getCurrentUser());
           if(StringUtils.isNotBlank(errmessage)){
             log(null, false, errmessage);
             return renderError(errmessage);
           }
           log(null, true, "编辑成功:"+gpztclSjzxInfoDto.getId());
        } catch (Exception e) {
           log(null, false, "编辑失败!");
           e.printStackTrace();
        }
	      log(null, true, errmessage);
        return renderSuccess("编辑成功!");
    }
    
    
    /**
     * 上传文件
     */
 	@RequestMapping("/upload")  
 	@ResponseBody
 	public Object upload(@RequestParam(value = "clumOne", required = false) MultipartFile file , HttpServletRequest request) {  
 		Result result = new Result();
 		try {
	 		List<GpztclSjzxInfoDto> dtos = gpztclSjzxInfoService.readXlsxIs(file);
	 		result.setObj(dtos);
	 		result.setSuccess(true);
	 		return result;
 		} catch (IOException e) {
 			result.setSuccess(false);
 			result.setMsg("解析失败!");
 			e.printStackTrace();
 		}

         return result;
     }

    /**     
    * 数据导出     
    * @param exportDto     
    * @param Dto     
    * @param response     
    */
    @SuppressWarnings({"unchecked", "rawtypes"})
    	@RequestMapping(value="/expXls")
    	public void expXls(ExportDto exportDto,GpztclSjzxInfo dto, HttpServletResponse response){
    		try {
    				String lineId = exportDto.getIds();
    				List<String> idsList =new ArrayList<String>();
    				if(StringUtils.isNotBlank(lineId)){
    					List<GpztclSjzxInfo> list2 = this.gpztclSjzxInfoService.selectGpztclSjzxInfoList(lineId);
    					List<Object> list=new ArrayList<Object>();
    					for (GpztclSjzxInfo gpztclSjzxInfo : list2) {
    						list.add(gpztclSjzxInfo);
    					}
    					ExportExcelUtil.writeExcelToClient(response, exportDto, ExportSetUtil.objectToListMap(list));
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
     public void expPdf(ExportDto exportDto,GpztclSjzxInfo dto, HttpServletResponse response){
	     	try {
	     		if(StringUtils.isNotBlank(exportDto.getIds())){
	     			List<String> idsList =new ArrayList<String>();
	     			 Collections.addAll(idsList, exportDto.getIds().split(ExportDto.SPLIT_STR));
	     			 List list = gpztclSjzxInfoService.selectBatchIds(idsList);
	     			 ExportPdfUtil.writePdfToClient(response, exportDto, ExportSetUtil.objectToListMap(list));
	     		}else{
	     	        PageInfo pageInfo = new PageInfo(0, 65530, "id", "desc");
	     	        Map<String, Object> condition = new HashMap<String, Object>();
	     	        pageInfo.setCondition(condition);
	     	        gpztclSjzxInfoService.selectDataGrid(pageInfo);
	     			ExportPdfUtil.writePdfToClient(response, exportDto, ExportSetUtil.objectToListMap(pageInfo.getRows()));
	     		}
	     	} catch (Exception e) {
	     		e.printStackTrace();
	     	}
     }

}