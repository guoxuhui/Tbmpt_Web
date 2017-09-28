package com.crfeb.tbmpt.dmcjjc.dd.model;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;

/**
 *
 * 基础数据
 *
 */
@TableName("dmcj_dd_info")
public class DdInfo implements Serializable {

	@TableField(exist = false)
	private static final long serialVersionUID = 1L;

	/** 主键id */
	@TableId(value = "id")
	private String id;

	/** 分类名称 */
	@TableField(value = "typename")
	private String typeName;

	/** 数据名称 */
	@TableField(value = "ddname")
	private String ddName;
	
	/** 排序号 */
	@TableField(value = "sortnum")
	private Integer sortNum;

	/** 备注 */
	@TableField(value = "remarks")
	private String remarks;
	
	/** 使用状态 */
	@TableField(value = "ifqy")
	private String ifqy;

	public String getIfqy() {
		return ifqy;
	}

	public void setIfqy(String ifqy) {
		this.ifqy = ifqy;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	public String getDdName() {
		return ddName;
	}

	public void setDdName(String ddName) {
		this.ddName = ddName;
	}

	public Integer getSortNum() {
		return sortNum;
	}

	public void setSortNum(Integer sortNum) {
		this.sortNum = sortNum;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

}
