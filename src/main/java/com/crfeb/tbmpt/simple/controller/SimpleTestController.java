package com.crfeb.tbmpt.simple.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.crfeb.tbmpt.commons.base.BaseController;
import com.crfeb.tbmpt.commons.utils.PageInfo;
import com.crfeb.tbmpt.simple.model.Test;
import com.crfeb.tbmpt.simple.service.ITestService;

/**
 * @author：smxg
 * @date：2016-10-10 11:12
 */
@Controller
@RequestMapping("/simple")
public class SimpleTestController extends BaseController {

    @Autowired
    private ITestService testService;

    /**
     * @return
     */
    @RequestMapping(value = "/manager", method = RequestMethod.GET)
    public String manager() {
        return "simple/test";
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
    public Object dataGrid(Integer page, Integer rows, String sort, String order) {
        PageInfo pageInfo = new PageInfo(page, rows, sort, order);
        Map<String, Object> condition = new HashMap<String, Object>();
        pageInfo.setCondition(condition);

        testService.selectDataGrid(pageInfo);
        return pageInfo;
    }

    /**
     * @return
     */
    @RequestMapping(value = "/addPage", method = RequestMethod.GET)
    public String addPage() {
        return "simple/testAdd";
    }

    /**
     * @param role
     * @return
     */
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public Object add(Test t) {
    	testService.insert(t);
        return renderSuccess("添加成功！");
    }

    /**
     * @param id
     * @return
     */
    @RequestMapping("/delete")
    @ResponseBody
    public Object delete(Long id) {
    	testService.deleteById(id);
        return renderSuccess("删除成功！");
    }

    /**
     * @param model
     * @param id
     * @return
     */
    @RequestMapping("/editPage")
    public String editPage(Model model, Long id) {
        Test t = testService.selectById(id);
        model.addAttribute("test", t);
        return "simple/testEdit";
    }

    /**
     */
    @RequestMapping("/edit")
    @ResponseBody
    public Object edit(Test t) {
    	testService.updateSelectiveById(t);
        return renderSuccess("编辑成功！");
    }

}
