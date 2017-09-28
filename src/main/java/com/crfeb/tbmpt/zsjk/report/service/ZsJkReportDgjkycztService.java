package com.crfeb.tbmpt.zsjk.report.service;

import java.util.List;

import com.baomidou.framework.service.ICommonService;
import com.crfeb.tbmpt.commons.utils.PageInfo;
import com.crfeb.tbmpt.zsjk.report.model.ZsJkReportDgjkyczt;

/**
 * <p>盾构监控异常状态  Service</p>
 * <p>系统：展示界面接口</p>
 * <p>日期：2017-03-07</p>
 * @version 1.0
 * @author wl_wpg
 */
public interface ZsJkReportDgjkycztService extends ICommonService<ZsJkReportDgjkyczt>{

	void inquireDgjkycztDataPage(PageInfo pageInfo,List<String> typeList);
}
