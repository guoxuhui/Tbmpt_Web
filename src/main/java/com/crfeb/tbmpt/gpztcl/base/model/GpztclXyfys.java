package com.crfeb.tbmpt.gpztcl.base.model;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotations.IdType;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.crfeb.tbmpt.commons.model.BaseModel;

@TableName("gpztcl_xyfys")
public class GpztclXyfys  implements Serializable {

	/**
	 * 
	 */
	
	
	@TableId(type = IdType.UUID)
	private String id;
	
	@TableField(value = "FID")
	private String fid;
	
	@TableField(value = "QDZH")
	private double qdZh;
	
	@TableField(value = "ZDZH")
	private double zdZh;
	
	@TableField(value = "QDZBX")
	private double qdZbX;
	
	@TableField(value = "QDZBY")
	private double qdZbY;
	
	@TableField(value = "QDFWJ")
	private double qdFwj;
	
	@TableField(value = "QDBJ")
	private double qdBj;
	
	@TableField(value = "ZDBJ")
	private double zdBj;
	
	@TableField(value = "ZX")
	private double zx;
	
	@TableField(value = "QDPYL")
	private double qdPyl;
	
	@TableField(value = "ZDPYL")
	private double zdPyl;

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

	public double getQdZh() {
		return qdZh;
	}

	public void setQdZh(double qdZh) {
		this.qdZh = qdZh;
	}

	public double getZdZh() {
		return zdZh;
	}

	public void setZdZh(double zdZh) {
		this.zdZh = zdZh;
	}

	public double getQdZbX() {
		return qdZbX;
	}

	public void setQdZbX(double qdZbX) {
		this.qdZbX = qdZbX;
	}

	
	public double getQdZbY() {
		return qdZbY;
	}

	public void setQdZbY(double qdZbY) {
		this.qdZbY = qdZbY;
	}

	public double getQdFwj() {
		return qdFwj;
	}

	public void setQdFwj(double qdFwj) {
		this.qdFwj = qdFwj;
	}

	public double getQdBj() {
		return qdBj;
	}

	public void setQdBj(double qdBj) {
		this.qdBj = qdBj;
	}

	public double getZdBj() {
		return zdBj;
	}

	public void setZdBj(double zdBj) {
		this.zdBj = zdBj;
	}

	public double getZx() {
		return zx;
	}

	public void setZx(double zx) {
		this.zx = zx;
	}

	public double getQdPyl() {
		return qdPyl;
	}

	public void setQdPyl(double qdPyl) {
		this.qdPyl = qdPyl;
	}

	public double getZdPyl() {
		return zdPyl;
	}

	public void setZdPyl(double zdPyl) {
		this.zdPyl = zdPyl;
	}
	
	
	
}
