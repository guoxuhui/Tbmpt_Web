package com.crfeb.tbmpt.zsjk.xmb.model;

import java.io.Serializable;
import com.baomidou.mybatisplus.annotations.IdType;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.crfeb.tbmpt.commons.model.BaseModel;
import com.baomidou.mybatisplus.annotations.TableName;

/**
 * <p>项目部角度 刀具消耗总投入信息实体信息</p>
 * <p>描述：实体描述信息.</p>
 * <p>系统：展示界面接口</p>
 * <p>模块：项目部角度模块</p>
 * <p>日期：2017-01-09</p>
 * @version 1.0
 * @author YangYJ
 */
@TableName("zsjk_xmb_DjXhZTrxx")
public class ZsJkXmbDjXhZTrXx  implements Serializable {

    @TableId(type = IdType.UUID)
    private String id;

    /**
     * X轴标题<br>
     */
    @TableField(value = "xTitle")
    private String xTitle;

    /**
     * Y轴标题<br>
     */
    @TableField(value = "yTitle")
    private String yTitle;

    /**
     * 线路ID<br>
     */
    @TableField(value = "xlId")
    private String xlId;

    /**
     * 分析内容<br>
     */
    @TableField(value = "fxnr")
    private String fxnr;

    /**
     * 刀具类型<br>
     */
    @TableField(value = "djlx")
    private String djlx;

    public String getId() {
      return id;
    }

    public void setId(String id) {
      this.id = id;
    }

    /**
     * X轴标题
     */
    public String getXTitle() {
      return xTitle;
    }

    /**
     * X轴标题
     */
    public void setXTitle(String xTitle) {
      this.xTitle = xTitle;
    }

    /**
     * Y轴标题
     */
    public String getYTitle() {
      return yTitle;
    }

    /**
     * Y轴标题
     */
    public void setYTitle(String yTitle) {
      this.yTitle = yTitle;
    }

    /**
     * 线路ID
     */
    public String getXlId() {
      return xlId;
    }

    /**
     * 线路ID
     */
    public void setXlId(String xlId) {
      this.xlId = xlId;
    }

    /**
     * 分析内容
     */
    public String getFxnr() {
      return fxnr;
    }

    /**
     * 分析内容
     */
    public void setFxnr(String fxnr) {
      this.fxnr = fxnr;
    }

    /**
     * 刀具类型
     */
    public String getDjlx() {
      return djlx;
    }

    /**
     * 刀具类型
     */
    public void setDjlx(String djlx) {
      this.djlx = djlx;
    }

}