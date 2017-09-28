package com.crfeb.tbmpt.zsjk.xmb.service;

import java.util.List;
import com.baomidou.framework.service.ICommonService;
import com.crfeb.tbmpt.sys.model.User;
import com.crfeb.tbmpt.commons.utils.PageInfo;
import com.crfeb.tbmpt.zsjk.xmb.model.dto.ZsJkXmbDjXhZTrDataXxDto;
import com.crfeb.tbmpt.zsjk.xmb.model.ZsJkXmbDjXhZTrDataXx;

/**
 * <p>项目部角度 刀具消耗总投入明细信息Service接口</p>
 * <p>系统：展示界面接口</p>
 * <p>模块：项目部角度模块</p>
 * <p>日期：2017-01-09</p>
 * @version 1.0
 * @author YangYJ
 */
public interface ZsJkXmbDjXhZTrDataXxService extends ICommonService<ZsJkXmbDjXhZTrDataXx>{

    void selectDataGrid(PageInfo pageInfo) throws Exception;

    /**
     * 新增项目部角度 刀具消耗总投入明细信息
     * @param zsJkXmbDjXhZTrDataXx 要保存的实体
     */
    String save(ZsJkXmbDjXhZTrDataXxDto zsJkXmbDjXhZTrDataXxDto,User user) throws Exception;

    /**
     * 通过集合删除项目部角度 刀具消耗总投入明细信息
     * @param ids String字符串，中间用“,”间隔开
     */
    String del(String[] ids,User user) throws Exception;

    /**     * 修改项目部角度 刀具消耗总投入明细信息
     * @param zsJkXmbDjXhZTrDataXx 要修改的实体
     */
    String update(ZsJkXmbDjXhZTrDataXxDto zsJkXmbDjXhZTrDataXxDto,User user) throws Exception;

   /**
     * 通过ID查找项目部角度 刀具消耗总投入明细信息
     * @param id 数据ID
     */
    ZsJkXmbDjXhZTrDataXx findById(String id) throws Exception;

	/**
	 * 校验当前字段是否已存在
	 * @param zsJkXmbDjXhZTrDataXx 实体信息
	 * @param clumName 需校验的字段
	 * @return 若为null则不重复，若不为null则重复
	 */
	String checkClumIfexits(ZsJkXmbDjXhZTrDataXxDto zsJkXmbDjXhZTrDataXxDto, String[] clumNames) throws Exception;

}