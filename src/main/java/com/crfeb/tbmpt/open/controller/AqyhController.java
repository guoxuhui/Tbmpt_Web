package com.crfeb.tbmpt.open.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONArray;
import com.crfeb.tbmpt.aqsc.base.model.PeiXunRenYuan;
import com.crfeb.tbmpt.aqsc.base.model.SpecialMan;
import com.crfeb.tbmpt.aqsc.base.model.Worklog;
import com.crfeb.tbmpt.aqsc.base.model.dto.WorklogDto;
import com.crfeb.tbmpt.aqsc.base.service.PeiXunRenYuanService;
import com.crfeb.tbmpt.aqsc.base.service.SpecialManService;
import com.crfeb.tbmpt.aqsc.base.service.WorklogService;
import com.crfeb.tbmpt.commons.base.BaseController;
import com.crfeb.tbmpt.commons.utils.IdCard;
import com.crfeb.tbmpt.commons.utils.PageInfo;
import com.crfeb.tbmpt.commons.utils.StringUtils;
import com.crfeb.tbmpt.open.service.IOpenAqyhService;
import com.crfeb.tbmpt.open.service.IOpenCommService;
import com.crfeb.tbmpt.open.service.IOpenPushService;
import com.crfeb.tbmpt.sys.model.User;

import oracle.net.aso.p;


/**
 * <p>安全隐患排查  Controller</p>
 * <p>系统：安全隐患排查</p>
 * <p>模块：安全隐患排查</p>
 * <p>日期：2016-12-27</p>
 * @version 1.0
 * @author smxg
 */
@Controller
@RequestMapping("/open/aqyh")
public class AqyhController extends BaseController {
	
    @Autowired
    IOpenCommService openCommServiceImpl;
    @Autowired
    IOpenPushService openPushService;
    @Autowired
    IOpenAqyhService openAqyhService;
    
    @Autowired
    private WorklogService worklogService;
    @Autowired
    private SpecialManService specialManService;
    @Autowired 
    private PeiXunRenYuanService peiXunRenYuanService;
    /**
     * 获取特种作业人员管理列表数据
     * @param page 当前页
     * @param name 查询人员名称
     * @return 返回人员列表数据
     */
	@RequestMapping(value = "/getTzzyryglList")
	@ResponseBody
	public Object getTzzyryglList (int page,String name) {
		PageInfo pageInfo = (PageInfo) openAqyhService.getSpecialManList(page,10,name,getCurrentUser());
		Object json = null;
		if(pageInfo.getTotal()<=(page-1)*10){
			json = new JSONArray();
		}else{
			json = JSONArray.toJSON(pageInfo.getRows());
		}
		return renderSuccess(json);
	}
	
    /**
     * 获取培训记录管理列表数据
     * @param page 当前页
     * @param name 查询人员名称
     * @return 返回人员培训记录数据
     */
	@RequestMapping(value = "/getRypxjlglList")
	@ResponseBody
	public Object getRypxjlglList (int page,String name) {
		PageInfo pageInfo = (PageInfo) openAqyhService.getPeiXunRenYuanList(page,10,name,getCurrentUser());
		Object json = null;
		if(pageInfo.getTotal()<=(page-1)*10){
			json = new JSONArray();
		}else{
			json = JSONArray.toJSON(pageInfo.getRows());
		}
		return renderSuccess(json);
	}
    
    /**
     * 获取人员日志管理列表数据
     * @param page 当前页
     * @param name 查询人员名称
     * @return 返回人员日志数据
     */
	@RequestMapping(value = "/getAqyrzglList")
	@ResponseBody
	public Object getAqyrzglList (int page,String rq) {
		User user = getCurrentUser();
		PageInfo pageInfo = (PageInfo) openAqyhService.getWorklogList(page,10,user.getEmpId(),getCurrentUser());
		Object json = null;
		if(pageInfo.getTotal()<=(page-1)*10){
			json = new JSONArray();
		}else{
			json = JSONArray.toJSON(pageInfo.getRows());
		}
		return renderSuccess(json);
	}
	
	@RequestMapping(value = "/addWorklogPage", method = RequestMethod.GET)
	public String addWorklogPape() {
		return "open/aqpc/aqpcAdd";
	}
	
	@RequestMapping(value = "/addWorklogDo")
	public Object addWorklogDo(WorklogDto worklogDto, Model model) {
		worklogDto.setWorkDay(worklogDto.getWorkDay().replace("/", "-"));
		String errmessage = "";
		try {
			errmessage = worklogService.checkClumIfexits(worklogDto, new String[] { "workDay" });
			log(null, false, errmessage);
			if (errmessage != null) {

				model.addAttribute("data", renderError(errmessage));
				model.addAttribute("isDo", true);
				renderError(errmessage);
			}
			worklogService.save(worklogDto, getCurrentUser());
			log(null, true, "新增成功!");
		} catch (Exception e) {
			log(null, false, "新增失败!");
			e.printStackTrace();
		}

		model.addAttribute("data", renderSuccess("提交成功"));
		model.addAttribute("isDo", true);

		return "open/aqpc/aqpcAdd";
	}
	
