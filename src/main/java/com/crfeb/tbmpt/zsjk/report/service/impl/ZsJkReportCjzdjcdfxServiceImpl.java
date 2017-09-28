package com.crfeb.tbmpt.zsjk.report.service.impl;


import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.framework.service.impl.CommonServiceImpl;
import com.crfeb.tbmpt.zsjk.report.mapper.ZsJkReportCjzdjcdfxMapper;
import com.crfeb.tbmpt.zsjk.report.model.ZsJkReportCjzdjcdfx;
import com.crfeb.tbmpt.zsjk.report.model.dto.ZsJkReportCjzdjcdfxDto;
import com.crfeb.tbmpt.zsjk.report.service.ZsJkReportCjzdjcdfxService;


/**
 * <p>沉降最大监测点分析  ServiceImpl </p>
 * <p>系统：展示界面接口</p>
 * <p>日期：2017-03-07</p>
 * @version 1.0
 * @author wl_wpg
 */
@Service
public class ZsJkReportCjzdjcdfxServiceImpl extends CommonServiceImpl<ZsJkReportCjzdjcdfxMapper, ZsJkReportCjzdjcdfx>  implements ZsJkReportCjzdjcdfxService {

	@Autowired
	private ZsJkReportCjzdjcdfxMapper zsJkReportCjzdjcdfxMapper;
	
	@Override
	public List<ZsJkReportCjzdjcdfx> inquireCjzdjcdfxList(ZsJkReportCjzdjcdfxDto dto)throws Exception{
		List<ZsJkReportCjzdjcdfx> cjzdjcdfxList = new ArrayList<ZsJkReportCjzdjcdfx>();
		cjzdjcdfxList = zsJkReportCjzdjcdfxMapper.selectCjzdjcdfxList(dto);
		
		return cjzdjcdfxList;
	}

	

}
