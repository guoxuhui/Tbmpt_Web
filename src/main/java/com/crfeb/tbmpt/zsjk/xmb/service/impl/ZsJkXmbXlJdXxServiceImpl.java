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
import com.crfeb.tbmpt.zsjk.xmb.mapper.ZsJkXmbXlJdXxMapper;
import com.crfeb.tbmpt.zsjk.xmb.model.ZsJkXmbXlJdXx;
import com.crfeb.tbmpt.zsjk.xmb.model.dto.ZsJkXmbXlJdMxXxDto;
import com.crfeb.tbmpt.zsjk.xmb.model.dto.ZsJkXmbXlJdXxDto;
import com.crfeb.tbmpt.zsjk.xmb.service.ZsJkXmbXlJdXxService;
/**
 * <p>项目部角度 项目各线路进度信息Service实现类</p>
 * <p>系统：展示界面接口</p>
 * <p>模块：项目部角度模块</p>
 * <p>日期：2017-01-09</p>
 * @version 1.0
 * @author YangYJ
 */
@Service
public class ZsJkXmbXlJdXxServiceImpl extends CommonServiceImpl<ZsJkXmbXlJdXxMapper, ZsJkXmbXlJdXx> implements ZsJkXmbXlJdXxService{

    @Autowired
    private ZsJkXmbXlJdXxMapper zsJkXmbXlJdXxMapper;

    @Override
    public void selectDataGrid(PageInfo pageInfo) throws Exception {
       Page<ZsJkXmbXlJdXx> page = new Page<ZsJkXmbXlJdXx>(pageInfo.getNowpage(), pageInfo.getSize());
       Map<String, Object> condition = pageInfo.getCondition();
       List<ZsJkXmbXlJdXx> list = zsJkXmbXlJdXxMapper.selectZsJkXmbXlJdXxList(page,condition);
       pageInfo.setRows(list);
       pageInfo.setTotal(page.getTotal());
    }

    @Override
    public String save(ZsJkXmbXlJdXxDto zsJkXmbXlJdXxDto,User user) throws Exception {
    	  String errorMessage = "";
        ZsJkXmbXlJdXx zsJkXmbXlJdXx = new ZsJkXmbXlJdXx();
        BeanUtils.copyProperties(zsJkXmbXlJdXxDto, zsJkXmbXlJdXx);
    	  zsJkXmbXlJdXxMapper.insert(zsJkXmbXlJdXx);
    	  return errorMessage;
    }

	   @Override
    public String del(String[] ids,User user) throws Exception {
    	  String errorMessage = "";
		  List<String> idList = Arrays.asList(ids);
		  zsJkXmbXlJdXxMapper.deleteBatchIds(idList);
    	  return errorMessage;
    }

    @Override
    public String update(ZsJkXmbXlJdXxDto zsJkXmbXlJdXxDto,User user) throws Exception {
    	  String errorMessage = "";
//          ZsJkXmbXlJdXx zsJkXmbXlJdXx = this.zsJkXmbXlJdXxMapper.selectById(zsJkXmbXlJdXxDto.getId());
//		  zsJkXmbXlJdXxMapper.updateById(zsJkXmbXlJdXx);
    	  return errorMessage;
    }

	   @Override
	   public ZsJkXmbXlJdXx findById(String id) throws Exception {
		   return zsJkXmbXlJdXxMapper.selectById(id);
	   }

    @Override
    public String checkClumIfexits(ZsJkXmbXlJdXxDto zsJkXmbXlJdXxDto, String[] clumNames) throws Exception {
//		   String id = zsJkXmbXlJdXxDto.getId();
//		   Map map = BeanUtils.toMap(zsJkXmbXlJdXxDto);
//		   Map<String,Object> columnMap = new HashMap<String,Object>();
//		   for (String clum : clumNames) {
//		        Object ss =  map.get(clum);
//		        columnMap.put(clum, ss);
//		   }
//		   List<ZsJkXmbXlJdXx> lists = zsJkXmbXlJdXxMapper.selectByMap(columnMap);
//		   if(StringUtils.isBlank(id)){//新增
//			    if(lists!=null&&lists.size()>0)return "当前数据已存在!";
//		   }else{//修改
//			    for (ZsJkXmbXlJdXx zsJkXmbXlJdXx2 : lists) {
//				   String newId = zsJkXmbXlJdXx2.getId();
//				   if(!newId.equals(id)){
//					   return "当前数据已存在!";
//				   }
//			    }
//		   }
		   return null;
    }

	@Override
	public ZsJkXmbXlJdXxDto getXmxljd(String xmId) throws Exception {
		ZsJkXmbXlJdXxDto dto = new ZsJkXmbXlJdXxDto();
		if(StringUtils.isNotBlank(xmId)){
			Map<String,Object> columnMap = new HashMap<String,Object>();
			columnMap.put("xmId", xmId);
			List<ZsJkXmbXlJdXx> entityList = this.zsJkXmbXlJdXxMapper.selectByMap(columnMap);
			if(null != entityList && entityList.size()>0){
				ZsJkXmbXlJdMxXxDto mxDto = new ZsJkXmbXlJdMxXxDto();
				List<ZsJkXmbXlJdMxXxDto> mxList = new ArrayList<ZsJkXmbXlJdMxXxDto>();
				for(ZsJkXmbXlJdXx entity:entityList){
					mxDto = new ZsJkXmbXlJdMxXxDto();
					BeanUtils.copy(entity, dto);
					BeanUtils.copy(entity, mxDto);
					mxList.add(mxDto);
				}
				dto.setXlList(mxList);
			}
		}
		return dto;
	}

}