package com.crfeb.tbmpt.zsjk.report.model;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotations.IdType;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;

@TableName("zsjk_report_dgjjcslx")
public class ZsJkReportDgjjcslx implements Serializable{
	
	@TableId(type=IdType.UUID)
	private String id;
	
	/**
	 * 编号
	 */
	@TableField(value="code")
	private String code;
	
	/**
	 * 名称
	 */
	@TableField(value="name")
	private String name;
	
	/**
	 * 上级ID
	 */
	@TableField(value="pid")
	private String pid;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPid() {
		return pid;
	}

	public void setPid(String pid) {
		this.pid = pid;
	}
	
	
	
	

}
