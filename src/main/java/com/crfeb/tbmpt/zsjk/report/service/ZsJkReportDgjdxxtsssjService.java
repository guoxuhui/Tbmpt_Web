package com.crfeb.tbmpt.zsjk.report.service;



import com.baomidou.framework.service.ICommonService;
import com.crfeb.tbmpt.zsjk.report.model.ZsJkReportDgjdxxtsssj;



/**
 * <p>获取 掘进姿态:盾构机导向系统实时数据  Service</p>
 * <p>系统：展示界面接口</p>
 * <p>日期：2017-03-08</p>
 * @version 1.0
 * @author wl_wpg
 */
public interface ZsJkReportDgjdxxtsssjService extends ICommonService<ZsJkReportDgjdxxtsssj>{

	/**
     * 获取 盾构机导向系统实时数据
     * @param xmId 项目Id
     * @param xlId 线路Id
     * @param dgjId 盾构机Id
     * @return 返回查询 盾构机导向系统实时数据 信息对象
     */
	public ZsJkReportDgjdxxtsssj inquireDgjdxxtsssjList(String xmId, String xlId, String dgjId)throws Exception;
	
	
}
