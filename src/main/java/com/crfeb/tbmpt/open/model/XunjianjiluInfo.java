package com.crfeb.tbmpt.open.model;

import com.crfeb.tbmpt.aqxj.xjdgl.model.AqxjXjnr;
import com.crfeb.tbmpt.gcaqxj.model.AqxjXjjlDetail;
import com.crfeb.tbmpt.gcaqxj.model.AqxjXjnrInfo;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ztzn on 2017/8/20.
 */
public class XunjianjiluInfo {

    /**
     * 巡检点id
     */
    private String foreignid;


    private String projectId;


    private String projectName;

    /**
     * 巡检点名称
     */
    private String name;
    /**
     * 检查频次
     */
    private String jianchapc;

    /**
     * 上次检查时间
     */
    private String lastCheckTime;

    private List<AqxjXjnrInfo> aqxjXjnrInfoList = new ArrayList<>();

    private List<AqxjXjjlDetail> aqxjXjjlDetails = new ArrayList<>();

    private String isSend;

    /**
     * 分类名称
     */
    private String typeName;
    /** 可多个，用逗号隔开 */
    private String zerenrid;

    /** 可多个，用逗号隔开 */
    private String zerenrmc;

    /** 可多个，用逗号隔开 */
    private String jiandurid;

    /** 可多个，用逗号隔开 */
    private String jiandurmc;

    /*
    巡检点位置
     */
    private String itemAddress;

    /**
     *
     * @return
     */
    private String miaoshu;



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getJianchapc() {
        return jianchapc;
    }

    public void setJianchapc(String jianchapc) {
        this.jianchapc = jianchapc;
    }

    public String getLastCheckTime() {
        return lastCheckTime;
    }

    public void setLastCheckTime(String lastCheckTime) {
        this.lastCheckTime = lastCheckTime;
    }

    public List<AqxjXjnrInfo> getAqxjXjnrInfoList() {
        return aqxjXjnrInfoList;
    }

    public void setAqxjXjnrInfoList(List<AqxjXjnrInfo> aqxjXjnrInfoList) {
        this.aqxjXjnrInfoList = aqxjXjnrInfoList;
    }

    public String getForeignid() {
        return foreignid;
    }

    public void setForeignid(String foreignid) {
        this.foreignid = foreignid;
    }

    public List<AqxjXjjlDetail> getAqxjXjjlDetails() {
        return aqxjXjjlDetails;
    }

    public void setAqxjXjjlDetails(List<AqxjXjjlDetail> aqxjXjjlDetails) {
        this.aqxjXjjlDetails = aqxjXjjlDetails;
    }

    public String getIsSend() {
        return isSend;
    }

    public void setIsSend(String isSend) {
        this.isSend = isSend;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public String getZerenrid() {
        return zerenrid;
    }

    public void setZerenrid(String zerenrid) {
        this.zerenrid = zerenrid;
    }

    public String getZerenrmc() {
        return zerenrmc;
    }

    public void setZerenrmc(String zerenrmc) {
        this.zerenrmc = zerenrmc;
    }

    public String getJiandurid() {
        return jiandurid;
    }

    public void setJiandurid(String jiandurid) {
        this.jiandurid = jiandurid;
    }

    public String getJiandurmc() {
        return jiandurmc;
    }

    public void setJiandurmc(String jiandurmc) {
        this.jiandurmc = jiandurmc;
    }

    public String getItemAddress() {
        return itemAddress;
    }

    public void setItemAddress(String itemAddress) {
        this.itemAddress = itemAddress;
    }

    public String getMiaoshu() {
        return miaoshu;
    }

    public void setMiaoshu(String miaoshu) {
        this.miaoshu = miaoshu;
    }

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }
}
