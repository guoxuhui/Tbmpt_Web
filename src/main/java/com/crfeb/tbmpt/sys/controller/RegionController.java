package com.crfeb.tbmpt.sys.controller;


import java.util.List;

import javax.servlet.http.HttpServletRequest;
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
import com.crfeb.tbmpt.sys.model.Region;
import com.crfeb.tbmpt.sys.service.IRegionService;

/**
 * @description：区划管理
 * @author：wpg
 * 
 * @date：2016-11-15 11:36
 */

/**
 * 
 * @Controller    :Spring 注解当前类为 构造器 类
 * @RequestMapping("/sys/region") :Spring MVC 注解 当前类的地址，
 */
@Controller
@RequestMapping("/sys/region")
public class RegionController extends BaseController {

	//依赖注入注解： @Autowired
    @Autowired
    private IRegionService regionService;

    /**
     * 地区管理主页 
     *
     * @return
     */
    //请求地址映射的注解:@RequestMapping
    @RequestMapping(value = "", method = RequestMethod.GET)
    public String manager() {
    	//放回页面的路径
        return "sys/region/index";
    }

    /**
     * 地区资源树
     * 
     * 返回 List<Tree>
     * @return
     */
    @RequestMapping(value = "/tree", method = RequestMethod.POST)
    @ResponseBody
    public Object tree() {
        return regionService.selectTree();
    }
   
     /**
     * 列表
     * 查询 所有 地区 
     * @return
     */
    @RequestMapping("/treeGrid")
    @ResponseBody
    public Object treeGrid() {
    	
        return regionService.selectTreeGrRegion();
    }

    /**
     * 添加地区页 
     *
     * @return
     */
    @RequestMapping("/addPage")
    public String addPage() {
        return "sys/region/add";
    }

    /**
     * 添加地区
     *
     * @param orgz
     * @return
     */
    @RequestMapping("/add")
    @ResponseBody
    public Object add(Region region) {
        regionService.save(region);
        return renderSuccess("添加成功！");
    }

    /**
     * 编辑地区页
     *
     * @param request
     * @param id
     * @return
     */
    @RequestMapping("/editPage")
    public String editPage(HttpServletRequest request, String id) {
        Region region = regionService.selectById(id);
        request.setAttribute("region", region);
        return "sys/region/edit";
    }

    /**
     * 编辑地区
     *
     * @param region
     * @return
     */
    @RequestMapping("/edit")
    @ResponseBody
    public Object edit(Region region) {
        
        if(regionService.editRegion(region))
        {
        	 return renderSuccess("编辑成功！"); 
        }else{
        	return renderSuccess("编辑失败！"); 
        }
        
    }

    /**
     * 删除地区
     *
     * @param id
     * @return
     */
    @RequestMapping("/delete")
    @ResponseBody
    public Object delete(String id) {
    	//regionService.deleteRegion2(id)
    	int i=regionService.deleteByRid(id);
        if(i==1)
        {
       	    return renderSuccess("删除成功！"); 
        }else if(i==2){
        	return renderSuccess("deleteRegionTree"); 
        }else{
            return renderError("删除失败！"); 
        }
        
    }
    
    /***
     * 删除地区 树
     * @param id
     * @return
     */
    @RequestMapping("/deleteTree")
    @ResponseBody
    public Object deleteTree(String id) {
    	if(regionService.deleteRegion(id))
        {
       	    return renderSuccess("删除成功！"); 
        }else{
            return renderError("删除失败！"); 
        }
    }
    
    /**
     * 数据导出
     * @param exportDto
     * @param Dto
     * @param response
     */
	@SuppressWarnings({"unchecked","rawtypes"})
	@RequestMapping(value="/expXls")
	public void expXls(ExportDto exportDto, HttpServletResponse response){
		try {
	    	//查询 所有  地区
	    	List regionList = regionService.selectTreeGrRegion();
			ExportExcelUtil.writeExcelToClient(response, exportDto, ExportSetUtil.objectToListMap(regionList));
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
	@SuppressWarnings({"unchecked","rawtypes"})
	@RequestMapping(value="/expPdf")
	public void expPdf(ExportDto exportDto, HttpServletResponse response){
		try {
	    	
	    	List regionList = regionService.selectTreeGrRegion();
			ExportPdfUtil.writePdfToClient(response, exportDto, ExportSetUtil.objectToListMap(regionList));
		} catch (Exception e) {
			e.printStackTrace();	
		}
	}
}
