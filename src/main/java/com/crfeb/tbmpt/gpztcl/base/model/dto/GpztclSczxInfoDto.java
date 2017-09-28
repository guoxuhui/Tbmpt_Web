package com.crfeb.tbmpt.gpztcl.base.model.dto;

import java.io.Serializable;
import java.util.List;

import com.crfeb.tbmpt.commons.model.BaseModel;

public class GpztclSczxInfoDto extends BaseModel implements Serializable {

    private static final long serialVersionUID = 1L;

    private String id;

    /**
     * 主表主键<br>
     */
    private String fid;

    /**
     * 环号<br>
     */
    private String hh;

    /**
     * 实测横向坐标<br>
     */
    private String sczbX;

    /**
     * 实测纵向坐标<br>
     */
    private String sczbY;

    /**
     * 实测高程<br>
     */
    private String sczbH;

    /**
     * 计算横向坐标<br>
     */
    private String jszbX;

    /**
     * 计算纵向坐标<br>
     */
    private String jszbY;

    /**
     * 计算高程<br>
     */
    private String jszbH;

    /**
     * 导向测量横向偏移<br>
     */
    private String dxpyX;

    /**
     * 导向测量纵向偏移<br>
     */
    private String dxpyY;
    
    /**
     * 备注<br>
     */
    private String remarks;
    /**
     * 实测偏移量横向<br>
     */
    private String scpylX;
    /**
     * 实测偏移量竖向<br>
     */
    private String scpylY;
    /**
     * 较差横向<br>
     */
    private String jcX;
    /**
     * 较差竖向<br>
     */
    private String jcY;
    
    private String dataList; 
    /**
     * 里程<br>
     */
    private String lc;
    

    public String getId() {
      return id;
    }

    public void setId(String id) {
      this.id = id;
    }

    /**
     * 主表主键
     */
    public String getFid() {
      return fid;
    }

    /**
     * 主表主键
     */
    public void setFid(String fid) {
      this.fid = fid;
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
     * 实测横向坐标
     */
    public String getSczbX() {
      return sczbX;
    }

    /**
     * 实测横向坐标
     */
    public void setSczbX(String sczbX) {
      this.sczbX = sczbX;
    }

    /**
     * 实测纵向坐标
     */
    public String getSczbY() {
      return sczbY;
    }

    /**
     * 实测纵向坐标
     */
    public void setSczbY(String sczbY) {
      this.sczbY = sczbY;
    }

    /**
     * 实测高程
     */
    public String getSczbH() {
      return sczbH;
    }

    /**
     * 实测高程
     */
    public void setSczbH(String sczbH) {
      this.sczbH = sczbH;
    }

    /**
     * 计算横向坐标
     */
    public String getJszbX() {
      return jszbX;
    }

    /**
     * 计算横向坐标
     */
    public void setJszbX(String jszbX) {
      this.jszbX = jszbX;
    }

    /**
     * 计算纵向坐标
     */
    public String getJszbY() {
      return jszbY;
    }

    /**
     * 计算纵向坐标
     */
    public void setJszbY(String jszbY) {
      this.jszbY = jszbY;
    }

    /**
     * 计算高程
     */
    public String getJszbH() {
      return jszbH;
    }

    /**
     * 计算高程
     */
    public void setJszbH(String jszbH) {
      this.jszbH = jszbH;
    }

    /**
     * 导向测量横向偏移
     */
    public String getDxpyX() {
      return dxpyX;
    }

    /**
     * 导向测量横向偏移
     */
    public void setDxpyX(String dxpyX) {
      this.dxpyX = dxpyX;
    }

    /**
     * 导向测量纵向偏移
     */
    public String getDxpyY() {
      return dxpyY;
    }

    /**
     * 导向测量纵向偏移
     */
    public void setDxpyY(String dxpyY) {
      this.dxpyY = dxpyY;
    }

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public String getScpylX() {
		return scpylX;
	}

	public void setScpylX(String scpylX) {
		this.scpylX = scpylX;
	}

	public String getScpylY() {
		return scpylY;
	}

	public void setScpylY(String scpylY) {
		this.scpylY = scpylY;
	}

	public String getJcX() {
		return jcX;
	}

	public void setJcX(String jcX) {
		this.jcX = jcX;
	}

	public String getJcY() {
		return jcY;
	}

	public void setJcY(String jcY) {
		this.jcY = jcY;
	}

	public String getDataList() {
		return dataList;
	}

	public void setDataList(String dataList) {
		this.dataList = dataList;
	}

	public String getLc() {
		return lc;
	}

	public void setLc(String lc) {
		this.lc = lc;
	}


	
	

	
    
    

}