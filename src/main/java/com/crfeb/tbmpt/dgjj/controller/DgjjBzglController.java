package com.crfeb.tbmpt.dgjj.controller;

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

import com.baomidou.mybatisplus.plugins.Page;
import com.crfeb.tbmpt.commons.base.BaseController;
import com.crfeb.tbmpt.commons.utils.BeanUtils;
import com.crfeb.tbmpt.commons.utils.CommUtils;
import com.crfeb.tbmpt.commons.utils.ExportDto;
import com.crfeb.tbmpt.commons.utils.ExportExcelUtil;
import com.crfeb.tbmpt.commons.utils.ExportPdfUtil;
import com.crfeb.tbmpt.commons.utils.ExportSetUtil;
import com.crfeb.tbmpt.commons.utils.PageInfo;
import com.crfeb.tbmpt.commons.utils.StringUtils;
import com.crfeb.tbmpt.dgjj.model.DgjjBzgl;
import com.crfeb.tbmpt.dgjj.model.dto.DgjjBzglDto;
import com.crfeb.tbmpt.dgjj.model.dto.DgjjBzglEmpDto;
import com.crfeb.tbmpt.dgjj.service.DgjjBzglEmpService;
import com.crfeb.tbmpt.dgjj.service.DgjjBzglService;
import com.crfeb.tbmpt.project.model.ProRSectionLine;
import com.crfeb.tbmpt.project.model.vo.SectionLineQueryVo;
import com.crfeb.tbmpt.project.service.IProFbgcInfoService;

@Controller
@RequestMapping(value="/dgjj/bzgl")
public class DgjjBzglController extends BaseController{
	@Autowired
	private DgjjBzglService bzglService;
	
	@Autowired
	private DgjjBzglEmpService bzglEmpService;
	
	@RequestMapping(value = "", method = RequestMethod.GET)
    public String manager() {
        return "dgjj/bzgl/dgjjbzgl";
    }
	/**
	 * 班组管理数据表格
	 * @param vo
	 * @param page
	 * @param rows
	 * @param sort
	 * @param order
	 * @return
	 */
	@RequestMapping(value = "/dataGrid", method = RequestMethod.POST)
    @ResponseBody
    public Object dataGrid(DgjjBzglDto vo,Integer page, Integer rows, String sort, String order) {
        PageInfo pageInfo = new PageInfo(page, rows, sort, order);
        Map<String, Object> condition = new HashMap<String, Object>();
        pageInfo.setCondition(condition);
        if (StringUtils.isNotBlank(vo.getProId())) {
            condition.put("proId", vo.getProId());
        }
        if (StringUtils.isNotBlank(vo.getSectionId())) {
            condition.put("sectionId", vo.getSectionId());
        }
        if (StringUtils.isNotBlank(vo.getLineName())) {
            condition.put("lineName", vo.getLineName());
        }
//        if (vo.getStartTime() != null) {
//            condition.put("startTime", vo.getStartTime());
//        }
//        if (vo.getEndTime() != null) {
//            condition.put("endTime", vo.getEndTime());
//        }
        if (StringUtils.isNotBlank(vo.getBzname())) {
            condition.put("bzName", vo.getBzname());
        }
        condition.put("sqlPurview", this.sqlPurview("p","id"));
        bzglService.selectDataGrid(pageInfo,getCurrentUser());
        return pageInfo;
    }
	
