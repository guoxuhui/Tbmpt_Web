package com.crfeb.tbmpt.zsjk.jt.model.dto;

import com.crfeb.tbmpt.commons.model.BaseModel;
import java.io.Serializable;

public class ZsJkJtLjCzXxDto  implements Serializable {

    private static final long serialVersionUID = 1L;

    private String id;

    /**
     * 计划总产值<br>
     */
    private String jhzcz;

    /**
     * 总累计产值<br>
     */
    private String zljcz;

    public String getId() {
      return id;
    }

    public void setId(String id) {
      this.id = id;
    }

    /**
     * 计划总产值
     */
    public String getJhzcz() {
      return jhzcz;
    }

    /**
     * 计划总产值
     */
    public void setJhzcz(String jhzcz) {
      this.jhzcz = jhzcz;
    }

    /**
     * 总累计产值
     */
    public String getZljcz() {
      return zljcz;
    }

    /**
     * 总累计产值
     */
    public void setZljcz(String zljcz) {
      this.zljcz = zljcz;
    }

}