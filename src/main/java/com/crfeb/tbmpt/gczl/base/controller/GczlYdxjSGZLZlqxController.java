package com.crfeb.tbmpt.gczl.base.controller;

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

import com.crfeb.tbmpt.commons.base.BaseController;
import com.crfeb.tbmpt.commons.utils.ExportDto;
import com.crfeb.tbmpt.commons.utils.ExportExcelUtil;
import com.crfeb.tbmpt.commons.utils.ExportPdfUtil;
import com.crfeb.tbmpt.commons.utils.ExportSetUtil;
import com.crfeb.tbmpt.commons.utils.PageInfo;
import com.crfeb.tbmpt.commons.utils.StringUtils;
import com.crfeb.tbmpt.gczl.base.model.GczlYdxjSGZLSgnr;
import com.crfeb.tbmpt.gczl.base.model.GczlYdxjSGZLZlqx;
import com.crfeb.tbmpt.gczl.base.model.dto.GczlYdxjSGZLZlqxDto;
import com.crfeb.tbmpt.gczl.base.service.GczlYdxjSGZLSgnrService;
import com.crfeb.tbmpt.gczl.base.service.GczlYdxjSGZLZlqxService;
import com.crfeb.tbmpt.sys.model.User;

/**
 * <p>结构施工质量基础数据-质量缺陷controlle</p>
 * <p>系统：</p>
 * <p>模块：基础模块</p>
 * <p>日期：2016-11-24</p>
 * @version 1.0
 * @author wangbinbin
 */
@Controller
@RequestMapping(value="/gczl/base/gczlYdxjSGZLZlqx")
public class GczlYdxjSGZLZlqxController extends BaseController{
	private static final Logger LOGGER = LogManager.getLogger(GczlYdxjSGZLZlqxController.class);

    @Autowired
    private GczlYdxjSGZLZlqxService gczlYdxjSGZLZlqxService;
    
    @Autowired
    private GczlYdxjSGZLSgnrService gczlYdxjSGZLSgnrService;

  @RequestMapping(value = "", method = RequestMethod.GET)
    public String manager() {
        return "gczl/base/gczlYdxjSGZLZlqx";
    }

    /**
     * 获取结构施工质量基础数据-质量缺陷easyUi列表
     * @param pageNo 页码
     * @param pageSize 每页条数
     * @param dto 查询条件dto
     * @return 返回查询结果信息
     */
    @RequestMapping(value = "/dataGrid", method = RequestMethod.POST)
    @ResponseBody
    public Object dataGrid(GczlYdxjSGZLZlqxDto dto,Integer page, Integer rows, String sort, String order)
    {
        PageInfo pageInfo = new PageInfo(page, rows, sort, order);
        Map<String, Object> condition = new HashMap<String, Object>();
        condition.put("sgNr", dto.getSgNr());
        pageInfo.setCondition(condition);
        gczlYdxjSGZLZlqxService.selectDataGrid(pageInfo);
        return pageInfo;
    }

    /**
     * 添加结构施工质量基础数据-质量缺陷
     * @param gczlYdxjGPZLDDInfo 要新增的实体信息
     * @return 返回保存成功/失败的提示信息
     */
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public Object add(GczlYdxjSGZLZlqxDto gczlYdxjSGZLZlqx) {
    	User user = this.getCurrentUser();
    	String errmessage = gczlYdxjSGZLZlqxService.checkClumIfexits(gczlYdxjSGZLZlqx,new String[]{"sgNr","zlQx"});
    	if(StringUtils.isNotBlank(errmessage)) return renderError(errmessage);
    	errmessage = gczlYdxjSGZLZlqxService.insert(gczlYdxjSGZLZlqx,user);
    	if(StringUtils.isNotBlank(errmessage)) return renderError(errmessage);
    	LOGGER.info("质量缺陷添加成功");
    	this.log(null, true, "用户"+user.getName()+"成功添加了质量缺陷记录"+gczlYdxjSGZLZlqx.getZlQx());
        return renderSuccess("添加成功！");
    }

