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
import com.crfeb.tbmpt.zsjk.xmb.model.dto.ZsJkXmbDjXhFxNrXxDto;
import com.crfeb.tbmpt.zsjk.xmb.mapper.ZsJkXmbDjXhFxNrXxMapper;
import com.crfeb.tbmpt.zsjk.xmb.model.ZsJkXmbDjXhFxNrXx;
import com.crfeb.tbmpt.zsjk.xmb.service.ZsJkXmbDjXhFxNrXxService;
/**
 * <p>项目部角度 刀具消耗分析内容信息Service实现类</p>
 * <p>系统：展示界面接口</p>
 * <p>模块：项目部角度模块</p>
 * <p>日期：2017-01-09</p>
 * @version 1.0
 * @author YangYJ
 */
@Service
public class ZsJkXmbDjXhFxNrXxServiceImpl extends CommonServiceImpl<ZsJkXmbDjXhFxNrXxMapper, ZsJkXmbDjXhFxNrXx> implements ZsJkXmbDjXhFxNrXxService{

    @Autowired
    private ZsJkXmbDjXhFxNrXxMapper zsJkXmbDjXhFxNrXxMapper;

    @Override
    public void selectDataGrid(PageInfo pageInfo) throws Exception {
       Page<ZsJkXmbDjXhFxNrXx> page = new Page<ZsJkXmbDjXhFxNrXx>(pageInfo.getNowpage(), pageInfo.getSize());
       Map<String, Object> condition = pageInfo.getCondition();
       List<ZsJkXmbDjXhFxNrXx> list = zsJkXmbDjXhFxNrXxMapper.selectZsJkXmbDjXhFxNrXxList(page,condition);
       pageInfo.setRows(list);
       pageInfo.setTotal(page.getTotal());
    }

    @Override
    public String save(ZsJkXmbDjXhFxNrXxDto zsJkXmbDjXhFxNrXxDto,User user) throws Exception {
    	  String errorMessage = "";
        ZsJkXmbDjXhFxNrXx zsJkXmbDjXhFxNrXx = new ZsJkXmbDjXhFxNrXx();
        BeanUtils.copyProperties(zsJkXmbDjXhFxNrXxDto, zsJkXmbDjXhFxNrXx);
    	  zsJkXmbDjXhFxNrXxMapper.insert(zsJkXmbDjXhFxNrXx);
    	  return errorMessage;
    }

	   @Override
    public String del(String[] ids,User user) throws Exception {
    	  String errorMessage = "";
		  List<String> idList = Arrays.asList(ids);
		  zsJkXmbDjXhFxNrXxMapper.deleteBatchIds(idList);
    	  return errorMessage;
    }

    @Override
    public String update(ZsJkXmbDjXhFxNrXxDto zsJkXmbDjXhFxNrXxDto,User user) throws Exception {
    	  String errorMessage = "";
        ZsJkXmbDjXhFxNrXx zsJkXmbDjXhFxNrXx = this.zsJkXmbDjXhFxNrXxMapper.selectById(zsJkXmbDjXhFxNrXxDto.getId());
		  zsJkXmbDjXhFxNrXxMapper.updateById(zsJkXmbDjXhFxNrXx);
    	  return errorMessage;
    }

	   @Override
	   public ZsJkXmbDjXhFxNrXx findById(String id) throws Exception {
		   return zsJkXmbDjXhFxNrXxMapper.selectById(id);
	   }

    @Override
    public String checkClumIfexits(ZsJkXmbDjXhFxNrXxDto zsJkXmbDjXhFxNrXxDto, String[] clumNames) throws Exception {
		   String id = zsJkXmbDjXhFxNrXxDto.getId();
		   Map map = BeanUtils.toMap(zsJkXmbDjXhFxNrXxDto);
		   Map<String,Object> columnMap = new HashMap<String,Object>();
		   for (String clum : clumNames) {
		        Object ss =  map.get(clum);
		        columnMap.put(clum, ss);
		   }
		   List<ZsJkXmbDjXhFxNrXx> lists = zsJkXmbDjXhFxNrXxMapper.selectByMap(columnMap);
		   if(StringUtils.isBlank(id)){//新增
			    if(lists!=null&&lists.size()>0)return "当前数据已存在!";
		   }else{//修改
			    for (ZsJkXmbDjXhFxNrXx zsJkXmbDjXhFxNrXx2 : lists) {
				   String newId = zsJkXmbDjXhFxNrXx2.getId();
				   if(!newId.equals(id)){
					   return "当前数据已存在!";
				   }
			    }
		   }
		   return null;
    }

	@Override
	public List<ZsJkXmbDjXhFxNrXxDto> getXmDjfxnr() throws Exception {
		List<ZsJkXmbDjXhFxNrXxDto> dtoList = new ArrayList<ZsJkXmbDjXhFxNrXxDto>();
		List<ZsJkXmbDjXhFxNrXx> entityList = new ArrayList<ZsJkXmbDjXhFxNrXx>();
		entityList = this.zsJkXmbDjXhFxNrXxMapper.selectList(null);
		if(null != entityList && entityList.size()>0){
			ZsJkXmbDjXhFxNrXxDto dto = new ZsJkXmbDjXhFxNrXxDto();
			for(ZsJkXmbDjXhFxNrXx entity:entityList){
				 dto = new ZsJkXmbDjXhFxNrXxDto();
				BeanUtils.copy(entity, dto);
				dtoList.add(dto);
			}
		}
		return dtoList;
	}

}