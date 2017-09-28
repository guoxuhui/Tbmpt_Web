package com.crfeb.tbmpt.gczl.base.controller;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
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
import com.crfeb.tbmpt.gczl.base.model.dto.GczlYdxjGPZLDDInfoDto;
import com.crfeb.tbmpt.gczl.base.service.GczlYdxjGPZLDDInfoService;

/**
 * <p>盾构施工质量基础数据controlle</p>
 * <p>系统：</p>
 * <p>模块：基础模块</p>
 * <p>日期：2016-11-19</p>
 * @version 1.0
 * @author wangbinbin
 */
@Controller
@RequestMapping(value="/gczl/base/gczlYdxjGPZLDDInfo")
public class GczlYdxjGPZLDDInfoController extends BaseController{

    @Autowired
    private GczlYdxjGPZLDDInfoService gczlYdxjGPZLDDInfoService;

  @RequestMapping(value = "", method = RequestMethod.GET)
    public String manager() {
        return "gczl/base/gczlYdxjGPZLDDInfo";
    }

    /**
     * 获取盾构施工质量基础数据easyUi列表
     * @param pageNo 页码
     * @param pageSize 每页条数
     * @param dto 查询条件dto
     * @return 返回查询结果信息
     */
    @RequestMapping(value = "/dataGrid", method = RequestMethod.POST)
    @ResponseBody
    public Object dataGrid(GczlYdxjGPZLDDInfo dto,Integer page, Integer rows, String sort, String order)
    {
        PageInfo pageInfo = new PageInfo(page, rows, sort, order);
        Map<String, Object> condition = new HashMap<String, Object>();
        if(StringUtils.isNotBlank(dto.getTypeName()))condition.put("typeName", dto.getTypeName().trim());
        if(StringUtils.isNotBlank(dto.getDdName()))condition.put("ddName", dto.getDdName().trim());
        pageInfo.setCondition(condition);
        gczlYdxjGPZLDDInfoService.selectDataGrid(pageInfo);
        return pageInfo;
    }
    
    
    /**
     * 添加盾构施工质量基础数据
     * @param gczlYdxjGPZLDDInfo 要新增的实体信息
     * @return 返回保存成功/失败的提示信息
     */
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public Object add(GczlYdxjGPZLDDInfoDto gczlYdxjGPZLDDInfoDto) {
    	String errmessage = gczlYdxjGPZLDDInfoService.checkClumIfexits(gczlYdxjGPZLDDInfoDto,new String[]{"typeName","ddName"});
    	if(errmessage!=null){
    		log(null, false, errmessage);
    		return renderError(errmessage);
    	}
    	gczlYdxjGPZLDDInfoService.insert(gczlYdxjGPZLDDInfoDto,getCurrentUser());
    	log(null, true, "盾构施工质量基础数据添加成功！");
        return renderSuccess("盾构施工质量基础数据添加成功！");
    }

    /**
     * 删除盾构施工质量基础数据
     * @param ids 要删除的数据的ID集合
     * @return 返回删除成功/失败的提示信息
     */
    @RequestMapping("/delete")
    @ResponseBody
    public Object delete(String ids) {
    	String[] idss = ids.split(",");
    	List<String> idlist = new ArrayList<String>();
    	Collections.addAll(idlist, idss);
    	gczlYdxjGPZLDDInfoService.deleteBatchIds(idlist);
        return renderSuccess("删除成功！");
    }
    
