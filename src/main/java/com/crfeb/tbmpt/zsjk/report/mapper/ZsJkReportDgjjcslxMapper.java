package com.crfeb.tbmpt.zsjk.report.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.mapper.CommonMapper;
import com.crfeb.tbmpt.zsjk.report.model.ZsJkReportDgjjcsfx;
import com.crfeb.tbmpt.zsjk.report.model.ZsJkReportDgjjcslx;
import com.crfeb.tbmpt.zsjk.report.model.dto.ZsJkReportDgjjcsfxDto;
import com.crfeb.tbmpt.zsjk.report.model.dto.ZsJkReportDgjjcslxDto;

public interface ZsJkReportDgjjcslxMapper extends CommonMapper<ZsJkReportDgjjcslx>{
	List<ZsJkReportDgjjcslxDto> selectZsJkReportDgjjcslxListByMap() throws Exception;
}
