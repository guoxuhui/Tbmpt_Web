package com.crfeb.tbmpt.gczlys.model.dto;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotations.IdType;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.crfeb.tbmpt.commons.model.BaseModel;

public class YanShouYingXiangDto extends BaseModel implements Serializable{

	 private static final long serialVersionUID = 1L;
	 
	 /**
	     * 
	     * 主键id
	     */
		
		private String id;
		/**
	     * 
	     * 项目id
	     */
	
		private String projectid;
		/**
	     * 
	     * 项目名称
	     */

		private String projectname;
		/**
	     * 
	     * 检查内容及验收情况
	     */

		private String miaoshu;
		/**
	     * 
	     * 工程部位
	     */

		private String gcbuwei;
		/**
	     * 
	     * 天气
	     */

		private String tianqi;
		/**
	     * 
	     * 分部分项工序
	     */

		private String gongxu;
		/**
	     * 
	     * 施工班组
	     */

		private String banzu;
		/**
	     * 
	     * 班组负责人
	     */

		private String bzfuzr;
		/**
	     * 
	     * 检查情况
	     */

		private String jianchaqk;
		
		/**
	     * 
	     * 验收人
	     */
		
		private String yanshour;
		
		/**
	     * 
	     * 验收时间
	     */
		private String yanshousj;
		
		/**
	     * 
	     * 开始验收时间（查询使用）
	     */
		private String startYanshousj;
		
		/**
	     * 
	     * 结束验收时间（查询使用）
	     */
		private String endYanshousj;

	public String getId() {
			return id;
		}

		public void setId(String id) {
			this.id = id;
		}

		public String getProjectid() {
			return projectid;
		}

		public void setProjectid(String projectid) {
			this.projectid = projectid;
		}

		public String getProjectname() {
			return projectname;
		}

		public void setProjectname(String projectname) {
			this.projectname = projectname;
		}

		public String getMiaoshu() {
			return miaoshu;
		}

		public void setMiaoshu(String miaoshu) {
			this.miaoshu = miaoshu;
		}

		public String getGcbuwei() {
			return gcbuwei;
		}

		public void setGcbuwei(String gcbuwei) {
			this.gcbuwei = gcbuwei;
		}

		public String getTianqi() {
			return tianqi;
		}

		public void setTianqi(String tianqi) {
			this.tianqi = tianqi;
		}

		public String getGongxu() {
			return gongxu;
		}

		public void setGongxu(String gongxu) {
			this.gongxu = gongxu;
		}

		public String getBanzu() {
			return banzu;
		}

		public void setBanzu(String banzu) {
			this.banzu = banzu;
		}

		public String getBzfuzr() {
			return bzfuzr;
		}

		public void setBzfuzr(String bzfuzr) {
			this.bzfuzr = bzfuzr;
		}

		public String getJianchaqk() {
			return jianchaqk;
		}

		public void setJianchaqk(String jianchaqk) {
			this.jianchaqk = jianchaqk;
		}

		public String getYanshour() {
			return yanshour;
		}

		public void setYanshour(String yanshour) {
			this.yanshour = yanshour;
		}

		public String getYanshousj() {
			return yanshousj;
		}

		public void setYanshousj(String yanshousj) {
			this.yanshousj = yanshousj;
		}

		public static long getSerialversionuid() {
			return serialVersionUID;
		}

		public String getStartYanshousj() {
			return startYanshousj;
		}

		public void setStartYanshousj(String startYanshousj) {
			this.startYanshousj = startYanshousj;
		}

		public String getEndYanshousj() {
			return endYanshousj;
		}

		public void setEndYanshousj(String endYanshousj) {
			this.endYanshousj = endYanshousj;
		}
}
