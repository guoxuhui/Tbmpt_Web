package com.crfeb.tbmpt.zsjk.report.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.mapper.CommonMapper;
import com.crfeb.tbmpt.zsjk.report.model.ZsJkReportTyyctj;
import com.crfeb.tbmpt.zsjk.report.model.dto.ZsJkReportGxfxDto;
import com.crfeb.tbmpt.zsjk.report.model.dto.ZsJkReportTyyctjDto;

public interface ZsJkReportTyyctjMapper extends CommonMapper<ZsJkReportTyyctj>{
	
	List<ZsJkReportTyyctjDto> selectZsJkReportTyyctjListByMap(@Param("condition")Map<String, Object> condition) throws Exception;

}
