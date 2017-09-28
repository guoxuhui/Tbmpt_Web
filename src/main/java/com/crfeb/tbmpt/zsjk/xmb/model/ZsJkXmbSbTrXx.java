package com.crfeb.tbmpt.zsjk.xmb.model;

import java.io.Serializable;
import com.baomidou.mybatisplus.annotations.IdType;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.crfeb.tbmpt.commons.model.BaseModel;
import com.baomidou.mybatisplus.annotations.TableName;

/**
 * <p>项目部角度 盾构机、电瓶车、龙门吊等主要设备的运行与投入情况实体信息</p>
 * <p>描述：实体描述信息.</p>
 * <p>系统：展示界面接口</p>
 * <p>模块：项目部角度模块</p>
 * <p>日期：2017-01-09</p>
 * @version 1.0
 * @author YangYJ
 */
@TableName("zsjk_xmb_SbTrxx")
public class ZsJkXmbSbTrXx  implements Serializable {

    @TableId(type = IdType.UUID)
    private String id;

    /**
     * 项目id<br>
     */
    @TableField(value = "xmId")
    private String xmId;
    
    /**
     * 项目名称<br>
     */
    @TableField(value = "xmmc")
    private String xmmc;

    /**
     * 设备名称<br>
     */
    @TableField(value = "sbmc")
    private String sbmc;

    /**
     * 所在线路名称<br>
     */
    @TableField(value = "xlmc")
    private String xlmc;

    /**
     * 厂商<br>
     */
    @TableField(value = "cs")
    private String cs;

    /**
     * 型号<br>
     */
    @TableField(value = "xh")
    private String xh;

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
     * 设备名称
     */
    public String getSbmc() {
      return sbmc;
    }

    /**
     * 设备名称
     */
    public void setSbmc(String sbmc) {
      this.sbmc = sbmc;
    }

    /**
     * 所在线路名称
     */
    public String getXlmc() {
      return xlmc;
    }

    /**
     * 所在线路名称
     */
    public void setXlmc(String xlmc) {
      this.xlmc = xlmc;
    }

    /**
     * 厂商
     */
    public String getCs() {
      return cs;
    }

    /**
     * 厂商
     */
    public void setCs(String cs) {
      this.cs = cs;
    }

    /**
     * 型号
     */
    public String getXh() {
      return xh;
    }

    /**
     * 型号
     */
    public void setXh(String xh) {
      this.xh = xh;
    }

	public String getXmmc() {
		return xmmc;
	}

	public void setXmmc(String xmmc) {
		this.xmmc = xmmc;
	}
    
    

}