package com.crfeb.tbmpt.zsjk.plc.service;

import com.crfeb.tbmpt.zsjk.plc.model.ProLineWork;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.baomidou.framework.service.ICommonService;

public interface IProLineWorkService extends ICommonService<ProLineWork> {
    List<ProLineWork> selectByLineId(@Param("lineId")String lineId) throws Exception;

}