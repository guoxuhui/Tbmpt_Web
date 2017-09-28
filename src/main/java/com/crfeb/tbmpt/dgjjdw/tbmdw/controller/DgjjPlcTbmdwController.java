package com.crfeb.tbmpt.dgjjdw.tbmdw.controller;

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

import com.alibaba.fastjson.JSONObject;
import com.crfeb.tbmpt.commons.base.BaseController;
import com.crfeb.tbmpt.commons.utils.BeanUtils;
import com.crfeb.tbmpt.commons.utils.ExportDto;
import com.crfeb.tbmpt.commons.utils.ExportExcelUtil;
import com.crfeb.tbmpt.commons.utils.ExportPdfUtil;
import com.crfeb.tbmpt.commons.utils.ExportSetUtil;
import com.crfeb.tbmpt.commons.utils.PageInfo;
import com.crfeb.tbmpt.commons.utils.StringUtils;
import com.crfeb.tbmpt.dgjjdw.real.model.dto.DgjjdwPlcRealDto;
import com.crfeb.tbmpt.dgjjdw.tbmdw.model.DgjjPlcTbmdw;
import com.crfeb.tbmpt.dgjjdw.tbmdw.model.dto.DgjjPlcTbmdwDto;
import com.crfeb.tbmpt.dgjjdw.tbmdw.service.IDgjjPlcTbmdwService;
import com.crfeb.tbmpt.gpztcl.base.model.GpztclXyfys;
import com.crfeb.tbmpt.gpztcl.base.model.dto.GpztclXyfysDto;
import com.crfeb.tbmpt.project.model.ProProjectinfo;
import com.crfeb.tbmpt.project.model.ProRProjectSection;
import com.crfeb.tbmpt.project.model.ProRSectionLine;
import com.crfeb.tbmpt.project.service.IProProjectinfoService;
import com.crfeb.tbmpt.project.service.IProRProjectSectionService;
import com.crfeb.tbmpt.project.service.IProRSectionLineService;
import com.crfeb.tbmpt.tbmmg.model.ProTbminfo;
import com.crfeb.tbmpt.tbmmg.service.IProTbminfoService;
import com.crfeb.tbmpt.zsjk.jt.model.ZsJkJtAqZlXx;
import com.crfeb.tbmpt.zsjk.jt.model.dto.ZsJkJtAqZlXxDto;
import com.crfeb.tbmpt.zsjk.jt.service.ZsJkJtAqZlXxService;

@Controller
@RequestMapping(value="/dgjjdw/tbmdw")
public class DgjjPlcTbmdwController extends BaseController{
	@Autowired
    private IDgjjPlcTbmdwService dgjjPlcTbmdwService;
	
	@Autowired
    private IProTbminfoService proTbminfoService;

    @Autowired
    private IProProjectinfoService proProjectinfoService;
    @Autowired
    private IProRProjectSectionService proRProjectSectionService;
    @Autowired
    private IProRSectionLineService proRSectionLineService;
	
	@RequestMapping(value = "", method = RequestMethod.GET)
    public String manager() {
        return "dgjjdw/tbmdw/dgjjPlcTbmdw";
    }
	
	/**
     * 获取盾构掘进对比信息easyUi列表
     * @param pageNo 页码
     * @param pageSize 每页条数
     * @param dto 查询条件dto
     * @return 返回查询结果信息
     */
//    @RequestMapping(value = "/dataGrid", method = RequestMethod.POST)
//    @ResponseBody
//    public Object dataGrid(DgjjPlcTbmdwDto dto,Integer page, Integer rows, String sort, String order)
//    {
//        PageInfo pageInfo = new PageInfo(page, rows, sort, order);
//        Map<String, Object> condition = new HashMap<String, Object>();
//        pageInfo.setCondition(condition);
//      //设置查询条件
//  		if(StringUtils.isNotBlank(dto.getTbmcode())){
//  			condition.put("tbmcode", dto.getTbmcode());
//  		}
//  		if(StringUtils.isNotBlank(dto.getTagname())){
//  			condition.put("tagname", dto.getTagname());
//  		}
//  		if(StringUtils.isNotBlank(dto.getDwname())){
//  			condition.put("dwname", dto.getDwname());
//  		}
//  		if(StringUtils.isNotBlank(dto.getDwlx())){
//  			condition.put("dwlx", dto.getDwlx());
//  		}
//        try {
//        	dgjjPlcTbmdwService.selectDataGrid(pageInfo);
//        } catch (Exception e) {
//           e.printStackTrace();
//        }
//        return pageInfo;
//    }
    
