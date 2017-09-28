package com.crfeb.tbmpt.dgjj.model.dto;


import com.baomidou.mybatisplus.annotations.TableField;
import com.crfeb.tbmpt.commons.model.BaseModel;
import java.io.Serializable; 
/**
 * <p>日掘进信息管理 列表信息  工具类</p>
 * <p>日期：2017-01-09</p>
 * @version 1.0
 * @author wpg
 */
public class DgjjRjjInfoDto extends BaseModel implements Serializable {
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Id
	 */
    private String id;

    /**
	 * 用于表单双击修改 ，传到后台判断是否被修改过；默认值：value='modified'，当前页面不用！
	 */
	private String modified;
	
	/**
	 *  作：保存“子表集合”数据；表单发生提交事件时赋值，
	 */
	private String details;
	public String getModified() {
		return modified;
	}

	public void setModified(String modified) {
		this.modified = modified;
	}

	public String getDetails() {
		return details;
	}

	public void setDetails(String details) {
		this.details = details;
	}

	/**
     * 工程（项目）编号<br>
     */
    private String gcBh;

    /**
     * 区间编号<br>
     */
    private String qlBh;

    /**
     * 线路编号<br>
     */
    private String xlBh;

	/**
     * 班组对象Id<br>
     */
	private String BId;
	
	/**
     * 班组名称<br>
     */
	private String bzname;
    public String getBzname() {
		return bzname;
	}

	public void setBzname(String bzname) {
		this.bzname = bzname;
	}
	/***
	 * 上班时间
	 */
	private String startTime;
	
	/***
	 * 下班时间
	 */
	private String endTime;
	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	/**
     * 施工环号<br>
     */
	private Integer sghh;
    
	/**
	 * 管片变换关键里程
	 */
	private Double gpbhgjlc;
	
	/**
	 * 设计类型
	 */
	private String sjlx;
	
	/**
	 * 设计轴线
	 */
	private String sjzx;
	
	/**
	 * 施工类型
	 */
	private String sglx;
	
	/**
	 * 封顶块位置
	 */
	private Integer fdkwz;
	
	/**
	 * 注浆压力Mpa（0.23）
	 */
	private Double zjyl;
	
	/***
	 *  ===== 注浆  =======
	 */
	/**
	 * 注浆-同步注浆量
	 */
	private Integer tbzjl;
	
	/**
	 * 注浆-同步注浆位置  ，例：1/2/3/4#
	 */
	private String tbzjwz;
	
	/**
	 * 注浆-管片注浆位置
	 */
	private String gpzjwz;
	
	/**
	 * 注浆-后续注浆时间
	 */
	private String hxzjsj;
	
	/**
	 * 土压（bar)
	 */
	private Double ty;
	
	/**
	 * 出土量(方)例:52m³
	 */
	private String  ctl;
	
	/**
	 * 平均总推力(KN)
	 */
	private Double  pjztl;
	
	/**
	 * 平均扭矩（KN.m）
	 */
	private Double pjnj;
	
	/***
	 * === 隧道轴线 ===
	 */
	
	/**
	 * 隧道轴线-高程
	 */
	@TableField(value = "sdzx_gc")
	private Double sdzxgc;
	
	/**
	 * 隧道轴线-平面
	 */
	@TableField(value = "sdzx_pm")
	private Double sdzxpm;
	
	/**
	 * === 推进千斤顶（mm） ===
	 */
	/**
	 * 推进千斤顶-管片安装前
	 */
	private Integer gpazqA;
	
	private Integer gpazqB;
	
	private Integer gpazqC;
	
	private Integer gpazqD;
	
	/**
	 * 推进千斤顶-管片安装后
	 */
	private Integer gpazhA;
	
	private Integer gpazhB;
	
	private Integer gpazhC;
	
	private Integer gpazhD;
	
	
	/**
	 * === 盾构机轴线姿态 ===
	 */
	
