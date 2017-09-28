package com.crfeb.tbmpt.zsjk.xmb.model.dto;

import java.io.Serializable;
import java.util.List;

public class ZsJkXmbDjXhZTrXxDto  implements Serializable {

    private static final long serialVersionUID = 1L;

    private String id;

    /**
     * X轴标题<br>
     */
    private String xTitle;

    /**
     * Y轴标题<br>
     */
    private String yTitle;

    /**
     * 线路ID<br>
     */
    private String xlId;

    /**
     * 分析内容<br>
     */
    private String fxnr;

    /**
     * 刀具类型<br>
     */
    private String djlx;
    
    private List<ZsJkXmbDjXhZTrDataXxDto> items;

    public String getId() {
      return id;
    }

    public void setId(String id) {
      this.id = id;
    }

    /**
     * X轴标题
     */
    public String getXTitle() {
      return xTitle;
    }

    /**
     * X轴标题
     */
    public void setXTitle(String xTitle) {
      this.xTitle = xTitle;
    }

    /**
     * Y轴标题
     */
    public String getYTitle() {
      return yTitle;
    }

    /**
     * Y轴标题
     */
    public void setYTitle(String yTitle) {
      this.yTitle = yTitle;
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
     * 分析内容
     */
    public String getFxnr() {
      return fxnr;
    }

    /**
     * 分析内容
     */
    public void setFxnr(String fxnr) {
      this.fxnr = fxnr;
    }

    /**
     * 刀具类型
     */
    public String getDjlx() {
      return djlx;
    }

    /**
     * 刀具类型
     */
    public void setDjlx(String djlx) {
      this.djlx = djlx;
    }


	public List<ZsJkXmbDjXhZTrDataXxDto> getItems() {
		return items;
	}

	public void setItems(List<ZsJkXmbDjXhZTrDataXxDto> items) {
		this.items = items;
	}
    
    

}