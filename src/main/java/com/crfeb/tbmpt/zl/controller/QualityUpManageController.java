/**
 * 
 */
package com.crfeb.tbmpt.zl.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.crfeb.tbmpt.commons.base.BaseController;
import com.crfeb.tbmpt.commons.result.Result;
import com.crfeb.tbmpt.commons.scan.SpringUtils;
import com.crfeb.tbmpt.commons.utils.DateUtils;
import com.crfeb.tbmpt.commons.utils.PageInfo;
import com.crfeb.tbmpt.commons.utils.StringUtils;
import com.crfeb.tbmpt.project.model.ProProjectinfo;
import com.crfeb.tbmpt.project.model.ProRProjectSection;
import com.crfeb.tbmpt.project.model.ProRSectionLine;
import com.crfeb.tbmpt.project.service.IProProjectinfoService;
import com.crfeb.tbmpt.project.service.IProProjectjinduService;
import com.crfeb.tbmpt.project.service.IProRProjectSectionService;
import com.crfeb.tbmpt.project.service.IProRSectionLineService;
import com.crfeb.tbmpt.sys.model.User;
import com.crfeb.tbmpt.sys.service.ISysEmployeeService;
import com.crfeb.tbmpt.tbmmg.model.ProTbminfo;
import com.crfeb.tbmpt.tbmmg.service.IProTbminfoService;
import com.crfeb.tbmpt.zl.model.QualityInfo;
import com.crfeb.tbmpt.zl.model.vo.QualityInfoQueryVo;
import com.crfeb.tbmpt.zl.service.IQualityInfoService;
import com.crfeb.tbmpt.zsjk.plc.model.dto.ProPlcBzRealDto;
import com.crfeb.tbmpt.zsjk.plc.service.IProPlcRealService;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;

/**
 * 管片质量上报管理
 * @author Administrator
 *
 */
@Controller
@RequestMapping("/zl/up")
public class QualityUpManageController extends BaseController {
	
	private static final Logger LOGGER = LogManager.getLogger(QualityPZManageController.class);
	
