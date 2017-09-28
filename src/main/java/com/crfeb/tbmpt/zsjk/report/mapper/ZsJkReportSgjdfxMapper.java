package com.crfeb.tbmpt.zsjk.report.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.mapper.CommonMapper;
import com.crfeb.tbmpt.zsjk.report.model.ZsJkReportSgjdfx;

/**
 * <p>施工进度分析  Mapper() </p>
 * <p>系统：展示界面接口</p>
 * <p>日期：2017-03-07</p>
 * @version 1.0
 * @author wl_wpg
 */
public interface ZsJkReportSgjdfxMapper extends CommonMapper<ZsJkReportSgjdfx> {

	/**
     * 获取 施工进度分析集合
     * @param params 查询条件集合
     * @return 返回查询结果集合
     */
	public List<ZsJkReportSgjdfx> selectSgjdfxList(@Param("params") Map<String, Object> params);





}
