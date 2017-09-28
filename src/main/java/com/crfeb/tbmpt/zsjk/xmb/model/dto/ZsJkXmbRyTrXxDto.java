package com.crfeb.tbmpt.zsjk.xmb.model.dto;

import com.crfeb.tbmpt.commons.model.BaseModel;
import java.io.Serializable;

public class ZsJkXmbRyTrXxDto  implements Serializable {

    private static final long serialVersionUID = 1L;

    private String id;

    /**
     * 项目ID<br>
     */
    private String xmId;

    /**
     * 部门名称<br>
     */
    private String bmmc;

    /**
     * 人数<br>
     */
    private String rs;

    /**
     * 上级部门<br>
     */
    private String pid;

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
     * 部门名称
     */
    public String getBmmc() {
      return bmmc;
    }

    /**
     * 部门名称
     */
    public void setBmmc(String bmmc) {
      this.bmmc = bmmc;
    }

    /**
     * 人数
     */
    public String getRs() {
      return rs;
    }

    /**
     * 人数
     */
    public void setRs(String rs) {
      this.rs = rs;
    }

    /**
     * 上级部门
     */
    public String getPid() {
      return pid;
    }

    /**
     * 上级部门
     */
    public void setPid(String pid) {
      this.pid = pid;
    }

}