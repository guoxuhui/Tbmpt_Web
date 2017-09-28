package com.crfeb.tbmpt.sgkshgl.jcdzbgl.mapper;


import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.mapper.AutoMapper;
import com.baomidou.mybatisplus.mapper.CommonMapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.crfeb.tbmpt.dmcjjc.info.model.JcPoint;
import com.crfeb.tbmpt.sgkshgl.jcdzbgl.model.SgkshglJcdzbgl;
import com.crfeb.tbmpt.sgkshgl.jcdzbgl.model.dto.SgkshglJcdzbglDto;

/**
 *
 * JcpointNew 表数据库控制层接口
 *
 */
public interface SgkshglJcdzbglMapper extends CommonMapper<SgkshglJcdzbgl> {

	List<SgkshglJcdzbglDto> selectJcPointPage(Page<JcPoint> page, Map<String, Object> condition);
	
	List<SgkshglJcdzbglDto> selectAll();

	void insertJcdzb(SgkshglJcdzbgl sgkshglJcdzbgl);

	SgkshglJcdzbglDto selectByObjectid(@Param("objectid")String objectid);


}