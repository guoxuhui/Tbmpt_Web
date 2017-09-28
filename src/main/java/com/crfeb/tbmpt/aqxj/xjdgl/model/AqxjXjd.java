package com.crfeb.tbmpt.aqxj.xjdgl.model;

import java.io.Serializable;
import com.baomidou.mybatisplus.annotations.IdType;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.crfeb.tbmpt.commons.model.BaseModel;
import com.baomidou.mybatisplus.annotations.TableName;

/**
 * <p>安全巡检点实体信息</p>
 * <p>描述：实体描述信息.</p>
 * <p>系统：工程项目安全巡检系统</p>
 * <p>模块：巡检点管理</p>
 * <p>日期：2017-05-26</p>
 * @version 1.0
 * @author zhuyabing
 */
@TableName("ZLXJ_XUNJIAN_D")
public class AqxjXjd extends BaseModel implements Serializable {

    @TableId(type = IdType.UUID)
    private String id;

    /**
     * 工程项目id<br>
     */
    @TableField(value = "projectId")
    private String projectId;

    /**
     * 工程项目名称<br>
     */
    @TableField(value = "projectName")
    private String projectName;

    /**
     * 巡检点名称<br>
     * <font color="ff0000">必填项</font><br>
     */
    @TableField(value = "mingCheng")
    private String mingCheng;

    /**
     * 检查点所在位置<br>
     */
    @TableField(value = "address")
    private String address;

    /**
     * 顺序号<br>
     */
    @TableField(value = "xuHao")
    private String xuHao;

    /**
     * 备注<br>
     */
    @TableField(value = "beiZhu")
    private String beiZhu;

    /**
     * 责任人id<br>
     */
    @TableField(value = "zeRenrid")
    private String zeRenrid;

    /**
     * 责任人名称<br>
     * <font color="ff0000">必填项</font><br>
     */
    @TableField(value = "zeRenrmc")
    private String zeRenrmc;

    /**
     * 监督人id<br>
     */
    @TableField(value = "jianDurid")
    private String jianDurid;

    /**
     * 监督人名称<br>
     * <font color="ff0000">必填项</font><br>
     */
    @TableField(value = "jianDurmc")
    private String jianDurmc;

    /**
     * 检查频次<br>
     */
    @TableField(value = "jianChapc")
    private String jianChapc;

    /**
     * 分类id<br>
     */
    @TableField(value = "typeId")
    private String typeId;

    /**
     * 分类名称<br>
     * <font color="ff0000">必填项</font><br>
     */
    @TableField(value = "typeName")
    private String typeName;

    public String getId() {
      return id;
    }

    public void setId(String id) {
      this.id = id;
    }

    /**
     * 工程项目id
     */
    public String getProjectId() {
      return projectId;
    }

    /**
     * 工程项目id
     */
    public void setProjectId(String projectId) {
      this.projectId = projectId;
    }

    /**
     * 工程项目名称
     */
    public String getProjectName() {
      return projectName;
    }

    /**
     * 工程项目名称
     */
    public void setProjectName(String projectName) {
      this.projectName = projectName;
    }

    /**
     * 巡检点名称
     */
    public String getMingCheng() {
      return mingCheng;
    }

    /**
     * 巡检点名称
     */
    public void setMingCheng(String mingCheng) {
      this.mingCheng = mingCheng;
    }

    /**
     * 检查点所在位置
     */
    public String getAddress() {
      return address;
    }

    /**
     * 检查点所在位置
     */
    public void setAddress(String address) {
      this.address = address;
    }

    /**
     * 顺序号
     */
    public String getXuHao() {
      return xuHao;
    }

    /**
     * 顺序号
     */
    public void setXuHao(String xuHao) {
      this.xuHao = xuHao;
    }

    /**
     * 备注
     */
    public String getBeiZhu() {
      return beiZhu;
    }

    /**
     * 备注
     */
    public void setBeiZhu(String beiZhu) {
      this.beiZhu = beiZhu;
    }

    /**
     * 责任人id
     */
    public String getZeRenrid() {
      return zeRenrid;
    }

    /**
     * 责任人id
     */
    public void setZeRenrid(String zeRenrid) {
      this.zeRenrid = zeRenrid;
    }

    /**
     * 责任人名称
     */
    public String getZeRenrmc() {
      return zeRenrmc;
    }

    /**
     * 责任人名称
     */
    public void setZeRenrmc(String zeRenrmc) {
      this.zeRenrmc = zeRenrmc;
    }

    /**
     * 监督人id
     */
    public String getJianDurid() {
      return jianDurid;
    }

    /**
     * 监督人id
     */
    public void setJianDurid(String jianDurid) {
      this.jianDurid = jianDurid;
    }

    /**
     * 监督人名称
     */
    public String getJianDurmc() {
      return jianDurmc;
    }

    /**
     * 监督人名称
     */
    public void setJianDurmc(String jianDurmc) {
      this.jianDurmc = jianDurmc;
    }

    /**
     * 检查频次
     */
    public String getJianChapc() {
      return jianChapc;
    }

    /**
     * 检查频次
     */
    public void setJianChapc(String jianChapc) {
      this.jianChapc = jianChapc;
    }

    /**
     * 分类id
     */
    public String getTypeId() {
      return typeId;
    }

    /**
     * 分类id
     */
    public void setTypeId(String typeId) {
      this.typeId = typeId;
    }

    /**
     * 分类名称
     */
    public String getTypeName() {
      return typeName;
    }

    /**
     * 分类名称
     */
    public void setTypeName(String typeName) {
      this.typeName = typeName;
    }

}