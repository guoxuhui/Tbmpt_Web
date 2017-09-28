package com.crfeb.tbmpt.sgkshgl.zxxwh.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.mapper.CommonMapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;
import com.crfeb.tbmpt.sgkshgl.zxxwh.model.SgkshglZxxwh;
import com.crfeb.tbmpt.sgkshgl.zxxwh.model.dto.SgkshglZxxwhDto;

/**
 * <p>CAD中心线维护 Mapper接口</p>
 * <p>模块：施工可视化管理</p>
 * <p>日期：2017-04-12</p>
 * @version 1.0
 * @author wl_wpg
 */
public interface SgkshglZxxwhMapper extends CommonMapper<SgkshglZxxwh> {
	
	/***
	 * 查询 列表
	 * @param page
	 * @param params
	 * @param sort
	 * @param order
	 * @return
	 */
	List<SgkshglZxxwhDto> selectList(Pagination page, @Param("params") Map<String, Object> params,@Param("sort") String sort, @Param("order") String order);
	
	/***
	 * 查询 最大值 Id
	 * @return
	 */
	SgkshglZxxwhDto selectMaxId();
	
	List<SgkshglZxxwh> selectAdd();
	
}