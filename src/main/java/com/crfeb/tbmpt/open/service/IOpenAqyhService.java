package com.crfeb.tbmpt.open.service;

import com.crfeb.tbmpt.sys.model.User;

/**
 * <p>移动端安全隐患排查模块API数据服务Service接口</p>
 * <p>系统：移动端接口</p>
 * <p>模块：安全隐患排查模块</p>
 * <p>日期：2016-12-05</p>
 * @version 1.0
 * @author smxg
 */
public interface IOpenAqyhService {

	/**
	 * 获取特种人员列表信息
	 * @param page 当前页数
	 * @param rows 每页大小
	 * @param smName 特种人员名称
	 * @param user 当前用户
	 * @return 返回特种人员列表
	 */
	Object getSpecialManList(int page,int rows,String smName,User user);
	
	/**
	 * 获取培训人员列表信息
	 * @param page 当前页数
	 * @param rows 每页大小
	 * @param pyName 培训人员名称
	 * @param user 当前用户
	 * @return 返回培训人员列表
	 */
	Object getPeiXunRenYuanList(int page,int rows,String pyName,User user);
	
	
	/**
	 * 获取人员日志列表信息
	 * @param page 当前页数
	 * @param rows 每页大小
	 * @param userId 人员日志名称
	 * @param user 当前用户
	 * @return 返回人员日志列表
	 */
	Object getWorklogList(int page,int rows,String userId,User user);
	
	
}
