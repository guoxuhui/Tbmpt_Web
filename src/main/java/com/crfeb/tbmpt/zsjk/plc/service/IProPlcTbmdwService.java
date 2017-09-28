package com.crfeb.tbmpt.zsjk.plc.service;

import com.crfeb.tbmpt.commons.utils.PageInfo;
import com.crfeb.tbmpt.sys.model.User;
import com.crfeb.tbmpt.zsjk.plc.model.ProPlcTbmdw;
import com.crfeb.tbmpt.zsjk.plc.model.dto.ProPlcTbmdwDto;
import com.baomidou.framework.service.ICommonService;

/**
 *
 * ProPlcTbmdw 表数据服务层接口
 *
 */
public interface IProPlcTbmdwService extends ICommonService<ProPlcTbmdw> {


    void selectDataGrid(PageInfo pageInfo) throws Exception;

    /**
     * 新增点位配置表
     * @param proPlcTbmdw 要保存的实体
     */
    String save(ProPlcTbmdwDto proPlcTbmdwDto,User user) throws Exception;

    /**
     * 通过集合删除点位配置表
     * @param ids String字符串，中间用“,”间隔开
     */
    String del(String[] ids,User user) throws Exception;

    /**     * 修改点位配置表
     * @param proPlcTbmdw 要修改的实体
     */
    String update(ProPlcTbmdwDto proPlcTbmdwDto,User user) throws Exception;

   /**
     * 通过ID查找点位配置表
     * @param id 数据ID
     */
    ProPlcTbmdw findById(String id) throws Exception;

	/**
	 * 校验当前字段是否已存在
	 * @param proPlcTbmdw 实体信息
	 * @param clumName 需校验的字段
	 * @return 若为null则不重复，若不为null则重复
	 */
	String checkClumIfexits(ProPlcTbmdwDto proPlcTbmdwDto, String[] clumNames) throws Exception;

	
}