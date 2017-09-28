package com.crfeb.tbmpt.gpztcl.base.model.dto;

import com.crfeb.tbmpt.commons.model.BaseModel;
import java.io.Serializable;

public class GpztclSczxpcDto extends BaseModel implements Serializable {

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
     * <font color="ff0000">必填项</font><br>
     */
    private String xlBh;

    /**
     * 起始环号<br>
     */
    private String starNo;

    /**
     * 截止环号<br>
     */
    private String endNo;

    /**
     * 测量类型<br>
     * <font color="ff0000">必填项</font><br>
     */
    private String clType;

    /**
     * 测量时间<br>
     * <font color="ff0000">必填项</font><br>
     */
    private String clTime;

    /**
     * 导入人员<br>
     */
    private String impMan;

    /**
     * 导入时间<br>
     */
    private String impTime;

    /**
     * 工程名称<br>
     */
    private String gcName;
    
    /**
     * 区间名称<br>
     */
    private String qlName;

    /**
     * 线路名称<br>
     */
    private String xlName;

    /**
     * 上报状态<br>
     */
    private String sendState;
    
    /**
     * 导入人员名称
     */
    private String impManName;
    
    /**
     * 明细数据集合
     */
    private String dataList; 

    public String getId() {
      return id;
    }

    public void setId(String id) {
      this.id = id;
    }

    /**
     * 工程（项目）编号
     */
    public String getGcBh() {
      return gcBh;
    }

    /**
     * 工程（项目）编号
     */
    public void setGcBh(String gcBh) {
      this.gcBh = gcBh;
    }

    /**
     * 区间编号
     */
    public String getQlBh() {
      return qlBh;
    }

    /**
     * 区间编号
     */
    public void setQlBh(String qlBh) {
      this.qlBh = qlBh;
    }

    /**
     * 线路编号
     */
    public String getXlBh() {
      return xlBh;
    }

    /**
     * 线路编号
     */
    public void setXlBh(String xlBh) {
      this.xlBh = xlBh;
    }

    /**
     * 起始环号
     */
    public String getStarNo() {
      return starNo;
    }

    /**
     * 起始环号
     */
    public void setStarNo(String starNo) {
      this.starNo = starNo;
    }

    /**
     * 截止环号
     */
    public String getEndNo() {
      return endNo;
    }

    /**
     * 截止环号
     */
    public void setEndNo(String endNo) {
      this.endNo = endNo;
    }

    /**
     * 测量类型
     */
    public String getClType() {
      return clType;
    }

    /**
     * 测量类型
     */
    public void setClType(String clType) {
      this.clType = clType;
    }

    /**
     * 测量时间
     */
    public String getClTime() {
      return clTime;
    }

    /**
     * 测量时间
     */
    public void setClTime(String clTime) {
      this.clTime = clTime;
    }

    /**
     * 导入人员
     */
    public String getImpMan() {
      return impMan;
    }

    /**
     * 导入人员
     */
    public void setImpMan(String impMan) {
      this.impMan = impMan;
    }

    /**
     * 导入时间
     */
    public String getImpTime() {
      return impTime;
    }

    /**
     * 导入时间
     */
    public void setImpTime(String impTime) {
      this.impTime = impTime;
    }

    /**
     * 上报状态
     */
    public String getSendState() {
      return sendState;
    }

    /**
     * 上报状态
     */
    public void setSendState(String sendState) {
      this.sendState = sendState;
    }

	public String getGcName() {
		return gcName;
	}

	public void setGcName(String gcName) {
		this.gcName = gcName;
	}

	public String getQlName() {
		return qlName;
	}

	public void setQlName(String qlName) {
		this.qlName = qlName;
	}

	public String getXlName() {
		return xlName;
	}

	public void setXlName(String xlName) {
		this.xlName = xlName;
	}

	public String getImpManName() {
		return impManName;
	}

	public void setImpManName(String impManName) {
		this.impManName = impManName;
	}

	public String getDataList() {
		return dataList;
	}

	public void setDataList(String dataList) {
		this.dataList = dataList;
	}
    
    

}