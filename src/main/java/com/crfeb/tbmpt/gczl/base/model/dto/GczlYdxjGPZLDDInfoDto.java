package com.crfeb.tbmpt.gczl.base.model.dto;

import java.io.Serializable;
import com.baomidou.mybatisplus.annotations.IdType;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.crfeb.tbmpt.commons.model.BaseModel;

/**
 * <p>盾构施工质量基础数据实体信息</p>
 * <p>描述：实体描述信息.</p>
 * <p>系统：</p>
 * <p>模块：基础模块</p>
 * <p>日期：2016-11-19</p>
 * @version 1.0
 * @author wangbinbin
 */
public class GczlYdxjGPZLDDInfoDto  extends BaseModel implements Serializable {

    private String id;

    /**
     * 分类名称<br>
     */
    private String typeName;

    /**
     * 基础数据名称<br>
     */
    private String ddName;

    /**
     * 使用状态<br>
     */
    private String ifQy;

    /**
     * 排序号<br>
     */
    private int sortNum;

    /**
     * 备注<br>
     */
    private String remarks;

    public String getId() {
      return id;
    }

    public void setId(String id) {
      this.id = id;
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

    /**
     * 基础数据名称
     */
    public String getDdName() {
      return ddName;
    }

    /**
     * 基础数据名称
     */
    public void setDdName(String ddName) {
      this.ddName = ddName;
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


    public int getSortNum() {
		return sortNum;
	}

	public void setSortNum(int sortNum) {
		this.sortNum = sortNum;
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

}