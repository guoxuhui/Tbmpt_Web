package com.crfeb.tbmpt.dgjjdw.tbmdw.service;

import com.crfeb.tbmpt.commons.utils.PageInfo;
import com.crfeb.tbmpt.dgjjdw.real.model.dto.DgjjdwPlcRealDto;
import com.crfeb.tbmpt.dgjjdw.tbmdw.model.DgjjPlcTbmdw;
import com.crfeb.tbmpt.dgjjdw.tbmdw.model.dto.DgjjPlcTbmdwDto;

import java.util.List;

import com.baomidou.framework.service.ICommonService;
import com.baomidou.framework.service.ISuperService;

/**
 *
 * ProPlcTbmdw 表数据服务层接口
 *
 */
public interface IDgjjPlcTbmdwService extends ICommonService<DgjjPlcTbmdw> {

	/**
	 * 查询数据表格
	 */
	void selectDataGrid(PageInfo pageInfo);

	/**
	 * 批量对比信息数据表格
	 */
	List<DgjjPlcTbmdwDto> selecttbmdwDataGrid(DgjjPlcTbmdwDto dto);

	/**
	 * 根据工业库定位名称，盾构机CODE查询实时点位信息
	 */
	public List<DgjjdwPlcRealDto>getTagName(String tbmName,String tbmcode);
	
	/**
	 * 根据盾构机CODE查询实时点位信息
	 */
	public List<DgjjdwPlcRealDto>getTagCode(String tbmcode);
	
	/**
	 * 根据盾构机ID查询对比信息
	 */
	List<DgjjPlcTbmdwDto>selectByTbmid(String tbmid);
}