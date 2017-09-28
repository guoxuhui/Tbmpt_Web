package com.crfeb.tbmpt.aqsc.base.model;

import java.io.Serializable;
import com.baomidou.mybatisplus.annotations.IdType;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.crfeb.tbmpt.commons.model.BaseModel;
import com.baomidou.mybatisplus.annotations.TableName;

/**
 * <p>培训记录实体信息</p>
 * <p>描述：实体描述信息.</p>
 * <p>系统：安全生产管理</p>
 * <p>模块：基础信息</p>
 * <p>日期：2017-02-21</p>
 * @version 1.0
 * @author wangbinbin
 */
@TableName("AQSC_HUIYIJILU")
public class HuiyiJilu extends BaseModel implements Serializable {

    @TableId(type = IdType.UUID)
    private String id;

    /**
     * 培训日期<br>
     * <font color="ff0000">必填项</font><br>
     */
    @TableField(value = "hydate")
    private String hydate;

    /**
     * 培训名称<br>
     * <font color="ff0000">必填项</font><br>
     */
    @TableField(value = "name")
    private String name;

    /**
     * 培训地点<br>
     * <font color="ff0000">必填项</font><br>
     */
    @TableField(value = "adress")
    private String adress;

    /**
     * 培训摘要<br>
     */
    @TableField(value = "content")
    private String content;

    /**
     * 培训主持人<br>
     */
    @TableField(value = "zhuchiren")
    private String zhuchiren;

    /**
     * 培训参加人<br>
     */
    @TableField(value = "chuxiren")
    private String chuxiren;

    /**
     * 培训记录人<br>
     */
    @TableField(value = "jiluren")
    private String jiluren;
    
    
    /**
     * 项目编号
     */
    @TableField(value = "xmBh")
    private String xmBh;
    
    /**
     * 项目名称
     */
    @TableField(value = "xmName")
    private String xmName;

    public String getId() {
      return id;
    }

    public void setId(String id) {
      this.id = id;
    }

    /**
     * 培训日期
     */
    public String getHydate() {
      return hydate;
    }

    /**
     * 培训日期
     */
    public void setHydate(String hydate) {
      this.hydate = hydate;
    }

    /**
     * 培训名称
     */
    public String getName() {
      return name;
    }

    /**
     * 培训名称
     */
    public void setName(String name) {
      this.name = name;
    }

    /**
     * 培训地点
     */
    public String getAdress() {
      return adress;
    }

    /**
     * 培训地点
     */
    public void setAdress(String adress) {
      this.adress = adress;
    }

    /**
     * 培训摘要
     */
    public String getContent() {
      return content;
    }

    /**
     * 培训摘要
     */
    public void setContent(String content) {
      this.content = content;
    }

    /**
     * 培训主持人
     */
    public String getZhuchiren() {
      return zhuchiren;
    }

    /**
     * 培训主持人
     */
    public void setZhuchiren(String zhuchiren) {
      this.zhuchiren = zhuchiren;
    }

    /**
     * 培训参加人
     */
    public String getChuxiren() {
      return chuxiren;
    }

    /**
     * 培训参加人
     */
    public void setChuxiren(String chuxiren) {
      this.chuxiren = chuxiren;
    }

    /**
     * 培训记录人
     */
    public String getJiluren() {
      return jiluren;
    }

    /**
     * 培训记录人
     */
    public void setJiluren(String jiluren) {
      this.jiluren = jiluren;
    }

	public String getXmBh() {
		return xmBh;
	}

	public void setXmBh(String xmBh) {
		this.xmBh = xmBh;
	}

	public String getXmName() {
		return xmName;
	}

	public void setXmName(String xmName) {
		this.xmName = xmName;
	}
    
    

}