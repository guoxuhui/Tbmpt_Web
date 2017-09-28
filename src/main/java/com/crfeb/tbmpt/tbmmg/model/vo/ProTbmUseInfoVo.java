package com.crfeb.tbmpt.tbmmg.model.vo;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotations.IdType;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;

/**
 *
 * 盾构机信息表
 * @author lzh
 * @date 2017年1月7日16:35:14
 */
@TableName("PRO_TBM_USE_INFO")
public class ProTbmUseInfoVo implements Serializable {

	@TableField(exist = false)
	private static final long serialVersionUID = 1L;

	/** 主键生成规则:长年号:2016+四位自增号 0001 */
	@TableId(type = IdType.UUID)
	private String id;
	
	/**工程编号*/
	@TableField(value = "GCBH")
	private String gcbh;
	
	/**区间编号*/
	@TableField(value = "QJBH")
	private String qjbh;
	
	/**线路编号*/
	@TableField(value = "XLBH")
	private String xlbh;
	
	/**盾构机编号*/
	@TableField(value = "TBMBH")
	private String tbmbh;
	
	/**盾构机使用时间*/
	@TableField(value = "TBM_USE_TIME")
	private String tbmUseTime;
	
	/**施工日期*/
	@TableField(value = "SG_DATE")
	private String sgDate;
	
	/**盾构日掘进ID*/
	@TableField(value = "DGJJID")
	private String dgjjid;
	
	/**盾构机名称*/
	@TableField (value = "TBM_NAME")
	private String tbmName;
	
	private String proName;
	
	private String qjName;
	
	private String xlName;

	
	
	public String getProName() {
		return proName;
	}

	public void setProName(String proName) {
		this.proName = proName;
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

	public String getTbmName() {
		return tbmName;
	}

	public void setTbmName(String tbmName) {
		this.tbmName = tbmName;
	}

	public String getDgjjid() {
		return dgjjid;
	}

	public void setDgjjid(String dgjjid) {
		this.dgjjid = dgjjid;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getGcbh() {
		return gcbh;
	}

	public void setGcbh(String gcbh) {
		this.gcbh = gcbh;
	}

	public String getQjbh() {
		return qjbh;
	}

	public void setQjbh(String qjbh) {
		this.qjbh = qjbh;
	}

	public String getXlbh() {
		return xlbh;
	}

	public void setXlbh(String xlbh) {
		this.xlbh = xlbh;
	}

	public String getTbmbh() {
		return tbmbh;
	}

	public void setTbmbh(String tbmbh) {
		this.tbmbh = tbmbh;
	}

	public String getTbmUseTime() {
		return tbmUseTime;
	}

	public void setTbmUseTime(String tbmUseTime) {
		this.tbmUseTime = tbmUseTime;
	}

	public String getSgDate() {
		return sgDate;
	}

	public void setSgDate(String sgDate) {
		this.sgDate = sgDate;
	}

	

	public static long getSerialversionuid() {
		return serialVersionUID;
	}



	
}
