package com.crfeb.tbmpt.gczlys.model;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotations.IdType;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.crfeb.tbmpt.commons.model.BaseModel;


@TableName("GCZL_YSX_INFO")
public class YanShouYingXiang extends BaseModel implements Serializable{
	/**
     * 
     * 主键id
     */
	@TableId(type = IdType.UUID)
	private String id;
	/**
     * 
     * 项目id
     */
	@TableField(value = "projectid")
	private String projectid;
	/**
     * 
     * 工程名称
     */
	@TableField(value = "projectname")
	private String projectname;
	/**
     * 
     * 现场检查实际情况
     */
	@TableField(value = "miaoshu")
	private String miaoshu;
	/**
     * 
     * 工程部位
     */
	@TableField(value = "gcbuwei")
	private String gcbuwei;
	/**
     * 
     * 天气
     */
	@TableField(value = "tianqi")
	private String tianqi;
	/**
     * 
     * 分部分项工序
     */
	@TableField(value = "gongxu")
	private String gongxu;
	/**
     * 
     * 施工班组
     */
	@TableField(value = "banzu")
	private String banzu;
	/**
     * 
     * 班组负责人
     */
	@TableField(value = "bzfuzr")
	private String bzfuzr;
	/**
     * 
     * 检查验收意见和结论
     */
	@TableField(value = "jianchaqk")
	private String jianchaqk;
	
	/**
     * 
     * 验收人
     */
	@TableField(value = "yanshour")
	private String yanshour;
	
	/**
     * 
     * 验收时间
     */
	@TableField(value = "yanshousj")
	private String yanshousj;


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
	
	
	
	
	
	
	

}
