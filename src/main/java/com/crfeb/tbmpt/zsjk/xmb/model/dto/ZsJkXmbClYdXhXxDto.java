package com.crfeb.tbmpt.zsjk.xmb.model.dto;

import com.baomidou.mybatisplus.annotations.TableField;
import com.crfeb.tbmpt.commons.model.BaseModel;
import java.io.Serializable;

public class ZsJkXmbClYdXhXxDto  implements Serializable {

    private static final long serialVersionUID = 1L;

    private String id;

    /**
     * 项目id<br>
     */
    private String xmId;

    /**
     * 月份<br>
     */
    private String yf;

    /**
     * 材料分类<br>
     */
    private String clfl;

    /**
     * 材料消耗计划投入值<br>
     */
    private String cljhtr;

    /**
     * 材料消耗实际投入值<br>
     */
    private String clsjtr;
    
    /**
     * 单位<br>
     */
    private String danWei;

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
     * 月份
     */
    public String getYf() {
      return yf;
    }

    /**
     * 月份
     */
    public void setYf(String yf) {
      this.yf = yf;
    }

    /**
     * 材料分类
     */
    public String getClfl() {
      return clfl;
    }

    /**
     * 材料分类
     */
    public void setClfl(String clfl) {
      this.clfl = clfl;
    }

    /**
     * 材料消耗计划投入值
     */
    public String getCljhtr() {
      return cljhtr;
    }

    /**
     * 材料消耗计划投入值
     */
    public void setCljhtr(String cljhtr) {
      this.cljhtr = cljhtr;
    }

    /**
     * 材料消耗实际投入值
     */
    public String getClsjtr() {
      return clsjtr;
    }

    /**
     * 材料消耗实际投入值
     */
    public void setClsjtr(String clsjtr) {
      this.clsjtr = clsjtr;
    }

	public String getDanWei() {
		return danWei;
	}

	public void setDanWei(String danWei) {
		this.danWei = danWei;
	}
    

}