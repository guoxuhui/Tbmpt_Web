package com.crfeb.tbmpt.sys.service;

import java.util.List;

import com.baomidou.framework.service.ICommonService;
import com.crfeb.tbmpt.commons.result.Tree;
import com.crfeb.tbmpt.sys.model.Orgz;
import com.crfeb.tbmpt.sys.model.User;

/**
 *
 * orgz 表数据服务层接口
 *
 */
public interface IOrgzService extends ICommonService<Orgz> {

	List<Tree> selectTree();

	List<Tree> selectTreeByUser(User user);
	
	List<Tree> selectTreeByOrgzPid(User user);

	/**
	 * 获取用户可选取的组织机构，异步加载树
	 * 
	 * @param user
	 * @param id
	 * @return
	 */
	List<Tree> selectTreeByUser(User user, String id);

	List<Orgz> selectTreeGrid();

	List<Orgz> selectTreeGridByUser(User user);

	List<Tree> selectTreeByPId(String id);

	/**
	 * 通过用户编号获取所在组织机构信息
	 * 
	 * @param uid
	 * @return
	 */
	Orgz getOrgzInfoByUserId(String userId);

	/**
	 * 通过用户编号获取所在组织机构上级信息
	 * 
	 * @param userId
	 * @return
	 */
	List<Tree> selectUserTreeByOrgzPid(User user);

	List<Tree> selectUserTreeByUser(User user);

	int save(Orgz orgz);

	/**
	 * 编辑保存
	 * 
	 * @param orgz
	 * @return
	 */
	String editSave(Orgz orgz);

	/***
	 * 删除操作
	 * 
	 * @param id
	 * @return
	 */
	String deleteOperate(String id);

	/***
	 * 删除部门树操作
	 * 
	 * @param id
	 * @return
	 */
	boolean deleteOrgz(String id);

	/**
	 * 通过Id获取组织机构信息 lw_wpg
	 * 
	 * @param uid
	 * @return
	 */
	Orgz selectOrgzInfoGridById(String rId);
}