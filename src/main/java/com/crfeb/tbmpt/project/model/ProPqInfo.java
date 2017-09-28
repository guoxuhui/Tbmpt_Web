package com.crfeb.tbmpt.project.model;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotations.IdType;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;

/**
 *
 * null
 *
 */
@TableName("PRO_PQ_INFO")
public class ProPqInfo implements Serializable {

	@TableField(exist = false)
	private static final long serialVersionUID = 1L;

	/** ID */
	@TableId(type = IdType.UUID)
	private String id;

	/** 片区名称 */
	@TableField(value = "PQ_NAME")
	private String pqName;

	/** 片区负责人 */
	@TableField(value = "PQ_PERSON")
	private String pqPerson;

	/** 电话 */
	private String phone;

	/** 备注 */
	private String beizhu;


	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPqName() {
		return this.pqName;
	}

	public void setPqName(String pqName) {
		this.pqName = pqName;
	}

	public String getPqPerson() {
		return this.pqPerson;
	}

	public void setPqPerson(String pqPerson) {
		this.pqPerson = pqPerson;
	}

	public String getPhone() {
		return this.phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getBeizhu() {
		return this.beizhu;
	}

	public void setBeizhu(String beizhu) {
		this.beizhu = beizhu;
	}

}
