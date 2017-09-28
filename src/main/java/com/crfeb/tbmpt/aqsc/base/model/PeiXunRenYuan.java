package com.crfeb.tbmpt.aqsc.base.model;

import java.io.Serializable;
import com.baomidou.mybatisplus.annotations.IdType;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.crfeb.tbmpt.commons.model.BaseModel;
import com.baomidou.mybatisplus.annotations.TableName;

/**
 * <p>培训人员管理实体信息</p>
 * <p>描述：实体描述信息.</p>
 * <p>系统：安全生产管理</p>
 * <p>模块：基础信息</p>
 * <p>日期：2017-02-20</p>
 * @version 1.0
 * @author wangbinbin
 */
@TableName("AQSC_PEIXUNRENYUAN")
public class PeiXunRenYuan extends BaseModel implements Serializable {

    @TableId(type = IdType.UUID)
    private String id;

    /**
     * 姓名<br>
     * <font color="ff0000">必填项</font><br>
     */
    @TableField(value = "name")
    private String name;

    /**
     * 工种<br>
     */
    @TableField(value = "gongzhong")
    private String gongzhong;

    /**
     * 性别<br>
     */
    @TableField(value = "sex")
    private String sex;

    /**
     * 身份证号<br>
     * <font color="ff0000">必填项</font><br>
     */
    @TableField(value = "cardNo")
    private String cardNo;

    /**
     * 家庭住址<br>
     */
    @TableField(value = "adress")
    private String adress;

    /**
     * 年龄<br>
     */
    @TableField(value = "age")
    private String age;

    /**
     * 联系电话<br>
     */
    @TableField(value = "phone")
    private String phone;

    /**
     * 入职日期<br>
     */
    @TableField(value = "inDate")
    private String inDate;

    /**
     * 离职日期<br>
     */
    @TableField(value = "outDate")
    private String outDate;

    /**
     * 培训时间<br>
     */
    @TableField(value = "peixunTime")
    private String peixunTime;

    /**
     * 状态<br>
     */
    @TableField(value = "state")
    private String state;

    /**
     * 单位名称<br>
     */
    @TableField(value = "demptName")
    private String demptName;

    /**
     * 备注<br>
     */
    @TableField(value = "remark")
    private String remark;
    
    /**
     * 生日<br>
     */
    @TableField(value = "birthDay")
    private String birthDay;
    
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
    

}