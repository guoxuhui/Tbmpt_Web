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

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.crfeb.tbmpt.commons.base.BaseController;
import com.crfeb.tbmpt.commons.result.Result;
import com.crfeb.tbmpt.commons.result.Tree;
import com.crfeb.tbmpt.commons.utils.DigestUtils;
import com.crfeb.tbmpt.commons.utils.ExportDto;
import com.crfeb.tbmpt.commons.utils.ExportExcelUtil;
import com.crfeb.tbmpt.commons.utils.ExportPdfUtil;
import com.crfeb.tbmpt.commons.utils.ExportSetUtil;
import com.crfeb.tbmpt.commons.utils.PageInfo;
import com.crfeb.tbmpt.commons.utils.StringUtils;
import com.crfeb.tbmpt.project.model.ProProjectinfo;
import com.crfeb.tbmpt.sys.mapper.OrgzMapper;
import com.crfeb.tbmpt.sys.model.Orgz;
import com.crfeb.tbmpt.sys.model.Role;
import com.crfeb.tbmpt.sys.model.SysEmployee;
import com.crfeb.tbmpt.sys.model.User;
import com.crfeb.tbmpt.sys.model.dto.UserDto;
import com.crfeb.tbmpt.sys.model.vo.UserVo;
import com.crfeb.tbmpt.sys.service.IOrgzService;
import com.crfeb.tbmpt.sys.service.ISysEmployeeService;
import com.crfeb.tbmpt.sys.service.IUserService;

/**
 * @description：用户管理
 * @author：smxg
 * @date：2016-10-10 11:12
 */
@Controller
@RequestMapping("/sys/user")
public class UserController extends BaseController {

    @Autowired
    private IUserService userService;
    
    @Autowired
    private IOrgzService orgzService;


    @Autowired
    private ISysEmployeeService sysEmployeeService;
    /**
     * 用户管理页
     *
     * @return
     */
    @RequestMapping(value = "", method = RequestMethod.GET)
    public String manager() {
        return "sys/user/index";
    }

    /**
     * 用户管理列表
     *
     * @param userVo
     * @param page
     * @param rows
     * @param sort
     * @param order
     * @return
     */
    @RequestMapping(value = "/dataGrid", method = RequestMethod.POST)
    @ResponseBody
    public Object dataGrid(UserDto dto, Integer page, Integer rows, String sort, String order) {
        PageInfo pageInfo = new PageInfo(page, rows,sort,order);
        Map<String, Object> condition = new HashMap<String, Object>();

        if (StringUtils.isNotBlank(dto.getLoginName())) {
            condition.put("loginName", dto.getLoginName());
        }
        if (StringUtils.isNotBlank(dto.getName())) {
            condition.put("name", dto.getName());
        }
        if (StringUtils.isNotBlank(dto.getOrgzId())) {
        	Orgz orgz = orgzService.selectById( dto.getOrgzId());
            condition.put("orgzCode", orgz.getCode());
        }
        if (dto.getStartTime() != null) {
            condition.put("startTime", dto.getStartTime());
        }
        if (dto.getEndTime() != null) {
            condition.put("endTime", dto.getEndTime());
        }
        pageInfo.setCondition(condition);
        userService.selectDataGrid(pageInfo);
        return pageInfo;
    }


    /**
     * 添加用户页
     *
     * @return
     */
    @RequestMapping(value = "/addPage", method = RequestMethod.GET)
    public String addPage() {
    	return "sys/user/add";
    }

    /**
     * 添加用户
     *
     * @param userVo
     * @return
     */
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public Object add(UserVo userVo) {
        User u = userService.selectByLoginName(userVo.getLoginName());
        if (u != null) {
            return renderError("用户名已存在!");
        }
        userVo.setPassword(DigestUtils.md5Hex(userVo.getPassword()));
        SysEmployee emp = sysEmployeeService.selectById(userVo.getEmpId());
        userVo.setName(emp.getName());
        userVo.setOrgzId(emp.getOrgzId());
        EntityWrapper<User> ew = new EntityWrapper<User>();
        ew.where("emp_id={0}", userVo.getEmpId());
        List<User> luser = userService.selectList(ew);
        if (luser != null && luser.size()>0) {
            return renderError("员工【"+emp.getName()+"】已经存在对应的用户账号了！");
        }
        userVo.setStatus(0);
        userService.insertByVo(userVo);
        return renderSuccess("添加成功");
    }
    
