package com.crfeb.tbmpt.machine.mapper;

import com.crfeb.tbmpt.machine.model.MachineAlarm;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.mapper.CommonMapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

/**
 *
 * MachineAlarm 表数据库控制层接口
 *
 */
public interface MachineAlarmMapper extends CommonMapper<MachineAlarm> {

	List<MachineAlarm> selectPageList(Pagination page,@Param("params") Map<String, Object> params, @Param("sort") String sort, @Param("order") String order);
}