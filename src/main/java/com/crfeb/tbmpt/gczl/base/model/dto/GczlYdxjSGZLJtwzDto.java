package com.crfeb.tbmpt.gczl.base.model.dto;

import java.io.Serializable;

import com.crfeb.tbmpt.commons.model.BaseModel;

public class GczlYdxjSGZLJtwzDto extends BaseModel implements Serializable {

    private static final long serialVersionUID = 1L;

    private String id;

    /**
     * 具体位置名称<br>
     * <font color="ff0000">必填项</font><br>
     */
    private String jtWz;

    /**
     * 排序号<br>
     */
    private String sortNum;

    /**
     * 使用状态<br>
     */
    private String ifQy;

    /**
     * 备注<br>
     */
    private String remarks;

    /**
     * 施工内容<br>
     * <font color="ff0000">必填项</font><br>
     */
    private String sgNr;

    public String getId() {
      return id;
    }

    public void setId(String id) {
      this.id = id;
    }

    /**
     * 具体位置名称
     */
    public String getJtWz() {
      return jtWz;
    }

    /**
     * 具体位置名称
     */
    public void setJtWz(String jtWz) {
      this.jtWz = jtWz;
    }

    /**
     * 排序号
     */
    public String getSortNum() {
      return sortNum;
    }

    /**
     * 排序号
     */
    public void setSortNum(String sortNum) {
      this.sortNum = sortNum;
    }

    /**
     * 使用状态
     */
    public String getIfQy() {
      return ifQy;
    }

    /**
     * 使用状态
     */
    public void setIfQy(String ifQy) {
      this.ifQy = ifQy;
    }

    /**
     * 备注
     */
    public String getRemarks() {
      return remarks;
    }

    /**
     * 备注
     */
    public void setRemarks(String remarks) {
      this.remarks = remarks;
    }

    /**
     * 施工内容
     */
    public String getSgNr() {
      return sgNr;
    }

    /**
     * 施工内容
     */
    public void setSgNr(String sgNr) {
      this.sgNr = sgNr;
    }

}