    /**
     * 查看用户信息
     *
     * @param id
     * @param model
     * @return
     */
    @RequestMapping("/userInfo")
    public String userInfo(String id, Model model) {
        UserVo userVo = userService.selectVoById(id);
        List<Role> rolesList = userVo.getRolesList();
        List<String> ids = new ArrayList<String>();
        for (Role role : rolesList) {
            ids.add("'"+role.getId()+"'");
        }
        model.addAttribute("roleIds", ids);
        model.addAttribute("user", userVo);
        return "sys/user/userInfo";
    }
    
    /**
     * 编辑用户页
     *
     * @param id
     * @param model
     * @return
     */
    @RequestMapping("/editPage")
    public String editPage(String id, Model model) {
        UserVo userVo = userService.selectVoById(id);
        List<Role> rolesList = userVo.getRolesList();
        List<String> ids = new ArrayList<String>();
        for (Role role : rolesList) {
            ids.add(role.getId());
        }
        model.addAttribute("roleIds", ids);
        model.addAttribute("user", userVo);
        return "sys/user/edit";
    }

    /**
     * 编辑用户
     *
     * @param userVo
     * @return
     */
    @RequestMapping("/edit")
    @ResponseBody
    public Object edit(UserVo userVo) {
        User user = userService.selectByLoginName(userVo.getLoginName());
        if (user != null && !user.getId().equals(userVo.getId())) {
            return renderError("用户名已存在!");
        }
        userService.updateByVo(userVo);
        return renderSuccess("修改成功！");
    }

    /**
     * 修改密码页
     *
     * @return
     */
    @RequestMapping(value = "/editPwdPage", method = RequestMethod.GET)
    public String editPwdPage() {
        return "sys/user/userEditPwd";
    }

    /**
     * 修改密码
     *
     * @param oldPwd
     * @param pwd
     * @return
     */
    @RequestMapping("/editUserPwd")
    @ResponseBody
    public Object editUserPwd(String oldPwd, String pwd) {
        if (!getCurrentUser().getPassword().equals(DigestUtils.md5Hex(oldPwd))) {
            return renderError("原密码不正确!");
        }

        userService.updatePwdByUserId(getUserId(), DigestUtils.md5Hex(pwd));
        return renderSuccess("密码修改成功！");
    }

    /**
     * 删除用户
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
    	if(idlist.contains("1")){
    		return renderSuccess("系统管理员帐号不能删除！");
    	}
    	userService.deleteBatchIds(idlist);
        return renderSuccess("删除成功！");
    }
    
    /**
     * 重置密码
     *
     * @param ids
     * @return
     */
    @RequestMapping("/pdreset")
    @ResponseBody
    public Object pdreset(String ids) {
    	String[] idss = ids.split(",");
    	List<String> idlist = new ArrayList<String>();
    	Collections.addAll(idlist, idss);
    	if(idlist.contains("1")){
    		return renderSuccess("系统管理员帐号不能重置！");
    	}
    	for(String id:idss){
    		userService.updatePwdByUserId(id, DigestUtils.md5Hex("123456"));
    	}
        return renderSuccess("重置成功！");
    }
    
    
    /**
     * 获取用户信息
     * @return
     */
    @RequestMapping(value = "/getDic", method = RequestMethod.POST)
    @ResponseBody
    public Object getDic() {
    	User currentUser = getCurrentUser();
    	Orgz orgz = orgzService.getOrgzInfoByUserId(currentUser.getId());
    	return userService.getDic(orgz.getCode());
    }
    
    /**
     * 获取用户信息
     * @return
     */
    @RequestMapping(value = "/getUserTree", method = RequestMethod.POST)
    @ResponseBody
    public Object getUserTree() {
    	User currentUser = getCurrentUser();
    	List<Tree> list = orgzService.selectUserTreeByUser(currentUser);
    	return list;
    }
    
