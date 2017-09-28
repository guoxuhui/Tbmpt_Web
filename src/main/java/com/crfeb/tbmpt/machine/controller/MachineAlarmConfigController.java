package com.crfeb.tbmpt.machine.controller;

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
import com.crfeb.tbmpt.commons.base.BaseController;
import com.crfeb.tbmpt.commons.utils.ExportDto;
import com.crfeb.tbmpt.commons.utils.ExportExcelUtil;
import com.crfeb.tbmpt.commons.utils.ExportPdfUtil;
import com.crfeb.tbmpt.commons.utils.ExportSetUtil;
import com.crfeb.tbmpt.commons.utils.PageInfo;
import com.crfeb.tbmpt.commons.utils.StringUtils;
import com.crfeb.tbmpt.dmcjjc.info.controller.DmcjJcInfoAction;
import com.crfeb.tbmpt.machine.model.MachineAlarmConfig;
import com.crfeb.tbmpt.machine.service.IMachineAlarmConfigService;

/**
 * <p>机器数据报警类别配置 controlle</p>
 * <p>模块：盾构机报警管理</p>
 * <p>日期：2017-06-06</p>
 * @version 1.0
 * @author wl_wpg
 */
@Controller
@RequestMapping(value="/machine/alarmhisConfig")
public class MachineAlarmConfigController extends BaseController{

	
	private static final Logger LOGGER = LogManager
			.getLogger(DmcjJcInfoAction.class);
	
	@Autowired 
	private IMachineAlarmConfigService machineAlarmConfigService;
	
	
	
	@RequestMapping(value = "", method = RequestMethod.GET)
    public String manager() {
        return "machine/alarmhisConfig/list";
    }
	
	/**
     * 地质信息easyUi列表
     * @param pageNo 页码
     * @param pageSize 每页条数
     * @param dto 查询条件dto
     * @return 返回查询结果信息
     */
    @RequestMapping(value = "/dataGrid", method = RequestMethod.POST)
    @ResponseBody
    public Object dataGrid(MachineAlarmConfig dto,Integer page, Integer rows, String sort, String order){
        PageInfo pageInfo = new PageInfo(page, rows, sort, order);
        Map<String, Object> condition = new HashMap<String, Object>();
        pageInfo.setCondition(condition);
        
        if(StringUtils.isNotBlank(dto.getName())){
    		condition.put("name", dto.getName());
		}
        if(StringUtils.isNotBlank(dto.getElectricalsystem())){
    		condition.put("electricalsystem", dto.getElectricalsystem());
		}
        
        try {
        	machineAlarmConfigService.selectDataGrid(pageInfo);
        } catch (Exception e) {
           e.printStackTrace();
        }
       
        return pageInfo;
    }
	

    /**
     * 添加
     * @param 要新增的实体信息
     * @return 返回保存成功/失败的提示信息
     */
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public Object add(MachineAlarmConfig config) {
    	
        try {
        	if(StringUtils.isNotBlank(config.getName())
        		&& StringUtils.isNotBlank(config.getElectricalsystem())){
        		machineAlarmConfigService.insert(config);
        		log("新增", true, "新增成功!");
        		LOGGER.info("机器数据报警类别配置新增成功！");
        		return renderSuccess("添加成功!");
        	}
          
        } catch (Exception e) {
          log("新增", false, "新增失败!");
          e.printStackTrace();
          LOGGER.info("MachineAlarmConfigController add新增失败!",e);
          return renderError("新增失败!");
        }
        log("新增", false, "新增失败!");
        LOGGER.info("MachineAlarmConfigController add新增失败!");
        return renderError("新增失败!");
    }

