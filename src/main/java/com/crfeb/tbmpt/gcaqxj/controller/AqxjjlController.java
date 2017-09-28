package com.crfeb.tbmpt.gcaqxj.controller;

import com.crfeb.tbmpt.commons.base.BaseController;
import com.crfeb.tbmpt.commons.utils.PageInfo;
import com.crfeb.tbmpt.gcaqxj.model.dto.AqxjXjjlInfoDto;
import com.crfeb.tbmpt.gcaqxj.service.IAqxjXjjlInfoService;
import com.crfeb.tbmpt.sys.model.Orgz;
import com.crfeb.tbmpt.sys.model.User;
import com.crfeb.tbmpt.sys.service.IOrgzService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2017/7/28.
 */
@Controller
@RequestMapping("/gcaqxj/xjjl")
public class AqxjjlController  extends BaseController {
    private static final Logger LOGGER = LogManager.getLogger(AqxjjlController.class);


    @Autowired
    IAqxjXjjlInfoService aqxjXjjlInfoService;
    @Autowired
    IOrgzService iOrgzService;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public String manager(Model model) {

        return "gcaqxj/xunjiandianjilu";
    }

    @RequestMapping(value = "/dataGrid", method = RequestMethod.POST)
    @ResponseBody
    public Object dataGrid(AqxjXjjlInfoDto aqxjXjjlInfoDto,Integer page, Integer rows, String sort, String order)
    {

        User user = this.getCurrentUser();

        Orgz orgz =iOrgzService.getOrgzInfoByUserId(user.getId());
        PageInfo pageInfo = new PageInfo(page, rows, sort, order);
        Map<String, Object> condition = new HashMap<String, Object>();

        if(orgz.getType()==0&&(null ==aqxjXjjlInfoDto.getProjectname()||aqxjXjjlInfoDto.getProjectname().equals(""))){
        }else if(orgz.getType()==0&&!(null ==aqxjXjjlInfoDto.getProjectname()||aqxjXjjlInfoDto.getProjectname().equals(""))){
            //项目名称
            condition.put("proiectName", aqxjXjjlInfoDto.getProjectname());
        }else {
            condition.put("proiectName", findProjectByCurrentUser().getProName());
        }

        //分类名称
        condition.put("itemtype", aqxjXjjlInfoDto.getItemtype());
        //检查点名称
        condition.put("mingcheng", aqxjXjjlInfoDto.getMingcheng());
        //检查人
        condition.put("jianchaperson", aqxjXjjlInfoDto.getJianchaperson());
        //责任人
        condition.put("zerenrmc", aqxjXjjlInfoDto.getZerenrmc());
        //监督人
        condition.put("jiandurmc", aqxjXjjlInfoDto.getJiandurmc());
        pageInfo.setCondition(condition);
        aqxjXjjlInfoService.selectDataGrid(pageInfo);
        return pageInfo;
    }

}
