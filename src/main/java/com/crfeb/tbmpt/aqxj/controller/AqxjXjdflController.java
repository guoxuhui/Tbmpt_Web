package com.crfeb.tbmpt.aqxj.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.crfeb.tbmpt.aqxj.model.AqxjXjdfl;
import com.crfeb.tbmpt.aqxj.model.dto.AqxjXjdflDto;
import com.crfeb.tbmpt.aqxj.service.AqxjxjdflService;
import com.crfeb.tbmpt.aqxj.xjdgl.mapper.AqxjXjdMapper;
import com.crfeb.tbmpt.aqxj.xjdgl.model.AqxjXjd;
import com.crfeb.tbmpt.aqxj.xjdgl.model.AqxjXjnr;
import com.crfeb.tbmpt.aqxj.xjdgl.service.AqxjXjdService;
import com.crfeb.tbmpt.aqxj.xjdgl.service.AqxjXjnrService;
import com.crfeb.tbmpt.commons.base.BaseController;
import com.crfeb.tbmpt.commons.result.Tree;
import com.crfeb.tbmpt.commons.utils.ExportDto;
import com.crfeb.tbmpt.commons.utils.ExportExcelUtil;
import com.crfeb.tbmpt.commons.utils.ExportPdfUtil;
import com.crfeb.tbmpt.commons.utils.ExportSetUtil;
import com.crfeb.tbmpt.commons.utils.PageInfo;
import com.crfeb.tbmpt.commons.utils.StringUtils;
import com.crfeb.tbmpt.gczl.base.model.GczlYdxjGPZLDDInfo;
import com.crfeb.tbmpt.gczl.base.model.GczlYdxjGPZLDDType;
import com.crfeb.tbmpt.gczl.base.model.GczlYdxjSGZLSgnr;
import com.crfeb.tbmpt.gczl.base.model.dto.GczlYdxjSGZLSgnrDto;
import com.crfeb.tbmpt.project.model.ProProjectinfo;
import com.crfeb.tbmpt.project.service.IProProjectinfoService;
import com.crfeb.tbmpt.sys.model.Orgz;
import com.crfeb.tbmpt.sys.model.User;
import com.crfeb.tbmpt.sys.service.IOrgzService;


@Controller
@RequestMapping("/aqxj/aqxjXjdfl")
public class AqxjXjdflController extends BaseController{
	
	
	private static final Logger LOGGER = LogManager.getLogger(AqxjXjdflController.class);
	
	@Autowired
	private AqxjxjdflService aqxjxjdflService;
	@Autowired
    private IProProjectinfoService iProProjectinfoService;
	@Autowired
	private IProProjectinfoService proProjectinfoService;
	@Autowired
    private AqxjXjdService aqxjXjdService;
	@Autowired
    private AqxjXjnrService aqxjXjnrService;
	@Autowired
    private AqxjXjdMapper aqxjXjdMapper;
	
	
	
	@RequestMapping(value = "", method = RequestMethod.GET)
    public ModelAndView manager() {
        ModelAndView modelAndView = new ModelAndView("aqxj/xjdfl/aqxjXjdfl");
  	  ProProjectinfo projectinfo = iProProjectinfoService.getProjectInfoByUserId(getUserId());
  	  if(projectinfo!=null){
  		  modelAndView.addObject("projectId",projectinfo.getId());
  		  modelAndView.addObject("projectName",projectinfo.getProName());
  	  }
        return modelAndView;
    }
	
	
	/**
     * 安全巡检管理管理easyUi列表
     * @param pageNo 页码
     * @param pageSize 每页条数
     * @param dto 查询条件dto
     * @return 返回查询结果信息
     *//*
    @RequestMapping(value = "/dataGrid", method = RequestMethod.POST)
    @ResponseBody
    public Object dataGrid(AqxjXjdflDto dto,Integer page, Integer rows, String sort, String order)
    {
        PageInfo pageInfo = new PageInfo(page, rows, sort, order);
        Map<String, Object> condition = new HashMap<String, Object>();
        condition.put("fenleiMc", dto.getFenleiMc());
        pageInfo.setCondition(condition);
        aqxjxjdflService.selectDataGrid(pageInfo);
        return pageInfo;
    }
	*/
	
	
	/* @RequestMapping(value = "/getFl", method = RequestMethod.POST)
	    @ResponseBody
	    public Object findAqxjXjdfl(String gcmc) {
		 	 List<AqxjXjdfl> types=null;
	    	 List<Tree> trees = new ArrayList<Tree>();
	    	 if(gcmc!=null&&gcmc!=""){
	    		 types=aqxjxjdflService.getFlMc(gcmc);
	    	 }else{
	    	 types = aqxjxjdflService.getAll();
	    	 }
	    	 if(types!=null&&types.size()>0)
	    	 for (AqxjXjdfl type : types) {
	    		 Tree tree = new Tree();
	    		 tree.setId(type.getFenleiMc());
	    		 tree.setText(type.getFenleiMc());
	    		 trees.add(tree);
			}
	    	return trees;
	    }
	*/
   
	
	
