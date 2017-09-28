package com.crfeb.tbmpt.gpztcl.base.model.dto;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotations.IdType;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.crfeb.tbmpt.commons.model.BaseModel;



public class GpztclXyfysDto  implements Serializable{

	
	private static final long serialVersionUID = 1L;
	
	
	private String id;
	
	
	private String fid;
	
	
	private double qdZh;
	
	
	private double zdZh;
	
	
	private double qdZbX;
	
	
	private double qdZbY;
	
	
	private double qdFwj;
	
	
	private double qdBj;
	
	
	private double zdBj;
	
	
	private double zx;
	
	
	private double qdPyl;
	
	
	private double zdPyl;
	
	private String details;
	
	private String proName;
	
	private String sectionName;
	
	private String lineName;
	
	private String proId;
	
	private String sectionId;
	
	private String xlId;
	

	public String getProId() {
		return proId;
	}

	public void setProId(String proId) {
		this.proId = proId;
	}

	public String getSectionId() {
		return sectionId;
	}

	public void setSectionId(String sectionId) {
		this.sectionId = sectionId;
	}

	public String getXlId() {
		return xlId;
	}

	public void setXlId(String xlId) {
		this.xlId = xlId;
	}

	public String getDetails() {
		return details;
	}

	public void setDetails(String details) {
		this.details = details;
	}

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
	
	public String getProName() {
		return proName;
	}

	public void setProName(String proName) {
		this.proName = proName;
	}

	public String getLineName() {
		return lineName;
	}

	public void setLineName(String lineName) {
		this.lineName = lineName;
	}

	public String getSectionName() {
		return sectionName;
	}

	public void setSectionName(String sectionName) {
		this.sectionName = sectionName;
	}


}
