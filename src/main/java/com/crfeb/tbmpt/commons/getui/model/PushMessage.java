package com.crfeb.tbmpt.commons.getui.model;

import java.util.Date;
import java.util.List;

/**
 * 消息实体封装
 * @author xhgui
 * @version 1.0
 * @since 1.0
 */
public class PushMessage {

	public static final String COMM = "COMM";
	public static final String TRANSM = "TRANSM";
	public static final String LINK = "LINK";
	public static final String MEDIA = "LINK";
	
	private String msgType;
	private String content;
	private List<String> targets;
	private Date createTime;
	
	
	public String getMsgType() {
		return msgType;
	}
	public void setMsgType(String msgType) {
		this.msgType = msgType;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public List<String> getTargets() {
		return targets;
	}
	public void setTargets(List<String> targets) {
		this.targets = targets;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

}
