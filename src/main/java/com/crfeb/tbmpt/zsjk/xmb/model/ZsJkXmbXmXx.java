package com.crfeb.tbmpt.zsjk.xmb.model;

import java.io.Serializable;
import com.baomidou.mybatisplus.annotations.IdType;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.crfeb.tbmpt.commons.model.BaseModel;
import com.baomidou.mybatisplus.annotations.TableName;

/**
 * <p>项目部角度 项目基本信息实体信息</p>
 * <p>描述：实体描述信息.</p>
 * <p>系统：展示界面接口</p>
 * <p>模块：项目部角度模块</p>
 * <p>日期：2017-01-09</p>
 * @version 1.0
 * @author YangYJ
 */
@TableName("zsjk_xmb_Xmxx")
public class ZsJkXmbXmXx  implements Serializable {

    @TableId(type = IdType.UUID)
    private String id;

    /**
     * 项目名称<br>
     */
    @TableField(value = "xmMc")
    private String xmMc;

    /**
     * 盾构机Id<br>
     */
    private String dgjId;

    /**
     * 盾构机名称<br>
     */
    private String dgjMc;
    
    /**
     * 中标价格<br>
     */
    @TableField(value = "zbjg")
    private String zbjg;

    /**
     * 项目产值<br>
     */
    @TableField(value = "xmCz")
    private String xmCz;

    /**
     * 项目完成产值<br>
     */
    @TableField(value = "xmWccz")
    private String xmWccz;

    /**
     * 城市地区<br>
     */
    @TableField(value = "csdq")
    private String csdq;

    /**
     * 总工期天数<br>
     */
    @TableField(value = "zgqds")
    private String zgqds;

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
     * 盾构机操作手<br>
     */
    private String dgjczs;
    /**
     * 区间总长<br>
     */
    private String qjzc;
    /**
     * 当前环数<br>
     */
    private String dqhs;
    /**
     * 当前地质<br>
     */
    private String dqdz;
    /**
     * 盾构机状态<br>
     */
    private String dgjzt;
    /**
     * 报警数量<br>
     */
    private String bjsl;
    
    
    
    public String getId() {
      return id;
    }

    public void setId(String id) {
      this.id = id;
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
     * 盾构机Id<br>
     */
    public String getDgjId() {
		return dgjId;
	}
	public void setDgjId(String dgjId) {
		this.dgjId = dgjId;
	}
 
	/**
     * 盾构机名称<br>
     */
	public String getDgjMc() {
		return dgjMc;
	}
	public void setDgjMc(String dgjMc) {
		this.dgjMc = dgjMc;
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
    public String getXmCz() {
      return xmCz;
    }

    /**
     * 项目产值
     */
    public void setXmCz(String xmCz) {
      this.xmCz = xmCz;
    }

    /**
     * 项目完成产值
     */
    public String getXmWccz() {
      return xmWccz;
    }

    /**
     * 项目完成产值
     */
    public void setXmWccz(String xmWccz) {
      this.xmWccz = xmWccz;
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
     * 总工期天数
     */
    public String getZgqds() {
      return zgqds;
    }

    /**
     * 总工期天数
     */
    public void setZgqds(String zgqds) {
      this.zgqds = zgqds;
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

	public String getDgjczs() {
		return dgjczs;
	}

	public void setDgjczs(String dgjczs) {
		this.dgjczs = dgjczs;
	}

	public String getQjzc() {
		return qjzc;
	}

	public void setQjzc(String qjzc) {
		this.qjzc = qjzc;
	}

	public String getDqhs() {
		return dqhs;
	}

	public void setDqhs(String dqhs) {
		this.dqhs = dqhs;
	}

	public String getDqdz() {
		return dqdz;
	}

	public void setDqdz(String dqdz) {
		this.dqdz = dqdz;
	}

	public String getDgjzt() {
		return dgjzt;
	}

	public void setDgjzt(String dgjzt) {
		this.dgjzt = dgjzt;
	}

	public String getBjsl() {
		return bjsl;
	}

	public void setBjsl(String bjsl) {
		this.bjsl = bjsl;
	}

    
}