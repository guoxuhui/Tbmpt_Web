package com.crfeb.tbmpt.project.controller;

import java.util.ArrayList;
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

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.crfeb.tbmpt.commons.base.BaseController;
import com.crfeb.tbmpt.commons.utils.BeanUtils;
import com.crfeb.tbmpt.commons.utils.DateUtils;
import com.crfeb.tbmpt.commons.utils.ExportDto;
import com.crfeb.tbmpt.commons.utils.ExportExcelUtil;
import com.crfeb.tbmpt.commons.utils.ExportPdfUtil;
import com.crfeb.tbmpt.commons.utils.ExportSetUtil;
import com.crfeb.tbmpt.commons.utils.PageInfo;
import com.crfeb.tbmpt.commons.utils.ProNoUtils;
import com.crfeb.tbmpt.commons.utils.StringUtils;
import com.crfeb.tbmpt.project.model.ProProjectinfo;
import com.crfeb.tbmpt.project.model.ProRProjectSection;
import com.crfeb.tbmpt.project.model.ProRSectionLine;
import com.crfeb.tbmpt.project.model.vo.ProDwgcVo;
import com.crfeb.tbmpt.project.model.vo.ProjectSectionQueryVo;
import com.crfeb.tbmpt.project.model.vo.ProjectSectionVo;
import com.crfeb.tbmpt.project.service.IProProjectinfoService;
import com.crfeb.tbmpt.project.service.IProRProjectSectionService;
import com.crfeb.tbmpt.project.service.IProRSectionLineService;
import com.crfeb.tbmpt.sys.model.User;

/**
 * 项目区间管理
 * @author：smxg
 * @date：2016-10-10 11:12
 */
@Controller
@RequestMapping("/project/section")
public class ProRProjectSectionController extends BaseController {

	@Autowired
    private IProRSectionLineService proRSectionLineService;
    @Autowired
    private IProRProjectSectionService proRProjectSectionService;
    @Autowired
    private IProProjectinfoService proProjectinfoService;
    /**
     * @return
     */
    @RequestMapping(value = "", method = RequestMethod.GET)
    public String manager() {
        return "project/section/index";
    }

    /**
     * @param page
     * @param rows
     * @param sort
     * @param order
     * @return
     */
    @RequestMapping(value = "/dataGrid", method = RequestMethod.POST)
    @ResponseBody
    public Object dataGrid(ProjectSectionQueryVo vo,Integer page, Integer rows, String sort, String order) {
        PageInfo pageInfo = new PageInfo(page, rows, sort, order);
        Map<String, Object> condition = new HashMap<String, Object>();
        if (StringUtils.isNotBlank(vo.getProId())) {
            condition.put("proId", vo.getProId());
        }
        if (StringUtils.isNotBlank(vo.getSectionName())) {
            condition.put("sectionName", vo.getSectionName());
        }
        if (vo.getStartTime() != null) {
            condition.put("startTime", vo.getStartTime());
        }
        if (vo.getEndTime() != null) {
            condition.put("endTime", vo.getEndTime());
        }
        condition.put("sqlPurview", this.sqlPurview("s","pro_id"));
        pageInfo.setCondition(condition);

        proRProjectSectionService.selectDataGrid(pageInfo,getCurrentUser());
        return pageInfo;
    }

    /**
     * @return
     */
    @RequestMapping(value = "/addPage", method = RequestMethod.GET)
    public String addPage() {
        return "project/section/add";
    }

    /**
     * @param role
     * @return
     */
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public Object add(ProRProjectSection t) {
    	Map<String,Object> map = new HashMap<String,Object>();
    	
        //名称是否重复
        map.put("section_name", t.getSectionName());
        List<ProRProjectSection> pps = proRProjectSectionService.selectByMap(map);
        if (pps != null && pps.size()>0) {
            return renderError("区间名称已经存在！");
        }
    	t.setEnterperson(getCurrentUser().getName());
    	t.setEntertime(DateUtils.getToday());
    	proRProjectSectionService.save(t);
        return renderSuccess("添加成功！");
    }

    /**
     * @param id
     * @return
     */
    @RequestMapping("/delete")
    @ResponseBody
    public Object delete(String ids) {
    	
    	String[] idss = ids.split(",");
    	List<String> idlist = new ArrayList<String>();
    	Collections.addAll(idlist, idss);
    	
    	EntityWrapper<ProRSectionLine> ew = new EntityWrapper<ProRSectionLine>();
    	ew.where("1=1").in("SECTION_ID", idlist);
    	List<ProRSectionLine> list = proRSectionLineService.selectList(ew);
    	if(list != null && list.size() > 0){
    		return renderError("操作区间存在线路信息，无法删除！");
    	}
    	Boolean falt = proRProjectSectionService.deleteBatchIds(idlist);
    	if(falt){
    		return renderSuccess("删除成功！");
    	}else{
    		return renderSuccess("删除异常！");
    	}
    }

    /**
     * @param model
     * @param id
     * @return
     */
    @RequestMapping("/editPage")
    public String editPage(Model model, String id) {
    	ProRProjectSection t = proRProjectSectionService.selectById(id);
        model.addAttribute("model", t);
        return "project/section/edit";
    }

