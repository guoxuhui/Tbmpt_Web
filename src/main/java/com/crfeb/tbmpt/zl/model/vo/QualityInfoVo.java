package com.crfeb.tbmpt.zl.model.vo;

import java.io.Serializable;
import java.util.List;

import com.baomidou.mybatisplus.annotations.IdType;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.crfeb.tbmpt.sys.base.model.dto.SysFujianDto;

/**
 * 质量上报信息表
 */
public class QualityInfoVo implements Serializable {

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

    /**
     * UUID
     */
    @TableId(type = IdType.UUID)
    private String id;

    /**
     * 项目部名称
     */
    @TableField(value = "PRO_NAME")
    private String proName;

    /**
     * 区间
     */
    private String section;

    /**
     * 线路
     */
    private String line;

    /**
     * 环号
     */
    @TableField(value = "CYCLE_NO")
    private String cycleNo;

    /**
     * 有无质量问题
     */
    @TableField(value = "HAS_PROBLEM")
    private String hasProblem;

    /**
     * 问题分类
     */
    @TableField(value = "PROBLEM_TYPE")
    private String problemType;

    /**
     * 问题描述
     */
    @TableField(value = "PROBLEM_DESC")
    private String problemDesc;

    /**
     * 拼装日期
     */
    @TableField(value = "FIX_DATE")
    private String fixDate;

    /**
     * 上报日期
     */
    @TableField(value = "UP_DATE")
    private String upDate;

    /**
     * 上报人
     */
    @TableField(value = "UP_USER")
    private String upUser;

    /**
     * 上报人名称
     */
    private String upUserName;

    /**
     * 联系方式
     */
    @TableField(value = "LINK_WAY")
    private String linkWay;

    /**
     * 处理状态 0:未处理1:已处理
     */
    private String status;
    private String sbDate;
    /**
     * 处理后照片
     */
    private String pic;

    /**
     * 备注
     */
    private String remark;
    private String proId;

    /**
     * 附件
     */
    private List<SysFujianDto> sysFujianDtos;

    public String getSbDate() {
        return sbDate;
    }

    public void setSbDate(String sbDate) {
        this.sbDate = sbDate;
    }

    public String getProId() {
        return proId;
    }

    public void setProId(String proId) {
        this.proId = proId;
    }

    private String proName1;
    private String lineName;
    private String sectionName;

    public String getSectionName() {
        return sectionName;
    }

    public void setSectionName(String sectionName) {
        this.sectionName = sectionName;
    }

    public String getProName1() {
        return proName1;
    }

    public void setProName1(String proName1) {
        this.proName1 = proName1;
    }

    public String getLineName() {
        return lineName;
    }

    public void setLineName(String lineName) {
        this.lineName = lineName;
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getProName() {
        return this.proName;
    }

    public void setProName(String proName) {
        this.proName = proName;
    }

    public String getSection() {
        return this.section;
    }

    public void setSection(String section) {
        this.section = section;
    }

    public String getLine() {
        return this.line;
    }

    public void setLine(String line) {
        this.line = line;
    }

    public String getCycleNo() {
        return this.cycleNo;
    }

    public void setCycleNo(String cycleNo) {
        this.cycleNo = cycleNo;
    }

    public String getHasProblem() {
        return this.hasProblem;
    }

    public void setHasProblem(String hasProblem) {
        this.hasProblem = hasProblem;
    }

    public String getProblemType() {
        return this.problemType;
    }

    public void setProblemType(String problemType) {
        this.problemType = problemType;
    }

    public String getProblemDesc() {
        return this.problemDesc;
    }

    public void setProblemDesc(String problemDesc) {
        this.problemDesc = problemDesc;
    }

    public String getFixDate() {
        return this.fixDate;
    }

    public void setFixDate(String fixDate) {
        this.fixDate = fixDate;
    }

    public String getUpDate() {
        return this.upDate;
    }

    public void setUpDate(String upDate) {
        this.upDate = upDate;
    }

    public String getUpUser() {
        return this.upUser;
    }

    public void setUpUser(String upUser) {
        this.upUser = upUser;
    }

    public String getLinkWay() {
        return this.linkWay;
    }

    public void setLinkWay(String linkWay) {
        this.linkWay = linkWay;
    }

    public String getStatus() {
        return this.status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getPic() {
        return this.pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public String getRemark() {
        return this.remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getUpUserName() {
        return upUserName;
    }

    public void setUpUserName(String upUserName) {
        this.upUserName = upUserName;
    }

    public List<SysFujianDto> getSysFujianDtos() {
        return sysFujianDtos;
    }

    public void setSysFujianDtos(List<SysFujianDto> sysFujianDtos) {
        this.sysFujianDtos = sysFujianDtos;
    }
}
