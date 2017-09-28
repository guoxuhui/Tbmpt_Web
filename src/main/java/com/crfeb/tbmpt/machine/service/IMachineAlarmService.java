package com.crfeb.tbmpt.machine.service;

import com.crfeb.tbmpt.commons.utils.PageInfo;
import com.crfeb.tbmpt.machine.model.MachineAlarm;
import com.baomidou.framework.service.ICommonService;
import com.baomidou.framework.service.ISuperService;

/**
 *
 * MachineAlarm 表数据服务层接口
 *
 */
public interface IMachineAlarmService extends ICommonService<MachineAlarm> {

	void selectDataGrid(PageInfo pageInfo);
}