package com.crfeb.tbmpt.zsjk.xmb.model.dto;

import com.crfeb.tbmpt.commons.model.BaseModel;
import java.io.Serializable;

public class ZsJkXmbDjXhZTrDataXxDto  implements Serializable {

    private static final long serialVersionUID = 1L;

    private String id;

    /**
     * 主表id<br>
     */
    private String fId;

    /**
     * X轴数据<br>
     */
    private String x;

    /**
     * Y轴数据<br>
     */
    private String y;

    /**
     * 环号<br>
     */
    private String hH;

    public String getId() {
      return id;
    }

    public void setId(String id) {
      this.id = id;
    }

    /**
     * 主表id
     */
    public String getFId() {
      return fId;
    }

    /**
     * 主表id
     */
    public void setFId(String fId) {
      this.fId = fId;
    }

    /**
     * X轴数据
     */
    public String getX() {
      return x;
    }

    /**
     * X轴数据
     */
    public void setX(String x) {
      this.x = x;
    }

    /**
     * Y轴数据
     */
    public String getY() {
      return y;
    }

    /**
     * Y轴数据
     */
    public void setY(String y) {
      this.y = y;
    }

    /**
     * 环号
     */
    public String getHH() {
      return hH;
    }

    /**
     * 环号
     */
    public void setHH(String hH) {
      this.hH = hH;
    }

}