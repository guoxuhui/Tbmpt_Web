package com.crfeb.tbmpt.open.controller;

import com.crfeb.tbmpt.commons.base.BaseController;
import com.crfeb.tbmpt.commons.result.Result;
import com.crfeb.tbmpt.commons.utils.*;
import com.crfeb.tbmpt.gcaqxj.model.AqxjXjdInfo;
import com.crfeb.tbmpt.gcaqxj.model.AqxjXjjlDetail;
import com.crfeb.tbmpt.gcaqxj.model.AqxjXjjlInfo;
import com.crfeb.tbmpt.gcaqxj.model.AqxjXjnrInfo;
import com.crfeb.tbmpt.gcaqxj.model.dto.AqxjXjjlInfoDto;
import com.crfeb.tbmpt.gcaqxj.service.IAqxjXjdInfoService;
import com.crfeb.tbmpt.gcaqxj.service.IAqxjXjjlDetailService;
import com.crfeb.tbmpt.gcaqxj.service.IAqxjXjjlInfoService;
import com.crfeb.tbmpt.gcaqxj.service.IAqxjXjnrInfoService;
import com.crfeb.tbmpt.gczlys.model.dto.YanShouYingXiangDto;
import com.crfeb.tbmpt.open.model.XunjianjiluInfo;
import com.crfeb.tbmpt.open.service.IOpenCommService;
import com.crfeb.tbmpt.project.mapper.ProProjectinfoMapper;
import com.crfeb.tbmpt.project.model.ProProjectinfo;
import com.crfeb.tbmpt.sys.base.model.dto.SysFujianDto;
import com.crfeb.tbmpt.sys.base.service.SysFujianService;
import com.crfeb.tbmpt.sys.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 * Created by ztzn on 2017/8/20.
 */
@Controller
@RequestMapping("/open/gcaqxj")
public class GcaqxjController extends BaseController {
    @Autowired
    IOpenCommService openCommService;

    @Autowired
    private ProProjectinfoMapper projectinfoMapper;

    @Autowired
    IAqxjXjdInfoService aqxjXjdInfoService;

    @Autowired
    SysFujianService sysFujianService;

    @Autowired
    IAqxjXjjlInfoService aqxjXjjlInfoService;

