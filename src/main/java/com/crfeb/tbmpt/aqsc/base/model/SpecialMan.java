package com.crfeb.tbmpt.aqsc.base.model;

import java.io.Serializable;
import com.baomidou.mybatisplus.annotations.IdType;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.crfeb.tbmpt.commons.model.BaseModel;
import com.baomidou.mybatisplus.annotations.TableName;

/**
 * <p>特种人员管理实体信息</p>
 * <p>描述：实体描述信息.</p>
 * <p>系统：安全生产管理</p>
 * <p>模块：基础信息</p>
 * <p>日期：2017-02-20</p>
 * @version 1.0
 * @author wangbinbin
 */
@TableName("AQSC_SPECIALMAN")
public class SpecialMan extends BaseModel implements Serializable {

    @TableId(type = IdType.UUID)
    private String id;

    /**
     * 姓名<br>
     * <font color="ff0000">必填项</font><br>
     */
    @TableField(value = "name")
    private String name;

    /**
     * 性别<br>
     */
    @TableField(value = "sex")
    private String sex;

    /**
     * 工种<br>
     * <font color="ff0000">必填项</font><br>
     */
    @TableField(value = "workType")
    private String workType;

    /**
     * 人员类别<br>
     */
    @TableField(value = "renyuanType")
    private String renyuanType;

    /**
     * 发证机关<br>
     */
    @TableField(value = "fazhengjiguan")
    private String fazhengjiguan;

    /**
     * 证件号码<br>
     */
    @TableField(value = "zhengjianhaoma")
    private String zhengjianhaoma;

    /**
     * 取证日期<br>
     */
    @TableField(value = "quzhengriqi")
    private String quzhengriqi;

    /**
     * 复审日期<br>
     */
    @TableField(value = "fushengriqi")
    private String fushengriqi;

    /**
     * 有效日期<br>
     */
    @TableField(value = "youxiaoqi")
    private String youxiaoqi;

    /**
     * 进场日期<br>
     */
    @TableField(value = "jinchangriqi")
    private String jinchangriqi;

    /**
     * 离场日期<br>
     */
    @TableField(value = "lichangriqi")
    private String lichangriqi;

    /**
     * 备注<br>
     */
    @TableField(value = "remark")
    private String remark;
    
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
    
    /**
     * 身份证号码<br>
     */
    @TableField(value = "cardNo")
    private String cardNo;


    public String getId() {
      return id;
    }

    public void setId(String id) {
      this.id = id;
    }

    /**
     * 姓名
     */
    public String getName() {
      return name;
    }

    /**
     * 姓名
     */
    public void setName(String name) {
      this.name = name;
    }

    /**
     * 性别
     */
    public String getSex() {
      return sex;
    }

    /**
     * 性别
     */
    public void setSex(String sex) {
      this.sex = sex;
    }

    /**
     * 工种
     */
    public String getWorkType() {
      return workType;
    }

    /**
     * 工种
     */
    public void setWorkType(String workType) {
      this.workType = workType;
    }

    /**
     * 人员类别
     */
    public String getRenyuanType() {
      return renyuanType;
    }

    /**
     * 人员类别
     */
    public void setRenyuanType(String renyuanType) {
      this.renyuanType = renyuanType;
    }

    /**
     * 发证机关
     */
    public String getFazhengjiguan() {
      return fazhengjiguan;
    }

    /**
     * 发证机关
     */
    public void setFazhengjiguan(String fazhengjiguan) {
      this.fazhengjiguan = fazhengjiguan;
    }

    /**
     * 证件号码
     */
    public String getZhengjianhaoma() {
      return zhengjianhaoma;
    }

    /**
     * 证件号码
     */
    public void setZhengjianhaoma(String zhengjianhaoma) {
      this.zhengjianhaoma = zhengjianhaoma;
    }

    /**
     * 取证日期
     */
    public String getQuzhengriqi() {
      return quzhengriqi;
    }

    /**
     * 取证日期
     */
    public void setQuzhengriqi(String quzhengriqi) {
      this.quzhengriqi = quzhengriqi;
    }

    /**
     * 复审日期
     */
    public String getFushengriqi() {
      return fushengriqi;
    }

    /**
     * 复审日期
     */
    public void setFushengriqi(String fushengriqi) {
      this.fushengriqi = fushengriqi;
    }

    /**
     * 有效日期
     */
    public String getYouxiaoqi() {
      return youxiaoqi;
    }

    /**
     * 有效日期
     */
    public void setYouxiaoqi(String youxiaoqi) {
      this.youxiaoqi = youxiaoqi;
    }

    /**
     * 进场日期
     */
    public String getJinchangriqi() {
      return jinchangriqi;
    }

    /**
     * 进场日期
     */
    public void setJinchangriqi(String jinchangriqi) {
      this.jinchangriqi = jinchangriqi;
    }

    /**
     * 离场日期
     */
    public String getLichangriqi() {
      return lichangriqi;
    }

    /**
     * 离场日期
     */
    public void setLichangriqi(String lichangriqi) {
      this.lichangriqi = lichangriqi;
    }

    /**
     * 备注
     */
    public String getRemark() {
      return remark;
    }

    /**
     * 备注
     */
    public void setRemark(String remark) {
      this.remark = remark;
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

    
	public String getCardNo() {
		return cardNo;
	}

	public void setCardNo(String cardNo) {
		this.cardNo = cardNo;
	}
    
}