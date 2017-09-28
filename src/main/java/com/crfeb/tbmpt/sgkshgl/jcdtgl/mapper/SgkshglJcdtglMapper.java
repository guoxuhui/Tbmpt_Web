package com.crfeb.tbmpt.sgkshgl.jcdtgl.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.mapper.CommonMapper;
import com.crfeb.tbmpt.sgkshgl.jcdtgl.model.SgkshglJcdtgl;
import com.crfeb.tbmpt.sgkshgl.jcdtgl.model.dto.SgkshglJcdtglDto;

/**
 * <p>基础底图管理 Mapper接口</p>
 * <p>模块：施工可视化管理</p>
 * <p>日期：2017-04-12</p>
 * @version 1.0
 * @author wl_wpg
 */
public interface SgkshglJcdtglMapper extends CommonMapper<SgkshglJcdtgl> {
	
	List<SgkshglJcdtglDto> selectByrefId(@Param("refId") String refId);
 
	List<SgkshglJcdtglDto> selectByIdList(@Param("idList") List<String> idList);
}