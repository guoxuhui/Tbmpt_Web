package com.crfeb.tbmpt.gcaqxj.model;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotations.IdType;

import com.crfeb.tbmpt.commons.model.BaseModel;
import com.baomidou.mybatisplus.annotations.TableField;import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;

/**
 *
 * AqxjXjjlInfo 巡检记录
 *
 */
@TableName("AQXJ_XJJL_INFO")
public class AqxjXjjlInfo extends BaseModel implements Serializable  {

	@TableField(exist = false)
	private static final long serialVersionUID = 1L;

	/** 主键id */
	@TableId(type = IdType.UUID)
	private String id;

	/** 项目部ID */
	@TableField(value = "PROJECTID")
	private String projectid;

	/** 项目部名称 */
	@TableField(value = "PROJECTNAME")
	private String projectname;

	/** 巡检点的名称 */
	@TableField(value = "MINGCHENG")
	private String mingcheng;

	/** 检查点的主键 */
	@TableField(value = "ITEMID")
	private String itemid;

	/** 当前巡检点的分类名称 */
	@TableField(value = "ITEMTYPE")
	private String itemtype;

	/** 巡检点的所在的物理位置 */
	@TableField(value = "ITEMADRESS")
	private String itemadress;

	/** 可多个，用逗号隔开 */
	@TableField(value = "ZERENRID")
	private String zerenrid;

	/** 可多个，用逗号隔开 */
	@TableField(value = "ZERENRMC")
	private String zerenrmc;

	/** 可多个，用逗号隔开 */
	@TableField(value = "JIANDURID")
	private String jiandurid;

	/** 可多个，用逗号隔开 */
	@TableField(value = "JIANDURMC")
	private String jiandurmc;

	/** 巡检点巡检的周期 */
	@TableField(value = "JIANCHAPC")
	private String jianchapc;

	/** 1:合格；0：不合格 */
	@TableField(value = "JIANCHAJG")
	private String jianchajg;

	/** 检查结果描述 */
	@TableField(value = "JIEGUOMS")
	private String jieguoms;

	/** 本次检查时间 */
	@TableField(value = "JIANCHATIME")
	private String jianchatime;

	/** 检查人员姓名 */
	@TableField(value = "JIANCHAPERSON")
	private String jianchaperson;

	/** 是否查看  1 查看  0  未查看 */
	@TableField(value = "ISVIEW")
	private String isView;

	/** 是否推送 1 推送  0  未推送 */
	@TableField(value = "ISSEND")
	private String isSend;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getProjectid() {
		return this.projectid;
	}

	public void setProjectid(String projectid) {
		this.projectid = projectid;
	}

	public String getProjectname() {
		return this.projectname;
	}

	public void setProjectname(String projectname) {
		this.projectname = projectname;
	}

	public String getMingcheng() {
		return this.mingcheng;
	}

	public void setMingcheng(String mingcheng) {
		this.mingcheng = mingcheng;
	}

	public String getItemid() {
		return this.itemid;
	}

	public void setItemid(String itemid) {
		this.itemid = itemid;
	}

	public String getItemtype() {
		return this.itemtype;
	}

	public void setItemtype(String itemtype) {
		this.itemtype = itemtype;
	}

	public String getItemadress() {
		return this.itemadress;
	}

	public void setItemadress(String itemadress) {
		this.itemadress = itemadress;
	}

	public String getZerenrid() {
		return this.zerenrid;
	}

	public void setZerenrid(String zerenrid) {
		this.zerenrid = zerenrid;
	}

	public String getZerenrmc() {
		return this.zerenrmc;
	}

	public void setZerenrmc(String zerenrmc) {
		this.zerenrmc = zerenrmc;
	}

	public String getJiandurid() {
		return this.jiandurid;
	}

	public void setJiandurid(String jiandurid) {
		this.jiandurid = jiandurid;
	}

	public String getJiandurmc() {
		return this.jiandurmc;
	}

	public void setJiandurmc(String jiandurmc) {
		this.jiandurmc = jiandurmc;
	}

	public String getJianchapc() {
		return this.jianchapc;
	}

	public void setJianchapc(String jianchapc) {
		this.jianchapc = jianchapc;
	}

	public String getJianchajg() {
		return this.jianchajg;
	}

	public void setJianchajg(String jianchajg) {
		this.jianchajg = jianchajg;
	}

	public String getJieguoms() {
		return this.jieguoms;
	}

	public void setJieguoms(String jieguoms) {
		this.jieguoms = jieguoms;
	}

	public String getJianchatime() {
		return this.jianchatime;
	}

	public void setJianchatime(String jianchatime) {
		this.jianchatime = jianchatime;
	}

	public String getJianchaperson() {
		return this.jianchaperson;
	}

	public void setJianchaperson(String jianchaperson) {
		this.jianchaperson = jianchaperson;
	}

	public String getIsView() {
		return isView;
	}

	public void setIsView(String isView) {
		this.isView = isView;
	}

	public String getIsSend() {
		return isSend;
	}

	public void setIsSend(String isSend) {
		this.isSend = isSend;
	}
}
