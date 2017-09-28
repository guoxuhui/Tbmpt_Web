package com.crfeb.tbmpt.project.service;

import java.util.List;

import com.baomidou.framework.service.ICommonService;
import com.crfeb.tbmpt.commons.utils.PageInfo;
import com.crfeb.tbmpt.project.model.ProFbgcInfo;
import com.crfeb.tbmpt.project.model.vo.ProFbgcVo;
import com.crfeb.tbmpt.sys.model.User;

public interface IProFbgcInfoService extends ICommonService<ProFbgcInfo>{
	
	
	void selectDataGrid(PageInfo pageInfo,User user);
	
	List<ProFbgcVo> selectDataGridByDwgcId(String dwgcId);
	
	/**
	 * 根据区间编号获取分部工程信息
	 */
	List<ProFbgcInfo> getFbgcInfoBySectionId(String id);
	
	/**
	 * 保存分部工程信息
	 * @param fbgcInfo 
	 * @return
	 */
	boolean save(ProFbgcInfo fbgcInfo);
	
	/**
	 * 编辑分部工程信息
	 * @param fbgcInfo 
	 * @return
	 */
	boolean edit(ProFbgcInfo fbgcInfo);
	
	/**
	 * 删除分部工程信息
	 * @param fbgcInfo 
	 * @return
	 */
	boolean del(ProFbgcInfo fbgcInfo);
	
	/**
	 * 获取分部工程 根据项目ID
	 * @param id
	 * @return
	 */
	List<ProFbgcInfo> getFbgcByPid(String id);
	/**
	 * 获取分部工程 根据区间ID
	 * @param id
	 * @return
	 */
	List<ProFbgcInfo> getFbgcBySid(String id);
}
