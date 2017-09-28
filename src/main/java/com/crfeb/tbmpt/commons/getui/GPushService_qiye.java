package com.crfeb.tbmpt.commons.getui;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.crfeb.tbmpt.commons.getui.model.PushMessage;
import com.gexin.rp.sdk.base.IPushResult;
import com.gexin.rp.sdk.base.impl.ListMessage;
import com.gexin.rp.sdk.base.impl.SingleMessage;
import com.gexin.rp.sdk.base.impl.Target;
import com.gexin.rp.sdk.http.IGtPush;
import com.gexin.rp.sdk.template.APNTemplate;
import com.gexin.rp.sdk.template.NotificationTemplate;
import com.gexin.rp.sdk.template.TransmissionTemplate;

public class GPushService_qiye {
	private static Logger log = LogManager.getLogger(GPushService_qiye.class);

	static String appId = "oZHa8u7nLg76b7ibQCWsOA";
	static String appkey = "1DqsiLWXVv6cDxjgXsDYZA";
	static String master = "dNK6TBwjoD6vHmrmB1mKn7";
	
	static String CID = "e83a6de24aea0411b67a535e49deb9dd";
	static IGtPush push;
	
	private GPushService_qiye() {
		push = new IGtPush(appkey, master);
		try {
			push.connect();
		} catch (IOException e) {
			log.error("GPushService_qiye push error ...");  
		}
	}
	
	private static GPushService_qiye gps = new GPushService_qiye();
	public static GPushService_qiye getInstace(){
		return gps;
	}

	
	@SuppressWarnings("deprecation")
	private APNTemplate APNTemplateComm(String content)
			throws Exception {
		APNTemplate template = new APNTemplate();
		template.setPushInfo("", 1, content,"default", content, "","","");
		return template;
	}
	
	private NotificationTemplate NotificationTemplateComm(String content)
			throws Exception {
		NotificationTemplate template = new NotificationTemplate();
		template.setAppId(appId);
		template.setAppkey(appkey);
		template.setTitle("报警消息");
		template.setText(content);
		template.setLogo("ic_launcher.png");
		template.setIsRing(true);
		//template.setIsVibrate(true);
		//template.setIsClearable(true);
		template.setTransmissionType(1);
		template.setTransmissionContent("dddd");
		return template;
	}
	
	private TransmissionTemplate TransmissionTemplate(String d_id,String u_id,String type,String context) {
	    TransmissionTemplate template = new TransmissionTemplate();
	    template.setAppId(appId);
	    template.setAppkey(appkey);
	    // 透传消息设置，1为强制启动应用，客户端接收到消息后就会立即启动应用；2为等待应用启动
	    template.setTransmissionType(2);
	    template.setTransmissionContent(d_id+"#"+u_id+"#"+type+"#"+context);
	    return template;
	}
	
	public void sendMsgSingTransm(String uid,String d_id,String u_id,String type,String context){
		
		try {
			if(push.connect()){
				TransmissionTemplate template = TransmissionTemplate(d_id,u_id,type,context);
				SingleMessage message = new SingleMessage();
				message.setOffline(true);
				message.setOfflineExpireTime(1 * 1000 * 3600);
				message.setData(template);
				//message.setPushNetWorkType(1); //根据WIFI推送设置

				Target target1 = new Target();
				target1.setAppId(appId);
				target1.setClientId(uid);	//根据别名推送设置，CID与Alias可二选一进行推送
				
				IPushResult ret = push.pushMessageToSingle(message, target1);
				log.error(ret.getResponse().toString());

			}
		} catch (Exception e) {
			log.error("GPushService_qiye sendMsgSingTransm push error ...");  
		}
	}
	
	public void sendMsgTransm(List<String> targets,String content){
		PushMessage pm = new PushMessage();
		pm.setMsgType(PushMessage.TRANSM);
		pm.setTargets(targets);
		pm.setContent(content);
		GPushBuffer.getInstance().offer(pm);
	}
	
