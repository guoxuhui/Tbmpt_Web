package com.crfeb.tbmpt.gpztcl.base.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.crfeb.tbmpt.commons.base.BaseController;
import com.crfeb.tbmpt.commons.utils.BeanUtils;
import com.crfeb.tbmpt.commons.utils.ExportDto;
import com.crfeb.tbmpt.commons.utils.ExportExcelUtil;
import com.crfeb.tbmpt.commons.utils.ExportPdfUtil;
import com.crfeb.tbmpt.commons.utils.ExportSetUtil;
import com.crfeb.tbmpt.commons.utils.PageInfo;
import com.crfeb.tbmpt.commons.utils.StringUtils;
import com.crfeb.tbmpt.gpztcl.base.model.GpztclSczxInfo;
import com.crfeb.tbmpt.gpztcl.base.model.GpztclSczxpc;
import com.crfeb.tbmpt.gpztcl.base.model.dto.GpztclSczxInfoDto;
import com.crfeb.tbmpt.gpztcl.base.model.dto.GpztclSczxpcDto;
import com.crfeb.tbmpt.gpztcl.base.service.GpztclSczxInfoService;
import com.crfeb.tbmpt.gpztcl.base.service.GpztclSczxpcService;

/**
 * <p>实测中线信息管理controlle</p>
 * <p>系统：管片姿态测量系统</p>
 * <p>模块：基础模块</p>
 * <p>日期：2016-12-20</p>
 * @version 1.0
 * @author YangYj
 */
@Controller
@RequestMapping(value="/gpztcl/base/gpztclSczxpc")
public class GpztclSczxpcController extends BaseController{
	private static final Logger LOGGER = LogManager.getLogger(GpztclSczxpcController.class);
    @Autowired
    private GpztclSczxpcService gpztclSczxpcService;
    
    @Autowired
    private GpztclSczxInfoService gpztclSczxInfoService;

  @RequestMapping(value = "", method = RequestMethod.GET)
    public String manager() {
        return "gpztcl/base/gpztclSczxpc";
    }

    /**
     * 获取实测中线信息管理easyUi列表
     * @param pageNo 页码
     * @param pageSize 每页条数
     * @param dto 查询条件dto
     * @return 返回查询结果信息
     */
    @RequestMapping(value = "/dataGrid", method = RequestMethod.POST)
    @ResponseBody
    public Object dataGrid(GpztclSczxpcDto dto,Integer page, Integer rows, String sort, String order)
    {
        PageInfo pageInfo = new PageInfo(page, rows, sort, order);
        Map<String, Object> condition = new HashMap<String, Object>();
        condition.put("gcBh", dto.getGcBh());
        condition.put("qlBh", dto.getQlBh());
        condition.put("xlBh", dto.getXlBh());
        condition.put("clType", dto.getClType());
        condition.put("clTime", dto.getClTime());
        String autoritySql = this.sqlPurview(null,null);
        condition.put("autoritySql", autoritySql);
        pageInfo.setCondition(condition);
        try {
           gpztclSczxpcService.selectDataGrid(pageInfo);
        } catch (Exception e) {
           e.printStackTrace();
        }
        return pageInfo;
    }

    /**
     * 添加实测中线信息管理
     * @param gczlYdxjGPZLDDInfo 要新增的实体信息
     * @return 返回保存成功/失败的提示信息
     */
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public Object add(GpztclSczxpcDto gpztclSczxpcDto) {
    	String errmessage = "";
        try {
    	errmessage = gpztclSczxpcService.checkClumIfexits(gpztclSczxpcDto,new String[]{"xlBh","clType","clTime"});
	    log(null, false, errmessage);
    	if(errmessage!=null) return renderError(errmessage);
          gpztclSczxpcService.save(gpztclSczxpcDto,getCurrentUser());
          log(null, true, "新增成功!");
        } catch (Exception e) {
          log(null, false, "新增失败!");
          e.printStackTrace();
        }
        return renderSuccess("添加成功！");
    }

    /**
     * 删除实测中线信息管理
     * @param ids 要删除的数据的ID集合
     * @return 返回删除成功/失败的提示信息
     */
    @RequestMapping("/delete")
    @ResponseBody
    public Object delete(String ids) {
    	String[] idss = ids.split(",");
    	try {
			this.gpztclSczxpcService.del(idss, this.getCurrentUser());
		} catch (Exception e) {
			e.printStackTrace();
		}
    	log(null, true, "删除："+ids);
    	return renderSuccess("删除成功！");
    }

