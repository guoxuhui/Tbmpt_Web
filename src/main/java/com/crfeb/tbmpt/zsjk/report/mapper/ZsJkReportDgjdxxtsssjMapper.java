package com.crfeb.tbmpt.zsjk.report.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.mapper.CommonMapper;
import com.crfeb.tbmpt.zsjk.report.model.ZsJkReportDgjdxxtsssj;

/**
 * <p>掘进姿态:盾构机导向系统实时数据  Mapper() </p>
 * <p>系统：展示界面接口</p>
 * <p>日期：2017-03-08</p>
 * @version 1.0
 * @author wl_wpg
 */
public interface ZsJkReportDgjdxxtsssjMapper extends CommonMapper<ZsJkReportDgjdxxtsssj> {

	/**
     * 获取 盾构机导向系统实时数据集合
     * @param params 查询条件集合
     * @return 返回查询结果集合
     */
	public List<ZsJkReportDgjdxxtsssj> selectDgjdxxtsssjList(@Param("params") Map<String, Object> params);





}
