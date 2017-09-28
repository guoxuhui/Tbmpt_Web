package com.crfeb.tbmpt.zsjk.report.service.impl;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.framework.service.impl.CommonServiceImpl;
import com.crfeb.tbmpt.commons.utils.StringUtils;
import com.crfeb.tbmpt.zsjk.report.mapper.ZsJkReportSgjdfxMapper;
import com.crfeb.tbmpt.zsjk.report.model.ZsJkReportSgjdfx;
import com.crfeb.tbmpt.zsjk.report.service.ZsJkReportSgjdfxService;


/**
 * <p>施工进度分析  ServiceImpl </p>
 * <p>系统：展示界面接口</p>
 * <p>日期：2017-03-07</p>
 * @version 1.0
 * @author wl_wpg
 */
@Service
public class ZsJkReportSgjdfxServiceImpl extends CommonServiceImpl<ZsJkReportSgjdfxMapper, ZsJkReportSgjdfx>  implements ZsJkReportSgjdfxService {

	@Autowired
	private ZsJkReportSgjdfxMapper zsJkReportSgjdfxMapper;
	/**
	 * 施工进度分析
	 * @param xmId 项目id
	 * @param xlId 线路Id
	 * @param dgjId 盾构机Id
	 * @param ksrq 开始日期
	 * @param jsrq 结束日期
	 * @return 结果集合
	 */
	@Override
	public List<ZsJkReportSgjdfx> inquireSgjdfxList(String xmId, String xlId, String dgjId, String ksrq, String jsrq) {
        
		List<ZsJkReportSgjdfx>  sgjdfxList = new ArrayList<ZsJkReportSgjdfx>();
		Map<String,Object> columnMap = new HashMap<String,Object>();
		if(StringUtils.isNotBlank(xmId)){
			columnMap.put("xmId", xmId);
		}
        if(StringUtils.isNotBlank(xlId)){
			columnMap.put("xlId", xlId);
		}
		if(StringUtils.isNotBlank(dgjId)){
			columnMap.put("dgjId", dgjId);
		}
		if(StringUtils.isNotBlank(ksrq)){
			columnMap.put("ksrq", ksrq);
		}
        if(StringUtils.isNotBlank(jsrq)){
        	columnMap.put("jsrq", jsrq);
		}
        sgjdfxList = zsJkReportSgjdfxMapper.selectSgjdfxList(columnMap);
		return sgjdfxList;
	}

	

}
