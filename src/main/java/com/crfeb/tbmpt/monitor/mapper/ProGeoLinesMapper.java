package com.crfeb.tbmpt.monitor.mapper;

import com.crfeb.tbmpt.gpztcl.base.model.dto.GpztclXyfysDto;
import com.crfeb.tbmpt.monitor.model.GeoLineModel;
import com.crfeb.tbmpt.monitor.model.ProGeoLines;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.mapper.AutoMapper;
import com.baomidou.mybatisplus.mapper.CommonMapper;

/**
 *
 * ProGeoLines 表数据库控制层接口
 *
 */
public interface ProGeoLinesMapper extends CommonMapper<ProGeoLines> {

	List<GeoLineModel> selectGeoLineListByLId(@Param("line_id")String line_id);
	List<GeoLineModel> selectGeoLineListByPId(@Param("pro_id")String pro_id);
}