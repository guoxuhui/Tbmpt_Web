package com.crfeb.tbmpt.gczl.base.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.crfeb.tbmpt.commons.base.BaseController;
import com.crfeb.tbmpt.commons.utils.ExportDto;
import com.crfeb.tbmpt.commons.utils.ExportExcelUtil;
import com.crfeb.tbmpt.commons.utils.ExportPdfUtil;
import com.crfeb.tbmpt.commons.utils.ExportSetUtil;
import com.crfeb.tbmpt.commons.utils.PageInfo;
import com.crfeb.tbmpt.commons.utils.StringUtils;
import com.crfeb.tbmpt.gczl.base.model.GczlYdxjSGZLSgnr;
import com.crfeb.tbmpt.gczl.base.model.dto.GczlYdxjSGZLSgnrDto;
import com.crfeb.tbmpt.gczl.base.service.GczlYdxjSGZLSgnrService;
import com.crfeb.tbmpt.sys.model.User;

/**
 * <p>施工内容管理controlle</p>
 * <p>系统：</p>
 * <p>模块：基础模块</p>
 * <p>日期：2016-11-23</p>
 * @version 1.0
 * @author wangbinbin
 */
@Controller
@RequestMapping(value="/gczl/base/gczlYdxjSGZLSgnr")
public class GczlYdxjSGZLSgnrController extends BaseController{
	private static final Logger LOGGER = LogManager.getLogger(GczlYdxjSGZLSgnrController.class);

    @Autowired
    private GczlYdxjSGZLSgnrService gczlYdxjSGZLSgnrService;
    
  @RequestMapping(value = "", method = RequestMethod.GET)
    public String manager() {
        return "gczl/base/gczlYdxjSGZLSgnr";
    }

    /**
     * 获取施工内容管理easyUi列表
     * @param pageNo 页码
     * @param pageSize 每页条数
     * @param dto 查询条件dto
     * @return 返回查询结果信息
     */
    @RequestMapping(value = "/dataGrid", method = RequestMethod.POST)
    @ResponseBody
    public Object dataGrid(GczlYdxjSGZLSgnrDto dto,Integer page, Integer rows, String sort, String order)
    {
        PageInfo pageInfo = new PageInfo(page, rows, sort, order);
        Map<String, Object> condition = new HashMap<String, Object>();
        condition.put("sgNr", dto.getSgNr());
        pageInfo.setCondition(condition);
        gczlYdxjSGZLSgnrService.selectDataGrid(pageInfo);
        return pageInfo;
    }

    /**
     * 添加施工内容管理
     * @param gczlYdxjGPZLDDInfo 要新增的实体信息
     * @return 返回保存成功/失败的提示信息
     */
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public Object add(GczlYdxjSGZLSgnrDto gczlYdxjSGZLSgnr) {
    	String errmessage = gczlYdxjSGZLSgnrService.checkClumIfexits(gczlYdxjSGZLSgnr,new String[]{"sgNr"});
    	User user = this.getCurrentUser();
    	if(errmessage!=null) return renderError(errmessage);
    	int i = gczlYdxjSGZLSgnrService.insert(gczlYdxjSGZLSgnr,user);
    	if(i>0){
    		LOGGER.info("结构施工质量基础信息添加数据成功。");
    		this.log(null, true, "用户"+user.getName()+"添加记录成功。");
    		return renderSuccess("添加成功！");
    	}else{
    		LOGGER.error("结构施工质量基础信息添加失败。");
    		this.log(null, false, "用户"+user.getName()+"添加记录失败。");
    		return renderError("添加失败。");
    	}
    }

    /**
     * 删除施工内容管理
     * @param ids 要删除的数据的ID集合
     * @return 返回删除成功/失败的提示信息
     */
    @RequestMapping("/delete")
    @ResponseBody
    public Object delete(String ids) {
    	User user = this.getCurrentUser();
    	String[] idss = ids.split(",");
    	List<String> idlist = new ArrayList<String>();
    	Collections.addAll(idlist, idss);
//    	gczlYdxjSGZLSgnrService.deleteBatchIds(idlist);
    	gczlYdxjSGZLSgnrService.deleteByIds(idlist);
    	LOGGER.info("结构施工质量基础信息删除数据成功。");
    	this.log(null, true, "用户"+user.getName()+"删除记录成功。ids:"+ids);
        return renderSuccess("删除成功！");
    }

