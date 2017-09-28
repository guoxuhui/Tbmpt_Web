package com.crfeb.tbmpt.gcaqxj.controller;

import com.crfeb.tbmpt.commons.base.BaseController;

import com.crfeb.tbmpt.gcaqxj.model.AqxjXjjlDetail;
import com.crfeb.tbmpt.gcaqxj.service.IAqxjXjjlDetailService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;


/**
 * Created by Administrator on 2017/7/31.
 */
@Controller
@RequestMapping("/gcaqxj/xjjl/detail")
public class AqxjXjjlDetailController  extends BaseController{
    private static final Logger LOGGER = LogManager.getLogger(AqxjXjjlDetailController.class);

    @Autowired
    IAqxjXjjlDetailService aqxjXjjlDetailService;

    @RequestMapping(value = "/list", method = RequestMethod.POST)
    @ResponseBody
    public Object getList(String  id )
    {
        List<AqxjXjjlDetail> list = aqxjXjjlDetailService.selectAll(id);
        return list;
    }
}