	@Autowired
	private IProProjectinfoService proProjectinfoService;
	@Autowired
	IQualityInfoService  iQualityInfoService;
	@Autowired
	private IProProjectjinduService proProjectjinduService;
	@Autowired
    private IProRProjectSectionService proRProjectSectionService;
	@Autowired
    private IProRSectionLineService proRSectionLineService;
	 @Autowired
	private ISysEmployeeService sysEmployeeService;
	/**
     * @return
     */
    @RequestMapping(value = "", method = RequestMethod.GET)
    public String manager() {
        return "zl/up/index";
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
    public Object dataGrid(QualityInfoQueryVo vo,Integer page, Integer rows, String sort, String order) {
    	if (StringUtils.isNotBlank(sort)) {
           if(sort.equals("cycleNo")){
            	sort = "to_number(Q.CYCLE_NO)";
            }
        }
    	PageInfo pageInfo = new PageInfo(page, rows, sort, order);
        Map<String, Object> condition = new HashMap<String, Object>();
        
        if (StringUtils.isNotBlank(vo.getProName())) {
            condition.put("proName", vo.getProName());
        }
        if (StringUtils.isNotBlank(vo.getUpDateStart())) {
            condition.put("upDateStart", vo.getUpDateStart());
        }
        if (StringUtils.isNotBlank(vo.getUpDateEnd())) {
            condition.put("upDateEnd", vo.getUpDateEnd());
        }
        if (StringUtils.isNotBlank(vo.getUpUser())) {
            condition.put("upUser", vo.getUpUser());
        }
        if (StringUtils.isNotBlank(vo.getStatus())) {
            condition.put("status", vo.getStatus());
        }
        if (StringUtils.isNotBlank(vo.getHasProblem())) {
            condition.put("hasProblem", vo.getHasProblem());
        }
        if (StringUtils.isNotBlank(vo.getSort())) {
            condition.put("sort", vo.getSort());
        }
        if (StringUtils.isNotBlank(vo.getSection())) {
            condition.put("section", vo.getSection());
        }
        if (StringUtils.isNotBlank(vo.getLine())) {
            condition.put("line", vo.getLine());
        }
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
  		
        condition.put("upUser",getCurrentUser().getId());
        pageInfo.setCondition(condition);
        iQualityInfoService.selectDataGrid(pageInfo);
        return pageInfo;
    }
    /**
     * 获取区间线路信息
     * @return
     */
    @RequestMapping(value = "/getProLineDic", method = RequestMethod.POST)
    @ResponseBody
    public Object getProLineDic(String sectionId) {
    	if(StringUtils.isEmpty(sectionId)){
    		return renderError("查询失败");
    	}else{
        	List<ProRSectionLine> list = proRSectionLineService.getLineBySectionId(sectionId);
        	return list;
    	}
    }
    /**
     * 获取项目区间信息
     * @return
     */
    @RequestMapping(value = "/getProSectionDic", method = RequestMethod.POST)
    @ResponseBody
    public Object getProSectionDic(String proId) {
    	if(StringUtils.isEmpty(proId)){
    		return renderError("查询失败");
    	}else{
        	List<ProRProjectSection> list = proRProjectSectionService.getSectionByProId(proId);
        	return list;
    	}
    }
    /**
     * @return
     */
    @RequestMapping(value = "/addPage", method = RequestMethod.GET)
    public String addPage() {
        return "zl/up/add";
    }
    /**
     * @return
     */
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public Object add(QualityInfo t) {
    	User user = this.getCurrentUser();
    	t.setProblemDesc(org.apache.commons.lang.StringEscapeUtils.unescapeHtml(t.getProblemDesc()));
    	t.setRemark(org.apache.commons.lang.StringEscapeUtils.unescapeHtml(t.getRemark()));
    	try {
    		if(t !=null && StringUtils.isNotBlank(t.getCycleNo())&& StringUtils.isNotBlank(t.getLine())){
        		
        		QualityInfo s = iQualityInfoService.selectQualityInfoByCycleNo(t.getLine(),t.getCycleNo());
        		if(s ==null){
        			//上报人 上报时间  联系方式 获取当前用户，进一步获取员工信息
                    t.setUpUser(getCurrentUser().getId());
                	t.setUpDate(DateUtils.getToday());
                	String empId=getCurrentUser().getEmpId();
                	if(empId!=null){
                		t.setLinkWay(sysEmployeeService.selectById(empId).getPhone());	
                	}
                	iQualityInfoService.save(t);
                	
                	LOGGER.info("管片质量上报添加成功。");
                	this.log(null, true, "用户："+user.getName()+"，添加了管片质量上报信息！");
                    return renderSuccess("添加成功！");
        		}else{
        			LOGGER.info("添加管片质量上报信息失败！");
            		log(null, false, "用户："+user.getName()+",添加管片质量上报信息失败!");
        			return renderError("环号已存在，添加失败！");
        		}
        	}
		} catch (Exception e) {
			e.printStackTrace();
			LOGGER.info("添加管片质量上报信息失败！");
    		log(null, false, "用户："+user.getName()+",添加管片质量上报信息失败!");
			return renderError("添加失败！");
		}
    	LOGGER.info("添加管片质量上报信息失败！");
		log(null, false, "用户："+user.getName()+",添加管片质量上报信息失败!");
    	return renderError("添加失败！");
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
        	Boolean falt = iQualityInfoService.deleteBatchIds(idlist);
        	if(falt){
        		LOGGER.info("删除管片质量上报成功。");
            	this.log(null, true, "用户："+user.getName()+",删除了管片质量上报信息！管片质量上报ids:"+ids.toString());
        		return renderSuccess("删除成功！");
        	}else{
        		return renderError("删除异常！");
        	}
		} catch (Exception e) {
			e.printStackTrace();
			LOGGER.info("删除管片质量上报信息失败！");
			log(null, false, "用户："+user.getName()+",删除管片质量上报信息失败!");
			return renderError("删除失败！");
		}
    	
    }
    

