package com.crfeb.tbmpt.zsjk.report.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.mapper.CommonMapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;
import com.crfeb.tbmpt.zsjk.report.model.ZsJkReportDgjkyczt;

/**
 * <p>盾构监控异常状态  Mapper() </p>
 * <p>系统：展示界面接口</p>
 * <p>日期：2017-03-07</p>
 * @version 1.0
 * @author wl_wpg
 */
public interface ZsJkReportDgjkycztMapper extends CommonMapper<ZsJkReportDgjkyczt> {

	/**
     * 获取 盾构监控异常状态集合
     * @param dto 工具类 
     * @return 返回查询结果集合
     */
	public List<ZsJkReportDgjkyczt> selectDgjkycztList(Pagination page,@Param("params") Map<String, Object> params, @Param("sort") String sort, @Param("order") String order);
}
