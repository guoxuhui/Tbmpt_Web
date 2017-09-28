package com.crfeb.tbmpt.zsjk.report.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.mapper.CommonMapper;
import com.crfeb.tbmpt.dmcjjc.bg.model.JcbgDetail;
import com.crfeb.tbmpt.zsjk.report.model.ZsJkReportDgjjcsfx;
import com.crfeb.tbmpt.zsjk.report.model.dto.ZsJkReportDgjjcsfxDto;


public interface ZsJkReportDgjjcsfxMapper extends CommonMapper<ZsJkReportDgjjcsfx>{
	List<ZsJkReportDgjjcsfxDto> selectZsJkReportDgjjcsfxListByMap(@Param("condition")Map<String, Object> condition,@Param("list")List<String> list) throws Exception;
}
