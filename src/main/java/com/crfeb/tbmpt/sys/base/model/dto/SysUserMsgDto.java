package com.crfeb.tbmpt.sys.base.model.dto;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotations.IdType;

import com.baomidou.mybatisplus.annotations.TableId;

/**
 * <p>用户消息信息 工具类</p>
 * <p>模块：系统消息模块</p>
 * <p>日期：2017-06-03</p>
 * @version 1.0
 * @author wl_wpg
 */
public class SysUserMsgDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/** 消息ID */
	@TableId(type = IdType.UUID)
	private String id;

	/** 消息标题 */
	private String msgname;

	/** 消息内容 */
	private String msgcontent;

	/** 消息分类 */
	private String msgtype;

	/** 消息时间 */
	private String msgtime;
	
	/** 消息图片 */
	private String msgimg;

	/** 消息状态 */
	private String state;

	/** 原始数据表 */
	private String linktable;

	/** 原始记录 */
	private String linkid;

	/** 详细内容URL */
	private String url;

	/** 所属用户ID */
	private String userid;

	/** 所属单位 */
	private String orgzid;

	/** 备注 */
	private String remark;


	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getMsgname() {
		return this.msgname;
	}

	public void setMsgname(String msgname) {
		this.msgname = msgname;
	}

	public String getMsgcontent() {
		return this.msgcontent;
	}

	public void setMsgcontent(String msgcontent) {
		this.msgcontent = msgcontent;
	}

	public String getMsgtype() {
		return this.msgtype;
	}

	public void setMsgtype(String msgtype) {
		this.msgtype = msgtype;
	}

	public String getMsgtime() {
		return this.msgtime;
	}

	public void setMsgtime(String msgtime) {
		this.msgtime = msgtime;
	}

	public String getMsgimg() {
		return msgimg;
	}

	public void setMsgimg(String msgimg) {
		this.msgimg = msgimg;
	}

	public String getState() {
		return this.state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getLinktable() {
		return this.linktable;
	}

	public void setLinktable(String linktable) {
		this.linktable = linktable;
	}

	public String getLinkid() {
		return this.linkid;
	}

	public void setLinkid(String linkid) {
		this.linkid = linkid;
	}

	public String getUrl() {
		return this.url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getUserid() {
		return this.userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getOrgzid() {
		return this.orgzid;
	}

	public void setOrgzid(String orgzid) {
		this.orgzid = orgzid;
	}

	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

}
