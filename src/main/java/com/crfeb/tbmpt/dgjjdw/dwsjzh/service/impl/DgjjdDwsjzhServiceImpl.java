package com.crfeb.tbmpt.dgjjdw.dwsjzh.service.impl;
 
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.crfeb.tbmpt.commons.utils.PageInfo; 
import com.crfeb.tbmpt.dgjjdw.dwsjzh.mapper.DgjjdDwsjzhMapper;
import com.crfeb.tbmpt.dgjjdw.dwsjzh.model.DgjjdDwsjzh;
import com.crfeb.tbmpt.dgjjdw.dwsjzh.service.DgjjdDwsjzhService; 
import com.baomidou.framework.service.impl.CommonServiceImpl; 
import com.baomidou.mybatisplus.plugins.Page;

/**
 *
 * ProPlcTbmdw 表数据服务层接口实现类
 *
 */
@Service
public class DgjjdDwsjzhServiceImpl extends CommonServiceImpl<DgjjdDwsjzhMapper, DgjjdDwsjzh> implements DgjjdDwsjzhService { 
	@Autowired
	private DgjjdDwsjzhMapper dgjjdDwsjzhMapper;
 
	
	/**
	 * 查询数据表格
	 */
	@Override
	public void selectDataGrid(PageInfo pageInfo) {
		Page<DgjjdDwsjzh> page = new Page<DgjjdDwsjzh>(pageInfo.getNowpage(), pageInfo.getSize());
	       Map<String, Object> condition = pageInfo.getCondition();
	       List<DgjjdDwsjzh> list = dgjjdDwsjzhMapper.selectDataGridList(page,condition,pageInfo.getSort(), pageInfo.getOrder());
	       pageInfo.setRows(list);
	       pageInfo.setTotal(page.getTotal());
		
	}


	@Override
	public List<DgjjdDwsjzh> getDwsjzhList(String dgjname,String dwname) {
		// TODO Auto-generated method stub
         
		  List<DgjjdDwsjzh> list = dgjjdDwsjzhMapper.selectList(dgjname,dwname);
		return list;
	}
	 
}