	/**
	 * 新增班组
	 * @param dto
	 * @return
	 */
	@RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public Object add(DgjjBzglDto dto){
		dto.setId(CommUtils.getUUID());
		dto.setFid(dto.getLineId());
		DgjjBzgl dg=new DgjjBzgl();
		BeanUtils.copyProperties(dto, dg);
		bzglService.insert(dg);
		return renderSuccess("保存成功！");
	}
	/**
	 * 删除班组
	 * @param ids
	 * @return
	 */
	@RequestMapping("/delete")
    @ResponseBody
    public Object delete(String ids){
		
    	String[] idss = ids.split(",");
    	List<String> idlist = new ArrayList<String>();
    	Collections.addAll(idlist, idss);
    	for(String id:idss){
    		Map<String, Object> columnMap = new HashMap<>();
       	 	columnMap.put("FID", id);
       	 	bzglEmpService.deleteByMap(columnMap);
    		bzglService.deleteById(id);
    	}
    	return renderSuccess("删除成功！");
    }
	/**
	 * 编辑班组
	 * @param dto
	 * @return
	 */
	@RequestMapping("/edit")
    @ResponseBody
    public Object edit(DgjjBzglDto dto) {
		dto.setFid(dto.getLineId());
		DgjjBzgl dg=new DgjjBzgl();
		BeanUtils.copyProperties(dto, dg);
		bzglService.updateById(dg);
        return renderSuccess("编辑成功！");
    }
	/**
	 * 导出excel
	 * @param exportDto
	 * @param vo
	 * @param response
	 */
	@SuppressWarnings({"unchecked", "rawtypes"})
 	@RequestMapping(value="/expXls")
    public void expXls(ExportDto exportDto,DgjjBzglDto vo, HttpServletResponse response){
		try {
			if(StringUtils.isNotBlank(exportDto.getIds())){
				List<String> idsList =new ArrayList<String>();
				 Collections.addAll(idsList, exportDto.getIds().split(ExportDto.SPLIT_STR));
				 List list = bzglService.selectBatchIds(idsList);
				 ExportPdfUtil.writePdfToClient(response, exportDto, ExportSetUtil.objectToListMap(list));
			}else{
				PageInfo pageInfo = new PageInfo(0, 65530, "id", "desc");
				Map<String, Object> condition = new HashMap<String, Object>();
		        pageInfo.setCondition(condition);
		        if (StringUtils.isNotBlank(vo.getProId())) {
		            condition.put("proId", vo.getProId());
		        }
		        if (StringUtils.isNotBlank(vo.getSectionId())) {
		            condition.put("sectionId", vo.getSectionId());
		        }
		        if (StringUtils.isNotBlank(vo.getLineName())) {
		            condition.put("lineName", vo.getLineName());
		        }
//		        if (vo.getStartTime() != null) {
//		            condition.put("startTime", vo.getStartTime());
//		        }
//		        if (vo.getEndTime() != null) {
//		            condition.put("endTime", vo.getEndTime());
//		        }
		        condition.put("sqlPurview", this.sqlPurview("p","id"));
		        bzglService.selectDataGrid(pageInfo,getCurrentUser());
		        ExportExcelUtil.writeExcelToClient(response, exportDto, ExportSetUtil.objectToListMap(pageInfo.getRows()));
			}
		} catch (Exception e) {
			e.printStackTrace();	
		}
	}
	/**
	 * 导出pdf
	 * @param exportDto
	 * @param vo
	 * @param response
	 */
	@SuppressWarnings({"unchecked", "rawtypes"})
 	@RequestMapping(value="/expPdf")
    public void expPdf(ExportDto exportDto,DgjjBzglDto vo, HttpServletResponse response){
		try {
			if(StringUtils.isNotBlank(exportDto.getIds())){
				List<String> idsList =new ArrayList<String>();
				 Collections.addAll(idsList, exportDto.getIds().split(ExportDto.SPLIT_STR));
				 List list = bzglService.selectBatchIds(idsList);
				 ExportPdfUtil.writePdfToClient(response, exportDto, ExportSetUtil.objectToListMap(list));
			}else{
				PageInfo pageInfo = new PageInfo(0, 65530, "id", "desc");
				Map<String, Object> condition = new HashMap<String, Object>();
		        pageInfo.setCondition(condition);
		        if (StringUtils.isNotBlank(vo.getProId())) {
		            condition.put("proId", vo.getProId());
		        }
		        if (StringUtils.isNotBlank(vo.getSectionId())) {
		            condition.put("sectionId", vo.getSectionId());
		        }
		        if (StringUtils.isNotBlank(vo.getLineName())) {
		            condition.put("lineName", vo.getLineName());
		        }
//		        if (vo.getStartTime() != null) {
//		            condition.put("startTime", vo.getStartTime());
//		        }
//		        if (vo.getEndTime() != null) {
//		            condition.put("endTime", vo.getEndTime());
//		        }
		        condition.put("sqlPurview", this.sqlPurview("p","id"));
		        bzglService.selectDataGrid(pageInfo,getCurrentUser());
		        ExportPdfUtil.writePdfToClient(response, exportDto, ExportSetUtil.objectToListMap(pageInfo.getRows()));
			}
		} catch (Exception e) {
			e.printStackTrace();	
		}
	}
}
