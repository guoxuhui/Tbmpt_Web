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

import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.servlet.http.HttpServletRequest;

/**
 * @description：登录退出
 * @author：smxg
 * @date：2016-10-10 11:12
 */
@Controller
@RequestMapping("/open")
public class QualityupController extends BaseController {
    private static final Logger LOGGER = LogManager.getLogger(OpenController.class);
    private String imagePath = "/upload/user/head/"+DateUtils.format(new Date(), "yyyyMMdd");
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
     * 管片上报问题Json
     */
    final static String qualityUpProblemType = "[{\"key\":\"开裂\",\"value\":\"开裂\"},{\"key\":\"错台\",\"value\":\"错台\"},{\"key\":\"渗漏水\",\"value\":\"渗漏水\"},{\"key\":\"姿态\",\"value\":\"姿态\"},{\"key\":\"复紧\",\"value\":\"复紧\"}]";
    
    /**
     * 管片上报状态Json
     */
    final static String qualityUpStatus = "[{\"key\":\"未处理\",\"value\":\"未处理\"},{\"key\":\"已处理\",\"value\":\"已处理\"},{\"key\":\"暂不处理\",\"value\":\"暂不处理\"}]";
    
    /**
	 * 通过type 获取查询日期数组
	 * @param timeType 0、今日 1、本周 2、本月3、本年
	 * @return
	 */
	private String[] timeType(String timeType){
		String startTime = "";
		String endTime = "";
		switch (timeType) {
		case "0"://今日
			startTime = DateUtils.format(new Date(), "yyyy-MM-dd");
			endTime = DateUtils.getDayAfter(DateUtils.format(new Date(), "yyyy-MM-dd"),1);
			break;
		case "1"://本周
			startTime = DateUtils.getCurrentMonday();
			endTime = DateUtils.getDayAfter(DateUtils.getSunday(),1);
			break;
		case "2"://本月
			startTime = DateUtils.getMonthFirstDay();
			endTime = DateUtils.getDayAfter(DateUtils.getMonthLastDay(),1);
			break;
		case "3"://本年
			startTime = DateUtils.getCurrentYear()+"-01-01";
			endTime = DateUtils.getDayAfter(DateUtils.getCurrentYear()+"-12-31",1);
			break;
		default:
			break;
		}
		return new String[]{startTime,endTime};
	}
	
    /**
     * 管片上报列表
     * @param page
     * @param cid
     * @param proid
     * @param monitorDateType 0、今日 1、本周 2、本月3、本年
     * @return JSONObject
     */
    @RequestMapping(value = "/qualityup/list")
    @ResponseBody
    public Object qualityupList(Integer page,String cid,String proid,String sectionId,String lineId,
    		String hasProblem,String status,String startTime,String endTime,String monitorDateType) {
    	JSONObject jsonObject = new JSONObject();
    	User user = getCurrentUser();
        if(user == null){
        	 return renderError("您尚未登录或登录时间过长,请重新登录!");
        }
        PageInfo pageInfo = new PageInfo(page, Constants.appPageSize);
        Map<String, Object> condition = new HashMap<String, Object>();
//    	condition.put("upUser", user.getId());
        if(org.apache.commons.lang.StringUtils.isNotBlank(proid)){
        	condition.put("proName", proid);
        }
        if(org.apache.commons.lang.StringUtils.isNotBlank(sectionId)){
        	condition.put("section", sectionId);
        }
        if(org.apache.commons.lang.StringUtils.isNotBlank(lineId)){
        	condition.put("line", lineId);
        }
        if(org.apache.commons.lang.StringUtils.isNotBlank(hasProblem)){
        	condition.put("hasProblem", hasProblem);
        }
        if(org.apache.commons.lang.StringUtils.isNotBlank(status)){
        	condition.put("status", status);
        }
        if(org.apache.commons.lang.StringUtils.isNotBlank(monitorDateType)){//区间-日期
        	condition.put("time", timeType(monitorDateType));
        	startTime="";
        	endTime="";
        }
        if(org.apache.commons.lang.StringUtils.isNotBlank(startTime)){
        	condition.put("startTime", startTime);
        }
        if(org.apache.commons.lang.StringUtils.isNotBlank(endTime)){
        	condition.put("endTime", endTime);
        }
        
//        condition.put("upUser",getCurrentUser().getId());
        pageInfo.setCondition(condition);
        openCommServiceImpl.listQualityUp(pageInfo);
        List<QualityInfoVo> qualityInfoVos = pageInfo.getRows();
        for(int i=0;i<qualityInfoVos.size();i++){
            try {
                qualityInfoVos.get(i).setSysFujianDtos(sysFujianService.findFuJianListByForeignId(qualityInfoVos.get(i).getId(), null, null));
            }catch (Exception e){
                e.printStackTrace();
            }
        }
		jsonObject.put("initQualityInfo", pageInfo);
		String str=jsonObject.toString().replace("null", "");
		jsonObject=JSONObject.parseObject(str);
        return renderSuccess(jsonObject);
    }


