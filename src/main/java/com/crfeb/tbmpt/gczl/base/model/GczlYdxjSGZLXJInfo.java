package com.crfeb.tbmpt.gczl.base.model;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotations.IdType;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.crfeb.tbmpt.commons.model.BaseModel;

/**
 * <p>结构施工质量巡检管理实体信息</p>
 * <p>描述：实体描述信息.</p>
 * <p>系统：</p>
 * <p>模块：基础模块</p>
 * <p>日期：2016-11-24</p>
 * @version 1.0
 * @author wangbinbin
 */
@TableName("GCZLYDXJ_SGZL_XJ_INFO")
public class GczlYdxjSGZLXJInfo extends BaseModel implements Serializable {

    @TableId(type = IdType.UUID)
    private String id;

    /**
     * 工程（项目）编号<br>
     * <font color="ff0000">必填项</font><br>
     */
    @TableField(value = "gc_Bh")
    private String gcBh;

    /**
     * 区间编号<br>
     * <font color="ff0000">必填项</font><br>
     */
    @TableField(value = "qj_Bh")
    private String qjBh;

    /**
     * 线路编号<br>
     * <font color="ff0000">必填项</font><br>
     */
    @TableField(value = "xl_Bh")
    private String xlBh;
    
    /**
     * 单位工程编号<br>
     */
    @TableField(value = "dwgc_Bh")
    private String dwgcBh;

    /**
     * 施工内容<br>
     * <font color="ff0000">必填项</font><br>
     */
    @TableField(value = "sgNr")
    private String sgNr;

    /**
     * 分部工程<br>
     * <font color="ff0000">必填项</font><br>
     */
    @TableField(value = "fbGc_bh")
    private String fbGcbh;

    /**
     * 施工段<br>
     * <font color="ff0000">必填项</font><br>
     */
    @TableField(value = "sgd")
    private String sgd;

    /**
     * 具体位置<br>
     * <font color="ff0000">必填项</font><br>
     */
    @TableField(value = "jtWz")
    private String jtWz;

    /**
     * 质量缺陷（存在质量问题、缺陷说明）<br>
     * <font color="ff0000">必填项</font><br>
     */
    @TableField(value = "zlQx")
    private String zlQx;

    /**
     * 现场照片上传路径<br>
     */
    @TableField(value = "xjZp")
    private String xjZp;

    /**
     * 现场照片缩略图存储路径<br>
     */
    @TableField(value = "xjZpslt")
    private String xjZpslt;

    /**
     * 其他需说明情况<br>
     */
    @TableField(value = "remarks")
    private String remarks;

    /**
     * 巡检时间<br>
     * <font color="ff0000">必填项</font><br>
     */
    @TableField(value = "xjTime")
    private String xjTime;

    /**
     * 维护时间<br>
     * <font color="ff0000">必填项</font><br>
     */
    @TableField(value = "whTime")
    private String whTime;

    /**
     * 巡检人员<br>
     */
    @TableField(value = "xjRy")
    private String xjRy;

    /**
     * 巡检人员所在部门<br>
     */
    @TableField(value = "xjBm")
    private String xjBm;

    /**
     * 上报时间<br>
     */
    @TableField(value = "sbTime")
    private String sbTime;

    /**
     * 上报状态<br>
     */
    @TableField(value = "sbZt")
    private String sbZt;

    /**
     * 上报人员<br>
     */
    @TableField(value = "sbRy")
    private String sbRy;

    /**
     * 上报部门<br>
     */
    @TableField(value = "sbBm")
    private String sbBm;

    /**
     * 审核时间<br>
     */
    @TableField(value = "shTime")
    private String shTime;

    /**
     * 审核状态<br>
     */
    @TableField(value = "shZt")
    private String shZt;

    /**
     * 审核说明<br>
     */
    @TableField(value = "shSm")
    private String shSm;

    /**
     * 审核人员<br>
     */
    @TableField(value = "shRy")
    private String shRy;

