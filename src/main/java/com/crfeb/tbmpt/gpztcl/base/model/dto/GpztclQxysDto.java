package com.crfeb.tbmpt.gpztcl.base.model.dto;

import com.crfeb.tbmpt.gpztcl.base.model.GpztclQxys;

public class GpztclQxysDto extends GpztclQxys {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * 用于表单双击修改 ，传到后台判断是否被修改过；默认值：value='modified'，当前页面不用！
	 */
	private String modified;
	
	/**
	 *  作：保存“子表集合”数据；表单发生提交事件时赋值，
	 */
	private String details;
	
	/**
	 * 线路表 Id,父Id
	 */
	private String xlBh;

	
	public GpztclQxysDto() {
		super();
		
	}



	public String getModified() {
		return modified;
	}

	public void setModified(String modified) {
		this.modified = modified;
	}

	public String getDetails() {
		return details;
	}

	public void setDetails(String details) {
		this.details = details;
	}

	public String getXlBh() {
		return xlBh;
	}

	public void setXlBh(String xlBh) {
		this.xlBh = xlBh;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
   
}
