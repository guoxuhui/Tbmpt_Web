package com.crfeb.tbmpt.zsjk.report.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.mapper.CommonMapper;
import com.crfeb.tbmpt.zsjk.report.model.ZsJkReportCjzdjcdfx;
import com.crfeb.tbmpt.zsjk.report.model.dto.ZsJkReportCjzdjcdfxDto;

/**
 * <p>沉降最大监测点分析  Mapper() </p>
 * <p>系统：展示界面接口</p>
 * <p>日期：2017-03-07</p>
 * @version 1.0
 * @author wl_wpg
 */
public interface ZsJkReportCjzdjcdfxMapper extends CommonMapper<ZsJkReportCjzdjcdfx> {

	/**
     * 获取 沉降最大监测点分析集合
     * @param dto 工具类 
     * @return 返回查询结果集合
     */
	public List<ZsJkReportCjzdjcdfx> selectCjzdjcdfxList(@Param("dto")ZsJkReportCjzdjcdfxDto dto);



}
