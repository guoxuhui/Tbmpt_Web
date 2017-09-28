package com.crfeb.tbmpt.sys.controller;

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

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.crfeb.tbmpt.commons.base.BaseController;
import com.crfeb.tbmpt.commons.utils.DateUtils;
import com.crfeb.tbmpt.commons.utils.ExportDto;
import com.crfeb.tbmpt.commons.utils.ExportExcelUtil;
import com.crfeb.tbmpt.commons.utils.ExportPdfUtil;
import com.crfeb.tbmpt.commons.utils.ExportSetUtil;
import com.crfeb.tbmpt.commons.utils.PageInfo;
import com.crfeb.tbmpt.commons.utils.StringUtils;
import com.crfeb.tbmpt.project.model.ProRProjectSection;
import com.crfeb.tbmpt.sys.model.Orgz;
import com.crfeb.tbmpt.sys.model.SysEmployee;
import com.crfeb.tbmpt.sys.model.User;
import com.crfeb.tbmpt.sys.model.dto.SysEmployeeDto;
import com.crfeb.tbmpt.sys.service.IOrgzService;
import com.crfeb.tbmpt.sys.service.ISysEmployeeService;
import com.crfeb.tbmpt.sys.service.IUserService;

/**
 * @description：员工管理
 * @author：smxg
 * @date：2016-10-10 11:12
 */
@Controller
@RequestMapping("/sys/emp")
public class EmpController extends BaseController {

    @Autowired
    private ISysEmployeeService sysEmployeeService;
    @Autowired
    private IOrgzService orgzService;
    @Autowired
    private IUserService userService;
    /**
     * 员工管理页
     *
     * @return
     */
    @RequestMapping(value = "", method = RequestMethod.GET)
    public String manager() {
        return "sys/emp/index";
    }

    /**
     * 员工列表
     *
     * @param page
     * @param rows
     * @param sort
     * @param order
     * @return
     */
    @RequestMapping(value = "/dataGrid", method = RequestMethod.POST)
    @ResponseBody
    public Object dataGrid(SysEmployeeDto dto,Integer page, Integer rows, String sort, String order) {
        PageInfo pageInfo = new PageInfo(page, rows, sort, order);
        Map<String, Object> condition = new HashMap<String, Object>();
        pageInfo.setCondition(condition);
        if (StringUtils.isNotBlank(dto.getName())) {
            condition.put("name", dto.getName());
        }
        if (StringUtils.isNotBlank(dto.getSex())) {
            condition.put("sex", dto.getSex());
        }
        if (StringUtils.isNotBlank(dto.getOrgzId())) {
        	Orgz orgz = orgzService.selectById( dto.getOrgzId());
        	if(orgz!=null){
        		condition.put("orgzCode", orgz.getCode());
        	}
        }
        if (StringUtils.isNotBlank(dto.getStartTime()) ) {
            condition.put("startTime", dto.getStartTime());
        }
        if (StringUtils.isNotBlank(dto.getEndTime())) {
            condition.put("endTime", dto.getEndTime());
        }
        sysEmployeeService.selectDataGrid(pageInfo);
        return pageInfo;
    }

    /**
     * 添加员工页
     *
     * @return
     */
    @RequestMapping(value = "/addPage", method = RequestMethod.GET)
    public String addPage() {
    	return "sys/emp/add";
    }

    /**
     * 添加员工
     *
     * @param role
     * @return
     */
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public Object add(SysEmployee emp) {
    	emp.setCreateUser(getCurrentUser().getName());
    	emp.setCreateTime(DateUtils.getToday());
    	sysEmployeeService.insert(emp);
        return renderSuccess("添加成功！");
    }

    /**
     * 删除员工
     *
     * @param id
     * @return
     */
    @RequestMapping("/delete")
    @ResponseBody
    public Object delete(String ids) {
    	String[] idss = ids.split(",");
    	List<String> idlist = new ArrayList<String>();
    	Collections.addAll(idlist, idss);
    	EntityWrapper<User> ew = new EntityWrapper<User>();
    	ew.where("1=1").in("EMP_ID", idlist);
    	List<User> list = userService.selectList(ew);
    	if(list != null && list.size() > 0){
    		return renderError("操作员工存在用户信息，无法删除！");
    	}
    	sysEmployeeService.deleteBatchIds(idlist);
        return renderSuccess("删除成功！");
    }

