package com.crfeb.tbmpt.risk.controller;

import com.crfeb.tbmpt.commons.base.BaseController;
import com.crfeb.tbmpt.commons.utils.StringUtils;
import com.crfeb.tbmpt.dmcjjc.info.model.JcInfo;
import com.crfeb.tbmpt.dmcjjc.info.model.vo.DmcjJcInfoDetailsDto;
import com.crfeb.tbmpt.dmcjjc.info.service.IDmcjWxJcInfoService;
import com.crfeb.tbmpt.project.model.ProProjectinfo;
import com.crfeb.tbmpt.project.service.IProProjectinfoService;
import com.ibm.icu.util.Calendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @description：报表跳转管理
 * @author：smxg
 * @date：2016-11-08 11:12
 */
@Controller
@RequestMapping("/fxsb/report")
public class RiskInfoAction extends BaseController{

	@Autowired  
	private  HttpServletRequest request; 

	@Autowired
	IDmcjWxJcInfoService dmcjWxJcInfoService;
	
	@Autowired
	private IProProjectinfoService proProjectinfoService;
	    	
	    
    @RequestMapping(value = "", method = RequestMethod.GET)
    public String index() {
		
		//根据用户id查出用户有哪些项目
  		List<ProProjectinfo> proj = proProjectinfoService.getProjectInfosByUserId(getCurrentUser().getId()); 
  		if(null != proj){
  			StringBuffer url = new StringBuffer();
  			url.append("report/reportJsp/showReport.jsp?");
  			//获取查询参数url
  			url.append("rpx=/fxsb.rpx&rpxHome=&dfxHome=&arg=/fxsb_arg.rpx&orgID=");
  			//获取用户部门信息
  			url.append(getCurrentUser().getOrgzId());
  			List<String> ls = new ArrayList<String>();
  	  		for(ProProjectinfo info:proj){
  	  			ls.add(info.getId());
  	  		}
  	  		String instr = StringUtils.ListToString(ls, ",");
  	  	   
  	  	    url.append("&proIds="+instr);
  	  	    
  	  	    //获取本周 周一 、周日 为默认查询日期
	  	  	SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
	  	  	Calendar cal = Calendar.getInstance();  
	  	    cal.setTime(new Date());  
	  	    // 判断要计算的日期是否是周日，如果是则减一天计算周六的，否则会出问题，计算到下一周去了  
	  	    int dayWeek = cal.get(Calendar.DAY_OF_WEEK);// 获得当前日期是一个星期的第几天  
	  	    if (1 == dayWeek) {  
	  	       cal.add(Calendar.DAY_OF_MONTH, -1);  
	  	    }  
	  	    // 设置一个星期的第一天，按中国的习惯一个星期的第一天是星期一  
	  	    cal.setFirstDayOfWeek(Calendar.MONDAY);  
	  	    // 获得当前日期是一个星期的第几天  
	  	    int day = cal.get(Calendar.DAY_OF_WEEK);  
	  	    // 根据日历的规则，给当前日期减去星期几与一个星期第一天的差值  
	  	    cal.add(Calendar.DATE, cal.getFirstDayOfWeek() - day);  
	  	    String imptimeBegin = sdf.format(cal.getTime());  
	  	    cal.add(Calendar.DATE, 6);  
	  	    String imptimeEnd =sdf.format(cal.getTime());  
	  	    
  	  	    url.append("&stime="+imptimeBegin);
  	  	    url.append("&etime="+imptimeEnd);
  			return "redirect:/"+url.toString();
  		}
  			
  		return null;	
  		
    }

	
}