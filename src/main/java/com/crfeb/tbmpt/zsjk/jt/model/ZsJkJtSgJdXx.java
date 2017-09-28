package com.crfeb.tbmpt.zsjk.jt.model;

import java.io.Serializable;
import com.baomidou.mybatisplus.annotations.IdType;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.crfeb.tbmpt.commons.model.BaseModel;
import com.baomidou.mybatisplus.annotations.TableName;

/**
 * <p>所有项目总的施工进度信息实体信息</p>
 * <p>描述：实体描述信息.</p>
 * <p>系统：展示界面接口</p>
 * <p>模块：集团角度模块</p>
 * <p>日期：2017-01-09</p>
 * @version 1.0
 * @author YangYJ
 */
@TableName("zsjk_jt_sgjdxx")
public class ZsJkJtSgJdXx  implements Serializable {

    @TableId(type = IdType.UUID)
    private String id;

    /**
     * 年度<br>
     */
    @TableField(value = "nd")
    private String nd;

    /**
     * 所有项目施工进度的百分比<br>
     */
    @TableField(value = "allXMjdbfb")
    private String allXMjdbfb;

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
     * 所有项目施工进度的百分比
     */
    public String getAllXMjdbfb() {
      return allXMjdbfb;
    }

    /**
     * 所有项目施工进度的百分比
     */
    public void setAllXMjdbfb(String allXMjdbfb) {
      this.allXMjdbfb = allXMjdbfb;
    }

}