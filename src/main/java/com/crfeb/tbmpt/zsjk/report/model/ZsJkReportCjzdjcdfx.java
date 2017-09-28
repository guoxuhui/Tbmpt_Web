package com.crfeb.tbmpt.zsjk.report.model;

import com.baomidou.mybatisplus.annotations.IdType;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;

/**
 * <p>沉降最大监测点分析 实体信息类</p>
 * <p>描述：实体描述信息.</p>
 * <p>系统：展示界面接口</p>
 * <p>日期：2017-03-07</p>
 * @version 1.0
 * @author wl_wpg
 */
@TableName("ZSJK_REPORT_CJZDJCDFX")
public class ZsJkReportCjzdjcdfx {

	/** UUID */
	@TableId(type = IdType.UUID)
    private String id;
	
	/** 项目ID */
	private String xmId;
	/** 区间ID */
	private String qjId;
	/** 监测时间 */
	private String jcsj;
	/** 环号 */
	private String hh;
	
	/** 累计变化量 */
	private String ljbhl;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getXmId() {
		return xmId;
	}
	public void setXmId(String xmId) {
		this.xmId = xmId;
	}
	public String getQjId() {
		return qjId;
	}
	public void setQjId(String qjId) {
		this.qjId = qjId;
	}
	public String getJcsj() {
		return jcsj;
	}
	public void setJcsj(String jcsj) {
		this.jcsj = jcsj;
	}
	public String getHh() {
		return hh;
	}
	public void setHh(String hh) {
		this.hh = hh;
	}
	public String getLjbhl() {
		return ljbhl;
	}
	public void setLjbhl(String ljbhl) {
		this.ljbhl = ljbhl;
	}
  
	
}
