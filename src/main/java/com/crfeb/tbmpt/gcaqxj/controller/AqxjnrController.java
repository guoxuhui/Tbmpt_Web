package com.crfeb.tbmpt.gcaqxj.controller;

import com.crfeb.tbmpt.commons.base.BaseController;
import com.crfeb.tbmpt.commons.utils.PageInfo;
import com.crfeb.tbmpt.gcaqxj.model.AqxjXjnrInfo;
import com.crfeb.tbmpt.gcaqxj.service.IAqxjXjnrInfoService;
import com.crfeb.tbmpt.sys.model.SysEmployee;
import com.crfeb.tbmpt.sys.model.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.*;

/**
 * 巡检内容Controller
 * Created by Administrator on 2017/7/26.
 */
@Controller
@RequestMapping("/gcaqxj/xjdgl/xjnr")
public class AqxjnrController extends BaseController {
    private static final Logger LOGGER = LogManager.getLogger(AqxjflController.class);

    @Autowired
    IAqxjXjnrInfoService aqxjXjnrInfoService;

    /**
     * 根据巡检点id 查询巡检内容列表
     * @param id
     * @param page
     * @param rows
     * @param sort
     * @param order
     * @return
     */
    @RequestMapping(value = "/dataGrid", method = RequestMethod.POST)
    @ResponseBody
    public Object dataGrid(String  id ,Integer page, Integer rows, String sort, String order)
    {
        page=1;
        rows=100;
        PageInfo pageInfo = new PageInfo(page, rows, sort, order);
        Map<String, Object> condition = new HashMap<String, Object>();
        condition.put("id", id);
        pageInfo.setCondition(condition);


       // List<AqxjXjnrInfo> list = aqxjXjnrInfoService.selectAll(id);
        aqxjXjnrInfoService.selectDataGrid(pageInfo);
        return pageInfo;
    }

    @RequestMapping(value = "/list", method = RequestMethod.POST)
    @ResponseBody
    public Object getList(String  nrid )
    {
         List<AqxjXjnrInfo> list = aqxjXjnrInfoService.selectAll(nrid);
        return list;
    }


    /**
     * 添加信息
     * @param aqxjXjnrInfo
     * @return
     */
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public Object add(AqxjXjnrInfo aqxjXjnrInfo) {
        User user = this.getCurrentUser();
        aqxjXjnrInfoService.insert(aqxjXjnrInfo,user);
        log(null, true, "巡检内容信息添加成功！");
        return renderSuccess("添加成功！");
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @ResponseBody
    public Object update(AqxjXjnrInfo aqxjXjnrInfo) {

        try {
            String  errmessage = aqxjXjnrInfoService.update(aqxjXjnrInfo,this.getCurrentUser());
            log(null, true, "巡检内容信息更新成功！");
            return renderSuccess("更新成功！");
        }catch (Exception e){
            e.printStackTrace();
            log(null, true, "巡检内容信息更新失败！");
            return  renderError("更新失败!");
        }
    }

    @RequestMapping("/delete")
    @ResponseBody
    public Object delete(String id) {
        User user = this.getCurrentUser();
        try {
            aqxjXjnrInfoService.deleteById(id);
            LOGGER.info("巡检内容信息删除数据成功。");
            this.log(null, true, "用户" + user.getName() + "删除记录成功。id:" + id);
            return renderSuccess("删除成功！");
        }catch (Exception e){
            e.printStackTrace();
            return  renderError("删除失败!");
        }
    }
}
