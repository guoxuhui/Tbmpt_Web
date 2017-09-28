package com.crfeb.tbmpt.sgkshgl.jcdzbgl.controller;

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

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.util.IOUtils;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.crfeb.tbmpt.commons.base.BaseController;
import com.crfeb.tbmpt.commons.utils.BeanUtils;
import com.crfeb.tbmpt.commons.utils.CommUtils;
import com.crfeb.tbmpt.commons.utils.DateUtils;
import com.crfeb.tbmpt.commons.utils.ExcelUtils;
import com.crfeb.tbmpt.commons.utils.ExportDto;
import com.crfeb.tbmpt.commons.utils.ExportExcelUtil;
import com.crfeb.tbmpt.commons.utils.ExportPdfUtil;
import com.crfeb.tbmpt.commons.utils.ExportSetUtil;
import com.crfeb.tbmpt.commons.utils.PageInfo;
import com.crfeb.tbmpt.commons.utils.ParseDATFile;
import com.crfeb.tbmpt.commons.utils.ParseExcelFile;
import com.crfeb.tbmpt.commons.utils.StringUtils;
import com.crfeb.tbmpt.commons.utils.SysPropertieUtil;
import com.crfeb.tbmpt.commons.utils.UploadUtil;
import com.crfeb.tbmpt.dmcjjc.info.controller.DmcjJcPointAction;
import com.crfeb.tbmpt.dmcjjc.info.model.JcPoint;
import com.crfeb.tbmpt.dmcjjc.info.model.vo.DmcjJcInfoDetailsDto;
import com.crfeb.tbmpt.dmcjjc.info.model.vo.DmcjJcPointDto;
import com.crfeb.tbmpt.gpztcl.base.model.GpztclXyfys;
import com.crfeb.tbmpt.gpztcl.base.model.dto.GpztclXyfysDto;
import com.crfeb.tbmpt.project.model.ProProjectinfo;
import com.crfeb.tbmpt.project.model.ProRProjectSection;
import com.crfeb.tbmpt.project.service.IProProjectinfoService;
import com.crfeb.tbmpt.sgkshgl.jcdzbgl.model.SgkshglJcdzbgl;
import com.crfeb.tbmpt.sgkshgl.jcdzbgl.model.dto.SgkshglJcdzbglDto;
import com.crfeb.tbmpt.sgkshgl.jcdzbgl.service.ISgkshglJcdzbglService;
import com.crfeb.tbmpt.sys.model.User;
import com.crfeb.tbmpt.zsjk.jt.model.ZsJkJtAqZlMxXx;


@Controller
@RequestMapping(value="/sgkshgl/jcdzbgl")
public class SgkshglJcdzbglController extends BaseController{
	private static final Logger LOGGER = LogManager
			.getLogger(DmcjJcPointAction.class);
	
	private String fileRelativePath = "/upload/sgkshgl/jcdzbgl/"+DateUtils.format(new Date(), "yyyyMMdd");//文件上传相对路径
	
	@Autowired 
	private ISgkshglJcdzbglService sgkshglJcdzbglService;
	@Autowired
	private IProProjectinfoService proProjectinfoService;
	
	/**
     * 跳转到列表页面
     */
    @RequestMapping(value = "", method = RequestMethod.GET)
    public String manager() {
        return "sgkshgl/jcdzbgl/list";
    }
    
    /**
     * 跳转到新增页面
     */
    @RequestMapping(value = "/addPage", method = RequestMethod.GET)
    public String addPage() {
        return "sgkshgl/jcdzbgl/add";
    }
    
    /**
     * 跳转到编辑页面 
     */
    @RequestMapping("/editPage")
    public String editPage(Model model, String objectid) {
//    	JcPoint info = new JcPoint();
//    	info.setId(id);
//        info = sgkshglJcdzbglService.selectOne(info);
//        model.addAttribute("vo", info);
//        
//        /**
//         * 检测监测点是否已经被使用
//         * 
//         * 如果使用则设置主要字段无法修改
//         * 否则全部字段可以修改
//         */
//		//检查日常监测管理子表中已有该监测点信息，则无法修改，并提示“该监测点存在日常监测信息，操作失败。”
//		int count = sgkshglJcdzbglService.selectDetailsByJcd(info.getJcdbh());
//		if(count > 0){
//			model.addAttribute("ifsy", true);
//		}else{
//			model.addAttribute("ifsy", false);
//		}
    	SgkshglJcdzbglDto sgkshglJcdzbglDto=sgkshglJcdzbglService.selectOne(objectid);
//    	model.addAttribute("dto", JSON.toJSONString(sgkshglJcdzbglDto));
    	model.addAttribute("dto",sgkshglJcdzbglDto);
        return "sgkshgl/jcdzbgl/edit";
    }
    
