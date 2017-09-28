package com.crfeb.tbmpt.gpztcl.base.model;

import java.io.Serializable;
import com.baomidou.mybatisplus.annotations.IdType;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.crfeb.tbmpt.commons.model.BaseModel;
import com.baomidou.mybatisplus.annotations.TableName;

/**
 * <p>实测中线信息明细管理实体信息</p>
 * <p>描述：实体描述信息.</p>
 * <p>系统：管片姿态测量系统</p>
 * <p>模块：基础模块</p>
 * <p>日期：2016-12-20</p>
 * @version 1.0
 * @author YangYj
 */
@TableName("GPZTCL_SCZXINFO")
public class GpztclSczxInfo extends BaseModel implements Serializable {

    @TableId(type = IdType.UUID)
    private String id;

    /**
     * 主表主键<br>
     */
    @TableField(value = "fid")
    private String fid;

    /**
     * 环号<br>
     */
    @TableField(value = "hh")
    private String hh;

    /**
     * 实测横向坐标<br>
     */
    @TableField(value = "sczbX")
    private String sczbX;

    /**
     * 实测纵向坐标<br>
     */
    @TableField(value = "sczbY")
    private String sczbY;

    /**
     * 实测高程<br>
     */
    @TableField(value = "sczbH")
    private String sczbH;

    /**
     * 计算横向坐标<br>
     */
    @TableField(value = "jszbX")
    private String jszbX;

    /**
     * 计算纵向坐标<br>
     */
    @TableField(value = "jszbY")
    private String jszbY;

    /**
     * 计算高程<br>
     */
    @TableField(value = "jszbH")
    private String jszbH;

    /**
     * 导向测量横向偏移<br>
     */
    @TableField(value = "dxpyX")
    private String dxpyX;

    /**
     * 导向测量纵向偏移<br>
     */
    @TableField(value = "dxpyY")
    private String dxpyY;
    
    /**
     * 备注<br>
     */
    @TableField(value = "remarks")
    private String remarks;
    
    /**
     * 里程<br>
     */
    @TableField(value = "lc")
    private String lc;

    public String getId() {
      return id;
    }

    public void setId(String id) {
      this.id = id;
    }

    /**
     * 主表主键
     */
    public String getFid() {
      return fid;
    }

    /**
     * 主表主键
     */
    public void setFid(String fid) {
      this.fid = fid;
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
     * 实测横向坐标
     */
    public String getSczbX() {
      return sczbX;
    }

    /**
     * 实测横向坐标
     */
    public void setSczbX(String sczbX) {
      this.sczbX = sczbX;
    }

    /**
     * 实测纵向坐标
     */
    public String getSczbY() {
      return sczbY;
    }

    /**
     * 实测纵向坐标
     */
    public void setSczbY(String sczbY) {
      this.sczbY = sczbY;
    }

    /**
     * 实测高程
     */
    public String getSczbH() {
      return sczbH;
    }

    /**
     * 实测高程
     */
    public void setSczbH(String sczbH) {
      this.sczbH = sczbH;
    }

    /**
     * 计算横向坐标
     */
    public String getJszbX() {
      return jszbX;
    }

    /**
     * 计算横向坐标
     */
    public void setJszbX(String jszbX) {
      this.jszbX = jszbX;
    }

    /**
     * 计算纵向坐标
     */
    public String getJszbY() {
      return jszbY;
    }

    /**
     * 计算纵向坐标
     */
    public void setJszbY(String jszbY) {
      this.jszbY = jszbY;
    }

    /**
     * 计算高程
     */
    public String getJszbH() {
      return jszbH;
    }

    /**
     * 计算高程
     */
    public void setJszbH(String jszbH) {
      this.jszbH = jszbH;
    }

    /**
     * 导向测量横向偏移
     */
    public String getDxpyX() {
      return dxpyX;
    }

    /**
     * 导向测量横向偏移
     */
    public void setDxpyX(String dxpyX) {
      this.dxpyX = dxpyX;
    }

    /**
     * 导向测量纵向偏移
     */
    public String getDxpyY() {
      return dxpyY;
    }

    /**
     * 导向测量纵向偏移
     */
    public void setDxpyY(String dxpyY) {
      this.dxpyY = dxpyY;
    }

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public String getLc() {
		return lc;
	}

	public void setLc(String lc) {
		this.lc = lc;
	}
	
	

}