package com.crfeb.tbmpt.sys.base.controller;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.util.IOUtils;
import com.crfeb.tbmpt.commons.base.BaseController;
import com.crfeb.tbmpt.commons.utils.BeanUtils;
import com.crfeb.tbmpt.commons.utils.DateUtils;
import com.crfeb.tbmpt.commons.utils.ExcelUtils;
import com.crfeb.tbmpt.commons.utils.ExportDto;
import com.crfeb.tbmpt.commons.utils.ExportExcelUtil;
import com.crfeb.tbmpt.commons.utils.ExportPdfUtil;
import com.crfeb.tbmpt.commons.utils.ExportSetUtil;
import com.crfeb.tbmpt.commons.utils.PageInfo;
import com.crfeb.tbmpt.commons.utils.ParseExcelFile;
import com.crfeb.tbmpt.commons.utils.StringUtils;
import com.crfeb.tbmpt.commons.utils.SysPropertieUtil;
import com.crfeb.tbmpt.commons.utils.UploadUtil;
import com.crfeb.tbmpt.dmcjjc.info.controller.DmcjJcInfoAction;
import com.crfeb.tbmpt.project.model.ProProjectinfo;
import com.crfeb.tbmpt.project.model.ProRProjectSection;
import com.crfeb.tbmpt.project.model.ProRSectionLine;
import com.crfeb.tbmpt.project.service.IProProjectinfoService;
import com.crfeb.tbmpt.project.service.IProRProjectSectionService;
import com.crfeb.tbmpt.project.service.IProRSectionLineService;
import com.crfeb.tbmpt.sddzgl.service.ISddzglZkxxService;
import com.crfeb.tbmpt.sys.base.model.SysUserMsg;
import com.crfeb.tbmpt.sys.base.model.dto.SysUserMsgDto;
import com.crfeb.tbmpt.sys.base.service.ISysUserMsgService;
import com.crfeb.tbmpt.sys.model.Orgz;
import com.crfeb.tbmpt.sys.model.User;

/**
 * <p>用户消息信息 控制器 Controller</p>
 * <p>模块：系统消息模块</p>
 * <p>日期：2017-06-03</p>
 * @version 1.0
 * @author wl_wpg
 */
@Controller
@RequestMapping(value="/sys/base/sysUserMsg")
public class SysUserMsgController extends BaseController{


	private static final Logger LOGGER = LogManager
			.getLogger(DmcjJcInfoAction.class);
	
	@Autowired 
	private ISysUserMsgService sysUserMsgService;
	
	
	@RequestMapping(value = "", method = RequestMethod.GET)
    public String manager() {
        return "sys/sysUserMsg/list";
    }
	
	/**
     * easyUi列表
     * @param pageNo 页码
     * @param pageSize 每页条数
     * @param dto 查询条件dto
     * @return 返回查询结果信息
     */
    @RequestMapping(value = "/dataGrid", method = RequestMethod.POST)
    @ResponseBody
    public Object dataGrid(SysUserMsgDto dto,Integer page, Integer rows, String sort, String order){
        PageInfo pageInfo = new PageInfo(page, rows, sort, order);
        Map<String, Object> condition = new HashMap<String, Object>();
        pageInfo.setCondition(condition);
      
        if(StringUtils.isNotBlank(dto.getMsgname())){
    		condition.put("msgname", dto.getMsgname());
		}
        if(StringUtils.isNotBlank(dto.getMsgtype())){
    		condition.put("msgtype", dto.getMsgtype());
		}
        if(StringUtils.isNotBlank(dto.getMsgtime())){
    		condition.put("msgtime", dto.getMsgtime());
		}
        if(StringUtils.isNotBlank(dto.getState())){
    		condition.put("state", dto.getState());
		}
        try {
        	sysUserMsgService.selectDataGrid(pageInfo,getCurrentUser());
        } catch (Exception e) {
           e.printStackTrace();
        }
       
        return pageInfo;
    }
	

	/**
     * 标记用户消息状态
     *
     * @param id
     * @param model
     * @return 
     */
	@RequestMapping(value = "/signState")
	@ResponseBody
	public Object signState(String[] ids) {
		try {
			for (String id : ids) {
				if(StringUtils.isNotBlank(id)){
		    		
		        	SysUserMsg sysUserMsg = sysUserMsgService.selectById(id);
		        	if(sysUserMsg !=null && StringUtils.isNotBlank(sysUserMsg.getId())){
		        		if(sysUserMsg.getState().equals("0")){
		        			sysUserMsg.setState("1");
		        		}
		        		if(sysUserMsgService.updateById(sysUserMsg))
		                {
		        			this.log("标记状态", true, "用户消息标记状态操作成功.ids:"+ids);
		        			LOGGER.info("用户消息标记状态操作成功.ids:"+ids);
		                }
		        	}
		    	}
			}
			return renderSuccess("操作：成功。");
		} catch (Exception e) {
			this.log("标记状态", false, "操作失败.ids:"+ids);
			LOGGER.error("SysUserMsgController signState失败", e);
			return renderError("操作：失败。");
		}
	}
   
	
	/**
     * 查询 当前用户 未读消息条数
     * @param pageNo 页码
     * @param pageSize 每页条数
     * @return 返回 当前用户未读消息条数
     */
    @RequestMapping(value = "/userMsgUnreadAmount")
    @ResponseBody
    public Object userMsgUnreadAmount(){
        PageInfo pageInfo = new PageInfo(0, 65530, "id", "desc");
        Map<String, Object> condition = new HashMap<String, Object>();
        pageInfo.setCondition(condition);
        condition.put("state", "0");
		
        try {
        	sysUserMsgService.selectDataGrid(pageInfo,getCurrentUser());
        } catch (Exception e) {
           e.printStackTrace();
        }
        return renderSuccess(pageInfo.getTotal());
        
    }
	
	
}
