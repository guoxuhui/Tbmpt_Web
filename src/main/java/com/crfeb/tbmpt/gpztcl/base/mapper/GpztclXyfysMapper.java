package com.crfeb.tbmpt.gpztcl.base.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.mapper.CommonMapper;
import com.crfeb.tbmpt.gpztcl.base.model.GpztclXyfys;
import com.crfeb.tbmpt.gpztcl.base.model.dto.GpztclXyfysDto;

public interface GpztclXyfysMapper extends CommonMapper<GpztclXyfys>{
	/**
	 * 查询平曲线要素列表
	 */
	List<GpztclXyfysDto> selectXyfysMapper(@Param("xlId")String xlId);

}
