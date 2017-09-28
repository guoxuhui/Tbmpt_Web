package com.crfeb.tbmpt.risk.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.crfeb.tbmpt.commons.base.BaseController;
import com.crfeb.tbmpt.commons.result.Tree;
import com.crfeb.tbmpt.commons.utils.*;
import com.crfeb.tbmpt.open.service.IOpenPushService;
import com.crfeb.tbmpt.project.model.ProPqInfo;
import com.crfeb.tbmpt.project.model.ProProjectinfo;
import com.crfeb.tbmpt.project.service.IProPqInfoService;
import com.crfeb.tbmpt.project.service.IProProjectinfoService;
import com.crfeb.tbmpt.project.service.IProRProjectSectionService;
import com.crfeb.tbmpt.project.service.IProRSectionLineService;
import com.crfeb.tbmpt.risk.model.RiskInfo;
import com.crfeb.tbmpt.risk.model.RiskLevel;
import com.crfeb.tbmpt.risk.model.vo.RiskInfoQueryVo;
import com.crfeb.tbmpt.risk.model.vo.RiskInfoVo;
import com.crfeb.tbmpt.risk.service.IRiskInfoService;
import com.crfeb.tbmpt.risk.service.IRiskLevelService;
import com.crfeb.tbmpt.sys.model.Orgz;
import com.crfeb.tbmpt.sys.model.SysEmployee;
import com.crfeb.tbmpt.sys.model.User;
import com.crfeb.tbmpt.sys.service.IOrgzService;
import com.crfeb.tbmpt.sys.service.ISysEmployeeService;
import com.crfeb.tbmpt.sys.service.IUserService;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.util.*;

/**
 * 风险上报管理
 * @author Administrator
 *
 */
@Controller
@RequestMapping("/risk/up")
public class RiskInfoUpController extends BaseController {
	
	private static final Logger LOGGER = LogManager.getLogger(RiskInfoUpController.class);
			
	@Autowired	
	private IRiskInfoService iRiskInfoService;
	@Autowired
    private IProRSectionLineService proRSectionLineService;
    @Autowired
    private IProRProjectSectionService proRProjectSectionService;
    @Autowired
    private IProProjectinfoService proProjectinfoService;
    @Autowired	
	private IRiskLevelService iRiskLevelService;
    @Autowired
    private ISysEmployeeService sysEmployeeService;
    @Autowired
    private IOrgzService orgzService;
    @Autowired
	private IProPqInfoService proPqInfoService;
    
	/**
     * @return
     */
    @RequestMapping(value = "", method = RequestMethod.GET)
    public String list() {
        return "risk/up/index";
    }
    
	/**
     * @return
     */
    @RequestMapping(value = "/indexPro", method = RequestMethod.GET)
    public String listPro(Model model,String proName,String startUpTime,String endUpTime) {
    		Map<String,Object> map = new HashMap<String,Object>();
    		map.put("abbreviation",proName);
    		List<ProProjectinfo> infos = proProjectinfoService.selectByMap(map);
    		model.addAttribute("proName", infos.get(0).getId());
    		if(StringUtils.isNotBlank(startUpTime))
    		model.addAttribute("startUpTime", startUpTime);
    		if(StringUtils.isNotBlank(endUpTime))
    		model.addAttribute("endUpTime", endUpTime);
        return "risk/up/indexPro";
    }

