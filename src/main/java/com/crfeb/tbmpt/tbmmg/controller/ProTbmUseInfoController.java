package com.crfeb.tbmpt.tbmmg.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.crfeb.tbmpt.commons.base.BaseController;
import com.crfeb.tbmpt.commons.utils.DateUtils;
import com.crfeb.tbmpt.commons.utils.ExportDto;
import com.crfeb.tbmpt.commons.utils.ExportExcelUtil;
import com.crfeb.tbmpt.commons.utils.ExportPdfUtil;
import com.crfeb.tbmpt.commons.utils.ExportSetUtil;
import com.crfeb.tbmpt.commons.utils.PageInfo;
import com.crfeb.tbmpt.commons.utils.StringUtils;
import com.crfeb.tbmpt.dgjj.model.DgjjRjjInfo;
import com.crfeb.tbmpt.dgjj.model.dto.DgjjRjjInfoDto;
import com.crfeb.tbmpt.dgjj.service.DgjjRjjInfoService;
import com.crfeb.tbmpt.project.model.ProProjectinfo;
import com.crfeb.tbmpt.project.model.ProRProjectSection;
import com.crfeb.tbmpt.project.model.ProRSectionLine;
import com.crfeb.tbmpt.project.service.IProProjectinfoService;
import com.crfeb.tbmpt.project.service.IProRProjectSectionService;
import com.crfeb.tbmpt.project.service.IProRSectionLineService;
import com.crfeb.tbmpt.sys.model.User;
import com.crfeb.tbmpt.tbmmg.model.ProTbmUseInfo;
import com.crfeb.tbmpt.tbmmg.model.ProTbminfo;
import com.crfeb.tbmpt.tbmmg.model.dto.TbmUseInfoDto;
import com.crfeb.tbmpt.tbmmg.model.dto.TbminfoDto;
import com.crfeb.tbmpt.tbmmg.service.IProTbmUseInfoService;
import com.crfeb.tbmpt.tbmmg.service.IProTbminfoService;
import com.crfeb.tbmpt.zsjk.jt.model.dto.ZsJkJtAqZlXxDto;

/**
 * @author：lzh
 * @date：2017年1月19日
 */
@Controller
@RequestMapping("/tbmmg/useInfo")
public class ProTbmUseInfoController extends BaseController {

	//盾构机使用信息Service
    @Autowired
    private IProTbmUseInfoService proTbmUseInfoService;
    
    //盾构机信息Service
    @Autowired
    private IProTbminfoService iProTbminfoService;
    
    //项目信息Service
    @Autowired
    private IProProjectinfoService proProjectinfoService;
    
    //区间信息Service
    @Autowired
    private IProRProjectSectionService proRProjectSectionService;
    
    //线路信息Service
    @Autowired
    private IProRSectionLineService proRSectionLineService;
    
    //盾构掘进日掘进信息Service
    @Autowired 
    private DgjjRjjInfoService dgjjRjjInfoService;
    
    /**
     * @return
     */
    @RequestMapping(value = "", method = RequestMethod.GET)
    public String manager() {
    	return "tbmmg/useInfo/index";
    }

