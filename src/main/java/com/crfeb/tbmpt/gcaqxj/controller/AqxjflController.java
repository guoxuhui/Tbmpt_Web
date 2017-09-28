package com.crfeb.tbmpt.gcaqxj.controller;

import com.crfeb.tbmpt.commons.base.BaseController;
import com.crfeb.tbmpt.commons.result.Tree;
import com.crfeb.tbmpt.commons.utils.PageInfo;
import com.crfeb.tbmpt.commons.utils.StringUtils;
import com.crfeb.tbmpt.gcaqxj.model.AqxjXjflInfo;
import com.crfeb.tbmpt.gcaqxj.model.dto.AqxjXjflInfoDto;
import com.crfeb.tbmpt.gcaqxj.service.IAqxjXjflInfoService;
import com.crfeb.tbmpt.project.model.ProProjectinfo;
import com.crfeb.tbmpt.project.service.IProProjectinfoService;
import com.crfeb.tbmpt.sys.model.Orgz;
import com.crfeb.tbmpt.sys.model.User;
import com.crfeb.tbmpt.sys.service.IOrgzService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.*;

/**
 * @description：巡检点分类管理
 * @author：tbmpt
 * @date： 2017/7/20.
 */
@Controller
@RequestMapping("/gcaqxj/xjdfl")
public class AqxjflController  extends BaseController {
    private static final Logger LOGGER = LogManager.getLogger(AqxjflController.class);

    @Autowired
    private IAqxjXjflInfoService aqxjXjflInfoService;
    @Autowired
    IProProjectinfoService proProjectinfoService;

    @Autowired
    IOrgzService iOrgzService;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public String manager() {
        return "gcaqxj/xunjiandianfenlei";
    }

    /**
     * 获取巡检点分类管理-easyUi列表
     * @param aqxjXjflInfoDto 查询条件dto
     * @param page
     * @param rows
     * @param sort
     * @param order
     * @return 返回查询结果信息
     */
    @RequestMapping(value = "/dataGrid", method = RequestMethod.POST)
    @ResponseBody
    public Object dataGrid(AqxjXjflInfoDto aqxjXjflInfoDto,Integer page, Integer rows, String sort, String order)
    {
        User user = this.getCurrentUser();
        ProProjectinfo proProjectinfo = proProjectinfoService.getProjectInfoByUserId(user.getId());
        PageInfo pageInfo = new PageInfo(page, rows, sort, order);
        Map<String, Object> condition = new HashMap<String, Object>();
        condition.put("proId",proProjectinfo.getId());
        condition.put("proiectName", aqxjXjflInfoDto.getProiectName());
        condition.put("typeName", aqxjXjflInfoDto.getTypeName());
        pageInfo.setCondition(condition);
        aqxjXjflInfoService.selectDataGrid(pageInfo);
        return pageInfo;
    }


    /**
     * 添加巡检点分类信息
     * @param aqxjXjflInfo
     * @return
     */
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public Object add(AqxjXjflInfo aqxjXjflInfo) {
        String errmessage = aqxjXjflInfoService.checkClumIfexits(aqxjXjflInfo,new String[]{"proiectId","typeName"});
        User user = this.getCurrentUser();
        if(errmessage!=null){
            log(null, false, errmessage);
            return renderError(errmessage);
        }
        if(StringUtils.isEmpty(aqxjXjflInfo.getParentId())){
            aqxjXjflInfo.setIsParent("0");
        }else{
            aqxjXjflInfo.setIsParent("1");
        }
        aqxjXjflInfoService.insert(aqxjXjflInfo,user);
        log(null, true, "巡检点分类管理信息添加成功！");
        return renderSuccess("添加成功！");
    }

    /**
     * 删除巡检分类信息
     * @param ids
     * @return
     */
    @RequestMapping("/delete")
    @ResponseBody
    public Object delete(String ids) {
        User user = this.getCurrentUser();
        List<AqxjXjflInfo>  aqxjXjflInfoList = aqxjXjflInfoService.selectListByParnetId(ids);
        if(aqxjXjflInfoList.size()==0){
            aqxjXjflInfoService.deleteById(ids);
            LOGGER.info("巡检分类管理信息删除数据成功。");
            this.log(null, true, "用户"+user.getName()+"删除记录成功。id:"+ids);
            return renderSuccess("删除成功！");
        }else{
            return renderError("该分类下有子类不允许删除!");
        }
    }