	/**
	 * 盾构机轴线姿态-水平
	 */
	/**
	 * 水平-切口
	 */
	@TableField(value = "dgjzx_sp_qk")
	private Integer dgjzxspqk;
	
	/**
	 * 水平-盾尾
	 */
	@TableField(value = "dgjzx_sp_dw")
	private Integer dgjzxspdw;
	
	/**
	 * 盾构机轴线姿态-垂直
	 */
	/**
	 * 垂直-切口
	 */
	@TableField(value = "dgjzx_cz_qk")
	private Integer dgjzxczqk;
	
	/**
	 * 垂直-盾尾
	 */
	@TableField(value = "gjzx_cz_dw")
	private Integer dgjzxczdw;
	
	/**
	 * === 盾尾间隙（mm）===
	 */
	/**
	 * 盾尾间隙（mm）-拼装前
	 */
	/**
	 * 拼装前-上
	 */
	@TableField(value = "dwjx_pzq_s")
	private Integer dwjxpzqs;
	/**
	 * 拼装前-下
	 */
	@TableField(value = "dwjx_pzq_x")
	private Integer dwjxpzqx;
	/**
	 * 拼装前-左
	 */
	@TableField(value = "dwjx_pzq_z")
	private Integer dwjxpzqz;
	/**
	 * 拼装前-右
	 */
	@TableField(value = "dwjx_pzq_y")
	private Integer dwjxpzqy;
	
	/**
	 * 盾尾间隙（mm）-拼装后-上-下-左-右
	 */
	/**
	 * 拼装后-上
	 */
	@TableField(value = "dwjx_pzh_s")
	private Integer dwjxpzhs;
	/**
	 * 拼装后-下
	 */
	@TableField(value = "dwjx_pzh_x")
	private Integer dwjxpzhx;
	/**
	 * 拼装后-左
	 */
	@TableField(value = "dwjx_pzh_z")
	private Integer dwjxpzhz;
	/**
	 * 拼装后-右
	 */
	@TableField(value = "dwjx_pzh_y")
	private Integer dwjxpzhy;
	
	
	/**
	 * === 管片姿态（mm）===
	 */
	
	/**
	 * 管片姿态（mm）-拼装前
	 */
	/**
	 * 拼装前-高程
	 */
	@TableField(value = "gpzt_pzq_gc")
	private Integer gpztpzqgc;
	
	/**
	 * 拼装前-平面
	 */
	@TableField(value = "gpzt_pzq_pm")
	private Integer gpztpzqpm;
	
	/**
	 * 管片姿态（mm）-拼装后
	 */
	/**
	 * 拼装后-高程
	 */
	@TableField(value = "gpzt_pzh_gc")
	private Integer gpztpzhgc;
	
	/**
	 * 拼装后-平面
	 */
	@TableField(value = "gpzt_pzh_pm")
	private Integer gpztpzhpm;
	
	/**
	 * 盾构掘进完成起止时间 ,例：16:15~17:05
	 */
	@TableField(value = "dgjjwc_qzsj")
	private String dgjjwcqzsj;
	
	/**
	 * 施工日期，例：2016.08.03
	 */
	@TableField(value = "sg_rq")
	private String sgrq;
	
