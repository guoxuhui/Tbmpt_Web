package com.crfeb.tbmpt.zsjk.report.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.crfeb.tbmpt.commons.base.BaseController;
import com.crfeb.tbmpt.commons.utils.ExportDto;
import com.crfeb.tbmpt.commons.utils.ExportExcelUtil;
import com.crfeb.tbmpt.commons.utils.ExportPdfUtil;
import com.crfeb.tbmpt.commons.utils.ExportSetUtil;
import com.crfeb.tbmpt.commons.utils.PageInfo;
import com.crfeb.tbmpt.commons.utils.StringUtils;
import com.crfeb.tbmpt.zsjk.report.model.ZsJkReportGxfx;
import com.crfeb.tbmpt.zsjk.report.model.dto.ZsJkReportGxfxDto;
import com.crfeb.tbmpt.zsjk.report.service.ZsJkReportGxfxService;



@Controller
@RequestMapping(value="/zsjk/report/zsJkReportGxfx")
public class ZsJkReportGxfxController extends BaseController{

    @Autowired
    private ZsJkReportGxfxService zsJkReportGxfxService;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public String manager() {
        return "zsjk/report/zsJkReportGxfx";
    }

    
  
  
    @RequestMapping(value = "/dataGrid", method = RequestMethod.POST)
    @ResponseBody
    public Object dataGrid(ZsJkReportGxfxDto dto,Integer page, Integer rows, String sort, String order)
    {
        PageInfo pageInfo = new PageInfo(page, rows, sort, order);
        Map<String, Object> condition = new HashMap<String, Object>();
        pageInfo.setCondition(condition);
        try {
           zsJkReportGxfxService.selectDataGrid(pageInfo);
        } catch (Exception e) {
           e.printStackTrace();
        }
        return pageInfo;
    }

    
    
    
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public Object add(ZsJkReportGxfxDto zsJkReportGxfxDto) {
    	String errmessage = "";
        try {
          zsJkReportGxfxService.save(zsJkReportGxfxDto,getCurrentUser());
          log(null, true, "新增成功!");
        } catch (Exception e) {
          log(null, false, "新增失败!");
          e.printStackTrace();
        }
        return renderSuccess("添加成功！");
    }

    
    
    
    @RequestMapping("/delete")
    @ResponseBody
    public Object delete(String ids) {
    	String[] idss = ids.split(",");
    	List<String> idlist = new ArrayList<String>();
    	Collections.addAll(idlist, idss);
    	zsJkReportGxfxService.deleteBatchIds(idlist);
    	log(null, true, "删除："+ids);
    	return renderSuccess("删除成功！");
    }

   
    
    
    @RequestMapping("/edit")
    @ResponseBody
    public Object edit(ZsJkReportGxfxDto zsJkReportGxfxDto) {
    	String errmessage = "";
        try {
           errmessage = zsJkReportGxfxService.update(zsJkReportGxfxDto,getCurrentUser());
           if(StringUtils.isNotBlank(errmessage)){
             log(null, false, errmessage);
             return renderError(errmessage);
           }
           log(null, true, "编辑成功:"+zsJkReportGxfxDto.getId());
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
    	public void expXls(ExportDto exportDto,ZsJkReportGxfx dto, HttpServletResponse response){
    		try {
    			if(StringUtils.isNotBlank(exportDto.getIds())){
	    			List<String> idsList =new ArrayList<String>();
    				Collections.addAll(idsList, exportDto.getIds().split(ExportDto.SPLIT_STR));
    				List list = zsJkReportGxfxService.selectBatchIds(idsList);
    				ExportExcelUtil.writeExcelToClient(response, exportDto, ExportSetUtil.objectToListMap(list));
	    		}else{
	    	        PageInfo pageInfo = new PageInfo(0, 65530, "id", "desc");
    		        Map<String, Object> condition = new HashMap<String, Object>();
	    	        pageInfo.setCondition(condition);
	    	        zsJkReportGxfxService.selectDataGrid(pageInfo);
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
     public void expPdf(ExportDto exportDto,ZsJkReportGxfx dto, HttpServletResponse response){
	     	try {
	     		if(StringUtils.isNotBlank(exportDto.getIds())){
	     			List<String> idsList =new ArrayList<String>();
	     			 Collections.addAll(idsList, exportDto.getIds().split(ExportDto.SPLIT_STR));
	     			 List list = zsJkReportGxfxService.selectBatchIds(idsList);
	     			 ExportPdfUtil.writePdfToClient(response, exportDto, ExportSetUtil.objectToListMap(list));
	     		}else{
	     	        PageInfo pageInfo = new PageInfo(0, 65530, "id", "desc");
	     	        Map<String, Object> condition = new HashMap<String, Object>();
	     	        pageInfo.setCondition(condition);
	     	        zsJkReportGxfxService.selectDataGrid(pageInfo);
	     			ExportPdfUtil.writePdfToClient(response, exportDto, ExportSetUtil.objectToListMap(pageInfo.getRows()));
	     		}
	     	} catch (Exception e) {
	     		e.printStackTrace();
	     	}
     }
     
     @RequestMapping("/list")
     @ResponseBody
     public Object list(String kssj,String jssj,String kshh,String jshh,String proId,String lineId) throws Exception {
     	
    	List<ZsJkReportGxfxDto> list=zsJkReportGxfxService.gxfx("2017-01-01", "2017-02-01", "5", "20", "b89bc80ae8894c2a84f33ffef04d411c", "78ace2bbdcc54fffbc3d77ff6218bd22");
//     	ModelAndView mv=new ModelAndView();
//     	mv.addObject("list",list);
//     	mv.setViewName("list/view");
//     	return mv;
     	return list;
     }

}
