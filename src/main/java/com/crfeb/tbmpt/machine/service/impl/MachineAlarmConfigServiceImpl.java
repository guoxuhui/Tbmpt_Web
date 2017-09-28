package com.crfeb.tbmpt.machine.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.crfeb.tbmpt.commons.utils.PageInfo;
import com.crfeb.tbmpt.machine.mapper.MachineAlarmConfigMapper;
import com.crfeb.tbmpt.machine.model.MachineAlarmConfig;
import com.crfeb.tbmpt.machine.service.IMachineAlarmConfigService;
import com.crfeb.tbmpt.sddzgl.model.dto.SddzglZkxxDto;
import com.crfeb.tbmpt.sys.model.User;
import com.baomidou.framework.service.impl.CommonServiceImpl;
import com.baomidou.mybatisplus.plugins.Page;

/**
 *
 * MachineAlarmConfig 表数据服务层接口实现类
 *
 */
@Service
public class MachineAlarmConfigServiceImpl extends CommonServiceImpl<MachineAlarmConfigMapper, MachineAlarmConfig> implements IMachineAlarmConfigService {

	@Autowired
	private MachineAlarmConfigMapper machineAlarmConfigMapper;
	
	public List<MachineAlarmConfig> selectAll() {
		return machineAlarmConfigMapper.selectAll();
	}
	
	public void updateConfigById(MachineAlarmConfig config){
		machineAlarmConfigMapper.updateById(config);
	}

	@Override
	public void selectDataGrid(PageInfo pageInfo) throws Exception {
		Page<MachineAlarmConfig> page = new Page<MachineAlarmConfig>(pageInfo.getNowpage(), pageInfo.getSize());
        Map<String, Object> condition = pageInfo.getCondition();
        List<MachineAlarmConfig> list = machineAlarmConfigMapper.selectList(page,condition,pageInfo.getSort(), pageInfo.getOrder());
        pageInfo.setRows(list);
        pageInfo.setTotal(page.getTotal());
		
	}
}