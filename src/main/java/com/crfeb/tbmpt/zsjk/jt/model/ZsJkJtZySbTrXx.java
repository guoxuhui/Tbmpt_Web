package com.crfeb.tbmpt.zsjk.jt.model;

import java.io.Serializable;
import com.baomidou.mybatisplus.annotations.IdType;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.crfeb.tbmpt.commons.model.BaseModel;
import com.baomidou.mybatisplus.annotations.TableName;

/**
 * <p>集团角度 主要设备投入情况信息实体信息</p>
 * <p>描述：实体描述信息.</p>
 * <p>系统：展示界面接口</p>
 * <p>模块：集团角度模块</p>
 * <p>日期：2017-01-09</p>
 * @version 1.0
 * @author YangYJ
 */
@TableName("zsjk_jt_ZySbTrxx")
public class ZsJkJtZySbTrXx  implements Serializable {

    @TableId(type = IdType.UUID)
    private String id;

    /**
     * 设备类别（盾构机、电瓶车、龙门吊）<br>
     */
    @TableField(value = "sbLb")
    private String sbLb;

    /**
     * 租入数量<br>
     */
    @TableField(value = "zrSl")
    private String zrSl;

    /**
     * 租出数量<br>
     */
    @TableField(value = "zcSl")
    private String zcSl;

    /**
     * 检修数量<br>
     */
    @TableField(value = "jxSl")
    private String jxSl;

    /**
     * 运行数量 <br>
     */
    @TableField(value = "yxSl")
    private String yxSl;

    /**
     * 运输数量<br>
     */
    @TableField(value = "ysSl")
    private String ysSl;

    public String getId() {
      return id;
    }

    public void setId(String id) {
      this.id = id;
    }

    /**
     * 设备类别（盾构机、电瓶车、龙门吊）
     */
    public String getSbLb() {
      return sbLb;
    }

    /**
     * 设备类别（盾构机、电瓶车、龙门吊）
     */
    public void setSbLb(String sbLb) {
      this.sbLb = sbLb;
    }

    /**
     * 租入数量
     */
    public String getZrSl() {
      return zrSl;
    }

    /**
     * 租入数量
     */
    public void setZrSl(String zrSl) {
      this.zrSl = zrSl;
    }

    /**
     * 租出数量
     */
    public String getZcSl() {
      return zcSl;
    }

    /**
     * 租出数量
     */
    public void setZcSl(String zcSl) {
      this.zcSl = zcSl;
    }

    /**
     * 检修数量
     */
    public String getJxSl() {
      return jxSl;
    }

    /**
     * 检修数量
     */
    public void setJxSl(String jxSl) {
      this.jxSl = jxSl;
    }

    /**
     * 运行数量 
     */
    public String getYxSl() {
      return yxSl;
    }

    /**
     * 运行数量 
     */
    public void setYxSl(String yxSl) {
      this.yxSl = yxSl;
    }

    /**
     * 运输数量
     */
    public String getYsSl() {
      return ysSl;
    }

    /**
     * 运输数量
     */
    public void setYsSl(String ysSl) {
      this.ysSl = ysSl;
    }

}