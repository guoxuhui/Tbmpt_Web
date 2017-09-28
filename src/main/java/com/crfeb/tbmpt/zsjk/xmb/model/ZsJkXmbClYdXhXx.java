package com.crfeb.tbmpt.zsjk.xmb.model;

import java.io.Serializable;
import com.baomidou.mybatisplus.annotations.IdType;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.crfeb.tbmpt.commons.model.BaseModel;
import com.baomidou.mybatisplus.annotations.TableName;

/**
 * <p>项目部角度 项目的材料月度总消耗信息实体信息</p>
 * <p>描述：实体描述信息.</p>
 * <p>系统：展示界面接口</p>
 * <p>模块：项目部角度模块</p>
 * <p>日期：2017-01-09</p>
 * @version 1.0
 * @author YangYJ
 */
@TableName("zsjk_xmb_ClYdXhxx")
public class ZsJkXmbClYdXhXx  implements Serializable {

    @TableId(type = IdType.UUID)
    private String id;

    /**
     * 项目id<br>
     */
    @TableField(value = "xmId")
    private String xmId;

    /**
     * 月份<br>
     */
    @TableField(value = "yf")
    private String yf;

    /**
     * 材料分类<br>
     */
    @TableField(value = "clfl")
    private String clfl;

    /**
     * 材料消耗计划投入值<br>
     */
    @TableField(value = "cljhtr")
    private String cljhtr;

    /**
     * 材料消耗实际投入值<br>
     */
    @TableField(value = "clsjtr")
    private String clsjtr;
    
    /**
     * 单位<br>
     */
    @TableField(value = "danWei")
    private String danWei;

    public String getId() {
      return id;
    }

    public void setId(String id) {
      this.id = id;
    }

    /**
     * 项目id
     */
    public String getXmId() {
      return xmId;
    }

    /**
     * 项目id
     */
    public void setXmId(String xmId) {
      this.xmId = xmId;
    }

    /**
     * 月份
     */
    public String getYf() {
      return yf;
    }

    /**
     * 月份
     */
    public void setYf(String yf) {
      this.yf = yf;
    }

    /**
     * 材料分类
     */
    public String getClfl() {
      return clfl;
    }

    /**
     * 材料分类
     */
    public void setClfl(String clfl) {
      this.clfl = clfl;
    }

    /**
     * 材料消耗计划投入值
     */
    public String getCljhtr() {
      return cljhtr;
    }

    /**
     * 材料消耗计划投入值
     */
    public void setCljhtr(String cljhtr) {
      this.cljhtr = cljhtr;
    }

    /**
     * 材料消耗实际投入值
     */
    public String getClsjtr() {
      return clsjtr;
    }

    /**
     * 材料消耗实际投入值
     */
    public void setClsjtr(String clsjtr) {
      this.clsjtr = clsjtr;
    }

	public String getDanWei() {
		return danWei;
	}

	public void setDanWei(String danWei) {
		this.danWei = danWei;
	}
    
    

}