package com.crfeb.tbmpt.open.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.crfeb.tbmpt.commons.base.BaseController;
import com.crfeb.tbmpt.commons.constant.Constants;
import com.crfeb.tbmpt.commons.result.Result;
import com.crfeb.tbmpt.commons.scan.SpringUtils;
import com.crfeb.tbmpt.commons.shiro.ShiroUser;
import com.crfeb.tbmpt.commons.utils.*;
import com.crfeb.tbmpt.open.model.DicInfo;
import com.crfeb.tbmpt.open.model.ProInfo;
import com.crfeb.tbmpt.open.service.IOpenCommService;
import com.crfeb.tbmpt.project.model.ProProjectinfo;
import com.crfeb.tbmpt.project.model.ProRProjectSection;
import com.crfeb.tbmpt.project.model.ProRSectionLine;
import com.crfeb.tbmpt.project.service.IProPqInfoService;
import com.crfeb.tbmpt.project.service.IProProjectinfoService;
import com.crfeb.tbmpt.project.service.IProRProjectSectionService;
import com.crfeb.tbmpt.project.service.IProRSectionLineService;
import com.crfeb.tbmpt.risk.model.RiskInfo;
import com.crfeb.tbmpt.risk.model.RiskLevel;
import com.crfeb.tbmpt.risk.model.vo.RiskInfoVo;
import com.crfeb.tbmpt.risk.service.IRiskLevelService;
import com.crfeb.tbmpt.sys.base.model.dto.SysFujianDto;
import com.crfeb.tbmpt.sys.base.service.SysFujianService;
import com.crfeb.tbmpt.sys.model.Orgz;
import com.crfeb.tbmpt.sys.model.SysEmployee;
import com.crfeb.tbmpt.sys.model.User;
import com.crfeb.tbmpt.sys.service.IOrgzService;
import com.crfeb.tbmpt.sys.service.ISysEmployeeService;
import com.crfeb.tbmpt.tbmmg.model.ProTbminfo;
import com.crfeb.tbmpt.tbmmg.service.IProTbminfoService;
import com.crfeb.tbmpt.zl.model.QualityInfo;
import com.crfeb.tbmpt.zl.model.vo.QualityInfoVo;
import com.crfeb.tbmpt.zsjk.plc.model.dto.ProPlcBzRealDto;
import com.crfeb.tbmpt.zsjk.plc.service.IProPlcRealService;
import com.ibm.icu.util.Calendar;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.DisabledAccountException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.servlet.http.HttpServletRequest;

/**
 * @description：登录退出
 * @author：smxg @date：2016-10-10 11:12
 */
@Controller
@RequestMapping("/open")
public class RiskupController extends BaseController {
	private static final Logger LOGGER = LogManager.getLogger(OpenController.class);
	private String imagePath = "/upload/user/head/" + DateUtils.format(new Date(), "yyyyMMdd");
	@Autowired
	IOpenCommService openCommServiceImpl;
	@Autowired
	private IProProjectinfoService proProjectinfoService;
	@Autowired
	private IRiskLevelService iRiskLevelService;
	@Autowired
	private IProPqInfoService proPqInfoService;
	@Autowired
	private ISysEmployeeService sysEmployeeService;
	@Autowired
	private IOrgzService orgzService;

	/**
	 * 通过type 获取查询日期数组
	 * 
	 * @param timeType
	 *            0、今日 1、本周 2、本月3、本年
	 * @return
	 */
	private String[] timeType(String timeType) {
		String startTime = "";
		String endTime = "";
		switch (timeType) {
		case "0":// 今日
			startTime = DateUtils.format(new Date(), "yyyy-MM-dd");
			endTime = DateUtils.getDayAfter(DateUtils.format(new Date(), "yyyy-MM-dd"), 1);
			break;
		case "1":// 本周
			startTime = DateUtils.getCurrentMonday();
			endTime = DateUtils.getDayAfter(DateUtils.getSunday(), 1);
			break;
		case "2":// 本月
			startTime = DateUtils.getMonthFirstDay();
			endTime = DateUtils.getDayAfter(DateUtils.getMonthLastDay(), 1);
			break;
		case "3":// 本年
			startTime = DateUtils.getCurrentYear() + "-01-01";
			endTime = DateUtils.getDayAfter(DateUtils.getCurrentYear() + "-12-31", 1);
			break;
		default:
			break;
		}
		return new String[] { startTime, endTime };
	}

