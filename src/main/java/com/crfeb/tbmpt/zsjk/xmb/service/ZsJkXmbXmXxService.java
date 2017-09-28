package com.crfeb.tbmpt.zsjk.xmb.service;

import java.util.List;
import com.baomidou.framework.service.ICommonService;
import com.crfeb.tbmpt.sys.model.User;
import com.crfeb.tbmpt.commons.utils.PageInfo;
import com.crfeb.tbmpt.zsjk.xmb.model.dto.ZsJkXmbXmXxDto;
import com.crfeb.tbmpt.zsjk.xmb.model.ZsJkXmbXmXx;

/**
 * <p>项目部角度 项目基本信息Service接口</p>
 * <p>系统：展示界面接口</p>
 * <p>模块：项目部角度模块</p>
 * <p>日期：2017-01-09</p>
 * @version 1.0
 * @author YangYJ
 */
public interface ZsJkXmbXmXxService extends ICommonService<ZsJkXmbXmXx>{

    void selectDataGrid(PageInfo pageInfo) throws Exception;

    /**
     * 新增项目部角度 项目基本信息
     * @param zsJkXmbXmXx 要保存的实体
     */
    String save(ZsJkXmbXmXxDto zsJkXmbXmXxDto,User user) throws Exception;

    /**
     * 通过集合删除项目部角度 项目基本信息
     * @param ids String字符串，中间用“,”间隔开
     */
    String del(String[] ids,User user) throws Exception;

    /**     * 修改项目部角度 项目基本信息
     * @param zsJkXmbXmXx 要修改的实体
     */
    String update(ZsJkXmbXmXxDto zsJkXmbXmXxDto,User user) throws Exception;

   /**
     * 通过ID查找项目部角度 项目基本信息
     * @param id 数据ID
     */
    ZsJkXmbXmXx findById(String id) throws Exception;

	/**
	 * 校验当前字段是否已存在
	 * @param zsJkXmbXmXx 实体信息
	 * @param clumName 需校验的字段
	 * @return 若为null则不重复，若不为null则重复
	 */
	String checkClumIfexits(ZsJkXmbXmXxDto zsJkXmbXmXxDto, String[] clumNames) throws Exception;
	
	/**
	 * 方法说明：获取项目详细信息
	 * @param xmId 项目id
	 * @return 返回 项目详细信息
	 * @throws Exception
	 * @author:YangYj
	 * @Time: 2017年1月10日 上午10:25:34
	 */
	public ZsJkXmbXmXxDto getXMXX(String xmId) throws Exception;

}