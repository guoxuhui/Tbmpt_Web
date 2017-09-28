package com.crfeb.tbmpt.sys.base.service;

import java.util.List;
import com.baomidou.framework.service.ICommonService;
import com.crfeb.tbmpt.sys.model.User;
import com.crfeb.tbmpt.commons.utils.PageInfo;
import com.crfeb.tbmpt.sys.base.model.dto.SysFujianDto;
import com.crfeb.tbmpt.sys.base.model.SysFujian;

/**
 * <p>公共统一附件Service接口</p>
 * <p>系统：系统管理</p>
 * <p>模块：公共模块</p>
 * <p>日期：2017-02-17</p>
 * @version 1.0
 * @author wangbinbin
 */
public interface SysFujianService extends ICommonService<SysFujian>{

    void selectDataGrid(PageInfo pageInfo) throws Exception;

    /**
     * 新增公共统一附件
     * @param sysFujian 要保存的实体
     */
    String save(SysFujianDto sysFujianDto,User user) throws Exception;

    /**
     * 通过集合删除公共统一附件
     * @param ids String字符串，中间用“,”间隔开
     */
    String del(String[] ids,User user) throws Exception;

    /**     * 修改公共统一附件
     * @param sysFujian 要修改的实体
     */
    String update(SysFujianDto sysFujianDto,User user) throws Exception;

   /**
     * 通过ID查找公共统一附件
     * @param id 数据ID
     */
    SysFujian findById(String id) throws Exception;

	/**
	 * 校验当前字段是否已存在
	 * @param sysFujian 实体信息
	 * @param clumName 需校验的字段
	 * @return 若为null则不重复，若不为null则重复
	 */
	String checkClumIfexits(SysFujianDto sysFujianDto, String[] clumNames) throws Exception;
	
	/**
	 * 通过业务表主键查找其相应的对应的附件信息
	 * @param foreignId 业务表主键
	 * @param backupOne 备用字段一 ; 非必填项
	 * @param backupTwo 备用字段二 ; 非必填项
	 * @return 附件列表信息
	 * @throws Exception
	 */
	List<SysFujianDto> findFuJianListByForeignId(String foreignId,String backupOne,String backupTwo)throws Exception;
	
    /**
     * 新增附件信息
     * @param sysfjList 系统列表
     * @param user 当前用户信息
     * @return
     * @throws Exception
     */
    String save(List<SysFujianDto> sysfjList,User user) throws Exception;
    
    /**
     * 方法说明：根据业务主键删除其下所有的附件信息
     * @param rootPath 根路径
     * @param foreignId 业务主键
     * @throws Exception
     * @author:YangYj
     * @Time: 2017年2月20日 上午8:40:54
     */
    public void deleteFujianByForeignId(String rootPath,String foreignId) throws Exception;

}