package com.crfeb.tbmpt.zsjk.jt.model.dto;

import com.baomidou.mybatisplus.annotations.TableField;
import com.crfeb.tbmpt.commons.model.BaseModel;
import java.io.Serializable;

public class ZsJkJtAqZlMxXxDto  implements Serializable {

    private static final long serialVersionUID = 1L;

    private String id;

    /**
     * 上报日期<br>
     */
    private String sbrq;

    /**
     * 上报单位<br>
     */
    private String sbdw;
    
    /**
     * 上报单位全称<br>
     */
    private String sbdwqc;

    /**
     * 信息分类<br>
     */
    private String xxfl;

    /**
     * 详细信息<br>
     */
    private String xxxx;

    /**
     * 上报人<br>
     */
    private String sbr;

    /**
     * 图片链接<br>
     */
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