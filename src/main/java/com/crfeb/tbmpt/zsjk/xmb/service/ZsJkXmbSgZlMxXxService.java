package com.crfeb.tbmpt.zsjk.xmb.service;

import java.util.List;
import com.baomidou.framework.service.ICommonService;
import com.crfeb.tbmpt.sys.model.User;
import com.crfeb.tbmpt.commons.utils.PageInfo;
import com.crfeb.tbmpt.zsjk.xmb.model.dto.ZsJkXmbSgZlMxXxDto;
import com.crfeb.tbmpt.zsjk.xmb.model.dto.ZsJkXmbSgZlXxDto;
import com.crfeb.tbmpt.zsjk.xmb.model.ZsJkXmbSgZlMxXx;

/**
 * <p>项目部角度  结构施工质量问题明细信息Service接口</p>
 * <p>系统：展示界面接口</p>
 * <p>模块：项目部角度模块</p>
 * <p>日期：2017-01-09</p>
 * @version 1.0
 * @author YangYJ
 */
public interface ZsJkXmbSgZlMxXxService extends ICommonService<ZsJkXmbSgZlMxXx>{

    void selectDataGrid(PageInfo pageInfo) throws Exception;

    /**
     * 新增项目部角度  结构施工质量问题明细信息
     * @param zsJkXmbSgZlMxXx 要保存的实体
     */
    String save(ZsJkXmbSgZlMxXxDto zsJkXmbSgZlMxXxDto,User user) throws Exception;

    /**
     * 通过集合删除项目部角度  结构施工质量问题明细信息
     * @param ids String字符串，中间用“,”间隔开
     */
    String del(String[] ids,User user) throws Exception;

    /**     * 修改项目部角度  结构施工质量问题明细信息
     * @param zsJkXmbSgZlMxXx 要修改的实体
     */
    String update(ZsJkXmbSgZlMxXxDto zsJkXmbSgZlMxXxDto,User user) throws Exception;

   /**
     * 通过ID查找项目部角度  结构施工质量问题明细信息
     * @param id 数据ID
     */
    ZsJkXmbSgZlMxXx findById(String id) throws Exception;

	/**
	 * 校验当前字段是否已存在
	 * @param zsJkXmbSgZlMxXx 实体信息
	 * @param clumName 需校验的字段
	 * @return 若为null则不重复，若不为null则重复
	 */
	String checkClumIfexits(ZsJkXmbSgZlMxXxDto zsJkXmbSgZlMxXxDto, String[] clumNames) throws Exception;
	
	/**
	 * 方法说明：获取结构施工质量问题明细数据
	 * @param projectId 项目ID
	 * @param shigzlfl 结构施工质量分类
	 * @param date 日期
	 * @return 返回结构施工质量问题明细数据
	 * @throws Exception
	 * @author:YangYj
	 * @Time: 2017年1月10日 上午11:43:15
	 */
	public List<ZsJkXmbSgZlMxXxDto> sgzlmx(String projectId,String shigzlfl,String date)throws Exception;

}