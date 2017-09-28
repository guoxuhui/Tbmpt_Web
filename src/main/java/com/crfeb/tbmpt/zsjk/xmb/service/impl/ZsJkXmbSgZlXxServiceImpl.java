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
import com.crfeb.tbmpt.commons.utils.DateUtils;
import com.crfeb.tbmpt.commons.utils.StringUtils;
import com.crfeb.tbmpt.sys.model.User;
import com.crfeb.tbmpt.commons.base.BaseService;
import com.crfeb.tbmpt.zsjk.xmb.model.dto.ZsJkXmbSgZlXxDto;
import com.crfeb.tbmpt.zsjk.xmb.mapper.ZsJkXmbSgZlXxMapper;
import com.crfeb.tbmpt.zsjk.xmb.model.ZsJkXmbSgZlXx;
import com.crfeb.tbmpt.zsjk.xmb.service.ZsJkXmbSgZlXxService;
/**
 * <p>项目部角度  结构施工质量问题信息Service实现类</p>
 * <p>系统：展示界面接口</p>
 * <p>模块：项目部角度模块</p>
 * <p>日期：2017-01-09</p>
 * @version 1.0
 * @author YangYJ
 */
@Service
public class ZsJkXmbSgZlXxServiceImpl extends CommonServiceImpl<ZsJkXmbSgZlXxMapper, ZsJkXmbSgZlXx> implements ZsJkXmbSgZlXxService{

    @Autowired
    private ZsJkXmbSgZlXxMapper zsJkXmbSgZlXxMapper;

    @Override
    public void selectDataGrid(PageInfo pageInfo) throws Exception {
       Page<ZsJkXmbSgZlXx> page = new Page<ZsJkXmbSgZlXx>(pageInfo.getNowpage(), pageInfo.getSize());
       Map<String, Object> condition = pageInfo.getCondition();
       List<ZsJkXmbSgZlXx> list = zsJkXmbSgZlXxMapper.selectZsJkXmbSgZlXxList(page,condition);
       pageInfo.setRows(list);
       pageInfo.setTotal(page.getTotal());
    }

    @Override
    public String save(ZsJkXmbSgZlXxDto zsJkXmbSgZlXxDto,User user) throws Exception {
    	  String errorMessage = "";
        ZsJkXmbSgZlXx zsJkXmbSgZlXx = new ZsJkXmbSgZlXx();
        BeanUtils.copyProperties(zsJkXmbSgZlXxDto, zsJkXmbSgZlXx);
    	  zsJkXmbSgZlXxMapper.insert(zsJkXmbSgZlXx);
    	  return errorMessage;
    }

	   @Override
    public String del(String[] ids,User user) throws Exception {
    	  String errorMessage = "";
		  List<String> idList = Arrays.asList(ids);
		  zsJkXmbSgZlXxMapper.deleteBatchIds(idList);
    	  return errorMessage;
    }

    @Override
    public String update(ZsJkXmbSgZlXxDto zsJkXmbSgZlXxDto,User user) throws Exception {
    	  String errorMessage = "";
        ZsJkXmbSgZlXx zsJkXmbSgZlXx = this.zsJkXmbSgZlXxMapper.selectById(zsJkXmbSgZlXxDto.getId());
		  zsJkXmbSgZlXxMapper.updateById(zsJkXmbSgZlXx);
    	  return errorMessage;
    }

	   @Override
	   public ZsJkXmbSgZlXx findById(String id) throws Exception {
		   return zsJkXmbSgZlXxMapper.selectById(id);
	   }

    @Override
    public String checkClumIfexits(ZsJkXmbSgZlXxDto zsJkXmbSgZlXxDto, String[] clumNames) throws Exception {
		   String id = zsJkXmbSgZlXxDto.getId();
		   Map map = BeanUtils.toMap(zsJkXmbSgZlXxDto);
		   Map<String,Object> columnMap = new HashMap<String,Object>();
		   for (String clum : clumNames) {
		        Object ss =  map.get(clum);
		        columnMap.put(clum, ss);
		   }
		   List<ZsJkXmbSgZlXx> lists = zsJkXmbSgZlXxMapper.selectByMap(columnMap);
		   if(StringUtils.isBlank(id)){//新增
			    if(lists!=null&&lists.size()>0)return "当前数据已存在!";
		   }else{//修改
			    for (ZsJkXmbSgZlXx zsJkXmbSgZlXx2 : lists) {
				   String newId = zsJkXmbSgZlXx2.getId();
				   if(!newId.equals(id)){
					   return "当前数据已存在!";
				   }
			    }
		   }
		   return null;
    }

	@Override
	public List<ZsJkXmbSgZlXxDto> sgzl(String startTime, String endtTime, String projectId) throws Exception {
		List<ZsJkXmbSgZlXxDto> dtoList = new ArrayList<ZsJkXmbSgZlXxDto>();
		if(StringUtils.isNotBlank(projectId)){
			if(StringUtils.isBlank(endtTime)){
				endtTime = DateUtils.getTodayLastTime();
			}
			if(StringUtils.isBlank(startTime)){
				startTime = DateUtils.getTodayStartTime();
			}
			Map<String,Object> condition = new HashMap<String,Object>();
			condition.put("startTime", startTime);
			condition.put("endTime", endtTime);
			condition.put("projectId", projectId);
			dtoList = this.zsJkXmbSgZlXxMapper.selectZsJkXmbSgZlXxDtoList(condition);
		}
		return dtoList;
	}

}