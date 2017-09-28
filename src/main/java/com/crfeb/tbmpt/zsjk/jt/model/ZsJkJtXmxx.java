package com.crfeb.tbmpt.zsjk.jt.model;

import java.io.Serializable;
import com.baomidou.mybatisplus.annotations.IdType;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.crfeb.tbmpt.commons.model.BaseModel;
import com.baomidou.mybatisplus.annotations.TableName;

/**
 * <p>展示接口集团角度项目信息实体信息</p>
 * <p>描述：实体描述信息.</p>
 * <p>系统：展示界面接口</p>
 * <p>模块：集团角度模块</p>
 * <p>日期：2017-01-09</p>
 * @version 1.0
 * @author YangYJ
 */
@TableName("zsjk_jt_xmxx")
public class ZsJkJtXmxx  implements Serializable {

    @TableId(type = IdType.UUID)
    private String id;

    /**
     * 项目ID<br>
     */
    @TableField(value = "xmId")
    private String xmId;

    /**
     * 项目名称<br>
     */
    @TableField(value = "xmMc")
    private String xmMc;

    /**
     * 中标价格<br>
     */
    @TableField(value = "zbjg")
    private String zbjg;

    /**
     * 项目产值<br>
     */
    @TableField(value = "xmcz")
    private String xmcz;

    /**
     * 当前项目完成产值<br>
     */
    @TableField(value = "dqxmwcz")
    private String dqxmwcz;

    /**
     * 城市地区<br>
     */
    @TableField(value = "csdq")
    private String csdq;

    /**
     * 坐标经度<br>
     */
    @TableField(value = "zbjd")
    private String zbjd;

    /**
     * 坐标纬度<br>
     */
    @TableField(value = "zbwd")
    private String zbwd;

    /**
     * 总工期天数<br>
     */
    @TableField(value = "zts")
    private String zts;

    /**
     * 开工日期<br>
     */
    @TableField(value = "kgrq")
    private String kgrq;

    /**
     * 预计完成日期<br>
     */
    @TableField(value = "yjwcrq")
    private String yjwcrq;

    /**
     * 地铁线路<br>
     */
    @TableField(value = "dtxl")
    private String dtxl;

    /**
     * 施工标段<br>
     */
    @TableField(value = "sgbd")
    private String sgbd;

    /**
     * 负责人<br>
     */
    @TableField(value = "fzr")
    private String fzr;

    /**
     * 总环数<br>
     */
    @TableField(value = "zhs")
    private String zhs;

    /**
     * 当前掘进总环数<br>
     */
    @TableField(value = "dqjzjhs")
    private String dqjzjhs;

    public String getId() {
      return id;
    }

    public void setId(String id) {
      this.id = id;
    }

    /**
     * 项目ID
     */
    public String getXmId() {
      return xmId;
    }

    /**
     * 项目ID
     */
    public void setXmId(String xmId) {
      this.xmId = xmId;
    }

    /**
     * 项目名称
     */
    public String getXmMc() {
      return xmMc;
    }

    /**
     * 项目名称
     */
    public void setXmMc(String xmMc) {
      this.xmMc = xmMc;
    }

    /**
     * 中标价格
     */
    public String getZbjg() {
      return zbjg;
    }

    /**
     * 中标价格
     */
    public void setZbjg(String zbjg) {
      this.zbjg = zbjg;
    }

    /**
     * 项目产值
     */
    public String getXmcz() {
      return xmcz;
    }

    /**
     * 项目产值
     */
    public void setXmcz(String xmcz) {
      this.xmcz = xmcz;
    }

    /**
     * 当前项目完成产值
     */
    public String getDqxmwcz() {
      return dqxmwcz;
    }

    /**
     * 当前项目完成产值
     */
    public void setDqxmwcz(String dqxmwcz) {
      this.dqxmwcz = dqxmwcz;
    }

    /**
     * 城市地区
     */
    public String getCsdq() {
      return csdq;
    }

    /**
     * 城市地区
     */
    public void setCsdq(String csdq) {
      this.csdq = csdq;
    }

    /**
     * 坐标经度
     */
    public String getZbjd() {
      return zbjd;
    }

    /**
     * 坐标经度
     */
    public void setZbjd(String zbjd) {
      this.zbjd = zbjd;
    }

    /**
     * 坐标纬度
     */
    public String getZbwd() {
      return zbwd;
    }

    /**
     * 坐标纬度
     */
    public void setZbwd(String zbwd) {
      this.zbwd = zbwd;
    }

    /**
     * 总工期天数
     */
    public String getZts() {
      return zts;
    }

    /**
     * 总工期天数
     */
    public void setZts(String zts) {
      this.zts = zts;
    }

    /**
     * 开工日期
     */
    public String getKgrq() {
      return kgrq;
    }

    /**
     * 开工日期
     */
    public void setKgrq(String kgrq) {
      this.kgrq = kgrq;
    }

    /**
     * 预计完成日期
     */
    public String getYjwcrq() {
      return yjwcrq;
    }

    /**
     * 预计完成日期
     */
    public void setYjwcrq(String yjwcrq) {
      this.yjwcrq = yjwcrq;
    }

    /**
     * 地铁线路
     */
    public String getDtxl() {
      return dtxl;
    }

    /**
     * 地铁线路
     */
    public void setDtxl(String dtxl) {
      this.dtxl = dtxl;
    }

    /**
     * 施工标段
     */
    public String getSgbd() {
      return sgbd;
    }

    /**
     * 施工标段
     */
    public void setSgbd(String sgbd) {
      this.sgbd = sgbd;
    }

    /**
     * 负责人
     */
    public String getFzr() {
      return fzr;
    }

    /**
     * 负责人
     */
    public void setFzr(String fzr) {
      this.fzr = fzr;
    }

    /**
     * 总环数
     */
    public String getZhs() {
      return zhs;
    }

    /**
     * 总环数
     */
    public void setZhs(String zhs) {
      this.zhs = zhs;
    }

    /**
     * 当前掘进总环数
     */
    public String getDqjzjhs() {
      return dqjzjhs;
    }

    /**
     * 当前掘进总环数
     */
    public void setDqjzjhs(String dqjzjhs) {
      this.dqjzjhs = dqjzjhs;
    }

}