package com.crfeb.tbmpt.gpztcl.base.model.dto;

import com.baomidou.mybatisplus.annotations.TableField;
import com.crfeb.tbmpt.commons.model.BaseModel;
import java.io.Serializable;

public class GpztclSjzxInfoDto extends BaseModel implements Serializable {

    private static final long serialVersionUID = 1L;

    private String id;

    /**
     * 工程（项目）编号<br>
     */
    private String gcBh;

    /**
     * 区间编号<br>
     */
    private String qlBh;

    /**
     * 线路编号<br>
     */
    private String xlBh;

    /**
     * 里程<br>
     */
    private String lc;

    /**
     * 横向坐标<br>
     */
    private String sjzbX;

    /**
     * 纵向坐标<br>
     */
    private String sjzbY;

    /**
     * 高程<br>
     */
    private String sjzbH;

    /**
     * 导入人员<br>
     */
    private String impman;

    /**
     * 导入时间<br>
     */
    private String imptime;
    
    
    
    /**
     * 备注<br>
     */
    private String remark;
    
    private int sxh;
    
    /**
     * 临时字段
     */
    private String clumOne;

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

	public String getClumOne() {
		return clumOne;
	}

	public void setClumOne(String clumOne) {
		this.clumOne = clumOne;
	}

	public int getSxh() {
		return sxh;
	}

	public void setSxh(int sxh) {
		this.sxh = sxh;
	}

    
    
}