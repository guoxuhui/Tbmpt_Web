package com.crfeb.tbmpt.zsjk.plc.service;

import com.crfeb.tbmpt.commons.utils.PageInfo;
import com.crfeb.tbmpt.sys.model.User;
import com.crfeb.tbmpt.zsjk.plc.model.ProPlcReal;
import com.crfeb.tbmpt.zsjk.plc.model.dto.ProPlcBzRealDto;
import com.crfeb.tbmpt.zsjk.plc.model.dto.ProPlcRealDto;
import com.crfeb.tbmpt.zsjk.plc.model.dto.ProSecLinePlcDto;

import java.util.List;

import com.baomidou.framework.service.ICommonService;

/**
 *
 * ProPlcReal 表数据服务层接口
 *
 */
public interface IProPlcRealService extends ICommonService<ProPlcReal> {


    void selectDataGrid(PageInfo pageInfo) throws Exception;

    /**
     * 新增盾构机实时数据
     * @param proPlcReal 要保存的实体
     */
    String save(ProPlcRealDto proPlcRealDto,User user) throws Exception;

    /**
     * 批量更新新增点位实时数据
     * @param list
     * @return
     * @throws Exception
     */
    String insertOrUpdateBatch(List<ProPlcReal> list) throws Exception;
    
    /**
     * 通过集合删除盾构机实时数据
     * @param ids String字符串，中间用“,”间隔开
     */
    String del(String[] ids,User user) throws Exception;

    /**     * 修改盾构机实时数据
     * @param proPlcReal 要修改的实体
     */
    String update(ProPlcRealDto proPlcRealDto,User user) throws Exception;

   /**
     * 通过ID查找盾构机实时数据
     * @param id 数据ID
     */
    ProPlcReal findById(String id) throws Exception;

	/**
	 * 校验当前字段是否已存在
	 * @param proPlcReal 实体信息
	 * @param clumName 需校验的字段
	 * @return 若为null则不重复，若不为null则重复
	 */
	String checkClumIfexits(ProPlcRealDto proPlcRealDto, String[] clumNames) throws Exception;

	/**
	 * 获取盾构机全部实时数据
	 * @param tbmId
	 * @return
	 * @throws Exception
	 */
	List<ProPlcBzRealDto> getSsJqsj(String tbmId) throws Exception ;
	
	/**
	 * 获取指定的盾构机点位编号实时数据
	 * @param tbmId
	 * @param dwIds
	 * @return
	 * @throws Exception
	 */
	List<ProPlcBzRealDto> getSsJqsjByDw(String tbmId,String dwIds) throws Exception ;
	
	
    /**
     * 方法说明：获取全部PLC标准点位数据，封装成项目区间线路点位信息表
     * @return 返回数据dto列表集合
     * @throws Exception
     * @Time: 2017年1月10日 下午2:31:15
     */
    List<ProSecLinePlcDto> selectProSecLinePlcAll() throws Exception;
	
}