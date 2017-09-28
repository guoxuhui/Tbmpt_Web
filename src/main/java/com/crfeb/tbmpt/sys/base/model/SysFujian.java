package com.crfeb.tbmpt.sys.base.model;

import java.io.Serializable;
import com.baomidou.mybatisplus.annotations.IdType;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.crfeb.tbmpt.commons.model.BaseModel;
import com.baomidou.mybatisplus.annotations.TableName;

/**
 * <p>公共统一附件实体信息</p>
 * <p>描述：实体描述信息.</p>
 * <p>系统：系统管理</p>
 * <p>模块：公共模块</p>
 * <p>日期：2017-02-17</p>
 * @version 1.0
 * @author wangbinbin
 */
@TableName("SYS_FUJIAN")
public class SysFujian extends BaseModel implements Serializable {

    @TableId(type = IdType.UUID)
    private String id;

    /**
     * 文件名称<br>
     */
    @TableField(value = "fileName")
    private String fileName;

    /**
     * 文件路径<br>
     */
    @TableField(value = "filePath")
    private String filePath;

    /**
     * 缩略图路径<br>
     */
    @TableField(value = "minImgPath")
    private String minImgPath;

    /**
     * 文件类型<br>
     */
    @TableField(value = "fileType")
    private String fileType;

    /**
     * 文件大小<br>
     */
    @TableField(value = "fileSize")
    private float fileSize;

    /**
     * 模块ID<br>
     */
    @TableField(value = "resId")
    private String resId;


    /**
     * 引用表ID<br>
     */
    @TableField(value = "foreignId")
    private String foreignId;

    /**
     * 顺序号<br>
     */
    @TableField(value = "orderno")
    private int orderno;

    /**
     * 备用字段1<br>
     */
    @TableField(value = "backupOne")
    private String backupOne;

    /**
     * 备用字段2<br>
     */
    @TableField(value = "backupTwo")
    private String backupTwo;

    public String getId() {
      return id;
    }

    public void setId(String id) {
      this.id = id;
    }

    /**
     * 文件名称
     */
    public String getFileName() {
      return fileName;
    }

    /**
     * 文件名称
     */
    public void setFileName(String fileName) {
      this.fileName = fileName;
    }

    /**
     * 文件路径
     */
    public String getFilePath() {
      return filePath;
    }

    /**
     * 文件路径
     */
    public void setFilePath(String filePath) {
      this.filePath = filePath;
    }

    /**
     * 缩略图路径
     */
    public String getMinImgPath() {
      return minImgPath;
    }

    /**
     * 缩略图路径
     */
    public void setMinImgPath(String minImgPath) {
      this.minImgPath = minImgPath;
    }

    /**
     * 文件类型
     */
    public String getFileType() {
      return fileType;
    }

    /**
     * 文件类型
     */
    public void setFileType(String fileType) {
      this.fileType = fileType;
    }

    /**
     * 文件大小
     */
    public float getFileSize() {
      return fileSize;
    }

    /**
     * 文件大小
     */
    public void setFileSize(float fileSize) {
      this.fileSize = fileSize;
    }

    /**
     * 模块ID
     */
    public String getResId() {
      return resId;
    }

    /**
     * 模块ID
     */
    public void setResId(String resId) {
      this.resId = resId;
    }


    /**
     * 引用表ID
     */
    public String getForeignId() {
      return foreignId;
    }

    /**
     * 引用表ID
     */
    public void setForeignId(String foreignId) {
      this.foreignId = foreignId;
    }

    /**
     * 顺序号
     */
    public int getOrderno() {
      return orderno;
    }

    /**
     * 顺序号
     */
    public void setOrderno(int orderno) {
      this.orderno = orderno;
    }

    /**
     * 备用字段1
     */
    public String getBackupOne() {
      return backupOne;
    }

    /**
     * 备用字段1
     */
    public void setBackupOne(String backupOne) {
      this.backupOne = backupOne;
    }

    /**
     * 备用字段2
     */
    public String getBackupTwo() {
      return backupTwo;
    }

    /**
     * 备用字段2
     */
    public void setBackupTwo(String backupTwo) {
      this.backupTwo = backupTwo;
    }

}