package com.crfeb.tbmpt.gczl.base.service;

import java.util.List;

import com.baomidou.framework.service.ICommonService;
import com.crfeb.tbmpt.commons.utils.PageInfo;
import com.crfeb.tbmpt.gczl.base.model.GczlYdxjGPZLDDType;

public interface GczlYdxjGPZLDDTypeService  extends ICommonService<GczlYdxjGPZLDDType>{
	/**
	 * 获取所有分类
	 */
	List<GczlYdxjGPZLDDType> getAll();

	void selectDataGrid(PageInfo pageInfo);
}
