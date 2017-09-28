package com.crfeb.tbmpt.sgkshgl.jcdtgl.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

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
import com.crfeb.tbmpt.commons.utils.StringUtils;
import com.crfeb.tbmpt.project.model.ProProjectinfo;
import com.crfeb.tbmpt.project.model.ProRProjectSection;
import com.crfeb.tbmpt.project.model.ProRSectionLine;
import com.crfeb.tbmpt.project.service.IProProjectinfoService;
import com.crfeb.tbmpt.project.service.IProRProjectSectionService;
import com.crfeb.tbmpt.project.service.IProRSectionLineService;
import com.crfeb.tbmpt.sgkshgl.jcdtgl.model.dto.SgkshglJcdtglDto;
import com.crfeb.tbmpt.sgkshgl.jcdtgl.service.ISgkshglJcdtglService;
import com.crfeb.tbmpt.sys.model.User;

/**
 * <p>基础底图管理 controlle</p>
 * <p>模块：施工可视化管理</p>
 * <p>日期：2017-04-12</p>
 * @version 1.0
 * @author wl_wpg
 */
@Controller
@RequestMapping(value="/sgkshgl/jcdtgl")
public class SgkshglJcdtglController extends BaseController{

	@Autowired
	IProProjectinfoService proProjectinfoService;
	@Autowired
    private IProRSectionLineService proRSectionLineService;
    @Autowired
    private IProRProjectSectionService proRProjectSectionService;

    @Autowired
    private ISgkshglJcdtglService sgkshglJcdtglService;
  
    
    /**
     * 基础底图管理页
     *
     * @return
     */
    @RequestMapping(value = "", method = RequestMethod.GET)
    public String manager() {
        return "sgkshgl/jcdtgl/index";
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
     * 列表
     *
     * @param page
     * @param rows
     * @param sort
     * @param order
     * @return
     */
    @RequestMapping(value = "/dataGrid", method = RequestMethod.POST)
    @ResponseBody
    public Object dataGrid(SgkshglJcdtglDto dto) {
    	List<SgkshglJcdtglDto> jcdtglList = new ArrayList<SgkshglJcdtglDto>();
        if (StringUtils.isNotBlank(dto.getRefId())) {
        	jcdtglList = sgkshglJcdtglService.selectDataGridByRefId(dto.getRefId());
        }
        else{
        	jcdtglList = sgkshglJcdtglService.selectDataGrid(getCurrentUser());
        }
        
        return jcdtglList;
    }

    /**
     * 打开添加页面
     *
     * @return
     */
    @RequestMapping(value = "/addPage", method = RequestMethod.GET)
    public String addPage() {
    	return "sgkshgl/jcdtgl/add";
    }

    /**
     * 添加保存
     *
     * @param role
     * @return
     */
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public Object add(SgkshglJcdtglDto dto) {
    	
    	try {
    		String consequence= sgkshglJcdtglService.addSave(dto);
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
        	sgkshglJcdtglService.deleteBatchIds(idlist);
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
    	
        return "sgkshgl/jcdtgl/edit";
    }

    /**
     * 编辑保存
     *
     * @param role
     * @return
     */
    @RequestMapping("/edit")
    @ResponseBody
    public Object edit(SgkshglJcdtglDto dto) {
    	try {
    		String consequence = sgkshglJcdtglService.edit(dto);
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
	public void expXls(ExportDto exportDto , HttpServletResponse response){
		try {
			if(StringUtils.isNotBlank(exportDto.getIds())){
				List<String> idsList =new ArrayList<String>();
				Collections.addAll(idsList, exportDto.getIds().split(ExportDto.SPLIT_STR));
				List list = sgkshglJcdtglService.selectBatchIds(idsList);
				ExportExcelUtil.writeExcelToClient(response, exportDto, ExportSetUtil.objectToListMap(list));
			}else{
		        List list = sgkshglJcdtglService.selectDataGrid(getCurrentUser());
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
	public void expPdf(ExportDto exportDto, HttpServletResponse response){
		try {
			if(StringUtils.isNotBlank(exportDto.getIds())){
				List<String> idsList =new ArrayList<String>();
				 Collections.addAll(idsList, exportDto.getIds().split(ExportDto.SPLIT_STR));
				 List list = sgkshglJcdtglService.selectBatchIds(idsList);
				 ExportPdfUtil.writePdfToClient(response, exportDto, ExportSetUtil.objectToListMap(list));
			}else{
				List list = sgkshglJcdtglService.selectDataGrid(getCurrentUser());
				ExportPdfUtil.writePdfToClient(response, exportDto, ExportSetUtil.objectToListMap(list));
			}
		} catch (Exception e) {
			e.printStackTrace();	
		}
	}

	
	
	 /**
     * 获取项目信息
     * @return
     */
    @RequestMapping(value = "/getProDic", method = RequestMethod.POST)
    @ResponseBody
    public Object getProDic() {
    	User user = getCurrentUser();
    	List<ProProjectinfo> list = proProjectinfoService.getProjectInfosByUserId(String.valueOf(user.getId()));
    	return list;
    }

    /**
     * 获取项目区间信息
     * @return
     */
    @RequestMapping(value = "/getProSectionDic", method = RequestMethod.POST)
    @ResponseBody
    public Object getProSectionDic(String proId) {
    	if(StringUtils.isEmpty(proId)){
    		return renderError("查询失败");
    	}else{
        	List<ProRProjectSection> list = proRProjectSectionService.getSectionByProId(proId);
        	return list;
    	}
    }
    
    /**
     * 获取区间线路信息
     * @return
     */
    @RequestMapping(value = "/getProXlDic", method = RequestMethod.POST)
    @ResponseBody
    public Object getProLineDic(String sectionId) {
    	
    	if(StringUtils.isEmpty(sectionId)){
    		return renderError("查询失败");
    	}else{
        	List<ProRSectionLine> list = proRSectionLineService.getLineBySectionId(sectionId);
        	return list;
    	}
    }
	
	
	
	
	
	
}