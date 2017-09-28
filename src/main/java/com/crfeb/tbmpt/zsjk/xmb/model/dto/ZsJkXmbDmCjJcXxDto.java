package com.crfeb.tbmpt.zsjk.xmb.model.dto;

import com.crfeb.tbmpt.commons.model.BaseModel;
import java.io.Serializable;

public class ZsJkXmbDmCjJcXxDto  implements Serializable {

    private static final long serialVersionUID = 1L;

    private String id;

    /**
     * 线路id<br>
     */
    private String xlId;

    /**
     * 里程<br>
     */
    private String lc;

    /**
     * 日期<br>
     */
    private String sbDate;

    /**
     * 点编号<br>
     */
    private String dianbh;

    /**
     * 值<br>
     */
    private String zhi;

    public String getId() {
      return id;
    }

    public void setId(String id) {
      this.id = id;
    }

    /**
     * 线路id
     */
    public String getXlId() {
      return xlId;
    }

    /**
     * 线路id
     */
    public void setXlId(String xlId) {
      this.xlId = xlId;
    }

    /**
     * 里程
     */
    public String getLc() {
      return lc;
    }

    /**
     * 里程
     */
    public void setLc(String lc) {
      this.lc = lc;
    }

    /**
     * 日期
     */
    public String getSbDate() {
      return sbDate;
    }

    /**
     * 日期
     */
    public void setSbDate(String sbDate) {
      this.sbDate = sbDate;
    }

    /**
     * 点编号
     */
    public String getDianbh() {
      return dianbh;
    }

    /**
     * 点编号
     */
    public void setDianbh(String dianbh) {
      this.dianbh = dianbh;
    }

    /**
     * 值
     */
    public String getZhi() {
      return zhi;
    }

    /**
     * 值
     */
    public void setZhi(String zhi) {
      this.zhi = zhi;
    }

}