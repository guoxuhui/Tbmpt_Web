package com.crfeb.tbmpt.gcaqxj.controller;

import com.crfeb.tbmpt.aqxj.xjdgl.model.AqxjXjd;
import com.crfeb.tbmpt.commons.base.BaseController;
import com.crfeb.tbmpt.commons.utils.*;
import com.crfeb.tbmpt.gcaqxj.model.AqxjXjdInfo;
import com.crfeb.tbmpt.gcaqxj.model.AqxjXjflInfo;
import com.crfeb.tbmpt.gcaqxj.model.dto.AqxjXjdInfoDto;
import com.crfeb.tbmpt.gcaqxj.model.dto.AqxjXjflInfoDto;
import com.crfeb.tbmpt.gcaqxj.service.IAqxjXjdInfoService;
import com.crfeb.tbmpt.project.model.ProProjectinfo;
import com.crfeb.tbmpt.project.service.IProProjectinfoService;
import com.crfeb.tbmpt.sys.model.Orgz;
import com.crfeb.tbmpt.sys.model.User;
import com.crfeb.tbmpt.sys.service.IOrgzService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.util.*;

/**
 * 巡检点管理
 * Created by Administrator on 2017/7/24.
 */
@Controller
@RequestMapping("/gcaqxj/xjdgl")
public class AqxjdglController  extends BaseController {

    private static final Logger LOGGER = LogManager.getLogger(AqxjflController.class);

    @Autowired
    IAqxjXjdInfoService aqxjXjdInfoService;

    @Autowired
    IProProjectinfoService proProjectinfoService;

    @Autowired
    IOrgzService iOrgzService;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public String manager(Model model) {
        User user = this.getCurrentUser();
        ProProjectinfo proProjectinfo = proProjectinfoService.getProjectInfoByUserId(user.getId());
        model.addAttribute("pro",proProjectinfo);
        model.addAttribute("orgId",findOrgzId());
        return "gcaqxj/xunjiandianGL";
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
        User user = this.getCurrentUser();
        Orgz orgz =iOrgzService.getOrgzInfoByUserId(user.getId());
        PageInfo pageInfo = new PageInfo(page, rows, sort, order);
        Map<String, Object> condition = new HashMap<String, Object>();

        if(orgz.getType()==0&&(null ==aqxjXjdInfoDto.getProjectname()||aqxjXjdInfoDto.getProjectname().equals(""))){
        }else if(orgz.getType()==0&&!(null ==aqxjXjdInfoDto.getProjectname()||aqxjXjdInfoDto.getProjectname().equals(""))){
            //项目名称
            condition.put("proiectName", aqxjXjdInfoDto.getProjectname());
        }else {
            condition.put("proiectName", findProjectByCurrentUser().getProName());
        }

        //分类名称
        condition.put("typeName", aqxjXjdInfoDto.getTypeName());
        //检查点名称
        condition.put("mingcheng", aqxjXjdInfoDto.getMingcheng());
        //责任人
        condition.put("zerenrmc", aqxjXjdInfoDto.getZerenrmc());
        //监督人
        condition.put("jiandurmc", aqxjXjdInfoDto.getJiandurmc());
        pageInfo.setCondition(condition);
        aqxjXjdInfoService.selectDataGrid(pageInfo);
        return pageInfo;
    }


    /**
     * 添加信息
     * @param aqxjXjdInfo
     * @return
     */
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public Object add(AqxjXjdInfo aqxjXjdInfo) {
        String errmessage = aqxjXjdInfoService.checkClumIfexits(aqxjXjdInfo,new String[]{"projectid","mingcheng"});
        User user = this.getCurrentUser();
        if(errmessage!=null){
            log(null, false, errmessage);
            return renderError(errmessage);
        }
        aqxjXjdInfoService.insert(aqxjXjdInfo,user);
        log(null, true, "巡检点分类管理信息添加成功！");
        return renderSuccess("添加成功！");
    }

