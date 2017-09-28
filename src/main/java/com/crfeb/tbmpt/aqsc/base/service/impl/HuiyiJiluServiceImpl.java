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
import com.crfeb.tbmpt.aqsc.base.model.dto.HuiyiJiluDto;
import com.crfeb.tbmpt.aqsc.base.mapper.HuiyiJiluMapper;
import com.crfeb.tbmpt.aqsc.base.model.HuiyiJilu;
import com.crfeb.tbmpt.aqsc.base.service.HuiyiJiluService;
/**
 * <p>培训记录Service实现类</p>
 * <p>系统：安全生产管理</p>
 * <p>模块：基础信息</p>
 * <p>日期：2017-02-21</p>
 * @version 1.0
 * @author wangbinbin
 */
@Service
public class HuiyiJiluServiceImpl extends CommonServiceImpl<HuiyiJiluMapper, HuiyiJilu> implements HuiyiJiluService{

    @Autowired
    private HuiyiJiluMapper huiyiJiluMapper;
    @Autowired
    private ProProjectinfoMapper projectinfoMapper;

    @Override
    public void selectDataGrid(PageInfo pageInfo) throws Exception {
       Page<HuiyiJilu> page = new Page<HuiyiJilu>(pageInfo.getNowpage(), pageInfo.getSize());
       Map<String, Object> condition = pageInfo.getCondition();
       List<HuiyiJilu> list = huiyiJiluMapper.selectHuiyiJiluList(page,condition,pageInfo.getSort(),pageInfo.getOrder());
       pageInfo.setRows(list);
       pageInfo.setTotal(page.getTotal());
    }

    @Override
    public String save(HuiyiJiluDto huiyiJiluDto,User user) throws Exception {
    	  String errorMessage = "";
        HuiyiJilu huiyiJilu = new HuiyiJilu();
        BeanUtils.copyProperties(huiyiJiluDto, huiyiJilu);
    	  BaseService.operatorMessage(huiyiJilu, null, user);
    	  
    	  List<ProProjectinfo> projects = projectinfoMapper.selectByUserId(user.getId());
	      	if(projects!=null&&projects.size()==1){
	      		ProProjectinfo pp  =  projects.get(0);
	      		huiyiJilu.setXmBh(pp.getId());
	      		huiyiJilu.setXmName(pp.getProName());
	      	}
    	  huiyiJiluMapper.insert(huiyiJilu);
    	  return errorMessage;
    }

	   @Override
    public String del(String[] ids,User user) throws Exception {
    	  String errorMessage = "";
		  List<String> idList = Arrays.asList(ids);
		  huiyiJiluMapper.deleteBatchIds(idList);
    	  return errorMessage;
    }

    @Override
    public String update(HuiyiJiluDto huiyiJiluDto,User user) throws Exception {
    	  String errorMessage = "";
        HuiyiJilu huiyiJilu = this.huiyiJiluMapper.selectById(huiyiJiluDto.getId());
       errorMessage = BaseService.operatorMessage(huiyiJilu, huiyiJiluDto, user);
		  BeanUtils.copyProperties(huiyiJiluDto, huiyiJilu);
		  List<ProProjectinfo> projects = projectinfoMapper.selectByUserId(user.getId());
	      	if(projects!=null&&projects.size()==1){
	      		ProProjectinfo pp  =  projects.get(0);
	      		huiyiJilu.setXmBh(pp.getId());
	      		huiyiJilu.setXmName(pp.getProName());
	      	}
		  huiyiJiluMapper.updateById(huiyiJilu);
    	  return errorMessage;
    }

	   @Override
	   public HuiyiJilu findById(String id) throws Exception {
		   return huiyiJiluMapper.selectById(id);
	   }

    @Override
    public String checkClumIfexits(HuiyiJiluDto huiyiJiluDto, String[] clumNames) throws Exception {
		   String id = huiyiJiluDto.getId();
		   Map map = BeanUtils.toMap(huiyiJiluDto);
		   Map<String,Object> columnMap = new HashMap<String,Object>();
		   for (String clum : clumNames) {
		        Object ss =  map.get(clum);
		        columnMap.put(clum, ss);
		   }
		   List<HuiyiJilu> lists = huiyiJiluMapper.selectByMap(columnMap);
		   if(StringUtils.isBlank(id)){//新增
			    if(lists!=null&&lists.size()>0)return "当前数据已存在!";
		   }else{//修改
			    for (HuiyiJilu huiyiJilu2 : lists) {
				   String newId = huiyiJilu2.getId();
				   if(!newId.equals(id)){
					   return "当前数据已存在!";
				   }
			    }
		   }
		   return null;
    }

}