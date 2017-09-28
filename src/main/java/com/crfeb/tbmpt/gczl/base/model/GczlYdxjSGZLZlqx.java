package com.crfeb.tbmpt.gczl.base.model;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotations.IdType;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.crfeb.tbmpt.commons.model.BaseModel;

/**
 * <p>结构施工质量基础数据-质量缺陷实体信息</p>
 * <p>描述：实体描述信息.</p>
 * <p>系统：</p>
 * <p>模块：基础模块</p>
 * <p>日期：2016-11-24</p>
 * @version 1.0
 * @author wangbinbin
 */
@TableName("GCZLYDXJ_SGZL_ZLQX")
public class GczlYdxjSGZLZlqx extends BaseModel implements Serializable {

    @TableId(type = IdType.UUID)
    private String id;

    /**
     * 存在质量问题、缺陷说明的名称<br>
     * <font color="ff0000">必填项</font><br>
     */
    @TableField(value = "zlQx")
    private String zlQx;

    /**
     * 排序号<br>
     */
    @TableField(value = "sortNum")
    private String sortNum;

    /**
     * 使用状态<br>
     */
    @TableField(value = "ifQy")
    private String ifQy;

    /**
     * 备注<br>
     */
    @TableField(value = "remarks")
    private String remarks;

    /**
     * 施工内容<br>
     * <font color="ff0000">必填项</font><br>
     */
    @TableField(value = "sgNr")
    private String sgNr;

    public String getId() {
      return id;
    }

    public void setId(String id) {
      this.id = id;
    }

    /**
     * 存在质量问题、缺陷说明的名称
     */
    public String getZlQx() {
      return zlQx;
    }

    /**
     * 存在质量问题、缺陷说明的名称
     */
    public void setZlQx(String zlQx) {
      this.zlQx = zlQx;
    }

    /**
     * 排序号
     */
    public String getSortNum() {
      return sortNum;
    }

    /**
     * 排序号
     */
    public void setSortNum(String sortNum) {
      this.sortNum = sortNum;
    }

    /**
     * 使用状态
     */
    public String getIfQy() {
      return ifQy;
    }

    /**
     * 使用状态
     */
    public void setIfQy(String ifQy) {
      this.ifQy = ifQy;
    }

    /**
     * 备注
     */
    public String getRemarks() {
      return remarks;
    }

    /**
     * 备注
     */
    public void setRemarks(String remarks) {
      this.remarks = remarks;
    }

    /**
     * 施工内容
     */
    public String getSgNr() {
      return sgNr;
    }

    /**
     * 施工内容
     */
    public void setSgNr(String sgNr) {
      this.sgNr = sgNr;
    }

}