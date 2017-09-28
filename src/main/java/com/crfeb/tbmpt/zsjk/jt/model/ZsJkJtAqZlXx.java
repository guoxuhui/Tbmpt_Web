package com.crfeb.tbmpt.zsjk.jt.model;

import java.io.Serializable;
import com.baomidou.mybatisplus.annotations.IdType;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.crfeb.tbmpt.commons.model.BaseModel;
import com.baomidou.mybatisplus.annotations.TableName;

/**
 * <p>安全质量信息实体信息</p>
 * <p>描述：实体描述信息.</p>
 * <p>系统：展示界面接口</p>
 * <p>模块：集团角度模块</p>
 * <p>日期：2017-01-09</p>
 * @version 1.0
 * @author YangYJ
 */
@TableName("zsjk_jt_aqzlxx")
public class ZsJkJtAqZlXx  implements Serializable {

    @TableId(type = IdType.UUID)
    private String id;

    /**
     * 日期<br>
     */
    @TableField(value = "rq")
    private String rq;

    /**
     * 质量问题数<br>
     */
    @TableField(value = "zlwts")
    private String zlwts;

    /**
     * 安全问题数<br>
     */
    @TableField(value = "aqwts")
    private String aqwts;

    /**
     * 隐患数<br>
     */
    @TableField(value = "yhs")
    private String yhs;

    public String getId() {
      return id;
    }

    public void setId(String id) {
      this.id = id;
    }

    /**
     * 日期
     */
    public String getRq() {
      return rq;
    }

    /**
     * 日期
     */
    public void setRq(String rq) {
      this.rq = rq;
    }

    /**
     * 质量问题数
     */
    public String getZlwts() {
      return zlwts;
    }

    /**
     * 质量问题数
     */
    public void setZlwts(String zlwts) {
      this.zlwts = zlwts;
    }

    /**
     * 安全问题数
     */
    public String getAqwts() {
      return aqwts;
    }

    /**
     * 安全问题数
     */
    public void setAqwts(String aqwts) {
      this.aqwts = aqwts;
    }

    /**
     * 隐患数
     */
    public String getYhs() {
      return yhs;
    }

    /**
     * 隐患数
     */
    public void setYhs(String yhs) {
      this.yhs = yhs;
    }

}