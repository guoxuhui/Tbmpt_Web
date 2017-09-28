package com.crfeb.tbmpt.zsjk.xmb.model;

import java.io.Serializable;
import com.baomidou.mybatisplus.annotations.IdType;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.crfeb.tbmpt.commons.model.BaseModel;
import com.baomidou.mybatisplus.annotations.TableName;

/**
 * <p>项目部角度  当前项目的累计产值信息实体信息</p>
 * <p>描述：实体描述信息.</p>
 * <p>系统：展示界面接口</p>
 * <p>模块：项目部角度模块</p>
 * <p>日期：2017-01-09</p>
 * @version 1.0
 * @author YangYJ
 */
@TableName("zsjk_xmb_XmLjCzxx")
public class ZsJkXmbXmLjCzXx  implements Serializable {

    @TableId(type = IdType.UUID)
    private String id;

    /**
     * 项目id<br>
     */
    @TableField(value = "projectId")
    private String projectId;

    /**
     * 项目名称<br>
     */
    @TableField(value = "projectName")
    private String projectName;

    /**
     * 累计产值<br>
     */
    @TableField(value = "leijcz")
    private String leijcz;

    public String getId() {
      return id;
    }

    public void setId(String id) {
      this.id = id;
    }

    /**
     * 项目id
     */
    public String getProjectId() {
      return projectId;
    }

    /**
     * 项目id
     */
    public void setProjectId(String projectId) {
      this.projectId = projectId;
    }

    /**
     * 项目名称
     */
    public String getProjectName() {
      return projectName;
    }

    /**
     * 项目名称
     */
    public void setProjectName(String projectName) {
      this.projectName = projectName;
    }

    /**
     * 累计产值
     */
    public String getLeijcz() {
      return leijcz;
    }

    /**
     * 累计产值
     */
    public void setLeijcz(String leijcz) {
      this.leijcz = leijcz;
    }

}