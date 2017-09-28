package com.crfeb.tbmpt.zsjk.jt.model.dto;

import com.crfeb.tbmpt.commons.model.BaseModel;
import java.io.Serializable;

public class ZsJkJtdgSgRyTrXxDto  implements Serializable {

    private static final long serialVersionUID = 1L;

    private String id;

    /**
     * 当前项目总数<br>
     */
    private String xmZrs;

    /**
     * 当前盾构队总数<br>
     */
    private String dgdZs;

    /**
     * 当前盾构队人员总数<br>
     */
    private String dgdZrs;

    /**
     * 平均每个盾构队人数<br>
     */
    private String dgdPjrs;

    public String getId() {
      return id;
    }

    public void setId(String id) {
      this.id = id;
    }

    /**
     * 当前项目总数
     */
    public String getXmZrs() {
      return xmZrs;
    }

    /**
     * 当前项目总数
     */
    public void setXmZrs(String xmZrs) {
      this.xmZrs = xmZrs;
    }

    /**
     * 当前盾构队总数
     */
    public String getDgdZs() {
      return dgdZs;
    }

    /**
     * 当前盾构队总数
     */
    public void setDgdZs(String dgdZs) {
      this.dgdZs = dgdZs;
    }

    /**
     * 当前盾构队人员总数
     */
    public String getDgdZrs() {
      return dgdZrs;
    }

    /**
     * 当前盾构队人员总数
     */
    public void setDgdZrs(String dgdZrs) {
      this.dgdZrs = dgdZrs;
    }

    /**
     * 平均每个盾构队人数
     */
    public String getDgdPjrs() {
      return dgdPjrs;
    }

    /**
     * 平均每个盾构队人数
     */
    public void setDgdPjrs(String dgdPjrs) {
      this.dgdPjrs = dgdPjrs;
    }

}