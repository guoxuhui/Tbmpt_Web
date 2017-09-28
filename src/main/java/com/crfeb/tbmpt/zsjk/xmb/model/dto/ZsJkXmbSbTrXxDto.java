package com.crfeb.tbmpt.zsjk.xmb.model.dto;

import java.io.Serializable;

public class ZsJkXmbSbTrXxDto  implements Serializable {

    private static final long serialVersionUID = 1L;

    private String id;

    /**
     * 项目id<br>
     */
    private String xmId;
    /**
     * 项目名称<br>
     */
    private String xmmc;

    /**
     * 设备名称<br>
     */
    private String sbmc;

    /**
     * 所在线路名称<br>
     */
    private String xlmc;

    /**
     * 厂商<br>
     */
    private String cs;

    /**
     * 型号<br>
     */
    private String xh;

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
     * 所在线路名称
     */
    public String getXlmc() {
      return xlmc;
    }

    /**
     * 所在线路名称
     */
    public void setXlmc(String xlmc) {
      this.xlmc = xlmc;
    }

    /**
     * 厂商
     */
    public String getCs() {
      return cs;
    }

    /**
     * 厂商
     */
    public void setCs(String cs) {
      this.cs = cs;
    }

    /**
     * 型号
     */
    public String getXh() {
      return xh;
    }

    /**
     * 型号
     */
    public void setXh(String xh) {
      this.xh = xh;
    }

	public String getXmmc() {
		return xmmc;
	}

	public void setXmmc(String xmmc) {
		this.xmmc = xmmc;
	}
    

}