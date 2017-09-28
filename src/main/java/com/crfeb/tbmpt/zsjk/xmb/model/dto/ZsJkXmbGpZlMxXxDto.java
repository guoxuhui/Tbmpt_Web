package com.crfeb.tbmpt.zsjk.xmb.model.dto;

import com.crfeb.tbmpt.commons.model.BaseModel;
import java.io.Serializable;

public class ZsJkXmbGpZlMxXxDto  implements Serializable {

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
     * 分类<br>
     */
    private String type;

    /**
     * 详细信息<br>
     */
    private String detail;

    /**
     * 项目名称<br>
     */
    private String projectName;

    /**
     * 获取管片质量问题明细数据<br>
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
     * 获取管片质量问题明细数据
     */
    public String getMiaoshu() {
      return miaoshu;
    }

    /**
     * 获取管片质量问题明细数据
     */
    public void setMiaoshu(String miaoshu) {
      this.miaoshu = miaoshu;
    }

}