package com.crfeb.tbmpt.aqxj.xjdgl.model.dto;

import com.crfeb.tbmpt.commons.model.BaseModel;
import java.io.Serializable;

public class AqxjXjnrDto extends BaseModel implements Serializable {

    private static final long serialVersionUID = 1L;

    private String id;

    /**
     * 巡检内容<br>
     * <font color="ff0000">必填项</font><br>
     */
    private String mingCheng;

    /**
     * 巡检点Id<br>
     */
    private String ItemId;

    /**
     * 顺序号<br>
     * <font color="ff0000">必填项</font><br>
     */
    private String xuHao;

    public String getId() {
      return id;
    }

    public void setId(String id) {
      this.id = id;
    }

    /**
     * 巡检内容
     */
    public String getMingCheng() {
      return mingCheng;
    }

    /**
     * 巡检内容
     */
    public void setMingCheng(String mingCheng) {
      this.mingCheng = mingCheng;
    }

    /**
     * 巡检点Id
     */
    public String getItemId() {
      return ItemId;
    }

    /**
     * 巡检点Id
     */
    public void setItemId(String ItemId) {
      this.ItemId = ItemId;
    }

    /**
     * 顺序号
     */
    public String getXuHao() {
      return xuHao;
    }

    /**
     * 顺序号
     */
    public void setXuHao(String xuHao) {
      this.xuHao = xuHao;
    }

}