    @Autowired
    private IProRProjectSectionService proRProjectSectionService;
    @Autowired
    private IProRSectionLineService proRSectionLineService;
    @Autowired
    private IProPlcRealService proPlcRealService;
    @Autowired
    private IProTbminfoService proTbminfoService;

    /**
     * 管片质量上报初始化
     * @return
     */
    @RequestMapping(value = "/qualityup/init")
    @ResponseBody
    public Object qualityupInit(String cid,String proid) {
    	JSONObject returnJson = new JSONObject();
    	try{
    		User user = getCurrentUser();
            if(user == null){
            	 return renderError("您尚未登录或登录时间过长,请重新登录!");
            }
    		JSONObject jsonObject = new JSONObject();

    		jsonObject.put("projectinfo", openCommServiceImpl.getProInfoByProId(proid));
	    	jsonObject.put("section", openCommServiceImpl.getSectionByProId(proid));
    		
	    	jsonObject.put("problemType",qualityUpProblemType);
	    	jsonObject.put("status",qualityUpStatus);
//	    	jsonObject.put("orgs", openCommServiceImpl.selectTreeByUser(user));
//	    	jsonObject.put("emps", openCommServiceImpl.selectUserTreeByUser(user));
            List<QualityInfoVo> riskInfoVos = openCommServiceImpl.selectVoQualityInfoByProId(proid,user.getId());
            List<QualityInfoVo> newqualityinfo = new ArrayList<QualityInfoVo>();
            if(riskInfoVos.size()>0){
                QualityInfoVo qualityInfoVo = riskInfoVos.get(0);
                newqualityinfo.add(qualityInfoVo);
            }
            jsonObject.put("newqualityinfo",newqualityinfo);


            //区间
            List<ProRProjectSection> list = proRProjectSectionService.getSectionByProId(proid);
            JSONArray sectionArray = new JSONArray();
            if(list!=null && list.size()>0){//区间
                for(int i=0;i<list.size();i++){
                    JSONObject linJson = new JSONObject();
                    linJson.putAll(JSONObject.parseObject(JSONArray.toJSON(list.get(i)).toString()));
                    List<ProRSectionLine> proRSectionLines = proRSectionLineService.getLineBySectionId(list.get(i).getId());
                    JSONArray sectionsArray = JSONArray.parseArray(JSON.toJSONString(proRSectionLines));
                    for(int j=0;j<sectionsArray.size();j++){
                        JSONObject hhJson = sectionsArray.getJSONObject(j);
                        Map map = getCurrHHByXlId(hhJson.getString("id"));
                        if(map != null) {
                        		hhJson.put("cycleNo",map.get("jjhh"));                        	
                        }
                    }
                    linJson.put("sections",sectionsArray);
                    sectionArray.add(linJson);
                }
            }
            jsonObject.put("sections",sectionArray);

	    	returnJson.put("initQualityInfo", jsonObject);
    	}catch (Exception e) {
			e.printStackTrace();
		}
    	return renderSuccess(returnJson);
    }
    
    /**
     * 管片上报
     * @return
     */
    @RequestMapping(value = "/qualityup/add")
    @ResponseBody
    public Object qualityupAdd(HttpServletRequest request,QualityInfo t,MultipartFile[] files) {
    	String rootPath =request.getSession().getServletContext().getRealPath("/");
    	User user = getCurrentUser();
    	t.setProblemDesc(org.apache.commons.lang.StringEscapeUtils.unescapeHtml(t.getProblemDesc()));
    	t.setRemark(org.apache.commons.lang.StringEscapeUtils.unescapeHtml(t.getRemark()));
		try {
			if(user == null){
	        	 return renderError("您尚未登录或登录时间过长,请重新登录!");
	        }
	        String cycNo = t.getCycleNo();
	        String lineId=t.getLine();
	    	// 上报人 上报时间 联系方式 获取当前用户，进一步获取员工信息
	    	t.setUpUser(getCurrentUser().getId());
	    	t.setUpDate(DateUtils.getToday());
	    	String empId = getCurrentUser().getEmpId();
	    	if (empId != null) {
	    		t.setLinkWay(openCommServiceImpl.selectSysEmployeeById(empId).getPhone());
	    	}
	        QualityInfo q=openCommServiceImpl.selectQualityInfoByLineIdNo(lineId,cycNo);
	        if(q!=null){
	        	if(files==null){
	        		t.setId(q.getId());
	        		openCommServiceImpl.editQualityInfoById(t);
	        		LOGGER.info("管片质量上报成功！");
	    			log(null, false, "用户："+user.getName()+",添加了管片质量上报信息!");
	        		return renderSuccess("添加成功！");
	        	}else{
	        		
					sysFujianService.deleteFujianByForeignId(rootPath, q.getId());
					t.setId(q.getId());
	        		openCommServiceImpl.editQualityInfoById(t);
	        		uploadDatas(files,q.getId(),"qualityup","上传管片上报备注","管片上报");
	        		LOGGER.info("管片质量上报成功！");
	    			log(null, false, "用户："+user.getName()+",添加了管片质量上报信息!");
	        		return renderSuccess("添加成功！");
					
	        	}
	        }else{
	        	String keyId = UUID.randomUUID().toString().replace("-", "");
	        	uploadDatas(files,keyId,"qualityup","上传管片上报备注","管片上报");
	        	t.setId(keyId);
	        	if(0<openCommServiceImpl.addQualityUp(t)){
	        		LOGGER.info("管片质量上报成功！");
	    			log(null, false, "用户："+user.getName()+",添加了管片质量上报信息!");
	        		return renderSuccess("添加成功！");
	        	}else{
	        		LOGGER.info("添加管片质量上报信息失败！");
	    			log(null, false, "用户："+user.getName()+",添加管片质量上报信息失败!");
	        		return renderError("添加失败！");
	        	}        	
	        }
		} catch (Exception e) {
			LOGGER.info("添加管片质量上报信息失败！");
			log(null, false, "用户："+user.getName()+",添加管片质量上报信息失败!");
			return renderError("添加失败！");
		}
    	
	}
    
