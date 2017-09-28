package com.crfeb.tbmpt.zsjk.report.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.mapper.CommonMapper;
import com.crfeb.tbmpt.zsjk.report.model.ZsJkReportSgjdxx;

/**
 * <p>施工进度信息  Mapper() </p>
 * <p>系统：展示界面接口</p>
 * <p>日期：2017-03-08</p>
 * @version 1.0
 * @author wl_wpg
 */
public interface ZsJkReportSgjdxxMapper extends CommonMapper<ZsJkReportSgjdxx> {

	/**
     * 获取 施工进度信息集合
     * @param params 参数集合
     * @return 返回查询结果集合
     */
	public List<ZsJkReportSgjdxx> selectSgjdxxList(@Param("params") Map<String, Object> params);





}
