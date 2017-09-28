package com.crfeb.tbmpt.zsjk.report.service.impl;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.framework.service.impl.CommonServiceImpl;
import com.crfeb.tbmpt.commons.utils.StringUtils;
import com.crfeb.tbmpt.zsjk.report.mapper.ZsJkReportPcqxsjMapper;
import com.crfeb.tbmpt.zsjk.report.model.ZsJkReportPcqxsj;
import com.crfeb.tbmpt.zsjk.report.service.ZsJkReportPcqxsjService;


/**
 * <p>掘进姿态:偏差曲线数据  ServiceImpl </p>
 * <p>系统：展示界面接口</p>
 * <p>日期：2017-03-09</p>
 * @version 1.0
 * @author wl_wpg
 */
@Service
public class ZsJkReportPcqxsjServiceImpl extends CommonServiceImpl<ZsJkReportPcqxsjMapper, ZsJkReportPcqxsj>  implements ZsJkReportPcqxsjService {

	@Autowired
	private ZsJkReportPcqxsjMapper zsJkReportPcqxsjMapper;
	/**
	 * 偏差曲线数据
	 * @param xmId 项目id
	 * @param xlId 线路Id
	 * @param dgjId 盾构机Id
	 * @param ksrq 开始日期
	 * @param jsrq 结束日期
	 * @return 结果集合
	 */
	@Override
	public List<ZsJkReportPcqxsj> inquirePcqxsjList(String xmId, String xlId, String dgjId, String kshs, String jshs) {
        
		List<ZsJkReportPcqxsj>  sgjdfxList = new ArrayList<ZsJkReportPcqxsj>();
		
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
			if(StringUtils.isNotBlank(kshs)){
				columnMap.put("kshs", kshs);
			}
            if(StringUtils.isNotBlank(jshs)){
            	columnMap.put("jshs", jshs);
			}
            sgjdfxList = zsJkReportPcqxsjMapper.selectPcqxsjList(columnMap);
		
		return sgjdfxList;
	}

	

}