    /**
     * 查询数据表格
     * @param page 页数
     * @param rows 行数
     * @param sort 分类
     * @param order 排序
     * @return
     */
    @RequestMapping(value = "/dataGrid", method = RequestMethod.POST)
    @ResponseBody
    public Object dataGrid(TbmUseInfoDto vo,Integer page, Integer rows, String sort, String order) {
        PageInfo pageInfo = new PageInfo(page, rows, sort, order);
        Map<String, Object> condition = new HashMap<String, Object>();
        pageInfo.setCondition(condition);
        if (StringUtils.isNotBlank(vo.getTbmName())) {
            condition.put("tbmName", vo.getTbmName().substring(0, vo.getTbmName().length()-1));
        }
        if (StringUtils.isNotBlank(vo.getSzdw())) {
            condition.put("szdw", vo.getSzdw());
        }
        if (StringUtils.isNotBlank(vo.getTbmState())) {
            condition.put("tbmState", vo.getTbmState());
        }
        if (StringUtils.isNotBlank(vo.getPerson())) {
            condition.put("person", vo.getPerson());
        }
        condition.put("sqlPurview", this.sqlPurview("t","GCID"));
        proTbmUseInfoService.selectDataGrid(pageInfo,getCurrentUser());
        return pageInfo;
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public Object add(TbmUseInfoDto tbmUseInfoDto) {
    	String errmessage = "";
        try {
        	proTbmUseInfoService.save(tbmUseInfoDto,getCurrentUser());
          log(null, true, "新增成功!");
        } catch (Exception e) {
          log(null, false, "新增失败!");
          e.printStackTrace();
        }
        return renderSuccess("添加成功！");
    }
   
    @RequestMapping("/edit")
    @ResponseBody
    public Object edit(TbmUseInfoDto tbmUseInfoDto) {
    	String errmessage = "";
        try {
           errmessage = proTbmUseInfoService.update(tbmUseInfoDto,getCurrentUser());
           if(StringUtils.isNotBlank(errmessage)){
             log(null, false, errmessage);
             return renderError(errmessage);
           }
           log(null, true, "编辑成功:"+tbmUseInfoDto.getId());
        } catch (Exception e) {
           log(null, false, "编辑失败!");
           e.printStackTrace();
        }
	      log(null, true, errmessage);
        return renderSuccess("编辑成功!");
    }
    
    @RequestMapping("/delete")
    @ResponseBody
    public Object delete(String ids) {
    	String[] idss = ids.split(",");
    	List<String> idlist = new ArrayList<String>();
    	Collections.addAll(idlist, idss);
    	proTbmUseInfoService.deleteBatchIds(idlist);
    	log(null, true, "删除："+ids);
    	return renderSuccess("删除成功！");
    }
    
    /**
     * 查看盾构机信息页面
     * @param model
     * @param id 盾构机id
     * @return
     */
    @RequestMapping("/showPage")
    public String showPage(Model model, String id) {
    	ProTbminfo t = iProTbminfoService.selectById(id);
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
    

    
    
    /**
     * 数据导出
     * @param exportDto
     * @param Dto
     * @param response
     */
	@SuppressWarnings({"unchecked", "rawtypes"})
	@RequestMapping(value="/expXls")
	public void expXls(ExportDto exportDto,TbmUseInfoDto vo, HttpServletResponse response){
		try {
			if(StringUtils.isNotBlank(exportDto.getIds())){
				List<String> idsList =new ArrayList<String>();
				 Collections.addAll(idsList, exportDto.getIds().split(ExportDto.SPLIT_STR));
				 List list = proTbmUseInfoService.selectBatchIds(idsList);
				 ExportExcelUtil.writeExcelToClient(response, exportDto, ExportSetUtil.objectToListMap(list));
			}else{
		        PageInfo pageInfo = new PageInfo(0, 65530, "id","desc");
		        Map<String, Object> condition = new HashMap<String, Object>();
		        condition.put("sqlPurview", this.sqlPurview("t","GCID"));
		        pageInfo.setCondition(condition);
		        
		        proTbmUseInfoService.selectDataGrid(pageInfo,getCurrentUser());
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
	public void expPdf(ExportDto exportDto, TbmUseInfoDto vo,HttpServletResponse response){
		try {
			if(StringUtils.isNotBlank(exportDto.getIds())){
				List<String> idsList =new ArrayList<String>();
				 Collections.addAll(idsList, exportDto.getIds().split(ExportDto.SPLIT_STR));
				 List list = proTbmUseInfoService.selectBatchIds(idsList);
				 ExportPdfUtil.writePdfToClient(response, exportDto, ExportSetUtil.objectToListMap(list));
			}else{
		        PageInfo pageInfo = new PageInfo(0, 65530, "id","desc");
		        Map<String, Object> condition = new HashMap<String, Object>();
		        condition.put("sqlPurview", this.sqlPurview("t","GCID"));
		        pageInfo.setCondition(condition);
		        
		        proTbmUseInfoService.selectDataGrid(pageInfo,getCurrentUser());
				ExportPdfUtil.writePdfToClient(response, exportDto, ExportSetUtil.objectToListMap(pageInfo.getRows()));
			}
		} catch (Exception e) {
			e.printStackTrace();	
		}
	}
	
	 /**
     * 获取项目信息
     * @return
     */
    @RequestMapping(value = "/getProDic", method = RequestMethod.POST)
    @ResponseBody
    public Object getProDic() {
    	User user = getCurrentUser();
    	List<ProProjectinfo> list = proProjectinfoService.getProjectInfosByUserId(String.valueOf(user.getId()));
    	return list;
    }

    /**
     * 获取项目区间信息
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
    @RequestMapping(value = "/getProXlDic", method = RequestMethod.POST)
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
     * 根据ID查看盾构机调配情况
     */
    @RequestMapping(value = "/tbmId", method = RequestMethod.GET)
    @ResponseBody
    public List<TbmUseInfoDto> selectById(String tbmid){
    		List<TbmUseInfoDto> list =new ArrayList<TbmUseInfoDto>();
    		TbmUseInfoDto tbmUseInfoDto=proTbmUseInfoService.selectBytbmid(tbmid);
    		list.add(tbmUseInfoDto);
    		return list;
    	}
    
}
