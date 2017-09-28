package com.crfeb.tbmpt.dgjjdw.bzdw.model.dto;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotations.IdType;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
/**
 * <p>盾构掘进点位管理：标准点位字典信息管理 工具类</p>
 * <p>模块：盾构掘进点位管理</p>
 * <p>日期：2017-03-17</p>
 * @version 1.0
 * @author wl_wpg
 */
public class DgjjdwPlcBzdwDto implements Serializable {

	@TableField(exist = false)
	private static final long serialVersionUID = 1L;

	/** ID /点位编号 */
	private String id;

	/** 点位名称 */
	private String name;

	/** 点位类型 */
	private String dwlx;

	/** 点位单位 */
	private String dwdw;

	/** 所属系统 */
	private String ssxt;

	/** 盾构机类型 */
	private String dgjlx;

	/** 描述 */
	private String ms;

	/** 备注 */
	private String bz;

	/** 相似度  */
	private int xiangsi;
	

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDwlx() {
		return this.dwlx;
	}

	public void setDwlx(String dwlx) {
		this.dwlx = dwlx;
	}

	public String getDwdw() {
		return this.dwdw;
	}

	public void setDwdw(String dwdw) {
		this.dwdw = dwdw;
	}

	public String getSsxt() {
		return this.ssxt;
	}

	public void setSsxt(String ssxt) {
		this.ssxt = ssxt;
	}

	public String getDgjlx() {
		return this.dgjlx;
	}

	public void setDgjlx(String dgjlx) {
		this.dgjlx = dgjlx;
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

	public int getXiangsi() {
		return xiangsi;
	}

	public void setXiangsi(int xiangsi) {
		this.xiangsi = xiangsi;
	}
	
	

}
