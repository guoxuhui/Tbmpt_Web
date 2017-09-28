package com.crfeb.tbmpt.gpztcl.base.model;

import java.io.Serializable;
import com.baomidou.mybatisplus.annotations.IdType;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.crfeb.tbmpt.commons.model.BaseModel;
import com.baomidou.mybatisplus.annotations.TableName;

/**
 * <p>实测中线信息管理实体信息</p>
 * <p>描述：实体描述信息.</p>
 * <p>系统：管片姿态测量系统</p>
 * <p>模块：基础模块</p>
 * <p>日期：2016-12-20</p>
 * @version 1.0
 * @author YangYj
 */
@TableName("GPZTCL_SCZXPC")
public class GpztclSczxpc extends BaseModel implements Serializable {

    @TableId(type = IdType.UUID)
    private String id;

    /**
     * 工程（项目）编号<br>
     */
    @TableField(value = "gc_Bh")
    private String gcBh;

    /**
     * 区间编号<br>
     */
    @TableField(value = "ql_Bh")
    private String qlBh;

    /**
     * 线路编号<br>
     * <font color="ff0000">必填项</font><br>
     */
    @TableField(value = "xl_Bh")
    private String xlBh;

    /**
     * 起始环号<br>
     */
    @TableField(value = "starNo")
    private String starNo;

    /**
     * 截止环号<br>
     */
    @TableField(value = "endNo")
    private String endNo;

    /**
     * 测量类型<br>
     * <font color="ff0000">必填项</font><br>
     */
    @TableField(value = "clType")
    private String clType;

    /**
     * 测量时间<br>
     * <font color="ff0000">必填项</font><br>
     */
    @TableField(value = "clTime")
    private String clTime;

    /**
     * 导入人员<br>
     */
    @TableField(value = "impMan")
    private String impMan;

    /**
     * 导入时间<br>
     */
    @TableField(value = "impTime")
    private String impTime;

    /**
     * 上报状态<br>
     */
    @TableField(value = "sendState")
    private String sendState;

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
    public String getQlBh() {
      return qlBh;
    }

    /**
     * 区间编号
     */
    public void setQlBh(String qlBh) {
      this.qlBh = qlBh;
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
     * 起始环号
     */
    public String getStarNo() {
      return starNo;
    }

    /**
     * 起始环号
     */
    public void setStarNo(String starNo) {
      this.starNo = starNo;
    }

    /**
     * 截止环号
     */
    public String getEndNo() {
      return endNo;
    }

    /**
     * 截止环号
     */
    public void setEndNo(String endNo) {
      this.endNo = endNo;
    }

    /**
     * 测量类型
     */
    public String getClType() {
      return clType;
    }

    /**
     * 测量类型
     */
    public void setClType(String clType) {
      this.clType = clType;
    }

    /**
     * 测量时间
     */
    public String getClTime() {
      return clTime;
    }

    /**
     * 测量时间
     */
    public void setClTime(String clTime) {
      this.clTime = clTime;
    }

    /**
     * 导入人员
     */
    public String getImpMan() {
      return impMan;
    }

    /**
     * 导入人员
     */
    public void setImpMan(String impMan) {
      this.impMan = impMan;
    }

    /**
     * 导入时间
     */
    public String getImpTime() {
      return impTime;
    }

    /**
     * 导入时间
     */
    public void setImpTime(String impTime) {
      this.impTime = impTime;
    }

    /**
     * 上报状态
     */
    public String getSendState() {
      return sendState;
    }

    /**
     * 上报状态
     */
    public void setSendState(String sendState) {
      this.sendState = sendState;
    }

}