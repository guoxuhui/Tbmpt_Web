package com.crfeb.tbmpt.open.model;

import java.util.ArrayList;
import java.util.List;

/**
 * 移动端验收影像显示信息
 * Created by ztzn on 2017/8/13.
 */
public class YanShouYingXiangInfo {

    private String id;
    /**
     *
     * 项目id
     */
    private String projectid;
    /**
     *
     * 工程名称
     */
    private String projectname;
    /**
     *
     * 现场检查实际情况
     */
    private String miaoshu;
    /**
     *
     * 工程部位
     */
    private String gcbuwei;
    /**
     *
     * 天气
     */
    private String tianqi;
    /**
     *
     * 分部分项工序
     */
    private String gongxu;
    /**
     *
     * 施工班组
     */
    private String banzu;
    /**
     *
     * 班组负责人
     */
    private String bzfuzr;
    /**
     *
     * 检查验收意见和结论
     */
    private String jianchaqk;

    /**
     *
     * 验收人
     */
    private String yanshour;

    /**
     *
     * 验收时间
     */
    private String yanshousj;

    /**
     * 附件名称
     */
    private List<String> fileNames = new ArrayList<String>();

    /**
     * 附件缩略图名称路径
     */
    private List<String> minifileNames = new ArrayList<String>();
    /**
     * 附件原图路径
     */
    private List<String> originalfileNames = new ArrayList<String>();

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

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

    public String getMiaoshu() {
        return miaoshu;
    }

    public void setMiaoshu(String miaoshu) {
        this.miaoshu = miaoshu;
    }

    public String getGcbuwei() {
        return gcbuwei;
    }

    public void setGcbuwei(String gcbuwei) {
        this.gcbuwei = gcbuwei;
    }

    public String getTianqi() {
        return tianqi;
    }

    public void setTianqi(String tianqi) {
        this.tianqi = tianqi;
    }

    public String getGongxu() {
        return gongxu;
    }

    public void setGongxu(String gongxu) {
        this.gongxu = gongxu;
    }

    public String getBanzu() {
        return banzu;
    }

    public void setBanzu(String banzu) {
        this.banzu = banzu;
    }

    public String getBzfuzr() {
        return bzfuzr;
    }

    public void setBzfuzr(String bzfuzr) {
        this.bzfuzr = bzfuzr;
    }

    public String getJianchaqk() {
        return jianchaqk;
    }

    public void setJianchaqk(String jianchaqk) {
        this.jianchaqk = jianchaqk;
    }

    public String getYanshour() {
        return yanshour;
    }

    public void setYanshour(String yanshour) {
        this.yanshour = yanshour;
    }

    public String getYanshousj() {
        return yanshousj;
    }

    public void setYanshousj(String yanshousj) {
        this.yanshousj = yanshousj;
    }

    public List<String> getFileNames() {
        return fileNames;
    }

    public void setFileNames(List<String> fileNames) {
        this.fileNames = fileNames;
    }

    public List<String> getMinifileNames() {
        return minifileNames;
    }

    public void setMinifileNames(List<String> minifileNames) {
        this.minifileNames = minifileNames;
    }

    public List<String> getOriginalfileNames() {
        return originalfileNames;
    }

    public void setOriginalfileNames(List<String> originalfileNames) {
        this.originalfileNames = originalfileNames;
    }
}
