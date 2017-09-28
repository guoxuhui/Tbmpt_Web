package com.crfeb.tbmpt.aqsc.base.service.impl;

import java.util.List;
import java.util.Map;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.baomidou.framework.service.impl.CommonServiceImpl;
import com.baomidou.mybatisplus.plugins.Page;
import com.crfeb.tbmpt.commons.utils.PageInfo;
import com.crfeb.tbmpt.commons.utils.BeanUtils;
import com.crfeb.tbmpt.commons.utils.StringUtils;
import com.crfeb.tbmpt.project.mapper.ProProjectinfoMapper;
import com.crfeb.tbmpt.project.model.ProProjectinfo;
import com.crfeb.tbmpt.sys.model.User;
import com.crfeb.tbmpt.commons.base.BaseService;
import com.crfeb.tbmpt.aqsc.base.model.dto.WorklogDto;
import com.crfeb.tbmpt.aqsc.base.mapper.WorklogMapper;
import com.crfeb.tbmpt.aqsc.base.model.Worklog;
import com.crfeb.tbmpt.aqsc.base.service.WorklogService;
/**
 * <p>工作日志Service实现类</p>
 * <p>系统：安全生产管理</p>
 * <p>模块：基础信息</p>
 * <p>日期：2017-02-20</p>
 * @version 1.0
 * @author wangbinbin
 */
@Service
public class WorklogServiceImpl extends CommonServiceImpl<WorklogMapper, Worklog> implements WorklogService{

    @Autowired
    private WorklogMapper worklogMapper;
    @Autowired
    private ProProjectinfoMapper projectinfoMapper;

    @Override
    public void selectDataGrid(PageInfo pageInfo) throws Exception {
       Page<Worklog> page = new Page<Worklog>(pageInfo.getNowpage(), pageInfo.getSize());
       Map<String, Object> condition = pageInfo.getCondition();
       List<Worklog> list = worklogMapper.selectWorklogList(page,condition);
       pageInfo.setRows(list);
       pageInfo.setTotal(page.getTotal());
    }

    @Override
    public String save(WorklogDto worklogDto,User user) throws Exception {
    	  String errorMessage = "";
        Worklog worklog = new Worklog();
        BeanUtils.copyProperties(worklogDto, worklog);
    	  BaseService.operatorMessage(worklog, null, user);
    	  List<ProProjectinfo> projects = projectinfoMapper.selectByUserId(user.getId());
	      	if(projects!=null&&projects.size()==1){
	      		ProProjectinfo pp  =  projects.get(0);
	      		worklog.setXmBh(pp.getId());
	      		worklog.setXmName(pp.getProName());
	      	}
    	  worklogMapper.insert(worklog);
    	  return errorMessage;
    }

	   @Override
    public String del(String[] ids,User user) throws Exception {
    	  String errorMessage = "";
		  List<String> idList = Arrays.asList(ids);
		  worklogMapper.deleteBatchIds(idList);
    	  return errorMessage;
    }

    @Override
    public String update(WorklogDto worklogDto,User user) throws Exception {
    	  String errorMessage = "";
        Worklog worklog = this.worklogMapper.selectById(worklogDto.getId());
        errorMessage = BaseService.operatorMessage(worklog, worklogDto, user);
        BeanUtils.copyProperties(worklogDto, worklog);
        List<ProProjectinfo> projects = projectinfoMapper.selectByUserId(user.getId());
      	if(projects!=null&&projects.size()==1){
      		ProProjectinfo pp  =  projects.get(0);
      		worklog.setXmBh(pp.getId());
      		worklog.setXmName(pp.getProName());
      	}
		  worklogMapper.updateById(worklog);
    	  return errorMessage;
    }

	   @Override
	   public Worklog findById(String id) throws Exception {
		   return worklogMapper.selectById(id);
	   }

    @Override
    public String checkClumIfexits(WorklogDto worklogDto, String[] clumNames) throws Exception {
		   String id = worklogDto.getId();
		   Map map = BeanUtils.toMap(worklogDto);
		   Map<String,Object> columnMap = new HashMap<String,Object>();
		   for (String clum : clumNames) {
		        Object ss =  map.get(clum);
		        columnMap.put(clum, ss);
		   }
		   List<Worklog> lists = worklogMapper.selectByMap(columnMap);
		   if(StringUtils.isBlank(id)){//新增
			    if(lists!=null&&lists.size()>0)return "当前数据已存在!";
		   }else{//修改
			    for (Worklog worklog2 : lists) {
				   String newId = worklog2.getId();
				   if(!newId.equals(id)){
					   return "当前数据已存在!";
				   }
			    }
		   }
		   return null;
    }

    @Override
	public void selectDataGrid(PageInfo pageInfo, User user) throws Exception {
		Map<String, Object> condition = pageInfo.getCondition();
		if(condition==null){
			condition = new HashMap<>();
		}
		condition.put("sqlPurview", this.sqlPurview(null,"xmBh",user));
		pageInfo.setCondition(condition);
		this.selectDataGrid(pageInfo);
		
	}
	
	public String sqlPurview(String tableAnotherName,String gcBhTableClum,User user){
    	String sql = " and "+(StringUtils.isNotBlank(tableAnotherName)?tableAnotherName:"t")+"."+(StringUtils.isNotBlank(gcBhTableClum)?gcBhTableClum:"gc_Bh")+" in (";
    	List<ProProjectinfo> projects = projectinfoMapper.selectByUserId(user.getId());
		for (int i = 0; i < projects.size(); i++) {
			if(i!=0)sql+=",";
			sql+="'"+projects.get(i).getId()+"'";
		}
    	sql+=")";
    	return sql;
    }

}