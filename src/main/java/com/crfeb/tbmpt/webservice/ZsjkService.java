package com.crfeb.tbmpt.webservice;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.crfeb.tbmpt.commons.result.Request;
import com.crfeb.tbmpt.commons.result.Result;
import com.crfeb.tbmpt.commons.scan.SpringUtils;
import com.crfeb.tbmpt.zsjk.jt.model.dto.ZsJkJtAqZlMxXxDto;
import com.crfeb.tbmpt.zsjk.jt.model.dto.ZsJkJtAqZlXxDto;
import com.crfeb.tbmpt.zsjk.jt.model.dto.ZsJkJtDjXhZTrXxDto;
import com.crfeb.tbmpt.zsjk.jt.model.dto.ZsJkJtLjCzXxDto;
import com.crfeb.tbmpt.zsjk.jt.model.dto.ZsJkJtSgJdXxDto;
import com.crfeb.tbmpt.zsjk.jt.model.dto.ZsJkJtXmxxDto;
import com.crfeb.tbmpt.zsjk.jt.model.dto.ZsJkJtZyClXhZTrXxDto;
import com.crfeb.tbmpt.zsjk.jt.model.dto.ZsJkJtdgSgRyTrXxDto;
import com.crfeb.tbmpt.zsjk.jt.service.ZsJkJtAqZlMxXxService;
import com.crfeb.tbmpt.zsjk.jt.service.ZsJkJtAqZlXxService;
import com.crfeb.tbmpt.zsjk.jt.service.ZsJkJtDjXhZTrXxService;
import com.crfeb.tbmpt.zsjk.jt.service.ZsJkJtLjCzXxService;
import com.crfeb.tbmpt.zsjk.jt.service.ZsJkJtSgJdXxService;
import com.crfeb.tbmpt.zsjk.jt.service.ZsJkJtXmxxService;
import com.crfeb.tbmpt.zsjk.jt.service.ZsJkJtZyClXhZTrXxService;
import com.crfeb.tbmpt.zsjk.jt.service.ZsJkJtZySbTrXxService;
import com.crfeb.tbmpt.zsjk.jt.service.ZsJkJtdgSgRyTrXxService;
import com.crfeb.tbmpt.zsjk.xmb.model.dto.ZsJkXmbClYdXhXxDto;
import com.crfeb.tbmpt.zsjk.xmb.model.dto.ZsJkXmbDjLxXxDto;
import com.crfeb.tbmpt.zsjk.xmb.model.dto.ZsJkXmbDjXhFxNrXxDto;
import com.crfeb.tbmpt.zsjk.xmb.model.dto.ZsJkXmbDjXhZTrXxDto;
import com.crfeb.tbmpt.zsjk.xmb.model.dto.ZsJkXmbDmCjJcXxDto;
import com.crfeb.tbmpt.zsjk.xmb.model.dto.ZsJkXmbGpZlMxXxDto;
import com.crfeb.tbmpt.zsjk.xmb.model.dto.ZsJkXmbGpZlWtsXxDto;
import com.crfeb.tbmpt.zsjk.xmb.model.dto.ZsJkXmbGpZtClXxDto;
import com.crfeb.tbmpt.zsjk.xmb.model.dto.ZsJkXmbRyTrXxDto;
import com.crfeb.tbmpt.zsjk.xmb.model.dto.ZsJkXmbSbTrXxDto;
import com.crfeb.tbmpt.zsjk.xmb.model.dto.ZsJkXmbSbWbXxDto;
import com.crfeb.tbmpt.zsjk.xmb.model.dto.ZsJkXmbSgZlMxXxDto;
import com.crfeb.tbmpt.zsjk.xmb.model.dto.ZsJkXmbSgZlXxDto;
import com.crfeb.tbmpt.zsjk.xmb.model.dto.ZsJkXmbXlJdXxDto;
import com.crfeb.tbmpt.zsjk.xmb.model.dto.ZsJkXmbXmLjCzXxDto;
import com.crfeb.tbmpt.zsjk.xmb.model.dto.ZsJkXmbXmXxDto;
import com.crfeb.tbmpt.zsjk.xmb.service.ZsJkXmbClYdXhXxService;
import com.crfeb.tbmpt.zsjk.xmb.service.ZsJkXmbDjLxXxService;
import com.crfeb.tbmpt.zsjk.xmb.service.ZsJkXmbDjXhFxNrXxService;
import com.crfeb.tbmpt.zsjk.xmb.service.ZsJkXmbDjXhZTrDataXxService;
import com.crfeb.tbmpt.zsjk.xmb.service.ZsJkXmbDjXhZTrXxService;
import com.crfeb.tbmpt.zsjk.xmb.service.ZsJkXmbDmCjJcXxService;
import com.crfeb.tbmpt.zsjk.xmb.service.ZsJkXmbGpZlMxXxService;
import com.crfeb.tbmpt.zsjk.xmb.service.ZsJkXmbGpZlWtsXxService;
import com.crfeb.tbmpt.zsjk.xmb.service.ZsJkXmbGpZtClXxService;
import com.crfeb.tbmpt.zsjk.xmb.service.ZsJkXmbRyTrXxService;
import com.crfeb.tbmpt.zsjk.xmb.service.ZsJkXmbSbTrXxService;
import com.crfeb.tbmpt.zsjk.xmb.service.ZsJkXmbSbWbXxService;
import com.crfeb.tbmpt.zsjk.xmb.service.ZsJkXmbSgZlMxXxService;
import com.crfeb.tbmpt.zsjk.xmb.service.ZsJkXmbSgZlXxService;
import com.crfeb.tbmpt.zsjk.xmb.service.ZsJkXmbXlJdXxService;
import com.crfeb.tbmpt.zsjk.xmb.service.ZsJkXmbXmLjCzMxXxService;
import com.crfeb.tbmpt.zsjk.xmb.service.ZsJkXmbXmLjCzXxService;
import com.crfeb.tbmpt.zsjk.xmb.service.ZsJkXmbXmXlXxService;
import com.crfeb.tbmpt.zsjk.xmb.service.ZsJkXmbXmXxService;

