package com.crfeb.tbmpt.dmcjjc.dd.service;

import java.util.List;

import com.crfeb.tbmpt.dmcjjc.dd.model.DdType;

/**
 * 3.1.1 基础数据分类
 * @author Administrator
 *
 */
public interface IDmcjDDTypeService {
	//查询所有基础数据分类信息
	public List<DdType> getAll();
}
