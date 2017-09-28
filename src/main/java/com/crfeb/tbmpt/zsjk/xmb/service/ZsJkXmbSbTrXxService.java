package com.crfeb.tbmpt.zsjk.xmb.service;

import java.util.List;
import com.baomidou.framework.service.ICommonService;
import com.crfeb.tbmpt.sys.model.User;
import com.crfeb.tbmpt.commons.utils.PageInfo;
import com.crfeb.tbmpt.zsjk.xmb.model.dto.ZsJkXmbSbTrXxDto;
import com.crfeb.tbmpt.zsjk.xmb.model.ZsJkXmbSbTrXx;

/**
 * <p>项目部角度 盾构机、电瓶车、龙门吊等主要设备的运行与投入情况Service接口</p>
 * <p>系统：展示界面接口</p>
 * <p>模块：项目部角度模块</p>
 * <p>日期：2017-01-09</p>
 * @version 1.0
 * @author YangYJ
 */
public interface ZsJkXmbSbTrXxService extends ICommonService<ZsJkXmbSbTrXx>{

    void selectDataGrid(PageInfo pageInfo) throws Exception;

    /**
     * 新增项目部角度 盾构机、电瓶车、龙门吊等主要设备的运行与投入情况
     * @param zsJkXmbSbTrXx 要保存的实体
     */
    String save(ZsJkXmbSbTrXxDto zsJkXmbSbTrXxDto,User user) throws Exception;

    /**
     * 通过集合删除项目部角度 盾构机、电瓶车、龙门吊等主要设备的运行与投入情况
     * @param ids String字符串，中间用“,”间隔开
     */
    String del(String[] ids,User user) throws Exception;

    /**     * 修改项目部角度 盾构机、电瓶车、龙门吊等主要设备的运行与投入情况
     * @param zsJkXmbSbTrXx 要修改的实体
     */
    String update(ZsJkXmbSbTrXxDto zsJkXmbSbTrXxDto,User user) throws Exception;

   /**
     * 通过ID查找项目部角度 盾构机、电瓶车、龙门吊等主要设备的运行与投入情况
     * @param id 数据ID
     */
    ZsJkXmbSbTrXx findById(String id) throws Exception;

	/**
	 * 校验当前字段是否已存在
	 * @param zsJkXmbSbTrXx 实体信息
	 * @param clumName 需校验的字段
	 * @return 若为null则不重复，若不为null则重复
	 */
	String checkClumIfexits(ZsJkXmbSbTrXxDto zsJkXmbSbTrXxDto, String[] clumNames) throws Exception;
	
	/**
	 * 方法说明：获取盾构机、电瓶车、龙门吊等主要设备的运行与投入情况，增加“维保记录”链接跳转至维保明细
	 * @param xmId 项目id
	 * @return 返回 盾构机、电瓶车、龙门吊等主要设备的运行与投入情况
	 * @throws Exception
	 * @author:YangYj
	 * @Time: 2017年1月10日 下午1:26:48
	 */
	public List<ZsJkXmbSbTrXxDto> xmsbtr(String xmId) throws Exception;

}