package com.crfeb.tbmpt.risk.service;

import com.baomidou.framework.service.ICommonService;
import com.crfeb.tbmpt.commons.utils.PageInfo;
import com.crfeb.tbmpt.risk.model.RiskInfo;
import com.crfeb.tbmpt.risk.model.vo.RiskInfoVo;
import com.crfeb.tbmpt.sys.model.User;

import java.util.List;

/**
 *
 * RiskInfo 表数据服务层接口
 *
 */
public interface IRiskInfoService extends ICommonService<RiskInfo> {

	void selectDataGrid(PageInfo pageInfo);
	void selectDataGrid(PageInfo pageInfo,User user);
	int save(RiskInfo section);
	
	void selectDataGridApp(PageInfo pageInfo);
	
	/**
	 * 根据项目Id、上报人、等级、时间获取行数
	 * @param time 时间
	 * @param proId 项目Id
	 * @param upUser 上报人
	 * @param rikeLevel 等级
	 * @return long 总数
	 */
	long selectProIdUpUserRikeLevelUpTimeConut(String proId,String upUser,String rikeLevel,String[] time);

	/**
	 * 根据项目Id获取上报信息
	 * @param proId
	 * @param userId
	 * @return
	 */
	List<RiskInfoVo> selectVoRiskInfoByProId(String proId,String userId);
	
}