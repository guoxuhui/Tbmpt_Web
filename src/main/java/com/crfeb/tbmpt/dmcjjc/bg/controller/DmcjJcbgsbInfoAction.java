package com.crfeb.tbmpt.dmcjjc.bg.controller;

import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Arrays;
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
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.util.IOUtils;
import com.crfeb.tbmpt.commons.base.BaseController;
import com.crfeb.tbmpt.commons.calc.CalcBhl;
import com.crfeb.tbmpt.commons.utils.ExcelUtils;
import com.crfeb.tbmpt.commons.utils.ExportDto;
import com.crfeb.tbmpt.commons.utils.ExportExcelUtil;
import com.crfeb.tbmpt.commons.utils.ExportSetUtil;
import com.crfeb.tbmpt.commons.utils.PageInfo;
import com.crfeb.tbmpt.commons.utils.StringUtils;
import com.crfeb.tbmpt.dmcjjc.bg.model.JcbgInfo;
import com.crfeb.tbmpt.dmcjjc.bg.model.JcbgsbInfo;
import com.crfeb.tbmpt.dmcjjc.bg.model.vo.JcbgsbInfoDto;
import com.crfeb.tbmpt.dmcjjc.bg.service.IDmcjJcbgInfoService;
import com.crfeb.tbmpt.dmcjjc.bg.service.IDmcjJcbgsbInfoService;
import com.crfeb.tbmpt.dmcjjc.info.model.vo.DmcjJcInfoDetailsDto;
import com.crfeb.tbmpt.dmcjjc.info.model.vo.DmcjJcInfoDto;
import com.crfeb.tbmpt.dmcjjc.info.service.IDmcjJcPointService;
import com.crfeb.tbmpt.project.model.ProProjectinfo;
import com.crfeb.tbmpt.project.service.IProProjectinfoService;

@Controller
@RequestMapping("/dmcjjc/bgsb")
public class DmcjJcbgsbInfoAction extends BaseController {
	private static final Logger LOGGER = LogManager
			.getLogger(DmcjJcbgsbInfoAction.class);
	
    @Autowired
    private IDmcjJcbgsbInfoService jcbgsbInfoService;
    @Autowired
    private IDmcjJcbgInfoService jcbgInfoService;
    @Autowired
    private IProProjectinfoService proProjectinfoService;
    @Autowired
    private IDmcjJcPointService pointService;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public String manager() {
        return "dmcjjc/jcbgsb/list";
    }
    
    @RequestMapping(value = "/list", method = RequestMethod.POST)
	@ResponseBody
	public Object list(JcbgsbInfoDto dto,Integer page, Integer rows, String sort, String order) {
		PageInfo pageInfo = new PageInfo(page, rows, sort, order);
		Map<String, Object> condition = new HashMap<String, Object>();
		//设置查询条件
		if(null != dto.getGcbh()){
			condition.put("gcbh", dto.getGcbh());
		}
		if(null != dto.getBeginTime()){
			condition.put("beginTime", dto.getBeginTime());
		}
		if(null != dto.getEndTime()){
			condition.put("endTime", dto.getEndTime());
		}
		String userId = String.valueOf(getCurrentUser().getId());
		condition.put("uid", userId);
		
		//根据用户id查出用户有哪些项目
		List<ProProjectinfo> proj = proProjectinfoService.getProjectInfosByUserId(userId); 
		if(null == proj)
			return pageInfo;
		
		List<String> ls = new ArrayList<String>();
		for(ProProjectinfo info:proj){
			ls.add("'"+info.getId()+"'");
		}
		String instr = StringUtils.ListToString(ls, ",");
		
		//用查出的所有项目id做为查询条件
		condition.put("ins",instr);
		
		pageInfo.setCondition(condition);
		jcbgsbInfoService.selectDataGrid(pageInfo);
		return pageInfo;
	}
    
    /**
	 * 跳转到选择监测报告信息页面
	 */
    @RequestMapping(value = "/addPage", method = RequestMethod.GET)
    public String addPage() {
        return "dmcjjc/jcbgsb/import";
    }
    
    @RequestMapping("/temp")  
	@ResponseBody
	public Object temp(String jcTime){
		//先检查该监测时间是否已经上报过文件
		String userId = String.valueOf(getCurrentUser().getId());
	    ProProjectinfo proj = proProjectinfoService.getProjectInfoByUserId(userId); 
	    if(null == proj){
        	return renderError("无工程，上报失败。");
        }

		return renderSuccess("上报操作：成功。");
	}
    
