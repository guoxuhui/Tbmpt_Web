package com.crfeb.tbmpt.zsjk.xmb.model;

import java.io.Serializable;
import com.baomidou.mybatisplus.annotations.IdType;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.crfeb.tbmpt.commons.model.BaseModel;
import com.baomidou.mybatisplus.annotations.TableName;

/**
 * <p>项目部角度 当前设备的维保（维修、保养）明细实体信息</p>
 * <p>描述：实体描述信息.</p>
 * <p>系统：展示界面接口</p>
 * <p>模块：项目部角度模块</p>
 * <p>日期：2017-01-09</p>
 * @version 1.0
 * @author YangYJ
 */
@TableName("zsjk_xmb_SbWbxx")
public class ZsJkXmbSbWbXx  implements Serializable {

    @TableId(type = IdType.UUID)
    private String id;

    /**
     * 设备ID<br>
     */
    @TableField(value = "sbId")
    private String sbId;

    /**
     * 日期<br>
     */
    @TableField(value = "rq")
    private String rq;

    /**
     * 设备名称<br>
     */
    @TableField(value = "sbmc")
    private String sbmc;

    /**
     * 次数<br>
     */
    @TableField(value = "cs")
    private String cs;

    public String getId() {
      return id;
    }

    public void setId(String id) {
      this.id = id;
    }

    /**
     * 设备ID
     */
    public String getSbId() {
      return sbId;
    }

    /**
     * 设备ID
     */
    public void setSbId(String sbId) {
      this.sbId = sbId;
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
     * 设备名称
     */
    public String getSbmc() {
      return sbmc;
    }

    /**
     * 设备名称
     */
    public void setSbmc(String sbmc) {
      this.sbmc = sbmc;
    }

    /**
     * 次数
     */
    public String getCs() {
      return cs;
    }

    /**
     * 次数
     */
    public void setCs(String cs) {
      this.cs = cs;
    }

}