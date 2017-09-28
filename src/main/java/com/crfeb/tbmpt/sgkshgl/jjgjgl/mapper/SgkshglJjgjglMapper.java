package com.crfeb.tbmpt.sgkshgl.jjgjgl.mapper;

import com.crfeb.tbmpt.monitor.model.ProGeoLines;
import com.crfeb.tbmpt.sgkshgl.jjgjgl.model.SgkshglJjgjgl;
import com.crfeb.tbmpt.sgkshgl.jjgjgl.model.dto.SgkshglJjgjglDto;
import com.crfeb.tbmpt.sys.model.SysEmployee;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.mapper.AutoMapper;
import com.baomidou.mybatisplus.mapper.CommonMapper;

/**
 *
 * ProGeoLines 表数据库控制层接口
 *
 */
public interface SgkshglJjgjglMapper extends CommonMapper<SgkshglJjgjgl> {

	List<SgkshglJjgjglDto> selectBylineId(@Param("lineId")String lineId);


}