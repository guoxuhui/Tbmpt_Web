package com.crfeb.tbmpt.dmcjjc.info.controller;

import java.io.File;
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
import com.crfeb.tbmpt.commons.calc.CalcBhl;
import com.crfeb.tbmpt.commons.utils.CommUtils;
import com.crfeb.tbmpt.commons.utils.DateUtils;
import com.crfeb.tbmpt.commons.utils.ExcelUtils;
import com.crfeb.tbmpt.commons.utils.ExportDto;
import com.crfeb.tbmpt.commons.utils.ExportExcelUtil;
import com.crfeb.tbmpt.commons.utils.ExportSetUtil;
import com.crfeb.tbmpt.commons.utils.PageInfo;
import com.crfeb.tbmpt.commons.utils.ParseExcelFile;
import com.crfeb.tbmpt.commons.utils.StringUtils;
import com.crfeb.tbmpt.commons.utils.SysPropertieUtil;
import com.crfeb.tbmpt.dmcjjc.info.model.JcPoint;
import com.crfeb.tbmpt.dmcjjc.info.model.vo.DmcjJcInfoDetailsDto;
import com.crfeb.tbmpt.dmcjjc.info.model.vo.DmcjJcPointDto;
import com.crfeb.tbmpt.dmcjjc.info.service.IDmcjJcInfoService;
import com.crfeb.tbmpt.dmcjjc.info.service.IDmcjJcPointService;
import com.crfeb.tbmpt.project.model.ProProjectinfo;
import com.crfeb.tbmpt.project.service.IProProjectinfoService;

/**
 * 4.1.2监测点管理
 * @author Administrator
 *
 */
@Controller
@RequestMapping("/dmcjjc/dmcjjcpoint")
public class DmcjJcPointAction extends BaseController {
	private static final Logger LOGGER = LogManager
			.getLogger(DmcjJcPointAction.class);
	
    @Autowired
    private IDmcjJcPointService dmcjJcPointService;
    @Autowired
    private IDmcjJcInfoService dmcjJcInfoService;
	@Autowired
	private IProProjectinfoService projService;
    @Autowired
    private IProProjectinfoService proProjectinfoService;

    /**
     * 跳转到列表页面
     */
    @RequestMapping(value = "", method = RequestMethod.GET)
    public String manager() {
        return "dmcjjc/jcpoint/list";
    }
    
    
    
    //-------wpg:导入页面 开始------------------------------------------------------------- 
     
     
     