    /**
     * 批量新增对比信息,数据表格
     * @param dto
     * @param page
     * @param rows
     * @param sort
     * @param order
     * @return
     */
    @RequestMapping(value = "/tbmdwDataGrid", method = RequestMethod.POST)
    @ResponseBody
    public Object tbmdwDataGrid(DgjjPlcTbmdwDto dto,Integer page, Integer rows, String sort, String order)
    {
    	List<DgjjPlcTbmdwDto>list=new ArrayList<DgjjPlcTbmdwDto>();
        if(dto.getBztbmname()!=null&&dto.getTbmcode()!=null){
        try {
        	list=dgjjPlcTbmdwService.selecttbmdwDataGrid(dto);
        } catch (Exception e) {
           e.printStackTrace();
        }
        }
        return list;
    }
    /**
     * 首界面,盾构机的数据表格
     * @param dto
     * @param page
     * @param rows
     * @param sort
     * @param order
     * @return
     */
    @RequestMapping(value = "/tbmmgDataGrid", method = RequestMethod.POST)
    @ResponseBody
    public Object tbmmgDataGrid(DgjjPlcTbmdwDto dto,Integer page, Integer rows, String sort, String order)
    {
        PageInfo pageInfo = new PageInfo(page, rows, sort, order);
        Map<String, Object> condition = new HashMap<String, Object>();
        pageInfo.setCondition(condition);
        if (StringUtils.isNotBlank(dto.getBztbmCode())) {
            condition.put("code", dto.getBztbmCode());
        }
        if (StringUtils.isNotBlank(dto.getBztbmname())) {
            condition.put("name", dto.getBztbmname());
        }
        if (dto.getStartTime() != null) {
            condition.put("startTime", dto.getStartTime());
        }
        if (dto.getEndTime() != null) {
            condition.put("endTime", dto.getEndTime());
        }
        try {
        	proTbminfoService.selectDataGrid(pageInfo);
        } catch (Exception e) {
           e.printStackTrace();
        }
        
        return pageInfo;
    }
    /**
     * 判断当前盾构机是否存在对比信息
     * @param tbmid
     * @return
     */
    @RequestMapping(value = "/ifExists", method = RequestMethod.GET)
    @ResponseBody
    public Object ifExists(String tbmid) {
    	List<DgjjPlcTbmdwDto>list=dgjjPlcTbmdwService.selectByTbmid(tbmid);
    	return list;
    }
    
    
    /**
     * 批量对比信息保存
     * @param dto
     * @return
     */
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    @ResponseBody
    public Object save(DgjjPlcTbmdwDto dto){
    	String json=dto.getDetails();
    	
    	//子表信息
		if(!"[]".equals(json) && null != json){
			json = json.replace("&quot;", "\"");
			 List<DgjjPlcTbmdwDto> list = new ArrayList<DgjjPlcTbmdwDto>();  
		     list = JSONObject.parseArray(json, DgjjPlcTbmdwDto.class);
		     List<DgjjPlcTbmdw> addlist = new ArrayList<>();
		     for(DgjjPlcTbmdwDto j:list){
		    	DgjjPlcTbmdw dgjjPlcTbmdw = new DgjjPlcTbmdw();
	    		BeanUtils.copyProperties(j, dgjjPlcTbmdw);
	    		addlist.add(dgjjPlcTbmdw);
		     }
		     Map<String, Object> columnMap = new HashMap<>();
        	 columnMap.put("TBMID", list.get(0).getTbmid());
        	 dgjjPlcTbmdwService.deleteByMap(columnMap);
		     dgjjPlcTbmdwService.insertBatch(addlist);
		}
		     return renderSuccess("保存成功！");
		
    }
    /**
     * 获取PLC点位名称,双击编辑进行模糊查询
     * @param tbmcode
     * @return
     */
    @RequestMapping(value = "/tagname", method = RequestMethod.POST)
    @ResponseBody
    public Object tagname(String q,String tbmcode){
    	if(StringUtils.isEmpty(q)){
    		List<DgjjdwPlcRealDto> list = dgjjPlcTbmdwService.getTagCode(tbmcode);
    		return list;
    	}else{
    		List<DgjjdwPlcRealDto> list = dgjjPlcTbmdwService.getTagName(q,tbmcode);
        	return list;
    	}
    }
    
    /**
     * 查看盾构机的详细信息
     * @param model
     * @param id
     * @return
     */
    @RequestMapping("/showPage")
    public String showPage(Model model, String id) {
     	ProTbminfo t = proTbminfoService.selectById(id);
         model.addAttribute("tbm", t);
         
         Map<String,Object> linemap = new HashMap<String, Object>();
         linemap.put("using_tbm_id", t.getId());
         List<ProRSectionLine> lines = proRSectionLineService.selectByMap(linemap);
         if(lines.size()>=1){
             model.addAttribute("line", lines.get(0));
             
             ProRProjectSection section = proRProjectSectionService.selectById(lines.get(0).getSectionId());
             model.addAttribute("section", section);

             ProProjectinfo pro = proProjectinfoService.selectById(lines.get(0).getProId());
             model.addAttribute("pro", pro);
         }
         
         return "tbmmg/info/show";
     }
}
