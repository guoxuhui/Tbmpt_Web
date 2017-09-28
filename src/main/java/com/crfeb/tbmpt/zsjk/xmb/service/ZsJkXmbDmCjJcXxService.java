package com.crfeb.tbmpt.zsjk.xmb.service;

import java.util.List;
import com.baomidou.framework.service.ICommonService;
import com.crfeb.tbmpt.sys.model.User;
import com.crfeb.tbmpt.commons.utils.PageInfo;
import com.crfeb.tbmpt.zsjk.xmb.model.dto.ZsJkXmbDmCjJcXxDto;
import com.crfeb.tbmpt.zsjk.xmb.model.ZsJkXmbDmCjJcXx;

/**
 * <p>项目部角度  地面沉降情况（默认取当前里程的前后50米）Service接口</p>
 * <p>系统：展示界面接口</p>
 * <p>模块：项目部角度模块</p>
 * <p>日期：2017-01-09</p>
 * @version 1.0
 * @author YangYJ
 */
public interface ZsJkXmbDmCjJcXxService extends ICommonService<ZsJkXmbDmCjJcXx>{

    void selectDataGrid(PageInfo pageInfo) throws Exception;

    /**
     * 新增项目部角度  地面沉降情况（默认取当前里程的前后50米）
     * @param zsJkXmbDmCjJcXx 要保存的实体
     */
    String save(ZsJkXmbDmCjJcXxDto zsJkXmbDmCjJcXxDto,User user) throws Exception;

    /**
     * 通过集合删除项目部角度  地面沉降情况（默认取当前里程的前后50米）
     * @param ids String字符串，中间用“,”间隔开
     */
    String del(String[] ids,User user) throws Exception;

    /**     * 修改项目部角度  地面沉降情况（默认取当前里程的前后50米）
     * @param zsJkXmbDmCjJcXx 要修改的实体
     */
    String update(ZsJkXmbDmCjJcXxDto zsJkXmbDmCjJcXxDto,User user) throws Exception;

   /**
     * 通过ID查找项目部角度  地面沉降情况（默认取当前里程的前后50米）
     * @param id 数据ID
     */
    ZsJkXmbDmCjJcXx findById(String id) throws Exception;

	/**
	 * 校验当前字段是否已存在
	 * @param zsJkXmbDmCjJcXx 实体信息
	 * @param clumName 需校验的字段
	 * @return 若为null则不重复，若不为null则重复
	 */
	String checkClumIfexits(ZsJkXmbDmCjJcXxDto zsJkXmbDmCjJcXxDto, String[] clumNames) throws Exception;
	
	/**
	 * 方法说明：获取地面沉降情况（默认取当前里程的前后50米）
	 * @param xlId 线路ID
	 * @param startLic 起止里程
	 * @param dianbh 点位编号
	 * @param fazhi 阈值（大于等于）
	 * @return
	 * @throws Exception
	 * @author:YangYj
	 * @Time: 2017年1月10日 上午11:51:33
	 */
	public List<ZsJkXmbDmCjJcXxDto> cjjc(String xlId,String startLic,String dianbh,String fazhi) throws Exception;

}