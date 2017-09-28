package com.crfeb.tbmpt.dgjjdw.real.model;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotations.IdType;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;

/**
 * <p>盾构掘进点位管理：盾构机机器数据实时信息 实体类</p>
 * <p>模块：盾构掘进点位管理</p>
 * <p>日期：2017-03-17</p>
 * @version 1.0
 * @author wl_wpg
 */
@TableName("PRO_PLC_REAL")
public class DgjjdwPlcReal implements Serializable {

	@TableField(exist = false)
	private static final long serialVersionUID = 1L;

	/** ID */
	@TableId(type = IdType.UUID)
	private String id;

	/** 盾构机ID */
	private String tbmid;

	/** 盾构机CODE */
	private String tbmcode;

	/** 工业库定位名称 */
	private String tagname;

	/** 工业库点位数据类型 */
	private String tagtype;

	/** 工业库点位值 */
	private String tagvalue;

	/** 描述 */
	private String ms;

	/** 备注 */
	private String bz;

	/** 时间 */
	private String tagtime;


	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTbmid() {
		return this.tbmid;
	}

	public void setTbmid(String tbmid) {
		this.tbmid = tbmid;
	}

	public String getTbmcode() {
		return this.tbmcode;
	}

	public void setTbmcode(String tbmcode) {
		this.tbmcode = tbmcode;
	}

	public String getTagname() {
		return this.tagname;
	}

	public void setTagname(String tagname) {
		this.tagname = tagname;
	}

	public String getTagtype() {
		return this.tagtype;
	}

	public void setTagtype(String tagtype) {
		this.tagtype = tagtype;
	}

	public String getTagvalue() {
		return this.tagvalue;
	}

	public void setTagvalue(String tagvalue) {
		this.tagvalue = tagvalue;
	}

	public String getMs() {
		return this.ms;
	}

	public void setMs(String ms) {
		this.ms = ms;
	}

	public String getBz() {
		return this.bz;
	}

	public void setBz(String bz) {
		this.bz = bz;
	}

	public String getTagtime() {
		return this.tagtime;
	}

	public void setTagtime(String tagtime) {
		this.tagtime = tagtime;
	}

}
