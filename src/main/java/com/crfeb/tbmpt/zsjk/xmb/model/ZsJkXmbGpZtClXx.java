package com.crfeb.tbmpt.zsjk.xmb.model;

import java.io.Serializable;
import com.baomidou.mybatisplus.annotations.IdType;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.crfeb.tbmpt.commons.model.BaseModel;
import com.baomidou.mybatisplus.annotations.TableName;

/**
 * <p>项目部角度  管片姿态测量信息实体信息</p>
 * <p>描述：实体描述信息.</p>
 * <p>系统：展示界面接口</p>
 * <p>模块：项目部角度模块</p>
 * <p>日期：2017-01-09</p>
 * @version 1.0
 * @author YangYJ
 */
@TableName("zsjk_xmb_GpZtClxx")
public class ZsJkXmbGpZtClXx  implements Serializable {

    @TableId(type = IdType.UUID)
    private String id;

    /**
     * 线路ID<br>
     */
    @TableField(value = "xlId")
    private String xlId;
    
    /**
     * 线路名称<br>
     */
    @TableField(value = "xlmc")
    private String xlmc;

    /**
     * 环号<br>
     */
    @TableField(value = "hh")
    private String hh;

    /**
     * 横向实测偏移量<br>
     */
    @TableField(value = "hxpy")
    private String hxpy;

    /**
     * 竖向实测偏移量<br>
     */
    @TableField(value = "sxpy")
    private String sxpy;

    public String getId() {
      return id;
    }

    public void setId(String id) {
      this.id = id;
    }

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
     * 环号
     */
    public String getHh() {
      return hh;
    }

    /**
     * 环号
     */
    public void setHh(String hh) {
      this.hh = hh;
    }

    /**
     * 横向实测偏移量
     */
    public String getHxpy() {
      return hxpy;
    }

    /**
     * 横向实测偏移量
     */
    public void setHxpy(String hxpy) {
      this.hxpy = hxpy;
    }

    /**
     * 竖向实测偏移量
     */
    public String getSxpy() {
      return sxpy;
    }

    /**
     * 竖向实测偏移量
     */
    public void setSxpy(String sxpy) {
      this.sxpy = sxpy;
    }

	public String getXlmc() {
		return xlmc;
	}

	public void setXlmc(String xlmc) {
		this.xlmc = xlmc;
	}
    

}