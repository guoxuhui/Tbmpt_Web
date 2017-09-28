package com.crfeb.tbmpt.gczl.base.model.dto;

import java.io.Serializable;

import com.crfeb.tbmpt.commons.model.ProjectModel;

public class GczlYdxjGPZLXJInfoDto extends ProjectModel implements Serializable {

    private static final long serialVersionUID = 1L;

    private String id;
    
    /**
     * 工程名称
     */
    private String gcName;

    /**
     * 区间名称<br>
     * <font color="ff0000">必填项</font><br>
     */
    private String qlName;
    
    /**
     * 线路名称<br>
     * <font color="ff0000">必填项</font><br>
     */
    private String xlName;
    
    /**
     * 环号<br>
     * <font color="ff0000">必填项</font><br>
     */
    private String hh;

    /**
     * 点位<br>
     * <font color="ff0000">必填项</font><br>
     */
    private String dw;

    /**
     * 质量问题分类<br>
     * <font color="ff0000">必填项</font><br>
     */
    private String typeName;

    /**
     * 情况描述<br>
     * <font color="ff0000">必填项</font><br>
     */
    private String qkms;

    /**
     * 现场照片上传路径<br>
     */
    private String xjzp;

    /**
     * 现场照片缩略图存储路径<br>
     */
    private String xjzpslt;

    /**
     * 其他需说明情况<br>
     */
    private String remarks;

    /**
     * 巡检时间<br>
     * <font color="ff0000">必填项</font><br>
     */
    private String xjtime;
    
    /**
     * 巡检起始时间<br>
     */
    private String xjtimeStr;
    
    /**
     * 巡检截止时间<br>
     */
    private String xjtimeEnd;
    
    

    /**
     * 维护时间<br>
     */
    private String whtime;

    /**
     * 巡检人员<br>
     */
    private String xjry;
    /**
     * 巡检人员<br>
     */
    private String xjryName;

    /**
     * 巡检人员所在部门<br>
     */
    private String xjbm;
    /**
     * 巡检人员所在部门<br>
     */
    private String xjbmName;

    /**
     * 上报时间<br>
     */
    private String sbtime;

    /**
     * 上报状态<br>
     */
    private String sbzt;

    /**
     * 上报人员<br>
     */
    private String sbry;
    /**
     * 上报人员名称<br>
     */
    private String sbryName;

    /**
     * 上报部门<br>
     */
    private String sbbm;
    /**
     * 上报部门名称<br>
     */
    private String sbbmName;

    /**
     * 审核时间<br>
     */
    private String shtime;

    /**
     * 审核状态<br>
     */
    private String shzt;

    /**
     * 审核说明<br>
     */
    private String shsm;

    /**
     * 审核人员<br>
     */
    private String shry;
    /**
     * 审核人员名称<br>
     */
    private String shryName;

    /**
     * 审核部门<br>
     */
    private String shbm;
    /**
     * 审核部门名称<br>
     */
    private String shbmName;

    /**
     * 整改反馈时间<br>
     */
    private String zgfktime;

    /**
     * 整改时间<br>
     */
    private String zgtime;

    /**
     * 整改状态<br>
     */
    private String zgzt;

    /**
     * 整改说明<br>
     */
    private String zgjg;

    /**
     * 整改反馈人员<br>
     */
    private String zgfkry;
    /**
     * 整改反馈人员名称<br>
     */
    private String zgfkryName;

    /**
     * 整改反馈部门<br>
     */
    private String zgbm;
    /**
     * 整改反馈部门名称<br>
     */
    private String zgbmName;

    /**
     * 指定整改人员<br>
     */
    private String zgry;
    
    
    
    
    /** gpps情况描述  */
    private String gppsQkMiaoshu;
    /** 管片破损长度   */
    private String gppsLength;
    /** 管片破损宽度    */
    private String gppsWidth;
    
    
    /** 径向    */
    private String gpctJinXiang;
    /** 环向    */
    private String gpctHuanXiang;
    
    /** 螺栓复紧数量    */
    private String lsfjNums;
    
    /** 渗漏水描述情况    */
    private String slsMiaoShu;
    
    /**
     * 操作说明
     * 用于处理“审核”、“整改标识”
     */
    private String optionName;
    
    
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
     * 点位
     */
    public String getDw() {
      return dw;
    }

