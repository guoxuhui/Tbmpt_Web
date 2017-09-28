package com.crfeb.tbmpt.zsjk.plc.service;

import com.crfeb.tbmpt.commons.utils.PageInfo;
import com.crfeb.tbmpt.sys.model.User;
import com.crfeb.tbmpt.zsjk.plc.model.ProPlcBzdw;
import com.crfeb.tbmpt.zsjk.plc.model.dto.ProPlcBzdwDto;
import com.baomidou.framework.service.ICommonService;

/**
 *
 * ProPlcBzdw 表数据服务层接口
 *
 */
public interface IProPlcBzdwService extends ICommonService<ProPlcBzdw> {

    void selectDataGrid(PageInfo pageInfo) throws Exception;

    /**
     * 新增盾构机标准点位数据
     * @param proPlcBzdw 要保存的实体
     */
    String save(ProPlcBzdwDto proPlcBzdwDto,User user) throws Exception;

    /**
     * 通过集合删除盾构机标准点位数据
     * @param ids String字符串，中间用“,”间隔开
     */
    String del(String[] ids,User user) throws Exception;

    /**     * 修改盾构机标准点位数据
     * @param proPlcBzdw 要修改的实体
     */
    String update(ProPlcBzdwDto proPlcBzdwDto,User user) throws Exception;

   /**
     * 通过ID查找盾构机标准点位数据
     * @param id 数据ID
     */
    ProPlcBzdw findById(String id) throws Exception;

	/**
	 * 校验当前字段是否已存在
	 * @param proPlcBzdw 实体信息
	 * @param clumName 需校验的字段
	 * @return 若为null则不重复，若不为null则重复
	 */
	String checkClumIfexits(ProPlcBzdwDto proPlcBzdwDto, String[] clumNames) throws Exception;


}