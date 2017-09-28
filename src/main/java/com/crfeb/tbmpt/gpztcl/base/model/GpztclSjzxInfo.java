package com.crfeb.tbmpt.gpztcl.base.model;

import java.io.Serializable;
import com.baomidou.mybatisplus.annotations.IdType;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.crfeb.tbmpt.commons.model.BaseModel;
import com.baomidou.mybatisplus.annotations.TableName;

/**
 * <p>线路中心线信息管理实体信息</p>
 * <p>描述：实体描述信息.</p>
 * <p>系统：管片姿态测量系统</p>
 * <p>模块：基础模块</p>
 * <p>日期：2016-12-20</p>
 * @version 1.0
 * @author wangbinbin
 */
@TableName("gpztcl_sjzxinfo")
public class GpztclSjzxInfo extends BaseModel implements Serializable {

    @TableId(type = IdType.UUID)
    private String id;

    /**
     * 工程（项目）编号<br>
     */
    @TableField(value = "GC_BH")
    private String gcBh;

    /**
     * 区间编号<br>
     */
    @TableField(value = "QL_BH")
    private String qlBh;

    /**
     * 线路编号<br>
     */
    @TableField(value = "XL_BH")
    private String xlBh;

    /**
     * 里程<br>
     */
    @TableField(value = "lc")
    private String lc;

    /**
     * 横向坐标<br>
     */
    @TableField(value = "sjzbX")
    private String sjzbX;

    /**
     * 纵向坐标<br>
     */
    @TableField(value = "sjzbY")
    private String sjzbY;

    /**
     * 高程<br>
     */
    @TableField(value = "sjzbH")
    private String sjzbH;

    /**
     * 导入人员<br>
     */
    @TableField(value = "impman")
    private String impman;

    /**
     * 导入时间<br>
     */
    @TableField(value = "imptime")
    private String imptime;
    
    /**
     * 备注<br>
     */
    @TableField(value = "remark")
    private String remark;
    
    @TableField(value = "sxh")
    private int sxh;

    public String getId() {
      return id;
    }

    public void setId(String id) {
      this.id = id;
    }

    /**
     * 里程
     */
    public String getLc() {
      return lc;
    }

    /**
     * 里程
     */
    public void setLc(String lc) {
      this.lc = lc;
    }

    /**
     * 横向坐标
     */
    public String getSjzbX() {
      return sjzbX;
    }

    /**
     * 横向坐标
     */
    public void setSjzbX(String sjzbX) {
      this.sjzbX = sjzbX;
    }

    /**
     * 纵向坐标
     */
    public String getSjzbY() {
      return sjzbY;
    }

    /**
     * 纵向坐标
     */
    public void setSjzbY(String sjzbY) {
      this.sjzbY = sjzbY;
    }

    /**
     * 高程
     */
    public String getSjzbH() {
      return sjzbH;
    }

    /**
     * 高程
     */
    public void setSjzbH(String sjzbH) {
      this.sjzbH = sjzbH;
    }

    /**
     * 导入人员
     */
    public String getImpman() {
      return impman;
    }

    /**
     * 导入人员
     */
    public void setImpman(String impman) {
      this.impman = impman;
    }

    /**
     * 导入时间
     */
    public String getImptime() {
      return imptime;
    }

    /**
     * 导入时间
     */
    public void setImptime(String imptime) {
      this.imptime = imptime;
    }

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getGcBh() {
		return gcBh;
	}

	public void setGcBh(String gcBh) {
		this.gcBh = gcBh;
	}

	public String getQlBh() {
		return qlBh;
	}

	public void setQlBh(String qlBh) {
		this.qlBh = qlBh;
	}

	public String getXlBh() {
		return xlBh;
	}

	public void setXlBh(String xlBh) {
		this.xlBh = xlBh;
	}

	public int getSxh() {
		return sxh;
	}

	public void setSxh(int sxh) {
		this.sxh = sxh;
	}

    
}