	/**
	 * 风险上报列表
	 * 
	 * @param page
	 * @param cid
	 * @param proid
	 * @param monitorDateType
	 *            0、今日 1、本周 2、本月3、本年
	 * @return JSONObject
	 */
	@RequestMapping(value = "/riskup/list")
	@ResponseBody
	public Object riskupList(Integer page, String cid, String proid ,String rikeLevel, 
			String isOut,String startTime,String endTime,String monitorDateType) {
		JSONObject jsonObject = new JSONObject();
		User user = getCurrentUser();
		if (user == null) {
			return renderError("您尚未登录或登录时间过长,请重新登录!");
		}
		PageInfo pageInfo = new PageInfo(page, Constants.appPageSize, "UP_TIME", "DESC");
		Map<String, Object> condition = new HashMap<String, Object>();
		// condition.put("upUser", user.getId());
		if (org.apache.commons.lang.StringUtils.isNotBlank(proid)) {
			condition.put("proName", proid);
		}
		if (org.apache.commons.lang.StringUtils.isNotBlank(rikeLevel)) {
			condition.put("rikeLevel", rikeLevel);
		}
		if (org.apache.commons.lang.StringUtils.isNotBlank(isOut)) {
			condition.put("isOut", isOut);
		}
		if (org.apache.commons.lang.StringUtils.isNotBlank(monitorDateType)) {// 区间-日期
			condition.put("time", timeType(monitorDateType));
			startTime="";
			endTime="";
		}
		if (org.apache.commons.lang.StringUtils.isNotBlank(startTime)) {
			condition.put("startTime", startTime);
		}
		if (org.apache.commons.lang.StringUtils.isNotBlank(endTime)) {
			condition.put("endTime", endTime);
		}
		// condition.put("upUser",getCurrentUser().getId());
		pageInfo.setCondition(condition);
		openCommServiceImpl.listRiskUp(pageInfo);
		pageInfoRows(pageInfo.getRows());
		jsonObject.put("riskUpList", pageInfo);
		return renderSuccess(jsonObject);
	}

	/**
	 * 风险上报初始化
	 * 
	 * @return
	 */
	@RequestMapping(value = "/riskup/init")
	@ResponseBody
	public Object riskupInit(String cid, String proid) {
		User user = getCurrentUser();
		if (user == null) {
			return renderError("您尚未登录或登录时间过长,请重新登录!");
		}
		JSONObject jsonObject = new JSONObject();
		ProProjectinfo proProjectinfo = openCommServiceImpl.getProByUserId(user.getId());
		System.out.println(proProjectinfo);

		jsonObject.put("projectinfo", openCommServiceImpl.getProInfoByProId(proid));
		jsonObject.put("section", openCommServiceImpl.getSectionByProId(proid));
		// line
		jsonObject.put("orgs", openCommServiceImpl.selectTreeByUser(user).get(0).getChildren());
		jsonObject.put("emps", openCommServiceImpl.selectUserTreeByUser(user).get(0).getChildren());
		// 风险级别
		EntityWrapper<RiskLevel> riskLevel = new EntityWrapper<RiskLevel>();
		riskLevel.orderBy("sort", true);
		jsonObject.put("risklevel", openCommServiceImpl.queryRiskLevelList(riskLevel));
		List<RiskInfoVo> riskInfoVos = openCommServiceImpl.selectVoRiskInfoByProId(proid, user.getId());
		List<RiskInfoVo> newriskinfo = new ArrayList<RiskInfoVo>();
		if (riskInfoVos.size() > 0) {
			RiskInfoVo riskInfoVo = riskInfoVos.get(0);
			newriskinfo.add(riskInfoVo);
		}
		pageInfoRows(newriskinfo);
		jsonObject.put("newriskinfo", newriskinfo);
		jsonObject.put("section", proPqInfoService.selectByName());
		JSONObject returnJson = new JSONObject();
		returnJson.put("initRiskInfo", jsonObject);
		return renderSuccess(returnJson);
	}

