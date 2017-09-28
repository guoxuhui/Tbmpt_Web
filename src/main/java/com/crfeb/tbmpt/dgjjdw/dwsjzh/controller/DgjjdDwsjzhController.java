package com.crfeb.tbmpt.dgjjdw.dwsjzh.controller;

import java.util.ArrayList;  
import java.util.Collections;
import java.util.HashMap;
import java.util.List; 
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller; 
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody; 
import com.crfeb.tbmpt.commons.base.BaseController;  
import com.crfeb.tbmpt.commons.utils.PageInfo;
import com.crfeb.tbmpt.dgjjdw.dwsjzh.model.DgjjdDwsjzh;
import com.crfeb.tbmpt.dgjjdw.dwsjzh.service.DgjjdDwsjzhService; 
import com.crfeb.tbmpt.gczlys.model.YanShouYingXiang;
import com.crfeb.tbmpt.sys.model.User;

@Controller
@RequestMapping(value="/dgjjdw/dwsjzh")
public class DgjjdDwsjzhController extends BaseController{
	
	private static final Logger LOGGER = LogManager.getLogger(DgjjdDwsjzhController.class);
	@Autowired
    private DgjjdDwsjzhService dgjjdDwsjzhService;
	 
	
	@RequestMapping(value = "", method = RequestMethod.GET)
    public String manager() {
        return "dgjjdw/dwsjzh/dwsjzhSearchList";
    }
	 
    /**
     * 批量新增对比信息,数据表格
     * @param dto
     * @param page
     * @param rows
     * @param sort
     * @param order
     * @return
     */
    @RequestMapping(value = "/dwsjzhDataGrid", method = RequestMethod.POST)
    @ResponseBody
     
    public Object dataGrid(DgjjdDwsjzh dgjjdDwsjzh,Integer page, Integer rows, String sort, String order)
    {
        PageInfo pageInfo = new PageInfo(page, rows, sort, order);
        Map<String, Object> condition = new HashMap<String, Object>();
        condition.put("dwname", dgjjdDwsjzh.getDwname());
        condition.put("dgjname", dgjjdDwsjzh.getDgjname());
 
        pageInfo.setCondition(condition);
        dgjjdDwsjzhService.selectDataGrid(pageInfo);
        return pageInfo;
    }
    
    //删除数据
    @RequestMapping("/delete")
    @ResponseBody
    
    public Object delete(String ids) {
    	String[] idss = ids.split(",");
    	List<String> idlist = new ArrayList<String>();
    	Collections.addAll(idlist, idss);//把数组转化为集合
    	User user = this.getCurrentUser(); 
    	dgjjdDwsjzhService.deleteBatchIds(idlist);
    	LOGGER.info("点位数据信息删除数据成功。");
    	this.log(null, true, "用户"+user.getName()+"删除记录成功。ids:"+ids);
        return renderSuccess("删除成功！");
    }
    
    
    /**
     * 新增信息
     * @param  要新增的实体信息
     * @return 返回保存成功/失败的提示信息
     */
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public Object add(DgjjdDwsjzh dgjjdDwsjzh) { 
    	dgjjdDwsjzhService.insert(dgjjdDwsjzh);
    	log(null, true, "点位数据添加成功！");
        return renderSuccess("添加成功！");
    }
    
    /**
     * 新增信息
     * @param  要新增的实体信息
     * @return 返回保存成功/失败的提示信息
     */
    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    @ResponseBody
    public Object edit(DgjjdDwsjzh dgjjdDwsjzh) { 
    	dgjjdDwsjzhService.insertOrUpdate(dgjjdDwsjzh);
    	log(null, true, "点位数据添加成功！");
        return renderSuccess("添加成功！");
    }
    
}
