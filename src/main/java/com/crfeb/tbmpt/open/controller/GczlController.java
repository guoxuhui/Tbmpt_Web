package com.crfeb.tbmpt.open.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.crfeb.tbmpt.commons.base.BaseController;
import com.crfeb.tbmpt.commons.utils.CommUtils;
import com.crfeb.tbmpt.commons.utils.DateUtils;
import com.crfeb.tbmpt.commons.utils.PageInfo;
import com.crfeb.tbmpt.commons.utils.StringUtils;
import com.crfeb.tbmpt.commons.utils.UploadUtil;
import com.crfeb.tbmpt.gczl.base.model.GczlYdxjGPZLXJInfo;
import com.crfeb.tbmpt.gczl.base.model.GczlYdxjSGZLXJInfo;
import com.crfeb.tbmpt.gczl.base.model.dto.GczlYdxjGPZLXJInfoDto;
import com.crfeb.tbmpt.gczl.base.model.dto.GczlYdxjSGZLXJInfoDto;
import com.crfeb.tbmpt.gczl.base.service.GczlOpenPushService;
import com.crfeb.tbmpt.gczl.base.service.GczlYdxjGPZLXJInfoService;
import com.crfeb.tbmpt.gczl.base.service.GczlYdxjSGZLXJInfoService;
import com.crfeb.tbmpt.open.model.DicInfo;
import com.crfeb.tbmpt.open.model.ProInfo;
import com.crfeb.tbmpt.open.service.IOpenCommService;
import com.crfeb.tbmpt.open.service.IOpenGczlService;
import com.crfeb.tbmpt.open.service.IOpenPushService;
import com.crfeb.tbmpt.project.model.ProProjectinfo;
import com.crfeb.tbmpt.sys.base.model.dto.SysFujianDto;
import com.crfeb.tbmpt.sys.base.service.SysFujianService;
import com.crfeb.tbmpt.sys.model.User;

/**
 * <p>工程质量巡检  Controller</p>
 * <p>系统：工程质量巡检</p>
 * <p>模块：工程质量巡检</p>
 * <p>日期：2016-12-27</p>
 * @version 1.0
 * @author smxg
 */
@Controller
@RequestMapping("/open/gczl")
public class GczlController extends BaseController {

    //private static final Logger LOGGER = LogManager.getLogger(GczlController.class);
    private String imagePath = "/upload/gczl/pic/"+DateUtils.format(new Date(), "yyyyMMdd");
    @Autowired
    IOpenCommService openCommServiceImpl;
    @Autowired
    IOpenGczlService openGczlServiceImpl;
    @Autowired
    private IOpenPushService iOpenPushService;
    @Autowired
    private GczlYdxjGPZLXJInfoService gczlYdxjGPZLXJInfoService;
    @Autowired
    private GczlYdxjSGZLXJInfoService gczlYdxjSGZLXJInfoService;
    
    @Autowired
    private GczlOpenPushService gczlOpenPushService;
    
    @Autowired
    private SysFujianService sysFujianService;
    
	/**
	 * 工程质量巡检首页
	 * 
	 * @return
	 */
	@RequestMapping(value = "/gczlHomePage", method = RequestMethod.GET)
	public String gczlHomePape() {
		return "open/gczl/gczlHome";
	}
	
	/**
	 * 获取施工质量巡检页面
	 * 2016年12月17日15:34:21
	 * @return
	 */
	@RequestMapping(value = "/gczlSGZLXJAddPage", method = RequestMethod.GET)
	public String gczlSGZLXJAddPage(Model model, String token) {
		long startTime = System.currentTimeMillis();
		if (!StringUtils.isEmpty(token)) {
			openCommServiceImpl.relogin(token);
		}
		User user = getCurrentUser();
		ProProjectinfo proinfo = openCommServiceImpl.getProByUserId(user.getId());
		model.addAttribute("user", user);
		
		DicInfo pro = openCommServiceImpl.getDwgcInfoByProId(proinfo.getId());
		model.addAttribute("pro", pro);
		
		List<DicInfo> dic = openGczlServiceImpl.getSgzlDDInfo();
		model.addAttribute("dic", dic);
		
		model.addAttribute("dicJson", JSONArray.toJSON(dic));
		long endTime = System.currentTimeMillis();
		System.out.println("gczlSGZLXJAddPage Session ID ="+this.request.getSession().getId()+" == "+
				"执行="+(endTime-startTime));
		return "open/gczl/gczlSGZLXJ";
	}

