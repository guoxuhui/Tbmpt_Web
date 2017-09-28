package com.crfeb.tbmpt.zsjk.xmb.service.impl;

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
import com.crfeb.tbmpt.sys.model.User;
import com.crfeb.tbmpt.commons.base.BaseService;
import com.crfeb.tbmpt.zsjk.xmb.model.dto.ZsJkXmbXmXlXxDto;
import com.crfeb.tbmpt.zsjk.xmb.mapper.ZsJkXmbXmXlXxMapper;
import com.crfeb.tbmpt.zsjk.xmb.model.ZsJkXmbXmXlXx;
import com.crfeb.tbmpt.zsjk.xmb.service.ZsJkXmbXmXlXxService;
/**
 * <p>项目部角度 项目下线路信息Service实现类</p>
 * <p>系统：展示界面接口</p>
 * <p>模块：项目部角度模块</p>
 * <p>日期：2017-01-09</p>
 * @version 1.0
 * @author YangYJ
 */
@Service
public class ZsJkXmbXmXlXxServiceImpl extends CommonServiceImpl<ZsJkXmbXmXlXxMapper, ZsJkXmbXmXlXx> implements ZsJkXmbXmXlXxService{

    @Autowired
    private ZsJkXmbXmXlXxMapper zsJkXmbXmXlXxMapper;

    @Override
    public void selectDataGrid(PageInfo pageInfo) throws Exception {
       Page<ZsJkXmbXmXlXx> page = new Page<ZsJkXmbXmXlXx>(pageInfo.getNowpage(), pageInfo.getSize());
       Map<String, Object> condition = pageInfo.getCondition();
       List<ZsJkXmbXmXlXx> list = zsJkXmbXmXlXxMapper.selectZsJkXmbXmXlXxList(page,condition);
       pageInfo.setRows(list);
       pageInfo.setTotal(page.getTotal());
    }

    @Override
    public String save(ZsJkXmbXmXlXxDto zsJkXmbXmXlXxDto,User user) throws Exception {
    	  String errorMessage = "";
        ZsJkXmbXmXlXx zsJkXmbXmXlXx = new ZsJkXmbXmXlXx();
        BeanUtils.copyProperties(zsJkXmbXmXlXxDto, zsJkXmbXmXlXx);
    	  zsJkXmbXmXlXxMapper.insert(zsJkXmbXmXlXx);
    	  return errorMessage;
    }

	   @Override
    public String del(String[] ids,User user) throws Exception {
    	  String errorMessage = "";
		  List<String> idList = Arrays.asList(ids);
		  zsJkXmbXmXlXxMapper.deleteBatchIds(idList);
    	  return errorMessage;
    }

    @Override
    public String update(ZsJkXmbXmXlXxDto zsJkXmbXmXlXxDto,User user) throws Exception {
    	  String errorMessage = "";
        ZsJkXmbXmXlXx zsJkXmbXmXlXx = this.zsJkXmbXmXlXxMapper.selectById(zsJkXmbXmXlXxDto.getId());
		  zsJkXmbXmXlXxMapper.updateById(zsJkXmbXmXlXx);
    	  return errorMessage;
    }

	   @Override
	   public ZsJkXmbXmXlXx findById(String id) throws Exception {
		   return zsJkXmbXmXlXxMapper.selectById(id);
	   }

    @Override
    public String checkClumIfexits(ZsJkXmbXmXlXxDto zsJkXmbXmXlXxDto, String[] clumNames) throws Exception {
		   String id = zsJkXmbXmXlXxDto.getId();
		   Map map = BeanUtils.toMap(zsJkXmbXmXlXxDto);
		   Map<String,Object> columnMap = new HashMap<String,Object>();
		   for (String clum : clumNames) {
		        Object ss =  map.get(clum);
		        columnMap.put(clum, ss);
		   }
		   List<ZsJkXmbXmXlXx> lists = zsJkXmbXmXlXxMapper.selectByMap(columnMap);
		   if(StringUtils.isBlank(id)){//新增
			    if(lists!=null&&lists.size()>0)return "当前数据已存在!";
		   }else{//修改
			    for (ZsJkXmbXmXlXx zsJkXmbXmXlXx2 : lists) {
				   String newId = zsJkXmbXmXlXx2.getId();
				   if(!newId.equals(id)){
					   return "当前数据已存在!";
				   }
			    }
		   }
		   return null;
    }

}