package com.crfeb.tbmpt.zsjk.xmb.service;

import java.util.List;
import com.baomidou.framework.service.ICommonService;
import com.crfeb.tbmpt.sys.model.User;
import com.crfeb.tbmpt.commons.utils.PageInfo;
import com.crfeb.tbmpt.zsjk.xmb.model.dto.ZsJkXmbSbWbXxDto;
import com.crfeb.tbmpt.zsjk.xmb.model.ZsJkXmbSbWbXx;

/**
 * <p>项目部角度 当前设备的维保（维修、保养）明细Service接口</p>
 * <p>系统：展示界面接口</p>
 * <p>模块：项目部角度模块</p>
 * <p>日期：2017-01-09</p>
 * @version 1.0
 * @author YangYJ
 */
public interface ZsJkXmbSbWbXxService extends ICommonService<ZsJkXmbSbWbXx>{

    void selectDataGrid(PageInfo pageInfo) throws Exception;

    /**
     * 新增项目部角度 当前设备的维保（维修、保养）明细
     * @param zsJkXmbSbWbXx 要保存的实体
     */
    String save(ZsJkXmbSbWbXxDto zsJkXmbSbWbXxDto,User user) throws Exception;

    /**
     * 通过集合删除项目部角度 当前设备的维保（维修、保养）明细
     * @param ids String字符串，中间用“,”间隔开
     */
    String del(String[] ids,User user) throws Exception;

    /**     * 修改项目部角度 当前设备的维保（维修、保养）明细
     * @param zsJkXmbSbWbXx 要修改的实体
     */
    String update(ZsJkXmbSbWbXxDto zsJkXmbSbWbXxDto,User user) throws Exception;

   /**
     * 通过ID查找项目部角度 当前设备的维保（维修、保养）明细
     * @param id 数据ID
     */
    ZsJkXmbSbWbXx findById(String id) throws Exception;

	/**
	 * 校验当前字段是否已存在
	 * @param zsJkXmbSbWbXx 实体信息
	 * @param clumName 需校验的字段
	 * @return 若为null则不重复，若不为null则重复
	 */
	String checkClumIfexits(ZsJkXmbSbWbXxDto zsJkXmbSbWbXxDto, String[] clumNames) throws Exception;
	/**
	 * 方法说明：获取当前设备的维保（维修、保养）明细
	 * @param sbId  设备ID
	 * @param dateType 日期类型（日、周、月）
	 * @return 返回当前设备的维保（维修、保养）明细 <br/>
	 * 注：  1、返回日期连续，无值则为<br/>
	 * 	  2、日默认查出7天<br/>
	 *    3、周默认查出7周<br/>
	 *	  4、月默认查出7月<br/>
	 * @throws Exception
	 * @author:YangYj
	 * @Time: 2017年1月10日 下午1:31:38
	 */
	public List<ZsJkXmbSbWbXxDto> zysbwxby(String sbId,String dateType)throws Exception;

}