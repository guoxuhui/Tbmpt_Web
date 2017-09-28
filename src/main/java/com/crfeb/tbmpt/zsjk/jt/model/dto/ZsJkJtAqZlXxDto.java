package com.crfeb.tbmpt.zsjk.jt.model.dto;

import com.crfeb.tbmpt.commons.model.BaseModel;
import java.io.Serializable;

public class ZsJkJtAqZlXxDto  implements Serializable {

    private static final long serialVersionUID = 1L;

    private String id;

    /**
     * 日期<br>
     */
    private String rq;

    /**
     * 质量问题数<br>
     */
    private String zlwts;

    /**
     * 安全问题数<br>
     */
    private String aqwts;

    /**
     * 隐患数<br>
     */
    private String yhs;
    /**
     * 查询使用起始日期<br>
     */
    private String strTime;
    /**
     * 查询使用截止日期<br>
     */
    private String endTime;

    public String getId() {
      return id;
    }

    public void setId(String id) {
      this.id = id;
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
     * 质量问题数
     */
    public String getZlwts() {
      return zlwts;
    }

    /**
     * 质量问题数
     */
    public void setZlwts(String zlwts) {
      this.zlwts = zlwts;
    }

    /**
     * 安全问题数
     */
    public String getAqwts() {
      return aqwts;
    }

    /**
     * 安全问题数
     */
    public void setAqwts(String aqwts) {
      this.aqwts = aqwts;
    }

    /**
     * 隐患数
     */
    public String getYhs() {
      return yhs;
    }

    /**
     * 隐患数
     */
    public void setYhs(String yhs) {
      this.yhs = yhs;
    }

	public String getStrTime() {
		return strTime;
	}

	public void setStrTime(String strTime) {
		this.strTime = strTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
    
    

}