    /**
     * 保存数据，从监测报告表到监测报告上报表，包括主表和子表
     * id:监测报告主表id
     */
 	@RequestMapping(value = "/tempsave",method = RequestMethod.GET)
 	public String tempsave(String id,Model mode) {
 		//查出该日常监测信息主表
 		JcbgInfo info = new JcbgInfo();
 		info.setId(id);
 		info = jcbgInfoService.selectOne(info);
 		
 		//查子表数据
 		List<DmcjJcInfoDetailsDto> details = jcbgInfoService.selectDetailsByFid(info.getId());
 		for (DmcjJcInfoDetailsDto dto : details) {
 			/**
			 * 1.1、没有上次高程使用初始高程
			 * 1.2、没有初始高程页面置空 0 页面显示置空 同时备注显示 初始高程
			 * 
			 * 2.1、没有上次高程使用初始高程
			 * 
			 * 3.1、有上次高程使用上次高程 （正常情况）
			 */
			// 根据监测点编号和监测时间取该监测点上次高程，如果没有，则上次高程为初始高程
			Float scgc = pointService.getJcBgsbScgc(dto.getJcdNo(), info.getJcTime(),info.getGcbh());
			//1.1、没有上次高程使用初始高程
			if(null == scgc || 0f == scgc.floatValue()){
				scgc = dto.getCsgc();
				//1.2、没有初始高程页面置空 0 页面显示置空 同时备注显示 初始高程
				if(null == scgc || 0f == scgc.floatValue()){
					dto.setScgc(scgc);
					dto.setBcbhl(0f);
					dto.setLjbhl(0f);
					dto.setBhsl(0f);
					
					dto.setRemarks("初始高程");
				}else{
					dto.setScgc(scgc);
					CalcBhl.calc(dto);// 计算变化量
				}
			}else{
				dto.setScgc(scgc);
				CalcBhl.calc(dto);// 计算变化量
			}
 		}
 		mode.addAttribute("id", info.getId());  
 		mode.addAttribute("list", JSON.toJSONString(details));  
 		mode.addAttribute("gcbh", info.getGcbh());  
 		mode.addAttribute("tianqi", info.getTianqi());  
 		mode.addAttribute("remarks", info.getRemarks());  
 		mode.addAttribute("jcTime", info.getJcTime());  
 		return "dmcjjc/jcbgsb/add";
 	}
    
    /**
   	 * 跳转到详情页面
   	 */
   @RequestMapping(value = "/showPage", method = RequestMethod.GET)
   public String showPage(String id,String jcTime,Model model) {
	   JcbgsbInfo cond = new JcbgsbInfo();
	   cond.setId(id);
	   //查询主表
	   cond = jcbgsbInfoService.selectOne(cond);
	   model.addAttribute("jc", cond);
       return "dmcjjc/jcbgsb/show";
   }
   
