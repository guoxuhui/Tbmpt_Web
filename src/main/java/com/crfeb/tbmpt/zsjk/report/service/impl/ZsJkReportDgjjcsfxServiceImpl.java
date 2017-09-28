package com.crfeb.tbmpt.zsjk.report.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.framework.service.impl.CommonServiceImpl;
import com.crfeb.tbmpt.commons.utils.StringUtils;
import com.crfeb.tbmpt.zsjk.report.mapper.ZsJkReportDgjjcsfxMapper;
import com.crfeb.tbmpt.zsjk.report.mapper.ZsJkReportGxfxMapper;
import com.crfeb.tbmpt.zsjk.report.model.ZsJkReportDgjjcsfx;
import com.crfeb.tbmpt.zsjk.report.model.ZsJkReportGxfx;
import com.crfeb.tbmpt.zsjk.report.model.dto.ZsJkReportDgjjcsfxDto;
import com.crfeb.tbmpt.zsjk.report.model.dto.ZsJkReportGxfxDto;
import com.crfeb.tbmpt.zsjk.report.service.ZsJkReportDgjjcsfxService;
import com.crfeb.tbmpt.zsjk.report.service.ZsJkReportGxfxService;

/**
 * @author wl_zjh
 * 盾构掘进参数分析
 *
 */
@Service
public class ZsJkReportDgjjcsfxServiceImpl extends CommonServiceImpl<ZsJkReportDgjjcsfxMapper, ZsJkReportDgjjcsfx> implements ZsJkReportDgjjcsfxService{
	@Autowired
    private ZsJkReportDgjjcsfxMapper zsJkReportDgjjcsfxMapper;
	
	/**
	 * @param xmId 项目ID
	 * @param dgjId 盾构机ID
	 * @param kshs 开始环数
	 * @param jshs 结束环数
	 */
	@Override
	public List<ZsJkReportDgjjcsfxDto> dgjjcsfx(String xmId,String dgjId,String kshs,String jshs,List<String> typeId,String typeName) throws Exception {
		Map<String,Object>  columnMap = new HashMap<String,Object>();
		if(StringUtils.isNotBlank(xmId)){
			columnMap.put("xmId", xmId);
		}
		if(StringUtils.isNotBlank(dgjId)){
			columnMap.put("dgjId", dgjId);
		}
		if(StringUtils.isNotBlank(kshs)){
			int k=Integer.valueOf(kshs);
			columnMap.put("kshs", k);
		}
		if(StringUtils.isNotBlank(jshs)){
			int j=Integer.valueOf(jshs);
			columnMap.put("jshs", j);
		}
		
		if(StringUtils.isNotBlank(typeName)){
			columnMap.put("typeName", typeName);
		}
		List<ZsJkReportDgjjcsfxDto> list= zsJkReportDgjjcsfxMapper.selectZsJkReportDgjjcsfxListByMap(columnMap,typeId);
		return list;
	}
	
	
}
