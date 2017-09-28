package com.crfeb.tbmpt.sgkshgl.jcdtgl.service;

import java.util.List;

import com.baomidou.framework.service.ICommonService;
import com.crfeb.tbmpt.sgkshgl.jcdtgl.model.SgkshglJcdtgl;
import com.crfeb.tbmpt.sgkshgl.jcdtgl.model.dto.SgkshglJcdtglDto;
import com.crfeb.tbmpt.sys.model.User;

/**
 * <p>基础底图管理 Service接口</p>
 * <p>模块：施工可视化管理</p>
 * <p>日期：2017-04-12</p>
 * @version 1.0
 * @author wl_wpg
 */
public interface ISgkshglJcdtglService extends ICommonService<SgkshglJcdtgl> {

	/***
     * 根据用户查询列表
     */
	List<SgkshglJcdtglDto> selectDataGrid(User user);
	/***
     * 根据关联Id查询列表
     */
	List<SgkshglJcdtglDto> selectDataGridByRefId(String refId);
	 
	String addSave(SgkshglJcdtglDto dto);
	String edit(SgkshglJcdtglDto dto);

}