	 @RequestMapping(value = "/getFl", method = RequestMethod.POST)
	    @ResponseBody
	    public Object fltree(String gcmc) {
		 List<Tree> trees=null;
		 if(gcmc!=null&&gcmc!=""){
			 trees=aqxjxjdflService.selectflTree(gcmc);
		 }else{
			 trees=aqxjxjdflService.selectTree();
		 }
	        return trees;
	    }
	 
	 
	 @RequestMapping(value = "/getcxFl", method = RequestMethod.POST)
	    @ResponseBody
	    public Object cxFltree(String gcmc) {
		 List<Tree> trees=null;
			 trees=aqxjxjdflService.selectAllFlMcTree();
	        return trees;
	    }
	 
	 /**
     * 添加分类巡检点
     * @param gczlYdxjGPZLDDInfo 要新增的实体信息
     * @return 返回保存成功/失败的提示信息
     */
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public Object add(AqxjXjdflDto aqxjXjdfl) {
    	String errmessage = aqxjxjdflService.checkClumIfexits(aqxjXjdfl,new String[]{"fenlei_mc"});
    	User user = this.getCurrentUser();
    	if(errmessage!=null) return renderError(errmessage);
    	int i = aqxjxjdflService.insert(aqxjXjdfl,user);
    	if(i>0){
    		LOGGER.info("巡检点分类添加数据成功。");
    		this.log(null, true, "用户"+user.getName()+"添加记录成功。");
    		return renderSuccess("添加成功！");
    	}else{
    		LOGGER.error("巡检点分类信息添加失败。");
    		this.log(null, false, "用户"+user.getName()+"添加记录失败。");
    		return renderError("添加失败。");
    	}
    }
   

    /**
     * 修改巡检点管理
     * @param gczlYdxjGPZLDDInfo 要修改的实体信息
     * @return 返回编辑成功/失败的提示信息
     */
    @RequestMapping("/edit")
    @ResponseBody
    public Object edit(AqxjXjdflDto aqxjXjdfl) {
    	User user = this.getCurrentUser();
    	AqxjXjdfl oldEntity = this.aqxjxjdflService.selectById(aqxjXjdfl.getId());
    	if(null == oldEntity || StringUtils.isBlank(oldEntity.getId())){
    		return renderError("数据不存在,请刷新重试!");
    	}
    	String errmessage = aqxjxjdflService.checkClumIfexits(aqxjXjdfl,new String[]{"fenlei_mc"});
    	if(StringUtils.isNotBlank(errmessage)) return renderError(errmessage);//校验失败,数据不可更新
    	errmessage = aqxjxjdflService.update(aqxjXjdfl,this.getCurrentUser());
    	if(StringUtils.isNotBlank(errmessage)) return renderError(errmessage);//更新失败
    	LOGGER.info("结构施工质量基础信息修改数据成功。");
    	this.log(null, true, "用户"+user.getName()+"修改记录成功。id:"+aqxjXjdfl.getId());
        return renderSuccess("编辑成功!");
    }
    

   
    /**
     * 删除巡检点管理
     * @param ids 要删除的数据的ID集合
     * @return 返回删除成功/失败的提示信息
     */
   /* @RequestMapping("/delete")
    @ResponseBody
    public Object delete(String ids) {
    	User user = this.getCurrentUser();
    	String[] idss = ids.split(",");
    	List<String> idlist = new ArrayList<String>();
    	Collections.addAll(idlist, idss);
    	aqxjxjdflService.deleteByIds(idlist);
    	LOGGER.info("结构施工质量基础信息删除数据成功。");
    	this.log(null, true, "用户"+user.getName()+"删除记录成功。ids:"+ids);
        return renderSuccess("删除成功！");
    }*/
    
    
    @RequestMapping("/childSelect")
    @ResponseBody
    public Object childSelect(String id) {
    	List<AqxjXjdfl> list = aqxjxjdflService.selectByPid(id);
    	if (list==null || list.isEmpty()) {
    		
			return renderError("无子类可以编辑");
        }
    	return renderSuccess("有子类不能编辑");
    }
    
    
    
