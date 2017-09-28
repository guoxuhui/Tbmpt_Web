package com.crfeb.tbmpt.zsjk.jt.model.dto;

import com.crfeb.tbmpt.commons.model.BaseModel;
import java.io.Serializable;

public class ZsJkJtZySbTrXxDto  implements Serializable {

    private static final long serialVersionUID = 1L;

    private String id;

    /**
     * 设备类别（盾构机、电瓶车、龙门吊）<br>
     */
    private String sbLb;

    /**
     * 租入数量<br>
     */
    private String zrSl;

    /**
     * 租出数量<br>
     */
    private String zcSl;

    /**
     * 检修数量<br>
     */
    private String jxSl;

    /**
     * 运行数量 <br>
     */
    private String yxSl;

    /**
     * 运输数量<br>
     */
    private String ysSl;

    public String getId() {
      return id;
    }

    public void setId(String id) {
      this.id = id;
    }

    /**
     * 设备类别（盾构机、电瓶车、龙门吊）
     */
    public String getSbLb() {
      return sbLb;
    }

    /**
     * 设备类别（盾构机、电瓶车、龙门吊）
     */
    public void setSbLb(String sbLb) {
      this.sbLb = sbLb;
    }

    /**
     * 租入数量
     */
    public String getZrSl() {
      return zrSl;
    }

    /**
     * 租入数量
     */
    public void setZrSl(String zrSl) {
      this.zrSl = zrSl;
    }

    /**
     * 租出数量
     */
    public String getZcSl() {
      return zcSl;
    }

    /**
     * 租出数量
     */
    public void setZcSl(String zcSl) {
      this.zcSl = zcSl;
    }

    /**
     * 检修数量
     */
    public String getJxSl() {
      return jxSl;
    }

    /**
     * 检修数量
     */
    public void setJxSl(String jxSl) {
      this.jxSl = jxSl;
    }

    /**
     * 运行数量 
     */
    public String getYxSl() {
      return yxSl;
    }

    /**
     * 运行数量 
     */
    public void setYxSl(String yxSl) {
      this.yxSl = yxSl;
    }

    /**
     * 运输数量
     */
    public String getYsSl() {
      return ysSl;
    }

    /**
     * 运输数量
     */
    public void setYsSl(String ysSl) {
      this.ysSl = ysSl;
    }

}