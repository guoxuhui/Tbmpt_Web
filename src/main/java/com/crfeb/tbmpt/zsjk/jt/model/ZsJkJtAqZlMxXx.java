package com.crfeb.tbmpt.zsjk.jt.model;

import java.io.Serializable;
import com.baomidou.mybatisplus.annotations.IdType;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.crfeb.tbmpt.commons.model.BaseModel;
import com.baomidou.mybatisplus.annotations.TableName;

/**
 * <p>集团角度安全质量明细信息实体信息</p>
 * <p>描述：实体描述信息.</p>
 * <p>系统：展示界面接口</p>
 * <p>模块：集团角度模块</p>
 * <p>日期：2017-01-09</p>
 * @version 1.0
 * @author YangYJ
 */
@TableName("zsjk_jt_aqzlmxxx")
public class ZsJkJtAqZlMxXx  implements Serializable {

    @TableId(type = IdType.UUID)
    private String id;

    /**
     * 上报日期<br>
     */
    @TableField(value = "sbrq")
    private String sbrq;

    /**
     * 上报单位<br>
     */
    @TableField(value = "sbdw")
    private String sbdw;
    
    /**
     * 上报单位全称<br>
     */
    @TableField(value = "sbdwqc")
    private String sbdwqc;

    /**
     * 信息分类<br>
     */
    @TableField(value = "xxfl")
    private String xxfl;

    /**
     * 详细信息<br>
     */
    @TableField(value = "xxxx")
    private String xxxx;

    /**
     * 上报人<br>
     */
    @TableField(value = "sbr")
    private String sbr;

    /**
     * 图片链接<br>
     */
    @TableField(value = "tpurl")
    private String tpurl;

    public String getId() {
      return id;
    }

    public void setId(String id) {
      this.id = id;
    }

    /**
     * 上报日期
     */
    public String getSbrq() {
      return sbrq;
    }

    /**
     * 上报日期
     */
    public void setSbrq(String sbrq) {
      this.sbrq = sbrq;
    }

    /**
     * 上报单位
     */
    public String getSbdw() {
      return sbdw;
    }

    /**
     * 上报单位
     */
    public void setSbdw(String sbdw) {
      this.sbdw = sbdw;
    }

    /**
     * 信息分类
     */
    public String getXxfl() {
      return xxfl;
    }

    /**
     * 信息分类
     */
    public void setXxfl(String xxfl) {
      this.xxfl = xxfl;
    }

    /**
     * 详细信息
     */
    public String getXxxx() {
      return xxxx;
    }

    /**
     * 详细信息
     */
    public void setXxxx(String xxxx) {
      this.xxxx = xxxx;
    }

    /**
     * 上报人
     */
    public String getSbr() {
      return sbr;
    }

    /**
     * 上报人
     */
    public void setSbr(String sbr) {
      this.sbr = sbr;
    }

    /**
     * 图片链接
     */
    public String getTpurl() {
      return tpurl;
    }

    /**
     * 图片链接
     */
    public void setTpurl(String tpurl) {
      this.tpurl = tpurl;
    }

	public String getSbdwqc() {
		return sbdwqc;
	}

	public void setSbdwqc(String sbdwqc) {
		this.sbdwqc = sbdwqc;
	}
    
    

}