package com.crfeb.tbmpt.gcaqxj.model.dto;

import com.baomidou.mybatisplus.annotations.IdType;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.crfeb.tbmpt.commons.model.BaseModel;

import java.io.Serializable;

/**
 *
 *
 *
 */

public class AqxjXjflInfoDto extends BaseModel implements Serializable {


	private static final long serialVersionUID = 1L;

	/** 主键idUUId */

	private String id;

	/** 分类名称 */
	private String typeName;

	/** 备注信息 */
	private String remark;

	/** 是否为大类࣬ 0 大类  1 小类 */
	private String isParent;

	/** 项目ID */
	private String proiectId;

	/** 项目名称*/
	private  String proiectName;


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
}
