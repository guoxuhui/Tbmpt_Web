package com.crfeb.tbmpt.risk.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.framework.service.impl.CommonServiceImpl;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.crfeb.tbmpt.commons.utils.PageInfo;
import com.crfeb.tbmpt.risk.mapper.RiskLevelMapper;
import com.crfeb.tbmpt.risk.model.RiskLevel;
import com.crfeb.tbmpt.risk.model.vo.RiskLevelVo;
import com.crfeb.tbmpt.risk.service.IRiskLevelService;
import com.crfeb.tbmpt.sys.model.User;

/**
 *
 * RiskLevel 表数据服务层接口实现类
 *
 */
@Service
public class RiskLevelServiceImpl extends CommonServiceImpl<RiskLevelMapper, RiskLevel> implements IRiskLevelService {

	
	@Autowired
    private RiskLevelMapper riskLevelMapper;
	@Override
	public void selectDataGrid(PageInfo pageInfo) {
		Page<RiskLevelVo> page = new Page<RiskLevelVo>(pageInfo.getNowpage(), pageInfo.getSize());
        List<RiskLevelVo> list = riskLevelMapper.selectVoList(page,pageInfo.getCondition(), pageInfo.getSort(), pageInfo.getOrder());
        pageInfo.setRows(list);
        pageInfo.setTotal(page.getTotal());
	}

	@Override
	public void selectDataGrid(PageInfo pageInfo, User user) {
		Page<RiskLevelVo> page = new Page<RiskLevelVo>(pageInfo.getNowpage(), pageInfo.getSize());
        List<RiskLevelVo> list = riskLevelMapper.selectVoList(page,pageInfo.getCondition(), pageInfo.getSort(), pageInfo.getOrder());
        pageInfo.setRows(list);
        pageInfo.setTotal(page.getTotal());
	}

	@Override
	public int save(RiskLevel riskLevel) {
		int result = 0;
		result = riskLevelMapper.insert(riskLevel);
		return result;
	}
	
	//查询所有
	public List<RiskLevel> queryList(EntityWrapper<RiskLevel> riskLevel){
		return riskLevelMapper.selectList(riskLevel);
	}


}