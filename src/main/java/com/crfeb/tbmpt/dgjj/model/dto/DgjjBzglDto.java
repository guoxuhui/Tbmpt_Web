package com.crfeb.tbmpt.dgjj.model.dto;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotations.IdType;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;



public class DgjjBzglDto implements Serializable {
	
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
		
		private String lineName;
		
		private String sectionName;
		
		private String proName;
						
		private String proId;
		
		private String sectionId;
		
		private String lineId;
		
		private String details;
		
		

		public String getDetails() {
			return details;
		}

		public void setDetails(String details) {
			this.details = details;
		}

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

		public String getLineId() {
			return lineId;
		}

		public void setLineId(String lineId) {
			this.lineId = lineId;
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

		public String getProName() {
			return proName;
		}

		public void setProName(String proName) {
			this.proName = proName;
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

		

		
		
		

	}


