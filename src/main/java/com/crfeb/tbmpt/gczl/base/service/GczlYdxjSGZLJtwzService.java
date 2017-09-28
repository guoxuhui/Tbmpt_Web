package com.crfeb.tbmpt.gczl.base.service;

import java.util.List;

import com.baomidou.framework.service.ICommonService;
import com.crfeb.tbmpt.commons.utils.PageInfo;
import com.crfeb.tbmpt.gczl.base.model.GczlYdxjSGZLJtwz;
import com.crfeb.tbmpt.gczl.base.model.dto.GczlYdxjSGZLJtwzDto;
import com.crfeb.tbmpt.sys.model.User;

/**
 * <p>结构施工质量基础数据-具体位置Service接口</p>
 * <p>系统：</p>
 * <p>模块：基础模块</p>
 * <p>日期：2016-11-24</p>
 * @version 1.0
 * @author wangbinbin
 */
public interface GczlYdxjSGZLJtwzService extends ICommonService<GczlYdxjSGZLJtwz>{

    void selectDataGrid(PageInfo pageInfo);

    /**
     * 新增结构施工质量基础数据-具体位置
     * @param gczlYdxjSGZLJtwz 要保存的实体
     */
    void save(GczlYdxjSGZLJtwz gczlYdxjSGZLJtwz);

    /**
     * 通过集合删除结构施工质量基础数据-具体位置
     * @param ids String字符串，中间用“,”间隔开
     */
    void del(String[] ids);

    /**     * 修改结构施工质量基础数据-具体位置
     * @param dto 要修改的实体
     */
    String update(GczlYdxjSGZLJtwzDto dto,User user);
    
    public String insert(GczlYdxjSGZLJtwzDto dto,User user);

   /**
     * 通过ID查找结构施工质量基础数据-具体位置
     * @param id 数据ID
     */
    GczlYdxjSGZLJtwz findById(String id);

	/**
	 * 校验当前字段是否已存在
	 * @param gczlYdxjSGZLJtwz 实体信息
	 * @param clumName 需校验的字段
	 * @return 若为null则不重复，若不为null则重复
	 */
	String checkClumIfexits(GczlYdxjSGZLJtwzDto gczlYdxjSGZLJtwz, String[] clumNames);
	
	/**
     * 启用或禁用当前实体对象
     * @param ids 要操作的对象ID
     * @param ifQy true:启用 false:禁用
     * @return 返回操作提示信息
     */
    String qy(String[] ids,boolean ifQy);
    
    /**
     * 根据具体位置实体信息查询具体位置列表
     * @param entity 查询条件实体
     * @return 返回具体位置实体集合
     */
    List<GczlYdxjSGZLJtwz> selectList( GczlYdxjSGZLJtwz entity);

}