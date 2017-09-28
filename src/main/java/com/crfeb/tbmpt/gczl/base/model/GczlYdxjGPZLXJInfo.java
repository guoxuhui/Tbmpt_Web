package com.crfeb.tbmpt.gczl.base.model;

import java.io.Serializable;
import com.baomidou.mybatisplus.annotations.IdType;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.crfeb.tbmpt.commons.model.BaseModel;
import com.crfeb.tbmpt.commons.model.ProjectModel;

/**
 * <p>盾构施工质量巡检信息实体信息</p>
 * <p>描述：实体描述信息.</p>
 * <p>系统：</p>
 * <p>模块：基础模块</p>
 * <p>日期：2016-11-23</p>
 * @version 1.0
 * @author wangbinbin
 */
@TableName("gczlydxj_gpzl_xj_info")
public class GczlYdxjGPZLXJInfo extends ProjectModel implements Serializable {

    @TableId(type = IdType.UUID)
    private String id;

    /**
     * 环号<br>
     * <font color="ff0000">必填项</font><br>
     */
    @TableField(value = "hh")
    private String hh;

    /**
     * 点位<br>
     * <font color="ff0000">必填项</font><br>
     */
    @TableField(value = "dw")
    private String dw;

    /**
     * 质量问题分类<br>
     * <font color="ff0000">必填项</font><br>
     */
    @TableField(value = "typeName")
    private String typeName;

    /**
     * 情况描述<br>
     * <font color="ff0000">必填项</font><br>
     */
    @TableField(value = "qkms")
    private String qkms;

    /**
     * 现场照片上传路径<br>
     */
    @TableField(value = "xjzp")
    private String xjzp;

    /**
     * 现场照片缩略图存储路径<br>
     */
    @TableField(value = "xjzpslt")
    private String xjzpslt;

    /**
     * 其他需说明情况<br>
     */
    @TableField(value = "remarks")
    private String remarks;

    /**
     * 巡检时间<br>
     * <font color="ff0000">必填项</font><br>
     */
    @TableField(value = "xjtime")
    private String xjtime;

    /**
     * 维护时间<br>
     */
    @TableField(value = "whtime")
    private String whtime;

    /**
     * 巡检人员<br>
     */
    @TableField(value = "xjry")
    private String xjry;

    /**
     * 巡检人员所在部门<br>
     */
    @TableField(value = "xjbm")
    private String xjbm;

    /**
     * 上报时间<br>
     */
    @TableField(value = "sbtime")
    private String sbtime;

    /**
     * 上报状态<br>
     */
    @TableField(value = "sbzt")
    private String sbzt;

    /**
     * 上报人员<br>
     */
    @TableField(value = "sbry")
    private String sbry;

    /**
     * 上报部门<br>
     */
    @TableField(value = "sbbm")
    private String sbbm;

    /**
     * 审核时间<br>
     */
    @TableField(value = "shtime")
    private String shtime;

    /**
     * 审核状态<br>
     */
    @TableField(value = "shzt")
    private String shzt;

    /**
     * 审核说明<br>
     */
    @TableField(value = "shsm")
    private String shsm;

    /**
     * 审核人员<br>
     */
    @TableField(value = "shry")
    private String shry;

    /**
     * 审核部门<br>
     */
    @TableField(value = "shbm")
    private String shbm;

    /**
     * 整改反馈时间<br>
     */
    @TableField(value = "zgfktime")
    private String zgfktime;

    /**
     * 整改时间<br>
     */
    @TableField(value = "zgtime")
    private String zgtime;

    /**
     * 整改状态<br>
     */
    @TableField(value = "zgzt")
    private String zgzt;

    /**
     * 整改说明<br>
     */
    @TableField(value = "zgjg")
    private String zgjg;

    /**
     * 整改反馈人员<br>
     */
    @TableField(value = "zgfkry")
    private String zgfkry;

    /**
     * 整改反馈部门<br>
     */
    @TableField(value = "zgbm")
    private String zgbm;

    /**
     * 指定整改人员<br>
     */
    @TableField(value = "zgry")
    private String zgry;
    
    /**
     * 整改截止时间<br>
     */
    @TableField(value = "zgjzTime")
    private String zgjzTime;

    
    
    public String getZgjzTime() {
		return zgjzTime;
	}

	public void setZgjzTime(String zgjzTime) {
		this.zgjzTime = zgjzTime;
	}

	public String getId() {
      return id;
    }

    public void setId(String id) {
      this.id = id;
    }

    /**
     * 环号
     */
    public String getHh() {
      return hh;
    }

    /**
     * 环号
     */
    public void setHh(String hh) {
      this.hh = hh;
    }

    /**
     * 点位
     */
    public String getDw() {
      return dw;
    }

    /**
     * 点位
     */
    public void setDw(String dw) {
      this.dw = dw;
    }

    /**
     * 质量问题分类
     */
    public String getTypeName() {
      return typeName;
    }

    /**
     * 质量问题分类
     */
    public void setTypeName(String typeName) {
      this.typeName = typeName;
    }

    /**
     * 情况描述
     */
    public String getQkms() {
      return qkms;
    }

