package com.crfeb.tbmpt.sddzgl.model;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotations.IdType;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.crfeb.tbmpt.commons.utils.CommUtils;

/**
 * <p>钻孔信息实体类</p>
 * <p>模块：隧道地质管理</p>
 * <p>日期：2017-03-28</p>
 * @version 1.0
 * @author wl_wpg
 */ 
@TableName("SDDZGL_ZKXX")
public class SddzglZkxx implements Serializable{


	/** ID */
	@TableId(type = IdType.UUID)
	private String id;
	
	/** 钻孔编号 */
	private String zkbh;
	
	/** 钻孔施工单位 */
	private String zksgdw;
	
	/** 钻孔类型 */
	private String zklx;
	
	/** 项目Id */
	private String xmId;
	
	/** 区间Id */
	private String qjId;
	
	/** 线路Id */
	private String xlId;
	
	/** 钻孔位置(X) */
	private String zkwzX;
	
	/** 钻孔位置(Y) */
	private String zkwzY;
	
	/** 对应环号 */
	private String dyhh;
	
	/** 钻孔里程 */
	private String zklc;
	
	/** 备注 */
	private String bz;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getZkbh() {
		return zkbh;
	}
	public void setZkbh(String zkbh) {
		this.zkbh = zkbh;
	}
	public String getZksgdw() {
		return zksgdw;
	}
	public void setZksgdw(String zksgdw) {
		this.zksgdw = zksgdw;
	}
	public String getZklx() {
		return zklx;
	}
	public void setZklx(String zklx) {
		this.zklx = zklx;
	}
	
	public String getXmId() {
		return xmId;
	}
	public void setXmId(String xmId) {
		this.xmId = xmId;
	}
	public String getQjId() {
		return qjId;
	}
	public void setQjId(String qjId) {
		this.qjId = qjId;
	}
	public String getXlId() {
		return xlId;
	}
	public void setXlId(String xlId) {
		this.xlId = xlId;
	}
	public String getZkwzX() {
		return zkwzX;
	}
	public void setZkwzX(String zkwzX) {
		this.zkwzX = zkwzX;
	}
	public String getZkwzY() {
		return zkwzY;
	}
	public void setZkwzY(String zkwzY) {
		this.zkwzY = zkwzY;
	}
	public String getDyhh() {
		return dyhh;
	}
	public void setDyhh(String dyhh) {
		this.dyhh = dyhh;
	}
	public String getZklc() {
		return zklc;
	}
	public void setZklc(String zklc) {
		this.zklc = zklc;
	}
	public String getBz() {
		return bz;
	}
	public void setBz(String bz) {
		this.bz = bz;
	}
	
	
}
