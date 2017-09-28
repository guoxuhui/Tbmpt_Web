package com.crfeb.tbmpt.zsjk.report.service.impl;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.framework.service.impl.CommonServiceImpl;
import com.crfeb.tbmpt.commons.utils.StringUtils;
import com.crfeb.tbmpt.zsjk.report.mapper.ZsJkReportSgjdxxMapper;
import com.crfeb.tbmpt.zsjk.report.model.ZsJkReportSgjdxx;
import com.crfeb.tbmpt.zsjk.report.service.ZsJkReportSgjdxxService;


/**
 * <p>施工进度信息  ServiceImpl </p>
 * <p>系统：展示界面接口</p>
 * <p>日期：2017-03-08</p>
 * @version 1.0
 * @author wl_wpg
 */
@Service
public class ZsJkReportSgjdxxServiceImpl extends CommonServiceImpl<ZsJkReportSgjdxxMapper, ZsJkReportSgjdxx>  implements ZsJkReportSgjdxxService {

	@Autowired
	private ZsJkReportSgjdxxMapper zsJkReportSgjdxxMapper;

	/**
     * 获取 施工进度信息
     * @param xmId 项目Id
     * @param xlId 线路Id
     * @param dgjId 盾构机Id
     * @return 返回查询 施工进度信息对象
     */
	@Override
	public ZsJkReportSgjdxx inquireSgjdxxList(String xmId, String xlId, String dgjId) throws Exception {
		List<ZsJkReportSgjdxx> SgjdxxList = new ArrayList<ZsJkReportSgjdxx>();
		if(StringUtils.isNotBlank(xmId) &&StringUtils.isNotBlank(xlId) && StringUtils.isNotBlank(dgjId)){
		    Map<String, Object> condition = new HashMap<String, Object>();
   			condition.put("xmId", xmId);
   			condition.put("xlId", xlId);
   			condition.put("dgjId", dgjId);
   			SgjdxxList = zsJkReportSgjdxxMapper.selectSgjdxxList(condition);
   		}
		
		ZsJkReportSgjdxx sgjdxx =null;
		if(null!=SgjdxxList && SgjdxxList.size()!=0){
			sgjdxx = new ZsJkReportSgjdxx();
			sgjdxx = SgjdxxList.get(0);
		}
		return sgjdxx;
	}
	

}
