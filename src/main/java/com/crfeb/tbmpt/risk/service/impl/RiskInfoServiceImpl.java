package com.crfeb.tbmpt.risk.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.framework.service.impl.CommonServiceImpl;
import com.baomidou.mybatisplus.plugins.Page;
import com.crfeb.tbmpt.commons.utils.PageInfo;
import com.crfeb.tbmpt.risk.mapper.RiskInfoMapper;
import com.crfeb.tbmpt.risk.model.RiskInfo;
import com.crfeb.tbmpt.risk.model.vo.RiskInfoVo;
import com.crfeb.tbmpt.risk.service.IRiskInfoService;
import com.crfeb.tbmpt.sys.model.User;

/**
 *
 * RiskInfo 表数据服务层接口实现类
 *
 */
@Service
public class RiskInfoServiceImpl extends CommonServiceImpl<RiskInfoMapper, RiskInfo> implements IRiskInfoService {

	@Autowired
    private RiskInfoMapper riskInfoMapper;
	@Override
	public void selectDataGrid(PageInfo pageInfo) {
		Page<RiskInfoVo> page = new Page<RiskInfoVo>(pageInfo.getNowpage(), pageInfo.getSize());
        List<RiskInfoVo> list = riskInfoMapper.selectVoList(page,pageInfo.getCondition(), pageInfo.getSort(), pageInfo.getOrder());
        pageInfo.setRows(list);
        pageInfo.setTotal(page.getTotal());
	}
	
	@Override
	public void selectDataGrid(PageInfo pageInfo, User user) {
		Page<RiskInfoVo> page = new Page<RiskInfoVo>(pageInfo.getNowpage(), pageInfo.getSize());
        List<RiskInfoVo> list = riskInfoMapper.selectVoList(page,pageInfo.getCondition(), pageInfo.getSort(), pageInfo.getOrder());
        pageInfo.setRows(list);
        pageInfo.setTotal(page.getTotal());
	}

	@Override
	public int save(RiskInfo riskInfo) {
		int result = 0;
		result = riskInfoMapper.insert(riskInfo);
		return result;
	}
	
	public void selectDataGridApp(PageInfo pageInfo) {
		Page<RiskInfoVo> page = new Page<RiskInfoVo>(pageInfo.getNowpage(), pageInfo.getSize());
        List<RiskInfoVo> list = riskInfoMapper.selectVoListApp(page,pageInfo.getCondition(), pageInfo.getSort(), pageInfo.getOrder());
        pageInfo.setRows(list);
        pageInfo.setTotal(page.getTotal());
	}
	
	/**
	 * 根据项目Id、上报人、等级、时间获取行数
	 * @param time 时间
	 * @param upUser 上报人
	 * @param rikeLevel 等级
	 * @return long 总数
	 */
	public long selectProIdUpUserRikeLevelUpTimeConut(String proName,String upUser,String rikeLevel,String[] time){
		return riskInfoMapper.selectProIdUpUserRikeLevelUpTimeConut(proName,upUser, rikeLevel,time);
	}

	/**
	 * 根据项目Id获取上报信息
	 * @param proId
	 * @param userId
	 * @return
	 */
	public List<RiskInfoVo> selectVoRiskInfoByProId(String proId,String userId){
		return riskInfoMapper.selectVoRiskInfoByProId(proId,userId);
	}
	
}