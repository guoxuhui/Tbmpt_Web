package com.crfeb.tbmpt.webservice;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.crfeb.tbmpt.commons.result.Request;
import com.crfeb.tbmpt.commons.result.Result;
import com.crfeb.tbmpt.commons.scan.SpringUtils;
import com.crfeb.tbmpt.commons.utils.PageInfo;
import com.crfeb.tbmpt.commons.utils.StringUtils;
import com.crfeb.tbmpt.zsjk.report.model.ZsJkReportCjzdjcdfx;
import com.crfeb.tbmpt.zsjk.report.model.ZsJkReportDgjdxxtsssj;
import com.crfeb.tbmpt.zsjk.report.model.ZsJkReportPcqxsj;
import com.crfeb.tbmpt.zsjk.report.model.ZsJkReportSgjdfx;
import com.crfeb.tbmpt.zsjk.report.model.ZsJkReportSgjdxx;
import com.crfeb.tbmpt.zsjk.report.model.dto.ZsJkReportCjzdjcdfxDto;
import com.crfeb.tbmpt.zsjk.report.model.dto.ZsJkReportDgjjcsfxDto;
import com.crfeb.tbmpt.zsjk.report.model.dto.ZsJkReportDgjjcslxDto;
import com.crfeb.tbmpt.zsjk.report.model.dto.ZsJkReportGxfxDto;
import com.crfeb.tbmpt.zsjk.report.model.dto.ZsJkReportTyyctjDto;
import com.crfeb.tbmpt.zsjk.report.service.ZsJkReportCjzdjcdfxService;
import com.crfeb.tbmpt.zsjk.report.service.ZsJkReportDgjdxxtsssjService;
import com.crfeb.tbmpt.zsjk.report.service.ZsJkReportDgjjcsfxService;
import com.crfeb.tbmpt.zsjk.report.service.ZsJkReportDgjjcslxService;
import com.crfeb.tbmpt.zsjk.report.service.ZsJkReportDgjkycztService;
import com.crfeb.tbmpt.zsjk.report.service.ZsJkReportGxfxService;
import com.crfeb.tbmpt.zsjk.report.service.ZsJkReportPcqxsjService;
import com.crfeb.tbmpt.zsjk.report.service.ZsJkReportSgjdfxService;
import com.crfeb.tbmpt.zsjk.report.service.ZsJkReportSgjdxxService;
import com.crfeb.tbmpt.zsjk.report.service.ZsJkReportTyyctjService;





public class ReportService {
	
	
	private ZsJkReportGxfxService zsJkReportGxfxService;
	
	private ZsJkReportDgjjcsfxService zsJkReportDgjjcsfxService;
	
	private ZsJkReportDgjjcslxService zsJkReportDgjjcslxService;
	
	private ZsJkReportTyyctjService zsJkReportTyyctjService;
	
