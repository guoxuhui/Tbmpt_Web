package com.crfeb.tbmpt.dmcjjc.dd.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.framework.service.impl.SuperServiceImpl;
import com.crfeb.tbmpt.dmcjjc.dd.mapper.DmcjDDTypeMapper;
import com.crfeb.tbmpt.dmcjjc.dd.model.DdType;
import com.crfeb.tbmpt.dmcjjc.dd.service.IDmcjDDTypeService;

@Service
public class DmcjDDTypeServiceImpl extends SuperServiceImpl<DmcjDDTypeMapper, DdType> implements IDmcjDDTypeService {

	@Autowired
	private DmcjDDTypeMapper dDTypeMapper;
	
	/**
	 * 功能描述：查询所有基础数据分类信息。
	 * 	条件：无。
	 * 	排序：按基础数据分类的排序号。
	 * 	数据模型：基础数据dmcj_dd_type。
	 */
	
	public List<DdType> getAll() {
		return dDTypeMapper.selectAll();
	}

}
