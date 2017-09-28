package com.crfeb.tbmpt.zl.model.vo;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotations.TableField;

/**
 * 质量上报信息表
 */
public class QualityInfoQueryVo implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    /**
     * 项目部名称
     */
    private String proName;
    /**
     * 上报日期开始
     */
    private String upDateStart;
    /**
     * 上报日期结束
     */
    private String upDateEnd;
    /**
     * 处理状态 0:未处理1:已处理
     */
    private String status;
    /**
     * 上报人
     */
    private String upUser;
    private String sort;
    
	/** 有无质量问题 */
	private String hasProblem;

    /** 区间 */
    private String section;

    /** 线路 */
    private String line;

    public String getSection() {
        return section;
    }

    public void setSection(String section) {
        this.section = section;
    }

    public String getLine() {
        return line;
    }

    public void setLine(String line) {
        this.line = line;
    }

    public String getUpUser() {
        return upUser;
    }

    public void setUpUser(String upUser) {
        this.upUser = upUser;
    }

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }

    public String getUpDateStart() {
        return upDateStart;
    }

    public void setUpDateStart(String upDateStart) {
        this.upDateStart = upDateStart;
    }

    public String getUpDateEnd() {
        return upDateEnd;
    }

    public void setUpDateEnd(String upDateEnd) {
        this.upDateEnd = upDateEnd;
    }

    public String getProName() {
        return this.proName;
    }

    public void setProName(String proName) {
        this.proName = proName;
    }


    public String getStatus() {
        return this.status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

	public String getHasProblem() {
		return hasProblem;
	}

	public void setHasProblem(String hasProblem) {
		this.hasProblem = hasProblem;
	}

    
}
