package com.crfeb.tbmpt.dgjj.model;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotations.IdType;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;

@TableName("dgjj_bzgl_emp")
public class DgjjBzglEmp implements Serializable {
	@TableId(type = IdType.UUID)
	private String id;
	@TableField(value = "FID")
	private String fid;
	@TableField(value = "EMP_ID")
	private String empId;
	@TableField(value = "EMP_NAME")
	private String empName;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getFid() {
		return fid;
	}
	public void setFid(String fid) {
		this.fid = fid;
	}
	public String getEmpId() {
		return empId;
	}
	public void setEmpId(String empId) {
		this.empId = empId;
	}
	public String getEmpName() {
		return empName;
	}
	public void setEmpName(String empName) {
		this.empName = empName;
	}
	

}
