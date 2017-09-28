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
import com.crfeb.tbmpt.zsjk.xmb.model.dto.ZsJkXmbDjLxXxDto;
import com.crfeb.tbmpt.zsjk.xmb.model.dto.ZsJkXmbDjXhFxNrXxDto;
import com.crfeb.tbmpt.zsjk.xmb.mapper.ZsJkXmbDjLxXxMapper;
import com.crfeb.tbmpt.zsjk.xmb.model.ZsJkXmbDjLxXx;
import com.crfeb.tbmpt.zsjk.xmb.model.ZsJkXmbDjXhFxNrXx;
import com.crfeb.tbmpt.zsjk.xmb.service.ZsJkXmbDjLxXxService;
/**
 * <p>项目部角度 刀具类型信息Service实现类</p>
 * <p>系统：展示界面接口</p>
 * <p>模块：项目部角度模块</p>
 * <p>日期：2017-01-09</p>
 * @version 1.0
 * @author YangYJ
 */
@Service
public class ZsJkXmbDjLxXxServiceImpl extends CommonServiceImpl<ZsJkXmbDjLxXxMapper, ZsJkXmbDjLxXx> implements ZsJkXmbDjLxXxService{

    @Autowired
    private ZsJkXmbDjLxXxMapper zsJkXmbDjLxXxMapper;

    @Override
    public void selectDataGrid(PageInfo pageInfo) throws Exception {
       Page<ZsJkXmbDjLxXx> page = new Page<ZsJkXmbDjLxXx>(pageInfo.getNowpage(), pageInfo.getSize());
       Map<String, Object> condition = pageInfo.getCondition();
       List<ZsJkXmbDjLxXx> list = zsJkXmbDjLxXxMapper.selectZsJkXmbDjLxXxList(page,condition);
       pageInfo.setRows(list);
       pageInfo.setTotal(page.getTotal());
    }

    @Override
    public String save(ZsJkXmbDjLxXxDto zsJkXmbDjLxXxDto,User user) throws Exception {
    	  String errorMessage = "";
        ZsJkXmbDjLxXx zsJkXmbDjLxXx = new ZsJkXmbDjLxXx();
        BeanUtils.copyProperties(zsJkXmbDjLxXxDto, zsJkXmbDjLxXx);
    	  zsJkXmbDjLxXxMapper.insert(zsJkXmbDjLxXx);
    	  return errorMessage;
    }

	   @Override
    public String del(String[] ids,User user) throws Exception {
    	  String errorMessage = "";
		  List<String> idList = Arrays.asList(ids);
		  zsJkXmbDjLxXxMapper.deleteBatchIds(idList);
    	  return errorMessage;
    }

    @Override
    public String update(ZsJkXmbDjLxXxDto zsJkXmbDjLxXxDto,User user) throws Exception {
    	  String errorMessage = "";
        ZsJkXmbDjLxXx zsJkXmbDjLxXx = this.zsJkXmbDjLxXxMapper.selectById(zsJkXmbDjLxXxDto.getId());
		  zsJkXmbDjLxXxMapper.updateById(zsJkXmbDjLxXx);
    	  return errorMessage;
    }

	   @Override
	   public ZsJkXmbDjLxXx findById(String id) throws Exception {
		   return zsJkXmbDjLxXxMapper.selectById(id);
	   }

    @Override
    public String checkClumIfexits(ZsJkXmbDjLxXxDto zsJkXmbDjLxXxDto, String[] clumNames) throws Exception {
		   String id = zsJkXmbDjLxXxDto.getId();
		   Map map = BeanUtils.toMap(zsJkXmbDjLxXxDto);
		   Map<String,Object> columnMap = new HashMap<String,Object>();
		   for (String clum : clumNames) {
		        Object ss =  map.get(clum);
		        columnMap.put(clum, ss);
		   }
		   List<ZsJkXmbDjLxXx> lists = zsJkXmbDjLxXxMapper.selectByMap(columnMap);
		   if(StringUtils.isBlank(id)){//新增
			    if(lists!=null&&lists.size()>0)return "当前数据已存在!";
		   }else{//修改
			    for (ZsJkXmbDjLxXx zsJkXmbDjLxXx2 : lists) {
				   String newId = zsJkXmbDjLxXx2.getId();
				   if(!newId.equals(id)){
					   return "当前数据已存在!";
				   }
			    }
		   }
		   return null;
    }

	@Override
	public List<ZsJkXmbDjLxXxDto> getDjLx() throws Exception {
		List<ZsJkXmbDjLxXxDto> dtoList = new ArrayList<ZsJkXmbDjLxXxDto>();
		List<ZsJkXmbDjLxXx> entityList = new ArrayList<ZsJkXmbDjLxXx>();
		entityList = this.zsJkXmbDjLxXxMapper.selectList(null);
		if(null != entityList && entityList.size()>0){
			ZsJkXmbDjLxXxDto dto = new ZsJkXmbDjLxXxDto();
			for(ZsJkXmbDjLxXx entity:entityList){
				dto = new ZsJkXmbDjLxXxDto();
				BeanUtils.copy(entity, dto);
				dtoList.add(dto);
			}
		}
		return dtoList;
	}

}