package com.crfeb.tbmpt.project.service;

import com.crfeb.tbmpt.commons.utils.PageInfo;
import com.crfeb.tbmpt.project.model.ProPqInfo;
import com.crfeb.tbmpt.project.model.vo.ProPqInfoVo;
import com.crfeb.tbmpt.sys.model.User;
import com.crfeb.tbmpt.zsjk.jt.model.dto.ZsJkJtAqZlXxDto;

import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import com.baomidou.framework.service.ICommonService;


/**
 *
 * ProPqInfo 表数据服务层接口
 *
 */
public interface IProPqInfoService extends ICommonService<ProPqInfo> {

	void selectDataGrid(PageInfo pageInfo, User currentUser);

	String save(ProPqInfoVo proPqInfoVo, User currentUser);

	String update(ProPqInfoVo proPqInfoVo, User currentUser);

	/**
	 * 生成excel模板
	 * @author wl_zjh
	 * @return
	 */
	HSSFWorkbook generateExcelTemplate();
	
	
	/**
	 * 将集合转换成对象
	 * @author wl_zjh
	 * @param list
	 * @return
	 */
	String transformationBingPreservation(List<List<Map<String, String>>> list);

	List<ProPqInfoVo> selectByName();
}