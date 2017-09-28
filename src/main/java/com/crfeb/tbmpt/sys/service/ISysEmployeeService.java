package com.crfeb.tbmpt.sys.service;

import com.crfeb.tbmpt.commons.utils.PageInfo;
import com.crfeb.tbmpt.sys.model.SysEmployee;
import com.crfeb.tbmpt.sys.model.vo.SysEmployeeVo;

import java.util.List;

import com.baomidou.framework.service.ICommonService;

/**
 *
 * SysEmployee 表数据服务层接口
 *
 */
public interface ISysEmployeeService extends ICommonService<SysEmployee> {

	void selectDataGrid(PageInfo pageInfo);
	List<SysEmployeeVo> selectAll();
	int edit(SysEmployee emp);
}