package com.crfeb.tbmpt.zsjk.xmb.model.dto;

import java.io.Serializable;

public class ZsJkXmbXlJdMxXxDto implements Serializable{
	
	 private static final long serialVersionUID = 1L;
	 
	/**
     * 线路ID<br>
     */
    private String xlId;

    /**
     * 区间名称+线路名称<br>
     */
    private String xlMc;

    /**
     * 线路总环数<br>
     */
    private String xlZhs;

    /**
     * 已完成环数<br>
     */
    private String ywchs;

    /**
     * 总工期（天）<br>
     */
    private String zgq;

    /**
     * 已完成天数<br>
     */
    private String ywcts;
    
    /**
     * 线路ID
     */
    public String getXlId() {
      return xlId;
    }

    /**
     * 线路ID
     */
    public void setXlId(String xlId) {
      this.xlId = xlId;
    }

    /**
     * 区间名称+线路名称
     */
    public String getXlMc() {
      return xlMc;
    }

    /**
     * 区间名称+线路名称
     */
    public void setXlMc(String xlMc) {
      this.xlMc = xlMc;
    }

    /**
     * 线路总环数
     */
    public String getXlZhs() {
      return xlZhs;
    }

    /**
     * 线路总环数
     */
    public void setXlZhs(String xlZhs) {
      this.xlZhs = xlZhs;
    }

    /**
     * 已完成环数
     */
    public String getYwchs() {
      return ywchs;
    }

    /**
     * 已完成环数
     */
    public void setYwchs(String ywchs) {
      this.ywchs = ywchs;
    }

    /**
     * 总工期（天）
     */
    public String getZgq() {
      return zgq;
    }

    /**
     * 总工期（天）
     */
    public void setZgq(String zgq) {
      this.zgq = zgq;
    }

    /**
     * 已完成天数
     */
    public String getYwcts() {
      return ywcts;
    }

    /**
     * 已完成天数
     */
    public void setYwcts(String ywcts) {
      this.ywcts = ywcts;
    }

}
