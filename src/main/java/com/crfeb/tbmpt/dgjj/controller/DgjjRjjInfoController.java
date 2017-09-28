package com.crfeb.tbmpt.dgjj.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays; 
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.crfeb.tbmpt.commons.base.BaseController;
import com.crfeb.tbmpt.commons.result.Result;
import com.crfeb.tbmpt.commons.utils.BeanUtils;
import com.crfeb.tbmpt.commons.utils.ExportDto;
import com.crfeb.tbmpt.commons.utils.ExportExcelUtil;
import com.crfeb.tbmpt.commons.utils.ExportPdfUtil;
import com.crfeb.tbmpt.commons.utils.ExportSetUtil;
import com.crfeb.tbmpt.commons.utils.PageInfo;
import com.crfeb.tbmpt.commons.utils.StringUtils;
import com.crfeb.tbmpt.commons.utils.UploadUtil;
import com.crfeb.tbmpt.dgjj.model.DgjjBzgl;
import com.crfeb.tbmpt.dgjj.model.DgjjRjjInfo;
import com.crfeb.tbmpt.dgjj.model.dto.DgjjRjjInfoDto;
import com.crfeb.tbmpt.dgjj.model.dto.DgjjRjjInfoParentDto;
import com.crfeb.tbmpt.dgjj.service.DgjjBzglService;
import com.crfeb.tbmpt.dgjj.service.DgjjRjjInfoService;
import com.crfeb.tbmpt.project.model.ProRSectionLine;
import com.crfeb.tbmpt.project.service.IProRSectionLineService;
import com.crfeb.tbmpt.tbmmg.model.ProTbmUseInfo;
import com.crfeb.tbmpt.tbmmg.model.ProTbminfo;
import com.crfeb.tbmpt.tbmmg.service.IProTbmUseInfoService;
import com.crfeb.tbmpt.tbmmg.service.IProTbminfoService;  

/**
 * <p>日掘进信息管理Controlle</p>
 * <p>系统：盾构掘进参数管理系统</p>
 * <p>模块：基础模块</p>
 * <p>日期：2017-01-09</p>
 * @version 1.0
 * @author wpg
 */
@Controller     //DgjjRjjInfo
@RequestMapping(value="/dgjj/rjj/dgjjRjjInfo")
public class DgjjRjjInfoController extends BaseController{

	
	@Autowired
    private DgjjRjjInfoService dgjjRjjInfoService; 
	
	//盾构机使用记录Service
	@Autowired
	private IProTbmUseInfoService proTbmUseInfoService;
	//班组service
	@Autowired
	private DgjjBzglService dgjjBzglService;
	//线路service
	@Autowired
	private IProRSectionLineService lineService;
	//盾构机信息service
	@Autowired
	private IProTbminfoService tbmInfoService;
	
	
      /***
       * 跳到 日掘进信息管理“线路信息”页面
       * @return
       */
	  @RequestMapping(value = "", method = RequestMethod.GET)
	  public String manager() {
	      return "dgjj/rjj/dgjjRjjInfo";
	  }
  
	  /**
	   * 日掘进信息管理获取 项目、区间、线路 的easyUi列表
	   * @param pageNo 页码
	   * @param pageSize 每页条数
	   * @param dto 查询条件dto
	   * @return 返回查询结果信息
	   */
	  @RequestMapping(value = "/dataGridParent", method = RequestMethod.POST)
	  @ResponseBody
	  public Object dataGridParent(DgjjRjjInfoParentDto dto,Integer page, Integer rows, String sort, String order)
	  {
	      PageInfo pageInfo = new PageInfo(page, rows, sort, order);
	      Map<String, Object> condition = new HashMap<String, Object>();
	      
	      condition.put("proId", dto.getProId());
	      condition.put("sqlPurview", this.sqlPurview("l","pro_id"));
	      pageInfo.setCondition(condition);
	      try {
	    	  dgjjRjjInfoService.selectDataGridParent(pageInfo,getCurrentUser());
	      } catch (Exception e) {
	         e.printStackTrace();
	      }
	      
	      return pageInfo;
	  }
	  
