package com.crfeb.tbmpt.aqsc.base.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletResponse;
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
import com.crfeb.tbmpt.project.model.ProProjectinfo;
import com.crfeb.tbmpt.aqsc.base.model.HuiyiJilu;
import com.crfeb.tbmpt.aqsc.base.model.dto.HuiyiJiluDto;
import com.crfeb.tbmpt.aqsc.base.service.HuiyiJiluService;

/**
 * <p>培训记录controlle</p>
 * <p>系统：安全生产管理</p>
 * <p>模块：基础信息</p>
 * <p>日期：2017-02-21</p>
 * @version 1.0
 * @author wangbinbin
 */
@Controller
@RequestMapping(value="/aqsc/base/huiyiJilu")
public class HuiyiJiluController extends BaseController{

    @Autowired
    private HuiyiJiluService huiyiJiluService;

  @RequestMapping(value = "", method = RequestMethod.GET)
    public String manager() {
        return "aqsc/base/huiyiJilu";
    }

    /**
     * 获取培训记录easyUi列表
     * @param pageNo 页码
     * @param pageSize 每页条数
     * @param dto 查询条件dto
     * @return 返回查询结果信息
     */
    @RequestMapping(value = "/dataGrid", method = RequestMethod.POST)
    @ResponseBody
    public Object dataGrid(HuiyiJiluDto dto,Integer page, Integer rows, String sort, String order)
    {
        PageInfo pageInfo = new PageInfo(page, rows, sort, order);
        Map<String, Object> condition = new HashMap<String, Object>();
        condition.put("hydate", dto.getHydate());
        condition.put("name", dto.getName());
        condition.put("adress", dto.getAdress());
        condition.put("content", dto.getContent());
        condition.put("starDay", dto.getStarDay());
        condition.put("endDay", dto.getEndDay());
        if(!this.getUserId().equals("1"))condition.put("sqlPurview", this.sqlPurview(null,"xmBh"));
        pageInfo.setCondition(condition);
        try {
           huiyiJiluService.selectDataGrid(pageInfo);
        } catch (Exception e) {
           e.printStackTrace();
        }
        return pageInfo;
    }

    /**
     * 添加培训记录
     * @param gczlYdxjGPZLDDInfo 要新增的实体信息
     * @return 返回保存成功/失败的提示信息
     */
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public Object add(HuiyiJiluDto huiyiJiluDto) {
    	String errmessage = "";
        try {
    	errmessage = huiyiJiluService.checkClumIfexits(huiyiJiluDto,new String[]{"hydate","name"});
	    log(null, false, errmessage);
    	if(errmessage!=null) return renderError(errmessage);
          huiyiJiluService.save(huiyiJiluDto,getCurrentUser());
          log(null, true, "新增成功!");
        } catch (Exception e) {
          log(null, false, "新增失败!");
          e.printStackTrace();
        }
        return renderSuccess("添加成功！");
    }

    /**
     * 删除培训记录
     * @param ids 要删除的数据的ID集合
     * @return 返回删除成功/失败的提示信息
     */
    @RequestMapping("/delete")
    @ResponseBody
    public Object delete(String ids) {
    	String[] idss = ids.split(",");
    	List<String> idlist = new ArrayList<String>();
    	Collections.addAll(idlist, idss);
    	huiyiJiluService.deleteBatchIds(idlist);
    	log(null, true, "删除："+ids);
    	return renderSuccess("删除成功！");
    }

    /**
     * 修改培训记录
     * @param gczlYdxjGPZLDDInfo 要修改的实体信息
     * @return 返回编辑成功/失败的提示信息
     */
    @RequestMapping("/edit")
    @ResponseBody
    public Object edit(HuiyiJiluDto huiyiJiluDto) {
    	String errmessage = "";
        try {
    	errmessage = huiyiJiluService.checkClumIfexits(huiyiJiluDto,new String[]{"hydate","name"});
    	if(errmessage!=null) return renderError(errmessage);
           errmessage = huiyiJiluService.update(huiyiJiluDto,getCurrentUser());
           if(StringUtils.isNotBlank(errmessage)){
             log(null, false, errmessage);
             return renderError(errmessage);
           }
           log(null, true, "编辑成功:"+huiyiJiluDto.getId());
        } catch (Exception e) {
           log(null, false, "编辑失败!");
           e.printStackTrace();
        }
	      log(null, true, errmessage);
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
    	public void expXls(ExportDto exportDto,HuiyiJiluDto dto, HttpServletResponse response){
    		try {
    			if(StringUtils.isNotBlank(exportDto.getIds())){
	    			List<String> idsList =new ArrayList<String>();
    				Collections.addAll(idsList, exportDto.getIds().split(ExportDto.SPLIT_STR));
    				List list = huiyiJiluService.selectBatchIds(idsList);
    				ExportExcelUtil.writeExcelToClient(response, exportDto, ExportSetUtil.objectToListMap(list));
	    		}else{
	    	        PageInfo pageInfo = new PageInfo(0, 65530, "id", "desc");
    		        Map<String, Object> condition = new HashMap<String, Object>();
    		        condition.put("hydate", dto.getHydate());
	     	        condition.put("name", dto.getName());
	     	        condition.put("adress", dto.getAdress());
	     	        condition.put("content", dto.getContent());
	     	        condition.put("starDay", dto.getStarDay());
	     	        condition.put("endDay", dto.getEndDay());
	     	        if(!this.getUserId().equals("1"))condition.put("sqlPurview", this.sqlPurview(null,"xmBh"));
	    	        pageInfo.setCondition(condition);
	    	        huiyiJiluService.selectDataGrid(pageInfo);
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
     public void expPdf(ExportDto exportDto,HuiyiJiluDto dto, HttpServletResponse response){
	     	try {
	     		if(StringUtils.isNotBlank(exportDto.getIds())){
	     			List<String> idsList =new ArrayList<String>();
	     			 Collections.addAll(idsList, exportDto.getIds().split(ExportDto.SPLIT_STR));
	     			 List list = huiyiJiluService.selectBatchIds(idsList);
	     			 ExportPdfUtil.writePdfToClient(response, exportDto, ExportSetUtil.objectToListMap(list));
	     		}else{
	     	        PageInfo pageInfo = new PageInfo(0, 65530, "id", "desc");
	     	        Map<String, Object> condition = new HashMap<String, Object>();
	     	        condition.put("hydate", dto.getHydate());
	     	        condition.put("name", dto.getName());
	     	        condition.put("adress", dto.getAdress());
	     	        condition.put("content", dto.getContent());
	     	        condition.put("starDay", dto.getStarDay());
	     	        condition.put("endDay", dto.getEndDay());
	     	        if(!this.getUserId().equals("1"))condition.put("sqlPurview", this.sqlPurview(null,"xmBh"));
	     	        pageInfo.setCondition(condition);
	     	        huiyiJiluService.selectDataGrid(pageInfo);
	     			ExportPdfUtil.writePdfToClient(response, exportDto, ExportSetUtil.objectToListMap(pageInfo.getRows()));
	     		}
	     	} catch (Exception e) {
	     		e.printStackTrace();
	     	}
     }

}