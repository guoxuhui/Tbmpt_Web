package com.crfeb.tbmpt.project.model.vo;

import java.io.Serializable;


public class ProPqInfoVo implements Serializable{
	
	
	/** ID */
	
	private String id;

	/** 片区名称 */
	
	private String pqName;

	/** 片区负责人 */
	
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
