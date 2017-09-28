package com.crfeb.tbmpt.machine.mapper;

import com.crfeb.tbmpt.machine.model.MachineAlarmConfig;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.mapper.CommonMapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

/**
 *
 * MachineAlarmConfig 表数据库控制层接口
 *
 */
public interface MachineAlarmConfigMapper extends CommonMapper<MachineAlarmConfig> {

	List<MachineAlarmConfig> selectAll();
	
	List<MachineAlarmConfig> selectList(Pagination page,@Param("params") Map<String, Object> params, @Param("sort") String sort, @Param("order") String order);;
	
}