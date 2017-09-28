package com.crfeb.tbmpt.gcaqxj.controller;

import com.crfeb.tbmpt.commons.base.BaseController;
import com.crfeb.tbmpt.gcaqxj.model.dto.AqxjXjjlInfoDto;
import com.crfeb.tbmpt.gcaqxj.service.IAqxjXjjlInfoService;
import com.crfeb.tbmpt.project.model.ProProjectinfo;
import com.crfeb.tbmpt.project.service.IProProjectinfoService;
import com.crfeb.tbmpt.sys.model.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/8/1.
 */
@Controller
@RequestMapping("/gcaqxj/tjcx")
public class ReportController extends BaseController {
    private static final Logger LOGGER = LogManager.getLogger(ReportController.class);

    @Autowired
    IAqxjXjjlInfoService aqxjXjjlInfoService;

    @Autowired
    IProProjectinfoService proProjectinfoService;

    /**
     *责任人不合格统计页面
     * @param model
     * @return
     */
    @RequestMapping(value = "/ReportZerenr", method = RequestMethod.GET)
    public String manager(Model model) {
        return "gcaqxj/report_zerenren";
    }

    /**
     * 责任人不合格统计列表
     * @param aqxjXjjlInfoDto
     * @return
     */
    @RequestMapping(value = "/ReportByZerenr", method = RequestMethod.POST)
    @ResponseBody
    public Object getReportByZerenr(AqxjXjjlInfoDto aqxjXjjlInfoDto )
    {
        Map<String,Object> condition = new HashMap<String,Object>();
        if(null ==aqxjXjjlInfoDto.getProjectname()||aqxjXjjlInfoDto.getProjectname().equals("")){
            condition.put("projectName",findProjectByCurrentUser().getProName());
        }else{
            condition.put("projectName",aqxjXjjlInfoDto.getProjectname());
        }
        condition.put("startDate",aqxjXjjlInfoDto.getStartDate());
        condition.put("endDate",aqxjXjjlInfoDto.getEndDate());
       List<Map<String,Object>> list = aqxjXjjlInfoService.selectReportByZerenr(condition);
        return list;
    }

    /**
     *巡检点不合格统计页面
     * @param model
     * @return
     */
    @RequestMapping(value = "/ReportByXunjiandian", method = RequestMethod.GET)
    public String reportByXunjiandian(Model model) {
        User user = this.getCurrentUser();
        ProProjectinfo proProjectinfo = proProjectinfoService.getProjectInfoByUserId(user.getId());
        model.addAttribute("pro",proProjectinfo);
        model.addAttribute("orgId",findOrgzId());
        return "gcaqxj/report_xunjiandian";
    }

    /**
     * 巡检点不合格统计列表
     * @param aqxjXjjlInfoDto
     * @return
     */
    @RequestMapping(value = "/ReportByXunjiandian", method = RequestMethod.POST)
    @ResponseBody
    public Object getReportByXunjiandian(AqxjXjjlInfoDto aqxjXjjlInfoDto )
    {
        Map<String,Object> condition = new HashMap<String,Object>();
        if(null ==aqxjXjjlInfoDto.getProjectname()||aqxjXjjlInfoDto.getProjectname().equals("")){
            condition.put("projectName",findProjectByCurrentUser().getProName());
        }else{
            condition.put("projectName",aqxjXjjlInfoDto.getProjectname());
        }
        condition.put("startDate",aqxjXjjlInfoDto.getStartDate());
        condition.put("endDate",aqxjXjjlInfoDto.getEndDate());
        List<Map<String,Object>> list = aqxjXjjlInfoService.selectReportByXunjiandian(condition);
        return list;
    }

    /**
     * 巡检热度统计页面
     * @param model
     * @return
     */
    @RequestMapping(value = "/ReportByproject", method = RequestMethod.GET)
    public String reportByproject(Model model) {
        return "gcaqxj/report_redu";
    }
    /**
     * 巡检点不合格统计列表
     * @param aqxjXjjlInfoDto
     * @return
     */
    @RequestMapping(value = "/ReportByproject", method = RequestMethod.POST)
    @ResponseBody
    public Object getReportByproject(AqxjXjjlInfoDto aqxjXjjlInfoDto )
    {
        Map<String,Object> condition = new HashMap<String,Object>();
        if(null ==aqxjXjjlInfoDto.getProjectname()||aqxjXjjlInfoDto.getProjectname().equals("")){
            condition.put("projectName",findProjectByCurrentUser().getProName());
        }else{
            condition.put("projectName",aqxjXjjlInfoDto.getProjectname());
        }
        condition.put("startDate",aqxjXjjlInfoDto.getStartDate());
        condition.put("endDate",aqxjXjjlInfoDto.getEndDate());
        List<Map<String,Object>> list = aqxjXjjlInfoService.selectReportByproject(condition);
        return list;
    }


    /**
     *项目部日巡检次数统计页面
     * @param model
     * @return
     */
    @RequestMapping(value = "/ReportByDay", method = RequestMethod.GET)
    public String ReportByDay(Model model) {
        return "gcaqxj/report_day";
    }

    /**
     * 项目部日巡检次数统计列表
     * @param aqxjXjjlInfoDto
     * @return
     */
    @RequestMapping(value = "/ReportByDay", method = RequestMethod.POST)
    @ResponseBody
    public Object ReportByDay(AqxjXjjlInfoDto aqxjXjjlInfoDto )
    {
        Map<String,Object> condition = new HashMap<String,Object>();
        if(null ==aqxjXjjlInfoDto.getProjectname()||aqxjXjjlInfoDto.getProjectname().equals("")){
            condition.put("projectName",findProjectByCurrentUser().getProName());
        }else{
            condition.put("projectName",aqxjXjjlInfoDto.getProjectname());
        }
        condition.put("startDate",aqxjXjjlInfoDto.getStartDate());
        condition.put("endDate",aqxjXjjlInfoDto.getEndDate());
        List<Map<String,Object>> list = aqxjXjjlInfoService.selectReportByDay(condition);
        return  renderSuccess(list);
    }

}
