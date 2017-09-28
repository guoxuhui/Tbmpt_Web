package com.crfeb.tbmpt.risk.model.vo;

import com.crfeb.tbmpt.sys.base.model.dto.SysFujianDto;

import java.io.Serializable;
import java.util.List;

/**
 * 风控信息表
 */
@SuppressWarnings("serial")
public class RiskInfoVo implements Serializable {

    /**
     * UUID
     */
    private String id;

    /**
     * 片区分类
     */
    private String section;

    /**
     * 片区分类
     */
    private String sectionStr;

    /**
     * 项目部名称
     */
    private String proName;

    /**
     * 项目部名称
     */
    private String proNameStr;

    /**
     * 安全风险级别
     */
    private String rikeLevel;

    /**
     * 安全风险级别
     */
    private String rikeLevelStr;

    /**
     * 风险级别颜色
     */
    private String rikeColor;

    /**
     * 安全质量风险
     */
    private String rikeDesc;

    /**
     * 上报人
     */
    private String upUser;

    /**
     * 上报人的名称
     */
    private String upUserName;

    /**
     * 上报人联系方式
     */
    private String upUserPhone;

    /**
     * 监控(发生)时段开始
     */
    private String rikeTimeStart;

    /**
     * 监控(发生)时段结束
     */
    private String rikeTimeEnd;

    /**
     * 填报时间
     */
    private String upTime;

    /**
     * 状态 是否排除 0:未排除 1:已排除
     */
    private String isOut;

    /**
     * 项目负责人
     */
    private String empId;

    /**
     * 关联机构编号
     */
    private String orgzId;

    /**
     * 上级机构编号
     */
    private String parentId;

    /**
     * 负责部门
     */
    private String dpts;

    /**
     * 负责部门
     */
    private String dptsStr;

    /**
     * 负责人
     */
    private String persoon;

    /**
     * 负责人
     */
    private String persoonStr;

    /**
     * 主要管控措施
     */
    private String mainControlMethod;

    /**
     * 备注
     */
    private String remark;

    /**
     * 附件
     */
    private List<SysFujianDto> sysFujianDtos;

    public String getSectionStr() {
        return sectionStr;
    }

    public void setSectionStr(String sectionStr) {
        this.sectionStr = sectionStr;
    }

    public String getDptsStr() {
        return dptsStr;
    }

    public void setDptsStr(String dptsStr) {
        this.dptsStr = dptsStr;
    }

    public String getPersoonStr() {
        return persoonStr;
    }

    public void setPersoonStr(String persoonStr) {
        this.persoonStr = persoonStr;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSection() {
        return this.section;
    }

    public void setSection(String section) {
        this.section = section;
    }

    public String getProName() {
        return this.proName;
    }

    public void setProName(String proName) {
        this.proName = proName;
    }

    public String getRikeLevel() {
        return this.rikeLevel;
    }

    public void setRikeLevel(String rikeLevel) {
        this.rikeLevel = rikeLevel;
    }

    public String getRikeDesc() {
        return this.rikeDesc;
    }

    public void setRikeDesc(String rikeDesc) {
        this.rikeDesc = rikeDesc;
    }

    public String getUpUser() {
        return this.upUser;
    }

    public void setUpUser(String upUser) {
        this.upUser = upUser;
    }

    public String getUpUserPhone() {
        return this.upUserPhone;
    }

    public void setUpUserPhone(String upUserPhone) {
        this.upUserPhone = upUserPhone;
    }

    public String getRikeTimeStart() {
        return rikeTimeStart;
    }

    public void setRikeTimeStart(String rikeTimeStart) {
        this.rikeTimeStart = rikeTimeStart;
    }

    public String getRikeTimeEnd() {
        return rikeTimeEnd;
    }

    public void setRikeTimeEnd(String rikeTimeEnd) {
        this.rikeTimeEnd = rikeTimeEnd;
    }

    public String getUpTime() {
        return this.upTime;
    }

    public void setUpTime(String upTime) {
        this.upTime = upTime;
    }

    public String getIsOut() {
        return this.isOut;
    }

    public void setIsOut(String isOut) {
        this.isOut = isOut;
    }

    public String getEmpId() {
        return this.empId;
    }

    public void setEmpId(String empId) {
        this.empId = empId;
    }

    public String getOrgzId() {
        return this.orgzId;
    }

    public void setOrgzId(String orgzId) {
        this.orgzId = orgzId;
    }

    public String getParentId() {
        return this.parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public String getDpts() {
        return this.dpts;
    }

    public void setDpts(String dpts) {
        this.dpts = dpts;
    }

    public String getPersoon() {
        return this.persoon;
    }

    public void setPersoon(String persoon) {
        this.persoon = persoon;
    }

    public String getMainControlMethod() {
        return this.mainControlMethod;
    }

    public void setMainControlMethod(String mainControlMethod) {
        this.mainControlMethod = mainControlMethod;
    }

    public String getRemark() {
        return this.remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getRikeLevelStr() {
        return rikeLevelStr;
    }

    public void setRikeLevelStr(String rikeLevelStr) {
        this.rikeLevelStr = rikeLevelStr;
    }

    public String getProNameStr() {
        return proNameStr;
    }

    public void setProNameStr(String proNameStr) {
        this.proNameStr = proNameStr;
    }

    public String getRikeColor() {
        return rikeColor;
    }

    public void setRikeColor(String rikeColor) {
        this.rikeColor = rikeColor;
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

