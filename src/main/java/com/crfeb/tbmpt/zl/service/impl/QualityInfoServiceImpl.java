package com.crfeb.tbmpt.zl.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.framework.service.impl.CommonServiceImpl;
import com.baomidou.mybatisplus.plugins.Page;
import com.crfeb.tbmpt.commons.utils.PageInfo;
import com.crfeb.tbmpt.sys.model.User;
import com.crfeb.tbmpt.zl.mapper.QualityInfoMapper;
import com.crfeb.tbmpt.zl.model.QualityInfo;
import com.crfeb.tbmpt.zl.model.vo.QualityInfoVo;
import com.crfeb.tbmpt.zl.service.IQualityInfoService;

/**
 *
 * QualityInfo 表数据服务层接口实现类
 *
 */
@Service
public class QualityInfoServiceImpl extends CommonServiceImpl<QualityInfoMapper, QualityInfo> implements IQualityInfoService {
	@Autowired
	QualityInfoMapper qualityInfoMapper;
	
	
	@Override
	public void selectDataGrid(PageInfo pageInfo) {
		Page<QualityInfoVo> page = new Page<QualityInfoVo>(pageInfo.getNowpage(), pageInfo.getSize());
        List<QualityInfoVo> list = qualityInfoMapper.selectVoList(page,pageInfo.getCondition(), pageInfo.getSort(), pageInfo.getOrder());
        pageInfo.setRows(list);
        pageInfo.setTotal(page.getTotal());
	}
	
	@Override
	public void selectDataGrid(PageInfo pageInfo, User user) {
		Page<QualityInfoVo> page = new Page<QualityInfoVo>(pageInfo.getNowpage(), pageInfo.getSize());
        List<QualityInfoVo> list = qualityInfoMapper.selectVoList(page,pageInfo.getCondition(), pageInfo.getSort(), pageInfo.getOrder());
        pageInfo.setRows(list);
        pageInfo.setTotal(page.getTotal());
	}

	@Override
	public int save(QualityInfo QualityInfo) {
		int result = 0;
		result = qualityInfoMapper.insert(QualityInfo);
		return result;
	}
	
	@Override
	public int saveList(List<QualityInfo> qualityInfoList) {
		int result = 0;
		result = qualityInfoMapper.insertBatch(qualityInfoList);
		return result;
	}
	
	@Override
	public void selectDataGridApp(PageInfo pageInfo) {
		Page<QualityInfoVo> page = new Page<QualityInfoVo>(pageInfo.getNowpage(), pageInfo.getSize());
        List<QualityInfoVo> list = qualityInfoMapper.selectVoListApp(page,pageInfo.getCondition(), pageInfo.getSort(), pageInfo.getOrder());
        pageInfo.setRows(list);
        pageInfo.setTotal(page.getTotal());
	}
	
	/**
	 * 根据项目Id、上报人、时间获取行数
	 * @param proId 项目Id
	 * @param upUser 上报人
	 * @param time 上报日期
	 * @return long 总数
	 */
	public long selectProIdUpUserUpDateConut(String proId,String upUser,String[] time){
		return qualityInfoMapper.selectProIdUpUserUpDateConut(proId,upUser,time);
	}
	
	/**
	 * 根据项目Id、类型、时间获取行数
	 * @param proId 项目Id
	 * @param type 类型
	 * @param time 上报日期
	 * @return long 总数
	 */
	public long selectProIdTypeUpDateConut(String proId,String type,String[] time){
		return qualityInfoMapper.selectProIdTypeUpDateConut(proId,type,time);
	}

	/**
	 * 根据项目名称  获取观片质量上报列表
	 * @param proId
	 * @param userId
	 * @return
	 */
	public List<QualityInfoVo> selectVoQualityInfoByProId(String proId,String userId){
		return qualityInfoMapper.selectVoQualityInfoByProId(proId,userId);
	}

	/**
	 * 图表分析数据组装
	 * @param timeStart
	 * @param timeEnd
	 * @param proName
	 * @param section
	 * @param line
	 * @param problemType
	 * @return
	 */
	public List<QualityInfo> selectVoQualityInfoByChartData(String timeStart, String timeEnd,String proName,String section,String line,String problemType){
		return qualityInfoMapper.selectVoQualityInfoByChartData(timeStart,timeEnd,proName,section,line,problemType);
	}

	@Override
	public List<QualityInfo> selectListByLineId(String line) {
		return qualityInfoMapper.selectQualityListByLineId(line);
	}

	@Override
	public QualityInfo selectQualityInfoByCycleNo(String line ,String cycleNo ) {
		return qualityInfoMapper.selectQualityInfoByCycleNo(line ,cycleNo);
	}
}