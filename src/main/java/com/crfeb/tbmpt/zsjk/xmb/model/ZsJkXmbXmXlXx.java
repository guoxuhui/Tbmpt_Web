package com.crfeb.tbmpt.zsjk.xmb.model;

import java.io.Serializable;
import com.baomidou.mybatisplus.annotations.IdType;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.crfeb.tbmpt.commons.model.BaseModel;
import com.baomidou.mybatisplus.annotations.TableName;

/**
 * <p>项目部角度 项目下线路信息实体信息</p>
 * <p>描述：实体描述信息.</p>
 * <p>系统：展示界面接口</p>
 * <p>模块：项目部角度模块</p>
 * <p>日期：2017-01-09</p>
 * @version 1.0
 * @author YangYJ
 */
@TableName("zsjk_xmb_XmXlxx")
public class ZsJkXmbXmXlXx  implements Serializable {

    @TableId(type = IdType.UUID)
    private String id;

    /**
     * 项目ID<br>
     */
    @TableField(value = "xmId")
    private String xmId;

    /**
     * 线路ID<br>
     */
    @TableField(value = "xlId")
    private String xlId;

    /**
     * 区间+线路<br>
     */
    @TableField(value = "xlMc")
    private String xlMc;

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
     * 区间+线路
     */
    public String getXlMc() {
      return xlMc;
    }

    /**
     * 区间+线路
     */
    public void setXlMc(String xlMc) {
      this.xlMc = xlMc;
    }

}