    /**
     * 修改施工内容管理
     * @param gczlYdxjGPZLDDInfo 要修改的实体信息
     * @return 返回编辑成功/失败的提示信息
     */
    @RequestMapping("/edit")
    @ResponseBody
    public Object edit(GczlYdxjSGZLSgnrDto gczlYdxjSGZLSgnr) {
    	User user = this.getCurrentUser();
    	GczlYdxjSGZLSgnr oldEntity = this.gczlYdxjSGZLSgnrService.selectById(gczlYdxjSGZLSgnr.getId());
    	if(null == oldEntity || StringUtils.isBlank(oldEntity.getId())){
    		return renderError("数据不存在,请刷新重试!");
    	}
    	String errmessage = gczlYdxjSGZLSgnrService.checkClumIfexits(gczlYdxjSGZLSgnr,new String[]{"sgNr"});
    	if(StringUtils.isNotBlank(errmessage)) return renderError(errmessage);//校验失败,数据不可更新
    	errmessage = gczlYdxjSGZLSgnrService.update(gczlYdxjSGZLSgnr,this.getCurrentUser());
    	if(StringUtils.isNotBlank(errmessage)) return renderError(errmessage);//更新失败
    	LOGGER.info("结构施工质量基础信息修改数据成功。");
    	this.log(null, true, "用户"+user.getName()+"修改记录成功。id:"+gczlYdxjSGZLSgnr.getId());
        return renderSuccess("编辑成功!");
    }

    /**     
    * 数据导出     
    * @param exportDto     
    * @param Dto     
    * @param response     
    */
    @SuppressWarnings({"unchecked", "rawtypes"})
    	@RequestMapping(value="/expXls")
    	public void expXls(ExportDto exportDto,GczlYdxjSGZLSgnr dto, HttpServletResponse response){
    		try {
    			if(StringUtils.isNotBlank(exportDto.getIds())){
	    			List<String> idsList =new ArrayList<String>();
    				Collections.addAll(idsList, exportDto.getIds().split(ExportDto.SPLIT_STR));
    				List list = gczlYdxjSGZLSgnrService.selectDtoBatchIds(idsList);
    				ExportExcelUtil.writeExcelToClient(response, exportDto, ExportSetUtil.objectToListMap(list));
	    		}else{
	    	        PageInfo pageInfo = new PageInfo(0, 65530, "id", "desc");
    		        Map<String, Object> condition = new HashMap<String, Object>();
	    	        pageInfo.setCondition(condition);
	    	        gczlYdxjSGZLSgnrService.selectDataGrid(pageInfo);
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
     public void expPdf(ExportDto exportDto,GczlYdxjSGZLSgnr dto, HttpServletResponse response){
	     	try {
	     		if(StringUtils.isNotBlank(exportDto.getIds())){
	     			List<String> idsList =new ArrayList<String>();
	     			 Collections.addAll(idsList, exportDto.getIds().split(ExportDto.SPLIT_STR));
	     			
	     			  List  list = gczlYdxjSGZLSgnrService.selectDtoBatchIds(idsList);
	     			 ExportPdfUtil.writePdfToClient(response, exportDto, ExportSetUtil.objectToListMap(list));
	     		}else{
	     	        PageInfo pageInfo = new PageInfo(0, 65530, "id", "desc");
	     	        Map<String, Object> condition = new HashMap<String, Object>();
	     	        pageInfo.setCondition(condition);
	     	        gczlYdxjSGZLSgnrService.selectDataGrid(pageInfo);
	     			ExportPdfUtil.writePdfToClient(response, exportDto, ExportSetUtil.objectToListMap(pageInfo.getRows()));
	     		}
	     	} catch (Exception e) {
	     		e.printStackTrace();
	     	}
     }
     
     /**
      * 启用/禁用施工内容数据
      * @param ids 要禁用的数据ID
      * @param state 1：启用      0：禁用
      * @return
      */
     @RequestMapping("/ifQy")
     @ResponseBody
     public Object ifQy(String ids,String state) {
     	String[] idss = ids.split(",");
     	boolean ifQy = true;
     	if(state.equals("0"))ifQy = false;
     	gczlYdxjSGZLSgnrService.qy(idss, ifQy);
         return renderSuccess("操作成功!");
     }
    /**
     * 构建施工内容下拉框
     * @return
     */
     @RequestMapping("/createSgNrSelect")
     @ResponseBody
     public List<GczlYdxjSGZLSgnr> createSgNrSelect(){
    	 return this.gczlYdxjSGZLSgnrService.selectBySta("1");//参数值为1表示构建的下拉框中只有状态为启用的信息
     }
     

}