   /**
	 * 查询和编辑的详情页面查询明细表格的方法
	 * 
	 * @param jcTime
	 */
	@RequestMapping(value = "/detailList", method = RequestMethod.POST)
	@ResponseBody
	public Object detailList(String fid, String jcTime, Integer page,
			Integer rows, String sort, String order) {
		String userId = String.valueOf(getCurrentUser().getId());
		ProProjectinfo proj = proProjectinfoService
				.getProjectInfoByUserId(userId);
		// 查询子表(以分页的方式查询)
//		PageInfo pageInfo = new PageInfo(page, rows, sort, order);
		PageInfo pageInfo = new PageInfo(0, 65530, "id", "desc");
		Map<String, Object> condition = new HashMap<String, Object>();
		condition.put("fid", fid);
		pageInfo.setCondition(condition);
		jcbgsbInfoService.calcBhl(fid, jcTime, pageInfo, proj.getId());
		return pageInfo;
	}
	/**
	 * 方法说明：查询页面导出明细excel
	 * @param exportDto
	 * @param dto
	 * @param response
	 * @param request
	 * @author:YangYj
	 * @Time: 2016年12月13日 上午11:11:24
	 */
	@SuppressWarnings({"unchecked"})
	@RequestMapping(value="/detailExpXls")
	public void detailExpXls(ExportDto exportDto,DmcjJcInfoDetailsDto dto, HttpServletResponse response,HttpServletRequest request){
		try {
			String fid = (String)request.getParameter("fid");
			String jcTime = (String) request.getParameter("jcTime");
			String projectId = (String) request.getParameter("projectId");
			PageInfo pageInfo = new PageInfo(0, 65530, "id", "desc");
			Map<String, Object> condition = new HashMap<String, Object>();
			condition.put("fid", fid);
			if(StringUtils.isNotBlank(exportDto.getIds())){
    			String[] array = exportDto.getIds().split(ExportDto.SPLIT_STR);
    			String ids = "";
    			for(String id:array){
    				ids +=",'"+id+"'";
    			}
    			ids = ids.substring(1);
				condition.put("ids", ids);
    		}
			pageInfo.setCondition(condition);
			jcbgsbInfoService.calcBhl(fid,jcTime, pageInfo,projectId);
			ExportExcelUtil.writeExcelToClient(response, exportDto, ExportSetUtil.objectToListMap(pageInfo.getRows()));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
    
    /**
	 * 导出选中数据到excel
	 */
	@RequestMapping("/expBgsb")
	@ResponseBody
	public void expBgsb(String[] ids, HttpServletResponse response) {
		// 生成excel并下载
		HSSFWorkbook wb = jcbgsbInfoService.expData(Arrays.asList(ids));
		String filename = "监测报告上报数据.xls";
		String iso_filename = ExcelUtils.parseGBK(filename);
		response.setContentType("text/plain");
		response.setHeader("Content-Disposition","attachment;filename=" + iso_filename);
		
		OutputStream ouputStream = null; 
		try {
			ouputStream = response.getOutputStream();
			wb.write(ouputStream);
			ouputStream.flush();
		} catch (IOException e) {
			LOGGER.error("expBgsb error",e);
		} finally{
			IOUtils.close(ouputStream);
		}
	}
	
	/**
     * 数据导出
     * @param exportDto
     * @param Dto
     * @param response
     */
	@SuppressWarnings({"unchecked"})
	@RequestMapping(value="/expXls")
	public void expXls(ExportDto exportDto,JcbgsbInfoDto dto, HttpServletResponse response){
		try {
			PageInfo pageInfo = new PageInfo(0, 65530, "id","desc");
			Map<String, Object> condition = new HashMap<String, Object>();
			if(StringUtils.isNotBlank(exportDto.getIds())){
				List<String> idsList =new ArrayList<String>();
				for(String info:exportDto.getIds().split(ExportDto.SPLIT_STR)){
					idsList.add("'"+info+"'");
				}
				
				String idsstr = StringUtils.ListToString(idsList, ",");
				condition.put("ids", idsstr);
				pageInfo.setCondition(condition);
				jcbgsbInfoService.selectDataGrid(pageInfo);
				ExportExcelUtil.writeExcelToClient(response, exportDto, ExportSetUtil.objectToListMap(pageInfo.getRows()));
			}else{
				//设置查询条件
				if(null != dto.getGcbh()){
					condition.put("gcbh", dto.getGcbh());
				}
				if(null != dto.getBeginTime()){
					condition.put("beginTime", dto.getBeginTime());
				}
				if(null != dto.getEndTime()){
					condition.put("endTime", dto.getEndTime());
				}
				String userId = String.valueOf(getCurrentUser().getId());
				condition.put("uid", userId);
				
				//根据用户id查出用户有哪些项目
				List<ProProjectinfo> proj = proProjectinfoService.getProjectInfosByUserId(userId); 
				if(null == proj){
					ExportExcelUtil.writeExcelToClient(response, exportDto, ExportSetUtil.objectToListMap(pageInfo.getRows()));
					return;
				}
				
				List<String> ls = new ArrayList<String>();
				for(ProProjectinfo info:proj){
					ls.add("'"+info.getId()+"'");
				}
				String instr = StringUtils.ListToString(ls, ",");
				
				//用查出的所有项目id做为查询条件
				condition.put("ins",instr);
				
				pageInfo.setCondition(condition);
				jcbgsbInfoService.selectDataGrid(pageInfo);
				ExportExcelUtil.writeExcelToClient(response, exportDto, ExportSetUtil.objectToListMap(pageInfo.getRows()));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
    
   /**
	 * 保存
	 */
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	@ResponseBody
	public Object save(DmcjJcInfoDto dto) {
		try {
			//如果没有子表信息，也不能保存
	       String json = dto.getDetails();
			//子表信息
			if(null == json || "[]".equals(json) || "".equals(json.trim())){
				return renderError("无监测报告上报明细，新增失败");
			}
			
			//相同监测时间不能重复保存
		   JcbgsbInfo jc = new JcbgsbInfo();
		   jc.setGcbh(dto.getGcbh());
		   jc.setJcTime(dto.getJcTime());
		   int count = jcbgsbInfoService.selectCount(jc);
		   if(count > 0){
		       return renderError("相同时间的监测报告上报已存在，新增失败。");
		   }
			
			//保存监测报告上报信息
			jcbgsbInfoService.save(dto);
			//更新监测报告状态为已上报
			jcbgInfoService.updateShangbaoStatus(dto.getId());
		} catch (Exception e) {
			LOGGER.error("DmcjJcbgInfoAction save失败", e);
			return renderError("添加操作：失败。");
		}
		return renderSuccess("添加操作：成功。");
	}
	
    /**
	 * 删除
	 */
	@RequestMapping(value = "/del",method = RequestMethod.POST)
	@ResponseBody
	public Object del(String[] ids) {
		Boolean falt = false;
		try {
			falt = jcbgsbInfoService.deleteAndDetailsList(ids);
		} catch (Exception e) {
			this.log("删除", false, "操作失败");
			LOGGER.error("DmcjJcbgsbInfoAction del失败", e);
			return renderError("删除操作：失败。");
		}
		if(falt){
			this.log("删除", true, "操作成功");
			return renderSuccess("删除操作：成功。");
		}else{
			return renderSuccess("删除操作：失败。原因：已经确认的数据不能删除！");
		}
	}
	
	/**
	 * 撤销确认
	 */
	@RequestMapping(value = "/jy",method = RequestMethod.POST)
	@ResponseBody
	public Object jy(String[] ids) {
		String tempIds = "";
		try {
			for (String id : ids) {
				tempIds +=","+id;
				jcbgsbInfoService.jy(id);
			}
			
		} catch (Exception e) {
			this.log("撤销确认", false, "操作失败。ids:"+tempIds);
			LOGGER.error("DmcjJcbgsbInfoAction jy失败", e);
			return renderError("撤销确认：失败。");
		}
		this.log("撤销确认", true, "操作成功。ids:"+tempIds);
		return renderSuccess("撤销确认：成功。");
	}

	/**
	 * 确认
	 */
	@RequestMapping(value = "/qy",method = RequestMethod.POST)
	@ResponseBody
	public Object qy(String[] ids) {
		String tempIds = "";
		try {
			for (String id : ids) {
				tempIds +=","+id;
				jcbgsbInfoService.qy(id);
			}
		} catch (Exception e) {
			this.log("确认", false, "操作失败。ids:"+tempIds);
			LOGGER.error("DmcjJcbgsbInfoAction qy失败", e);
			return renderError("确认操作：失败。");
		}
		this.log("确认", true, "操作成功。ids:"+tempIds);
		return renderSuccess("确认操作：成功。");
	}
	    /**
		 * 跳转到编辑页面
	     * @param model 
		 */
	    @RequestMapping(value = "/editPage", method = RequestMethod.GET)
	    public String editPage(Model model,String jcTime,String id) {
	    	JcbgsbInfo cond = new JcbgsbInfo();
			cond.setId(id);
			// 查询主表
			cond = jcbgsbInfoService.selectOne(cond);
			model.addAttribute("jc", cond);
	        return "dmcjjc/jcbgsb/edit";
	    }
	    /**
		 * 方法说明：主子表一起更新
		 * @param dto
		 * @return
		 * @author:YangYj
		 * @Time: 2016年12月13日 下午5:35:47
		 */
		@RequestMapping(value = "/updateAll", method = RequestMethod.POST)
		@ResponseBody
		public Object updateAll(DmcjJcInfoDto dto) {
			try {
				//相同监测时间不能重复
				Map<String, Object> cond = new HashMap<String,Object>();
		       cond.put("gcbh", dto.getGcbh());
		       cond.put("jctime", dto.getJcTime());
		       List<JcbgsbInfo> jcs = jcbgsbInfoService.selectByMap(cond);
		       if(jcs.size() > 1){
		           return renderError("相同时间的监测报告上报信息已存在，修改失败。");
		       }else if(jcs.size() == 1){
		    	   //等于1有可能是自己，要排除掉
		    	   if( ! jcs.get(0).getId().equals(dto.getId())){
		    		   return renderError("相同时间的监测报告已存在，修改失败。");
		    	   }
		       }
		       jcbgsbInfoService.updateAll(dto);
			} catch (Exception e) {
				LOGGER.error("编辑失败", e);
				this.log("编辑", false, "编辑失败!"+dto.getId());
				return renderError("编辑操作：失败。");
			}
			this.log("编辑", true, "数据更新成功。id："+dto.getId());
			LOGGER.info("数据更新成功。id："+dto.getId());
			return renderSuccess("编辑操作：成功。");
		}
	
}
