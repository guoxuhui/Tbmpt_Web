package com.crfeb.tbmpt.zsjk.report.service;


import java.util.List;

import com.baomidou.framework.service.ICommonService;
import com.crfeb.tbmpt.zsjk.report.model.ZsJkReportSgjdfx;

/**
 * <p>施工进度分析  Service</p>
 * <p>系统：展示界面接口</p>
 * <p>日期：2017-03-08</p>
 * @version 1.0
 * @author wl_wpg
 */
public interface ZsJkReportSgjdfxService extends ICommonService<ZsJkReportSgjdfx>{

	/**
	 * 施工进度发现
	 * @param xmId 项目id
	 * @param xlId 线路Id
	 * @param dgjId 盾构机Id
	 * @param ksrq 开始日期
	 * @param jsrq 结束日期
	 * @return 结果集合
	 */
	List<ZsJkReportSgjdfx> inquireSgjdfxList(String xmId,String xlId,String dgjId,String ksrq,String jsrq);



}
