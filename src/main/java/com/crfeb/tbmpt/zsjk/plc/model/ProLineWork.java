package com.crfeb.tbmpt.zsjk.plc.model;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotations.IdType;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;

/**
 *
 * 盾构机点位与标准关联表
 *
 */
@TableName("PRO_LINE_WORK")
public class ProLineWork implements Serializable {

	@TableField(exist = false)
	private static final long serialVersionUID = 1L;

	/** ID */
	@TableId(type = IdType.UUID)
	private String id;

	private String line_id;

	private String tbm_id;

	private String work_date;

	private String work_hs_sum;
	
	private String work_hs_start;
	private String work_hs_end;
	private String time_jj;
	private String time_ph;
	private String time_tj;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getLine_id() {
		return line_id;
	}
	public void setLine_id(String line_id) {
		this.line_id = line_id;
	}
	public String getTbm_id() {
		return tbm_id;
	}
	public void setTbm_id(String tbm_id) {
		this.tbm_id = tbm_id;
	}
	public String getWork_date() {
		return work_date;
	}
	public void setWork_date(String work_date) {
		this.work_date = work_date;
	}
	public String getWork_hs_sum() {
		return work_hs_sum;
	}
	public void setWork_hs_sum(String work_hs_sum) {
		this.work_hs_sum = work_hs_sum;
	}
	public String getWork_hs_start() {
		return work_hs_start;
	}
	public void setWork_hs_start(String work_hs_start) {
		this.work_hs_start = work_hs_start;
	}
	public String getWork_hs_end() {
		return work_hs_end;
	}
	public void setWork_hs_end(String work_hs_end) {
		this.work_hs_end = work_hs_end;
	}
	public String getTime_jj() {
		return time_jj;
	}
	public void setTime_jj(String time_jj) {
		this.time_jj = time_jj;
	}
	public String getTime_ph() {
		return time_ph;
	}
	public void setTime_ph(String time_ph) {
		this.time_ph = time_ph;
	}
	public String getTime_tj() {
		return time_tj;
	}
	public void setTime_tj(String time_tj) {
		this.time_tj = time_tj;
	}



}
