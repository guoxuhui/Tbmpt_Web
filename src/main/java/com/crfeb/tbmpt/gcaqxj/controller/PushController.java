package com.crfeb.tbmpt.gcaqxj.controller;

import com.crfeb.tbmpt.commons.base.BaseController;
import com.crfeb.tbmpt.commons.utils.PageInfo;
import com.crfeb.tbmpt.gcaqxj.model.dto.AqxjXjjlInfoDto;
import com.crfeb.tbmpt.gcaqxj.service.IAqxjXjjlInfoService;
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
import java.util.Map;

/**
 * 推送
 * Created by Administrator on 2017/7/31.
 */
@Controller
@RequestMapping("/gcaqxj/push")
public class PushController extends BaseController {
    private static final Logger LOGGER = LogManager.getLogger(PushController.class);

    @Autowired
    IAqxjXjjlInfoService aqxjXjjlInfoService;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public String manager(Model model) {

        return "gcaqxj/xunjiantuisong";
    }

    @RequestMapping(value = "/dataGrid", method = RequestMethod.POST)
    @ResponseBody
    public Object dataGrid(AqxjXjjlInfoDto aqxjXjjlInfoDto,Integer page, Integer rows, String sort, String order)
    {
        User user = this.getCurrentUser();
        PageInfo pageInfo = new PageInfo(page, rows, sort, order);
        Map<String, Object> condition = new HashMap<String, Object>();
        //项目名称
        condition.put("proiectName", aqxjXjjlInfoDto.getProjectname());
        //责任人
        condition.put("zerenrmc",user.getName());
        //是否查看
        condition.put("isView", aqxjXjjlInfoDto.getIsView());
        pageInfo.setCondition(condition);
        aqxjXjjlInfoService.selectPushDataGrid(pageInfo);
        return pageInfo;
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @ResponseBody
    public Object update(String  id) {
        try {
           String errmessage= aqxjXjjlInfoService.update(id,this.getCurrentUser());
            if(errmessage!=null){
                log(null, false, errmessage);
                return renderError(errmessage);
            }
            log(null, true, "更新推送信息是否查看状态成功");
            return renderSuccess("更新成功！");
        }catch (Exception e){
            e.printStackTrace();
            log(null, true, "更新推送信息是否查看状态失败！");
            return  renderError("系统错误，请联系系统管理员!!");
        }
    }



}
