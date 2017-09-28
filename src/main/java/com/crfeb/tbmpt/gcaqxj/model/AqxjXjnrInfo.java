package com.crfeb.tbmpt.gcaqxj.model;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotations.IdType;

import com.crfeb.tbmpt.commons.model.BaseModel;
import com.baomidou.mybatisplus.annotations.TableField;import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;

/**
 *
 * AqxjXjnrInfo
 *
 */
@TableName("AQXJ_XJNR_INFO")
public class AqxjXjnrInfo extends BaseModel implements Serializable  {

	@TableField(exist = false)
	private static final long serialVersionUID = 1L;


	/** 主键id */
	@TableId(type = IdType.UUID)
	private String id;

	/** 巡检点的名称 */
	@TableField(value = "MINGCHENG")
	private String mingcheng;

	/** 检查点的主键 */
	@TableField(value = "ITEMID")
	private String itemid;

	/** 用于对数据进行按需排序 */
	@TableField(value = "XUHAO")
	private String xuhao;

	/** 巡检内容 */
	@TableField(value = "CONTENT_XUNJIAN")
	private String contentXunjian;


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

	public String getXuhao() {
		return this.xuhao;
	}

	public void setXuhao(String xuhao) {
		this.xuhao = xuhao;
	}

	public String getContentXunjian() {
		return this.contentXunjian;
	}

	public void setContentXunjian(String contentXunjian) {
		this.contentXunjian = contentXunjian;
	}


	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
}
