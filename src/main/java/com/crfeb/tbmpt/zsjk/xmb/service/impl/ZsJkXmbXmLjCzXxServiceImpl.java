package com.crfeb.tbmpt.zsjk.xmb.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.framework.service.impl.CommonServiceImpl;
import com.baomidou.mybatisplus.plugins.Page;
import com.crfeb.tbmpt.commons.utils.BeanUtils;
import com.crfeb.tbmpt.commons.utils.PageInfo;
import com.crfeb.tbmpt.commons.utils.StringUtils;
import com.crfeb.tbmpt.sys.model.User;
import com.crfeb.tbmpt.zsjk.xmb.mapper.ZsJkXmbXmLjCzMxXxMapper;
import com.crfeb.tbmpt.zsjk.xmb.mapper.ZsJkXmbXmLjCzXxMapper;
import com.crfeb.tbmpt.zsjk.xmb.model.ZsJkXmbXmLjCzMxXx;
import com.crfeb.tbmpt.zsjk.xmb.model.ZsJkXmbXmLjCzXx;
import com.crfeb.tbmpt.zsjk.xmb.model.dto.ZsJkXmbXmLjCzMxXxDto;
import com.crfeb.tbmpt.zsjk.xmb.model.dto.ZsJkXmbXmLjCzXxDto;
import com.crfeb.tbmpt.zsjk.xmb.service.ZsJkXmbXmLjCzXxService;
/**
 * <p>项目部角度  当前项目的累计产值信息Service实现类</p>
 * <p>系统：展示界面接口</p>
 * <p>模块：项目部角度模块</p>
 * <p>日期：2017-01-09</p>
 * @version 1.0
 * @author YangYJ
 */
@Service
public class ZsJkXmbXmLjCzXxServiceImpl extends CommonServiceImpl<ZsJkXmbXmLjCzXxMapper, ZsJkXmbXmLjCzXx> implements ZsJkXmbXmLjCzXxService{

    @Autowired
    private ZsJkXmbXmLjCzXxMapper zsJkXmbXmLjCzXxMapper;
    
    @Autowired
    private ZsJkXmbXmLjCzMxXxMapper zsJkXmbXmLjCzMxXxMapper;

    @Override
    public void selectDataGrid(PageInfo pageInfo) throws Exception {
       Page<ZsJkXmbXmLjCzXx> page = new Page<ZsJkXmbXmLjCzXx>(pageInfo.getNowpage(), pageInfo.getSize());
       Map<String, Object> condition = pageInfo.getCondition();
       List<ZsJkXmbXmLjCzXx> list = zsJkXmbXmLjCzXxMapper.selectZsJkXmbXmLjCzXxList(page,condition);
       pageInfo.setRows(list);
       pageInfo.setTotal(page.getTotal());
    }

    @Override
    public String save(ZsJkXmbXmLjCzXxDto zsJkXmbXmLjCzXxDto,User user) throws Exception {
    	  String errorMessage = "";
        ZsJkXmbXmLjCzXx zsJkXmbXmLjCzXx = new ZsJkXmbXmLjCzXx();
        BeanUtils.copyProperties(zsJkXmbXmLjCzXxDto, zsJkXmbXmLjCzXx);
    	  zsJkXmbXmLjCzXxMapper.insert(zsJkXmbXmLjCzXx);
    	  return errorMessage;
    }

	   @Override
    public String del(String[] ids,User user) throws Exception {
    	  String errorMessage = "";
		  List<String> idList = Arrays.asList(ids);
		  zsJkXmbXmLjCzXxMapper.deleteBatchIds(idList);
    	  return errorMessage;
    }

    @Override
    public String update(ZsJkXmbXmLjCzXxDto zsJkXmbXmLjCzXxDto,User user) throws Exception {
    	  String errorMessage = "";
        ZsJkXmbXmLjCzXx zsJkXmbXmLjCzXx = this.zsJkXmbXmLjCzXxMapper.selectById(zsJkXmbXmLjCzXxDto.getId());
		  zsJkXmbXmLjCzXxMapper.updateById(zsJkXmbXmLjCzXx);
    	  return errorMessage;
    }

	   @Override
	   public ZsJkXmbXmLjCzXx findById(String id) throws Exception {
		   return zsJkXmbXmLjCzXxMapper.selectById(id);
	   }

    @Override
    public String checkClumIfexits(ZsJkXmbXmLjCzXxDto zsJkXmbXmLjCzXxDto, String[] clumNames) throws Exception {
		   String id = zsJkXmbXmLjCzXxDto.getId();
		   Map map = BeanUtils.toMap(zsJkXmbXmLjCzXxDto);
		   Map<String,Object> columnMap = new HashMap<String,Object>();
		   for (String clum : clumNames) {
		        Object ss =  map.get(clum);
		        columnMap.put(clum, ss);
		   }
		   List<ZsJkXmbXmLjCzXx> lists = zsJkXmbXmLjCzXxMapper.selectByMap(columnMap);
		   if(StringUtils.isBlank(id)){//新增
			    if(lists!=null&&lists.size()>0)return "当前数据已存在!";
		   }else{//修改
			    for (ZsJkXmbXmLjCzXx zsJkXmbXmLjCzXx2 : lists) {
				   String newId = zsJkXmbXmLjCzXx2.getId();
				   if(!newId.equals(id)){
					   return "当前数据已存在!";
				   }
			    }
		   }
		   return null;
    }

	@Override
	public ZsJkXmbXmLjCzXxDto dqxmdljcz(String projectId) throws Exception {
		ZsJkXmbXmLjCzXxDto dto = new ZsJkXmbXmLjCzXxDto();
		if(StringUtils.isNotBlank(projectId)){
			ZsJkXmbXmLjCzXx param = new ZsJkXmbXmLjCzXx();
			ZsJkXmbXmLjCzXx entity = new ZsJkXmbXmLjCzXx();
			param.setProjectId(projectId);
			entity = this.zsJkXmbXmLjCzXxMapper.selectOne(param);
			if(null != entity){
				BeanUtils.copy(entity, dto);
				List<ZsJkXmbXmLjCzMxXx> childList = new ArrayList<ZsJkXmbXmLjCzMxXx>();
				List<ZsJkXmbXmLjCzMxXxDto> childDtoList = new ArrayList<ZsJkXmbXmLjCzMxXxDto>();
				Map<String,Object> columnMap = new HashMap<String,Object>();
				columnMap.put("xmId", projectId);
				columnMap.put("fId", entity.getId());
				childList = this.zsJkXmbXmLjCzMxXxMapper.selectByMap(columnMap);
				if(null != childList && childList.size()>0){
					ZsJkXmbXmLjCzMxXxDto childDto = new ZsJkXmbXmLjCzMxXxDto();
					for(ZsJkXmbXmLjCzMxXx child : childList){
						childDto = new ZsJkXmbXmLjCzMxXxDto();
						BeanUtils.copy(child, childDto);
						childDtoList.add(childDto);
					}
					dto.setItems(childDtoList);
				}
			}
		}
		return dto;
	}

}