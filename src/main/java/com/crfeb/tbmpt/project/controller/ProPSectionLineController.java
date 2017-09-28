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

import com.crfeb.tbmpt.commons.base.BaseController;
import com.crfeb.tbmpt.commons.utils.BeanUtils;
import com.crfeb.tbmpt.commons.utils.DateUtils;
import com.crfeb.tbmpt.commons.utils.ExportDto;
import com.crfeb.tbmpt.commons.utils.ExportExcelUtil;
import com.crfeb.tbmpt.commons.utils.ExportPdfUtil;
import com.crfeb.tbmpt.commons.utils.ExportSetUtil;
import com.crfeb.tbmpt.commons.utils.PageInfo;
import com.crfeb.tbmpt.commons.utils.StringUtils;
import com.crfeb.tbmpt.project.model.ProProjectinfo;
import com.crfeb.tbmpt.project.model.ProRProjectSection;
import com.crfeb.tbmpt.project.model.ProRSectionLine;
import com.crfeb.tbmpt.project.model.vo.ProjectSectionVo;
import com.crfeb.tbmpt.project.model.vo.SectionLineQueryVo;
import com.crfeb.tbmpt.project.model.vo.SectionLineVo;
import com.crfeb.tbmpt.project.service.IProProjectinfoService;
import com.crfeb.tbmpt.project.service.IProRProjectSectionService;
import com.crfeb.tbmpt.project.service.IProRSectionLineService;
import com.crfeb.tbmpt.sys.model.User;
import com.crfeb.tbmpt.tbmmg.model.dto.TbminfoDto;
import com.crfeb.tbmpt.tbmmg.service.IProTbminfoService;

/**
 * 项目线路管理
 * @author：smxg
 * @date：2016-10-10 11:12
 */
@Controller
@RequestMapping("/project/line")
public class ProPSectionLineController extends BaseController {