    @RequestMapping("/showPage")
    public String showPage(Model model, String objectid) {

    	SgkshglJcdzbglDto sgkshglJcdzbglDto=sgkshglJcdzbglService.selectOne(objectid);
    	model.addAttribute("dto",sgkshglJcdzbglDto);
        return "sgkshgl/jcdzbgl/show";
    }
    
    /**
     * 列表页面分页查询
     */
    @RequestMapping(value = "/list", method = RequestMethod.POST)
	@ResponseBody
	public Object list(SgkshglJcdzbglDto dto,Integer page, Integer rows, String sort, String order) {
		PageInfo pageInfo = new PageInfo(page, rows, sort, order);
		Map<String, Object> condition = new HashMap<String, Object>();
		//设置查询条件
		if(null != dto.getProId()){
			condition.put("proId", dto.getProId());
		}
		if(null != dto.getSecId()){
			condition.put("secId", dto.getSecId());
		}
		if(null != dto.getJcId()){
			condition.put("jcId", dto.getJcId());
		}
		if(null != dto.getJcType()){
			condition.put("jcType", dto.getJcType());
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
		sgkshglJcdzbglService.selectDataGrid(pageInfo);
		return pageInfo;
	}
    
    /**
     * 项目资源树
     *
     * @return
     */
    @RequestMapping(value = "/tree", method = RequestMethod.POST)
    @ResponseBody
    public Object treeByUid(String id,String flag) {
    	User user = getCurrentUser();
    		if("admin".equals(user.getLoginName())){
    			return proProjectinfoService.selectTree();
    		}else{
    			return proProjectinfoService.selectTreeByUser(user);
    		}
    	}
    
    /**
	 * 保存
	 */
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	@ResponseBody
	public Object save(SgkshglJcdzbglDto dto) {
		try {
			SgkshglJcdzbgl sgkshglJcdzbgl=new SgkshglJcdzbgl();
			BeanUtils.copyProperties(dto, sgkshglJcdzbgl);
			sgkshglJcdzbglService.save(sgkshglJcdzbgl);
		} catch (Exception e) {
			this.log("新增", false, "操作失败.");
			LOGGER.error("SgkshglJcdzbglController save失败", e);
			return renderError("新增操作：失败。");
		}
		this.log("新增", true, "操作成功.");
		return renderSuccess("新增操作：成功。");
	}
	
    @RequestMapping(value = "/importPage", method = RequestMethod.GET)
    public String importPage() {
        return "sgkshgl/jcdzbgl/import";
    }
    /**
     * 修改
     * @return
     */
    @RequestMapping(value = "/update",method = RequestMethod.POST)
	@ResponseBody
	public Object update(SgkshglJcdzbgl dto) {
		try {
			//修改时要检查同一个分类名称下基础数据名称不能重复
//			sgkshglJcdzbglService.updateByObjectId(dto);
			sgkshglJcdzbglService.updateById(dto);
			
		} catch (Exception e) {
			this.log("编辑", false, "操作失败.");
			LOGGER.error("SgkshglJcdzbglController update失败", e);
			return renderError("修改操作：失败。");
		}
		this.log("编辑", true, "操作成功.");
		return renderSuccess("修改操作：成功。");
	}
    
    /**
     * 生成excel模板
     * @author wl_zjh
     * @param response
     */
    @RequestMapping(value="/excelTemplate",method = RequestMethod.GET)
    @ResponseBody
    public void ExcelTemplate(HttpServletResponse response) {
 		// 生成excel并下载
 		HSSFWorkbook wb = sgkshglJcdzbglService.generateExcelTemplate();
 		String filename = "监测点坐标管理.xls";
 		String iso_filename = ExcelUtils.parseGBK(filename);
 		response.setContentType("text/plain");
 		response.setHeader("Content-Disposition","attachment;filename=" + iso_filename);
 		
 		OutputStream ouputStream = null; 
 		try {
 			ouputStream = response.getOutputStream();
 			wb.write(ouputStream);
 			ouputStream.flush();
 		} catch (IOException e) {
 			
 		} finally{
 			IOUtils.close(ouputStream);
 		}
 	}
    
    /**
     * @author wl_zjh
     * 上传文件，然后跳转到新增页面 
     */
//    @RequestMapping("/upload")  
// 	@ResponseBody
// 	public Object upload(String gcbh,@RequestParam(value = "file", required = false) MultipartFile file, HttpServletRequest request, Model model) {  
// 		
//
//         //获取   后续路径
//         SysPropertieUtil configEntity = SysPropertieUtil.getInstince();
//         String upLoadPath = configEntity.getUploadPath();
//         //文件路径=前续路径 (来自配置文件) +  后续
//         String path = upLoadPath + fileRelativePath;
//         //filePath = upLoadPath + filePath;
//         
//         
//         //获取路径
//         //应该用新地址  找文件夹   “jcPointUpload”
//         //String path = request.getSession().getServletContext().getRealPath("jcPointUpload");  
//         File uploadDir = new File(path);
//         //即判断你指定的路径或着指定的目录文件是否已经存在。
//         if(!uploadDir.exists()){  
//         	uploadDir.mkdirs();  
//         }  
//         //用 “系统时间” 和 “文件名称” 组成导入 文件的名称，避免名称重复；
//         String fileName = System.currentTimeMillis()+file.getOriginalFilename();  
//         //用日志 输出 “上传文件路径”
//         LOGGER.info("upload file path="+path);
//         File targetFile = new File(path, fileName);  
         //保存文件到服务器  
//         String ring =null;
//         try {  
//             file.transferTo(targetFile);  
//             //获取 文件 的 信息 并 保存 ：文件名，项目ID ，文件路径
//             ring = this.ObtainFileData(fileName, gcbh, path);
//             if(ring ==null || ring==""){
//     		    return renderError("新增操作：失败。");
//     	   }
//         } catch (Exception e) {  
//         	LOGGER.error("upload error:",e);
//         	if(ring ==null || ring==""){
//     		    return renderError("新增操作：失败。");
//     	   }
//         	
//         }
//         return renderSuccess( ring);
// 		
         
//     }  
 	
    @RequestMapping("/upload")  
	public String upload(String fileName,HttpServletRequest request,Model model) {  
		   
 	   String path = request.getSession().getServletContext().getRealPath("upload");  
 	   SysPropertieUtil configEntity = SysPropertieUtil.getInstince();
 	   String upLoadPath = configEntity.getUploadPath();
 	   String filePath = upLoadPath+fileName;
 	   int index=fileName.lastIndexOf(".");
 	   String str=fileName.substring(index); 	    
       try {    
           //获取 文件 的 信息 并 保存 ：文件名，项目ID ，文件路径
           List<List<Map<String,String>>> list = ParseExcelFile.readExcelWithTitle(upLoadPath+""+fileName);
     	   //把 Excel表格数据集合 转成  对象  并  保存
     	   List<SgkshglJcdzbgl> importList=sgkshglJcdzbglService.readExcel(list);
     	  model.addAttribute("list", JSON.toJSONString(importList));    	   
       } catch (Exception e) {  
       	LOGGER.error("upload error:",e);
       	
       }
       return "sgkshgl/jcdzbgl/importshow";
       
   }  
    /**
  	 * @author wl_zjh
 	 * 作用：新增页面再请求上传的文件，解析文件内容 并 把信息存表
  	 * @return 
 	 */
    public String ObtainFileData(String fileName,String gcbh,String path) {
 	   String ring =null;
 	   try {
     	   //
     	   List<List<Map<String,String>>> list = ParseExcelFile.readExcelWithTitle(path+"/"+fileName);     	   
 		} catch (Exception e) {
 			LOGGER.error(" 失败", e);
 		}
 	  
 		return ring;
 	  
    }    
    
    /**
     * 上传文件，然后跳转到新增页面 
     */
 	@RequestMapping("/uploadFile")  
 	@ResponseBody
 	public Object uploadFile(@RequestParam(value = "file", required = false) MultipartFile file, HttpServletRequest request, Model model) {
 		String filePath = fileRelativePath;
 		String fileName = System.currentTimeMillis()+file.getOriginalFilename();
 		UploadUtil uploadutil = new UploadUtil();  
        String messageInfo = uploadutil.uploadFile(request, file, fileName, filePath);
        if(StringUtils.isNotBlank(messageInfo)){
        	LOGGER.error("上传文件失败!");
        	this.log("", false, "上传文件失败!");
        	return renderError("上传文件失败!");
        }
 		Map<String ,String> map = new HashMap<String, String>();
        map.put("fileName", filePath+"/"+fileName);
        return renderSuccess(map);
 		
     }
 	
 	@RequestMapping(value="/saveImport", method=RequestMethod.POST)  
 	@ResponseBody
 	public Object saveImport(SgkshglJcdzbglDto dto) {
    	String json=dto.getDetails();   	
    	//子表信息
		if(!"[]".equals(json) && null != json){
			json = json.replace("&quot;", "\"");
			 List<SgkshglJcdzbglDto> list = new ArrayList<SgkshglJcdzbglDto>();  
		     list = JSONObject.parseArray(json, SgkshglJcdzbglDto.class);
		     List<SgkshglJcdzbgl> addlist = new ArrayList<>();
		     
		     for(SgkshglJcdzbglDto j:list){
		    	 SgkshglJcdzbgl sgkshglJcdzbgl = new SgkshglJcdzbgl();
	    		BeanUtils.copyProperties(j, sgkshglJcdzbgl);
	    		addlist.add(sgkshglJcdzbgl);
		     }
		     Map<String, Object> columnMap = new HashMap<>();

		     sgkshglJcdzbglService.insertBatch(addlist);
		     
		}
    	return renderSuccess("保存成功！");
	}
 	
 	@RequestMapping(value="/del", method=RequestMethod.POST)  
 	@ResponseBody
 	public Object delete(String ids) {	
    	String[] idss = ids.split(",");
    	List<String> idlist = new ArrayList<String>();
    	Collections.addAll(idlist, idss);    	
    	Boolean falt = sgkshglJcdzbglService.deleteBatchIds(idlist);
    	if(falt){
    		return renderSuccess("删除成功！");
    	}else{
    		return renderSuccess("删除异常！");
    	}
    }
 	
    /**     
    * 数据导出     
    * @param exportDto     
    * @param Dto     
    * @param response     
    */
    @SuppressWarnings({"unchecked", "rawtypes"})
    	@RequestMapping(value="/expXls")
    	public void expXls(ExportDto exportDto,ZsJkJtAqZlMxXx dto, HttpServletResponse response){
    		try {
    			if(StringUtils.isNotBlank(exportDto.getIds())){
	    			List<String> idsList =new ArrayList<String>();
    				Collections.addAll(idsList, exportDto.getIds().split(ExportDto.SPLIT_STR));
    				List list = sgkshglJcdzbglService.selectBatchIds(idsList);
    				ExportExcelUtil.writeExcelToClient(response, exportDto, ExportSetUtil.objectToListMap(list));
	    		}else{
	    	        PageInfo pageInfo = new PageInfo(0, 65530, "id", "desc");
    		        Map<String, Object> condition = new HashMap<String, Object>();
	    	        pageInfo.setCondition(condition);
	    	        sgkshglJcdzbglService.selectDataGrid(pageInfo);
	    			ExportExcelUtil.writeExcelToClient(response, exportDto, ExportSetUtil.objectToListMap(pageInfo.getRows()));
	    		}
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
     @SuppressWarnings({"unchecked", "rawtypes"})
     @RequestMapping(value="/expPdf")
     public void expPdf(ExportDto exportDto,ZsJkJtAqZlMxXx dto, HttpServletResponse response){
	     	try {
	     		if(StringUtils.isNotBlank(exportDto.getIds())){
	     			List<String> idsList =new ArrayList<String>();
	     			 Collections.addAll(idsList, exportDto.getIds().split(ExportDto.SPLIT_STR));
	     			 List list = sgkshglJcdzbglService.selectBatchIds(idsList);
	     			 ExportPdfUtil.writePdfToClient(response, exportDto, ExportSetUtil.objectToListMap(list));
	     		}else{
	     	        PageInfo pageInfo = new PageInfo(0, 65530, "id", "desc");
	     	        Map<String, Object> condition = new HashMap<String, Object>();
	     	        pageInfo.setCondition(condition);
	     	       sgkshglJcdzbglService.selectDataGrid(pageInfo);
	     			ExportPdfUtil.writePdfToClient(response, exportDto, ExportSetUtil.objectToListMap(pageInfo.getRows()));
	     		}
	     	} catch (Exception e) {
	     		e.printStackTrace();
	     	}
     }
 
 	
}
