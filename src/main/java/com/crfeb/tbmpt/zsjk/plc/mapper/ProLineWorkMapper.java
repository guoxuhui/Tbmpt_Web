package com.crfeb.tbmpt.zsjk.plc.mapper;

import com.crfeb.tbmpt.zsjk.plc.model.ProLineWork;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.mapper.CommonMapper;


public interface ProLineWorkMapper extends CommonMapper<ProLineWork> {
    List<ProLineWork> selectByLineId(@Param("lineId")String lineId) throws Exception;
    
}