    @RequestMapping("/delete")
    @ResponseBody
    public Object delete(String ids) {
        String[] idss = ids.split(",");
        List<String> idlist = new ArrayList<String>();
        Collections.addAll(idlist, idss);//把数组转化为集合
        User user = this.getCurrentUser();
    	/*String[] idss = ids.split(",");
    	List<String> idlist = new ArrayList<String>();
    	Collections.addAll(idlist, idss);*/
        aqxjXjdInfoService.deleteBatchIds(idlist);
        LOGGER.info("巡检点信息删除数据成功。");
        this.log(null, true, "用户"+user.getName()+"删除记录成功。ids:"+ids);
        return renderSuccess("删除成功！");
    }

    /**
     * 修改资料信息
     * @param aqxjXjdInfo 要修改的实体信息
     * @return 返回编辑成功/失败的提示信息
     */
    @RequestMapping("/edit")
    @ResponseBody
    public Object edit(AqxjXjdInfo aqxjXjdInfo) {
        User user = this.getCurrentUser();
        AqxjXjdInfo oldEntity = this.aqxjXjdInfoService.selectById(aqxjXjdInfo.getId());
        if(null == oldEntity || StringUtils.isBlank(oldEntity.getId())){
            return renderError("数据不存在,请刷新重试!");
        }
        String errmessage = aqxjXjdInfoService.checkClumIfexits(aqxjXjdInfo,new String[]{"projectid","mingcheng"});
        if(errmessage!=null){
            log(null, false, errmessage);
            return renderError(errmessage);
        }
        errmessage = aqxjXjdInfoService.update(aqxjXjdInfo,this.getCurrentUser());
        if(StringUtils.isNotBlank(errmessage)) return renderError(errmessage);//更新失败
        LOGGER.info("巡检点信息修改数据成功。");
        this.log(null, true, "用户"+user.getName()+"修改记录成功。id:"+aqxjXjdInfo.getId());
        return renderSuccess("编辑成功!");
    }


    /**
     * 数据导出
     * @param exportDto
     * @param dto
     * @param response
     */
    @SuppressWarnings({"unchecked", "rawtypes"})
    @RequestMapping(value="/expXls")
    public void expXls(ExportDto exportDto,AqxjXjdInfo dto, HttpServletResponse response){
        try {
            if(StringUtils.isNotBlank(exportDto.getIds())){
                List<String> idsList =new ArrayList<String>();
                Collections.addAll(idsList, exportDto.getIds().split(ExportDto.SPLIT_STR));
                List list = aqxjXjdInfoService.selectBatchIds(idsList);
                ExportExcelUtil.writeExcelToClient(response, exportDto, ExportSetUtil.objectToListMap(list));
            }else{
                PageInfo pageInfo = new PageInfo(0, 65530, "id", "desc");
                Map<String, Object> condition = new HashMap<String, Object>();
                pageInfo.setCondition(condition);
                aqxjXjdInfoService.selectDataGrid(pageInfo);
                ExportExcelUtil.writeExcelToClient(response, exportDto, ExportSetUtil.objectToListMap(pageInfo.getRows()));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 数据导出
     * @param exportDto
     * @param dto
     * @param response
     */
    @SuppressWarnings({"unchecked", "rawtypes"})
    @RequestMapping(value="/expPdf")
    public void expPdf(ExportDto exportDto,AqxjXjd dto, HttpServletResponse response){
        try {
            if(StringUtils.isNotBlank(exportDto.getIds())){
                List<String> idsList =new ArrayList<String>();
                Collections.addAll(idsList, exportDto.getIds().split(ExportDto.SPLIT_STR));
                List list = aqxjXjdInfoService.selectBatchIds(idsList);
                ExportPdfUtil.writePdfToClient(response, exportDto, ExportSetUtil.objectToListMap(list));
            }else{
                PageInfo pageInfo = new PageInfo(0, 65530, "id", "desc");
                Map<String, Object> condition = new HashMap<String, Object>();
                pageInfo.setCondition(condition);
                aqxjXjdInfoService.selectDataGrid(pageInfo);
                ExportPdfUtil.writePdfToClient(response, exportDto, ExportSetUtil.objectToListMap(pageInfo.getRows()));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
