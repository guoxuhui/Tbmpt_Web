package com.crfeb.tbmpt.zsjk.xmb.model.dto;

import com.crfeb.tbmpt.commons.model.BaseModel;
import java.io.Serializable;

public class ZsJkXmbGpZlWtsXxDto  implements Serializable {

    private static final long serialVersionUID = 1L;

    private String id;

    /**
     * 项目id<br>
     */
    private String projectId;

    /**
     * 日期<br>
     */
    private String sbDate;

    /**
     * 质量分类（管片破损、管片错台、螺栓复紧、渗漏水等）<br>
     */
    private String qualityType;

    /**
     * 数量<br>
     */
    private String num;

    /**
     * 获得各质量分类（管片破损、管片错台、螺栓复紧、渗漏水等）下的质量问题数<br>
     */
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