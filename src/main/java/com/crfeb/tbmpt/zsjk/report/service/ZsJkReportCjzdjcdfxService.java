package com.crfeb.tbmpt.zsjk.report.service;

import java.util.List;

import com.baomidou.framework.service.ICommonService;
import com.crfeb.tbmpt.zsjk.report.model.ZsJkReportCjzdjcdfx;
import com.crfeb.tbmpt.zsjk.report.model.dto.ZsJkReportCjzdjcdfxDto;

/**
 * <p>沉降最大监测点分析  Service</p>
 * <p>系统：展示界面接口</p>
 * <p>日期：2017-03-07</p>
 * @version 1.0
 * @author wl_wpg
 */
public interface ZsJkReportCjzdjcdfxService extends ICommonService<ZsJkReportCjzdjcdfx>{

	
	/**
     * 获取 沉降最大监测点分析集合
     * @param dto 工具类 
     * @return 返回查询结果集合
     */
	public List<ZsJkReportCjzdjcdfx> inquireCjzdjcdfxList(ZsJkReportCjzdjcdfxDto dto)throws Exception;


}
