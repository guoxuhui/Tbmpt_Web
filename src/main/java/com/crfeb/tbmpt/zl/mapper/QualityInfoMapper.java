package com.crfeb.tbmpt.zl.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.mapper.CommonMapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;
import com.crfeb.tbmpt.zl.model.QualityInfo;
import com.crfeb.tbmpt.zl.model.vo.QualityInfoVo;

/**
 *
 * QualityInfo 表数据库控制层接口
 *
 */
public interface QualityInfoMapper extends CommonMapper<QualityInfo> {


	/**
	 * 查询管片质量上报列表
	 * @param page
	 * @param params
	 * @param sort
	 * @param order
	 * @return
	 */
	List<QualityInfoVo> selectVoList(Pagination page,@Param("params") Map<String, Object> params, @Param("sort") String sort, @Param("order") String order);
	
	/**
	 * 查询管片质量上报列表
	 * @param page
	 * @param params
	 * @param sort
	 * @param order
	 * @return
	 */
	List<QualityInfoVo> selectVoListApp(Pagination page,@Param("params") Map<String, Object> params, @Param("sort") String sort, @Param("order") String order);
	
	/**
	 * 根据项目Id、上报人、时间获取行数
	 * @param proId 项目Id
	 * @param upUser 上报人
	 * @param time 上报日期
	 * @return long 总数
	 */
	long selectProIdUpUserUpDateConut(@Param("proName") String proName,@Param("upUser") String upUser,@Param("time") String[] time);

	/**
	 * 根据项目Id、类型、时间获取行数
	 * @param proId 项目Id
	 * @param type 类型
	 * @param time 上报日期
	 * @return long 总数
	 */
	long selectProIdTypeUpDateConut(@Param("proName") String proName,@Param("type") String type,@Param("time") String[] time);

	/**
	 * 根据项目名称  获取观片质量上报列表
	 * @param proId
	 * @param userId
	 * @return
	 */
	List<QualityInfoVo> selectVoQualityInfoByProId(@Param("proId") String proId,@Param("userId")String userId);

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
	List<QualityInfo> selectVoQualityInfoByChartData(@Param("timeStart") String timeStart, @Param("timeEnd") String timeEnd,@Param("proName") String proName,@Param("section") String section,@Param("line") String line,@Param("problemType") String problemType);

	List<QualityInfo> selectQualityListByLineId(@Param("line") String line);
	
	QualityInfo selectQualityInfoByCycleNo(@Param("line") String line,@Param("cycleNo") String cycleNo);
	
	
	
	
	
}