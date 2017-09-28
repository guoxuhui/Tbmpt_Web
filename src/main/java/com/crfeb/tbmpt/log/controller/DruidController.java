package com.crfeb.tbmpt.log.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @description：数据源日志管理
 * @author：smxg
 * @date：2016-10-10 11:12
 */
@Controller
@RequestMapping("/log/druid")
public class DruidController {

    @RequestMapping(value = "", method = RequestMethod.GET)
    public String manager() {
    	
        return "redirect:/druid";
    }

}
