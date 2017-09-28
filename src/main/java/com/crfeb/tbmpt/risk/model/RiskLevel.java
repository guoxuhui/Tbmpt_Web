package com.crfeb.tbmpt.risk.model;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotations.IdType;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;

/**
 *
 * 风控级别表
 *
 */
@TableName("RISK_LEVEL")
public class RiskLevel implements Serializable {

	@TableField(exist = false)
	private static final long serialVersionUID = 1L;

	/** 主键生成规则:长年号:2016+四位自增号 0001 */
	@TableId(type = IdType.UUID)
	private String id;

	/** 风险级别名称 */
	@TableField(value = "LEVEL_NAME")
	private String levelName;

	/** 颜色标识 */
	@TableField(value = "COLOR_FLAG")
	private String colorFlag;

	/** 说明 */
	@TableField(value = "RISK_DESC")
	private String riskDesc;

	/** 显示排序 */
	private String sort;


	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getLevelName() {
		return this.levelName;
	}

	public void setLevelName(String levelName) {
		this.levelName = levelName;
	}

	public String getColorFlag() {
		return this.colorFlag;
	}

	public void setColorFlag(String colorFlag) {
		this.colorFlag = colorFlag;
	}

	public String getRiskDesc() {
		return this.riskDesc;
	}

	public void setRiskDesc(String riskDesc) {
		this.riskDesc = riskDesc;
	}

	public String getSort() {
		return this.sort;
	}

	public void setSort(String sort) {
		this.sort = sort;
	}

}
