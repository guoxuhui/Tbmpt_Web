package com.crfeb.tbmpt.zsjk.report.service;



import com.baomidou.framework.service.ICommonService;
import com.crfeb.tbmpt.zsjk.report.model.ZsJkReportSgjdxx;



/**
 * <p>施工进度信息  Service</p>
 * <p>系统：展示界面接口</p>
 * <p>日期：2017-03-08</p>
 * @version 1.0
 * @author wl_wpg
 */
public interface ZsJkReportSgjdxxService extends ICommonService<ZsJkReportSgjdxx>{

	/**
     * 获取 施工进度信息
     * @param xmId 项目Id
     * @param xlId 线路Id
     * @param dgjId 盾构机Id
     * @return 返回查询 施工进度信息对象
     */
	public ZsJkReportSgjdxx inquireSgjdxxList(String xmId, String xlId, String dgjId)throws Exception;
	
	
}