	/**
	 * 风险上报
	 * 
	 * @return
	 */
	@RequestMapping(value = "/riskup/add")
	@ResponseBody
	public Object riskupAdd(RiskInfo t, MultipartFile[] files) {
		User user = getCurrentUser();
		try{		
		t.setRikeDesc(org.apache.commons.lang.StringEscapeUtils.unescapeHtml(t.getRikeDesc()));
		t.setMainControlMethod(org.apache.commons.lang.StringEscapeUtils.unescapeHtml(t.getMainControlMethod()));
		t.setRemark(org.apache.commons.lang.StringEscapeUtils.unescapeHtml(t.getRemark()));
		
		if (user == null) {
			return renderError("您尚未登录或登录时间过长,请重新登录!");
		}
		
		String dpts = t.getDpts();
    	if(org.apache.commons.lang.StringUtils.isNotBlank(dpts)){
    		String[] dptsArray = dpts.split(",");
    		if(dptsArray.length>5){
    			return renderError("最多选择5个部门");
    		}
    		
    	}
    	String persoon = t.getPersoon();
    	if(org.apache.commons.lang.StringUtils.isNotBlank(dpts)){
    		String[] persoonArray = persoon.split(",");
    		if(persoonArray.length>5){
    			return renderError("最多选择5个负责人");
    		}
    		
    	}
    	
    	String startTime = t.getRikeTimeStart();
    	long start = Long.valueOf(startTime.replaceAll("[-\\s:]",""));
    	String endTime = t.getRikeTimeEnd();
    	long end = Long.valueOf(endTime.replaceAll("[-\\s:]",""));
    	if(end < start){
    		return renderError("结束时间必须大于开始时间");
    	}
    	
		t.setUpUser(getCurrentUser().getId());
		t.setUpTime(DateUtils.getToday());
		String empId = getCurrentUser().getEmpId();
		if (empId != null) {
			t.setUpUserPhone(openCommServiceImpl.selectSysEmployeeById(empId).getPhone());
		}
		// 设置片区 从项目信息里获取
		t.setSection(proProjectinfoService.selectByProId(t.getProName()).getArea());
		String keyId = UUID.randomUUID().toString().replace("-", "");
		uploadDatas(files, keyId, "riskup", "上传风险上报备注", "风险上报");
		t.setId(keyId);
    	String orgzId=user.getOrgzId();
    	Orgz orgz = orgzService.selectById(orgzId);
    	//项目部的人员风险级别都设为0级
    	if(orgz!=null&&orgz.getType()==2){
    		t.setRikeLevel("a76f1c4bd37b4faeb33116c7f21a994f");
    	}else{
			LOGGER.info("上报风险信息失败。");
			log(null, false, "用户："+user.getName()+",上报风险信息失败!");
			return renderError("添加失败！");
    	}
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return renderError("上报失败！"+e.getMessage());
		}
		
	try{				
		if (0 < openCommServiceImpl.addRiskUp(t)) {
			LOGGER.info("上报风险信息成功。");
			this.log(null, true, "用户："+user.getName()+"，上报风险，风险描述："+t.getRikeDesc());
			return renderSuccess("添加成功！");
		} else {
			LOGGER.info("上报风险信息失败。");
			log(null, false, "用户："+user.getName()+",上报风险信息失败!");
			return renderError("添加失败！");		   
		} 
		}catch (Exception e) {
			e.printStackTrace();
			LOGGER.info("上报风险信息失败。");
			log(null, false, "用户："+user.getName()+",上报风险信息失败!");
			return renderError("上报失败！");
		}
	}

