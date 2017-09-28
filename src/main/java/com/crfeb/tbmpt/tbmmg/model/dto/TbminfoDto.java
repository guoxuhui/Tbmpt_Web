package com.crfeb.tbmpt.tbmmg.model.dto;

import java.io.Serializable;

/**
 * @description：ProjectinfoQeryVo
 * @author：smxg
 * @date：2016-10-17 11:12
 */
public class TbminfoDto implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String code;
	private String name;

	/** 盾构机类型（1：土压、2：泥水）*/
	private int type;
	
    private String startTime;
    
    private String endTime;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}
}