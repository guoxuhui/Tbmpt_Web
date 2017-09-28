package com.crfeb.tbmpt.zsjk.xmb.model;

import java.io.Serializable;
import com.baomidou.mybatisplus.annotations.IdType;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.crfeb.tbmpt.commons.model.BaseModel;
import com.baomidou.mybatisplus.annotations.TableName;

/**
 * <p>项目部角度  结构施工质量问题信息实体信息</p>
 * <p>描述：实体描述信息.</p>
 * <p>系统：展示界面接口</p>
 * <p>模块：项目部角度模块</p>
 * <p>日期：2017-01-09</p>
 * @version 1.0
 * @author YangYJ
 */
@TableName("zsjk_xmb_SgZlxx")
public class ZsJkXmbSgZlXx  implements Serializable {

    @TableId(type = IdType.UUID)
    private String id;

    /**
     * 项目id<br>
     */
    @TableField(value = "projectId")
    private String projectId;

    /**
     * 获得各结构施工质量（使用部位、工程）下的质量问题数量<br>
     */
    @TableField(value = "miaoshu")
    private String miaoshu;

    /**
     * 日期<br>
     */
    @TableField(value = "sbDate")
    private String sbDate;

    /**
     * 结构施工质量分类<br>
     */
    @TableField(value = "shigzlfl")
    private String shigzlfl;

    /**
     * 数量<br>
     */
    @TableField(value = "num")
    private String num;

    public String getId() {
      return id;
    }

    public void setId(String id) {
      this.id = id;
    }

    /**
     * 项目id
     */
    public String getProjectId() {
      return projectId;
    }

    /**
     * 项目id
     */
    public void setProjectId(String projectId) {
      this.projectId = projectId;
    }

    /**
     * 获得各结构施工质量（使用部位、工程）下的质量问题数量
     */
    public String getMiaoshu() {
      return miaoshu;
    }

    /**
     * 获得各结构施工质量（使用部位、工程）下的质量问题数量
     */
    public void setMiaoshu(String miaoshu) {
      this.miaoshu = miaoshu;
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
     * 结构施工质量分类
     */
    public String getShigzlfl() {
      return shigzlfl;
    }

    /**
     * 结构施工质量分类
     */
    public void setShigzlfl(String shigzlfl) {
      this.shigzlfl = shigzlfl;
    }

    /**
     * 数量
     */
    public String getNum() {
      return num;
    }

    /**
     * 数量
     */
    public void setNum(String num) {
      this.num = num;
    }

}