    /**
     * 启用/禁用盾构施工质量基础数据
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
    	gczlYdxjGPZLDDInfoService.qy(idss, ifQy);
    	log(ifQy?"启用":"禁用", false, "操作数据id:"+ids);
        return renderSuccess("操作成功！");
    }

    /**
     * 修改盾构施工质量基础数据
     * @param gczlYdxjGPZLDDInfo 要修改的实体信息
     * @return 返回编辑成功/失败的提示信息
     */
    @RequestMapping("/edit")
    @ResponseBody
    public Object edit(GczlYdxjGPZLDDInfoDto gczlYdxjGPZLDDInfoDto) {
    	String errmessage ="";
    	errmessage = gczlYdxjGPZLDDInfoService.checkClumIfexits(gczlYdxjGPZLDDInfoDto,new String[]{"typeName","ddName"});
    	if(StringUtils.isNotBlank(errmessage)){
    		log(null, false, errmessage);
    		return renderError(errmessage);
    	}
    	errmessage = gczlYdxjGPZLDDInfoService.update(gczlYdxjGPZLDDInfoDto,getCurrentUser());
    	if(StringUtils.isNotBlank(errmessage)){
    		log(null, false, errmessage);
    		return renderError(errmessage);
    	}
    	log("编辑", true, "盾构施工质量基础数据编辑成功！");
        return renderSuccess("盾构施工质量基础数据编辑成功！");
    }

    /**     
    * 数据导出     
    * @param exportDto     
    * @param Dto     
    * @param response     
    */
    @SuppressWarnings({"unchecked", "rawtypes"})
    	@RequestMapping(value="/expXls")
    	public void expXls(ExportDto exportDto,GczlYdxjGPZLDDInfo dto, HttpServletResponse response){
    		try {
    			if(StringUtils.isNotBlank(exportDto.getIds())){
	    			List<String> idsList =new ArrayList<String>();
    				Collections.addAll(idsList, exportDto.getIds().split(ExportDto.SPLIT_STR));
    				List list = gczlYdxjGPZLDDInfoService.selectBatchIds(idsList);
    				ExportExcelUtil.writeExcelToClient(response, exportDto, ExportSetUtil.objectToListMap(list));
	    		}else{
	    	        PageInfo pageInfo = new PageInfo(0, 65530, "id", "desc");
    		        Map<String, Object> condition = new HashMap<String, Object>();
	    	        pageInfo.setCondition(condition);
	    	        gczlYdxjGPZLDDInfoService.selectDataGrid(pageInfo);
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
     public void expPdf(ExportDto exportDto,GczlYdxjGPZLDDInfo dto, HttpServletResponse response){
	     	try {
	     		if(StringUtils.isNotBlank(exportDto.getIds())){
	     			List<String> idsList =new ArrayList<String>();
	     			 Collections.addAll(idsList, exportDto.getIds().split(ExportDto.SPLIT_STR));
	     			 List list = gczlYdxjGPZLDDInfoService.selectBatchIds(idsList);
	     			 ExportPdfUtil.writePdfToClient(response, exportDto, ExportSetUtil.objectToListMap(list));
	     		}else{
	     	        PageInfo pageInfo = new PageInfo(0, 65530, "id", "desc");
	     	        Map<String, Object> condition = new HashMap<String, Object>();
	     	        pageInfo.setCondition(condition);
	     	        gczlYdxjGPZLDDInfoService.selectDataGrid(pageInfo);
	     			ExportPdfUtil.writePdfToClient(response, exportDto, ExportSetUtil.objectToListMap(pageInfo.getRows()));
	     		}
	     	} catch (Exception e) {
	     		e.printStackTrace();
	     	}
     }
     
     /**
      * 获取盾构施工质量列表数据（可用于公共下拉列表调用）
      * @return 返回list列表数据
      */
     @RequestMapping(value = "/gpDdInfoDic", method = RequestMethod.POST)
     @ResponseBody
     public Object findGpzlDdTypdDic(String typeName) {
     	 List<Tree> trees = new ArrayList<Tree>();
     	 Map<String, Object> columnMap = new HashMap<>();
     	 columnMap.put("typeName", typeName);
     	 columnMap.put("ifQy", "启用");
		List<GczlYdxjGPZLDDInfo> types = gczlYdxjGPZLDDInfoService.selectByMap(columnMap );
     	 if(types!=null&&types.size()>0)
     	 for (GczlYdxjGPZLDDInfo type : types) {
     		 Tree tree = new Tree();
     		 tree.setId(type.getDdName());
     		 tree.setText(type.getDdName());
     		 trees.add(tree);
 		}
     	return trees;
     }

}