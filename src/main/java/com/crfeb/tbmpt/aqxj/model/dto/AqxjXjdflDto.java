package com.crfeb.tbmpt.aqxj.model.dto;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotations.IdType;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.crfeb.tbmpt.commons.model.BaseModel;

public class AqxjXjdflDto extends BaseModel implements Serializable{
	
	 private static final long serialVersionUID = 1L;
	 
	 
	 /**
	     * 
	     * 主键id
	     */
		private String id;
		
		/**
	     * 
	     * 分类名称
	     */
		private String fenleiMc;
		
		/**
	     * 
	     * 备注
	     */
		private String beiZ;
		
		/**
	     * 
	     * 工程编号
	     */
		private String gcBh;
		
		/**
	     * 
	     * 工程项目名称
	     */
		private String gcMc;
		
		/**
	     * 
	     * 父id
	     */
		private String pid;
		
		private String pName;
		
		
		

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

		public static long getSerialversionuid() {
			return serialVersionUID;
		}

		public String getpName() {
			return pName;
		}

		public void setpName(String pName) {
			this.pName = pName;
		}
		
		
		
		

}
