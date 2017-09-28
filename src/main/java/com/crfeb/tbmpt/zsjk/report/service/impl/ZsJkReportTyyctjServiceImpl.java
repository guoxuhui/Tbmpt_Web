package com.crfeb.tbmpt.zsjk.report.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.framework.service.impl.CommonServiceImpl;
import com.crfeb.tbmpt.commons.utils.StringUtils;
import com.crfeb.tbmpt.zsjk.report.mapper.ZsJkReportGxfxMapper;
import com.crfeb.tbmpt.zsjk.report.mapper.ZsJkReportTyyctjMapper;
import com.crfeb.tbmpt.zsjk.report.model.ZsJkReportGxfx;
import com.crfeb.tbmpt.zsjk.report.model.ZsJkReportTyyctj;
import com.crfeb.tbmpt.zsjk.report.model.dto.ZsJkReportGxfxDto;
import com.crfeb.tbmpt.zsjk.report.model.dto.ZsJkReportTyyctjDto;
import com.crfeb.tbmpt.zsjk.report.service.ZsJkReportGxfxService;
import com.crfeb.tbmpt.zsjk.report.service.ZsJkReportTyyctjService;

/**
 * @author wl_zjh
 *	土压异常统计
 */
@Service
public class ZsJkReportTyyctjServiceImpl extends CommonServiceImpl<ZsJkReportTyyctjMapper, ZsJkReportTyyctj> implements ZsJkReportTyyctjService{
	
	@Autowired
    private ZsJkReportTyyctjMapper zsJkReportTyyctjMapper;
	
	/**
	 * @param xmId 项目ID
	 * @param kssj 开始时间
	 * @param jssj 结束时间
	 * @return list 集合
	 */
	public List<ZsJkReportTyyctjDto> tyyctj(String xmId,String kssj,String jssj) throws Exception {
		Map<String,Object>  columnMap = new HashMap<String,Object>();
		if(StringUtils.isNotBlank(xmId)){
			columnMap.put("xmId", xmId);
		}
		if(StringUtils.isNotBlank(kssj)){
			columnMap.put("kssj", kssj);
		}
		if(StringUtils.isNotBlank(jssj)){
			columnMap.put("jssj", jssj);
		}
		List<ZsJkReportTyyctjDto> list= zsJkReportTyyctjMapper.selectZsJkReportTyyctjListByMap(columnMap);
		return list;
	}

}
