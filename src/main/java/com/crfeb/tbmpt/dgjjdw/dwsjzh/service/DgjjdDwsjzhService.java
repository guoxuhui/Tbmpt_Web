package com.crfeb.tbmpt.dgjjdw.dwsjzh.service;

import java.util.List;

import com.crfeb.tbmpt.commons.utils.PageInfo;
import com.crfeb.tbmpt.dgjjdw.dwsjzh.model.DgjjdDwsjzh;   
import com.baomidou.framework.service.ICommonService; 
/**
 *
 * ProPlcTbmdw 表数据服务层接口
 *
 */
public interface DgjjdDwsjzhService extends ICommonService<DgjjdDwsjzh> {

	/**
	 * 查询数据表格
	 */
	void selectDataGrid(PageInfo pageInfo);

    public List<DgjjdDwsjzh> getDwsjzhList(String dgjName,String dwName);  
}