    /**
     * 审核部门<br>
     */
    @TableField(value = "shBm")
    private String shBm;

    /**
     * 整改反馈时间<br>
     */
    @TableField(value = "zgfkTime")
    private String zgfkTime;

    /**
     * 整改时间<br>
     */
    @TableField(value = "zgTime")
    private String zgTime;

    /**
     * 整改状态<br>
     */
    @TableField(value = "zgZt")
    private String zgZt;

    /**
     * 整改说明<br>
     */
    @TableField(value = "zgJg")
    private String zgJg;

    /**
     * 整改反馈人员<br>
     */
    @TableField(value = "zgFkry")
    private String zgFkry;

    /**
     * 整改反馈部门<br>
     */
    @TableField(value = "zgBm")
    private String zgBm;
    
    /**
     * 整改人员<br>
     */
    @TableField(value = "zgRy")
    private String zgRy;

    /**整改截止时间*/
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
     * 工程（项目）编号
     */
    public String getGcBh() {
      return gcBh;
    }

    /**
     * 工程（项目）编号
     */
    public void setGcBh(String gcBh) {
      this.gcBh = gcBh;
    }

    /**
     * 区间编号
     */
    public String getQjBh() {
      return qjBh;
    }

    /**
     * 区间编号
     */
    public void setQjBh(String qjBh) {
      this.qjBh = qjBh;
    }

    /**
     * 线路编号
     */
    public String getXlBh() {
      return xlBh;
    }

    /**
     * 线路编号
     */
    public void setXlBh(String xlBh) {
      this.xlBh = xlBh;
    }

    /**
     * 施工内容
     */
    public String getSgNr() {
      return sgNr;
    }

    /**
     * 施工内容
     */
    public void setSgNr(String sgNr) {
      this.sgNr = sgNr;
    }

    /**
     * 分部工程
     */
    public String getFbGcbh() {
      return fbGcbh;
    }

    /**
     * 分部工程
     */
    public void setFbGcbh(String fbGcbh) {
      this.fbGcbh = fbGcbh;
    }

    /**
     * 施工段
     */
    public String getSgd() {
      return sgd;
    }

    /**
     * 施工段
     */
    public void setSgd(String sgd) {
      this.sgd = sgd;
    }

    /**
     * 具体位置
     */
    public String getJtWz() {
      return jtWz;
    }

    /**
     * 具体位置
     */
    public void setJtWz(String jtWz) {
      this.jtWz = jtWz;
    }

    /**
     * 质量缺陷（存在质量问题、缺陷说明）
     */
    public String getZlQx() {
      return zlQx;
    }

    /**
     * 质量缺陷（存在质量问题、缺陷说明）
     */
    public void setZlQx(String zlQx) {
      this.zlQx = zlQx;
    }

    /**
     * 现场照片上传路径
     */
    public String getXjZp() {
      return xjZp;
    }

    /**
     * 现场照片上传路径
     */
    public void setXjZp(String xjZp) {
      this.xjZp = xjZp;
    }

    /**
     * 现场照片缩略图存储路径
     */
    public String getXjZpslt() {
      return xjZpslt;
    }

