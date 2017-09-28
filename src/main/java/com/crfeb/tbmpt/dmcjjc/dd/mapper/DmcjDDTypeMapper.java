package com.crfeb.tbmpt.dmcjjc.dd.mapper;

import java.util.List;

import com.baomidou.mybatisplus.mapper.AutoMapper;
import com.crfeb.tbmpt.dmcjjc.dd.model.DdType;

/**
 *
 * dmcj_dd_Type 表数据库控制层接口
 *
 */
public interface DmcjDDTypeMapper extends AutoMapper<DdType> {
	List<DdType> selectAll();
	
}