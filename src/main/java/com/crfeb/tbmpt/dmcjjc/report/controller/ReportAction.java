package com.crfeb.tbmpt.dmcjjc.report.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.crfeb.tbmpt.commons.base.BaseController;
import com.crfeb.tbmpt.dmcjjc.info.model.JcInfo;
import com.crfeb.tbmpt.dmcjjc.info.model.vo.DmcjJcInfoDetailsDto;
import com.crfeb.tbmpt.dmcjjc.info.service.IDmcjWxJcInfoService;

/**
 * @description：报表跳转管理
 * @author：smxg
 * @date：2016-11-08 11:12
 */
@Controller
@RequestMapping("/dmcjjc/report")
public class ReportAction extends BaseController{

	@Autowired  
	private  HttpServletRequest request; 

	@Autowired
	IDmcjWxJcInfoService dmcjWxJcInfoService;
	/**
	 * http://127.0.0.1:8080/tbmpt/report/reportJsp/preview.jsp
		?rpx=/report/reportFiles/dmcjjcrb.rpx&rpxHome=&dfxHome=&arg=/report/reportFiles/dmcjjcrb_arg.rpx&orgID=
	 * @return
	 */
    @RequestMapping(value = "/jcrb", method = RequestMethod.GET)
    public String jcrb() {
    	StringBuffer url = new StringBuffer();
    	url.append("report/reportJsp/preview.jsp?");
    	//获取查询参数url
    	url.append("rpx=/report/reportFiles/dmcjjcrb.rpx&rpxHome=&dfxHome=&arg=/report/reportFiles/dmcjjcrb_arg.rpx&orgID=");
    	//获取用户部门信息
    	url.append(getCurrentUser().getOrgzId());
        return "redirect:/"+url.toString();
    }
    @RequestMapping(value = "/jcrbsb", method = RequestMethod.GET)
    public String jcrbsb() {
    	StringBuffer url = new StringBuffer();
    	url.append("report/reportJsp/preview.jsp?");
    	//获取查询参数url
    	url.append("rpx=/report/reportFiles/dmcjjcrbsb.rpx&rpxHome=&dfxHome=&arg=/report/reportFiles/dmcjjcrbsb_arg.rpx&orgID=");
    	//获取用户部门信息
    	url.append(getCurrentUser().getOrgzId());
        return "redirect:/"+url.toString();
    }
    
    @RequestMapping(value = "/dmcjjczb", method = RequestMethod.GET)
    public String dmcjjczb() {
    	StringBuffer url = new StringBuffer();
    	url.append("report/reportJsp/preview.jsp?");
    	//获取查询参数url
    	url.append("rpx=/report/reportFiles/dmcjjczb.rpx&rpxHome=&dfxHome=&arg=/report/reportFiles/dmcjjczb_arg.rpx&orgID=");
    	//获取用户部门信息
    	url.append(getCurrentUser().getOrgzId());
        return "redirect:/"+url.toString();
    }
    @RequestMapping(value = "/dmcjjczbsb", method = RequestMethod.GET)
    public String dmcjjczbsb() {
    	StringBuffer url = new StringBuffer();
    	url.append("report/reportJsp/preview.jsp?");
    	//获取查询参数url
    	url.append("rpx=/report/reportFiles/dmcjjczbsb.rpx&rpxHome=&dfxHome=&arg=/report/reportFiles/dmcjjczbsb_arg.rpx&orgID=");
    	//获取用户部门信息
    	url.append(getCurrentUser().getOrgzId());
        return "redirect:/"+url.toString();
    }
    /**
     * 地面沉降变化情况统计
     * @return
     */
    @RequestMapping(value = "/dmcjbhtj", method = RequestMethod.GET)
    public String dmcjbhtj() {
    	StringBuffer url = new StringBuffer();
    	url.append("report/reportJsp/preview.jsp?");
    	//获取查询参数url
    	url.append("rpx=/report/reportFiles/dmcjbhtj.rpx&rpxHome=&dfxHome=&arg=/report/reportFiles/dmcjbhtj_arg.rpx&orgID=");
    	//获取用户部门信息
    	url.append(getCurrentUser().getOrgzId());
        return "redirect:/"+url.toString();
    }
    /**
     * 无锡日报表
     * @return
     */
    @RequestMapping(value = "/dmcjjcrbwx", method = RequestMethod.GET)
    public String dmcjjcrb_wx() {
    	StringBuffer url = new StringBuffer();
    	url.append("report/reportJsp/preview.jsp?");
    	//获取查询参数url
    	url.append("rpx=/report/reportFiles/dmcjjcrbwx.rpx&rpxHome=&dfxHome=&arg=/report/reportFiles/dmcjjcrbwx_arg.rpx&orgID=");
    	//获取用户部门信息
    	url.append(getCurrentUser().getOrgzId());
        return "redirect:/"+url.toString();
    }
    
	/**
	 * 获取报表结果
	 * 
	 * @return
	 */
    /*
	@RequestMapping(value = "/dmcjjcrbwxDo")
	public String dmcjjcrbwxDo(String gcBh,String cxDate) {
		
    	StringBuffer url = new StringBuffer();
    	url.append("report/reportJsp/preview.jsp?");
    	//获取查询参数url
    	url.append("rpx=/report/reportFiles/dmcjjcrbwxpage.rpx&rpxHome=&dfxHome=&proname=");
    	//获取用户部门信息
    	url.append(gcBh);
    	cxDate = cxDate.replace("\\", "-");
    	url.append("&riqi="+cxDate);
    	url.append("&shij=");
        return "redirect:/"+url.toString();
	}
	*/
	/**
	 * 获取报表结果
	 * 
	 * @return
	 */
	@RequestMapping(value = "/dmcjjcrbwxDo")
	public String dmcjjcrbwxDo(String gcBh,String cxDate,Model model) {
		
		Map<String,Object> map = new HashMap<String ,Object>();
		map.put("gcbh", gcBh);
		map.put("jctime", cxDate);
		List<JcInfo> list = dmcjWxJcInfoService.selectByMap(map);

		int page = 0;
		List<String> pageList = new ArrayList<String>();
		if(list != null){
			List<DmcjJcInfoDetailsDto> dlist = dmcjWxJcInfoService.selectDetailsByFid(list.get(0).getId());
			if(dlist != null){
				page = (int) Math.ceil(dlist.size()/25);
				for(int i=0;i<page;i++){
					pageList.add(String.valueOf(i+1));
				}
			}
			
		}
		
    	StringBuffer url = new StringBuffer();
    	url.append("/report/reportJsp/preview.jsp?");
    	//获取查询参数url
    	url.append("rpx=/report/reportFiles/dmcjjcrbwxpage.rpx&rpxHome=&dfxHome=&proname=");
    	//获取用户部门信息
    	url.append(gcBh);
    	cxDate = cxDate.replace("\\", "-");
    	url.append("&riqi="+cxDate);
    	url.append("&shij=");
    	url.append("&yeshu=");
    	
    	model.addAttribute("url", url.toString());
    	model.addAttribute("page",page);
    	model.addAttribute("pageList",pageList);
        return "dmcjjc/wxjcinfo/report";
	}
}