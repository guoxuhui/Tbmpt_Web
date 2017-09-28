package com.crfeb.tbmpt.dmcjjc.zdcjddb.controller;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.crfeb.tbmpt.commons.base.BaseController;
import com.crfeb.tbmpt.commons.utils.StringUtils;
import com.crfeb.tbmpt.dmcjjc.info.service.IDmcjWxJcInfoService;
import com.crfeb.tbmpt.dmcjjc.zdcjddb.service.IZdcjddbService;
import com.crfeb.tbmpt.project.model.ProRProjectSection;
import com.crfeb.tbmpt.project.service.IProProjectinfoService;
import com.crfeb.tbmpt.project.service.IProRProjectSectionService;

/**
 * @description：最大沉降点对比
 * @author：smxg
 * @date：2017年5月16日
 */
@Controller
@RequestMapping("/dmcjjc/zdcjddb")
public class zdcjddbAction extends BaseController{

	@Autowired
	IDmcjWxJcInfoService dmcjWxJcInfoService;
	@Autowired
	IZdcjddbService zdcjddbService;
	@Autowired
    IProProjectinfoService proProjectinfoService;
	@Autowired
	IProRProjectSectionService proRProjectSectionService;
	
    /**
     * 跳转菜单页面
     * @return 返回页面
     */
    @RequestMapping(value = "", method = RequestMethod.GET)
    public String index() {
        return "dmcjjc/zdcjddb/index";
    }
	
    /**
     * 获取监测点沉降数据统计结果，获取最大最小沉降数据列表
     * @param qjId 区间ID
     * @param sDate  开始日期 
     * @param eDate  截止日期
     * @return
     */
	@RequestMapping(value = "/getData")
	@ResponseBody
	public Object getData(String xmId,String qjId,String sDate,String eDate) {
		Object data = null;
		List<String> list = new ArrayList<String>();
		try {
			if(StringUtils.isNotBlank(xmId)){
				if(StringUtils.isNotBlank(qjId)){
					list.add(qjId);
					data = zdcjddbService.getZdcjddbData(list, sDate, eDate);
				}else{
					List<ProRProjectSection> sss = proRProjectSectionService.getSectionByProId(xmId);
					if(sss != null && !sss.isEmpty()){
						for(ProRProjectSection s:sss){
							list.add(s.getId());
						}
						data = zdcjddbService.getZdcjddbData(list, sDate, eDate);
					}
				}
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}
		if(data == null){
			return renderError("请求失败！");
		}else{
			return renderSuccess(data);				
		}
	}
	
}