	/**
	 * 提交施工质量数据
	 * 
	 * @return
	 */
	@RequestMapping(value = "/gczlSGZLXJAddDo", method = RequestMethod.POST)
	public Object gczlSGZLXJAddDo(Model model, GczlYdxjSGZLXJInfoDto gczlYdxjSGZLXJInfoDto,
			@RequestParam(value = "file", required = false) CommonsMultipartFile[] files, HttpServletRequest request) {
		
		
		gczlYdxjSGZLXJInfoDto.setWhTime(DateUtils.getToday());
    	User user = this.getCurrentUser();
    	gczlYdxjSGZLXJInfoDto.setXjRy(user.getId());
    	gczlYdxjSGZLXJInfoDto.setXjBm(user.getOrgzId());
    	gczlYdxjSGZLXJInfoDto.setSbZt("未上报");
    	gczlYdxjSGZLXJInfoDto.setShZt("未审核");
    	gczlYdxjSGZLXJInfoDto.setZgZt("未整改");
		String errmessage = gczlYdxjSGZLXJInfoService.checkClumIfexits(gczlYdxjSGZLXJInfoDto,
				new String[] {"gcBh","dwgc_bh","sgNr","fbGcbh","sgd","jtWz","zlQx","xjTime"});
		if (errmessage != null) {
			log(null, false, errmessage);
			return renderError(errmessage);
		}

		String xjtime = gczlYdxjSGZLXJInfoDto.getXjTime();
		xjtime = xjtime.replace("T", " ");
		if (xjtime.length() < 19) {
			xjtime = xjtime + ":00";
		} else if (xjtime.length() > 19) {
			xjtime = xjtime.substring(0, 19);
		}
		gczlYdxjSGZLXJInfoDto.setXjTime(xjtime);
		
		String uuid = CommUtils.getUUID();
		gczlYdxjSGZLXJInfoDto.setId(uuid);
		gczlYdxjSGZLXJInfoService.insert(gczlYdxjSGZLXJInfoDto, user);
		List<SysFujianDto> sfdtos = multFujianOpt(uuid,files,user,"整改前");
		
		gczlOpenPushService.saveGPZLXJInfoPic(sfdtos, user);
		model.addAttribute("data", renderSuccess("提交成功"));
		model.addAttribute("isDo", true);

		ProProjectinfo proinfo = openCommServiceImpl.getProByUserId(user.getId());
		model.addAttribute("user", user);
		
		DicInfo pro = openCommServiceImpl.getDwgcInfoByProId(proinfo.getId());
		model.addAttribute("pro", pro);
		
		List<DicInfo> dic = openGczlServiceImpl.getSgzlDDInfo();
		model.addAttribute("dic", dic);
		model.addAttribute("dicJson", JSONArray.toJSON(dic));
		log(null, true, "移动端施工质量巡检信息添加成功！");
		return "open/gczl/gczlSGZLXJ";
	}
	
	/**
	 * 保存文件工具
	 * @param id 主表ID
	 * @param files CommonsMultipartFile
	 * @param user User
	 * @return 单位工程列表
	 * @author:Xhguo
	 * @Time: 2017年2月18日14:56:44
	 */
	private List<SysFujianDto> multFujianOpt(String id,CommonsMultipartFile[] files,User user,String bz){
		
		List<SysFujianDto> list = null;
		if(files != null)
		for(CommonsMultipartFile file : files){
			SysFujianDto fj = null;
			String fileName = "";
			if (file != null) {
			fileName = file.getOriginalFilename();
			}
			if (!StringUtils.isEmpty(fileName)) {
				String type = fileName.substring(fileName.lastIndexOf(".") + 1);
				String tempUid = UUID.randomUUID().toString();
				String originalFileName = "Y" + tempUid;// 原始图片保存文件名
				String compressFileNewName = "S" + tempUid;// 缩略图片保存文件名
				UploadUtil uploadutil = new UploadUtil();
				String errorMessage = uploadutil.uploadImage(request, file, originalFileName, compressFileNewName,
						imagePath);// 上传原始图片并生成对应的缩略图
				if (StringUtils.isNotBlank(errorMessage)) {
				} else {
					fj = new SysFujianDto();
					fj.setFileName(fileName);
					fj.setFilePath(imagePath + "/" + originalFileName + "." + type);
					fj.setMinImgPath(imagePath + "/" + compressFileNewName + "." + type);
					fj.setFileType(type);
					fj.setFileSize(file.getSize());
					fj.setForeignId(id);
					
					if(bz != null){
						fj.setBackupOne(bz);
					}
				}
			}
			
			if(fj != null){
				if(list == null){
					list = new ArrayList<SysFujianDto>();
				}
				list.add(fj);
			}
		}
		
		return list;
	}
	

	/**
	 * 获取施工质量历史巡检页面
	 * 
	 * @return
	 */
	@RequestMapping(value = "/gczlSGZLLSPage", method = RequestMethod.GET)
	public String gczlSGZLLSPage(String token) {
		long startTime = System.currentTimeMillis();
		if(!StringUtils.isEmpty(token)){
			openCommServiceImpl.relogin(token);
		}
		long endTime = System.currentTimeMillis();
		System.out.println("gczlSGZLLSPage Session ID ="+this.request.getSession().getId()+" == "+
				"执行="+(endTime-startTime));
		return "open/gczl/gczlSGZLLS";
	}

	/**
	 * 获取施工获取待办事项页面
	 * @return
	 */
	@RequestMapping(value = "/gczlSGDCLSXPage", method = RequestMethod.GET)
	public String gczlSGDCLSXPage(String token) {
		long startTime = System.currentTimeMillis();
		if(!StringUtils.isEmpty(token)){
			openCommServiceImpl.relogin(token);
		}
		long endTime = System.currentTimeMillis();
		System.out.println("gczlSGDCLSXPage Session ID ="+this.request.getSession().getId()+" == "+
				"执行="+(endTime-startTime));
		return "open/gczl/gczlSGDCLSX";
	}

