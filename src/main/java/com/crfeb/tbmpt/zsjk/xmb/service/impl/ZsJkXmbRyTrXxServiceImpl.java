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
import com.crfeb.tbmpt.zsjk.xmb.mapper.ZsJkXmbRyTrXxMapper;
import com.crfeb.tbmpt.zsjk.xmb.model.ZsJkXmbRyTrXx;
import com.crfeb.tbmpt.zsjk.xmb.model.dto.ZsJkXmbRyTrXxDto;
import com.crfeb.tbmpt.zsjk.xmb.service.ZsJkXmbRyTrXxService;
/**
 * <p>项目部角度 项目人员投入信息Service实现类</p>
 * <p>系统：展示界面接口</p>
 * <p>模块：项目部角度模块</p>
 * <p>日期：2017-01-09</p>
 * @version 1.0
 * @author YangYJ
 */
@Service
public class ZsJkXmbRyTrXxServiceImpl extends CommonServiceImpl<ZsJkXmbRyTrXxMapper, ZsJkXmbRyTrXx> implements ZsJkXmbRyTrXxService{

    @Autowired
    private ZsJkXmbRyTrXxMapper zsJkXmbRyTrXxMapper;

    @Override
    public void selectDataGrid(PageInfo pageInfo) throws Exception {
       Page<ZsJkXmbRyTrXx> page = new Page<ZsJkXmbRyTrXx>(pageInfo.getNowpage(), pageInfo.getSize());
       Map<String, Object> condition = pageInfo.getCondition();
       List<ZsJkXmbRyTrXx> list = zsJkXmbRyTrXxMapper.selectZsJkXmbRyTrXxList(page,condition);
       pageInfo.setRows(list);
       pageInfo.setTotal(page.getTotal());
    }

    @Override
    public String save(ZsJkXmbRyTrXxDto zsJkXmbRyTrXxDto,User user) throws Exception {
    	  String errorMessage = "";
        ZsJkXmbRyTrXx zsJkXmbRyTrXx = new ZsJkXmbRyTrXx();
        BeanUtils.copyProperties(zsJkXmbRyTrXxDto, zsJkXmbRyTrXx);
    	  zsJkXmbRyTrXxMapper.insert(zsJkXmbRyTrXx);
    	  return errorMessage;
    }

	   @Override
    public String del(String[] ids,User user) throws Exception {
    	  String errorMessage = "";
		  List<String> idList = Arrays.asList(ids);
		  zsJkXmbRyTrXxMapper.deleteBatchIds(idList);
    	  return errorMessage;
    }

    @Override
    public String update(ZsJkXmbRyTrXxDto zsJkXmbRyTrXxDto,User user) throws Exception {
    	  String errorMessage = "";
        ZsJkXmbRyTrXx zsJkXmbRyTrXx = this.zsJkXmbRyTrXxMapper.selectById(zsJkXmbRyTrXxDto.getId());
		  zsJkXmbRyTrXxMapper.updateById(zsJkXmbRyTrXx);
    	  return errorMessage;
    }

	   @Override
	   public ZsJkXmbRyTrXx findById(String id) throws Exception {
		   return zsJkXmbRyTrXxMapper.selectById(id);
	   }

    @Override
    public String checkClumIfexits(ZsJkXmbRyTrXxDto zsJkXmbRyTrXxDto, String[] clumNames) throws Exception {
		   String id = zsJkXmbRyTrXxDto.getId();
		   Map map = BeanUtils.toMap(zsJkXmbRyTrXxDto);
		   Map<String,Object> columnMap = new HashMap<String,Object>();
		   for (String clum : clumNames) {
		        Object ss =  map.get(clum);
		        columnMap.put(clum, ss);
		   }
		   List<ZsJkXmbRyTrXx> lists = zsJkXmbRyTrXxMapper.selectByMap(columnMap);
		   if(StringUtils.isBlank(id)){//新增
			    if(lists!=null&&lists.size()>0)return "当前数据已存在!";
		   }else{//修改
			    for (ZsJkXmbRyTrXx zsJkXmbRyTrXx2 : lists) {
				   String newId = zsJkXmbRyTrXx2.getId();
				   if(!newId.equals(id)){
					   return "当前数据已存在!";
				   }
			    }
		   }
		   return null;
    }

	@Override
	public List<ZsJkXmbRyTrXxDto> xmrytr(String xmId) throws Exception {
		List<ZsJkXmbRyTrXxDto> dtoList = new ArrayList<ZsJkXmbRyTrXxDto>();
		List<ZsJkXmbRyTrXx> entityList = new ArrayList<ZsJkXmbRyTrXx>();
		if(StringUtils.isNotBlank(xmId)){
			Map<String,Object> columnMap = new HashMap<String,Object>();
			columnMap.put("xmId", xmId);
			entityList = this.zsJkXmbRyTrXxMapper.selectByMap(columnMap);
			if(null != entityList && entityList.size()>0){
				ZsJkXmbRyTrXxDto dto = new ZsJkXmbRyTrXxDto();
				for(ZsJkXmbRyTrXx entity : entityList){
					dto = new ZsJkXmbRyTrXxDto();
					BeanUtils.copy(entity,dto);
					dtoList.add(dto);
				}
			}
		}
		return dtoList;
	}
}