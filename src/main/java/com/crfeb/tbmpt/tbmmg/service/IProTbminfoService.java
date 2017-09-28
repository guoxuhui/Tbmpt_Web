package com.crfeb.tbmpt.tbmmg.service;

import java.util.List;

import com.baomidou.framework.service.ICommonService;
import com.crfeb.tbmpt.commons.utils.PageInfo;
import com.crfeb.tbmpt.sddzgl.model.dto.SddzglDzxxDto;
import com.crfeb.tbmpt.tbmmg.model.ProTbminfo;

/**
 *
 * ProTbminfo 表数据服务层接口
 *
 */
public interface IProTbminfoService extends ICommonService<ProTbminfo> {

	void selectDataGrid(PageInfo pageInfo);
	
	/**
	 * 获取全部盾构机信息
	 * @return
	 */
	List<ProTbminfo> selectAllList();

	List<SddzglDzxxDto> selectDzxx();
}