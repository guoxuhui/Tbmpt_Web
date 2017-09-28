package com.crfeb.tbmpt.dmcjjc.bg.controller;

import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.util.IOUtils;
import com.crfeb.tbmpt.commons.base.BaseController;
import com.crfeb.tbmpt.commons.calc.CalcBhl;
import com.crfeb.tbmpt.commons.utils.CommUtils;
import com.crfeb.tbmpt.commons.utils.ExcelUtils;
import com.crfeb.tbmpt.commons.utils.ExportDto;
import com.crfeb.tbmpt.commons.utils.ExportExcelUtil;
import com.crfeb.tbmpt.commons.utils.ExportSetUtil;
import com.crfeb.tbmpt.commons.utils.PageInfo;
import com.crfeb.tbmpt.commons.utils.StringUtils;
import com.crfeb.tbmpt.dmcjjc.bg.model.JcbgDetail;
import com.crfeb.tbmpt.dmcjjc.bg.model.JcbgInfo;
import com.crfeb.tbmpt.dmcjjc.bg.model.JcbgsbInfo;
import com.crfeb.tbmpt.dmcjjc.bg.model.vo.JcbgInfoDto;
import com.crfeb.tbmpt.dmcjjc.bg.service.IDmcjJcbgDetailService;
import com.crfeb.tbmpt.dmcjjc.bg.service.IDmcjJcbgInfoService;
import com.crfeb.tbmpt.dmcjjc.bg.service.IDmcjJcbgsbInfoService;
import com.crfeb.tbmpt.dmcjjc.info.model.JcInfo;
import com.crfeb.tbmpt.dmcjjc.info.model.JcPoint;
import com.crfeb.tbmpt.dmcjjc.info.model.vo.DmcjJcInfoDetailsDto;
import com.crfeb.tbmpt.dmcjjc.info.model.vo.DmcjJcInfoDto;
import com.crfeb.tbmpt.dmcjjc.info.service.IDmcjJcInfoService;
import com.crfeb.tbmpt.dmcjjc.info.service.IDmcjJcPointService;
import com.crfeb.tbmpt.project.model.ProProjectinfo;
import com.crfeb.tbmpt.project.service.IProProjectinfoService;

@Controller
@RequestMapping("/dmcjjc/bg")
public class DmcjJcbgInfoAction extends BaseController {
	private static final Logger LOGGER = LogManager
			.getLogger(DmcjJcbgInfoAction.class);
	
    @Autowired
    private IDmcjJcbgInfoService bgInfoService;
    @Autowired
    private IDmcjJcbgsbInfoService bgSbService;
    @Autowired
    private IDmcjJcbgDetailService bgDetailService;
    @Autowired
    private IDmcjJcInfoService jcInfoService;
    @Autowired
    private IDmcjJcPointService pointService;
    @Autowired
    private IProProjectinfoService proProjectinfoService;
    
    /**
	 * 跳转到选择导入日常监测页面
	 */
    @RequestMapping(value = "/addPage", method = RequestMethod.GET)
    public String addPage() {
        return "dmcjjc/jcbg/import";
    }
    
    /**
	 * 跳转到编辑页面
     * @param model 
	 */
    @RequestMapping(value = "/editPage", method = RequestMethod.GET)
    public String editPage(Model model,String jcTime,String id) {
    	JcbgInfo cond = new JcbgInfo();
		cond.setId(id);
		// 查询主表
		cond = bgInfoService.selectOne(cond);
		model.addAttribute("jc", cond);
        return "dmcjjc/jcbg/edit";
    }

    @RequestMapping(value = "", method = RequestMethod.GET)
    public String manager() {
        return "dmcjjc/jcbg/list";
    }
    
