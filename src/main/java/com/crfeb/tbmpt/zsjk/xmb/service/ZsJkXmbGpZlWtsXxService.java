package com.crfeb.tbmpt.zsjk.xmb.service;

import java.util.List;
import com.baomidou.framework.service.ICommonService;
import com.crfeb.tbmpt.sys.model.User;
import com.crfeb.tbmpt.commons.utils.PageInfo;
import com.crfeb.tbmpt.zsjk.xmb.model.dto.ZsJkXmbGpZlWtsXxDto;
import com.crfeb.tbmpt.zsjk.xmb.model.ZsJkXmbGpZlWtsXx;

/**
 * <p>项目部角度  各质量分类（管片破损、管片错台、螺栓复紧、渗漏水等）下的管片质量问题数量信息Service接口</p>
 * <p>系统：展示界面接口</p>
 * <p>模块：项目部角度模块</p>
 * <p>日期：2017-01-09</p>
 * @version 1.0
 * @author YangYJ
 */
public interface ZsJkXmbGpZlWtsXxService extends ICommonService<ZsJkXmbGpZlWtsXx>{

    void selectDataGrid(PageInfo pageInfo) throws Exception;

    /**
     * 新增项目部角度  各质量分类（管片破损、管片错台、螺栓复紧、渗漏水等）下的管片质量问题数量信息
     * @param zsJkXmbGpZlWtsXx 要保存的实体
     */
    String save(ZsJkXmbGpZlWtsXxDto zsJkXmbGpZlWtsXxDto,User user) throws Exception;

    /**
     * 通过集合删除项目部角度  各质量分类（管片破损、管片错台、螺栓复紧、渗漏水等）下的管片质量问题数量信息
     * @param ids String字符串，中间用“,”间隔开
     */
    String del(String[] ids,User user) throws Exception;

    /**     * 修改项目部角度  各质量分类（管片破损、管片错台、螺栓复紧、渗漏水等）下的管片质量问题数量信息
     * @param zsJkXmbGpZlWtsXx 要修改的实体
     */
    String update(ZsJkXmbGpZlWtsXxDto zsJkXmbGpZlWtsXxDto,User user) throws Exception;

   /**
     * 通过ID查找项目部角度  各质量分类（管片破损、管片错台、螺栓复紧、渗漏水等）下的管片质量问题数量信息
     * @param id 数据ID
     */
    ZsJkXmbGpZlWtsXx findById(String id) throws Exception;

	/**
	 * 校验当前字段是否已存在
	 * @param zsJkXmbGpZlWtsXx 实体信息
	 * @param clumName 需校验的字段
	 * @return 若为null则不重复，若不为null则重复
	 */
	String checkClumIfexits(ZsJkXmbGpZlWtsXxDto zsJkXmbGpZlWtsXxDto, String[] clumNames) throws Exception;
	
	/**
	 * 方法说明：获得各质量分类（管片破损、管片错台、螺栓复紧、渗漏水等）下的质量问题数量
	 * @param projectId 项目id
	 * @param startTime 起始日期
	 * @param endTime 截止日期   （二个日期不输的话默认当天）
	 * @return  返回管片质量分类
	 * @throws Exception
	 * @author:YangYj
	 * @Time: 2017年1月10日 上午11:22:50
	 */
	public List<ZsJkXmbGpZlWtsXxDto> gpzl(String projectId,String startTime,String endTime) throws Exception;

}