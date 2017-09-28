package com.crfeb.tbmpt.dmcjjc.dd.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.crfeb.tbmpt.commons.base.BaseController;
import com.crfeb.tbmpt.dmcjjc.dd.service.IDmcjDDTypeService;

@Controller
@RequestMapping("/dmcjjc/dmcjddtype")
public class DmcjDDTypeAction extends BaseController {

    @Autowired
    private IDmcjDDTypeService dmcjDDTypeService;

    /**
     * 获取所有基础数据分类
     *
     * @return
     */
    @RequestMapping(value = "/getAll", method = RequestMethod.POST)
    @ResponseBody
    public Object getAll() {
        return dmcjDDTypeService.getAll();
    }
    
}
