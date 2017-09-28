package com.crfeb.tbmpt.aqxj.xjdgl.model;

import java.io.Serializable;
import com.baomidou.mybatisplus.annotations.IdType;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.crfeb.tbmpt.commons.model.BaseModel;
import com.baomidou.mybatisplus.annotations.TableName;

/**
 * <p>安全巡检内容实体信息</p>
 * <p>描述：实体描述信息.</p>
 * <p>系统：工程项目安全巡检系统</p>
 * <p>模块：编辑巡检内容</p>
 * <p>日期：2017-05-27</p>
 * @version 1.0
 * @author zhuyabing
 */
@TableName("ZLXJ_XUNJIAN_NR")
public class AqxjXjnr extends BaseModel implements Serializable {

    @TableId(type = IdType.UUID)
    private String id;

    /**
     * 巡检内容<br>
     * <font color="ff0000">必填项</font><br>
     */
    @TableField(value = "mingCheng")
    private String mingCheng;

    /**
     * 巡检点Id<br>
     */
    @TableField(value = "ItemId")
    private String ItemId;

    /**
     * 顺序号<br>
     * <font color="ff0000">必填项</font><br>
     */
    @TableField(value = "xuHao")
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