    /**
     * 数据导出
     * @param exportDto
     * @param Dto
     * @param response
     */
	@SuppressWarnings({"unchecked", "rawtypes"})
	@RequestMapping(value="/expXls")
	public void expXls(ExportDto exportDto,UserDto dto, HttpServletResponse response){
		try {
			if(StringUtils.isNotBlank(exportDto.getIds())){
				List<String> idsList =new ArrayList<String>();
				 Collections.addAll(idsList, exportDto.getIds().split(ExportDto.SPLIT_STR));
				 List list = userService.selectBatchIds(idsList);
				 ExportExcelUtil.writeExcelToClient(response, exportDto, ExportSetUtil.objectToListMap(list));
			}else{
		        PageInfo pageInfo = new PageInfo(0, 65530);
		        Map<String, Object> condition = new HashMap<String, Object>();
	
		        if (StringUtils.isNotBlank(dto.getLoginName())) {
		            condition.put("loginName", dto.getLoginName());
		        }
		        if (StringUtils.isNotBlank(dto.getName())) {
		            condition.put("name", dto.getName());
		        }
		        if (dto.getStartTime() != null) {
		            condition.put("startTime", dto.getStartTime());
		        }
		        if (dto.getEndTime() != null) {
		            condition.put("endTime", dto.getEndTime());
		        }
		        pageInfo.setCondition(condition);
		        userService.selectDataGrid(pageInfo);
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
	public void expPdf(ExportDto exportDto,UserDto dto, HttpServletResponse response){
		try {
			if(StringUtils.isNotBlank(exportDto.getIds())){
				List<String> idsList =new ArrayList<String>();
				 Collections.addAll(idsList, exportDto.getIds().split(ExportDto.SPLIT_STR));
				 List list = userService.selectBatchIds(idsList);
				 ExportPdfUtil.writePdfToClient(response, exportDto, ExportSetUtil.objectToListMap(list));
			}else{
		        PageInfo pageInfo = new PageInfo(0, 65530);
		        Map<String, Object> condition = new HashMap<String, Object>();
	
		        if (StringUtils.isNotBlank(dto.getLoginName())) {
		            condition.put("loginName", dto.getLoginName());
		        }
		        if (StringUtils.isNotBlank(dto.getName())) {
		            condition.put("name", dto.getName());
		        }
		        if (dto.getStartTime() != null) {
		            condition.put("startTime", dto.getStartTime());
		        }
		        if (dto.getEndTime() != null) {
		            condition.put("endTime", dto.getEndTime());
		        }
		        pageInfo.setCondition(condition);
		        userService.selectDataGrid(pageInfo);
				ExportPdfUtil.writePdfToClient(response, exportDto, ExportSetUtil.objectToListMap(pageInfo.getRows()));
			}
		} catch (Exception e) {
			e.printStackTrace();	
		}
	}
	
	
	
	/**
     *  获取 用户信息
     *
     * @param id
     * @param model
     * @return 
     */
    @RequestMapping(value = "/getUserInfo")
    @ResponseBody
    public Object getUserInfo(Model model) {
    	User user = getCurrentUser();  //获取用户信息
    	String url = ""; 
    	if(user !=null ){
    		String roles ="123456789";          //界面显示平台权限： 标识 ，“123456789”为集团单位
        	Orgz orgz = orgzService.selectOrgzInfoGridById(user.getOrgzId()); //通过用户的的 部门Id 获取   当前用户部门信息                              
        	ProProjectinfo projectinfo = new ProProjectinfo();
        	if(orgz != null){
        		//项目机构类型
        		if(orgz.getType() >= 1){ //项目部部门
        			roles ="987654321";   //界面显示平台权限 ：标识"987654321"为项目部部门 
        			if(orgz.getType() > 1){
        				//项目部以下 部门
        				//roles =2;    //权限 标识"2"为项目部下级 部门
        				Orgz orgz2 = null;
        			    orgz2 = orgzService.selectOrgzInfoGridById(orgz.getPid());//获取上级部门“项目部”
        			    if(StringUtils.isNotBlank(orgz2.getId())){
        			    	/**
        			         * 获取当前登陆人员所在项目项目部<br>
        			         * 若为多个项目返回null，若为单个项目
        			         */
        			    	projectinfo =findProjectByCurrentUser();
        			    }
        			}
        		}
        	}
        	
        	url ="?appId=20161201010101&appSecret=08fb965863f87aeff6d8a0333609db23&role="+roles+"&xmId="+projectinfo.getId();
           
            return renderSuccess(url);
    	}
    	return renderSuccess("未登录！");
    }


    /**
     * 用户管理列表
     * @param orgzId
     * @return
     */
    @RequestMapping(value = "/userList", method = RequestMethod.POST)
    @ResponseBody
    public Object selectProjectUserList(String orgzId) {
        Orgz orgz = orgzService.selectById(orgzId);
        List<User>  users = userService.selectUserByOrgz(orgz.getCode());
        return users;
    }
}
