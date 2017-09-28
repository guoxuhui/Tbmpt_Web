package com.crfeb.tbmpt.aqsc.base.model;

import java.io.Serializable;
import com.baomidou.mybatisplus.annotations.IdType;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.crfeb.tbmpt.commons.model.BaseModel;
import com.baomidou.mybatisplus.annotations.TableName;

/**
 * <p>工作日志实体信息</p>
 * <p>描述：实体描述信息.</p>
 * <p>系统：安全生产管理</p>
 * <p>模块：基础信息</p>
 * <p>日期：2017-02-20</p>
 * @version 1.0
 * @author wangbinbin
 */
@TableName("AQSC_WORKLOG")
public class Worklog extends BaseModel implements Serializable {

    @TableId(type = IdType.UUID)
    private String id;

    /**
     * 工作日期<br>
     */
    @TableField(value = "workDay")
    private String workDay;

    /**
     * 工作内容<br>
     * <font color="ff0000">必填项</font><br>
     */
    @TableField(value = "content")
    private String content;
    
    /**
     * 项目编号
     */
    @TableField(value = "xmBh")
    private String xmBh;
    
    /**
     * 项目名称
     */
    @TableField(value = "xmName")
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