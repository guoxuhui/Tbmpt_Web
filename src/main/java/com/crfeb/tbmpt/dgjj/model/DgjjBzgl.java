package com.crfeb.tbmpt.dgjj.model;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotations.IdType;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;

@TableName("dgjj_bzgl")
public class DgjjBzgl implements Serializable {
	@TableId(type = IdType.UUID)
	private String id;
	
	@TableField(value = "FID")
	private String fid;
	
	@TableField(value = "BZ_NAME")
	private String bzname;
	
	@TableField(value = "START_TIME")
	private String startTime;
	
	@TableField(value = "END_TIME")
	private String endTime;

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

	public String getBzname() {
		return bzname;
	}

	public void setBzname(String bzname) {
		this.bzname = bzname;
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

	
	
	
	
	

}
