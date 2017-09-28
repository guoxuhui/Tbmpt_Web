package com.crfeb.tbmpt.zsjk.plc.service.impl;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.crfeb.tbmpt.commons.utils.BeanUtils;
import com.crfeb.tbmpt.commons.utils.PageInfo;
import com.crfeb.tbmpt.commons.utils.StringUtils;
import com.crfeb.tbmpt.sys.model.User;
import com.crfeb.tbmpt.zsjk.plc.mapper.ProPlcTbmdwMapper;
import com.crfeb.tbmpt.zsjk.plc.model.ProPlcTbmdw;
import com.crfeb.tbmpt.zsjk.plc.model.dto.ProPlcTbmdwDto;
import com.crfeb.tbmpt.zsjk.plc.service.IProPlcTbmdwService;
import com.baomidou.framework.service.impl.CommonServiceImpl;
import com.baomidou.mybatisplus.plugins.Page;

/**
 *
 * ProPlcTbmdw 表数据服务层接口实现类
 *
 */
@Service
public class ProPlcTbmdwServiceImpl extends CommonServiceImpl<ProPlcTbmdwMapper, ProPlcTbmdw> implements IProPlcTbmdwService {

	@Autowired
    private ProPlcTbmdwMapper proPlcTbmdwMapper;

    @Override
    public void selectDataGrid(PageInfo pageInfo) throws Exception {
       Page<ProPlcTbmdw> page = new Page<ProPlcTbmdw>(pageInfo.getNowpage(), pageInfo.getSize());
       Map<String, Object> condition = pageInfo.getCondition();
       List<ProPlcTbmdw> list = proPlcTbmdwMapper.selectProPlcTbmdwXxList(page,condition);
       pageInfo.setRows(list);
       pageInfo.setTotal(page.getTotal());
    }

    @Override
    public String save(ProPlcTbmdwDto proPlcTbmdwDto,User user) throws Exception {
    	  String errorMessage = "";
        ProPlcTbmdw proPlcTbmdw = new ProPlcTbmdw();
        BeanUtils.copyProperties(proPlcTbmdwDto, proPlcTbmdw);
    	  proPlcTbmdwMapper.insert(proPlcTbmdw);
    	  return errorMessage;
    }

	   @Override
    public String del(String[] ids,User user) throws Exception {
    	  String errorMessage = "";
		  List<String> idList = Arrays.asList(ids);
		  proPlcTbmdwMapper.deleteBatchIds(idList);
    	  return errorMessage;
    }

    @Override
    public String update(ProPlcTbmdwDto proPlcTbmdwDto,User user) throws Exception {
    	  String errorMessage = "";
        ProPlcTbmdw proPlcTbmdw = this.proPlcTbmdwMapper.selectById(proPlcTbmdwDto.getId());
		  proPlcTbmdwMapper.updateById(proPlcTbmdw);
    	  return errorMessage;
    }

	   @Override
	   public ProPlcTbmdw findById(String id) throws Exception {
		   return proPlcTbmdwMapper.selectById(id);
	   }

    @Override
    public String checkClumIfexits(ProPlcTbmdwDto proPlcTbmdwDto, String[] clumNames) throws Exception {
		   String id = proPlcTbmdwDto.getId();
		   Map map = BeanUtils.toMap(proPlcTbmdwDto);
		   Map<String,Object> columnMap = new HashMap<String,Object>();
		   for (String clum : clumNames) {
		        Object ss =  map.get(clum);
		        columnMap.put(clum, ss);
		   }
		   List<ProPlcTbmdw> lists = proPlcTbmdwMapper.selectByMap(columnMap);
		   if(StringUtils.isBlank(id)){//新增
			    if(lists!=null&&lists.size()>0)return "当前数据已存在!";
		   }else{//修改
			    for (ProPlcTbmdw proPlcTbmdw2 : lists) {
				   String newId = proPlcTbmdw2.getId();
				   if(!newId.equals(id)){
					   return "当前数据已存在!";
				   }
			    }
		   }
		   return null;
    }
}