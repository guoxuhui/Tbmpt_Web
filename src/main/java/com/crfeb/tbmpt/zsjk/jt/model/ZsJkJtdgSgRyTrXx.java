package com.crfeb.tbmpt.zsjk.jt.model;

import java.io.Serializable;
import com.baomidou.mybatisplus.annotations.IdType;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.crfeb.tbmpt.commons.model.BaseModel;
import com.baomidou.mybatisplus.annotations.TableName;

/**
 * <p>集团角度项目的盾构施工人员投入信息实体信息</p>
 * <p>描述：实体描述信息.</p>
 * <p>系统：展示界面接口</p>
 * <p>模块：集团角度模块</p>
 * <p>日期：2017-01-09</p>
 * @version 1.0
 * @author YangYJ
 */
@TableName("zsjk_jt_dgSgRyTrxx")
public class ZsJkJtdgSgRyTrXx  implements Serializable {

    @TableId(type = IdType.UUID)
    private String id;

    /**
     * 当前项目总数<br>
     */
    @TableField(value = "xmZrs")
    private String xmZrs;

    /**
     * 当前盾构队总数<br>
     */
    @TableField(value = "dgdZs")
    private String dgdZs;

    /**
     * 当前盾构队人员总数<br>
     */
    @TableField(value = "dgdZrs")
    private String dgdZrs;

    /**
     * 平均每个盾构队人数<br>
     */
    @TableField(value = "dgdPjrs")
    private String dgdPjrs;

    public String getId() {
      return id;
    }

    public void setId(String id) {
      this.id = id;
    }

    /**
     * 当前项目总数
     */
    public String getXmZrs() {
      return xmZrs;
    }

    /**
     * 当前项目总数
     */
    public void setXmZrs(String xmZrs) {
      this.xmZrs = xmZrs;
    }

    /**
     * 当前盾构队总数
     */
    public String getDgdZs() {
      return dgdZs;
    }

    /**
     * 当前盾构队总数
     */
    public void setDgdZs(String dgdZs) {
      this.dgdZs = dgdZs;
    }

    /**
     * 当前盾构队人员总数
     */
    public String getDgdZrs() {
      return dgdZrs;
    }

    /**
     * 当前盾构队人员总数
     */
    public void setDgdZrs(String dgdZrs) {
      this.dgdZrs = dgdZrs;
    }

    /**
     * 平均每个盾构队人数
     */
    public String getDgdPjrs() {
      return dgdPjrs;
    }

    /**
     * 平均每个盾构队人数
     */
    public void setDgdPjrs(String dgdPjrs) {
      this.dgdPjrs = dgdPjrs;
    }

}