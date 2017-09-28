package com.crfeb.tbmpt.gczl.base.model.dto;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotations.TableField;
import com.crfeb.tbmpt.commons.model.BaseModel;

public class GczlYdxjSGZLXJInfoDto extends BaseModel implements Serializable {

    private static final long serialVersionUID = 1L;

    private String id;

    /**
     * 工程（项目）编号<br>
     * <font color="ff0000">必填项</font><br>
     */
    private String gcBh;

    /**
     * 区间编号<br>
     * <font color="ff0000">必填项</font><br>
     */
    private String qjBh;

    /**
     * 线路编号<br>
     * <font color="ff0000">必填项</font><br>
     */
    private String xlBh;
    
    /**
     * 单位工程编号<br>
     */
    private String dwgcBh;
    
    /**
     * 单位工程名称<br>
     */
    private String dwgcName;


    /**
     * 施工内容<br>
     * <font color="ff0000">必填项</font><br>
     */
    private String sgNr;

    /**
     * 分部工程<br>
     * <font color="ff0000">必填项</font><br>
     */
    private String fbGcbh;

    /**
     * 施工段<br>
     * <font color="ff0000">必填项</font><br>
     */
    private String sgd;

    /**
     * 具体位置<br>
     * <font color="ff0000">必填项</font><br>
     */
    private String jtWz;

    /**
     * 质量缺陷（存在质量问题、缺陷说明）<br>
     * <font color="ff0000">必填项</font><br>
     */
    private String zlQx;

    /**
     * 现场照片上传路径<br>
     */
    private String xjZp;

    /**
     * 现场照片缩略图存储路径<br>
     */
    private String xjZpslt;

    /**
     * 其他需说明情况<br>
     */
    private String remarks;

    /**
     * 巡检时间<br>
     * <font color="ff0000">必填项</font><br>
     */
    private String xjTime;

    /**
     * 维护时间<br>
     * <font color="ff0000">必填项</font><br>
     */
    private String whTime;

    /**
     * 巡检人员<br>
     */
    private String xjRy;

    /**
     * 巡检人员所在部门<br>
     */
    private String xjBm;

    /**
     * 上报时间<br>
     */
    private String sbTime;

    /**
     * 上报状态<br>
     */
    private String sbZt;

    /**
     * 上报人员<br>
     */
    private String sbRy;

    /**
     * 上报部门<br>
     */
    private String sbBm;

    /**
     * 审核时间<br>
     */
    private String shTime;

    /**
     * 审核状态<br>
     */
    private String shZt;

    /**
     * 审核说明<br>
     */
    private String shSm;

    /**
     * 审核人员<br>
     */
    private String shRy;

    /**
     * 审核部门<br>
     */
    private String shBm;

    /**
     * 整改反馈时间<br>
     */
    private String zgfkTime;

    /**
     * 整改时间<br>
     */
    private String zgTime;

    /**
     * 整改状态<br>
     */
    private String zgZt;

    /**
     * 整改说明<br>
     */
    private String zgJg;

    /**
     * 整改反馈人员<br>
     */
    private String zgFkry;

    /**
     * 整改反馈部门<br>
     */
    private String zgBm;
    /**
     * 整改人员<br>
     */
    private String zgRy;
    /**
     * 工程名称<br>
     */
    private String gcName;
    /**
     * 区间名称<br>
     */
    private String qjName;
    /**
     * 线路名称<br>
     */
    private String xlName;
    /**
     * 分部工程名称<br>
     */
    private String fbGcName;
    /**
     * 施工内容名称<br>
     */
    private String sgNrName;
    /**
     * 具体位置名称<br>
     */
    private String jtWzName;
    /**
     * 质量缺陷名称<br>
     */
    private String zlQxName;
    /**
     * 巡检人员名称<br>
     */
    private String xjRyName;
    /**
     * 巡检人员所在部门名称<br>
     */
    private String xjBmName;
    /**
     * 上报人员所在部门名称<br>
     */
    private String sbRyName;
    /**
     * 上报人员所在部门名称<br>
     */
    private String sbBmName;
    /**
     * 审核人员名称<br>
     */
    private String shRyName;
    /**
     * 审核人员所在部门名称<br>
     */
    private String shBmName;
    /**
     * 整改反馈人员名称<br>
     */
    private String zgFkryName;
    /**
     * 起始巡检时间<br>
     */
    private String startXjTime;
    
    /**
     * 截止巡检时间<br>
     */
    private String endXjTime;
    
    /**
     * 整改人员所在部门名称<br>
     */
    private String zgBmName;
    
    /**整改截止时间*/
    private String zgjzTime;
    
    
    
    public String getZgjzTime() {
		return zgjzTime;
	}

