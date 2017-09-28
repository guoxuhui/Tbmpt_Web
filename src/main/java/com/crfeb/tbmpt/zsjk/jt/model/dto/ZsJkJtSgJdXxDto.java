package com.crfeb.tbmpt.zsjk.jt.model.dto;

import com.crfeb.tbmpt.commons.model.BaseModel;
import java.io.Serializable;

public class ZsJkJtSgJdXxDto  implements Serializable {

    private static final long serialVersionUID = 1L;

    private String id;

    /**
     * 年度<br>
     */
    private String nd;

    /**
     * 所有项目施工进度的百分比<br>
     */
    private String allXMjdbfb;

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
     * 所有项目施工进度的百分比
     */
    public String getAllXMjdbfb() {
      return allXMjdbfb;
    }

    /**
     * 所有项目施工进度的百分比
     */
    public void setAllXMjdbfb(String allXMjdbfb) {
      this.allXMjdbfb = allXMjdbfb;
    }

}