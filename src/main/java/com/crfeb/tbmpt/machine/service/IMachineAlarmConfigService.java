package com.crfeb.tbmpt.machine.service;

import com.crfeb.tbmpt.commons.utils.PageInfo;
import com.crfeb.tbmpt.machine.model.MachineAlarmConfig;
import com.crfeb.tbmpt.sys.model.User;

import java.util.List;

import com.baomidou.framework.service.ICommonService;

/**
 *
 * MachineAlarmConfig 表数据服务层接口
 *
 */
public interface IMachineAlarmConfigService extends ICommonService<MachineAlarmConfig> {
	List<MachineAlarmConfig> selectAll();
	void updateConfigById(MachineAlarmConfig config);
	
	/**
     * 分页查询 easyUi列表
     * @param pageInfo 分页公用类
     */
    void selectDataGrid(PageInfo pageInfo) throws Exception;
}