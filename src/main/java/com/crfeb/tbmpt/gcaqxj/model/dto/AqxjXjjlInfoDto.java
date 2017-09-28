package com.crfeb.tbmpt.gcaqxj.model.dto;

import com.baomidou.mybatisplus.annotations.TableField;
import com.crfeb.tbmpt.commons.model.BaseModel;

import java.io.Serializable;

/**
 * Created by Administrator on 2017/7/28.
 */
public class AqxjXjjlInfoDto extends BaseModel implements Serializable {

    private static final long serialVersionUID = 1L;

    /** 项目ID */
    private String projectid;

    /** 项目名称 */
    private String projectname;

    /** 检查点名称*/
    private String mingcheng;

    /** 检查点类型id*/
    private String itemid;

    /** 检查点类型*/
    private String itemtype;

    /** 检查点位置 */
    private String itemadress;

    /** 责任人id*/
    private String zerenrid;

    /** 责任人名称 */
    private String zerenrmc;

    /** 监督人id
     * */
    private String jiandurid;

    /** 监督人名称 */
    private String jiandurmc;

    /** 检查频次*/
    private String jianchapc;

    /** 检查结果*/
    private String jianchajg;

    /** 检查结果描述 */
    private String jieguoms;

    /** 检查时间*/
    private String jianchatime;

    /** 检查人*/
    private String jianchaperson;

    /** 是否被查看鿴 */
    private String isView;

    /** 是否发送鿴 */
    private String isSend;

    /**
     * 开始时间
     */
    private  String  startDate;

    /**
     *结束时间
     */
    private String endDate;


    public String getProjectid() {
        return projectid;
    }

    public void setProjectid(String projectid) {
        this.projectid = projectid;
    }

    public String getProjectname() {
        return projectname;
    }

    public void setProjectname(String projectname) {
        this.projectname = projectname;
    }

    public String getMingcheng() {
        return mingcheng;
    }

    public void setMingcheng(String mingcheng) {
        this.mingcheng = mingcheng;
    }

    public String getItemid() {
        return itemid;
    }

    public void setItemid(String itemid) {
        this.itemid = itemid;
    }

    public String getItemtype() {
        return itemtype;
    }

    public void setItemtype(String itemtype) {
        this.itemtype = itemtype;
    }

    public String getItemadress() {
        return itemadress;
    }

    public void setItemadress(String itemadress) {
        this.itemadress = itemadress;
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

    public String getJianchapc() {
        return jianchapc;
    }

    public void setJianchapc(String jianchapc) {
        this.jianchapc = jianchapc;
    }

    public String getJianchajg() {
        return jianchajg;
    }

    public void setJianchajg(String jianchajg) {
        this.jianchajg = jianchajg;
    }

    public String getJieguoms() {
        return jieguoms;
    }

    public void setJieguoms(String jieguoms) {
        this.jieguoms = jieguoms;
    }

    public String getJianchatime() {
        return jianchatime;
    }

    public void setJianchatime(String jianchatime) {
        this.jianchatime = jianchatime;
    }

    public String getJianchaperson() {
        return jianchaperson;
    }

    public void setJianchaperson(String jianchaperson) {
        this.jianchaperson = jianchaperson;
    }

    public String getIsView() {
        return isView;
    }

    public void setIsView(String isView) {
        this.isView = isView;
    }

    public String getIsSend() {
        return isSend;
    }

    public void setIsSend(String isSend) {
        this.isSend = isSend;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }
}
