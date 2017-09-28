package com.crfeb.tbmpt.zsjk.xmb.model.dto;

import com.crfeb.tbmpt.commons.model.BaseModel;
import java.io.Serializable;

public class ZsJkXmbSbWbXxDto  implements Serializable {

    private static final long serialVersionUID = 1L;

    private String id;

    /**
     * 设备ID<br>
     */
    private String sbId;

    /**
     * 日期<br>
     */
    private String rq;

    /**
     * 设备名称<br>
     */
    private String sbmc;

    /**
     * 次数<br>
     */
    private String cs;

    public String getId() {
      return id;
    }

    public void setId(String id) {
      this.id = id;
    }

    /**
     * 设备ID
     */
    public String getSbId() {
      return sbId;
    }

    /**
     * 设备ID
     */
    public void setSbId(String sbId) {
      this.sbId = sbId;
    }

    /**
     * 日期
     */
    public String getRq() {
      return rq;
    }

    /**
     * 日期
     */
    public void setRq(String rq) {
      this.rq = rq;
    }

    /**
     * 设备名称
     */
    public String getSbmc() {
      return sbmc;
    }

    /**
     * 设备名称
     */
    public void setSbmc(String sbmc) {
      this.sbmc = sbmc;
    }

    /**
     * 次数
     */
    public String getCs() {
      return cs;
    }

    /**
     * 次数
     */
    public void setCs(String cs) {
      this.cs = cs;
    }

}