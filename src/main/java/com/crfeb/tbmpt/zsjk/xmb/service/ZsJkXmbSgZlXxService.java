package com.crfeb.tbmpt.zsjk.xmb.service;

import java.util.List;
import com.baomidou.framework.service.ICommonService;
import com.crfeb.tbmpt.sys.model.User;
import com.crfeb.tbmpt.commons.utils.PageInfo;
import com.crfeb.tbmpt.zsjk.xmb.model.dto.ZsJkXmbSgZlXxDto;
import com.crfeb.tbmpt.zsjk.xmb.model.ZsJkXmbSgZlXx;

/**
 * <p>项目部角度  结构施工质量问题信息Service接口</p>
 * <p>系统：展示界面接口</p>
 * <p>模块：项目部角度模块</p>
 * <p>日期：2017-01-09</p>
 * @version 1.0
 * @author YangYJ
 */
public interface ZsJkXmbSgZlXxService extends ICommonService<ZsJkXmbSgZlXx>{

    void selectDataGrid(PageInfo pageInfo) throws Exception;

    /**
     * 新增项目部角度  结构施工质量问题信息
     * @param zsJkXmbSgZlXx 要保存的实体
     */
    String save(ZsJkXmbSgZlXxDto zsJkXmbSgZlXxDto,User user) throws Exception;

    /**
     * 通过集合删除项目部角度  结构施工质量问题信息
     * @param ids String字符串，中间用“,”间隔开
     */
    String del(String[] ids,User user) throws Exception;

    /**     * 修改项目部角度  结构施工质量问题信息
     * @param zsJkXmbSgZlXx 要修改的实体
     */
    String update(ZsJkXmbSgZlXxDto zsJkXmbSgZlXxDto,User user) throws Exception;

   /**
     * 通过ID查找项目部角度  结构施工质量问题信息
     * @param id 数据ID
     */
    ZsJkXmbSgZlXx findById(String id) throws Exception;

	/**
	 * 校验当前字段是否已存在
	 * @param zsJkXmbSgZlXx 实体信息
	 * @param clumName 需校验的字段
	 * @return 若为null则不重复，若不为null则重复
	 */
	String checkClumIfexits(ZsJkXmbSgZlXxDto zsJkXmbSgZlXxDto, String[] clumNames) throws Exception;
	
	/**
	 * 方法说明：获得各结构施工质量（使用部位、工程）下的质量问题数量
	 * @param startTime 起始日期
	 * @param endtTime 截止日期 （二个日期不输的话默认当天）
	 * @param projectId 项目id
	 * @return 返回各结构施工质量（使用部位、工程）下的质量问题数量
	 * @throws Exception
	 * @author:YangYj
	 * @Time: 2017年1月10日 上午11:39:28
	 */
	public List<ZsJkXmbSgZlXxDto> sgzl(String startTime,String endtTime,String projectId)throws Exception;

}