public class ZsjkService extends BaseService{
	
	private ZsJkJtXmxxService zsJkJtXmxxService;
	
	private ZsJkJtLjCzXxService zsJkJtLjCzXxService;
	
    private ZsJkJtAqZlMxXxService zsJkJtAqZlMxXxService;
	
	private ZsJkJtAqZlXxService zsJkJtAqZlXxService;
	
    private ZsJkJtdgSgRyTrXxService zsJkJtdgSgRyTrXxService;
	
	private ZsJkJtDjXhZTrXxService zsJkJtDjXhZTrXxService;
	
    private ZsJkJtSgJdXxService zsJkJtSgJdXxService;
	
	private ZsJkJtZySbTrXxService zsJkJtZySbTrXxService;
	
	private ZsJkJtZyClXhZTrXxService zsJkJtZyClXhZTrXxService;
	
    private ZsJkXmbClYdXhXxService zsJkXmbClYdXhXxService;
	
	private ZsJkXmbDjLxXxService zsJkXmbDjLxXxService;
	
    private ZsJkXmbDjXhFxNrXxService zsJkXmbDjXhFxNrXxService;
	
	private ZsJkXmbDjXhZTrDataXxService zsJkXmbDjXhZTrDataXxService;
	
    private ZsJkXmbDjXhZTrXxService zsJkXmbDjXhZTrXxService;
	
	private ZsJkXmbDmCjJcXxService zsJkXmbDmCjJcXxService;
	
    private ZsJkXmbGpZtClXxService zsJkXmbGpZtClXxService;
	
	private ZsJkXmbGpZlWtsXxService zsJkXmbGpZlWtsXxService;
	
	private ZsJkXmbGpZlMxXxService zsJkXmbGpZlMxXxService;
	
    private ZsJkXmbRyTrXxService zsJkXmbRyTrXxService;
	
	private ZsJkXmbSbTrXxService zsJkXmbSbTrXxService;
	