    /**
     * 点位
     */
    public void setDw(String dw) {
      this.dw = dw;
    }

    /**
     * 质量问题分类
     */
    public String getTypeName() {
      return typeName;
    }

    /**
     * 质量问题分类
     */
    public void setTypeName(String typeName) {
      this.typeName = typeName;
    }

    /**
     * 情况描述
     */
    public String getQkms() {
      return qkms;
    }

    /**
     * 情况描述
     */
    public void setQkms(String qkms) {
      this.qkms = qkms;
    }

    /**
     * 现场照片上传路径
     */
    public String getXjzp() {
      return xjzp;
    }

    /**
     * 现场照片上传路径
     */
    public void setXjzp(String xjzp) {
      this.xjzp = xjzp;
    }

    /**
     * 现场照片缩略图存储路径
     */
    public String getXjzpslt() {
      return xjzpslt;
    }

    /**
     * 现场照片缩略图存储路径
     */
    public void setXjzpslt(String xjzpslt) {
      this.xjzpslt = xjzpslt;
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
    public String getXjtime() {
      return xjtime;
    }

    /**
     * 巡检时间
     */
    public void setXjtime(String xjtime) {
      this.xjtime = xjtime;
    }

    /**
     * 维护时间
     */
    public String getWhtime() {
      return whtime;
    }

    /**
     * 维护时间
     */
    public void setWhtime(String whtime) {
      this.whtime = whtime;
    }

    /**
     * 巡检人员
     */
    public String getXjry() {
      return xjry;
    }

    /**
     * 巡检人员
     */
    public void setXjry(String xjry) {
      this.xjry = xjry;
    }

    /**
     * 巡检人员所在部门
     */
    public String getXjbm() {
      return xjbm;
    }

    /**
     * 巡检人员所在部门
     */
    public void setXjbm(String xjbm) {
      this.xjbm = xjbm;
    }

    /**
     * 上报时间
     */
    public String getSbtime() {
      return sbtime;
    }

    /**
     * 上报时间
     */
    public void setSbtime(String sbtime) {
      this.sbtime = sbtime;
    }

    /**
     * 上报状态
     */
    public String getSbzt() {
      return sbzt;
    }

    /**
     * 上报状态
     */
    public void setSbzt(String sbzt) {
      this.sbzt = sbzt;
    }

    /**
     * 上报人员
     */
    public String getSbry() {
      return sbry;
    }

    /**
     * 上报人员
     */
    public void setSbry(String sbry) {
      this.sbry = sbry;
    }

    /**
     * 上报部门
     */
    public String getSbbm() {
      return sbbm;
    }

    /**
     * 上报部门
     */
    public void setSbbm(String sbbm) {
      this.sbbm = sbbm;
    }

    /**
     * 审核时间
     */
    public String getShtime() {
      return shtime;
    }

    /**
     * 审核时间
     */
    public void setShtime(String shtime) {
      this.shtime = shtime;
    }

    /**
     * 审核状态
     */
    public String getShzt() {
      return shzt;
    }

    /**
     * 审核状态
     */
    public void setShzt(String shzt) {
      this.shzt = shzt;
    }

    /**
     * 审核说明
     */
    public String getShsm() {
      return shsm;
    }

    /**
     * 审核说明
     */
    public void setShsm(String shsm) {
      this.shsm = shsm;
    }

    /**
     * 审核人员
     */
    public String getShry() {
      return shry;
    }

    /**
     * 审核人员
     */
    public void setShry(String shry) {
      this.shry = shry;
    }

    /**
     * 审核部门
     */
    public String getShbm() {
      return shbm;
    }

    /**
     * 审核部门
     */
    public void setShbm(String shbm) {
      this.shbm = shbm;
    }

    /**
     * 整改反馈时间
     */
    public String getZgfktime() {
      return zgfktime;
    }

    /**
     * 整改反馈时间
     */
    public void setZgfktime(String zgfktime) {
      this.zgfktime = zgfktime;
    }

    /**
     * 整改时间
     */
    public String getZgtime() {
      return zgtime;
    }

    /**
     * 整改时间
     */
    public void setZgtime(String zgtime) {
      this.zgtime = zgtime;
    }

    /**
     * 整改状态
     */
    public String getZgzt() {
      return zgzt;
    }

    /**
     * 整改状态
     */
    public void setZgzt(String zgzt) {
      this.zgzt = zgzt;
    }

    /**
     * 整改说明
     */
    public String getZgjg() {
      return zgjg;
    }

    /**
     * 整改说明
     */
    public void setZgjg(String zgjg) {
      this.zgjg = zgjg;
    }

    /**
     * 整改反馈人员
     */
    public String getZgfkry() {
      return zgfkry;
    }

    /**
     * 整改反馈人员
     */
    public void setZgfkry(String zgfkry) {
      this.zgfkry = zgfkry;
    }

    /**
     * 整改反馈部门
     */
    public String getZgbm() {
      return zgbm;
    }

    /**
     * 整改反馈部门
     */
    public void setZgbm(String zgbm) {
      this.zgbm = zgbm;
    }

    /**
     * 指定整改人员
     */
    public String getZgry() {
      return zgry;
    }

    /**
     * 指定整改人员
     */
    public void setZgry(String zgry) {
      this.zgry = zgry;
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

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getGppsLength() {
		return gppsLength;
	}

	public void setGppsLength(String gppsLength) {
		this.gppsLength = gppsLength;
	}

	public String getGppsWidth() {
		return gppsWidth;
	}

	public void setGppsWidth(String gppsWidth) {
		this.gppsWidth = gppsWidth;
	}

	public String getGppsQkMiaoshu() {
		return gppsQkMiaoshu;
	}

	public void setGppsQkMiaoshu(String gppsQkMiaoshu) {
		this.gppsQkMiaoshu = gppsQkMiaoshu;
	}

	public String getGpctJinXiang() {
		return gpctJinXiang;
	}

	public void setGpctJinXiang(String gpctJinXiang) {
		this.gpctJinXiang = gpctJinXiang;
	}

	public String getGpctHuanXiang() {
		return gpctHuanXiang;
	}

	public void setGpctHuanXiang(String gpctHuanXiang) {
		this.gpctHuanXiang = gpctHuanXiang;
	}

	public String getLsfjNums() {
		return lsfjNums;
	}

	public void setLsfjNums(String lsfjNums) {
		this.lsfjNums = lsfjNums;
	}

	public String getSlsMiaoShu() {
		return slsMiaoShu;
	}

	public void setSlsMiaoShu(String slsMiaoShu) {
		this.slsMiaoShu = slsMiaoShu;
	}

	public String getXjryName() {
		return xjryName;
	}

	public void setXjryName(String xjryName) {
		this.xjryName = xjryName;
	}

	public String getXjbmName() {
		return xjbmName;
	}

	public void setXjbmName(String xjbmName) {
		this.xjbmName = xjbmName;
	}

	public String getSbryName() {
		return sbryName;
	}

	public void setSbryName(String sbryName) {
		this.sbryName = sbryName;
	}

	public String getSbbmName() {
		return sbbmName;
	}

	public void setSbbmName(String sbbmName) {
		this.sbbmName = sbbmName;
	}

	public String getShryName() {
		return shryName;
	}

	public void setShryName(String shryName) {
		this.shryName = shryName;
	}

	public String getShbmName() {
		return shbmName;
	}

	public void setShbmName(String shbmName) {
		this.shbmName = shbmName;
	}

	public String getZgfkryName() {
		return zgfkryName;
	}

	public void setZgfkryName(String zgfkryName) {
		this.zgfkryName = zgfkryName;
	}

	public String getZgbmName() {
		return zgbmName;
	}

	public void setZgbmName(String zgbmName) {
		this.zgbmName = zgbmName;
	}

	public String getOptionName() {
		return optionName;
	}

	public void setOptionName(String optionName) {
		this.optionName = optionName;
	}

	public String getXjtimeStr() {
		return xjtimeStr;
	}

	public void setXjtimeStr(String xjtimeStr) {
		this.xjtimeStr = xjtimeStr;
	}

	public String getXjtimeEnd() {
		return xjtimeEnd;
	}

	public void setXjtimeEnd(String xjtimeEnd) {
		this.xjtimeEnd = xjtimeEnd;
	}

    
}