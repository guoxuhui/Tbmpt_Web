package com.crfeb.tbmpt.gczlys.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.crfeb.tbmpt.project.model.ProProjectinfo;
import com.crfeb.tbmpt.project.service.IProDwgcInfoService;
import com.crfeb.tbmpt.project.service.IProProjectinfoService;
import com.crfeb.tbmpt.sys.model.Orgz;
import com.crfeb.tbmpt.sys.service.IOrgzService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody; 
 
import com.crfeb.tbmpt.commons.base.BaseController;
import com.crfeb.tbmpt.commons.utils.PageInfo;
import com.crfeb.tbmpt.commons.utils.StringUtils; 
import com.crfeb.tbmpt.gczlys.model.YanShouYingXiang;
import com.crfeb.tbmpt.gczlys.model.dto.YanShouYingXiangDto;
import com.crfeb.tbmpt.gczlys.service.YanShouYingXiangService;
import com.crfeb.tbmpt.sys.model.User;



@Controller
@RequestMapping("/gczlys/yanShouYingXiang")
public class YanShouYingXiangController extends BaseController{
	private static final Logger LOGGER = LogManager.getLogger(YanShouYingXiangController.class);
	
	@Autowired
	private YanShouYingXiangService yanShouYingXiangService;

	@Autowired
	IProProjectinfoService proProjectinfoService;

	@Autowired
	IOrgzService iOrgzService;
	
	
	
	 @RequestMapping(value = "", method = RequestMethod.GET)
	    public String manager() {
	        return "gczlys/ysyxzl/YanShouYingXiang";
	    }
	
	 /**
     * 获取结构施工质量基础数据-具体位置easyUi列表
     * @param pageNo 页码
     * @param pageSize 每页条数
     * @param dto 查询条件dto
     * @return 返回查询结果信息
     */
    @RequestMapping(value = "/dataGrid", method = RequestMethod.POST)
    @ResponseBody
    public Object dataGrid(YanShouYingXiangDto yanShouYingXiang,Integer page, Integer rows, String sort, String order)
    {
		User user = this.getCurrentUser();
		ProProjectinfo proProjectinfo = proProjectinfoService.getProjectInfoByUserId(user.getId());
		//获取用户组织机构信息
		Orgz orgz =iOrgzService.getOrgzInfoByUserId(user.getId());
        PageInfo pageInfo = new PageInfo(page, rows, sort, order);
        Map<String, Object> condition = new HashMap<String, Object>();

		if(orgz.getType()==0&&(null ==yanShouYingXiang.getProjectname()||yanShouYingXiang.getProjectname().equals(""))){
		}else if(orgz.getType()==0&&!(null ==yanShouYingXiang.getProjectname()||yanShouYingXiang.getProjectname().equals(""))){
			//项目名称
			condition.put("projectname", yanShouYingXiang.getProjectname());
		}else {
			condition.put("projectname", findProjectByCurrentUser().getProName());
			condition.put("createUser", this.getCurrentUser().getEmpId());
		}

		//condition.put("projectname",proProjectinfo.getProName());
        condition.put("miaoshu", yanShouYingXiang.getMiaoshu());
        condition.put("gcbuwei", yanShouYingXiang.getGcbuwei());
        condition.put("gongxu", yanShouYingXiang.getGongxu());
        condition.put("banzu", yanShouYingXiang.getBanzu());
        condition.put("yanshousj", yanShouYingXiang.getYanshousj());
        condition.put("startYanshousj", yanShouYingXiang.getStartYanshousj());
        condition.put("endYanshousj", yanShouYingXiang.getEndYanshousj());

        pageInfo.setCondition(condition);
        yanShouYingXiangService.selectDataGrid(pageInfo);
        return pageInfo;
    }
    
    
    /**
     * 添加验收影像资料信息
     * @param gczlYdxjGPZLDDInfo 要新增的实体信息
     * @return 返回保存成功/失败的提示信息
     */
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public Object add(YanShouYingXiang yanShouYingXiang) {
    	
    	String errmessage = yanShouYingXiangService.checkClumIfexits(yanShouYingXiang,new String[]{"projectid","gcbuwei","gongxu","miaoshu"});
    	
    	User user = this.getCurrentUser();
    	if(errmessage!=null){
    		log(null, false, errmessage);
    		return renderError(errmessage);
    	}
    	yanShouYingXiangService.insert(yanShouYingXiang,user);
    	log(null, true, "验收影像资料信息添加成功！");
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
    	yanShouYingXiangService.deleteBatchIds(idlist);
    	LOGGER.info("验收影像资料信息删除数据成功。");
    	this.log(null, true, "用户"+user.getName()+"删除记录成功。ids:"+ids);
        return renderSuccess("删除成功！");
    }
    
    
    /**
     * 修改验收影像资料信息o
     * @param yanShouYingXiang 要修改的实体信息
     * @return 返回编辑成功/失败的提示信息
     */
    @RequestMapping("/edit")
    @ResponseBody
    public Object edit(YanShouYingXiang yanShouYingXiang) {
    	User user = this.getCurrentUser();
    	YanShouYingXiang oldEntity = this.yanShouYingXiangService.selectById(yanShouYingXiang.getId());
    	if(null == oldEntity || StringUtils.isBlank(oldEntity.getId())){
    		return renderError("数据不存在,请刷新重试!");
    	}
    	String errmessage = yanShouYingXiangService.checkClumIfexits(yanShouYingXiang,new String[]{"projectid","gcbuwei","gongxu","miaoshu"});
    	if(StringUtils.isNotBlank(errmessage)) return renderError(errmessage);//校验失败,数据不可更新
    	errmessage = yanShouYingXiangService.update(yanShouYingXiang,this.getCurrentUser());
    	if(StringUtils.isNotBlank(errmessage)) return renderError(errmessage);//更新失败
    	LOGGER.info("验收影像资料信息修改数据成功。");
    	this.log(null, true, "用户"+user.getName()+"修改记录成功。id:"+yanShouYingXiang.getId());
        return renderSuccess("编辑成功!");
    }
    
    
    
}
