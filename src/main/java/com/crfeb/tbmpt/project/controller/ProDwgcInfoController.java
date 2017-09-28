package com.crfeb.tbmpt.project.controller;

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
import com.crfeb.tbmpt.commons.utils.BeanUtils;
import com.crfeb.tbmpt.commons.utils.ExportDto;
import com.crfeb.tbmpt.commons.utils.ExportExcelUtil;
import com.crfeb.tbmpt.commons.utils.ExportPdfUtil;
import com.crfeb.tbmpt.commons.utils.ExportSetUtil;
import com.crfeb.tbmpt.commons.utils.PageInfo;
import com.crfeb.tbmpt.commons.utils.StringUtils;
import com.crfeb.tbmpt.dgjj.model.dto.DgjjBzglDto;
import com.crfeb.tbmpt.project.model.ProDwgcInfo;
import com.crfeb.tbmpt.project.model.ProFbgcInfo;
import com.crfeb.tbmpt.project.model.ProProjectinfo;
import com.crfeb.tbmpt.project.model.vo.ProDwgcVo;
import com.crfeb.tbmpt.project.model.vo.ProFbgcVo;
import com.crfeb.tbmpt.project.service.IProDwgcInfoService;
import com.crfeb.tbmpt.project.service.IProFbgcInfoService;
import com.crfeb.tbmpt.project.service.IProProjectinfoService;

@Controller
@RequestMapping(value="/project/dwgc")
public class ProDwgcInfoController extends BaseController{
	/**
	 * 班组管理数据表格
	 * @param vo
	 * @param page
	 * @param rows
	 * @param sort
	 * @param order
	 * @return
	 */
	@Autowired
	private IProDwgcInfoService proDwgcInfoService;
	
    @Autowired
    private IProProjectinfoService proProjectinfoService;
	
	@Autowired
	private IProFbgcInfoService proFbgcInfoService;
	
	@RequestMapping(value = "", method = RequestMethod.GET)
	public String list() {
		return "project/dwgc/index";
	}
	
	@RequestMapping(value = "/dataGrid", method = RequestMethod.POST)
    @ResponseBody
    public Object dataGrid(ProDwgcVo vo,Integer page, Integer rows, String sort, String order) {
        PageInfo pageInfo = new PageInfo(page, rows, sort, order);
        Map<String, Object> condition = new HashMap<String, Object>();
        pageInfo.setCondition(condition);
        if (StringUtils.isNotBlank(vo.getProId())) {
            condition.put("proId", vo.getProId());
        }
       
        if (vo.getDwgcname() != null) {
            condition.put("dwgcname", vo.getDwgcname());
        }
//        if (vo.getEndTime() != null) {
//            condition.put("endTime", vo.getEndTime());
//        }
        condition.put("sqlPurview", this.sqlPurview("p","id"));
        proDwgcInfoService.selectDataGrid(pageInfo,getCurrentUser());
        return pageInfo;
    }
	
	/**
     * 增加
     */
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public Object add(ProDwgcInfo dwgcInfo) {
    	proDwgcInfoService.insert(dwgcInfo);
    	return renderSuccess("添加成功！");
    }
    /**
     * 修改
     */
    @RequestMapping("/edit")
    @ResponseBody
    public Object edit(ProDwgcInfo dwgcInfo) {
    	proDwgcInfoService.updateSelectiveById(dwgcInfo);
        return renderSuccess("编辑成功！");
    }
    /**
     * 删除
     * @param id
     * @return
     */
    @RequestMapping("/delete")
    @ResponseBody
    public Object delete(String ids) {
    	
    	String[] idss = ids.split(",");
    	List<String> idlist = new ArrayList<String>();
    	Collections.addAll(idlist, idss);
    	
    	for(String id:idss){
    		Map<String, Object> columnMap = new HashMap<>();
       	 	columnMap.put("DWGC_ID", id);
       	 	proFbgcInfoService.deleteByMap(columnMap);
       	 	proDwgcInfoService.deleteById(id);
    	}
    	return renderSuccess("删除成功！");
    
    }
    
