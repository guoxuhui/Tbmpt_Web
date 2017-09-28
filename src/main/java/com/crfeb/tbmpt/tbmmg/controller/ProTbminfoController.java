package com.crfeb.tbmpt.tbmmg.controller;

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

import com.alibaba.fastjson.JSON;
import com.crfeb.tbmpt.commons.base.BaseController;
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
import com.crfeb.tbmpt.project.service.IProProjectinfoService;
import com.crfeb.tbmpt.project.service.IProRProjectSectionService;
import com.crfeb.tbmpt.project.service.IProRSectionLineService;
import com.crfeb.tbmpt.sddzgl.model.dto.SddzglDzxxDto;
import com.crfeb.tbmpt.tbmmg.model.ProTbmUseInfo;
import com.crfeb.tbmpt.tbmmg.model.ProTbminfo;
import com.crfeb.tbmpt.tbmmg.model.dto.TbminfoDto;
import com.crfeb.tbmpt.tbmmg.service.IProTbmUseInfoService;
import com.crfeb.tbmpt.tbmmg.service.IProTbminfoService;

/**
 * @author：smxg
 * @date：2016-10-10 11:12
 */
@Controller
@RequestMapping("/tbmmg/info")
public class ProTbminfoController extends BaseController {

    @Autowired
    private IProTbminfoService proTbminfoService;
    @Autowired
    private IProProjectinfoService proProjectinfoService;
    @Autowired
    private IProRProjectSectionService proRProjectSectionService;
    @Autowired
    private IProRSectionLineService proRSectionLineService;
    @Autowired
    private IProTbmUseInfoService proTbmUseInfoService;

