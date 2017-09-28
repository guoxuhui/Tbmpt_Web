package com.crfeb.tbmpt.monitor.model.dto;

import java.io.Serializable;

public class XmbXmXlXxDto  implements Serializable {

    private static final long serialVersionUID = 1L;

    private String id;

    /**
     * 项目ID<br>
     */
    private String xmId;

    /**
     * 线路ID<br>
     */
    private String xlId;

    /**
     * 区间+线路<br>
     */
    private String xlMc;

    public String getId() {
      return id;
    }

    public void setId(String id) {
      this.id = id;
    }

    /**
     * 项目ID
     */
    public String getXmId() {
      return xmId;
    }

    /**
     * 项目ID
     */
    public void setXmId(String xmId) {
      this.xmId = xmId;
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
     * 区间+线路
     */
    public String getXlMc() {
      return xlMc;
    }

    /**
     * 区间+线路
     */
    public void setXlMc(String xlMc) {
      this.xlMc = xlMc;
    }

}