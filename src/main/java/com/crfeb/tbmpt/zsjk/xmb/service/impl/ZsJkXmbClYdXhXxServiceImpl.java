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
import com.crfeb.tbmpt.zsjk.xmb.mapper.ZsJkXmbClYdXhXxMapper;
import com.crfeb.tbmpt.zsjk.xmb.model.ZsJkXmbClYdXhXx;
import com.crfeb.tbmpt.zsjk.xmb.model.dto.ZsJkXmbClYdXhXxDto;
import com.crfeb.tbmpt.zsjk.xmb.service.ZsJkXmbClYdXhXxService;
/**
 * <p>项目部角度 项目的材料月度总消耗信息Service实现类</p>
 * <p>系统：展示界面接口</p>
 * <p>模块：项目部角度模块</p>
 * <p>日期：2017-01-09</p>
 * @version 1.0
 * @author YangYJ
 */
@Service
public class ZsJkXmbClYdXhXxServiceImpl extends CommonServiceImpl<ZsJkXmbClYdXhXxMapper, ZsJkXmbClYdXhXx> implements ZsJkXmbClYdXhXxService{

    @Autowired
    private ZsJkXmbClYdXhXxMapper zsJkXmbClYdXhXxMapper;

    @Override
    public void selectDataGrid(PageInfo pageInfo) throws Exception {
       Page<ZsJkXmbClYdXhXx> page = new Page<ZsJkXmbClYdXhXx>(pageInfo.getNowpage(), pageInfo.getSize());
       Map<String, Object> condition = pageInfo.getCondition();
       List<ZsJkXmbClYdXhXx> list = zsJkXmbClYdXhXxMapper.selectZsJkXmbClYdXhXxList(page,condition);
       pageInfo.setRows(list);
       pageInfo.setTotal(page.getTotal());
    }

    @Override
    public String save(ZsJkXmbClYdXhXxDto zsJkXmbClYdXhXxDto,User user) throws Exception {
    	  String errorMessage = "";
        ZsJkXmbClYdXhXx zsJkXmbClYdXhXx = new ZsJkXmbClYdXhXx();
        BeanUtils.copyProperties(zsJkXmbClYdXhXxDto, zsJkXmbClYdXhXx);
    	  zsJkXmbClYdXhXxMapper.insert(zsJkXmbClYdXhXx);
    	  return errorMessage;
    }

	   @Override
    public String del(String[] ids,User user) throws Exception {
    	  String errorMessage = "";
		  List<String> idList = Arrays.asList(ids);
		  zsJkXmbClYdXhXxMapper.deleteBatchIds(idList);
    	  return errorMessage;
    }

    @Override
    public String update(ZsJkXmbClYdXhXxDto zsJkXmbClYdXhXxDto,User user) throws Exception {
    	  String errorMessage = "";
        ZsJkXmbClYdXhXx zsJkXmbClYdXhXx = this.zsJkXmbClYdXhXxMapper.selectById(zsJkXmbClYdXhXxDto.getId());
		  zsJkXmbClYdXhXxMapper.updateById(zsJkXmbClYdXhXx);
    	  return errorMessage;
    }

	   @Override
	   public ZsJkXmbClYdXhXx findById(String id) throws Exception {
		   return zsJkXmbClYdXhXxMapper.selectById(id);
	   }

    @Override
    public String checkClumIfexits(ZsJkXmbClYdXhXxDto zsJkXmbClYdXhXxDto, String[] clumNames) throws Exception {
		   String id = zsJkXmbClYdXhXxDto.getId();
		   Map map = BeanUtils.toMap(zsJkXmbClYdXhXxDto);
		   Map<String,Object> columnMap = new HashMap<String,Object>();
		   for (String clum : clumNames) {
		        Object ss =  map.get(clum);
		        columnMap.put(clum, ss);
		   }
		   List<ZsJkXmbClYdXhXx> lists = zsJkXmbClYdXhXxMapper.selectByMap(columnMap);
		   if(StringUtils.isBlank(id)){//新增
			    if(lists!=null&&lists.size()>0)return "当前数据已存在!";
		   }else{//修改
			    for (ZsJkXmbClYdXhXx zsJkXmbClYdXhXx2 : lists) {
				   String newId = zsJkXmbClYdXhXx2.getId();
				   if(!newId.equals(id)){
					   return "当前数据已存在!";
				   }
			    }
		   }
		   return null;
    }

	@Override
	public List<ZsJkXmbClYdXhXxDto> getXmClydxh(String xmId, String strDate, String endDate) throws Exception {
		List<ZsJkXmbClYdXhXxDto>  dtoList = new ArrayList<ZsJkXmbClYdXhXxDto>();
		if(StringUtils.isNotBlank(xmId) && StringUtils.isNotBlank(strDate) && StringUtils.isNotBlank(endDate)){
			Map<String,Object> columnMap = new HashMap<String,Object>();
			columnMap.put("xmId", xmId);
			columnMap.put("strDate", strDate);
			columnMap.put("endDate", endDate);
			dtoList = this.zsJkXmbClYdXhXxMapper.selectZsJkXmbClYdXhDtoXxList(columnMap);
		}
		return dtoList;
	}

}