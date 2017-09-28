/**
 * 
 */
package com.crfeb.tbmpt.zl.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.crfeb.tbmpt.commons.base.BaseController;
import com.crfeb.tbmpt.commons.utils.PageInfo;
import com.crfeb.tbmpt.commons.utils.StringUtils;
import com.crfeb.tbmpt.project.model.ProProjectinfo;
import com.crfeb.tbmpt.project.model.ProRProjectSection;
import com.crfeb.tbmpt.project.model.ProRSectionLine;
import com.crfeb.tbmpt.project.service.IProProjectinfoService;
import com.crfeb.tbmpt.project.service.IProRProjectSectionService;
import com.crfeb.tbmpt.project.service.IProRSectionLineService;
import com.crfeb.tbmpt.sys.model.User;
import com.crfeb.tbmpt.sys.service.ISysEmployeeService;
import com.crfeb.tbmpt.zl.model.QualitySetting;
import com.crfeb.tbmpt.zl.model.vo.QualitySettingQueryVo;
import com.crfeb.tbmpt.zl.service.IQualitySettingService;

/**
 * 管片质量配置管理
 * @author Administrator
 *
 */  

@Controller
@RequestMapping("/zl/pz")
public class QualityPZManageController extends BaseController {
	
	private static final Logger LOGGER = LogManager.getLogger(QualityPZManageController.class);
	
	@Autowired
	private IProProjectinfoService proProjectinfoService;
	@Autowired
	IQualitySettingService  iQualitySettingService;
	@Autowired
    private IProRSectionLineService proRSectionLineService;
    @Autowired
    private IProRProjectSectionService proRProjectSectionService;
    @Autowired
    private ISysEmployeeService sysEmployeeService;
	/**
     * @return
     */
    @RequestMapping(value = "", method = RequestMethod.GET)
    public String manager() {
        return "zl/pz/index";
    }
    /**
     * 获取项目信息
     * @return
     */
    @RequestMapping(value = "/getProDic")
    @ResponseBody
    public Object getProDic() {
    	User user = getCurrentUser();
    	List<ProProjectinfo> list = proProjectinfoService.getProjectInfosByUserId(String.valueOf(user.getId()));
    	return list;
    }
    /**
     * 获取项目区间信息
     * 注：施工质量巡检管理中引用此方法
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
     * @param page
     * @param rows
     * @param sort
     * @param order
     * @return
     */
    @RequestMapping(value = "/dataGrid", method = RequestMethod.POST)
    @ResponseBody
    public Object dataGrid(QualitySettingQueryVo vo,Integer page, Integer rows, String sort, String order) {
        PageInfo pageInfo = new PageInfo(page, rows, sort, order);
        Map<String, Object> condition = new HashMap<String, Object>();
        if (StringUtils.isNotBlank(vo.getProName())) {
            condition.put("proName", vo.getProName());
        }
//        if (StringUtils.isNotBlank(vo.getUpUser())) {
//            condition.put("upUser", vo.getUpUser());
//        }
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
        
        iQualitySettingService.selectDataGrid(pageInfo,getCurrentUser());
//        List list=pageInfo.getRows();
//        for (Object object : list) {
//        	QualitySettingVo qs=(QualitySettingVo) object;
//        	qs.setProName(proProjectinfoService.selectById(qs.getProName()).getProName());
//        	qs.setSection(proRProjectSectionService.selectById(qs.getSection()).getSectionName());
//        	qs.setLine(proRSectionLineService.selectById(qs.getLine()).getLineName());
//		}
     
        return pageInfo;
    }
    /**
     * @return
     */
    @RequestMapping(value = "/addPage", method = RequestMethod.GET)
    public String addPage() {
        return "zl/pz/add";
    }
    /**
     * @param role
     * @return
     */
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public Object add(QualitySetting t) {
    	User user = this.getCurrentUser();
    	try {
    		t.setUpUser(user.getName());
        	if(StringUtils.isNotBlank(user.getEmpId())){
        		if(sysEmployeeService.selectById(user.getEmpId())!=null
        			&& StringUtils.isNotBlank(sysEmployeeService.selectById(user.getEmpId()).getPhone()) ){
        			t.setLinkWay(sysEmployeeService.selectById(user.getEmpId()).getPhone());
        		}
        	}
        	iQualitySettingService.save(t);
        	
        	LOGGER.info("管片质量配置添加成功。");
        	this.log(null, true, "用户："+user.getName()+",添加了管片质量配置！");
            return renderSuccess("添加成功！");
		} catch (Exception e) {
			LOGGER.info("管片质量配置添加失败。");
	    	this.log(null, false, "用户："+user.getName()+",修改管片质量配置失败!");
			return renderError("添加失败！");
		}
    	
    }
    
    /**
     * @param id
     * @return
     */
    @RequestMapping("/delete")
    @ResponseBody
    public Object delete(String ids) {
    	User user = this.getCurrentUser();
    	try{
    		String[] idss = ids.split(",");
        	List<String> idlist = new ArrayList<String>();
        	Collections.addAll(idlist, idss);
        	Boolean falt = iQualitySettingService.deleteBatchIds(idlist);
        	if(falt){
        		LOGGER.info("删除管片质量配置成功。");
            	this.log(null, true, "用户："+user.getName()+",删除了管片质量配置,管片质量配置ids:"+ids.toString());
        		return renderSuccess("删除成功！");
        	}else{
        		LOGGER.info("删除管片质量配置信息失败！");
        		log(null, false, "用户："+user.getName()+",删除管片质量配置信息失败!");
        		return renderSuccess("删除异常！");
        	}
    	} catch (Exception e) {
			e.printStackTrace();
			LOGGER.info("删除管片质量配置信息失败！");
    		log(null, false, "用户："+user.getName()+",删除管片质量配置信息失败!");
			return renderSuccess("删除失败！");
		}
    	
    }
    

    /**
     */
    @RequestMapping("/edit")
    @ResponseBody
    public Object edit(QualitySetting t) {
    	User user = this.getCurrentUser();
    	try {
    		
    		iQualitySettingService.updateSelectiveById(t);
    		LOGGER.info("修改管片质量配置成功。");
        	this.log(null, true, "用户："+user.getName()+",修改了管片质量配置");
            return renderSuccess("编辑成功！");
	    } catch (Exception e) {
			e.printStackTrace();
			LOGGER.info("管片质量配置修改失败。");
	    	this.log(null, false, "用户："+user.getName()+",修改管片质量配置失败!");
			return renderError("编辑失败！");
		}
    }

}
