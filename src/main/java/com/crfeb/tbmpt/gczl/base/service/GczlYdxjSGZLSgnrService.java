package com.crfeb.tbmpt.gczl.base.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.baomidou.framework.service.ICommonService;
import com.crfeb.tbmpt.commons.utils.PageInfo;
import com.crfeb.tbmpt.gczl.base.model.GczlYdxjSGZLSgnr;
import com.crfeb.tbmpt.gczl.base.model.dto.GczlYdxjSGZLSgnrDto;
import com.crfeb.tbmpt.sys.model.User;

/**
 * <p>施工内容管理Service接口</p>
 * <p>系统：</p>
 * <p>模块：基础模块</p>
 * <p>日期：2016-11-23</p>
 * @version 1.0
 * @author wangbinbin
 */
public interface GczlYdxjSGZLSgnrService extends ICommonService<GczlYdxjSGZLSgnr>{

    void selectDataGrid(PageInfo pageInfo);

    /**
     * 新增施工内容管理
     * @param gczlYdxjSGZLSgnr 要保存的实体
     */
    void save(GczlYdxjSGZLSgnr gczlYdxjSGZLSgnr);

    /**
     * 通过集合删除施工内容管理
     * @param ids String字符串，中间用“,”间隔开
     */
    void del(String[] ids);

    /**     * 修改施工内容管理
     * @param gczlYdxjSGZLSgnr 要修改的实体
     */
    public String update(GczlYdxjSGZLSgnrDto gczlYdxjSGZLSgnr, User user);

   /**
     * 通过ID查找施工内容管理
     * @param id 数据ID
     */
    GczlYdxjSGZLSgnr findById(String id);
	/**
	 * 校验当前字段是否已存在
	 * @param gczlYdxjSGZLSgnr 实体信息
	 * @param clumName 需校验的字段
	 * @return 若为null则不重复，若不为null则重复
	 */
	String checkClumIfexits(GczlYdxjSGZLSgnrDto gczlYdxjSGZLSgnr, String[] clumNames);
	
    /**
     * 启用或禁用当前实体对象
     * @param ids 要操作的对象ID
     * @param ifQy true:启用 false:禁用
     * @return 返回操作提示信息
     */
    String qy(String[] ids,boolean ifQy);
    
    /**
     * 拷贝实体信息到dto中
     * @param source 实体信息
     * @param target dto信息
     */
    public void BeanUtilsEntityToDto(GczlYdxjSGZLSgnr source,GczlYdxjSGZLSgnrDto target);
    
    /**
     * 根据主键查询施工管理内容信息，返回对应的dto集合
     * @param idList 物理主键集合
     * @return 返回对应的施工内容dto集合
     */
    public List<GczlYdxjSGZLSgnrDto> selectDtoBatchIds(List<String> idList);
    
    /**
     * 根据物理主键集合删除施工内容及其对应的具体位置及质量缺陷信息
     * @param idlist 施工内容物理主键集合
     */
    public void deleteByIds(List<String> idlist);
    
    /**
     * 根据状态查询施工内容信息
     * @param sta 施工状态 1:启用；0：禁用 为空时则查询所有的数据
     * @return 返回施工内容数据
     */
    List<GczlYdxjSGZLSgnr> selectBySta(String sta);
    
    int insert(GczlYdxjSGZLSgnrDto dto, User user);
    
    void test(GczlYdxjSGZLSgnr entity);

}