package com.crfeb.tbmpt.project.service;

import java.util.List;

import com.baomidou.framework.service.ICommonService;
import com.crfeb.tbmpt.commons.utils.PageInfo;
import com.crfeb.tbmpt.project.model.ProProjectinfo;
import com.crfeb.tbmpt.sys.model.Orgz;
import com.crfeb.tbmpt.sys.model.User;

/**
 *
 * ProProjectinfo 表数据服务层接口
 *
 */
public interface IProProjectinfoService extends ICommonService<ProProjectinfo> {

	void selectDataGrid(PageInfo pageInfo);
	void selectDataGrid(PageInfo pageInfo,User user);
	
	public void saveProjectInfo(ProProjectinfo pro, User user);

	/**
	 * 根据编号获取项目信息
	 * @param proId
	 * @return
	 */
	ProProjectinfo selectByProId(String id);
	
	/**
	 * 根据项目编号删除项目
	 * @param proId
	 * @return 
	 */
	Boolean deleteByProId(String id);
	
	/**
	 *  根据用户编号获取用户所在项目信息
	 * @param uid
	 * @return
	 */
	ProProjectinfo getProjectInfoByUserId(String userId); 
	
	
	/**
	 *  根据用户编号获取用户可以管理的所有项目信息
	 * @param uid
	 * @return
	 */
	List<ProProjectinfo> getProjectInfosByUserId(String userId);
	
	//获取所有项目信息
	List<ProProjectinfo> getAllProjectInfos();
	
	/**
	 * 根据ID查找机构名称
	 * @author wl_zjh
	 * @param orgzId
	 * @return
	 */
	List<Orgz> selectByOrgzId(String orgzId);
	
	/**
	 * 项目树
	 * @param wl_zjh
	 * @param id
	 * @return
	 */
	Object selectTreeByUser(User user, String id);
	Object selectTree();
	Object selectTreeByUser(User user);
	
	
	/**
	 *  获取所有项目
	 * @return
	 */
	List<ProProjectinfo> getProjectInfosBylist();

	List<ProProjectinfo> selectAll();
}