    /**
     * 删除结构施工质量基础数据-质量缺陷
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
    	gczlYdxjSGZLZlqxService.deleteBatchIds(idlist);
    	LOGGER.info("质量缺陷删除成功");
    	this.log(null, true, "用户"+user.getName()+"成功删除了质量缺陷记录ids:"+ids);
        return renderSuccess("删除成功！");
    }

    /**
     * 修改结构施工质量基础数据-质量缺陷
     * @param gczlYdxjGPZLDDInfo 要修改的实体信息
     * @return 返回编辑成功/失败的提示信息
     */
    @RequestMapping("/edit")
    @ResponseBody
    public Object edit(GczlYdxjSGZLZlqxDto gczlYdxjSGZLZlqx) {
    	User user = this.getCurrentUser();
    	String errmessage = gczlYdxjSGZLZlqxService.checkClumIfexits(gczlYdxjSGZLZlqx,new String[]{"sgNr","zlQx"});
    	if(StringUtils.isNotBlank(errmessage)) return renderError(errmessage);
    	errmessage = gczlYdxjSGZLZlqxService.update(gczlYdxjSGZLZlqx,this.getCurrentUser());
     	if(StringUtils.isNotBlank(errmessage)) return renderError(errmessage);
    	LOGGER.info("质量缺陷更新成功");
    	this.log(null, true, "用户"+user.getName()+"成功更新了质量缺陷记录:"+gczlYdxjSGZLZlqx.getZlQx());
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
    	public void expXls(ExportDto exportDto,GczlYdxjSGZLZlqx dto, HttpServletResponse response){
    		try {
    			if(StringUtils.isNotBlank(exportDto.getIds())){
	    			List<String> idsList =new ArrayList<String>();
    				Collections.addAll(idsList, exportDto.getIds().split(ExportDto.SPLIT_STR));
    				List list = gczlYdxjSGZLZlqxService.selectBatchIds(idsList);
    				ExportExcelUtil.writeExcelToClient(response, exportDto, ExportSetUtil.objectToListMap(list));
	    		}else{
	    	        PageInfo pageInfo = new PageInfo(0, 65530, "id", "desc");
    		        Map<String, Object> condition = new HashMap<String, Object>();
    		        if(null !=dto && StringUtils.isNotBlank(dto.getSgNr())){
     		        	  condition.put("sgNr", dto.getSgNr());
     		            }
	    	        pageInfo.setCondition(condition);
	    	        gczlYdxjSGZLZlqxService.selectDataGrid(pageInfo);
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
     public void expPdf(ExportDto exportDto,GczlYdxjSGZLZlqx dto, HttpServletResponse response){
	     	try {
	     		if(StringUtils.isNotBlank(exportDto.getIds())){
	     			List<String> idsList =new ArrayList<String>();
	     			 Collections.addAll(idsList, exportDto.getIds().split(ExportDto.SPLIT_STR));
	     			 List list = gczlYdxjSGZLZlqxService.selectBatchIds(idsList);
	     			 ExportPdfUtil.writePdfToClient(response, exportDto, ExportSetUtil.objectToListMap(list));
	     		}else{
	     	        PageInfo pageInfo = new PageInfo(0, 65530, "id", "desc");
	     	        Map<String, Object> condition = new HashMap<String, Object>();
	     	        if(null !=dto && StringUtils.isNotBlank(dto.getSgNr())){
	   		        	  condition.put("sgNr", dto.getSgNr());
	   		         }
	     	        pageInfo.setCondition(condition);
	     	        gczlYdxjSGZLZlqxService.selectDataGrid(pageInfo);
	     			ExportPdfUtil.writePdfToClient(response, exportDto, ExportSetUtil.objectToListMap(pageInfo.getRows()));
	     		}
	     	} catch (Exception e) {
	     		e.printStackTrace();
	     	}
     }
     
     /**
      * 启用/禁用具体位置信息
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
     	gczlYdxjSGZLZlqxService.qy(idss, ifQy);
         return renderSuccess("操作成功!");
     }
     
     /**
      * 构建具体位置下拉框
      * @return
      */
      @RequestMapping("/createZlqxSelect")
      @ResponseBody
      public List<GczlYdxjSGZLZlqx> createZlqxSelect(HttpServletRequest request){
		
		String sgNrId = (String) request.getParameter("sgNrId");//施工内容id
		String ifQy = (String) request.getParameter("ifQy");//是否启用 1:启用;0:禁用
		String sgNr =(String) request.getParameter("sgNr");//施工内容名称
		

		if (StringUtils.isNotBlank(ifQy)) {
			if ("1".equals(ifQy)) {
				ifQy = "启用";
			} else if ("0".equals(ifQy)) {
				ifQy = "禁用";
			}
		} else {
			ifQy = "启用";
		}
		GczlYdxjSGZLZlqx entity = new GczlYdxjSGZLZlqx();
		if (StringUtils.isNotBlank(sgNr)) {
			GczlYdxjSGZLSgnr sgnrEntity = new GczlYdxjSGZLSgnr();
			GczlYdxjSGZLSgnr tempSgnr = new GczlYdxjSGZLSgnr();
			sgnrEntity.setSgNr(sgNr);
			tempSgnr = this.gczlYdxjSGZLSgnrService.selectOne(sgnrEntity);
			if (null != tempSgnr && StringUtils.isNotBlank(tempSgnr.getId())) {
				entity.setSgNr(tempSgnr.getId());
			} else {
				entity.setSgNr("-100");
			}
		} else if (StringUtils.isNotBlank(sgNrId)) {
			entity.setSgNr(sgNrId);
		}
		entity.setIfQy(ifQy);
		return this.gczlYdxjSGZLZlqxService.selectList(entity);
      }

}