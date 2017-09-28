package com.crfeb.tbmpt.gcaqxj.controller;

import com.crfeb.tbmpt.commons.base.BaseController;
import com.crfeb.tbmpt.commons.utils.PageInfo;
import com.crfeb.tbmpt.commons.utils.StringUtils;
import com.crfeb.tbmpt.gcaqxj.model.dto.AqxjXjdInfoDto;
import com.crfeb.tbmpt.gcaqxj.service.IAqxjXjdInfoService;
import com.crfeb.tbmpt.gcaqxj.service.IAqxjXjflInfoService;
import com.crfeb.tbmpt.project.model.ProProjectinfo;
import com.crfeb.tbmpt.project.service.IProProjectinfoService;
import com.crfeb.tbmpt.sys.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.*;

/**
 * Created by Administrator on 2017/8/2.
 */
@Controller
@RequestMapping("/gcaqxj/info/copy")
public class XunjianCopyController  extends BaseController{

    @Autowired
    IProProjectinfoService proProjectinfoService;

    @Autowired
    IAqxjXjflInfoService aqxjXjflInfoService;

    @Autowired
    IAqxjXjdInfoService aqxjXjdInfoService;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public String manager(Model model) {
        User user = this.getCurrentUser();
        ProProjectinfo proProjectinfo = proProjectinfoService.getProjectInfoByUserId(user.getId());
        model.addAttribute("pro",proProjectinfo);
        model.addAttribute("orgId",findOrgzId());
        return "gcaqxj/xunjianCopy";
    }

    /**
     * 获取巡检点管理-easyUi列表
     * @param aqxjXjdInfoDto 查询条件dto
     * @param page
     * @param rows
     * @param sort
     * @param order
     * @return 返回查询结果信息
     */
    @RequestMapping(value = "/dataGrid", method = RequestMethod.POST)
    @ResponseBody
    public Object dataGrid(AqxjXjdInfoDto aqxjXjdInfoDto,Integer page, Integer rows, String sort, String order)
    {
        PageInfo pageInfo = new PageInfo(page, rows, sort, order);
        Map<String, Object> condition = new HashMap<String, Object>();
        if(null==aqxjXjdInfoDto.getProjectid()||aqxjXjdInfoDto.getProjectid().equals("")){
            return  renderError("请指定工程信息!");
        }else {
            //项目名称
            condition.put("projectid", aqxjXjdInfoDto.getProjectid());
            //分类名称
            condition.put("typeName", aqxjXjdInfoDto.getTypeName());
            //检查点名称
            condition.put("mingcheng", aqxjXjdInfoDto.getMingcheng());

            pageInfo.setCondition(condition);
            aqxjXjdInfoService.selectDataGrid(pageInfo);
            return pageInfo;
        }
    }

    /**
     * 【工程信息应用】
     *
     * @param proName
     * @return
     */
    @RequestMapping(value = "/copyPro" ,method = RequestMethod.POST)
      @ResponseBody
      public Object copyPro(String proName) {
        User user = this.getCurrentUser();
        String userId = String.valueOf(user.getId());
        List<ProProjectinfo> project= proProjectinfoService.getProjectInfosByUserId(userId);
        boolean flag =aqxjXjflInfoService.addCopyAqxjxjdfl(proName,user,project.get(0));
        if(flag){
            return renderSuccess("复制成功！");
        }
        return renderSuccess("noCopy");
    }

    /**
     * 复制巡检点
     * @param ids
     * @param typeName
     * @param typeId
     * @return
     */
    @RequestMapping(value = "/copyXunjiandian",method = RequestMethod.POST)
    @ResponseBody
    public Object delete(String ids,String typeName,String typeId) {
        String[] idss = ids.split(",");
        List<String> idlist = new ArrayList<String>();
        Collections.addAll(idlist, idss);//把数组转化为集合
        User user = this.getCurrentUser();
        List<ProProjectinfo> project= proProjectinfoService.getProjectInfosByUserId(user.getId());
        String  errMessage = aqxjXjdInfoService.addCopyXunJianDian(ids,typeName,typeId,project.get(0));
        if(StringUtils.isNotBlank(errMessage)) return renderError(errMessage);//复制失败
        return renderSuccess("复制成功！");
    }

}
