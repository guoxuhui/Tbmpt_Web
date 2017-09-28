package com.crfeb.tbmpt.zsjk.xmb.model;

import java.io.Serializable;
import com.baomidou.mybatisplus.annotations.IdType;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.crfeb.tbmpt.commons.model.BaseModel;
import com.baomidou.mybatisplus.annotations.TableName;

/**
 * <p>项目部角度 项目各线路进度信息实体信息</p>
 * <p>描述：实体描述信息.</p>
 * <p>系统：展示界面接口</p>
 * <p>模块：项目部角度模块</p>
 * <p>日期：2017-01-09</p>
 * @version 1.0
 * @author YangYJ
 */
@TableName("zsjk_xmb_XlJdxx")
public class ZsJkXmbXlJdXx  implements Serializable {

    @TableId(type = IdType.UUID)
    private String id;

    /**
     * 项目ID<br>
     */
    @TableField(value = "xmId")
    private String xmId;

    /**
     * 项目名称<br>
     */
    @TableField(value = "xmMc")
    private String xmMc;

    /**
     * 线路ID<br>
     */
    @TableField(value = "xlId")
    private String xlId;

    /**
     * 区间名称+线路名称<br>
     */
    @TableField(value = "xlMc")
    private String xlMc;

    /**
     * 线路总环数<br>
     */
    @TableField(value = "xlZhs")
    private String xlZhs;

    /**
     * 已完成环数<br>
     */
    @TableField(value = "ywchs")
    private String ywchs;

    /**
     * 总工期（天）<br>
     */
    @TableField(value = "zgq")
    private String zgq;

    /**
     * 已完成天数<br>
     */
    @TableField(value = "ywcts")
    private String ywcts;

    public String getId() {
      return id;
    }

    public void setId(String id) {
      this.id = id;
    }

    /**
     * 项目ID
     */
    public String getXmId() {
      return xmId;
    }

    /**
     * 项目ID
     */
    public void setXmId(String xmId) {
      this.xmId = xmId;
    }

    /**
     * 项目名称
     */
    public String getXmMc() {
      return xmMc;
    }

    /**
     * 项目名称
     */
    public void setXmMc(String xmMc) {
      this.xmMc = xmMc;
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
     * 区间名称+线路名称
     */
    public String getXlMc() {
      return xlMc;
    }

    /**
     * 区间名称+线路名称
     */
    public void setXlMc(String xlMc) {
      this.xlMc = xlMc;
    }

    /**
     * 线路总环数
     */
    public String getXlZhs() {
      return xlZhs;
    }

    /**
     * 线路总环数
     */
    public void setXlZhs(String xlZhs) {
      this.xlZhs = xlZhs;
    }

    /**
     * 已完成环数
     */
    public String getYwchs() {
      return ywchs;
    }

    /**
     * 已完成环数
     */
    public void setYwchs(String ywchs) {
      this.ywchs = ywchs;
    }

    /**
     * 总工期（天）
     */
    public String getZgq() {
      return zgq;
    }

    /**
     * 总工期（天）
     */
    public void setZgq(String zgq) {
      this.zgq = zgq;
    }

    /**
     * 已完成天数
     */
    public String getYwcts() {
      return ywcts;
    }

    /**
     * 已完成天数
     */
    public void setYwcts(String ywcts) {
      this.ywcts = ywcts;
    }

}