package com.crfeb.tbmpt.project.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.crfeb.tbmpt.commons.base.BaseController;
import com.crfeb.tbmpt.commons.utils.DateUtils;
import com.crfeb.tbmpt.commons.utils.ExportDto;
import com.crfeb.tbmpt.commons.utils.ExportExcelUtil;
import com.crfeb.tbmpt.commons.utils.ExportPdfUtil;
import com.crfeb.tbmpt.commons.utils.ExportSetUtil;
import com.crfeb.tbmpt.commons.utils.PageInfo;
import com.crfeb.tbmpt.commons.utils.StringUtils;
import com.crfeb.tbmpt.project.model.ProProjectinfo;
import com.crfeb.tbmpt.project.model.ProRProjectSection;
import com.crfeb.tbmpt.project.model.vo.ProjectinfoQeryVo;
import com.crfeb.tbmpt.project.service.IProProjectinfoService;
import com.crfeb.tbmpt.project.service.IProRProjectSectionService;
import com.crfeb.tbmpt.sys.model.Orgz;
import com.crfeb.tbmpt.sys.model.SysEmployee;
import com.crfeb.tbmpt.sys.model.User;
import com.crfeb.tbmpt.sys.service.IOrgzService;
import com.crfeb.tbmpt.sys.service.ISysEmployeeService;

/**
 * 工程管理模块
 * @author：smxg
 * @date：2016-10-10 11:12
 */
@Controller
@RequestMapping("/project/info")
public class ProProjectinfoController extends BaseController {

    @Autowired
    private IProProjectinfoService proProjectinfoService;
    @Autowired
    private IProRProjectSectionService proRProjectSectionService;
    @Autowired
    private IOrgzService orgzService;
    @Autowired
    private ISysEmployeeService sysEmployeeService;
    
    
    /**
     * @return
     */
    @RequestMapping(value = "", method = RequestMethod.GET)
    public String list() {
        return "project/info/index";
    }

    /**
     * @param page
     * @param rows
     * @param sort
     * @param order
     * @return
     */
    @RequestMapping(value = "/dataGrid", method = RequestMethod.POST)
    @ResponseBody
    public Object dataGrid(ProjectinfoQeryVo vo,Integer page, Integer rows, String sort, String order) {
        PageInfo pageInfo = new PageInfo(page, rows, sort, order);
        Map<String, Object> condition = new HashMap<String, Object>();
        if (StringUtils.isNotBlank(vo.getName())) {
            condition.put("name", vo.getName());
        }
        //项目简称
        if (StringUtils.isNotBlank(vo.getAbbreviation())) {
            condition.put("abbreviation", vo.getAbbreviation());
        }
        
        if (StringUtils.isNotBlank(vo.getStartTime())) {
            condition.put("startTime", vo.getStartTime());
        }
        if (StringUtils.isNotBlank(vo.getEndTime())) {
            condition.put("endTime", vo.getEndTime());
        }
        if (StringUtils.isNotBlank(vo.getProState()) && !vo.getProState().equals("-1") ) {
            condition.put("proState", vo.getProState());
        }
        condition.put("sqlPurview", this.sqlPurview("p","id"));
        pageInfo.setCondition(condition);

        proProjectinfoService.selectDataGrid(pageInfo,getCurrentUser());
        return pageInfo;
    }

    /**
     * @return
     */
    @RequestMapping(value = "/addPage", method = RequestMethod.GET)
    public String addPage() {
        return "project/info/add";
    }

    /**
     * @param role
     * @return
     */
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public Object add(ProProjectinfo t) {
    	User user = getCurrentUser();
    	Map<String,Object> map = new HashMap<String,Object>();
    	
        //项目名称是否重复
        map.put("pro_name", t.getProName());
        List<ProProjectinfo> pps = proProjectinfoService.selectByMap(map);
        if (pps != null && pps.size()>0) {
            return renderError("项目名称已经存在！");
        }
        
        map.clear();
        //项目不 是否 已 有项目
        map.put("parent_id", t.getParentId());
        List<ProProjectinfo> luser = proProjectinfoService.selectByMap(map);
        if (luser != null && luser.size()>0) {
            return renderError("所选项目部门已经关联，请选择其他项目部门！");
        }
        //获取‘ 录入人 ’ 名称
        t.setEnterperson(getCurrentUser().getName());
    	t.setEntertime(DateUtils.getToday());
    	proProjectinfoService.saveProjectInfo(t,user);
        return renderSuccess("添加成功！");
    }