    String[] problemTypeColorS = {"#c23531","#2f4554","#61a0a8","#d48265","#91c7c2"};
    String[] qualityTypeNames = {"开裂","错台","渗漏水","姿态","复紧"};
    /**
     * 管片上报监控
     * @param cid
     * @param queryType 0、今日 1、本周 2、本年3、本年
     * @return
     */
    @RequestMapping(value = "/qualitymonitor")
    @ResponseBody
    public Object qualitymonitor(String cid,String queryType) {
    	User user = getCurrentUser();
        if(user == null){
        	 return renderError("您尚未登录或登录时间过长,请重新登录!");
        }
    	JSONObject jsonObject = new JSONObject();
    	//查询当前人的所有项目
    	List<ProProjectinfo> proProjectinfos = proProjectinfoService.getProjectInfosBylist();
//    	[proId:number,projectName:"",
//    	 qualityNumbers:{leveltype:number,leveltype:number,leveltype:number}
//    	 ]
//    	 [proId:number,projectName:"",
//    	 qualityNumbers:{leveltype:number,leveltype:number,leveltype:number}
//    	 ]
//    	 [proId:number,projectName:"",
//    	 qualityNumbers:{leveltype:number,leveltype:number,leveltype:number}
//    	 ]
    	String[] time = new String[]{"",""};
    	if(org.apache.commons.lang.StringUtils.isNotBlank(queryType)){//区间-日期
    		time = timeType(queryType);
    	}
    	com.alibaba.fastjson.JSONArray jsonArray = new com.alibaba.fastjson.JSONArray();
    	for(int i=0;i<proProjectinfos.size();i++){
    		JSONObject proProjectinfo = new JSONObject();
    		JSONObject keyJson = new JSONObject();
        proProjectinfo.put("id", proProjectinfos.get(i).getId());
        proProjectinfo.put("proName", proProjectinfos.get(i).getProName());
        keyJson.put("projectinfo", proProjectinfo);
        com.alibaba.fastjson.JSONArray arrayJson = new com.alibaba.fastjson.JSONArray(); 
        int removeFlag = 0;
        for (int j = 0; j < qualityTypeNames.length; j++) {
    			JSONObject countJson = new JSONObject();
	    		long qualityNumbers = openCommServiceImpl.selectQualityUpProIdTypeUpDateConut(proProjectinfos.get(i).getId(),qualityTypeNames[j],time);
	    		if(qualityNumbers>0){
    				countJson.put("leveltype", qualityNumbers);
				countJson.put("levelcolor", problemTypeColorS[j]);
				arrayJson.add(countJson);
                removeFlag++;
	         }
	      }
    			if (removeFlag > 0) {
				keyJson.put("qualityNumbers", arrayJson);
				jsonArray.add(keyJson);
			}
    		}
    		jsonObject.put("qualitymonitorlist", jsonArray);
    		return renderSuccess(jsonObject);
    }

    /**
     * 上传图片
     * 
     * @param files
     * @param keyId 主键UUID
     * @param syspath 模块路径
     * @param beizhu 备注
     * @param menueName 菜单名称
     * @return JSONObject
     */
    public Object uploadDatas (MultipartFile[] files, String keyId, final String syspath, final String beizhu, final String menueName) {
    	try{
    	    if(files!=null){
            for(int i=0;i<files.length;i++){
                MultipartFile file = files[i];
                String finalKeyId = keyId;
                appUploadPic(file, finalKeyId,syspath,beizhu,menueName);
            }
        }
    	}catch (Exception e) {
    		return renderError(e.getMessage());
		}
    	return renderSuccess();
    }
    
