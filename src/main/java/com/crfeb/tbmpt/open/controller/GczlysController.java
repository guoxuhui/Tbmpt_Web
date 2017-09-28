package com.crfeb.tbmpt.open.controller;

import com.crfeb.tbmpt.commons.base.BaseController;
import com.crfeb.tbmpt.commons.result.Result;
import com.crfeb.tbmpt.commons.utils.*;
import com.crfeb.tbmpt.gcaqxj.service.IAqxjXjjlInfoService;
import com.crfeb.tbmpt.gczl.base.model.dto.GczlYdxjSGZLXJInfoDto;
import com.crfeb.tbmpt.gczlys.model.YanShouYingXiang;
import com.crfeb.tbmpt.gczlys.model.dto.YanShouYingXiangDto;
import com.crfeb.tbmpt.gczlys.service.YanShouYingXiangService;
import com.crfeb.tbmpt.open.model.DicInfo;
import com.crfeb.tbmpt.open.service.IOpenCommService;
import com.crfeb.tbmpt.open.service.IOpenGczlService;
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
 * 工程质量验收Controller
 * Created by ztzn on 2017/8/9.
 */
@Controller
@RequestMapping("/open/gczlys")
public class GczlysController   extends BaseController {

    @Autowired
    IOpenCommService openCommService;

    @Autowired
    private ProProjectinfoMapper projectinfoMapper;

    @Autowired
    YanShouYingXiangService yanShouYingXiangService;

    @Autowired
    SysFujianService sysFujianService;

    private String imagePath = "/upload/gczlys/pic/"+ DateUtils.format(new Date(), "yyyyMMdd");

    /**
     * 添加页面
     * @param model
     * @param token
     * @return
     */
    @RequestMapping(value = "/gczlysAddPage", method = RequestMethod.GET)
    public String gczlHomePape(Model model, String token) {
//        long startTime = System.currentTimeMillis();
        if (!StringUtils.isEmpty(token)) {
            openCommService.relogin(token);
        }
        User user = getCurrentUser();
        ProProjectinfo proinfo = openCommService.getProByUserId(user.getId());
        model.addAttribute("user", user);
        model.addAttribute("pro", proinfo);
        return "open/gczlys/gczlGCZLYS";
    }

    /**
     * 获取当前登录人所在项目信息
     *
     * @return json
     */
    @RequestMapping(value = "/getProInfos", method = RequestMethod.POST)
    @ResponseBody
    public Result getProjectInfo() {
        Result result = new Result();
        User user = getCurrentUser();
        try {
            List<ProProjectinfo> proProjectinfoList = projectinfoMapper.selectByUserId(user.getId());
            result.setSuccess(true);
            if (proProjectinfoList.size() == 0) {
                result.setMsg("当前用户无项目信息!");
            } else {
                result.setObj(proProjectinfoList);
            }
        } catch (Exception e) {
            e.printStackTrace();
            result.setSuccess(false);
            result.setMsg("获取项目数据异常!");
        }
        return result;
    }

    /**
     * 提交工程影像质量数据
     *
     * @return
     */
    @RequestMapping(value = "/gczlGCZLYSAddDo", method = RequestMethod.POST)
    public Object gczlSGZLXJAddDo(Model model, YanShouYingXiangDto yanShouYingXiangDto,
                                  @RequestParam(value = "file", required = false) CommonsMultipartFile[] files, HttpServletRequest request) {
        yanShouYingXiangDto.setYanshousj( DateUtils.getToday());
        User user = this.getCurrentUser();
        yanShouYingXiangDto.setYanshour(user.getName());
        String uuid = CommUtils.getUUID();
        yanShouYingXiangDto.setId(uuid);
        YanShouYingXiang yanShouYingXiang = new YanShouYingXiang();
        BeanUtils.copyProperties(yanShouYingXiangDto,yanShouYingXiang);
        yanShouYingXiangService.insert(yanShouYingXiang,user);
        //保存附件信息
        List<SysFujianDto> sfdtos = multFujianOpt(uuid,files,user,null);
        try{
            sysFujianService.save(sfdtos,user);
            model.addAttribute("data", renderSuccess("提交成功"));
            model.addAttribute("isDo", true);
            ProProjectinfo proinfo = openCommService.getProByUserId(user.getId());
            model.addAttribute("user", user);

            model.addAttribute("pro", proinfo);
            log(null, true, "移动端工程质量验收影像信息添加成功！");
            return "open/gczlys/gczlGCZLYS";
        }catch (Exception e){
            return renderError("添加附件信息失败!");
        }


    }

    /**
     * 获取巡检记录日历信息
     * @param yanShouYingXiangDto
     * @return
     */
    @RequestMapping(value = "/getrecords", method = RequestMethod.POST)
    @ResponseBody
    public Result getRecord(YanShouYingXiangDto yanShouYingXiangDto) {
        Result result = new Result();
        try {

            Map<String, Object> condition = new HashMap<String, Object>();
            condition.put("projectname", yanShouYingXiangDto.getProjectname());
            condition.put("projectId", yanShouYingXiangDto.getProjectid());
            condition.put("yanshour", yanShouYingXiangDto.getYanshour());
            condition.put("startYanshousj", yanShouYingXiangDto.getStartYanshousj());
            condition.put("endYanshousj", yanShouYingXiangDto.getEndYanshousj());
            List<Map<String, Object>> list = yanShouYingXiangService.selectReport(condition);
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

    @RequestMapping(value = "/dataGrid", method = RequestMethod.POST)
    @ResponseBody
    public Object dataGrid(YanShouYingXiangDto yanShouYingXiang,Integer page, Integer rows, String sort, String order)
    {
        Result result =  new Result();
        try{
            PageInfo pageInfo = new PageInfo(page, rows);
            Map<String, Object> condition = new HashMap<String, Object>();
            condition.put("miaoshu", yanShouYingXiang.getMiaoshu());
            condition.put("gcbuwei", yanShouYingXiang.getGcbuwei());
            condition.put("gongxu", yanShouYingXiang.getGongxu());
            condition.put("banzu", yanShouYingXiang.getBanzu());
            condition.put("yanshousj", yanShouYingXiang.getYanshousj());
            condition.put("startYanshousj", yanShouYingXiang.getStartYanshousj());
            condition.put("endYanshousj", yanShouYingXiang.getEndYanshousj());
            condition.put("yanshour", yanShouYingXiang.getYanshour());
            condition.put("createUser", this.getCurrentUser().getEmpId());
            pageInfo.setCondition(condition);
            yanShouYingXiangService.selectDataGridList(pageInfo);
            result.setSuccess(true);
            if (pageInfo.getTotal() == 0) {
                result.setMsg("请求时间内无数据!");
            } else {
                if((page-1)*rows ==pageInfo.getTotal()){
                    pageInfo.setRows(new ArrayList());
                }
                result.setObj(pageInfo);

            }
        }catch (Exception e){
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
        private List<SysFujianDto> multFujianOpt(String id,CommonsMultipartFile[] files,User user,String bz){

        List<SysFujianDto> list = null;
        if(files != null)
            for(CommonsMultipartFile file : files){
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

                        if(bz != null){
                            fj.setBackupOne(bz);
                        }
                    }
                }

                if(fj != null){
                    if(list == null){
                        list = new ArrayList<SysFujianDto>();
                    }
                    list.add(fj);
                }
            }

        return list;
    }
}
