package com.crfeb.tbmpt.zsjk.xmb.service;

import java.util.List;
import com.baomidou.framework.service.ICommonService;
import com.crfeb.tbmpt.sys.model.User;
import com.crfeb.tbmpt.commons.utils.PageInfo;
import com.crfeb.tbmpt.zsjk.xmb.model.dto.ZsJkXmbDjXhZTrXxDto;
import com.crfeb.tbmpt.zsjk.xmb.model.ZsJkXmbDjXhZTrXx;

/**
 * <p>项目部角度 刀具消耗总投入信息Service接口</p>
 * <p>系统：展示界面接口</p>
 * <p>模块：项目部角度模块</p>
 * <p>日期：2017-01-09</p>
 * @version 1.0
 * @author YangYJ
 */
public interface ZsJkXmbDjXhZTrXxService extends ICommonService<ZsJkXmbDjXhZTrXx>{

    void selectDataGrid(PageInfo pageInfo) throws Exception;

    /**
     * 新增项目部角度 刀具消耗总投入信息
     * @param zsJkXmbDjXhZTrXx 要保存的实体
     */
    String save(ZsJkXmbDjXhZTrXxDto zsJkXmbDjXhZTrXxDto,User user) throws Exception;

    /**
     * 通过集合删除项目部角度 刀具消耗总投入信息
     * @param ids String字符串，中间用“,”间隔开
     */
    String del(String[] ids,User user) throws Exception;

    /**     * 修改项目部角度 刀具消耗总投入信息
     * @param zsJkXmbDjXhZTrXx 要修改的实体
     */
    String update(ZsJkXmbDjXhZTrXxDto zsJkXmbDjXhZTrXxDto,User user) throws Exception;

   /**
     * 通过ID查找项目部角度 刀具消耗总投入信息
     * @param id 数据ID
     */
    ZsJkXmbDjXhZTrXx findById(String id) throws Exception;

	/**
	 * 校验当前字段是否已存在
	 * @param zsJkXmbDjXhZTrXx 实体信息
	 * @param clumName 需校验的字段
	 * @return 若为null则不重复，若不为null则重复
	 */
	String checkClumIfexits(ZsJkXmbDjXhZTrXxDto zsJkXmbDjXhZTrXxDto, String[] clumNames) throws Exception;
	
	/**
	 * 方法说明：获取刀具的材料消耗
	 * @param xlId 线路ID
	 * @param fxnr 分析内容
	 * @param djlx 刀具类型
	 * @param qshh 起始环号
	 * @param jzhh 截止环号
	 * @return  返回刀具的材料消耗
	 * @throws Exception
	 * @author:YangYj
	 * @Time: 2017年1月10日 上午11:15:43
	 */
	public ZsJkXmbDjXhZTrXxDto getXmDjxhztr(String xlId,String fxnr,String djlx,String qshh,String jzhh) throws Exception;

}