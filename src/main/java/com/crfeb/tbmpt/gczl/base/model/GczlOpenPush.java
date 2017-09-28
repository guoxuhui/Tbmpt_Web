package com.crfeb.tbmpt.gczl.base.model;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotations.IdType;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;

@TableName("GCZL_XJTS")
public class GczlOpenPush implements Serializable{

	@TableId(type = IdType.UUID)
	private String id;
	
	/**保存施工信息的id*/
	@TableField(value = "GCZL_ID")
	private String gczlId;
	/**保存用户信息的id*/
	@TableField(value = "USER_ID")
	private String userId;
	/**保存施工信息的类型，GP或者SG*/
	@TableField(value = "TYPE")
	private String type;
	/**内容描述*/
	@TableField(value = "NRMS")
	private String nrms;
	/**巡检照片缩略图*/
	@TableField(value = "XJZPSLT")
	private String xjzpslt;
	/**创建时间*/
	@TableField(value = "CREATE_TIME")
	private String createTime;
	/**当前状态*/
	@TableField(value = "ZT")
	private String zt;
	@TableField(value = "XJRY")
	private String xjry;
	@TableField(value = "UPDATE_TIME")
	private String updateTime;
	
	public String getXjry() {
		return xjry;
	}
	public void setXjry(String xjry) {
		this.xjry = xjry;
	}
	
	
	public String getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getGczlId() {
		return gczlId;
	}
	public void setGczlId(String gczlId) {
		this.gczlId = gczlId;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getNrms() {
		return nrms;
	}
	public void setNrms(String nrms) {
		this.nrms = nrms;
	}
	public String getXjzpslt() {
		return xjzpslt;
	}
	public void setXjzpslt(String xjzpslt) {
		this.xjzpslt = xjzpslt;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	public String getZt() {
		return zt;
	}
	public void setZt(String zt) {
		this.zt = zt;
	}
	
	
	
}
