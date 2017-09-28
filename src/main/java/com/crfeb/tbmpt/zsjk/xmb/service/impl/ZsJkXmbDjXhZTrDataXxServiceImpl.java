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
import com.crfeb.tbmpt.zsjk.xmb.model.dto.ZsJkXmbDjXhZTrDataXxDto;
import com.crfeb.tbmpt.zsjk.xmb.mapper.ZsJkXmbDjXhZTrDataXxMapper;
import com.crfeb.tbmpt.zsjk.xmb.model.ZsJkXmbDjXhZTrDataXx;
import com.crfeb.tbmpt.zsjk.xmb.service.ZsJkXmbDjXhZTrDataXxService;
/**
 * <p>项目部角度 刀具消耗总投入明细信息Service实现类</p>
 * <p>系统：展示界面接口</p>
 * <p>模块：项目部角度模块</p>
 * <p>日期：2017-01-09</p>
 * @version 1.0
 * @author YangYJ
 */
@Service
public class ZsJkXmbDjXhZTrDataXxServiceImpl extends CommonServiceImpl<ZsJkXmbDjXhZTrDataXxMapper, ZsJkXmbDjXhZTrDataXx> implements ZsJkXmbDjXhZTrDataXxService{

    @Autowired
    private ZsJkXmbDjXhZTrDataXxMapper zsJkXmbDjXhZTrDataXxMapper;

    @Override
    public void selectDataGrid(PageInfo pageInfo) throws Exception {
       Page<ZsJkXmbDjXhZTrDataXx> page = new Page<ZsJkXmbDjXhZTrDataXx>(pageInfo.getNowpage(), pageInfo.getSize());
       Map<String, Object> condition = pageInfo.getCondition();
       List<ZsJkXmbDjXhZTrDataXx> list = zsJkXmbDjXhZTrDataXxMapper.selectZsJkXmbDjXhZTrDataXxList(page,condition);
       pageInfo.setRows(list);
       pageInfo.setTotal(page.getTotal());
    }

    @Override
    public String save(ZsJkXmbDjXhZTrDataXxDto zsJkXmbDjXhZTrDataXxDto,User user) throws Exception {
    	  String errorMessage = "";
        ZsJkXmbDjXhZTrDataXx zsJkXmbDjXhZTrDataXx = new ZsJkXmbDjXhZTrDataXx();
        BeanUtils.copyProperties(zsJkXmbDjXhZTrDataXxDto, zsJkXmbDjXhZTrDataXx);
    	  zsJkXmbDjXhZTrDataXxMapper.insert(zsJkXmbDjXhZTrDataXx);
    	  return errorMessage;
    }

	   @Override
    public String del(String[] ids,User user) throws Exception {
    	  String errorMessage = "";
		  List<String> idList = Arrays.asList(ids);
		  zsJkXmbDjXhZTrDataXxMapper.deleteBatchIds(idList);
    	  return errorMessage;
    }

    @Override
    public String update(ZsJkXmbDjXhZTrDataXxDto zsJkXmbDjXhZTrDataXxDto,User user) throws Exception {
    	  String errorMessage = "";
        ZsJkXmbDjXhZTrDataXx zsJkXmbDjXhZTrDataXx = this.zsJkXmbDjXhZTrDataXxMapper.selectById(zsJkXmbDjXhZTrDataXxDto.getId());
		  zsJkXmbDjXhZTrDataXxMapper.updateById(zsJkXmbDjXhZTrDataXx);
    	  return errorMessage;
    }

	   @Override
	   public ZsJkXmbDjXhZTrDataXx findById(String id) throws Exception {
		   return zsJkXmbDjXhZTrDataXxMapper.selectById(id);
	   }

    @Override
    public String checkClumIfexits(ZsJkXmbDjXhZTrDataXxDto zsJkXmbDjXhZTrDataXxDto, String[] clumNames) throws Exception {
		   String id = zsJkXmbDjXhZTrDataXxDto.getId();
		   Map map = BeanUtils.toMap(zsJkXmbDjXhZTrDataXxDto);
		   Map<String,Object> columnMap = new HashMap<String,Object>();
		   for (String clum : clumNames) {
		        Object ss =  map.get(clum);
		        columnMap.put(clum, ss);
		   }
		   List<ZsJkXmbDjXhZTrDataXx> lists = zsJkXmbDjXhZTrDataXxMapper.selectByMap(columnMap);
		   if(StringUtils.isBlank(id)){//新增
			    if(lists!=null&&lists.size()>0)return "当前数据已存在!";
		   }else{//修改
			    for (ZsJkXmbDjXhZTrDataXx zsJkXmbDjXhZTrDataXx2 : lists) {
				   String newId = zsJkXmbDjXhZTrDataXx2.getId();
				   if(!newId.equals(id)){
					   return "当前数据已存在!";
				   }
			    }
		   }
		   return null;
    }

}