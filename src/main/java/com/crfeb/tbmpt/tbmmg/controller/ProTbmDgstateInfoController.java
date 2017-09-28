package com.crfeb.tbmpt.tbmmg.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.crfeb.tbmpt.commons.base.BaseController;
import com.crfeb.tbmpt.commons.utils.StringUtils;
import com.crfeb.tbmpt.tbmmg.service.IProTbmDgstateInfoService;
/**
 * @author：smxg
 * @date：2016-10-10 11:12
 */
@Controller
@RequestMapping("/tbmmg/dgstateinfo")
public class ProTbmDgstateInfoController extends BaseController {

    @Autowired
    private IProTbmDgstateInfoService proTbmDgstateInfoService;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public String index() {
    	return "tbmmg/dgstateinfo/index";
    }

    @RequestMapping("/getData")
    @ResponseBody
    public Object getData(String riqi) {
    	if(StringUtils.isEmpty(riqi)){
    		return renderError("日期不能为空");
    	}else{
    		Object obj = proTbmDgstateInfoService.getGzDbInfo(riqi);
    		return renderSuccess(obj);
    	}
    }
}