	/**
	 * 风险上报监控
	 * 
	 * @param cid
	 * @param queryType
	 *            0、今日 1、本周 2、本年3、本年
	 * @return JSONObject
	 */
	@RequestMapping(value = "/riskmonitor")
	@ResponseBody
	public Object riskmonitor(String cid, String queryType) {
		User user = getCurrentUser();
		if (user == null) {
			return renderError("您尚未登录或登录时间过长,请重新登录!");
		}
		JSONObject jsonObject = new JSONObject();
		// 查询当前人的所有项目
		List<ProProjectinfo> proProjectinfos = proProjectinfoService.getProjectInfosBylist();
		// [projectinfo:{},
		// riskNumbers:{leveltype:number,leveltype:number,leveltype:number}
		// ]
		EntityWrapper<RiskLevel> riskLevel = new EntityWrapper<RiskLevel>();
		// 当前所有等级
		List<RiskLevel> levels = openCommServiceImpl.queryRiskLevelList(riskLevel);
		for(RiskLevel l:levels){
			if("a76f1c4bd37b4faeb33116c7f21a994f".equals(l.getId())){
				levels.remove(l);
				break;
			}
		}
		com.alibaba.fastjson.JSONArray jsonArray = new com.alibaba.fastjson.JSONArray();
		String[] time = new String[] { "", "" };
		if (org.apache.commons.lang.StringUtils.isNotBlank(queryType)) {// 区间-日期
			time = timeType(queryType);
		}
		for (int i = 0; i < proProjectinfos.size(); i++) {
			JSONObject proProjectinfo = new JSONObject();
			proProjectinfo.put("id", proProjectinfos.get(i).getId());
			proProjectinfo.put("proName", proProjectinfos.get(i).getProName());
			JSONObject keyJson = new JSONObject();
			keyJson.put("projectinfo", proProjectinfo);
			com.alibaba.fastjson.JSONArray arrayJson = new com.alibaba.fastjson.JSONArray();
			int removeFlag = 0;
			for (int j = 0; j < levels.size(); j++) {
				JSONObject countJson = new JSONObject();
				long leveltype = openCommServiceImpl.selectRiskInfoProIdUpUserRikeLevelUpTimeConut(
						proProjectinfos.get(i).getId(), null, levels.get(j).getId(), time);
				if (leveltype > 0) {
					countJson.put("leveltype", leveltype);
					countJson.put("levelcolor", levels.get(j).getColorFlag());
					arrayJson.add(countJson);
					removeFlag++;
				}
			}
			if (removeFlag > 0) {
				keyJson.put("riskNumbers", arrayJson);
				jsonArray.add(keyJson);
			}
		}
		jsonObject.put("riskmonitorlist", jsonArray);
		return renderSuccess(jsonObject);
	}

