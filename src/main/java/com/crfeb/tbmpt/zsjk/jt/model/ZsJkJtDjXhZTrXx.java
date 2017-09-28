package com.crfeb.tbmpt.zsjk.jt.model;

import java.io.Serializable;
import com.baomidou.mybatisplus.annotations.IdType;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.crfeb.tbmpt.commons.model.BaseModel;
import com.baomidou.mybatisplus.annotations.TableName;

/**
 * <p>集团角度刀具消耗总投入信息实体信息</p>
 * <p>描述：实体描述信息.</p>
 * <p>系统：展示界面接口</p>
 * <p>模块：集团角度模块</p>
 * <p>日期：2017-01-09</p>
 * @version 1.0
 * @author YangYJ
 */
@TableName("zsjk_jt_djxhztrxx")
public class ZsJkJtDjXhZTrXx  implements Serializable {

    @TableId(type = IdType.UUID)
    private String id;

    /**
     * 年度<br>
     */
    @TableField(value = "nd")
    private String nd;

    /**
     * 刀具消耗计划投入值<br>
     */
    @TableField(value = "jhTrz")
    private String jhTrz;

    /**
     * 刀具消耗实际投入值<br>
     */
    @TableField(value = "sjTrz")
    private String sjTrz;

    public String getId() {
      return id;
    }

    public void setId(String id) {
      this.id = id;
    }

    /**
     * 年度
     */
    public String getNd() {
      return nd;
    }

    /**
     * 年度
     */
    public void setNd(String nd) {
      this.nd = nd;
    }

    /**
     * 刀具消耗计划投入值
     */
    public String getJhTrz() {
      return jhTrz;
    }

    /**
     * 刀具消耗计划投入值
     */
    public void setJhTrz(String jhTrz) {
      this.jhTrz = jhTrz;
    }

    /**
     * 刀具消耗实际投入值
     */
    public String getSjTrz() {
      return sjTrz;
    }

    /**
     * 刀具消耗实际投入值
     */
    public void setSjTrz(String sjTrz) {
      this.sjTrz = sjTrz;
    }

}