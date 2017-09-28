package com.crfeb.tbmpt.open.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.crfeb.tbmpt.commons.getui.GPushService;
import com.crfeb.tbmpt.commons.utils.DateUtils;
import com.crfeb.tbmpt.commons.utils.StringUtils;
import com.crfeb.tbmpt.gczl.base.model.GczlYdxjGPZLXJInfo;
import com.crfeb.tbmpt.gczl.base.model.GczlYdxjSGZLXJInfo;
import com.crfeb.tbmpt.open.service.IOpenPushService;
import com.crfeb.tbmpt.sys.base.model.SysUserMsg;
import com.crfeb.tbmpt.sys.base.service.ISysUserMsgService;
import com.crfeb.tbmpt.sys.model.User;
import com.crfeb.tbmpt.sys.service.IUserService;

/**
 * <p>移动端数据推送模块API数据服务Service接口实现类</p>
 * <p>系统：移动端接口</p>
 * <p>模块：数据推送</p>
 * <p>日期：2016-12-07</p>
 * @version 1.0
 * @author smxg
 */
@Service
public class IOpenPushServiceImpl implements IOpenPushService{


	@Autowired
	private IUserService iUserService;
	@Autowired
	private ISysUserMsgService sysUserMsgService;
	
	
	@Override
	public void gczlPushMsg(User user,String resUrl,String content) {
		 List<String> cidList = this.iUserService.selectUserCId(resUrl,user.getId());
		 GPushService.getInstace().sendCommMultiMsg(cidList, content);
	}
	
	@Override
	public void gczlPushMsgV2(String type,String msgtype,Object obj,User user,String resUrl,String content){
		List<String> cidList = this.iUserService.selectUserCId(resUrl,user.getId());
		GPushService.getInstace().sendCommMultiMsg(cidList, content);
		
		List<User> uList = this.iUserService.selectUserByRes(resUrl,user.getId());
		for(User u:uList){
			if("施工".equals(type)){
				GczlYdxjSGZLXJInfo info = (GczlYdxjSGZLXJInfo)obj;
				//进行消息推送以及消息写入用户消息表
		    	SysUserMsg msg = new SysUserMsg();
				msg.setMsgname("施工巡检"+msgtype+"消息");
				msg.setMsgcontent("施工内容："+info.getSgNr()+"，  具体位置："+info.getJtWz()+"，巡检人： "+info.getXjRy());
				msg.setMsgtype("工程质量巡检消息");
				msg.setMsgtime(DateUtils.getToday());
				msg.setState("0");
				msg.setLinktable("GCZLYDXJ_SGZL_XJ_INFO");
				msg.setLinkid(info.getId());
				msg.setUrl("/open/gczl/gczlSGZLXJInfoPage?id="+info.getId());
				msg.setUserid(user.getId());
				msg.setOrgzid(user.getOrgzId());
				sysUserMsgService.insert(msg);
			}else if("管片".equals(type)){
				GczlYdxjGPZLXJInfo info = (GczlYdxjGPZLXJInfo)obj;
				//进行消息推送以及消息写入用户消息表
		    	SysUserMsg msg = new SysUserMsg();
				msg.setMsgname("管片巡检"+msgtype+"消息");
				msg.setMsgcontent("问题质量分类："+info.getTypeName()+"，情况描述"+info.getQkms()+"，巡检人： "+info.getXjry());
				msg.setMsgtype("工程质量巡检消息");
				msg.setMsgtime(DateUtils.getToday());
				msg.setState("0");
				msg.setLinktable("GCZLYDXJ_SGZL_XJ_INFO");
				msg.setLinkid(info.getId());
				msg.setUrl("/open/gczl/gczlGPZLXJInfoPage?id="+info.getId());
				msg.setUserid(user.getId());
				msg.setOrgzid(user.getOrgzId());
				sysUserMsgService.insert(msg);
			}
			
		}
		
	}

	/**
	 * 风险上报消息推送
	 * @param user 用户
	 * @param resUrl 菜单路径
	 * @param content 消息内容
	 */
	public void riskUpPushMsg(User user,String resUrl,String content){
		List<String> cidList = new ArrayList<String>();
		if(user!=null && StringUtils.isNotBlank(user.getCid())){
			cidList.add(new String(user.getCid()));
			GPushService.getInstace().sendCommMultiMsg(cidList, content);
		}
		
	}
}
