package com.crfeb.tbmpt.zsjk.xmb.model;

import java.io.Serializable;
import com.baomidou.mybatisplus.annotations.IdType;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.crfeb.tbmpt.commons.model.BaseModel;
import com.baomidou.mybatisplus.annotations.TableName;

/**
 * <p>项目部角度  当前项目的累计产值明细信息实体信息</p>
 * <p>描述：实体描述信息.</p>
 * <p>系统：展示界面接口</p>
 * <p>模块：项目部角度模块</p>
 * <p>日期：2017-01-09</p>
 * @version 1.0
 * @author YangYJ
 */
@TableName("zsjk_xmb_XmLjCzMxxx")
public class ZsJkXmbXmLjCzMxXx  implements Serializable {

    @TableId(type = IdType.UUID)
    private String id;

    /**
     * 项目id<br>
     */
    @TableField(value = "xmId")
    private String xmId;

    /**
     * 主表主键<br>
     */
    @TableField(value = "fId")
    private String fId;

    /**
     * 月份<br>
     */
    @TableField(value = "mouth")
    private String mouth;

    /**
     * 区间名称+线路名称<br>
     */
    @TableField(value = "qujxl")
    private String qujxl;

    /**
     * 本月产值<br>
     */
    @TableField(value = "benycz")
    private String benycz;

    /**
     * 月计划产值<br>
     */
    @TableField(value = "yuejhcz")
    private String yuejhcz;

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
     * 主表主键
     */
    public String getFId() {
      return fId;
    }

    /**
     * 主表主键
     */
    public void setFId(String fId) {
      this.fId = fId;
    }

    /**
     * 月份
     */
    public String getMouth() {
      return mouth;
    }

    /**
     * 月份
     */
    public void setMouth(String mouth) {
      this.mouth = mouth;
    }

    /**
     * 区间名称+线路名称
     */
    public String getQujxl() {
      return qujxl;
    }

    /**
     * 区间名称+线路名称
     */
    public void setQujxl(String qujxl) {
      this.qujxl = qujxl;
    }

    /**
     * 本月产值
     */
    public String getBenycz() {
      return benycz;
    }

    /**
     * 本月产值
     */
    public void setBenycz(String benycz) {
      this.benycz = benycz;
    }

    /**
     * 月计划产值
     */
    public String getYuejhcz() {
      return yuejhcz;
    }

    /**
     * 月计划产值
     */
    public void setYuejhcz(String yuejhcz) {
      this.yuejhcz = yuejhcz;
    }

}