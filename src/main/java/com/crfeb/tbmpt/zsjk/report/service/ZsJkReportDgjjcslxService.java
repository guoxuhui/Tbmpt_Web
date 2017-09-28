package com.crfeb.tbmpt.zsjk.report.service;

import java.util.List;

import com.baomidou.framework.service.ICommonService;
import com.crfeb.tbmpt.zsjk.report.model.ZsJkReportDgjjcsfx;
import com.crfeb.tbmpt.zsjk.report.model.ZsJkReportDgjjcslx;
import com.crfeb.tbmpt.zsjk.report.model.dto.ZsJkReportDgjjcslxDto;

public interface ZsJkReportDgjjcslxService extends ICommonService<ZsJkReportDgjjcslx>{
	public List<ZsJkReportDgjjcslxDto> dgjjcslx() throws Exception;

}
