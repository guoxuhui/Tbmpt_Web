package com.crfeb.tbmpt.zsjk.report.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.framework.service.impl.CommonServiceImpl;
import com.crfeb.tbmpt.zsjk.report.mapper.ZsJkReportDgjjcsfxMapper;
import com.crfeb.tbmpt.zsjk.report.mapper.ZsJkReportDgjjcslxMapper;
import com.crfeb.tbmpt.zsjk.report.model.ZsJkReportDgjjcsfx;
import com.crfeb.tbmpt.zsjk.report.model.ZsJkReportDgjjcslx;
import com.crfeb.tbmpt.zsjk.report.model.dto.ZsJkReportDgjjcsfxDto;
import com.crfeb.tbmpt.zsjk.report.model.dto.ZsJkReportDgjjcslxDto;
import com.crfeb.tbmpt.zsjk.report.service.ZsJkReportDgjjcsfxService;
import com.crfeb.tbmpt.zsjk.report.service.ZsJkReportDgjjcslxService;

/**
 * @author wl_zjh
 * 盾构掘进参数类型
 *
 */
@Service
public class ZsJkReportDgjjcslxServiceImpl extends CommonServiceImpl<ZsJkReportDgjjcslxMapper, ZsJkReportDgjjcslx> implements ZsJkReportDgjjcslxService{

	@Autowired
    private ZsJkReportDgjjcslxMapper zsJkReportDgjjcslxMapper;
	@Override
	public List<ZsJkReportDgjjcslxDto> dgjjcslx() throws Exception {
	List<ZsJkReportDgjjcslxDto> list= zsJkReportDgjjcslxMapper.selectZsJkReportDgjjcslxListByMap();
	return list;
}
}