	/**
	 * 工效分析
	 * @param proId 项目ID 
	 * @param lineId 线路ID 
	 * @param kssj 开始时间  
	 * @param jssj 结束时间 
	 * @param kshh 开始环号 
	 * @param jshh 结束环号
	 * @return 
	 */
	public String gxfx(String parameter){
		boolean audit = this.audit(parameter);
		if(!audit)return erroMessage();
		Result rs = new Result();
		JSONObject obj = JSONObject.parseObject(parameter);
		Request rq =  obj.toJavaObject(obj, Request.class);
		Map<String,String> parameters = (Map<String, String>) rq.getArg();
		String proId = parameters.get("proId");
		String lineId = parameters.get("lineId");
		String kssj = parameters.get("kssj");
		String jssj = parameters.get("jssj");
		String kshh = parameters.get("kshh");
		String jshh = parameters.get("jshh");
		zsJkReportGxfxService = SpringUtils.getBean(ZsJkReportGxfxService.class);
		try {
			List<ZsJkReportGxfxDto> resultData = zsJkReportGxfxService.gxfx(kssj, jssj,kshh,jshh, proId, lineId);
			rs.setObj(resultData);
			rs.setSuccess(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return toJsonString(rs);
	}
	
	/**
	 * 盾构掘进参数分析
	 * @param xmId 项目ID
	 * @param dgjId 盾构机ID
	 * @param kshs 开始环数
	 * @param jshs 结束环数
	 * @param types 数组
	 */
	public String dgjjcsfx(String parameter){
		boolean audit = this.audit(parameter);
		if(!audit)return erroMessage();
		Result rs = new Result();
		JSONObject obj = JSONObject.parseObject(parameter);
		Request rq =  obj.toJavaObject(obj, Request.class);
		Map<String,String> parameters = (Map<String, String>) rq.getArg();
		String xmId = parameters.get("xmId");
		String dgjId = parameters.get("dgjId");
		String kshs = parameters.get("kshs");
		String jshs = parameters.get("jshs");
		JSONObject oo = (JSONObject) rq.getArg();
		JSONArray types = oo.getJSONArray("types");
		List<String> list  = new ArrayList<String>();
		for(int i=0;i<types.size();i++){
			String typeId = types.getJSONObject(i).getString("id");
			String typeName = types.getJSONObject(i).getString("name");
			list.add(typeId);
		}

		zsJkReportDgjjcsfxService = SpringUtils.getBean(ZsJkReportDgjjcsfxService.class);
		try {
			List<ZsJkReportDgjjcsfxDto> resultData=zsJkReportDgjjcsfxService.dgjjcsfx(xmId, dgjId, kshs, jshs, list,null);
			rs.setObj(resultData);
			rs.setSuccess(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return toJsonString(rs);
	}
	/**
	 * 盾构掘进参数类型
	 * @param 
	 */
	public String dgjjcslx(String parameter){
		
		Result rs = new Result();
		zsJkReportDgjjcslxService = SpringUtils.getBean(ZsJkReportDgjjcslxService.class);
		try {
			List<ZsJkReportDgjjcslxDto> resultData=zsJkReportDgjjcslxService.dgjjcslx();
			rs.setObj(resultData);
			rs.setSuccess(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return toJsonString(rs);
	}
	/**
	 * 土压异常统计
	 * @param xmId 项目ID
	 * @param kssj 开始时间
	 * @param jssj 结束时间 
	 */
	public String tyyctj(String parameter){
		boolean audit = this.audit(parameter);
		if(!audit)return erroMessage();
		Result rs = new Result();
		JSONObject obj = JSONObject.parseObject(parameter);
		Request rq =  obj.toJavaObject(obj, Request.class);
		Map<String,String> parameters = (Map<String, String>) rq.getArg();
		String xmId = parameters.get("xmId");
		String kssj=parameters.get("kssj");
		String jssj=parameters.get("jssj");
		zsJkReportTyyctjService = SpringUtils.getBean(ZsJkReportTyyctjService.class);
		try {
			List<ZsJkReportTyyctjDto> resultData = zsJkReportTyyctjService.tyyctj(xmId, kssj, jssj);
			rs.setObj(resultData);
			rs.setSuccess(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return toJsonString(rs);
	}
	
	/**wpg:--------开始---------------------------------------------- */
	
	
	private ZsJkReportCjzdjcdfxService zsJkReportCjzdjcdfxService;
	
	
	/**
	 * 沉降最大监测点分析 
	 * @param xmId 项目ID
	 * @param qjId 区间ID
	 * @param kssj 开始时间
	 * @param jssj 结束时间
	 * @param hsjg 环数间隔
	 * @param zdcjl 最大沉降量
	 * @param dto 工具类
	 * @return
	 */
	public String cjzdjcdfx(String parameter){
		boolean audit = this.audit(parameter);
		if(!audit)return erroMessage();
		Result rs = new Result();
		JSONObject obj = JSONObject.parseObject(parameter);
		Request rq =  obj.toJavaObject(obj, Request.class);
		Map<String,String> parameters = (Map<String, String>) rq.getArg();
		
		ZsJkReportCjzdjcdfxDto dto = new ZsJkReportCjzdjcdfxDto();
		
		if(StringUtils.isNotBlank(parameters.get("xmId"))){
   			dto.setXmId(parameters.get("xmId"));
   		}
   		if(StringUtils.isNotBlank(parameters.get("qjId"))){
   			dto.setQjId(parameters.get("qjId"));
   		}
   		if(StringUtils.isNotBlank(parameters.get("kssj"))){
   			dto.setKssj(parameters.get("kssj"));
   		}
   		if(StringUtils.isNotBlank(parameters.get("jssj"))){
   			dto.setJssj(parameters.get("jssj"));
   		}
   		String ing ="" ;
        if(StringUtils.isNotBlank(parameters.get("hsjg")) && !parameters.get("hsjg").equals("0")){
   			dto.setHsjg(parameters.get("hsjg"));
   			ing = parameters.get("hsjg");
   		}
        if(StringUtils.isNotBlank(parameters.get("zdcjl"))){
			dto.setZdcjl(parameters.get("zdcjl"));
			ing = parameters.get("zdcjl");
		}
   		try {
   		    zsJkReportCjzdjcdfxService = SpringUtils.getBean(ZsJkReportCjzdjcdfxService.class);
		    List<ZsJkReportCjzdjcdfx> cjzdjcdfxList = zsJkReportCjzdjcdfxService.inquireCjzdjcdfxList(dto);          
			rs.setObj(cjzdjcdfxList);
			rs.setSuccess(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return toJsonString(rs);
	}
	
	
	
	private ZsJkReportDgjkycztService zsJkReportDgjkycztService;
	/**
	 * 盾构监控异常状态
	 * @param page 页码  默认 ：1
	 * @param rows 行数  默认 ：15
	 * @param xmId 项目ID
	 * @param xlId 线路ID
	 * @param dgjId 盾构机ID
	 * @param kshs 开始环数
	 * @param jshs 结束环数
	 * @param types 要查询的字段  类型集合
	 * @param pageInfo 分页对象
	 * @return 
	 */
	public String dgjkyczt(String parameter){
		Result rs = new Result();
		try {
			
			boolean audit = this.audit(parameter);
		if(!audit)return erroMessage();
		
		JSONObject obj = JSONObject.parseObject(parameter);
		Request rq =  obj.toJavaObject(obj, Request.class);
		Map<String,String> parameters = (Map<String, String>) rq.getArg();

		
		/**
		 * 获取 要查询的字段  类型，0停机时间、1出土方量、2注浆量、3平均掘进速度、4土压时间统计（<0.6）
		 */
		JSONArray types = obj.getJSONArray("types");
		List<String> list  = new ArrayList<String>();
		if(null != types){
			for(int i=0;i<types.size();i++){
			String typeId = types.getJSONObject(i).getString("id");
			list.add(typeId);
        }
		}
		

		Integer page =1;
		Integer rows =15;
		if(StringUtils.isNotBlank(parameters.get("page"))){
			page = Integer.valueOf(parameters.get("page"));
		}
		if(StringUtils.isNotBlank(parameters.get("rows"))){
			rows = Integer.valueOf(parameters.get("rows"));
		}
		 PageInfo pageInfo = new PageInfo(page, rows);
         Map<String, Object> condition = new HashMap<String, Object>();
         pageInfo.setCondition(condition);
         
 		if(StringUtils.isNotBlank(parameters.get("xmId"))){
   			condition.put("xmId", parameters.get("xmId"));
   		}
   		if(StringUtils.isNotBlank(parameters.get("xlId"))){
   			condition.put("xlId", parameters.get("xlId"));
   		}
   		if(StringUtils.isNotBlank(parameters.get("dgjId"))){
   			condition.put("dgjId", parameters.get("dgjId"));
   		}
   		if(StringUtils.isNotBlank(parameters.get("kshs"))){
   			condition.put("kshs", parameters.get("kshs"));
   		}
   		if(StringUtils.isNotBlank(parameters.get("jshs"))){
   			condition.put("jshs",parameters.get("jshs"));
   		}
 		
   		    zsJkReportDgjkycztService = SpringUtils.getBean(ZsJkReportDgjkycztService.class);
		
	    	zsJkReportDgjkycztService.inquireDgjkycztDataPage(pageInfo, list);
	    	 Map<Object ,Object> map = new HashMap<Object, Object>();
	         //总条数
	    	 map.put("total", pageInfo.getTotal());
	         //数据集
	    	 map.put("rows", pageInfo.getRows());
	    	rs.setObj(map);
			rs.setSuccess(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return toJsonString(rs);
	}
	
	
	
	private ZsJkReportSgjdfxService zsJkReportSgjdfxService ;
	/**
	 * 施工进度分析
	 * @param xmId 项目id
	 * @param xlId 线路Id
	 * @param dgjId 盾构机Id
	 * @param ksrq 开始日期
	 * @param jsrq 结束日期
	 * @return 结果集合
	 */
	public String sgjdfx(String parameter){
		boolean audit = this.audit(parameter);
		if(!audit)return erroMessage();
		Result rs = new Result();
		JSONObject obj = JSONObject.parseObject(parameter);
		Request rq =  obj.toJavaObject(obj, Request.class);
		Map<String,String> parameters = (Map<String, String>) rq.getArg();
		String xmId = parameters.get("xmId");
		String xlId = parameters.get("xlId");
		String dgjId = parameters.get("dgjId");
		String ksrq = parameters.get("ksrq");
		String jsrq = parameters.get("jsrq");
		zsJkReportSgjdfxService = SpringUtils.getBean(ZsJkReportSgjdfxService.class);
		try {
			List<ZsJkReportSgjdfx> resultData = zsJkReportSgjdfxService.inquireSgjdfxList(xmId, xlId, dgjId,ksrq,jsrq);
			rs.setObj(resultData);
			rs.setSuccess(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return toJsonString(rs);
	}
	
	
	
	private ZsJkReportSgjdxxService zsJkReportSgjdxxService;
	/**
	 * 施工进度信息
	 * @param xmId 项目id
	 * @param xlId 线路Id
	 * @param dgjId 盾构机Id
	 * @return 对象
	 */
	public String sgjdxx(String parameter){
		boolean audit = this.audit(parameter);
		if(!audit)return erroMessage();
		Result rs = new Result();
		JSONObject obj = JSONObject.parseObject(parameter);
		Request rq =  obj.toJavaObject(obj, Request.class);
		Map<String,String> parameters = (Map<String, String>) rq.getArg();
		String xmId = parameters.get("xmId");
		String xlId = parameters.get("xlId");
		String dgjId = parameters.get("dgjId");
		zsJkReportSgjdxxService = SpringUtils.getBean(ZsJkReportSgjdxxService.class);
		try {
			ZsJkReportSgjdxx  sgjdxx = zsJkReportSgjdxxService.inquireSgjdxxList(xmId, xlId, dgjId);
			rs.setObj(sgjdxx);
			rs.setSuccess(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return toJsonString(rs);
	}
	
	
	
	
	private ZsJkReportDgjdxxtsssjService zsJkReportDgjdxxtsssjService;
	/**
	 * 掘进姿态:盾构机导向系统实时数据
	 * @param xmId 项目id
	 * @param xlId 线路Id
	 * @param dgjId 盾构机Id
	 * @return 对象
	 */
	public String dgjdxxtsssj(String parameter){
		boolean audit = this.audit(parameter);
		if(!audit)return erroMessage();
		Result rs = new Result();
		JSONObject obj = JSONObject.parseObject(parameter);
		Request rq =  obj.toJavaObject(obj, Request.class);
		Map<String,String> parameters = (Map<String, String>) rq.getArg();
		String xmId = parameters.get("xmId");
		String xlId = parameters.get("xlId");
		String dgjId = parameters.get("dgjId");
		zsJkReportDgjdxxtsssjService = SpringUtils.getBean(ZsJkReportDgjdxxtsssjService.class);
		try {
			ZsJkReportDgjdxxtsssj  dgjdxxtsssj = zsJkReportDgjdxxtsssjService.inquireDgjdxxtsssjList(xmId, xlId, dgjId);
			rs.setObj(dgjdxxtsssj);
			rs.setSuccess(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return toJsonString(rs);
	}
	
	
	
	
	private ZsJkReportPcqxsjService zsJkReportPcqxsjService;
	/**
	 * 掘进姿态:偏差曲线数据 
	 * @param xmId 项目ID
	 * @param xlId 线路ID
	 * @param dgjId 盾构机ID
	 * @param kshs 开始环数
	 * @param jshs 结束环数
	 * @return 数据结果集合
	 */
	public String pcqxsj(String parameter){
		boolean audit = this.audit(parameter);
		if(!audit)return erroMessage();
		Result rs = new Result();
		JSONObject obj = JSONObject.parseObject(parameter);
		Request rq =  obj.toJavaObject(obj, Request.class);
		Map<String,String> parameters = (Map<String, String>) rq.getArg();
		String xmId = parameters.get("xmId");
		String xlId = parameters.get("xlId");
		String dgjId = parameters.get("dgjId");
		String kshs = parameters.get("kshs");
		String jshs = parameters.get("jshs");
		zsJkReportPcqxsjService = SpringUtils.getBean(ZsJkReportPcqxsjService.class);
		try {
			
			List<ZsJkReportPcqxsj> resultData = zsJkReportPcqxsjService.inquirePcqxsjList(xmId, xlId, dgjId,kshs,jshs);
			rs.setObj(resultData);
			rs.setSuccess(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return toJsonString(rs);
	}
	
	
	
	/**wpg:--------结束---------------------------------------------- */
	
	/**
	 * 校验是否合法性
	 * @param parameter
	 * @return
	 */
	public boolean audit(String parameter){
		JSONObject obj = JSONObject.parseObject(parameter);
		Request rq =  obj.toJavaObject(obj, Request.class);
		String secret =  rq.getAppSecret();
//		if(secret.equals("999999")){
			return true;
//		}else return false;
	}
	/**
	 * 校验是否合法性
	 * @param parameter
	 * @return
	 */
	public String erroMessage(){
		Result rs = new Result();
		rs.setMsg("非法调用!");
		rs.setSuccess(false);
		rs.setObj(null);
		return toJsonString(rs);
	}
	/**
	 * 校验是否合法性
	 * @param parameter
	 * @return
	 */
	public String toJsonString(Result rs){
//		 QuoteFieldNames———-输出key时是否使用双引号,默认为true 
//		 WriteMapNullValue——–是否输出值为null的字段,默认为false 
//		 WriteNullNumberAsZero—-数值字段如果为null,输出为0,而非null 
//		 WriteNullListAsEmpty—–List字段如果为null,输出为[],而非null 
//		 WriteNullStringAsEmpty—字符类型字段如果为null,输出为”“,而非null 
//		 WriteNullBooleanAsFalse–Boolean字段如果为null,输出为false,而非null
		return JSONObject.toJSONString(rs,SerializerFeature.WriteMapNullValue,SerializerFeature.WriteNullStringAsEmpty);
	}

}
