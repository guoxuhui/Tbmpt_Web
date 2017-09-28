package com.crfeb.tbmpt.zsjk.xmb.model.dto;

import java.io.Serializable;
import java.util.List;

public class ZsJkXmbXmLjCzMxXxDto  implements Serializable {

    private static final long serialVersionUID = 1L;

    private String id;

    /**
     * 项目id<br>
     */
    private String xmId;

    /**
     * 主表主键<br>
     */
    private String fId;

    /**
     * 月份<br>
     */
    private String mouth;

    /**
     * 区间名称+线路名称<br>
     */
    private String qujxl;

    /**
     * 本月产值<br>
     */
    private String benycz;

    /**
     * 月计划产值<br>
     */
    private String yuejhcz;
    /**
     * 明细信息<br>
     */
    private List<ZsJkXmbXmLjCzMxXxDto> items;

    public String getId() {
      return id;
    }

    public void setId(String id) {
      this.id = id;
    }

    /**
     * 项目id
     */
    public String getXmId() {
      return xmId;
    }

    /**
     * 项目id
     */
    public void setXmId(String xmId) {
      this.xmId = xmId;
    }

    /**
     * 主表主键
     */
    public String getFId() {
      return fId;
    }

    /**
     * 主表主键
     */
    public void setFId(String fId) {
      this.fId = fId;
    }

    /**
     * 月份
     */
    public String getMouth() {
      return mouth;
    }

    /**
     * 月份
     */
    public void setMouth(String mouth) {
      this.mouth = mouth;
    }

    /**
     * 区间名称+线路名称
     */
    public String getQujxl() {
      return qujxl;
    }

    /**
     * 区间名称+线路名称
     */
    public void setQujxl(String qujxl) {
      this.qujxl = qujxl;
    }

    /**
     * 本月产值
     */
    public String getBenycz() {
      return benycz;
    }

    /**
     * 本月产值
     */
    public void setBenycz(String benycz) {
      this.benycz = benycz;
    }

    /**
     * 月计划产值
     */
    public String getYuejhcz() {
      return yuejhcz;
    }

    /**
     * 月计划产值
     */
    public void setYuejhcz(String yuejhcz) {
      this.yuejhcz = yuejhcz;
    }

	public List<ZsJkXmbXmLjCzMxXxDto> getItems() {
		return items;
	}

	public void setItems(List<ZsJkXmbXmLjCzMxXxDto> items) {
		this.items = items;
	}
    
    

}