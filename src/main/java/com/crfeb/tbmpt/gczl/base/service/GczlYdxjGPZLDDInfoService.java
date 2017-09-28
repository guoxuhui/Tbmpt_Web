package com.crfeb.tbmpt.gczl.base.service;

import java.util.List;
import com.baomidou.framework.service.ICommonService;
import com.crfeb.tbmpt.commons.utils.PageInfo;
import com.crfeb.tbmpt.gczl.base.model.GczlYdxjGPZLDDInfo;
import com.crfeb.tbmpt.gczl.base.model.dto.GczlYdxjGPZLDDInfoDto;
import com.crfeb.tbmpt.sys.model.User;

/**
 * <p>盾构施工质量基础数据Service接口</p>
 * <p>系统：</p>
 * <p>模块：基础模块</p>
 * <p>日期：2016-11-19</p>
 * @version 1.0
 * @author wangbinbin
 */
public interface GczlYdxjGPZLDDInfoService extends ICommonService<GczlYdxjGPZLDDInfo>{

    void selectDataGrid(PageInfo pageInfo);
    
    /**
     * 新增盾构施工质量基础数据
     * @param gczlYdxjGPZLDDInfo 要保存的实体
     */
    void save(GczlYdxjGPZLDDInfo gczlYdxjGPZLDDInfo,User user);
   
    
    /**
     * 通过集合删除盾构施工质量基础数据
     * @param ids String字符串，中间用“,”间隔开
     */
    void del(String[] ids);
    
    /**
     * 修改盾构施工质量基础数据
     * @param gczlYdxjGPZLDDInfo 要修改的实体
     */
    String update(GczlYdxjGPZLDDInfoDto gczlYdxjGPZLDDInfoDto,User user);
    
    /**
     * 通过ID查找盾构施工质量基础数据
     * @param id 数据ID
     */
    GczlYdxjGPZLDDInfo findById(String id);
    
    /**
     * 启用或禁用当前实体对象
     * @param ids 要操作的对象ID
     * @param ifQy true:启用 false:禁用
     * @return 返回操作提示信息
     */
    String qy(String[] ids,boolean ifQy);

	/**
	 * 校验当前字段是否已存在
	 * @param gczlYdxjGPZLDDInfo 实体信息
	 * @param clumName 需校验的字段
	 * @return 若为null则不重复，若不为null则重复
	 */
	String checkClumIfexits(GczlYdxjGPZLDDInfoDto gczlYdxjGPZLDDInfoDto, String[] clumNames);

	void insert(GczlYdxjGPZLDDInfoDto gczlYdxjGPZLDDInfoDto, User currentUser);
    
}