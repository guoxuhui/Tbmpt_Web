package com.crfeb.tbmpt.log.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.crfeb.tbmpt.commons.result.Result;
import com.crfeb.tbmpt.commons.utils.ExportDto;
import com.crfeb.tbmpt.commons.utils.ExportExcelUtil;
import com.crfeb.tbmpt.commons.utils.ExportPdfUtil;
import com.crfeb.tbmpt.commons.utils.ExportSetUtil;
import com.crfeb.tbmpt.commons.utils.PageInfo;
import com.crfeb.tbmpt.commons.utils.StringUtils;
import com.crfeb.tbmpt.sys.model.dto.LogDto;
import com.crfeb.tbmpt.sys.service.ILogService;

/**
 * @description：日志管理
 * @author：smxg
 * @date：2016-10-10 11:12
 */
@Controller
@RequestMapping("/log/syslog")
public class LogController {

    @Autowired
    private ILogService sysLogService;


    @RequestMapping(value = "", method = RequestMethod.GET)
    public String manager() {
        return "log/syslog/index";
    }


    @RequestMapping(value = "/dataGrid1", method = RequestMethod.POST)
    @ResponseBody
    public PageInfo dataGrid1(Integer page, Integer rows) {
        PageInfo pageInfo = new PageInfo(page, rows);
        Map<String, Object> condition = new HashMap<String, Object>();
        pageInfo.setCondition(condition);
        sysLogService.selectDataGrid(pageInfo);
        return pageInfo;
    }
    
    
    /**
     * 日志列表 条件查询
     *
     * @param page 当前页码；
     * @param rows 每页条数；
     * @param sort 字段名称；
     * @param order 排序方式；
     * @return 分页对象
     * @author wl_wpg
     */
    @RequestMapping(value = "/dataGrid", method = RequestMethod.POST)
    @ResponseBody
    public Object dataGrid(LogDto dto,Integer page, Integer rows, String sort, String order) {
    	
    	PageInfo pageInfo = new PageInfo(page, rows, sort, order);
        Map<String, Object> condition = new HashMap<String, Object>();
        pageInfo.setCondition(condition);
       
	    /** 登陆名 */
        if (""!=dto.getLoginName() && StringUtils.isNotBlank(dto.getLoginName())) {
            condition.put("loginName", dto.getLoginName());
        }
        /** 操作系统 */
        if (dto.getSysName() != null && ""!=dto.getSysName()) {
            condition.put("sysName", dto.getSysName());
        }
        /** 开始时间*/
        if (dto.getStartTime() != null && ""!=dto.getStartTime()) {
            condition.put("startTime", dto.getStartTime());
        }
        /** 结束时间  && ""!=dto.getEndTime()*/
        if (dto.getEndTime() != null) {
            condition.put("endTime", dto.getEndTime());
        }
       	sysLogService.selectDataGrid(pageInfo);
    	
        return pageInfo;
    }

    /**
     * 清空日志
     */
	@RequestMapping(value="/clearLogs")
	@ResponseBody
	public Object clearLogs(){
		sysLogService.deleteAll();
		
        Result result = new Result();
        result.setSuccess(true);
        result.setMsg("删除成功！");
        return result;
	}
    
    /**
     * 数据导出
     * @param exportDto
     * @param Dto
     * @param response
     */
	@SuppressWarnings({"unchecked","rawtypes"})
	@RequestMapping(value="/expXls")
	public void expXls(ExportDto exportDto, HttpServletResponse response){
		try {
			List tbmlist = sysLogService.selectAll();
			ExportExcelUtil.writeExcelToClient(response, exportDto, ExportSetUtil.objectToListMap(tbmlist));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
    /**
     * 数据导出
     * @param exportDto
     * @param Dto
     * @param response
     */
	@SuppressWarnings({"unchecked","rawtypes"})
	@RequestMapping(value="/expPdf")
	public void expPdf(ExportDto exportDto, HttpServletResponse response){
		try {
			List tbmlist = sysLogService.selectAll();
			ExportPdfUtil.writePdfToClient(response, exportDto, ExportSetUtil.objectToListMap(tbmlist));
		} catch (Exception e) {
			e.printStackTrace();	
		}
	}
}
