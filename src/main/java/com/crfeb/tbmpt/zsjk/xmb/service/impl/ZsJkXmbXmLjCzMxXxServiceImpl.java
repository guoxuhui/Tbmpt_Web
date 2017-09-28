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
import com.crfeb.tbmpt.zsjk.xmb.model.dto.ZsJkXmbXmLjCzMxXxDto;
import com.crfeb.tbmpt.zsjk.xmb.mapper.ZsJkXmbXmLjCzMxXxMapper;
import com.crfeb.tbmpt.zsjk.xmb.model.ZsJkXmbXmLjCzMxXx;
import com.crfeb.tbmpt.zsjk.xmb.service.ZsJkXmbXmLjCzMxXxService;
/**
 * <p>项目部角度  当前项目的累计产值明细信息Service实现类</p>
 * <p>系统：展示界面接口</p>
 * <p>模块：项目部角度模块</p>
 * <p>日期：2017-01-09</p>
 * @version 1.0
 * @author YangYJ
 */
@Service
public class ZsJkXmbXmLjCzMxXxServiceImpl extends CommonServiceImpl<ZsJkXmbXmLjCzMxXxMapper, ZsJkXmbXmLjCzMxXx> implements ZsJkXmbXmLjCzMxXxService{

    @Autowired
    private ZsJkXmbXmLjCzMxXxMapper zsJkXmbXmLjCzMxXxMapper;

    @Override
    public void selectDataGrid(PageInfo pageInfo) throws Exception {
       Page<ZsJkXmbXmLjCzMxXx> page = new Page<ZsJkXmbXmLjCzMxXx>(pageInfo.getNowpage(), pageInfo.getSize());
       Map<String, Object> condition = pageInfo.getCondition();
       List<ZsJkXmbXmLjCzMxXx> list = zsJkXmbXmLjCzMxXxMapper.selectZsJkXmbXmLjCzMxXxList(page,condition);
       pageInfo.setRows(list);
       pageInfo.setTotal(page.getTotal());
    }

    @Override
    public String save(ZsJkXmbXmLjCzMxXxDto zsJkXmbXmLjCzMxXxDto,User user) throws Exception {
    	  String errorMessage = "";
        ZsJkXmbXmLjCzMxXx zsJkXmbXmLjCzMxXx = new ZsJkXmbXmLjCzMxXx();
        BeanUtils.copyProperties(zsJkXmbXmLjCzMxXxDto, zsJkXmbXmLjCzMxXx);
    	  zsJkXmbXmLjCzMxXxMapper.insert(zsJkXmbXmLjCzMxXx);
    	  return errorMessage;
    }

	   @Override
    public String del(String[] ids,User user) throws Exception {
    	  String errorMessage = "";
		  List<String> idList = Arrays.asList(ids);
		  zsJkXmbXmLjCzMxXxMapper.deleteBatchIds(idList);
    	  return errorMessage;
    }

    @Override
    public String update(ZsJkXmbXmLjCzMxXxDto zsJkXmbXmLjCzMxXxDto,User user) throws Exception {
    	  String errorMessage = "";
        ZsJkXmbXmLjCzMxXx zsJkXmbXmLjCzMxXx = this.zsJkXmbXmLjCzMxXxMapper.selectById(zsJkXmbXmLjCzMxXxDto.getId());
		  zsJkXmbXmLjCzMxXxMapper.updateById(zsJkXmbXmLjCzMxXx);
    	  return errorMessage;
    }

	   @Override
	   public ZsJkXmbXmLjCzMxXx findById(String id) throws Exception {
		   return zsJkXmbXmLjCzMxXxMapper.selectById(id);
	   }

    @Override
    public String checkClumIfexits(ZsJkXmbXmLjCzMxXxDto zsJkXmbXmLjCzMxXxDto, String[] clumNames) throws Exception {
		   String id = zsJkXmbXmLjCzMxXxDto.getId();
		   Map map = BeanUtils.toMap(zsJkXmbXmLjCzMxXxDto);
		   Map<String,Object> columnMap = new HashMap<String,Object>();
		   for (String clum : clumNames) {
		        Object ss =  map.get(clum);
		        columnMap.put(clum, ss);
		   }
		   List<ZsJkXmbXmLjCzMxXx> lists = zsJkXmbXmLjCzMxXxMapper.selectByMap(columnMap);
		   if(StringUtils.isBlank(id)){//新增
			    if(lists!=null&&lists.size()>0)return "当前数据已存在!";
		   }else{//修改
			    for (ZsJkXmbXmLjCzMxXx zsJkXmbXmLjCzMxXx2 : lists) {
				   String newId = zsJkXmbXmLjCzMxXx2.getId();
				   if(!newId.equals(id)){
					   return "当前数据已存在!";
				   }
			    }
		   }
		   return null;
    }

}