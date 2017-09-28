package com.crfeb.tbmpt.gczl.base.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.framework.service.impl.CommonServiceImpl;
import com.baomidou.mybatisplus.plugins.Page;
import com.crfeb.tbmpt.commons.utils.PageInfo;
import com.crfeb.tbmpt.gczl.base.mapper.GczlYdxjGPZLDDTypeMapper;
import com.crfeb.tbmpt.gczl.base.model.GczlYdxjGPZLDDType;
import com.crfeb.tbmpt.gczl.base.service.GczlYdxjGPZLDDTypeService;
@Service
public class GczlYdxjGPZLDDTypeServiceImpl extends CommonServiceImpl<GczlYdxjGPZLDDTypeMapper, GczlYdxjGPZLDDType> implements GczlYdxjGPZLDDTypeService{

	@Autowired
    private GczlYdxjGPZLDDTypeMapper gczlYdxjGPZLDDTypeMapper;

	@Override
	public List<GczlYdxjGPZLDDType> getAll() {
		return gczlYdxjGPZLDDTypeMapper.selectList(null);
	}

	@Override
	public void selectDataGrid(PageInfo pageInfo) {
		Page<GczlYdxjGPZLDDType> page = new Page<GczlYdxjGPZLDDType>(pageInfo.getNowpage(), pageInfo.getSize());
		Map<String, Object> condition = pageInfo.getCondition();
        List<GczlYdxjGPZLDDType> list = gczlYdxjGPZLDDTypeMapper.selectGczlYdxjGPZLDDTypeList(page,condition);
        pageInfo.setRows(list);
        pageInfo.setTotal(page.getTotal());
	}
	


}