    /**
     * 修改
     * @param  要修改的实体信息
     * @return 返回编辑成功/失败的提示信息
     */
    @RequestMapping("/edit")
    @ResponseBody
    public Object edit(MachineAlarmConfig config) {
    	
        try {
        	if(StringUtils.isNotBlank(config.getId())
        		&& StringUtils.isNotBlank(config.getName())
        		&& StringUtils.isNotBlank(config.getElectricalsystem())){
        		machineAlarmConfigService.updateById(config);
        		log("编辑", true, "编辑成功!");
        		LOGGER.info("机器数据报警类别配置编辑成功!");
        		return renderSuccess("编辑成功!");
        	}
          
        } catch (Exception e) {
          log("编辑", false, "编辑失败!");
          e.printStackTrace();
          LOGGER.info("MachineAlarmConfigController edit编辑失败!",e);
        }
        log("编辑", false, "编辑失败!");
        LOGGER.info("MachineAlarmConfigController edit编辑失败!");
        return renderError("编辑失败!");
    }
    
    /**
     * 删除
     * @param ids 要删除的数据的ID集合
     * @return 返回删除成功/失败的提示信息
     */
    @RequestMapping("/delete")
    @ResponseBody
    public Object delete(String ids) {
    	
    	try {
    		String[] idss = ids.split(",");
        	List<String> idlist = new ArrayList<String>();
        	Collections.addAll(idlist, idss);
        	machineAlarmConfigService.deleteBatchIds(idlist);
        	log("删除", true, "删除："+ids);
        	LOGGER.info("MachineAlarmConfigController delete删除成功!");
        	return renderSuccess("删除成功！");
          
        } catch (Exception e) {
          log("删除", false, "删除失败!");
          e.printStackTrace();
          LOGGER.info("MachineAlarmConfigController delete删除失败!",e);
          return renderError("删除失败!");
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
	public void expXls(ExportDto exportDto,MachineAlarmConfig dto, HttpServletResponse response){
		try {
			if(StringUtils.isNotBlank(exportDto.getIds())){
    			List<String> idsList =new ArrayList<String>();
				Collections.addAll(idsList, exportDto.getIds().split(ExportDto.SPLIT_STR));
				List list = machineAlarmConfigService.selectBatchIds(idsList);
				
				ExportExcelUtil.writeExcelToClient(response, exportDto, ExportSetUtil.objectToListMap(list));
    		}else{
    	        PageInfo pageInfo = new PageInfo(0, 65530, "id", "desc");
		        Map<String, Object> condition = new HashMap<String, Object>();
    	        pageInfo.setCondition(condition);
    	        if(StringUtils.isNotBlank(dto.getName())){
    	    		condition.put("name", dto.getName());
    			}
    	        if(StringUtils.isNotBlank(dto.getElectricalsystem())){
    	    		condition.put("electricalsystem", dto.getElectricalsystem());
    			}
    	        machineAlarmConfigService.selectDataGrid(pageInfo);
    	        List list = pageInfo.getRows();
     	        
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
     public void expPdf(ExportDto exportDto,MachineAlarmConfig dto, HttpServletResponse response){
	     	try {
	     		if(StringUtils.isNotBlank(exportDto.getIds())){
	     			List<String> idsList =new ArrayList<String>();
					Collections.addAll(idsList, exportDto.getIds().split(ExportDto.SPLIT_STR));
					List list = machineAlarmConfigService.selectBatchIds(idsList);
	     			 
	     			 ExportPdfUtil.writePdfToClient(response, exportDto, ExportSetUtil.objectToListMap(list));
	     		}else{
	     			PageInfo pageInfo = new PageInfo(0, 65530, "id", "desc");
			        Map<String, Object> condition = new HashMap<String, Object>();
	    	        pageInfo.setCondition(condition);
	    	        if(StringUtils.isNotBlank(dto.getName())){
	    	    		condition.put("name", dto.getName());
	    			}
	    	        if(StringUtils.isNotBlank(dto.getElectricalsystem())){
	    	    		condition.put("electricalsystem", dto.getElectricalsystem());
	    			}
	    	        machineAlarmConfigService.selectDataGrid(pageInfo);
	    	        List list = pageInfo.getRows();
	    	        
	     			ExportPdfUtil.writePdfToClient(response, exportDto, ExportSetUtil.objectToListMap(list));
	     		}
	     	} catch (Exception e) {
	     		e.printStackTrace();
	     	}
     }
     
    
	
	
	
	
	
}