    /**
     */
    @RequestMapping("/edit")
    @ResponseBody
    public Object edit(ProRProjectSection t) {
    	ProRProjectSection o = proRProjectSectionService.selectById(t.getId());
    	if(!o.getSectionName().equals(t.getSectionName())){
        	Map<String,Object> map = new HashMap<String,Object>();
        	
            //名称是否重复
            map.put("section_name", t.getSectionName());
            List<ProRProjectSection> pps = proRProjectSectionService.selectByMap(map);
            if (pps != null && pps.size()>0) {
                return renderError("区间名称已经存在！");
            }
    		proRProjectSectionService.updateSelectiveById(t);
    		return renderSuccess("编辑成功！");
    	}else{
    		proRProjectSectionService.updateSelectiveById(t);
            return renderSuccess("编辑成功！");
    	}
    	
    }
    
    /**
     * 获取项目信息
     * @return
     */
    @RequestMapping(value = "/getProDic")
    @ResponseBody
    public Object getDic() {
    	User user = getCurrentUser();
    	List<ProProjectinfo> list = proProjectinfoService.getProjectInfosByUserId(String.valueOf(user.getId()));
    	return list;
    }
    
    
    /**
     * 获取项目信息
     * @return
     */
    @RequestMapping(value = "/getAllProDic")
    @ResponseBody
    public Object getAllDic() {
    	List<ProProjectinfo> list = proProjectinfoService.getAllProjectInfos();
    	return list;
    }
    
    
    /**
     * 数据导出
     * @param exportDto
     * @param Dto
     * @param response
     */
	@SuppressWarnings({"unchecked", "rawtypes"})
	@RequestMapping(value="/expXls")
	public void expXls(ExportDto exportDto,ProjectSectionQueryVo vo, HttpServletResponse response){
		try {
			if(StringUtils.isNotBlank(exportDto.getIds())){
				List<String> idsList =new ArrayList<String>();
				Collections.addAll(idsList, exportDto.getIds().split(ExportDto.SPLIT_STR));
				List list = proRProjectSectionService.selectBatchIds(idsList);
				List<ProjectSectionVo> dtolist = new ArrayList<ProjectSectionVo>();
				 List list2 = new ArrayList();
	   			 if(list !=null && list.size()>0){
	   				 for(int i =0;i<list.size();i++){
	   					ProjectSectionVo sectionVo = new ProjectSectionVo();
	   					BeanUtils.copyProperties(list.get(i),sectionVo);
	                      
	   					ProProjectinfo projectinfo= proProjectinfoService.selectById(sectionVo.getProId());
	   					sectionVo.setProName(projectinfo.getProName());
	   					dtolist.add(sectionVo);
	   				 }
	   			 }
	   			 list2 = dtolist;
				ExportExcelUtil.writeExcelToClient(response, exportDto, ExportSetUtil.objectToListMap(list2));
			}else{
				PageInfo pageInfo = new PageInfo(0, 65530, "id", "desc");
		        Map<String, Object> condition = new HashMap<String, Object>();
		        if (StringUtils.isNotBlank(vo.getProId())) {
		            condition.put("proId", vo.getProId());
		        }
		        if (StringUtils.isNotBlank(vo.getSectionName())) {
		            condition.put("sectionName", vo.getSectionName());
		        }
		        if (vo.getStartTime() != null) {
		            condition.put("startTime", vo.getStartTime());
		        }
		        if (vo.getEndTime() != null) {
		            condition.put("endTime", vo.getEndTime());
		        }
		        condition.put("sqlPurview", this.sqlPurview("s","pro_id"));
		        pageInfo.setCondition(condition);

		        proRProjectSectionService.selectDataGrid(pageInfo,getCurrentUser());
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
	public void expPdf(ExportDto exportDto,ProjectSectionQueryVo vo, HttpServletResponse response){
		try {
			if(StringUtils.isNotBlank(exportDto.getIds())){
				List<String> idsList =new ArrayList<String>();
				 Collections.addAll(idsList, exportDto.getIds().split(ExportDto.SPLIT_STR));
				 List list = proRProjectSectionService.selectBatchIds(idsList);
				 List<ProjectSectionVo> dtolist = new ArrayList<ProjectSectionVo>();
				 List list2 = new ArrayList();
	   			 if(list !=null && list.size()>0){
	   				 for(int i =0;i<list.size();i++){
	   					ProjectSectionVo sectionVo = new ProjectSectionVo();
	   					BeanUtils.copyProperties(list.get(i),sectionVo);
	                      
	   					ProProjectinfo projectinfo= proProjectinfoService.selectById(sectionVo.getProId());
	   					sectionVo.setProName(projectinfo.getProName());
	   					dtolist.add(sectionVo);
	   				 }
	   			 }
	   			 list2 = dtolist;
				 ExportPdfUtil.writePdfToClient(response, exportDto, ExportSetUtil.objectToListMap(list2));
			}else{
				PageInfo pageInfo = new PageInfo(0, 65530, "id", "desc");
		        Map<String, Object> condition = new HashMap<String, Object>();
		        if (StringUtils.isNotBlank(vo.getProId())) {
		            condition.put("proId", vo.getProId());
		        }
		        if (StringUtils.isNotBlank(vo.getSectionName())) {
		            condition.put("sectionName", vo.getSectionName());
		        }
		        if (vo.getStartTime() != null) {
		            condition.put("startTime", vo.getStartTime());
		        }
		        if (vo.getEndTime() != null) {
		            condition.put("endTime", vo.getEndTime());
		        }
		        condition.put("sqlPurview", this.sqlPurview("s","pro_id"));
		        pageInfo.setCondition(condition);

		        proRProjectSectionService.selectDataGrid(pageInfo,getCurrentUser());
				ExportPdfUtil.writePdfToClient(response, exportDto, ExportSetUtil.objectToListMap(pageInfo.getRows()));
			}
		} catch (Exception e) {
			e.printStackTrace();	
		}
	}

}
