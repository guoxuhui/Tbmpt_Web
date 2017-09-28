package com.crfeb.tbmpt.zsjk.xmb.model;

import java.io.Serializable;
import com.baomidou.mybatisplus.annotations.IdType;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.crfeb.tbmpt.commons.model.BaseModel;
import com.baomidou.mybatisplus.annotations.TableName;

/**
 * <p>项目部角度  各质量分类（管片破损、管片错台、螺栓复紧、渗漏水等）下的管片质量问题数量信息实体信息</p>
 * <p>描述：实体描述信息.</p>
 * <p>系统：展示界面接口</p>
 * <p>模块：项目部角度模块</p>
 * <p>日期：2017-01-09</p>
 * @version 1.0
 * @author YangYJ
 */
@TableName("zsjk_xmb_GpZlWtsxx")
public class ZsJkXmbGpZlWtsXx  implements Serializable {

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
     * 质量分类（管片破损、管片错台、螺栓复紧、渗漏水等）<br>
     */
    @TableField(value = "qualityType")
    private String qualityType;

    /**
     * 数量<br>
     */
    @TableField(value = "num")
    private String num;

    /**
     * 获得各质量分类（管片破损、管片错台、螺栓复紧、渗漏水等）下的质量问题数<br>
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
     * 质量分类（管片破损、管片错台、螺栓复紧、渗漏水等）
     */
    public String getQualityType() {
      return qualityType;
    }

    /**
     * 质量分类（管片破损、管片错台、螺栓复紧、渗漏水等）
     */
    public void setQualityType(String qualityType) {
      this.qualityType = qualityType;
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

    /**
     * 获得各质量分类（管片破损、管片错台、螺栓复紧、渗漏水等）下的质量问题数
     */
    public String getMiaoshu() {
      return miaoshu;
    }

    /**
     * 获得各质量分类（管片破损、管片错台、螺栓复紧、渗漏水等）下的质量问题数
     */
    public void setMiaoshu(String miaoshu) {
      this.miaoshu = miaoshu;
    }

}