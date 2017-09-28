package com.crfeb.tbmpt.sgkshgl.zxxwh.service;


import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import com.baomidou.framework.service.ICommonService;
import com.crfeb.tbmpt.commons.utils.PageInfo;
import com.crfeb.tbmpt.sgkshgl.zxxwh.model.SgkshglZxxwh;
import com.crfeb.tbmpt.sgkshgl.zxxwh.model.dto.SgkshglZxxwhDto;
import com.crfeb.tbmpt.sys.model.User;

/**
 * <p>CAD中心线维护 Service接口</p>
 * <p>模块：施工可视化管理</p>
 * <p>日期：2017-04-12</p>
 * @version 1.0
 * @author wl_wpg
 */
public interface ISgkshglZxxwhService extends ICommonService<SgkshglZxxwh> {

	/***
     * 根据用户查询列表
     */
	void selectDataGrid(PageInfo pageInfo,User user);
	
	 
	String addSave(SgkshglZxxwhDto dto);
	String edit(SgkshglZxxwh dto);

	/**     
     * 生成 Excel导入 模版
     * @param
     */
    HSSFWorkbook generateExcelTemplate();
    /**
     * 把 Excel表格数据集合 转成  对象  并  保存
     * @param list
     * @return
     */
    String transformationBingPreservation(List<List<Map<String, String>>> list,SgkshglZxxwhDto dto);
    
    
}