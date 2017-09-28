package com.crfeb.tbmpt.risk.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.mapper.CommonMapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;
import com.crfeb.tbmpt.risk.model.RiskLevel;
import com.crfeb.tbmpt.risk.model.vo.RiskLevelVo;

/**
 *
 * RiskLevel 表数据库控制层接口
 *
 */
public interface RiskLevelMapper extends CommonMapper<RiskLevel> {
	/**
	 * 查询风控级别列表
	 * @param page
	 * @param params
	 * @param sort
	 * @param order
	 * @return
	 */
	List<RiskLevelVo> selectVoList(Pagination page,@Param("params") Map<String, Object> params, @Param("sort") String sort, @Param("order") String order);


}