    /**
     * @param id
     * @return
     */
    @RequestMapping("/delete")
    @ResponseBody
    public Object delete(String ids) {
    	
    	String[] idss = ids.split(",");
    	List<String> idlist = new ArrayList<String>();
    	Collections.addAll(idlist, idss);
    	EntityWrapper<ProRProjectSection> ew = new EntityWrapper<ProRProjectSection>();
    	ew.where("1=1").in("PRO_ID", idlist);
    	//查询该项目下是否有  区间 信息；
    	List<ProRProjectSection> list = proRProjectSectionService.selectList(ew);
    	if(list != null && list.size() > 0){
    		return renderError("操作项目存在区间信息，无法删除！");
    	}
    	// 删除 项目
    	Boolean falt = proProjectinfoService.deleteBatchIds(idlist);
    	if(falt){
    		return renderSuccess("删除成功！");
    	}else{
    		return renderSuccess("删除异常！");
    	}
    }

    /**
     * @param model
     * @param id
     * @return
     */
    @RequestMapping("/editPage")
    public String editPage(Model model, String id) {
		ProProjectinfo pro = proProjectinfoService.selectById(id);
        model.addAttribute("pro", JSON.toJSONString(pro));
        model.addAttribute("id", JSON.toJSONString(id));
        if (StringUtils.isNotBlank(pro.getParentId())) {
            Orgz orgz=orgzService.selectById(pro.getParentId());
            model.addAttribute("orgz", JSON.toJSONString(orgz));
        }else{
        	model.addAttribute("orgz", JSON.toJSONString(""));
        }
        if (StringUtils.isNotBlank(pro.getEmpId())) {
            SysEmployee emp=sysEmployeeService.selectById(pro.getEmpId());
            model.addAttribute("emp", JSON.toJSONString(emp));
        }else{
        	model.addAttribute("emp", JSON.toJSONString(""));
        }
        return "project/info/edit";
    }

    /**
     */
    @RequestMapping("/edit")
    @ResponseBody
    public Object edit(ProProjectinfo t) {
    	ProProjectinfo o = proProjectinfoService.selectById(t.getId());
    	
    	if(!o.getProName().equals(t.getProName())){
    		Map<String,Object> map = new HashMap<String,Object>();
            //项目名称是否重复
            map.put("pro_name", t.getProName());
            List<ProProjectinfo> pps = proProjectinfoService.selectByMap(map);
            if (pps != null && pps.size()>0) {
                return renderError("项目名称已经存在！");
            }
    		
    		proProjectinfoService.updateSelectiveById(t);
    		return renderSuccess("编辑成功！");
    	}else{
    		proProjectinfoService.updateSelectiveById(t);
    		return renderSuccess("编辑成功！");
    	}
    }
    
    /**
     * @return
     */
    @RequestMapping(value = "/selectAddress", method = RequestMethod.GET)
    public String selectAddress(String proId,Model model) {
    	if(proId!=null&&proId!=""){
    		ProProjectinfo pro = proProjectinfoService.selectById(proId);
    		model.addAttribute("proPosition", JSON.toJSONString(pro.getPosition()));    		
    	}else{
    		model.addAttribute("proPosition", JSON.toJSONString(""));
    	}
        return "project/info/selectAddressGD";
    }

    /**
     * 根据用户编号获取用户可以管理的所有项目信息(返回多个工程列表)
     */
    @RequestMapping(value = "/getProjects", method = RequestMethod.POST)
    @ResponseBody
    public Object getProjects() {
    	String userId = String.valueOf(getCurrentUser().getId());
        return proProjectinfoService.getProjectInfosByUserId(userId);
    }
    

