package com.crfeb.tbmpt.open.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONArray;
import com.crfeb.tbmpt.commons.base.BaseController;
import com.crfeb.tbmpt.commons.utils.PageInfo;
import com.crfeb.tbmpt.commons.utils.StringUtils;
import com.crfeb.tbmpt.open.service.IOpenCommService;
import com.crfeb.tbmpt.sys.base.model.SysUserMsg;
import com.crfeb.tbmpt.sys.base.service.ISysUserMsgService;
import com.crfeb.tbmpt.sys.model.User;

/**
 * @description：用户消息
 * @author：smxg @date：2016-10-10 11:12
 */
@Controller
@RequestMapping("/open/msg")
public class UserMsgController extends BaseController {
	private static final Logger LOGGER = LogManager.getLogger(UserMsgController.class);
	@Autowired
	IOpenCommService openCommServiceImpl;
	@Autowired 
	private ISysUserMsgService sysUserMsgService;

	/**
     * 方法说明：获取消息状态统计 
     * @return 若成功返回成功信息，失败返回失败提示
     * @author xhguo 
     * @Time 2017年6月10日21:09:51
	 */
	@RequestMapping(value = "/getMsgStats")
	@ResponseBody
	public Object getMsgStats() {
		User user = getCurrentUser();
		if (user == null) {
			LOGGER.debug("您尚未登录或登录时间过长,请重新登录!");
			return renderError("您尚未登录或登录时间过长,请重新登录!");
		}
		int sum = 0;
		Map<String,Object> map = new HashMap<String,Object>();
		List<Map<String, String>> list = null;
        try {
        	list = sysUserMsgService.getMsgStats(user.getId());
        	for(Map<String, String> mm : list){
        		sum = sum + Integer.parseInt(mm.get("SUM"));
        	}
        } catch (Exception e) {
           e.printStackTrace();
        }
        map.put("sum", sum);
        map.put("list", list);
        return renderSuccess(map);
	}
	
	/**
     * 方法说明：获取消息分类列表 按照分页查询 每页默认10条 
     * @param type 分类类型名称
     * @param page 当前页数
     * @return 若成功返回成功信息，失败返回失败提示
     * @author xhguo 
     * @Time 2017年6月10日21:09:51
	 */
	@RequestMapping(value = "/getMsgByType")
	@ResponseBody
	public Object getMsgByType(String type,int page) {
		User user = getCurrentUser();
		if (user == null) {
			return renderError("您尚未登录或登录时间过长,请重新登录!");
		}
		if (StringUtils.isBlank(type)) {
			return renderError("参数异常!");
		}
		PageInfo pageInfo = new PageInfo(page, 10, "msgtime", "desc");
        Map<String, Object> condition = new HashMap<String, Object>();
        pageInfo.setCondition(condition);
        condition.put("msgtype", type);
		
        try {
        	sysUserMsgService.selectDataGrid(pageInfo,getCurrentUser());
        } catch (Exception e) {
           e.printStackTrace();
        }
        
        Object json = null;
		if(pageInfo.getTotal()<(page-1)*10){
			json = new JSONArray();
		}else{
			json = JSONArray.toJSON(pageInfo.getRows());
		}
        
		return renderSuccess(json);
	}
	
	/**
     * 方法说明：单条更新消息已读状态
     * @param id 消息ID
     * @return 若成功返回成功信息，失败返回失败提示
     * @author xhguo 
     * @Time 2017年6月10日21:09:51
	 */
	@RequestMapping(value = "/unReaderState")
	@ResponseBody
	public Object unReaderState(String id) {
		User user = getCurrentUser();
		if (user == null) {
			return renderError("您尚未登录或登录时间过长,请重新登录!");
		}
		if (StringUtils.isBlank(id)) {
			return renderError("参数异常!");
		}
		SysUserMsg sysUserMsg = sysUserMsgService.selectById(id);
    	if(sysUserMsg !=null && StringUtils.isNotBlank(sysUserMsg.getId())){
    		if(sysUserMsg.getState().equals("0")){
    			sysUserMsg.setState("1");
    		}
    		if(sysUserMsgService.updateById(sysUserMsg))
            {
    			this.log("标记状态", true, "用户消息标记状态操作成功.id:"+id);
    			LOGGER.info("用户消息标记状态操作成功.id:"+id);
            }
    	}
		return renderSuccess("操作成功！");
	}
	
	/**
     * 方法说明：按照分类更新全部消息已读状态 type为空 或者 ALL是 标记全部未读消息
     * @param type 消息分类名称
     * @return 若成功返回成功信息，失败返回失败提示
     * @author xhguo 
     * @Time 2017年6月10日21:09:51
	 */
	@RequestMapping(value = "/unReaderStateAll")
	@ResponseBody
	public Object unReaderStateAll(String type) {
		User user = getCurrentUser();
		if (user == null) {
			LOGGER.debug("您尚未登录或登录时间过长,请重新登录!");
			return renderError("您尚未登录或登录时间过长,请重新登录!");
			
		}
		sysUserMsgService.updateMsgState(user.getId(), type);
		return renderSuccess("操作成功！");
	}

}
