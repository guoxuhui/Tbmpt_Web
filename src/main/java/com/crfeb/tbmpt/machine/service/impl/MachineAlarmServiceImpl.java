package com.crfeb.tbmpt.machine.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.crfeb.tbmpt.commons.utils.PageInfo;
import com.crfeb.tbmpt.machine.mapper.MachineAlarmMapper;
import com.crfeb.tbmpt.machine.model.MachineAlarm;
import com.crfeb.tbmpt.machine.service.IMachineAlarmService;
import com.baomidou.framework.service.impl.CommonServiceImpl;
import com.baomidou.mybatisplus.plugins.Page;

/**
 *
 * MachineAlarm 表数据服务层接口实现类
 *
 */
@Service
public class MachineAlarmServiceImpl extends CommonServiceImpl<MachineAlarmMapper, MachineAlarm> implements IMachineAlarmService {

	@Autowired
	private MachineAlarmMapper machineAlarmMapper;
	
	public void selectDataGrid(PageInfo pageInfo){
		Page<MachineAlarm> page = new Page<MachineAlarm>(pageInfo.getNowpage(), pageInfo.getSize());
		List<MachineAlarm> list = machineAlarmMapper.selectPageList(page,pageInfo.getCondition(), pageInfo.getSort(), pageInfo.getOrder());
		pageInfo.setRows(list);
		pageInfo.setTotal(page.getTotal());
	}
}