    /**
     * 现场照片缩略图存储路径
     */
    public void setXjZpslt(String xjZpslt) {
      this.xjZpslt = xjZpslt;
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
    public String getXjTime() {
      return xjTime;
    }

    /**
     * 巡检时间
     */
    public void setXjTime(String xjTime) {
      this.xjTime = xjTime;
    }

    /**
     * 维护时间
     */
    public String getWhTime() {
      return whTime;
    }

    /**
     * 维护时间
     */
    public void setWhTime(String whTime) {
      this.whTime = whTime;
    }

    /**
     * 巡检人员
     */
    public String getXjRy() {
      return xjRy;
    }

    /**
     * 巡检人员
     */
    public void setXjRy(String xjRy) {
      this.xjRy = xjRy;
    }

    /**
     * 巡检人员所在部门
     */
    public String getXjBm() {
      return xjBm;
    }

    /**
     * 巡检人员所在部门
     */
    public void setXjBm(String xjBm) {
      this.xjBm = xjBm;
    }

    /**
     * 上报时间
     */
    public String getSbTime() {
      return sbTime;
    }

    /**
     * 上报时间
     */
    public void setSbTime(String sbTime) {
      this.sbTime = sbTime;
    }

    /**
     * 上报状态
     */
    public String getSbZt() {
      return sbZt;
    }

    /**
     * 上报状态
     */
    public void setSbZt(String sbZt) {
      this.sbZt = sbZt;
    }

    /**
     * 上报人员
     */
    public String getSbRy() {
      return sbRy;
    }

    /**
     * 上报人员
     */
    public void setSbRy(String sbRy) {
      this.sbRy = sbRy;
    }

    /**
     * 上报部门
     */
    public String getSbBm() {
      return sbBm;
    }

    /**
     * 上报部门
     */
    public void setSbBm(String sbBm) {
      this.sbBm = sbBm;
    }

    /**
     * 审核时间
     */
    public String getShTime() {
      return shTime;
    }

    /**
     * 审核时间
     */
    public void setShTime(String shTime) {
      this.shTime = shTime;
    }

    /**
     * 审核状态
     */
    public String getShZt() {
      return shZt;
    }

    /**
     * 审核状态
     */
    public void setShZt(String shZt) {
      this.shZt = shZt;
    }

    /**
     * 审核说明
     */
    public String getShSm() {
      return shSm;
    }

    /**
     * 审核说明
     */
    public void setShSm(String shSm) {
      this.shSm = shSm;
    }

    /**
     * 审核人员
     */
    public String getShRy() {
      return shRy;
    }

    /**
     * 审核人员
     */
    public void setShRy(String shRy) {
      this.shRy = shRy;
    }

    /**
     * 审核部门
     */
    public String getShBm() {
      return shBm;
    }

    /**
     * 审核部门
     */
    public void setShBm(String shBm) {
      this.shBm = shBm;
    }

    /**
     * 整改反馈时间
     */
    public String getZgfkTime() {
      return zgfkTime;
    }

    /**
     * 整改反馈时间
     */
    public void setZgfkTime(String zgfkTime) {
      this.zgfkTime = zgfkTime;
    }

    /**
     * 整改时间
     */
    public String getZgTime() {
      return zgTime;
    }

    /**
     * 整改时间
     */
    public void setZgTime(String zgTime) {
      this.zgTime = zgTime;
    }

    /**
     * 整改状态
     */
    public String getZgZt() {
      return zgZt;
    }

    /**
     * 整改状态
     */
    public void setZgZt(String zgZt) {
      this.zgZt = zgZt;
    }

    /**
     * 整改说明
     */
    public String getZgJg() {
      return zgJg;
    }

    /**
     * 整改说明
     */
    public void setZgJg(String zgJg) {
      this.zgJg = zgJg;
    }

    /**
     * 整改反馈人员
     */
    public String getZgFkry() {
      return zgFkry;
    }

    /**
     * 整改反馈人员
     */
    public void setZgFkry(String zgFkry) {
      this.zgFkry = zgFkry;
    }

    /**
     * 整改反馈部门
     */
    public String getZgBm() {
      return zgBm;
    }

    /**
     * 整改反馈部门
     */
    public void setZgBm(String zgBm) {
      this.zgBm = zgBm;
    }
    /**
     * 整改人员
     */
	public String getZgRy() {
		return zgRy;
	}
	 /**
     * 整改人员
     */
	public void setZgRy(String zgRy) {
		this.zgRy = zgRy;
	}
	 /**
     * 单位工程编号<br>
     */
	public String getDwgcBh() {
		return dwgcBh;
	}
	 /**
     * 单位工程编号<br>
     */
	public void setDwgcBh(String dwgcBh) {
		this.dwgcBh = dwgcBh;
	}
	
	
    

}