    @Autowired
    IAqxjXjnrInfoService aqxjXjnrInfoService;
    @Autowired
    IAqxjXjjlDetailService aqxjXjjlDetailService;
    private String imagePath = "/upload/gcaqxj/pic/" + DateUtils.format(new Date(), "yyyyMMdd");
    /**
     * 添加页面
     *
     * @param model
     * @param token
     * @return
     */
    @RequestMapping(value = "/gcaqxjAddPage", method = RequestMethod.GET)
    public String gczlHomePape(Model model, String token, String id) {
//        long startTime = System.currentTimeMillis();
        if (!StringUtils.isEmpty(token)) {
            openCommService.relogin(token);
        }
        AqxjXjdInfo aqxjXjdInfo = aqxjXjdInfoService.selectById(id);
        XunjianjiluInfo xunjianjiluInfo = new XunjianjiluInfo();
        xunjianjiluInfo.setProjectId(aqxjXjdInfo.getProjectid());
        xunjianjiluInfo.setProjectName(aqxjXjdInfo.getProjectname());
        xunjianjiluInfo.setForeignid(aqxjXjdInfo.getId());
        xunjianjiluInfo.setJianchapc(aqxjXjdInfo.getJianchapc());
        xunjianjiluInfo.setName(aqxjXjdInfo.getMingcheng());
        xunjianjiluInfo.setTypeName(aqxjXjdInfo.getTypeName());
        String lastCheckTime = aqxjXjjlInfoService.selectByMingcheng(aqxjXjdInfo.getMingcheng());
        xunjianjiluInfo.setLastCheckTime(lastCheckTime);
        xunjianjiluInfo.setZerenrid(aqxjXjdInfo.getZerenrid());
        xunjianjiluInfo.setZerenrmc(aqxjXjdInfo.getZerenrmc());
        xunjianjiluInfo.setJiandurid(aqxjXjdInfo.getJiandurid());
        xunjianjiluInfo.setJiandurmc(aqxjXjdInfo.getJiandurmc());
        xunjianjiluInfo.setItemAddress(aqxjXjdInfo.getXujiandianweizhi());
        List<AqxjXjnrInfo> aqxjXjnrInfoList = aqxjXjnrInfoService.selectAll(id);
        xunjianjiluInfo.setAqxjXjnrInfoList(aqxjXjnrInfoList);
        model.addAttribute("dto", xunjianjiluInfo);
        return "open/gcaqxj/gcaqxjAddPage";
    }
    /**
     * 提交数据
     *
     * @return
     */
    @RequestMapping(value = "/gczlGCXJAddDo", method = RequestMethod.POST)
    public Object gczlSGZLXJAddDo(Model model, XunjianjiluInfo xunjianjiluInfo,
                                  @RequestParam(value = "file", required = false) CommonsMultipartFile[] files, HttpServletRequest request) {
        List<AqxjXjjlDetail> xjjlDetails = xunjianjiluInfo.getAqxjXjjlDetails();
        User user = getCurrentUser();
        ProProjectinfo proinfo = openCommService.getProByUserId(user.getId());
        AqxjXjjlInfo aqxjXjjlInfo = new AqxjXjjlInfo();
        aqxjXjjlInfo.setProjectid(proinfo.getId());
        aqxjXjjlInfo.setProjectname(proinfo.getProName());
        aqxjXjjlInfo.setMingcheng(xunjianjiluInfo.getName());
        aqxjXjjlInfo.setItemid(xunjianjiluInfo.getForeignid());
        aqxjXjjlInfo.setItemtype(xunjianjiluInfo.getTypeName());
        aqxjXjjlInfo.setItemadress(xunjianjiluInfo.getItemAddress());
        aqxjXjjlInfo.setZerenrid(xunjianjiluInfo.getZerenrid());
        aqxjXjjlInfo.setZerenrmc(xunjianjiluInfo.getZerenrmc());
        aqxjXjjlInfo.setJiandurid(xunjianjiluInfo.getJiandurid());
        aqxjXjjlInfo.setJiandurmc(xunjianjiluInfo.getJiandurmc());
        aqxjXjjlInfo.setJianchatime(DateUtils.getToday());
        aqxjXjjlInfo.setJianchaperson(user.getName());
        //aqxjXjjlInfo
        aqxjXjjlInfo.setJianchapc(xunjianjiluInfo.getJianchapc());
        aqxjXjjlInfo.setJieguoms(xunjianjiluInfo.getMiaoshu());
        int flag = 1;
        for (int i = 0; i < xjjlDetails.size(); i++) {
            if (xjjlDetails.get(i).getJianchajg().equals("0")) {
                flag = 0;
                break;
            }
        }
        aqxjXjjlInfo.setJianchajg(flag + "");
        aqxjXjjlInfo.setIsView("0");//设置默认未查看
        String uuid = CommUtils.getUUID();
        aqxjXjjlInfo.setId(uuid);
        aqxjXjjlInfo.setIsSend(xunjianjiluInfo.getIsSend());//设置是否推送
        aqxjXjjlInfoService.insert(aqxjXjjlInfo, user);
        /**
         * 将巡检内容详细写入数据库
         */
        for (int i = 0; i < xjjlDetails.size(); i++) {
            AqxjXjjlDetail aqxjXjjlDetail = xjjlDetails.get(i);
            aqxjXjjlDetail.setMingcheng(aqxjXjjlInfo.getMingcheng());
            aqxjXjjlDetail.setItemid(uuid);
            aqxjXjjlDetailService.insert(aqxjXjjlDetail, user);
        }
        //保存附件信息
        List<SysFujianDto> sfdtos = multFujianOpt(uuid, files, user, null);
        try {
            if (sfdtos.size() == 0) {
                System.out.println("无附件信息!");
            } else {
                sysFujianService.save(sfdtos, user);
            }

            model.addAttribute("data", renderSuccess("提交成功"));
            model.addAttribute("isDo", true);
            AqxjXjdInfo aqxjXjdInfo = aqxjXjdInfoService.selectById(xunjianjiluInfo.getForeignid());
            XunjianjiluInfo dto = new XunjianjiluInfo();
            dto.setForeignid(aqxjXjdInfo.getId());
            dto.setJianchapc(aqxjXjdInfo.getJianchapc());
            dto.setName(aqxjXjdInfo.getMingcheng());
            dto.setTypeName(aqxjXjdInfo.getTypeName());
            String lastCheckTime = aqxjXjjlInfoService.selectByMingcheng(aqxjXjdInfo.getMingcheng());
            dto.setLastCheckTime(lastCheckTime);
            dto.setZerenrid(aqxjXjdInfo.getZerenrid());
            dto.setZerenrmc(aqxjXjdInfo.getZerenrmc());
            dto.setJiandurid(aqxjXjdInfo.getJiandurid());
            dto.setJiandurmc(aqxjXjdInfo.getJiandurmc());
            dto.setItemAddress(aqxjXjdInfo.getXujiandianweizhi());
            List<AqxjXjnrInfo> aqxjXjnrInfoList = aqxjXjnrInfoService.selectAll(xunjianjiluInfo.getForeignid());
            dto.setAqxjXjnrInfoList(aqxjXjnrInfoList);
            model.addAttribute("dto", dto);
            log(null, true, "移动端工程安全巡检信息添加成功！");
            return "open/gcaqxj/gcaqxjAddPage";
        } catch (Exception e) {
            e.printStackTrace();
            return renderError("添加附件信息失败!");
        }

    }

