package com.crfeb.tbmpt.zl.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.mapper.CommonMapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;
import com.crfeb.tbmpt.zl.model.QualitySetting;
import com.crfeb.tbmpt.zl.model.vo.QualitySettingVo;

/**
 *
 * QualitySetting 表数据库控制层接口
 *
 */
public interface QualitySettingMapper extends CommonMapper<QualitySetting> {

	/**
	 * 查询管片质量上报列表
	 * @param page
	 * @param params
	 * @param sort
	 * @param order
	 * @return
	 */
	List<QualitySettingVo> selectVoList(Pagination page,@Param("params") Map<String, Object> params, @Param("sort") String sort, @Param("order") String order);
}