    @RequestMapping(value = "/list", method = RequestMethod.POST)
	@ResponseBody
	public Object list(JcbgInfoDto dto,Integer page, Integer rows, String sort, String order) {
		PageInfo pageInfo = new PageInfo(page, rows, sort, order);
		Map<String, Object> condition = new HashMap<String, Object>();
		//设置查询条件
			if(null != dto.getGcbh()){
				condition.put("gcbh", dto.getGcbh());
			}
			if(null != dto.getBeginTime()){
				condition.put("beginTime", dto.getBeginTime());
			}
			if(null != dto.getEndTime()){
				condition.put("endTime", dto.getEndTime());
			}
			if(null != dto.getIfqr() && !"".equals(dto.getIfqr().trim())){
				condition.put("ifqr", dto.getIfqr());
			}
			if(null != dto.getIfsb() && !"".equals(dto.getIfsb().trim())){
				condition.put("ifsb", dto.getIfsb());
			}
		String userId = String.valueOf(getCurrentUser().getId());
		condition.put("uid", userId);
		
		//根据用户id查出用户有哪些项目
		List<ProProjectinfo> proj = proProjectinfoService.getProjectInfosByUserId(userId); 
		if(null == proj)
			return pageInfo;
		
		List<String> ls = new ArrayList<String>();
		for(ProProjectinfo info:proj){
			ls.add("'"+info.getId()+"'");
		}
		String instr = StringUtils.ListToString(ls, ",");
		
		//用查出的所有项目id做为查询条件
		condition.put("ins",instr);
		
		pageInfo.setCondition(condition);
		bgInfoService.selectDataGrid(pageInfo);
		return pageInfo;
	}
    
    /**
	 * 导出选中数据到excel
     * @throws Exception 
	 */
	@RequestMapping("/expBg")
	@ResponseBody
	public void expBgsb(String[] ids, HttpServletResponse response) throws Exception {
		String userId = String.valueOf(getCurrentUser().getId());
		ProProjectinfo proj = proProjectinfoService.getProjectInfoByUserId(userId);
		if(null == proj){
			throw new Exception("无工程，无法导出数据");
		}
		// 生成excel并下载
		HSSFWorkbook wb = bgInfoService.expData(Arrays.asList(ids),proj.getId());
		String filename = "监测报告数据.xls";
		String iso_filename = ExcelUtils.parseGBK(filename);
		response.setContentType("text/plain");
		response.setHeader("Content-Disposition","attachment;filename=" + iso_filename);
		
		OutputStream ouputStream = null; 
		try {
			ouputStream = response.getOutputStream();
			wb.write(ouputStream);
			ouputStream.flush();
		} catch (IOException e) {
			LOGGER.error("expBgsb error",e);
		} finally{
			IOUtils.close(ouputStream);
		}
	}
	
