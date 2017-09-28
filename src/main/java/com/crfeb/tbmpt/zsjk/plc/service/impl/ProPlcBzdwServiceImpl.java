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
import com.crfeb.tbmpt.zsjk.plc.mapper.ProPlcBzdwMapper;
import com.crfeb.tbmpt.zsjk.plc.model.ProPlcBzdw;
import com.crfeb.tbmpt.zsjk.plc.model.dto.ProPlcBzdwDto;
import com.crfeb.tbmpt.zsjk.plc.service.IProPlcBzdwService;
import com.baomidou.framework.service.impl.CommonServiceImpl;
import com.baomidou.mybatisplus.plugins.Page;

/**
 *
 * ProPlcBzdw 表数据服务层接口实现类
 *
 */
@Service
public class ProPlcBzdwServiceImpl extends CommonServiceImpl<ProPlcBzdwMapper, ProPlcBzdw> implements IProPlcBzdwService {

	@Autowired
    private ProPlcBzdwMapper proPlcBzdwMapper;

    @Override
    public void selectDataGrid(PageInfo pageInfo) throws Exception {
       Page<ProPlcBzdw> page = new Page<ProPlcBzdw>(pageInfo.getNowpage(), pageInfo.getSize());
       Map<String, Object> condition = pageInfo.getCondition();
       List<ProPlcBzdw> list = proPlcBzdwMapper.selectProPlcBzdwXxList(page,condition);
       pageInfo.setRows(list);
       pageInfo.setTotal(page.getTotal());
    }

    @Override
    public String save(ProPlcBzdwDto proPlcBzdwDto,User user) throws Exception {
    	  String errorMessage = "";
        ProPlcBzdw proPlcBzdw = new ProPlcBzdw();
        BeanUtils.copyProperties(proPlcBzdwDto, proPlcBzdw);
    	  proPlcBzdwMapper.insert(proPlcBzdw);
    	  return errorMessage;
    }

	   @Override
    public String del(String[] ids,User user) throws Exception {
    	  String errorMessage = "";
		  List<String> idList = Arrays.asList(ids);
		  proPlcBzdwMapper.deleteBatchIds(idList);
    	  return errorMessage;
    }

    @Override
    public String update(ProPlcBzdwDto proPlcBzdwDto,User user) throws Exception {
    	  String errorMessage = "";
        ProPlcBzdw proPlcBzdw = this.proPlcBzdwMapper.selectById(proPlcBzdwDto.getId());
		  proPlcBzdwMapper.updateById(proPlcBzdw);
    	  return errorMessage;
    }

	   @Override
	   public ProPlcBzdw findById(String id) throws Exception {
		   return proPlcBzdwMapper.selectById(id);
	   }

    @Override
    public String checkClumIfexits(ProPlcBzdwDto proPlcBzdwDto, String[] clumNames) throws Exception {
		   String id = proPlcBzdwDto.getId();
		   Map map = BeanUtils.toMap(proPlcBzdwDto);
		   Map<String,Object> columnMap = new HashMap<String,Object>();
		   for (String clum : clumNames) {
		        Object ss =  map.get(clum);
		        columnMap.put(clum, ss);
		   }
		   List<ProPlcBzdw> lists = proPlcBzdwMapper.selectByMap(columnMap);
		   if(StringUtils.isBlank(id)){//新增
			    if(lists!=null&&lists.size()>0)return "当前数据已存在!";
		   }else{//修改
			    for (ProPlcBzdw proPlcBzdw2 : lists) {
				   String newId = proPlcBzdw2.getId();
				   if(!newId.equals(id)){
					   return "当前数据已存在!";
				   }
			    }
		   }
		   return null;
    }

}