package com.crfeb.tbmpt.aqxj.model;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotations.IdType;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.crfeb.tbmpt.commons.model.BaseModel;

@TableName("ZLXJ_XUNJIAN_FL")
public class AqxjXjdfl extends BaseModel implements Serializable{
	
	 /**
     * 
     * 主键id
     */
	@TableId(type = IdType.UUID)
	private String id;
	
	/**
     * 
     * 分类名称
     */
	@TableField(value = "fenlei_mc")
	private String fenleiMc;
	
	/**
     * 
     * 备注
     */
	@TableField(value = "beizhu")
	private String beiZ;
	
	/**
     * 
     * 工程编号
     */
	@TableField(value = "gc_bh")
	private String gcBh;
	
	/**
     * 
     * 工程项目名称
     */
	@TableField(value = "gc_mc")
	private String gcMc;
	
	/**
     * 
     * 父id
     */
	@TableField(value = "pid")
	private String pid;
	
	
	


	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getFenleiMc() {
		return fenleiMc;
	}

	public void setFenleiMc(String fenleiMc) {
		this.fenleiMc = fenleiMc;
	}

	public String getBeiZ() {
		return beiZ;
	}

	public void setBeiZ(String beiZ) {
		this.beiZ = beiZ;
	}

	public String getGcBh() {
		return gcBh;
	}

	public void setGcBh(String gcBh) {
		this.gcBh = gcBh;
	}

	public String getGcMc() {
		return gcMc;
	}

	public void setGcMc(String gcMc) {
		this.gcMc = gcMc;
	}

	public String getPid() {
		return pid;
	}

	public void setPid(String pid) {
		this.pid = pid;
	}


	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
