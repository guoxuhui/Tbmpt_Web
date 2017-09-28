package com.crfeb.tbmpt.zsjk.report.service;

import java.util.List;

import com.baomidou.framework.service.ICommonService;
import com.crfeb.tbmpt.zsjk.report.model.ZsJkReportDgjjcsfx;
import com.crfeb.tbmpt.zsjk.report.model.ZsJkReportTyyctj;
import com.crfeb.tbmpt.zsjk.report.model.dto.ZsJkReportTyyctjDto;

public interface ZsJkReportTyyctjService extends ICommonService<ZsJkReportTyyctj>{

	public List<ZsJkReportTyyctjDto> tyyctj(String xmId, String kssj, String jssj) throws Exception;

}