    /**
     */
    @RequestMapping("/edit")
    @ResponseBody
    public Object edit(QualityInfo t) {
    	User user = this.getCurrentUser();
    	t.setProblemDesc(org.apache.commons.lang.StringEscapeUtils.unescapeHtml(t.getProblemDesc()));
    	t.setRemark(org.apache.commons.lang.StringEscapeUtils.unescapeHtml(t.getRemark()));
    	try {
    		if(t !=null && StringUtils.isNotBlank(t.getId()) && StringUtils.isNotBlank(t.getCycleNo())&& StringUtils.isNotBlank(t.getLine())){
        		
        		QualityInfo s = iQualityInfoService.selectQualityInfoByCycleNo(t.getLine(),t.getCycleNo());
        		if(s ==null || (s !=null && s.getId().equals(t.getId()))){
        			//上报人 上报时间  联系方式 获取当前用户，进一步获取员工信息
                    t.setUpUser(getCurrentUser().getId());
                	t.setUpDate(DateUtils.getToday());
                	String empId=getCurrentUser().getEmpId();
                	if(empId!=null){
                		t.setLinkWay(sysEmployeeService.selectById(empId).getPhone());	
                	}
                	iQualityInfoService.updateSelectiveById(t);
                	LOGGER.info("编辑管片质量上报信息成功！");
        			log(null, true, "用户："+user.getName()+",编辑管片质量上报信息成功!");
                    return renderSuccess("编辑成功！");
        		}else{
        			LOGGER.info("编辑管片质量上报信息失败！");
        			log(null, false, "用户："+user.getName()+",编辑管片质量上报信息失败!");
        			return renderError("环号已存在，添加失败！");
        		}
        	}
		} catch (Exception e) {
			e.printStackTrace();
			LOGGER.info("编辑管片质量上报信息失败！");
			log(null, false, "用户："+user.getName()+",编辑管片质量上报信息失败!");
			return renderError("编辑失败！");
		}
    	LOGGER.info("编辑管片质量上报信息失败！");
		log(null, false, "用户："+user.getName()+",编辑管片质量上报信息失败!");
    	return renderError("编辑失败！");
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

    @Autowired
    private IProPlcRealService proPlcRealService;
    @Autowired
    private IProTbminfoService proTbminfoService;

    /**
     * 獲取環號
     * @param xlId 线路ID
     * @return jjhh 掘进换号  plcTime 当前时间  tbmInfo 盾构机信息
     */
    @RequestMapping("/getHHByXlId")
    @ResponseBody
    public Object getCurrHHByXlId(String xlId){
        Result result = new Result();
        if(StringUtils.isEmpty(xlId)){
            result.setSuccess(false);
            result.setMsg("线路ID不能为空！");
            return result;
        }
        Map<String,Object> tbmMap = new HashMap<String,Object>();
        proTbminfoService = SpringUtils.getBean(IProTbminfoService.class);
        proRSectionLineService = SpringUtils.getBean(IProRSectionLineService.class);
        ProRSectionLine line = proRSectionLineService.selectById(xlId);
        ProTbminfo tbm = null;
        if(line != null){
            tbm = proTbminfoService.selectById(line.getTbmId());
        }
        if(tbm == null){
            result.setSuccess(false);
            result.setMsg("当前区间线路盾构机不存在！");
            return result;
        }
        List<ProPlcBzRealDto> pbzs = new ArrayList<ProPlcBzRealDto>();
        String dwIds = "TY_DXXT_0001";
        try {
            pbzs = proPlcRealService.getSsJqsjByDw(tbm.getId(), dwIds);
        } catch (Exception e) {
            e.printStackTrace();
        }
        tbmMap.put("tbmInfo", tbm);
        if(pbzs != null && pbzs.size()==1){
            if("TY_DXXT_0001".equals(pbzs.get(0).getDwid())){
                tbmMap.put("jjhh", pbzs.get(0).getTagvalue());
            }
            tbmMap.put("plcTime", pbzs.get(0).getTagtime());
        }

        if(tbmMap.get("jjhh") == null){
           /* tbmMap.put("jjhh", "0");
            tbmMap.put("plcTime", DateUtils.getToday());*/
            result.setSuccess(false);
            result.setMsg("当前区间线路无掘进环号！");
            return result;
        }

        result.setSuccess(true);
        result.setObj(tbmMap);
        return JSON.toJSONString(result, SerializerFeature.WriteMapNullValue,SerializerFeature.WriteNullStringAsEmpty);

    }

    /**
     * 数据同步
     * @return
     */
    @RequestMapping("/dataSynchronous")
    @ResponseBody
    public Object dataSynchronous(HttpServletRequest request, HttpServletResponse response) {
    	QualityPZTask q = new QualityPZTask();
    	int result = q.qualitypzOpen();
        if(result>0){
            return renderSuccess("同步成功！" + "共更新：" + result + "条数据");
        }else{
            return renderSuccess("同步成功！" + "共更新：0 条数据");
        }
    }
    
	/**
     * @return
     */
    @RequestMapping(value = "/indexPro", method = RequestMethod.GET)
    public String listPro(Model model,String proName,String section,String line,String rq) {
		if(rq!=null&&rq!=""){
			Integer num = Integer.parseInt(String.valueOf((rq.charAt(1))));
			String type = String.valueOf(rq.charAt(2));
            String startTime = "";
            String endTime = "";
            if("天".equals(type)){//日
//              unitDay = "天";
              endTime = DateUtils.addDayOfCurrentDay(0);
              startTime = DateUtils.addDayOfCurrentDay(-num);
          }else if("周".equals(type)){//周
//              unitDay = "周";
              endTime = DateUtils.addDayOfCurrentDay(0);
              startTime = DateUtils.addDayOfCurrentDay(-(7*num));
          }else if("月".equals(type)){//月
//              unitDay = "月";
              endTime = DateUtils.addDayOfCurrentDay(0);
              startTime = DateUtils.addDayOfCurrentDay(-(30*num));
          }
			model.addAttribute("proName", proName);
			model.addAttribute("section",section);
			model.addAttribute("line",line);
			model.addAttribute("startTime",startTime);
			model.addAttribute("endTime",endTime);
		}
		return "zl/up/indexPro";
    }

}
