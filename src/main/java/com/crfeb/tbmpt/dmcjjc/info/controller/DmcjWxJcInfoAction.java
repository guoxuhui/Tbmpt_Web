package com.crfeb.tbmpt.dmcjjc.info.controller;


import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Arrays;
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
import com.crfeb.tbmpt.commons.utils.ExportSetUtil;
import com.crfeb.tbmpt.commons.utils.PageInfo;
import com.crfeb.tbmpt.commons.utils.ParseDATFile;
import com.crfeb.tbmpt.commons.utils.ParseXLSXFile;
import com.crfeb.tbmpt.commons.utils.StringUtils;
import com.crfeb.tbmpt.commons.utils.SysPropertieUtil;
import com.crfeb.tbmpt.commons.utils.UploadUtil;
import com.crfeb.tbmpt.dmcjjc.bg.model.JcbgInfo;
import com.crfeb.tbmpt.dmcjjc.bg.model.JcbgsbInfo;
import com.crfeb.tbmpt.dmcjjc.bg.service.IDmcjJcbgInfoService;
import com.crfeb.tbmpt.dmcjjc.bg.service.IDmcjJcbgsbInfoService;
import com.crfeb.tbmpt.dmcjjc.info.model.JcInfo;
import com.crfeb.tbmpt.dmcjjc.info.model.JcPoint;
import com.crfeb.tbmpt.dmcjjc.info.model.vo.DmcjJcInfoDetailsDto;
import com.crfeb.tbmpt.dmcjjc.info.model.vo.DmcjJcInfoDto;
import com.crfeb.tbmpt.dmcjjc.info.service.IDmcjJcPointService;
import com.crfeb.tbmpt.dmcjjc.info.service.IDmcjWxJcInfoService;
import com.crfeb.tbmpt.project.model.ProProjectinfo;
import com.crfeb.tbmpt.project.service.IProProjectinfoService;

/**
 * <p>无锡日常监测管理模块Controlle</p>
 * <p>系统：地面沉降监测管理系统</p>
 * <p>模块：基础模块</p>
 * <p>日期：2017-02-15</p>
 * @version 1.0
 * @author wl_wpg
 */
@Controller
@RequestMapping("/dmcjjc/dmcjwxjcinfo")
public class DmcjWxJcInfoAction extends BaseController {
	private static final Logger LOGGER = LogManager
			.getLogger(DmcjWxJcInfoAction.class);
	
	
	@Autowired
    private IDmcjWxJcInfoService dmcjWxJcInfoService;
	

    @Autowired
    private IProProjectinfoService proProjectinfoService;
    @Autowired
    private IDmcjJcPointService pointService;
   
    //监测报告Service对象
  	@Autowired
    private IDmcjJcbgInfoService bgInfoService;
  	 
  	
  	//监测报告上报Service对象
  	@Autowired
    private IDmcjJcbgsbInfoService jcbgsbInfoService; 
  	 
	
	 private String fileRelativePath = "/upload/dmcjjc/file/"+DateUtils.format(new Date(), "yyyyMMdd");//文件上传相对路径

    /**
     * 跳转到列表页面
     */
    @RequestMapping(value = "", method = RequestMethod.GET)
    public String manager() {
        return "dmcjjc/wxjcinfo/list";
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
		dmcjWxJcInfoService.selectDataGrid(pageInfo);
		return pageInfo;
	}
    
   
	
   /**
   	* 跳转到导入页面
   	*/
   @RequestMapping(value = "/importPage", method = RequestMethod.GET)
   public String importPage() {
       return "dmcjjc/wxjcinfo/import";
   }
	