	public void sendMsgTransmToPush(List<String> uids,String d_id,String u_id,String type,String context){
		
		try {
			if(push.connect()){
				TransmissionTemplate template = TransmissionTemplate(d_id,u_id,type,context);
				ListMessage message = new ListMessage();
				message.setOffline(true);
				message.setOfflineExpireTime(1 * 1000 * 3600);
				message.setData(template);
				//message.setPushNetWorkType(1); //根据WIFI推送设置

				List<Target> targets = new ArrayList<Target>();
				List<String> apntargets = new ArrayList<String>();
				for(String uid : uids){
					if(uid.length() == 32){
						Target target1 = new Target();
						target1.setAppId(appId);
						target1.setClientId(uid);	//根据别名推送设置，CID与Alias可二选一进行推送
						targets.add(target1);
					}else if(uid.length() > 32){
						apntargets.add(uid);
					}
				}
				String taskId = push.getContentId(message, "toList_Android_MsgTransm_Push");
				IPushResult ret = push.pushMessageToList(taskId, targets);
				log.error(ret.getResponse().toString());

			}
		} catch (Exception e) {
			log.error("GPushService_qiye sendMsgTransm push error ...");  
		}
		
	}
	
	public void sendCommSingMsg(String uid,String content){
		PushMessage pm = new PushMessage();
		pm.setMsgType(PushMessage.COMM);
		List<String> targets = new ArrayList<String>();
		targets.add(uid);
		pm.setTargets(targets);
		pm.setContent(content);
		GPushBuffer.getInstance().offer(pm);
	}
	
	public String sendCommSingMsgToPush(String uid,String content){
		
		String result = null;
		try {
			if(push.connect()){
				NotificationTemplate template = NotificationTemplateComm(content);
				SingleMessage message = new SingleMessage();
				message.setOffline(true);
				message.setOfflineExpireTime(1 * 1000 * 3600);
				message.setData(template);
				//message.setPushNetWorkType(1); //根据WIFI推送设置

				Target target1 = new Target();
				target1.setAppId(appId);
				target1.setClientId(uid);	//根据别名推送设置，CID与Alias可二选一进行推送
				
				if(uid.length() == 32){
					IPushResult ret = push.pushMessageToSingle(message, target1);
					result = ret.getResponse().toString();
				}else if(uid.length() > 32){
					APNTemplate apnvoice = APNTemplateComm(content);
					SingleMessage sm = new SingleMessage();
					sm.setData(apnvoice);
					IPushResult apnret = push.pushAPNMessageToSingle(appId, uid, sm);
					result = apnret.getResponse().toString();
				}
			}
		} catch (Exception e) {
			log.error("GPushService_qiye sendCommSingMsg push error ...");  
		}
		
		return result;
	}
	
	public void sendCommMultiMsgToPush(List<String> uids,String content){
		
		try {
			if(push.connect()){
				NotificationTemplate template = NotificationTemplateComm(content);
				ListMessage message = new ListMessage();
				message.setOffline(true);
				message.setOfflineExpireTime(1 * 1000 * 3600);
				message.setData(template);
				//message.setPushNetWorkType(1); //根据WIFI推送设置

				List<Target> targets = new ArrayList<Target>();
				List<String> apntargets = new ArrayList<String>();
				for(String uid : uids){
					if(uid.length() == 32){
						Target target1 = new Target();
						target1.setAppId(appId);
						target1.setClientId(uid);	//根据别名推送设置，CID与Alias可二选一进行推送
						targets.add(target1);
					}else if(uid.length() > 32){
						apntargets.add(uid);
					}
				}

				if(targets.size() > 0){
					String taskId = push.getContentId(message, "toList_Android_Alarm_Push");
					IPushResult ret = push.pushMessageToList(taskId, targets);
					log.error(ret.getResponse().toString());
					log.error("GPushService_qiye opt taskid="+taskId +"   targets="+targets.size());
				}

				if(apntargets.size() > 0){
					APNTemplate apnvoice = APNTemplateComm(content);
					ListMessage lm = new ListMessage();
					lm.setData(apnvoice);
					String contentId = push.getAPNContentId(appId, lm);
					IPushResult apnret = push.pushAPNMessageToList(appId, contentId, apntargets);
					log.error(apnret.getResponse());
					log.error("GPushService_qiye opt APN targets="+targets.size());
				}

			}
		} catch (Exception e) {
			log.error("GPushService_qiye sendCommMultiMsgToPush push error ...");  
		}
		
	}
	
	
	public static void main(String[] args) {
		log.error(GPushService.getInstace().sendCommSingMsgToPush(
				"e2c04dde4ec36434a62bfadffc09503a", 
				"手机号码为11111111111的用户试图绑定您的设备HQ0000000000，确定本次绑定请输入验证码1234，否则，请忽略本消息。"));
	}
}