    /**
     * 情况描述
     */
    public void setQkms(String qkms) {
      this.qkms = qkms;
    }

    /**
     * 现场照片上传路径
     */
    public String getXjzp() {
      return xjzp;
    }

    /**
     * 现场照片上传路径
     */
    public void setXjzp(String xjzp) {
      this.xjzp = xjzp;
    }

    /**
     * 现场照片缩略图存储路径
     */
    public String getXjzpslt() {
      return xjzpslt;
    }

    /**
     * 现场照片缩略图存储路径
     */
    public void setXjzpslt(String xjzpslt) {
      this.xjzpslt = xjzpslt;
    }

    /**
     * 其他需说明情况
     */
    public String getRemarks() {
      return remarks;
    }

    /**
     * 其他需说明情况
     */
    public void setRemarks(String remarks) {
      this.remarks = remarks;
    }

    /**
     * 巡检时间
     */
    public String getXjtime() {
      return xjtime;
    }

    /**
     * 巡检时间
     */
    public void setXjtime(String xjtime) {
      this.xjtime = xjtime;
    }

    /**
     * 维护时间
     */
    public String getWhtime() {
      return whtime;
    }

    /**
     * 维护时间
     */
    public void setWhtime(String whtime) {
      this.whtime = whtime;
    }

    /**
     * 巡检人员
     */
    public String getXjry() {
      return xjry;
    }

    /**
     * 巡检人员
     */
    public void setXjry(String xjry) {
      this.xjry = xjry;
    }

    /**
     * 巡检人员所在部门
     */
    public String getXjbm() {
      return xjbm;
    }

    /**
     * 巡检人员所在部门
     */
    public void setXjbm(String xjbm) {
      this.xjbm = xjbm;
    }

    /**
     * 上报时间
     */
    public String getSbtime() {
      return sbtime;
    }

    /**
     * 上报时间
     */
    public void setSbtime(String sbtime) {
      this.sbtime = sbtime;
    }

    /**
     * 上报状态
     */
    public String getSbzt() {
      return sbzt;
    }

    /**
     * 上报状态
     */
    public void setSbzt(String sbzt) {
      this.sbzt = sbzt;
    }

    /**
     * 上报人员
     */
    public String getSbry() {
      return sbry;
    }

    /**
     * 上报人员
     */
    public void setSbry(String sbry) {
      this.sbry = sbry;
    }

    /**
     * 上报部门
     */
    public String getSbbm() {
      return sbbm;
    }

    /**
     * 上报部门
     */
    public void setSbbm(String sbbm) {
      this.sbbm = sbbm;
    }

    /**
     * 审核时间
     */
    public String getShtime() {
      return shtime;
    }

    /**
     * 审核时间
     */
    public void setShtime(String shtime) {
      this.shtime = shtime;
    }

    /**
     * 审核状态
     */
    public String getShzt() {
      return shzt;
    }

    /**
     * 审核状态
     */
    public void setShzt(String shzt) {
      this.shzt = shzt;
    }

    /**
     * 审核说明
     */
    public String getShsm() {
      return shsm;
    }

    /**
     * 审核说明
     */
    public void setShsm(String shsm) {
      this.shsm = shsm;
    }

    /**
     * 审核人员
     */
    public String getShry() {
      return shry;
    }

    /**
     * 审核人员
     */
    public void setShry(String shry) {
      this.shry = shry;
    }

    /**
     * 审核部门
     */
    public String getShbm() {
      return shbm;
    }

    /**
     * 审核部门
     */
    public void setShbm(String shbm) {
      this.shbm = shbm;
    }

    /**
     * 整改反馈时间
     */
    public String getZgfktime() {
      return zgfktime;
    }

    /**
     * 整改反馈时间
     */
    public void setZgfktime(String zgfktime) {
      this.zgfktime = zgfktime;
    }

    /**
     * 整改时间
     */
    public String getZgtime() {
      return zgtime;
    }

    /**
     * 整改时间
     */
    public void setZgtime(String zgtime) {
      this.zgtime = zgtime;
    }

    /**
     * 整改状态
     */
    public String getZgzt() {
      return zgzt;
    }

    /**
     * 整改状态
     */
    public void setZgzt(String zgzt) {
      this.zgzt = zgzt;
    }

    /**
     * 整改说明
     */
    public String getZgjg() {
      return zgjg;
    }

    /**
     * 整改说明
     */
    public void setZgjg(String zgjg) {
      this.zgjg = zgjg;
    }

    /**
     * 整改反馈人员
     */
    public String getZgfkry() {
      return zgfkry;
    }

    /**
     * 整改反馈人员
     */
    public void setZgfkry(String zgfkry) {
      this.zgfkry = zgfkry;
    }

    /**
     * 整改反馈部门
     */
    public String getZgbm() {
      return zgbm;
    }

    /**
     * 整改反馈部门
     */
    public void setZgbm(String zgbm) {
      this.zgbm = zgbm;
    }

    /**
     * 指定整改人员
     */
    public String getZgry() {
      return zgry;
    }

    /**
     * 指定整改人员
     */
    public void setZgry(String zgry) {
      this.zgry = zgry;
    }

}