    /**
     * 修改巡检点分类信息
     * @param aqxjXjflInfo 要修改的实体信息
     * @return 返回编辑成功/失败的提示信息
     */
    @RequestMapping("/edit")
    @ResponseBody
    public Object edit(AqxjXjflInfo aqxjXjflInfo) {
        User user = this.getCurrentUser();
        AqxjXjflInfo oldEntity = aqxjXjflInfoService.selectById(aqxjXjflInfo.getId());
        if(StringUtils.isEmpty(aqxjXjflInfo.getParentId())){
            aqxjXjflInfo.setIsParent("0");
        }else{
            aqxjXjflInfo.setIsParent("1");
        }
        if(null == oldEntity || StringUtils.isBlank(oldEntity.getId())){
            return renderError("数据不存在,请刷新重试!");
        }else{
            //该节点为父节点 且无子节点随意修改
            if(oldEntity.getIsParent().equals("0")){
                //查询该节点下的子节点信息
                List<AqxjXjflInfo>  aqxjXjflInfoList = aqxjXjflInfoService.selectListByParnetId(oldEntity.getId());
                if(aqxjXjflInfoList.size()==0){
                    String errmessage = aqxjXjflInfoService.update(aqxjXjflInfo, user);
                    if(StringUtils.isNotBlank(errmessage)) return renderError(errmessage);//更新失败
                    LOGGER.info("巡检点分类信息修改数据成功。");
                    this.log(null, true, "用户"+user.getName()+"修改记录成功。id:"+aqxjXjflInfo.getId());
                    return renderSuccess("编辑成功!");
                }else{
                    //包含子节点,当要修改 不能修改
                    return renderError("该分类下有子类不允许修改!");
//                    if(!StringUtils.isEmpty(aqxjXjflInfo.getParentId())){
//                        return renderError("该分类下有子类不允许修改其所属父类!");
//                    }else{
//                        //为子节点可以修改
//                        String errmessage = aqxjXjflInfoService.update(aqxjXjflInfo, user);
//                        if(StringUtils.isNotBlank(errmessage)) return renderError(errmessage);//更新失败
//                        LOGGER.info("巡检点分类信息修改数据成功。");
//                        this.log(null, true, "用户"+user.getName()+"修改记录成功。id:"+aqxjXjflInfo.getId());
//                        return renderSuccess("编辑成功!");
//                    }
                }
            }else{
                //为子节点可以修改
                String errmessage = aqxjXjflInfoService.update(aqxjXjflInfo, user);
                if(StringUtils.isNotBlank(errmessage)) return renderError(errmessage);//更新失败
                LOGGER.info("巡检点分类信息修改数据成功。");
                this.log(null, true, "用户"+user.getName()+"修改记录成功!id:"+aqxjXjflInfo.getId());
                return renderSuccess("编辑成功!");
            }
        }

    }
    /**
     * 获取所有巡检点分类信息(返回多个巡检点分类列表)
     */
    @RequestMapping(value = "/getType", method = RequestMethod.POST)
    @ResponseBody
    public Object getTypes() {
        User user = this.getCurrentUser();
        ProProjectinfo proProjectinfo = proProjectinfoService.getProjectInfoByUserId(user.getId());
        return aqxjXjflInfoService.getAllTypes(proProjectinfo.getId());
    }

    /**
     * 获取所有包含子分类巡检点分类信息(返回多个巡检点分类列表)
     */
    @RequestMapping(value = "/getParentType", method = RequestMethod.POST)
    @ResponseBody
    public Object getParentType() {
        User user = this.getCurrentUser();
        ProProjectinfo proProjectinfo = proProjectinfoService.getProjectInfoByUserId(user.getId());
        return aqxjXjflInfoService.getTypes(proProjectinfo.getId());
    }
    /**
     * 根据工程id获取分类信息
     * @param proid
     * @return
     */
    @RequestMapping(value = "/getFl", method = RequestMethod.POST)
    @ResponseBody
    public Object fltree(String proid) {
        List<Tree> trees=null;
        if(proid!=null&&proid!=""){
            trees=aqxjXjflInfoService.selectflTree(proid);
        }else{
            trees=aqxjXjflInfoService.selectTree();
        }
        return trees;
    }

    /**
     * 根据工程id获取分类信息
     * @return
     */
    @RequestMapping(value = "/treeGrid", method = RequestMethod.POST)
    @ResponseBody
    public Object treeGrid() {
        List<AqxjXjflInfo> trees=null;
        User user = getCurrentUser();
        Orgz orgz =iOrgzService.getOrgzInfoByUserId(user.getId());
        String proid ="";
        if(orgz.getType()==0){
            trees=aqxjXjflInfoService.getAllTypes(proid); //传入空的项目ID
        }else {
            ProProjectinfo proProjectinfo = proProjectinfoService.getProjectInfoByUserId(user.getId());
             proid = proProjectinfo.getId();
            trees=aqxjXjflInfoService.getAllTypes(proid);
        }
        return trees;
    }
}
