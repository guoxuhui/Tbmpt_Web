package com.crfeb.tbmpt.zl.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.framework.service.impl.CommonServiceImpl;
import com.baomidou.mybatisplus.plugins.Page;
import com.crfeb.tbmpt.commons.utils.PageInfo;
import com.crfeb.tbmpt.sys.model.User;
import com.crfeb.tbmpt.zl.mapper.QualitySettingMapper;
import com.crfeb.tbmpt.zl.model.QualitySetting;
import com.crfeb.tbmpt.zl.model.vo.QualitySettingVo;
import com.crfeb.tbmpt.zl.service.IQualitySettingService;

/**
 *
 * QualitySetting 表数据服务层接口实现类
 *
 */
@Service
public class QualitySettingServiceImpl extends CommonServiceImpl<QualitySettingMapper, QualitySetting> implements IQualitySettingService {
	@Autowired
	QualitySettingMapper qualitySettingMapper;
	
	
	@Override
	public void selectDataGrid(PageInfo pageInfo) {
		Page<QualitySettingVo> page = new Page<QualitySettingVo>(pageInfo.getNowpage(), pageInfo.getSize());
        List<QualitySettingVo> list = qualitySettingMapper.selectVoList(page,pageInfo.getCondition(), pageInfo.getSort(), pageInfo.getOrder());
        pageInfo.setRows(list);
        pageInfo.setTotal(page.getTotal());
	}
	
	@Override
	public void selectDataGrid(PageInfo pageInfo, User user) {
		Page<QualitySettingVo> page = new Page<QualitySettingVo>(pageInfo.getNowpage(), pageInfo.getSize());
        List<QualitySettingVo> list = qualitySettingMapper.selectVoList(page,pageInfo.getCondition(), pageInfo.getSort(), pageInfo.getOrder());
        pageInfo.setRows(list);
        pageInfo.setTotal(page.getTotal());
	}

	@Override
	public int save(QualitySetting QualitySetting) {
		int result = 0;
		result = qualitySettingMapper.insert(QualitySetting);
		return result;
	}


}