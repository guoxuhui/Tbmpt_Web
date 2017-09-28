package com.crfeb.tbmpt.dmcjjc.info.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

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

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.util.IOUtils;
import com.crfeb.tbmpt.commons.base.BaseController;
import com.crfeb.tbmpt.commons.utils.BeanUtils;
import com.crfeb.tbmpt.commons.utils.CommUtils;
import com.crfeb.tbmpt.commons.utils.DateUtils;
import com.crfeb.tbmpt.commons.utils.ExcelUtils;
import com.crfeb.tbmpt.commons.utils.ExportDto;
import com.crfeb.tbmpt.commons.utils.ExportExcelUtil;
import com.crfeb.tbmpt.commons.utils.ExportSetUtil;
import com.crfeb.tbmpt.commons.utils.PageInfo;
import com.crfeb.tbmpt.commons.utils.ParseDATFile;
import com.crfeb.tbmpt.commons.utils.ParseXLSXFile;
import com.crfeb.tbmpt.commons.utils.StringUtils;
import com.crfeb.tbmpt.commons.utils.SysPropertieUtil;
import com.crfeb.tbmpt.commons.utils.UploadUtil;
import com.crfeb.tbmpt.dmcjjc.bg.model.JcbgInfo;
import com.crfeb.tbmpt.dmcjjc.bg.service.IDmcjJcbgInfoService;
import com.crfeb.tbmpt.dmcjjc.info.model.JcDetails;
import com.crfeb.tbmpt.dmcjjc.info.model.JcInfo;
import com.crfeb.tbmpt.dmcjjc.info.model.JcPoint;
import com.crfeb.tbmpt.dmcjjc.info.model.vo.DmcjJcInfoDetailsDto;
import com.crfeb.tbmpt.dmcjjc.info.model.vo.DmcjJcInfoDto;
import com.crfeb.tbmpt.dmcjjc.info.service.IDmcjJcInfoDetailService;
import com.crfeb.tbmpt.dmcjjc.info.service.IDmcjJcInfoService;
import com.crfeb.tbmpt.dmcjjc.info.service.IDmcjJcPointService;
import com.crfeb.tbmpt.project.model.ProProjectinfo;
import com.crfeb.tbmpt.project.service.IProProjectinfoService;

@Controller
@RequestMapping("/dmcjjc/dmcjjcinfo")
public class DmcjJcInfoAction extends BaseController {
	private static final Logger LOGGER = LogManager
			.getLogger(DmcjJcInfoAction.class);
	
    @Autowired
    private IDmcjJcInfoService dmcjJcInfoService;
    @Autowired
    private IProProjectinfoService proProjectinfoService;
    @Autowired
    private IDmcjJcPointService pointService;
    @Autowired
    private IDmcjJcInfoDetailService jcInfoDetailService;
	@Autowired
	private IDmcjJcbgInfoService jcbgService;
	
	 private String fileRelativePath = "/upload/dmcjjc/file/"+DateUtils.format(new Date(), "yyyyMMdd");//文件上传相对路径

    /**
     * 跳转到列表页面
     */
    @RequestMapping(value = "", method = RequestMethod.GET)
    public String manager() {
        return "dmcjjc/jcinfo/list";
    }

