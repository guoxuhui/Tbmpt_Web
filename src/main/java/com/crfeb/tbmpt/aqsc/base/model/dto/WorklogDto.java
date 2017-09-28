package com.crfeb.tbmpt.aqsc.base.model.dto;

import com.crfeb.tbmpt.commons.model.BaseModel;
import java.io.Serializable;

public class WorklogDto extends BaseModel implements Serializable {

    private static final long serialVersionUID = 1L;

    private String id;

    /**
     * 工作日期<br>
     */
    private String workDay;

    /**
     * 工作内容<br>
     * <font color="ff0000">必填项</font><br>
     */
    private String content;
    
    /**
     * 查询起始日期
     */
    private String starDay;
    
    /**
     * 查询截止日期
     */
    private String endDay;
    
    /**
     * 项目编号
     */
    private String xmBh;
    
    /**
     * 项目名称
     */
    private String xmName;

    public String getId() {
      return id;
    }

    public void setId(String id) {
      this.id = id;
    }

    /**
     * 工作日期
     */
    public String getWorkDay() {
      return workDay;
    }

    /**
     * 工作日期
     */
    public void setWorkDay(String workDay) {
      this.workDay = workDay;
    }

    /**
     * 工作内容
     */
    public String getContent() {
      return content;
    }

    /**
     * 工作内容
     */
    public void setContent(String content) {
      this.content = content;
    }

	public String getStarDay() {
		return starDay;
	}

	public void setStarDay(String starDay) {
		this.starDay = starDay;
	}

	public String getEndDay() {
		return endDay;
	}

	public void setEndDay(String endDay) {
		this.endDay = endDay;
	}

	public String getXmBh() {
		return xmBh;
	}

	public void setXmBh(String xmBh) {
		this.xmBh = xmBh;
	}

	public String getXmName() {
		return xmName;
	}

	public void setXmName(String xmName) {
		this.xmName = xmName;
	}
    
    

}