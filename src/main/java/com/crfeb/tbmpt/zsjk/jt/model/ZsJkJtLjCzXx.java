package com.crfeb.tbmpt.zsjk.jt.model;

import java.io.Serializable;
import com.baomidou.mybatisplus.annotations.IdType;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.crfeb.tbmpt.commons.model.BaseModel;
import com.baomidou.mybatisplus.annotations.TableName;

/**
 * <p>当前所有项目总累计产值信息实体信息</p>
 * <p>描述：实体描述信息.</p>
 * <p>系统：展示界面接口</p>
 * <p>模块：集团角度模块</p>
 * <p>日期：2017-01-09</p>
 * @version 1.0
 * @author YangYJ
 */
@TableName("zsjk_jt_ljczxx")
public class ZsJkJtLjCzXx  implements Serializable {

    @TableId(type = IdType.UUID)
    private String id;

    /**
     * 计划总产值<br>
     */
    @TableField(value = "jhzcz")
    private String jhzcz;

    /**
     * 总累计产值<br>
     */
    @TableField(value = "zljcz")
    private String zljcz;

    public String getId() {
      return id;
    }

    public void setId(String id) {
      this.id = id;
    }

    /**
     * 计划总产值
     */
    public String getJhzcz() {
      return jhzcz;
    }

    /**
     * 计划总产值
     */
    public void setJhzcz(String jhzcz) {
      this.jhzcz = jhzcz;
    }

    /**
     * 总累计产值
     */
    public String getZljcz() {
      return zljcz;
    }

    /**
     * 总累计产值
     */
    public void setZljcz(String zljcz) {
      this.zljcz = zljcz;
    }

}