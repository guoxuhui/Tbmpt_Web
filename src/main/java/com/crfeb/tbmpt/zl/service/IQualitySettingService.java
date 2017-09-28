package com.crfeb.tbmpt.zl.service;

import com.baomidou.framework.service.ICommonService;
import com.crfeb.tbmpt.commons.utils.PageInfo;
import com.crfeb.tbmpt.sys.model.User;
import com.crfeb.tbmpt.zl.model.QualitySetting;

/**
 *
 * QualitySetting 表数据服务层接口
 *
 */
public interface IQualitySettingService extends ICommonService<QualitySetting> {

	void selectDataGrid(PageInfo pageInfo);
	void selectDataGrid(PageInfo pageInfo,User user);
	int save(QualitySetting info);

}