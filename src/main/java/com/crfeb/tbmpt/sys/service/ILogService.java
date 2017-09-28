package com.crfeb.tbmpt.sys.service;

import java.util.List;

import com.baomidou.framework.service.ICommonService;
import com.crfeb.tbmpt.commons.utils.PageInfo;
import com.crfeb.tbmpt.sys.model.Log;
import com.crfeb.tbmpt.sys.model.User;

/**
 *
 * SysLog 表数据服务层接口
 *
 */
public interface ILogService extends ICommonService<Log> {

	/**
     * 日志列表 条件查询
     *
     * @param pageInfo 分页对象
     * @author wl_wpg
     */
    void selectDataGrid(PageInfo pageInfo);

    /**
     * 用户业务数据操作记录日志
     * @param user
     * @param content
     */
    void log(User user,String content);
    
    List<Log> selectAll();
    
    void deleteAll();
    
    /**
     * 操作记录日志
     * @param sysName     系统模块
     * @param moduleName  模块菜单
     * @param optName     操作功能
     * @param content     操作内容
     */
    void log(String sysName,String moduleName,String optName,String optStatus,String content);
    
}