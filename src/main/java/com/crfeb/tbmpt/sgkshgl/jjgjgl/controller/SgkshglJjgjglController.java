package com.crfeb.tbmpt.sgkshgl.jjgjgl.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.crfeb.tbmpt.commons.base.BaseController;
import com.crfeb.tbmpt.commons.utils.PageInfo;
import com.crfeb.tbmpt.commons.utils.StringUtils;
import com.crfeb.tbmpt.project.model.ProProjectinfo;
import com.crfeb.tbmpt.project.model.ProRSectionLine;
import com.crfeb.tbmpt.project.model.vo.SectionLineQueryVo;
import com.crfeb.tbmpt.project.service.IProProjectinfoService;
import com.crfeb.tbmpt.project.service.IProRSectionLineService;
import com.crfeb.tbmpt.sgkshgl.jjgjgl.model.SgkshglJjgjgl;
import com.crfeb.tbmpt.sgkshgl.jjgjgl.model.dto.SgkshglJjgjglDto;
import com.crfeb.tbmpt.sgkshgl.jjgjgl.service.ISgkshglJjgjglService;
import com.crfeb.tbmpt.sys.model.User;
import com.crfeb.tbmpt.sys.service.IOrgzService;

@Controller
@RequestMapping(value="/sgkshgl/jjgjgl")
public class SgkshglJjgjglController extends BaseController{
	
	@Autowired
    private ISgkshglJjgjglService sgkshgJjjgjglService;
	@Autowired
    private IProProjectinfoService proProjectinfoService;
	@Autowired
    private IProRSectionLineService proRSectionLineService;
	
	
	@RequestMapping(value = "", method = RequestMethod.GET)
	public String list() {
		return "sgkshgl/jjgjgl/index";
	}
	
	
    /**
     * 项目资源树
     *
     * @return
     */
    @RequestMapping(value = "/tree", method = RequestMethod.POST)
    @ResponseBody
    public Object treeByUid(String id,String flag) {
    	User user = getCurrentUser();
    		if("admin".equals(user.getLoginName())){
    			return proProjectinfoService.selectTree();
    		}else{
    			return proProjectinfoService.selectTreeByUser(user);
    		}
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
        proRSectionLineService.selectDataGrid(pageInfo,getCurrentUser());
        return pageInfo;
    }
    
    @RequestMapping(value = "/selectGj", method = RequestMethod.GET)
    public String addPage(String lineId,Model model) {
    	List<SgkshglJjgjglDto> list=sgkshgJjjgjglService.selectBylineId(lineId);
    	ProRSectionLine line = proRSectionLineService.selectById(lineId);
    	ProProjectinfo project = proProjectinfoService.selectById(line.getProId());
    	model.addAttribute("projectPoint",JSON.toJSONString(project.getPosition()));
    	model.addAttribute("projectId",JSON.toJSONString(project.getId()));
    	model.addAttribute("linePoint",JSON.toJSON(line.getStartPosition()));
    	if(!list.isEmpty()){
    		model.addAttribute("list",JSON.toJSONString(list));    		
    	}else{
    		model.addAttribute("list",JSON.toJSONString(""));
    	}
    	model.addAttribute("lineId",JSON.toJSONString(lineId));
    	//查找这个区间的其他线路在地图上显示
    	List<ProRSectionLine> sectionList = proRSectionLineService.getLineBySectionId(line.getSectionId());
    	for(ProRSectionLine l:sectionList){
    		if(!line.getId().equals(l.getId())){
    			List<SgkshglJjgjglDto> qtLineList=sgkshgJjjgjglService.selectBylineId(l.getId());
    			if(!qtLineList.isEmpty()){
    	    		model.addAttribute("qtLineList",JSON.toJSONString(qtLineList));    		
    	    	}else{
    	    		model.addAttribute("qtLineList",JSON.toJSONString(""));
    	    	}
    		}
    	}
        return "sgkshgl/jjgjgl/printGjGD";
    }
    
    @RequestMapping(value = "/save", method = RequestMethod.GET)
    @ResponseBody
    public Object save(String points,String lineId) {
//    	 var str = "113.294369,22.973142;113.305867,22.943325;113.310467,22.90498;113.318516,22.868755;113.325415,22.844245;113.348411,22.826125;113.364509,22.812268;113.377157,22.796276;113.392105,22.778151;113.408202,22.763222;113.432349,22.762155;113.461095,22.762155;113.477192,22.767487";
//    	    var strs = str.split(";");
//    	    for(var i=0;i<strs.length;i++){
//    	    	var ps = strs[i].split(",");
//    	    	points.push(new BMap.Point(ps[0],ps[1]));
//    	    }
    	String jg =sgkshgJjjgjglService.saveAll(points,lineId);
    	
    	return renderSuccess("保存成功！");
    }
    
    @RequestMapping(value="/ifhz",method=RequestMethod.POST)
    @ResponseBody
    public Object ifhz(String lineId){
    	List<SgkshglJjgjglDto> list=new ArrayList<SgkshglJjgjglDto>();
    	boolean bool;
    	if(!lineId.isEmpty()){
    		 list = sgkshgJjjgjglService.selectBylineId(lineId);
    	}
    	if(!list.isEmpty()){
    		return bool=true;
    	}else{
    		return bool=false;
    	}
    }
}