    private ZsJkXmbSbWbXxService zsJkXmbSbWbXxService;
	
	private ZsJkXmbSgZlMxXxService zsJkXmbSgZlMxXxService;
	
    private ZsJkXmbSgZlXxService zsJkXmbSgZlXxService;
	
	private ZsJkXmbXlJdXxService zsJkXmbXlJdXxService;
	
    private ZsJkXmbXmLjCzMxXxService zsJkXmbXmLjCzMxXxService;
	
	private ZsJkXmbXmLjCzXxService zsJkXmbXmLjCzXxService;
	
	private ZsJkXmbXmXlXxService zsJkXmbXmXlXxService;
	
	private ZsJkXmbXmXxService zsJkXmbXmXxService;
	
	
	
	/**
	 * 获取项目信息列表
	 * @return
	 */
	public String getXMXXList(String parameter){
		boolean audit = this.audit(parameter);
		if(!audit)return erroMessage();
		Result rs = new Result();
		zsJkJtXmxxService = SpringUtils.getBean(ZsJkJtXmxxService.class);
		List<ZsJkJtXmxxDto> list = new ArrayList<ZsJkJtXmxxDto>();
		try {
			list = zsJkJtXmxxService.getXMXXList();
			rs.setObj(list);
			rs.setSuccess(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return toJsonString(rs);
	}
	/**
	 * 获取所有项目总累计产值
	 * @return
	 */
	public String getAllXmljcz(String parameter){
		boolean audit = this.audit(parameter);
		if(!audit)return erroMessage();
		Result rs = new Result();
		JSONObject obj = JSONObject.parseObject(parameter);
		Request rq =  obj.toJavaObject(obj, Request.class);
		Map<String,String> parameters = (Map<String, String>) rq.getArg();
		String strTime = parameters.get("strTime");
		String endTime = parameters.get("endTime");
		zsJkJtLjCzXxService = SpringUtils.getBean(ZsJkJtLjCzXxService.class);
		try {
			ZsJkJtLjCzXxDto dataDto = zsJkJtLjCzXxService.getAllXmljcz(strTime, endTime);
			rs.setObj(dataDto);
			rs.setSuccess(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return toJsonString(rs);
	}
	
	/**
	 * 获取所有项目的进度
	 * @param year 不传默认当年
	 * @return
	 */
	public String getAllXMjd(String parameter){
		boolean audit = this.audit(parameter);
		if(!audit)return erroMessage();
		Result rs = new Result();
		JSONObject obj = JSONObject.parseObject(parameter);
		Request rq =  obj.toJavaObject(obj, Request.class);
		Map<String,String> parameters = (Map<String, String>) rq.getArg();
		String year = parameters.get("year");
		zsJkJtSgJdXxService = SpringUtils.getBean(ZsJkJtSgJdXxService.class);
		try {
			ZsJkJtSgJdXxDto resultData = this.zsJkJtSgJdXxService.getAllXMjd(year);
			rs.setObj(resultData);
			rs.setSuccess(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return toJsonString(rs);
	}
	
	/**
	 * 获取安全质量信息
	 * @param strTime 起始时间
	 * @param endTime 截止时间
	 * @return
	 */
	public String getAQZL(String parameter){
		boolean audit = this.audit(parameter);
		if(!audit)return erroMessage();
		Result rs = new Result();
		JSONObject obj = JSONObject.parseObject(parameter);
		Request rq =  obj.toJavaObject(obj, Request.class);
		Map<String,String> parameters = (Map<String, String>) rq.getArg();
		String strTime = parameters.get("strTime");
		String endTime = parameters.get("endTime");
		zsJkJtAqZlXxService = SpringUtils.getBean(ZsJkJtAqZlXxService.class);
		try {
			List<ZsJkJtAqZlXxDto> resultData = this.zsJkJtAqZlXxService.getAQZL(strTime, endTime);
			rs.setObj(resultData);
			rs.setSuccess(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return toJsonString(rs);
	}
	/**
	 * 获取安全、质量、隐患各自的明细数据
	 * @param strTime 起始时间
	 * @param endTime 截止时间
	 * @param xxfl 信息分类（安全、质量、隐患）
	 * @return
	 */
	public String getAQZLMX(String parameter){
		boolean audit = this.audit(parameter);
		if(!audit)return erroMessage();
		Result rs = new Result();
		JSONObject obj = JSONObject.parseObject(parameter);
		Request rq =  obj.toJavaObject(obj, Request.class);
		Map<String,String> parameters = (Map<String, String>) rq.getArg();
		String strTime = parameters.get("strTime");
		String endTime = parameters.get("endTime");
		String xxfl = parameters.get("xxfl");
		zsJkJtAqZlMxXxService = SpringUtils.getBean(ZsJkJtAqZlMxXxService.class);
		try {
			List<ZsJkJtAqZlMxXxDto> resultData = this.zsJkJtAqZlMxXxService.getAQZLMX(strTime, endTime, xxfl);
			rs.setObj(resultData);
			rs.setSuccess(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return toJsonString(rs);
	}
	/**
	 * 获取本年度所有项目的材料消耗总投入
	 * @param nd 年度（不传默认当年）
	 * @return 材料消耗计划投入值、材料消耗实际投入值
	 */
	public String getclxhztr(String parameter){
		boolean audit = this.audit(parameter);
		if(!audit)return erroMessage();
		Result rs = new Result();
		JSONObject obj = JSONObject.parseObject(parameter);
		Request rq =  obj.toJavaObject(obj, Request.class);
		Map<String,String> parameters = (Map<String, String>) rq.getArg();
		String nd = parameters.get("nd");
		zsJkJtZyClXhZTrXxService = SpringUtils.getBean(ZsJkJtZyClXhZTrXxService.class);
		try {
			ZsJkJtZyClXhZTrXxDto resultData = this.zsJkJtZyClXhZTrXxService.getclxhztr(nd);
			rs.setObj(resultData);
			rs.setSuccess(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return toJsonString(rs);
	}
	/**
	 * 获取本年度刀具的材料消耗总投入
	 * @param nd 年度（不传默认当年）
	 * @return 刀具消耗计划投入值、刀具消耗实际投入值
	 */
	public String djxhztr(String parameter){
		boolean audit = this.audit(parameter);
		if(!audit)return erroMessage();
		Result rs = new Result();
		JSONObject obj = JSONObject.parseObject(parameter);
		Request rq =  obj.toJavaObject(obj, Request.class);
		Map<String,String> parameters = (Map<String, String>) rq.getArg();
		String nd = parameters.get("nd");
		zsJkJtDjXhZTrXxService = SpringUtils.getBean(ZsJkJtDjXhZTrXxService.class);
		try {
			ZsJkJtDjXhZTrXxDto resultData = this.zsJkJtDjXhZTrXxService.djxhztr(nd);
			rs.setObj(resultData);
			rs.setSuccess(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return toJsonString(rs);
	}
	/**
	 * 获取所有项目的盾构施工人员投入
	 * @return 当前所有项目总数、当前盾构队总数、当前盾构队人员总数、平均每个盾构队人数
	 */
	public String getAllxmrytr(String parameter){
		boolean audit = this.audit(parameter);
		if(!audit)return erroMessage();
		Result rs = new Result();
		zsJkJtdgSgRyTrXxService = SpringUtils.getBean(ZsJkJtdgSgRyTrXxService.class);
		try {
			ZsJkJtdgSgRyTrXxDto resultData = this.zsJkJtdgSgRyTrXxService.getAllxmrytr();
			rs.setObj(resultData);
			rs.setSuccess(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return toJsonString(rs);
	}
	/**
	 * 获取盾构机、电瓶车、龙门吊等主要设备的运行与投入情况
	 * @return 盾构机数量、电瓶车数量、龙门吊（租入、租出、检修、运行、运输）（以分类进行分组，每组传5个状态值）
	 */
	public String getZysbtr(String parameter){
		boolean audit = this.audit(parameter);
		if(!audit)return erroMessage();
		Result rs = new Result();
		zsJkJtZySbTrXxService = SpringUtils.getBean(ZsJkJtZySbTrXxService.class);
		try {
			Map<String, HashMap<String, String>> resultData = this.zsJkJtZySbTrXxService.getZysbtr();
			rs.setObj(resultData);
			rs.setSuccess(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return toJsonString(rs);
	}
	
//////////  以下为项目部接口  //////////////////////////////////////////////////////////////////////////	
	
	/**
	 * 获取项目详细信息（导入2.0系统项目简介）
	 * @param xmId 项目ID
	 * @return
	 */
	public String getXMXX(String parameter){
		boolean audit = this.audit(parameter);
		if(!audit)return erroMessage();
		Result rs = new Result();
		JSONObject obj = JSONObject.parseObject(parameter);
		Request rq =  obj.toJavaObject(obj, Request.class);
		Map<String,String> parameters = (Map<String, String>) rq.getArg();
		String xmId = parameters.get("xmId");
		zsJkXmbXmXxService = SpringUtils.getBean(ZsJkXmbXmXxService.class);
		try {
			ZsJkXmbXmXxDto resultData = zsJkXmbXmXxService.getXMXX(xmId);
			rs.setObj(resultData);
			rs.setSuccess(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return toJsonString(rs);
	}
	/**
	 * 获取当前项目各线路的进度
	 * @param xmId
	 * @return 项目ID、项目名称、（线路ID、区间名称+线路名称）、线路总环数、已完成环数，总工期（天），已完成天数
	 */
	public String getXmxljd(String parameter){
		boolean audit = this.audit(parameter);
		if(!audit)return erroMessage();
		Result rs = new Result();
		JSONObject obj = JSONObject.parseObject(parameter);
		Request rq =  obj.toJavaObject(obj, Request.class);
		Map<String,String> parameters = (Map<String, String>) rq.getArg();
		String xmId = parameters.get("xmId");
		zsJkXmbXlJdXxService = SpringUtils.getBean(ZsJkXmbXlJdXxService.class);
		try {
			ZsJkXmbXlJdXxDto  resultData = zsJkXmbXlJdXxService.getXmxljd(xmId);
			rs.setObj(resultData);
			rs.setSuccess(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
				
		return toJsonString(rs);
	}
	/**
	 * 获取所在项目的材料月度总消耗
	 * @param xmId 项目ID
	 * @param strDate 超始日期
	 * @param endDate 截止日期
	 * @return
	 */
	public String getXmClydxh(String parameter){
		boolean audit = this.audit(parameter);
		if(!audit)return erroMessage();
		Result rs = new Result();
		JSONObject obj = JSONObject.parseObject(parameter);
		Request rq =  obj.toJavaObject(obj, Request.class);
		Map<String,String> parameters = (Map<String, String>) rq.getArg();
		String xmId = parameters.get("xmId");
		String strDate = parameters.get("strDate");
		String endDate = parameters.get("endDate");
		zsJkXmbClYdXhXxService = SpringUtils.getBean(ZsJkXmbClYdXhXxService.class);
		try {
			List<ZsJkXmbClYdXhXxDto>  resultData = zsJkXmbClYdXhXxService.getXmClydxh(xmId, strDate, endDate);
			rs.setObj(resultData);
			rs.setSuccess(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return toJsonString(rs);
	}
	
	/**
	 * 获取刀具分析内容
	 * @return
	 */
	public String getXmDjfxnr(String parameter){
		boolean audit = this.audit(parameter);
		if(!audit)return erroMessage();
		Result rs = new Result();
		zsJkXmbDjXhFxNrXxService = SpringUtils.getBean(ZsJkXmbDjXhFxNrXxService.class);
		try {
			List<ZsJkXmbDjXhFxNrXxDto> resultData = zsJkXmbDjXhFxNrXxService.getXmDjfxnr();
			rs.setObj(resultData);
			rs.setSuccess(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return toJsonString(rs);
	}
	
	/**
	 * 获取刀具类型
	 * @return
	 */
	public String getDjLx(String parameter){
		boolean audit = this.audit(parameter);
		if(!audit)return erroMessage();
		Result rs = new Result();
		zsJkXmbDjLxXxService = SpringUtils.getBean(ZsJkXmbDjLxXxService.class);
		try {
			List<ZsJkXmbDjLxXxDto> resultData = zsJkXmbDjLxXxService.getDjLx();
			rs.setObj(resultData);
			rs.setSuccess(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return toJsonString(rs);
	}
	
	/**
	 * 获取刀具的材料消耗
	 * @param xlId 线路ID
	 * @param fxnr 分析内容
	 * @param djlx 刀具类型
	 * @param qshh 起始环号
	 * @param jzhh 截止环号
	 * @return
	 */
	public String getXmDjxhztr(String parameter){
		boolean audit = this.audit(parameter);
		if(!audit)return erroMessage();
		Result rs = new Result();
		JSONObject obj = JSONObject.parseObject(parameter);
		Request rq =  obj.toJavaObject(obj, Request.class);
		Map<String,String> parameters = (Map<String, String>) rq.getArg();
		String xlId = parameters.get("xlId");
		String fxnr = parameters.get("fxnr");
		String djlx = parameters.get("djlx");
		String qshh = parameters.get("qshh");
		String jzhh = parameters.get("jzhh");
		zsJkXmbDjXhZTrXxService = SpringUtils.getBean(ZsJkXmbDjXhZTrXxService.class);
		try {
			ZsJkXmbDjXhZTrXxDto  resultData = zsJkXmbDjXhZTrXxService.getXmDjxhztr(xlId, fxnr, djlx, qshh, jzhh);
			rs.setObj(resultData);
			rs.setSuccess(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return toJsonString(rs);
	}
	
	
	/**
	 * 获得各质量分类（管片破损、管片错台、螺栓复紧、渗漏水等）下的质量问题数
	 * @param projectId 项目id
	 * @param startTime 开始时间
	 * @param endTime 截止日期
	 * @return
	 */
	public String gpzl(String parameter){
		boolean audit = this.audit(parameter);
		if(!audit)return erroMessage();
		Result rs = new Result();
		JSONObject obj = JSONObject.parseObject(parameter);
		Request rq =  obj.toJavaObject(obj, Request.class);
		Map<String,String> parameters = (Map<String, String>) rq.getArg();
		String projectId = parameters.get("projectId");
		String startTime = parameters.get("startTime");
		String endTime = parameters.get("endTime");
		zsJkXmbGpZlWtsXxService = SpringUtils.getBean(ZsJkXmbGpZlWtsXxService.class);
		try {
			List<ZsJkXmbGpZlWtsXxDto>  resultData = zsJkXmbGpZlWtsXxService.gpzl(projectId, startTime, endTime);
			rs.setObj(resultData);
			rs.setSuccess(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return toJsonString(rs);
	}
	
	/**
	 * 获取管片质量问题明细数据
	 * @param projectId 项目id
	 * @param date 日期
	 * @param qualityType 质量分类（管片破损、管片错台、螺栓复紧、渗漏水等）
	 * @return
	 */
	public String gpzlwtmxsj(String parameter){
		boolean audit = this.audit(parameter);
		if(!audit)return erroMessage();
		Result rs = new Result();
		JSONObject obj = JSONObject.parseObject(parameter);
		Request rq =  obj.toJavaObject(obj, Request.class);
		Map<String,String> parameters = (Map<String, String>) rq.getArg();
		String projectId = parameters.get("projectId");
		String date = parameters.get("date");
		String qualityType = parameters.get("qualityType");
		zsJkXmbGpZlMxXxService = SpringUtils.getBean(ZsJkXmbGpZlMxXxService.class);
		try {
			List<ZsJkXmbGpZlMxXxDto> resultData = zsJkXmbGpZlMxXxService.gpzlwtmxsj(projectId, date, qualityType);
			rs.setObj(resultData);
			rs.setSuccess(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return toJsonString(rs);
	}
	/**
	 * 获得各结构施工质量（使用部位、工程）下的质量问题数量
	 * @param startTime 起始时间
	 * @param endtTime 结束时间
	 * @param projectId 项目id
	 * @return
	 */
	public String sgzl(String parameter){
		boolean audit = this.audit(parameter);
		if(!audit)return erroMessage();
		Result rs = new Result();
		JSONObject obj = JSONObject.parseObject(parameter);
		Request rq =  obj.toJavaObject(obj, Request.class);
		Map<String,String> parameters = (Map<String, String>) rq.getArg();
		String startTime = parameters.get("startTime");
		String endtTime = parameters.get("endtTime");
		String projectId = parameters.get("projectId");
		zsJkXmbSgZlXxService = SpringUtils.getBean(ZsJkXmbSgZlXxService.class);
		try {
			List<ZsJkXmbSgZlXxDto> resultData = zsJkXmbSgZlXxService.sgzl(startTime, endtTime, projectId);
			rs.setObj(resultData);
			rs.setSuccess(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return toJsonString(rs);
	}
	
	/**
	 * 获取结构施工质量问题明细数据
	 * @param shigzlfl 结构施工质量分类
	 * @param date 日期
	 * @param projectId 项目Id
	 * @return
	 */
	public String sgzlmx(String parameter){
		boolean audit = this.audit(parameter);
		if(!audit)return erroMessage();
		Result rs = new Result();
		JSONObject obj = JSONObject.parseObject(parameter);
		Request rq =  obj.toJavaObject(obj, Request.class);
		Map<String,String> parameters = (Map<String, String>) rq.getArg();
		String shigzlfl = parameters.get("shigzlfl");
		String date = parameters.get("date");
		String projectId = parameters.get("projectId");
		zsJkXmbSgZlMxXxService = SpringUtils.getBean(ZsJkXmbSgZlMxXxService.class);
		try {
			List<ZsJkXmbSgZlMxXxDto> resultData = zsJkXmbSgZlMxXxService.sgzlmx(projectId, shigzlfl, date);
			rs.setObj(resultData);
			rs.setSuccess(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return toJsonString(rs);
	}
	
	/**
	 * 获取当前项目的累计产值
	 * @param projectId 项目id
	 * @return
	 */
	public String dqxmdljcz(String parameter){
		boolean audit = this.audit(parameter);
		if(!audit)return erroMessage();
		Result rs = new Result();
		JSONObject obj = JSONObject.parseObject(parameter);
		Request rq =  obj.toJavaObject(obj, Request.class);
		Map<String,String> parameters = (Map<String, String>) rq.getArg();
		String projectId = parameters.get("projectId");
		zsJkXmbXmLjCzXxService = SpringUtils.getBean(ZsJkXmbXmLjCzXxService.class);
		try {
			ZsJkXmbXmLjCzXxDto resultData = zsJkXmbXmLjCzXxService.dqxmdljcz(projectId);
			rs.setObj(resultData);
			rs.setSuccess(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
				
		return toJsonString(rs);
	}
	
	/**
	 * 获取地面沉降情况（默认取当前里程的前后50米）
	 * @param xlId 线路id
	 * @param startLic 起止里程
	 * @param dianbh 点位编号
	 * @param fazhi 阈值（大于等于）
	 * @return
	 */
	public String cjjc(String parameter){
		boolean audit = this.audit(parameter);
		if(!audit)return erroMessage();
		Result rs = new Result();
		JSONObject obj = JSONObject.parseObject(parameter);
		Request rq =  obj.toJavaObject(obj, Request.class);
		Map<String,String> parameters = (Map<String, String>) rq.getArg();
		String xlId = parameters.get("xlId");
		String startLic = parameters.get("startLic");
		String dianbh = parameters.get("dianbh");
		String fazhi = parameters.get("fazhi");
		zsJkXmbDmCjJcXxService = SpringUtils.getBean(ZsJkXmbDmCjJcXxService.class);
		try {
			List<ZsJkXmbDmCjJcXxDto> resultData = zsJkXmbDmCjJcXxService.cjjc(xlId, startLic, dianbh, fazhi);
			rs.setObj(resultData);
			rs.setSuccess(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return toJsonString(rs);
	}
	
	/**
	 * 获取项目管片的姿态测量信息
	 * @param ID 线路ID
	 * @param qshh 起始环号
	 * @param jzhh 截止环号
	 * @return
	 */
	public String gpztxx(String parameter){
		boolean audit = this.audit(parameter);
		if(!audit)return erroMessage();
		Result rs = new Result();
		JSONObject obj = JSONObject.parseObject(parameter);
		Request rq =  obj.toJavaObject(obj, Request.class);
		Map<String,String> parameters = (Map<String, String>) rq.getArg();
		String ID = parameters.get("ID");
		String qshh = parameters.get("qshh");
		String jzhh = parameters.get("jzhh");
		zsJkXmbGpZtClXxService = SpringUtils.getBean(ZsJkXmbGpZtClXxService.class);
		try {
			List<ZsJkXmbGpZtClXxDto> resultData = zsJkXmbGpZtClXxService.gpztxx(ID, qshh, jzhh);
			rs.setObj(resultData);
			rs.setSuccess(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return toJsonString(rs);
	}
	
	/**
	 * 获取本项目人员情况
	 * @param ID 项目ID
	 * @return
	 */
	public String xmrytr(String parameter){
		boolean audit = this.audit(parameter);
		if(!audit)return erroMessage();
		Result rs = new Result();
		JSONObject obj = JSONObject.parseObject(parameter);
		Request rq =  obj.toJavaObject(obj, Request.class);
		Map<String,String> parameters = (Map<String, String>) rq.getArg();
		String ID = parameters.get("ID");
		zsJkXmbRyTrXxService = SpringUtils.getBean(ZsJkXmbRyTrXxService.class);
		try {
			List<ZsJkXmbRyTrXxDto> resultData = zsJkXmbRyTrXxService.xmrytr(ID);
			rs.setObj(resultData);
			rs.setSuccess(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return toJsonString(rs);
	}
	
	/**
	 * 获取盾构机、电瓶车、龙门吊等主要设备的运行与投入情况，增加“维保记录”
	 * @param ID 项目ID
	 * @return
	 */
	public String xmsbtr(String parameter){
		boolean audit = this.audit(parameter);
		if(!audit)return erroMessage();
		Result rs = new Result();
		JSONObject obj = JSONObject.parseObject(parameter);
		Request rq =  obj.toJavaObject(obj, Request.class);
		Map<String,String> parameters = (Map<String, String>) rq.getArg();
		String ID = parameters.get("ID");
		zsJkXmbSbTrXxService = SpringUtils.getBean(ZsJkXmbSbTrXxService.class);
		try {
			List<ZsJkXmbSbTrXxDto> resultData = zsJkXmbSbTrXxService.xmsbtr(ID);
			rs.setObj(resultData);
			rs.setSuccess(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return toJsonString(rs);
	}
	
	/**
	 * 获取当前设备的维保（维修、保养）明细
	 * @param sbId 设备ID
	 * @param dateType 日期类型
	 * @return
	 */
	public String zysbwxby(String parameter){
		boolean audit = this.audit(parameter);
		if(!audit)return erroMessage();
		Result rs = new Result();
		JSONObject obj = JSONObject.parseObject(parameter);
		Request rq =  obj.toJavaObject(obj, Request.class);
		Map<String,String> parameters = (Map<String, String>) rq.getArg();
		String sbId = parameters.get("sbId");
		String dateType = parameters.get("dateType");
		zsJkXmbSbWbXxService = SpringUtils.getBean(ZsJkXmbSbWbXxService.class);
		try {
			List<ZsJkXmbSbWbXxDto> resultData = zsJkXmbSbWbXxService.zysbwxby(sbId, dateType);
			rs.setObj(resultData);
			rs.setSuccess(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return toJsonString(rs);
	}
}
