package com.crfeb.tbmpt.sys.service.impl;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.baomidou.framework.service.impl.CommonServiceImpl;
import com.baomidou.mybatisplus.plugins.Page;
import com.crfeb.tbmpt.commons.shiro.ShiroUser;
import com.crfeb.tbmpt.commons.utils.DateUtils;
import com.crfeb.tbmpt.commons.utils.PageInfo;
import com.crfeb.tbmpt.sys.mapper.LogMapper;
import com.crfeb.tbmpt.sys.model.Log; 
import com.crfeb.tbmpt.sys.model.User;
import com.crfeb.tbmpt.sys.service.ILogService;

/**
 *
 * SysLog 表数据服务层接口实现类
 *
 */
@Service
public class LogServiceImpl extends CommonServiceImpl<LogMapper, Log> implements ILogService {
	private static final Logger LOGGER = LogManager.getLogger(LogServiceImpl.class);
    @Autowired
    private LogMapper sysLogMapper;
    
    
    public void selectDataGrid1(PageInfo pageInfo) {
    	//参数：当前页码，每页条数
        Page<Log> page = new Page<Log>(pageInfo.getNowpage(), pageInfo.getSize());
        List<Log> list = sysLogMapper.selectSysLogList(page);
        pageInfo.setRows(list);
        pageInfo.setTotal(page.getTotal());
    }

   
    /**
     * 日志列表  条件查询
     * @param pageInfo 分页对象
     * @author wl_wpg
     */
    public void selectDataGrid(PageInfo pageInfo) {
    	try {
    		  Page<Log> page = new Page<Log>(pageInfo.getNowpage(), pageInfo.getSize());
	        List<Log> list = sysLogMapper.selectLogList(page, pageInfo.getCondition(),pageInfo.getSort(), pageInfo.getOrder());
	        pageInfo.setRows(list);
	        pageInfo.setTotal(page.getTotal());
    	} catch (Exception e) {
    		e.printStackTrace();
    	} 
       
    }

    
    
    
    public List<Log> selectAll(){
    	return sysLogMapper.selectAll();
    }
    
    public void deleteAll(){
    	sysLogMapper.deleteAll();
    }
    
    /**
     * 业务日志记录实现方法
     * 
     */
	public void log(User user,String content) {
		HttpServletRequest request = null;
		request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        try {
            if (null != user) {
                String loginName = user.getLoginName();
                Log sysLog = new Log();
                sysLog.setLoginName(loginName);
                sysLog.setRoleName(loginName);
                sysLog.setOptContent(content);
                if (request != null) {
                    sysLog.setClientIp(request.getRemoteAddr());
                }else{
                	sysLog.setClientIp("");
                }
                sysLog.setCreateTime(DateUtils.getToday());
                LOGGER.info(sysLog.toString());
                sysLogMapper.insert(sysLog);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
	}

    /**
     * 业务日志记录实现方法
     * 
     */
	public void log(String sysName,String moduleName,String optName,String optStatus,String content) {
		HttpServletRequest request = null;
		request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
		Subject currentUser = SecurityUtils.getSubject();
		ShiroUser user = (ShiroUser)currentUser.getPrincipal();
        try {
            if (null != currentUser) {
                String loginName = user.getLoginName();
                Log sysLog = new Log();
                sysLog.setLoginId(user.getId());
                sysLog.setLoginName(loginName);
                sysLog.setRoleName(user.getName());
                
                sysLog.setSysName(sysName);
                sysLog.setModuleName(moduleName);
                sysLog.setOptName(optName);
                sysLog.setOptStatus(optStatus);

                sysLog.setOptContent(content);
                if (request != null) {
                	sysLog.setOptUrl(request.getRequestURI());
                    sysLog.setClientIp(request.getRemoteAddr());
                }else{
                	sysLog.setClientIp("");
                }
                sysLog.setOptTime(DateUtils.getToday());
                sysLog.setCreateTime(DateUtils.getToday());
                LOGGER.info(sysLog.toString());
                sysLogMapper.insert(sysLog);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
	}
}