     /**
      * @author wpg
    	 * 跳转到   导入Excel页面
    	 */
    @RequestMapping(value = "/importPage", method = RequestMethod.GET)
    public String importPage() {
        return "dmcjjc/jcpoint/import";
    }
     
   
    /***
     * @author wpg
     * 2016-12-13
     * 
     * 作用：生成 Excel模版 .xls文件
     * @param response
     */
    @RequestMapping("/excelTemplate")
    @ResponseBody
    public void ExcelTemplate(HttpServletResponse response) {
 		// 生成excel并下载
 		HSSFWorkbook wb = dmcjJcPointService.generateExcelTemplate();
 		String filename = "监测点模版.xls";
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
     * @author wpg
     * 2016-12-14
     *  
     * 上传文件
     * 
     * 调用：this.ObtainFileData(fileName, gcbh, path) 解析文件内容 并 把信息存表
     * 
     * MultipartFile file  文件上传对象
     * 
     * @RequestParam(value = "file", required = false) 
     * Spring MVC  required=false表示不传的话，会给参数赋值为null，required=true就是必须要有
     */
    //后续路径
    private String fileRelativePath = "/upload/dmcjjc/JcPointFile/"+DateUtils.format(new Date(), "yyyyMMdd");//文件上传相对路径
 	@RequestMapping("/upload")  
 	@ResponseBody
 	public Object upload(String gcbh,@RequestParam(value = "file", required = false) MultipartFile file, HttpServletRequest request, Model model) {  
 		
 		 //当前项目  是否为空
         if(null == gcbh){
         	return renderError("无工程，新增失败。");
         }

         //----------wpg 新添加----------------------------
         //获取   后续路径
         SysPropertieUtil configEntity = SysPropertieUtil.getInstince();
         String upLoadPath = configEntity.getUploadPath();
         //文件路径=前续路径 (来自配置文件) +  后续
         String path = upLoadPath + fileRelativePath;
         //filePath = upLoadPath + filePath;
         
         
         //获取路径
         //应该用新地址  找文件夹   “jcPointUpload”
         //String path = request.getSession().getServletContext().getRealPath("jcPointUpload");  
         File uploadDir = new File(path);
         //即判断你指定的路径或着指定的目录文件是否已经存在。
         if(!uploadDir.exists()){  
         	uploadDir.mkdirs();  
         }  
         //用 “系统时间” 和 “文件名称” 组成导入 文件的名称，避免名称重复；
         String fileName = System.currentTimeMillis()+file.getOriginalFilename();  
         //用日志 输出 “上传文件路径”
         LOGGER.info("upload file path="+path);
         File targetFile = new File(path, fileName);  
         //保存文件到服务器  
         String ring =null;
         try {  
             file.transferTo(targetFile);  
             //获取 文件 的 信息 并 保存 ：文件名，项目ID ，文件路径
             ring = this.ObtainFileData(fileName, gcbh, path);
             if(ring ==null || ring==""){
     		    return renderError("新增操作：失败。");
     	   }
         } catch (Exception e) {  
         	LOGGER.error("upload error:",e);
         	if(ring ==null || ring==""){
     		    return renderError("新增操作：失败。");
     	   }
         	
         }
         return renderSuccess( ring);
 		
       
     }  
 	
 	
 	/**
	 * @author wpg 
	 * 2016-12-14
	 * 作用：新增页面再请求上传的文件，解析文件内容 并 把信息存表
 	 * @return 
	 */
    public String ObtainFileData(String fileName,String gcbh,String path) {
 	   String ring =null;
 	   try {
     	   //
     	   List<List<Map<String,String>>> list = ParseExcelFile.readExcelWithTitle(path+"/"+fileName);
     	   //把 Excel表格数据集合 转成 监测点 对象  并  保存
     	   ring = dmcjJcPointService.transformationBingPreservation(list,gcbh);
 		} catch (Exception e) {
 			LOGGER.error(" 失败", e);
 		}
 	  
 		return ring;
 	  
    }
    
 	
 	//----------- 结束 -----------------------------------------------------------------
    
    /**
     * 跳转到新增页面
     */
    @RequestMapping(value = "/addPage", method = RequestMethod.GET)
    public String addPage() {
        return "dmcjjc/jcpoint/add";
    }
    
    /**
     * 跳转到编辑页面 
     */
    @RequestMapping("/editPage")
    public String editPage(Model model, String id) {
    	JcPoint info = new JcPoint();
    	info.setId(id);
        info = dmcjJcPointService.selectOne(info);
        model.addAttribute("vo", info);
        
        /**
         * 检测监测点是否已经被使用
         * 
         * 如果使用则设置主要字段无法修改
         * 否则全部字段可以修改
         */
		//检查日常监测管理子表中已有该监测点信息，则无法修改，并提示“该监测点存在日常监测信息，操作失败。”
		int count = dmcjJcInfoService.selectDetailsByJcd(info.getJcdbh());
		if(count > 0){
			model.addAttribute("ifsy", true);
		}else{
			model.addAttribute("ifsy", false);
		}
        
        return "dmcjjc/jcpoint/edit";
    }
    
    /**
     * 列表页面分页查询
     */
    @RequestMapping(value = "/list", method = RequestMethod.POST)
	@ResponseBody
	public Object list(DmcjJcPointDto dto,Integer page, Integer rows, String sort, String order) {
		PageInfo pageInfo = new PageInfo(page, rows, sort, order);
		Map<String, Object> condition = new HashMap<String, Object>();
		//设置查询条件
		if(null != dto.getGcbh()){
			condition.put("gcbh", dto.getGcbh());
		}
		if(null != dto.getJcdbh()){
			condition.put("jcdbh", dto.getJcdbh());
		}
		if(null != dto.getQjbh()){
			condition.put("qjbh", dto.getQjbh());
		}
		if(null != dto.getXlbh()){
			condition.put("xlbh", dto.getXlbh());
		}
		if(null != dto.getSjType()){
			condition.put("sjType", dto.getSjType());
		}
		if(null != dto.getSjTimeType()){
			condition.put("sjTimeType", dto.getSjTimeType());
		}
		
		//根据用户id查出用户有哪些项目
		String userId = String.valueOf(getCurrentUser().getId());
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
		dmcjJcPointService.selectDataGrid(pageInfo);
		return pageInfo;
	}
    
    /**
   	 * 跳转到查看页面
   	 */
   @RequestMapping(value = "/showPage", method = RequestMethod.GET)
   public String showPage(String id,Model model) {
	   JcPoint cond = new JcPoint();
	   cond.setId(id);
	   //查询主表
	   cond = dmcjJcPointService.selectOne(cond);
	   model.addAttribute("jc", cond);
       return "dmcjjc/jcpoint/show";
   }
    
    /**
	 * 保存
	 */
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	@ResponseBody
	public Object save(JcPoint dmcjDDInfo) {
		try {
			JcPoint info = new JcPoint();
			info.setGcbh(dmcjDDInfo.getGcbh());
			info.setJcdbh(dmcjDDInfo.getJcdbh());
			if ( 0 == dmcjJcPointService.selectCount(info )){
				dmcjDDInfo.setId(CommUtils.getUUID());
				dmcjJcPointService.insert(dmcjDDInfo);
			}else{
				
				return renderError("监测点点号已存在，新增失败。");
			}
		} catch (Exception e) {
			this.log("新增", false, "操作失败.");
			LOGGER.error("DmcjDDInfoAction save失败", e);
			return renderError("新增操作：失败。");
		}
		this.log("新增", true, "操作成功.");
		return renderSuccess("新增操作：成功。");
	}
    
	/**
	 * 修改
	 */
	@RequestMapping(value = "/update",method = RequestMethod.POST)
	@ResponseBody
	public Object update(JcPoint dmcjDDInfo) {
		try {
			//修改时要检查同一个分类名称下基础数据名称不能重复
			JcPoint info = new JcPoint();
			info.setGcbh(dmcjDDInfo.getGcbh());
			info.setJcdbh(dmcjDDInfo.getJcdbh());
			int exist = dmcjJcPointService.selectCount(info);
			if ( exist > 1){
				return renderError("监测点点号已存在，修改失败。");
			}
			if(exist == 1){
				info = dmcjJcPointService.selectOne(info);
				//等于1有可能是自己，所以要用主键id判断一下
				if( ! info.getId().equals(dmcjDDInfo.getId())){
					return renderError("监测点点号已存在，修改失败。");
				}
			}
			
			//检查日常监测管理子表中已有该监测点信息，则无法修改，并提示“该监测点存在日常监测信息，操作失败。”
			/*
			int count = dmcjJcInfoService.selectDetailsByJcd(dmcjDDInfo.getJcdbh());
			if(count > 0){
				return renderError("该监测点存在日常监测信息，操作失败。");
			}
			*/
			dmcjJcPointService.updateById(dmcjDDInfo);
		} catch (Exception e) {
			this.log("编辑", false, "操作失败.");
			LOGGER.error("DmcjDDInfoAction update失败", e);
			return renderError("修改操作：失败。");
		}
		this.log("编辑", true, "操作成功.");
		return renderSuccess("修改操作：成功。");
	}
	
    /**
	 * 删除
	 */
	@RequestMapping(value = "/del",method = RequestMethod.POST)
	@ResponseBody
	public Object del(String[] ids) {
		try {
			List<String> list = Arrays.asList(ids);
			List<JcPoint> delList = new ArrayList<JcPoint>();
			String tempIds ="";
			//检查日常监测管理子表中已有该监测点信息，则无法修改，并提示“该监测点存在日常监测信息，操作失败。”
			for (String id : list) {
				JcPoint  point = new JcPoint();
				point.setId(id);
				point = dmcjJcPointService.selectOne(point);
				int count = dmcjJcInfoService.selectDetailsByJcd(point.getJcdbh());
				if(count > 0){
					return renderSuccess("已有点位存在于日常监测信息中，操作失败。");
				}
				delList.add(point);
				tempIds +=","+id;
			}
			for (JcPoint jcPoint : delList) {
				dmcjJcPointService.deleteSelective(jcPoint);
			}
		} catch (Exception e) {
			this.log("删除", false, "操作失败");
			LOGGER.error("DmcjJcPointAction del失败", e);
			return renderError("删除操作：失败。");
		}
		this.log("删除", true, "操作成功");
		return renderSuccess("删除操作：成功。");
	}
	
	/**
	 * 禁用
	 */
	@RequestMapping(value = "/jy",method = RequestMethod.POST)
	@ResponseBody
	public Object jy(String id) {
		try {
			dmcjJcPointService.jy(id);
		} catch (Exception e) {
			this.log("禁用", false, "操作失败");
			LOGGER.error("DmcjJcPointAction jy失败", e);
			return renderError("禁用操作：失败。");
		}
		this.log("禁用", true, "操作成功");
		return renderSuccess("禁用操作：成功。");
	}

	/**
	 * 启用
	 */
	@RequestMapping(value = "/qy",method = RequestMethod.POST)
	@ResponseBody
	public Object qy(String id) {
		try {
			dmcjJcPointService.qy(id);
		} catch (Exception e) {
			this.log("启用", false, "操作失败");
			LOGGER.error("DmcjJcPointAction qy失败", e);
			return renderError("启用操作：失败。");
		}
		this.log("启用", true, "操作成功");
		return renderSuccess("启用操作：成功。");
	}
	
	/**
	 * 导出选中数据到excel
	 */
	@RequestMapping("/expJcPoint")
	@ResponseBody
	public void expJcPoint(String[] ids, HttpServletResponse response) {
		// 生成excel并下载
		HSSFWorkbook wb = dmcjJcPointService.expData(Arrays.asList(ids));
		String filename = "监测点数据.xls";
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
     * 数据导出
     * @param exportDto
     * @param Dto
     * @param response
     */
	@SuppressWarnings({"unchecked"})
	@RequestMapping(value="/expXls")
	public void expXls(ExportDto exportDto,DmcjJcPointDto dto, HttpServletResponse response){
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
				dmcjJcPointService.selectDataGrid(pageInfo);
				ExportExcelUtil.writeExcelToClient(response, exportDto, ExportSetUtil.objectToListMap(pageInfo.getRows()));
			}else{
				//设置查询条件
				if(null != dto.getGcbh()){
					condition.put("gcbh", dto.getGcbh());
				}
				if(null != dto.getJcdbh()){
					condition.put("jcdbh", dto.getJcdbh());
				}
				if(null != dto.getQjbh()){
					condition.put("qjbh", dto.getQjbh());
				}
				if(null != dto.getXlbh()){
					condition.put("xlbh", dto.getXlbh());
				}
				if(null != dto.getSjType()){
					condition.put("sjType", dto.getSjType());
				}
				if(null != dto.getSjTimeType()){
					condition.put("sjTimeType", dto.getSjTimeType());
				}
				
				//根据用户id查出用户有哪些项目
				String userId = String.valueOf(getCurrentUser().getId());
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
				dmcjJcPointService.selectDataGrid(pageInfo);
				ExportExcelUtil.writeExcelToClient(response, exportDto, ExportSetUtil.objectToListMap(pageInfo.getRows()));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//在编辑监测明细信息时，根据监测点编号取监测点详细信息，包括线路名称，区间名称，上次高程和计算变化量
	@RequestMapping(value = "/jcdInfo",method = RequestMethod.POST)
	@ResponseBody
	public Object jcdInfo(String jcdno, String jcTime, Float bcgc,String flag) {
		String userId = String.valueOf(getCurrentUser().getId());
		ProProjectinfo proj = projService.getProjectInfoByUserId(userId); 
		//如果调用接口“获取当前登录用户所在的项目信息"返回为空，则不能获取上次高程，
		//并且无上次高程时，就无法进行保存日常监测信息、报告信息以及上报报告。
		if(null == proj){
			return renderError("该监测点无项目信息，无法保存监测信息");
		}
		DmcjJcInfoDetailsDto dto = new DmcjJcInfoDetailsDto();
		try {
			//构造查询条件
			List<Map<String,String>> list2 = new ArrayList<Map<String,String>>();
			Map<String,String> m1 = new HashMap<String,String>();
			m1.put("jcd", jcdno);
			list2.add(m1);
			List<DmcjJcPointDto> list = dmcjJcPointService.selectJcPointsByJcdno(list2);
			if(null != list && list.size() > 0){
				DmcjJcPointDto point = list.get(0);
				//计算变化量
				Float scgc = 0f;
				if("info".equals(flag)){
					//取日常监测中的上次高程
					scgc = dmcjJcPointService.getJcInfoScgc(jcdno, jcTime,proj.getId());
				}else if("bg".equals(flag)){
					//取监测报告中的上次高程
					scgc = dmcjJcPointService.getJcBgScgc(jcdno, jcTime,proj.getId());
				}
				
				if(null == scgc)
					scgc = point.getCsgc();
				point.setScgc(scgc);
				
				dto.setJcdNo(jcdno);//从编辑页面传来
				dto.setScgc(scgc);
				dto.setBcgc(bcgc);//从编辑页面传来
				dto.setCsgc(point.getCsgc());
				dto.setQujian(point.getQjbh());
				dto.setXianlu(point.getXlbh());
				dto.setLicheng(point.getLicheng());
				dto.setWeizhi(point.getWeizhi());
				dto.setQujianName(point.getQujianName());
				dto.setXianluName(point.getXianluName());
//				dto.setId(CommUtils.getUUID());主键不需要，此时只是页面临时展示
				
				CalcBhl.calc(dto);//计算变化量
			}
		} catch (Exception e) {
			LOGGER.error("DmcjJcPointAction jcdInfo失败", e);
		}
		return renderSuccess(dto) ;
	}
}
