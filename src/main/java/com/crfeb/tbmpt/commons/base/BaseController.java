package com.crfeb.tbmpt.commons.base;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.InitBinder;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.crfeb.tbmpt.commons.result.Result;
import com.crfeb.tbmpt.commons.shiro.ShiroUser;
import com.crfeb.tbmpt.commons.utils.StringEscapeEditor;
import com.crfeb.tbmpt.commons.utils.StringUtils;
import com.crfeb.tbmpt.project.mapper.ProProjectinfoMapper;
import com.crfeb.tbmpt.project.model.ProProjectinfo;
import com.crfeb.tbmpt.sys.model.Orgz;
import com.crfeb.tbmpt.sys.model.Res;
import com.crfeb.tbmpt.sys.model.User;
import com.crfeb.tbmpt.sys.service.ILogService;
import com.crfeb.tbmpt.sys.service.IOrgzService;
import com.crfeb.tbmpt.sys.service.IResService;
import com.crfeb.tbmpt.sys.service.IUserService;

/**
 * @description：基础 controller
 * @author：smxg
 * @date：2016-10-10 11:12
 */
public abstract class BaseController {
    @Autowired
    private IUserService userService;
    @Autowired
    private ProProjectinfoMapper projectinfoMapper;
    @Autowired
    protected ILogService logService;
    @Autowired
    protected IResService resService;
    @Autowired
    protected IOrgzService orgzService;
    @Autowired
    protected HttpServletRequest request;


    @InitBinder
    public void initBinder(ServletRequestDataBinder binder) {
        /**
         * 自动转换日期类型的字段格式
         */
        binder.registerCustomEditor(Date.class, new CustomDateEditor(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"), true));

        /**
         * 防止XSS攻击
         */
        binder.registerCustomEditor(String.class, new StringEscapeEditor(true, false));
    }

    /**
     * 获取当前登录用户对象
     * @return
     */
    public User getCurrentUser() {
        ShiroUser user = (ShiroUser) SecurityUtils.getSubject().getPrincipal();
        if(user != null){
        	return user.getUser();
        }else{
        	return null;
        }
    }

    /**
     * 获取当前登录用户id
     * @return
     */
    public String getUserId() {
        return this.getCurrentUser().getId();
    }

    /**
     * 获取当前登录用户名
     * @return
     */
    public String getStaffName() {
        return this.getCurrentUser().getName();
    }

    /**
     * ajax失败
     * @param msg 失败的消息
     * @return {Object}
     */
    public Object renderError(String msg) {
        Result result = new Result();
        result.setMsg(msg);
        return result;
    }

    /**
     * ajax成功
     * @return {Object}
     */
    public Object renderSuccess() {
        Result result = new Result();
        result.setSuccess(true);
        return result;
    }

    /**
     * ajax成功
     * @param msg 消息
     * @return {Object}
     */
    public Object renderSuccess(String msg) {
        Result result = new Result();
        result.setSuccess(true);
        result.setMsg(msg);
        return result;
    }

    /**
     * ajax成功
     * @param obj 成功时的对象
     * @return {Object}
     */
    public Object renderSuccess(Object obj) {
        Result result = new Result();
        result.setSuccess(true);
        result.setObj(obj);
        return result;
    }
    
    /**
     * 获取当前登陆人员所能查看的项目信息sql<br>
     * 1、当前人员在项目部下，则获取其项目信息id<br>
     * 2、当前人员在项目部上，则获取其所有的项目信息id<br>
     * @param tableAnotherName 表的别名，若为空，默认为"t"
     * @param gcBhTableClum 项目id数据库字段，若为空，默认为"gc_Bh"
     * @return 返回sql连接串
     */
    public String sqlPurview(String tableAnotherName,String gcBhTableClum){
    	String sql = " and "+(StringUtils.isNotBlank(tableAnotherName)?tableAnotherName:"t")+"."+(StringUtils.isNotBlank(gcBhTableClum)?gcBhTableClum:"gc_Bh")+" in (";
    	//获取用户所在机构
    	User user=userService.selectById(this.getUserId());
    	Orgz org = orgzService.selectById(user.getOrgzId());
    	List<ProProjectinfo> projects=new ArrayList<ProProjectinfo>();
    	//机关单位看到所有的项目
    	if(org.getType()==0){
    		projects = projectinfoMapper.selectAllByNull();
    	}else{
    		projects = projectinfoMapper.selectByUserId(this.getUserId());    		
    	}
		for (int i = 0; i < projects.size(); i++) {
			if(i!=0)sql+=",";
			sql+="'"+projects.get(i).getId()+"'";
		}
    	sql+=")";
    	return sql;
    }
    
    
    /**
     * 获取当前登陆人员所在项目的组织机构的责任部门id<br>
     * 若为多个项目返回null，若为单个项目返回部门id
     */
    public String findOrgzId(){
    	List<ProProjectinfo> projects = projectinfoMapper.selectByUserId(this.getUserId());
    	if(projects!=null&&projects.size()==1){
    		return projects.get(0).getOrgzId();
    	}
    	return "";
    }
    /**
     * 获取当前登陆人员所在项目项目部<br>
     * 若为多个项目返回null，若为单个项目
     */
    public ProProjectinfo findProjectByCurrentUser(){
    	List<ProProjectinfo> projects = projectinfoMapper.selectByUserId(this.getUserId());
    	if(projects!=null&&projects.size()==1){
    		return projects.get(0);
    	}
    	return null;
    }
    
    /**
     * 日志记录公共方法
     * 
     * @param optName 操作功能 为空时获取相应的按钮功能
     * @param optName 操作状态  成功、失败
     * @param content 操作内容
     */
    public void log(String optName,boolean optStatus,String content){
    	String status = "失败";
    	if(optStatus){
    		status = "成功";
    	}
    	Res sysres = null;
    	Res modelres = null;
    	Res res = null;
    	String currUrl = request.getRequestURI();
    	String modelUrl = currUrl.substring(0, currUrl.lastIndexOf("/"));
    	//获取当前模块菜单
    	EntityWrapper<Res> modelew = new EntityWrapper<Res>();
    	modelew.where("url={0}",modelUrl);
    	List<Res> modelress = resService.selectList(modelew);
    	if(modelress.size()>0){
    		modelres = modelress.get(0);
    	}
    	
    	if(modelres != null){
    		String[] ls = currUrl.split("/");
    		EntityWrapper<Res> sysew = new EntityWrapper<Res>();
    		sysew.where("url={0}","/"+ls[1]).and("resource_type={0}", "0");
    		List<Res> sysRess = resService.selectList(sysew);
    		if(sysRess.size()>0){
    			sysres = sysRess.get(0);
    		}
    		
    		if(optName == null){
    			EntityWrapper<Res> ew = new EntityWrapper<Res>();
    			ew.where("id={0}",modelres.getPid());
        		List<Res> ress = resService.selectList(ew);
        		if(ress.size()>0){
        			res = ress.get(0);
        		}
    			logService.log(sysres.getName(),modelres.getName(),res.getName(),status,content);
    		}else{
    			logService.log(sysres.getName(),modelres.getName(),optName,status,content);
    		}
    	}
    	
    }
}