    /**
     * 数据导出
     * @param exportDto
     * @param Dto
     * @param response
     */
	@SuppressWarnings({"unchecked", "rawtypes"})
	@RequestMapping(value="/expXls")
	public void expXls(ExportDto exportDto,ProjectinfoQeryVo vo, HttpServletResponse response){
		try {
			if(StringUtils.isNotBlank(exportDto.getIds())){
				List<String> idsList =new ArrayList<String>();
				Collections.addAll(idsList, exportDto.getIds().split(ExportDto.SPLIT_STR));
				List list = proProjectinfoService.selectBatchIds(idsList);
				ExportExcelUtil.writeExcelToClient(response, exportDto, ExportSetUtil.objectToListMap(list));
			}else{
		        PageInfo pageInfo = new PageInfo(0, 65530, "id", "desc");
		        Map<String, Object> condition = new HashMap<String, Object>();
		        if (StringUtils.isNotBlank(vo.getName())) {
		            condition.put("name", vo.getName());
		        }
		        if (vo.getStartTime() != null) {
		            condition.put("startTime", vo.getStartTime());
		        }
		        if (vo.getEndTime() != null) {
		            condition.put("endTime", vo.getEndTime());
		        }
		        condition.put("sqlPurview", this.sqlPurview("p","id"));
		        pageInfo.setCondition(condition);
		        proProjectinfoService.selectDataGrid(pageInfo);
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
	public void expPdf(ExportDto exportDto,ProjectinfoQeryVo vo, HttpServletResponse response){
		try {
			if(StringUtils.isNotBlank(exportDto.getIds())){
				List<String> idsList =new ArrayList<String>();
				 Collections.addAll(idsList, exportDto.getIds().split(ExportDto.SPLIT_STR));
				 List list = proProjectinfoService.selectBatchIds(idsList);
				 ExportPdfUtil.writePdfToClient(response, exportDto, ExportSetUtil.objectToListMap(list));
			}else{
		        PageInfo pageInfo = new PageInfo(0, 65530, "id", "desc");
		        Map<String, Object> condition = new HashMap<String, Object>();
		        if (StringUtils.isNotBlank(vo.getName())) {
		            condition.put("name", vo.getName());
		        }
		        if (vo.getStartTime() != null) {
		            condition.put("startTime", vo.getStartTime());
		        }
		        if (vo.getEndTime() != null) {
		            condition.put("endTime", vo.getEndTime());
		        }
		        condition.put("sqlPurview", this.sqlPurview("p","id"));
		        pageInfo.setCondition(condition);
		        proProjectinfoService.selectDataGrid(pageInfo);
				ExportPdfUtil.writePdfToClient(response, exportDto, ExportSetUtil.objectToListMap(pageInfo.getRows()));
			}
		} catch (Exception e) {
			e.printStackTrace();	
		}
	}
	
	/**
	 * 根据ID查找机构名称
	 * @author wl_zjh
	 * @param orgzId
	 * @return
	 */
	@RequestMapping(value = "/orgzId", method = RequestMethod.GET)
    @ResponseBody
    public List<Orgz> selectByOrgzId(String orgzId){
		List<Orgz> list=proProjectinfoService.selectByOrgzId(orgzId);
		return list;
	}
	
	/**
	 * 查找项目详细信息
	 */
	@RequestMapping(value = "/showPage", method = RequestMethod.GET)
    public String selectById(Model model,String id){
		ProProjectinfo pro = proProjectinfoService.selectById(id);
        model.addAttribute("pro", JSON.toJSONString(pro));
        if (StringUtils.isNotBlank(pro.getParentId())) {
            Orgz orgz=orgzService.selectById(pro.getParentId());
            model.addAttribute("orgz", JSON.toJSONString(orgz));
        }else{
        	model.addAttribute("orgz", JSON.toJSONString(""));
        }
//        if (StringUtils.isNotBlank(pro.getJsdw())) {
//        	Orgz orgzJsdw=orgzService.selectById(pro.getJsdw());
//            model.addAttribute("orgzJsdw", JSON.toJSONString(orgzJsdw));
//        }else{
//        	model.addAttribute("orgzJsdw", JSON.toJSONString(""));
//        }
        if (StringUtils.isNotBlank(pro.getEmpId())) {
            SysEmployee emp=sysEmployeeService.selectById(pro.getEmpId());
            model.addAttribute("emp", JSON.toJSONString(emp));
        }else{
        	model.addAttribute("emp", JSON.toJSONString(""));
        }
        return "project/info/show";
	}
	
	@RequestMapping("/addPage")
    public String addPage(Model model) {   	
        return "project/info/add";
    }


    /**
     * 获取所有项目信息
     * @return
     */
    @RequestMapping(value = "/getProlist" ,method = RequestMethod.POST)
    @ResponseBody
    public Object getList(){
        List<ProProjectinfo> list = proProjectinfoService.selectAll();
        return list;
    }
}
