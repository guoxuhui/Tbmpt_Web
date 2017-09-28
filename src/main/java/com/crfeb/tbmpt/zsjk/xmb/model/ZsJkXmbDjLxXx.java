package com.crfeb.tbmpt.zsjk.xmb.model;

import java.io.Serializable;
import com.baomidou.mybatisplus.annotations.IdType;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.crfeb.tbmpt.commons.model.BaseModel;
import com.baomidou.mybatisplus.annotations.TableName;

/**
 * <p>项目部角度 刀具类型信息实体信息</p>
 * <p>描述：实体描述信息.</p>
 * <p>系统：展示界面接口</p>
 * <p>模块：项目部角度模块</p>
 * <p>日期：2017-01-09</p>
 * @version 1.0
 * @author YangYJ
 */
@TableName("zsjk_xmb_DjLxxx")
public class ZsJkXmbDjLxXx  implements Serializable {

    @TableId(type = IdType.UUID)
    private String id;

    /**
     * 名称<br>
     */
    @TableField(value = "mc")
    private String mc;

    public String getId() {
      return id;
    }

    public void setId(String id) {
      this.id = id;
    }

    /**
     * 名称
     */
    public String getMc() {
      return mc;
    }

    /**
     * 名称
     */
    public void setMc(String mc) {
      this.mc = mc;
    }

}