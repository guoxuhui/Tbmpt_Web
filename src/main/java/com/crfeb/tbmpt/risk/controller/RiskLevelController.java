package com.crfeb.tbmpt.risk.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.crfeb.tbmpt.commons.base.BaseController;
import com.crfeb.tbmpt.commons.utils.PageInfo;
import com.crfeb.tbmpt.commons.utils.StringUtils;
import com.crfeb.tbmpt.project.model.ProProjectinfo;
import com.crfeb.tbmpt.project.service.IProProjectinfoService;
import com.crfeb.tbmpt.project.service.IProRProjectSectionService;
import com.crfeb.tbmpt.project.service.IProRSectionLineService;
import com.crfeb.tbmpt.risk.model.RiskLevel;
import com.crfeb.tbmpt.risk.model.vo.RiskLevelQueryVo;
import com.crfeb.tbmpt.risk.service.IRiskLevelService;
import com.crfeb.tbmpt.sys.model.User;

/**
 * 风控级别管理
 * @author Administrator
 *
 */
@Controller
@RequestMapping("/risk/level")
public class RiskLevelController extends BaseController {
	private static final Logger LOGGER = LogManager.getLogger(RiskLevelController.class);
	
	@Autowired
    private IProRSectionLineService proRSectionLineService;
    @Autowired
    private IProRProjectSectionService proRProjectSectionService;
    @Autowired
    private IProProjectinfoService proProjectinfoService;
	@Autowired	
	private IRiskLevelService iRiskLevelService;
	
