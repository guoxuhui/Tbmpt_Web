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
import com.crfeb.tbmpt.zsjk.xmb.mapper.ZsJkXmbXmXlXxMapper;
import com.crfeb.tbmpt.zsjk.xmb.mapper.ZsJkXmbXmXxMapper;
import com.crfeb.tbmpt.zsjk.xmb.model.ZsJkXmbXmXlXx;
import com.crfeb.tbmpt.zsjk.xmb.model.ZsJkXmbXmXx;
import com.crfeb.tbmpt.zsjk.xmb.model.dto.ZsJkXmbXmXlXxDto;
import com.crfeb.tbmpt.zsjk.xmb.model.dto.ZsJkXmbXmXxDto;
import com.crfeb.tbmpt.zsjk.xmb.service.ZsJkXmbXmXxService;
/**
 * <p>项目部角度 项目基本信息Service实现类</p>
 * <p>系统：展示界面接口</p>
 * <p>模块：项目部角度模块</p>
 * <p>日期：2017-01-09</p>
 * @version 1.0
 * @author YangYJ
 */
@Service
public class ZsJkXmbXmXxServiceImpl extends CommonServiceImpl<ZsJkXmbXmXxMapper, ZsJkXmbXmXx> implements ZsJkXmbXmXxService{

    @Autowired
    private ZsJkXmbXmXxMapper zsJkXmbXmXxMapper;
    @Autowired
    private ZsJkXmbXmXlXxMapper zsJkXmbXmXlXxMapper;

    @Override
    public void selectDataGrid(PageInfo pageInfo) throws Exception {
       Page<ZsJkXmbXmXx> page = new Page<ZsJkXmbXmXx>(pageInfo.getNowpage(), pageInfo.getSize());
       Map<String, Object> condition = pageInfo.getCondition();
       List<ZsJkXmbXmXx> list = zsJkXmbXmXxMapper.selectZsJkXmbXmXxList(page,condition);
       pageInfo.setRows(list);
       pageInfo.setTotal(page.getTotal());
    }

    @Override
    public String save(ZsJkXmbXmXxDto zsJkXmbXmXxDto,User user) throws Exception {
    	  String errorMessage = "";
        ZsJkXmbXmXx zsJkXmbXmXx = new ZsJkXmbXmXx();
        BeanUtils.copyProperties(zsJkXmbXmXxDto, zsJkXmbXmXx);
    	  zsJkXmbXmXxMapper.insert(zsJkXmbXmXx);
    	  return errorMessage;
    }

	   @Override
    public String del(String[] ids,User user) throws Exception {
    	  String errorMessage = "";
		  List<String> idList = Arrays.asList(ids);
		  zsJkXmbXmXxMapper.deleteBatchIds(idList);
    	  return errorMessage;
    }

    @Override
    public String update(ZsJkXmbXmXxDto zsJkXmbXmXxDto,User user) throws Exception {
    	  String errorMessage = "";
        ZsJkXmbXmXx zsJkXmbXmXx = this.zsJkXmbXmXxMapper.selectById(zsJkXmbXmXxDto.getId());
		  zsJkXmbXmXxMapper.updateById(zsJkXmbXmXx);
    	  return errorMessage;
    }

	   @Override
	   public ZsJkXmbXmXx findById(String id) throws Exception {
		   return zsJkXmbXmXxMapper.selectById(id);
	   }

    @Override
    public String checkClumIfexits(ZsJkXmbXmXxDto zsJkXmbXmXxDto, String[] clumNames) throws Exception {
		   String id = zsJkXmbXmXxDto.getId();
		   Map map = BeanUtils.toMap(zsJkXmbXmXxDto);
		   Map<String,Object> columnMap = new HashMap<String,Object>();
		   for (String clum : clumNames) {
		        Object ss =  map.get(clum);
		        columnMap.put(clum, ss);
		   }
		   List<ZsJkXmbXmXx> lists = zsJkXmbXmXxMapper.selectByMap(columnMap);
		   if(StringUtils.isBlank(id)){//新增
			    if(lists!=null&&lists.size()>0)return "当前数据已存在!";
		   }else{//修改
			    for (ZsJkXmbXmXx zsJkXmbXmXx2 : lists) {
				   String newId = zsJkXmbXmXx2.getId();
				   if(!newId.equals(id)){
					   return "当前数据已存在!";
				   }
			    }
		   }
		   return null;
    }

	@Override
	public ZsJkXmbXmXxDto getXMXX(String xmId) throws Exception {
		ZsJkXmbXmXxDto xmDto = new ZsJkXmbXmXxDto();
		ZsJkXmbXmXx entity = new ZsJkXmbXmXx();
		if(StringUtils.isNotBlank(xmId)){
			entity = this.zsJkXmbXmXxMapper.selectById(xmId);
			if(null != entity){
				BeanUtils.copy(entity, xmDto);
			}
			//=========================查询项目下线路信息===============================//
			Map<String,Object> columnMap = new HashMap<String,Object>();
			columnMap.put("xmId", xmId);
			List<ZsJkXmbXmXlXx> xlList = zsJkXmbXmXlXxMapper.selectByMap(columnMap);
			if(null != xlList && xlList.size()>0){
				List<ZsJkXmbXmXlXxDto> xlDtoList = new ArrayList<ZsJkXmbXmXlXxDto>();
				ZsJkXmbXmXlXxDto xlDto = null;
				for(int i =0;i<xlList.size();i++){
					xlDto = new ZsJkXmbXmXlXxDto(); 
					BeanUtils.copy(xlList.get(i), xlDto);
					xlDtoList.add(xlDto);
				}
				xmDto.setXlList(xlDtoList);
			}
		}
		return xmDto;
	}

}