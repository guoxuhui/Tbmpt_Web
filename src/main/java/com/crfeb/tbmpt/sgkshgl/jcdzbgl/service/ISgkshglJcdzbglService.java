package com.crfeb.tbmpt.sgkshgl.jcdzbgl.service;

import com.crfeb.tbmpt.commons.utils.PageInfo;
import com.crfeb.tbmpt.sgkshgl.jcdzbgl.model.SgkshglJcdzbgl;
import com.crfeb.tbmpt.sgkshgl.jcdzbgl.model.dto.SgkshglJcdzbglDto;

import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import com.baomidou.framework.service.ICommonService;
import com.baomidou.framework.service.ISuperService;

/**
 *
 * JcpointNew 表数据服务层接口
 *
 */
public interface ISgkshglJcdzbglService extends ICommonService<SgkshglJcdzbgl> {

	void selectDataGrid(PageInfo pageInfo);

	void save(SgkshglJcdzbgl sgkshglJcdzbgl);

	SgkshglJcdzbglDto selectOne(String objectid);

	HSSFWorkbook generateExcelTemplate();

	String transformationBingPreservation(List<List<Map<String, String>>> list);

	List<SgkshglJcdzbgl> readExcel(List<List<Map<String, String>>> list);


}