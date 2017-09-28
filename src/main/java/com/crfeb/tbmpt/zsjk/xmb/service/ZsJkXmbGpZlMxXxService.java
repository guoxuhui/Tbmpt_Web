package com.crfeb.tbmpt.zsjk.xmb.service;

import java.util.List;
import com.baomidou.framework.service.ICommonService;
import com.crfeb.tbmpt.sys.model.User;
import com.crfeb.tbmpt.commons.utils.PageInfo;
import com.crfeb.tbmpt.zsjk.xmb.model.dto.ZsJkXmbGpZlMxXxDto;
import com.crfeb.tbmpt.zsjk.xmb.model.ZsJkXmbGpZlMxXx;

/**
 * <p>项目部角度  管片质量问题明细数据信息Service接口</p>
 * <p>系统：展示界面接口</p>
 * <p>模块：项目部角度模块</p>
 * <p>日期：2017-01-09</p>
 * @version 1.0
 * @author YangYJ
 */
public interface ZsJkXmbGpZlMxXxService extends ICommonService<ZsJkXmbGpZlMxXx>{

    void selectDataGrid(PageInfo pageInfo) throws Exception;

    /**
     * 新增项目部角度  管片质量问题明细数据信息
     * @param zsJkXmbGpZlMxXx 要保存的实体
     */
    String save(ZsJkXmbGpZlMxXxDto zsJkXmbGpZlMxXxDto,User user) throws Exception;

    /**
     * 通过集合删除项目部角度  管片质量问题明细数据信息
     * @param ids String字符串，中间用“,”间隔开
     */
    String del(String[] ids,User user) throws Exception;

    /**     * 修改项目部角度  管片质量问题明细数据信息
     * @param zsJkXmbGpZlMxXx 要修改的实体
     */
    String update(ZsJkXmbGpZlMxXxDto zsJkXmbGpZlMxXxDto,User user) throws Exception;

   /**
     * 通过ID查找项目部角度  管片质量问题明细数据信息
     * @param id 数据ID
     */
    ZsJkXmbGpZlMxXx findById(String id) throws Exception;

	/**
	 * 校验当前字段是否已存在
	 * @param zsJkXmbGpZlMxXx 实体信息
	 * @param clumName 需校验的字段
	 * @return 若为null则不重复，若不为null则重复
	 */
	String checkClumIfexits(ZsJkXmbGpZlMxXxDto zsJkXmbGpZlMxXxDto, String[] clumNames) throws Exception;
	
	/**
	 * 方法说明：获取管片质量问题明细数据
	 * @param projectId  项目ID
	 * @param date 日期
	 * @param qualityType 质量分类
	 * @return 返回管片质量问题明细数据
	 * @throws Exception
	 * @author:YangYj
	 * @Time: 2017年1月10日 上午11:35:33
	 */
	public List<ZsJkXmbGpZlMxXxDto> gpzlwtmxsj(String projectId,String date,String qualityType) throws Exception;

}