	    /***
	     * 作用：下载已有的“日掘进参数模版.xls”
	     * @param filepath 下载文件全路径
	     * @param dto
	     * @param response
	     * @author wpg
	     */
	    @RequestMapping(value = "/downLoadModel")
	    @ResponseBody
	    public Object downLoadModel(HttpServletResponse response)
	    {
	    	//模版已建好
	    	//模版存放路径，模版存放在基础路径的 downloadMode文件夹下；
	    	String filepath = "/downloadMode/riJueJinMode/riJueJinCanShuMode.xls";
	    	//通用类  ，调用 文件下载 方法
	    	UploadUtil.downloadFile2(filepath, request, response);
	    	return null;
	    }
	  
	  /**  
	   * 日掘进信息管理获取 班组 信息  
	   * @param pageNo 页码
	   * @param pageSize 每页条数
	   * @param dto 查询条件dto
	   * @return 返回查询结果信息
	   */
	  @RequestMapping(value = "/selectBanZuBy", method = RequestMethod.POST)
	  @ResponseBody
	  public Object selectBanZuByXlbh(DgjjRjjInfoParentDto dto)
	  {
		  List<DgjjBzgl> bzglList = new ArrayList<DgjjBzgl>(); 
		  try {
			  if(dto.getXlBh()!=null && dto.getXlBh()!=""){
				   bzglList = dgjjRjjInfoService.selecDgjjBzgltListByXlbh(dto.getXlBh());
			  }
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		  return bzglList;  
	  } 
	  
	  
    /**
     * 作用：解析“日掘进参数 .xls”文件
     * 
     */
 	@RequestMapping("/upload")  
 	@ResponseBody
 	public Object upload(@RequestParam(value = "clumOne", required = false) MultipartFile file ,HttpServletRequest request) {  
 		Result result = new Result();
 		try {
 			if(file !=null && file.getOriginalFilename()!=null && file.getSize() !=0 ){
 				List<DgjjRjjInfo> dtos = dgjjRjjInfoService.readXlsxIs(file);
		 		result.setObj(dtos);
		 		result.setSuccess(true);
		 		return result;
 			}else{
 				result.setMsg("解析失败！请选择文件！");
 			}
	 		
 		} catch (IOException e) {
 			e.printStackTrace();
 			result.setMsg("解析失败！");
 		}
 		result.setSuccess(false);
		
        return result;
     }

  
 	 /**
     * Excle 方式 增加“日掘进信息”保存
     * @param dto 
     * @return
     */
    @RequestMapping(value = "/excleWayAdd", method = RequestMethod.POST)
 	@ResponseBody
 	public Object save(DgjjRjjInfoDto dto ) {
    	 try {
    		 // 获取 “曲线信息集合”字符串 [{&quot;FId&quot;:&quot;8e8c910507aa45c69287661afa4a8de0&quot;,&quot;bpdzh&quot;:&quot;412.000000&quot;,&quot;bpdgc&quot;:&quot;24.000000&quot;,&quot;sjqxbj&quot;:&quot;234.000000&quot;,&quot;remarks&quot;:&quot;wqe&quot;}；
    		 String json=dto.getDetails();
    	     	
    	     	//子表信息
    	 		if(!"[]".equals(json) && null != json){
    	 			json = json.replace("&quot;", "\"");
    	 			
    	 			 List<DgjjRjjInfoDto> list = new ArrayList<DgjjRjjInfoDto>();  
    	                       //转换类型
    	 		     list = JSONObject.parseArray(json, DgjjRjjInfoDto.class);
    	 		    
    	 		     
    	 		     for(DgjjRjjInfoDto j:list){
    	 		    	DgjjRjjInfo rjj = new DgjjRjjInfo();
    	 		    	//反射将一个对象"j"的值 赋值另外一个对象"qxys"
    	 	    		BeanUtils.copyProperties(j,rjj);
    	 	    		//保存
    	 	    		dgjjRjjInfoService.insert(rjj);
    	 	    		
    	 	    		//通过班组ID获得班组对象
 //   	        		DgjjBzgl dgjjBzgl = dgjjBzglService.selectById(rjj.getBId());
    	        		//添加盾构机使用记录
 //   	        		ProRSectionLine line = lineService.selectById(dgjjBzgl.getFid());
//    	        		ProTbmUseInfo proTbmUseInfo = new ProTbmUseInfo();
//    	        		proTbmUseInfo.setGcid(line.getProId());
//    	        		proTbmUseInfo.setXlid(line.getId());
//    	        		proTbmUseInfo.setQjid(line.getSectionId());
//    	        		proTbmUseInfo.setTbmbh(line.getTbmId());
//    	        		proTbmUseInfo.setDgjjid(rjj.getId());
//    	        		ProTbminfo tbmInfo = tbmInfoService.selectById(line.getTbmId());
//    	        		proTbmUseInfo.setTbmName(tbmInfo.getTbmName());
 //   	        		if (!StringUtils.isBlank(rjj.getDgjjwcqzsj()) && ! StringUtils.isBlank(rjj.getSgrq())) {
//    	        		proTbmUseInfo.setSgDate(rjj.getSgrq());
//    	        		proTbmUseInfo.setTbmUseTime(rjj.getDgjjwcqzsj());
//    	        		proTbmUseInfoService.insert(proTbmUseInfo);
 //   	        		}
    	 		     }
    	 		   
    	 		}
    	     	return renderSuccess("保存成功！");
		} catch (Exception e) {
			e.printStackTrace();
			log(null, false, "新增 “日掘进”信息失败!");
		}
    	 return renderError("保存失败！");
 	}	
 	
  	

    /**
     * 获取线路中心线信息管理easyUi列表
     * @param pageNo 页码
     * @param pageSize 每页条数
     * @param dto 查询条件dto
     * @return 返回查询结果信息
     */
    @RequestMapping(value = "/dataGrid", method = RequestMethod.POST)
    @ResponseBody
    public Object dataGrid(DgjjRjjInfoParentDto dto,Integer page, Integer rows, String sort, String order)
    {     
    	PageInfo pageInfo = new PageInfo(page, rows, sort, order);
	      Map<String, Object> condition = new HashMap<String, Object>();
	      
	      condition.put("XlBh", dto.getXlBh());
	       
	      pageInfo.setCondition(condition);
	      try {
	    	  dgjjRjjInfoService.selectDgjjRjjInfoListByXlBh(pageInfo);
	      } catch (Exception e) {
	         e.printStackTrace();
	      }
	      
	      return pageInfo;
   
    }
    
    /***
     * 打开“添加单条 日掘进信息”页面
     * @author wpg
     * @param xlBh 线路表ID
     * @param model 
     * @return
     */
    @RequestMapping(value = "addUnfold", method = RequestMethod.GET)
    public String addUnfold(String xlBh,Model model) { 
        model.addAttribute("xlBh",JSON.toJSONString(xlBh));
        return "dgjj/rjj/addRjj";
    }
    
    
    /**
     * 根据 路径ID查询 班组信息 
     * @return
     */
    @RequestMapping(value ="/addBzgl", method = RequestMethod.POST)
    @ResponseBody
    public Object addRjjSelectBzgl(String xlBh) {
    	
    	List<DgjjBzgl> bzglList = new ArrayList<DgjjBzgl>(); 
		  try {
			  if(xlBh!=null && xlBh!=""){
				   bzglList = dgjjRjjInfoService.selecDgjjBzgltListByXlbh(xlBh);
			  }
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return bzglList; 
        
    }
    
    /***
     * 保存 添加 日掘进信息
     * @param rjj 日掘进对象
     * @return
     */
    @RequestMapping("/addSave")
    @ResponseBody
    public Object addSave(DgjjRjjInfo rjj) {
    	
    	Result result = new Result();
        try {
        	if(null!=rjj.getBId()&& ""!=rjj.getBId()){
        		dgjjRjjInfoService.insert(rjj);
        		result.setSuccess(true);
        		result.setMsg("添加成功！");
        		
        		//通过班组ID获得班组对象
//        		DgjjBzgl dgjjBzgl = dgjjBzglService.selectById(rjj.getBId());
        		//添加盾构机使用记录
//        		ProRSectionLine line = lineService.selectById(dgjjBzgl.getFid());
//        		ProTbmUseInfo proTbmUseInfo = new ProTbmUseInfo();
//        		proTbmUseInfo.setGcid(line.getProId());
//        		proTbmUseInfo.setXlid(line.getId());
//        		proTbmUseInfo.setQjid(line.getSectionId());
//        		proTbmUseInfo.setTbmbh(line.getTbmId());
//        		proTbmUseInfo.setDgjjid(rjj.getId());
//        		ProTbminfo tbmInfo = tbmInfoService.selectById(line.getTbmId());
//        		proTbmUseInfo.setTbmName(tbmInfo.getTbmName());
//        		if (!StringUtils.isBlank(rjj.getDgjjwcqzsj()) && ! StringUtils.isBlank(rjj.getSgrq())) {
//        		proTbmUseInfo.setSgDate(rjj.getSgrq());
//        		proTbmUseInfo.setTbmUseTime(rjj.getDgjjwcqzsj());
//        		proTbmUseInfoService.insert(proTbmUseInfo);
//        		}
        	}
		} catch (Exception e) {
			result.setSuccess(false);
			result.setMsg("保存失败！");
			e.printStackTrace();
			log(null, false, "新增 “日掘进”信息失败!");
		}
        return result;
    }
   

    /**
     * 编辑页
     *
     * @param request
     * @param id
     * @return
     */
    @RequestMapping("/editUnfold")
    public String editPage(HttpServletRequest request, String id ,String xlBh,  Model model) {
    	if( id !=null && xlBh!=null){
    		DgjjRjjInfoDto Dto = dgjjRjjInfoService.selectDgjjRjj(id);
    		request.setAttribute("Dto", Dto);
    	    model.addAttribute("xlBh",JSON.toJSONString(xlBh)); 
    	}
    	
        return "dgjj/rjj/editRjj";
    }
    
    /***
     * 保存 编辑 日掘进信息
     * @param rjj 日掘进对象
     * @return
     */
    @RequestMapping("/editSave")
    @ResponseBody
    public Object editSave(DgjjRjjInfo rjj) {
        
        if(dgjjRjjInfoService.updateById(rjj))
        {
        	 return renderSuccess("编辑成功！"); 
        }else{
        	return renderError("编辑失败！");

        }
        
    }
   
    
    /**
     * 查看单条日掘进信息
     *
     * @param request
     * @param id
     * @return
     */
    @RequestMapping("/lookUnfold")
    public String lookPage(HttpServletRequest request, String id ) {
    	if( id !=null ){
    		DgjjRjjInfoDto Dto = dgjjRjjInfoService.selectDgjjRjj(id);
    		request.setAttribute("Dto", Dto);
    	    
    	}
    	
        return "dgjj/rjj/lookRjj";
    }
    
    

    /**
	 * 删除 选中日掘进数据
	 */
	@RequestMapping(value = "/del",method = RequestMethod.POST)
	@ResponseBody
	public Object del(String[] ids) {
		try { 
			List<String> list = Arrays.asList(ids);
			for (String id : list) {
				dgjjRjjInfoService.deleteById(id); 
			}
			
		} catch (Exception e) {
			this.log("删除", false, "操作失败"); 
			return renderError("删除操作：失败。");
		}
		this.log("删除", true, "操作成功");
		return renderSuccess("删除操作：成功。");
	}
	
    
	  
   /**
    * 行编辑“日掘进信息”保存修改
    * @param dto 
    * @return
    */
    @RequestMapping(value = "/showUpdate", method = RequestMethod.POST)
	@ResponseBody
	public Object showUpdate(DgjjRjjInfoDto dto ) {
   	 try {
   		 // 获取 “曲线信息集合”字符串 [{&quot;FId&quot;:&quot;8e8c910507aa45c69287661afa4a8de0&quot;,&quot;bpdzh&quot;:&quot;412.000000&quot;,&quot;bpdgc&quot;:&quot;24.000000&quot;,&quot;sjqxbj&quot;:&quot;234.000000&quot;,&quot;remarks&quot;:&quot;wqe&quot;}；
   		 String json=dto.getDetails();
   	     	
   	     	//子表信息
   	 		if(!"[]".equals(json) && null != json){
   	 			json = json.replace("&quot;", "\"");
   	 			
   	 			 List<DgjjRjjInfoDto> list = new ArrayList<DgjjRjjInfoDto>();  
   	                       //转换类型
   	 		     list = JSONObject.parseArray(json, DgjjRjjInfoDto.class);
   	 		    
   	 		     
   	 		     for(DgjjRjjInfoDto j:list){
   	 		    	DgjjRjjInfo rjj = new DgjjRjjInfo();
   	 		    	//反射将一个对象"j"的值 赋值另外一个对象"qxys"
   	 	    		BeanUtils.copyProperties(j,rjj);
   	 	    		//保存
   	 	    		dgjjRjjInfoService.updateById(rjj);
   	 		     }
   	 		   
   	 		}
   	     	return renderSuccess("保存成功！");
		} catch (Exception e) {
			e.printStackTrace();
			log(null, false, "修改保存 “日掘进”信息失败!");
		}
   	 return renderError("保存失败！");
	}	
	
    
   
    /**
     * Excel 数据导出     
     * @param exportDto
     * @param Dto ：日掘进工具类
     * @param response
     */
	@SuppressWarnings({"unchecked"})
	@RequestMapping(value="/expXls")
	public void expXls(ExportDto exportDto,DgjjRjjInfoDto dto, HttpServletResponse response){
		try {
			PageInfo pageInfo = new PageInfo(0, 65530, "id","desc");
			Map<String, Object> condition = new HashMap<String, Object>();
			//判断 是否有  选中的行 exportDto.getIds()：选中行的Id集成 字符串
			if(StringUtils.isNotBlank(exportDto.getIds())){
				List<String> idsList =new ArrayList<String>();
				for(String info:exportDto.getIds().split(ExportDto.SPLIT_STR)){
					idsList.add("'"+info+"'");
				}
				
				String idsstr = StringUtils.ListToString(idsList, ",");
				condition.put("ids", idsstr);
				pageInfo.setCondition(condition);
				dgjjRjjInfoService.selectDataGrid(pageInfo);
				ExportExcelUtil.writeExcelToClient(response, exportDto, ExportSetUtil.objectToListMap(pageInfo.getRows()));
			}else{
				//设置查询条件
				if(null != dto.getXlBh()){
					condition.put("XlBh", dto.getXlBh());
				}
				
				pageInfo.setCondition(condition);
				dgjjRjjInfoService.selectDataGrid(pageInfo);
				ExportExcelUtil.writeExcelToClient(response, exportDto, ExportSetUtil.objectToListMap(pageInfo.getRows()));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	} 
	
	/** 
     * Pdf 导出数据    
     * @param exportDto
     * @param Dto ：日掘进工具类
     * @param response
     */
	@SuppressWarnings("unchecked")
	@RequestMapping(value="/expPdf")
	public void expPdf(ExportDto exportDto,DgjjRjjInfoDto dto, HttpServletResponse response){
		try {
			PageInfo pageInfo = new PageInfo(0, 65530, "id","desc");
			Map<String, Object> condition = new HashMap<String, Object>();
			//判断 是否有  选中的行 exportDto.getIds()：选中行的Id集成 字符串
			if(StringUtils.isNotBlank(exportDto.getIds())){
				List<String> idsList =new ArrayList<String>();
				for(String info:exportDto.getIds().split(ExportDto.SPLIT_STR)){
					idsList.add("'"+info+"'");
				}
				
				String idsstr = StringUtils.ListToString(idsList, ",");
				condition.put("ids", idsstr);
				pageInfo.setCondition(condition);
				dgjjRjjInfoService.selectDataGrid(pageInfo);
				ExportPdfUtil.writePdfToClient(response, exportDto, ExportSetUtil.objectToListMap(pageInfo.getRows()));
			}else{
				//设置查询条件
				if(null != dto.getXlBh()){
					condition.put("XlBh", dto.getXlBh());
				}
				
				pageInfo.setCondition(condition);
				dgjjRjjInfoService.selectDataGrid(pageInfo);
				ExportPdfUtil.writePdfToClient(response, exportDto, ExportSetUtil.objectToListMap(pageInfo.getRows()));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	} 

}