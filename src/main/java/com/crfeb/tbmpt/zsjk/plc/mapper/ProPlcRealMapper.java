package com.crfeb.tbmpt.zsjk.plc.mapper;

import com.crfeb.tbmpt.zsjk.plc.model.ProPlcReal;
import com.crfeb.tbmpt.zsjk.plc.model.dto.ProPlcBzRealDto;
import com.crfeb.tbmpt.zsjk.plc.model.dto.ProPlcRealDto;
import com.crfeb.tbmpt.zsjk.plc.model.dto.ProSecLinePlcDto;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.mapper.AutoMapper;
import com.baomidou.mybatisplus.mapper.CommonMapper;
import com.baomidou.mybatisplus.plugins.Page;

/**
 *
 * ProPlcReal 表数据库控制层接口
 *
 */
public interface ProPlcRealMapper extends CommonMapper<ProPlcReal> {

    List<ProPlcReal> selectProPlcRealXxList(Page<ProPlcReal> page,@Param("condition")Map<String, Object> condition) throws Exception;
    
    /**
     * 方法说明：根据条件查询数据列表
     * @param condition 参数条件
     * @return 返回数据dto列表集合
     * @throws Exception
     * @Time: 2017年1月10日 下午2:31:15
     */
    List<ProPlcRealDto> selectProPlcRealDtoXxList(@Param("condition")Map<String, Object> condition) throws Exception;

    /**
     * 方法说明：根据条件查询数据列表
     * @param condition 参数条件
     * @return 返回数据dto列表集合
     * @throws Exception
     * @Time: 2017年1月10日 下午2:31:15
     */
    List<ProPlcBzRealDto> selectProPlcBzRealXxDtoList(@Param("condition")Map<String, Object> condition) throws Exception;

    
    /**
     * 方法说明：获取全部PLC标准点位数据，封装成项目区间线路点位信息表
     * @return 返回数据dto列表集合
     * @throws Exception
     * @Time: 2017年1月10日 下午2:31:15
     */
    List<ProSecLinePlcDto> selectProSecLinePlcAll() throws Exception;
    
}