    /**
     * 列表页面分页查询
     */
    @RequestMapping(value = "/list", method = RequestMethod.POST)
	@ResponseBody
	public Object list(DmcjJcInfoDto dto,Integer page, Integer rows, String sort, String order) {
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
		if(null != dto.getIfqr() && !"".equals(dto.getIfqr().trim())){
			condition.put("ifqr", dto.getIfqr());
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
		dmcjJcInfoService.selectDataGrid(pageInfo);
		return pageInfo;
	}
    
    /**
	 * 删除
	 */
	@RequestMapping(value = "/del",method = RequestMethod.POST)
	@ResponseBody
	public Object del(String[] ids) {
		try {
			for (String id : ids) {
				JcInfo cond = new JcInfo();
				cond.setId(id);
				//删子表
				dmcjJcInfoService.deleteDetails(id);
				//删主表
				dmcjJcInfoService.deleteSelective(cond);
			}
		} catch (Exception e) {
			this.log("删除", false, "操作失败");
			LOGGER.error("DmcjJcInfoAction del失败", e);
			return renderError("删除操作：失败。");
		}
		this.log("删除", true, "操作成功");
		return renderSuccess("删除操作：成功。");
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
				JcInfo info = new JcInfo();
				info.setId(id);
				info = dmcjJcInfoService.selectOne(info);
				String jcTime = info.getJcTime();
				String gcbh = info.getGcbh();
				//如果在同一工程相同监测时间的条件下，存在监测报告信息，则不能进行反向确认操作，
				//并提示“选择的日常监测信息已形成监测报告，操作失败。”
				JcbgInfo bginfo = new JcbgInfo();
				bginfo.setGcbh(gcbh);
				bginfo.setJcTime(jcTime);
				int i = jcbgService.selectCount(bginfo);
				if(i > 0){
					return renderError("选择的日常监测信息已形成监测报告，操作失败。");
				}
			}
			
			for (String id : ids) {
				dmcjJcInfoService.jy(id);
			}
			
		} catch (Exception e) {
			this.log("撤销确认", false, "操作失败。ids:"+tempIds);
			LOGGER.error("DmcjJcInfoAction jy失败", e);
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
				dmcjJcInfoService.qy(id);
			}
		} catch (Exception e) {
			this.log("确认", false, "操作失败。ids:"+tempIds);
			LOGGER.error("DmcjJcInfoAction qy失败", e);
			return renderError("确认操作：失败。");
		}
		this.log("确认", true, "操作成功。ids:"+tempIds);
		return renderSuccess("确认操作：成功。");
	}
	
	/**
	 * 根据主键批量删除子表记录
	 */
	@RequestMapping(value = "/deleteDetails",method = RequestMethod.POST)
	@ResponseBody
	public Object deleteDetails(String[] ids) {
		try {
			dmcjJcInfoService.deleteDetailsByIds(Arrays.asList(ids));
		} catch (Exception e) {
			LOGGER.error("DmcjJcInfoAction deleteDetails失败", e);
			return renderError("删除操作：失败。");
		}
		return renderSuccess("删除操作：成功。");
	}
	
	/**
	 * 导出选中数据到excel
	 * @throws Exception 
	 */
	@RequestMapping("/expJcInfo")
	@ResponseBody
	public void expJcInfo(String[] ids, HttpServletResponse response) throws Exception {
		// 生成excel并下载
		String userId = String.valueOf(getCurrentUser().getId());
		ProProjectinfo proj = proProjectinfoService.getProjectInfoByUserId(userId); 
		if(null == proj){
			throw new Exception("无工程，无法导出数据");
		}
		HSSFWorkbook wb = dmcjJcInfoService.expData(Arrays.asList(ids),proj.getId());
		String filename = "日常监测数据.xls";
		String iso_filename = ExcelUtils.parseGBK(filename);
		response.setContentType("text/plain");
		response.setHeader("Content-Disposition","attachment;filename=" + iso_filename);
		
		OutputStream ouputStream = null; 
		try {
			ouputStream = response.getOutputStream();
			wb.write(ouputStream);
			ouputStream.flush();
		} catch (IOException e) {
			LOGGER.error("expJcInfo error",e);
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
	public void expXls(ExportDto exportDto,DmcjJcInfoDto dto, HttpServletResponse response){
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
				dmcjJcInfoService.selectDataGrid(pageInfo);
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
				if(null != dto.getIfqr() && !"".equals(dto.getIfqr().trim())){
					condition.put("ifqr", dto.getIfqr());
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
				dmcjJcInfoService.selectDataGrid(pageInfo);
				ExportExcelUtil.writeExcelToClient(response, exportDto, ExportSetUtil.objectToListMap(pageInfo.getRows()));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 方法说明：查看页面导出明细数据excel
	 * @param exportDto
	 * @param dto
	 * @param response
	 * @param request
	 * @author:YangYj
	 * @Time: 2016年12月13日 上午11:09:17
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
			condition.put("ids", "");
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
			dmcjJcInfoService.calcBhl(fid,jcTime, pageInfo,projectId);
			ExportExcelUtil.writeExcelToClient(response, exportDto, ExportSetUtil.objectToListMap(pageInfo.getRows()));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
   	 * 跳转到导入页面
   	*/
   @RequestMapping(value = "/importPage", method = RequestMethod.GET)
   public String importPage() {
       return "dmcjjc/jcinfo/import";
   }
	
  /* *//**
    * 下载导入的文件
    *//*
   @RequestMapping("/download")
   public String download(String filepath,HttpServletRequest req){
	   return filepath;
   }*/
   
   /**
    * 下载导入的文件
    */
   @RequestMapping("/download")
   public String download(String filepath, HttpServletRequest request,
			HttpServletResponse response) {
		UploadUtil uploadUtil = new UploadUtil();
		String errorMessag = uploadUtil.downloadFile(filepath, request, response);
		if(StringUtils.isNotBlank(errorMessag)){
			this.log("下载文件", false, errorMessag);
			LOGGER.error(errorMessag);
		}
		return null;
	}

   
   /**
    * 上传文件，然后跳转到新增页面 
    */
	@RequestMapping("/upload")  
	@ResponseBody
	public Object upload(String jcTime,@RequestParam(value = "file", required = false) MultipartFile file, HttpServletRequest request, Model model) {  
		String userId = String.valueOf(getCurrentUser().getId());
        ProProjectinfo proj = proProjectinfoService.getProjectInfoByUserId(userId); 
        if(null == proj){
        	return renderError("无工程，新增失败。");
        } 
        String fileName = System.currentTimeMillis()+file.getOriginalFilename();  
        String filePath = fileRelativePath;
        UploadUtil uploadutil = new UploadUtil();  
        String messageInfo = uploadutil.uploadFile(request, file, fileName, filePath);
        if(StringUtils.isNotBlank(messageInfo)){
        	LOGGER.error("上传文件失败!");
        	this.log("", false, "上传文件失败!");
        	return renderError("上传文件失败!");
        }
        //跳转到新增页面
        Map<String ,String> map = new HashMap<String, String>();
        map.put("fileName", filePath+"/"+fileName);
        map.put("jcTime", jcTime);
        return renderSuccess(map);
    }  
    	
	/**
   	 * 新增页面再请求上传的文件，解析文件内容展示给前台，此时不存表，只在页面展示
   	*/
   @RequestMapping(value = "/add", method = RequestMethod.GET)
   public String addDatePage(String fileName,String jcTime,HttpServletRequest request,Model model) {
	   String userId = String.valueOf(getCurrentUser().getId());
	   ProProjectinfo proj = proProjectinfoService.getProjectInfoByUserId(userId); 
	   String path = request.getSession().getServletContext().getRealPath("upload");  
	   SysPropertieUtil configEntity = SysPropertieUtil.getInstince();
	   String upLoadPath = configEntity.getUploadPath();
	   String filePath = upLoadPath+fileName;
	   int index=fileName.lastIndexOf(".");
	   String str=fileName.substring(index);
	   List<DmcjJcInfoDetailsDto> details=new ArrayList<DmcjJcInfoDetailsDto>();
	   List<String> isno =new ArrayList<String>();
	   int success;
	   int failure;
	   if(".DAT".equals(str)){	   
       //从DAT文件中解析出点位编号和本次高程,并计算出变化量
       Map<String,Map<String,String>> list = ParseDATFile.getDetailFromFile(upLoadPath+"/"+fileName);
       //设置初始高程区间线路位置变化量等其他信息（文件里只有监测点编号和本次高程）
       details = dmcjJcInfoService.calcBhl(list,jcTime,proj.getId());
       //不存在的监测点
	   isno = dmcjJcInfoService.isno(list,jcTime,proj.getId());
	   }
	   else if(".xls".equals(str)){
		   Map<String, Map<String, String>> list = ParseXLSXFile.readXlsx(upLoadPath+"/"+fileName);
		   details = dmcjJcInfoService.calcBhl(list,jcTime,proj.getId());
		   //不存在的监测点
		   isno = dmcjJcInfoService.isno(list,jcTime,proj.getId());  
	   }
       //过滤掉不属于该用户所在项目的监测点
	   String pid = proj.getId();
	   List<JcPoint> points =  pointService.getJcPointsByPid(pid);
       //details中的点位编号如果不在points中，则从details中把该点删除
	   List<DmcjJcInfoDetailsDto> availdDetails = new ArrayList<DmcjJcInfoDetailsDto>();
	   for (DmcjJcInfoDetailsDto dto : details) {
			String jcdno = dto.getJcdNo();
		   for (JcPoint jcPoint : points) {
			   String  availdJcd = jcPoint.getJcdbh();
			   if(jcdno.trim().equals(availdJcd.trim())){
				   availdDetails.add(dto);
				   break;
			   }
		   }
	   }
	   //不是该工程的点位
	   List<DmcjJcInfoDetailsDto> absentDetails = new ArrayList<DmcjJcInfoDetailsDto>();
	   for(DmcjJcInfoDetailsDto dto : details){
		   absentDetails.add(dto);
	   }
	   absentDetails.removeAll(availdDetails);
       List<String> absent =new ArrayList<String>();
       for(DmcjJcInfoDetailsDto dto:absentDetails){
    	   absent.add(dto.getJcdNo());
       }
       failure=isno.size()+absentDetails.size();
       success=availdDetails.size();
//       model.addAttribute("fileUrl", request.getContextPath()+"/upload/"+fileName);  
	   //把解析出的内容发给前台新增页面用于显示明细部分
       model.addAttribute("success", success);
       model.addAttribute("failure", failure);
       model.addAttribute("list", JSON.toJSONString(availdDetails));
       model.addAttribute("isno", JSON.toJSONString(isno));
       model.addAttribute("absentDetails", JSON.toJSONString(absent));
       model.addAttribute("fileUrl", fileName); 
       model.addAttribute("jcTime", jcTime); 
       model.addAttribute("fielName",fileName);
       model.addAttribute("gcbh", pid);  
       if(StringUtils.isNotBlank(fileName)){
    	   if(fileName.lastIndexOf("/")>0){
    		   model.addAttribute("fielName",fileName.substring(fileName.lastIndexOf("/")+1,fileName.length()));
    	   }
       }
       return "dmcjjc/jcinfo/add";
   }
	
   /**
    * 查询和编辑的详情页面查询明细表格的方法
 * @param jcTime 
    */
   @RequestMapping(value = "/detailList", method = RequestMethod.POST)
   @ResponseBody
   public Object detailList(String fid, String jcTime,Integer page, Integer rows, String sort, String order) {
	   String userId = String.valueOf(getCurrentUser().getId());
	   ProProjectinfo proj = proProjectinfoService.getProjectInfoByUserId(userId); 
	   if(null == proj){
			return renderError("该监测点无项目信息，无法保存监测信息");
		}
	   //查询子表(以分页的方式查询)
	   PageInfo pageInfo = new PageInfo(0, 65530, "id", "desc");
//	   PageInfo pageInfo = new PageInfo(page, rows, sort, order);
	   Map<String, Object> condition = new HashMap<String, Object>();
	   condition.put("fid", fid);
	   pageInfo.setCondition(condition);
	   dmcjJcInfoService.calcBhl(fid,jcTime, pageInfo,proj.getId());
	   return pageInfo;
   }
   
	/**
   	 * 跳转到查看页面
   	 */
   @RequestMapping(value = "/showPage", method = RequestMethod.GET)
   public String showPage(String id,Model model) {
	   JcInfo cond = new JcInfo();
	   cond.setId(id);
	   //查询主表
	   cond = dmcjJcInfoService.selectOne(cond);
	   DmcjJcInfoDto dto = new DmcjJcInfoDto();
	   BeanUtils.copyProperties(cond, dto);
	   String filePath = cond.getImpFilePath();
	   if(StringUtils.isNotBlank(filePath)){
		   String fileName = filePath;
		   if(filePath.lastIndexOf("/")>0){
			    fileName = filePath.substring(filePath.lastIndexOf("/")+1,filePath.length());
    	   }
		   dto.setImpFileName(fileName);
	   }
	   model.addAttribute("jc", dto);
	   
       return "dmcjjc/jcinfo/show";
   }
	
	/**
 	 * 跳转到编辑页面
 	 */
	@RequestMapping(value = "/editPage", method = RequestMethod.GET)
	public String editPage(String id,Model model) {
		JcInfo cond = new JcInfo();
		cond.setId(id);
		// 查询主表
		cond = dmcjJcInfoService.selectOne(cond);
	   DmcjJcInfoDto dto = new DmcjJcInfoDto();
	   BeanUtils.copyProperties(cond, dto);
	   String filePath = cond.getImpFilePath();
	   if(StringUtils.isNotBlank(filePath)){
		   String fileName = filePath;
		   if(filePath.lastIndexOf("/")>0){
			    fileName = filePath.substring(filePath.lastIndexOf("/")+1,filePath.length());
    	   }
		   dto.setImpFileName(fileName);
	   }
		model.addAttribute("jc", dto);
		return "dmcjjc/jcinfo/edit";
	}
   
	/**
	 * 修改
	 */
	@RequestMapping(value = "/update",method = RequestMethod.POST)
	@ResponseBody
	public Object update(DmcjJcInfoDto dto) {
		try {
		   //先检查该监测时间是否已经上传过文件
	       Map<String, Object> cond = new HashMap<String,Object>();
	       cond.put("gcbh", dto.getGcbh());
	       cond.put("jctime", dto.getJcTime());
	       List<JcInfo> jcs = dmcjJcInfoService.selectByMap(cond);
	       if(jcs.size() > 1){
	           return renderError("相同时间的日常监测信息已存在，修改失败。");
	       }else if(jcs.size() == 1){
	    	   //等于1有可能是自己，要排除掉
	    	   if( ! jcs.get(0).getId().equals(dto.getId())){
	    		   return renderError("相同时间的日常监测信息已存在，修改失败。");
	    	   }
	       }
			
			dmcjJcInfoService.update(dto);
		} catch (Exception e) {
			this.log("编辑", false, "操作失败.id:"+dto.getId());
			LOGGER.error("DmcjJcInfoAction update失败", e);
			return renderError("修改操作：失败。");
		}
		this.log("编辑", true, "操作成功.id:"+dto.getId());
		return renderSuccess("修改操作：成功。");
	}
	
	/**
   	 * 跳转到新增页面
   	 */
   @RequestMapping(value = "/addPage", method = RequestMethod.GET)
   public String addPage() {
       return "dmcjjc/jcinfo/add";
   }
   
   /**
	 * 保存
	 */
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	@ResponseBody
	public Object save(DmcjJcInfoDto dto) {
		try {
			//检查相同监测时间下不能重复保存
	       JcInfo jc = new JcInfo();
	       jc.setGcbh(dto.getGcbh());
	       jc.setJcTime(dto.getJcTime());
	       int count = dmcjJcInfoService.selectCount(jc);
	       if(count > 0){
	           return renderError("相同时间的日常监测信息已存在，新增失败。");
	       }
			
	       //如果没有子表信息，也不能保存
	       String json = dto.getDetails();
			//子表信息
			if(null == json || "[]".equals(json) || "".equals(json.trim())){
				return renderError("无日常监测明细，新增失败");
			}
	       
			dmcjJcInfoService.save(dto);
		} catch (Exception e) {
			this.log("添加", false, "操作失败.");
			LOGGER.error("DmcjJcInfoAction save失败", e);
			return renderError("添加操作：失败。");
		}
		this.log("添加", true, "操作成功.");
		return renderSuccess("添加操作：成功。");
	}
	
	
	/**
	 * 保存
	 */
	@RequestMapping(value = "/updateAll", method = RequestMethod.POST)
	@ResponseBody
	public Object updateAll(DmcjJcInfoDto dto) {
		try {
		   //先检查该监测时间是否已经上传过文件
	       Map<String, Object> cond = new HashMap<String,Object>();
	       cond.put("gcbh", dto.getGcbh());
	       cond.put("jctime", dto.getJcTime());
	       List<JcInfo> jcs = dmcjJcInfoService.selectByMap(cond);
	       if(jcs.size() > 1){
	           return renderError("相同时间的日常监测信息已存在，修改失败。");
	       }else if(jcs.size() == 1){
	    	   //等于1有可能是自己，要排除掉
	    	   if( ! jcs.get(0).getId().equals(dto.getId())){
	    		   return renderError("相同时间的日常监测信息已存在，修改失败。");
	    	   }
	       }
			dmcjJcInfoService.updateAll(dto);
		} catch (Exception e) {
			this.log("添加", false, "操作失败.");
			LOGGER.error("DmcjJcInfoAction save失败", e);
			return renderError("添加操作：失败。");
		}
		this.log("添加", true, "操作成功.");
		return renderSuccess("添加操作：成功。");
	}
	
	
	/**
	 * 检查监测点是不是该线路下
	 */
	@RequestMapping(value = "/checkPointInLine", method = RequestMethod.POST)
	@ResponseBody
	public Object checkPointInLine(String jcdno,String xlbh) {
		try {
			JcPoint p = new JcPoint();
			p.setJcdbh(jcdno);
			p.setXlbh(xlbh);
			int count = pointService.selectCount(p);
			if(count <= 0){
				return renderError("保存失败，该线路下没有这个监测点点号");
			}
		} catch (Exception e) {
			LOGGER.error("DmcjJcInfoAction checkPointInLine失败", e);
			return renderError("检查失败");
		}
		return renderSuccess("检查成功");
	}
	
	/**
	 * 新增一条明细记录
	 */
	@RequestMapping(value = "/detailSave", method = RequestMethod.POST)
	@ResponseBody
	public Object detailSave(JcDetails detail) {
		try {
			String jcdno = detail.getJcdNo();
			String xlbh = detail.getXianlu();
			//先检查监测点是不是该线路下的
			JcPoint p = new JcPoint();
			p.setJcdbh(jcdno);
			p.setXlbh(xlbh);
			int count = pointService.selectCount(p);
			if(count <= 0){
				return renderError("保存失败，该线路下没有这个监测点点号");
			}
			//再检查日常监测中监测点点号是否有重复
			Map<String,Object> map = new HashMap<String,Object>();
			map.put("fid", detail.getFid());
			map.put("jcdno", jcdno);
			List<JcDetails> detailList = jcInfoDetailService.selectByMap(map);
			if(null != detailList && detailList.size() > 0){
				return renderError("保存失败，日常监测明细中已包含该监测点点号");
			}
			
			detail.setId(CommUtils.getUUID());
			dmcjJcInfoService.saveDetail(detail);
		} catch (Exception e) {
			LOGGER.error("DmcjJcInfoAction save失败", e);
			return renderError("保存明细操作：失败。");
		}
		return renderSuccess("保存明细操作：成功。");
	}
	
	/**
	 * 修改一条明细记录
	 */
	@RequestMapping(value = "/detailUpdate", method = RequestMethod.POST)
	@ResponseBody
	public Object detailUpdate(JcDetails detail) {
		try {
			String jcdno = detail.getJcdNo();
			String xlbh = detail.getXianlu();
			//先检查监测点是不是该线路下的
			JcPoint p = new JcPoint();
			p.setJcdbh(jcdno);
			p.setXlbh(xlbh);
			int count = pointService.selectCount(p);
			if(count <= 0){
				return renderError("保存失败，该线路下没有这个监测点点号");
			}
			//再检查日常监测中监测点点号是否有重复
			Map<String,Object> map = new HashMap<String,Object>();
			map.put("fid", detail.getFid());
			map.put("jcdno", jcdno);
			List<JcDetails> detailList = jcInfoDetailService.selectByMap(map);
			if(null != detailList && detailList.size() > 1){
				//大于1个肯定有重复
				return renderError("保存失败，日常监测明细中已包含该监测点点号");
			}
			if(null != detailList && detailList.size() == 1){
				//等于1的时候要排除自己
				if( ! detail.getId().equals(detailList.get(0).getId())){
					return renderError("保存失败，日常监测明细中已包含该监测点点号");
				}
			}
			jcInfoDetailService.updateById(detail);
		} catch (Exception e) {
			LOGGER.error("DmcjJcInfoAction updateSave失败", e);
			return renderError("保存明细操作：失败。");
		}
		return renderSuccess("保存明细操作：成功。");
	}
	
	/**
	 * 生成excel模板
	 */
    @RequestMapping("/excelTemplate")
    @ResponseBody
    public void ExcelTemplate(HttpServletResponse response) {
 		// 生成excel并下载
 		HSSFWorkbook wb = dmcjJcInfoService.generateExcelTemplate();
 		String filename = "rcjcmuban.xls";
 		String iso_filename = ExcelUtils.parseGBK(filename);
 		response.setContentType("text/plain");
 		response.setHeader("Content-Disposition","attachment;filename=" + iso_filename);
 		
 		OutputStream ouputStream = null; 
 		try {
 			ouputStream = response.getOutputStream();
 			wb.write(ouputStream);
 			ouputStream.flush();
 		} catch (IOException e) {
 			LOGGER.error("expJcPoint error",e);
 		} finally{
 			IOUtils.close(ouputStream);
 		}
 	}
}
