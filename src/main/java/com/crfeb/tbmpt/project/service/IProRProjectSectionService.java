package com.crfeb.tbmpt.project.service;

import java.util.List;

import com.baomidou.framework.service.ICommonService;
import com.crfeb.tbmpt.commons.utils.PageInfo;
import com.crfeb.tbmpt.project.model.ProRProjectSection;
import com.crfeb.tbmpt.sys.model.User;

/**
 *
 * ProRProjectSection 表数据服务层接口
 *
 */
public interface IProRProjectSectionService extends ICommonService<ProRProjectSection> {

	void selectDataGrid(PageInfo pageInfo);
	void selectDataGrid(PageInfo pageInfo,User user);
	
	/**
	 * 根据项目编号获取区间信息
	 * @param pid
	 * @return
	 */
	List<ProRProjectSection> getSectionByProId(String id);
	
	int save(ProRProjectSection section);

}