	/**
     * @return
     */
    @RequestMapping(value = "", method = RequestMethod.GET)
    public String manager() {
        return "risk/level/index";
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
    public Object dataGrid(RiskLevelQueryVo vo,Integer page, Integer rows, String sort, String order) {
        PageInfo pageInfo = new PageInfo(page, rows, sort, order);
        Map<String, Object> condition = new HashMap<String, Object>();
        if (StringUtils.isNotBlank(vo.getLevelName())) {
            condition.put("levelName", vo.getLevelName());
        }
        if (StringUtils.isNotBlank(vo.getSort())) {
            condition.put("sort", vo.getSort());
        }
        if (StringUtils.isNotBlank(vo.getRiskDesc())) {
            condition.put("riskDesc", vo.getRiskDesc());
        }
        pageInfo.setCondition(condition);
        iRiskLevelService.selectDataGrid(pageInfo);
        return pageInfo;
    }
    /**
     * @return
     */
    @RequestMapping(value = "/addPage", method = RequestMethod.GET)
    public String addPage() {
        return "risk/level/add";
    }
    /**
     * @param role
     * @return
     */
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public Object add(RiskLevel t) {
    	if(t!=null&&!t.getRiskDesc().isEmpty()){
    		t.setRiskDesc(org.apache.commons.lang.StringEscapeUtils.unescapeHtml(t.getRiskDesc()));
    	}
    	User user = this.getCurrentUser();
    	try {
    		if(t !=null && StringUtils.isNotBlank(t.getLevelName())){
        		
        		 Map<String, Object> condition = new HashMap<String, Object>();
         		condition.put("LEVEL_NAME", t.getLevelName());
         	    List<RiskLevel> rList = iRiskLevelService.selectByMap(condition);
         		if(rList.isEmpty()){ 
         			iRiskLevelService.save(t);
         			LOGGER.info("风险配置添加成功。");
                	this.log(null, true, "用户："+user.getName()+",添加了风险配置，风险名称："+t.getLevelName());
            		
                    return renderSuccess("添加成功！");
         		}
         		LOGGER.info("风险配置添加失败。");
            	this.log(null, false, "用户："+user.getName()+",添加风险配置失败!");
         		return renderError("添加失败！风险级别名称已存在！");
        	}
		} catch (Exception e) {
			e.printStackTrace();
			LOGGER.info("风险配置添加失败。");
        	this.log(null, false, "用户："+user.getName()+",添加风险配置失败!");
			return renderError("添加失败！");
		}
    	LOGGER.info("风险配置添加失败。");
    	this.log(null, false, "用户："+user.getName()+",添加风险配置失败!");
    	return renderError("添加失败！");
    }
    
    /**
     * @param id
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
        	Boolean falt = iRiskLevelService.deleteBatchIds(idlist);
        	if(falt){
        		LOGGER.info("删除风险配置成功。");
            	this.log(null, true, "用户："+user.getName()+",删除了风险配置,风险配置ids:"+ids.toString());
        		return renderSuccess("删除成功！");
        	}else{
        		LOGGER.info("删除风险配置信息失败！");
        		log(null, false, "用户："+user.getName()+",删除风险配置信息失败!");
        		return renderSuccess("删除异常！");
        	}
		} catch (Exception e) {
			e.printStackTrace();
			LOGGER.info("删除风险配置信息失败！");
    		log(null, false, "用户："+user.getName()+",删除风险配置信息失败!");
			return renderSuccess("删除失败！");
		}
    	
    }
    /**
     * @param model
     * @param id
     * @return
     */
    @RequestMapping("/editPage")
    public String editPage(Model model, String id) {
    	RiskLevel t = iRiskLevelService.selectById(id);
        model.addAttribute("model", t);
        return "risk/level/edit";
    }

    /**
     */
    @RequestMapping("/edit")
    @ResponseBody
    public Object edit(RiskLevel t) {
    		
           
    	User user = this.getCurrentUser();
    	try {
    		if(t !=null && StringUtils.isNotBlank(t.getId())
    				&& StringUtils.isNotBlank(t.getLevelName())){
        		Map<String, Object> condition = new HashMap<String, Object>();
        		condition.put("LEVEL_NAME", t.getLevelName());
        	    List<RiskLevel> rList = iRiskLevelService.selectByMap(condition);
        		if(rList.isEmpty()){ 
        			iRiskLevelService.updateSelectiveById(t);
        			LOGGER.info("修改风险配置成功。");
                	this.log(null, true, "用户："+user.getName()+",修改了风险配置,风险名称："+t.getLevelName());
                    return renderSuccess("编辑成功！");
        		}else if(!rList.isEmpty() && rList.size()==1){
        			RiskLevel r = rList.get(0);
        			if(t.getId().equals(r.getId())){
        				LOGGER.info("修改风险配置成功。");
                    	this.log(null, true, "用户："+user.getName()+",修改了风险配置,风险名称："+t.getLevelName());
        				iRiskLevelService.updateSelectiveById(t);
                        return renderSuccess("编辑成功！");
        			}
        			LOGGER.info("风险配置修改失败。");
                	this.log(null, false, "用户："+user.getName()+",修改风险配置失败!");
        			return renderError("风险级别名称已存在！");
        		}else{
        			LOGGER.info("风险配置修改失败。");
                	this.log(null, false, "用户："+user.getName()+",修改风险配置失败!");
        			return renderError("风险级别名称存在多个，请保留一个！");
        		}
        	}
		} catch (Exception e) {
			e.printStackTrace();
			LOGGER.info("风险配置修改失败。");
        	this.log(null, false, "用户："+user.getName()+",修改风险配置失败!");
			return renderError("编辑失败！");
		}
    	LOGGER.info("风险配置修改失败。");
    	this.log(null, false, "用户："+user.getName()+",修改风险配置失败!");
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
    
    
    /**
     * 获取界别信息
     * @return
     */
    @RequestMapping(value = "/getRiskLevelList")
    @ResponseBody
    public Object getRiskLevelList() {
    	EntityWrapper<RiskLevel> riskLevel = new EntityWrapper<RiskLevel>();
    	riskLevel.orderBy("sort", true);
    	return iRiskLevelService.queryList(riskLevel);
    }
    
}
