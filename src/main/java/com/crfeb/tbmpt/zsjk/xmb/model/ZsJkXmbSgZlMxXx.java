package com.crfeb.tbmpt.zsjk.xmb.model;

import java.io.Serializable;
import com.baomidou.mybatisplus.annotations.IdType;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.crfeb.tbmpt.commons.model.BaseModel;
import com.baomidou.mybatisplus.annotations.TableName;

/**
 * <p>项目部角度  结构施工质量问题明细信息实体信息</p>
 * <p>描述：实体描述信息.</p>
 * <p>系统：展示界面接口</p>
 * <p>模块：项目部角度模块</p>
 * <p>日期：2017-01-09</p>
 * @version 1.0
 * @author YangYJ
 */
@TableName("zsjk_xmb_SgZlMxxx")
public class ZsJkXmbSgZlMxXx  implements Serializable {

    @TableId(type = IdType.UUID)
    private String id;

    /**
     * 项目id<br>
     */
    @TableField(value = "projectId")
    private String projectId;

    /**
     * 日期<br>
     */
    @TableField(value = "sbDate")
    private String sbDate;

    /**
     * 分类<br>
     */
    @TableField(value = "type")
    private String type;

    /**
     * 详细信息<br>
     */
    @TableField(value = "detail")
    private String detail;

    /**
     * 项目名称<br>
     */
    @TableField(value = "projectName")
    private String projectName;

    /**
     * 获取结构施工质量问题明细数据<br>
     */
    @TableField(value = "miaoshu")
    private String miaoshu;

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
     * 分类
     */
    public String getType() {
      return type;
    }

    /**
     * 分类
     */
    public void setType(String type) {
      this.type = type;
    }

    /**
     * 详细信息
     */
    public String getDetail() {
      return detail;
    }

    /**
     * 详细信息
     */
    public void setDetail(String detail) {
      this.detail = detail;
    }

    /**
     * 项目名称
     */
    public String getProjectName() {
      return projectName;
    }

    /**
     * 项目名称
     */
    public void setProjectName(String projectName) {
      this.projectName = projectName;
    }

    /**
     * 获取结构施工质量问题明细数据
     */
    public String getMiaoshu() {
      return miaoshu;
    }

    /**
     * 获取结构施工质量问题明细数据
     */
    public void setMiaoshu(String miaoshu) {
      this.miaoshu = miaoshu;
    }

}