	/**
     * 数据导出
     * @param exportDto
     * @param Dto
     * @param response
     */
	@SuppressWarnings({"unchecked"})
	@RequestMapping(value="/expXls")
	public void expXls(ExportDto exportDto,JcbgInfoDto dto, HttpServletResponse response){
		try {
			PageInfo pageInfo = new PageInfo(0, 65530, "id","desc");
			Map<String, Object> condition = new HashMap<String, Object>();
			if(StringUtils.isNotBlank(exportDto.getIds())){
				List<String> idsList =new ArrayList<String>();
				for(String info:exportDto.getIds().split(ExportDto.SPLIT_STR)){
					idsList.add("'"+info+"'");
				}
				
				String idsstr = StringUtils.ListToString(idsList, ",");
				condition.put("ids", idsstr);
				pageInfo.setCondition(condition);
				bgInfoService.selectDataGrid(pageInfo);
				ExportExcelUtil.writeExcelToClient(response, exportDto, ExportSetUtil.objectToListMap(pageInfo.getRows()));
			}else{
				//设置查询条件
					if(null != dto.getGcbh()){
						condition.put("gcbh", dto.getGcbh());
					}
					if(null != dto.getBeginTime()){
						condition.put("beginTime", dto.getBeginTime());
					}
					if(null != dto.getEndTime()){
						condition.put("endTime", dto.getEndTime());
					}
					if(null != dto.getIfqr() && !"".equals(dto.getIfqr().trim())){
						condition.put("ifqr", dto.getIfqr());
					}
					if(null != dto.getIfsb() && !"".equals(dto.getIfsb().trim())){
						condition.put("ifsb", dto.getIfsb());
					}
				String userId = String.valueOf(getCurrentUser().getId());
				condition.put("uid", userId);
				
				//根据用户id查出用户有哪些项目
				List<ProProjectinfo> proj = proProjectinfoService.getProjectInfosByUserId(userId); 
				if(null == proj){
					ExportExcelUtil.writeExcelToClient(response, exportDto, ExportSetUtil.objectToListMap(pageInfo.getRows()));
					return;
				}
				
				List<String> ls = new ArrayList<String>();
				for(ProProjectinfo info:proj){
					ls.add("'"+info.getId()+"'");
				}
				String instr = StringUtils.ListToString(ls, ",");
				
				//用查出的所有项目id做为查询条件
				condition.put("ins",instr);
				
				pageInfo.setCondition(condition);
				bgInfoService.selectDataGrid(pageInfo);
				ExportExcelUtil.writeExcelToClient(response, exportDto, ExportSetUtil.objectToListMap(pageInfo.getRows()));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
    
    /**
	 * 删除
	 */
	@RequestMapping(value = "/del",method = RequestMethod.POST)
	@ResponseBody
	public Object del(String[] ids) {
		String tempIds = "";
		try {
			for (String id : ids) {
				tempIds +=","+id;
				JcbgInfo cond = new JcbgInfo();
				cond.setId(id);
				//删子表
				bgInfoService.deleteDetails(id);
				//删主表
				bgInfoService.deleteSelective(cond);
			}
		} catch (Exception e) {
			this.log("删除", false, "操作失败.ids:"+tempIds);
			LOGGER.error("DmcjJcbgInfoAction del失败", e);
			return renderError("删除操作：失败。");
		}
		this.log("删除", true, "操作成功.ids:"+tempIds);
		return renderSuccess("删除操作：成功。");
	}
	
	/**
	 * 撤销确认
	 */
	@RequestMapping(value = "/jy",method = RequestMethod.POST)
	@ResponseBody
	public Object jy(String[] ids) {
		String tempIds  = "";
		try {
			for (String id : ids) {
				tempIds +=","+id;
				JcbgInfo bg = new JcbgInfo();
				bg.setId(id);
				bg = bgInfoService.selectOne(bg);
				String jcTime = bg.getJcTime();
				String gcbh = bg.getGcbh();
				JcbgsbInfo sb = new JcbgsbInfo();
				sb.setGcbh(gcbh);
				sb.setJcTime(jcTime);
				int i = bgSbService.selectCount(sb);
				if(i > 0){
					return renderError("选择的监测报告已上报，操作失败。");
				}
			}
			
			for (String id : ids) {
				bgInfoService.jy(id);
			}
		} catch (Exception e) {
			this.log("撤销确认", false, "操作失败.ids:"+tempIds);
			LOGGER.error("DmcjJcInfoAction jy失败", e);
			return renderError("撤销确认：失败。");
		}
		this.log("撤销确认", true, "操作成功.ids:"+tempIds);
		return renderSuccess("撤销确认：成功。");
	}

	/**
	 * 确认
	 */
	@RequestMapping(value = "/qy",method = RequestMethod.POST)
	@ResponseBody
	public Object qy(String[] ids) {
		String tempIds ="";
		try {
			for (String id : ids) {
				tempIds +=","+id;
				bgInfoService.qy(id);
			}
		} catch (Exception e) {
			this.log("确认", false, "操作失败.ids:"+tempIds);
			LOGGER.error("DmcjJcInfoAction qy失败", e);
			return renderError("确认操作：失败。");
		}
		this.log("确认", true, "操作成功.ids:"+tempIds);
		return renderSuccess("确认操作：成功。");
	}
	
	/**
   	 * 跳转到详情页面
   	 */
   @RequestMapping(value = "/showPage", method = RequestMethod.GET)
   public String showPage(String id,String jcTime,Model model) {
	   JcbgInfo cond = new JcbgInfo();
	   cond.setId(id);
	   //查询主表
	   cond = bgInfoService.selectOne(cond);
	   model.addAttribute("jc", cond);
       return "dmcjjc/jcbg/show";
   }
   
   /**
    * 根据主表id查询主表和子表记录
    */
   @RequestMapping(value = "/getData", method = RequestMethod.GET)
   public String getData(String id,String jcTime,Model model) {
	   String userId = String.valueOf(getCurrentUser().getId());
		ProProjectinfo proj = proProjectinfoService.getProjectInfoByUserId(userId);
	   JcbgInfo cond = new JcbgInfo();
	   cond.setId(id);
	   //查询主表
	   cond = bgInfoService.selectOne(cond);
	   //查询子表
	   List<DmcjJcInfoDetailsDto> details = bgInfoService.selectDetailsWithPoint(id,jcTime,proj.getId());
	   model.addAttribute("jc", cond);
	   model.addAttribute("jcdetails", details);
	   return "dmcjjc/jcbg/SbDmcjJcbg";
   }
   
   /**
    * 上报数据，通过IDmcjJcbgsbInfoService.save
    * 把数据保存到jcbgsb_info和jcbgsb_details表中 
    */
	@RequestMapping(value = "/sbDmcjJcbg",method = RequestMethod.POST)
	@ResponseBody
	public Object sbDmcjJcbg(DmcjJcInfoDto dto) {
		try {
			//检查在同一工程相同监测时间的条件下监测报告上报信息是否已存在，
			//如果存在则提示“相同时间的监测报告上报信息已存在，不能重复上报。”；    
			String gcbh = dto.getGcbh();
			String jctime = dto.getJcTime();
			JcbgsbInfo cond = new JcbgsbInfo();
			cond.setGcbh(gcbh);
			cond.setJcTime(jctime);
			int count = bgSbService.selectCount(cond);
			if(count > 0){
				return renderError("相同时间的监测报告上报信息已存在，不能重复上报。");
			}
			//：1、验证监测报告上报信息子表是否有数据，如果没有数据，则提示“无监测报告明细，上报失败。”；
			String json = dto.getDetails();
			if("[]".equals(json) || "".equals(json) || null == json){
				return renderError("无监测报告明细，上报失败。");
			}
			//2、验证监测报告上报信息子表监测点点位编号是否存在，如果不存在，则提示“监测报告明细中监测点不存在，上报失败。”；
			
			bgSbService.save(dto);
			//上报完成后再更新	为已上报
			bgInfoService.updateShangbaoStatus(dto.getId());
		} catch (Exception e) {
			LOGGER.error("DmcjJcbgInfoAction update失败", e);
			return renderError("上报操作：失败。");
		}
		return renderSuccess("上报操作：成功。");
	}
	
	@RequestMapping("/temp")  
	@ResponseBody
	public Object temp(String jcTime){
		String userId = String.valueOf(getCurrentUser().getId());
	    ProProjectinfo proj = proProjectinfoService.getProjectInfoByUserId(userId); 
	    if(null == proj){
        	return renderError("无工程，上报失败。");
        }
		return renderSuccess("上报操作：成功。");
	}
	
	/**
    * 保存数据，从监测信息表到监测报告表，包括主表和子表
    * id:日常监测信息主表id
    */
	@RequestMapping(value = "/tempsave",method = RequestMethod.GET)
	public String tempsave(String id,Model mode) {
		//查出该日常监测信息主表
		JcInfo info = new JcInfo();
		info.setId(id);
		info = jcInfoService.selectOne(info);
		
		//查子表数据
		List<DmcjJcInfoDetailsDto> details = jcInfoService.selectDetailsByFid(info.getId());
		for (DmcjJcInfoDetailsDto dto : details) {
			/**
			 * 1.1、没有上次高程使用初始高程
			 * 1.2、没有初始高程页面置空 0 页面显示置空 同时备注显示 初始高程
			 * 
			 * 2.1、没有上次高程使用初始高程
			 * 
			 * 3.1、有上次高程使用上次高程 （正常情况）
			 */
			// 根据监测点编号和监测时间取该监测点上次高程，如果没有，则上次高程为初始高程
			Float scgc = pointService.getJcBgScgc(dto.getJcdNo(), info.getJcTime(),info.getGcbh());
			//1.1、没有上次高程使用初始高程
			if(null == scgc || 0f == scgc.floatValue()){
				scgc = dto.getCsgc();
				//1.2、没有初始高程页面置空 0 页面显示置空 同时备注显示 初始高程
				if(null == scgc || 0f == scgc.floatValue()){
					dto.setScgc(scgc);
					dto.setBcbhl(0f);
					dto.setLjbhl(0f);
					dto.setBhsl(0f);
					
					dto.setRemarks("初始高程");
				}else{
					dto.setScgc(scgc);
					CalcBhl.calc(dto);// 计算变化量
				}
			}else{
				dto.setScgc(scgc);
				CalcBhl.calc(dto);// 计算变化量
			}
		}
		mode.addAttribute("jc", info);  
		mode.addAttribute("list", JSON.toJSONString(details));  
		mode.addAttribute("gcbh", info.getGcbh());  
		mode.addAttribute("tianqi", info.getTianqi());  
		mode.addAttribute("remarks", info.getRemarks());  
		mode.addAttribute("jcTime", info.getJcTime());  
		return "dmcjjc/jcbg/add";
	}
	
   /**
	 * 保存
	 */
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	@ResponseBody
	public Object save(DmcjJcInfoDto dto) {
		try {
			//如果没有子表信息，也不能保存
	       String json = dto.getDetails();
			//子表信息
			if(null == json || "[]".equals(json) || "".equals(json.trim())){
				return renderError("无监测报告明细，新增失败");
			}
			
			//相同监测时间不能重复保存
		   JcbgInfo jc = new JcbgInfo();
		   jc.setGcbh(dto.getGcbh());
		   jc.setJcTime(dto.getJcTime());
		   int count = bgInfoService.selectCount(jc);
		   if(count > 0){
		       return renderError("相同时间的监测报告已存在，新增失败。");
		   }
			
			bgInfoService.save(dto);
		} catch (Exception e) {
			this.log("添加", false, "操作失败");
			LOGGER.error("DmcjJcbgInfoAction save失败", e);
			return renderError("新增操作：失败。");
		}
		this.log("添加", true, "操作成功");
		return renderSuccess("新增操作：成功。");
	}
	
	
		/**
		 * 方法说明：主子表一起更新
		 * @param dto
		 * @return
		 * @author:YangYj
		 * @Time: 2016年12月13日 下午5:35:47
		 */
		@RequestMapping(value = "/updateAll", method = RequestMethod.POST)
		@ResponseBody
		public Object updateAll(DmcjJcInfoDto dto) {
			try {
				//相同监测时间不能重复
				Map<String, Object> cond = new HashMap<String,Object>();
		       cond.put("gcbh", dto.getGcbh());
		       cond.put("jctime", dto.getJcTime());
		       List<JcbgInfo> jcs = bgInfoService.selectByMap(cond);
		       if(jcs.size() > 1){
		           return renderError("相同时间的监测报告已存在，修改失败。");
		       }else if(jcs.size() == 1){
		    	   //等于1有可能是自己，要排除掉
		    	   if( ! jcs.get(0).getId().equals(dto.getId())){
		    		   return renderError("相同时间的监测报告已存在，修改失败。");
		    	   }
		       }
				bgInfoService.updateAll(dto);
			} catch (Exception e) {
				LOGGER.error("DmcjJcbgInfoAction save失败", e);
				this.log("编辑", false, "编辑失败!"+dto.getId());
				return renderError("编辑操作：失败。");
			}
			this.log("编辑", true, "编辑成功!"+dto.getId());
			return renderSuccess("编辑操作：成功。");
		}
	
	/**
	 * 根据主键批量删除子表记录
	 */
	@RequestMapping(value = "/deleteDetails",method = RequestMethod.POST)
	@ResponseBody
	public Object deleteDetails(String[] ids) {
		try {
			bgInfoService.deleteDetailsByIds(Arrays.asList(ids));
		} catch (Exception e) {
			LOGGER.error("DmcjJcbgInfoAction deleteDetails失败", e);
			return renderError("删除明细操作：失败。");
		}
		return renderSuccess("删除明细操作：成功。");
	}
	
	/**
	 * 修改一条明细记录
	 */
	@RequestMapping(value = "/detailUpdate", method = RequestMethod.POST)
	@ResponseBody
	public Object detailUpdate(JcbgDetail detail) {
		try {
			String jcdno = detail.getJcdNo();
			String xlbh = detail.getXianlu();
			//先检查监测点是不是该线路下的
			JcPoint p = new JcPoint();
			p.setJcdbh(jcdno);
			p.setXlbh(xlbh);
			int count = pointService.selectCount(p);
			if(count <= 0){
				return renderError("保存失败，该线路下没有这个监测点点号");
			}
			//再检查监测报告中监测点点号是否有重复
			Map<String,Object> map = new HashMap<String,Object>();
			map.put("fid", detail.getFid());
			map.put("jcdno", jcdno);
			List<JcbgDetail> detailList = bgDetailService.selectByMap(map);
			if(null != detailList && detailList.size() > 1){
				//大于1个肯定有重复
				return renderError("保存失败，监测报告明细中已包含该监测点点号");
			}
			if(null != detailList && detailList.size() == 1){
				//等于1的时候要排除自己
				if( ! detail.getId().equals(detailList.get(0).getId())){
					return renderError("保存失败，监测报告明细中已包含该监测点点号");
				}
			}
			bgDetailService.updateById(detail);
		} catch (Exception e) {
			LOGGER.error("DmcjJcbgInfoAction updateSave失败", e);
			return renderError("保存明细操作：失败。");
		}
		return renderSuccess("保存明细操作：成功。");
	}
	
	/**
	 * 新增一条明细记录
	 */
	@RequestMapping(value = "/detailSave", method = RequestMethod.POST)
	@ResponseBody
	public Object detailSave(JcbgDetail detail) {
		try {
			String jcdno = detail.getJcdNo();
			String xlbh = detail.getXianlu();
			//先检查监测点是不是该线路下的
			JcPoint p = new JcPoint();
			p.setJcdbh(jcdno);
			p.setXlbh(xlbh);
			int count = pointService.selectCount(p);
			if(count <= 0){
				return renderError("保存失败，该线路下没有这个监测点点号");
			}
			//再检查日常监测中监测点点号是否有重复
			Map<String,Object> map = new HashMap<String,Object>();
			map.put("fid", detail.getFid());
			map.put("jcdno", jcdno);
			List<JcbgDetail> detailList = bgDetailService.selectByMap(map);
			if(null != detailList && detailList.size() > 0){
				return renderError("保存失败，监测报告明细中已包含该监测点点号");
			}
			
			detail.setId(CommUtils.getUUID());
			bgInfoService.saveDetail(detail);
		} catch (Exception e) {
			LOGGER.error("DmcjJcbgInfoAction save失败", e);
			return renderError("保存明细操作：失败。");
		}
		return renderSuccess("保存明细操作：成功。");
	}
	
	/**
	 * 修改
	 */
	@RequestMapping(value = "/update",method = RequestMethod.POST)
	@ResponseBody
	public Object update(DmcjJcInfoDto dto) {
		try {
			//相同监测时间不能重复
			Map<String, Object> cond = new HashMap<String,Object>();
	       cond.put("gcbh", dto.getGcbh());
	       cond.put("jctime", dto.getJcTime());
	       List<JcbgInfo> jcs = bgInfoService.selectByMap(cond);
	       if(jcs.size() > 1){
	           return renderError("相同时间的监测报告已存在，修改失败。");
	       }else if(jcs.size() == 1){
	    	   //等于1有可能是自己，要排除掉
	    	   if( ! jcs.get(0).getId().equals(dto.getId())){
	    		   return renderError("相同时间的监测报告已存在，修改失败。");
	    	   }
	       }
			
			bgInfoService.update(dto);
		} catch (Exception e) {
			LOGGER.error("DmcjJcInfoAction update失败", e);
			return renderError("修改操作：失败。");
		}
		return renderSuccess("修改操作：成功。");
	}
	
	/**
	 * 查询和编辑的详情页面查询明细表格的方法
	 * 
	 * @param jcTime
	 */
	@RequestMapping(value = "/detailList", method = RequestMethod.POST)
	@ResponseBody
	public Object detailList(String fid, String jcTime, Integer page,
			Integer rows, String sort, String order) {
		String userId = String.valueOf(getCurrentUser().getId());
		ProProjectinfo proj = proProjectinfoService
				.getProjectInfoByUserId(userId);
		// 查询子表(以分页的方式查询)
		PageInfo pageInfo = new PageInfo(0, 65530, "id", "desc");
		Map<String, Object> condition = new HashMap<String, Object>();
		condition.put("fid", fid);
		pageInfo.setCondition(condition);
		bgInfoService.calcBhl(fid, jcTime, pageInfo, proj.getId());
		return pageInfo;
	}
	
	/**
	 * 方法说明：查询页面导出明细excel
	 * @param exportDto
	 * @param dto
	 * @param response
	 * @param request
	 * @author:YangYj
	 * @Time: 2016年12月13日 上午11:11:24
	 */
	@SuppressWarnings({"unchecked"})
	@RequestMapping(value="/detailExpXls")
	public void detailExpXls(ExportDto exportDto,DmcjJcInfoDetailsDto dto, HttpServletResponse response,HttpServletRequest request){
		try {
			String fid = (String)request.getParameter("fid");
			String jcTime = (String) request.getParameter("jcTime");
			String projectId = (String) request.getParameter("projectId");
			PageInfo pageInfo = new PageInfo(0, 65530, "id", "desc");
			Map<String, Object> condition = new HashMap<String, Object>();
			condition.put("fid", fid);
			if(StringUtils.isNotBlank(exportDto.getIds())){
    			String[] array = exportDto.getIds().split(ExportDto.SPLIT_STR);
    			String ids = "";
    			for(String id:array){
    				ids +=",'"+id+"'";
    			}
    			ids = ids.substring(1);
				condition.put("ids", ids);
    		}
			pageInfo.setCondition(condition);
			bgInfoService.calcBhl(fid,jcTime, pageInfo,projectId);
			ExportExcelUtil.writeExcelToClient(response, exportDto, ExportSetUtil.objectToListMap(pageInfo.getRows()));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
