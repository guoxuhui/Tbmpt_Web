package com.crfeb.tbmpt.aqsc.base.model.dto;

import com.baomidou.mybatisplus.annotations.TableField;
import com.crfeb.tbmpt.commons.model.BaseModel;
import java.io.Serializable;

public class HuiyiJiluDto extends BaseModel implements Serializable {

    private static final long serialVersionUID = 1L;

    private String id;

    /**
     * 培训日期<br>
     * <font color="ff0000">必填项</font><br>
     */
    private String hydate;

    /**
     * 培训名称<br>
     * <font color="ff0000">必填项</font><br>
     */
    private String name;

    /**
     * 培训地点<br>
     * <font color="ff0000">必填项</font><br>
     */
    private String adress;

    /**
     * 培训摘要<br>
     */
    private String content;

    /**
     * 培训主持人<br>
     */
    private String zhuchiren;

    /**
     * 培训参加人<br>
     */
    private String chuxiren;

    /**
     * 培训记录人<br>
     */
    private String jiluren;
    
    /**
     * 查询起始日期
     */
    private String starDay;
    
    /**
     * 查询截止日期
     */
    private String endDay;
    
    /**
     * 项目编号
     */
    private String xmBh;
    
    /**
     * 项目名称
     */
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

	public String getStarDay() {
		return starDay;
	}

	public void setStarDay(String starDay) {
		this.starDay = starDay;
	}

	public String getEndDay() {
		return endDay;
	}

	public void setEndDay(String endDay) {
		this.endDay = endDay;
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