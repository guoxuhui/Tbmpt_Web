package com.crfeb.tbmpt.gcaqxj.model.dto;

import com.crfeb.tbmpt.commons.model.BaseModel;

import java.io.Serializable;

/**
 * Created by Administrator on 2017/7/24.
 */
public class AqxjXjdInfoDto extends BaseModel implements Serializable {

    private String projectid;

    /** 项目名称 */
    private String projectname;

    /** 检查点分类 */
    private String typeName;
    /** 巡检点的名称 */
    private String mingcheng;
    /**责任人  */
    private String zerenrmc;

    /**监督人*/
    private String jiandurmc;

    public String getProjectname() {
        return projectname;
    }

    public void setProjectname(String projectname) {
        this.projectname = projectname;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public String getMingcheng() {
        return mingcheng;
    }

    public void setMingcheng(String mingcheng) {
        this.mingcheng = mingcheng;
    }

    public String getZerenrmc() {
        return zerenrmc;
    }

    public void setZerenrmc(String zerenrmc) {
        this.zerenrmc = zerenrmc;
    }

    public String getJiandurmc() {
        return jiandurmc;
    }

    public void setJiandurmc(String jiandurmc) {
        this.jiandurmc = jiandurmc;
    }

    public String getProjectid() {
        return projectid;
    }

    public void setProjectid(String projectid) {
        this.projectid = projectid;
    }


    @Override
    public String toString() {
        return "AqxjXjdInfoDto{" +
                "projectid='" + projectid + '\'' +
                ", projectname='" + projectname + '\'' +
                ", typeName='" + typeName + '\'' +
                ", mingcheng='" + mingcheng + '\'' +
                ", zerenrmc='" + zerenrmc + '\'' +
                ", jiandurmc='" + jiandurmc + '\'' +
                '}';
    }
}