    /**
     * @return
     */
    @RequestMapping(value = "", method = RequestMethod.GET)
    public String manager() {
    	return "tbmmg/info/index";
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
    public Object dataGrid(TbminfoDto vo,Integer page, Integer rows, String sort, String order) {
        PageInfo pageInfo = new PageInfo(page, rows, sort, order);
        Map<String, Object> condition = new HashMap<String, Object>();
        pageInfo.setCondition(condition);
        if (StringUtils.isNotBlank(vo.getCode())) {
            condition.put("code", vo.getCode());
        }
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
     * @return
     */
    @RequestMapping(value = "/addPage", method = RequestMethod.GET)
    public String addPage() {
        return "tbmmg/info/add";
    }

    /**
     * @param role
     * @return
     */
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public Object add(ProTbminfo t) {
    	
    	Map<String,Object> map = new HashMap<String,Object>();
    	
        //名称是否重复
        map.put("tbm_name", t.getTbmName());
        List<ProTbminfo> pps = proTbminfoService.selectByMap(map);
        if (pps != null && pps.size()>0) {
            return renderError("盾构机名称已经存在！");
        }
    	
    	t.setCreateUser(getCurrentUser().getName());
    	t.setCreateTime(DateUtils.getToday());
    	proTbminfoService.insert(t);
    	log("添加",true, "盾构机添加成功");
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
    	
    	for(String id:idss){
    		Map<String,Object> map = new HashMap<String,Object>();
            map.put("tbmbm", id);
            List<ProTbmUseInfo> pps = proTbmUseInfoService.selectByMap(map);
            if (pps != null && pps.size()>0) {
                return renderError("盾构机已经存在调拨记录，不能删除！");
            }
    	}
    	
    	proTbminfoService.deleteBatchIds(idlist);
        return renderSuccess("删除成功！");
    }

    /**
     * @param model
     * @param id
     * @return
     */
    @RequestMapping("/editPage")
    public String editPage(Model model, String id) {
    	ProTbminfo t = proTbminfoService.selectById(id);
        model.addAttribute("model", t);
        return "tbmmg/info/edit";
    }

    /**
     */
    @RequestMapping("/edit")
    @ResponseBody
    public Object edit(ProTbminfo t) {
    	ProTbminfo o = proTbminfoService.selectById(t.getId());
    	if(!o.getTbmName().equals(t.getTbmName())){
	    	Map<String,Object> map = new HashMap<String,Object>();
	    	
	        //名称是否重复
	        map.put("tbm_name", t.getTbmName());
	        List<ProTbminfo> pps = proTbminfoService.selectByMap(map);
	        if (pps != null && pps.size()>0) {
	            return renderError("盾构机名称已经存在！");
	        }
	    	
	    	t.setUpdateUser(getCurrentUser().getName());
	    	t.setUpdateTime(DateUtils.getToday());
	    	proTbminfoService.updateSelectiveById(t);
	        return renderSuccess("编辑成功！");
    	}else{
    		t.setUpdateUser(getCurrentUser().getName());
	    	t.setUpdateTime(DateUtils.getToday());
	    	proTbminfoService.updateSelectiveById(t);
	        return renderSuccess("编辑成功！");
    	}
    }
    
    /**
     * @param model
     * @param id
     * @return
     */
    @RequestMapping("/showPage")
    public String showPage(Model model, String id) {
    	ProTbminfo t = proTbminfoService.selectById(id);
        model.addAttribute("tbm", JSON.toJSONString(t));
        
        Map<String,Object> linemap = new HashMap<String, Object>();
        linemap.put("using_tbm_id", t.getId());
        List<ProRSectionLine> lines = proRSectionLineService.selectByMap(linemap);
        if(lines.size()>=1){
            model.addAttribute("line", JSON.toJSONString(lines.get(0)));
            
            ProRProjectSection section = proRProjectSectionService.selectById(lines.get(0).getSectionId());
            model.addAttribute("section", JSON.toJSONString(section));

            ProProjectinfo pro = proProjectinfoService.selectById(lines.get(0).getProId());
            model.addAttribute("pro", JSON.toJSONString(pro));
        }else{
        	model.addAttribute("line", JSON.toJSONString(""));
        	model.addAttribute("section", JSON.toJSONString(""));
        	model.addAttribute("pro", JSON.toJSONString(""));
        }
        
        return "tbmmg/info/show";
    }
    
    
    /**
     * 数据导出
     * @param exportDto
     * @param Dto
     * @param response
     */
	@SuppressWarnings({"unchecked", "rawtypes"})
	@RequestMapping(value="/expXls")
	public void expXls(ExportDto exportDto,TbminfoDto vo, HttpServletResponse response){
		try {
			if(StringUtils.isNotBlank(exportDto.getIds())){
				List<String> idsList =new ArrayList<String>();
				 Collections.addAll(idsList, exportDto.getIds().split(ExportDto.SPLIT_STR));
				 List list = proTbminfoService.selectBatchIds(idsList);
				 ExportExcelUtil.writeExcelToClient(response, exportDto, ExportSetUtil.objectToListMap(list));
			}else{
		        PageInfo pageInfo = new PageInfo(0, 65530, "id","desc");
		        Map<String, Object> condition = new HashMap<String, Object>();
		        pageInfo.setCondition(condition);
		        if (StringUtils.isNotBlank(vo.getCode())) {
		            condition.put("code", vo.getCode());
		        }
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
	public void expPdf(ExportDto exportDto, TbminfoDto vo,HttpServletResponse response){
		try {
			if(StringUtils.isNotBlank(exportDto.getIds())){
				List<String> idsList =new ArrayList<String>();
				 Collections.addAll(idsList, exportDto.getIds().split(ExportDto.SPLIT_STR));
				 List list = proTbminfoService.selectBatchIds(idsList);
				 ExportPdfUtil.writePdfToClient(response, exportDto, ExportSetUtil.objectToListMap(list));
			}else{
		        PageInfo pageInfo = new PageInfo(0, 65530, "id","desc");
		        Map<String, Object> condition = new HashMap<String, Object>();
		        pageInfo.setCondition(condition);
		        if (StringUtils.isNotBlank(vo.getCode())) {
		            condition.put("code", vo.getCode());
		        }
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
				ExportPdfUtil.writePdfToClient(response, exportDto, ExportSetUtil.objectToListMap(pageInfo.getRows()));
			}
		} catch (Exception e) {
			e.printStackTrace();	
		}
	}
	
	/**
	 * 获取地层信息
	 */
	@RequestMapping("/dzxxList")
    @ResponseBody
	public List<SddzglDzxxDto> dzxxList (){
		List<SddzglDzxxDto> list=proTbminfoService.selectDzxx();
		return list;
	}
}
