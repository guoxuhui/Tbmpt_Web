package com.crfeb.tbmpt.zsjk.xmb.service;

import java.util.List;
import com.baomidou.framework.service.ICommonService;
import com.crfeb.tbmpt.sys.model.User;
import com.crfeb.tbmpt.commons.utils.PageInfo;
import com.crfeb.tbmpt.zsjk.xmb.model.dto.ZsJkXmbGpZtClXxDto;
import com.crfeb.tbmpt.zsjk.xmb.model.ZsJkXmbGpZtClXx;

/**
 * <p>项目部角度  管片姿态测量信息Service接口</p>
 * <p>系统：展示界面接口</p>
 * <p>模块：项目部角度模块</p>
 * <p>日期：2017-01-09</p>
 * @version 1.0
 * @author YangYJ
 */
public interface ZsJkXmbGpZtClXxService extends ICommonService<ZsJkXmbGpZtClXx>{

    void selectDataGrid(PageInfo pageInfo) throws Exception;

    /**
     * 新增项目部角度  管片姿态测量信息
     * @param zsJkXmbGpZtClXx 要保存的实体
     */
    String save(ZsJkXmbGpZtClXxDto zsJkXmbGpZtClXxDto,User user) throws Exception;

    /**
     * 通过集合删除项目部角度  管片姿态测量信息
     * @param ids String字符串，中间用“,”间隔开
     */
    String del(String[] ids,User user) throws Exception;

    /**     * 修改项目部角度  管片姿态测量信息
     * @param zsJkXmbGpZtClXx 要修改的实体
     */
    String update(ZsJkXmbGpZtClXxDto zsJkXmbGpZtClXxDto,User user) throws Exception;

   /**
     * 通过ID查找项目部角度  管片姿态测量信息
     * @param id 数据ID
     */
    ZsJkXmbGpZtClXx findById(String id) throws Exception;

	/**
	 * 校验当前字段是否已存在
	 * @param zsJkXmbGpZtClXx 实体信息
	 * @param clumName 需校验的字段
	 * @return 若为null则不重复，若不为null则重复
	 */
	String checkClumIfexits(ZsJkXmbGpZtClXxDto zsJkXmbGpZtClXxDto, String[] clumNames) throws Exception;
	
	/**
	 * 方法说明：获取项目管片的姿态测量信息
	 * @param ID 线路ID
	 * @param qshh起始环号 
	 * @param jzhh 截止环号
	 * @return 返回项目管片的姿态测量信息
	 * @throws Exception
	 * @author:YangYj
	 * @Time: 2017年1月10日 下午1:21:29
	 */
	public List<ZsJkXmbGpZtClXxDto> gpztxx(String ID,String qshh,String jzhh) throws Exception;

}