    @Autowired
    private IProRSectionLineService proRSectionLineService;
    @Autowired
    private IProRProjectSectionService proRProjectSectionService;
    @Autowired
    private IProProjectinfoService proProjectinfoService;
    @Autowired
    private IProTbminfoService proTbminfoService;
    /**
     * @return
     */
    @RequestMapping(value = "", method = RequestMethod.GET)
    public String manager() {
    	return "project/line/index";
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
    public Object dataGrid(SectionLineQueryVo vo,Integer page, Integer rows, String sort, String order) {
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
        if (vo.getStartTime() != null) {
            condition.put("startTime", vo.getStartTime());
        }
        if (vo.getEndTime() != null) {
            condition.put("endTime", vo.getEndTime());
        }
        condition.put("sqlPurview", this.sqlPurview("p","id"));
        proRSectionLineService.selectDataGrid(pageInfo,getCurrentUser());
        return pageInfo;
    }

    /**
     * @return
     */
    @RequestMapping(value = "/addPage", method = RequestMethod.GET)
    public String addPage() {
        return "project/line/add";
    }

    /**
     * @param role
     * @return
     */
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public Object add(ProRSectionLine t) {
    	Map<String,Object> map = new HashMap<String,Object>();
    	
        //名称是否重复
        map.put("line_name", t.getLineName());
        List<ProRSectionLine> pps = proRSectionLineService.selectByMap(map);
        if (pps != null && pps.size()>0) {
            return renderError("线路名称已经存在！");
        }
    	//‘录入人’ 名称
    	t.setEnterperson(getCurrentUser().getName());
    	//录入时间
    	t.setEntertime(DateUtils.getToday());
    	proRSectionLineService.save(t);
        return renderSuccess("添加成功！");
    }

    /**
     * @param id
     * @return
     */
    @RequestMapping("/delete")
    @ResponseBody
    public Object delete(String ids) {
    	List<String> result = new ArrayList<String>();
    	String[] idss = ids.split(",");
    	List<String> idlist = new ArrayList<String>();
    	Collections.addAll(idlist, idss);
    	for(String id:idss){
    		if(proRSectionLineService.del(id) == 0){
    			result.add(id);
    		}
    	}
    	if(result.size()>0){
    		String res = org.apache.commons.lang.StringUtils.join(result, ",");
    		return renderError("以下线路因为已经开始，将无法删除！\n"+res);
    	}else{
    		return renderSuccess("删除成功！");
    	}
    }
    
    /**
     * 线路开始施工
     * @param id
     * @return
     */
    @RequestMapping("/startWork")
    @ResponseBody
    public Object startWork(String id) {
    	proRSectionLineService.startWork(id);
        return renderSuccess("操作成功！");
    }
    
    /**
     * 线路施工结束
     * @param id
     * @return
     */
    @RequestMapping("/endWork")
    @ResponseBody
    public Object endWork(String id) {
    	proRSectionLineService.endWork(id);
        return renderSuccess("操作成功！");
    }

    /**
     * @param model
     * @param id
     * @return
     */
    @RequestMapping("/editPage")
    public String editPage(Model model, String id) {
    	ProRSectionLine t = proRSectionLineService.selectById(id);
        model.addAttribute("model", t);
        return "project/line/edit";
    }

    /**
     */
    @RequestMapping("/edit")
    @ResponseBody
    public Object edit(ProRSectionLine t) {
    	ProRSectionLine o = proRSectionLineService.selectById(t.getId());
    	if(!o.getLineName().equals(t.getLineName())){
	    	Map<String,Object> map = new HashMap<String,Object>();
	    	
	        //名称是否重复
	        map.put("line_name", t.getLineName());
	        List<ProRSectionLine> pps = proRSectionLineService.selectByMap(map);
	        if (pps != null && pps.size()>0) {
	            return renderError("线路名称已经存在！");
	        }
	    	
	    	proRSectionLineService.edit(t);
	        return renderSuccess("编辑成功！");
    	}else{
    		proRSectionLineService.edit(t);
            return renderSuccess("编辑成功！");
    	}
    }
    
    /**
     * 获取项目信息
     * @return
     */
    @RequestMapping(value = "/getProDic")
    @ResponseBody
    public Object getProDic() {
    	User user = getCurrentUser();
    	List<ProProjectinfo> list = proProjectinfoService.getProjectInfosByUserId(String.valueOf(user.getId()));
    	return list;
    }

    /**
     * 获取项目区间信息
     * 注：施工质量巡检管理中引用此方法
     * @return
     */
    @RequestMapping(value = "/getProSectionDic", method = RequestMethod.POST)
    @ResponseBody
    public Object getProSectionDic(String proId) {
    	if(StringUtils.isEmpty(proId)){
    		return renderError("查询失败");
    	}else{
        	List<ProRProjectSection> list = proRProjectSectionService.getSectionByProId(proId);
        	return list;
    	}
    }
    
    /**
     * 获取区间线路信息
     * @return
     */
    @RequestMapping(value = "/getProLineDic", method = RequestMethod.POST)
    @ResponseBody
    public Object getProLineDic(String sectionId) {
    	if(StringUtils.isEmpty(sectionId)){
    		return renderError("查询失败");
    	}else{
        	List<ProRSectionLine> list = proRSectionLineService.getLineBySectionId(sectionId);
        	return list;
    	}
    }
    
    /**
     * 获取项目区间信息
     * @return
     */
    @RequestMapping(value = "/getTbmDic", method = RequestMethod.POST)
    @ResponseBody
    public Object getTbmDic(TbminfoDto vo,Integer page, Integer rows, String sort, String order) {
        PageInfo pageInfo = new PageInfo(page, rows, sort, order);
        Map<String, Object> condition = new HashMap<String, Object>();
        pageInfo.setCondition(condition);
        if (StringUtils.isNotBlank(vo.getName())) {
            condition.put("name", vo.getName());
        }
        if (vo.getStartTime() != null) {
            condition.put("startTime", vo.getStartTime());
        }
        if (vo.getEndTime() != null) {
            condition.put("endTime", vo.getEndTime());
        }
        proTbminfoService.selectDataGrid(pageInfo);
        return pageInfo;
    }
    

    /**
     * 数据导出
     * @param exportDto
     * @param Dto
     * @param response
     */
	@SuppressWarnings({"unchecked", "rawtypes"})
	@RequestMapping(value="/expXls")
	public void expXls(ExportDto exportDto,SectionLineQueryVo vo, HttpServletResponse response){
		try {
			if(StringUtils.isNotBlank(exportDto.getIds())){
				List<String> idsList =new ArrayList<String>();
				Collections.addAll(idsList, exportDto.getIds().split(ExportDto.SPLIT_STR));
				List list = proRSectionLineService.selectBatchIds(idsList);
				List<SectionLineVo> dtolist = new ArrayList<SectionLineVo>();
				 List list2 = new ArrayList();
	   			 if(list !=null && list.size()>0){
	   				 for(int i =0;i<list.size();i++){
	   					SectionLineVo lineVo = new SectionLineVo();
	   					BeanUtils.copyProperties(list.get(i),lineVo);
	   					ProRProjectSection section = proRProjectSectionService.selectById(lineVo.getSectionId());
	   					ProProjectinfo projectinfo= proProjectinfoService.selectById(lineVo.getProId());
	   					lineVo.setProName(projectinfo.getProName());
	   					dtolist.add(lineVo);
	   				 }
	   			 }
	   			 list2 = dtolist;
				ExportExcelUtil.writeExcelToClient(response, exportDto, ExportSetUtil.objectToListMap(list2));
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
		        if (vo.getStartTime() != null) {
		            condition.put("startTime", vo.getStartTime());
		        }
		        if (vo.getEndTime() != null) {
		            condition.put("endTime", vo.getEndTime());
		        }
		        condition.put("sqlPurview", this.sqlPurview("p","id"));
		        proRSectionLineService.selectDataGrid(pageInfo,getCurrentUser());
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
	public void expPdf(ExportDto exportDto,SectionLineQueryVo vo, HttpServletResponse response){
		try {
			if(StringUtils.isNotBlank(exportDto.getIds())){
				List<String> idsList =new ArrayList<String>();
				 Collections.addAll(idsList, exportDto.getIds().split(ExportDto.SPLIT_STR));
				 List list = proRSectionLineService.selectBatchIds(idsList);
				 List<SectionLineVo> dtolist = new ArrayList<SectionLineVo>();
				 List list2 = new ArrayList();
	   			 if(list !=null && list.size()>0){
	   				 for(int i =0;i<list.size();i++){
	   					SectionLineVo lineVo = new SectionLineVo();
	   					BeanUtils.copyProperties(list.get(i),lineVo);
	   					ProRProjectSection section = proRProjectSectionService.selectById(lineVo.getSectionId());
	   					ProProjectinfo projectinfo= proProjectinfoService.selectById(lineVo.getProId());
	   					lineVo.setProName(projectinfo.getProName());
	   					dtolist.add(lineVo);
	   				 }
	   			 }
	   			 list2 = dtolist;
				 ExportPdfUtil.writePdfToClient(response, exportDto, ExportSetUtil.objectToListMap(list2));
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
		        if (vo.getStartTime() != null) {
		            condition.put("startTime", vo.getStartTime());
		        }
		        if (vo.getEndTime() != null) {
		            condition.put("endTime", vo.getEndTime());
		        }
		        condition.put("sqlPurview", this.sqlPurview("p","id"));
		        proRSectionLineService.selectDataGrid(pageInfo,getCurrentUser());
				ExportPdfUtil.writePdfToClient(response, exportDto, ExportSetUtil.objectToListMap(pageInfo.getRows()));
			}
		} catch (Exception e) {
			e.printStackTrace();	
		}
	}
    
}