    /**
     * 获取巡检记录日历信息
     *
     * @param xuniandianName
     * @return
     */
    @RequestMapping(value = "/getrecords", method = RequestMethod.POST)
    @ResponseBody
    public Result getRecord(String xuniandianName, String startDate, String endDate) {
        Result result = new Result();
        try {
            User user = getCurrentUser();
            ProProjectinfo proinfo = openCommService.getProByUserId(user.getId());
            Map<String, Object> condition = new HashMap<String, Object>();
            condition.put("projectName", proinfo.getProName());
            condition.put("mingcheng", xuniandianName);
            condition.put("startDate", startDate);
            condition.put("endDate", endDate);
            List<Map<String, Object>> list = aqxjXjjlInfoService.selectReportByxunjiandian(condition);
            result.setSuccess(true);
            if (list.size() == 0) {
                result.setMsg("请求时间内无数据!");
            } else {
                result.setObj(list);
            }
        } catch (Exception e) {
            result.setMsg("获取数据时出错!");
        }
        return result;
    }

    /**
     * 根据日期获取巡检记录日历信息
     *
     * @param aqxjXjjlInfoDto
     * @return
     */
    @RequestMapping(value = "/getrecordsByDays", method = RequestMethod.POST)
    @ResponseBody
    public Result getRecordByDay(AqxjXjjlInfoDto aqxjXjjlInfoDto, Integer page, Integer rows) {
        Result result = new Result();
        PageInfo pageInfo = new PageInfo(page, rows);
        try {
            User user = getCurrentUser();
            ProProjectinfo proinfo = openCommService.getProByUserId(user.getId());
            Map<String, Object> condition = new HashMap<String, Object>();
            condition.put("proiectName", proinfo.getProName());
            condition.put("mingcheng", aqxjXjjlInfoDto.getMingcheng());
            condition.put("jianchaTime", aqxjXjjlInfoDto.getJianchatime());
            condition.put("jianchaperson", aqxjXjjlInfoDto.getJianchaperson());
            condition.put("jianchajg", aqxjXjjlInfoDto.getJianchajg());
            pageInfo.setCondition(condition);
            aqxjXjjlInfoService.selectDataGrid(pageInfo);
            result.setSuccess(true);
            if (pageInfo.getTotal() == 0) {
                result.setMsg("请求时间内无数据!");
            } else {
                if((page-1)*rows ==pageInfo.getTotal()){
                    pageInfo.setRows(new ArrayList());
                }
                result.setObj(pageInfo);
            }

        } catch (Exception e) {
            result.setMsg("获取数据时出错!");
        }
        return result;
    }


    /**
     * 获取提醒我的巡检数据
     *
     * @param aqxjXjjlInfoDto
     * @param page
     * @param rows
     * @return
     */
    @RequestMapping(value = "/getPushdataGrid", method = RequestMethod.POST)
    @ResponseBody
    public Result dataGrid(AqxjXjjlInfoDto aqxjXjjlInfoDto, Integer page, Integer rows) {
        Result result = new Result();
        User user = this.getCurrentUser();
        ProProjectinfo proinfo = openCommService.getProByUserId(user.getId());
        try {
            PageInfo pageInfo = new PageInfo(page, rows);
            Map<String, Object> condition = new HashMap<String, Object>();
            //项目名称
            condition.put("proiectName", proinfo.getProName());
            //责任人
            condition.put("zerenrmc", user.getName());
            //是否查看
            condition.put("isView", aqxjXjjlInfoDto.getIsView());
            //是否推送
            condition.put("isSend", aqxjXjjlInfoDto.getIsSend());
            pageInfo.setCondition(condition);
            aqxjXjjlInfoService.selectPushDataGrid(pageInfo);
            result.setSuccess(true);
            if (pageInfo.getTotal() == 0) {
                result.setMsg("请求时间内无数据!");
            } else {
                if((page-1)*rows ==pageInfo.getTotal()){
                    pageInfo.setRows(new ArrayList());
                }
                result.setObj(pageInfo);
            }
        } catch (Exception e) {
            result.setMsg("获取数据时出错!");
        }
        return result;
    }


