package com.crfeb.tbmpt.gczl.base.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.mapper.CommonMapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.crfeb.tbmpt.gczl.base.model.GczlYdxjGPZLDDType;

/**
 * 盾构施工质量数据分类<br>
 * @author wangbin
 *
 */
public interface GczlYdxjGPZLDDTypeMapper extends CommonMapper<GczlYdxjGPZLDDType> {

	List<GczlYdxjGPZLDDType> selectGczlYdxjGPZLDDTypeList(Page<GczlYdxjGPZLDDType> page,@Param("condition")Map<String, Object> condition);
	

	
	
}
