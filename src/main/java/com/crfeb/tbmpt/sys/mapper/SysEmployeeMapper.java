package com.crfeb.tbmpt.sys.mapper;

import com.crfeb.tbmpt.sys.model.SysEmployee;
import com.crfeb.tbmpt.sys.model.vo.SysEmployeeVo;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.mapper.CommonMapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

/**
 *
 * SysEmployee 表数据库控制层接口
 *
 */
public interface SysEmployeeMapper extends CommonMapper<SysEmployee> {

	List<SysEmployeeVo> selectAll();
	
	List<SysEmployeeVo> selectSysEmployeeList(Pagination page, @Param("params") Map<String, Object> params,@Param("sort") String sort, @Param("order") String order);
	
	List<SysEmployee> selectByOrgzId(@Param("orgzId") String orgzId);

}