    /**
     * @param page
     * @param rows
     * @param sort
     * @param order
     * @return
     */
    @RequestMapping(value = "/dataGrid", method = RequestMethod.POST)
    @ResponseBody
    public Object dataGrid(RiskInfoQueryVo vo,Integer page, Integer rows, String sort, String order) {
        PageInfo pageInfo = new PageInfo(page, rows, sort, order);
        Map<String, Object> condition = new HashMap<String, Object>();
        if(vo.getStartUpTime() != null && vo.getStartUpTime().equals("")) {
        		vo.setStartUpTime(null);
        }
        if(vo.getEndUpTime() != null && vo.getEndUpTime().equals("")) {
    			vo.setEndUpTime(null);
        }
        conditionSearchMap(condition, vo);
        
        //根据用户id查出用户有哪些项目
  		List<ProProjectinfo> proj = proProjectinfoService.getProjectInfosByUserId(getCurrentUser().getId()); 
  		if(null == proj)
  			return pageInfo;
  			
  		List<String> ls = new ArrayList<String>();
  		for(ProProjectinfo info:proj){
  			ls.add("'"+info.getId()+"'");
  		}
  		String instr = StringUtils.ListToString(ls, ",");
  		//用查出的所有项目id做为查询条件
  		condition.put("ids",instr);
        
        pageInfo.setCondition(condition);
        iRiskInfoService.selectDataGrid(pageInfo,getCurrentUser());
        pageInfoRows(pageInfo.getRows());
        return pageInfo;
    }
    
    //搜索对象
    public void conditionSearchMap(Map<String, Object> condition,RiskInfoQueryVo vo){
    	if (StringUtils.isNotBlank(vo.getProName())) {
            condition.put("proName", vo.getProName());
        }
        if (StringUtils.isNotBlank(vo.getStartUpTime())) {
            condition.put("startUpTime", vo.getStartUpTime());
        }
        if (StringUtils.isNotBlank(vo.getEndUpTime())) {
            condition.put("endUpTime", vo.getEndUpTime()+" 23:59:59");
        }
        if (StringUtils.isNotBlank(vo.getEmpId())) {
            condition.put("empId", vo.getEmpId());
        }
        if (StringUtils.isNotBlank(vo.getIsOut())) {
            condition.put("isOut", vo.getIsOut());
        }
        if (StringUtils.isNotBlank(vo.getRikeLevel())) {
            condition.put("rikeLevel", vo.getRikeLevel());
        }
        if (StringUtils.isNotBlank(vo.getRikeTimeStart())) {
            condition.put("rikeTimeStart", vo.getRikeTimeStart());
        }
		if (StringUtils.isNotBlank(vo.getRikeTimeEnd())) {
			condition.put("rikeTimeEnd", vo.getRikeTimeEnd());
		}
        if (StringUtils.isNotBlank(vo.getUpUser())) {
            condition.put("upUser", vo.getUpUser());
        }
    }
    
//    /**
//     * @return
//     */
//    @RequestMapping(value = "/addPage", method = RequestMethod.GET)
//    public String addPage() {
//        return "risk/info/add";
//    }
    
