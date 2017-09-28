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
import com.crfeb.tbmpt.zsjk.xmb.model.dto.ZsJkXmbGpZlMxXxDto;
import com.crfeb.tbmpt.zsjk.xmb.mapper.ZsJkXmbGpZlMxXxMapper;
import com.crfeb.tbmpt.zsjk.xmb.model.ZsJkXmbGpZlMxXx;
import com.crfeb.tbmpt.zsjk.xmb.service.ZsJkXmbGpZlMxXxService;
/**
 * <p>项目部角度  管片质量问题明细数据信息Service实现类</p>
 * <p>系统：展示界面接口</p>
 * <p>模块：项目部角度模块</p>
 * <p>日期：2017-01-09</p>
 * @version 1.0
 * @author YangYJ
 */
@Service
public class ZsJkXmbGpZlMxXxServiceImpl extends CommonServiceImpl<ZsJkXmbGpZlMxXxMapper, ZsJkXmbGpZlMxXx> implements ZsJkXmbGpZlMxXxService{

    @Autowired
    private ZsJkXmbGpZlMxXxMapper zsJkXmbGpZlMxXxMapper;

    @Override
    public void selectDataGrid(PageInfo pageInfo) throws Exception {
       Page<ZsJkXmbGpZlMxXx> page = new Page<ZsJkXmbGpZlMxXx>(pageInfo.getNowpage(), pageInfo.getSize());
       Map<String, Object> condition = pageInfo.getCondition();
       List<ZsJkXmbGpZlMxXx> list = zsJkXmbGpZlMxXxMapper.selectZsJkXmbGpZlMxXxList(page,condition);
       pageInfo.setRows(list);
       pageInfo.setTotal(page.getTotal());
    }

    @Override
    public String save(ZsJkXmbGpZlMxXxDto zsJkXmbGpZlMxXxDto,User user) throws Exception {
    	  String errorMessage = "";
        ZsJkXmbGpZlMxXx zsJkXmbGpZlMxXx = new ZsJkXmbGpZlMxXx();
        BeanUtils.copyProperties(zsJkXmbGpZlMxXxDto, zsJkXmbGpZlMxXx);
    	  zsJkXmbGpZlMxXxMapper.insert(zsJkXmbGpZlMxXx);
    	  return errorMessage;
    }

	   @Override
    public String del(String[] ids,User user) throws Exception {
    	  String errorMessage = "";
		  List<String> idList = Arrays.asList(ids);
		  zsJkXmbGpZlMxXxMapper.deleteBatchIds(idList);
    	  return errorMessage;
    }

    @Override
    public String update(ZsJkXmbGpZlMxXxDto zsJkXmbGpZlMxXxDto,User user) throws Exception {
    	  String errorMessage = "";
        ZsJkXmbGpZlMxXx zsJkXmbGpZlMxXx = this.zsJkXmbGpZlMxXxMapper.selectById(zsJkXmbGpZlMxXxDto.getId());
		  zsJkXmbGpZlMxXxMapper.updateById(zsJkXmbGpZlMxXx);
    	  return errorMessage;
    }

	   @Override
	   public ZsJkXmbGpZlMxXx findById(String id) throws Exception {
		   return zsJkXmbGpZlMxXxMapper.selectById(id);
	   }

    @Override
    public String checkClumIfexits(ZsJkXmbGpZlMxXxDto zsJkXmbGpZlMxXxDto, String[] clumNames) throws Exception {
		   String id = zsJkXmbGpZlMxXxDto.getId();
		   Map map = BeanUtils.toMap(zsJkXmbGpZlMxXxDto);
		   Map<String,Object> columnMap = new HashMap<String,Object>();
		   for (String clum : clumNames) {
		        Object ss =  map.get(clum);
		        columnMap.put(clum, ss);
		   }
		   List<ZsJkXmbGpZlMxXx> lists = zsJkXmbGpZlMxXxMapper.selectByMap(columnMap);
		   if(StringUtils.isBlank(id)){//新增
			    if(lists!=null&&lists.size()>0)return "当前数据已存在!";
		   }else{//修改
			    for (ZsJkXmbGpZlMxXx zsJkXmbGpZlMxXx2 : lists) {
				   String newId = zsJkXmbGpZlMxXx2.getId();
				   if(!newId.equals(id)){
					   return "当前数据已存在!";
				   }
			    }
		   }
		   return null;
    }

	@Override
	public List<ZsJkXmbGpZlMxXxDto> gpzlwtmxsj(String projectId, String date, String qualityType) throws Exception {
		List<ZsJkXmbGpZlMxXxDto> dtoList = new ArrayList<ZsJkXmbGpZlMxXxDto>();
		List<ZsJkXmbGpZlMxXx> entityList = new ArrayList<ZsJkXmbGpZlMxXx>();
		if(StringUtils.isNotBlank(projectId) && StringUtils.isNotBlank(qualityType)){
			Map<String,Object> columnMap = new HashMap<String,Object>();
			columnMap.put("projectId", projectId);
			columnMap.put("sbDate", date);
			columnMap.put("type", qualityType);
			entityList = this.zsJkXmbGpZlMxXxMapper.selectByMap(columnMap);
			if(null !=  entityList && entityList.size()>0){
				ZsJkXmbGpZlMxXxDto dto = new ZsJkXmbGpZlMxXxDto();
				for(ZsJkXmbGpZlMxXx entity : entityList){
					dto = new ZsJkXmbGpZlMxXxDto();
					BeanUtils.copy(entity, dto);
					dtoList.add(dto);
				}
			}
		}
		return dtoList;
	}

}