    //附件信息
    @Autowired
    private SysFujianService sysFujianService;
    
    /**
  	 * app 上传图片
  	 * @param file
  	 * @return
  	 */
	public Object appUploadPic(MultipartFile file, String keyId,String syspath,String beizhu,String menueName) throws Exception{
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
		String tempName = DateUtils.format(new Date(), "yyyyMMddHHmmssSSS") + UUID.randomUUID().toString().substring(0, 4);
		if (!file.isEmpty() && file.getSize() > 0) {
			String[] imgType = { "BMP", "bmp", "jpg", "JPG", "wbmp", "jpeg", "png", "PNG", "JPEG", "WBMP", "GIF","gif" };
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
     * 获取环号
     * @param xlId
     * @return
     */
    public Map<String,Object> getCurrHHByXlId(String xlId){
        Map<String,Object> tbmMap = new HashMap<String,Object>();
        proTbminfoService = SpringUtils.getBean(IProTbminfoService.class);
        proRSectionLineService = SpringUtils.getBean(IProRSectionLineService.class);
        ProRSectionLine line = proRSectionLineService.selectById(xlId);
        ProTbminfo tbm = null;
        if(line != null){
            tbm = proTbminfoService.selectById(line.getTbmId());
            List<ProPlcBzRealDto> pbzs = new ArrayList<ProPlcBzRealDto>();
            String dwIds = "TY_DXXT_0001";
            try {
            		if(tbm != null) {
            			pbzs = proPlcRealService.getSsJqsjByDw(tbm.getId(), dwIds);
            	        tbmMap.put("tbmInfo", tbm);
            	        if(pbzs != null && pbzs.size()==1){
            	            if("TY_DXXT_0001".equals(pbzs.get(0).getDwid())){
            	                tbmMap.put("jjhh", pbzs.get(0).getTagvalue());
            	            }
            	            tbmMap.put("plcTime", pbzs.get(0).getTagtime());
            	        }
            	        if(tbmMap.get("jjhh") == null){
            	            tbmMap.put("jjhh", "0");
            	            tbmMap.put("plcTime", DateUtils.getToday());
            	        }
            	        return tbmMap;
            		}else {
            			return null;
            		}
            } catch (Exception e) {
                e.printStackTrace();
            }
            
            return null;
        }else {
        		return null;
        }
    }
    
	/**
	 * 根据id删除管片上报记录
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/qualityupDelete")
	@ResponseBody
	public Object qualityupDelete(HttpServletRequest request,String id) {
		String rootPath =request.getSession().getServletContext().getRealPath("/");
		User user = this.getCurrentUser();
		try {
			sysFujianService.deleteFujianByForeignId(rootPath, id);
			openCommServiceImpl.delectQualityInfoById(id);
			LOGGER.info("删除管片质量上报成功。");
        	this.log(null, true, "用户："+user.getName()+",删除了管片质量上报信息！管片质量上报ids:"+id.toString());
			return renderSuccess("删除成功！");
		} catch (Exception e) {
			LOGGER.info("删除管片质量上报信息失败！");
			log(null, false, "用户："+user.getName()+",删除管片质量上报信息失败!");
			return renderError(e.getMessage());
		}
	}
	
	/**
	 * 管片上报编辑
	 * @param request
	 * @param t
	 * @param files
	 * @return
	 */
	@RequestMapping(value = "/qualityup/edit")
	@ResponseBody
	public Object qualityupEdit(HttpServletRequest request,QualityInfo t, MultipartFile[] files){
		User user = this.getCurrentUser();
		try {
			if(files==null || files.length <= 0){
				
				if (user == null) {
					return renderError("您尚未登录或登录时间过长,请重新登录!");
				}
		        t.setUpUser(getCurrentUser().getId());
				t.setUpDate(DateUtils.getToday());
				String empId = getCurrentUser().getEmpId();
				if (empId != null) {
					t.setLinkWay(openCommServiceImpl.selectSysEmployeeById(empId).getPhone());
				}
				Object object= openCommServiceImpl.editQualityInfoById(t);
				LOGGER.info("编辑管片质量上报信息成功！");
    			log(null, true, "用户："+user.getName()+",编辑管片质量上报信息成功!");
    			return object;
			}
			qualityupDelete(request,t.getId());
			return qualityupAdd(request,t,files);
		} catch (Exception e) {
			LOGGER.info("编辑管片质量上报信息失败！");
			log(null, false, "用户："+user.getName()+",编辑管片质量上报信息失败!");
			return renderError("编辑失败！");
		}
		
	}

}
