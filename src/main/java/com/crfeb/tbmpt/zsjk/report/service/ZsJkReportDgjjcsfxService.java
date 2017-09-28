package com.crfeb.tbmpt.zsjk.report.service;

import java.util.List;

import com.baomidou.framework.service.ICommonService;
import com.crfeb.tbmpt.zsjk.report.model.ZsJkReportDgjjcsfx;
import com.crfeb.tbmpt.zsjk.report.model.ZsJkReportGxfx;
import com.crfeb.tbmpt.zsjk.report.model.dto.ZsJkReportDgjjcsfxDto;


public interface ZsJkReportDgjjcsfxService extends ICommonService<ZsJkReportDgjjcsfx>{

	public List<ZsJkReportDgjjcsfxDto> dgjjcsfx(String xmId, String dgjId, String kshs, String jshs, List<String> typeId,
			String typeName) throws Exception;
}
