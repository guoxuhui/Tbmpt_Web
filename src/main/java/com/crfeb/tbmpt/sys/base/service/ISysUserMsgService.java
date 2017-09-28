package com.crfeb.tbmpt.sys.base.service;

import com.crfeb.tbmpt.commons.utils.PageInfo;
import com.crfeb.tbmpt.sys.base.model.SysUserMsg;
import com.crfeb.tbmpt.sys.model.User;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.baomidou.framework.service.ICommonService;

/**
 * <p>用户消息信息 业务层接口 Service</p>
 * <p>模块：系统消息模块</p>
 * <p>日期：2017-06-03</p>
 * @version 1.0
 * @author wl_wpg
 */
public interface ISysUserMsgService extends ICommonService<SysUserMsg> {

	void selectDataGrid(PageInfo pageInfo,User user);
	
	/**
     * 方法说明：按照分类更新全部消息已读状态 type为空 或者 ALL是 标记全部未读消息
     * @param type 消息分类名称
     * @return 若成功返回成功信息，失败返回失败提示
     * @author xhguo 
     * @Time 2017年6月10日21:09:51
	 */
	void updateMsgState(@Param("userid") String userid,@Param("msgtype") String msgtype);
	
	/**
     * 方法说明：获取消息状态统计 
     * @param userid 用户id
     * @return 若成功返回成功信息，失败返回失败提示
     * @author xhguo 
     * @Time 2017年6月10日21:09:51
	 */
	List<Map<String, String>> getMsgStats(@Param("userid") String userid);
}