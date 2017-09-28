package com.crfeb.tbmpt.dmcjjc.dd.service;

import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import com.baomidou.framework.service.ISuperService;
import com.crfeb.tbmpt.commons.utils.PageInfo;
import com.crfeb.tbmpt.dmcjjc.dd.model.DdInfo;

/**
 * 3.1.2 基础数据管理
 * @author Administrator
 *
 */
public interface IDmcjDDInfoService extends ISuperService<DdInfo>{
	public void qy(String id);
	public void jy(String id);
	public void getDmcjDDType();
	public HSSFWorkbook expDDInfo(List<String> ids);
	public List<DdInfo> getDmcjDDInfo(String typeName);
	public void selectDataGrid(PageInfo pageInfo);
}