    /**
     * 删除巡检点分类
     *
     * @param id
     * @return
     */
    @RequestMapping("/delete")
    @ResponseBody
    public Object delete(String id) {
       
        String result ="";
    	try {
    		if(StringUtils.isNotBlank(id)){
    			//删除分类
    			result = aqxjxjdflService.deleteOperate(id);
    			
    			List<AqxjXjd> xjdList=aqxjXjdMapper.selectByTypeId(id);
    			ArrayList<String> idlist = new ArrayList<String>();
        		if(xjdList!=null&&!xjdList.isEmpty()){
        			for(AqxjXjd aqxjXjd: xjdList){
        				String xjdid=aqxjXjd.getId();
        				idlist.add(xjdid);
        			}
        			//删除巡检点
        			aqxjXjdService.deleteBatchIds(idlist);
        			/**
        			 * 删除巡检内容
        			 * */
        			
        			String[] idss=new String[idlist.size()];
        			idlist.toArray(idss);
        			String idsNr=aqxjXjnrService.selectXjnrByItems(idss);
        			List<String> idlistNr= new ArrayList<String>();
        			String[] idsss = idsNr.split(",");
        	    	Collections.addAll(idlistNr, idsss);
        	    	aqxjXjnrService.deleteBatchIds(idlistNr);
        		}
    			if(result=="" && result.equals("")){
    				return renderSuccess("删除成功！");
        	    }
    			if(result.equals("deleteAqxjTree")){
    				return renderError("该部门有下级部门，不能删除！请先删除下级部门！");
        	    }
    			/*else if(result.equals("deleteAqxjTree")){
    				//return renderSuccess(result);
    				return renderError("该部门有下级部门，不能删除！请先删除下级部门！");
        	    }
    			else if(result.equals("该部门的下级部门超过三级")){
        	    	return renderError("该部门的下级部门超过三级，该部门不能删除！请先删除下级部门！");
        	    }*/
    		}
    		
    		
		} catch (Exception e) {
			e.printStackTrace();
			return renderError("编辑失败！");
		}
    	return renderError("编辑失败！");
    }
    
    
    /**
     * 【工程信息应用】复制工程巡检分类
     *
     * @param proName
     * @return
     */
    @RequestMapping("/copyPro")
    @ResponseBody
    public Object copyPro(String proName) {
    	User user = this.getCurrentUser();
    	String userId = String.valueOf(user.getId());
    	String org=user.getOrgzName();
    	List<ProProjectinfo> project= proProjectinfoService.getProjectInfosByUserId(userId);
    	String currentProName=project.get(0).getProName();
       boolean flag =aqxjxjdflService.copyAqxjxjdfl(proName,user,currentProName);
       if(flag){
       		return renderSuccess("复制成功！");
       }
      
    	  return renderSuccess("noCopy");
    }
    
    
    
    /**
     * 【复制分类】复制巡检分类
     *
     * @param proName
     * @return
     */
    @RequestMapping("/copyfengL")
    @ResponseBody
    public Object copyfengL(String currProName,String fengL,String currFengL,String id,String currId) {
    	User user = this.getCurrentUser();
    	/*String userId = String.valueOf(user.getId());
    	String org=user.getOrgzName();
    	List<ProProjectinfo> project= proProjectinfoService.getProjectInfosByUserId(userId);
    	String currentProName=project.get(0).getProName();*/
       boolean flag =aqxjxjdflService.copyFLAqxjxjdfl(user,currProName,fengL,currFengL,id,currId);
       if(flag){
       		return renderSuccess("复制成功！");
       }
      
    	  return renderSuccess("noCopy");
    }
    
    
    /**
     * 【工程信息应用】复制工程巡检点
     *
     * @param proName
     * @return
     */
    @RequestMapping("/copyProXjd")
    @ResponseBody
    public Object copyProXjd(String proName) {
    	User user = this.getCurrentUser();
    	String userId = String.valueOf(user.getId());
    	String org=user.getOrgzName();
    	List<ProProjectinfo> project= proProjectinfoService.getProjectInfosByUserId(userId);
    	String currentProName=project.get(0).getProName();
    	String str = (String) aqxjXjdService.copyAqxjxjd(currentProName,proName);
       
       	return renderSuccess(str);
    }
    
    
    
