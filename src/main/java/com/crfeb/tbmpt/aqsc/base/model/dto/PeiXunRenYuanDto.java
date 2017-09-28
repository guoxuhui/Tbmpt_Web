package com.crfeb.tbmpt.aqsc.base.model.dto;

import com.crfeb.tbmpt.commons.model.BaseModel;
import java.io.Serializable;

public class PeiXunRenYuanDto extends BaseModel implements Serializable {

    private static final long serialVersionUID = 1L;

    private String id;

    /**
     * 姓名<br>
     * <font color="ff0000">必填项</font><br>
     */
    private String name;

    /**
     * 工种<br>
     */
    private String gongzhong;

    /**
     * 性别<br>
     */
    private String sex;

    /**
     * 身份证号<br>
     * <font color="ff0000">必填项</font><br>
     */
    private String cardNo;

    /**
     * 家庭住址<br>
     */
    private String adress;

    /**
     * 年龄<br>
     */
    private String age;

    /**
     * 联系电话<br>
     */
    private String phone;

    /**
     * 入职日期<br>
     */
    private String inDate;
    
    /**
     * 查询起始日期
     */
    private String queryStarDate;
    
    /**
     * 查询截止日期
     */
    private String queryEndDate;

    /**
     * 离职日期<br>
     */
    private String outDate;

    /**
     * 培训时间<br>
     */
    private String peixunTime;

    /**
     * 状态<br>
     */
    private String state;

    /**
     * 单位名称<br>
     */
    private String demptName;

    /**
     * 备注<br>
     */
    private String remark;
    /**
     * 生日<br>
     */
    private String birthDay;
    
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
     * 姓名
     */
    public String getName() {
      return name;
    }

    /**
     * 姓名
     */
    public void setName(String name) {
      this.name = name;
    }

    /**
     * 工种
     */
    public String getGongzhong() {
      return gongzhong;
    }

    /**
     * 工种
     */
    public void setGongzhong(String gongzhong) {
      this.gongzhong = gongzhong;
    }

    /**
     * 性别
     */
    public String getSex() {
      return sex;
    }

    /**
     * 性别
     */
    public void setSex(String sex) {
      this.sex = sex;
    }

    /**
     * 身份证号
     */
    public String getCardNo() {
      return cardNo;
    }

    /**
     * 身份证号
     */
    public void setCardNo(String cardNo) {
      this.cardNo = cardNo;
    }

    /**
     * 家庭住址
     */
    public String getAdress() {
      return adress;
    }

    /**
     * 家庭住址
     */
    public void setAdress(String adress) {
      this.adress = adress;
    }

    /**
     * 年龄
     */
    public String getAge() {
      return age;
    }

    /**
     * 年龄
     */
    public void setAge(String age) {
      this.age = age;
    }

    /**
     * 联系电话
     */
    public String getPhone() {
      return phone;
    }

    /**
     * 联系电话
     */
    public void setPhone(String phone) {
      this.phone = phone;
    }

    /**
     * 入职日期
     */
    public String getInDate() {
      return inDate;
    }

    /**
     * 入职日期
     */
    public void setInDate(String inDate) {
      this.inDate = inDate;
    }

    /**
     * 离职日期
     */
    public String getOutDate() {
      return outDate;
    }

    /**
     * 离职日期
     */
    public void setOutDate(String outDate) {
      this.outDate = outDate;
    }

    /**
     * 培训时间
     */
    public String getPeixunTime() {
      return peixunTime;
    }

    /**
     * 培训时间
     */
    public void setPeixunTime(String peixunTime) {
      this.peixunTime = peixunTime;
    }

    /**
     * 状态
     */
    public String getState() {
      return state;
    }

    /**
     * 状态
     */
    public void setState(String state) {
      this.state = state;
    }

    /**
     * 单位名称
     */
    public String getDemptName() {
      return demptName;
    }

    /**
     * 单位名称
     */
    public void setDemptName(String demptName) {
      this.demptName = demptName;
    }

    /**
     * 备注
     */
    public String getRemark() {
      return remark;
    }

    /**
     * 备注
     */
    public void setRemark(String remark) {
      this.remark = remark;
    }

	public String getBirthDay() {
		return birthDay;
	}

	public void setBirthDay(String birthDay) {
		this.birthDay = birthDay;
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

	public String getQueryStarDate() {
		return queryStarDate;
	}

	public void setQueryStarDate(String queryStarDate) {
		this.queryStarDate = queryStarDate;
	}

	public String getQueryEndDate() {
		return queryEndDate;
	}

	public void setQueryEndDate(String queryEndDate) {
		this.queryEndDate = queryEndDate;
	}
    
	
}