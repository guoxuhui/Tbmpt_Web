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
import com.crfeb.tbmpt.commons.utils.StringUtils;
import com.crfeb.tbmpt.sys.model.Orgz;
import com.crfeb.tbmpt.sys.model.User;
import com.crfeb.tbmpt.sys.service.IOrgzService;

/**
 * @description：部门管理
 * @author：smxg
 * @date：2016-10-10 11:12
 */
@Controller
@RequestMapping("/sys/orgz")
public class OrgzController extends BaseController {

    @Autowired
    private IOrgzService orgzService;

    /**
     * 部门管理主页
     *
     * @return
     */
    @RequestMapping(value = "", method = RequestMethod.GET)
    public String manager() {
        return "sys/orgz/index";
    }

    /**
     * 部门资源树
     *
     * @return
     */
    @RequestMapping(value = "/tree2", method = RequestMethod.POST)
    @ResponseBody
    public Object tree() {
        return orgzService.selectTree();
    }
    
    /**
     * 部门资源树
     *
     * @return
     */
    @RequestMapping(value = "/tree", method = RequestMethod.POST)
    @ResponseBody
    public Object treeByUid(String id,String flag) {
    	User user = getCurrentUser();
    	if("true".equals(flag)){
    		return orgzService.selectTreeByUser(user,id);
    	}else{
    		if("admin".equals(user.getLoginName())){
    			return orgzService.selectTree();
    		}else{
    			return orgzService.selectTreeByUser(user);
    		}
    	}
        
    }
    
    /**
     * 部门列表
     *
     * @return
     */
    @RequestMapping("/treeGrid2")
    @ResponseBody
    public Object treeGrid2() {
        return orgzService.selectTreeGrid();
        
    }
    
    /**
     * 部门列表
     *
     * @return
     */
    @RequestMapping("/treeGrid")
    @ResponseBody
    public Object treeGrid() {
    	User user = getCurrentUser();
        return orgzService.selectTreeGridByUser(user);
    }

    /**
     * 添加部门页
     *
     * @return
     */
    @RequestMapping("/addPage")
    public String addPage() {
        return "sys/orgz/add";
    }

    /**
     * 添加部门
     *
     * @param orgz
     * @return
     */
    @RequestMapping("/add")
    @ResponseBody
    public Object add(Orgz orgz) {
        orgzService.save(orgz);
        return renderSuccess("添加成功！");
    }

    /**
     * 编辑部门页
     *
     * @param request
     * @param id
     * @return
     */
    @RequestMapping("/editPage")
    public String editPage(HttpServletRequest request, String id) {
        Orgz orgz = orgzService.selectById(id);
        request.setAttribute("orgz", orgz);
        return "sys/orgz/edit";
    }

    /**
     * 编辑部门
     *
     * @param orgz
     * @return
     */
    @RequestMapping("/edit")
    @ResponseBody
    public Object edit(Orgz orgz) {
    	String result ="";
    	try {
    		if(orgz !=null){
    			result = orgzService.editSave(orgz);
    			if(result=="" && result.equals("")){
        		     return renderSuccess("编辑成功！");
        	    }
    		}else{
    			return renderError("编辑失败！");
    		}
		} catch (Exception e) {
			e.printStackTrace();
			return renderError("编辑失败！");
		}
    	return renderError(result);
    	
    }

    /**
     * 删除部门
     *
     * @param id
     * @return
     */
    @RequestMapping("/delete")
    @ResponseBody
    public Object delete(String id) {
       
        String result ="";
    	try {
    		if(StringUtils.isNotBlank(id)){
    			result = orgzService.deleteOperate(id);
    			if(result=="" && result.equals("")){
    				return renderSuccess("删除成功！");
        	    }
    			else if(result.equals("deleteOrgzTree")){
    				return renderSuccess(result);
        	    }
    			else if(result.equals("该部门的下级部门超过三级")){
        	    	return renderError("该部门的下级部门超过三级，该部门不能删除！请先删除下级部门！");
        	    }
    		}
		} catch (Exception e) {
			e.printStackTrace();
			return renderError("编辑失败！");
		}
    	return renderError("编辑失败！");
    }
    /***
     * 删除机构部门树
     * @param id
     * @return
     */
    @RequestMapping("/deleteTree")
    @ResponseBody
    public Object deleteTree(String id) {
    	try {
    		if(orgzService.deleteOrgz(id))
            {
           	    return renderSuccess("删除成功！"); 
            }else{
                return renderError("删除失败！"); 
            }
		} catch (Exception e) {
			e.printStackTrace();
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
	    	User user = getCurrentUser();
	    	List tbmlist = orgzService.selectTreeGridByUser(user);
			ExportExcelUtil.writeExcelToClient(response, exportDto, ExportSetUtil.objectToListMap(tbmlist));
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
	    	User user = getCurrentUser();
	    	List tbmlist = orgzService.selectTreeGridByUser(user);
			ExportPdfUtil.writePdfToClient(response, exportDto, ExportSetUtil.objectToListMap(tbmlist));
		} catch (Exception e) {
			e.printStackTrace();	
		}
	}
}
