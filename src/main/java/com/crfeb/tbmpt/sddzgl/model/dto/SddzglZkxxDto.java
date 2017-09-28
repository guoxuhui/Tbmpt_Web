package com.crfeb.tbmpt.sddzgl.model.dto;

import java.io.Serializable;

import com.crfeb.tbmpt.sddzgl.model.SddzglZkxx;


/**
 * <p>钻孔信息实体工具类</p>
 * <p>模块：隧道地质管理</p>
 * <p>日期：2017-03-28</p>
 * @version 1.0
 * @author wl_wpg
 */
public class SddzglZkxxDto extends SddzglZkxx implements Serializable{

	/** 项目名称 */
	private String xmMc; 
	
	/** 区间名称 */
	private String qjMc; 
	
	/** 线路名称 */
	private String xlMc;

	public String getXmMc() {
		return xmMc;
	}

	public void setXmMc(String xmMc) {
		this.xmMc = xmMc;
	}

	public String getQjMc() {
		return qjMc;
	}

	public void setQjMc(String qjMc) {
		this.qjMc = qjMc;
	}

	public String getXlMc() {
		return xlMc;
	}

	public void setXlMc(String xlMc) {
		this.xlMc = xlMc;
	} 
	
}
