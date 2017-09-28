package com.crfeb.tbmpt.zsjk.jt.model.dto;

import com.crfeb.tbmpt.commons.model.BaseModel;
import java.io.Serializable;

public class ZsJkJtDjXhZTrXxDto  implements Serializable {

    private static final long serialVersionUID = 1L;

    private String id;

    /**
     * 年度<br>
     */
    private String nd;

    /**
     * 刀具消耗计划投入值<br>
     */
    private String jhTrz;

    /**
     * 刀具消耗实际投入值<br>
     */
    private String sjTrz;

    public String getId() {
      return id;
    }

    public void setId(String id) {
      this.id = id;
    }

    /**
     * 年度
     */
    public String getNd() {
      return nd;
    }

    /**
     * 年度
     */
    public void setNd(String nd) {
      this.nd = nd;
    }

    /**
     * 刀具消耗计划投入值
     */
    public String getJhTrz() {
      return jhTrz;
    }

    /**
     * 刀具消耗计划投入值
     */
    public void setJhTrz(String jhTrz) {
      this.jhTrz = jhTrz;
    }

    /**
     * 刀具消耗实际投入值
     */
    public String getSjTrz() {
      return sjTrz;
    }

    /**
     * 刀具消耗实际投入值
     */
    public void setSjTrz(String sjTrz) {
      this.sjTrz = sjTrz;
    }

}