    /**
     * 数据导出
     * @param exportDto
     * @param Dto
     * @param response
     */
	@SuppressWarnings({"unchecked", "rawtypes"})
	@RequestMapping(value="/expXls")
	public void expXls(ExportDto exportDto,ProDwgcVo vo, HttpServletResponse response){
		try {
			if(StringUtils.isNotBlank(exportDto.getIds())){
				List<String> idsList =new ArrayList<String>();
				Collections.addAll(idsList, exportDto.getIds().split(ExportDto.SPLIT_STR));
				List list = proDwgcInfoService.selectBatchIds(idsList);
				List<ProDwgcVo> dtolist = new ArrayList<ProDwgcVo>();
				 List list2 = new ArrayList();
	   			 if(list !=null && list.size()>0){
	   				 for(int i =0;i<list.size();i++){
	   					ProDwgcVo dwgcVo = new ProDwgcVo();
	   					BeanUtils.copyProperties(list.get(i),dwgcVo);
	                      
	   					ProProjectinfo projectinfo= proProjectinfoService.selectById(dwgcVo.getProId());
	   					dwgcVo.setProName(projectinfo.getProName());
	   					dtolist.add(dwgcVo);
	   				 }
	   			 }
	   			 list2 = dtolist;
				ExportExcelUtil.writeExcelToClient(response, exportDto, ExportSetUtil.objectToListMap(list2));
			}else{
				PageInfo pageInfo = new PageInfo(0, 65530, "id", "desc");
		        Map<String, Object> condition = new HashMap<String, Object>();
//		        if (StringUtils.isNotBlank(vo.getProId())) {
//		            condition.put("proId", vo.getProId());
//		        }
//		        if (StringUtils.isNotBlank(vo.getQjId())) {
//		            condition.put("qjId", vo.getQjId());
//		        }
//		        if (StringUtils.isNotBlank(vo.getFbgcname())) {
//		            condition.put("fbgcname", vo.getFbgcname());
//		        }
		        condition.put("sqlPurview", this.sqlPurview("p","id"));
		        pageInfo.setCondition(condition);

		        proDwgcInfoService.selectDataGrid(pageInfo,getCurrentUser());
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
	public void expPdf(ExportDto exportDto,ProDwgcVo vo, HttpServletResponse response){
		try {
			if(StringUtils.isNotBlank(exportDto.getIds())){
				List<String> idsList =new ArrayList<String>();
				 Collections.addAll(idsList, exportDto.getIds().split(ExportDto.SPLIT_STR));
				 List list = proDwgcInfoService.selectBatchIds(idsList);
				 List<ProDwgcVo> dtolist = new ArrayList<ProDwgcVo>();
				 List list2 = new ArrayList();
	   			 if(list !=null && list.size()>0){
	   				 for(int i =0;i<list.size();i++){
	   					ProDwgcVo dwgcVo = new ProDwgcVo();
	   					BeanUtils.copyProperties(list.get(i),dwgcVo);
	                      
	   					ProProjectinfo projectinfo= proProjectinfoService.selectById(dwgcVo.getProId());
	   					dwgcVo.setProName(projectinfo.getProName());
	   					dtolist.add(dwgcVo);
	   				 }
	   			 }
	   			 list2 = dtolist;
				 ExportPdfUtil.writePdfToClient(response, exportDto, ExportSetUtil.objectToListMap(list2));
			}else{
				PageInfo pageInfo = new PageInfo(0, 65530, "id", "desc");
		        Map<String, Object> condition = new HashMap<String, Object>();
//		        if (StringUtils.isNotBlank(vo.getProId())) {
//		            condition.put("proId", vo.getProId());
//		        }
//		        if (StringUtils.isNotBlank(vo.getQjId())) {
//		            condition.put("qjId", vo.getQjId());
//		        }
//		        if (StringUtils.isNotBlank(vo.getFbgcname())) {
//		            condition.put("fbgcname", vo.getFbgcname());
//		        }
		        condition.put("sqlPurview", this.sqlPurview("p","id"));
		        pageInfo.setCondition(condition);

		        proDwgcInfoService.selectDataGrid(pageInfo,getCurrentUser());
				ExportPdfUtil.writePdfToClient(response, exportDto, ExportSetUtil.objectToListMap(pageInfo.getRows()));
			}
		} catch (Exception e) {
			e.printStackTrace();	
		}
	}
	
	/**
	 * 方法说明：根据项目id查询项目下的所有单位工程信息
	 * @param projectId 项目id
	 * @return 返回单位工程列表信息
	 * @author:YangYj
	 * @Time: 2017年2月16日 下午3:13:56
	 */
	@RequestMapping(value="/dwgcListByProId")
	@ResponseBody
	public List<ProDwgcInfo> getDwgcListByProjectId(String projectId){
		List<ProDwgcInfo> list = new ArrayList<ProDwgcInfo>();
		if(StringUtils.isBlank(projectId)) projectId = "-1";
		Map<String,Object> columnMap = new HashMap<String,Object>();
		columnMap.put("pro_id", projectId);
		list = this.proDwgcInfoService.selectByMap(columnMap);
		return list;
	}

}