	/**
	 * 获取施工质量历史巡检页面
	 * 2016年12月17日15:04:10
	 * @return
	 */
	@RequestMapping(value = "/gczlSGZLLS")
	@ResponseBody
	public Object gczlSGZLLS(int page,String type) {
		User user = getCurrentUser();
		ProProjectinfo proinfo = openCommServiceImpl.getProByUserId(user.getId());
		PageInfo pageinfo = (PageInfo) openGczlServiceImpl.getSgzlList(proinfo.getId(),type, page, 10);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("lists", pageinfo.getRows());
		return map;
	}
	
	/**
	 * 获取施工质量巡检详情页面
	 * 2016年12月17日15:04:28
	 * @return
	 */
	@RequestMapping(value = "/gczlSGZLXJInfoPage", method = RequestMethod.GET)
	public String gczlSGZLXJInfoPage(Model model, String token, String id) {
		long startTime = System.currentTimeMillis();
		if(!StringUtils.isEmpty(token)){
			openCommServiceImpl.relogin(token);
		}
		GczlYdxjSGZLXJInfoDto info = openGczlServiceImpl.getSgzlInfo(id);
		model.addAttribute("info", info);
		
		try {
			List<SysFujianDto> zgq_fjs = sysFujianService.findFuJianListByForeignId(id, "整改前", null);
			model.addAttribute("zgq_fjs", zgq_fjs);
			List<SysFujianDto> zgh_fjs = sysFujianService.findFuJianListByForeignId(id, "整改后", null);
			model.addAttribute("zgh_fjs", zgh_fjs);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		System.out.println(JSONObject.toJSONString(info));
		long endTime = System.currentTimeMillis();
		System.out.println("gczlSGZLXJInfoPage Session ID ="+this.request.getSession().getId()+" == "+
				"执行="+(endTime-startTime));
		return "open/gczl/gczlSGZLXJInfo";
	}
	
	/**
	 * 上报施工质量巡检信息
	 * @param id
	 * @param tp   时间戳
	 * @return
	 */
    @RequestMapping("/gczlSGZLXJSB")
    public String gczlSGZLXJSB(Model model,String id,String tp,GczlYdxjSGZLXJInfoDto gczlYdxjSGZLXJInfoDto) {
    	String[] ids = {id};
    	if(!id.trim().equals(""))gczlYdxjSGZLXJInfoService.sbInfo(getCurrentUser(),gczlYdxjSGZLXJInfoDto);
    	//iOpenPushService.gczlPushMsg(getCurrentUser(),"/gczl/base/gczlYdxjSGZLXJInfo", "有新的施工质量巡检信息上报");
    	GczlYdxjSGZLXJInfo sginfo = gczlYdxjSGZLXJInfoService.findById(gczlYdxjSGZLXJInfoDto.getId());
    	iOpenPushService.gczlPushMsgV2("施工", "已上报", sginfo, getCurrentUser(),"/gczl/base/gczlYdxjSGZLXJInfo", "有新的施工质量巡检信息已上报");
    	log("上报", true, "移动端上报数据ID："+id);
    	
		try {
			List<SysFujianDto> zgq_fjs = sysFujianService.findFuJianListByForeignId(id, "整改前", null);
			model.addAttribute("zgq_fjs", zgq_fjs);
			List<SysFujianDto> zgh_fjs = sysFujianService.findFuJianListByForeignId(id, "整改后", null);
			model.addAttribute("zgh_fjs", zgh_fjs);
		} catch (Exception e) {
			e.printStackTrace();
		}
    	
    	GczlYdxjSGZLXJInfoDto info = openGczlServiceImpl.getSgzlInfo(id);
		model.addAttribute("info",info);
    	model.addAttribute("data", renderSuccess("提交成功"));
  		model.addAttribute("type","sb");
  		return "open/gczl/gczlSGZLXJInfo";
    }
    
	/**
	 * 审核施工质量巡检信息
	 * @param id
	 * @param zgry   整改人员
	 * @param shms   审核描述
	 * @param tp     时间戳
	 * @return
	 */
    @RequestMapping("/gczlSGZLXJSH")
    public Object gczlSGZLXJSH(Model model,GczlYdxjSGZLXJInfoDto gczlYdxjSGZLXJInfoDto) {
    	gczlYdxjSGZLXJInfoService.shInfo(gczlYdxjSGZLXJInfoDto, getCurrentUser());
    	//iOpenPushService.gczlPushMsg(getCurrentUser(),"/gczl/base/gczlYdxjSGZLXJInfo", "有新的施工质量巡检信息审核");
    	GczlYdxjSGZLXJInfo sginfo = gczlYdxjSGZLXJInfoService.findById(gczlYdxjSGZLXJInfoDto.getId());
    	iOpenPushService.gczlPushMsgV2("施工", "已审核", sginfo, getCurrentUser(),"/gczl/base/gczlYdxjSGZLXJInfo", "有新的施工质量巡检信息已审核");
    	log("上报", true, "移动端审核数据ID："+gczlYdxjSGZLXJInfoDto.getId());
    	
		try {
			List<SysFujianDto> zgq_fjs = sysFujianService.findFuJianListByForeignId(gczlYdxjSGZLXJInfoDto.getId(), "整改前", null);
			model.addAttribute("zgq_fjs", zgq_fjs);
			List<SysFujianDto> zgh_fjs = sysFujianService.findFuJianListByForeignId(gczlYdxjSGZLXJInfoDto.getId(), "整改后", null);
			model.addAttribute("zgh_fjs", zgh_fjs);
		} catch (Exception e) {
			e.printStackTrace();
		}
    	
    	GczlYdxjSGZLXJInfoDto info = openGczlServiceImpl.getSgzlInfo(gczlYdxjSGZLXJInfoDto.getId());
		model.addAttribute("info",info);
    	model.addAttribute("data", renderSuccess("操作成功"));
  		model.addAttribute("type","sb");
  		return "open/gczl/gczlSGZLXJInfo";
    }
    
	/**
	 * 整改施工质量巡检信息
	 * @param id
	 * @param zgtime   整改时间
	 * @param zgjg     整改结果
	 * @param tp     时间戳
	 * @return
	 */
    @RequestMapping("/gczlSGZLXJZG")
    public Object gczlSGZLXJZG(Model model,GczlYdxjSGZLXJInfoDto gczlYdxjSGZLXJInfoDto,
    		@RequestParam(value = "file", required = false) CommonsMultipartFile[] files, HttpServletRequest request) {
    	//替换整改时间格式
    	String zgtime = gczlYdxjSGZLXJInfoDto.getZgTime();
    	zgtime = zgtime.replace("T", " ");
    	if(zgtime.length() < 19){
    		zgtime = zgtime+":00";
    	}else if(zgtime.length() > 19){
    		zgtime = zgtime.substring(0,19);
    	}
    	gczlYdxjSGZLXJInfoDto.setZgTime(zgtime);
    	
    	gczlYdxjSGZLXJInfoService.zgInfo(gczlYdxjSGZLXJInfoDto, getCurrentUser());
    	//iOpenPushService.gczlPushMsg(getCurrentUser(),"/gczl/base/gczlYdxjSGZLXJInfo", "有新的施工质量巡检信息已整改");
    	GczlYdxjSGZLXJInfo sginfo = gczlYdxjSGZLXJInfoService.findById(gczlYdxjSGZLXJInfoDto.getId());
    	iOpenPushService.gczlPushMsgV2("施工", "已整改", sginfo, getCurrentUser(),"/gczl/base/gczlYdxjSGZLXJInfo", "有新的施工质量巡检信息已整改");
    	log("上报", true, "移动端整改数据ID："+gczlYdxjSGZLXJInfoDto.getId());
    	User user = getCurrentUser();
    	
		List<SysFujianDto> sfdtos = multFujianOpt(gczlYdxjSGZLXJInfoDto.getId(),files,user,"整改后");
		gczlOpenPushService.saveGPZLXJInfoPic(sfdtos, user);
		
		
		try {
			List<SysFujianDto> zgq_fjs = sysFujianService.findFuJianListByForeignId(gczlYdxjSGZLXJInfoDto.getId(), "整改前", null);
			model.addAttribute("zgq_fjs", zgq_fjs);
			List<SysFujianDto> zgh_fjs = sysFujianService.findFuJianListByForeignId(gczlYdxjSGZLXJInfoDto.getId(), "整改后", null);
			model.addAttribute("zgh_fjs", zgh_fjs);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
    	GczlYdxjSGZLXJInfoDto info = openGczlServiceImpl.getSgzlInfo(gczlYdxjSGZLXJInfoDto.getId());
		model.addAttribute("info",info);
    	model.addAttribute("data", renderSuccess("操作成功"));
  		model.addAttribute("type","sb");
  		return "open/gczl/gczlSGZLXJInfo";
    }
    

	
	/**
	 * 获取管片质量巡检页面
	 * 
	 * @return
	 */
	@RequestMapping(value = "/gczlGPZLXJAddPage", method = RequestMethod.GET)
	public String gczlGPZLXJAddPage(Model model, String token) {
		long startTime = System.currentTimeMillis();
		if (!StringUtils.isEmpty(token)) {
			openCommServiceImpl.relogin(token);
		}
		User user = getCurrentUser();
		ProProjectinfo proinfo = openCommServiceImpl.getProByUserId(user.getId());
		ProInfo pro = openCommServiceImpl.getProInfoByProId(proinfo.getId());
		model.addAttribute("user", user);
		model.addAttribute("pro", pro);
		List<DicInfo> dic = openGczlServiceImpl.getGpzlDDInfo();
		model.addAttribute("dic", dic);
		long endTime = System.currentTimeMillis();
		System.out.println("gczlGPZLXJAddPage Session ID ="+this.request.getSession().getId()+" == "+
				"执行="+(endTime-startTime));
		return "open/gczl/gczlGPZLXJ";
	}

	/**
	 * 提交管片质量数据
	 * 
	 * @return
	 */
	@RequestMapping(value = "/gczlGPZLXJAddDo", method = RequestMethod.POST)
	public Object gczlGPZLXJAddDo(Model model, GczlYdxjGPZLXJInfoDto gczlYdxjGPZLXJInfoDto,
			@RequestParam(value = "file", required = false) CommonsMultipartFile[] files, HttpServletRequest request) {
		String errmessage = gczlYdxjGPZLXJInfoService.checkClumIfexits(gczlYdxjGPZLXJInfoDto,
				new String[] { "typeName", "xlBh", "hh", "dw", "xjtime" });
		if (errmessage != null) {
			log(null, false, errmessage);
			return renderError(errmessage);
		}
		User user = getCurrentUser();
		String xjtime = gczlYdxjGPZLXJInfoDto.getXjtime();
		xjtime = xjtime.replace("T", " ");
		if (xjtime.length() < 19) {
			xjtime = xjtime + ":00";
		} else if (xjtime.length() > 19) {
			xjtime = xjtime.substring(0, 19);
		}
		gczlYdxjGPZLXJInfoDto.setXjtime(xjtime);
		
		String uuid = CommUtils.getUUID();
		gczlYdxjGPZLXJInfoDto.setId(uuid);
		gczlYdxjGPZLXJInfoService.insertDto(gczlYdxjGPZLXJInfoDto,user);
		List<SysFujianDto> sfdtos = multFujianOpt(uuid,files,user,"整改前");
		
		gczlOpenPushService.saveGPZLXJInfoPic(sfdtos, user);

		
		model.addAttribute("data", renderSuccess("提交成功"));
		model.addAttribute("isDo", true);

		ProProjectinfo proinfo = openCommServiceImpl.getProByUserId(user.getId());
		ProInfo pro = openCommServiceImpl.getProInfoByProId(proinfo.getId());
		model.addAttribute("user", user);
		model.addAttribute("pro", pro);
		List<DicInfo> dic = openGczlServiceImpl.getGpzlDDInfo();
		model.addAttribute("dic", dic);

		log(null, true, "移动端管片质量巡检信息添加成功！");
		
		return "open/gczl/gczlGPZLXJ";
	}

	/**
	 * 获取管片质量历史巡检页面
	 * 
	 * @return
	 */
	@RequestMapping(value = "/gczlGPZLLSPage", method = RequestMethod.GET)
	public String gczlGPZLLSPage(String token) {
		long startTime = System.currentTimeMillis();
		if(!StringUtils.isEmpty(token)){
			openCommServiceImpl.relogin(token);
		}
		long endTime = System.currentTimeMillis();
		System.out.println("gczlGPZLLSPage Session ID ="+this.request.getSession().getId()+" == "+
				"执行="+(endTime-startTime));
		return "open/gczl/gczlGPZLLS";
	}

	/**
	 * 获取管片获取待办事项页面
	 * @return
	 */
	@RequestMapping(value = "/gczlGPDCLSXPage", method = RequestMethod.GET)
	public String gczlGPDCLSXPage(String token) {
		long startTime = System.currentTimeMillis();
		if(!StringUtils.isEmpty(token)){
			openCommServiceImpl.relogin(token);
		}
		long endTime = System.currentTimeMillis();
		System.out.println("gczlGPDCLSXPage Session ID ="+this.request.getSession().getId()+" == "+
				"执行="+(endTime-startTime));
		return "open/gczl/gczlGPDCLSX";
	}
	
	/**
	 * 获取管片质量历史巡检页面
	 * 
	 * @return
	 */
	@RequestMapping(value = "/gczlGPZLLS")
	@ResponseBody
	public Object gczlGPZLLS(int page,String type) {
		User user = getCurrentUser();
		ProProjectinfo proinfo = openCommServiceImpl.getProByUserId(user.getId());
		PageInfo pageinfo = (PageInfo)openGczlServiceImpl.getGpzlList(proinfo.getId(), type, page, 10);
		Map<String,Object> map = new HashMap<String,Object>();
		if(pageinfo.getTotal()<(page-1)*10){
			map.put("lists", new ArrayList<>());
		}else{
			map.put("lists", pageinfo.getRows());
		}
		
		return map;
	}

	/**
	 * 获取管片质量巡检详情页面
	 * 
	 * @return
	 */
	@RequestMapping(value = "/gczlGPZLXJInfoPage", method = RequestMethod.GET)
	public String gczlGPZLXJInfoPage(Model model, String token, String id) {
		long startTime = System.currentTimeMillis();
		if (!StringUtils.isEmpty(token)) {
			openCommServiceImpl.relogin(token);
		}
		GczlYdxjGPZLXJInfoDto info = openGczlServiceImpl.getGpzlInfo(id);
		model.addAttribute("info", info);
		
		try {
			List<SysFujianDto> zgq_fjs = sysFujianService.findFuJianListByForeignId(id, "整改前", null);
			model.addAttribute("zgq_fjs", zgq_fjs);
			List<SysFujianDto> zgh_fjs = sysFujianService.findFuJianListByForeignId(id, "整改后", null);
			model.addAttribute("zgh_fjs", zgh_fjs);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		System.out.println(JSONObject.toJSONString(info));
		long endTime = System.currentTimeMillis();
		System.out.println("gczlGPZLXJInfoPage Session ID ="+this.request.getSession().getId()+" == "+
				"执行="+(endTime-startTime));
		return "open/gczl/gczlGPZLXJInfo";
	}

	/**
	 * 上报管片质量巡检信息
	 * @param id
	 * @param tp   时间戳
	 * @return
	 */
    @RequestMapping("/gczlGPZLXJSB")
    public String gczlGPZLXJSB(Model model,String id,String tp,GczlYdxjGPZLXJInfoDto gczlYdxjGPZLXJInfoDto) {
    		
    	if(!id.trim().equals(""))gczlYdxjGPZLXJInfoService.sendUp(getCurrentUser(),gczlYdxjGPZLXJInfoDto);
    	GczlYdxjGPZLXJInfo gpinfo = gczlYdxjGPZLXJInfoService.findById(gczlYdxjGPZLXJInfoDto.getId());
    	//iOpenPushService.gczlPushMsg(getCurrentUser(),"/gczl/base/gczlYdxjGPZLXJInfo", "有新的管片质量巡检信息上报");
    	iOpenPushService.gczlPushMsgV2("管片", "已上报", gpinfo, getCurrentUser(),"/gczl/base/gczlYdxjGPZLXJInfo", "有新的管片质量巡检信息已上报");
    	log("上报", true, "移动端上报数据ID："+id);
    	
		try {
			List<SysFujianDto> zgq_fjs = sysFujianService.findFuJianListByForeignId(gczlYdxjGPZLXJInfoDto.getId(), "整改前", null);
			model.addAttribute("zgq_fjs", zgq_fjs);
			List<SysFujianDto> zgh_fjs = sysFujianService.findFuJianListByForeignId(gczlYdxjGPZLXJInfoDto.getId(), "整改后", null);
			model.addAttribute("zgh_fjs", zgh_fjs);
		} catch (Exception e) {
			e.printStackTrace();
		}
    	
    	GczlYdxjGPZLXJInfoDto info = openGczlServiceImpl.getGpzlInfo(id);
		model.addAttribute("info",info);
    	model.addAttribute("data", renderSuccess("提交成功"));
  		model.addAttribute("type","sb");
  		return "open/gczl/gczlGPZLXJInfo";
    }
    
	/**
	 * 审核管片质量巡检信息
	 * @param id
	 * @param zgry   整改人员
	 * @param shms   审核描述
	 * @param tp     时间戳
	 * @return
	 */
    @RequestMapping("/gczlGPZLXJSH")
    public Object gczlGPZLXJSH(Model model,GczlYdxjGPZLXJInfoDto gczlYdxjGPZLXJInfoDto) {
    	String errorMessage = "";
		errorMessage = gczlYdxjGPZLXJInfoService.shengHe(gczlYdxjGPZLXJInfoDto, getCurrentUser());
		
		try {
			List<SysFujianDto> zgq_fjs = sysFujianService.findFuJianListByForeignId(gczlYdxjGPZLXJInfoDto.getId(), "整改前", null);
			model.addAttribute("zgq_fjs", zgq_fjs);
			List<SysFujianDto> zgh_fjs = sysFujianService.findFuJianListByForeignId(gczlYdxjGPZLXJInfoDto.getId(), "整改后", null);
			model.addAttribute("zgh_fjs", zgh_fjs);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if(StringUtils.isNotBlank(errorMessage)){
			log(gczlYdxjGPZLXJInfoDto.getShzt(), false, errorMessage);
			
			GczlYdxjGPZLXJInfoDto info = openGczlServiceImpl.getGpzlInfo(gczlYdxjGPZLXJInfoDto.getId());
			model.addAttribute("info",info);
			model.addAttribute("data", renderError(errorMessage));
	  		model.addAttribute("type","sh");
	  		return "open/gczl/gczlGPZLXJInfo";
		}
		
		GczlYdxjGPZLXJInfoDto dbDto = gczlYdxjGPZLXJInfoService.findDtoById(gczlYdxjGPZLXJInfoDto.getId());
    	String qjAndXl = dbDto.getQlName()+dbDto.getXlName();
    	GczlYdxjGPZLXJInfo gpinfo = gczlYdxjGPZLXJInfoService.findById(gczlYdxjGPZLXJInfoDto.getId());
		//iOpenPushService.gczlPushMsg(getCurrentUser(),"/gczl/base/gczlYdxjGPZLXJInfo", qjAndXl+"有新的管片质量巡检信息已审核");
    	iOpenPushService.gczlPushMsgV2("管片", "已审核", gpinfo, getCurrentUser(),"/gczl/base/gczlYdxjGPZLXJInfo", qjAndXl+"有新的管片质量巡检信息已审核");
		log(gczlYdxjGPZLXJInfoDto.getShzt(), true, "审核操作：成功!");
		
		GczlYdxjGPZLXJInfoDto info = openGczlServiceImpl.getGpzlInfo(gczlYdxjGPZLXJInfoDto.getId());
		model.addAttribute("info",info);
		model.addAttribute("data", renderSuccess("审核操作：成功!"));
  		model.addAttribute("type","sh");
    	return "open/gczl/gczlGPZLXJInfo";
    }
    
	/**
	 * 整改管片质量巡检信息
	 * @param id
	 * @param zgtime   整改时间
	 * @param zgjg     整改结果
	 * @param tp     时间戳
	 * @return
	 */
    @RequestMapping("/gczlGPZLXJZG")
    public Object gczlGPZLXJZG(Model model,GczlYdxjGPZLXJInfoDto gczlYdxjGPZLXJInfoDto,
    		@RequestParam(value = "file", required = false) CommonsMultipartFile[] files, HttpServletRequest request) {
    	String zgtime = gczlYdxjGPZLXJInfoDto.getZgtime();
    	zgtime = zgtime.replace("T", " ");
    	if(zgtime.length() < 19){
    		zgtime = zgtime+":00";
    	}else if(zgtime.length() > 19){
    		zgtime = zgtime.substring(0,19);
    	}
    	gczlYdxjGPZLXJInfoDto.setZgtime(zgtime);
    	
    	User user = getCurrentUser();
    	
		List<SysFujianDto> sfdtos = multFujianOpt(gczlYdxjGPZLXJInfoDto.getId(),files,user,"整改后");
		gczlOpenPushService.saveGPZLXJInfoPic(sfdtos, user);
    	
    	String errorMessage = "";
		errorMessage = gczlYdxjGPZLXJInfoService.zhengGai(gczlYdxjGPZLXJInfoDto, getCurrentUser());
		if(StringUtils.isNotBlank(errorMessage)){
			log("整改", false, errorMessage);
			
			GczlYdxjGPZLXJInfoDto info = openGczlServiceImpl.getGpzlInfo(gczlYdxjGPZLXJInfoDto.getId());
			model.addAttribute("info",info);
			model.addAttribute("data", renderError(errorMessage));
	  		model.addAttribute("type","zg");
	  		return "open/gczl/gczlGPZLXJInfo";
		}
		
		try {
			List<SysFujianDto> zgq_fjs = sysFujianService.findFuJianListByForeignId(gczlYdxjGPZLXJInfoDto.getId(), "整改前", null);
			model.addAttribute("zgq_fjs", zgq_fjs);
			List<SysFujianDto> zgh_fjs = sysFujianService.findFuJianListByForeignId(gczlYdxjGPZLXJInfoDto.getId(), "整改后", null);
			model.addAttribute("zgh_fjs", zgh_fjs);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		GczlYdxjGPZLXJInfoDto dbDto = gczlYdxjGPZLXJInfoService.findDtoById(gczlYdxjGPZLXJInfoDto.getId());
    	String qjAndXl = dbDto.getQlName()+dbDto.getXlName();
    	GczlYdxjGPZLXJInfo gpinfo = gczlYdxjGPZLXJInfoService.findById(gczlYdxjGPZLXJInfoDto.getId());
		//iOpenPushService.gczlPushMsg(getCurrentUser(),"/gczl/base/gczlYdxjGPZLXJInfo", qjAndXl+"有新的管片质量巡检信息已整改");
    	iOpenPushService.gczlPushMsgV2("管片", "已整改", gpinfo, getCurrentUser(),"/gczl/base/gczlYdxjGPZLXJInfo", qjAndXl+"有新的管片质量巡检信息已整改");
		
		log("整改", true, "整改操作：成功!");
		
		GczlYdxjGPZLXJInfoDto info = openGczlServiceImpl.getGpzlInfo(gczlYdxjGPZLXJInfoDto.getId());
		model.addAttribute("info",info);
		model.addAttribute("data", renderSuccess("整改操作：成功!"));
  		model.addAttribute("type","zg");
    	return "open/gczl/gczlGPZLXJInfo";
    }
    

	/**
	 * 获取管片报表页面
	 * 
	 * @return
	 */
	@RequestMapping(value = "/gczlGPReportPage", method = RequestMethod.GET)
	public String gczlGPReportPage(String token) {
		if(!StringUtils.isEmpty(token)){
			openCommServiceImpl.relogin(token);
		}
		return "open/gczl/gczlGPReport";
	}

	/**
	 * 获取施工报表页面
	 * 
	 * @return
	 */
	@RequestMapping(value = "/gczlSGReportPage", method = RequestMethod.GET)
	public String gczlSGReportPage(String token) {
		if(!StringUtils.isEmpty(token)){
			openCommServiceImpl.relogin(token);
		}
		return "open/gczl/gczlSGReport";
	}
	
	/**
	 * 获取报表页面
	 * 
	 * @return
	 */
	@RequestMapping(value = "/gczlReportPage", method = RequestMethod.GET)
	public String gczlReportPage(Model model,String token) {
		long startTime = System.currentTimeMillis();
		if(!StringUtils.isEmpty(token)){
			openCommServiceImpl.relogin(token);
		}
		User user = getCurrentUser();
		ProProjectinfo proinfo = openCommServiceImpl.getProByUserId(user.getId());
		ProInfo pro = openCommServiceImpl.getProInfoByProId(proinfo.getId());
		model.addAttribute("user", user);
		model.addAttribute("pro", pro);
		long endTime = System.currentTimeMillis();
		System.out.println("gczlReportPage Session ID ="+this.request.getSession().getId()+" == "+
				"执行="+(endTime-startTime));
		return "open/gczl/gczlReport";
	}
	
	/**
	 * 获取报表结果
	 * 
	 * @return
	 */
	@RequestMapping(value = "/gczlReportDo")
	public String gczlReportPage(String gcBh,String cxDate) {
		
    	StringBuffer url = new StringBuffer();
    	url.append("report/reportJsp/preview.jsp?");
    	//获取查询参数url
    	url.append("rpx=/report/reportFiles/gczlDay.rpx&rpxHome=&dfxHome=&proid=");
    	//获取用户部门信息
    	url.append(gcBh);
    	cxDate = cxDate.replace("\\", "-");
    	url.append("&oneDay="+cxDate);
        return "redirect:/"+url.toString();
	}

	/**
	 * 获取工程质量个推消息记录
	 * 
	 * @return
	 */
	@RequestMapping(value = "/gczlPushMessage")
	@ResponseBody
	public Object getGczlMessageInfo (int page) {
		PageInfo pageInfo = (PageInfo) openGczlServiceImpl.getGczlMessageList(page,10,getUserId());
		Object json = null;
		if(pageInfo.getTotal()<(page-1)*10){
			json = new JSONArray();
		}else{
			json = JSONArray.toJSON(pageInfo.getRows());
		}
		
		return renderSuccess(json);
	}
	
	
    /**
     * 方法说明：整改再次提醒
     * @param id 数据ID
     * @param type 数据类型，gp：管片、sg：施工
     * @return 若成功返回成功信息，失败返回失败提示
     * @author xhguo 
     * @Time 2017年2月20日16:52:23
     */
	@RequestMapping(value = "/optGPZgTx")
	public Object optGPZgTx(Model model,GczlYdxjGPZLXJInfoDto gczlYdxjGPZLXJInfoDto){
		//gczlYdxjGPZLXJInfoService.shengHe(gczlYdxjGPZLXJInfoDto, getCurrentUser());

		
		GczlYdxjGPZLXJInfoDto dbDto = gczlYdxjGPZLXJInfoService.findDtoById(gczlYdxjGPZLXJInfoDto.getId());
    	String qjAndXl = dbDto.getQlName()+dbDto.getXlName();
		//iOpenPushService.gczlPushMsg(getCurrentUser(),"/gczl/base/gczlYdxjGPZLXJInfo", qjAndXl+"有新的管片质量巡检信息待整改提醒");
		GczlYdxjGPZLXJInfo gpinfo = gczlYdxjGPZLXJInfoService.findById(gczlYdxjGPZLXJInfoDto.getId());
    	iOpenPushService.gczlPushMsgV2("管片", "待整改", gpinfo, getCurrentUser(),"/gczl/base/gczlYdxjGPZLXJInfo", "有新的管片质量巡检信息待整改");
		log(gczlYdxjGPZLXJInfoDto.getShzt(), true, "待整改提醒操作：成功!");
		
		GczlYdxjGPZLXJInfoDto info = openGczlServiceImpl.getGpzlInfo(gczlYdxjGPZLXJInfoDto.getId());
		model.addAttribute("info",info);
		model.addAttribute("data", renderSuccess("待整改提醒操作：成功!"));
  		model.addAttribute("type","sh");
    	
		return "open/gczl/gczlGPZLXJInfo";
    	
	}
	
    /**
     * 方法说明：整改再次提醒
     * @param id 数据ID
     * @return 若成功返回成功信息，失败返回失败提示
     * @author xhguo 
     * @Time 2017年2月20日16:52:23
     */
	@RequestMapping(value = "/optSGZgTx")
	public Object optSGZgTx(Model model,GczlYdxjSGZLXJInfoDto gczlYdxjSGZLXJInfoDto){
		//gczlYdxjSGZLXJInfoService.shInfo(gczlYdxjSGZLXJInfoDto, getCurrentUser());
    	//iOpenPushService.gczlPushMsg(getCurrentUser(),"/gczl/base/gczlYdxjSGZLXJInfo", "有新的施工质量巡检信息待整改提醒");
    	GczlYdxjSGZLXJInfo sginfo = gczlYdxjSGZLXJInfoService.findById(gczlYdxjSGZLXJInfoDto.getId());
    	iOpenPushService.gczlPushMsgV2("施工", "待整改", sginfo, getCurrentUser(),"/gczl/base/gczlYdxjSGZLXJInfo", "有新的施工质量巡检信息待整改");
    	log("待整改", true, "移动端审核数据ID："+gczlYdxjSGZLXJInfoDto.getId());
    	
		try {
			List<SysFujianDto> zgq_fjs = sysFujianService.findFuJianListByForeignId(gczlYdxjSGZLXJInfoDto.getId(), "整改前", null);
			model.addAttribute("zgq_fjs", zgq_fjs);
			List<SysFujianDto> zgh_fjs = sysFujianService.findFuJianListByForeignId(gczlYdxjSGZLXJInfoDto.getId(), "整改后", null);
			model.addAttribute("zgh_fjs", zgh_fjs);
		} catch (Exception e) {
			e.printStackTrace();
		}
    	
    	GczlYdxjSGZLXJInfoDto info = openGczlServiceImpl.getSgzlInfo(gczlYdxjSGZLXJInfoDto.getId());
		model.addAttribute("info",info);
    	model.addAttribute("data", renderSuccess("待整改提醒操作：成功!"));
  		model.addAttribute("type","sh");
  		return "open/gczl/gczlSGZLXJInfo";
    	
	}
}
