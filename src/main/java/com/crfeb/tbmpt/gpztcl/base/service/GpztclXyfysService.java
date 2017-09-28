package com.crfeb.tbmpt.gpztcl.base.service;

import java.util.List;

import com.baomidou.framework.service.ICommonService;
import com.crfeb.tbmpt.gpztcl.base.model.GpztclXyfys;
import com.crfeb.tbmpt.gpztcl.base.model.dto.GpztclXyfysDto;
import com.crfeb.tbmpt.project.model.vo.SectionLineVo;
import com.crfeb.tbmpt.sys.model.User;

public interface GpztclXyfysService extends ICommonService<GpztclXyfys>{
	/**
	 * 根据xlId查询主表信息
	 * @param xlId
	 * @return
	 */
	List<SectionLineVo> selectByXlId(String xlId);
	
	/**
	 * 根据xlId查询所有平曲线要素数据
	 * @param xlId
	 * @return
	 */
	List<GpztclXyfysDto> selectXyfys(String xlId);
	
}
