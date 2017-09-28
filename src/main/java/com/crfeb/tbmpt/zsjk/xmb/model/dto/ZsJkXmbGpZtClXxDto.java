package com.crfeb.tbmpt.zsjk.xmb.model.dto;

import com.crfeb.tbmpt.commons.model.BaseModel;
import java.io.Serializable;

public class ZsJkXmbGpZtClXxDto  implements Serializable {

    private static final long serialVersionUID = 1L;

    private String id;

    /**
     * 线路ID<br>
     */
    private String xlId;
    
    /**
     * 线路名称<br>
     */
    private String xlmc;

    /**
     * 环号<br>
     */
    private String hh;

    /**
     * 横向实测偏移量<br>
     */
    private String hxpy;

    /**
     * 竖向实测偏移量<br>
     */
    private String sxpy;

    public String getId() {
      return id;
    }

    public void setId(String id) {
      this.id = id;
    }

    /**
     * 线路ID
     */
    public String getXlId() {
      return xlId;
    }

    /**
     * 线路ID
     */
    public void setXlId(String xlId) {
      this.xlId = xlId;
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
     * 横向实测偏移量
     */
    public String getHxpy() {
      return hxpy;
    }

    /**
     * 横向实测偏移量
     */
    public void setHxpy(String hxpy) {
      this.hxpy = hxpy;
    }

    /**
     * 竖向实测偏移量
     */
    public String getSxpy() {
      return sxpy;
    }

    /**
     * 竖向实测偏移量
     */
    public void setSxpy(String sxpy) {
      this.sxpy = sxpy;
    }

	public String getXlmc() {
		return xlmc;
	}

	public void setXlmc(String xlmc) {
		this.xlmc = xlmc;
	}
    

}