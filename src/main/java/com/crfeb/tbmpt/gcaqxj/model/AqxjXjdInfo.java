package com.crfeb.tbmpt.gcaqxj.model;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotations.IdType;

import com.crfeb.tbmpt.commons.model.BaseModel;
import com.baomidou.mybatisplus.annotations.TableField;import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;

/**
 *
 * AqxjXjdInfo
 *
 */
@TableName("AQXJ_XJD_INFO")
public class AqxjXjdInfo extends BaseModel implements Serializable  {

	@TableField(exist = false)
	private static final long serialVersionUID = 1L;

	/** 二维码使用UUID */
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

	/** 描述加配图 */
	@TableField(value = "XUJIANDIANWEIZHI")
	private String xujiandianweizhi;

	/** 用于对数据进行按需排序 */
	@TableField(value = "XUHAO")
	private String xuhao;

	/** 填写巡检点的特殊描述 */
	@TableField(value = "BEIZHU")
	private String beizhu;

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

	/** 分类id */
	@TableField(value = "TYPEID")
	private String typeId;

	/** 分类名称 */
	@TableField(value = "TYPENAME")
	private String typeName;


	public String getId() {
		return this.id;
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

	public String getXujiandianweizhi() {
		return this.xujiandianweizhi;
	}

	public void setXujiandianweizhi(String xujiandianweizhi) {
		this.xujiandianweizhi = xujiandianweizhi;
	}

	public String getXuhao() {
		return this.xuhao;
	}

	public void setXuhao(String xuhao) {
		this.xuhao = xuhao;
	}

	public String getBeizhu() {
		return this.beizhu;
	}

	public void setBeizhu(String beizhu) {
		this.beizhu = beizhu;
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



	public String getTypeId() {
		return typeId;
	}

	public void setTypeId(String typeId) {
		this.typeId = typeId;
	}

	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}
}
