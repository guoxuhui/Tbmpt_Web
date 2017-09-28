package com.crfeb.tbmpt.zsjk.xmb.model;

import java.io.Serializable;
import com.baomidou.mybatisplus.annotations.IdType;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.crfeb.tbmpt.commons.model.BaseModel;
import com.baomidou.mybatisplus.annotations.TableName;

/**
 * <p>项目部角度 刀具消耗总投入明细信息实体信息</p>
 * <p>描述：实体描述信息.</p>
 * <p>系统：展示界面接口</p>
 * <p>模块：项目部角度模块</p>
 * <p>日期：2017-01-09</p>
 * @version 1.0
 * @author YangYJ
 */
@TableName("zsjk_xmb_DjXhZTrDataxx")
public class ZsJkXmbDjXhZTrDataXx  implements Serializable {

    @TableId(type = IdType.UUID)
    private String id;

    /**
     * 主表id<br>
     */
    @TableField(value = "fId")
    private String fId;

    /**
     * X轴数据<br>
     */
    @TableField(value = "x")
    private String x;

    /**
     * Y轴数据<br>
     */
    @TableField(value = "y")
    private String y;

    /**
     * 环号<br>
     */
    @TableField(value = "hH")
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