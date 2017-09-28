package com.crfeb.tbmpt.zsjk.xmb.model.dto;

import java.io.Serializable;

public class ZsJkXmbSgZlXxDto  implements Serializable {

    private static final long serialVersionUID = 1L;

    private String id;

    /**
     * 项目id<br>
     */
    private String projectId;

    /**
     * 获得各结构施工质量（使用部位、工程）下的质量问题数量<br>
     */
    private String miaoshu;

    /**
     * 日期<br>
     */
    private String sbDate;

    /**
     * 结构施工质量分类<br>
     */
    private String shigzlfl;

    /**
     * 数量<br>
     */
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