	// 封装vo
	public void pageInfoRows(List pageInfo) {
		try {
			List riskInfoList = pageInfo;
			for (Object riskObj : riskInfoList) {
				RiskInfoVo riskInfo = (RiskInfoVo) riskObj;
				// 处理所选员工Id集合
				String empIds = riskInfo.getPersoon();
				if (empIds != null && !empIds.equals("")) {
					String[] empIdsArray = new String[5];
					if (empIds.indexOf(",") != -1) {
						empIdsArray = empIds.split(",");
					} else {
						empIdsArray[0] = empIds;
					}
					List<String> empList = new ArrayList<String>();
					for (int i = 0; i < empIdsArray.length; i++) {
						String emp = empIdsArray[i];
						empList.add(emp);
					}
					List<SysEmployee> empObjList = sysEmployeeService.selectBatchIds(empList);
					String strTmp = "";
					for (SysEmployee sysEmployee : empObjList) {
						strTmp += sysEmployee.getName() + ",";
					}
					if (!"".equals(strTmp)) {
						strTmp = strTmp.substring(0, strTmp.length() - 1);
					}
					riskInfo.setPersoonStr(strTmp);
				}
				String dptIds = riskInfo.getDpts();
				if (dptIds != null && !dptIds.equals("")) {
					String[] dptIdsArray = new String[5];
					if (dptIds.indexOf(",") != -1) {
						dptIdsArray = dptIds.split(",");
					} else {
						dptIdsArray[0] = dptIds;
					}

					List<String> dptList = new ArrayList<String>();
					for (int i = 0; i < dptIdsArray.length; i++) {
						String dpt = dptIdsArray[i];
						dptList.add(dpt);
					}
					List<Orgz> dptObjList = orgzService.selectBatchIds(dptList);
					String strTmpDpt = "";
					for (Orgz orgz : dptObjList) {
						strTmpDpt += orgz.getName() + ",";
					}
					if (!"".equals(strTmpDpt)) {
						strTmpDpt = strTmpDpt.substring(0, strTmpDpt.length() - 1);
					}
					riskInfo.setDptsStr(strTmpDpt);
				}
				riskInfo.setSysFujianDtos(sysFujianService.findFuJianListByForeignId(riskInfo.getId(), null, null));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 上传图片
	 * 
	 * @param files
	 * @param keyId
	 *            主键UUID
	 * @param syspath
	 *            模块路径
	 * @param beizhu
	 *            备注
	 * @param menueName
	 *            菜单名称
	 * @return JSONObject
	 */
	public Object uploadDatas(MultipartFile[] files, String keyId, final String syspath, final String beizhu,
			final String menueName) {
		try {
			if (files != null) {
				for (int i = 0; i < files.length; i++) {
					MultipartFile file = files[i];
					String finalKeyId = keyId;
					appUploadPic(file, finalKeyId, syspath, beizhu, menueName);
				}
			}
		} catch (Exception e) {
			return renderError(e.getMessage());
		}
		return renderSuccess();
	}

	// 附件信息
	@Autowired
	private SysFujianService sysFujianService;

	/**
	 * app 上传图片
	 * 
	 * @param file
	 * @return
	 */
	public Object appUploadPic(MultipartFile file, String keyId, String syspath, String beizhu, String menueName)
			throws Exception {
		JSONObject jsonObject = new JSONObject();
		UploadUtil uploadutil = new UploadUtil();
		String fileName = "";
		if (file != null) {
			fileName = file.getOriginalFilename();
		}
		User user = getCurrentUser();
		String type = fileName.substring(fileName.lastIndexOf(".") + 1);
		String tempUid = UUID.randomUUID().toString();
		String originalFileName = "Y" + tempUid;// 原始图片保存文件名
		String compressFileNewName = "S" + tempUid;// 缩略图片保存文件名
		List<SysFujianDto> sysfjList = new ArrayList<SysFujianDto>();
		String errorMessage = "";
		String imagePath = "/upload/" + syspath + "/" + DateUtils.format(new Date(), "yyyyMMdd");
		String tempName = DateUtils.format(new Date(), "yyyyMMddHHmmssSSS")
				+ UUID.randomUUID().toString().substring(0, 4);
		if (!file.isEmpty() && file.getSize() > 0) {
			String[] imgType = { "BMP", "bmp", "jpg", "JPG", "wbmp", "jpeg", "png", "PNG", "JPEG", "WBMP", "GIF",
					"gif" };
			originalFileName = fileName.substring(0, fileName.lastIndexOf("."));// "Y"+tempUid;//原始图片保存文件名
			if (!Arrays.asList(imgType).contains(type)) {
				// 非图片
				LOGGER.error("文件格式错误，不可上传，请确认。");
				this.log("上传附件", false, "文件格式错误，不可上传，请确认。");
				throw new Exception("文件格式错误，不可上传，请确认。");
			} else {
				// 图片 图片上传的同时需要生成缩略图
				compressFileNewName = originalFileName + "S";// 缩略图片保存文件名
				errorMessage = uploadutil.uploadImage(request, file, tempName, tempName + "S", imagePath);// 上传原始图片并生成对应的缩略图
				if (StringUtils.isNotBlank(errorMessage)) {
					LOGGER.error("上传图片失败。");
					this.log("上传附件", false, "用户" + user.getName() + "上传图片失败。");
					throw new Exception(errorMessage);
				}
			}
			SysFujianDto sysFujianDto = new SysFujianDto();
			// =======================更新原始图片和缩略图片路径到数据库==================//
			sysFujianDto.setFilePath(imagePath + "/" + tempName + "." + type);
			if (StringUtils.isNotBlank(compressFileNewName)) {
				sysFujianDto.setMinImgPath(imagePath + "/" + tempName + "S" + "." + type);
			}
			sysFujianDto.setFileName(originalFileName + "." + type);
			sysFujianDto.setFileType(type);
			sysFujianDto.setFileSize(UtilMath.divide(file.getSize(), 1024, 2));// 文件大小换算成kb
			sysFujianDto.setResId(menueName);
			sysFujianDto.setForeignId(keyId);
			sysFujianDto.setBackupOne(beizhu);
			sysfjList.add(sysFujianDto);
		}
		// ==================更新上传附件信息到附件表中==========================//
		if (null != sysfjList && sysfjList.size() > 0) {
			try {
				sysFujianService.save(sysfjList, user);
			} catch (Exception e) {
				e.printStackTrace();
				LOGGER.error("附件上传失败。");
				this.log("上传附件", false, "用户" + user.getName() + "上传附件失败。");
				throw new Exception("上传附件失败");
			}
		}
		LOGGER.info("上传附件成功。");
		this.log("上传附件", true, "用户" + user.getName() + "上传图片成功。");
		jsonObject.put("sysfjList", sysfjList);
		return jsonObject;
	}

	/**
	 * 根据id删除风险上报记录
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/riskupDelete")
	@ResponseBody
	public Object riskupDelete(HttpServletRequest request,String id) {
		String rootPath =request.getSession().getServletContext().getRealPath("/");
		User user = this.getCurrentUser();
		try {
			sysFujianService.deleteFujianByForeignId(rootPath, id);
			openCommServiceImpl.delectRiskInfoById(id);
			LOGGER.info("风险上报信息删除数据成功。");
        	this.log(null, true, "用户："+user.getName()+",删除了风险上报,风险ids:"+id.toString());
    		return renderSuccess("删除成功！");
		} catch (Exception e) {
			e.printStackTrace();
			log(null, false, "用户："+user.getName()+",删除风险上报信息失败!");
			return renderError(e.getMessage());
		}
	}
	
	/**
	 * 风险上报编辑
	 * @param request
	 * @param t
	 * @param files
	 * @return
	 */
	@RequestMapping(value = "/riskup/edit")
	@ResponseBody
	public Object riskupEdit(HttpServletRequest request,RiskInfo t, MultipartFile[] files){
		User user = getCurrentUser();
	try{
		if(files==null || files.length <= 0){
			if (user == null) {
				return renderError("您尚未登录或登录时间过长,请重新登录!");
			}
			t.setUpUser(getCurrentUser().getId());
			t.setUpTime(DateUtils.getToday());
	    	String orgzId=user.getOrgzId();
	    	Orgz orgz = orgzService.selectById(orgzId);
	    	//项目部的人员风险级别都设为0级
	    	if(orgz!=null&&orgz.getType()==2){
	    		t.setRikeLevel("a76f1c4bd37b4faeb33116c7f21a994f");
	    	}
			String empId = getCurrentUser().getEmpId();
			if (empId != null) {
				t.setUpUserPhone(openCommServiceImpl.selectSysEmployeeById(empId).getPhone());
			}
			LOGGER.info("修改风险上报信息成功。");
        	this.log(null, true, "用户："+user.getName()+",修改了风险,风险id:"+t.getId());
			return openCommServiceImpl.editRiskInfoById(t);
		}
			riskupDelete(request,t.getId());
			return riskupAdd(t,files);
		} catch (Exception e) {
			e.printStackTrace();
			log(null, false, "用户："+user.getName()+",修改风险上报信息失败!");
			return renderError("编辑失败！");
		}
	}
	
	@RequestMapping(value = "/riskup/report")	
	public Object riskupReport(HttpServletRequest request,String startTime,String endTime){
//		List<ProProjectinfo> proj = proProjectinfoService.getProjectInfosByUserId(getCurrentUser().getId()); 
//		StringBuffer url = new StringBuffer();
//		String time="stime="+startTime+"&etime="+endTime;
//    	url.append("report/reportJsp/preview.jsp?");			
//    	//获取查询参数url
//    	url.append("rpx=/report/reportFiles/fxsb.rpx&rpxHome=&dfxHome=");
//    	url.append("&"+time);
//		List<String> ls = new ArrayList<String>();
//  		for(ProProjectinfo info:proj){
//  			ls.add(info.getId());
//  		}
//  		String instr = StringUtils.ListToString(ls, ",");
//  	    url.append("&proIds="+instr);
//        return "redirect:/"+url.toString();
		//根据用户id查出用户有哪些项目
  		List<ProProjectinfo> proj = proProjectinfoService.getProjectInfosByUserId(getCurrentUser().getId()); 
  		if(null != proj){
  			StringBuffer url = new StringBuffer();
  			url.append("report/reportJsp/showReport.jsp?");
  			//获取查询参数url
  			url.append("rpx=/fxsb.rpx&rpxHome=&dfxHome=&arg=/fxsb_arg.rpx&orgID=");
  			//获取用户部门信息
  			url.append(getCurrentUser().getOrgzId());
  			List<String> ls = new ArrayList<String>();
  	  		for(ProProjectinfo info:proj){
  	  			ls.add(info.getId());
  	  		}
  	  		String instr = StringUtils.ListToString(ls, ",");
  	  	   
  	  	    url.append("&proIds="+instr);
  	  	    
  	  	    //获取本周 周一 、周日 为默认查询日期
	  	  	SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
	  	  	Calendar cal = Calendar.getInstance();  
	  	    cal.setTime(new Date());  
	  	    // 判断要计算的日期是否是周日，如果是则减一天计算周六的，否则会出问题，计算到下一周去了  
	  	    int dayWeek = cal.get(Calendar.DAY_OF_WEEK);// 获得当前日期是一个星期的第几天  
	  	    if (1 == dayWeek) {  
	  	       cal.add(Calendar.DAY_OF_MONTH, -1);  
	  	    }  
	  	    // 设置一个星期的第一天，按中国的习惯一个星期的第一天是星期一  
	  	    cal.setFirstDayOfWeek(Calendar.MONDAY);  
	  	    // 获得当前日期是一个星期的第几天  
	  	    int day = cal.get(Calendar.DAY_OF_WEEK);  
	  	    // 根据日历的规则，给当前日期减去星期几与一个星期第一天的差值  
	  	    cal.add(Calendar.DATE, cal.getFirstDayOfWeek() - day);  
	  	    String imptimeBegin = sdf.format(cal.getTime());  
	  	    cal.add(Calendar.DATE, 6);  
	  	    String imptimeEnd =sdf.format(cal.getTime());  
	  	    
  	  	    url.append("&stime="+imptimeBegin);
  	  	    url.append("&etime="+imptimeEnd);
  			return "redirect:/"+url.toString();
	
  		}
		return null;
	}
}
