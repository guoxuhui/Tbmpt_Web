package com.crfeb.tbmpt.zsjk.xmb.model;

import java.io.Serializable;
import com.baomidou.mybatisplus.annotations.IdType;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.crfeb.tbmpt.commons.model.BaseModel;
import com.baomidou.mybatisplus.annotations.TableName;

/**
 * <p>项目部角度 项目人员投入信息实体信息</p>
 * <p>描述：实体描述信息.</p>
 * <p>系统：展示界面接口</p>
 * <p>模块：项目部角度模块</p>
 * <p>日期：2017-01-09</p>
 * @version 1.0
 * @author YangYJ
 */
@TableName("zsjk_xmb_RyTrxx")
public class ZsJkXmbRyTrXx  implements Serializable {

    @TableId(type = IdType.UUID)
    private String id;

    /**
     * 项目ID<br>
     */
    @TableField(value = "xmId")
    private String xmId;

    /**
     * 部门名称<br>
     */
    @TableField(value = "bmmc")
    private String bmmc;

    /**
     * 人数<br>
     */
    @TableField(value = "rs")
    private String rs;

    /**
     * 上级部门<br>
     */
    @TableField(value = "pid")
    private String pid;

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
     * 部门名称
     */
    public String getBmmc() {
      return bmmc;
    }

    /**
     * 部门名称
     */
    public void setBmmc(String bmmc) {
      this.bmmc = bmmc;
    }

    /**
     * 人数
     */
    public String getRs() {
      return rs;
    }

    /**
     * 人数
     */
    public void setRs(String rs) {
      this.rs = rs;
    }

    /**
     * 上级部门
     */
    public String getPid() {
      return pid;
    }

    /**
     * 上级部门
     */
    public void setPid(String pid) {
      this.pid = pid;
    }

}