    /**
     * 修改实测中线信息管理
     * @param gczlYdxjGPZLDDInfo 要修改的实体信息
     * @return 返回编辑成功/失败的提示信息
     */
    @RequestMapping("/edit")
    @ResponseBody
    public Object edit(GpztclSczxpcDto gpztclSczxpcDto) {
    	String errmessage = "";
        try {
        	errmessage = gpztclSczxpcService.checkClumIfexits(gpztclSczxpcDto,new String[]{"xlBh","clType","clTime"});
        	if(errmessage!=null) return renderError(errmessage);
           errmessage = gpztclSczxpcService.update(gpztclSczxpcDto,getCurrentUser());
           if(StringUtils.isNotBlank(errmessage)){
             log(null, false, errmessage);
             return renderError(errmessage);
           }
           log(null, true, "编辑成功:"+gpztclSczxpcDto.getId());
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
    	public void expXls(ExportDto exportDto,GpztclSczxpc dto, HttpServletResponse response){
    		try {
    			Map<String, Object> condition = new HashMap<String, Object>();
    			if(StringUtils.isNotBlank(exportDto.getIds())){
	    			String[] array = exportDto.getIds().split(ExportDto.SPLIT_STR);
	    			String ids = "";
	    			for(String id:array){
	    				ids +=",'"+id+"'";
	    			}
	    			ids = ids.substring(1);
	    			condition.put("ids", ids);
	    		}
	    	        PageInfo pageInfo = new PageInfo(0, 65530, "id", "desc");
	    	        pageInfo.setCondition(condition);
	    	        gpztclSczxpcService.selectDataGrid(pageInfo);
	    			ExportExcelUtil.writeExcelToClient(response, exportDto, ExportSetUtil.objectToListMap(pageInfo.getRows()));
	    		
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
     public void expPdf(ExportDto exportDto,GpztclSczxpc dto, HttpServletResponse response){
	     	try {
	     		if(StringUtils.isNotBlank(exportDto.getIds())){
	     			List<String> idsList =new ArrayList<String>();
	     			 Collections.addAll(idsList, exportDto.getIds().split(ExportDto.SPLIT_STR));
	     			 List list = gpztclSczxpcService.selectBatchIds(idsList);
	     			 ExportPdfUtil.writePdfToClient(response, exportDto, ExportSetUtil.objectToListMap(list));
	     		}else{
	     	        PageInfo pageInfo = new PageInfo(0, 65530, "id", "desc");
	     	        Map<String, Object> condition = new HashMap<String, Object>();
	     	        pageInfo.setCondition(condition);
	     	        gpztclSczxpcService.selectDataGrid(pageInfo);
	     			ExportPdfUtil.writePdfToClient(response, exportDto, ExportSetUtil.objectToListMap(pageInfo.getRows()));
	     		}
	     	} catch (Exception e) {
	     		e.printStackTrace();
	     	}
     }
     
     /**
     * 方法说明：上报/撤销上报操作
     * @param ids 物理主键值
     * @param state 上报操作 0：撤销上报；1：上报
     * @return
     * @author:YangYj
     * @Time: 2016年12月24日 下午2:08:02
     */
    @RequestMapping("/ifsB")
     @ResponseBody
     public Object ifsB(String ids,String state) {
     	String  sendState = "";
     	String optName = "";//操作
     	if(state.equals("0")){
     		optName = "撤销上报";
     		sendState = "已撤销";
     	}
     	if(state.equals("1")){
     		optName = "上报";
     		sendState = "已上报";
     	}
     	try {
			this.gpztclSczxpcService.ifSb(ids, sendState);
		} catch (Exception e) {
			e.printStackTrace();
			this.log(optName, false, "操作失败");
			return renderError("操作失败");
			
		}
     	this.log(optName, true, "操作成功。ids>>"+ids);
        return renderSuccess("操作成功!");
     }
    
    /**
   	 * 跳转到详情页面
   	 */
   @RequestMapping(value = "/showPage", method = RequestMethod.GET)
   public ModelAndView showPage(String id,String jcTime) {
	   ModelAndView model = new ModelAndView("gpztcl/base/gpztclSczxpcShowPage");
	   GpztclSczxpcDto dto = new GpztclSczxpcDto();
	   Map<String,Object> columnMap = new HashMap<String,Object>();
	   columnMap.put("fid", id);
	   try {
		    dto = this.gpztclSczxpcService.selectDtoById(id);
			List<GpztclSczxInfo> child = this.gpztclSczxInfoService.selectByMap(columnMap);
			dto.setDataList(JSON.toJSONString(child));
			model.addObject("dto", dto);
			model.addObject("dataList", child);
		} catch (Exception e) {
			e.printStackTrace();
		}
       return model;
   }

}