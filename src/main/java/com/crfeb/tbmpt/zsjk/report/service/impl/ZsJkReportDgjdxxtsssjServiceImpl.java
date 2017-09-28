package com.crfeb.tbmpt.zsjk.report.service.impl;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.framework.service.impl.CommonServiceImpl;
import com.crfeb.tbmpt.commons.utils.StringUtils;
import com.crfeb.tbmpt.zsjk.report.mapper.ZsJkReportDgjdxxtsssjMapper;
import com.crfeb.tbmpt.zsjk.report.model.ZsJkReportDgjdxxtsssj;
import com.crfeb.tbmpt.zsjk.report.service.ZsJkReportDgjdxxtsssjService;


/**
 * <p>获取 掘进姿态:盾构机导向系统实时数据  ServiceImpl </p>
 * <p>系统：展示界面接口</p>
 * <p>日期：2017-03-08</p>
 * @version 1.0
 * @author wl_wpg
 */
@Service
public class ZsJkReportDgjdxxtsssjServiceImpl extends CommonServiceImpl<ZsJkReportDgjdxxtsssjMapper, ZsJkReportDgjdxxtsssj>  implements ZsJkReportDgjdxxtsssjService {

	@Autowired
	private ZsJkReportDgjdxxtsssjMapper zsJkReportDgjdxxtsssjMapper;

	/**
     * 获取 盾构机导向系统实时数据
     * @param xmId 项目Id
     * @param xlId 线路Id
     * @param dgjId 盾构机Id
     * @return 返回查询  盾构机导向系统实时数据对象
     */
	@Override
	public ZsJkReportDgjdxxtsssj inquireDgjdxxtsssjList(String xmId, String xlId, String dgjId) throws Exception {
		List<ZsJkReportDgjdxxtsssj> dgjdxxtsssjList=null;
		if(StringUtils.isNotBlank(xmId) && StringUtils.isNotBlank(xlId) && StringUtils.isNotBlank(dgjId)){
			Map<String, Object> condition = new HashMap<String, Object>();
		    condition.put("xmId", xmId);
   			condition.put("xlId", xlId);
   			condition.put("dgjId", dgjId);
   			dgjdxxtsssjList = zsJkReportDgjdxxtsssjMapper.selectDgjdxxtsssjList(condition);
   		}
		ZsJkReportDgjdxxtsssj sgjdxx = null;
		if(null!=dgjdxxtsssjList && dgjdxxtsssjList.size()!=0){
			sgjdxx = new ZsJkReportDgjdxxtsssj();
			sgjdxx = dgjdxxtsssjList.get(0);
		}
		return sgjdxx;
	}
	

}
