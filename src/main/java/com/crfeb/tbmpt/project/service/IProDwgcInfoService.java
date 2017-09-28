package com.crfeb.tbmpt.project.service;

import java.util.List;

import com.baomidou.framework.service.ICommonService;
import com.crfeb.tbmpt.commons.utils.PageInfo;
import com.crfeb.tbmpt.project.model.ProDwgcInfo;
import com.crfeb.tbmpt.sys.model.User;

public interface IProDwgcInfoService extends ICommonService<ProDwgcInfo>{

	void selectDataGrid(PageInfo pageInfo,User user);
	
	
	/**
	 * 获取单位工程信息列表
	 * @param id 项目ID
	 * @return 单位工程列表
	 * @author:Xhguo
	 * @Time: 2017年2月18日14:56:44
	 */
	List<ProDwgcInfo> getDwgcInfoByProId(String id);

}