	/**
     * 添加工作日志
     * @param gczlYdxjGPZLDDInfo 要新增的实体信息
     * @return 返回保存成功/失败的提示信息
     */
    @RequestMapping(value = "/addWorklog")
    @ResponseBody
    public Object addWorklog(WorklogDto worklogDto) {
    	String errmessage = "";
        try {
    	errmessage = worklogService.checkClumIfexits(worklogDto,new String[]{"workDay"});
	    log(null, false, errmessage);
    	if(errmessage!=null) return renderError(errmessage);
          worklogService.save(worklogDto,getCurrentUser());
          log(null, true, "新增成功!");
        } catch (Exception e) {
          log(null, false, "新增失败!");
          e.printStackTrace();
        }
        return renderSuccess("添加成功！");
    }

	@RequestMapping(value = "/editWorklogPage", method = RequestMethod.GET)
	public String editWorklogPape(Model model,String id) {
		Worklog log = null;
		try {
			if(!StringUtils.isEmpty(id)){
				log = worklogService.findById(id);
				
				model.addAttribute("log", log);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return "open/aqpc/aqpcEdit";
	}
	
	@RequestMapping(value = "/editWorklogDo")
	public Object editWorklogDo(WorklogDto worklogDto, Model model) {
		worklogDto.setWorkDay(worklogDto.getWorkDay().replace("/", "-"));
		String errmessage = "";
		try {
			errmessage = worklogService.checkClumIfexits(worklogDto, new String[] { "workDay" });
			if (errmessage != null) {

				model.addAttribute("data", renderError(errmessage));
				model.addAttribute("isDo", true);
				renderError(errmessage);
			}
			errmessage = worklogService.update(worklogDto, getCurrentUser());
			if (StringUtils.isNotBlank(errmessage)) {
				log(null, false, errmessage);

				if (errmessage != null) {

					model.addAttribute("data", renderError(errmessage));
					model.addAttribute("isDo", true);
					renderError(errmessage);
				}
			}
			log(null, true, "编辑成功:" + worklogDto.getId());
		} catch (Exception e) {
			log(null, false, "编辑失败!");
			e.printStackTrace();
		}

		model.addAttribute("data", renderSuccess("编辑成功"));
		model.addAttribute("isDo", true);

		return "open/aqpc/aqpcEdit";
	}
    
    /**
     * 修改工作日志
     * @param gczlYdxjGPZLDDInfo 要修改的实体信息
     * @return 返回编辑成功/失败的提示信息
     */
    @RequestMapping("/editWorklog")
    @ResponseBody
    public Object editWorklog(WorklogDto worklogDto) {
    	String errmessage = "";
        try {
    	errmessage = worklogService.checkClumIfexits(worklogDto,new String[]{"workDay"});
    	if(errmessage!=null) return renderError(errmessage);
           errmessage = worklogService.update(worklogDto,getCurrentUser());
           if(StringUtils.isNotBlank(errmessage)){
             log(null, false, errmessage);
             return renderError(errmessage);
           }
           log(null, true, "编辑成功:"+worklogDto.getId());
        } catch (Exception e) {
           log(null, false, "编辑失败!");
           e.printStackTrace();
        }
	      log(null, true, errmessage);
        return renderSuccess("编辑成功!");
    }

    
    /**
     * 删除工作日志
     * @param ids 要删除的数据的ID集合
     * @return 返回删除成功/失败的提示信息
     */
    @RequestMapping("/delWorklog")
    @ResponseBody
    public Object delWorklog(String ids) {
    	String[] idss = ids.split(",");
    	List<String> idlist = new ArrayList<String>();
    	Collections.addAll(idlist, idss);
    	worklogService.deleteBatchIds(idlist);
    	log(null, true, "删除："+ids);
    	return renderSuccess("删除成功！");
    }
    
    
    /**
     * 返回特种人员详情页面
     * @param id 特种人员信息 id
     * @return 返回特种人员详情页面地址
     * @author Lzh
     * @date 2017年2月24日
     */
    @RequestMapping(value = "/aqyhTZZYRYInfoPage", method = RequestMethod.GET)
	public String aqyhTZZYRYInfoPage(Model model,String id) {
		SpecialMan specialMan = null;
		try {
			if(!StringUtils.isEmpty(id)){
				specialMan = specialManService.findById(id);
				
				model.addAttribute("info", specialMan);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return "open/aqpc/aqyhTZZYRYInfo";
	}
    
    /**
     * 返回培训人员详情页面
     * @param id 培训人员信息 id
     * @return 返回培训人员详情页面地址
     * @author Lzh
     * @date 2017年2月24日
     */
    @RequestMapping(value = "/aqyhPXRYInfoPage", method = RequestMethod.GET)
	public String aqyhPXRYInfoPage(Model model,String id) {
    	PeiXunRenYuan peiXunRenYuan = null;
		try {
			if(!StringUtils.isEmpty(id)){
				
				peiXunRenYuan = peiXunRenYuanService.findById(id);
				peiXunRenYuan.setAge(IdCard.getAgeByIdCard(peiXunRenYuan.getCardNo())+"");
				model.addAttribute("info", peiXunRenYuan);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return "open/aqpc/aqyhPXRYInfo";
	}
}
