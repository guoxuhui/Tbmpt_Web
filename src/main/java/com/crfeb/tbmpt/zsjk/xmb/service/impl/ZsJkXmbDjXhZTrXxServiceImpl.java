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
import com.crfeb.tbmpt.zsjk.xmb.mapper.ZsJkXmbDjXhZTrDataXxMapper;
import com.crfeb.tbmpt.zsjk.xmb.mapper.ZsJkXmbDjXhZTrXxMapper;
import com.crfeb.tbmpt.zsjk.xmb.model.ZsJkXmbDjXhZTrXx;
import com.crfeb.tbmpt.zsjk.xmb.model.dto.ZsJkXmbDjXhZTrDataXxDto;
import com.crfeb.tbmpt.zsjk.xmb.model.dto.ZsJkXmbDjXhZTrXxDto;
import com.crfeb.tbmpt.zsjk.xmb.service.ZsJkXmbDjXhZTrXxService;
/**
 * <p>项目部角度 刀具消耗总投入信息Service实现类</p>
 * <p>系统：展示界面接口</p>
 * <p>模块：项目部角度模块</p>
 * <p>日期：2017-01-09</p>
 * @version 1.0
 * @author YangYJ
 */
@Service
public class ZsJkXmbDjXhZTrXxServiceImpl extends CommonServiceImpl<ZsJkXmbDjXhZTrXxMapper, ZsJkXmbDjXhZTrXx> implements ZsJkXmbDjXhZTrXxService{

    @Autowired
    private ZsJkXmbDjXhZTrXxMapper zsJkXmbDjXhZTrXxMapper;
    
    @Autowired
    private ZsJkXmbDjXhZTrDataXxMapper zsJkXmbDjXhZTrDataXxMapper;

    @Override
    public void selectDataGrid(PageInfo pageInfo) throws Exception {
       Page<ZsJkXmbDjXhZTrXx> page = new Page<ZsJkXmbDjXhZTrXx>(pageInfo.getNowpage(), pageInfo.getSize());
       Map<String, Object> condition = pageInfo.getCondition();
       List<ZsJkXmbDjXhZTrXx> list = zsJkXmbDjXhZTrXxMapper.selectZsJkXmbDjXhZTrXxList(page,condition);
       pageInfo.setRows(list);
       pageInfo.setTotal(page.getTotal());
    }

    @Override
    public String save(ZsJkXmbDjXhZTrXxDto zsJkXmbDjXhZTrXxDto,User user) throws Exception {
    	  String errorMessage = "";
        ZsJkXmbDjXhZTrXx zsJkXmbDjXhZTrXx = new ZsJkXmbDjXhZTrXx();
        BeanUtils.copyProperties(zsJkXmbDjXhZTrXxDto, zsJkXmbDjXhZTrXx);
    	  zsJkXmbDjXhZTrXxMapper.insert(zsJkXmbDjXhZTrXx);
    	  return errorMessage;
    }

	   @Override
    public String del(String[] ids,User user) throws Exception {
    	  String errorMessage = "";
		  List<String> idList = Arrays.asList(ids);
		  zsJkXmbDjXhZTrXxMapper.deleteBatchIds(idList);
    	  return errorMessage;
    }

    @Override
    public String update(ZsJkXmbDjXhZTrXxDto zsJkXmbDjXhZTrXxDto,User user) throws Exception {
    	  String errorMessage = "";
        ZsJkXmbDjXhZTrXx zsJkXmbDjXhZTrXx = this.zsJkXmbDjXhZTrXxMapper.selectById(zsJkXmbDjXhZTrXxDto.getId());
		  zsJkXmbDjXhZTrXxMapper.updateById(zsJkXmbDjXhZTrXx);
    	  return errorMessage;
    }

	   @Override
	   public ZsJkXmbDjXhZTrXx findById(String id) throws Exception {
		   return zsJkXmbDjXhZTrXxMapper.selectById(id);
	   }

    @Override
    public String checkClumIfexits(ZsJkXmbDjXhZTrXxDto zsJkXmbDjXhZTrXxDto, String[] clumNames) throws Exception {
		   String id = zsJkXmbDjXhZTrXxDto.getId();
		   Map map = BeanUtils.toMap(zsJkXmbDjXhZTrXxDto);
		   Map<String,Object> columnMap = new HashMap<String,Object>();
		   for (String clum : clumNames) {
		        Object ss =  map.get(clum);
		        columnMap.put(clum, ss);
		   }
		   List<ZsJkXmbDjXhZTrXx> lists = zsJkXmbDjXhZTrXxMapper.selectByMap(columnMap);
		   if(StringUtils.isBlank(id)){//新增
			    if(lists!=null&&lists.size()>0)return "当前数据已存在!";
		   }else{//修改
			    for (ZsJkXmbDjXhZTrXx zsJkXmbDjXhZTrXx2 : lists) {
				   String newId = zsJkXmbDjXhZTrXx2.getId();
				   if(!newId.equals(id)){
					   return "当前数据已存在!";
				   }
			    }
		   }
		   return null;
    }

	@Override
	public ZsJkXmbDjXhZTrXxDto getXmDjxhztr(String xlId, String fxnr, String djlx, String qshh, String jzhh)
			throws Exception {
		ZsJkXmbDjXhZTrXxDto dto = new ZsJkXmbDjXhZTrXxDto();
		ZsJkXmbDjXhZTrXx entity = new ZsJkXmbDjXhZTrXx();
		ZsJkXmbDjXhZTrXx param = new ZsJkXmbDjXhZTrXx();
		param.setDjlx(djlx);
		param.setXlId(xlId);
		param.setFxnr(fxnr);
		entity = this.zsJkXmbDjXhZTrXxMapper.selectOne(param);
		if(null != entity){
			BeanUtils.copy(entity, dto);
			Map<String,Object> columnMap = new HashMap<String,Object>();
			columnMap.put("fId", entity.getId());
			columnMap.put("qshh", qshh);
			columnMap.put("jzhh", jzhh);
			List<ZsJkXmbDjXhZTrDataXxDto> items = zsJkXmbDjXhZTrDataXxMapper.selectZsJkXmbDjXhZTrDataXxDtoList(columnMap);
			dto.setItems(items);
		}
		return dto;
	}

}