package com.crfeb.tbmpt.tbmmg.service;

import java.util.List;

import com.baomidou.framework.service.ICommonService;
import com.crfeb.tbmpt.tbmmg.model.ProTbmDgstateInfo;
import com.crfeb.tbmpt.tbmmg.model.ProTbminfo;

/**
 *
 * ProTbmDgstateInfo 表数据服务层接口
 *
 */
public interface IProTbmDgstateInfoService extends ICommonService<ProTbminfo> {

	List<ProTbmDgstateInfo> getWzZb(String riqi);
	List<ProTbmDgstateInfo> getTbmInfo(String riqi);
	
	Object getGzDbInfo(String riqi);
}