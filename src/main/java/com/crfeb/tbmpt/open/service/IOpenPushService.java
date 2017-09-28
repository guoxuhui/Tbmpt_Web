package com.crfeb.tbmpt.open.service;

import com.crfeb.tbmpt.sys.model.User;

/**
 * <p>移动端数据推送模块API数据服务Service接口</p>
 * <p>系统：移动端接口</p>
 * <p>模块：数据推送</p>
 * <p>日期：2016-12-05</p>
 * @version 1.0
 * @author smxg
 */
public interface IOpenPushService {

	/**
	 * 工程质量模块消息统一推送接口
	 * 主要内容有：上报、审核成功、审核失败、整改
	 * 注意事项：当前用户不用推送
	 * @param user 用户
	 * @param resUrl 菜单路径
	 * @param content 消息内容
	 */
	void gczlPushMsg(User user,String resUrl,String content);
	
	/**
	 * 工程质量模块消息统一推送接口V2
	 * 主要内容有：上报、审核成功、审核失败、整改
	 * 注意事项：当前用户不用推送
	 * @param user 用户
	 * @param resUrl 菜单路径
	 * @param content 消息内容
	 */
	void gczlPushMsgV2(String type,String msgtype,Object obj,User user,String resUrl,String content);
		
	/**
	 * 风险上报消息推送
	 * @param user 用户
	 * @param resUrl 菜单路径
	 * @param content 消息内容
	 */
	void riskUpPushMsg(User user,String resUrl,String content);
}
