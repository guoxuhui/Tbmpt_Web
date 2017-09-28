package com.crfeb.tbmpt.zsjk.plc.mapper;

import com.crfeb.tbmpt.zsjk.plc.model.ProPlcTbmdw;
import com.crfeb.tbmpt.zsjk.plc.model.dto.ProPlcTbmdwDto;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.mapper.AutoMapper;
import com.baomidou.mybatisplus.mapper.CommonMapper;
import com.baomidou.mybatisplus.plugins.Page;

/**
 *
 * ProPlcTbmdw 表数据库控制层接口
 *
 */
public interface ProPlcTbmdwMapper extends CommonMapper<ProPlcTbmdw> {
    List<ProPlcTbmdw> selectProPlcTbmdwXxList(Page<ProPlcTbmdw> page,@Param("condition")Map<String, Object> condition) throws Exception;
    
    /**
     * 方法说明：根据条件查询数据列表
     * @param condition 参数条件
     * @return 返回数据dto列表集合
     * @throws Exception
     * @Time: 2017年1月10日 下午2:31:15
     */
    List<ProPlcTbmdwDto> selectProPlcTbmdwDtoXxList(@Param("condition")Map<String, Object> condition) throws Exception;


}