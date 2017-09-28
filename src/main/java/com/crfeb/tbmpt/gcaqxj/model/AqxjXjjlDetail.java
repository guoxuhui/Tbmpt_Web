package com.crfeb.tbmpt.gcaqxj.model;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotations.IdType;

import com.crfeb.tbmpt.commons.model.BaseModel;
import com.baomidou.mybatisplus.annotations.TableField;import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;

/**
 *
 * AqxjXjjlDetail
 *
 */
@TableName("AQXJ_XJJL_DETAIL")
public class AqxjXjjlDetail extends BaseModel implements Serializable  {

	@TableField(exist = false)
	private static final long serialVersionUID = 1L;

	/** 巡检内容详细表主键id */
	@TableId(type = IdType.UUID)
	private String id;

	/** 巡检点的名称 */
	@TableField(value = "MINGCHENG")
	private String mingcheng;

	/** 检查点的id */
	@TableField(value = "ITEMID")
	private String itemid;

	/** 巡检项详细内容 */
	@TableField(value = "CONTENTXUNJIAN")
	private String contentxunjian;

	/** 1:合格；0：不合格 */
	@TableField(value = "JIANCHAJG")
	private String jianchajg;


	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
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

	public String getContentxunjian() {
		return this.contentxunjian;
	}

	public void setContentxunjian(String contentxunjian) {
		this.contentxunjian = contentxunjian;
	}

	public String getJianchajg() {
		return this.jianchajg;
	}

	public void setJianchajg(String jianchajg) {
		this.jianchajg = jianchajg;
	}

}
