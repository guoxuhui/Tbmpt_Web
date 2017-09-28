package com.crfeb.tbmpt.zsjk.xmb.model;

import java.io.Serializable;
import com.baomidou.mybatisplus.annotations.IdType;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.crfeb.tbmpt.commons.model.BaseModel;
import com.baomidou.mybatisplus.annotations.TableName;

/**
 * <p>项目部角度  地面沉降情况（默认取当前里程的前后50米）实体信息</p>
 * <p>描述：实体描述信息.</p>
 * <p>系统：展示界面接口</p>
 * <p>模块：项目部角度模块</p>
 * <p>日期：2017-01-09</p>
 * @version 1.0
 * @author YangYJ
 */
@TableName("zsjk_xmb_DmCjJcxx")
public class ZsJkXmbDmCjJcXx  implements Serializable {

    @TableId(type = IdType.UUID)
    private String id;

    /**
     * 线路id<br>
     */
    @TableField(value = "xlId")
    private String xlId;

    /**
     * 里程<br>
     */
    @TableField(value = "lc")
    private String lc;

    /**
     * 日期<br>
     */
    @TableField(value = "sbDate")
    private String sbDate;

    /**
     * 点编号<br>
     */
    @TableField(value = "dianbh")
    private String dianbh;

    /**
     * 值<br>
     */
    @TableField(value = "zhi")
    private String zhi;

    public String getId() {
      return id;
    }

    public void setId(String id) {
      this.id = id;
    }

    /**
     * 线路id
     */
    public String getXlId() {
      return xlId;
    }

    /**
     * 线路id
     */
    public void setXlId(String xlId) {
      this.xlId = xlId;
    }

    /**
     * 里程
     */
    public String getLc() {
      return lc;
    }

    /**
     * 里程
     */
    public void setLc(String lc) {
      this.lc = lc;
    }

    /**
     * 日期
     */
    public String getSbDate() {
      return sbDate;
    }

    /**
     * 日期
     */
    public void setSbDate(String sbDate) {
      this.sbDate = sbDate;
    }

    /**
     * 点编号
     */
    public String getDianbh() {
      return dianbh;
    }

    /**
     * 点编号
     */
    public void setDianbh(String dianbh) {
      this.dianbh = dianbh;
    }

    /**
     * 值
     */
    public String getZhi() {
      return zhi;
    }

    /**
     * 值
     */
    public void setZhi(String zhi) {
      this.zhi = zhi;
    }

}