   /**
	 * 生成 上传  （日常监测明细）excel模板 
	 */
   @RequestMapping("/excelTemplate")
   @ResponseBody
   public void ExcelTemplate(HttpServletResponse response) {
		// 生成excel并下载
		HSSFWorkbook wb = dmcjWxJcInfoService.generateExcelTemplate();
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
    
   /**
	 * 方法说明：上传文件 （日常监测明细）
	 * @param gcbh 项目Id
	 * @param jcTime 监测时间
	 * @param tianqi 天气
	 * @param remarks 备注
	 * @param file 导入文件（日常监测明细）
	 * @author: wl_wpg
	 * @Time: 2017年02月15日 上午11:09:17
	 */
	@RequestMapping("/upload")  
	@ResponseBody
	public Object upload(String gcbh,String jcTime,String tianqi,Float baseNumber,String remarks,@RequestParam(value = "file", required = false) MultipartFile file, HttpServletRequest request, Model model) {  
		String ring ="";
		String userId = String.valueOf(getCurrentUser().getId());
        ProProjectinfo proj = proProjectinfoService.getProjectInfoByUserId(userId); 
        if(null == proj){
        	return renderError("无工程，新增失败。");
        } 
        //根据当前系统时间创建上传的文件名称；
        String fileName = System.currentTimeMillis()+file.getOriginalFilename();  
        /**
         * fileRelativePath：全局变量，文件上传相对路径；（"/upload/dmcjjc/file/"+DateUtils.format(new Date(), "yyyyMMdd");）；
         *
         */
        String filePath = fileRelativePath;
        UploadUtil uploadutil = new UploadUtil(); 
        /**
         * 上传文件
         * 返回空则上传文件成功，否则返回错误信息
         */
        String messageInfo = uploadutil.uploadFile(request, file, fileName, filePath);
        //非空判断；
        if(StringUtils.isNotBlank(messageInfo)){
        	LOGGER.error("上传文件失败!");
        	this.log("", false, "上传文件失败!");
        	return renderError("上传文件失败!");
        }
        //文件路径 + 上传后的 文件名；
        String fileUrlName = filePath+"/"+fileName;
        try { 
        	/***
        	 * gcbh:项目Id
        	 * fileUrlName:文件路径 + 上传后的 文件名；
        	 * jcTime:监测时间；
        	 * tianqi:监测时的天气；
        	 */
        	ring = addDatePage(gcbh,fileUrlName,jcTime,tianqi, baseNumber, remarks, request,model);
            
        } catch (Exception e) {  
        	LOGGER.error("upload error:",e); 
    		return renderError("新增操作：失败。");
        }
        return renderSuccess(ring);
         
    }  
	
 
	 
	/**
	 * 方法说明： 解析文件内容 ， 调用方法  存表，
	 * @param gcbh 项目Id
	 * @param fileUrlName 上传文件保存路径
	 * @param jcTime 监测时间
	 * @param tianqi 天气
	 * @param remarks 备注 
	 * @author: wl_wpg
	 * @Time: 2017年02月15日  
	 */
    public String addDatePage(String gcbh,String fileUrlName,String jcTime,String tianqi,Float baseNumber,String remarks,HttpServletRequest request,Model model) {
    	//声明：返回提示语句字符串；
        String ring ="";
        String operation ="";
    	try {
    	   //String path = request.getSession().getServletContext().getRealPath("upload");  
    	   SysPropertieUtil configEntity = SysPropertieUtil.getInstince();
    	   //服务器 路径
    	   String upLoadPath = configEntity.getUploadPath();
    	   //String filePath = upLoadPath+fileUrlName;
    	   int index=fileUrlName.lastIndexOf(".");
    	   //获取 文件 扩展名；
    	   String str=fileUrlName.substring(index);
    	   List<DmcjJcInfoDetailsDto> details=new ArrayList<DmcjJcInfoDetailsDto>();
    	   //不存在的监测点
    	   List<String> isno =new ArrayList<String>(); 
           //不是该工程的点位的条数；
    	   int success;
    	   //不是该工程的‘监测点’+‘条数’；
    	   int failure;
    	   if(".DAT".equals(str)){	 
    		   
           //从DAT文件中 解析 出点位编号和本次高程, 并 添加  ‘监测点’前缀
    	   Map<String,Map<String,String>> list = dmcjWxJcInfoService.bjJcdbh(ParseDATFile.getDetailFromFile(upLoadPath+"/"+fileUrlName), baseNumber);
            
           //设置初始高程区间线路位置变化量等其他信息（文件里只有监测点编号和本次高程）
           //通过计算   得出完整  ‘日常监测信息’，返回 完整的 ‘日常监测信息’集合
           details = dmcjWxJcInfoService.addCalcBhl(list,jcTime,gcbh);
           //不存在的监测点集合
    	   isno = dmcjWxJcInfoService.isno(list,jcTime,gcbh);
    	   }
    	   else if(".xls".equals(str)){
    		   //解析 出点位编号和本次高程, 并 添加  ‘监测点’前缀
    		   Map<String,Map<String,String>> list = dmcjWxJcInfoService.bjJcdbh(ParseXLSXFile.readXlsx(upLoadPath+"/"+fileUrlName),  baseNumber );
    		   details = dmcjWxJcInfoService.addCalcBhl(list,jcTime,gcbh);
    		   //不存在的监测点 集合
    		   isno = dmcjWxJcInfoService.isno(list,jcTime,gcbh);  
    	   }
    	   if(details.isEmpty() ){
    		   return ring ="无对应监测点 或 无日常监测明细，保存失败！";
    	   }
           //过滤掉不属于该用户所在项目的监测点
    	   //获取 当前用户 项目；
    	   List<JcPoint> points =  pointService.getJcPointsByPid(gcbh);
           //details中的点位编号如果不在points中，则从details中把该点删除
    	   List<DmcjJcInfoDetailsDto> availdDetails = new ArrayList<DmcjJcInfoDetailsDto>();
    	   for (DmcjJcInfoDetailsDto dto : details) {
    		    // 监测点 编号
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
          
           //解析出的正确数据集合 不能为空
           if(availdDetails.isEmpty()){
   			  return ring ="无日常监测明细，新增失败";
   		   }
           //保存 (日常监测，监测报告，监测报告上报) 操作
            operation = save(availdDetails,gcbh,fileUrlName,jcTime,tianqi,remarks);
           /***
            * 编辑‘结果提示语句’
            */
          
           //不是该工程的‘监测点’+‘条数’；
           if(operation=="" && success>0 ){
        	   ring ="导入成功的记录数:"+success; 
           }
           if(failure>0){
        	   ring =ring+"<br/>导入失败的记录数:"+failure;
        	   if(!isno.isEmpty()){
            	   ring =ring+"<br/>不存在的监测点:<br/>"+isno;
               }
               if(!absentDetails.isEmpty()){
            	   ring =ring+"<br/>不是该项目的监测点:<br/>"+absentDetails;
               }
           }
	     } catch (Exception e) {
		    this.log("添加", false, "操作失败.");
			LOGGER.error("dmcjWxJcInfoService.save 失败", e);
			return ring ="添加操作：报错。";
		 }
    	if(operation!=""){ 
 		   return operation;
 	   }
    	this.log("添加", true, "操作成功!");
        return ring;
   }
	
    
     
    /**
	 * 方法说明：保存  解析文件内容  
	 * @param list 日常监测明细 集合
	 * @param gcbh 项目Id
	 * @param fileUrlName 上传文件保存路径
	 * @param jcTime 监测时间
	 * @param tianqi 天气
	 * @param remarks 备注 
	 * @author: wl_wpg
	 * @Time: 2017年02月15日  
	 */                                                
	public String save(List<DmcjJcInfoDetailsDto> list,String gcbh,String fileUrlName,String jcTime,String tianqi,String remarks) {
		String ring = "";
		try {
			//如果没有子表信息，也不能保存 
			//子表信息 
			if(null == list || "".equals(list) || list.isEmpty()){
				return ring ="无日常监测明细，新增失败";
			}
			//检查相同监测时间下不能重复保存
	       JcInfo jc = new JcInfo();
	       jc.setGcbh(gcbh);
	       jc.setJcTime(jcTime);
	       
	       //通过‘工程编号’和 ‘监测时间’ 查询 是否有同样‘监测时间’ 
	       int count = dmcjWxJcInfoService.selectCount(jc);
	       if(count > 0){
	           return ring ="相同时间的日常监测信息已存在，新增失败。";
	       }
			
			dmcjWxJcInfoService.save(gcbh,list,fileUrlName,jcTime,tianqi,remarks);
			
		} catch (Exception e) {
			this.log("添加", false, "操作失败.");
			LOGGER.error("dmcjWxJcInfoService.save 失败", e);
			return ring ="导入操作报错，导入失败！";
		}
		this.log("添加", true, "操作成功.");
		//返回 为空 为‘提交成功’；
		return ring;
	}
	
	/**
   	 * 跳转到查看页面
   	 */
   @RequestMapping(value = "/showPage", method = RequestMethod.GET)
   public String showPage(String id,Model model) {
	   JcInfo cond = new JcInfo();
	   cond.setId(id);
	   //查询主表
	   cond = dmcjWxJcInfoService.selectOne(cond);
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
	   
       return "dmcjjc/wxjcinfo/show";
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
	   dmcjWxJcInfoService.calcBhl(fid,jcTime, pageInfo,proj.getId());
	   return pageInfo;
   }
   
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
			dmcjWxJcInfoService.calcBhl(fid,jcTime, pageInfo,projectId);
			ExportExcelUtil.writeExcelToClient(response, exportDto, ExportSetUtil.objectToListMap(pageInfo.getRows()));
		} catch (Exception e) {
			e.printStackTrace();
		}
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
					//先 查询 主表 信息
					JcInfo jsIno = dmcjWxJcInfoService.selectOne(cond);
					//jsIno.setIfqr("未确认");
					 if(null != jsIno && StringUtils.isNotBlank(jsIno.getGcbh())){
						//监测报告 主表信息
							JcbgInfo jcbgCond = new JcbgInfo(); 
							/** 工程编号 */
							jcbgCond.setGcbh(jsIno.getGcbh());
							/** 本次检测时间 */
							jcbgCond.setJcTime(jsIno.getJcTime());
							
							JcbgInfo jcbgInfo = bgInfoService.selectOne(jcbgCond);
						    //jcbgInfo.setIfqr("未确认"); 
							//jcbgInfo.setIfsb("未上报"); 
							//JcbgDetail jcbgDetail = new JcbgDetail();
							
							//监测报告上报  主表信息
							JcbgsbInfo jcbgsbCond = new JcbgsbInfo(); 
							/** 工程编号 */
							jcbgsbCond.setGcbh(jsIno.getGcbh());
							/** 本次检测时间 */
							jcbgsbCond.setJcTime(jsIno.getJcTime());
							
							JcbgsbInfo jcBgSbInfo =  jcbgsbInfoService.selectOne(jcbgsbCond);
							//jcBgSbInfo.setIfqr("未确认"); 
							 
							/**
							 * 删除  ‘监测报告上报’
							 */
							//删子表
							jcbgsbInfoService.deleteDetails(jcBgSbInfo.getId());
							//删主表
							jcbgsbInfoService.deleteSelective(jcBgSbInfo);
							/**
							 * 删除  ‘监测报告’ 
							 */
							//删子表  
							bgInfoService.deleteDetails(jcbgInfo.getId());
							//删主表
							bgInfoService.deleteSelective(jcbgInfo);
							/**
							 * 删除 ‘日常监测’ 
							 */
							//删子表
							dmcjWxJcInfoService.deleteDetails(id);
							//删主表
							dmcjWxJcInfoService.deleteSelective(cond);
					 }
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
		 * 根据主键批量删除子表记录
		 */
		@RequestMapping(value = "/deleteDetails",method = RequestMethod.POST)
		@ResponseBody
		public Object deleteDetails(String[] ids) {
			try {
				dmcjWxJcInfoService.deleteDetailsByIds(Arrays.asList(ids));
			} catch (Exception e) {
				LOGGER.error("DmcjJcInfoAction deleteDetails失败", e);
				return renderError("删除操作：失败。");
			}
			return renderSuccess("删除操作：成功。");
		}
		
		
	
}