	public void setZgjzTime(String zgjzTime) {
		this.zgjzTime = zgjzTime;
	}

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
    public String getQjBh() {
      return qjBh;
    }

    /**
     * 区间编号
     */
    public void setQjBh(String qjBh) {
      this.qjBh = qjBh;
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
     * 施工内容
     */
    public String getSgNr() {
      return sgNr;
    }

    /**
     * 施工内容
     */
    public void setSgNr(String sgNr) {
      this.sgNr = sgNr;
    }

    /**
     * 分部工程
     */
    public String getFbGcbh() {
      return fbGcbh;
    }

    /**
     * 分部工程
     */
    public void setFbGcbh(String fbGcbh) {
      this.fbGcbh = fbGcbh;
    }

    /**
     * 施工段
     */
    public String getSgd() {
      return sgd;
    }

    /**
     * 施工段
     */
    public void setSgd(String sgd) {
      this.sgd = sgd;
    }

    /**
     * 具体位置
     */
    public String getJtWz() {
      return jtWz;
    }

    /**
     * 具体位置
     */
    public void setJtWz(String jtWz) {
      this.jtWz = jtWz;
    }

    /**
     * 质量缺陷（存在质量问题、缺陷说明）
     */
    public String getZlQx() {
      return zlQx;
    }

    /**
     * 质量缺陷（存在质量问题、缺陷说明）
     */
    public void setZlQx(String zlQx) {
      this.zlQx = zlQx;
    }

    /**
     * 现场照片上传路径
     */
    public String getXjZp() {
      return xjZp;
    }

    /**
     * 现场照片上传路径
     */
    public void setXjZp(String xjZp) {
      this.xjZp = xjZp;
    }

    /**
     * 现场照片缩略图存储路径
     */
    public String getXjZpslt() {
      return xjZpslt;
    }

    /**
     * 现场照片缩略图存储路径
     */
    public void setXjZpslt(String xjZpslt) {
      this.xjZpslt = xjZpslt;
    }

    /**
     * 其他需说明情况
     */
    public String getRemarks() {
      return remarks;
    }

    /**
     * 其他需说明情况
     */
    public void setRemarks(String remarks) {
      this.remarks = remarks;
    }

    /**
     * 巡检时间
     */
    public String getXjTime() {
      return xjTime;
    }

    /**
     * 巡检时间
     */
    public void setXjTime(String xjTime) {
      this.xjTime = xjTime;
    }

    /**
     * 维护时间
     */
    public String getWhTime() {
      return whTime;
    }

    /**
     * 维护时间
     */
    public void setWhTime(String whTime) {
      this.whTime = whTime;
    }

    /**
     * 巡检人员
     */
    public String getXjRy() {
      return xjRy;
    }

    /**
     * 巡检人员
     */
    public void setXjRy(String xjRy) {
      this.xjRy = xjRy;
    }

    /**
     * 巡检人员所在部门
     */
    public String getXjBm() {
      return xjBm;
    }

    /**
     * 巡检人员所在部门
     */
    public void setXjBm(String xjBm) {
      this.xjBm = xjBm;
    }

    /**
     * 上报时间
     */
    public String getSbTime() {
      return sbTime;
    }

    /**
     * 上报时间
     */
    public void setSbTime(String sbTime) {
      this.sbTime = sbTime;
    }

    /**
     * 上报状态
     */
    public String getSbZt() {
      return sbZt;
    }

    /**
     * 上报状态
     */
    public void setSbZt(String sbZt) {
      this.sbZt = sbZt;
    }

    /**
     * 上报人员
     */
    public String getSbRy() {
      return sbRy;
    }

    /**
     * 上报人员
     */
    public void setSbRy(String sbRy) {
      this.sbRy = sbRy;
    }

    /**
     * 上报部门
     */
    public String getSbBm() {
      return sbBm;
    }

    /**
     * 上报部门
     */
    public void setSbBm(String sbBm) {
      this.sbBm = sbBm;
    }

    /**
     * 审核时间
     */
    public String getShTime() {
      return shTime;
    }

    /**
     * 审核时间
     */
    public void setShTime(String shTime) {
      this.shTime = shTime;
    }

    /**
     * 审核状态
     */
    public String getShZt() {
      return shZt;
    }

    /**
     * 审核状态
     */
    public void setShZt(String shZt) {
      this.shZt = shZt;
    }

    /**
     * 审核说明
     */
    public String getShSm() {
      return shSm;
    }

    /**
     * 审核说明
     */
    public void setShSm(String shSm) {
      this.shSm = shSm;
    }

    /**
     * 审核人员
     */
    public String getShRy() {
      return shRy;
    }

    /**
     * 审核人员
     */
    public void setShRy(String shRy) {
      this.shRy = shRy;
    }

    /**
     * 审核部门
     */
    public String getShBm() {
      return shBm;
    }

    /**
     * 审核部门
     */
    public void setShBm(String shBm) {
      this.shBm = shBm;
    }

    /**
     * 整改反馈时间
     */
    public String getZgfkTime() {
      return zgfkTime;
    }

    /**
     * 整改反馈时间
     */
    public void setZgfkTime(String zgfkTime) {
      this.zgfkTime = zgfkTime;
    }

    /**
     * 整改时间
     */
    public String getZgTime() {
      return zgTime;
    }

    /**
     * 整改时间
     */
    public void setZgTime(String zgTime) {
      this.zgTime = zgTime;
    }

    /**
     * 整改状态
     */
    public String getZgZt() {
      return zgZt;
    }

    /**
     * 整改状态
     */
    public void setZgZt(String zgZt) {
      this.zgZt = zgZt;
    }

    /**
     * 整改说明
     */
    public String getZgJg() {
      return zgJg;
    }

    /**
     * 整改说明
     */
    public void setZgJg(String zgJg) {
      this.zgJg = zgJg;
    }

    /**
     * 整改反馈人员
     */
    public String getZgFkry() {
      return zgFkry;
    }

    /**
     * 整改反馈人员
     */
    public void setZgFkry(String zgFkry) {
      this.zgFkry = zgFkry;
    }

    /**
     * 整改反馈部门
     */
    public String getZgBm() {
      return zgBm;
    }

    /**
     * 整改反馈部门
     */
    public void setZgBm(String zgBm) {
      this.zgBm = zgBm;
    }

	public String getGcName() {
		return gcName;
	}

	public void setGcName(String gcName) {
		this.gcName = gcName;
	}

	public String getQjName() {
		return qjName;
	}

	public void setQjName(String qjName) {
		this.qjName = qjName;
	}

	public String getXlName() {
		return xlName;
	}

	public void setXlName(String xlName) {
		this.xlName = xlName;
	}

	public String getFbGcName() {
		return fbGcName;
	}

	public void setFbGcName(String fbGcName) {
		this.fbGcName = fbGcName;
	}

	public String getSgNrName() {
		return sgNrName;
	}

	public void setSgNrName(String sgNrName) {
		this.sgNrName = sgNrName;
	}

	public String getJtWzName() {
		return jtWzName;
	}

	public void setJtWzName(String jtWzName) {
		this.jtWzName = jtWzName;
	}

	public String getZlQxName() {
		return zlQxName;
	}

	public void setZlQxName(String zlQxName) {
		this.zlQxName = zlQxName;
	}

	public String getXjRyName() {
		return xjRyName;
	}

	public void setXjRyName(String xjRyName) {
		this.xjRyName = xjRyName;
	}

	public String getXjBmName() {
		return xjBmName;
	}

	public void setXjBmName(String xjBmName) {
		this.xjBmName = xjBmName;
	}

	public String getSbRyName() {
		return sbRyName;
	}

	public void setSbRyName(String sbRyName) {
		this.sbRyName = sbRyName;
	}

	public String getSbBmName() {
		return sbBmName;
	}

	public void setSbBmName(String sbBmName) {
		this.sbBmName = sbBmName;
	}

	public String getShRyName() {
		return shRyName;
	}

	public void setShRyName(String shRyName) {
		this.shRyName = shRyName;
	}

	public String getShBmName() {
		return shBmName;
	}

	public void setShBmName(String shBmName) {
		this.shBmName = shBmName;
	}

	public String getZgFkryName() {
		return zgFkryName;
	}

	public void setZgFkryName(String zgFkryName) {
		this.zgFkryName = zgFkryName;
	}

	public String getZgBmName() {
		return zgBmName;
	}

	public void setZgBmName(String zgBmName) {
		this.zgBmName = zgBmName;
	}

	public String getZgRy() {
		return zgRy;
	}

	public void setZgRy(String zgRy) {
		this.zgRy = zgRy;
	}

	public String getStartXjTime() {
		return startXjTime;
	}

	public void setStartXjTime(String startXjTime) {
		this.startXjTime = startXjTime;
	}

	public String getEndXjTime() {
		return endXjTime;
	}

	public void setEndXjTime(String endXjTime) {
		this.endXjTime = endXjTime;
	}

	public String getDwgcBh() {
		return dwgcBh;
	}

	public void setDwgcBh(String dwgcBh) {
		this.dwgcBh = dwgcBh;
	}

	public String getDwgcName() {
		return dwgcName;
	}

	public void setDwgcName(String dwgcName) {
		this.dwgcName = dwgcName;
	}
	

}