    /*
    * 保存文件工具
    * @param id 主表ID
    * @param files CommonsMultipartFile
    * @param user User
 */
    private List<SysFujianDto> multFujianOpt(String id, CommonsMultipartFile[] files, User user, String bz) {

        List<SysFujianDto> list = null;
        if (files != null)
            for (CommonsMultipartFile file : files) {
                SysFujianDto fj = null;
                String fileName = "";
                if (file != null) {
                    fileName = file.getOriginalFilename();
                }
                if (!StringUtils.isEmpty(fileName)) {
                    String type = fileName.substring(fileName.lastIndexOf(".") + 1);
                    String tempUid = UUID.randomUUID().toString();
                    String originalFileName = "Y" + tempUid;// 原始图片保存文件名
                    String compressFileNewName = "S" + tempUid;// 缩略图片保存文件名
                    UploadUtil uploadutil = new UploadUtil();
                    String errorMessage = uploadutil.uploadImage2(request, file, originalFileName, compressFileNewName,
                            imagePath);// 上传原始图片并生成对应的缩略图
                    if (StringUtils.isNotBlank(errorMessage)) {
                    } else {
                        fj = new SysFujianDto();
                        fj.setFileName(fileName);
                        fj.setFilePath(imagePath + "/" + originalFileName + "." + type);
                        fj.setMinImgPath(imagePath + "/" + compressFileNewName + "." + type);
                        fj.setFileType(type);
                        fj.setFileSize(file.getSize());
                        fj.setForeignId(id);

                        if (bz != null) {
                            fj.setBackupOne(bz);
                        }
                    }
                }

                if (fj != null) {
                    if (list == null) {
                        list = new ArrayList<SysFujianDto>();
                    }
                    list.add(fj);
                }
            }

        return list;
    }


    /**巡检记录详情
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/view", method = RequestMethod.GET)
    public String view(String id,Model model ){
        AqxjXjjlInfo  aqxjXjjlInfo = aqxjXjjlInfoService.selectByPKId(id);
        XunjianjiluInfo xunjianjiluInfo = new XunjianjiluInfo();
        if(null ==aqxjXjjlInfo.getJianchapc()){
            xunjianjiluInfo.setJianchapc("");
        }else{
            xunjianjiluInfo.setJianchapc(aqxjXjjlInfo.getJianchapc());
        }

        xunjianjiluInfo.setMiaoshu(aqxjXjjlInfo.getJieguoms());
        String lastCheckTime = aqxjXjjlInfoService.selectByMingcheng(aqxjXjjlInfo.getMingcheng());
        xunjianjiluInfo.setLastCheckTime(lastCheckTime);
        xunjianjiluInfo.setName(aqxjXjjlInfo.getMingcheng());
        List<AqxjXjjlDetail> aqxjXjjlDetails = aqxjXjjlDetailService.selectAll(id);
        xunjianjiluInfo.setAqxjXjjlDetails(aqxjXjjlDetails);
        model.addAttribute("dto",xunjianjiluInfo);
        try{
            List<SysFujianDto> sysFujianList =sysFujianService.findFuJianListByForeignId(id,null,null);
            model.addAttribute("sysFujianList", sysFujianList);
//            aqxjXjjlInfoService.update(id, this.getCurrentUser());
        }catch (Exception e){
            e.printStackTrace();
        }
        return "open/gcaqxj/detailView";
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @ResponseBody
    public Object update(String  id) {
        try {

            String errmessage = aqxjXjjlInfoService.update(id, this.getCurrentUser());
            if (errmessage != null) {
                log(null, false, errmessage);
                return renderError(errmessage);
            }
            log(null, true, "更新推送信息查看状态成功");
            return renderSuccess("更新成功！");
        } catch (Exception e) {
            e.printStackTrace();
            log(null, true, "更新推送信息是否查看状态失败！");
            return renderError("系统错误，请联系系统管理员!!");
        }
    }
}
