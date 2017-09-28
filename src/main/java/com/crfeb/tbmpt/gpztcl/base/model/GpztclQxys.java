package com.crfeb.tbmpt.gpztcl.base.model;

import java.io.Serializable;
import com.baomidou.mybatisplus.annotations.IdType;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;

/**
 * <p>纵 坡 、竖 曲 线 要 素    实体信息</p>
 * <p>描述：输入数据 区域.</p>
 * <p>系统：管片姿态测量系统</p>
 * <p>模块：线路中心线信息管理</p>
 * <p>日期：2016-12-23</p>
 * @version 1.0
 * @author YangYj
 */
@TableName("GPZTCL_qxys")
public class GpztclQxys  implements Serializable {


	@TableId(type = IdType.UUID)
    private String id;

    /**
     * 线路中心线信息管理 表  ，主表Id <br>
     */
    private String FId;

    /***
     * 输入数据区域
     */
    /**
     * 变坡点 桩号(K) bpd zh<br>
     */
    private Double bpdzh;

    /**
     * 变坡点 高程(m) bpd gc<br>   
     */
    private Double bpdgc;

    /**
     * 设计曲线半径R(m) sjqxbj<br>
     */
    private Double sjqxbj;

    /**
     * 备注  Remarks<br>
     */
    private String remarks;
    
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getFId() {
		return FId;
	}

	public void setFId(String fId) {
		FId = fId;
	}

	public Double getBpdzh() {
		return bpdzh;
	}

	public void setBpdzh(Double bpdzh) {
		this.bpdzh = bpdzh;
	}

	public Double getBpdgc() {
		return bpdgc;
	}

	public void setBpdgc(Double bpdgc) {
		this.bpdgc = bpdgc;
	}

	public Double getSjqxbj() {
		return sjqxbj;
	}

	public void setSjqxbj(Double sjqxbj) {
		this.sjqxbj = sjqxbj;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	

	

}