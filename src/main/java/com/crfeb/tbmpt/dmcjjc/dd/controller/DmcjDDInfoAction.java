package com.crfeb.tbmpt.dmcjjc.dd.controller;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

import com.alibaba.fastjson.util.IOUtils;
import com.crfeb.tbmpt.commons.base.BaseController;
import com.crfeb.tbmpt.commons.utils.CommUtils;
import com.crfeb.tbmpt.commons.utils.ExcelUtils;
import com.crfeb.tbmpt.commons.utils.PageInfo;
import com.crfeb.tbmpt.dmcjjc.dd.model.DdInfo;
import com.crfeb.tbmpt.dmcjjc.dd.service.IDmcjDDInfoService;

@Controller
@RequestMapping("/dmcjjc/dmcjddinfo")
public class DmcjDDInfoAction extends BaseController {
	private static final Logger LOGGER = LogManager
			.getLogger(DmcjDDInfoAction.class);

	@Autowired
	private IDmcjDDInfoService dmcjDDInfoService;

	 /**
	 * 跳转到列表页面
	 */
    @RequestMapping(value = "", method = RequestMethod.GET)
    public String manager() {
        return "dmcjjc/jcdd/list";
    }
	
    /**
	 * 跳转到新增页面
	 */
    @RequestMapping(value = "/addPage", method = RequestMethod.GET)
    public String addPage() {
        return "dmcjjc/jcdd/add";
    }
    
    /**
     * 跳转到编辑页面 
     */
    @RequestMapping("/editPage")
    public String editPage(Model model, String id) {
    	DdInfo info = new DdInfo();
    	info.setId(id);
        info = dmcjDDInfoService.selectOne(info);
        model.addAttribute("vo", info);
        return "dmcjjc/jcdd/edit";
    }
    
	/**
	 * 列表查询
	 */
	@RequestMapping(value = "/list", method = RequestMethod.POST)
	@ResponseBody
	public Object list(String typeName, Integer page, Integer rows, String sort, String order) {
		PageInfo pageInfo = new PageInfo(page, rows, sort, order);
		Map<String, Object> condition = new HashMap<String, Object>();
		if(null != typeName){
			condition.put("typeName", typeName.trim());
		}
		pageInfo.setCondition(condition);
		dmcjDDInfoService.selectDataGrid(pageInfo);
		return pageInfo;
	}

	/**
	 * 保存
	 */
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	@ResponseBody
	public Object save(DdInfo dmcjDDInfo) {
		try {
			//先检查同一分类名称下基础数据名称不能重复，如果重复，则提示“基础数据名称已存在，新增失败。”
			DdInfo info = new DdInfo();
			info.setTypeName(dmcjDDInfo.getTypeName().trim());
			info.setDdName(dmcjDDInfo.getDdName());
			if ( 0 == dmcjDDInfoService.selectCount(info )){
				dmcjDDInfo.setId(CommUtils.getUUID());//设置主键UUID
				dmcjDDInfoService.insert(dmcjDDInfo);
			}else{
				return renderError("基础数据名称已存在，新增失败。");
			}
		} catch (Exception e) {
			LOGGER.error("DmcjDDInfoAction save失败", e);
			this.log("新增", false, "新增操作失败"+dmcjDDInfo.getDdName());
			return renderError("新增操作：失败。");
		}
		this.log("新增", true, "新增操作成功."+dmcjDDInfo.getDdName());
		LOGGER.info("新增操作成功."+dmcjDDInfo.getDdName());
		return renderSuccess("新增操作：成功。");
	}

	/**
	 * 修改
	 */
	@RequestMapping(value = "/update",method = RequestMethod.POST)
	@ResponseBody
	public Object update(DdInfo dmcjDDInfo) {
		try {
			//修改时要检查同一个分类名称下基础数据名称不能重复
			Map<String,Object> cond = new HashMap<String,Object>();
			cond.put("typeName", dmcjDDInfo.getTypeName());
			cond.put("ddName", dmcjDDInfo.getDdName());
			List<DdInfo> infos = dmcjDDInfoService.selectByMap(cond);
			if(null != infos && infos.size() > 1){
				//如果大于1个，肯定有重名的
				return renderError("基础数据名称已存在，修改失败。");
			}
			if(null != infos && infos.size() == 1){
				//当有1个时，可能是自己，如果不是自己则修改失败，自己可以修改
				DdInfo i = infos.get(0);
				if( ! dmcjDDInfo.getId().equals(i.getId())){
					//此时同名的一定不是自己，所以不能修改
					return renderError("基础数据名称已存在，修改失败。");
				}
			}
			dmcjDDInfoService.updateById(dmcjDDInfo);
		} catch (Exception e) {
			this.log("编辑", false, "操作失败.id:"+dmcjDDInfo.getId());
			LOGGER.error("DmcjDDInfoAction update失败", e);
			return renderError("修改操作：失败。");
		}
		this.log("编辑", true, "操作成功.id:"+dmcjDDInfo.getId());
		LOGGER.info("编辑操作成功.id:"+dmcjDDInfo.getId());
		return renderSuccess("修改操作：成功。");
	}

