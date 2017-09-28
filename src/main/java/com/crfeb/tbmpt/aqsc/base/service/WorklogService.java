package com.crfeb.tbmpt.aqsc.base.service;

import java.util.List;
import com.baomidou.framework.service.ICommonService;
import com.crfeb.tbmpt.sys.model.User;
import com.crfeb.tbmpt.commons.utils.PageInfo;
import com.crfeb.tbmpt.aqsc.base.model.dto.WorklogDto;
import com.crfeb.tbmpt.aqsc.base.model.Worklog;

/**
 * <p>工作日志Service接口</p>
 * <p>系统：安全生产管理</p>
 * <p>模块：基础信息</p>
 * <p>日期：2017-02-20</p>
 * @version 1.0
 * @author wangbinbin
 */
public interface WorklogService extends ICommonService<Worklog>{

    void selectDataGrid(PageInfo pageInfo) throws Exception;
    
    void selectDataGrid(PageInfo pageInfo,User user) throws Exception;

    /**
     * 新增工作日志
     * @param worklog 要保存的实体
     */
    String save(WorklogDto worklogDto,User user) throws Exception;

    /**
     * 通过集合删除工作日志
     * @param ids String字符串，中间用“,”间隔开
     */
    String del(String[] ids,User user) throws Exception;

    /**     * 修改工作日志
     * @param worklog 要修改的实体
     */
    String update(WorklogDto worklogDto,User user) throws Exception;

   /**
     * 通过ID查找工作日志
     * @param id 数据ID
     */
    Worklog findById(String id) throws Exception;

	/**
	 * 校验当前字段是否已存在
	 * @param worklog 实体信息
	 * @param clumName 需校验的字段
	 * @return 若为null则不重复，若不为null则重复
	 */
	String checkClumIfexits(WorklogDto worklogDto, String[] clumNames) throws Exception;

}