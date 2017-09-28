package com.crfeb.tbmpt.gcaqxj.model;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotations.IdType;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.crfeb.tbmpt.commons.model.BaseModel;

/**
 *
 * null
 *
 */
@TableName("AQXJ_XJFL_INFO")
public class AqxjXjflInfo extends BaseModel implements Serializable {

	@TableField(exist = false)
	private static final long serialVersionUID = 1L;

	/** 主键id */
	@TableId(type = IdType.UUID)
	private String id;

	/** 分类名称 */
	@TableField(value = "typeName")
	private String typeName;

	/** 备注信息 */
	@TableField(value = "remark")
	private String remark;

	/** 是否为大类࣬ 0 大类  1 小类 */
	@TableField(value = "isParent")
	private String isParent;

	/** 项目ID */
	@TableField(value = "proiectId")
	private String proiectId;

	/** 项目名称 */
	@TableField(value = "proiectName")
	private String proiectName;

	/**父节点id*/
	@TableField(value = "parentId")
	private String parentId;


	/**父节点名称*/
	@TableField(value = "parentName")
	private String parentName;


	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTypeName() {
		return this.typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getIsParent() {
		return this.isParent;
	}

	public void setIsParent(String isParent) {
		this.isParent = isParent;
	}

	public String getProiectId() {
		return this.proiectId;
	}

	public void setProiectId(String proiectId) {
		this.proiectId = proiectId;
	}

	public String getProiectName() {
		return proiectName;
	}

	public void setProiectName(String proiectName) {
		this.proiectName = proiectName;
	}

	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	public String getParentName() {
		return parentName;
	}

	public void setParentName(String parentName) {
		this.parentName = parentName;
	}
}