	/**
	 * 删除
	 */
	@RequestMapping(value = "/del",method = RequestMethod.POST)
	@ResponseBody
	public Object del(String[] ids) {
		try {
			for (String id : ids) {
				DdInfo info = new DdInfo();
				info.setId(id);
				dmcjDDInfoService.deleteSelective(info);
			}
		} catch (Exception e) {
			this.log("删除", false, "操作失败.ids:"+ids);
			LOGGER.error("DmcjDDInfoAction del失败", e);
			return renderError("删除操作：失败。");
		}
		this.log("删除", true, "操作成功.ids:"+ids);
		LOGGER.info("删除操作成功.ids:"+ids);
		return renderSuccess("删除操作：成功。");
	}

	/**
   	 * 跳转到查看页面
   	 */
   @RequestMapping(value = "/showPage", method = RequestMethod.GET)
   public String showPage(String id,Model model) {
	   DdInfo cond = new DdInfo();
	   cond.setId(id);
	   //查询主表
	   cond = dmcjDDInfoService.selectOne(cond);
	   model.addAttribute("vo", cond);
       return "dmcjjc/jcdd/show";
   }
	
	/**
	 * 禁用
	 */
	@RequestMapping(value = "/jy",method = RequestMethod.POST)
	@ResponseBody
	public Object jy(String[] ids) {
		try {
			for (String id : ids) {
				dmcjDDInfoService.jy(id);
			}
		} catch (Exception e) {
			this.log("禁用", false, "操作失败.ids:"+ids);
			LOGGER.error("DmcjDDInfoAction jy失败", e);
			return renderError("禁用操作：失败。");
		}
		this.log("禁用", true, "操作成功.ids:"+ids);
		LOGGER.info("禁用操作成功.ids:"+ids);
		return renderSuccess("禁用操作：成功。");
	}

	/**
	 * 启用
	 */
	@RequestMapping(value = "/qy",method = RequestMethod.POST)
	@ResponseBody
	public Object qy(String[] ids) {
		try {
			for (String id : ids) {
				dmcjDDInfoService.qy(id);
			}
		} catch (Exception e) {
			this.log("启用", false, "操作失败.ids:"+ids);
			LOGGER.error("DmcjDDInfoAction qy失败", e);
			return renderError("启用操作：失败。");
		}
		this.log("启用", true, "操作成功.ids:"+ids);
		LOGGER.info("启用操作成功.ids:"+ids);
		return renderSuccess("启用操作：成功。");
	}

	/**
	 * 导出选中数据到excel
	 */
	@RequestMapping("/expDDInfo")
	@ResponseBody
	public void expDDInfo(String[] ids, HttpServletResponse response) {
		// 生成excel并下载
		HSSFWorkbook wb = dmcjDDInfoService.expDDInfo(Arrays.asList(ids));
		String filename = "基础数据.xls";
		String iso_filename = ExcelUtils.parseGBK(filename);
		response.setContentType("text/plain");
		response.setHeader("Content-Disposition","attachment;filename=" + iso_filename);
		
		OutputStream ouputStream = null; 
		try {
			ouputStream = response.getOutputStream();
			wb.write(ouputStream);
			ouputStream.flush();
		} catch (IOException e) {
			LOGGER.error("expDDInfo error",e);
		} finally{
			IOUtils.close(ouputStream);
		}
	}

	/**
	 * 根据基础数据分类获得基础数据信息
	 */
	@RequestMapping(value = "/getDmcjDDInfo")
	@ResponseBody
	public Object getDmcjDDInfo(String typeName) {
		return dmcjDDInfoService.getDmcjDDInfo(typeName);
	}
	
	/**
	 * 获取点位设计类型
	 */
	@RequestMapping(value = "/getJcType")
	@ResponseBody
	public Object getJcType() {
		return dmcjDDInfoService.getDmcjDDInfo("点位设计类型");
	}
	
	/**
	 * 获取点位设计时间
	 */
	@RequestMapping(value = "/getSjTime")
	@ResponseBody
	public Object getSjTime() {
		return dmcjDDInfoService.getDmcjDDInfo("点位设计时间");
	}
	
	/**
	 * 获取监测说明,明细信息备注用
	 */
	@RequestMapping(value = "/getJcShuoMing")
	@ResponseBody
	public Object getJcShuoMing() {
		return dmcjDDInfoService.getDmcjDDInfo("监测说明");
	}
}
