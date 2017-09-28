package com.crfeb.tbmpt.zsjk.xmb.model.dto;

import com.crfeb.tbmpt.commons.model.BaseModel;
import java.io.Serializable;

public class ZsJkXmbDjLxXxDto  implements Serializable {

    private static final long serialVersionUID = 1L;

    private String id;

    /**
     * 名称<br>
     */
    private String mc;

    public String getId() {
      return id;
    }

    public void setId(String id) {
      this.id = id;
    }

    /**
     * 名称
     */
    public String getMc() {
      return mc;
    }

    /**
     * 名称
     */
    public void setMc(String mc) {
      this.mc = mc;
    }

}