	/**
	 * 备注
	 */
	private String remarks;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}


    public String getGcBh() {
		return gcBh;
	}

	public void setGcBh(String gcBh) {
		this.gcBh = gcBh;
	}

	public String getQlBh() {
		return qlBh;
	}

	public void setQlBh(String qlBh) {
		this.qlBh = qlBh;
	}

	public String getXlBh() {
		return xlBh;
	}

	public void setXlBh(String xlBh) {
		this.xlBh = xlBh;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getBId() {
		return BId;
	}

	public void setBId(String bId) {
		BId = bId;
	}

	
	public Integer getSghh() {
		return sghh;
	}

	public void setSghh(Integer sghh) {
		this.sghh = sghh;
	}

	public Double getGpbhgjlc() {
		return gpbhgjlc;
	}

	public void setGpbhgjlc(Double gpbhgjlc) {
		this.gpbhgjlc = gpbhgjlc;
	}

	public String getSjlx() {
		return sjlx;
	}

	public void setSjlx(String sjlx) {
		this.sjlx = sjlx;
	}

	public String getSjzx() {
		return sjzx;
	}

	public void setSjzx(String sjzx) {
		this.sjzx = sjzx;
	}

	public String getSglx() {
		return sglx;
	}

	public void setSglx(String sglx) {
		this.sglx = sglx;
	}

	public Integer getFdkwz() {
		return fdkwz;
	}

	public void setFdkwz(Integer fdkwz) {
		this.fdkwz = fdkwz;
	}

	public Double getZjyl() {
		return zjyl;
	}

	public void setZjyl(Double zjyl) {
		this.zjyl = zjyl;
	}

	public Integer getTbzjl() {
		return tbzjl;
	}

	public void setTbzjl(Integer tbzjl) {
		this.tbzjl = tbzjl;
	}

	public String getTbzjwz() {
		return tbzjwz;
	}

	public void setTbzjwz(String tbzjwz) {
		this.tbzjwz = tbzjwz;
	}

	public String getGpzjwz() {
		return gpzjwz;
	}

	public void setGpzjwz(String gpzjwz) {
		this.gpzjwz = gpzjwz;
	}

	public String getHxzjsj() {
		return hxzjsj;
	}

	public void setHxzjsj(String hxzjsj) {
		this.hxzjsj = hxzjsj;
	}

	public Double getTy() {
		return ty;
	}

	public void setTy(Double ty) {
		this.ty = ty;
	}

	public String getCtl() {
		return ctl;
	}

	public void setCtl(String ctl) {
		this.ctl = ctl;
	}

	public Double getPjztl() {
		return pjztl;
	}

	public void setPjztl(Double pjztl) {
		this.pjztl = pjztl;
	}

	public Double getPjnj() {
		return pjnj;
	}

	public void setPjnj(Double pjnj) {
		this.pjnj = pjnj;
	}

	public Double getSdzxgc() {
		return sdzxgc;
	}

	public void setSdzxgc(Double sdzxgc) {
		this.sdzxgc = sdzxgc;
	}

	public Double getSdzxpm() {
		return sdzxpm;
	}

	public void setSdzxpm(Double sdzxpm) {
		this.sdzxpm = sdzxpm;
	}

	public Integer getGpazqA() {
		return gpazqA;
	}

	public void setGpazqA(Integer gpazqA) {
		this.gpazqA = gpazqA;
	}

	public Integer getGpazqB() {
		return gpazqB;
	}

	public void setGpazqB(Integer gpazqB) {
		this.gpazqB = gpazqB;
	}

	public Integer getGpazqC() {
		return gpazqC;
	}

	public void setGpazqC(Integer gpazqC) {
		this.gpazqC = gpazqC;
	}

	public Integer getGpazqD() {
		return gpazqD;
	}

	public void setGpazqD(Integer gpazqD) {
		this.gpazqD = gpazqD;
	}

	public Integer getGpazhA() {
		return gpazhA;
	}

	public void setGpazhA(Integer gpazhA) {
		this.gpazhA = gpazhA;
	}

	public Integer getGpazhB() {
		return gpazhB;
	}

	public void setGpazhB(Integer gpazhB) {
		this.gpazhB = gpazhB;
	}

	public Integer getGpazhC() {
		return gpazhC;
	}

	public void setGpazhC(Integer gpazhC) {
		this.gpazhC = gpazhC;
	}

	public Integer getGpazhD() {
		return gpazhD;
	}

	public void setGpazhD(Integer gpazhD) {
		this.gpazhD = gpazhD;
	}

	public Integer getDgjzxspqk() {
		return dgjzxspqk;
	}

	public void setDgjzxspqk(Integer dgjzxspqk) {
		this.dgjzxspqk = dgjzxspqk;
	}

	public Integer getDgjzxspdw() {
		return dgjzxspdw;
	}

	public void setDgjzxspdw(Integer dgjzxspdw) {
		this.dgjzxspdw = dgjzxspdw;
	}

	public Integer getDgjzxczqk() {
		return dgjzxczqk;
	}

	public void setDgjzxczqk(Integer dgjzxczqk) {
		this.dgjzxczqk = dgjzxczqk;
	}

	public Integer getDgjzxczdw() {
		return dgjzxczdw;
	}

	public void setDgjzxczdw(Integer dgjzxczdw) {
		this.dgjzxczdw = dgjzxczdw;
	}

	public Integer getDwjxpzqs() {
		return dwjxpzqs;
	}

	public void setDwjxpzqs(Integer dwjxpzqs) {
		this.dwjxpzqs = dwjxpzqs;
	}

	public Integer getDwjxpzqx() {
		return dwjxpzqx;
	}

	public void setDwjxpzqx(Integer dwjxpzqx) {
		this.dwjxpzqx = dwjxpzqx;
	}

	public Integer getDwjxpzqz() {
		return dwjxpzqz;
	}

	public void setDwjxpzqz(Integer dwjxpzqz) {
		this.dwjxpzqz = dwjxpzqz;
	}

	public Integer getDwjxpzqy() {
		return dwjxpzqy;
	}

	public void setDwjxpzqy(Integer dwjxpzqy) {
		this.dwjxpzqy = dwjxpzqy;
	}

	public Integer getDwjxpzhs() {
		return dwjxpzhs;
	}

	public void setDwjxpzhs(Integer dwjxpzhs) {
		this.dwjxpzhs = dwjxpzhs;
	}

	public Integer getDwjxpzhx() {
		return dwjxpzhx;
	}

	public void setDwjxpzhx(Integer dwjxpzhx) {
		this.dwjxpzhx = dwjxpzhx;
	}

	public Integer getDwjxpzhz() {
		return dwjxpzhz;
	}

	public void setDwjxpzhz(Integer dwjxpzhz) {
		this.dwjxpzhz = dwjxpzhz;
	}

	public Integer getDwjxpzhy() {
		return dwjxpzhy;
	}

	public void setDwjxpzhy(Integer dwjxpzhy) {
		this.dwjxpzhy = dwjxpzhy;
	}

	public Integer getGpztpzqgc() {
		return gpztpzqgc;
	}

	public void setGpztpzqgc(Integer gpztpzqgc) {
		this.gpztpzqgc = gpztpzqgc;
	}

	public Integer getGpztpzqpm() {
		return gpztpzqpm;
	}

	public void setGpztpzqpm(Integer gpztpzqpm) {
		this.gpztpzqpm = gpztpzqpm;
	}

	public Integer getGpztpzhgc() {
		return gpztpzhgc;
	}

	public void setGpztpzhgc(Integer gpztpzhgc) {
		this.gpztpzhgc = gpztpzhgc;
	}

	public Integer getGpztpzhpm() {
		return gpztpzhpm;
	}

	public void setGpztpzhpm(Integer gpztpzhpm) {
		this.gpztpzhpm = gpztpzhpm;
	}

	public String getDgjjwcqzsj() {
		return dgjjwcqzsj;
	}

	public void setDgjjwcqzsj(String dgjjwcqzsj) {
		this.dgjjwcqzsj = dgjjwcqzsj;
	}

	public String getSgrq() {
		return sgrq;
	}

	public void setSgrq(String sgrq) {
		// 把传进了的 sgrq 中的 点“.”替换成 杠“-” 
		this.sgrq = sgrq.replace(".", "-") 
				.replace("/", "-")
				.replace("、", "-")
				.replace(":", "-")
				.replace("：", "-")
				.replace("年", "-")
				.replace("月", "-")
				.replace("日", "-"); 
		
		 
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	
	
    
}