    /**
     * 编辑员工页
     *
     * @param model
     * @param id
     * @return
     */
    @RequestMapping("/editPage")
    public String editPage(Model model, String id) {
    	SysEmployee emp = sysEmployeeService.selectById(id);
        model.addAttribute("role", emp);
        return "sys/emp/edit";
    }

    /**
     * 删除员工
     *
     * @param role
     * @return
     */
    @RequestMapping("/edit")
    @ResponseBody
    public Object edit(SysEmployee emp) {
    	emp.setUpdateUser(getCurrentUser().getName());
    	emp.setUpdateTime(DateUtils.getToday());
    	sysEmployeeService.edit(emp);
        return renderSuccess("编辑成功！");
    }
    
    /**
     * 选择员工
     *
     * @param role
     * @return
     */
    @RequestMapping("/empSelection")
    public String empSelection(SysEmployee emp) {
    	return "sys/emp/empSelection";
    }

    /**
     * 数据导出
     * @param exportDto
     * @param Dto
     * @param response
     */
	@SuppressWarnings({"unchecked", "rawtypes"})
	@RequestMapping(value="/expXls")
	public void expXls(ExportDto exportDto,SysEmployeeDto dto, HttpServletResponse response){
		try {
			if(StringUtils.isNotBlank(exportDto.getIds())){
				List<String> idsList =new ArrayList<String>();
				Collections.addAll(idsList, exportDto.getIds().split(ExportDto.SPLIT_STR));
				List list = sysEmployeeService.selectBatchIds(idsList);
				ExportExcelUtil.writeExcelToClient(response, exportDto, ExportSetUtil.objectToListMap(list));
			}else{
		        PageInfo pageInfo = new PageInfo(0, 65530, "id", "desc");
		        Map<String, Object> condition = new HashMap<String, Object>();
		        pageInfo.setCondition(condition);
		        if (StringUtils.isNotBlank(dto.getName())) {
		            condition.put("name", dto.getName());
		        }
		        if (StringUtils.isNotBlank(dto.getSex())) {
		            condition.put("sex", dto.getSex());
		        }
		        if (dto.getOrgzId() != null) {
		        	Orgz orgz = orgzService.selectById( dto.getOrgzId());
		            condition.put("orgzCode", orgz.getCode());
		        }
		        if (dto.getStartTime() != null) {
		            condition.put("startTime", dto.getStartTime());
		        }
		        if (dto.getEndTime() != null) {
		            condition.put("endTime", dto.getEndTime());
		        }
		        sysEmployeeService.selectDataGrid(pageInfo);
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
	public void expPdf(ExportDto exportDto,SysEmployeeDto dto, HttpServletResponse response){
		try {
			if(StringUtils.isNotBlank(exportDto.getIds())){
				List<String> idsList =new ArrayList<String>();
				 Collections.addAll(idsList, exportDto.getIds().split(ExportDto.SPLIT_STR));
				 List list = sysEmployeeService.selectBatchIds(idsList);
				 ExportPdfUtil.writePdfToClient(response, exportDto, ExportSetUtil.objectToListMap(list));
			}else{
		        PageInfo pageInfo = new PageInfo(0, 65530, "id", "desc");
		        Map<String, Object> condition = new HashMap<String, Object>();
		        pageInfo.setCondition(condition);
		        if (StringUtils.isNotBlank(dto.getName())) {
		            condition.put("name", dto.getName());
		        }
		        if (StringUtils.isNotBlank(dto.getSex())) {
		            condition.put("sex", dto.getSex());
		        }
		        if (dto.getOrgzId() != null) {
		        	Orgz orgz = orgzService.selectById( dto.getOrgzId());
		            condition.put("orgzCode", orgz.getCode());
		        }
		        if (dto.getStartTime() != null) {
		            condition.put("startTime", dto.getStartTime());
		        }
		        if (dto.getEndTime() != null) {
		            condition.put("endTime", dto.getEndTime());
		        }
		        sysEmployeeService.selectDataGrid(pageInfo);
				ExportPdfUtil.writePdfToClient(response, exportDto, ExportSetUtil.objectToListMap(pageInfo.getRows()));
			}
		} catch (Exception e) {
			e.printStackTrace();	
		}
	}

}
