package com.crfeb.tbmpt.monitor.service;

import com.crfeb.tbmpt.monitor.model.GeoLineModel;
import com.crfeb.tbmpt.monitor.model.ProGeoLines;

import java.util.List;

import com.baomidou.framework.service.ICommonService;
import com.baomidou.framework.service.ISuperService;

/**
 *
 * ProGeoLines 表数据服务层接口
 *
 */
public interface IProGeoLinesService extends ICommonService<ProGeoLines> {

	public List<GeoLineModel> selectGeoLineListByLId(String line_id);
	public List<GeoLineModel> selectGeoLineListByPId(String pro_id);
}