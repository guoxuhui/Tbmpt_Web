package com.crfeb.tbmpt.zsjk.plc.mapper;

import com.crfeb.tbmpt.zsjk.plc.model.ProPlcBzdw;
import com.crfeb.tbmpt.zsjk.plc.model.dto.ProPlcBzdwDto;
import com.crfeb.tbmpt.zsjk.xmb.model.ZsJkXmbClYdXhXx;
import com.crfeb.tbmpt.zsjk.xmb.model.dto.ZsJkXmbClYdXhXxDto;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.mapper.AutoMapper;
import com.baomidou.mybatisplus.mapper.CommonMapper;
import com.baomidou.mybatisplus.plugins.Page;

/**
 *
 * ProPlcBzdw 表数据库控制层接口
 * 
 */
public interface ProPlcBzdwMapper extends CommonMapper<ProPlcBzdw> {

    List<ProPlcBzdw> selectProPlcBzdwXxList(Page<ProPlcBzdw> page,@Param("condition")Map<String, Object> condition) throws Exception;
    
    /**
     * 方法说明：根据条件查询数据列表
     * @param condition 参数条件
     * @return 返回数据dto列表集合
     * @throws Exception
     * @Time: 2017年1月10日 下午2:31:15
     */
    List<ProPlcBzdwDto> selectProPlcBzdwDtoXxList(@Param("condition")Map<String, Object> condition) throws Exception;

}