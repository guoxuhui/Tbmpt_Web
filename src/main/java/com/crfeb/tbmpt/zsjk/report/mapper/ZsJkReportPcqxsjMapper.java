package com.crfeb.tbmpt.zsjk.report.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.mapper.CommonMapper;
import com.crfeb.tbmpt.zsjk.report.model.ZsJkReportPcqxsj;
/**
 * <p>掘进姿态:偏差曲线数据  Mapper() </p>
 * <p>系统：展示界面接口</p>
 * <p>日期：2017-03-09</p>
 * @version 1.0
 * @author wl_wpg
 */
public interface ZsJkReportPcqxsjMapper extends CommonMapper<ZsJkReportPcqxsj> {

	/**
     * 偏差曲线数据集合
     * @param params 查询条件集合
     * @return 返回查询结果集合
     */
	public List<ZsJkReportPcqxsj> selectPcqxsjList(@Param("params") Map<String, Object> params);





}