    /**
     * @param t
     * @return
     */
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public Object add(RiskInfo t) {
		t.setRikeDesc(org.apache.commons.lang.StringEscapeUtils.unescapeHtml(t.getRikeDesc()));
		t.setMainControlMethod(org.apache.commons.lang.StringEscapeUtils.unescapeHtml(t.getMainControlMethod()));
		t.setRemark(org.apache.commons.lang.StringEscapeUtils.unescapeHtml(t.getRemark()));
        User user = this.getCurrentUser();
        try {
        	
        	String dpts = t.getDpts();
	    	if(org.apache.commons.lang.StringUtils.isNotBlank(dpts)){
	    		String[] dptsArray = dpts.split(",");
	    		if(dptsArray.length>5){
	    			LOGGER.info("上报风险信息失败。");
	    			log(null, false, "用户："+user.getName()+",上报风险信息失败!");
	    			return renderError("最多选择5个部门");
	    		}
	    		
	    	}
	    	String persoon = t.getPersoon();
	    	if(org.apache.commons.lang.StringUtils.isNotBlank(dpts)){
	    		String[] persoonArray = persoon.split(",");
	    		if(persoonArray.length>5){
	    			LOGGER.info("上报风险信息失败。");
	    			log(null, false, "用户："+user.getName()+",上报风险信息失败!");
	    			return renderError("最多选择5个负责人");
	    		}
	    		
	    	}
	    	
	    	String startTime = t.getRikeTimeStart();
	    	long start = Long.valueOf(startTime.replaceAll("[-\\s:]",""));
	    	String endTime = t.getRikeTimeEnd();
	    	long end = Long.valueOf(endTime.replaceAll("[-\\s:]",""));
	    	if(end < start){
	    		LOGGER.info("上报风险信息失败。");
				log(null, false, "用户："+user.getName()+",上报风险信息失败!");
	    		return renderError("结束时间必须大于开始时间");
	    	}
    	
    		//设置片区 从项目信息里获取
		t.setSection(proProjectinfoService.selectByProId(t.getProName()).getArea());
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
			t.setUpUserPhone(sysEmployeeService.selectById(empId).getPhone());
		}
    		iRiskInfoService.save(t);
            
            LOGGER.info("上报风险信息成功。");
        	this.log(null, true, "用户："+user.getName()+"，上报风险，风险描述："+t.getRikeDesc());
            return renderSuccess("上报成功！");
		} catch (Exception e) {
			e.printStackTrace();
			LOGGER.info("上报风险信息失败。");
			log(null, false, "用户："+user.getName()+",上报风险信息失败!");
			return renderError("上报失败！");
		}
        
    }
    
    /**
     * @return
     */
    @RequestMapping("/delete")
    @ResponseBody
    public Object delete(String ids) {
    	User user = this.getCurrentUser();
    	try {
    		
        	String[] idss = ids.split(",");
        	List<String> idlist = new ArrayList<String>();
        	Collections.addAll(idlist, idss);
        	Boolean falt = iRiskInfoService.deleteBatchIds(idlist);
        	if(falt){
        		LOGGER.info("风险上报信息删除数据成功。");
            	this.log(null, true, "用户："+user.getName()+",删除了风险上报,风险ids:"+ids.toString());
        		return renderSuccess("删除成功！");
        	}else{
        		LOGGER.info("删除风险上报信息失败！");
        		log(null, false, "用户："+user.getName()+",删除风险上报信息失败!");
        		return renderSuccess("删除异常！");
        	}
		} catch (Exception e) {
			e.printStackTrace();
			log(null, false, "用户："+user.getName()+",删除风险上报信息失败!");
			return renderError("删除失败！");
		}
        
    }
    /**
     * @param model
     * @param id
     * @return
     */
    @RequestMapping("/editPage")
    public String editPage(Model model, String id) {
    	RiskInfo t = iRiskInfoService.selectById(id);
        model.addAttribute("model", t);
        return "risk/info/edit";
    }
    /**
     * 获取项目信息
     * @return
     */
    @RequestMapping(value = "/getProDic")
    @ResponseBody
    public Object getDic() {
    	User user = getCurrentUser();
    	List<ProProjectinfo> list = proProjectinfoService.getProjectInfosByUserId(String.valueOf(user.getId()));
    	return list;
    }
    /**
     */
    @RequestMapping("/edit")
    @ResponseBody
    public Object edit(RiskInfo t) {
	t.setRikeDesc(org.apache.commons.lang.StringEscapeUtils.unescapeHtml(t.getRikeDesc()));
	t.setMainControlMethod(org.apache.commons.lang.StringEscapeUtils.unescapeHtml(t.getMainControlMethod()));
	t.setRemark(org.apache.commons.lang.StringEscapeUtils.unescapeHtml(t.getRemark()));
	User user = this.getCurrentUser();
	try {
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
		t.setSection(proProjectinfoService.selectByProId(t.getProName()).getArea());
    	String orgzId=user.getOrgzId();
    	Orgz orgz = orgzService.selectById(orgzId);
    	//项目部的人员风险级别都设为0级
    	if(orgz!=null&&orgz.getType()==2){
    		t.setRikeLevel("a76f1c4bd37b4faeb33116c7f21a994f");
    	}
    	iRiskInfoService.updateSelectiveById(t);
        LOGGER.info("修改风险上报信息成功。");
    	this.log(null, true, "用户："+user.getName()+",修改了风险,风险id:"+t.getId());
        return renderSuccess("编辑成功！");
	} catch (Exception e) {
		e.printStackTrace();
		log(null, false, "用户："+user.getName()+",修改风险上报信息失败!");
		return renderError("编辑失败！");
	}
    }
    
    
    /**
     * 数据导出
     * @param exportDto
     * @param response
     */
	@SuppressWarnings({"unchecked", "rawtypes"})
	@RequestMapping(value="/expXls")
	public void expXls(ExportDto exportDto,RiskInfoQueryVo vo, HttpServletResponse response){
		try {
			if(StringUtils.isNotBlank(exportDto.getIds())){
				List<String> idsList =new ArrayList<String>();
				Collections.addAll(idsList, exportDto.getIds().split(ExportDto.SPLIT_STR));
				List list = iRiskInfoService.selectBatchIds(idsList);
				pageInfoRows(list);
				ExportExcelUtil.writeExcelToClient(response, exportDto, ExportSetUtil.objectToListMap(expData(exportDto,list)));
			}else{
		        PageInfo pageInfo = new PageInfo(0, 65530, "id", "desc");
		        Map<String, Object> condition = new HashMap<String, Object>();
		        conditionSearchMap(condition, vo);
		        pageInfo.setCondition(condition);
		        iRiskInfoService.selectDataGrid(pageInfo);
		        pageInfoRows(pageInfo.getRows());
				ExportExcelUtil.writeExcelToClient(response, exportDto, ExportSetUtil.objectToListMap(expData(exportDto,pageInfo.getRows())));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 导出数据整理
	 * @param pageInfo
	 * @return
	 */
	private List expData(ExportDto exportDto,List pageInfo){
		List riskInfoList=pageInfo;
        for (int i=0;i<riskInfoList.size();i++) {
        	if(riskInfoList.get(i) instanceof RiskInfo){
        		if(i==0){
//        			removeExportDTOFields(exportDto, false);
        			removeExportDTOFields(exportDto, true);
        		}
        		RiskInfo riskInfo = (RiskInfo) riskInfoList.get(i);
        		if("0".equals(riskInfo.getIsOut())){
        			riskInfo.setIsOut("未排除");
        		}else{
        			riskInfo.setIsOut("已排除");
        		}
        		int index = riskInfo.getRikeLevel().indexOf(",");
        		if(index >-1){
        			riskInfo.setRikeLevel(riskInfo.getRikeLevel().substring(0,riskInfo.getRikeLevel().indexOf(",")));
        		}
    		}else{
        		if(i==0){
//        			removeExportDTOFields(exportDto, false);
        			removeExportDTOFields(exportDto, true);
        		}
        		RiskInfoVo riskInfo = (RiskInfoVo) riskInfoList.get(i);
        		if("0".equals(riskInfo.getIsOut())){
        			riskInfo.setIsOut("未排除");
        		}else{
        			riskInfo.setIsOut("已排除");
        		}
        		int index = riskInfo.getRikeLevelStr().indexOf(",");
        		if(index >-1){
        			riskInfo.setRikeLevel(riskInfo.getRikeLevelStr().substring(0,index));
        		}
        		
        	}
		}
        return riskInfoList;
	}
	
	/**
	 * 删除导出字段
	 * @param exportDto
	 */
	private void removeExportDTOFields(ExportDto exportDto,boolean isVo){
		String fieldsNew = "";
		String titlesNew = "";
		String fields = exportDto.getFields();
		String titles = exportDto.getTitles();
		String[] fieldsArray = fields.split(",");
		String[] titlesArray = titles.split(",");
		int falg = 0;
		for(int i=0;i<fieldsArray.length;i++){
			if(falg>0){
				fieldsNew += ",";
				titlesNew += ",";
			}
			if(isVo){//vo
				if(!"sectionStr".equals(fieldsArray[i]) && !"dpts".equals(fieldsArray[i]) && !"persoon".equals(fieldsArray[i]) && !"rikeLevelStr".equals(fieldsArray[i])){
					fieldsNew += fieldsArray[i];
					titlesNew += titlesArray[i];
					falg++;
				}
			}else{//不是vo
				if(!"sectionStr".equals(fieldsArray[i]) && !"dptsStr".equals(fieldsArray[i]) && !"upUserName".equals(fieldsArray[i]) && !"persoonStr".equals(fieldsArray[i]) && !"rikeLevelStr".equals(fieldsArray[i])){
					fieldsNew += fieldsArray[i];
					titlesNew += titlesArray[i];
					falg++;
				}
				if("upUserName".equals(fieldsArray[i])){
					fieldsNew += "upUser";
					titlesNew += titlesArray[i];
				}
			}
		}
		exportDto.setFields(fieldsNew);
		exportDto.setTitles(titlesNew);
	}
	
	/**
     * 数据导出
     * @param exportDto
     * @param response
     */
	@SuppressWarnings({"unchecked", "rawtypes"})
	@RequestMapping(value="/expPdf")
	public void expPdf(ExportDto exportDto,RiskInfoQueryVo vo, HttpServletResponse response){
		try {
			if(StringUtils.isNotBlank(exportDto.getIds())){
				List<String> idsList =new ArrayList<String>();
				Collections.addAll(idsList, exportDto.getIds().split(ExportDto.SPLIT_STR));
				List list = iRiskInfoService.selectBatchIds(idsList);
				pageInfoRows(list);
				ExportPdfUtil.writePdfToClient(response, exportDto, ExportSetUtil.objectToListMap(expData(exportDto,list)));
			}else{
		        PageInfo pageInfo = new PageInfo(0, 65530, "id", "desc");
		        Map<String, Object> condition = new HashMap<String, Object>();
		        conditionSearchMap(condition, vo);
		        pageInfo.setCondition(condition);
		        iRiskInfoService.selectDataGrid(pageInfo);
		        pageInfoRows(pageInfo.getRows());
		        ExportPdfUtil.writePdfToClient(response, exportDto, ExportSetUtil.objectToListMap(expData(exportDto,pageInfo.getRows())));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * 查找风险上报详细信息
	 */
	@RequestMapping(value = "/showPage", method = RequestMethod.GET)
    public String showPage(Model model,String id){
		RiskInfo riskInfo = iRiskInfoService.selectById(id);
    	String proId=riskInfo.getProName();
    	if(proId!=null&&!proId.equals("")){
    		riskInfo.setProName(proProjectinfoService.selectById(proId).getProName());	
    	}
    	riskInfo.setUpUser(userService.selectById(riskInfo.getUpUser()).getName());
    	String  riskLevel=riskInfo.getRikeLevel();
    	if(riskLevel!=null&&!riskLevel.equals("")){
    		riskInfo.setRikeLevel(iRiskLevelService.selectById(riskLevel).getLevelName()+","+iRiskLevelService.selectById(riskLevel).getColorFlag());	
    	}
    	//处理所选员工Id集合
    	String empIds=riskInfo.getPersoon();
    	if(empIds!=null&&!empIds.equals("")){
    		String[] empIdsArray=new String[5];
        	if(empIds.indexOf(",")!=-1){
        		empIdsArray=empIds.split(",");
        	}else{
        		empIdsArray[0]=empIds;
        	}
        	List<String> empList=new ArrayList<String>();
        	for (int i = 0; i < empIdsArray.length; i++) {
				String emp = empIdsArray[i];
				empList.add(emp);
			}
        	List<SysEmployee> empObjList=sysEmployeeService.selectBatchIds(empList);
        	String strTmp="";
        	for (SysEmployee sysEmployee : empObjList) {
        		strTmp+=sysEmployee.getName()+",";
			}
        	if(strTmp!=""){
        		strTmp=strTmp.substring(0, strTmp.length()-1);        		
        	}
        	riskInfo.setPersoon(strTmp);
    	}
    	String dptIds=riskInfo.getDpts();
    	if(dptIds!=null&&!dptIds.equals("")){
    		String[] dptIdsArray=new String[5];
        	if(dptIds.indexOf(",")!=-1){
        		dptIdsArray=dptIds.split(",");
        	}else{
        		dptIdsArray[0]=dptIds;
        	}
        	List<String> dptList=new ArrayList<String>();
        	for (int i = 0; i < dptIdsArray.length; i++) {
				String dpt = dptIdsArray[i];
				dptList.add(dpt);
			}
        	List<Orgz> dptObjList=orgzService.selectBatchIds(dptList);
        	String strTmpDpt="";
        	for (Orgz orgz : dptObjList) {
        		strTmpDpt+=orgz.getName()+",";
			}
        	if(strTmpDpt!=""){
        		strTmpDpt=strTmpDpt.substring(0, strTmpDpt.length()-1);        		
        	}
        	riskInfo.setDpts(strTmpDpt);
    	}
        model.addAttribute("info", JSON.toJSONString(riskInfo));
        return "risk/up/show";
	}
	
	//封装vo
    public void pageInfoRows(List pageInfo){
    	try{
	    	List riskInfoList=pageInfo;
	        for (Object  riskObj : riskInfoList) {
	        	if(riskObj instanceof RiskInfo){
	        		RiskInfo riskInfo = (RiskInfo) riskObj;
		        	String proId=riskInfo.getProName();
		        	if(proId!=null&&!proId.equals("")){
		        		riskInfo.setProName(proProjectinfoService.selectById(proId).getProName());	
		        	}
		        	String  riskLevel=riskInfo.getRikeLevel();
		        	if(riskLevel!=null&&!riskLevel.equals("")){
						RiskLevel tempLevel = iRiskLevelService.selectById(riskLevel);
						if(tempLevel!=null){
							riskInfo.setRikeLevel(tempLevel.getLevelName()+","+tempLevel.getColorFlag());
						}
		        	}
		        	//片区
		        	ProPqInfo section = proPqInfoService.selectById(riskInfo.getSection());
		        	if(section!=null){
						riskInfo.setSection(section.getPqName());
					}
					User user= userService.selectById(riskInfo.getUpUser());
		        	if(user!=null){
						riskInfo.setUpUser(user.getName());
					}
		        	//处理所选员工Id集合
		        	String empIds=riskInfo.getPersoon();
		        	if(empIds!=null&&!empIds.equals("")){
		        		String[] empIdsArray=new String[5];
		            	if(empIds.indexOf(",")!=-1){
		            		empIdsArray=empIds.split(",");
		            	}else{
		            		empIdsArray[0]=empIds;
		            	}
		            	
		            	List<String> empList=new ArrayList<String>();
		            	for (int i = 0; i < empIdsArray.length; i++) {
		    				String emp = empIdsArray[i];
		    				empList.add(emp);
		    			}
		            	List<SysEmployee> empObjList=sysEmployeeService.selectBatchIds(empList);
		            	String strTmp="";
		            	for (SysEmployee sysEmployee : empObjList) {
		            		strTmp+=sysEmployee.getName()+",";
		    			}
		            	if(!"".equals(strTmp)){
		            		strTmp=strTmp.substring(0, strTmp.length()-1);	
		            	}
		            	
		            	riskInfo.setPersoon(strTmp);
		        	}
		        	
		        	
		        	String dptIds=riskInfo.getDpts();
		        	if(dptIds!=null&&!dptIds.equals("")){
		        		String[] dptIdsArray=new String[5];
		            	if(dptIds.indexOf(",")!=-1){
		            		dptIdsArray=dptIds.split(",");
		            	}else{
		            		dptIdsArray[0]=dptIds;
		            	}
		            	
		            	List<String> dptList=new ArrayList<String>();
		            	for (int i = 0; i < dptIdsArray.length; i++) {
		    				String dpt = dptIdsArray[i];
		    				dptList.add(dpt);
		    			}
		            	List<Orgz> dptObjList=orgzService.selectBatchIds(dptList);
		            	String strTmpDpt="";
		            	for (Orgz orgz : dptObjList) {
		            		strTmpDpt+=orgz.getName()+",";
		    			}
		            	if(!"".equals(strTmpDpt)){
		            		strTmpDpt=strTmpDpt.substring(0, strTmpDpt.length()-1);	
		            	}
		            	riskInfo.setDpts(strTmpDpt);
		        	}
	        	}else{
	        		RiskInfoVo riskInfo = (RiskInfoVo) riskObj;
		        	String proId=riskInfo.getProName();
		        	if(proId!=null&&!proId.equals("")){
		        		riskInfo.setProName(proProjectinfoService.selectById(proId).getProName());	
		        	}
		        	String  riskLevel=riskInfo.getRikeLevel();
		        	if(riskLevel!=null&&!riskLevel.equals("")){
						RiskLevel tempLevel = iRiskLevelService.selectById(riskLevel);
						if(tempLevel!=null){
							riskInfo.setRikeLevelStr(tempLevel.getLevelName()+","+tempLevel.getColorFlag());
						}
		        	}
		        	//片区
		        	ProPqInfo section = proPqInfoService.selectById(riskInfo.getSection());
		        	if(section!=null){
						riskInfo.setSectionStr(section.getPqName());
					}
					User user= userService.selectById(riskInfo.getUpUser());
					if(user!=null){
						riskInfo.setUpUserName(user.getName());
					}
		        	//处理所选员工Id集合
		        	String empIds=riskInfo.getPersoon();
		        	if(empIds!=null&&!empIds.equals("")){
		        		String[] empIdsArray=new String[5];
		            	if(empIds.indexOf(",")!=-1){
		            		empIdsArray=empIds.split(",");
		            	}else{
		            		empIdsArray[0]=empIds;
		            	}
		            	
		            	List<String> empList=new ArrayList<String>();
		            	for (int i = 0; i < empIdsArray.length; i++) {
		    				String emp = empIdsArray[i];
		    				empList.add(emp);
		    			}
		            	List<SysEmployee> empObjList=sysEmployeeService.selectBatchIds(empList);
		            	String strTmp="";
		            	for (SysEmployee sysEmployee : empObjList) {
		            		strTmp+=sysEmployee.getName()+",";
		    			}
		            	if(!"".equals(strTmp)){
		            		strTmp=strTmp.substring(0, strTmp.length()-1);	
		            	}
		            	
		            	riskInfo.setPersoonStr(strTmp);
		        	}
		        	
		        	String dptIds=riskInfo.getDpts();
		        	if(dptIds!=null&&!dptIds.equals("")){
		        		String[] dptIdsArray=new String[5];
		            	if(dptIds.indexOf(",")!=-1){
		            		dptIdsArray=dptIds.split(",");
		            	}else{
		            		dptIdsArray[0]=dptIds;
		            	}
		            	
		            	List<String> dptList=new ArrayList<String>();
		            	for (int i = 0; i < dptIdsArray.length; i++) {
		    				String dpt = dptIdsArray[i];
		    				dptList.add(dpt);
		    			}
		            	List<Orgz> dptObjList=orgzService.selectBatchIds(dptList);
		            	String strTmpDpt="";
		            	for (Orgz orgz : dptObjList) {
		            		strTmpDpt+=orgz.getName()+",";
		    			}
		            	if(!"".equals(strTmpDpt)){
		            		strTmpDpt=strTmpDpt.substring(0, strTmpDpt.length()-1);	
		            	}
		            	riskInfo.setDptsStr(strTmpDpt);
		        	}
	        	}
	        	
			}
    	}catch (Exception e) {
			e.printStackTrace();
		}
    }

	/**
	 * 消息推送
	 */
	@Autowired
	IOpenPushService iOpenPushService;

	/**
	 * 用户信息
	 */
	@Autowired
	private IUserService userService;

	/**
	 * 消息推送
	 * @return
	 */
	@RequestMapping(value = "/pushMessage", method = RequestMethod.POST)
	@ResponseBody
	public Object pushMessage(String ids) {
		try {
			String[] idsArray = ids.split(",");
			boolean pushFlag = true;
			for (int i=0;i<idsArray.length;i++){
				JSONObject sendJson = new JSONObject();
				RiskInfo riskInfo = iRiskInfoService.selectById(idsArray[i]);
				if(riskInfo!=null && org.apache.commons.lang.StringUtils.isNotBlank(riskInfo.getUpUser())){//开始查询用户
					User user = userService.selectById(riskInfo.getUpUser());
					if(user!=null && StringUtils.isNotBlank(user.getCid())){
//						sendJson.put("id",riskInfo.getId());
//						sendJson.put("type","riskup");
//						sendJson.put("content","您有新的风险上报信息需要处理");
						iOpenPushService.riskUpPushMsg(user,"/risk/up/pushMessage","您有新的风险上报信息需要处理");
					}else{
						pushFlag = false;
					}
				}else{
					pushFlag = false;
				}
			}
			return renderSuccess(pushFlag?"推送成功":"推送失敗");
		} catch (Exception e) {
			e.printStackTrace();
			return renderError("推送失敗！");
		}
		
	}
	
	
///===1=负责部门=================================================
	
	
	
    /**
     * 通过项目id查询部门资源树
     *
     * @return
     */
    @RequestMapping(value = "/orgz_tree", method = RequestMethod.POST)
    @ResponseBody
    public Object treeByUid(String proId) {
    	///User user = getCurrentUser();
    	List<Tree> tree = new ArrayList<Tree>();
    	if(!StringUtils.isBlank(proId)){
    		ProProjectinfo pro = proProjectinfoService.selectById(proId);
    		if(pro !=null && !StringUtils.isBlank(pro.getParentId())){
    			Orgz orgz=orgzService.selectById(pro.getParentId());
    			if(orgz !=null && !StringUtils.isBlank(orgz.getId())){
    				/*User user = new User();
    				user.setOrgzId(orgz.getId());*/
    				tree = orgzService.selectTreeByPId(orgz.getId());
    			}
    		}
    	}
    	return tree;
    	
    }
    
  ///===2=负责人======
    
    /**
     * 获取用户信息
     * @return
     */
    @RequestMapping(value = "/getUserTree", method = RequestMethod.POST)
    @ResponseBody
    public Object getUserTree(String proId) {
    	//User currentUser = getCurrentUser();
    	List<Tree> tree = new ArrayList<Tree>();
    	if(!StringUtils.isBlank(proId)){
    		ProProjectinfo pro = proProjectinfoService.selectById(proId);
    		if(pro !=null && !StringUtils.isBlank(pro.getParentId())){
    			Orgz orgz=orgzService.selectById(pro.getParentId());
    			if(orgz !=null && !StringUtils.isBlank(orgz.getId())){
    				User user = new User();
    				user.setOrgzId(orgz.getId());
    				tree = orgzService.selectUserTreeByUser(user);
    			}
    		}
    	}
    	return tree;
    }
    
    /**
     * 获取
     * @return
     */
    @RequestMapping(value = "/getUserOrgz")
    @ResponseBody
    public Object getUserOrgz() {
    	User user = getCurrentUser();
    	String orgzId=user.getOrgzId();
    	Orgz orgz = orgzService.selectById(orgzId);
    	//项目机构则显示风险级别选择
    	if(orgz!=null&&orgz.getType()==0){
    		return renderSuccess("是");
    	}
    	return renderSuccess("否");
    }
    
	
}

