package com.crfeb.tbmpt.dgjjdw.tbmdw.mapper;

import com.crfeb.tbmpt.dgjjdw.tbmdw.model.DgjjPlcTbmdw;
import com.crfeb.tbmpt.dgjjdw.tbmdw.model.dto.DgjjPlcTbmdwDto;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.mapper.AutoMapper;
import com.baomidou.mybatisplus.mapper.CommonMapper;
import com.baomidou.mybatisplus.plugins.Page;

/**
 *
 * ProPlcTbmdw 表数据库控制层接口
 *
 */
public interface DgjjPlcTbmdwMapper extends CommonMapper<DgjjPlcTbmdw> {

	List<DgjjPlcTbmdw> selectDataGridList(Page<DgjjPlcTbmdw> page, @Param("condition")Map<String, Object> condition);

	List<DgjjPlcTbmdwDto> selectTbmCode(@Param("tbmcode")String tbmcode);

	List<DgjjPlcTbmdwDto> selectTbmid(@Param("tbmid")String tbmid);
}