    /**
     * 【复制分类】复制巡检点
     *
     * @param proName
     * @return
     */
    @RequestMapping("/copyFlXjd")
    @ResponseBody
    public Object copyFlXjd(String id,String currId,String currProName,String fengL) {
    	String str = (String) aqxjXjdService.copyFlXjd(id,currId,currProName,fengL);
       
       	return renderSuccess(str);
    }
    
    
    /**
     * 【工程信息应用】复制工程巡检内容
     *
     * @param proName
     * @return
     */
    @RequestMapping("/copyProXjnr")
    @ResponseBody
    public Object copyProXjnr(String proName) {
    	User user = this.getCurrentUser();
    	String userId = String.valueOf(user.getId());
    	String org=user.getOrgzName();
    	List<ProProjectinfo> project= proProjectinfoService.getProjectInfosByUserId(userId);
    	String currentProName=project.get(0).getProName();
    	String str1=(String) aqxjXjnrService.copyAqxjxjnr(currentProName,proName);
       	return renderSuccess(str1);
    }
    
    
    /**
     * 【复制分类】复制巡检内容
     *
     * @param proName
     * @return
     */
    @RequestMapping("/copyFlXjnr")
    @ResponseBody
    public Object copyFlXjnr(String id, String currId, String currProName) {
    	/*User user = this.getCurrentUser();
    	String userId = String.valueOf(user.getId());
    	String org=user.getOrgzName();
    	List<ProProjectinfo> project= proProjectinfoService.getProjectInfosByUserId(userId);
    	String currentProName=project.get(0).getProName();*/
    	 aqxjXjnrService.copyFlAqxjxjnr(id,currId,currProName);
       	return renderSuccess("复制成功");
    }
    
    /***
     * 删除巡检点分类树
     * @param id
     * @return
     */
    @RequestMapping("/deleteTree")
    @ResponseBody
    public Object deleteTree(String id) {
    	try {
    		if(aqxjxjdflService.deleteAqxj(id))
            {
           	    return renderSuccess("删除成功！"); 
            }else{
                return renderError("删除失败！"); 
            }
		} catch (Exception e) {
			e.printStackTrace();
			return renderError("删除失败！"); 
		}
    	
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
	    	User user = getCurrentUser();
	    	List tbmlist = orgzService.selectTreeGridByUser(user);
			ExportExcelUtil.writeExcelToClient(response, exportDto, ExportSetUtil.objectToListMap(tbmlist));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
   
    /**
     * 树列表
     *
     * @return
     */
   @RequestMapping("/treeGrid")
    @ResponseBody
    public Object treeGrid(AqxjXjdfl dto) {
    	User user = getCurrentUser();
    	Map<String, Object> condition = new HashMap<String, Object>();
        if(StringUtils.isNotBlank(dto.getFenleiMc()))condition.put("fenleiMc", dto.getFenleiMc().trim());
        if(StringUtils.isNotBlank(dto.getGcMc()))condition.put("gcMc", dto.getGcMc().trim());
        return aqxjxjdflService.selectTreeGridByUser(user,condition);
    } 
    
    
    /**
     * 获取盾构施工质量基础数据easyUi列表
     * @param pageNo 页码
     * @param pageSize 每页条数
     * @param dto 查询条件dto
     * @return 返回查询结果信息
     */
  /*  @RequestMapping(value = "/treeGrid", method = RequestMethod.POST)
    @ResponseBody
    public Object treeGrid(AqxjXjdfl dto,Integer page, Integer rows, String sort, String order)
    {
       // PageInfo pageInfo = new PageInfo(page, rows, sort, order);
        Map<String, Object> condition = new HashMap<String, Object>();
        if(StringUtils.isNotBlank(dto.getFenleiMc()))condition.put("fenleiMc", dto.getFenleiMc().trim());
        if(StringUtils.isNotBlank(dto.getGcMc()))condition.put("gcMc", dto.getGcMc().trim());
        pageInfo.setCondition(condition);
        aqxjxjdflService.selectDataGrid(pageInfo);
        return pageInfo;
    }*/
    
    
    /**
     * 父类资源树
     *
     * 返回 List<Tree>
     */
    @RequestMapping(value = "/tree", method = RequestMethod.POST)
    @ResponseBody
    public Object tree() {
        return aqxjxjdflService.selectTree();
    }
   
}
