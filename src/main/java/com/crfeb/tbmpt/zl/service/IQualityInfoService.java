package com.crfeb.tbmpt.zl.service;

import com.baomidou.framework.service.ICommonService;
import com.crfeb.tbmpt.commons.utils.PageInfo;
import com.crfeb.tbmpt.sys.model.User;
import com.crfeb.tbmpt.zl.model.QualityInfo;
import com.crfeb.tbmpt.zl.model.vo.QualityInfoVo;

import java.util.List;

/**
 *
 * QualityInfo 表数据服务层接口
 *
 */
public interface IQualityInfoService extends ICommonService<QualityInfo> {

	void selectDataGrid(PageInfo pageInfo);
	void selectDataGrid(PageInfo pageInfo,User user);
	int save(QualityInfo info);

	int saveList(List<QualityInfo> qualityInfoList);
	
	void selectDataGridApp(PageInfo pageInfo);
	
	/**
	 * 根据线路Id、查询 管片环号 集合
	 * @param line 线路Id
	 */
	List<QualityInfo> selectListByLineId(String line );
	
	/**
	 * 根据线路id、环号、查询 管片信息
	 * @param cycleNo 环号
	 */
	QualityInfo selectQualityInfoByCycleNo(String line  ,String cycleNo );
	/**
	 * 根据项目Id、上报人、时间获取行数
	 * @param proId 项目Id
	 * @param upUser 上报人
	 * @param time 上报日期
	 * @return long 总数
	 */
	long selectProIdUpUserUpDateConut(String proId,String upUser,String[] time);

	/**
	 * 根据项目Id、类型、时间获取行数
	 * @param proId 项目Id
	 * @param type 类型
	 * @param time 上报日期
	 * @return long 总数
	 */
	public long selectProIdTypeUpDateConut(String proId,String type,String[] time);
	
	/**
	 * 根据项目名称  获取观片质量上报列表
	 * @param proId
	 * @param userId
	 * @return
	 */
	List<QualityInfoVo> selectVoQualityInfoByProId(String proId, String userId);

	/**
	 * 图表分析数据组装
	 * @param timeStart
	 * @param timeEnd
	 * @param proName
	 * @param section
	 * @param line
	 * @oaram problemType
	 * @return
	 */
	List<QualityInfo> selectVoQualityInfoByChartData(String timeStart, String timeEnd,String proName,String section,String line,String problemType);

}