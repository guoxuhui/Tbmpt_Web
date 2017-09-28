package com.crfeb.tbmpt.monitor.service.impl;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.crfeb.tbmpt.gpztcl.base.mapper.GpztclQxysMapper;
import com.crfeb.tbmpt.monitor.mapper.ProGeoLinesMapper;
import com.crfeb.tbmpt.monitor.model.GeoLineModel;
import com.crfeb.tbmpt.monitor.model.ProGeoLines;
import com.crfeb.tbmpt.monitor.service.IProGeoLinesService;
import com.baomidou.framework.service.impl.CommonServiceImpl;
import com.baomidou.framework.service.impl.SuperServiceImpl;

/**
 *
 * ProGeoLines 表数据服务层接口实现类
 *
 */
@Service
public class ProGeoLinesServiceImpl extends CommonServiceImpl<ProGeoLinesMapper, ProGeoLines> implements IProGeoLinesService {

	@Autowired
	ProGeoLinesMapper proGeoLinesMapper;
	public List<GeoLineModel> selectGeoLineListByLId(String line_id) {
		return proGeoLinesMapper.selectGeoLineListByLId(line_id);
	}
	public List<GeoLineModel> selectGeoLineListByPId(String pro_id) {
		return proGeoLinesMapper.selectGeoLineListByPId(pro_id);
	}
}