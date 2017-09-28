package com.crfeb.tbmpt.zsjk.xmb.service;

import java.util.List;
import com.baomidou.framework.service.ICommonService;
import com.crfeb.tbmpt.sys.model.User;
import com.crfeb.tbmpt.commons.utils.PageInfo;
import com.crfeb.tbmpt.zsjk.xmb.model.dto.ZsJkXmbClYdXhXxDto;
import com.crfeb.tbmpt.zsjk.xmb.model.ZsJkXmbClYdXhXx;

/**
 * <p>项目部角度 项目的材料月度总消耗信息Service接口</p>
 * <p>系统：展示界面接口</p>
 * <p>模块：项目部角度模块</p>
 * <p>日期：2017-01-09</p>
 * @version 1.0
 * @author YangYJ
 */
public interface ZsJkXmbClYdXhXxService extends ICommonService<ZsJkXmbClYdXhXx>{

    void selectDataGrid(PageInfo pageInfo) throws Exception;

    /**
     * 新增项目部角度 项目的材料月度总消耗信息
     * @param zsJkXmbClYdXhXx 要保存的实体
     */
    String save(ZsJkXmbClYdXhXxDto zsJkXmbClYdXhXxDto,User user) throws Exception;

    /**
     * 通过集合删除项目部角度 项目的材料月度总消耗信息
     * @param ids String字符串，中间用“,”间隔开
     */
    String del(String[] ids,User user) throws Exception;

    /**     * 修改项目部角度 项目的材料月度总消耗信息
     * @param zsJkXmbClYdXhXx 要修改的实体
     */
    String update(ZsJkXmbClYdXhXxDto zsJkXmbClYdXhXxDto,User user) throws Exception;

   /**
     * 通过ID查找项目部角度 项目的材料月度总消耗信息
     * @param id 数据ID
     */
    ZsJkXmbClYdXhXx findById(String id) throws Exception;

	/**
	 * 校验当前字段是否已存在
	 * @param zsJkXmbClYdXhXx 实体信息
	 * @param clumName 需校验的字段
	 * @return 若为null则不重复，若不为null则重复
	 */
	String checkClumIfexits(ZsJkXmbClYdXhXxDto zsJkXmbClYdXhXxDto, String[] clumNames) throws Exception;
	
	/**
	 * 方法说明：获取所在项目的材料月度总消耗
	 * @param xmId 项目ID
	 * @param strDate 起始时间
	 * @param endDate 截止时间
	 * @return 返回项目的材料各月度总消耗信息
	 * @throws Exception
	 * @author:YangYj
	 * @Time: 2017年1月10日 上午10:55:22
	 */
	public List<ZsJkXmbClYdXhXxDto> getXmClydxh(String xmId,String strDate,String endDate) throws Exception;

}