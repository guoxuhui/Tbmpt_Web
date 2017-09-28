package com.crfeb.tbmpt.risk.service;

import java.util.List;

import com.baomidou.framework.service.ICommonService;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.crfeb.tbmpt.commons.utils.PageInfo;
import com.crfeb.tbmpt.risk.model.RiskLevel;
import com.crfeb.tbmpt.sys.model.User;

/**
 *
 * RiskLevel 表数据服务层接口
 *
 */
public interface IRiskLevelService extends ICommonService<RiskLevel> {

	void selectDataGrid(PageInfo pageInfo);
	void selectDataGrid(PageInfo pageInfo,User user);
	int save(RiskLevel section);
	List<RiskLevel> queryList(EntityWrapper<RiskLevel> riskLevel);
}