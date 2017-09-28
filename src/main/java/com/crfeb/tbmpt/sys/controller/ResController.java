package com.crfeb.tbmpt.sys.controller;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.crfeb.tbmpt.commons.base.BaseController;
import com.crfeb.tbmpt.commons.utils.DateUtils;
import com.crfeb.tbmpt.commons.utils.ExportDto;
import com.crfeb.tbmpt.commons.utils.ExportExcelUtil;
import com.crfeb.tbmpt.commons.utils.ExportPdfUtil;
import com.crfeb.tbmpt.commons.utils.ExportSetUtil;
import com.crfeb.tbmpt.sys.model.Res;
import com.crfeb.tbmpt.sys.model.User;
import com.crfeb.tbmpt.sys.service.IResService;

/**
 * @description：资源管理
 * @author：smxg
 * @date：2016-10-10 11:12
 */
@Controller
@RequestMapping("/sys/resource")
public class ResController extends BaseController {

    @Autowired
    private IResService resourceService;

    /**
     * 菜单树
     *
     * @return
     */
    @RequestMapping(value = "/tree", method = RequestMethod.POST)
    @ResponseBody
    public Object tree() {
        User currentUser = getCurrentUser();
        return resourceService.selectTree(currentUser);
    }
    
    /**
     * 菜单树
     *
     * @return
     */
    @RequestMapping(value = "/menu", method = RequestMethod.POST)
    @ResponseBody
    public Object menu() {
        User currentUser = getCurrentUser();
        return resourceService.selectMenu(currentUser);
    }
    
    /**
     * 菜单树
     *
     * @return
     */
    @RequestMapping(value = "/menu2", method = RequestMethod.POST)
    @ResponseBody
    public Object menu2(String title) {
        User currentUser = getCurrentUser();
        return resourceService.selectMenu2(currentUser,title);
    }
    

    /**
     * 资源管理页
     *
     * @return
     */
    @RequestMapping(value = "", method = RequestMethod.GET)
    public String manager() {
        return "sys/resource/index";
    }

    /**
     * 资源管理列表
     *
     * @return
     */
    @RequestMapping(value = "/treeGrid", method = RequestMethod.POST)
    @ResponseBody
    public Object treeGrid() {
        return resourceService.selectAll();
    }
    /**
     * 添加资源页
     *
     * @return
     */
    @RequestMapping("/addPage")
    public String addPage() {
    	return "sys/resource/add";
    }

    /**
     * 添加资源
     *
     * @param resource
     * @return
     */
    @RequestMapping("/add")
    @ResponseBody
    public Object add(Res resource) {
    	resource.setCreateTime(DateUtils.getToday());
        resourceService.insert(resource);
        return renderSuccess("添加成功！");
    }

    /**
     * 二级资源树
     *
     * @return
     */
    @RequestMapping("/allTree")
    @ResponseBody
    public Object allTree() {
        return resourceService.selectAllTree();
    }

    /**
     * 三级资源树
     *
     * @return
     */
    @RequestMapping(value = "/allTrees", method = RequestMethod.POST)
    @ResponseBody
    public Object allTrees() {
        return resourceService.allSelectTree();
    }

    /**
     * 编辑资源页
     *
     * @param model
     * @param id
     * @return
     */
    @RequestMapping("/editPage")
    public String editPage(Model model, String id) {
        Res resource = resourceService.selectById(id);
        model.addAttribute("resource", resource);
        return "sys/resource/edit";
    }

    /**
     * 编辑资源
     *
     * @param resource
     * @return
     */
    @RequestMapping("/edit")
    @ResponseBody
    public Object edit(Res resource) {
        resourceService.updateById(resource);
        return renderSuccess("编辑成功！");
    }

    /**
     * 删除资源
     *
     * @param id
     * @return
     */
    @RequestMapping("/delete")
    @ResponseBody
    public Object delete(String id) {
        resourceService.deleteById(id);
        return renderSuccess("删除成功！");
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
	        User currentUser = getCurrentUser();
	        List tbmlist = resourceService.selectTree(currentUser);
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
	        User currentUser = getCurrentUser();
	        List tbmlist = resourceService.selectTree(currentUser);
			ExportPdfUtil.writePdfToClient(response, exportDto, ExportSetUtil.objectToListMap(tbmlist));
		} catch (Exception e) {
			e.printStackTrace();	
		}
	}
	
	
    /**
     * 获取图标
     *
     * @return
     */
    @RequestMapping(value = "/icons")
    public String icons() {
    	return "sys/icons";
    }

}
