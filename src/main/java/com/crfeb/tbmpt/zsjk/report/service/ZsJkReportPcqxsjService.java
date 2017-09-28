package com.crfeb.tbmpt.zsjk.report.service;


import java.util.List;

import com.baomidou.framework.service.ICommonService;
import com.crfeb.tbmpt.zsjk.report.model.ZsJkReportPcqxsj;

/**
 * <p>掘进姿态:偏差曲线数据   Service</p>
 * <p>系统：展示界面接口</p>
 * <p>日期：2017-03-09</p>
 * @version 1.0
 * @author wl_wpg
 */
public interface ZsJkReportPcqxsjService extends ICommonService<ZsJkReportPcqxsj>{

	/**
	 * 偏差曲线数据
	 * @param xmId 项目id
	 * @param xlId 线路Id
	 * @param dgjId 盾构机Id
	 * @param kshs 开始环数
	 * @param jshs 结束环数
	 * @return 结果集合
	 */
	List<ZsJkReportPcqxsj> inquirePcqxsjList(String xmId,String xlId,String dgjId,String kshs,String jshs);



}
