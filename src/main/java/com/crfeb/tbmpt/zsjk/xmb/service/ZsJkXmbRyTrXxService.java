package com.crfeb.tbmpt.zsjk.xmb.service;

import java.util.List;
import com.baomidou.framework.service.ICommonService;
import com.crfeb.tbmpt.sys.model.User;
import com.crfeb.tbmpt.commons.utils.PageInfo;
import com.crfeb.tbmpt.zsjk.xmb.model.dto.ZsJkXmbRyTrXxDto;
import com.crfeb.tbmpt.zsjk.xmb.model.ZsJkXmbRyTrXx;

/**
 * <p>项目部角度 项目人员投入信息Service接口</p>
 * <p>系统：展示界面接口</p>
 * <p>模块：项目部角度模块</p>
 * <p>日期：2017-01-09</p>
 * @version 1.0
 * @author YangYJ
 */
public interface ZsJkXmbRyTrXxService extends ICommonService<ZsJkXmbRyTrXx>{

    void selectDataGrid(PageInfo pageInfo) throws Exception;

    /**
     * 新增项目部角度 项目人员投入信息
     * @param zsJkXmbRyTrXx 要保存的实体
     */
    String save(ZsJkXmbRyTrXxDto zsJkXmbRyTrXxDto,User user) throws Exception;

    /**
     * 通过集合删除项目部角度 项目人员投入信息
     * @param ids String字符串，中间用“,”间隔开
     */
    String del(String[] ids,User user) throws Exception;

    /**     * 修改项目部角度 项目人员投入信息
     * @param zsJkXmbRyTrXx 要修改的实体
     */
    String update(ZsJkXmbRyTrXxDto zsJkXmbRyTrXxDto,User user) throws Exception;

   /**
     * 通过ID查找项目部角度 项目人员投入信息
     * @param id 数据ID
     */
    ZsJkXmbRyTrXx findById(String id) throws Exception;

	/**
	 * 校验当前字段是否已存在
	 * @param zsJkXmbRyTrXx 实体信息
	 * @param clumName 需校验的字段
	 * @return 若为null则不重复，若不为null则重复
	 */
	String checkClumIfexits(ZsJkXmbRyTrXxDto zsJkXmbRyTrXxDto, String[] clumNames) throws Exception;
	
	/**
	 * 方法说明：获取本项目人员情况
	 * @param xmId 项目id
	 * @return 返回本项目人员情况
	 * @throws Exception
	 * @author:YangYj
	 * @Time: 2017年1月10日 下午1:24:24
	 */
	public List<ZsJkXmbRyTrXxDto> xmrytr(String xmId) throws Exception;

}