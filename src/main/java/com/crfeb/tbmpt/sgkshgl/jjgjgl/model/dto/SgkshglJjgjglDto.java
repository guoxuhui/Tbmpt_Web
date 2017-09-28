package com.crfeb.tbmpt.sgkshgl.jjgjgl.model.dto;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;

public class SgkshglJjgjglDto implements Serializable{
	
		
		private static final long serialVersionUID = 1L;

		/** null */
		private String id;

		/** null */
		private String lId;

		/** 环号 */
		private Double ring;

		/** 经纬度 */
		private String lng;

		/** 经纬度 */
		private String lat;
		
		/**线路名称*/
		private String lineName;
		
		
		public String getLineName() {
			return lineName;
		}

		public void setLineName(String lineName) {
			this.lineName = lineName;
		}

		public String getId() {
			return this.id;
		}

		public void setId(String id) {
			this.id = id;
		}

		public String getLId() {
			return this.lId;
		}

		public void setLId(String lId) {
			this.lId = lId;
		}

		public Double getRing() {
			return this.ring;
		}

		public void setRing(Double ring) {
			this.ring = ring;
		}

		public String getLng() {
			return this.lng;
		}

		public void setLng(String lng) {
			this.lng = lng;
		}

		public String getLat() {
			return this.lat;
		}

		public void setLat(String lat) {
			this.lat = lat;
		}

	}

