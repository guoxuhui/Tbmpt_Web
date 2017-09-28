package com.crfeb.tbmpt.risk.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.mapper.CommonMapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;
import com.crfeb.tbmpt.risk.model.RiskInfo;
import com.crfeb.tbmpt.risk.model.vo.RiskInfoVo;

/**
 *
 * RiskInfo 表数据库控制层接口
 *
 */
public interface RiskInfoMapper extends CommonMapper<RiskInfo> {


	/**
	 * 查询风控级别列表
	 * @param page
	 * @param params
	 * @param sort
	 * @param order
	 * @return
	 */
	List<RiskInfoVo> selectVoList(Pagination page,@Param("params") Map<String, Object> params, @Param("sort") String sort, @Param("order") String order);
	
	/**
	 * 查询风控级别列表
	 * @param page
	 * @param params
	 * @param sort
	 * @param order
	 * @return
	 */
	List<RiskInfoVo> selectVoListApp(Pagination page,@Param("params") Map<String, Object> params, @Param("sort") String sort, @Param("order") String order);
	
	/**
	 * 根据项目Id、上报人、等级、时间获取行数
	 * @param time 时间
	 * @param upUser 上报人
	 * @param rikeLevel 等级
	 * @return long 总数
	 */
	long selectProIdUpUserRikeLevelUpTimeConut(@Param("proName") String proName,@Param("upUser") String upUser,@Param("rikeLevel") String rikeLevel,@Param("time") String[] time);


	/**
	 * 根据项目Id获取上报信息
	 * @param proId
	 * @param userId
	 * @return
	 */
	List<RiskInfoVo> selectVoRiskInfoByProId(@Param("proId")String proId,@Param("userId")String userId);
	
}